package fpt.edu.cocshop.Model;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable, Parent<MenuItem> {
    private String name;
    private List<MenuItem> items;

    public Menu() {
    }

    public Menu(String name, List<MenuItem> items) {
        this.name = name;
        this.items = items;
    }

    public Menu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<MenuItem> getChildList() {
        return items;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
