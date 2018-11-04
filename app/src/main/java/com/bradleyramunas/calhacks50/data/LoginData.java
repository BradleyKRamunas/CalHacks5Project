package com.bradleyramunas.calhacks50.data;


public class LoginData {

    public LoginDataEnum status;
    public int user_id;
    public String name;

    public enum LoginDataEnum {
        SUCCESS, FAILURE, EXIST
    }

    public LoginData(LoginDataEnum status, int user_id, String name) {
        this.status = status;
        this.user_id = user_id;
        this.name = name;
    }

    public LoginData() {
        this.status = LoginDataEnum.FAILURE;
        this.user_id = -1;
        this.name = "";
    }
}