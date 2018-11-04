package com.bradleyramunas.calhacks50.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bradleyramunas.calhacks50.R;
import com.bradleyramunas.calhacks50.data.Response;
import com.bradleyramunas.calhacks50.data.Status;
import com.bradleyramunas.calhacks50.data.api.solves.SolvesJson;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    private HomeViewModel viewModel;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FrameLayout frameLayout;
    private ProgressBar progressBar;

    private HashMap<String, Fragment> fragmentMap = new HashMap<>();
    private Fragment currentFragment = null;
    private String currentCategory = "";

    private int user_id;

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
        user_id = getIntent().getIntExtra("user_id", -1);
        drawerLayout = findViewById(R.id.home_DrawerLayout_Layout);
        progressBar = findViewById(R.id.home_ProgressBar_Loading);
        navigationView = findViewById(R.id.home_NavigationView_Drawer);
        frameLayout = findViewById(R.id.home_FrameLayout_Holder);
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            menuItem.setChecked(true);
            drawerLayout.closeDrawers();
            if(menuItem.getTitle().equals("Home")) {

            } else {
                currentCategory = menuItem.getTitle().toString();
                getSupportActionBar().setTitle(currentCategory);
                requestFragment(menuItem.getTitle().toString());
            }
            return true;
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestFragment(String category) {
        if(fragmentMap.containsKey(category)) {
            Fragment fragment = fragmentMap.get(category);
            setFragment(fragment);
        } else {
            if(currentFragment != null) {
                getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            }
            viewModel.getSolves(user_id, category);
        }
    }

    private void createFragment(SolvesJson data) {
        Fragment newFragment = ScoreFragment.newInstance(currentCategory, data.unsolved, data.total, user_id);
        fragmentMap.put(currentCategory, newFragment);
        setFragment(newFragment);
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.home_FrameLayout_Holder, fragment).commit();
        currentFragment = fragment;
    }

    private void handleResult(Response<SolvesJson> response) {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        progressBar.setVisibility(View.GONE);
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
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void onComplete(SolvesJson data) {
        createFragment(data);
    }

    private void onError(Throwable error) {

    }

}
