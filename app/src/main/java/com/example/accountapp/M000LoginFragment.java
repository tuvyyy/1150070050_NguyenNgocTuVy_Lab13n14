package com.example.accountapp;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class M000LoginFragment extends Fragment implements View.OnClickListener {

    private EditText edtEmail, edtPass;
    private Context mContext;
    private AuthService service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.m000_frg_login, container, false);
        initView(v);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        service = ApiClient.getClient().create(AuthService.class);
    }

    private void initView(View v) {
        edtEmail = v.findViewById(R.id.edt_email);
        edtPass = v.findViewById(R.id.edt_pass);

        v.findViewById(R.id.tv_login).setOnClickListener(this);
        v.findViewById(R.id.tv_register).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(
                mContext, androidx.appcompat.R.anim.abc_fade_in));

        int id = v.getId();
        if (id == R.id.tv_login) {
            login();
        } else if (id == R.id.tv_register) {
            ((MainActivity) mContext).gotoRegisterScreen();
        }
    }

    private void login() {
        String email = edtEmail.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();

        UserRequest body = new UserRequest(email, pass);

        service.login(body).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(mContext, response.body(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(mContext, "API error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
