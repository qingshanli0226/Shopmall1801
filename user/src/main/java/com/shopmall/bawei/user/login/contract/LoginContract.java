package com.shopmall.bawei.user.login.contract;


import com.shopmall.bawei.net.mode.LoginBean;
import com.shopmall.bawei.framework.base.BasePresenter;
import com.shopmall.bawei.framework.base.IView;

public class LoginContract {

    public interface ILoginView extends IView {
        void onLogin(LoginBean loginBean);
    }

    public static abstract class LoginPresenter extends BasePresenter<ILoginView> {
        public abstract void login(String userName, String password);
    }
}
