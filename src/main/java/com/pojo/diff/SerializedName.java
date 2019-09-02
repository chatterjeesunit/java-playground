package com.pojo.diff;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SerializedName {

    /**
     * Name of the field to be added in Diff Result.
     * @return Field Name
     */
    String name() default "";

}