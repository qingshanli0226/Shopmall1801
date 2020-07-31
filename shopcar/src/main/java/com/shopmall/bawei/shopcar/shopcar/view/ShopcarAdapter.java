package com.shopmall.bawei.shopcar.shopcar.view;


import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.common.ShopmallConstant;
import com.shopmall.bawei.framework.base.BaseRVAdapter;
import com.shopmall.bawei.net.mode.ShopcarBean;
import com.shopmall.bawei.shopcar.R;
import com.shopmall.bawei.shopcar.shopcar.presenter.ShopcarPresenterImpl;

public class ShopcarAdapter extends BaseRVAdapter<ShopcarBean> {

    private ShopcarPresenterImpl shopcarPresenter;

    public void setShopcarPresenter(ShopcarPresenterImpl shopcarPresenter) {
        this.shopcarPresenter = shopcarPresenter;
    }


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_shop_car;
    }

    @Override
    protected void convert(final ShopcarBean itemData, BaseViewHolder baseViewHolder, final int position) {
        ImageView productImg = baseViewHolder.getView(R.id.productImage);
        Glide.with(baseViewHolder.itemView.getContext()).load(ShopmallConstant.BASE_RESOURCE_IMAGE_URL+itemData.getUrl()).into(productImg);
        TextView productNameTv = baseViewHolder.getView(R.id.productName);
        productNameTv.setText(itemData.getProductName());
        TextView productPriceTv = baseViewHolder.getView(R.id.productPrice);
        productPriceTv.setText((String)itemData.getProductPrice());
        TextView productNumTv = baseViewHolder.getView(R.id.productCount);
        productNumTv.setText(itemData.getProductNum());
        final CheckBox produectSelectCheckBox = baseViewHolder.getView(R.id.productSelect);
        produectSelectCheckBox.setChecked(itemData.isProductSelected());

        initProductSelectCheckBoxClickListener(produectSelectCheckBox, itemData, position);
        initAddDecClickListener(baseViewHolder, itemData, position);


    }

    private void initAddDecClickListener(BaseViewHolder baseViewHolder, final ShopcarBean itemData, final int position) {
        baseViewHolder.getView(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先获取之前产品的数量
                int oldNum = Integer.parseInt(itemData.getProductNum());
                int newNum = oldNum+1;
                shopcarPresenter.updateProductNum(itemData.getProductId(), String.valueOf(newNum), itemData.getProductName(), itemData.getUrl(),(String) itemData.getProductPrice(), position, String.valueOf(newNum));
            }
        });

        baseViewHolder.getView(R.id.btnSub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先获取之前产品的数量
                int oldNum = Integer.parseInt(itemData.getProductNum());
                if (oldNum > 1) {//确保购物车上的商品数量大于或者等于1
                    int newNum = oldNum - 1;
                    shopcarPresenter.updateProductNum(itemData.getProductId(), String.valueOf(newNum), itemData.getProductName(), itemData.getUrl(), (String) itemData.getProductPrice(),position, String.valueOf(newNum));
                }
            }
        });
    }

    private void initProductSelectCheckBoxClickListener(final CheckBox produectSelectCheckBox, final ShopcarBean itemData, final int position) {
        produectSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean newSelected;
                if (produectSelectCheckBox.isChecked()) {
                    newSelected = false;
                } else {
                    newSelected = true;
                }
                shopcarPresenter.updateProductSelected(itemData.getProductId(),newSelected,itemData.getProductName(),itemData.getUrl(),(String)itemData.getProductPrice(), position);
            }
        });
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
