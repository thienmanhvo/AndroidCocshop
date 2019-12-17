package fpt.edu.cocshop.Store_List;

import java.util.List;

import fpt.edu.cocshop.Home_Store_List.HomeStoreListContract;
import fpt.edu.cocshop.Model.Store;

public class StoreListPresenter implements HomeStoreListContract.Presenter, StoreListContract.Model.OnFinishedListener {

    private StoreListContract.View storeListView;

    private StoreListContract.Model storeListModel;

    public StoreListPresenter(StoreListContract.View storeListView) {
        this.storeListView = storeListView;
        storeListModel = new StoreListModel();
    }

    @Override
    public void onDestroy() {
        this.storeListView = null;
    }

    @Override
    public void requestDataFromServer(int pageSize, int pageIndex, double latitude, double longitude, String brandId) {
        if (storeListView != null) {
            storeListView.showProgress();
        }

        storeListModel.getStoreList(this,pageSize,pageIndex,latitude,longitude,brandId);
    }

    @Override
    public void onStoreFinished(List<Store> StoreArrayList) {
        if (storeListView != null) {
            storeListView.setStoreToRecyclerView(StoreArrayList);
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
