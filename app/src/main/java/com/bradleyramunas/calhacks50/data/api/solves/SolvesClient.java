package com.bradleyramunas.calhacks50.data.api.solves;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SolvesClient {

    @GET("/api/v1/solves")
    Single<SolvesJson> getSolveCountsForCategory(@Query("user_id") int user_id, @Query("category") String category);
}
