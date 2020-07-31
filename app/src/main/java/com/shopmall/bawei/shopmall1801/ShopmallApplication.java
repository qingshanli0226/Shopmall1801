package com.shopmall.bawei.shopmall1801;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.framework.manager.CacheManager;
import com.shopmall.bawei.framework.manager.ShopUserManager;
import com.shopmall.bawei.net.NetModule;

import java.util.ArrayList;

public class ShopmallApplication extends Application {

    public static ShopmallApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        //初始化Arouter
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(ShopmallApplication.this);

        NetModule.init(this);
        ShopUserManager.getInstance().init(this);
        CacheManager.getInstance().init(this);
    }
}
