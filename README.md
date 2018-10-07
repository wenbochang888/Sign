# 贴吧一键签到脚本(Java版本)

## 本项目与2018/10/7号完成

## 项目使用开源技术

SpringBoot，MyBatis，HttpClient，Jsoup等等，非常感谢开源社区做的贡献

## 项目思路介绍

此项目需要用户提供两个参数，一个为用户名，一个为登录贴吧的cookies

### 用户名

这个用于后续查询签到情况，非贴吧用户名，可以随便设置，设置一个自己记起来比较熟悉的即可。建议英文 + 数字，不支持中文。

### cookies

这个用于登录贴吧，百度在登录方面做了js加密处理，这里我没有办法解决，所以必须要用户提供cookies，才能获取到用户的贴吧。
如何获取cookies：
- 首先登录到一个贴吧，任何一个都行，但前提必须登录。如：<a href = "http://tieba.baidu.com/f?kw=%E5%B9%BF%E4%B8%9C%E5%95%86%E5%AD%A6%E9%99%A2">广东商学院吧</a>

- 按F12，进入开发者模式，选择右上角的network，然后按F5刷新页面

- 拉上最上面，随便选择一个即可，然后里面有一个cookie的参数，将里面的内容复制出来即可。cookie：不要复制，从TIEBA开始

![Image text](https://github.com/wenbochang888/Sign/blob/master/src/img/network.jpg)

![Image text](https://github.com/wenbochang888/Sign/blob/master/src/img/cookie.jpg)

