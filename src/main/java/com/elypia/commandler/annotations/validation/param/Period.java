package com.elypia.commandler.annotations.validation.param;

import com.elypia.commandler.annotations.Validation;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Validation
public @interface Period {

    long min() default 0;
    long max() default Long.MAX_VALUE;
}