package com.shopmall.bawei.user.login.contract;


import com.shopmall.bawei.framework.base.BasePresenter;
import com.shopmall.bawei.framework.base.IView;
import com.shopmall.bawei.net.mode.RegisterBean;

public class RegisterContract {

    public interface IRegisterView extends IView {
        void onRegister(String result);
    }

    public static abstract class ReigsterPresenter extends BasePresenter<IRegisterView> {
        public abstract void register(String name, String password);
    }
}
