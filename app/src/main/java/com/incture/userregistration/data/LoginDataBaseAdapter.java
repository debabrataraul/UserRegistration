package com.incture.userregistration.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by debabrataraul on 11/14/2016.
 */

public class LoginDataBaseAdapter {

    static final String DATABASE_NAME="users1.db";
    static  final int DATABASE_VERSION=1;
    public static final int NAME_COLUMN = 1;

    static final String DATABASE_CREATE="create table " + "USERS1" + "( "
            + "EMAIL" + " TEXT primary key not null,"
            + "PASSWORD TEXT,NAME TEXT,MOBILE TEXT,ADDRESS TEXT,PROFILE_PIC BLOB ); ";

    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;

    public LoginDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }
    public LoginDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String email,String password,String name,String mobile,String address,byte[] profilePic) {
        ContentValues newValues = new ContentValues();
        newValues.put("EMAIL", email);
        newValues.put("PASSWORD", password);
        newValues.put("NAME", name);
        newValues.put("MOBILE", mobile);
        newValues.put("ADDRESS", address);
        newValues.put("PROFILE_PIC", profilePic);

        db.insert("USERS1", null, newValues);

    }

    public int deleteEntry(String UserName) {

        String where = "EMAIL=?";
        int numberOFEntriesDeleted = db.delete("USERS1", where,
                new String[] { UserName });
        return numberOFEntriesDeleted;
    }

    public Map<String,Object> getSinlgeEntry(String email) {
        Map<String,Object> userDetails=null;
        Cursor cursor = db.rawQuery( "select * from USERS1 where EMAIL = '"+email+"'", null );
        if (cursor.getCount() < 1) {
            cursor.close();
            return userDetails;
        }
        cursor.moveToFirst();
        userDetails=new HashMap<String, Object>();

        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        String name = cursor.getString(cursor.getColumnIndex("NAME"));
        String mobile = cursor.getString(cursor.getColumnIndex("MOBILE"));
        String address = cursor.getString(cursor.getColumnIndex("ADDRESS"));
        byte[] profilePic = cursor.getBlob(cursor.getColumnIndex("PROFILE_PIC"));

        userDetails.put("PASSWORD",password);
        userDetails.put("NAME",name);
        userDetails.put("MOBILE",mobile);
        userDetails.put("ADDRESS",address);
        userDetails.put("EMAIL",email);
        userDetails.put("PROFILE_PIC",profilePic);

        cursor.close();
        return userDetails;
    }

    public void updateEntry(String email, String password,String name,String Mobile,String address,byte[] profilePic) {
        ContentValues updatedValues = new ContentValues();

        updatedValues.put("EMAIL", email);
        updatedValues.put("PASSWORD", password);
        updatedValues.put("NAME", name);
        updatedValues.put("MOBILE", Mobile);
        updatedValues.put("ADDRESS", address);
        updatedValues.put("PROFILE_PIC",profilePic);
        String where = "EMAIL = ?";
        db.update("USERS1", updatedValues, where, new String[] { email });
    }
    public void updateProfilePic(String email,byte[] profilePic){
        String sql = "UPDATE USERS1 SET PROFILE_PIC  = "+profilePic+" WHERE EMAIL = "+email;
        ContentValues updatedValues = new ContentValues();

        updatedValues.put("PROFILE_PIC",profilePic);
        String where = "EMAIL = ?";
        db.update("USERS1", updatedValues, where, new String[] { email });
    }

    public Map<String,Map<String,String>> getAllUserData(){

        Map<String,Map<String,String>> allUserDetails;
        Map<String,String> userDetails;
        Cursor cursor = db.rawQuery( "select * from USERS1", null );
        if (cursor.getCount() < 1) {
            cursor.close();
            return null;
        }

        allUserDetails=new HashMap<String, Map<String, String>>();
        cursor.moveToFirst();

        do {
            userDetails=new HashMap<String, String>();
            userDetails.put("EMAIL",cursor.getString(cursor.getColumnIndex("EMAIL")));
            userDetails.put("NAME",cursor.getString(cursor.getColumnIndex("NAME")));
            userDetails.put("MOBILE",cursor.getString(cursor.getColumnIndex("MOBILE")));
            userDetails.put("ADDRESS",cursor.getString(cursor.getColumnIndex("ADDRESS")));

            allUserDetails.put(cursor.getString(cursor.getColumnIndex("EMAIL")),userDetails);
        }while (cursor.moveToNext());

        return  allUserDetails;
    }

}
