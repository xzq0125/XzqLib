

## 一个可以设置上下左右drawable固定宽高的TextView

如下图，drawable的大小超过了我们预期的大小，因为TextView的drawableXXX的大小是包裹内容

DrawableTextView可以给drawable设置大小


## 效果图
![Screenshots](https://github.com/xzq0125/XzqLib/blob/master/drawabletextview/drawableTextView.jpg)


## 引用

    dependencies {
        compile 'com.xzq:drawabletextview:1.0.0'
    }


## 使用
- xml布局
```
       <com.xzq.drawabletextview.DrawableTextView
           android:id="@+id/drawableTextView"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:layout_marginTop="76dp"
           android:background="@color/colorAccent"
           android:drawablePadding="10dp"
           android:gravity="center"
           android:padding="10dp"
           android:text="DrawableTextView"
           android:textColor="@android:color/white"
           android:textSize="19sp"
           app:drawableBottom="@mipmap/ic_launcher"
           app:drawableHeight="10dp"
           app:drawableLeft="@mipmap/ic_launcher"
           app:drawableRight="@mipmap/ic_launcher"
           app:drawableTop="@mipmap/ic_launcher"
           app:drawableWidth="10dp" />
```    
    
- Java代码
```
       暂不支持
```
## 属性及方法说明
xml布局属性|默认值|属性类型|对应方法|说明及使用
---|---|---|---|---
app:drawableLeft|null|引用|null|设置图片在文字的左边
app:drawableRight|null|引用|null|设置图片在文字的右边
app:drawableTop|null|引用|null|设置图片在文字的顶部
app:drawableBottom|null|引用|null|设置图片在文字的底部
app:drawableWidth|0|dimension|null|设置图片的宽度
app:drawableHeight|0|dimension|null|设置图片的高度





















