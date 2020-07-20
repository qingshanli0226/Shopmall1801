package com.shopmall.bawei.net;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

//通过拦截器，在网络请求头上添加一些自己的东西
public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Request newRequest = request.newBuilder().addHeader("token", "").build();

        return chain.proceed(newRequest);
    }
}
