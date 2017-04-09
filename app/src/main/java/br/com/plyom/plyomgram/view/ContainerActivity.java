package br.com.plyom.plyomgram.view;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import br.com.plyom.plyomgram.R;
import br.com.plyom.plyomgram.view.fragment.HomeFragment;
import br.com.plyom.plyomgram.view.fragment.ProfileFragment;
import br.com.plyom.plyomgram.view.fragment.SearchFragment;

public class ContainerActivity extends AppCompatActivity {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottom_bar);
        bottomBar.setDefaultTab(R.id.home);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.profile:
                        fragment = new ProfileFragment();
                        break;
                    case R.id.search:
                        fragment = new SearchFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .addToBackStack(null).commit();
            }
        });

    }
}
