package com.alibaba.webproject.mybatis.entity;

import java.io.Serializable;

public class LoginStatus implements Serializable {
        /**
         * 动态生成ID
         */
        private static final long serialVersionUID = -2705545751132164226L;

        private Integer id;

        private String nickname;

        private String tel;

        private Integer code;

        private String message;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

