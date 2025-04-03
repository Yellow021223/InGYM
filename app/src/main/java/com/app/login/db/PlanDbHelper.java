package com.app.login.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.app.login.entity.PlanInfo;

import java.util.ArrayList;
import java.util.List;

public class PlanDbHelper extends SQLiteOpenHelper {
    private static PlanDbHelper sHelper;
    private static final String DB_NAME = "plan.db";   //数据库名
    private static final int VERSION = 1;    //版本号

    //必须实现其中一个构方法
    public PlanDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static PlanDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new PlanDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建plan_table表
        db.execSQL("create table plan_table(_id integer primary key autoincrement, " +
                "username text," +
                "exercise_id text," +
                "exercise_img integer," +
                "exercise_title text," +
                "exercise_calories integer," +
                "exercise_count integer" +
                ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * 添加运动到计划中
     */
    public int addPlan(String username,int exercise_id,int exercise_img,String exercise_title,int exercise_calories) {
        //判断是否添加过此项运动，如果添加过，只需要修改组数就行了
        PlanInfo addPlan = isAddPlan(username, exercise_id);
        if (addPlan==null){
            //获取SQLiteDatabase实例
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            //填充占位符
            values.put("username", username);
            values.put("exercise_id", exercise_id);
            values.put("exercise_img", exercise_img);
            values.put("exercise_title", exercise_title);
            values.put("exercise_calories", exercise_calories);
            String nullColumnHack = "values(null,?,?,?,?,?)";
            //执行
            int insert = (int) db.insert("plan_table", nullColumnHack, values);
            db.close();
            return insert;
        }else {
            return updateExercise(addPlan.getPlan_id(),addPlan);
        }
    }

    /**
     * 修改计划(增加组数)
     */

    public int updateExercise(int plan_id, PlanInfo planInfo) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 填充占位符
        ContentValues values = new ContentValues();
        values.put("exercise_count", planInfo.getExercise_count()+1);
        // 执行SQL
        int update = db.update("plan_table", values, " _id=?", new String[]{plan_id+""});
        // 关闭数据库连接
        db.close();
        return update;

    }

    /**
     * 减少组数
     * @return
     */

    public int subStractUpdateExercise(int plan_id, PlanInfo planInfo) {
        if (planInfo.getExercise_count()>=2){
            //获取SQLiteDatabase实例
            SQLiteDatabase db = getWritableDatabase();
            // 填充占位符
            ContentValues values = new ContentValues();
            values.put("exercise_count", planInfo.getExercise_count() - 1);
            // 执行SQL
            int update = db.update("plan_table", values, " _id=?", new String[]{plan_id+""});
            // 关闭数据库连接
            db.close();
            return update;
        }
        return 0;
    }



    /**
     * 根据用户名和商品ID判断有没有添加过这项运动到计划中
     */
    @SuppressLint("Range")
    public PlanInfo isAddPlan(String username,int exercise_id) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        PlanInfo planInfo = null;
        String sql = "select _id,username,exercise_id,exercise_img,exercise_title,exercise_calories,exercise_count  from plan_table where username=? and exercise_id=?";
        String[] selectionArgs = {username,exercise_id+""};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.moveToNext()) {
            int plan_id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("username"));
            int exerciseId = cursor.getInt(cursor.getColumnIndex("exercise_id"));
            int exercise_img = cursor.getInt(cursor.getColumnIndex("exercise_img"));
            String exercise_title = cursor.getString(cursor.getColumnIndex("exercise_title"));
            int exercise_calories = cursor.getInt(cursor.getColumnIndex("exercise_calories"));
            int exercise_count = cursor.getInt(cursor.getColumnIndex("exercise_count"));
            planInfo = new PlanInfo(plan_id, name, exerciseId, exercise_img,exercise_title,exercise_calories,exercise_count);
        }
        cursor.close();
        db.close();
        return planInfo;
    }

    /**
     * 根据用户名查询计划
     * @return
     */

    @SuppressLint("Range")
    public List<PlanInfo> queryPlanList(String username) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        List<PlanInfo> list = new ArrayList<>();
        String sql = "select _id,username,exercise_id,exercise_img,exercise_title,exercise_calories,exercise_count from plan_table where username=?";
        String[] selectionArgs = {username};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            int plan_id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("username"));
            int exerciseId = cursor.getInt(cursor.getColumnIndex("exercise_id"));
            int exercise_img = cursor.getInt(cursor.getColumnIndex("exercise_img"));
            String exercise_title = cursor.getString(cursor.getColumnIndex("exercise_title"));
            int exercise_calories = cursor.getInt(cursor.getColumnIndex("exercise_calories"));
            int exercise_count = cursor.getInt(cursor.getColumnIndex("exercise_count"));
            list.add(new PlanInfo(plan_id, name, exerciseId, exercise_img,exercise_title,exercise_calories,exercise_count));
        }
        cursor.close();
        db.close();
        return list;
    }


    /**
     * 删除plan
     */
    public int delete(String plan_id) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 执行SQL
        int delete = db.delete("plan_table", " _id=?", new String[]{plan_id});
        // 关闭数据库连接
        db.close();
        return delete;
    }


}
