package com.bradleyramunas.calhacks50.data.api.leaderboard;

import com.bradleyramunas.calhacks50.data.api.BaseApi;

import io.reactivex.Single;

public class Leaderboard extends BaseApi {

    public Leaderboard() {
        super();
    }

    public Single<LeaderboardJson> getLeaderboardForId(int question_id) {
        LeaderboardClient client = retrofit.create(LeaderboardClient.class);
        return client.getLeaderboard(question_id);
    }
}
