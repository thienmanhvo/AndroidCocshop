package fpt.edu.cocshop.Model;

import java.io.Serializable;
import java.util.List;

public class Brand implements Serializable {
    private String name;
    private List<Location> location;
    private String picturePath;
    private float rating;

    public Brand(String name, List<Location> location, String picturePath) {
        this.name = name;
        this.location = location;
        this.picturePath = picturePath;
    }

    public Brand(String name, List<Location> location, String picturePath, float rating) {
        this.name = name;
        this.location = location;
        this.picturePath = picturePath;
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
