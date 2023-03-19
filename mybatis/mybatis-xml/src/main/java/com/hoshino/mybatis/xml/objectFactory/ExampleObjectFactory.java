package com.hoshino.mybatis.xml.objectFactory;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * @author Yy_hoshino
 * @date 2021-05-06 1:40
 */
public class ExampleObjectFactory extends DefaultObjectFactory {

    public <T> T create(Class<T> type) {
        return super.create(type);
    }

    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        return super.create(type, constructorArgTypes, constructorArgs);
    }

    public void setProperties(Properties properties) {
        super.setProperties(properties);
    }

    public <T> boolean isCollection(Class<T> type) {
        return Collection.class.isAssignableFrom(type);
    }

}
