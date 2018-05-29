package com.full1.finaljecheon;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Created by thevision1 on 2017-02-08.
 */

public class DatabaseHelpers extends SQLiteOpenHelper {
    private static String DB_PATH = "/data/data/com.full1.finaljecheon/databases/";
    public static final String DATABASE_NAME = "MyJeCheon.db";

    public static final String TABLE_TOUR = "tour";
    public static final String TABLE_FOOD = "food";
    public static final String TABLE_MOTEL = "motel";

    public static final String COL_0_NAME = "name";
    public static final String COL_1_ADDRESS = "address";
    public static final String COL_2_PHONE = "phone";
    public static final String COL_3_IMGURL = "imgurl";

   //    public DatabaseHelpers(Context context) {
//        super(context, DATABASE_NAME, null, 1);
//
//    }


    public DatabaseHelpers(Context context) {
        super(context, DATABASE_NAME, null, 1);

        try {
            boolean bResult = isCheckDB(context);  // DB가 있는지?
            Log.d("MiniApp", "DB Check=" + bResult);
            if (!bResult) {   // DB가 없으면 복사
                copyDB(context);
            } else {
                copyDB(context);
            }
        } catch (Exception e) {

        }
    }

    // DB가 있나 체크하기
    public boolean isCheckDB(Context mContext) {
        File file = new File(DB_PATH+DATABASE_NAME);

        if (file.exists()) {
            return true;
        }

        return false;

    }

    // DB를 복사하기
// assets의 /db/xxxx.db 파일을 설치된 프로그램의 내부 DB공간으로 복사하기
    public void copyDB(Context mContext) throws IOException {
        Log.d("MiniApp", "copyDB");
        //Open your local db as the input stream
        InputStream myInput;
        myInput = mContext.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DATABASE_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;

        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
//        AssetManager manager = mContext.getAssets();
//        File folder = new File(DB_PATH);
//        File file = new File(DATABASE_NAME);
//
//        FileOutputStream fos = null;
//        BufferedOutputStream bos = null;
//        try {
//            InputStream is = manager.open(DATABASE_NAME);
//            BufferedInputStream bis = new BufferedInputStream(is);
//
//            if (folder.exists()) {
//            } else {
//                folder.mkdirs();
//            }
//
//
//            if (file.exists()) {
//                file.delete();
//                file.createNewFile();
//            }
//
//            fos = new FileOutputStream(file);
//            bos = new BufferedOutputStream(fos);
//            int read = -1;
//            byte[] buffer = new byte[1024];
//            while ((read = bis.read(buffer, 0, 1024)) != -1) {
//                bos.write(buffer, 0, read);
//            }
//
//            bos.flush();
//
//            bos.close();
//            fos.close();
//            bis.close();
//            is.close();
//
//        } catch (IOException e) {
//            Log.e("ErrorMessage : ", e.getMessage());
//        }
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        // 데이터베이스 테이블 항목 생성
      /*  db.execSQL("create table " + TABLE_CCTV_IP + " (" + CCTV_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CCTV_COL_2 + " TEXT, "
                + CCTV_COL_3 + " TEXT, "
                + CCTV_COL_4 + " TEXT)"
        );*/
//        db.execSQL("create table " + TABLE_GRAPH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        onCreate(db);
    }

    /*
    public boolean insertData(String table, GraphDataVariable GDV) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GRAPH_TIME, GDV.getTimestamp());
        contentValues.put(GRAPH_TEMP, GDV.getTemperature());
        contentValues.put(GRAPH_CO2, GDV.getCo2());
        contentValues.put(GRAPH_EC1, GDV.getEc());
        contentValues.put(GRAPH_HUMID, GDV.getHumidity());
        contentValues.put(GRAPH_IRRIG, GDV.getIrrigation());
        contentValues.put(GRAPH_WATER, GDV.getWaterContent());
        contentValues.put(GRAPH_PH1, GDV.getPh());
        long result = db.insert(table, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }
*/
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        // res == result
        Cursor res = db.rawQuery("select * from "+ TABLE_FOOD, null);
        return res;
    }

    public Cursor getData(String table){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ table + ";", null);
        Log.d("res", "true");
        return res;
    }

    //////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////
    public boolean updateData(String id, String ip){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        // contentValues.put(COL_1, location);
        contentValues.put(COL_0_NAME, ip);

        db.update(TABLE_FOOD, contentValues, "CCTV_Number = ?", new String[] { id });
        return true;
    }

    public boolean updateData(String id, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_0_NAME, username);
        contentValues.put(COL_0_NAME, password);

        db.update(TABLE_FOOD, contentValues, "CCTV_Number = ?", new String[] { id });
        return true;
    }

/*
    // 데이터 삭제 id 를 기준으로
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }
*/


/*
    private void createTable(SQLiteDatabase db, String table) {

        if (table.equals(TABLE_GRAPH))
            db.execSQL("create table " + table + " ("
                    + GRAPH_SEQUENCE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + GRAPH_TIME + " TEXT, "
                    + GRAPH_TEMP + " TEXT, "
                    + GRAPH_HUMID + " TEXT, "
                    + GRAPH_WATER + " TEXT, "
                    + GRAPH_IRRIG + " TEXT, "
                    + GRAPH_CO2 + " TEXT, "
                    + GRAPH_EC1 + " TEXT, "
                    + GRAPH_PH1 + " TEXT) "
            );
    }

    public void dropTable(String table) throws SQLException {
        // SQLiteDatabase db = DBHelper.getWritableDatabase();
        SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL("DROP TABLE IF EXISTS " + table);
        // db.execSQL("drop table "+ TABLE_NAME);

        createTable(db, table);
    }
*/
}