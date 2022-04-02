package com.github.fahdarhalai.dac.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@java.lang.annotation.Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.SOURCE)
public @interface Partition {

    String sourceColumn() default "";
    String targetColumn() default "";
    String sourceTable() default "";
    String targetTable() default "";

    @java.lang.annotation.Target(value = ElementType.FIELD)
    @Retention(value = RetentionPolicy.SOURCE)
    @interface Source {
        String value();
    }

    @java.lang.annotation.Target(value = ElementType.FIELD)
    @Retention(value = RetentionPolicy.SOURCE)
    @interface Target {
        String value();
    }
}
