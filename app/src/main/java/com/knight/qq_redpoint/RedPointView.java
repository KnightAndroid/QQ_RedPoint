package com.knight.qq_redpoint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * created by huangbo at 2019/6/26 15:52
 */

public class RedPointView extends View {



    //画笔
    private Paint rPaint,tPaint;
    //半径
    private int rRadius;

    //点 PointF 和 Point 主要是 传参类型不一样，Point 是整形，PointF 是float
    private PointF rDragPointF,rCenterPointF;

    private boolean rTouch;

    //固定圆的圆心
    PointF tCenterPointF = new PointF(300,400);

    //拖拽圆的圆心
    PointF tDragPointF = new PointF(200,550);

    //固定圆的半径
    private float tCenterRadius = 30;

    //拖拽圆的半径
    private float tDragRadius = 30;

    //线条
    private Path rPath;





    //一个参数的构造函数,调用两个参数的构造函数
    public RedPointView(Context context) {
        this(context,null);
    }

    //两个参数的构造函数,调用三个参数的构造函数
    public RedPointView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }


    //三个参数的构造函数
    public RedPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    //初始化小圆
    private void init(){
        //初始化Paint对象
        rPaint = new Paint();
        //设置颜色 红色
        rPaint.setColor(Color.RED);
        //设置抗锯齿
        rPaint.setAntiAlias(true);
        //设置填充
        rPaint.setStyle(Paint.Style.FILL);

        tPaint = new Paint();
        //设置颜色 红色
        tPaint.setColor(Color.RED);
        //设置抗锯齿
        tPaint.setAntiAlias(true);
        //半径 25
        rRadius = 25;
        //创建PointF对象
        rCenterPointF = new PointF();
        //拖拽圆
        rDragPointF = new PointF();



    }

    //当视图大小改变后会回调 onSizeChanged方法比onDraw方法前调用
    protected void onSizeChanged(int w,int h,int oldw,int oldh){
        super.onSizeChanged(w,h,oldw,oldh);
      //  rCenterPointF.x = w / 2;
      //  rCenterPointF.y = h / 2;

    }

    //绘制方法
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        //绘制固定圆
        canvas.drawCircle(tCenterPointF.x,tCenterPointF.y,tCenterRadius,rPaint);
        //绘制拖拽圆
        canvas.drawCircle(tDragPointF.x,tDragPointF.y,tDragRadius,rPaint);

        float x = tCenterPointF.x - tDragPointF.x;
        float y = tDragPointF.y - tCenterPointF.y;

        double a = Math.atan(y / x);

        //中心圆的p1 x坐标偏移
        float offsetX1 = (float) (tCenterRadius * Math.sin(a));
        float offsetY1= (float) (tCenterRadius * Math.cos(a));

        //拖拽圆的p2 x坐标偏移
        float offsetX2 = (float) (tDragRadius * Math.sin(a));
        float offsetY2= (float) (tDragRadius * Math.cos(a));

        //p1的坐标
        float p1_x = tCenterPointF.x - offsetX1;
        float p1_y = tCenterPointF.y - offsetY1;

        //p2的坐标
        float p2_x = tDragPointF.x - offsetX2;
        float p2_y = tDragPointF.y - offsetY2;

        //p3的坐标
        float p3_x = tDragPointF.x + offsetX2;
        float p3_y = tDragPointF.y + offsetY2;

        //p4的坐标
        float p4_x = tCenterPointF.x + offsetX1;
        float p4_y = tCenterPointF.y + offsetY1;

        //控制点的坐标
        float controll_x = (tCenterPointF.x + tDragPointF.x) / 2;
        float controll_y = (tDragPointF.y + tCenterPointF.y) / 2;

        rPath = new Path();

        rPath.reset();
        rPath.moveTo(p1_x,p1_y);
        rPath.quadTo(controll_x,controll_y,p2_x,p2_y);
        rPath.lineTo(p3_x,p3_y);
        rPath.quadTo(controll_x,controll_y,p4_x,p4_y);
        rPath.lineTo(p1_x,p1_y);
        rPath.close();
        canvas.drawPath(rPath,tPaint);








    }



//    //重写onTouchEvent方法
//    @Override
//    public boolean onTouchEvent(MotionEvent event){
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//               rTouch = true;
//
//               break;
//            case MotionEvent.ACTION_UP:
//                rTouch = false;
//                break;
//        }
//        rDragPointF.set(event.getX(),event.getY());
//        //触发dispatchDraw
//        postInvalidate();
//        return true;
//
//    }
//
//
//    //重写dispatchDraw 虽然 View 和 ViewGroup 都有 dispatchDraw() 方法，不过由于 View 是没有子 View 的，
//    //所以一般来说 dispatchDraw() 这个方法只对 ViewGroup（以及它的子类）有意义。
//    @Override
//    protected void dispatchDraw(Canvas canvas){
//        if(rTouch){
//            canvas.drawCircle(rDragPointF.x, rDragPointF.y, rRadius, rPaint);
//        }
//        super.dispatchDraw(canvas);
//
//    }














}
