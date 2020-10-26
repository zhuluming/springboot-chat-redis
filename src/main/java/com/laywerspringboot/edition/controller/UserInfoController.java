package com.laywerspringboot.edition.controller;

import com.laywerspringboot.edition.Utils.*;
import com.laywerspringboot.edition.entity.Role;
import com.laywerspringboot.edition.entity.User;
import com.laywerspringboot.edition.entity.Userrole;
import com.laywerspringboot.edition.entity.dto.RegisterUser;
import com.laywerspringboot.edition.exception.UserInfoException;
import com.laywerspringboot.edition.service.RoleService;
import com.laywerspringboot.edition.service.UserService;
import com.laywerspringboot.edition.service.UserroleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:小七
 * @createTime:2020-10-23-19-35
 */

@RestController()
@RequestMapping("userInfo")
@CrossOrigin()
@Api(description = "用户信息api",value = "注册登录")
public class UserInfoController  {
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private UserroleService userroleService;

    /**
     * 验证用户名
     * @param username
     * @return
     */
    @ApiOperation(value = "验证用户名")
    @GetMapping("/register/isUsername/{username}")
    public R isUsernameEmpty(@ApiParam(value = "用户名")@PathVariable("username")String username){
        boolean flag = userService.queryByMsg(0, username);
        return flag?R.registerOk("用户名可用"):R.registerError("用户名重复");
    }

    /**
     * 验证姓名
     * @param realname
     * @return
     */
     @ApiOperation(value = "验证真实姓名")
    @GetMapping("/register/isRealName/{realname}")
    public R isRealNameEmpty(@ApiParam(value = "真实姓名")@PathVariable("realname")String realname){
        boolean flag = userService.queryByMsg(1, realname);
        return flag?R.registerOk("姓名可用"):R.registerError("姓名已存在，忘记请找回");
    }

    /**
     * 验证身份证号
     * @param idcard
     * @return
     */
    @ApiOperation(value = "验证身份证号")
    @GetMapping("/register/isIdCardEmpty/{idcard}")
    public R isIdCardEmpty(@ApiParam(value = "身份证号")@PathVariable("idcard")String idcard){
        boolean flag = userService.queryByMsg(2, idcard);
        return flag?R.registerOk("身份证可用"):R.registerError("身份证已存在，忘记请找回");
    }

    /**
     * 验证手机号和验证码
     * @param phoneid
     * @return
     */
    @ApiOperation(value = "验证手机号和验证码")
    @GetMapping("/register/phone/{phoneid}")
    public R isUuid(@ApiParam(value = "手机号")@PathVariable("phoneid")String phoneid) {
        boolean flag = userService.queryByMsg(3, phoneid);
      /*  if (flag) {
            return R.registerError("手机号已被注册，请直接登录");
        }*/
        //String uuid = YunPianMsgUtils.sendMsg(phoneid);
        String uuid = TecentMsgUtils.sendMsg(phoneid);
        Long currentTime = System.currentTimeMillis();
        return  userService.insertAndUpdateUUid(phoneid, uuid, currentTime);

    }
    /**
     * 用户注册功能
     * @param user 前端传的数据对象
     * @return 返回json给前端
     */
    @ApiOperation(value = "用户注册功能")
    @PostMapping("/register")
    public R register(@ApiParam(value = "注册用户对象")@RequestBody RegisterUser user){
        User insertUser = new User();
        Role role = new Role();
        Userrole userrole = new Userrole();
        isUsername(user.getUsername(), "用户名为空");
        isUsername(user.getPassword(), "密码为空");
        isUsername(user.getRealname(), "真实姓名为空");
        isUsername(user.getReplynewpassword(), "确认密码为空");
        isUsername(user.getPhoneid(), "手机号为空");
        isUsername(user.getIdcard(), "身份证为空");
        if (user.getPassword().equals(user.getReplynewpassword())){
            throw new UserInfoException("两次密码不一致");
        }
        //dto转换成user
        User transferUser = DtoTransfer.transferUser(user);

        List<User> users = userService.queryByUser(transferUser);
        if (ObjectUtils.isEmpty(users)){
            //代表可以注册
            for (User user1 : users) {
                insertUser = userService.insert(user1);
                role = roleService.insertRole(user);
                userrole = new Userrole(insertUser.getId(),role.getRId());
                userroleService.insert(userrole);
            }
            if (insertUser != null && role != null && userrole != null ){
                return R.registerOk("欢迎来到律动");
            }

        }
        throw new UserInfoException("注册失败哦，请联系管理员");

    }


    /**
     * 注册表单过来时抽的工具类
     * @param username
     * @param msg
     */
    private void isUsername(String username, String msg) {
        if (StringUtils.isEmpty(username)) {
            throw new UserInfoException(msg);
        }
    }

}
