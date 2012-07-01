package com.naver.blog.inter999.simpletesttool.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
	public enum ActiveType {
		ACTIVE, INACTIVE
	}

	String TestGroup() default "";

	ActiveType IsActive() default ActiveType.ACTIVE;

	String DataProvider() default "";
}