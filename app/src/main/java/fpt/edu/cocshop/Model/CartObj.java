package fpt.edu.cocshop.Model;

import java.io.Serializable;
import java.util.HashMap;

public class CartObj implements Serializable {

    private String customerEmail;
    private HashMap<String, ItemOrder> cart;
    private long totalQuantity;
    private long totalPrice;
    private long totalPriceOld;

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public HashMap<String, ItemOrder> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, ItemOrder> cart) {
        this.cart = cart;
    }

    public CartObj(String customerEmail) {
        this.customerEmail = customerEmail;
        this.cart = new HashMap<>();
        this.totalPrice = 0;
        this.totalQuantity = 0;
        this.totalPriceOld = 0;
    }

    public CartObj() {
        this.customerEmail = "Guest";
        this.cart = new HashMap<>();
        this.totalPrice = 0;
        this.totalQuantity = 0;
        this.totalPriceOld = 0;
    }

    public void addToCart(ItemOrder dto, int sign) {
        int quantity = 0;
        if (this.cart.containsKey(dto.getId())) {
            quantity = this.cart.get(dto.getId()).getQuantityInCart() + (1 * sign);
            if (quantity == 0) {
                this.cart.remove(dto.getId());
            }
        } else {
            quantity = 1;
        }
        dto.setQuantityInCart(quantity);
        totalQuantity = totalQuantity + sign;
        totalPrice = totalPrice + (dto.getPrice() * sign);
        totalPriceOld = totalPrice + (dto.getPriceOld() * sign);
        this.cart.put(dto.getId(), dto);
    }

//    public void removeCart(String id) {
//        if (this.cart.containsKey(id)) {
//            totalQuantity = totalQuantity - this.cart.get(id).getQuantityInCart();
//            totalPrice = totalPrice - this.cart.get(id).getPrice();
//            totalPriceOld = totalPriceOld - this.cart.get(id).getPriceOld();
//            this.cart.remove(id);
//        }
//    }

//    public float getTotalPrice() throws Exception {
//        float result = 0;
//        for (ItemOrder dto : this.cart.values()) {
//            result += (dto.getQuantity() * dto.getPrice());
//        }
//        return result;
//    }
//
//    public float getTotalQuantity() throws Exception {
//        float result = 0;
//        for (ItemOrder dto : this.cart.values()) {
//            result += dto.getQuantity();
//        }
//        return result;
//    }
//
//    public float getTotalPriceOld() throws Exception {
//        float result = 0;
//        for (ItemOrder dto : this.cart.values()) {
//            result += dto.getQuantity();
//        }
//        return result;
//    }

//    public void add(ItemOrder dto) throws Exception {
//        if (this.cart.containsKey(dto.getDishItemId())) {
//            int quantity = this.cart.get(dto.getDishItemId()).getQuantity() + 1;
//            dto.setQuantity(quantity);
//        }
//        this.cart.put(dto.getDishItemId(), dto);
//    }
//
//    public void remove(String id) throws Exception {
//        if (this.cart.containsKey(id)) {
//            this.cart.remove(id);
//        }
//    }

    public long getTotalQuantity() {
        return totalQuantity;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public long getTotalPriceOld() {
        return totalPriceOld;
    }

    public void setTotalQuantity(long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTotalPriceOld(long totalPriceOld) {
        this.totalPriceOld = totalPriceOld;
    }

//    public float getTotall() throws Exception {
//        float result = 0;
//        for (ItemOrder ItemOrder : this.cart.values()) {
//            result += (ItemOrder.getQuantity()) * ItemOrder.getPrice();
//        }
//        return result;
//
//    }
}
