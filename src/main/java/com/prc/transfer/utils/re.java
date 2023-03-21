//package com.prc.transfer.utils;
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.*;
//
//public class BaiduSpeechRecognition {
//    private static final String TOKEN_URL = "https://openapi.baidu.com/oauth/2.0/token";
//    private static final String ASR_URL = "https://vop.baidu.com/server_api";
//    private static final String CUID = "1234567890";
//    private static final String FORMAT = "wav";
//    private static final int RATE = 16000;
//
//    private String apiKey;
//    private String secretKey;
//    private String token;
//    private long expireTime;
//
//    public BaiduSpeechRecognition(String apiKey, String secretKey) {
//        this.apiKey = apiKey;
//        this.secretKey = secretKey;
//        this.token = "";
//        this.expireTime = 0;
//    }
//
//    public String getToken() throws IOException {
//        if (token.isEmpty() || System.currentTimeMillis() >= expireTime) {
//            String url = TOKEN_URL + "?grant_type=client_credentials" + "&client_id=" + apiKey + "&client_secret=" + secretKey;
//            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//            connection.setRequestMethod("POST");
//            connection.connect();
//            Map<String, Object> response = jsonToMap(inputStreamToString(connection.getInputStream()));
//            token = (String) response.get("access_token");
//            expireTime = System.currentTimeMillis() + ((Integer) response.get("expires_in") * 1000);
//        }
//        return token;
//    }
//
//    public String recognize(File audioFile) throws IOException {
//        String token = getToken();
//        String url = ASR_URL + "?cuid=" + CUID + "&token=" + token + "&format=" + FORMAT + "&rate=" + RATE;
//        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//        connection.setRequestMethod("POST");
//        connection.setRequestProperty("Content-Type", "audio/" + FORMAT + "; rate=" + RATE);
//        connection.setDoOutput(true);
//        OutputStream outputStream = connection.getOutputStream();
//        FileInputStream inputStream = new FileInputStream(audioFile);
//        byte[] buffer = new byte[4096];
//        int bytesRead;
//        while ((bytesRead = inputStream.read(buffer)) != -1) {
//            outputStream.write(buffer, 0, bytesRead);
//        }
//        inputStream.close();
//        outputStream.close();
//        connection.connect();
//        Map<String, Object> response = jsonToMap(inputStreamToString(connection.getInputStream()));
//        List<Map<String, Object>> result = (List<Map<String, Object>>) response.get("result");
//        StringBuilder sb = new StringBuilder();
//        for (Map<String, Object> r : result) {
//            sb.append(r.get("word")).append("\n");
//        }
//        return sb.toString();
//    }
//
//    private static String inputStreamToString(InputStream inputStream) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            sb.append(line).append("\n");
//        }
//        reader.close();
//        return sb.toString();
//    }
//
//    private static Map<String, Object> jsonToMap(String jsonString) {
//        try {
//            return new ObjectMapper().readValue(jsonString, new TypeReference<Map<String, Object>>() {
//            });
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        BaiduSpeechRecognition recognizer = new BaiduSpeechRecognition("YOUR_API_KEY", "YOUR_SECRET_KEY");
//        File audioFile = new File("PATH_TO_AUDIO_FILE");
//        String transcript = recognizer.recognize(audioFile);
//        // 将文本写入WebVTT文件
//        try (PrintWriter writer = new PrintWriter("output.vtt")) {
//            String[] lines = transcript.split("\n");
//            int index = 1;
//            for (String line : lines) {
//                double startTime = (index - 1) * 2.0; // 每句话2秒
//                double endTime = index * 2.0;
//                String start = String.format("%.3f", startTime).replace(",", ".");
//                String end = String.format("%.3f", endTime).replace(",", ".");
//                writer.println(index);
//                writer.println(start + " --> " + end);
//                writer.println(line.trim());
//                writer.println();
//                index++;
//            }
//        }
//    }
//}
//
//
