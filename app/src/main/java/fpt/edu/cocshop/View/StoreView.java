package fpt.edu.cocshop.View;

import java.util.List;

import fpt.edu.cocshop.Model.Store;

public interface StoreView {
    void onComplete(List<Store> stores);

    void onError(String msg);
}
