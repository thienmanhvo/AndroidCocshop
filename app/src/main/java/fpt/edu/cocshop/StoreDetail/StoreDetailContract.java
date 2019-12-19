package fpt.edu.cocshop.StoreDetail;

import java.util.List;

import fpt.edu.cocshop.Model.Store;
import fpt.edu.cocshop.Store_List.StoreListContract;

public interface StoreDetailContract {
    interface Model {

        interface OnFinishedListener {
            void onStoreFinished(Store store);

            void onFailure(String t);
        }


        void getStoreDetail(StoreDetailContract.Model.OnFinishedListener onFinishedListener, double latitude, double longitude, String storeId);

    }

    interface View {

        void showProgress();

        void hideProgress();

        void setStoreToRecyclerView(Store Store);

        void onResponseFailure(String throwable);

    }

    interface Presenter {

        void onDestroy();

        //void getMoreData(int pageNo);

        void requestDataFromServer(double latitude, double longitude, String storeId);

    }
}
