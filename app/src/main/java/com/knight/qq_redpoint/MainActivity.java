package com.knight.qq_redpoint;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.knight.qq_redpoint.listView.ListViewAdapter;
import com.knight.qq_redpoint.recycleview.RecycleviewAdapter;

public class MainActivity extends AppCompatActivity {
    private ListView lv;

    private RecyclerView rv_view;
    private ListViewAdapter mListViewAdapter;
    private RecycleviewAdapter mRecycleviewAdapter;
    private Context mContext=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        lv = (ListView) findViewById(R.id.lv);
//        mListViewAdapter = new ListViewAdapter(mContext);
//        lv.setAdapter(mListViewAdapter);
        rv_view = findViewById(R.id.rv_view);
        mRecycleviewAdapter = new RecycleviewAdapter(mContext);
        rv_view.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        rv_view.setAdapter(mRecycleviewAdapter);

    }

}
