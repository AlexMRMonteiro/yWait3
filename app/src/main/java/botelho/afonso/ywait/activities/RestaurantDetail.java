package botelho.afonso.ywait.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import botelho.afonso.ywait.R;
import botelho.afonso.ywait.adapters.DetailAdapter;
import botelho.afonso.ywait.models.Detail;
import botelho.afonso.ywait.models.Info;
import botelho.afonso.ywait.models.MenuCategory;
import botelho.afonso.ywait.models.Restaurant;

public class RestaurantDetail extends AppCompatActivity {

    private Restaurant restaurant;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private List<Detail> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        Intent intent = getIntent();
        restaurant = (Restaurant) intent.getParcelableExtra("botelho.afonso.ywait.RESTAURANT");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getCategories();
    }

    private void getCategories() {
        Detail info = new Info(restaurant);
        items.add(info);

        items.add((Detail) new MenuCategory("Entradas"));
        items.add((Detail) new MenuCategory("Sopas"));
        items.add((Detail) new MenuCategory("Peixe"));
        items.add((Detail) new MenuCategory("Massa"));
        items.add((Detail) new MenuCategory("Sobremesa"));
        items.add((Detail) new MenuCategory("Bebidas"));


        DetailAdapter adapter = new DetailAdapter(items);
        recyclerView.setAdapter(adapter);
    }
}
