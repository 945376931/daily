package com.alibaba.webproject.Controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.webproject.Service.UserService;
import com.alibaba.webproject.mybatis.dto.UserDto;
import com.alibaba.webproject.mybatis.entity.LoginStatus;
import com.alibaba.webproject.mybatis.entity.User;
import com.alibaba.webproject.mybatis.entity.UserResponeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /** 上传图片**/
    @PostMapping(value="/photoUpload")
    public void photoUpload(@RequestParam(value="file")MultipartFile file, HttpServletRequest request){
        if(file.isEmpty()){
            System.out.println("没有图片上传");
        }
        String filename=file.getOriginalFilename();//文件名
        String suffixName=filename.substring((filename.lastIndexOf("."))); //后缀名
        String filePath="E://photoFileload//";
        filename = UUID.randomUUID()+ suffixName; //组合后的新文件名    123.jpg；
        File  dest=new File((filePath+filename));   //E://photo//123.jpg
        if(!dest.getParentFile().exists()){  //如果当前文件不存在
            dest.getParentFile().mkdir();
        }
        try{
            file.transferTo(dest);         //这个是用来传图片的
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**查询登录中的个人信息根据**/
    @ResponseBody
    @RequestMapping(value = "/findLoginUserById",produces = "application/json;charset=utf-8")
       public  String   findLoginUserById(@SessionAttribute("login_user")LoginStatus loginStatus){
        UserResponeObject<Object>  responeObject=new UserResponeObject<>();
        UserDto dto=new UserDto();
        System.out.println("用户的id"+loginStatus.getId());
        dto.setId(loginStatus.getId());
        User user = userService.findLoginUserById(dto);
        System.out.println(user);
        try {
            responeObject.setContent(user);
            responeObject.setCode(10000);
            responeObject.setMsg("查询成功");
        }catch (Exception e){
            responeObject.setCode(10001);
            responeObject.setMsg("查询失败");
        }
        String s1= JSON.toJSONString(responeObject);
        System.out.println(s1);
        return s1;
    }







}
