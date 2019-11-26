package com.alibaba.webproject.mybatis.dto;
public class UserDto {
    /**封装查询的条件**/
    private  Integer  id;
    private  String nickname; //用户名    zhao'pian
    private  String signature; //签名
    private  String  sex;      //性别
    private  String  tel;      //号码
    private  String mail;      //邮箱
    private  String passwd;    //密码
    private  Integer permisson_id;  //权限


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Integer getPermisson_id() {
        return permisson_id;
    }

    public void setPermisson_id(Integer permisson_id) {
        this.permisson_id = permisson_id;
    }
}
