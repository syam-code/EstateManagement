package com.example.estatem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    ChipNavigationBar buttonNav;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNav = findViewById(R.id.button_nav);

        if (savedInstanceState==null){
            buttonNav.setItemSelected(R.id.beranda, true);
            fragmentManager = getSupportFragmentManager();
//            BerandaFragment berandaFragment = new BerandaFragment();
//            fragmentManager.beginTransaction().replace(R.id.fragment_container, berandaFragment)
//                    .commit();
            SetorHafalanFragment setorHafalanFragment = new SetorHafalanFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, setorHafalanFragment).commit();
        }

        buttonNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch (id){
                    case R.id.beranda:
                        fragment = new BerandaFragment();
                        break;
                    case R.id.akun:
                        fragment = new AkunFragment();
                        break;
                    case R.id.berita:
                        fragment = new RecordFragment();
                        break;
                }

                if (fragment!=null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                            .commit();
                } else {
                    Log.e(TAG, "Error");
                }
            }
        });

    }
}