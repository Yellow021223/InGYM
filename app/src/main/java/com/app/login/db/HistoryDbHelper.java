package com.app.login.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.app.login.entity.HistoryInfo;
import com.app.login.entity.PlanInfo;

import java.util.ArrayList;
import java.util.List;

public class HistoryDbHelper extends SQLiteOpenHelper {
    private static HistoryDbHelper sHelper;
    private static final String DB_NAME = "history.db";   //数据库名
    private static final int VERSION = 1;    //版本号

    //必须实现其中一个构方法
    public HistoryDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static HistoryDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new HistoryDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建history_table表
        db.execSQL("create table history_table(history_id integer primary key autoincrement, " +
                "username text," +
                "exercise_img integer," +
                "exercise_title text," +
                "exercise_calories integer," +
                "exercise_count integer," +
                "date text," +
                "comment text" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    /**
     * 批量插入数据
     */
    public void insertByAll(List<PlanInfo> list, String date, String comment) {
        //获取数据库实例
        SQLiteDatabase db = getWritableDatabase();
        //开始事务
        db.beginTransaction();
        try {
            for (int i = 0; i < list.size(); i++) {
                ContentValues values = new ContentValues();
                values.put("username", list.get(i).getUsername());
                values.put("exercise_img", list.get(i).getExercise_img());
                values.put("exercise_title", list.get(i).getExercise_title());
                values.put("exercise_calories", list.get(i).getExercise_calories());
                values.put("exercise_count", list.get(i).getExercise_count());
                values.put("date", date);
                values.put("comment", comment);
                db.insert("history_table", null, values);

            }
            //标记事物成功
            db.setTransactionSuccessful();
        } finally {
            //结束事务
            db.endTransaction();
        }

        //关闭数据库
        db.close();

    }
    /**
     * 查询history数据
     */
    @SuppressLint("Range")
    public List<HistoryInfo> queryHistoryListData(String username) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        List<HistoryInfo> list = new ArrayList<>();
        String sql = "select history_id,username,exercise_img,exercise_title,exercise_calories,exercise_count,date,comment  from history_table where username=?";
        String[] selectionArgs = {username};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            int history_id = cursor.getInt(cursor.getColumnIndex("history_id"));
            String userName = cursor.getString(cursor.getColumnIndex("username"));
            int exercise_img = cursor.getInt(cursor.getColumnIndex("exercise_img"));
            String exercise_title = cursor.getString(cursor.getColumnIndex("exercise_title"));
            int exercise_calories = cursor.getInt(cursor.getColumnIndex("exercise_calories"));
            int exercise_count = cursor.getInt(cursor.getColumnIndex("exercise_count"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String comment = cursor.getString(cursor.getColumnIndex("comment"));
            list.add(new HistoryInfo(history_id, userName, exercise_img, exercise_title,exercise_calories,exercise_count,date,comment));
        }
        cursor.close();
        db.close();
        return list;
    }
    /**
     * 删除plan
     */
    public int delete(String history_id) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 执行SQL
        int delete = db.delete("history_table", " history_id=?", new String[]{history_id});
        // 关闭数据库连接
        db.close();
        return delete;
    }

}
