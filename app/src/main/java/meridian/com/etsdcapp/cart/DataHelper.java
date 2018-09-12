package meridian.com.etsdcapp.cart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import meridian.com.etsdcapp.model.CartModel;

/**
 * Created by user 1 on 15-12-2015.
 */
public class DataHelper extends SQLiteOpenHelper {
    // TABLE INFORMATTION

//    public static final String MEMBER_ITEM = "item";
//    public static final String MEMBER_QUANTITY = "quantity";
//    public static final String MEMBER_PRICE = "price";
//    public static final String MEMBER_TOTAL_PRICE = "total";

    // DATABASE INFORMATION
    static final String DB_NAME = "MEMBERS7";
    static final int DB_VERSION = 1;
    // public static final String TABLE_COURSE = "tbl_course";
    public static final String TABLE_COURSE = "Course_table6";
    public static final String TABLE_CRC = "Crc_table";
    public static final String TABLE_SUBCRC = "Subcrc_table";
    public static final String KEY_COURSE_ID = "_course_ID";
    public static final String KEY_COURSE = "_course";
    public static final String KEY_SUB_COURSE = "_subcourse";
    public static final String KEY_SUB_COURSE_ID="_subcourseid";
    public static final String KEY_MAINCOURSE_ID="maincourseid";
    // TABLE CREATION STATEMENT

    // private static final String CREATE_TABLE = "CREATE TABLE  "+ TABLE_COURSE + "( outlet_id INTEGER PRIMARY KEY AUTOINCREMENT,outlet_name TEXT  , outlet_qty INTEGER ,outlet_price INTEGER,outlet_tot INTEGER)";


    private static final String CREATE_TABLE_NW = "CREATE TABLE " + TABLE_COURSE + " (" + KEY_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ KEY_MAINCOURSE_ID + " TEXT,"  + KEY_COURSE + " TEXT," + KEY_SUB_COURSE + " TEXT "  + "," + KEY_SUB_COURSE_ID + " TEXT )";

    private static final String CREATE_TABLE_CRC = "CREATE TABLE " + TABLE_COURSE + " (" + KEY_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_COURSE + " TEXT," + KEY_SUB_COURSE + " TEXT" + ")";
    private static final String CREATE_TABLE_SUBCRC = "CREATE TABLE " + TABLE_SUBCRC + " (" + KEY_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_COURSE + " TEXT," + KEY_SUB_COURSE + " TEXT" + ")";
    public static final String DELETE_TABLE_COURSE = "DROP TABLE IF EXISTS " + TABLE_COURSE;
    //  public static final String DROP_TABLE_REG = "DROP TABLE IF EXISTS "+TABLE_NAME;


    public DataHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_NW);
        System.out.println("table created");

    }

    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DELETE_TABLE_COURSE);
        // db.execSQL(DROP_TABL);
        //Create tables again
        onCreate(db);
    }

    public boolean verification(String _username) throws SQLException {
        int count = -1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        try {
            String query = "SELECT COUNT(*) FROM "
                    + TABLE_COURSE + " WHERE " + KEY_COURSE + " = ?";
            c = db.rawQuery(query, new String[]{_username});
            if (c.moveToFirst()) {
                count = c.getInt(0);
            }
            return count > 0;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }
//    public  void select() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String selectQuery = "SELECT  * FROM " + DataHelper.TABLE_COURSE;
//
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        if (cursor.getCount() > 0) {
//            while (cursor.moveToNext()) {
//
//                _course_ID = cursor.getInt(cursor.getColumnIndex("_course_ID"));
//                final String _course = cursor.getString(cursor.getColumnIndex("_course"));
//
//                _subcourse = cursor.getString(cursor.getColumnIndex("_subcourse"));
//                _subcourseid = cursor.getString(cursor.getColumnIndex("_subcourseid"));
//            }
//        }
//    }

    public boolean verification_id(int user_id) throws SQLException {
        int count = -1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        try {
            String query = "SELECT COUNT(*) FROM "
                    + TABLE_COURSE + " WHERE " + KEY_COURSE_ID + " = " + user_id;
            c = db.rawQuery(query, null);
            if (c.moveToFirst()) {
                count = c.getInt(0);
            }
            return count > 0;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public int getProfilesCount()
    {
        SQLiteDatabase db = this.getReadableDatabase();
       int cnt = (int) DatabaseUtils.queryNumEntries(db, TABLE_COURSE);
        db.close();
        return cnt;
    }




    public int updateCourse(String maincrc_id,String course,String subcourse,String subcourse_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COURSE, course);
        values.put(KEY_SUB_COURSE, subcourse);
        values.put(KEY_SUB_COURSE_ID, subcourse_id);
        values.put(KEY_MAINCOURSE_ID, maincrc_id);
        // updating row
        return db.update(TABLE_COURSE, values, KEY_COURSE + " = ?",
                new String[] { String.valueOf(course) });

    }




    public void updateData(String course,String subcourse)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_SUB_COURSE, subcourse);
        db.update(TABLE_COURSE, cv, "_course= " +course, null);
        //These Fields should be your String values of actual column names

    }


    public void insertData(String maincrc_id,String course, String subcourse,String subcourse_id) {


        // Open the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Start the transaction.
        db.beginTransaction();
        ContentValues cv;
        try {
            cv = new ContentValues();

            cv.put(KEY_COURSE, course);

//            for (int i = 0; i < subcourse.size(); i++) {
                cv.put(KEY_SUB_COURSE, subcourse);
               cv.put(KEY_SUB_COURSE_ID, subcourse_id);
            cv.put(KEY_MAINCOURSE_ID, maincrc_id);

         //   }
          db.insert(TABLE_COURSE, null, cv);
         //   Log.i("Insert", i + "");

            db.setTransactionSuccessful();

        } catch (SQLiteException e) {
            e.printStackTrace();

        } finally {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }
    }

    public void delete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COURSE, KEY_COURSE + " = ?",
                new String[]{String.valueOf(id)});
    }
    public void deletesub(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COURSE, KEY_SUB_COURSE+ " = ?",
                new String[]{String.valueOf(id)});
    }
    public void removeAll()
    {
        // db.delete(String tableName, String whereClause, String[] whereArgs);
        // If whereClause is null, it will delete all rows.
        SQLiteDatabase db = this.getWritableDatabase(); // helper is object extends SQLiteOpenHelper
        db.delete(TABLE_COURSE, null, null);

    }


}
