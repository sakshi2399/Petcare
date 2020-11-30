package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Registeration extends AppCompatActivity {

    TextInputEditText textInputEditTextFullname, textInputEditTextUsername, textInputEditTextPassword , textInputEditTextEmail
    ,textInputEditTextAddress, textInputEditTextcity,textInputEditTextpincode;
    Button buttonSignup;
    TextView textViewLogin;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        textInputEditTextFullname = findViewById(R.id.fullname);
        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password);
        textInputEditTextEmail = findViewById(R.id.email);
        textInputEditTextAddress = findViewById(R.id.address);
        textInputEditTextcity = findViewById(R.id.city);
        textInputEditTextpincode = findViewById(R.id.pincode);
        buttonSignup = findViewById(R.id.buttonSignUp);
        textViewLogin = findViewById(R.id.loginText);
        progressBar = findViewById(R.id.progress);

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),General.class);
                startActivity(intent);
                finish();
            }
        });
        buttonSignup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                final String fullname,username,password,email,address,city,pincode;
                fullname=String.valueOf(textInputEditTextFullname.getText());
                username=String.valueOf(textInputEditTextUsername.getText());
                password=String.valueOf(textInputEditTextPassword.getText());
                email=String.valueOf(textInputEditTextEmail.getText());
                address=String.valueOf(textInputEditTextAddress.getText());
                city=String.valueOf(textInputEditTextcity.getText());
                pincode=String.valueOf(textInputEditTextpincode.getText());
                if(!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals(""))
                {
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[7];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";
                            field[4] = "address";
                            field[5] = "city";
                            field[6] = "pincode";
                            //Creating array for data
                            String[] data = new String[7];
                            data[0] = fullname;
                            data[1] = username;
                            data[2] = password;
                            data[3] = email;
                            data[4] = address;
                            data[5] = city;
                            data[6] = pincode;
                            PutData putData = new PutData("http://192.168.43.193/LoginRegisteration/signup.php", "POST", field, data);
                            if (putData.startPut())
                            {
                                if (putData.onComplete())

                                {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();

                                    if(result.equals("Sign Up Success"))
                                    {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),General.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else
                                        {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    }
                                    //End ProgressBar (Set visibility to GONE)

                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(), "All fields are required",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Start ProgressBar first (Set visibility VISIBLE)

    }
}