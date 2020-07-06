package com.zzz.project1.model.bo;

public class AdminAddBO {

    private String email;

    private String nickname;

    private String pwd;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    @Override
    public String toString() {
        return "AdminAddBO{" +
                "email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", pwd='" + pwd +
                '}';
    }
}
