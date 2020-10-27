package com.example.software;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class myClass extends SQLiteOpenHelper {
    public static String dbName="quickCellarApp.db";
    public static int dbVersion=1;
    public static String dbPath="";
    Context myContext;

    public myClass(Context context){
        super(context,dbName,null,dbVersion);
        myContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private boolean ExistDatabase(){
        File myFile=new File(dbPath + dbName);
        return myFile.exists();
    }

    private void copyDatabase(){
        try{
            InputStream myInput=myContext.getAssets().open(dbName);
            OutputStream myOutput=new FileOutputStream(dbPath + dbName);
            byte [] myBuffer=new byte[1024];
            int lengh;
            while ((lengh=myInput.read(myBuffer))>0){
                myOutput.write(myBuffer,0,lengh);
            }
            myOutput.flush();myOutput.close();myInput.close();
        }catch (Exception ex){

        }

    }
    public void startWork(){
        dbPath=myContext.getFilesDir().getParent()+"/databases/";
        if(!ExistDatabase()){
            this.getWritableDatabase();
            copyDatabase();
        }
    }

}
