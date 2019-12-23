
package com.lx.common.transfer;


import com.lx.common.entity.AbstractEntity;
import com.lx.common.util.ReflectUtils;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * Create Date: 2018年5月18日 下午5:26:19
 * 
 * @version: V3.0.1
 * @author: zhao wei
 */
public class TransferObject<T extends AbstractEntity> implements Serializable {

	private static final long serialVersionUID = 6414339399817474755L;

	public void toObject(T obj) {
		BeanUtils.copyProperties(this, obj);

	};

	public T toObject(Class<T> clazz) {
		T entity = ReflectUtils.newInstance(clazz);
		if (null != entity) {
			toObject(entity);
		}
		return entity;
	};

	public void convert(T obj) {
		BeanUtils.copyProperties(obj, this);
	}


}
