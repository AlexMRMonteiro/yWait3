package botelho.afonso.ywait.models;

import java.util.ArrayList;
import java.util.List;

public class MenuCategory extends Detail {
    private String name;
    private List<MenuItem> items;

    public MenuCategory(String name) {
        this.name = name;
        items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<MenuItem> getItems() {
        return items;
    }
}
