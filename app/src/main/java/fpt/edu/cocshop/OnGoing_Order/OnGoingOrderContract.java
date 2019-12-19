package fpt.edu.cocshop.OnGoing_Order;

import fpt.edu.cocshop.Model.Order;

public class OnGoingOrderContract {

    interface Model {

        interface OnFinishedListener {

            void onFinished(Order order);

            void onFailure(String t);
        }

        void getOrder(OnGoingOrderContract.Model.OnFinishedListener onFinishedListener);

    }

    public interface View {

        void showProgress();

        void hideProgress();

        void onResponseSuccess(Order order);

        void onResponseFailure(String throwable);

    }

    interface Presenter {

        void onDestroy();

        //void getMoreData(int pageNo);

        void requestDataFromServer();

    }
}
