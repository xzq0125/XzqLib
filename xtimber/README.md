

## 本库是通过扩展JakeWharton（J神）的Timber库，Timber的[github](https://github.com/JakeWharton/timber)
    J神是这样介绍Timber的，Logging for lazy people. 翻译过来就是懒人日志，这个懒主要体现在，相比官方的Log不用再设置TAG，
    Timber会自动的把当前调用类作为默认的TAG，当然了，也可以手动设置TAG；XTimber在此基础上仅仅增加了一个定位到调用类的功能。
## XTimber的特点
- 自动设置TAG
- 接收Throwable异常信息
- 方便查看并定位到日志输出位置


## 效果图
在BaseActivity里面调用以下方法

![Screenshots](https://github.com/xzq0125/XzqLib/blob/master/xtimber/xtimber1.jpg)

下面是输出的日志,下面的截图是配置了MethodLocation模式的效果图

![Screenshots](https://github.com/xzq0125/XzqLib/blob/master/xtimber/xtimber2.jpg)

   - 第一行是官方`Log.d(tag,msg)`输出的日志
   - 第二行是官方`XTimber.d(msg)`输出的日志
   - 第三行是官方`XTimber.e(throwable)`输出的日志

   点击第二行`(BaseActivity.java:32)`可以将光标定位到BaseActivity类的32行处

## 引用

    dependencies {
        implementation 'xzq.util:xtimber:1.0.0'
    }

##  使用
Application里初始化

方式一、
```
     XTimber.plant(new XTimber.DebugTree() {
                /**
                 * 是否可以输出日志
                 * @param tag TAG
                 * @param priority 日志等级
                 * @return 是否可以输出日志，利用BuildConfig.DEBUG来控制日志开关
                 *         （release版本关闭日志，debug版本输出日志）
                 */
                @Override
                protected boolean isLoggable(@Nullable String tag, int priority) {
                    return BuildConfig.DEBUG;
                }

                /**
                 * 获取Log输出方法路径的模式，默认值是{@code MethodLocation.FULL}
                 *
                 * @return Log输出方法路径的模式
                 */
                @Override
                protected MethodLocation methodLocation() {
                    //不输出方法位置，此模式输出的日志跟官方Log一样
                    //return MethodLocation.NONE;
                    //只输出方法名称
                    //return MethodLocation.METHOD;
                    //显示方法的全路径(默认模式)
                    return MethodLocation.FULL;
                }
            });

```
 方式二、
```
  if (BuildConfig.DEBUG){
      XTimber.plant(new XTimber.DebugTree());
  }
 ```
   手动设置TAG
```
 XTimber.tag("TAG");
 XTimber.d("msg");

 输出：11-14 18:11:19.584 15439-15439/com.xzq.lib D/TAG: at	com.xzq.lib.base.BaseActivity.onCreate(BaseActivity.java:33)	msg
```

## 特别说明
MethodLocation的功能是笔者在Timber的基础上增加的功能，可以在日志中输出方法位置并定位

MethodLocation的三种模式

```
        /**
         * Log输出方法路径的模式
         */
        public enum MethodLocation {
            /**
             * 显示方法的全路径
             */
            FULL,
            /**
             * 只输出方法名称
             */
            METHOD,
            /**
             * 关闭路径输出
             */
            NONE
        }
```





















