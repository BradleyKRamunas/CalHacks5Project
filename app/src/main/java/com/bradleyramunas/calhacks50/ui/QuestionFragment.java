package com.bradleyramunas.calhacks50.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bradleyramunas.calhacks50.R;
import com.bradleyramunas.calhacks50.data.Response;
import com.bradleyramunas.calhacks50.data.Status;
import com.bradleyramunas.calhacks50.data.api.submit.SubmitJson;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import katex.hourglass.in.mathlib.MathView;

public class QuestionFragment extends Fragment {
    private int user_id;
    private int id;
    private String preText;
    private String questionText;
    private String ans;

    private TextView preTextView;
    private MathView questionTextView;
    private EditText answer;

    private Button submit;

    private QuestionViewModel viewModel;

    public static QuestionFragment newInstance(int user_id, int question_id, String text, String question, String answer) {
        Bundle bundle = new Bundle();
        bundle.putInt("user_id", user_id);
        bundle.putInt("id", question_id);
        bundle.putString("text", text);
        bundle.putString("question", question);
        bundle.putString("answer", answer);

        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if(bundle != null) {
            user_id = bundle.getInt("user_id");
            id = bundle.getInt("id");
            preText = bundle.getString("text");
            questionText = bundle.getString("question");
            ans = bundle.getString("answer");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_question, container, false);

        preTextView = view.findViewById(R.id.home_TextView_Text);
        questionTextView = view.findViewById(R.id.home_MathView_Question);
        submit = view.findViewById(R.id.home_Button_Submit);
        answer = view.findViewById(R.id.home_EditText_Answer);
        viewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);

        readBundle(getArguments());

        preTextView.setText(preText);
        questionTextView.setDisplayText(questionText);
        submit.setOnClickListener(click -> {
            Log.i("TEST", "TESt");
            String usrAns = answer.getText().toString();
            if(usrAns.equals(ans)) {
                Log.i("TEST", "FIRST");
                Log.i("TEST@", "user_id" + user_id + ", question_id" + id);
                getActivity().setResult(0);
                viewModel.submitRecord(user_id, id, 1000);
                getActivity().finish();
            } else {
                Log.i("TEST", "SECOND");
                getActivity().setResult(1);
                viewModel.fail();
                getActivity().finish();
            }

        });

        return view;
    }

}
