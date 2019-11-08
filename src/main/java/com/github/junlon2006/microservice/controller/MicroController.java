package com.github.junlon2006.microservice.controller;

import com.alibaba.fastjson.JSON;
import com.github.junlon2006.microservice.data.Result;
import com.github.junlon2006.microservice.entity.MicroTortoiseInfo;
import com.github.junlon2006.microservice.service.MicroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Controller to manage tortoise information
 *
 * @author junlon2006
 * @date 2019-08-03 11:00:00
 * @since jdk8
 */

@RestController
@RequestMapping("/junlon/v1/tortoise")
public class MicroController {

    @Autowired
    private MicroService microService;

    /**
     * get all tortoise information list from register database
     *
     * @return
     */
    @GetMapping("/get-all")
    public Result<List<MicroTortoiseInfo>> getTortoiseInfo() {
        return microService.getAllTortoiseInfo();
    }

    /**
     * set tortoise information list, and insert data to database
     *
     * @param tortoiseInfos
     * @return
     */
    @PostMapping(value = "/set-list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<MicroTortoiseInfo>> setTortoiseInfo(@RequestBody @Validated List<MicroTortoiseInfo> tortoiseInfos) {
        return microService.setTortoiseList(tortoiseInfos);
    }

    /**
     * get tortoise information by name
     *
     * @param name
     * @return
     */
    @PostMapping(value = "/find-by-name", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<MicroTortoiseInfo> getInfoByName(@RequestBody String name) {
        return microService.getInfoByName(name);
    }

    /**
     * save tortoise picture
     *
     * @param microTortoiseInfo
     * @param file
     * @return
     */
    @PostMapping(value = "/save-pic", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result saveTortoisePicture(@RequestPart("microTortoiseInfo") String microTortoiseInfo,
                                      @RequestParam("picture") MultipartFile file) {
        return microService.saveTortoisePicture(JSON.parseObject(microTortoiseInfo, MicroTortoiseInfo.class), file);
    }

}
