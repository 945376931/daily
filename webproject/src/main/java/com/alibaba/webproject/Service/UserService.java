package com.alibaba.webproject.Service;


import com.alibaba.webproject.mybatis.dao.TbUserDao;
import com.alibaba.webproject.mybatis.dto.UserDto;
import com.alibaba.webproject.mybatis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**用户主操作的service层**/
@Service
public class UserService {

    @Autowired
    private TbUserDao tbuserdao;

    /** 查询登录用户信息的接口**/
    public User findLoginUserById(UserDto dto){
        return  tbuserdao.findLoginUserById(dto);
    }



}
