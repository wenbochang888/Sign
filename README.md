# 贴吧一键签到脚本(Java版本)

## 本项目与2018/10/7号完成

## 项目使用开源技术

SpringBoot，MyBatis，HttpClient，Jsoup等等，感谢开源社区做的贡献

## 用户名和cookies的获取

此项目需要用户提供两个参数，一个为用户名，一个为登录贴吧的cookies

### 用户名

这个用于后续查询签到情况，非贴吧用户名，可以随便设置，设置一个自己记起来比较熟悉的即可。
建议英文 + 数字，不支持中文。

### cookies

这个用于登录贴吧，百度在登录方面做了js加密处理，这里我没有办法解决，所以必须要用户提供cookies，才能获取到用户的贴吧。
如何获取cookies：
- 首先登录到一个贴吧，任何一个都行，但前提必须登录。如：<a target="_blank" href = "http://tieba.baidu.com/f?kw=%E5%B9%BF%E4%B8%9C%E5%95%86%E5%AD%A6%E9%99%A2">广东商学院吧</a>

- 按F12，进入开发者模式，选择右上角的network，然后按F5刷新页面

- 拉上最上面，随便选择一个即可，然后里面有一个cookie的参数，将里面的内容复制出来即可。cookie：不要复制，从TIEBA开始

![Image text](https://github.com/wenbochang888/Sign/blob/master/src/img/network.jpg)


![Image text](https://github.com/wenbochang888/Sign/blob/master/src/img/cookie.jpg)

ps. 本人绝不会拿cookies做任何事情，仅用于签到，如不信任，请不要暴露cookies

## 项目思路介绍

- 首先获取用户的cookies，并且存入MySQL数据库中

- 用户名和cookies在MySQL中一一对应

- 然后根据cookies获取用户的所用贴吧

- 如果已经签到则返回失败，如果没有签到则帮其自动发送post签到

- 在凌晨零点进行签到用到了Spring提供的cron注解

- 你可以用用户名来实时查看你的签到情况

## 项目展示

首先登录cookies
![Image text](https://github.com/wenbochang888/Sign/blob/master/src/img/show1.jpg)

立马自动签到全贴吧
![Image text](https://github.com/wenbochang888/Sign/blob/master/src/img/show2.jpg)

一直刷新即可
![Image text](https://github.com/wenbochang888/Sign/blob/master/src/img/show3.jpg)

零点自动签到
![Image text](https://github.com/wenbochang888/Sign/blob/master/src/img/sign.jpg)


This is other test