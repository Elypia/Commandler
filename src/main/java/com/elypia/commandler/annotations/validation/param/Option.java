package com.elypia.commandler.annotations.validation.param;

import com.elypia.commandler.annotations.Validation;

import java.lang.annotation.*;

/**
 * Limit a String parameter to a certain set of values.
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Validation("./resources/params/option.svg")
public @interface Option {

    /**
     * @return The only values this paremeter may be.
     * The check is non-case sensitive.
     */

    String[] value();
}
