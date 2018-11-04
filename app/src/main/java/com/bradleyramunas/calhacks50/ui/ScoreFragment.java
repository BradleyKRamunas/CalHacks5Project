package com.bradleyramunas.calhacks50.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bradleyramunas.calhacks50.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ScoreFragment extends Fragment {
    private String category;
    private int unsolved;
    private int total;

    private TextView totalLabel;

    public static ScoreFragment newInstance(String category, int unsolved, int total) {
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        bundle.putInt("unsolved", unsolved);
        bundle.putInt("total", total);

        ScoreFragment fragment = new ScoreFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if(bundle != null) {
            category = bundle.getString("category");
            unsolved = bundle.getInt("unsolved");
            total = bundle.getInt("total");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_score, container, false);

        totalLabel = view.findViewById(R.id.score_TextView_Remaining);

        readBundle(getArguments());

        totalLabel.setText(unsolved + "/" + total);

        return view;
    }
}
