package com.ydanylchenko.configuration.reader.properties;

import com.ydanylchenko.configuration.reader.Config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotates field of {@link Config} fields for setting to this field
 * value from properties - system or file(about file you could find information in {@link PropertyFile} class javadoc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Property {
    String value() default "";
}
