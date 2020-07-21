package com.shopmall.bawei.user.register.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shopmall.bawei.framework.base.BaseActivity;
import com.shopmall.bawei.framework.base.BaseMVPActivity;
import com.shopmall.bawei.net.mode.RegisterBean;
import com.shopmall.bawei.user.R;
import com.shopmall.bawei.user.register.contract.RetisterContract;
import com.shopmall.bawei.user.register.presenter.RegisterPresenterImpl;

import io.reactivex.observables.GroupedObservable;


public class RegisterActivity extends BaseMVPActivity<RegisterPresenterImpl, RetisterContract.IRegisterView> implements View.OnClickListener,RetisterContract.IRegisterView {

    private EditText passwordEditText;
    private EditText nameEditText;

    @Override
    protected void initPresenter() {
        iHttpPresenter = new RegisterPresenterImpl();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        nameEditText = findViewById(R.id.name);
        passwordEditText = findViewById(R.id.password);
        findViewById(R.id.btnRegister).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRegister) {
            iHttpPresenter.register(nameEditText.getText().toString().trim(), passwordEditText.getText().toString().trim());
        }
    }

    @Override
    public void onReigster(String registerResult) {
        Toast.makeText(this, registerResult, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String code, String message) {
        loadingBar.setVisibility(View.GONE);
        Toast.makeText(this, code + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoaing() {
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
       loadingBar.setVisibility(View.GONE);
    }
}
