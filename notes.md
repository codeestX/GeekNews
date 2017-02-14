1、di: dependency injection, 依赖注入。

2、方法数越界问题
> [Android 使用android-support-multidex解决Dex超出方法数的限制问题,让你的应用不再爆棚](http://blog.csdn.net/t12x3456/article/details/40837287/)
> 使用MultiDex。原来一个App的所有代码都在一个Dex文件里面，应用越来越大，第三方库、包越来越多，导致方法数越界。

3、ContextWrapper类。（getCacheDir()、getFilesDir()、getExternalFilesDir()、getExternalCacheDir()的作用）
> [getCacheDir()、getFilesDir()、getExternalFilesDir()、getExternalCacheDir()的作用](http://blog.csdn.net/u011494050/article/details/39671159)
> 通过Context.getExternalFilesDir()方法可以获取到 SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
  通过Context.getExternalCacheDir()方法可以获取到 SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
  通过Context.getCacheDir()方法用于获取/data/data/你的应用的包名/cache目录
  通过Context.getFilesDir()方法用于获取/data/data/你的应用的包名/files目录
  external方法获取sdcard上的文件目录。
  如果使用上面的方法，当你的应用在被用户卸载后，SDCard/Android/data/你的应用的包名/ 这个目录下的所有文件都会被删除，不会留下垃圾信息。
  而且上面二个目录分别对应 设置->应用->应用详情里面的”清除数据“与”清除缓存“选项
  具体的详细的，参考上文