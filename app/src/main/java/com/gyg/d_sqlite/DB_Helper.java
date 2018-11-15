package com.gyg.d_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Helper extends SQLiteOpenHelper {

    //表名字，后面一定要加.db
    private static final String DB_NAME = "student.db";
    //版本至少大于一
    private static final int DB_version = 1;

    //指定数据库的版本，名称
    public DB_Helper(Context context) {
        super(context, DB_NAME,null,DB_version);
    }

    //创建数据库的表
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table student(_id integer primary key not null,name char(10) not null,nickname char(10) not null)";
        db.execSQL(sql);
    }

    //修改表的方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (newVersion > oldVersion){
            String sql = "drop table if exists student";
            db.execSQL(sql);
            onCreate(db);
        }
    }
}
