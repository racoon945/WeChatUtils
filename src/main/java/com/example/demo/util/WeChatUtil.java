package com.example.demo.util;

import com.example.demo.model.query.CreateQRCodeQuery;
import com.example.demo.model.response.AccessToken;
import com.example.demo.model.response.CodeUnlimited;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import static com.alibaba.fastjson.JSON.parseObject;
import static com.alibaba.fastjson.JSON.toJSONString;
import static com.example.demo.util.WeChatConstant.*;

/**
 * @author racoon
 * @date 2019 12 04
 */
@Slf4j
public class WeChatUtil {

    public static String getAccessToken(String baseUrl) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(baseUrl).build();
        //建立一个响应对象，一开始置为null
        Response response = null;
        //开始申请，发送网络请求。
        Call call = client.newCall(request);
        try {
            response = call.execute();
            System.out.println("-*------" + response.body().toString());
            String bodyString = response.body().string();
            System.out.println(bodyString + "=========");
            if (response.isSuccessful()) {
                System.out.println("get请求成功");
                System.out.println(response.code());
                System.out.println(response.message());

                AccessToken token = parseObject(bodyString, AccessToken.class);
                return token.getAccess_token();
            } else {
                System.out.println("get请求失败");
                System.out.println(response);
                System.out.println(response.code());
                System.out.println(response.message());
                System.out.println(bodyString);
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
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("getImageBuffer请求成功");
                System.out.println(response.code());
                System.out.println(response.message());
                if (response.body().contentLength() < 1000) {
                    CodeUnlimited codeUnlimited = parseObject(response.body().string(), CodeUnlimited.class);
                    String errmsg = codeUnlimited.getErrmsg();
                    Integer errcode = codeUnlimited.getErrcode();
                    return "errcode:"+errcode+", errmsg:"+errmsg;
                } else {


                // System.out.println(response.body().string()); //此代码会导致source()中字节类型数据被清空
                InputStream inputStream = response.body().byteStream();
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                int ch;
                try {
                    while ((ch = inputStream.read()) != -1) {
                        byteStream.write(ch);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                byte[] program = byteStream.toByteArray();
                // return program;
                // BASE64Encoder encoder = new BASE64Encoder();
                // String  binary = encoder.encodeBuffer(program).trim();
                String binary = Base64.getEncoder().encodeToString(program);
                System.err.println("binary" + binary);
                return binary;
                }
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
