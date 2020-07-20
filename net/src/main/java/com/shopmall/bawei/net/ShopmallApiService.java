package com.shopmall.bawei.net;



import com.shopmall.bawei.net.mode.HomeBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

//认为是IMode
public interface ShopmallApiService {

    @GET("atguigu/json/HOME_URL.json")
    Observable<HomeBean> getHomeData();


}
