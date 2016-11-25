package com.incture.userregistration;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.incture.userregistration.data.LoginDataBaseAdapter;

import java.util.ArrayList;
import java.util.Map;

public class ShowUserDetails extends AppCompatActivity {
    LoginDataBaseAdapter loginDataBaseAdapter;
    ListView listView;
    Bitmap bm;
    ImageView profilePV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_show_user_details);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        ArrayList<String> userDetailsList =  (ArrayList<String>)getIntent().getSerializableExtra("userDetailsList");
        String email=getIntent().getStringExtra("EMAIL");
        Map<String,Object> userDetails=loginDataBaseAdapter.getSinlgeEntry(email);

        byte[] profilePic=(byte[]) userDetails.get("PROFILE_PIC");
        if(profilePic!=null) {
            bm = BitmapFactory.decodeByteArray(profilePic, 0, profilePic.length);
        }
         profilePV=(ImageView)findViewById(R.id.profilePicDetails);
         profilePV.setImageBitmap(bm);

        listView=(ListView) findViewById(R.id.userDetails_list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                ShowUserDetails.this ,R.layout.set_details,R.id.tvName, userDetailsList);
        listView.setAdapter(arrayAdapter);
    }
}
