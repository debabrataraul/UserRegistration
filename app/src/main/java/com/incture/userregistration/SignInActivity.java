package com.incture.userregistration;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.incture.userregistration.data.LoginDataBaseAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {

    Button loginBtnIn;
    EditText emailETin,passwordETin;

    Context context = this;
    LoginDataBaseAdapter loginDataBaseAdapter;
    ListView listView;

    RelativeLayout login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        loginBtnIn=(Button)findViewById(R.id.loginBtnIn);
        listView = (ListView) findViewById(R.id.user_list);

        login = (RelativeLayout)findViewById(R.id.test);

        emailETin=(EditText) findViewById(R.id.emailETin);
        passwordETin=(EditText) findViewById(R.id.passwordETin);

        loginBtnIn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                String email=emailETin.getText().toString();
                String password=passwordETin.getText().toString();

                Map<String,Object> userDetails=loginDataBaseAdapter.getSinlgeEntry(email);

                if(userDetails==null){
                    Toast.makeText(SignInActivity.this,
                            "User details Not Present", Toast.LENGTH_LONG).show();
                }else {
                    if (!userDetails.get("PASSWORD").equals(password)) {
                        Toast.makeText(SignInActivity.this,
                                "Email id or Password does not match", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(SignInActivity.this,
                                "Congrats: Login Successful", Toast.LENGTH_LONG).show();

                        /*login.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                        List<String> userDetailsList = new ArrayList();
                        userDetailsList.add("Name     :   " + userDetails.get("NAME"));
                        userDetailsList.add("Email    :   " + userDetails.get("EMAIL"));
                        userDetailsList.add("Mobile   :   " + userDetails.get("MOBILE"));
                        userDetailsList.add("Address  :   " + userDetails.get("ADDRESS"));

                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                               SignInActivity.this ,R.layout.set_details,R.id.tvName, userDetailsList);
                        //android.R.layout.simple_list_item_1
                        listView.setAdapter(arrayAdapter);*/


                        Intent intentUserPage=new Intent(getApplicationContext(),UserPage.class);
                        intentUserPage.putExtra("EMAIL",(String) userDetails.get("EMAIL"));
                        intentUserPage.putExtra("NAME",(String)userDetails.get("NAME"));
                        intentUserPage.putExtra("MOBILE",(String)userDetails.get("MOBILE"));
                        //can n't pass more data in intent
                        //intentUserPage.putExtra("PROFILE_PIC",(byte[]) userDetails.get("PROFILE_PIC"));
                        startActivity(intentUserPage);

                    }
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
