package com.shopmall.bawei.shopmall1801.home.view;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.common.ShopmallConstant;
import com.shopmall.bawei.framework.base.BaseRVAdapter;
import com.shopmall.bawei.net.mode.HomeBean;
import com.shopmall.bawei.shopmall1801.R;

public class ChannelAdapter extends BaseRVAdapter<HomeBean.ChannelInfoBean> {


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_channel_layout;
    }

    @Override
    protected void convert(HomeBean.ChannelInfoBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.channelImg);
        Glide.with(baseViewHolder.itemView.getContext()).load(ShopmallConstant.BASE_RESOURCE_IMAGE_URL + itemData.getImage()).into(imageView);
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
