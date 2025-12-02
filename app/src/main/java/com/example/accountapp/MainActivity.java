package com.example.accountapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gotoLoginScreen();
    }

    public void gotoLoginScreen() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_container, new M000LoginFragment())
                .commit();
    }

    public void gotoRegisterScreen() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_container, new M001RegisterFragment())
                .commit();
    }
}
