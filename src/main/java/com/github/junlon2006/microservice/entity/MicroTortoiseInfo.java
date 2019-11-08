package com.github.junlon2006.microservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


/**
 * entiy about tortoise info
 *
 * @author junlon
 * @date 2019-08-03 11:10:00
 * @since jdk8
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tortoise_info")
public class MicroTortoiseInfo extends Model<MicroTortoiseInfo> {
    @TableId(type = IdType.AUTO)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String birthDay;
    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double weight;
    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type;
}
