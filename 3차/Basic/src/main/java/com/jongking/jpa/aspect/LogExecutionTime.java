package com.jongking.jpa.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 어디에 붙을 수 있는 지
@Retention(RetentionPolicy.RUNTIME) // 어느시점까지 프로그램에 존재할 것 인가
public @interface LogExecutionTime {

}
