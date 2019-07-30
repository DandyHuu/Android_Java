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

public class RegiterFragment extends Fragment implements View.OnClickListener {
    private final String TAG = "RegiterFragment";
    private EditText edtUsernamerRe, edtPassRe;
    private Button btnRegiterRe;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG,"onCreateView");
        View v = inflater.inflate(R.layout.activity_regiter, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        edtPassRe = getActivity().findViewById(R.id.ed_pass_re);
        edtUsernamerRe = getActivity().findViewById(R.id.ed_username_re);

        btnRegiterRe = getActivity().findViewById(R.id.btn_dangky_re);
        btnRegiterRe.setOnClickListener(this);
        Log.e(TAG,"onActivityCreated");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_dangky_re) {
            String username = edtUsernamerRe.getText().toString();
            String pass = edtPassRe.getText().toString();
            if (pass.isEmpty() || username.isEmpty()) {
                Toast.makeText(getContext(),"Data null",Toast.LENGTH_LONG).show();
            }

            MainActivity main = (MainActivity) getActivity();

            main.showFragment(main.getFmLogin());
            main.getFmLogin().setData(username,pass);
            edtPassRe.setText("");
            edtUsernamerRe.setText("");

        }
    }
}
