package com.shopmall.bawei.user.register.presenter;

import android.util.Log;
import android.widget.Toast;

import com.shopmall.bawei.common.ShopmallConstant;
import com.shopmall.bawei.net.NetBusinessException;
import com.shopmall.bawei.net.NetFunction;
import com.shopmall.bawei.net.RetroCreator;
import com.shopmall.bawei.net.ShopmallObserver;
import com.shopmall.bawei.net.mode.BaseBean;
import com.shopmall.bawei.net.mode.RegisterBean;
import com.shopmall.bawei.user.register.contract.RetisterContract;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class RegisterPresenterImpl extends RetisterContract.RegisterPresenter {

    @Override
    public void register(String name, String password) {

        HashMap<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("password", password);

        RetroCreator.getShopmallApiServie().register(params)
                .delay(3,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        //该方法是在网络请求发起前，在主线程中调用的方法
                        iHttpView.showLoaing();//显示加载的UI
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        //该方法是在通过RxJava获取网络数据后在主线程中调用的一个方法
                        iHttpView.hideLoading();//隐藏加载的UI
                    }
                })
                .subscribe(new ShopmallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        iHttpView.onReigster(s);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iHttpView.showError(errorCode,errorMessage);
                    }
                });
    }
}
