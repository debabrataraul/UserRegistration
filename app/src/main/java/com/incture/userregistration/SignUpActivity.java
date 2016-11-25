package com.incture.userregistration;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.incture.userregistration.data.LoginDataBaseAdapter;

public class SignUpActivity extends AppCompatActivity {


    Button btnSubmit;
    EditText nameET,emailET,addressET,passwordET,confirmPasswordET,mobileET;

    Context context = this;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        btnSubmit=(Button)findViewById(R.id.btnSubmit);

        nameET=(EditText) findViewById(R.id.nameET);
        emailET=(EditText) findViewById(R.id.emailET);
        addressET=(EditText) findViewById(R.id.addressET);
        passwordET=(EditText) findViewById(R.id.passwordET);
        confirmPasswordET=(EditText) findViewById(R.id.confirmPasswordET);
        mobileET=(EditText) findViewById(R.id.mobileET);

        btnSubmit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String name=nameET.getText().toString();
                String password=passwordET.getText().toString();
                String confirmPassword=confirmPasswordET.getText().toString();
                String email = emailET.getText().toString();
                String mobile=mobileET.getText().toString();
                String address=addressET.getText().toString();

                if(name.equals("")){
                    Toast.makeText(getApplicationContext(),"Name is Mandatory",Toast.LENGTH_LONG).show();
                }else  if(email.equals("")){
                    Toast.makeText(getApplicationContext(),"Email is Mandatory",Toast.LENGTH_LONG).show();
                }else  if(password.equals("")){
                    Toast.makeText(getApplicationContext(),"Password is Mandatory",Toast.LENGTH_LONG).show();
                }else  if(confirmPassword.equals("")){
                    Toast.makeText(getApplicationContext(),"ConfirmPassword is Mandatory",Toast.LENGTH_LONG).show();
                }else  if(mobile.equals("")){
                    Toast.makeText(getApplicationContext(),"Mobile is Mandatory",Toast.LENGTH_LONG).show();
                }else  if(!password.equals(confirmPassword)){
                    Toast.makeText(getApplicationContext(),"Password & ConfirmPassword Should be Same",Toast.LENGTH_LONG).show();
                }else{
                    byte []profilePic=null;
                    loginDataBaseAdapter.insertEntry(email,password,name,mobile,address,profilePic);
                    Toast.makeText(getApplicationContext(),
                            "Account Successfully Created ", Toast.LENGTH_LONG)
                            .show();
                    Intent i = new Intent(SignUpActivity.this,
                            MainActivity.class);
                    startActivity(i);
                    finish();

                }

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDataBaseAdapter.close();
    }
}
