package com.coolweather.network;

import com.coolweather.util.LogUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Saw on 2015/12/7 0007.
 */
public class HttpUtil {
    public static void sendHttpRequest(final String addr, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(addr);
                    LogUtil.d(getClass().getName(), "before open connection");
                    connection = (HttpURLConnection) url.openConnection();
                    LogUtil.d(getClass().getName(), "after open connection");
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    LogUtil.d(getClass().getName(), "before get Inputstream");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                            (connection.getInputStream()));
                    LogUtil.d(getClass().getName(), "after get Inputstream");
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    if (listener != null) {
                        listener.onFinish(stringBuilder.toString());
                        LogUtil.d(this.getClass().getName(), "response = " + stringBuilder.toString());
                    }

                } catch (Exception e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
