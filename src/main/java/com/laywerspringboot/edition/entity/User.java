package com.laywerspringboot.edition.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-10-23 18:49:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户对象",description = "用户表")
public class User implements Serializable {
    private static final long serialVersionUID = -19099051080462463L;
    /**
     * 用户Id
     */
     @ApiModelProperty(name="id",example = "1")
    private Integer id;
    /**
     * 用户名称
     */
    @ApiModelProperty(name="id",example = "三七/siqi")
    private String username;
    /**
     * 用户密码
     */
    @ApiModelProperty(name="password",example = "qwer")
    private String password;
    /**
     * 用户真实姓名
     */
    @ApiModelProperty(name="realname",example = "思琦")
    private String realname;
    /**
     * 手机号
     */
   @ApiModelProperty(name="phoneid",example = "18279444776")
    private String phoneid;
    /**
     * 身份证
     */
     @ApiModelProperty(name="idcard",example = "544645.....")
    private String idcard;
    /**
     * 用户头像地址
     */
    @ApiModelProperty(name="photoaddress",example = "......")
    private String photoaddress;
    /**
     * 修改时间
     */
    @ApiModelProperty(name="altertime",example = "2020-10-23 22:02:17")
    private Date altertime;
    /**
     * 用户验证码，每次验证完删除
     */
    @ApiModelProperty(name="uuid",example = "464564")
    private String uuid;
    /**
     * 随机盐
     */
    @ApiModelProperty(name="salt",example = "46456")
    private String salt;
    /**
     * 失败次数
     */
    private int count;
    public User(String username, String password, String realname, String phoneid,
                String idcard, String photoaddress, Date altertime, String uuid, String salt) {
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.phoneid = phoneid;
        this.idcard = idcard;
        this.photoaddress = photoaddress;
        this.altertime = altertime;
        this.uuid = uuid;
        this.salt = salt;
    }
}