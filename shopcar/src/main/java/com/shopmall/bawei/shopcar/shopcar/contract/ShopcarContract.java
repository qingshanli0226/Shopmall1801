package com.shopmall.bawei.shopcar.shopcar.contract;

import com.shopmall.bawei.framework.base.BasePresenter;
import com.shopmall.bawei.framework.base.IView;

public class ShopcarContract {

    public interface IShopcarView extends IView {
        void onProductNumChange(String result, int position, String newNum);
        void onProductSelected(String result, int position);
        void onAllSelected(String result);
    }

    public static abstract class ShopcarPresenter extends BasePresenter<IShopcarView> {
        public abstract void updateProductNum(String productId, String productNum, String productName, String url, String productPrice, int position, String newNum);
        public abstract void updateProductSelected(String productId, boolean productSelected, String productName, String url, String productPrice, int position);
        public abstract void selectAllProduct(boolean isAllSelect);
    }
}
