package com.shopmall.bawei.shopmall1801.home.contract;

import com.shopmall.bawei.framework.base.BasePresenter;
import com.shopmall.bawei.framework.base.IView;
import com.shopmall.bawei.net.mode.HomeBean;

public class HomeContract {

    public interface IHomeView extends IView {
        void onHomeData(HomeBean homeBean);
    }

    public static abstract class HomePresenter extends BasePresenter<IHomeView> {
        public abstract void getHomeData();
    }

}
