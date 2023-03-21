package com.prc.transfer.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class SrtHandle {
    public static final String date  = "HH:mm:ss,SSS";
    public static final SimpleDateFormat sdf = new SimpleDateFormat(date, Locale.ROOT);

    public static void main(String[] args) throws Throwable{
        xunFeiYuYin2srt("xunfei.txt", "字幕.srt");
    }

    private static void xunFeiYuYin2srt(String xunfeiFilePath, String srtFilePath) throws Throwable{
        StringBuilder sb = new StringBuilder();
        FileInputStream fis = new FileInputStream(xunfeiFilePath);

        byte[] bytes = new byte[9999999];
        fis.read(bytes);

        String data = new String(bytes);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));

        JSONArray array = JSONArray.parseArray(data.toString());
        for (int i = 0; i < array.size(); i++) {
            JSONObject object = array.getJSONObject(i);
            String bg = object.getString("bg");
            String ed = object.getString("ed");

            long bgg = Long.valueOf(bg);
            long edd = Long.valueOf(ed);
            String startTime = sdf.format(bgg);
            String endTime = sdf.format(edd);

            String msg = object.getString("onebest");

            sb.append(i + 1).append("\n")
                    .append(startTime)
                    .append(" --> ")
                    .append(endTime).append("\n")
                    .append(msg)
                    .append("\n").append("\n");
        }

        BufferedWriter out = new BufferedWriter(new FileWriter(srtFilePath));
        out.write(sb.toString());
        out.close();
        fis.close();
    }
}

