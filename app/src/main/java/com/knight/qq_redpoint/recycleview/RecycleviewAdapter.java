package com.knight.qq_redpoint.recycleview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.knight.qq_redpoint.BetterRedPointViewControl;
import com.knight.qq_redpoint.R;

import java.util.ArrayList;

/**
 * created by knight at 2019/7/4 19:12
 */

public class RecycleviewAdapter extends RecyclerView.Adapter<ItemHolder> {
    /**
     * 用于记录需要删除的view的position
     */
    ArrayList<Integer> removeList =new ArrayList<Integer>();

    private Context mContext;
    public RecycleviewAdapter(Context mContext){
        this.mContext = mContext;
    }



    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //加载布局文件
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item,null);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, final int i) {
        itemHolder.tv_dragView.setText(String.valueOf(i));

        Glide.with(mContext).load(R.mipmap.iv_image).apply(RequestOptions.bitmapTransform(new CircleCrop()).override(200,200)).into(itemHolder.iv_head);
        if(removeList.contains(i)){
            itemHolder.tv_dragView.setVisibility(View.GONE);
        }
        else {
            itemHolder.tv_dragView.setVisibility(View.VISIBLE);
            itemHolder.tv_dragView.setText(String.valueOf(i));
        }

        new BetterRedPointViewControl(mContext, itemHolder.tv_dragView, R.layout.includeview, new BetterRedPointViewControl.DragStatusListener() {
            @Override
            public void inScope() {
                notifyDataSetChanged();
            }

            @Override
            public void outScope() {
                removeList.add(i);
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return 100;
    }
}
