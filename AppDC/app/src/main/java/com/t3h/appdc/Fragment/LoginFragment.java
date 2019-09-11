package com.t3h.appdc.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.t3h.appdc.LoginActivity;
import com.t3h.appdc.MainActivity;
import com.t3h.appdc.R;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private EditText edUser, edPass;
    private Button btnLogin, btnResgeter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        edUser = getActivity().findViewById(R.id.ed_username);
        edPass = getActivity().findViewById(R.id.ed_pass);

        btnLogin =  getActivity().findViewById(R.id.btn_dangnhap);
        btnResgeter = getActivity().findViewById(R.id.btn_dangky);

        btnLogin.setOnClickListener(this);
        btnResgeter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_dangnhap:
                Intent main = new Intent(getActivity(), MainActivity.class);
                startActivity(main);

                break;
            case R.id.btn_dangky:
                LoginActivity login = (LoginActivity) getActivity();
                login.showFragment(login.getFrmResgeter());
                break;
            default:
                break;
        }
    }
}
