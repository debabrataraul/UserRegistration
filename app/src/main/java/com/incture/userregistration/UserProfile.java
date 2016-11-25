package com.incture.userregistration;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.incture.userregistration.data.FeedItemProvider;

public class UserProfile extends AppCompatActivity {

    TextView profileTV,activitiesTV;
    ScrollView profileSV;//activitiesSV;
    LinearLayout activitiesLL;
    ListView listView;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        profileTV=(TextView)findViewById(R.id.profile_btn_iv);
        activitiesTV=(TextView)findViewById(R.id.activities_btn_iv);

        profileSV=(ScrollView) findViewById(R.id.content_user_profile);
        activitiesLL=(LinearLayout) findViewById(R.id.content_user_activities);

        profileTV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                profileTV.setBackgroundResource(R.drawable.left_rounded_toolbar_tv_selected);
                profileTV.setTextColor(Color.BLACK);
                activitiesTV.setBackgroundResource(R.drawable.right_rounded_toolbar_tv);
                activitiesTV.setTextColor(Color.WHITE);

                profileSV.setVisibility(View.VISIBLE);
                activitiesLL.setVisibility(View.GONE);
            }
        });

       //listView=(ListView)findViewById(R.id.user_activities_list_view);
         recyclerView= (RecyclerView) findViewById(R.id.recycler_view);

        activitiesTV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                activitiesTV.setBackgroundResource(R.drawable.right_rounded_toolbar_tv_selected);
                activitiesTV.setTextColor(Color.BLACK);
                profileTV.setBackgroundResource(R.drawable.left_rounded_toolbar_tv);
                profileTV.setTextColor(Color.WHITE);

                profileSV.setVisibility(View.GONE);
                activitiesLL.setVisibility(View.VISIBLE);

                /*ArrayList<String> userDetailsList =new ArrayList<String>();
                userDetailsList.add("John Davis created a task");
                userDetailsList.add("John Davis created a task2");
                userDetailsList.add("John Davis created a task3");



                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        UserProfile.this ,R.layout.activities_recycler_view,R.id.activityHeadingRV, userDetailsList);
                listView.setAdapter(arrayAdapter);*/



                MyRecyclerViewAdapter myRecyclerViewAdapter=new MyRecyclerViewAdapter(
                        UserProfile.this,FeedItemProvider.readFeeds());

                recyclerView.setAdapter(myRecyclerViewAdapter);
                //recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(UserProfile.this));

            }
        });


    }//end of onCreate
}
