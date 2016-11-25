package com.incture.userregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn,btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn=(Button)findViewById(R.id.btnSignIn);
        btnSignUp=(Button)findViewById(R.id.btnSignUp);


        btnSignUp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intentSignUP = new Intent(getApplicationContext(),
                        SignUpActivity.class);
                startActivity(intentSignUP);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intentSignUP = new Intent(getApplicationContext(),
                        SignInActivity.class);
                startActivity(intentSignUP);
            }
        });

       Button test=(Button)findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intentUserProfile = new Intent(getApplicationContext(),
                        UserProfile.class);
                startActivity(intentUserProfile);
            }
        });

    }
}
