package com.knight.qq_redpoint.listView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.knight.qq_redpoint.BetterRedPointViewControl;
import com.knight.qq_redpoint.R;

import java.util.ArrayList;

/**
 * created by knight at 2019/7/4 19:29
 */

public class ListViewAdapter extends BaseAdapter {

    /**
     * 用于记录需要删除的view的position
     */
    ArrayList<Integer> removeList =new ArrayList<Integer>();



    private Context mContext;

    public ListViewAdapter(Context mContext){
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return 200;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
            viewHolder.tv_dragView = (TextView) convertView.findViewById(R.id.tv_dragView);
            viewHolder.iv_head = (ImageView) convertView.findViewById(R.id.iv_head);
            Glide.with(mContext).load(R.mipmap.iv_image).apply(RequestOptions.bitmapTransform(new CircleCrop()).override(200,200)).into(viewHolder.iv_head);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(removeList.contains(position)){
            viewHolder.tv_dragView.setVisibility(View.GONE);
        }
        else {
            viewHolder.tv_dragView.setVisibility(View.VISIBLE);
            viewHolder.tv_dragView.setText(String.valueOf(position));
        }
        /**
         * 注意对于需要实现拖拽效果的view需要单独指定一个布局文件，并且次布局最好不能有viewGroup，
         * 否则view上面显示的文字可能在拖拽时不能识别，这样一是为了方便，二是为了减少消耗
         * 布局方式请参考xml文件
         */
        new BetterRedPointViewControl(mContext, viewHolder.tv_dragView, R.layout.includeview, new BetterRedPointViewControl.DragStatusListener() {
            @Override
            public void inScope() {
                notifyDataSetChanged();
            }

            @Override
            public void outScope() {
                removeList.add(position);
                notifyDataSetChanged();

            }
        });
        return convertView;
    }


    static class ViewHolder{
        TextView tv_dragView;
        ImageView iv_head;
    }
}
