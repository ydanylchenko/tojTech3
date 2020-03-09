package com.ydanylchenko.configuration.reader.properties;

import com.ydanylchenko.configuration.reader.exceptions.TestsConfigurationException;
import com.ydanylchenko.configuration.reader.Config;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Properties;

/**
 * Class for loading base tests properties. It gets properties - system or from file (by specified names) and sets it to fields of TestConfig object
 */
public class PropertiesLoader {

    /**
     * Sets TestConfig object fields values to specified properties values
     *
     * @param config {@link Config} object
     */
    public static void populate(Config config) {
        Properties properties = System.getProperties();
        PropertyFile propertyFileAnnotation = config.getClass().getAnnotation(PropertyFile.class);
        String propertyFileName = propertyFileAnnotation.value();
        Properties loadedProperties = new Properties();
        try {
            InputStream stream = PropertiesLoader.class.getClassLoader().getResourceAsStream(propertyFileName);
            if (stream != null) {
                loadedProperties.load(stream);
                for (Map.Entry<Object, Object> loadedProperty : loadedProperties.entrySet()) {
                    properties.putIfAbsent(loadedProperty.getKey(), loadedProperty.getValue());
                }
            } else {
                throw new TestsConfigurationException("Unable to read property file with name '" + propertyFileName + "' - file not found");
            }
        } catch (IOException e) {
            throw new TestsConfigurationException("Error while reading property file with name '" + propertyFileName + "' : " + e.getMessage(), e);
        }
        Field[] fields = config.getClass().getDeclaredFields();
        for (Field field : fields) {
            Property propertyAnnotation = field.getAnnotation(Property.class);
            if (propertyAnnotation != null) {
                String propertyName = propertyAnnotation.value();
                if (propertyName == null) {
                    throw new TestsConfigurationException("Property value cannot be empty. Field name : " + field.getName());
                }
                String propertyValue = properties.getProperty(propertyName);
                if (propertyValue != null) {
                    try {
                        field.setAccessible(true);
                        field.set(config, propertyValue);
                    } catch (IllegalAccessException e) {
                        throw new TestsConfigurationException("Field cannot be set...", e);
                    }
                }
            }
        }
    }
}
