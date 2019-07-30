package com.t3h.buoi12;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private final String TAG = "LoginFragment";
    private EditText edtUsername, edtPass;
    private Button btnLogin, btnRegiter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG , "onCreateView");
        View v = inflater.inflate(R.layout.activity_login, container , false);
        return v;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG , "onActivityCreated");
        edtUsername = getActivity().findViewById(R.id.ed_username);
        edtPass = getActivity().findViewById(R.id.ed_pass);

        btnLogin = getActivity().findViewById(R.id.btn_dangnhap);
        btnRegiter = getActivity().findViewById(R.id.btn_dangky);

        btnLogin.setOnClickListener(this);
        btnRegiter.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_dangnhap:
                String username = edtUsername.getText().toString();
                String pass = edtPass.getText().toString();
                if (pass.isEmpty() || username.isEmpty()) {
//                    Toast.makeText(this,"Null!",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_dangky:
                MainActivity main = (MainActivity) getActivity();
                main.showFragment(main.getFmRegiter());
                break;
            default:
                break;
        }
    }
    public void setData(String username, String pass){
        edtUsername.setText(username);
        edtPass.setText(pass);

    }
}
