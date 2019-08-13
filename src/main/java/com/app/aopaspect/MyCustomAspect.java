package com.app.aopaspect;

import com.app.aopaspect.CustomAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by sunitc on 9/25/17.
 */
@Aspect
@Component
public class MyCustomAspect {

    @Around("@annotation(com.app.aopaspect.CustomAnnotation)")
    public void testingOne(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("****** Join Point Intercepted ******");
        System.out.println("Method Arguments = ");
        for (Object arg : joinPoint.getArgs()) {
            System.out.println("\t" + arg);
        }

//        System.out.println(joinPoint.getTarget().getClass().getSimpleName());

        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        CustomAnnotation annotation = method.getAnnotation(CustomAnnotation.class);
        System.out.println("Annotation Bean Name = " + annotation.beanName());

        Object object = joinPoint.proceed();

        System.out.println("\tFinal Object = " + object);

        return;

    }

}
