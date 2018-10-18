

## 分割线可去除的LinearLayout
item之间的分割线，常见于APP的"用户"、"设置"等页面，常见布局是一个LinearLayout包含多个View，View与View之间有的有分割线，有的没有分割线

假设分割线高度为1dp，下面列举几种常见的实现方式

- 可以在View之间插入一个高度为1dp的View来达到分割线的效果，但是此方式增加了子View的数目，也不雅观，不推荐

- 可以给View设置一个用xml创建的layer_list背景（layer_list利用不用高度的背景层叠也可以达到分割线的效果，此方式用法还算方便，直接给View设置一个背景就行,不过多层背景会造成GPU过度绘制，且行且用之）

- 父布局设置分割线颜色的背景，然后TextView之间设置marginTop = "1dp"，不雅观

DividerLinearLayout继承于LinearLayout，拥有原有的分割线的功能，通过重写LinearLayout的onDraw方法，拓展了通过Index来控制分割线的显示，或者也可以在子View里面配置属性来控制分割线显示

DividerLinearLayout可以减少布局层次结构，可以减少子View数目，可以动态控制分割线的显示

如果你使用的是LinearLayoutCompat，则本库同时也提供了DividerLinearLayoutCompat


## 效果图
![Screenshots](https://github.com/xzq0125/XzqLib/blob/master/dividerlinearlayout/dividerlinearlayout.gif)


## 引用

    dependencies {
        compile 'xzq.widget:dividerlinearlayout:1.0.0'
    }


## 使用
- xml布局
```
    <com.xzq.dividerlinearlayout.DividerLinearLayout
        android:id="@+id/dividerLinearLayout"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:layout_marginTop="100dp"
        android:divider="@drawable/divider_common_line"
        android:orientation="vertical"
        android:showDividers="middle|beginning|end"
        android:visibility="visible"
        app:non_divider_indexes="1">

        <TextView
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:gravity="center"
            android:text="Item 0"
            android:textColor="@color/colorPrimary"
            android:textSize="19sp" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:gravity="center"
            android:text="Item 1"
            android:textColor="@color/colorPrimary"
            android:textSize="19sp" />

        <TextView
            android:layout_width="200dp"
            android:layout_height="50dp"
            android:gravity="center"
            android:text="Item 2"
            android:textColor="@color/colorPrimary"
            android:textSize="19sp" />

    </com.xzq.dividerlinearlayout.DividerLinearLayout>
```    
    
- Java代码
```
        DividerLinearLayout dividerLinearLayout = findViewById(R.id.dividerLinearLayout);
        //1、
        List<String> list = new ArrayList<>();
        list.add("0");
        list.add("1");
        dividerLinearLayout.setNonDividerIndexes(list);
        
        //2、
        dividerLinearLayout.setNonDividerIndexes("0", "1");
        dividerLinearLayout.setNonDividerIndexes(0, 1);
```
## 属性及方法说明
xml布局属性|默认值|属性类型|对应方法|说明及使用
---|---|---|---|---
app:dll_non_divider_indexes|null|String|void setNonDividerIndexes(List<String> list)|设置不需要分割线的索引列表，使用，app:non_divider_indexes = "0,1",自动忽略越界的index
app:dll_layout_divider_previous_hide|false|Boolean|null|在子项也可以配置与前一个子项之间的分割线的显示性，默认显示分割线





















