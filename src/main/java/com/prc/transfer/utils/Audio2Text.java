package com.prc.transfer.utils;

import java.io.File;
import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;

/**
 * 语音识别Java-API JSON上传方式示例代码
 * @author
 *
 */
public class Audio2Text {
    //接口地址
    private static final String AUDIO2TEXT_URL = "http://vop.baidu.com/server_api";

    public static String AudioText(String mp3Path, String pcmPath){
        return null;
    }
    public static void main(String[] args) throws Exception {
        //合成的MP3语音文件
        String path = "D:\\project\\video2srt\\src\\main\\resources\\File\\test11.mp3";
        //MP3转pcm要保存的路径和文件名
        String path2 = "D:\\project\\video2srt\\src\\main\\resources\\File\\test13.pcm";
        MP3ConvertPCM.mp3Convertpcm(path, path2);
        // 对语音二进制数据进行识别
        byte[] data = FileUtil.readFileByBytes(path2);    //readFileByBytes仅为获取二进制数据示例
        String speech = Base64Util.encode(data);
        File file = new File(path2);
        long len = file.length();
        //"refresh_token":"25.8f006f681f09d79c91444e8d8007aa6a.315360000.1994206075.282335-31290490","expires_in":2592000,
        String token = "24.fb4f553f5e54939d4f679aa1f40381cf.2592000.1681891093.282335-31290490";
        String result = Audio2text("pcm", 16000,RandomStringGenerator.getRandomStringByLength(60),token, speech, len);
        System.out.println(result);
    }
    /**
     * JSON方式上传
     * @param format	必填	语音文件的格式，pcm 或者 wav 或者 amr。不区分大小写。推荐pcm文件
     * @param rate	必填	采样率， 8000 或者 16000， 推荐 16000 采用率
     * @param channel	必填	声道数，仅支持单声道，请填写固定值 1
     * @param cuid	必填	用户唯一标识，用来区分用户，计算UV值。建议填写能区分用户的机器 MAC 地址或 IMEI 码，长度为60字符以内。
     * @param token	必填	开放平台获取到的access_token, 见上面的“鉴权认证机制”段落
     * @param lan	选填	语种选择，默认中文（zh）。 中文=zh、粤语=ct、英文=en，不区分大小写
     * @param url	选填	可下载的语音下载地址，与callback连一起使用，确保百度服务器可以访问。
     * @param callback	选填	用户服务器的识别结果回调地址，确保百度服务器可以访问
     * @param speech	选填	本地语音文件的的二进制语音数据 ，需要进行base64 编码。与len参数连一起使用。
     * @param len	选填	本地语音文件的的字节数，单位字节
     */
    public static String Audio2text(String format,Integer rate,String cuid,String token,String speech,long len) throws Exception{
        HashMap paramMap = new HashMap();
        paramMap.put("speech",speech);
        paramMap.put("format", format);
        paramMap.put("rate", rate);
        paramMap.put("channel",1);
        paramMap.put("cuid",cuid);
        paramMap.put("token", token);
        paramMap.put("len", len);
        //net.sf.json.JSONObject params = net.sf.json.JSONObject.fromObject(paramMap);
        JSONObject params = new JSONObject(paramMap);

        String data = HttpUtils.postASR(AUDIO2TEXT_URL,params.toString());
        System.out.println("语音文件识别的内容:"+data);
        return data;
    }

}