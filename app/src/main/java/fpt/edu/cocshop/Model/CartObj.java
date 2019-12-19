package fpt.edu.cocshop.Model;

import java.io.Serializable;
import java.util.HashMap;

public class CartObj implements Serializable {

    private String customerEmail;
    private HashMap<String, ItemOrder> cart;
    private long totalQuantity;
    private long totalPrice;
    private Double discount;


    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

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
    }

    public CartObj() {
        this.customerEmail = "Guest";
        this.cart = new HashMap<>();
        this.totalPrice = 0;
        this.totalQuantity = 0;
    }

    public void addToCart(ItemOrder dto, int sign) {
        int quantity = 0;
        if (this.cart.containsKey(dto.getId())) {
            quantity = this.cart.get(dto.getId()).getQuantityInCart() + (1 * sign);
            if (quantity == 0) {
                this.cart.remove(dto.getId());
                totalQuantity = totalQuantity + sign;
                totalPrice = totalPrice + (dto.getPrice() * sign);
                return;
            }
        } else {
            quantity = 1;
        }
        dto.setQuantityInCart(quantity);
        totalQuantity = totalQuantity + sign;
        totalPrice = totalPrice + (dto.getPrice() * sign);
        this.cart.put(dto.getId(), dto);
    }


    public long getTotalQuantity() {
        return totalQuantity;
    }

    public long getTotalPrice() {
        return totalPrice;
    }



    public void setTotalQuantity(long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

}
