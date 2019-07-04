package com.knight.qq_redpoint;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.view.MotionEventCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.knight.qq_redpoint.utils.SystemUtil;

public class BetterRedPointViewControl implements View.OnTouchListener,DragViewStatusListener{


    //上下文
    private Context context;
    //拖拽布局的id
    private int mDragViewId;
    //WindowManager 对象
    private WindowManager windowManager;
    //布局参数
    private WindowManager.LayoutParams params;

    private BetterRedPointView betterRedPointView;
    //被拖拽的View
    private View dragView;
    //要显示的View
    private View showView;

    //状态栏高度
    private int statusHeight;
    //最大拖拽距离
    private float maxDistance = 560;
    //中心圆的半径
    private float tCenterRadius = 30;
    //小圆最小半径
    private float minRadius = 8;

    
    //构造函数
    public BetterRedPointViewControl(Context context,View showView,int mDragViewId,DragStatusListener dragStatusListener){
        this.context = context;
        this.showView = showView;
        this.mDragViewId = mDragViewId;
        this.dragStatusListener = dragStatusListener;
        //设置监听 执行自己的出名事件
        showView.setOnTouchListener(this);
        params = new WindowManager.LayoutParams();
        params.format = PixelFormat.TRANSLUCENT;

    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        if(action == MotionEvent.ACTION_DOWN){

            ViewParent parent = v.getParent();
            if(parent == null){
                return false;
            }

            parent.requestDisallowInterceptTouchEvent(true);
            statusHeight = SystemUtil.getStatusBarHeight(showView);
            showView.setVisibility(View.INVISIBLE);
            dragView = LayoutInflater.from(context).inflate(mDragViewId,null,false);
            getText();
            windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
            //每当触摸的时候就创建拖拽的小圆
            betterRedPointView = new BetterRedPointView(context,dragView,windowManager);
            //初始化数据
            init();
            //设置监听回调
            betterRedPointView.setDragViewStatusListener(this);
            //添加到窗体进行显示
            windowManager.addView(betterRedPointView,params);
            windowManager.addView(dragView,params);

        }
        betterRedPointView.onTouchEvent(event);
        return true;
    }

    @Override
    public void outDragMove(PointF dragPoint) {

    }

    @Override
    public void outDragMoveUp(PointF dragPoint) {
      removeView();
      showExplodeImage(dragPoint);
      dragStatusListener.outScope();
    }

    @Override
    public void inDragUp(PointF dragPoint) {
      removeView();
      dragStatusListener.inScope();
    }

    @Override
    public void recoverCenterPoint(PointF centerPoint) {
      removeView();
      dragStatusListener.inScope();
    }


    /**
     * 初始化数据
     *
     */
    private void init(){
        //计算小圆在屏幕中的坐标
        int[] points = new int[2];
        showView.getLocationInWindow(points);
        int x = points[0] + showView.getWidth() / 2;
        int y = points[1] + showView.getHeight() / 2;
        betterRedPointView.setStatusBarHeight(statusHeight);
//        betterRedPointView.setMaxDistance(maxDistance);
//        betterRedPointView.setCenterRadius(tCenterRadius);
//        betterRedPointView.setMinRadius(minRadius);
        betterRedPointView.setCenterDragPoint(x,y);
    }
    /**
     * 获取文本内容
     *
     */
    private void getText(){
        if(showView instanceof TextView && dragView instanceof TextView){
            ((TextView)dragView).setText((((TextView) showView).getText().toString()));

        }
    }


    /**
     *  移出对象
     *
     */
    private void removeView(){
        if (windowManager != null && betterRedPointView.getParent() != null && dragView.getParent() != null) {
            windowManager.removeView(betterRedPointView);
            windowManager.removeView(dragView);
        }
    }



    /**
     *
     * 超过拖拽范围外显示爆炸效果
     *
     */
    private void showExplodeImage(PointF dragPointF){

//        //添加爆炸图像
//        explodeImage = new ImageView(context);
//        explodeImage.setImageResource(R.mipmap.explode_1);
//        //一开始不显示
//        explodeImage.setVisibility(View.INVISIBLE);
//        params.gravity= Gravity.TOP|Gravity.LEFT;
////        这里得到的是其真实的大小，因为此时还得不到其测量值
//        int intrinsicWidth = explodeImage.getDrawable().getIntrinsicWidth();
//        int intrinsicHeight = explodeImage.getDrawable().getIntrinsicHeight();
//
//        params.x= (int) dragPointF.x-intrinsicWidth/2;
//        params.y= (int) dragPointF.y-intrinsicHeight/2-statusHeight;
//        params.width=WindowManager.LayoutParams.WRAP_CONTENT;
//        params.height=WindowManager.LayoutParams.WRAP_CONTENT;
//        //增加到viewGroup中
//        windowManager.addView(explodeImage,params);
//        //属性动画
//        ValueAnimator animator = ValueAnimator.ofInt(0,explodeImgaes.length - 1);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                //不断更新图像变化
//                explodeImage.setBackgroundResource(explodeImgaes[(int) animation.getAnimatedValue()]);
//            }
//        });
//        //为动画添加监听
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                super.onAnimationCancel(animation);
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                //结束了 把图像设置不可见状态
//                explodeImage.clearAnimation();
//                explodeImage.setVisibility(View.GONE);
//                windowManager.removeView(explodeImage);
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//                super.onAnimationRepeat(animation);
//            }
//
//            @Override
//            public void onAnimationStart(Animator animation) {
//                super.onAnimationStart(animation);
//                //开始时 设置为可见
//                explodeImage.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onAnimationPause(Animator animation) {
//                super.onAnimationPause(animation);
//            }
//
//            @Override
//            public void onAnimationResume(Animator animation) {
//                super.onAnimationResume(animation);
//            }
//        });
//        //时间
//        animator.setDuration(600);
//        //播放一次
//        animator.setRepeatMode(ValueAnimator.RESTART);
//        //差值器
//        animator.setInterpolator(new OvershootInterpolator());
//        animator.start();

        final ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.out_anim);
        final AnimationDrawable mAnimDrawable = (AnimationDrawable) imageView
                .getDrawable();
        params.gravity= Gravity.TOP|Gravity.LEFT;
//        这里得到的是其真实的大小，因为此时还得不到其测量值
        int intrinsicWidth = imageView.getDrawable().getIntrinsicWidth();
        int intrinsicHeight = imageView.getDrawable().getIntrinsicHeight();

        params.x= (int) dragPointF.x-intrinsicWidth/2;
        params.y= (int) dragPointF.y-intrinsicHeight/2 - statusHeight;
        params.width=WindowManager.LayoutParams.WRAP_CONTENT;
        params.height=WindowManager.LayoutParams.WRAP_CONTENT;
//      获取播放一次帧动画的总时长
        long duration = getAnimDuration(mAnimDrawable);

        windowManager.addView(imageView, params);
        mAnimDrawable.start();
//        由于帧动画不能定时停止，只能采用这种办法
        imageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAnimDrawable.stop();
                imageView.clearAnimation();
                windowManager.removeView(imageView);

            }
        },duration);
    }

    /**
     * 得到帧动画的摧毁时间
     * @param mAnimDrawable
     * @return
     */
    private long getAnimDuration(AnimationDrawable mAnimDrawable) {
        long duration=0;
        for(int i=0;i<mAnimDrawable.getNumberOfFrames();i++){
            duration += mAnimDrawable.getDuration(i);
        }
        return duration;
    }



    public interface DragStatusListener{
        void inScope();

        void outScope();
    }


    private DragStatusListener dragStatusListener;



}
