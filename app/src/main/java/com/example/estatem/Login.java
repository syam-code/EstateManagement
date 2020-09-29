package com.example.estatem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class Login extends AppCompatActivity implements View.OnClickListener {
    Intent intent;

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.textView13: // R.id.textView1
                intent = new Intent(this, LupaPassword.class);
                break;
            case R.id.textView14: // R.id.textView1
                intent = new Intent(this, Register.class);
                break;
        }
        startActivity(intent);
    }
    public void myClickHandler(View target) {
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);





    }
}
