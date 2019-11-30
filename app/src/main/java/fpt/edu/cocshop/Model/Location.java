package fpt.edu.cocshop.Model;

import java.io.Serializable;

public class Location implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location(String name) {
        this.name = name;
    }
}
