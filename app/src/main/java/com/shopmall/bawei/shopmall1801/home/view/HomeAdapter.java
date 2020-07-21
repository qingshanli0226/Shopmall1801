package com.shopmall.bawei.shopmall1801.home.view;

import android.util.Log;

import com.shopmall.bawei.framework.base.BaseRVAdapter;
import com.shopmall.bawei.net.mode.HomeBean;
import com.shopmall.bawei.shopmall1801.R;

public class HomeAdapter extends BaseRVAdapter<Object> {

    private final int BANNER_TYPE = 0;
    private final int CHANNEL_TYPE = 1;
    private final int ACT_TYPE = 2;
    private final int HOT_TYPE = 3;
    private final int RECOMMEND_TYPE = 4;
    private final int SECKILL_TYPE = 5;

    @Override
    protected int getLayoutId(int viewType) {
        switch (viewType) {
            case BANNER_TYPE:
                return R.layout.home_view_banner;
            case CHANNEL_TYPE:
                return R.layout.home_view_channel;
            case ACT_TYPE:
                return R.layout.home_view_act;
            case HOT_TYPE:
                return R.layout.home_view_hot;
            case RECOMMEND_TYPE:
                return R.layout.home_view_recommend;
            case SECKILL_TYPE:
                return R.layout.home_view_seckill;
                default:return R.layout.home_view_banner;
        }
    }

    @Override
    protected void convert(Object itemData, BaseViewHolder baseViewHolder, int position) {
        switch (position) {
            case 0: displayBanner(itemData, baseViewHolder);break;
            case 1: displayChannel(itemData, baseViewHolder);break;
            case 2: displayAct(itemData, baseViewHolder);break;
            case 3: displayHot(itemData, baseViewHolder);break;
            case 4: displayRecommend(itemData, baseViewHolder);break;
            case 5: displaySeckill(itemData, baseViewHolder);break;
            default:displayBanner(itemData,baseViewHolder);
        }
    }

    private void displayChannel(Object itemData, BaseViewHolder baseViewHolder) {
        Log.d("LQS", "displayChannel...");
        HomeBean.ChannelInfoBean channelInfoBean = (HomeBean.ChannelInfoBean)itemData;//强转成我们需要的类型
    }

    private void displayAct(Object itemData, BaseViewHolder baseViewHolder) {
        Log.d("LQS", "displayAct...");
        HomeBean.ActInfoBean actInfoBean = (HomeBean.ActInfoBean)itemData;//强转成我们需要的类型
    }

    private void displayBanner(Object itemData, BaseViewHolder baseViewHolder) {
        Log.d("LQS", "displayBanner...");
        HomeBean.BannerInfoBean bannerInfoBean = (HomeBean.BannerInfoBean)itemData;//强转成我们需要的类型
    }

    private void displayHot(Object itemData, BaseViewHolder baseViewHolder) {
        Log.d("LQS", "displayHot...");
        HomeBean.HotInfoBean hotInfoBean = (HomeBean.HotInfoBean)itemData;//强转成我们需要的类型
    }

    private void displayRecommend(Object itemData, BaseViewHolder baseViewHolder) {
        Log.d("LQS", "displayRecommend...");
        HomeBean.RecommendInfoBean recommendInfoBean = (HomeBean.RecommendInfoBean)itemData;//强转成我们需要的类型
    }
    private void displaySeckill(Object itemData, BaseViewHolder baseViewHolder) {
        Log.d("LQS", "displaySeckill...");
        HomeBean.SeckillInfoBean seckillInfoBean = (HomeBean.SeckillInfoBean)itemData;//强转成我们需要的类型
    }


    @Override
    protected int getViewType(int position) {
        switch (position) {
            case 0: return BANNER_TYPE;
            case 1: return CHANNEL_TYPE;
            case 2: return ACT_TYPE;
            case 3: return HOT_TYPE;
            case 4: return RECOMMEND_TYPE;
            case 5: return SECKILL_TYPE;
            default:
                return BANNER_TYPE;
        }
    }

}
