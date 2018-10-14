package botelho.afonso.ywait;

public class Restaurant {

    private String name;
    private String desc;
    private String rating;

    public Restaurant(String name, String desc, String rating) {
        this.name = name;
        this.desc = desc;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getRating() {
        return rating;
    }
}
