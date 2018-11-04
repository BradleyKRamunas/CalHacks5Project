package com.bradleyramunas.calhacks50.ui;

import android.util.Log;

import com.bradleyramunas.calhacks50.data.LoginData;
import com.bradleyramunas.calhacks50.data.Response;
import com.bradleyramunas.calhacks50.data.Status;
import com.bradleyramunas.calhacks50.data.api.login.Login;
import com.bradleyramunas.calhacks50.data.api.user.CreateAccount;

import java.util.concurrent.TimeUnit;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<Response<LoginData>> eventData = new MutableLiveData<>();
    private Login login = new Login();
    private CreateAccount createAccount = new CreateAccount();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public MutableLiveData<Response<LoginData>> getEventLiveData() {
        return eventData;
    }

    public void attemptLogin(String username, String password) {
        Response<LoginData> loading = new Response<>(Status.LOADING, null, null);
        eventData.setValue(loading);
        Disposable disposable = login.getLoginResponse(username, password).subscribeOn(Schedulers.io()).subscribe((result) -> {
            if(result.status.equals("success")) {
                Response<LoginData> loginDataResponse = new Response<>(Status.COMPLETE, new LoginData(LoginData.LoginDataEnum.SUCCESS, result.user_id, result.username), null);
                eventData.postValue(loginDataResponse);
            } else {
                Response<LoginData> loginDataResponse = new Response<>(Status.COMPLETE, new LoginData(), null);
                eventData.postValue(loginDataResponse);
            }
        });
        compositeDisposable.add(disposable);
    }

    public void attemptCreate(String username, String password) {
        Response<LoginData> loading = new Response<>(Status.LOADING, null, null);
        eventData.setValue(loading);
        Disposable disposable = createAccount.getCreateAccountResponse(username, password).subscribeOn(Schedulers.io()).subscribe((result) -> {
            if(result.status.equals("success")) {
                Response<LoginData> loginDataResponse = new Response<>(Status.COMPLETE, new LoginData(LoginData.LoginDataEnum.SUCCESS, result.user_id, result.username), null);
                eventData.postValue(loginDataResponse);
            } else {
                Response<LoginData> loginDataResponse = new Response<>(Status.COMPLETE, new LoginData(), null);
                eventData.postValue(loginDataResponse);
            }
        });
        compositeDisposable.add(disposable);
    }

}
