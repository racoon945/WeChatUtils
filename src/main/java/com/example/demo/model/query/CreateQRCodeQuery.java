package com.example.demo.model.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

/**
 * @author racoon
 * @date 2019 12 04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateQRCodeQuery {
    /**
     * default:
     * description: 接口调用凭证
     */
    @NotBlank
    // private String access_token;
    /**
     * default:
     * description: 最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~，其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）
     */
    @NotBlank
    @Length(max=32,message = "最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~")
    private String scene;
    /**
     * default: 主页
     * description: 必须是已经发布的小程序存在的页面（否则报错），例如 pages/index/index, 根路径前不要填加 /,不能携带参数（参数请放在scene字段里），如果不填写这个字段，默认跳主页面
     */
    private String page;
    /**
     * default: 430
     * description: 二维码的宽度，单位 px，最小 280px，最大 1280px
     */
    private Integer width;
    /**
     * default: false
     * description: 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调，默认 false
     */
    private Boolean auto_color;
    /**
     * default: {"r":0,"g":0,"b":0}
     * description: auto_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"} 十进制表示
     */
    private String line_color;
    /**
     * default: false
     * description: 是否需要透明底色，为 true 时，生成透明底色的小程序
     */
    private Boolean is_hyaline;
    // /**
    //  * default:
    //  * description:
    //  */
    // private String ;

}
