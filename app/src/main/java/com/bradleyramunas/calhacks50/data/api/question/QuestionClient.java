package com.bradleyramunas.calhacks50.data.api.question;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuestionClient {

    @GET("/api/v1/question")
    Single<QuestionJson> getRandomUnsolvedQuestion(@Query("user_id") int user_id, @Query("category") String category);
}
