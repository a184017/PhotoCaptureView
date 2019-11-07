package com.android.photocaptureviewer.utils;

import java.text.SimpleDateFormat;

public class ImageTimeUtils {
    public static String timeStamp2Date(long milliseconds, String format) {
        String thisDateTime = null;
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        try {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            thisDateTime = mSimpleDateFormat.format(milliseconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thisDateTime;
    }

    public static long date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            long a = sdf.parse(date_str).getTime() / 1000;
            return a;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
