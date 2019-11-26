package com.alibaba.webproject.mybatis.entity;

public class UserResponeObject<T> {
    /**状态码 **/
private  Integer code;
    /**响应消息**/
private String msg;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }


    public UserResponeObject() {
    }

    public UserResponeObject(Integer code, String msg, T content) {
        this.code = code;
        this.msg = msg;
        this.content = content;
    }

    /**响应数据实体**/
    private T content;
    @Override
    public String toString() {
        return "UserResponeObject{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", content=" + content +
                '}';
    }
}
