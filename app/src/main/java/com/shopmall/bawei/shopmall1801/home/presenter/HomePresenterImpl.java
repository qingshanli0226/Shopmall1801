package com.shopmall.bawei.shopmall1801.home.presenter;

import com.shopmall.bawei.net.RetroCreator;
import com.shopmall.bawei.net.mode.HomeBean;
import com.shopmall.bawei.shopmall1801.home.contract.HomeContract;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenterImpl extends HomeContract.HomePresenter {


    @Override
    public void getHomeData() {
        RetroCreator.getShopmallApiServie().getHomeData()
                .delay(3, TimeUnit.SECONDS)//模拟网络请求差的情况，3秒钟后才实际进行网络请求
                .subscribeOn(Schedulers.io())
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
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        if (homeBean.getCode() == 200) {
                            iHttpView.onHomeData(homeBean);
                        } else {
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
