package com.kimsoft.kims.easier.boot.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Kimi
 * @date 2020/4/17
 */
@Data
@ApiModel("用户表单")
public class UserForm {
    @ApiModelProperty("用户id，新增传null，更新传id")
    private String id;
    @ApiModelProperty("用户姓名")
    private String name;
    @ApiModelProperty("英文名")
    private String enName;
    @ApiModelProperty("账号")
    private String account;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("职位")
    private String title;
    @ApiModelProperty("所属部门id")
    private Long departmentId;
    @ApiModelProperty("所属部门名称")
    private String departmentName;
    @ApiModelProperty("电话")
    private String phone;
    @ApiModelProperty("邮箱")
    private String email;
}
