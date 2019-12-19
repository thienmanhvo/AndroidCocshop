package fpt.edu.cocshop.OnGoing_Order;

import fpt.edu.cocshop.Model.Order;

public class OnGoingOrderPresenter implements OnGoingOrderContract.Presenter, OnGoingOrderContract.Model.OnFinishedListener {

    private OnGoingOrderContract.View storeListView;

    private OnGoingOrderContract.Model storeListModel;

    public OnGoingOrderPresenter(OnGoingOrderContract.View storeListView) {
        this.storeListView = storeListView;
        storeListModel = new OnGoingOrderModel();
    }


    @Override
    public void onDestroy() {
        this.storeListView = null;
    }

    @Override
    public void requestDataFromServer() {
        if (storeListView != null) {
            storeListView.showProgress();
        }

        storeListModel.getOrder(this);
    }

    @Override
    public void onFinished(Order order) {
        if (storeListView != null) {
            storeListView.onResponseSuccess(order);
            storeListView.hideProgress();
        }
    }

    @Override
    public void onFailure(String t) {
        if (storeListView != null) {
            storeListView.onResponseFailure(t);
            storeListView.hideProgress();
        }
    }
}
