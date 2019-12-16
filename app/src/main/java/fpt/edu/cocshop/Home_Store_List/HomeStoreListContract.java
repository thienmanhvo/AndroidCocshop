package fpt.edu.cocshop.Home_Store_List;

import java.util.List;

import fpt.edu.cocshop.Model.Store;

public interface HomeStoreListContract {

    interface Model {

        interface OnFinishedListener {
            void onFinished(List<Store> StoreArrayList);

            void onFailure(String t);
        }

        void getStoreList(OnFinishedListener onFinishedListener, int pageSize, int pageIndex, double latitude, double longitude);
        void getNearestStoreList(OnFinishedListener onFinishedListener, int pageSize, int pageIndex, double latitude, double longitude,double radius);

    }

    interface View {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Store> StoreArrayList);

        void onResponseFailure(String throwable);

    }

    interface Presenter {

        void onDestroy();

        //void getMoreData(int pageNo);

        void requestDataFromServer(int pageSize, int pageIndex, double latitude, double longitude,double radius);

    }
}
