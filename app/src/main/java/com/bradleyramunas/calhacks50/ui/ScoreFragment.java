package com.bradleyramunas.calhacks50.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bradleyramunas.calhacks50.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ScoreFragment extends Fragment {
    private String category;
    private int unsolved;
    private int total;
    private int user_id;

    private TextView totalLabel;
    private Button question;

    public static ScoreFragment newInstance(String category, int unsolved, int total, int user_id) {
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        bundle.putInt("unsolved", unsolved);
        bundle.putInt("total", total);
        bundle.putInt("id", user_id);

        ScoreFragment fragment = new ScoreFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if(bundle != null) {
            category = bundle.getString("category");
            unsolved = bundle.getInt("unsolved");
            total = bundle.getInt("total");
            user_id = bundle.getInt("id");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_score, container, false);

        totalLabel = view.findViewById(R.id.score_TextView_Remaining);
        question = view.findViewById(R.id.score_Button_Question);

        readBundle(getArguments());

        totalLabel.setText(unsolved + " out of " + total);
        if(unsolved == 0) {
            question.setEnabled(false);
        } else {
            question.setEnabled(true);
        }
        question.setOnClickListener(l -> {
            Intent intent = new Intent(getActivity(), QuestionActivity.class);
            intent.putExtra("category", category);
            intent.putExtra("user_id", user_id);
            startActivityForResult(intent, 0);
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 0) {
            unsolved -= 1;
            totalLabel.setText(unsolved + " out of " + total);
        }
    }
}
