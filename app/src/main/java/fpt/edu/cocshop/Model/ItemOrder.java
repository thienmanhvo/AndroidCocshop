package fpt.edu.cocshop.Model;

public class ItemOrder {
    private String id;
    private int quantityInCart;
    private long price;
    private long priceOld;
    private String name;

    public ItemOrder() {
    }

    public ItemOrder(String dishItemId, int quantity, long price, long priceOld, String dishItemName) {
        this.id = dishItemId;
        this.quantityInCart = quantity;
        this.price = price;
        this.priceOld = priceOld;
        this.name = dishItemName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantityInCart() {
        return quantityInCart;
    }

    public void setQuantityInCart(int quantityInCart) {
        this.quantityInCart = quantityInCart;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(long priceOld) {
        this.priceOld = priceOld;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
