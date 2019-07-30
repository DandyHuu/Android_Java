package com.t3h.buoi12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private LoginFragment fmLogin = new LoginFragment();
    private RegiterFragment fmRegiter = new RegiterFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        showFragment(fmLogin);
    }

    private void initFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.panel,fmRegiter);
        transaction.add(R.id.panel,fmLogin);
        transaction.commit();
    }

    public void showFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
//        fragmentTransaction.replace(R.id.panel,fragment);
        //hide all fragment
        fragmentTransaction.hide(fmLogin);
        fragmentTransaction.hide(fmRegiter);
        //show fragment need display
        fragmentTransaction.show(fragment);

        fragmentTransaction.commit();
    }

    public LoginFragment getFmLogin() {
        return fmLogin;
    }

    public RegiterFragment getFmRegiter() {
        return fmRegiter;
    }


}
