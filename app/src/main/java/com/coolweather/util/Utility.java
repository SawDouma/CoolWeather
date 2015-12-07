package com.coolweather.util;

import android.text.TextUtils;

import com.coolweather.db.CoolWeatherDB;
import com.coolweather.model.City;
import com.coolweather.model.County;
import com.coolweather.model.Province;

/**
 * Created by Saw on 2015/12/7 0007.
 */
public class Utility {

    /**
     * 解析服务器返回的省级数据
     *
     * @param coolWeatherDB 数据库对象。
     * @param response      Json字符串
     * @return 是否解析成功
     */
    public synchronized static boolean handleProvinceResponse(CoolWeatherDB coolWeatherDB, String
            response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0) {
                for (String p : allProvinces) {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvince_code(array[0]);
                    province.setProvince_name(array[1]);
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 处理服务器返回的市级数据
     *
     * @param coolWeatherDB 数据库类的实例
     * @param response      Json字符串数据
     * @param provinceId    省id
     * @return 是否解析成功
     */
    public synchronized static boolean handleCityResponse(CoolWeatherDB coolWeatherDB, String
            response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0) {
                for (String p : allCities) {
                    String[] array = p.split("\\|");
                    City city = new City();
                    city.setCity_code(array[0]);
                    city.setCity_name(array[1]);
                    city.setProvince_id(provinceId);
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析服务器返回的区（县）数据
     * @param coolWeatherDB 数据库实例
     * @param response  Json字符串数据
     * @param cityId    所属城市Id
     * @return  是否解析成功。
     */
    public synchronized static boolean handleCountyResponse(CoolWeatherDB coolWeatherDB, String
            response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                for (String p : allCounties) {
                    String[] array = p.split("\\|");
                    County county = new County();
                    county.setCounty_code(array[0]);
                    county.setCounty_name(array[1]);
                    county.setCity_id(cityId);
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }


}
