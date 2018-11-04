package com.bradleyramunas.calhacks50.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bradleyramunas.calhacks50.R;
import com.bradleyramunas.calhacks50.data.Response;
import com.bradleyramunas.calhacks50.data.Status;
import com.bradleyramunas.calhacks50.data.api.solves.SolvesJson;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    private HomeViewModel viewModel;

    private NavigationView navigationView;
    private FrameLayout frameLayout;

    private HashMap<String, ScoreFragment> fragmentMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupView();

        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        viewModel.getEventLiveData().observe(this, this::handleResult);
    }

    public void setupView() {
        getSupportActionBar().setTitle("Home");
        navigationView = findViewById(R.id.home_NavigationView_Drawer);
        frameLayout = findViewById(R.id.home_FrameLayout_Holder);

    }

    private void handleResult(Response<SolvesJson> response) {
        Status status = response.getStatus();
        switch(status) {
            case LOADING:
                onLoading();
                break;
            case COMPLETE:
                onComplete(response.getData());
                break;
            case ERROR:
                onError(response.getError());
                break;
        }
    }

    private void onLoading() {

    }

    private void onComplete(SolvesJson data) {

    }

    private void onError(Throwable error) {

    }

}
