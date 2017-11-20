# BaseAndroid
android项目的基础框架，基于mvp架构，rxjava1，retrofit,okhttp,rxlifecycle,butterknife ,greenDao等,并且对gradle 配置，包括多隧道开发都定义比较完整。
都是基于github上比较流行的模块。可以用来快速开发新项目。

## 一，项目基本架构：
项目采用架构mvp，用gradle 插件 MVPHelper(https://github.com/githubwing/MVPHelper)

## 二，项目网络请求：
基于rxjava ,rxandroid ,retrofit ，okhttp,封装方便调用的网络请求。

## 三，gradle 配置：
各种编译keystore ,debug，release编译配置，隧道配置，第三方jar配置等，可以直接拿到项目中。

## 四，用到的开源框架：
logger(https://github.com/orhanobut/logger)
日志框架

butterknife(https://github.com/JakeWharton/butterknife)
ButterKnife 使用注解的方式来替代繁琐的 findViewById 和注册监听器时大量的匿名内部类写法。

rxlifecycle(https://github.com/trello/RxLifecycle)
可以通过绑定生命周期的方式，来解决内存泄漏的问题。

glide(https://github.com/bumptech/glide)
图片加载

rxBinding(https://github.com/JakeWharton/RxBinding)
RxBinding 是一组开源库，它允许你以RxJava的形式来处理UI事件

materialdialog(https://github.com/afollestad/material-dialogs)
漂亮的dialog 

BaseRecyclerViewAdapterHelper(https://github.com/CymChad/BaseRecyclerViewAdapterHelper)
简单好用的adapter 



