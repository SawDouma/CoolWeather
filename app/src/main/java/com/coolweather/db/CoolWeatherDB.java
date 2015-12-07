package com.coolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coolweather.model.City;
import com.coolweather.model.County;
import com.coolweather.model.Province;
import com.coolweather.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saw on 2015/12/1 0001.
 * <p/>
 * 单例类
 */
public class CoolWeatherDB {
    /**
     * 数据库名称
     */
    public static final String DB_NAME = "cool_weather";

    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    private static CoolWeatherDB coolWeatherDB;

    private SQLiteDatabase db;

    private CoolWeatherDB(Context context) {
        CoolWeatherOpenHelper coolWeatherOpenHelper = new CoolWeatherOpenHelper(context, DB_NAME,
                null, VERSION);
        db = coolWeatherOpenHelper.getWritableDatabase();
    }

    /**
     * 获CoolWeatherDB的实例
     *
     * @param context
     * @return
     */
    public synchronized static CoolWeatherDB getInstance(Context context) {
        if (coolWeatherDB == null) {
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }

    /**
     * 将Province实例保存到数据库
     *
     * @param province
     */
    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("province_name", province.getProvince_name());
            contentValues.put("province_code", province.getProvince_code());
            db.insert("Province", null, contentValues);
        }
    }

    /**
     * 从数据库中获取已保存的省
     *
     * @return
     */
    public List<Province> loadProvinces() {
        List<Province> list = new ArrayList<>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvince_code(cursor.getString(cursor.getColumnIndex("province_code")));
                province.setProvince_name(cursor.getString(cursor.getColumnIndex("province_name")));
                list.add(province);
            } while (cursor.moveToNext());
        }
        return list;
    }


    /**
     * 将城市实例保存到数据库。
     *
     * @param city
     */
    public void saveCity(City city) {
        if (city != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("city_name", city.getCity_name());
            contentValues.put("city_code", city.getCity_code());
            contentValues.put("province_id", city.getProvince_id());
            db.insert("City", null, contentValues);
        }
    }

    /**
     * 获取指定省里的所有城市
     *
     * @param province_id 指定的省的ID。
     */
    public  List<City> loadCities(int province_id) {
        List<City> list = new ArrayList<>();
        Cursor cursor = db.query("City", null, "province_id = ?", new String[]{String.valueOf
                (province_id)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCity_code(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setCity_name(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setProvince_id(cursor.getInt(cursor.getColumnIndex("province_id")));
                list.add(city);
            } while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * 将County实例保存到数据库
     *
     * @param county
     */
    public void saveCounty(County county) {
        if (county != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("county_name", county.getCounty_name());
            contentValues.put("county_code", county.getCounty_code());
            contentValues.put("city_id", county.getCity_id());
            db.insert("County", null, contentValues);
        }
    }

    /**
     * 根据City获取区(县)
     *
     * @param city
     */
    public List<County> loadCounties(int city_id) {
        List<County> list = new ArrayList<>();
        Cursor cursor = db.query("County", null, "city_id = ?", new String[]{String.valueOf
                (city_id)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCounty_code(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCounty_name(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCity_id(city_id);
                list.add(county);

            } while (cursor.moveToNext());
        }

        return list;
    }

}



















