package com.coolweather.network;

/**
 * Created by Saw on 2015/12/7 0007.
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
