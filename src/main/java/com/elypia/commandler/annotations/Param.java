package com.elypia.commandler.annotations;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Params.class)
public @interface Param {

	/**
	 * The name to display this parameter as.
	 */

	String name();

	/**
	 * A small description of what the parameter is.
	 */

	String help();

	/**
	 * If this parameter is sensitive and should be hidden
	 * as much as possible in the logs, or messages.
	 */

	boolean secret() default false;
}