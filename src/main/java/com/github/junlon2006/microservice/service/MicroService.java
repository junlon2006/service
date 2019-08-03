package com.github.junlon2006.microservice.service;

import com.github.junlon2006.microservice.data.Result;
import com.github.junlon2006.microservice.entity.MicroTortoiseInfo;

import java.util.List;

/**
 * service for tortoise management
 *
 * @author junlon
 * @date 2019-08-03 11:15:00
 * @since jdk8
 */
public interface MicroService {
    /**
     * get all tortoise information
     *
     * @return
     */
    Result<?> getAllTortoiseInfo();

    /**
     * set tortoise information
     *
     * @param tortoiseInfos
     * @return
     */
    Result<?> setTortoiseList(List<MicroTortoiseInfo> tortoiseInfos);
}
