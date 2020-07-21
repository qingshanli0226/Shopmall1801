package com.shopmall.bawei.user.register.contract;

import com.shopmall.bawei.framework.base.BasePresenter;
import com.shopmall.bawei.framework.base.IView;
import com.shopmall.bawei.net.mode.RegisterBean;

public class RetisterContract {

    public interface IRegisterView extends IView {
        void onReigster(String registerResult);
    }

    public static abstract class RegisterPresenter extends BasePresenter<IRegisterView> {
        public abstract void register(String name, String password);
    }
}
