package fpt.edu.cocshop.StoreDetail;

import fpt.edu.cocshop.Model.Store;

public class StoreDetailPresenter implements StoreDetailContract.Presenter, StoreDetailContract.Model.OnFinishedListener {


    private StoreDetailContract.View storeDetailView;

    private StoreDetailContract.Model storeDetailModel;

    public StoreDetailPresenter(StoreDetailContract.View storeDetailView) {
        this.storeDetailView = storeDetailView;
        storeDetailModel = new StoreDetailModel();
    }

    @Override
    public void onStoreFinished(Store store) {
        if (storeDetailView != null) {
            storeDetailView.setStoreToRecyclerView(store);
            storeDetailView.hideProgress();
        }
    }

    @Override
    public void onFailure(String t) {
        if (storeDetailView != null) {
            storeDetailView.onResponseFailure(t);
            storeDetailView.hideProgress();

        }
    }

    @Override
    public void onDestroy() {
        this.storeDetailView = null;
    }

    @Override
    public void requestDataFromServer(double latitude, double longitude, String brandId) {

        if (storeDetailView != null) {
            storeDetailView.showProgress();
        }

        storeDetailModel.getStoreDetail(this, latitude, longitude, brandId);
    }
}
