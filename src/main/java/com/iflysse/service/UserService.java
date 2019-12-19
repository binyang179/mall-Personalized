package com.iflysse.service;

import com.iflysse.pojo.User;
import com.iflysse.viewmodel.UserViewModel.UserList;

import java.util.List;

/**
 * 分类信息服务
 */
public interface UserService {
    /**
     * 根据用户编号和密码获取用户信息
     * @param userName 用户编号
     * @param password 用户密码
     * @return 用户列表
     */
    public List<UserList> getUserInfoByUser(String userName, String password);

    /**
     * 根据用户编号获取用户信息
     * @param userName 用户编号
     * @return 用户列表
     */
    public List<UserList> getUserInfoByUserName(String userName);

    /**
     * 新增用户信息
     * @param userName 用户编号
     * @param password 用户密码
     * @return 用户列表
     */
    public void addUser(String userName,String password);


    public void updateUser(User user);
}
