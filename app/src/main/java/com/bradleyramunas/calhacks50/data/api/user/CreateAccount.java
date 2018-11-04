package com.bradleyramunas.calhacks50.data.api.user;

import com.bradleyramunas.calhacks50.data.api.BaseApi;

import io.reactivex.Single;

public class CreateAccount extends BaseApi {

    public CreateAccount() {
        super();
    }

    public Single<CreateAccountJson> getCreateAccountResponse(String username, String password) {
        CreateAccountClient client = retrofit.create(CreateAccountClient.class);
        return client.attemptToCreateAccount(username, password);
    }
}
