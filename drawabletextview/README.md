

## 一个可以设置上下左右drawable指定宽高的TextView

如下图，图片的大小超过了我们预期的大小，因为TextView的drawableXXX的大小是包裹内容

DrawableTextView可以给drawable设置指定的宽高


## 效果图
![Screenshots](https://github.com/xzq0125/XzqLib/blob/master/drawabletextview/drawableTextView.jpg)


## 引用

    dependencies {
        compile 'xzq.widget:drawabletextview:1.0.0'
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
           app:dtv_drawableBottom="@mipmap/ic_launcher"
           app:dtv_drawableHeight="10dp"
           app:dtv_drawableLeft="@mipmap/ic_launcher"
           app:dtv_drawableRight="@mipmap/ic_launcher"
           app:dtv_drawableTop="@mipmap/ic_launcher"
           app:dtv_drawableWidth="10dp" />
```    
    
- Java代码
```
       public void setDrawableLeft(Drawable drawableLeft, int drawableWidth, int drawableHeight)
       public void setDrawableTop(Drawable drawableLeft, int drawableWidth, int drawableHeight)
       public void setDrawableRight(Drawable drawableLeft, int drawableWidth, int drawableHeight)
       public void setDrawableBottom(Drawable drawableLeft, int drawableWidth, int drawableHeight)
       public void setCompoundDrawables(@Nullable Drawable left, @Nullable Drawable top,
                                            @Nullable Drawable right, @Nullable Drawable bottom,
                                            int drawableWidth, int drawableHeight)
```
## 属性及方法说明
xml布局属性|默认值|属性类型|对应方法|说明及使用
---|---|---|---|---
app:dtv_drawableLeft|null|引用|null|设置图片在文字的左边
app:dtv_drawableRight|null|引用|null|设置图片在文字的右边
app:dtv_drawableTop|null|引用|null|设置图片在文字的顶部
app:dtv_drawableBottom|null|引用|null|设置图片在文字的底部
app:dtv_drawableWidth|0|dimension|null|设置图片的宽度
app:dtv_drawableHeight|0|dimension|null|设置图片的高度





















