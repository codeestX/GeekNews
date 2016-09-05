#GeekNews

GeekNews, A pure reading App based on Material Design + MVP + RxJava + Retrofit + Dagger2 + Realm + Glide

极客日报，一款纯粹的阅读App，基于Material Design + MVP + RxJava + Retrofit + Dagger2 + Realm + Glide

出于学习的目的做了这款Material Design风格的简洁的阅读应用，基本涵盖了当前Android端开发最常用的主流框架，阅读内容目前包括知乎日报、微信精选、干货集中营，知识、新闻、福利一网打尽，后期会持续更新，坚持在这个项目上实践最新的技术，也会争取拓展更多的阅读内容

本项目还在测试阶段，发现bug或有好的建议欢迎issue、email，如果感觉对你有帮助也欢迎点个star、fork，本项目仅做学习交流使用，请勿用于其他用途

###Preview

![](https://github.com/codeestX/GeekNews/raw/master/screenshots/part1.gif) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
![](https://github.com/codeestX/GeekNews/raw/master/screenshots/part2.gif) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
![](https://github.com/codeestX/GeekNews/raw/master/screenshots/part3.gif)

###Download APK


##Points

* 使用RxJava配合Retrofit2做网络请求
* 使用RxUtil对线程操作和网络请求结果处理做了封装
* 使用RxPresenter对订阅的生命周期做管理
* 使用RxBus来方便组件间的通信
* 使用RxJava其他操作符来做延时、轮询、转化等操作
* 使用okhttp3对网络返回内容做缓存，还有日志、超时重连、头部消息的配置
* 使用Material Design控件和动画
* 使用MVP架构整个项目，对应于model、ui、presenter三个包
* 使用Dagger2将M层注入P层，将P层注入V层，无需new，直接调用对象
* 使用Realm做阅读记录、收藏记录的存储和查询
* 使用Glide做图片的处理和加载
* 使用Fragmentation简化Fragment的操作
* 使用RecyclerView实现下拉刷新、上拉加载、侧滑删除、长按拖曳
* 使用WebView做阅览页，通过修改其配置达成各种需要的功能
* 使用原生的夜间模式、分享、反馈
* 包含搜索、收藏、检测更新（尚未实现）等功能

##Version

###V1.0.0
`1.提交第一版`

##Thanks

###API:
[知乎日报API](https://github.com/izzyleung/ZhihuDailyPurify/wiki/%E7%9F%A5%E4%B9%8E%E6%97%A5%E6%8A%A5-API-%E5%88%86%E6%9E%90)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
[微信精选API](http://apistore.baidu.com/apiworks/servicedetail/632.html)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
[干货集中营API](http://gank.io/api)

###APP:
[Hot](https://github.com/zj-wukewei/Hot) 提供了Dagger2配合MVP的架构思路

[知了日报](https://github.com/HotBitmapGG/RxZhiHu) 提供了部分设计思路

还参考了很多大神的类似作品，感谢大家的开源精神

###RES:
[iconfont](http://www.iconfont.cn/) 提供了icon素材

[material UP](http://www.material.uplabs.com/) 提供了Material Design风格的素材

###LIB:
	//ui
    compile 'com.android.support:appcompat-v7:24.1.1'
    compile 'com.android.support:design:24.1.1'
    compile 'com.android.support:recyclerview-v7:24.1.1'
    compile 'com.android.support:cardview-v7:24.1.1'

    //rx
    compile 'io.reactivex:rxjava:1.1.0'
    compile 'io.reactivex:rxandroid:1.1.0'
    compile 'com.jakewharton.rxbinding:rxbinding:0.4.0'

    //network
    compile 'com.google.code.gson:gson:2.4'
    compile 'com.squareup.retrofit2:retrofit:2.0.2'
    compile 'com.squareup.retrofit2:converter-gson:2.0.2'
    compile 'com.squareup.retrofit2:adapter-rxjava:2.0.2'
    compile 'com.squareup.okhttp3:okhttp:3.2.0'
    compile 'com.squareup.okhttp3:logging-interceptor:3.0.1'
    compile 'com.github.bumptech.glide:glide:3.7.0'
    compile 'com.github.bumptech.glide:okhttp3-integration:1.4.0@aar'

    //di
    compile 'com.google.dagger:dagger:2.0.2'
    compile 'com.google.dagger:dagger-compiler:2.0.2'
    compile 'com.jakewharton:butterknife:8.2.1'
    apt 'com.jakewharton:butterknife-compiler:8.2.1'
    provided 'org.glassfish:javax.annotation:10.0-b28'

    //other
    compile 'com.umeng.analytics:analytics:latest.integration'
    compile 'com.orhanobut:logger:1.15'
    compile 'me.yokeyword:fragmentation:0.7.9'
    compile 'com.victor:lib:1.0.4'
    compile 'com.prolificinteractive:material-calendarview:1.4.0'
    compile 'net.opacapp:multiline-collapsingtoolbar:1.2.2'
    compile 'com.github.chrisbanes:PhotoView:1.3.0'
    compile 'com.miguelcatalan:materialsearchview:1.4.0'

    //canary
    debugCompile 'com.github.moduth:blockcanary-android:1.2.1'
    releaseCompile 'com.github.moduth:blockcanary-no-op:1.2.1'
    debugCompile 'com.squareup.leakcanary:leakcanary-android:1.4-beta2'
    releaseCompile 'com.squareup.leakcanary:leakcanary-android-no-op:1.4-beta2'`


##License

Copyright (c) 2016 codeestX

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.