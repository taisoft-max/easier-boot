package com.kimsoft.kims.easier.boot.user.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author kimi
 * @since 2020-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="用户信息")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId
    private Long id;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "是否删除,0未删除，1删除")
    /** 逻辑删除 */
    @TableLogic(value = "null",delval = "now()")
    // 查询时SQL不显示此字段
    @TableField(select = false)
    private LocalDateTime deleted;

    @ApiModelProperty(value = "是否第一次登录，0不是，1(默认)是")
    private Boolean isFirstLogin;

    @ApiModelProperty(value = "用户姓名")
    private String name;

    @ApiModelProperty(value = "英文姓名")
    private String enName;

    @ApiModelProperty(value = "账户")
    private String account;

    @ApiModelProperty(value = "职位")
    private String title;

    @ApiModelProperty(value = "密码")
    private String password;

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

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
