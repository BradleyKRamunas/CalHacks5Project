package com.bradleyramunas.calhacks50.data.api.login;

import com.bradleyramunas.calhacks50.Constants;
import com.bradleyramunas.calhacks50.data.api.BaseApi;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends BaseApi {

    public Login() {
        super();
    }

    public Single<LoginJson> getLoginResponse(String username, String password) {
        LoginClient client = retrofit.create(LoginClient.class);
        return client.attemptLogin(username, password);
    }
}
