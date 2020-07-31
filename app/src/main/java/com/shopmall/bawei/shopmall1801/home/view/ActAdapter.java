package com.shopmall.bawei.shopmall1801.home.view;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.common.ShopmallConstant;
import com.shopmall.bawei.framework.base.BaseRVAdapter;
import com.shopmall.bawei.net.mode.HomeBean;
import com.shopmall.bawei.shopmall1801.R;

public class ActAdapter extends BaseRVAdapter<HomeBean.ActInfoBean> {


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_act_layout;
    }

    @Override
    protected void convert(HomeBean.ActInfoBean itemData, BaseViewHolder baseViewHolder, int position) {
        WebView imageView = baseViewHolder.getView(R.id.actImg);
        imageView.setWebViewClient(new WebViewClient());
        imageView.setWebChromeClient(new WebChromeClient());
        imageView.loadUrl(ShopmallConstant.BASE_RESOURCE_IMAGE_URL + itemData.getIcon_url());
        //Glide.with(baseViewHolder.itemView.getContext()).load(ShopmallConstant.BASE_RESOURCE_IMAGE_URL + itemData.getIcon_url()).into(imageView);
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
