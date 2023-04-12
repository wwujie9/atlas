package com.base.atlas.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Description: 接受参数父类
 * @ClassName: BaseReqModel
 * @Auther: jiaql
 * @Date: 2019/3/13 09:10
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class BaseDtoModel {

    /**
     * 应用的AppID
     *
     **/
    @NotBlank(message = "参数错误")
    @ApiModelProperty(value = "应用的AppID, 一般为包名，如com.yiqijia.haoshijia")
    private String appID;
    /**
     * 客户端时间戳
     *
     **/
    @NotBlank(message = "参数错误")
    private String timeStamp;

    /**
     * 语言代码
     *
     **/
    @NotBlank(message = "参数错误")
    private String langID;

    /**
     * 用户ID
     **/
    @ApiModelProperty(value = "用户id")
    private String userId;

    /**
     * 当前登录用户ID(用于查看是否关注)
     */
    private String loginUserId;
}
