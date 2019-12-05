package com.example.demo.util;

import com.example.demo.model.query.CreateQRCodeQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.demo.util.WeChatConstant.APP_ID;
import static com.example.demo.util.WeChatConstant.SECRET;

/**
 * @author racoon
 * @date 2019 12 04
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class WeChatUtilTest {

    @Test
    void getAccessToken() {
        String baseUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APP_ID + "&secret=" + SECRET;
        WeChatUtil.getAccessToken(baseUrl);
    }
    @Test
    void getImageBuffer(){
        CreateQRCodeQuery query = new CreateQRCodeQuery("123",null,null,null,null,null);
        WeChatUtil.getImageBuffer(query);

    }
}