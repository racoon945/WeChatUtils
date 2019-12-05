package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.query.CreateQRCodeQuery;
import com.example.demo.model.response.AccessToken;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.SortedMap;
import java.util.TreeMap;

import static com.alibaba.fastjson.JSON.toJSONString;
import static com.example.demo.util.WeChatConstant.*;

/**
 * @author racoon
 * @date 2019 12 04
 */
@Slf4j
public class WeChatUtil {

    public static String getAccessToken(String baseUrl) {
        SortedMap<String, String> map = new TreeMap<>();
        map.put("grant_type", "client_credential");
        map.put("appid", APP_ID);
        map.put("secret", SECRET);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(baseUrl).build();
        //建立一个响应对象，一开始置为null
        Response response = null;
        //开始申请，发送网络请求。
        Call call = client.newCall(request);
        try {
            response = call.execute();
            System.out.println("-*------"+response.body().toString());
            String BodyString = response.body().string();
                System.out.println(BodyString+"=========");
            if (response.isSuccessful()) {
                System.out.println("get请求成功");
                System.out.println(response.code());
                System.out.println(response.message());

                AccessToken token = JSONObject.parseObject(BodyString, AccessToken.class);
                return token.getAccess_token();
            } else {
                System.out.println("get请求失败");
                System.out.println(response);
                System.out.println(response.code());
                System.out.println(response.message());
                System.out.println(BodyString);
                log.debug("get请求失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "query occur exception";
    }

    public static String getImageBuffer(CreateQRCodeQuery query) {
        String accessToken = getAccessToken(GET_TOKEN_URL);
        String content = toJSONString(query);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), content);
        System.out.println(requestBody.toString());
        String baseUrl = GET_UNLIMITED_CODE_URL + accessToken;
        Request request = new Request.Builder()
                .url(baseUrl)
                .post(requestBody)
                .build();
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            if (response.isSuccessful()) {
                System.out.println("get请求成功");
                System.out.println(response.code());
                System.out.println(response.message());
                // System.out.println(response.body().string()); //此代码会导致source()中字节类型数据被清空
                InputStream inputStream = response.body().byteStream();
                ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
                int ch;
                try {
                    while ((ch = inputStream.read()) != -1) {
                        bytestream.write(ch);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                byte[] program = bytestream.toByteArray();
                // return program;
                BASE64Encoder encoder = new BASE64Encoder();
                String  binary = encoder.encodeBuffer(program).trim();
                System.err.println("binary"+binary);
                return binary;
            } else {
                System.out.println("get请求失败");
                System.out.println(response.code());
                System.out.println(response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
