package com.bradleyramunas.calhacks50.data.api.question;

import com.bradleyramunas.calhacks50.data.api.BaseApi;

import io.reactivex.Single;

public class Question extends BaseApi {

    public Question() {
        super();
    }

    public Single<QuestionJson> getRandomUnsolveQuestion(int user_id, String category) {
        QuestionClient questionClient = retrofit.create(QuestionClient.class);
        return questionClient.getRandomUnsolvedQuestion(user_id, category);
    }
}
