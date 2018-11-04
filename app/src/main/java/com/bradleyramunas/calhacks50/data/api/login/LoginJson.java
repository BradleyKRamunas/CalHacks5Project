package com.bradleyramunas.calhacks50.data.api.login;

import com.google.gson.annotations.Expose;

public class LoginJson {
    @Expose
    public String status;

    @Expose
    public int user_id;

    @Expose
    public String username;
}
