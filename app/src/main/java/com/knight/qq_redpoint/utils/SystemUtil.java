package com.knight.qq_redpoint.utils;

import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

public class SystemUtil {


    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpVal,context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     * @param context
     * @param spVal
     * @return
     *
     */
    public static int sp2px(Context context,float spVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,spVal,context.getResources().getDisplayMetrics());
    }


    //获取状态栏高度
    public static int getStatusBarHeight(View v){
        if(v == null){
            return 0;
        }
        Rect frame = new Rect();
        v.getWindowVisibleDisplayFrame(frame);
        return frame.top;

    }
}
