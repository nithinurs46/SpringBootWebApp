package com.springweb.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// execution -- matches all the methods in the class

	@Pointcut("execution(* com.springweb.controller.UserController.*(..))")
	public void userControllerMethods() {
	}

	@Before("userControllerMethods()")
	public void beforeExecution(JoinPoint joinPoint) {
		logger.info("invoking method: " + joinPoint);
	}
	
	@Pointcut("execution(* com.springweb.svcImpl.UserSvcImpl.getAllUsers())")
	public void forGetAllUsers() {
	}

	@Before("forGetAllUsers()")
	public void beforeGetAllUsers(JoinPoint joinPoint) {
		logger.info("invoking method *****: " + joinPoint);
	}
	
	@Pointcut("execution(* com.springweb.svcImpl.UserSvcImpl.addUser(..))")
	public void forAddUser() {
	}
	
	@Before("forAddUser()")
	public void beforeAddUser(JoinPoint joinPoint) {
		logger.info("Adding new user *****: " + joinPoint.getArgs());
	}
	
	@Pointcut("within(com.springweb.svcImpl..*)")
	public void forUserRepo() {
	}
	
	@After("forUserRepo()")
	public void userRepo(JoinPoint joinPoint) {
		logger.info("Deleting user *****: " + joinPoint.getArgs());
	}
	
	@Around("execution(* com.springweb.svcImpl.UserSvcImpl.getAllUsers())")
	public Object userAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
		logger.info("Advice before the method...");
		logger.info("Adding new user --kind--*****: " + joinPoint.getKind());
		logger.info("Adding new user --args--*****: " + joinPoint.getArgs());
		logger.info("Adding new user --target--*****: " + joinPoint.getTarget());
		Object[] intA = joinPoint.getArgs();
		for(int i=0;i<intA.length;i++){
			System.out.println(intA[i]);
		}
		Object returnVal = joinPoint.proceed();
		logger.info("Advice after the method... "+joinPoint.getSignature().getName());
		return returnVal;
	}

}
