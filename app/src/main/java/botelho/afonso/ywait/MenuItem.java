package botelho.afonso.ywait;

public class MenuItem {
    private String name;
    /*private String id;
    private String category;
    private String subCategory;
    private float price;
    private int prepareTime;
    private String rating;
    private String foto;

    public MenuItem(String id, String name, String category, String subCategory, float price, int prepareTime, String rating, String foto) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.subCategory = subCategory;
        this.price = price;
        this.prepareTime = prepareTime;
        this.rating = rating;
        this.foto = foto;
    }

    public String getId() {
        return id;
    }*/

    public MenuItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /*public String getCategory() {
        return category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public float getPrice() {
        return price;
    }

    public int getPrepareTime() {
        return prepareTime;
    }

    public String getRating() {
        return rating;
    }

    public String getFoto() {
        return foto;
    }*/
}
