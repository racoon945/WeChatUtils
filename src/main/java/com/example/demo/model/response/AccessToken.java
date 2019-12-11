package com.example.demo.model.response;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author racoon
 * @date 2019 12 04
 *
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AccessToken extends WeChatBaseResponse {
    /**
     * default:
     * description: 获取到的凭证
     */
    private String access_token;
    /**
     * default:
     * description: 凭证有效时间，单位：秒。目前是7200秒之内的值。
     */
    private Integer expires_in;

    /**
     * default:
     * description: 错误码
     *
     * 值       说明
     *
     * -1       系统繁忙，此时请开发者稍候再试
     * 0        请求成功
     * 40001    AppSecret 错误或者 AppSecret 不属于这个小程序，请开发者确认 AppSecret 的正确性
     * 40002    请确保 grant_type 字段值为 client_credential
     * 40013    不合法的 AppID，请开发者检查 AppID 的正确性，避免异常字符，注意大小写
     */
    // private Integer errcode;
    /**
     * default:
     * description: 错误信息
     */
    // private String errmsg;
}
