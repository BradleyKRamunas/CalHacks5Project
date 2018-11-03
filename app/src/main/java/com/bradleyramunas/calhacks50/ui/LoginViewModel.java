package com.bradleyramunas.calhacks50.ui;

import android.util.Log;

import com.bradleyramunas.calhacks50.data.LoginData;
import com.bradleyramunas.calhacks50.data.Response;
import com.bradleyramunas.calhacks50.data.Status;

import java.util.concurrent.TimeUnit;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<Response<LoginData>> eventData = new MutableLiveData<>();


    public MutableLiveData<Response<LoginData>> getEventLiveData() {
        return eventData;
    }

    public void attemptLogin(String username, String password) {
        Response<LoginData> loading = new Response<>(Status.LOADING, null, null);
        eventData.setValue(loading);
        Response<LoginData> loginDataResponse = new Response<>(Status.COMPLETE, LoginData.FAILURE, null);
        Observable.just(loginDataResponse).delay(5000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).subscribe(data -> eventData.postValue(data));
    }

    public void attemptCreate(String username, String password) {
        Response<LoginData> loading = new Response<>(Status.LOADING, null, null);
        eventData.setValue(loading);
        Response<LoginData> loginDataResponse = new Response<>(Status.COMPLETE, LoginData.EXIST, null);
        Observable.just(loginDataResponse).delay(5000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).subscribe(data -> eventData.postValue(data));
    }

}
