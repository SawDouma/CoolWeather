package com.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Saw on 2015/12/1 0001.
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper {

    /**
     * 创建省表语句
     */
    private static final String CREATE_PROVINCE = "create table province(" +
            "id integer primary key autoincrement, " +
            "province_name text, " +
            "province_code text)";

    /**
     * 创建市表语句
     */
    private static final String CREATE_CITY = "create table city(" +
            "id integer primary key autoincrement, " +
            "city_name text, " +
            "city_code text, " +
            "province_id integer)";

    /**
     * 创建区(县)表语句
     */
    private static final String CREATE_COUNTY = "create table county(" +
            "id integer primary key autoincrement, " +
            "county_name text, " +
            "county_code text, " +
            "city_id integer)";

    public CoolWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
