
package com.lx.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.dozer.MappingException;
import org.dozer.fieldmap.HintContainer;
import org.dozer.propertydescriptor.DeepHierarchyElement;
import org.dozer.util.DozerConstants;
import org.dozer.util.MappingUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Create Date: 2018年4月28日 上午9:05:59
 * 
 * @version: V3.0.1
 * @author: zhao wei
 */
@Slf4j
public class ReflectUtils {

	public static final String CGLIB_CLASS_SEPARATOR = "$$";

	public static final String IAE_MESSAGE = "argument type mismatch";

	public static Class<?> getUserClass(Object instance) {
		Validate.notNull(instance, "Instance must not be null");
		Class clazz = instance.getClass();
		if ((clazz != null) && clazz.getName().contains(CGLIB_CLASS_SEPARATOR)) {
			Class<?> superClass = clazz.getSuperclass();
			if ((superClass != null) && !Object.class.equals(superClass)) {
				return superClass;
			}
		}
		return clazz;

	}

	/**
	 * 获取指定类型的指定位置的泛型实参
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> findParameterizedType(Class<?> clazz, int index) {
		Type parameterizedType = clazz.getGenericSuperclass();
		// CGLUB subclass target object(泛型在父类上)
		if (!(parameterizedType instanceof ParameterizedType)) {
			parameterizedType = clazz.getSuperclass().getGenericSuperclass();
		}
		if (!(parameterizedType instanceof ParameterizedType)) {
			return null;
		}
		Type[] actualTypeArguments = ((ParameterizedType) parameterizedType).getActualTypeArguments();
		if (actualTypeArguments == null || actualTypeArguments.length == 0) {
			return null;
		}
		return (Class<T>) actualTypeArguments[0];
	}

	public static PropertyDescriptor findPropertyDescriptor(Class<?> objectClass, String fieldName, HintContainer deepIndexHintContainer) {
		PropertyDescriptor result = null;
		if (MappingUtils.isDeepMapping(fieldName)) {
			DeepHierarchyElement[] hierarchy = getDeepFieldHierarchy(objectClass, fieldName, deepIndexHintContainer);
			result = hierarchy[hierarchy.length - 1].getPropDescriptor();
		} else {
			PropertyDescriptor[] descriptors = getPropertyDescriptors(objectClass);

			if (descriptors != null) {
				int size = descriptors.length;
				for (int i = 0; i < size; i++) {

					/*
					 * Bugfix #2826468. if object class has methods, f.e, getValue() and getValue(int index) in this case could happen that this field
					 * couldn't be mapped, because getValue(int index) becomes first and PropertyDescriptor.getReadMethod() returns null. We need to
					 * exclude IndexedPropertyDescriptor from search. At this time dozer dosen't support mappings from indexed fields from POJO.
					 * 
					 * See KnownFailures.testIndexedGetFailure()
					 */
					// TODO Disables for now as it breaks indexed array mapping
					// if (descriptors[i] instanceof IndexedPropertyDescriptor) {
					// continue;
					// }

					String propertyName = descriptors[i].getName();
					Method readMethod = descriptors[i].getReadMethod();
					if (fieldName.equals(propertyName)) {
						return fixGenericDescriptor(objectClass, descriptors[i]);
					}

					if (fieldName.equalsIgnoreCase(propertyName)) {
						result = descriptors[i];
					}
				}
			}
		}

		return result;
	}

	/**
	 * There are some nasty bugs for introspection with generics. This method addresses those nasty bugs and tries to find proper methods if available
	 * http://bugs.sun.com/view_bug.do?bug_id=6788525 http://bugs.sun.com/view_bug.do?bug_id=6528714
	 * 
	 * @param descriptor
	 * @return
	 */
	public static PropertyDescriptor fixGenericDescriptor(Class<?> clazz, PropertyDescriptor descriptor) {
		Method readMethod = descriptor.getReadMethod();
		Method writeMethod = descriptor.getWriteMethod();

		if (readMethod != null && (readMethod.isBridge() || readMethod.isSynthetic())) {
			String propertyName = descriptor.getName();
			// capitalize the first letter of the string;
			String baseName = Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1);
			String setMethodName = "set" + baseName;
			String getMethodName = "get" + baseName;
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				if (method.getName().equals(getMethodName) && !method.isBridge() && !method.isSynthetic()) {
					try {
						descriptor.setReadMethod(method);
					} catch (IntrospectionException e) {
						// move on
					}
				}
				if (method.getName().equals(setMethodName) && !method.isBridge() && !method.isSynthetic()) {
					try {
						descriptor.setWriteMethod(method);
					} catch (IntrospectionException e) {
						// move on
					}
				}
			}
		}
		return descriptor;
	}

	public static DeepHierarchyElement[] getDeepFieldHierarchy(Class<?> parentClass, String field, HintContainer deepIndexHintContainer) {
		if (!MappingUtils.isDeepMapping(field)) {
			MappingUtils.throwMappingException("Field does not contain deep field delimitor");
		}

		StringTokenizer toks = new StringTokenizer(field, DozerConstants.DEEP_FIELD_DELIMITER);
		Class<?> latestClass = parentClass;
		DeepHierarchyElement[] hierarchy = new DeepHierarchyElement[toks.countTokens()];
		int index = 0;
		int hintIndex = 0;
		while (toks.hasMoreTokens()) {
			String aFieldName = toks.nextToken();
			String theFieldName = aFieldName;
			int collectionIndex = -1;

			if (aFieldName.contains("[")) {
				theFieldName = aFieldName.substring(0, aFieldName.indexOf("["));
				collectionIndex = Integer.parseInt(aFieldName.substring(aFieldName.indexOf("[") + 1, aFieldName.indexOf("]")));
			}

			PropertyDescriptor propDescriptor = findPropertyDescriptor(latestClass, theFieldName, deepIndexHintContainer);
			DeepHierarchyElement r = new DeepHierarchyElement(propDescriptor, collectionIndex);

			if (propDescriptor == null) {
				MappingUtils.throwMappingException("Exception occurred determining deep field hierarchy for Class --> " + parentClass.getName()
						+ ", Field --> " + field + ".  Unable to determine property descriptor for Class --> " + latestClass.getName()
						+ ", Field Name: " + aFieldName);
			}

			latestClass = propDescriptor.getPropertyType();
			if (toks.hasMoreTokens()) {
				if (latestClass.isArray()) {
					latestClass = latestClass.getComponentType();
				} else if (Collection.class.isAssignableFrom(latestClass)) {
					Class<?> genericType = determineGenericsType(propDescriptor);

					if (genericType == null && deepIndexHintContainer == null) {
						MappingUtils.throwMappingException(
								"Hint(s) or Generics not specified.  Hint(s) or Generics must be specified for deep mapping with indexed field(s). Exception occurred determining deep field hierarchy for Class --> "
										+ parentClass.getName() + ", Field --> " + field + ".  Unable to determine property descriptor for Class --> "
										+ latestClass.getName() + ", Field Name: " + aFieldName);
					}
					if (genericType != null) {
						latestClass = genericType;
					} else {
						latestClass = deepIndexHintContainer.getHint(hintIndex);
						hintIndex += 1;
					}
				}
			}
			hierarchy[index++] = r;
		}

		return hierarchy;
	}

	public static Method getMethod(Object obj, String methodName) {
		return getMethod(obj.getClass(), methodName);
	}

	public static Method getMethod(Class<?> clazz, String methodName) {
		Method result = findMethod(clazz, methodName);
		if (result == null) {
			MappingUtils.throwMappingException("No method found for class:" + clazz + " and method name:" + methodName);
		}
		return result;
	}

	public static Method findMethod(Class<?> clazz, String methodName) {
		Method[] methods = clazz.getMethods();
		Method result = null;
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				result = method;
			}
		}
		return result;
	}

	public static Method findAMethod(Class<?> clazz, String methodName) throws NoSuchMethodException {
		StringTokenizer tokenizer = new StringTokenizer(methodName, "(");
		String m = tokenizer.nextToken();
		Method result;
		// If tokenizer has more elements, it mean that parameters may have been specified
		if (tokenizer.hasMoreElements()) {
			StringTokenizer tokens = new StringTokenizer(tokenizer.nextToken(), ")");
			String params = tokens.hasMoreTokens() ? tokens.nextToken() : null;
			result = findMethodWithParam(clazz, m, params);
		} else {
			result = findMethod(clazz, methodName);
		}
		if (result == null) {
			throw new NoSuchMethodException(clazz.getName() + "." + methodName);
		}
		return result;
	}

	public static Method findMethodWithParam(Class<?> parentDestClass, String methodName, String params) throws NoSuchMethodException {
		List<Class<?>> list = new ArrayList<Class<?>>();
		if (params != null) {
			StringTokenizer tokenizer = new StringTokenizer(params, ",");
			while (tokenizer.hasMoreTokens()) {
				String token = tokenizer.nextToken();
				list.add(MappingUtils.loadClass(token));
			}
		}
		return getMethod(parentDestClass, methodName, list.toArray(new Class[list.size()]));
	}

	public static PropertyDescriptor[] getPropertyDescriptors(Class<?> objectClass) {
		// If the class is an interface, use custom method to get all prop descriptors in the inheritance hierarchy.
		// PropertyUtils.getPropertyDescriptors() does not work correctly for interface inheritance. It finds props in the
		// actual interface ok, but does not find props in the inheritance hierarchy.
		if (objectClass.isInterface()) {
			return getInterfacePropertyDescriptors(objectClass);
		} else {
			return PropertyUtils.getPropertyDescriptors(objectClass);
		}
	}

	static PropertyDescriptor[] getInterfacePropertyDescriptors(Class<?> interfaceClass) {
		List<PropertyDescriptor> propDescriptors = new ArrayList<PropertyDescriptor>();
		// Add prop descriptors for interface passed in
		propDescriptors.addAll(Arrays.asList(PropertyUtils.getPropertyDescriptors(interfaceClass)));

		// Look for interface inheritance. If super interfaces are found, recurse up the hierarchy tree and add prop
		// descriptors for each interface found.
		// PropertyUtils.getPropertyDescriptors() does not correctly walk the inheritance hierarchy for interfaces.
		Class<?>[] interfaces = interfaceClass.getInterfaces();
		if (interfaces != null) {
			for (Class<?> superInterfaceClass : interfaces) {
				List<PropertyDescriptor> superInterfacePropertyDescriptors = Arrays.asList(getInterfacePropertyDescriptors(superInterfaceClass));
				/*
				 * #1814758 Check for existing descriptor with the same name to prevent 2 property descriptors with the same name being added to the
				 * result list. This caused issues when getter and setter of an attribute on different interfaces in an inheritance hierarchy
				 */
				for (PropertyDescriptor superPropDescriptor : superInterfacePropertyDescriptors) {
					PropertyDescriptor existingPropDescriptor = findPropDescriptorByName(propDescriptors, superPropDescriptor.getName());
					if (existingPropDescriptor == null) {
						propDescriptors.add(superPropDescriptor);
					} else {
						try {
							if (existingPropDescriptor.getReadMethod() == null) {
								existingPropDescriptor.setReadMethod(superPropDescriptor.getReadMethod());
							}
							if (existingPropDescriptor.getWriteMethod() == null) {
								existingPropDescriptor.setWriteMethod(superPropDescriptor.getWriteMethod());
							}
						} catch (IntrospectionException e) {
							throw new MappingException(e);
						}

					}
				}
			}
		}
		return propDescriptors.toArray(new PropertyDescriptor[propDescriptors.size()]);
	}

	public static PropertyDescriptor findPropDescriptorByName(List<PropertyDescriptor> propDescriptors, String name) {
		PropertyDescriptor result = null;
		for (PropertyDescriptor pd : propDescriptors) {
			if (pd.getName().equals(name)) {
				result = pd;
				break;
			}
		}
		return result;
	}

	public static Field getFieldFromBean(Class<?> clazz, String fieldName) {
		return getFieldFromBean(clazz, fieldName, clazz);
	}

	public static Field getFieldFromBean(Class<?> clazz, String fieldName, final Class<?> originalClass) {
		try {
			Field field = clazz.getDeclaredField(fieldName);
			// Allow access to private instance var's that dont have public setter.
			field.setAccessible(true);
			return field;
		} catch (NoSuchFieldException e) {
			if (clazz.getSuperclass() != null) {
				return getFieldFromBean(clazz.getSuperclass(), fieldName, originalClass);
			}
			throw new MappingException("No such field found " + originalClass.getName() + "." + fieldName, e);
		}
	}

	public static Object invoke(Method method, Object obj, Object... args) {
		Object result = null;
		try {
			result = method.invoke(obj, args);
		} catch (IllegalArgumentException e) {

			if (e.getMessage().equals(IAE_MESSAGE)) {
				MappingUtils.throwMappingException(prepareExceptionMessage(method, args), e);
			}
			MappingUtils.throwMappingException(e);
		} catch (IllegalAccessException e) {
			MappingUtils.throwMappingException(e);
		} catch (InvocationTargetException e) {
			MappingUtils.throwMappingException(e);
		}
		return result;
	}

	public static String prepareExceptionMessage(Method method, Object[] args) {
		StringBuffer message = new StringBuffer("Illegal object type for the method '" + method.getName() + "'. \n ");
		message.append("Expected types: \n");
		for (Class<?> type : method.getParameterTypes()) {
			message.append(type.getName());
		}
		message.append("\n Actual types: \n");
		for (Object param : args) {
			message.append(param.getClass().getName());
		}
		return message.toString();
	}

	public static Method getMethod(Class<?> clazz, String name, Class<?>[] parameterTypes) throws NoSuchMethodException {
		return clazz.getMethod(name, parameterTypes);
	}

	public static <T> T newInstance(Class<T> clazz) {
		T result = null;
		try {
			result = clazz.newInstance();
		} catch (InstantiationException e) {
			MappingUtils.throwMappingException(e);
		} catch (IllegalAccessException e) {
			MappingUtils.throwMappingException(e);
		}
		return result;
	}

	public static Class<?> determineGenericsType(PropertyDescriptor propDescriptor) {
		Class<?> result = null;
		// Try getter and setter to determine the Generics type in case one does not exist
		if (propDescriptor.getWriteMethod() != null) {
			result = determineGenericsType(propDescriptor.getWriteMethod(), false);
		}

		if (result == null && propDescriptor.getReadMethod() != null) {
			result = determineGenericsType(propDescriptor.getReadMethod(), true);
		}

		return result;
	}

	public static Class<?> determineGenericsType(Method method, boolean isReadMethod) {
		Class<?> result = null;
		if (isReadMethod) {
			Type parameterType = method.getGenericReturnType();
			result = determineGenericsType(parameterType);
		} else {
			Type[] parameterTypes = method.getGenericParameterTypes();
			if (parameterTypes != null) {
				result = determineGenericsType(parameterTypes[0]);
			}
		}

		return result;
	}

	public static Class<?> determineGenericsType(Type type) {
		Class<?> result = null;
		if (type != null && ParameterizedType.class.isAssignableFrom(type.getClass())) {
			Type genericType = ((ParameterizedType) type).getActualTypeArguments()[0];
			if (genericType != null) {
				result = (Class<?>) genericType;
			}
		}
		return result;
	}

	public static Method getNonVoidSetter(Class<?> clazz, String fieldName) {
		String methodName = "set" + StringUtils.capitalize(fieldName);
		for (Method method : clazz.getMethods()) {
			if (method.getName().equals(methodName)) {
			}
			if (method.getName().equals(methodName) && method.getParameterTypes().length == 1 && method.getReturnType() != Void.TYPE) {
				return method;
			}
		}
		return null;
	}

	public static Method getVoidSetter(Class<?> clazz, String fieldName) {
		String methodName = "set" + StringUtils.capitalize(fieldName);
		for (Method method : clazz.getMethods()) {
			if (method.getName().equals(methodName)) {
			}
			if (method.getName().equals(methodName) && method.getParameterTypes().length == 1 && method.getReturnType() == Void.TYPE) {
				return method;
			}
		}
		return null;
	}

	public static void setFieldSetterValue(String fieldName, Object obj, Object value) {
		Method method = getVoidSetter(obj.getClass(), fieldName);
		invoke(method, obj, value);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static Method getGetter(Class<?> clazz, String fieldName) {
		Field field;
		try {
			field = getField(clazz, fieldName);
		} catch (Exception e) {
			return null;
		}
		if(field == null)return null;
		Class fieldClass = field.getType();
		String methodName = null;
		if (fieldClass.isAssignableFrom(Boolean.class)&&fieldClass.isPrimitive()) {
			methodName = "is" + StringUtils.capitalize(fieldName);
		}else {
			methodName = "get" + StringUtils.capitalize(fieldName);
		}
		log.debug("getGetter:获取到属性{"+fieldName+"}的getter方法的名称为{"+methodName+"}");
		for (Method method : clazz.getMethods()) {
			if (method.getName().equals(methodName)) {
				return method;
			}
		}
		log.debug("getGetter:根据方法名{"+methodName+"}没有获取到方法");
		return null;
	}

	public static String getFieldValue(String fieldName, Object obj) {
		Method method = getGetter(obj.getClass(), fieldName);
		Object object = invoke(method, obj, null);
		if (null != object)
			return object.toString();
		return null;
	}

	public static Field getFieldByGetMethod(Class<?> clazz, Method getMethod) {
		String getMethodName = getMethod.getName();
		String fieldName = getMethodName.substring(3, 4).toLowerCase() + getMethodName.substring(4);
		return getFieldFromBean(clazz, fieldName);
	}
	/**
	 * 复制对象的属性到map中
	 * Lian weimao CreateTime:2018年8月3日 下午1:44:18
	 * @param source 对象
	 * @param fields 需要赋值的属性名
	 * @param keys 属性对应到map中的key,若为null，则默认使用属性名作为key
	 * @return
	 */
	public static Map<String, Object> copyFieldToMap(Object source,List<String> fields,List<String> keys){
		if(source == null || fields == null || keys!=null && keys.size() != fields.size()) {
			return null;
		}
		Map<String, Object> map = new HashMap<>();
		for (int i = 0; i < fields.size(); i++) {
			String fieldName = fields.get(i);
			String key = keys==null?fieldName:keys.get(i);
			try {
				Method getter = getGetter(source.getClass(), fieldName);
				Object value = getter.invoke(source,null);
				map.put(key, value);
			} catch (IllegalAccessException | IllegalArgumentException	| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	/**
	 * 复制对象的属性到map中
	 * Lian weimao CreateTime:2018年8月3日 下午1:44:18
	 * @param source 对象
	 * @param fields 需要赋值的属性名,属性名同时会作为map的key使用
	 * @return
	 */
	public static Map<String, Object> copyFieldToMap(Object source,List<String> fields){
		return copyFieldToMap(source,fields,null);
	}
	/**
	 * 检查对象的属性，是否为null值
	 * Lian weimao CreateTime:2018年9月19日 下午1:53:18
	 * @param obj 要检查的对象
	 * @param ignoreFields 忽略的属性，这里的属性对应的值不会被检查
	 * @return
	 */
	public static boolean fieldsAllNull(Object obj,String...ignoreFields) {
		Field[] fields = obj.getClass().getDeclaredFields();
		List<String> ignoreFieldsList = Arrays.asList(ignoreFields);
		List<String> needCheckFieldsList = Stream.of(fields)
				.map(Field::getName)
				.filter(fName->!ignoreFieldsList.contains(fName))
				.collect(Collectors.toList());
		for (String fName : needCheckFieldsList) {
			Object fieldValue = null;
			try {
				fieldValue = getGetter(obj.getClass(), fName).invoke(obj);
			} catch (IllegalAccessException | IllegalArgumentException	| InvocationTargetException e) {
				e.printStackTrace();
				continue;
			}
			if(fieldValue != null)return false;
		}
		return true;
		
	}
	public static Field[] getAllFields(Class<?> clazz) {
		List<Field> fieldList = new ArrayList<>();
		Class temp = clazz;
		while(temp != null) {
			fieldList.addAll(Arrays.asList(temp.getDeclaredFields()));
			temp = temp.getSuperclass();
		}
		Field[] fields = new Field[fieldList.size()];
		return fieldList.toArray(fields);
	}
	
	public static Field getField(Class<?> clazz,String fieldName) {
		return Arrays.asList(getAllFields(clazz))
				.stream()
				.filter(field->fieldName.equals(field.getName()))
				.findFirst()
				.orElse(null);
	}
}
