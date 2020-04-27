package com.kimsoft.kims.easier.boot.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Kimi
 * @date 2020/4/17
 */
@Data
public class UserDto {
    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "用户姓名")
    private String name;

    @ApiModelProperty(value = "英文姓名")
    private String enName;

    @ApiModelProperty(value = "账户")
    private String account;

    @ApiModelProperty(value = "职位")
    private String title;

    @ApiModelProperty(value = "部门id")
    private Long departmentId;

    @ApiModelProperty(value = "部门名称")
    private String departmentName;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "备注")
    private String remark;
}
