package com.derdoktor667.dev.thematrix.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {

    private static final String TAG = LogUtils.makeLogTag(Utils.class);

    private static final String USER_AGENT = "TheMatrixAndroid/0.0";

    public static HttpURLConnection openUrlConnection(String url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setUseCaches(false);
        conn.setChunkedStreamingMode(0);
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.connect();
        return conn;
    }

}
