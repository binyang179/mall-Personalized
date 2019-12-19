package com.iflysse.service.impl.userimpl;

import com.iflysse.mapper.UserMapper;
import com.iflysse.pojo.User;
import com.iflysse.service.UserService;
import com.iflysse.viewmodel.UserViewModel.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    private List<UserList> buildUserList(List<User> users){
        if(users==null || users.size()==0){
            return new ArrayList<UserList>(0);
        }

        List<UserList> result = new ArrayList<>();
        for(User item:users){
            UserList glist = buildUser(item);
            result.add(glist);
        }
        return result;
    }

    private UserList buildUser(User user){
        UserList item = new UserList();
        item.setUserId(user.getUserId());
        item.setUserName(user.getUserName());
        item.setPassword(user.getPassword());
      return item;
    }

    @Override
    public List<UserList> getUserInfoByUser(String userName, String password){
        return buildUserList(userMapper.getUserInfoByUser(userName, password));
    }

    @Override
    public List<UserList> getUserInfoByUserName(String userName){
        return buildUserList(userMapper.getUserInfoByUserName(userName));
    }

    @Override
    public void addUser(String userName,String password){
        userMapper.addUser(userName,password);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
        try {
            // 进行一个python的调用
            String exe = "python";
            String command = "/home/crab179/IdeaProjects/iflytek/Personalized/python/personal_recommendation_func.py";
            String userId = String.valueOf(user.getUserId());
            String[] cmdArr = new String[]{exe, command, userId};
            Process process = Runtime.getRuntime().exec(cmdArr);
            InputStream is = process.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            String str = dis.readLine();
            process.waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
