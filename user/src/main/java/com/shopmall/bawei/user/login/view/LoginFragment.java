package com.shopmall.bawei.user.login.view;

import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.common.ShopmallConstant;
import com.shopmall.bawei.net.mode.LoginBean;
import com.shopmall.bawei.framework.manager.ShopUserManager;
import com.shopmall.bawei.common.view.BottomBar;
import com.shopmall.bawei.framework.base.BaseMVPFragment;
import com.shopmall.bawei.user.R;
import com.shopmall.bawei.user.login.contract.LoginContract;
import com.shopmall.bawei.user.login.presenter.LoginPresenterImpl;


public class LoginFragment extends BaseMVPFragment<LoginPresenterImpl, LoginContract.ILoginView>
        implements LoginContract.ILoginView, View.OnClickListener,LoginRegisterActivity.INameInterface {

    private EditText nameEditText;
    private EditText passwordEditText;

    @Override
    protected void initHttpData() {

    }

    @Override
    protected void initPresenter() {
        ihttpPresenter = new LoginPresenterImpl();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        nameEditText = findViewById(R.id.name);
        passwordEditText = findViewById(R.id.password);
        findViewById(R.id.loginButton).setOnClickListener(this);
        findViewById(R.id.registerTv).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.loginButton) {
            login();
        } else if (v.getId() == R.id.registerTv) {
            switchRegisterFragment();
        }

    }

    private void switchRegisterFragment() {
        LoginRegisterActivity loginRegisterActivity = (LoginRegisterActivity)getActivity();
        loginRegisterActivity.switchFragment(1, nameEditText.getText().toString().trim());//
    }

    private void login() {
        ihttpPresenter.login(nameEditText.getText().toString().trim(), passwordEditText.getText().toString().trim());
    }

    @Override
    public void showError(String code, String message) {
        if (code.equals(ShopmallConstant.USER_NOT_REGISTER_ERROR)) {
            switchRegisterFragment();
        }
        showMessage(code + ":" + message);

    }

    @Override
    public void showLoaing() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setName(String name) {
        nameEditText.setText(name);
        passwordEditText.setText("");
    }

    @Override
    public void onLogin(LoginBean loginBean) {
        showMessage("登录成功");
        //实现跳转到MainActivity，显示HomeFragment,Activity的启动模式问题.
        ShopUserManager.getInstance().saveLoginBean(loginBean);//把登录后的用户信息存储起来
        /*Intent intent = new Intent();
        intent.setAction("com.bawei.1801.HOME");//通过隐式方式启动主页面
        intent.putExtra("index", BottomBar.HOME_INDEX);
        startActivity(intent);*/


        showMessage("使用ARouter");
        ARouter.getInstance().build("/main/MainActivity").withInt("index", BottomBar.HOME_INDEX).navigation();

        getActivity().finish();//是不是一定能回到MainActivity，这个不一定，因为，MainActivity有可能被系统回收.
    }
}
