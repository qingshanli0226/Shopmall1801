package com.shopmall.bawei.shopcar.shopcar.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.shopmall.bawei.framework.base.BaseMVPFragment;
import com.shopmall.bawei.framework.manager.CacheManager;
import com.shopmall.bawei.net.mode.ShopcarBean;
import com.shopmall.bawei.shopcar.R;
import com.shopmall.bawei.shopcar.shopcar.contract.ShopcarContract;
import com.shopmall.bawei.shopcar.shopcar.presenter.ShopcarPresenterImpl;

import java.util.List;

public class ShopcarFragment extends BaseMVPFragment<ShopcarPresenterImpl,ShopcarContract.IShopcarView> implements ShopcarContract.IShopcarView {
    private RecyclerView shopcarRv;
    private ShopcarAdapter shopcarAdapter;
    private TextView totalPriceTv;
    private CheckBox allSelectCheckBox;
    private boolean newAllSelect;


    private CacheManager.IShopcarDataChangeListener iShopcarDataChangeListener = new CacheManager.IShopcarDataChangeListener() {
        @Override
        public void onDataChanged(List<ShopcarBean> shopcarBeanList) {
            shopcarAdapter.updataData(shopcarBeanList);
        }

        @Override
        public void onOneDataChanged(int position, ShopcarBean shopcarBean) {
            shopcarAdapter.notifyItemChanged(position);//只更新这个位置的Item UI
        }

        @Override
        public void onMoneyChanged(String moneyValue) {
            totalPriceTv.setText(moneyValue);
        }

        @Override
        public void onAllSelected(boolean isAllSelect) {
            allSelectCheckBox.setChecked(isAllSelect);
        }
    };

    @Override
    protected void initHttpData() {
        //获取购物车数据
        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
        shopcarAdapter.updataData(shopcarBeanList);
        totalPriceTv.setText(CacheManager.getInstance().getMoneyValue());

        //去监听数据的改变,改变后去刷新UI
        CacheManager.getInstance().setShopcarDataChangeListener(iShopcarDataChangeListener);
        if (CacheManager.getInstance().isAllSelected()) {
            allSelectCheckBox.setChecked(true);
        } else {
            allSelectCheckBox.setChecked(false);
        }

        //设置全选的点击事件
        //设置全选的点击事件
        //设置全选的点击事件
        allSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allSelectCheckBox.isChecked()) {
                    newAllSelect = true;
                    ihttpPresenter.selectAllProduct(newAllSelect);
                } else {
                    newAllSelect = false;
                    ihttpPresenter.selectAllProduct(newAllSelect);
                }
            }
        });
    }

    @Override
    protected void initPresenter() {
        ihttpPresenter = new ShopcarPresenterImpl();
        shopcarAdapter.setShopcarPresenter(ihttpPresenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopcar;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        shopcarRv = findViewById(R.id.shopcarRv);
        shopcarRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        shopcarAdapter = new ShopcarAdapter();
        shopcarRv.setAdapter(shopcarAdapter);
        totalPriceTv = findViewById(R.id.sumValue);
        allSelectCheckBox = findViewById(R.id.allSelect);
    }

    @Override
    public void onProductNumChange(String result, int position, String newNum) {
//当服务端的商品数据发生改变后，本地缓存的商品数据的数量也要改变，保证和服务端数据一致
        CacheManager.getInstance().updateProductNum(position, newNum);
    }

    @Override
    public void onProductSelected(String result, int position) {
      //该回调代表当前该商品在购物车选择的状态发生了改变
        showMessage("该商品在购物车的选择发生改变");
        //需要保证服务端购物车该商品选择的状态和本地该商品在购物车上选择的状态一致性
        CacheManager.getInstance().updateProductSelected(position);
    }

    @Override
    public void onAllSelected(String result) {
        showMessage("所有的商品的选择状态发生了改变,全选状态为:" + newAllSelect);
        //更新本地缓存的数据的选择状态
        CacheManager.getInstance().selectAllProduct(newAllSelect);
    }

    @Override
    public void showError(String code, String message) {

    }

    @Override
    public void showLoaing() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unSetShopcarDataChangerListener(iShopcarDataChangeListener);
    }
}
