package com.laywerspringboot.edition.Utils;

import com.laywerspringboot.edition.entity.User;
import com.laywerspringboot.edition.entity.dto.RegisterUser;

/**
 * @Author:小七
 * @createTime:2020-10-24-13-35
 */
public class DtoTransfer {
    /**
     * 注册用户转换成user
     * @param registerUser
     * @return
     */
    public static User transferUser(RegisterUser registerUser){
        User user = new User();
        user.setUsername(registerUser.getUsername());
        user.setPassword(registerUser.getPassword());
        user.setRealname(registerUser.getRealname());
        user.setPhoneid(registerUser.getPhoneid());
        user.setIdcard(registerUser.getIdcard());
        user.setPhotoaddress(registerUser.getPhotoaddress());
        return user;
    }
    public static User transferLoginUser(RegisterUser registerUser){
        User user = new User();
        user.setUsername(registerUser.getUsername());
        user.setPassword(registerUser.getPassword());
        return user;
    }
    public static User transferPhoneLoginUser(RegisterUser registerUser){
        User user = new User();
        user.setPhoneid(registerUser.getPhoneid());
        user.setUuid(registerUser.getUuid());
        return user;
    }
}
