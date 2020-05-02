package ru.itis.lifecarespring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Pointcut("execution(public * ru.itis.lifecarespring.services..*.*(..))")
	public void callAtServicesPublic(){}

	@Before("callAtServicesPublic()")
	public void beforeCallAt(JoinPoint point){
		String targetClass = point.getTarget().getClass().getName();
		String method = ((MethodSignature)(point.getSignature())).getMethod().getName();
		String args = Arrays.stream(point.getArgs()).map(arg -> arg.toString()).collect(Collectors.joining(", "));
		logger.info("Class: " + targetClass + "; Method: " +  method +  "; Args: {" + args + "}");
	}

}
