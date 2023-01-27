package com.es.mindata.heroeschallenge.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@EnableAspectJAutoProxy
@Component
@Slf4j
public class ExecutionTimeAspect {

	@Around("@annotation(LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
		long startTime = System.currentTimeMillis();
		try{
			return joinPoint.proceed();
		}finally {
			String signature = joinPoint.getSignature().getName();
			String method = joinPoint.getSignature().getDeclaringTypeName();
			log.info("Method: {} executed in: {} milliseconds", method + "." + signature, System.currentTimeMillis() - startTime);
		}
	}
}
