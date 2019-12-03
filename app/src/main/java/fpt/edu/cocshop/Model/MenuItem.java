package fpt.edu.cocshop.Model;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private String name;
    private String imagePath;
    private long price;

    public MenuItem(String name, String imagePath, long price) {
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
    }

    public MenuItem() {
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
