package edu.nju.hostelworld.service;

import edu.nju.hostelworld.entity.*;
import edu.nju.hostelworld.util.ResultMessage;

import java.util.List;

/**
 * Created by Administrator on 17/3/2.
 */
public interface UserService {
    /**
     * @return
     */
    public List<User> getAll();

    /**
     * 系统注册新用户
     *
     * @param userName
     * @param password
     * @return DUPLICATE_NAME, FAILURE, SUCCESS
     */
    public ResultMessage register(Class<?> c, String userName, String password);

    /**
     * 注册新会员，默认为会员注册
     *
     * @param userName
     * @param password
     * @return DUPLICATE_NAME, FAILURE, SUCCESS
     */
    public ResultMessage register(String userName, String password);

    /**
     * 系统删除用户
     *
     * @param userId
     * @return
     */
    public ResultMessage delete(int userId);

    /**
     * 修改密码
     *
     * @param id
     * @param password
     * @return WRONG_ORIGINAL, NOT_CHANGE, FAILURE, SUCCESS
     */
    public ResultMessage changePassword(int id, String original, String password);

    /**
     * 将银行卡余额改至一个数值
     *
     * @param id
     * @param money
     * @return SUCCESS, FAILURE
     */
    public ResultMessage changeBankMoneyTo(int id, double money);

    /**
     * 将银行卡余额进行增减
     *
     * @param id
     * @param money
     * @return SUCCESS, FAILURE
     */
    public ResultMessage changeBankMoneyAdd(int id, double money);

    public ResultMessage changeBankMoneyMinus(int id, double money);

    /**
     * 通过用户ID获取用户数据
     *
     * @param userId
     * @return
     */
    public User getById(int userId);

    /**
     * 通过用户种类获取用户列表，种类有会员、客栈和经理
     *
     * @param type
     * @return SUCCESS, FAILURE
     */
    public List<User> getByType(String type);

    /**
     * 用户通过用户名,密码登录，
     *
     * @param userName
     * @param password
     * @return
     */
    public User login(String userName, String password);

    /**
     * 通过用户id,password验证用户情况
     *
     * @param userName
     * @param password
     * @return NOT_EXIST, SUCCESS, WRONG_PASSWORD
     */
    public ResultMessage checkPassword(String userName, String password);

    /**
     * 只检查用户是否存在
     *
     * @param userName
     * @return NOT_EXIST, SUCCESS
     */
    public ResultMessage checkUserExist(String userName);

    /**
     * @param user
     * @return
     */
    public ResultMessage update(User user);
}
