package com.shopmall.bawei.shopmall1801.home.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.shopmall.bawei.framework.base.BaseMVPActivity;
import com.shopmall.bawei.net.mode.HomeBean;
import com.shopmall.bawei.shopcar.ShopcarActivity;
import com.shopmall.bawei.shopmall1801.R;
import com.shopmall.bawei.shopmall1801.home.contract.HomeContract;
import com.shopmall.bawei.shopmall1801.home.presenter.HomePresenterImpl;
import com.shopmall.bawei.user.register.view.RegisterActivity;

public class MainActivity extends BaseMVPActivity<HomePresenterImpl, HomeContract.IHomeView> implements HomeContract.IHomeView {

    private RecyclerView homeRv;
    private HomeAdapter homeAdapter;
    @Override
    protected void initPresenter() {
        iHttpPresenter = new HomePresenterImpl();
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnShopcar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ShopcarActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnGetHomeData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iHttpPresenter.getHomeData();
            }
        });

        homeRv = findViewById(R.id.homeRv);
        homeRv.setLayoutManager(new LinearLayoutManager(this));
        homeAdapter = new HomeAdapter();
        homeRv.setAdapter(homeAdapter);
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

    }

    @Override
    public void showLoaing() {
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingBar.setVisibility(View.GONE);
    }
}
