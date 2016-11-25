package com.incture.userregistration;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.incture.userregistration.data.LoginDataBaseAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    LoginDataBaseAdapter loginDataBaseAdapter;
    ListView listView;
    TextView tv1,tv2;
    ImageView profilePV;
    Bitmap bm;
    String NAME,EMAIL;
    int REQUEST_CAMERA=0,SELECT_FILE=1;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header= navigationView.getHeaderView(0);
        tv1=(TextView)header.findViewById(R.id.textViewName);
        tv2=(TextView)header.findViewById(R.id.textViewEmail);
        NAME=this.getIntent().getStringExtra("NAME");
        EMAIL=this.getIntent().getStringExtra("EMAIL");
        tv1.setText(NAME);
        tv2.setText(EMAIL);
        //database connection initialization
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        //getting the profile pic
        Map<String,Object> userDetails=loginDataBaseAdapter.getSinlgeEntry(EMAIL);
        byte[] profilePic=(byte[]) userDetails.get("PROFILE_PIC");


        profilePV = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.profilePic);


        if(profilePic!=null){
             bm = BitmapFactory.decodeByteArray(profilePic, 0, profilePic.length);
            if (bm!=null) {
                profilePV.setImageBitmap(bm);
                header.setBackgroundResource(R.drawable.sunshine_theme);
            }
        }else{
            profilePV.setImageResource(R.drawable.profile_pic1);
            //setting back ground
            header.setBackgroundResource(R.drawable.sunshine_theme);
        }




        profilePV.setOnClickListener((new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               // selectImage();
               // verifyStoragePermissions(UserPage.this);
                Intent userProfileIntent=new Intent(getApplicationContext(),UserProfile.class);
                startActivity(userProfileIntent);

            }
        }));


        final Map<String,Map<String,String>> allUserDetails=displayAllUser();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                int itemPosition     = position;
                // ListView Clicked item value
                String itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

                List<String> userDetailsList = new ArrayList();
                Map<String,String> userDetails=allUserDetails.get(itemValue);
                userDetailsList.add("Name     :   " + userDetails.get("NAME"));
                userDetailsList.add("Email    :   " + userDetails.get("EMAIL"));
                userDetailsList.add("Mobile   :   " + userDetails.get("MOBILE"));
                userDetailsList.add("Address  :   " + userDetails.get("ADDRESS"));


                Intent intentUser = new Intent(getApplicationContext(),
                        ShowUserDetails.class);
                intentUser.putExtra("userDetailsList", (Serializable) userDetailsList);
                intentUser.putExtra("EMAIL", userDetails.get("EMAIL"));
                startActivity(intentUser);

            }

        });

    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(UserPage.this);
        builder.setTitle("Change Profile Pic");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment
                            .getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                File f = new File(Environment.getExternalStorageDirectory()
                        .toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                   // Bitmap bm;
                    BitmapFactory.Options btmapOptions = new BitmapFactory.Options();

                    bm = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            btmapOptions);

                    // bm = Bitmap.createScaledBitmap(bm, 70, 70, true);

                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "userTemp" + File.separator + "default";
                    f.delete();
                    OutputStream fOut = null;
                    File file = new File(path, String.valueOf(System
                            .currentTimeMillis()) + ".jpg");
                    try {
                        fOut = new FileOutputStream(file);
                        bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
                        fOut.flush();
                        fOut.close();
                        profilePV.setImageBitmap(bm);
                        updateProfilePic();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();

                String tempPath = getPath(selectedImageUri, UserPage.this);
               // Bitmap bm;
                BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
                bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
                profilePV.setImageBitmap(bm);
                updateProfilePic();
            }
        }
    }
    public String getPath(Uri uri, Activity activity) {
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = activity
                .managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

   private Map<String,Map<String,String>> displayAllUser(){
//        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
//        loginDataBaseAdapter = loginDataBaseAdapter.open();

       Map<String,Map<String,String>> allUserDetails=
               loginDataBaseAdapter.getAllUserData();

       List<String> users= new ArrayList<String>();
       users.addAll(allUserDetails.keySet());
       listView=(ListView) findViewById(R.id.users_list);

       ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
               UserPage.this ,R.layout.set_details,R.id.tvName, users);
       listView.setAdapter(arrayAdapter);
        return allUserDetails;
    }
    private void updateProfilePic(){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        /*loginDataBaseAdapter = new LoginDataBaseAdapter(UserPage.this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();*/

        loginDataBaseAdapter.updateProfilePic(EMAIL,byteArray);
        Toast.makeText(UserPage.this,
                "Profile Pic Updated...!", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDataBaseAdapter.close();
    }
}
