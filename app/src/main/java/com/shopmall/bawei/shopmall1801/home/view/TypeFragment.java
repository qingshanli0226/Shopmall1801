package com.shopmall.bawei.shopmall1801.home.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shopmall.bawei.framework.base.BaseFragment;
import com.shopmall.bawei.shopmall1801.R;

public class TypeFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_type;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public void onRightClick() {
        super.onRightClick();
        showMessage("点击了搜索按钮");
    }
}
