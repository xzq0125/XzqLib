

## RecyclerView的分割线，只支持LinearLayoutManager（注意：分割线依赖于com.android.support:recyclerview-v7:27.1.1）

## 效果图
![Screenshots](https://github.com/xzq0125/XzqLib/blob/master/divider/divider.gif)

## 引用

    dependencies {
        compile 'xzq.support:divider:27.1.1'
    }

## 使用
```
    /**
     * DividerHelper
     * Created by xzq on 2018/10/22.
     */
    public class DividerHelper {

        //垂直方向的分割线，可以根据自己的需求定制
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

    //调用
    recyclerView.addItemDecoration(DividerHelper.VERTICAL);

```





















