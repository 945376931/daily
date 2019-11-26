package com.alibaba.webproject.Controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.webproject.Service.UserLoginService;
import com.alibaba.webproject.mybatis.entity.LoginStatus;
import com.alibaba.webproject.mybatis.entity.User;
import com.alibaba.webproject.mybatis.entity.UserResponeObject;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author zzy
 * @Date  2019/10/24
 * @desp  登录/注册控制器
 */

@CrossOrigin
@RestController
public class loginController {
    @Autowired
    private UserLoginService userLoginService;
    /**
     * 登录接口
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/login",produces = "application/json;charset=utf-8")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userLoginService.getUserByName(username);
        HttpSession session = request.getSession(); // 建立一个session，用来获取用户的登陆状态，根据这个用户的登陆状态可以去查询当前用户的信息和进行操作
        LoginStatus loginStatus=new LoginStatus();  //建立一个保存登陆状态的实体
        UserResponeObject<Object> userResult = new UserResponeObject<>();//封装返回的结果
//        List<User> pageContent = null;                                   //一个User的列表
        if (username == null || username.equals("") || password == null || password.equals("")) {
            userResult.setCode(10002);//显示错误
            userResult.setMsg("密码或用户名为空");
        } else if (user == null) {
            userResult.setMsg("该用户不存在，请先注册");
            userResult.setCode(10001);
        } else if (password.equals(user.getPasswd())){ //登陆成功
            loginStatus.setId(user.getId());
            loginStatus.setNickname(user.getNickname());
            session.setAttribute("login_user",loginStatus);//一个map类型：loginuser：对应的保存的session用户状态
            userResult.setContent(user);
            userResult.setCode(10000);
            userResult.setMsg("登录成功");
        }
        String s1=JSON.toJSONString(userResult);
        System.out.println(s1);
        return  s1;
    }



    /**注册操作**/
    @ResponseBody   //返回的是一个json格式的数据
    @RequestMapping(value="/register",produces = "application/json;charset=utf-8")
    public  String  register(@RequestBody String UserJsonStr){  //@RequestBody  为前端请求的为json格式
        UserResponeObject<Object> userResult = new UserResponeObject<>();
        try {
            User user = JSONObject.parseObject(UserJsonStr, User.class);
            System.out.println("123"+user);
            userLoginService.saveRegister(user);
            userResult.setCode(10000);//
            userResult.setMsg("注册成功");
            userResult.setContent(user);
        }catch (Exception e){
            userResult.setCode(10001);
            userResult.setMsg("注册用户失败");
            e.printStackTrace();
        }
        String s=JSON.toJSONString(userResult);
        System.out.println(s);
        return s;
    }
}
