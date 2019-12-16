package fpt.edu.cocshop.Home_Store_List;

import java.util.List;

import fpt.edu.cocshop.Model.Store;

public class HomeStoreListPresenter implements HomeStoreListContract.Presenter, HomeStoreListContract.Model.OnFinishedListener {


    private HomeStoreListContract.View storeListView;

    private HomeStoreListContract.Model storeListModel;

    public HomeStoreListPresenter(HomeStoreListContract.View storeListView) {
        this.storeListView = storeListView;
        storeListModel = new HomeStoreListModel();
    }

    @Override
    public void onFinished(List<Store> StoreArrayList) {
        if (storeListView != null) {
            storeListView.setDataToRecyclerView(StoreArrayList);
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

    @Override
    public void onDestroy() {
        this.storeListView = null;
    }

    @Override
    public void requestDataFromServer(int pageSize, int pageIndex, double latitude, double longitude, double radius) {
        if (storeListView != null) {
            storeListView.showProgress();
        }
        storeListModel.getNearestStoreList(this, pageSize, pageIndex, latitude, longitude, radius);
        storeListModel.getStoreList(this, pageSize, pageIndex, latitude, longitude, radius);
    }
}
