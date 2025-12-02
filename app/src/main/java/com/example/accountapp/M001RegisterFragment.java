package com.example.accountapp;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class M001RegisterFragment extends Fragment {

    private EditText edtEmail, edtPass, edtRepass;

    private Context mContext;
    private AuthService service;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        service = ApiClient.getClient().create(AuthService.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.m001_frg_register, container, false);

        edtEmail = v.findViewById(R.id.edt_email);
        edtPass = v.findViewById(R.id.edt_pass);
        edtRepass = v.findViewById(R.id.edt_re_pass);

        // nút đăng ký = TextView tv_register
        v.findViewById(R.id.tv_register).setOnClickListener(view -> register());

        // nút back
        v.findViewById(R.id.iv_back).setOnClickListener(view -> {
            ((MainActivity) mContext).gotoLoginScreen();
        });

        return v;
    }

    private void register() {
        String email = edtEmail.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();
        String repass = edtRepass.getText().toString().trim();

        if (email.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
            Toast.makeText(mContext, "Empty value", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!pass.equals(repass)) {
            Toast.makeText(mContext, "Password does not match", Toast.LENGTH_SHORT).show();
            return;
        }

        UserRequest body = new UserRequest(email, pass);

        service.register(body).enqueue(new Callback<String>() {
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
