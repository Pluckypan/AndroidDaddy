package com.echo.camera;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class Aspecter {

    @After("call(* com.echo.camera.Person.toString())")
    public void aFunc(JoinPoint joinPoint) {
        Log.d("aop", "Aspecter aFunc exec");
    }

    @After("call(* com.echo.camera.Person.format(..))")
    public void bFunc(JoinPoint joinPoint) {
        String args = Arrays.toString(joinPoint.getArgs());
        Log.d("aop", "Aspecter bFunc exec args=" + args);
    }

    @Pointcut("execution(@com.echo.camera.Anno * *(..)) && @annotation(ann)")
    public void testAnno(Anno ann) {
        Log.i("aop", "Aspecter testAnno");
    }

    @Around("testAnno(ann)")
    public Object check(ProceedingJoinPoint joinPoint, Anno ann) throws Throwable {
        String args = Arrays.toString(joinPoint.getArgs());
        Object result;
        if (ann.modify()) {
            Object[] newArgs = {9, 7};
            result = joinPoint.proceed(newArgs);
        } else {
            result = joinPoint.proceed();
        }
        Log.i("aop", "Aspecter check args=" + args + " result=" + result + " aTag=" + ann.aTag() + " modify=" + ann.modify());
        return result;
    }
}