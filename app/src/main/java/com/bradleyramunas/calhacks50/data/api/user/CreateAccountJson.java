package com.bradleyramunas.calhacks50.data.api.user;

import com.google.gson.annotations.Expose;

public class CreateAccountJson {
    @Expose
    public String status;

    @Expose
    public int user_id;

    @Expose
    public String username;
}
