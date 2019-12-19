package fpt.edu.cocshop.Check_out;

import java.util.List;

import fpt.edu.cocshop.Model.CartObj;
import fpt.edu.cocshop.Model.Product;
import fpt.edu.cocshop.Model.Store;
import fpt.edu.cocshop.StoreDetail.StoreDetailContract;

public interface CheckOutContract {
    interface Model {

        interface OnFinishedListener {
            void onCheckoutFinished();

            void onFailure(String t);
        }


        void checkOut(CheckOutContract.Model.OnFinishedListener onFinishedListener, double latitude, double longitude, CartObj cartObj);

    }

    interface View {

        void showProgress();

        void hideProgress();

        void onResponseSuccess();

        void onResponseFailure(String throwable);

    }

    interface Presenter {

        void onDestroy();

        //void getMoreData(int pageNo);

        void postDataToServer(double latitude, double longitude, CartObj cartObj);

    }
}
