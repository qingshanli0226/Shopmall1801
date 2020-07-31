package com.shopmall.bawei.user.login.view;

import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.framework.base.BaseActivity;
import com.shopmall.bawei.user.R;



@Route(path = "/usr/LoginRegisterActivity")
public class LoginRegisterActivity extends BaseActivity {
    private ViewPager viewPager;
    private LoginRegisterAdapter loginRegisterAdapter;



    @Override
    protected void initView() {
        viewPager = findViewById(R.id.viewPager);
        loginRegisterAdapter = new LoginRegisterAdapter(getSupportFragmentManager());
        viewPager.setAdapter(loginRegisterAdapter);

        ARouter.getInstance().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_register;
    }


    //Activity提供方法，让Fragment调用，实现Fragment之间的跳转
    public void switchFragment(int index, String name) {
        viewPager.setCurrentItem(index);
        ((INameInterface)loginRegisterAdapter.getItem(index)).setName(name);
    }

    public interface INameInterface {
        void setName(String name);
    }

}
