package com.prc.transfer.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.baidu.aip.speech.AipSpeech;
import com.prc.transfer.config.AuthParams;
import org.json.JSONArray;
import org.json.JSONObject;

public class VideoToWebVtt {

    // 设置APPID/AK/SK
    private static final String APP_ID = AuthParams.APP_ID;
    private static final String API_KEY = AuthParams.API_KEY;
    private static final String SECRET_KEY = AuthParams.SECRET_KEY;

    // 设置语音识别参数
    private static final String FORMAT = "pcm";
    private static final int RATE = 16000;
    private static final String LANGUAGE = "zh";

    // 设置webvtt字幕参数
    private static final String WEBVTT_HEADER = "WEBVTT\n\n";
    private static final String TIME_FORMAT = "%.3f";
    private static final String TIME_SEPARATOR = " --> ";
    private static final String LINE_SEPARATOR = "\n";

    public static void main(String[] args) throws IOException {
        // 初始化AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 设置本地视频文件路径
        String videoFilePath = "path/to/your/video/file";

        // 调用百度语音识别API，将语音转换成文本
        String speechResult = recognizeSpeech(client, videoFilePath);

        // 将文本转换成webvtt字幕并保存到本地
        String webvttResult = convertToWebVtt(speechResult);
        saveWebVttToFile(webvttResult, "path/to/save/webvtt/file");
    }

    /**
     * 调用百度语音识别API，将语音转换成文本
     */
    private static String recognizeSpeech(AipSpeech client, String filePath) throws IOException {
        // 读取本地视频文件
        byte[] data = FileUtil.readFileByBytes(filePath);

        // 调用百度语音识别API进行语音识别
        JSONObject res = client.asr(data, FORMAT, RATE, null);
        JSONArray resultArray = res.getJSONArray("result");

        // 将识别结果拼接成一段文本
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < resultArray.length(); i++) {
            stringBuilder.append(resultArray.getString(i));
        }
        return stringBuilder.toString();
    }

    /**
     * 将文本转换成webvtt字幕
     */
    private static String convertToWebVtt(String text) {
        // 将文本按行划分
        String[] lines = text.split("\n");

        // 将每一行文本转换成webvtt格式的一行字幕
        List<String> webvttLines = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            // 计算每一行字幕的开始时间和结束时间
            float startTime = i * 1.0f;
            float endTime = (i + 1) * 1.0f;

            // 格式化开始时间和结束时间
            String startTimeStr = String.format(TIME_FORMAT, startTime);
            String endTimeStr = String.format(TIME_FORMAT, endTime);

            // 将当前行文本转
            //换成webvtt格式的一行字幕
            String webvttLine = startTimeStr + TIME_SEPARATOR + endTimeStr + LINE_SEPARATOR + lines[i] + LINE_SEPARATOR;
            webvttLines.add(webvttLine);
        }

        // 将webvtt格式的字幕拼接成一段完整的字幕
        StringBuilder webvttStringBuilder = new StringBuilder();
        webvttStringBuilder.append(WEBVTT_HEADER);
        for (String line : webvttLines) {
            webvttStringBuilder.append(line);
        }
        return webvttStringBuilder.toString();
    }

    /**
     * 将webvtt字幕保存到本地文件
     */
    private static void saveWebVttToFile(String webvtt, String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(new File(filePath));
            fileWriter.write(webvtt);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
