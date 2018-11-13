package com.viettel.asset.exception;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.context.request.RequestContextHolder;


@SuppressWarnings("unused")
@Aspect
public class AopLoggingInterceptor {
//	@Autowired(required=true)
//	private HttpServletRequest request;
	@Around("execution(* com.viettel.asset.rest..*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

		System.out.println("logAround() is running!");
		System.out.println("hijacked method : " + joinPoint.getSignature().getName());
		System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));
//		HttpServletRequest request2 = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//	System.out.println(request.getRemoteAddr());
		System.out.println("Around before is running!");
		Object result =joinPoint.proceed();
		System.out.println("Around after is running!");
		
		System.out.println("******");
		return result;

	}

//	@Override
//	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
//		System.out.println("Method name : "
//				+ methodInvocation.getMethod().getName());
//		System.out.println("Method arguments : "
//				+ Arrays.toString(methodInvocation.getArguments()));
//
//		// same with MethodBeforeAdvice
//		System.out.println("HijackAroundMethod : Before method hijacked!");
//
//		try {
//			// proceed to original method call
//			Object result = methodInvocation.proceed();
//
//			// same with AfterReturningAdvice
//			System.out.println("HijackAroundMethod : Before after hijacked!");
//
//			return result;
//
//		} catch (IllegalArgumentException e) {
//			// same with ThrowsAdvice
//			System.out.println("HijackAroundMethod : Throw exception hijacked!");
//			throw e;
//		}
//	}

}
