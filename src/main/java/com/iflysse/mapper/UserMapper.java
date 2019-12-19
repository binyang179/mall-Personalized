package com.iflysse.mapper;

import com.iflysse.pojo.GoodsCart;
import com.iflysse.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Category Mapper
 */
public interface UserMapper {
    /**
     * 根据用户编号和密码获取用户信息
     * @param userName 用户Name编号
     * @param password 用户密码
     * @return 用户列表
     */
    public List<User> getUserInfoByUser(@Param("userName") String userName, @Param("password") String password);

    /**
     * 根据用户编号获取用户信息
     * @param userName 用户编号
     * @return 用户列表
     */
    public List<User> getUserInfoByUserName(@Param("userName") String userName);

    /**
     * 新增用户信息
     * @param userName 用户编号
     * @param password 用户密码
     * @return 用户列表
     */
    public void addUser(@Param("userName") String userName,@Param("password") String password);

    /**
     * 新增用户信息
     * @return 用户列表
     */
    public void updateUser(@Param("user") User user);

}
