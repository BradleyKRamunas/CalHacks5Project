package com.bradleyramunas.calhacks50.ui;

import com.bradleyramunas.calhacks50.data.Response;
import com.bradleyramunas.calhacks50.data.Status;
import com.bradleyramunas.calhacks50.data.api.solves.Solves;
import com.bradleyramunas.calhacks50.data.api.solves.SolvesJson;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<Response<SolvesJson>> eventData = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Solves solves = new Solves();

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public MutableLiveData<Response<SolvesJson>> getEventLiveData() {
        return eventData;
    }

    public void getSolves(int user_id, String category) {
        Response<SolvesJson> loading = new Response<>(Status.LOADING, null, null);
        eventData.setValue(loading);
        Disposable disposable = solves.getSolves(user_id, category).subscribeOn(Schedulers.io()).subscribe(result -> {
            Response<SolvesJson> response = new Response<>(Status.COMPLETE, result, null);
            eventData.postValue(response);
        });
    }
}
