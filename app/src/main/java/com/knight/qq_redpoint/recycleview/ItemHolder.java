package com.knight.qq_redpoint.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.knight.qq_redpoint.R;

/**
 * created by knight at 2019/7/4 19:05
 */

public class ItemHolder extends RecyclerView.ViewHolder {


    public final ImageView iv_head;
    public final TextView tv_dragView;

    public ItemHolder(View itemView){
        super(itemView);
        iv_head = itemView.findViewById(R.id.iv_head);
        tv_dragView = itemView.findViewById(R.id.tv_dragView);

    }





}
