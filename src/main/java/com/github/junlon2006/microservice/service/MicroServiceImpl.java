package com.github.junlon2006.microservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.junlon2006.microservice.data.Result;
import com.github.junlon2006.microservice.entity.MicroTortoiseInfo;
import com.github.junlon2006.microservice.mapper.MicroTortoiseInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

/**
 * @author junlon2006
 * @date 2019-08-03 15:13:00
 * @since jdk8
 */
@Slf4j
@Service
public class MicroServiceImpl implements MicroService {

    @Autowired
    private MicroTortoiseInfoMapper microTortoiseInfoMapper;

    @Autowired
    private RedissonClient redissonClient;

    private final static String redissonMap = "tortoiseInfoMap";

    private void insertData2Redis(MicroTortoiseInfo microTortoiseInfo) {
        RMap<String, MicroTortoiseInfo> map = redissonClient.getMap(redissonMap);
        map.put(microTortoiseInfo.getName(), microTortoiseInfo);
    }

    private MicroTortoiseInfo getInfoFromRedis(String name) {
        RMap<String, MicroTortoiseInfo> map = redissonClient.getMap(redissonMap);
        return map.get(name);
    }

    private MicroTortoiseInfo getInfoFromMySql(String name) {
        MicroTortoiseInfo queryResult = microTortoiseInfoMapper.selectOne(new QueryWrapper<MicroTortoiseInfo>()
                .lambda()
                .eq(MicroTortoiseInfo::getName, name));
        if (Objects.nonNull(queryResult)) {
            insertData2Redis(queryResult);
        }

        return queryResult;
    }

    private MicroTortoiseInfo getTortoiseInfoByName(String name) {
        MicroTortoiseInfo microTortoiseInfo;
        microTortoiseInfo = getInfoFromRedis(name);
        if (Objects.nonNull(microTortoiseInfo)) {
            return microTortoiseInfo;
        }

        return getInfoFromMySql(name);
    }

    private void handleSetTortoiseList(MicroTortoiseInfo microTortoiseInfo) {
        MicroTortoiseInfo queryResult = microTortoiseInfoMapper.selectOne(new QueryWrapper<MicroTortoiseInfo>()
                .lambda()
                .eq(MicroTortoiseInfo::getBirthDay, microTortoiseInfo.getBirthDay())
                .eq(MicroTortoiseInfo::getName, microTortoiseInfo.getName())
                .eq(MicroTortoiseInfo::getType, microTortoiseInfo.getType())
                .eq(MicroTortoiseInfo::getWeight, microTortoiseInfo.getWeight()));
        if (Objects.isNull(queryResult)) {
            microTortoiseInfo.insert();
            log.info("[INSERT] new tortoise information={}", microTortoiseInfo);
        } else {
            log.info("[DUPICATED] skip duplicated tortoise information={}", microTortoiseInfo);
        }
    }

    @Override
    public Result<?> setTortoiseList(List<MicroTortoiseInfo> tortoiseInfos) {
        tortoiseInfos.forEach(c -> handleSetTortoiseList(c));
        return Result.builder().code(200).message("SUCCESS").data(tortoiseInfos).build();
    }

    @Override
    public Result<?> getInfoByName(String name) {
        MicroTortoiseInfo microTortoiseInfo = getTortoiseInfoByName(name);
        return Result.builder().code(200).message("SUCCESS").data(microTortoiseInfo).build();
    }

    @Override
    public Result<?> getAllTortoiseInfo() {
        List<MicroTortoiseInfo> tortoiseInfos = microTortoiseInfoMapper.selectList(new QueryWrapper<>());
        return Result.builder().code(200).message("SUCCESS").data(tortoiseInfos).build();
    }

    @Override
    public Result<?> saveTortoisePicture(MicroTortoiseInfo microTortoiseInfo, MultipartFile file) {
        log.info("microTortoiseInfo={}, file={}, len={}", microTortoiseInfo, file, file.getSize());
        return Result.builder().code(200).message("SUCCESS").data(microTortoiseInfo).build();
    }
}