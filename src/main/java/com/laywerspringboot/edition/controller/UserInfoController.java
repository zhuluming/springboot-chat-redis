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
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
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
        String uuid = TecentUtils.sendMsg(phoneid);
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
        isUserTrue(user.getUsername(), "用户名为空");
        isUserTrue(user.getPassword(), "密码为空");
        isUserTrue(user.getRealname(), "真实姓名为空");
        isUserTrue(user.getReplynewpassword(), "确认密码为空");
        isUserTrue(user.getPhoneid(), "手机号为空");
        isUserTrue(user.getIdcard(), "身份证为空");
        isUserTrue(user.getPhotoaddress(), "图片上传有问题，请重新上传");
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
            userrole = new Userrole(insertUser.getId(),role.getRId());
            userroleService.insert(userrole);
            if (insertUser != null && role != null && userrole != null ){
                //注册成功把根据用户身份把对应的头像地址返回
                return R.registerOk("欢迎来到律动");
            }
        }
        throw new UserInfoException("用户已存在，请直接登录");

    }



    @ApiOperation(value = "用户登录功能")
    @PostMapping("/userLogin")
    public R userLogin(@ApiParam(value = "登录用户对象")@RequestBody RegisterUser registerUser){
        isUserTrue(registerUser.getUsername(), "用户名为空");
        isUserTrue(registerUser.getPassword(), "密码为空");
        User transferUser = DtoTransfer.transferUser(registerUser);
        User user = userService.isUsernameExist(transferUser);
        if (user == null){
            throw new UserInfoException("用户名不存在");
        }
        //一次错误操作都没有
        //可以成功登录
        if (user.getCount() <= 5){
            String token = userService.isPasswordTrue(registerUser, user);
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
                String token = userService.isPasswordTrue(registerUser, user);
                return R.loginOk("欢迎进入律动").put("token", token);
            }
        }
        if (user.getCount()>=7 && user.getCount() < 8 ){
            if (time < 1800000  ){
                return R.error("请于"+user.getAltertime()+"后的30分钟后尝试").put("jstatus", 1);
            }else {
                String token = userService.isPasswordTrue(registerUser, user);
                return R.loginOk("欢迎进入律动").put("token", token);
            }
        }

        return R.error("请选择手机登录").put("jstatus", -1);

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
