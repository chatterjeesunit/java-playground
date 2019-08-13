package com.app.person.aop;

import com.app.person.annotation.CustomPermissionChecker;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by sunitc on 9/25/17.
 */
@Aspect
@Component
public class CustomPermissionAspect {

    @Before("@annotation(com.app.person.annotation.CustomPermissionChecker)")
    public void testingOne(JoinPoint joinPoint) throws Throwable {
        System.out.println("Testing one.... intercepted");
        System.out.println(joinPoint.getSignature());
        System.out.println(joinPoint.getArgs());
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        CustomPermissionChecker permissionChecker = method.getAnnotation(CustomPermissionChecker.class);
        System.out.println(permissionChecker.className() + " : " + permissionChecker.permissionList());

    }

//    @Before("execution(* com.app.person.service..*.*(..))")
//    public void testingTwo(JoinPoint joinPoint) {
//        System.out.println("Testing two.... intercepted");
//        System.out.println(joinPoint.getSignature());
//        System.out.println(joinPoint.getArgs());
//    }

}
