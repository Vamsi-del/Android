package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4;
    EditText name,email,pass,pass1;
    RadioGroup rg;
    RadioButton rb,rb1;
    Spinner sp;
    CheckBox t,h,en;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.Pa);
        pass1=findViewById(R.id.CP);
        sp = findViewById(R.id.spin);
        rg = findViewById(R.id.rg);
        t = findViewById(R.id.tel);
        h = findViewById(R.id.hin);
        en = findViewById(R.id.en);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);
    }

    public void Register(View view){
        int id1=rg.getCheckedRadioButtonId();
        rb=findViewById(id1);
        Toast.makeText(this, ""+rb.getText().toString(), Toast.LENGTH_SHORT).show();
        String n=name.getText().toString();
        String e=email.getText().toString();
        String p=pass.getText().toString();
        String p1=pass1.getText().toString();
        String branch=sp.getSelectedItem().toString();
        int id=rg.getCheckedRadioButtonId();
        rb=findViewById(id);
        StringBuilder builder = new StringBuilder();
        if (t.isChecked()){
            builder.append("Telugu"+"\n");
        }
        if (h.isChecked()){
            builder.append("Hindi"+"\n");
        }
        if (en.isChecked()) {
            builder.append("English" + "\n");
        }
        if (!(p1.equals(p) && (e.endsWith("@gmail.com")) && (n.length()>=6))){
            if (!p1.equals(p)) {
                tv3.setText("*Passwords Must Be Same   "+p);
                tv4.setText("*Passwords Must Be Same   "+p1);
            }
            else {
                tv3.setText("");
                tv4.setText("");
            }
            if (n.length()<=6) {
                tv1.setText("*Name Should be Atleast 6 Characters");
                }
            else {
                tv1.setText("");
            }
            if (!e.endsWith("@gmail.com")) {
                tv2.setText("*Email Should didn't end with @gmail.com");
                }
            else {
                tv2.setText("");
            }
        }
        else {
            Toast.makeText(this, "Welcome to google Your Details are \n" + n + "\n" + e + "\n" + p, Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MainActivity2.class);
            startActivity(i);
        }
    }

}