package com.lx.framework.configure;

import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;


public abstract class BaseAutoConfiguration {

  protected void register(ConfigurableListableBeanFactory beanFactory, Object bean, String name,
      String alias) {
    beanFactory.registerSingleton(name, bean);
    if (!beanFactory.containsSingleton(alias)) {
      beanFactory.registerAlias(name, alias);
    }
  }

  // 读取配置并转换成对象
  protected <T> T resolverSetting(Class<T> clazz, MutablePropertySources propertySources, String prefix) {
    return new Binder(ConfigurationPropertySources.from(propertySources)).bind(prefix, Bindable.of(clazz))
            .orElseThrow(() -> new FatalBeanException("Could not bind properties"));
  }

  // 读取配置并转换成对象
  protected <T> T resolverSetting(Class<T> clazz, MutablePropertySources propertySources) {
    return resolverSetting(clazz, propertySources, "lx");
  }

  // 读取配置并转换成对象
  protected <T> T resolverSetting(Class<T> clazz, Environment environment, String prefix) {
    return Binder.get(environment).bind(prefix, Bindable.of(clazz)).get();
  }

  // 读取配置并转换成对象
  protected <T> T resolverSetting(Class<T> clazz, Environment environment) {
    return resolverSetting(clazz, environment, "lx");
  }
}
