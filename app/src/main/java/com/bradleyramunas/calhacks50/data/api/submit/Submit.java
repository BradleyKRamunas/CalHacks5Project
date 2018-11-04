package com.bradleyramunas.calhacks50.data.api.submit;

import com.bradleyramunas.calhacks50.data.api.BaseApi;

import io.reactivex.Single;

public class Submit extends BaseApi {

    public Submit() {
        super();
    }

    public Single<SubmitJson> submitSolution(int user_id, int question_id, int time) {
        SubmitClient client = retrofit.create(SubmitClient.class);
        return client.postSubmission(user_id, question_id, time);
    }
}
