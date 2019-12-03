package fpt.edu.cocshop.Model;

import java.io.Serializable;

public class Menu implements Serializable {
    private String name;

    public Menu() {
    }

    public Menu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
