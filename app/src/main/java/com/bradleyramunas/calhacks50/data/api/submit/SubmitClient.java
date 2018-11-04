package com.bradleyramunas.calhacks50.data.api.submit;

import io.reactivex.Single;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SubmitClient {

    @POST("/api/v1/solve")
    Single<SubmitJson> postSubmission(@Query("user_id") int user_id, @Query("question_id") int question_id, @Query("time") int time);
}
