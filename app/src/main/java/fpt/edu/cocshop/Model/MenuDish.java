package fpt.edu.cocshop.Model;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.io.Serializable;
import java.util.List;

public class MenuDish implements Serializable, Parent<MenuDishItem> {
    private String name;
    private List<MenuDishItem> items;
    private String id;

    public MenuDish() {
    }

    public MenuDish(String name, List<MenuDishItem> items) {
        this.name = name;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MenuDish(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<MenuDishItem> getItems() {
        return items;
    }

    public void setItems(List<MenuDishItem> items) {
        this.items = items;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<MenuDishItem> getChildList() {
        return items;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
