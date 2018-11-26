

## 电池电量显示控件

## 效果图
![Screenshots](https://github.com/xzq0125/XzqLib/blob/master/batteryview/batteryview.gif)

## 引用（暂不支持）

    dependencies {
        compile 'xzq.widget:batteryview:1.0.0'
    }

## 使用
- xml布局
```
    <com.xzq.battery.BatteryView
        android:id="@+id/batteryView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_marginTop="200dp"
        app:bv_body_color="@color/black"
        app:bv_body_rx="1dp"
        app:bv_body_ry="1dp"
        app:bv_lid_color="@color/black"
        app:bv_lid_height="12dp"
        app:bv_lid_margin_left="3dp"
        app:bv_lid_rx="1dp"
        app:bv_lid_ry="1dp"
        app:bv_lid_width="4dp"
        app:bv_power_color="@color/red"
        app:bv_power_margin="1dp"
        app:bv_power_max="100"
        app:bv_power_percent="20"
        app:bv_power_progress="50"
        app:bv_power_rx="1dp"
        app:bv_power_ry="1dp" />
```


- Java代码
```
  BatteryView batteryView = findViewById(R.id.batteryView);
  方式1
  //设置50%电量
  batteryView.setPower(50);

  方式2
  //设置50%电量
    batteryView.setMax(100);
    batteryView.setProgress(50);
    或者
    batteryView.setMax(5000);
    batteryView.setProgress(2500);

```

## 属性及方法说明
xml布局属性|默认值|属性类型|对应方法|说明及使用
---|---|---|---|---
app:bv_body_color|#ffffff|color|void setBodyColor(@ColorInt int colorBody)|设置电池外观颜色
app:bv_lid_color|#ffffff|color|void setLidColor(@ColorInt int colorLid)|设置电池盖子颜色
app:bv_power_color|#167ff6|color|void setPowerColor(@ColorInt int colorPower)|设置电量颜色
app:bv_body_rx|0.5dp|dimen|无|设置电池外观圆角，椭圆的x半径（上下边的半径）
app:bv_body_ry|0.5dp|dimen|无|设置电池外观圆角，椭圆的y半径（左右边的半径）
app:bv_lid_rx|0.5dp|dimen|无|设置电池盖子圆角，椭圆的x半径（上下边的半径）
app:bv_lid_ry|0.5dp|dimen|无|设置电池盖子圆角，椭圆的y半径（左右边的半径）
app:bv_power_rx|0.5dp|dimen|无|设置电池电量圆角，椭圆的x半径（上下边的半径）
app:bv_power_ry|0.5dp|dimen|无|设置电池电量圆角，椭圆的y半径（左右边的半径）
app:bv_lid_width|4dp|dimen|无|设置电池盖子宽度
app:bv_lid_height|12dp|dimen|无|设置电池盖子高度
app:bv_lid_margin_left|3dp|dimen|无|设置电池盖子到电池身的边距
app:bv_power_margin|1dp|dimen|无|设置电池电量距离电池身的边距
app:bv_power_max|0|Integer|void setMax(int max)|设置电池进度最大值（方式2）
app:bv_power_progress|0|Integer|void setProgress(int progress)|设置电池当前进度值（方式2）
app:bv_power_percent|0|Integer|setPower(int powerPercent)|设置电量百分比（方式1）

