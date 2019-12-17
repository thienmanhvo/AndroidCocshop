package fpt.edu.cocshop.Home_Store_List;

import java.util.List;

import fpt.edu.cocshop.Model.Brand;
import fpt.edu.cocshop.Model.Store;

public interface HomeStoreListContract {

    interface Model {

        interface OnFinishedListener {
            void onStoreFinished(List<Store> StoreArrayList, int taskId);

            void onPopularBrandFinished(List<Brand> BrandArrayList, int taskId);

            void onTopStoreFinished(List<Store> StoreArrayList, int taskId);

            void onFailure(String t);
        }

        void getStoreList(OnFinishedListener onFinishedListener, int pageSize, int pageIndex, double latitude, double longitude);

        void getPopularBrandList(OnFinishedListener onFinishedListener, int pageSize, int pageIndex);

        void getNearestStoreList(OnFinishedListener onFinishedListener, int pageSize, int pageIndex, double latitude, double longitude, double radius);

        void getTopStoreList(OnFinishedListener onFinishedListener, int pageSize, int pageIndex, double latitude, double longitude);

    }

    interface View {

        void showProgress();

        void hideProgress();

        void setNearestStoreToRecyclerView(List<Store> StoreArrayList);

        void setPopularBrandToRecyclerView(List<Brand> BrandArrayList);

        void setTopStoreToRecyclerView(List<Store> StoreArrayList);

        void onResponseFailure(String throwable);

    }

    interface Presenter {

        void onDestroy();

        //void getMoreData(int pageNo);

        void requestDataFromServer(int pageSize, int pageIndex, double latitude, double longitude, double radius);

    }
}
