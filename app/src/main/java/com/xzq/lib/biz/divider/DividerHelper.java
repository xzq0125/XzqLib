package com.xzq.lib.biz.divider;

import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;

import com.xzq.divider.Divider;
import com.xzq.divider.ItemDivider;
import com.xzq.lib.App;
import com.xzq.lib.R;

/**
 * DividerHelper
 * Created by xzq on 2018/10/22.
 */
public class DividerHelper {

    //垂直方向的分割线
    public static RecyclerView.ItemDecoration VERTICAL
            = Divider.create(Divider.with(App.get())
            //颜色资源id
            .colorRes(R.color.color_divider)
            //16进制颜色值
            .colorHex(0xffffff)
            //drawable资源id
            .drawableRes(R.drawable.divider_common_line)
            //drawable
            .drawable(ContextCompat.getDrawable(App.get(), R.mipmap.ic_launcher))
            //方向
            .orientation(ItemDivider.VERTICAL)
            //高度1dp
            .dpSize(1)
            //高度1px
            .pxSize(1)
            //left padding 15 dp, right padding 15dp
            .dpPadding(15)
            //left padding 10 dp, right padding 20dp
            .dpPadding(new Rect(10, 0, 20, 0))
            //left padding 15 px, right padding 15px
            .pxPadding(15)
            //left padding 10 px, right padding 20px
            .pxPadding(new Rect(10, 0, 20, 0)));

}
