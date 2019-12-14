package fpt.edu.cocshop.Model;

import java.util.HashMap;

public class StoreCart {
    public static HashMap<String, CartObj> storeCart;

    private StoreCart() {
    }

    public static StoreCart getInstance() {
        return StoreCartHelper.getInstance();
    }

    private static class StoreCartHelper {
        private static volatile StoreCart INSTANCE;

        public static StoreCart getInstance() {
            // Do something before get instance ...
            if (INSTANCE == null) {
                // Do the task too long before create instance ...
                // Block so other threads cannot come into while initialize
                synchronized (StoreCart.class) {
                    // Re-check again. Maybe another thread has initialized before
                    if (INSTANCE == null) {
                        INSTANCE = new StoreCart();
                    }
                }
            }
            // Do something after get instance ...
            return INSTANCE;
        }
    }
}
