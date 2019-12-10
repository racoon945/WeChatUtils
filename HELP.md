# Getting Started

### Reference Documentation

1、 根据app_key和secret取得access_token

2、 根据access_token取得buffer流

3、 将buffer转换成Base64字符串

"data:image/png;base64,"+base64Str 即可获得图片

//如果base64位图片没有带前面得头：'data:image/png;base64,'，需要添加，才可以展示图片