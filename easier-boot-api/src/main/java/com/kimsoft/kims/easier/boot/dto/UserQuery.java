package com.kimsoft.kims.easier.boot.dto;

import com.kimsoft.kims.easier.boot.common.QueryPageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Kimi
 * @date 2020/4/12
 */
@Data
@ApiModel("用户列表查询条件")
public class UserQuery extends QueryPageParam {
    @ApiModelProperty(value = "所属组织代码")
    private String orgCode;
    @ApiModelProperty("用户姓名")
    private String name;
}
