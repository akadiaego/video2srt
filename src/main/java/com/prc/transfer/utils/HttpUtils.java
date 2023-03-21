package com.prc.transfer.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

public class HttpUtils {

    //设置APPID/AK/SK
    public static final String APP_ID = "31290490";
    public static final String API_KEY = "TIFryGpdgEf1GrEMbXsRVcnq";
    public static final String SECRET_KEY = "fCClCPO5RpSEZodN9mCS4k2MUL9DrBQq";

    //grant_type： 必须参数，固定为client_credentials；
    //client_id： 必须参数，应用的API Key；
    //client_secret： 必须参数，应用的Secret Key；

    /**
            * 获取语音识别内容
     * @param requestUrl
     * @param params
     * @return
             * @throws Exception
     */
    public static String postASR(String requestUrl, String params) throws Exception {
        System.out.println(params);
        String generalUrl = requestUrl;//这里不需要对接口地址拼接access_token参数 切记！！！
        System.out.println("发送的连接为:"+generalUrl);
        URL url = new URL(generalUrl);
        // 打开和URL之间的连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        System.out.println("打开链接，开始发送请求"+new Date().getTime()/1000);
        connection.setRequestMethod("POST");
        // 设置通用的请求属性
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        // 得到请求的输出流对象
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(params);
        out.flush();
        out.close();
        // 建立实际的连接
        connection.connect();
        // 获取所有响应头字段
        Map<String, List<String>> headers = connection.getHeaderFields();
        // 遍历所有的响应头字段
        for (String key : headers.keySet()) {
            System.out.println(key + "--->" + headers.get(key));
        }
        // 定义 BufferedReader输入流来读取URL的响应
        BufferedReader in = null;
        if (requestUrl.contains("nlp"))
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "GBK"));
        else
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String result = "";
        String getLine;
        while ((getLine = in.readLine()) != null) {
            result += getLine;
        }
        in.close();
        // 将字符串转换为 JSON 对象
        JSONObject jsonObj = new JSONObject(result);
        JSONArray numbersArray = jsonObj.getJSONArray("result"); // 获取键为 "numbers" 的数组
        String content = numbersArray.getString(0); // 获取数组的第一个元素
        System.out.println("请求结束"+new Date().getTime()/1000);
        System.out.println("result:" + result);
        return content;
    }
    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();

    public static void main(String []args) throws IOException{
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");

        // 拼接URL
        String host = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&";
        Request request = new Request.Builder()
//                .url("https://aip.baidubce.com/oauth/2.0/token?client_id=&client_secret=&grant_type=client_credentials")
                .url(host+"client_id="+API_KEY+"&client_secret="+SECRET_KEY)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        System.out.println(response.body().string());

    }


}
