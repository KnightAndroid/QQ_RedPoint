package com.knight.qq_redpoint;

import android.graphics.PointF;

public interface DragViewStatusListener {


    /**
     * 在拖拽范围外移动
     *
     * @param dragPoint
     */
    void outDragMove(PointF dragPoint);


    /**
     * 在拖拽范围外移动
     * 产生爆炸效果
     *
     */
    void outDragMoveUp(PointF dragPoint);


    /**
     * 在拖拽范围内移动
     *
      * @param dragPoint
     */
    void inDragUp(PointF dragPoint);



    /**
     * 当移出拖拽范围 后拖拽到范围内 恢复中心圆
     *
     *
     */
    void recoverCenterPoint(PointF centerPoint);


}
