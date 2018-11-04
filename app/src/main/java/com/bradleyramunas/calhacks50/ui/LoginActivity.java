package com.bradleyramunas.calhacks50.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bradleyramunas.calhacks50.R;
import com.bradleyramunas.calhacks50.data.LoginData;
import com.bradleyramunas.calhacks50.data.Response;
import com.bradleyramunas.calhacks50.data.Status;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel viewModel;

    private TextView failureTextView;
    private ProgressBar progressBar;
    private TextView username;
    private TextView password;
    private Button login;
    private Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupView();

        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        viewModel.getEventLiveData().observe(this, this::handleResult);
    }

    private void setupView() {
        failureTextView = findViewById(R.id.login_TextView_Incorrect);
        progressBar = findViewById(R.id.login_ProgressBar_Loading);
        username = findViewById(R.id.login_EditText_Username);
        password = findViewById(R.id.login_EditText_Password);
        login = findViewById(R.id.login_Button_Login);
        create = findViewById(R.id.login_Button_Create);
    }

    private void handleResult(Response<LoginData> response) {
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
        failureTextView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        login.setEnabled(false);
        create.setEnabled(false);
    }

    private void onComplete(LoginData data) {
        progressBar.setVisibility(View.INVISIBLE);
        login.setEnabled(true);
        create.setEnabled(true);
        switch(data.status) {
            case SUCCESS:
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("user_id", data.user_id);
                startActivity(intent);
                break;
            case FAILURE:
                failureTextView.setText(getString(R.string.text_incorrect_username_or_password));
                failureTextView.setVisibility(View.VISIBLE);
                break;
            case EXIST:
                failureTextView.setText(getString(R.string.text_already_exists));
                failureTextView.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void onError(Throwable error) {
        progressBar.setVisibility(View.INVISIBLE);
        Log.e("LoginActivity", error.getMessage());
    }

    public void onLoginPress(View view) {
        viewModel.attemptLogin(username.getText().toString(), password.getText().toString());
    }

    public void onCreateAccountPress(View view) {
        viewModel.attemptCreate(username.getText().toString(), password.getText().toString());
    }



}
