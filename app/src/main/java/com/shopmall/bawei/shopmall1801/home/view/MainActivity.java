package com.shopmall.bawei.shopmall1801.home.view;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.common.view.ToolBar;
import com.shopmall.bawei.framework.base.BaseMVPActivity;
import com.shopmall.bawei.net.mode.HomeBean;
import com.shopmall.bawei.shopcar.ShopcarActivity;
import com.shopmall.bawei.shopmall1801.R;
import com.shopmall.bawei.shopmall1801.home.contract.HomeContract;
import com.shopmall.bawei.shopmall1801.home.presenter.HomePresenterImpl;
import com.shopmall.bawei.user.register.view.RegisterActivity;

public class MainActivity extends BaseMVPActivity<HomePresenterImpl, HomeContract.IHomeView> implements HomeContract.IHomeView, View.OnClickListener {

    private RecyclerView homeRv;
    private HomeAdapter homeAdapter;
    private TextView errorTv;
    private RelativeLayout normalContent;
    private ToolBar toolBar;
    @Override
    protected void initPresenter() {
        iHttpPresenter = new HomePresenterImpl();
    }
    @Override
    protected void initData() {
        iHttpPresenter.getHomeData();
    }

    @Override
    protected void initView() {
        homeRv = findViewById(R.id.homeRv);
        homeRv.setLayoutManager(new LinearLayoutManager(this));
        homeAdapter = new HomeAdapter();
        homeRv.setAdapter(homeAdapter);
        errorTv = findViewById(R.id.errorTv);
        errorTv.setOnClickListener(this);
        normalContent = findViewById(R.id.normalContent);
        toolBar = findViewById(R.id.mainToolbar);
        toolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ShopcarActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        Toast.makeText(this, "获取到首页数据", Toast.LENGTH_SHORT).show();
        homeAdapter.addOneData(homeBean.getBanner_info());
        homeAdapter.addOneData(homeBean.getChannel_info());
        homeAdapter.addOneData(homeBean.getAct_info());
        homeAdapter.addOneData(homeBean.getHot_info());
        homeAdapter.addOneData(homeBean.getRecommend_info());
        homeAdapter.addOneData(homeBean.getSeckill_info());
    }

    @Override
    public void showError(String code, String message) {
        errorTv.setVisibility(View.VISIBLE);
        normalContent.setVisibility(View.GONE);
        errorTv.setText(message + " 点击刷新数据");
    }

    @Override
    public void showLoaing() {
        errorTv.setVisibility(View.GONE);
        normalContent.setVisibility(View.VISIBLE);
        loadingBar.setVisibility(View.VISIBLE);
        errorTv.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.errorTv:
                iHttpPresenter.getHomeData();
                break;
                default:break;
        }
    }
}
