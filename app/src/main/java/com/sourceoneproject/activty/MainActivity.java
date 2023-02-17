package com.sourceoneproject.activty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sourceoneproject.R;
import com.sourceoneproject.fragment.LatestFragment;
import com.sourceoneproject.fragment.PopularFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.latest);
    }

    LatestFragment latestFragment = new LatestFragment();
    PopularFragment popularFragment = new PopularFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.latest:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, latestFragment).commit();
                return true;

            case R.id.popular:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, popularFragment).commit();
                return true;

              }
        return false;
    }
}