package com.bradleyramunas.calhacks50.data.api.leaderboard;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LeaderboardClient {

    @GET("/v1/api/leaderboard")
    Single<LeaderboardJson> getLeaderboard(@Query("question_id") int question_id);
}
