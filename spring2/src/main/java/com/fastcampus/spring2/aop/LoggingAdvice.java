package com.fastcampus.spring2.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// (Advice)부가기능 클래스

@Component
@Aspect
public class LoggingAdvice {

	@Around("execution(* com.fastcampus.spring2.aop.MyMath.*(..))")		// pointcut - 부가기능이 적용될 메서드의 패턴
	public Object methodCallLog(ProceedingJoinPoint pjp) throws Throwable{
		
		long start = System.currentTimeMillis();
		System.out.println("<<[start] " + pjp.getSignature().getName() + Arrays.toString(pjp.getArgs()));
		
		Object result = pjp.proceed();	// target의 메서드를 호출.
		
		System.out.println("result = " + result);
		System.out.println("[end]>> " + (System.currentTimeMillis() - start) + "ms");
		
		return result;
		
	}
}
