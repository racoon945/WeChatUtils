package com.example.demo.util;

/**
 * @author racoon
 * @date 2019 12 04
 */
public class WeChatConstant {
    public static final String APP_ID = "自己的appId";
    public static final String SECRET = "自己的secret";
    public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APP_ID + "&secret=" + SECRET;
    public static final String GET_UNLIMITED_CODE_URL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";

}