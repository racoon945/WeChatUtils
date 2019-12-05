package com.example.demo.controller;

import com.example.demo.model.query.CreateQRCodeQuery;
import com.example.demo.util.WeChatUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author racoon
 * @date 2019 12 05
 */
@RestController
public class WeChatController {
    // @PostMapping(value = "/getCode",produces = MediaType.IMAGE_JPEG_VALUE)
    @PostMapping(value = "/getCode")
    public Object getCode(CreateQRCodeQuery query){
        return  WeChatUtil.getImageBuffer(query);
    }
}
