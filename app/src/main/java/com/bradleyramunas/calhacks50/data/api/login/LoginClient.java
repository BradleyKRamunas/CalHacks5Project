package com.bradleyramunas.calhacks50.data.api.login;

import io.reactivex.Single;
import retrofit2.http.POST;

public interface LoginClient {

    @POST
    Single<LoginJson> attemptLogin(String username, String password);
}
