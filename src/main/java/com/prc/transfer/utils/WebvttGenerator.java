package com.prc.transfer.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baidu.aip.speech.AipSpeech;
import com.prc.transfer.config.AuthParams;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class WebvttGenerator {

    // 设置APPID/AK/SK
    private static final String APP_ID = AuthParams.APP_ID;
    private static final String API_KEY = AuthParams.API_KEY;
    private static final String SECRET_KEY = AuthParams.SECRET_KEY;

    public static void main(String[] args) throws IOException {
        String filePath = "D:\\project\\video2srt\\src\\main\\resources\\File\\test11.mp3";
        String webvttPath = "D:\\project\\video2srt\\src\\main\\resources\\File\\test.vtt";
        generateWebvtt(filePath, webvttPath);
    }

    public static void generateWebvtt(String filePath, String webvttPath) throws IOException {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 设置可选参数
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("dev_pid", 1537); // 普通话(支持简单的英文识别)
        options.put("cuid", "your_cuid");

        // 读取音频文件
        byte[] fileData = FileUtil.readFileByBytes(filePath);

        // 调用百度语音识别API识别音频
        JSONObject res = client.asr(fileData, "mp3", 16000, options);

        // 解析API返回的结果
        JSONArray results = res.optJSONArray("result");
        List<String> texts = new ArrayList<>();
        for (int i = 0; i < results.length(); i++) {
            String text = results.optString(i);
            texts.add(text);
        }

        // 将识别结果写入webvtt文件
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(webvttPath), StandardCharsets.UTF_8);
        writer.write("WEBVTT\n\n");
        for (int i = 0; i < texts.size(); i++) {
            String text = texts.get(i);
            String startTime = formatTime(i * 5); // 字幕每5秒切割一次
            String endTime = formatTime((i + 1) * 5);
            writer.write(startTime + " --> " + endTime + "\n");
            writer.write(text + "\n\n");
        }
        writer.close();
    }

    private static String formatTime(int seconds) {
        int h = seconds / 3600;
        int m = (seconds - h * 3600) / 60;
        int s = seconds - h * 3600 - m * 60;
        return String.format("%02d:%02d:%02d.000", h, m, s);
    }
}
