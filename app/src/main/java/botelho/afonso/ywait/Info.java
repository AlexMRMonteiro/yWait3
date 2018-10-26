package botelho.afonso.ywait;

public class Info extends Detail {
    private Restaurant restaurant;

    public Info(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
