package com.bradleyramunas.calhacks50.data.api.solves;

import com.bradleyramunas.calhacks50.data.api.BaseApi;

import io.reactivex.Single;

public class Solves extends BaseApi {

    public Solves() {
        super();
    }

    public Single<SolvesJson> getSolves(int user_id, String category) {
        SolvesClient client = retrofit.create(SolvesClient.class);
        return client.getSolveCountsForCategory(user_id, category);
    }
}
