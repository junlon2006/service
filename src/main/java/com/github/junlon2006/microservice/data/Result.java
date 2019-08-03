package com.github.junlon2006.microservice.data;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author junlon2006
 * @date 2019-08-03 12:00:00
 * @since jdk.8
 * @param <T>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL) /** 去除null **/
public class Result<T> {
    private Integer code;
    private String message;
    private T data;
}
