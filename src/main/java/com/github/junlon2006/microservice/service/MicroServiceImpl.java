package com.github.junlon2006.microservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.junlon2006.microservice.data.Result;
import com.github.junlon2006.microservice.entity.MicroTortoiseInfo;
import com.github.junlon2006.microservice.mapper.MicroTortoiseInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Result<?> getAllTortoiseInfo() {
        List<MicroTortoiseInfo> tortoiseInfos = microTortoiseInfoMapper.selectList(new QueryWrapper<>());
        return Result.builder().code(200).message("SUCCESS").data(tortoiseInfos).build();
    }
}