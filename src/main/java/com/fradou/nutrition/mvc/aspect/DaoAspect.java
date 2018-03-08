package com.fradou.nutrition.mvc.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DaoAspect {
	
	public static final Logger LOG = Logger.getLogger(DaoAspect.class.getName()); 

	@Pointcut("execution(* com.fradou.nutrition.mvc.dao.*..*(..))")
	public void daoPacketPointcut() {}

	@AfterThrowing(pointcut="daoPacketPointcut()", throwing="errorThrow")
	public void onDaoThrow(JoinPoint jp, Throwable errorThrow) {
		System.out.println("Erreur dans le DAO : " + jp.getSignature() + errorThrow.getMessage());
	}
	
	@After(value="daoPacketPointcut()")
	public void onDaoReturn() {
		System.out.println("On est revenu");
	}
}
