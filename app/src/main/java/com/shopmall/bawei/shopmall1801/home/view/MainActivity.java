package com.shopmall.bawei.shopmall1801.home.view;

import android.Manifest;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobWorkItem;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shopmall.bawei.common.view.BottomBar;
import com.shopmall.bawei.common.view.ToolBar;
import com.shopmall.bawei.framework.base.BaseActivity;
import com.shopmall.bawei.shopcar.ShopcarActivity;
import com.shopmall.bawei.shopmall1801.R;

import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener, BottomBar.IBottomBarSelectListener {

    private BottomBar bottomBar;
    private Fragment[] fragments = new Fragment[] {new HomeFragment(), new TypeFragment(), new ShopcarFragment(),new MineFragment()};
    private Fragment currentFragment; //当前正在显示的Fragment

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initView() {
        bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setBottomBarSelectListener(this);
        bottomBar.setTabTitle(new String[] {"1801", "1802", "1803", "1804"});

        switchFragment(BottomBar.HOME_INDEX);//MainAcitivity默认进入HomeFragment

        initPermission();
    }


    private void initPermission() {
        //该Api（方法）在23版本之前系统是没有的。例如15版本的系统就没有该API
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//判断当前系统版本是不是大于等于23
            Toast.makeText(this, "系统版本大于23，需动态申请权限", Toast.LENGTH_SHORT).show();
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        } else {
            Toast.makeText(this, "系统版本低于23，所以无需动态申请权限", Toast.LENGTH_SHORT).show();
        }//这个就是在代码里做了版本适配(兼容适配),确保了应用程序在15到29之间，动态权限申请不会出现找不到方法的错误
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
                default:break;
        }
    }

    @Override
    public void onBottomBarSelected(int selectIndex) {
        switchFragment(selectIndex);//MainActivity监听BottomBar的点击事件，根据点击Button的位置去切换到对应的Fragment
    }

    //实现Fragment的切换
    private void switchFragment(int selectIndex) {
        Fragment fragment = fragments[selectIndex];//首先找到要切换的Fragment
        if (currentFragment == fragment) {//判断当前显示的Fragment是不是我们要切换的Fragment
             return;
        }

        //使用show+hide方式完成Fragment的切换
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (currentFragment!=null) {//currentFragment为空的情况是MainActivity刚实例化时
            fragmentTransaction.hide(currentFragment);
        }
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment).commit();
        } else {
            fragmentTransaction.add( R.id.frameLayoutId, fragment,fragment.getClass().getSimpleName()).commit();
        }
        currentFragment = fragment;
    }
}
