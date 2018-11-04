package com.bradleyramunas.calhacks50.data.api.user;

import io.reactivex.Single;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CreateAccountClient {

    @POST("/api/v1/create")
    Single<CreateAccountJson> attemptToCreateAccount(@Query("name") String username, @Query("pass") String password);
}
