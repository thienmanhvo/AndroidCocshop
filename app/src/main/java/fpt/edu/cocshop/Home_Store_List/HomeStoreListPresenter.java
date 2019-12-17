package fpt.edu.cocshop.Home_Store_List;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.cocshop.Model.Brand;
import fpt.edu.cocshop.Model.Store;

public class HomeStoreListPresenter implements HomeStoreListContract.Presenter, HomeStoreListContract.Model.OnFinishedListener {


    private HomeStoreListContract.View storeListView;

    private HomeStoreListContract.Model storeListModel;
    private int totalTaskDone;

    public HomeStoreListPresenter(HomeStoreListContract.View storeListView) {
        this.storeListView = storeListView;
        storeListModel = new HomeStoreListModel();
        totalTaskDone = 0;
    }

    @Override
    public void onStoreFinished(List<Store> StoreArrayList, int taskId) {
        totalTaskDone++;
        if (storeListView != null) {
            storeListView.setNearestStoreToRecyclerView(StoreArrayList);
            if (totalTaskDone == 3) {
                storeListView.hideProgress();
            }
        }
    }

    @Override
    public void onPopularBrandFinished(List<Brand> BrandArrayList, int taskId) {
        totalTaskDone++;
        if (storeListView != null) {
            storeListView.setPopularBrandToRecyclerView(BrandArrayList);
            if (totalTaskDone == 3) {
                storeListView.hideProgress();
            }
        }
    }

    @Override
    public void onTopStoreFinished(List<Store> StoreArrayList, int taskId) {
        totalTaskDone++;
        if (storeListView != null) {
            storeListView.setTopStoreToRecyclerView(StoreArrayList);
            if (totalTaskDone == 3) {
                storeListView.hideProgress();
            }
        }
    }

    @Override
    public void onFailure(String t) {
        totalTaskDone++;
        if (storeListView != null) {
            storeListView.onResponseFailure(t);
            if (totalTaskDone == 3) {
                storeListView.hideProgress();
            }
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

        storeListModel.getPopularBrandList(this, pageSize, pageIndex);
        storeListModel.getNearestStoreList(this, pageSize, pageIndex, latitude, longitude, radius);
        storeListModel.getTopStoreList(this, pageSize, pageIndex, latitude, longitude);
    }
}
