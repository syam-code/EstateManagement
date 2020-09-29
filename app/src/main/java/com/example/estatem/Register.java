package com.example.estatem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Register extends AppCompatActivity implements View.OnClickListener {
    Intent intent;

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.textView13: // R.id.textView1
                intent = new Intent(this, Login.class);
                break;
            case R.id.textView14: // R.id.textView1
                intent = new Intent(this, Login.class);
                break;
        }
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

}