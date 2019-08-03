package com.github.junlon2006.microservice.config;

import com.github.junlon2006.microservice.data.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author junlon2006
 * @date 2019-08-03 15:15:00
 * @since jdk8
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception ex) {
        log.error("micro service error: {}", ex.toString());
        Result<?> result = new Result<>();
        result.setCode(200);
        result.setMessage(ex.getMessage());
        return result;
    }
}
