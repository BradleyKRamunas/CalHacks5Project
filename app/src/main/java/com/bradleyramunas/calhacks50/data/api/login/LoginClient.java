package com.bradleyramunas.calhacks50.data.api.login;

import io.reactivex.Single;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginClient {

    @POST("/api/v1/login")
    Single<LoginJson> attemptLogin(@Query("name") String username, @Query("pass") String password);
}
