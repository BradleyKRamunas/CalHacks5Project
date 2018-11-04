package com.bradleyramunas.calhacks50.ui;

import android.util.Log;

import com.bradleyramunas.calhacks50.data.Response;
import com.bradleyramunas.calhacks50.data.Status;
import com.bradleyramunas.calhacks50.data.api.question.Question;
import com.bradleyramunas.calhacks50.data.api.question.QuestionJson;
import com.bradleyramunas.calhacks50.data.api.submit.Submit;
import com.bradleyramunas.calhacks50.data.api.submit.SubmitJson;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class QuestionViewModel extends ViewModel {

    private MutableLiveData<Response<QuestionJson>> eventData = new MutableLiveData<>();
    private MutableLiveData<Response<SubmitJson>> submitData = new MutableLiveData<>();
    private Question question = new Question();
    private Submit submit = new Submit();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public MutableLiveData<Response<QuestionJson>> getEventLiveData() {
        return eventData;
    }

    public MutableLiveData<Response<SubmitJson>> getSubmitLiveData() {
        return submitData;
    }

    public void fetchRandomQuestion(String category, int user_id) {
        Response<QuestionJson> loading = new Response<>(Status.LOADING, null, null);
        eventData.setValue(loading);
        Disposable disposable = question.getRandomUnsolveQuestion(user_id, category).subscribeOn(Schedulers.io()).subscribe(result -> {
            Response<QuestionJson> response = new Response<>(Status.COMPLETE, result, null);
            eventData.postValue(response);
        });
        compositeDisposable.add(disposable);
    }

    public void submitRecord(int user_id, int question_id, int time) {
        compositeDisposable.add(submit.submitSolution(user_id, question_id, time).subscribeOn(Schedulers.io()).subscribe(it -> {
            Log.i("HELLLLLLLLLO", "HI");
            Response<SubmitJson> response = new Response<>(Status.COMPLETE, null, null);
            submitData.postValue(response);
        }));
    }

    public void fail() {
        Log.i("HI", "HIIIII");
        Response<SubmitJson> response = new Response<>(Status.ERROR, null, null);
        submitData.setValue(response);
    }
}
