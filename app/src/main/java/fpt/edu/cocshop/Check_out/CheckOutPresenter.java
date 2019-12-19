package fpt.edu.cocshop.Check_out;

import fpt.edu.cocshop.Model.CartObj;
import fpt.edu.cocshop.Store_List.StoreListContract;

public class CheckOutPresenter implements CheckOutContract.Presenter, CheckOutContract.Model.OnFinishedListener {


    private CheckOutContract.View checkOutView;

    private CheckOutContract.Model checkOutModel;

    public CheckOutPresenter(CheckOutContract.View checkOutView) {
        this.checkOutView = checkOutView;
        checkOutModel = new CheckOutModel();
    }

    @Override
    public void onCheckoutFinished() {
        if (checkOutView != null) {
            checkOutView.onResponseSuccess();
            checkOutView.hideProgress();
        }
    }

    @Override
    public void onFailure(String t) {
        if (checkOutView != null) {
            checkOutView.onResponseFailure(t);
            checkOutView.hideProgress();
        }
    }

    @Override
    public void onDestroy() {
        this.checkOutView = null;
    }

    @Override
    public void postDataToServer(double latitude, double longitude, CartObj cartObj, String storeId) {
        if (checkOutView != null) {
            checkOutView.showProgress();
        }

        checkOutModel.checkOut(this, latitude, longitude, cartObj, storeId);
    }
}
