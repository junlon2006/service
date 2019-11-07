package com.github.junlon2006.microservice.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author junlon2006
 * @date 2019-08-03 15:15:00
 * @since jdk8
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {
    /**
     * AOP切点，监控所有com.github.junlon2006.microservice.controller中的mapping APIs。
     */
    @Pointcut("execution(* com.github.junlon2006.microservice.controller..*.*(..))")
    public void webLog() {

    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        /**
         * request information
         */
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + proceedingJoinPoint.getSignature().getDeclaringTypeName() + "." +
                proceedingJoinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(proceedingJoinPoint.getArgs()));

        /**
         * handler request, track time cost
         */
        long start = System.currentTimeMillis();
        Object ret = proceedingJoinPoint.proceed();
        log.info("RESPONSE[{}ms] : {}", System.currentTimeMillis() - start, ret);

        /**
         * response callback return, it is necessary
         */
        return ret;
    }
}
