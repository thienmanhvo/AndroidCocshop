package fpt.edu.cocshop.Store_List;

import java.util.List;

import fpt.edu.cocshop.Home_Store_List.HomeStoreListContract;
import fpt.edu.cocshop.Model.Store;

public interface StoreListContract {

    interface Model {

        interface OnFinishedListener {
            void onStoreFinished(List<Store> StoreArrayList);

            void onFailure(String t);
        }


        void getStoreList(StoreListContract.Model.OnFinishedListener onFinishedListener, int pageSize, int pageIndex, double latitude, double longitude,String brandId);

    }

    interface View {

        void showProgress();

        void hideProgress();

        void setStoreToRecyclerView(List<Store> StoreArrayList);

        void onResponseFailure(String throwable);

    }

    interface Presenter {

        void onDestroy();

        //void getMoreData(int pageNo);

        void requestDataFromServer(int pageSize, int pageIndex, double latitude, double longitude, String brandId);

    }
}
