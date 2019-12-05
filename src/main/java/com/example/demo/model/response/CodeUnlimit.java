package com.example.demo.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.nio.Buffer;

/**
 * @author racoon
 * @date 2019 12 04
 * {
 *  "errcode": 0,
 *  "errmsg": "ok",
 *  "contentType": "image/jpeg",
 *  "buffer": Buffer
 * }
 */
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeUnlimit extends WeChatBaseResonse {
    /**
     * default:
     * description:
     */
    private String contentType;
    /**
     * default:
     * description: 返回的图片 Buffer
     */
    private Buffer buffer;
}
