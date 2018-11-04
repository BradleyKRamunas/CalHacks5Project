package com.bradleyramunas.calhacks50.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.bradleyramunas.calhacks50.R;
import com.bradleyramunas.calhacks50.data.Response;
import com.bradleyramunas.calhacks50.data.Status;
import com.bradleyramunas.calhacks50.data.api.question.QuestionJson;

public class QuestionActivity extends AppCompatActivity {

    private QuestionViewModel viewModel;
    private ProgressBar progressBar;
    private int user_id;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        setupView();

        viewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);
        viewModel.getEventLiveData().observe(this, this::handleResult);
        viewModel.getSubmitLiveData().observe(this, result -> {
            Log.i("HELLOOOO", "HELLOOO???");
            if(result.getStatus() == Status.COMPLETE) {
                setResult(0);
                finish();
            } else {
                setResult(1);
                finish();
            }
        });
        viewModel.fetchRandomQuestion(category, user_id);
    }

    private void setupView() {
        user_id = getIntent().getIntExtra("user_id", -1);
        category = getIntent().getStringExtra("category");
        progressBar = findViewById(R.id.question_ProgressBar_Loading);
        getSupportActionBar().setTitle("Question");
    }

    private void handleResult(Response<QuestionJson> response) {
        Status status = response.getStatus();
        progressBar.setVisibility(View.GONE);
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
        progressBar.setVisibility(View.VISIBLE);
    }

    private void onComplete(QuestionJson data) {
        Log.i("TESTPRIOR", "question id:"+data.id);
        Fragment fragment = QuestionFragment.newInstance(user_id, data.id, data.text, data.question, data.answer);
        getSupportFragmentManager().beginTransaction().replace(R.id.question_FrameLayout_Holder, fragment).commit();
    }

    private void onError(Throwable error) {

    }
}
