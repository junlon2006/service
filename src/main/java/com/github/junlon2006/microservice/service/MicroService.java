package com.github.junlon2006.microservice.service;

import com.github.junlon2006.microservice.data.Result;
import com.github.junlon2006.microservice.entity.MicroTortoiseInfo;
import org.springframework.web.multipart.MultipartFile;

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
     * get tortoise information by name, just return first match item
     *
     * @param name
     * @return
     */
    Result<MicroTortoiseInfo> getInfoByName(String name);

    /**
     * get all tortoise information
     *
     * @return
     */
    Result<List<MicroTortoiseInfo>> getAllTortoiseInfo();

    /**
     * set tortoise information
     *
     * @param tortoiseInfos
     * @return
     */
    Result<List<MicroTortoiseInfo>> setTortoiseList(List<MicroTortoiseInfo> tortoiseInfos);

    /**
     * set tortoise picture
     *
     * @param microTortoiseInfo
     * @param file
     * @return
     */
    Result saveTortoisePicture(MicroTortoiseInfo microTortoiseInfo, MultipartFile file);
}
