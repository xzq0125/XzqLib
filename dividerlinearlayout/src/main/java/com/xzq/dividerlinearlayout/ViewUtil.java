package com.xzq.dividerlinearlayout;

import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * ViewUtil
 *
 * @author xzq
 */

public class ViewUtil {
    public static boolean isLayoutRtl(View view) {
        return ViewCompat.getLayoutDirection(view) == ViewCompat.LAYOUT_DIRECTION_RTL;
    }
}
