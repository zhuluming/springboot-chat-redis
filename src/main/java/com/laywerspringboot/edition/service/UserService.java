package com.laywerspringboot.edition.service;

import com.laywerspringboot.edition.Utils.R;
import com.laywerspringboot.edition.entity.User;
import com.laywerspringboot.edition.entity.dto.RegisterUser;

import java.util.List;


/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2020-10-24 11:09:25
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 根据信息查询
     * @param flag
     * 0代表查username 用户名称
     * 1代表查realname 真实姓名
     * 2代表查phoneid  身份证
     *3代表查手机号
     * @param registerMsg 注册信息
     * @return true 代表数据库为空 表示可用
     */
    boolean  queryByMsg(int flag,String registerMsg);
    /**
     * 查询手机号，并把存储验证码到数据库
     * @param phoneid
     * @param uuid
     * @return
     */
    R insertAndUpdateUUid(String phoneid, String uuid, Long currentTime );
    /**
     * 根据对象查询
     * @param user
     * @return
     */
    List<User> queryByUser(User user);
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */

    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User  insert(User user);


    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    User isUsernameExist(User user);

    String isPasswordTrue(RegisterUser registerUser, User user);

}