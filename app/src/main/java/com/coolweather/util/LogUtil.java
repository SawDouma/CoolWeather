package com.coolweather.util;

import android.util.Log;

/**
 * Created by Saw on 2015/12/2 0002.
 */
public class LogUtil {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    public static int LEVEL = VERBOSE;

    private static String DEFAULTTAG = "SRemote";

    public static String getDefaultTag() {
        return DEFAULTTAG;
    }

    public static void setDefaultTag(String str) {
        LogUtil.DEFAULTTAG = str;
    }

    public static int getLevel() {
        return LEVEL;
    }

    /**
     * change the log level
     * @param level  the level of logs.
     */
    public static void setLevel(int level) {
        LogUtil.LEVEL = level;
    }

    public static void v(String msg) {
        v(DEFAULTTAG, msg);
    }

    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String msg) {
        d(DEFAULTTAG, msg);
    }

    public static void d(String tag, String msg) {
        if (LEVEL <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String msg) {
        i(DEFAULTTAG, msg);
    }

    public static void i(String tag, String msg) {
        if (LEVEL <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String msg) {
        w(DEFAULTTAG, msg);
    }

    public static void w(String tag, String msg) {
        if (LEVEL <= WARN) {
            Log.w(tag, msg);
        }
    }

    public static void e(String msg) {
        e(DEFAULTTAG, msg);
    }

    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR) {
            Log.e(tag, msg);
        }
    }
}
