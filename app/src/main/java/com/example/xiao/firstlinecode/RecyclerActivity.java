package com.example.xiao.firstlinecode;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.xiao.firstlinecode.adapter.RecyclerAdapter;
import com.example.xiao.firstlinecode.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends BaseActivity {

    private static final String TAG = "RecyclerActivity";

    private RecyclerView mRecyclerView;

    private List<String> mDatas = new ArrayList<>();

    private RecyclerAdapter mAdapter;

    /**
     * LinearLayoutManager:线性布局
     * GridLayoutManager:网格布局
     * StaggeredGridLayoutManager:瀑布流布局
     */
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        initData();
        initView();
        initEvent();

    }

    private void initData() {
        for (int i = 0; i < 20; i++) {

            mDatas.add("item" + i);

        }
    }

    private void initView() {
        mAdapter = new RecyclerAdapter(this, mDatas);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

//        layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // 横向滚动
        /**
         * 瀑布流布局
         * 第一个参数：指定布局的列数
         * 第二个参数：指定布局的排列方向
         */
        layoutManager = new StaggeredGridLayoutManager(6, StaggeredGridLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        
    }

    private void initEvent() {

    }
}
