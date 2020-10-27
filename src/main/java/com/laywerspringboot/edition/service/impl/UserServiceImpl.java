package com.laywerspringboot.edition.service.impl;

import com.laywerspringboot.edition.Utils.JWTUtils;
import com.laywerspringboot.edition.Utils.ObjectUtils;
import com.laywerspringboot.edition.Utils.R;
import com.laywerspringboot.edition.dao.UserDao;
import com.laywerspringboot.edition.dao.UuidnumDao;
import com.laywerspringboot.edition.entity.User;
import com.laywerspringboot.edition.entity.Uuidnum;
import com.laywerspringboot.edition.entity.dto.RegisterUser;
import com.laywerspringboot.edition.exception.SendMessageException;
import com.laywerspringboot.edition.exception.UserInfoException;
import com.laywerspringboot.edition.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-10-24 11:09:25
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private UuidnumDao uuidnumDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    /**
     * 根据信息查询
     *
     * @param flag        0代表查username 用户名称
     *                    1代表查realname 真实姓名
     *                    2代表查phoneid  身份证
     *                    3代表查手机号
     * @param registerMsg 注册信息
     * @return true 代表数据库为空 表示可用
     */
    @Override
    public boolean queryByMsg(int flag, String registerMsg) {
        User user = new User();
        if (flag == 0) {
            user.setUsername(registerMsg);
        }
        if (flag == 1) {
            user.setRealname(registerMsg);
        }
        if (flag == 2) {
            user.setIdcard(registerMsg);
        }
        if (flag == 3) {
            user.setPhoneid(registerMsg);
        }
        return ObjectUtils.isEmpty(queryMsg(user));

    }

    /**
     * 查询手机号，并把存储验证码次数到数据库，防止短信次数过多
     * 标志转换为原子类，防止高并发多次申请
     * 当根据手机号查询为空时‘，直接插入，并把标值设为1
     * 当根据手机号查询已存在时，先取出标志，加1后插入
     * 当连续获取两次当前时间毫秒时间戳会相同
     *
     * @param phoneid
     * @param uuid
     * @return
     */
    public R insertAndUpdateUUid(String phoneid, String uuid, Long currentTime) {
        User user = new User();
        user.setPhoneid(phoneid);
        Uuidnum uuidnum = new Uuidnum();
        uuidnum.setPhoneid(phoneid);
        List<Uuidnum> uuidnums = uuidnumDao.queryAll(uuidnum);
        if (ObjectUtils.isEmpty(uuidnums)) {
            //当为空，代表要插入手机号
            Integer flag = 0;
            AtomicInteger atomicInteger = new AtomicInteger(flag);
            atomicInteger.addAndGet(1);
            uuidnum.setNum(flag);
            try {
                uuidnum.setTime(currentTime);
                uuidnumDao.insert(uuidnum);
                user.setUuid(uuid);
                insert(user);
                return R.registerOk(uuid);
            } catch (Exception e) {
                throw new UserInfoException("插入验证码次数失败");
            }
        }
        //不为空时
        //先判断时间间隔
        List<Uuidnum> uuidnums1 = uuidnumDao.queryAll(uuidnum);
        Uuidnum uuidnum1 = uuidnums1.get(0);
        Long startTime = uuidnum1.getTime();
        //求时间差值
        long time = currentTime - startTime;
        uuidnum1.setEndtime(currentTime);
        //根据手机号查询
        List<User> users = queryByUser(user);
        User user2 = users.get(0);
        if (updatePhoneIdAndUUID(uuid, uuidnum1, time, user2, currentTime)) {
            return R.isUuid(uuid);
        } else {
            throw new SendMessageException("别太心急哦，请稍后重试");

        }
    }


    /**
     * 修改手机号和验证码获取时间的逻辑
     *
     * @param uuid
     * @param uuidnum1
     * @param time
     * @param user2
     * @return
     */
    private boolean updatePhoneIdAndUUID(String uuid, Uuidnum uuidnum1, long time, User user2, Long endTime) {
        if ((time >= 30000 || time > 0) && uuidnum1.getNum() <= 1) {
            return updatePhoneIdAndUUid(uuid, uuidnum1, user2, endTime);
        }
        if (time >= 3600000 && uuidnum1.getNum() <= 3) {
            return updatePhoneIdAndUUid(uuid, uuidnum1, user2, endTime);
        }
        if (time >= 86400000 && uuidnum1.getNum() <= 3) {
            return updatePhoneIdAndUUid(uuid, uuidnum1, user2, endTime);
        }
        return false;
    }

    /**
     * 修改手机号和验证码获取时间
     *
     * @param uuid
     * @param uuidnum1
     * @param user2
     * @return
     */
    private boolean updatePhoneIdAndUUid(String uuid, Uuidnum uuidnum1, User user2, Long time) {
        try {
            user2.setUuid(uuid);
            update(user2);
            AtomicInteger atomicInteger = new AtomicInteger(uuidnum1.getNum());
            uuidnum1.setNum(atomicInteger.addAndGet(1));
            uuidnumDao.update(uuidnum1);
            return true;
        } catch (Exception e) {
            throw new UserInfoException("插入验证码次数失败");
        }
    }


    private List<User> queryMsg(User user) {
        return userDao.queryAll(user);
    }

    /**
     * 根据对象查询
     *
     * @param user
     * @return
     */
    @Override
    public List<User> queryByUser(User user) {
        return userDao.queryAll(user);
    }


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }

    /**
     * 判断登陆时用户名是否存在
     *
     * @param user
     * @return
     */
    @Override
    public User isUsernameExist(User user) {
        User user1 = userDao.queryByUsername(user.getUsername());
        return user1;
    }
    /**
     * 判断密码是否正确
     * @param registerUser
     * @param user
     */
    public String isPasswordTrue( RegisterUser registerUser, User user) {
        if (!user.getPassword().equals(registerUser.getPassword())) {
            //次数+1
            AtomicInteger atomicInteger = new AtomicInteger(0);
            int count = atomicInteger.addAndGet(1);
            Date date = new Date(System.currentTimeMillis());
            user.setAltertime(date);
            user.setCount(count);
            this.update(user);
            throw new UserInfoException("密码错误");
        }
        //将count置空
        user.setCount(0);
        this.update(user);
        //id username 用户头像名，生成token
        HashMap<String, String> payload = new HashMap<>();
        payload.put("id",user.getId().toString());
        payload.put("username",user.getUsername());
        String token = JWTUtils.getToken(payload);

        return token;
    }
}


