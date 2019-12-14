package fpt.edu.cocshop.Model;

import java.io.Serializable;

public class MenuDishItem implements Serializable {
    private String name;
    private String imagePath;
    private long price;
    private String id;
    private long priceOld;
   // private int quantityInCart;

    public MenuDishItem(String name, String imagePath, long price) {
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
        priceOld = (long) (price * 0.8);
        //quantityInCart = 0;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(long priceSale) {
        this.priceOld = priceSale;
    }

//    public int getQuantityInCart() {
//        return quantityInCart;
//    }

//    public void setQuantityInCart(int quantityInCart) {
//        this.quantityInCart = quantityInCart;
//    }

    public MenuDishItem() {
        //priceSale = (long) (price * 0.8);
      //  quantityInCart = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
