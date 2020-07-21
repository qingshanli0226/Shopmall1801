package com.shopmall.bawei.net;



import com.shopmall.bawei.net.mode.BaseBean;
import com.shopmall.bawei.net.mode.HomeBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

//认为是IMode
public interface ShopmallApiService {

    @GET("atguigu/json/HOME_URL.json")
    Observable<BaseBean<HomeBean>> getHomeData();

    @POST("register")
    @FormUrlEncoded
    Observable<BaseBean<String>> register(@FieldMap HashMap<String, String> params);
}
