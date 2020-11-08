package com.laywerspringboot.edition.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author:小七
 * @createTime:2020-10-23-19-35
 */

@RestController()
@RequestMapping("userInfo")
@CrossOrigin()
@Slf4j
@Api(description = "用户信息api",value = "注册登录")
public class UserInfoController  {
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private UserroleService userroleService;
    @Autowired
    private TecentUtils tecentUtils;


    /**
     * 验证用户名
     * @param username
     * @return
     */
    @CrossOrigin()
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
    @CrossOrigin()
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
    @CrossOrigin()
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
    @CrossOrigin()
    @ApiOperation(value = "验证手机号和验证码")
    @GetMapping("/register/phone/{phoneid}")
    public R isUuid(@ApiParam(value = "手机号")@PathVariable("phoneid")String phoneid) {

        boolean flag = userService.queryByMsg(3, phoneid);
        if (!flag) {
            return R.registerError("手机号已被注册，请直接登录");
        }
        //todo 逻辑代验证
        //String uuid = YunPianMsgUtils.sendMsg(phoneid);
        String uuid = tecentUtils.sendMsg(phoneid);

        Long currentTime = System.currentTimeMillis();
        return userService.insertAndUpdateUUid(phoneid, uuid, currentTime);

    }

    /**
     * 重置密码时候验证手机号和验证码
     * @param phoneid
     * @return
     */
    @CrossOrigin()
    @ApiOperation(value = "修改时验证手机号和验证码")
    @GetMapping("/register/resetPwd/{phoneid}")
    public R isUuid1(@ApiParam(value = "手机号")@PathVariable("phoneid")String phoneid) {

        boolean flag = userService.queryByMsg(3, phoneid);

        //todo 逻辑代验证
        //String uuid = YunPianMsgUtils.sendMsg(phoneid);
        String uuid = tecentUtils.sendMsg(phoneid);

        Long currentTime = System.currentTimeMillis();
        return userService.insertAndUpdateUUid(phoneid, uuid, currentTime);

    }

    /**
     * 用户注册功能
     * @param user 前端传的数据对象
     * @return 返回json给前端
     */
    @CrossOrigin()
    @ApiOperation(value = "用户注册功能,并把图片上传ajax的地址一起传输")
    @PostMapping("/register")
    public R register(@ApiParam(value = "注册用户对象")@RequestBody RegisterUser user){
        User insertUser = new User();
        Role role = new Role();
        Userrole userrole = new Userrole();
        isUserTrue(user.getUsername(), "用户名为空");
        isUserTrue(user.getPassword(), "密码为空");
        isUserTrue(user.getRealname(), "真实姓名为空");
        isUserTrue(user.getReplynewpassword(), "确认密码为空");
        isUserTrue(user.getPhoneid(), "手机号为空");
        isUserTrue(user.getIdcard(), "身份证为空");
        isUserTrue(user.getUuid(), "验证码为空");
        isUserTrue(user.getPhotoaddress(), "图片没上传成功");
        if (!user.getPassword().equals(user.getReplynewpassword())){
            throw new UserInfoException("两次密码不一致");
        }
        //dto转换成user
        User transferUser = DtoTransfer.transferUser(user);

        List<User> users = userService.queryByUser(transferUser);
        if (ObjectUtils.isEmpty(users)){
            //代表可以注册
            updateTime(transferUser);
            insertUser = userService.insert(transferUser);
            role = roleService.insertRole(user);
            userrole = new Userrole(0,insertUser.getId(),role.getRId());

            Userrole insert = userroleService.insert(userrole);
            if (insertUser != null && role != null && insert != null ){
                //注册成功把根据用户身份把对应的头像地址返回
                return R.registerOk("欢迎来到律动");
            }
        }
        throw new UserInfoException("用户已存在，请直接登录");

    }


    /**
     * 用户登录

     * @param LoginUser
     * @return
     */
    @CrossOrigin()
    @ApiOperation(value = "用户登录功能")
    @PostMapping("/userLogin")
    public R userLogin(@ApiParam(value = "登录用户对象")@RequestBody RegisterUser LoginUser){
        isUserTrue(LoginUser.getUsername(), "用户名为空");
        isUserTrue(LoginUser.getPassword(), "密码为空");
        User transferLoginUser = DtoTransfer.transferLoginUser(LoginUser);
        User user = userService.isUserExist(transferLoginUser);
        if (user == null){
            throw new UserInfoException("用户名不存在");
        }
        //一次错误操作都没有
        //可以成功登录
        if (user.getCount() <= 5){
            String token = userService.isPasswordTrue(LoginUser, user);
            return R.loginOk("欢迎进入律动").put("token", token).put("jstatus", 0);
        }
        //传入状态的时间
        long millis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(user.getAltertime());
        long timeInMillis = calendar.getTimeInMillis();
        long time = millis - timeInMillis;
        if (user.getCount() > 5 && user.getCount() < 7){
            if (time < 900000 ){
                return R.error("请于"+user.getAltertime()+"后的15分钟后尝试").put("jstatus", 1);
            }else {
                String token = userService.isPasswordTrue(LoginUser, user);
                return R.loginOk("欢迎进入律动").put("token", token);
            }
        }
        if (user.getCount()>=7 && user.getCount() < 8 ){
            if (time < 1800000  ){
                return R.error("请于"+user.getAltertime()+"后的30分钟后尝试").put("jstatus", 1);
            }else {
                String token = userService.isPasswordTrue(LoginUser, user);
                return R.loginOk("欢迎进入律动").put("token", token);
            }
        }

        return R.error("请选择手机登录").put("jstatus", -1);

    }

    /**
     * 手机登录功能
     * @param LoginUser
     * @return
     */
    @CrossOrigin()
    @ApiOperation(value = "手机登录功能")
    @PostMapping("/phoneLogin")
    public R phoneLogin( @ApiParam(value = "手机登录对象")@RequestBody RegisterUser LoginUser){
        isUserTrue(LoginUser.getPhoneid(), "手机号为空");
        isUserTrue(LoginUser.getUuid(), "验证码为空");
        String uuid = tecentUtils.sendMsg(LoginUser.getPhoneid());
        if (LoginUser.getUuid().equals(uuid)){
            User transferPhoneLoginUser = DtoTransfer.transferPhoneLoginUser(LoginUser);
            User user = userService.isUserExist(transferPhoneLoginUser);
            user.setCount(0);
            userService.update(user);
            String token = userService.isPasswordTrue(LoginUser, user);
            return R.loginOk("欢迎进入律动");
        }
        return R.error("验证码有误");
    }

    /**
     * 重置密码
     * @param updateUser
     * @param request
     * @return
     */
    @CrossOrigin()
    @ApiOperation(value = "重置密码")
    @PostMapping("/pwdReset")
    public R pwdReset(@ApiParam(value = "重置密码对象")@RequestBody RegisterUser updateUser, HttpServletRequest request){
        Integer id = JWTUtils.getTokenId(request);
        User user = userService.queryById(id);
        if (updateUser.getPhoneid().equals(user.getPhoneid())){
            //String uuid = TecentUtils.sendMsg(updateUser.getPhoneid());
          if (updateUser.getUuid().equals(user.getUuid())){
              //新密码和旧密码不等 就应该可以修改 false
              // 新密码和旧密码相等，应该不能修改
              //12345678 123456789 12345678
              System.out.println(user.getPassword());
              System.out.println(updateUser.getPassword());
              if (updateUser.getPassword().equals(user.getPassword())){
                if (!updateUser.getPassword().equals(updateUser.getReplynewpassword())){
                    user.setPassword(updateUser.getReplynewpassword());
                    userService.update(user);
                    return R.updateOk("修改成功");
                }else {
                    return R.error("两次密码不一致");
                }
              }else {
                  return R.error("原密码不对");
              }
          }else {
              return R.error("验证码有误");
          }

        }
        return R.error("手机号有误");
    }

    /**
     * 验证手机号和验证码
     * @param request
     * @return
     */
    @CrossOrigin()
    @ApiOperation(value = "查询手机号并返回给前端，前端做模糊处理")
    @GetMapping("/findPhone")
    public R findPhone(HttpServletRequest request){
        Integer tokenId = JWTUtils.getTokenId(request);
        User user = userService.queryById(tokenId);
        return R.findPhoneOK(user.getPhoneid());

    }


    /**
     * 重置手机号
     * @param updateUser
     * @param request
     * @return
     */
    @CrossOrigin()
    @ApiOperation(value = "重置手机号")
    @PostMapping("/phoneUpdate")
    public R phoneUpdate(@ApiParam(value = "重置手机号对象")@RequestBody RegisterUser updateUser, HttpServletRequest request) {
        Integer id = JWTUtils.getTokenId(request);
        User user = userService.queryById(id);
        if (updateUser.getPhoneid().equals(user.getPhoneid())){
            //如果相等才能修改
            //String uuid = TecentUtils.sendMsg(updateUser.getPhoneid());
            System.out.println(updateUser.getUuid());
            System.out.println(user.getUuid());
            if (updateUser.getUuid().equals(user.getUuid())){
                user.setPhoneid(updateUser.getNewPhoneid());
                userService.update(user);
                return R.updateOk("修改成功");
            }else {
                return R.updateOk("验证码不对");
            }
        }
        System.out.println(user.getPhoneid());
        System.out.println(updateUser.getPhoneid());
        return R.error("不是原手机号");
    }


    /**
     * 是否开启消息推送，0为不允许，1为允许
     * @param request
     * @return
     */
    @CrossOrigin()
    @ApiOperation(value = "返回开启消息推送后的token")
    @GetMapping("/InfoPush/{msgflag}")
    public String InfoPush(@ApiParam(value = "是否允许推送，0为允许，1不允许")@PathVariable("msgflag") String msgflag,HttpServletRequest request){
        DecodedJWT token = JWTUtils.verify(request.getHeader("token"));
        HashMap<String, String> map = new HashMap<>();
        map.put("id", token.getClaim("id").asString());
        map.put("username",token.getClaim("username").asString());
        map.put("rolename",token.getClaim("rolename").asString());
        map.put("msgflag",msgflag);
        return JWTUtils.getToken(map);
    }

    /**
     * 查询用户名是否存在
     * @param username
     * @param request
     * @return
     */
    @CrossOrigin()
    @ApiOperation(value = "查询用户名是否存在")
    @GetMapping("/findName/{username}")
    public R findName(@ApiParam(value = "用户填的原始用户名")@PathVariable("username") String username,HttpServletRequest request){
        Integer id = JWTUtils.getTokenId(request);
        User user = userService.queryById(id);
        return user == null?R.error("用户名不存在"):R.findOk("用户名存在");
    }

    /**
     * 修改用户名
     * @param newUsername
     * @param request
     * @return
     */

    @CrossOrigin()
    @ApiOperation(value = "修改用户名是否存在")
    @GetMapping("/nameUpdate/{newUsername}")
    public R nameUpdate(@ApiParam(value = "用户填的新的用户名")@PathVariable("newUsername") String newUsername,HttpServletRequest request){
        Integer id = JWTUtils.getTokenId(request);
        User user = userService.queryById(id);
        user.setUsername(newUsername);

        return userService.update(user) == null?R.error("修改用户名失败"):R.updateOk("修改成功");
    }





    /**
     * 修改时间
     * @param transferUser
     */
    private void updateTime(User transferUser) {
        //插入date,数据库配置设为上海时间
        Date date = new Date(System.currentTimeMillis());
        transferUser.setAltertime(date);
    }


    /**
     * 注册表单过来时抽的工具类
     * @param username
     * @param msg
     */
    private void isUserTrue(String username, String msg) {
        if (StringUtils.isEmpty(username)) {
            throw new UserInfoException(msg);
        }
    }

}
