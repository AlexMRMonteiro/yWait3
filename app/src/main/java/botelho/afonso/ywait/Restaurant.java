package botelho.afonso.ywait;

public class Restaurant {

    private String id;
    private String name;
    private String desc;
    private String rating;

    public Restaurant(String id, String name, String desc, String rating) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.rating = rating;
    }

    public String getId() { return id; }

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
