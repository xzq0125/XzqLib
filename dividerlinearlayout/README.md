

#DividerLinearLayout
分割线可去除的LinearLayout


## 效果图
![Screenshots](https://github.com/xzq0125/XzqLib/blob/master/dividerlinearlayout/dividerlinearlayout.gif)


## 引用

    dependencies {
        compile 'com.xzq:dividerLinearLayout:1.0.1'
    }


## 使用
xml布局

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
    
    
Java代码

        DividerLinearLayout dividerLinearLayout = findViewById(R.id.dividerLinearLayout);
        //1、
        List<String> list = new ArrayList<>();
        list.add("0");
        list.add("1");
        dividerLinearLayout.setNonDividerIndexes(list);
        
        //2、
        dividerLinearLayout.setNonDividerIndexes("0", "1");
        dividerLinearLayout.setNonDividerIndexes(0, 1);


