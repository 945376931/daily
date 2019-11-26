package com.alibaba.webproject.Service;

import com.alibaba.webproject.mybatis.dao.TbUserDao;
import com.alibaba.webproject.mybatis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {
    //根据名字去查询数据库里有没有这个用户




    @Autowired
     private TbUserDao tbuserdao;
   /** 登录的接口**/
    public User getUserByName(String username){
        return  tbuserdao.getUserByName(username);
    }

   /** 注册的接口**/
    public void saveRegister(User  user){

       tbuserdao.saveRegister(user);

    }



}
