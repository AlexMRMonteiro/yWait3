package botelho.afonso.ywait;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetail extends AppCompatActivity {

    /*private ImageView imageView;
    private TextView nameTextView;
    private TextView descTextView;
    private TextView ratingTextView;*/
    private Restaurant restaurant;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private List<Detail> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        /*imageView = (ImageView) findViewById(R.id.imageView);
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        descTextView = (TextView) findViewById(R.id.descTextView);
        ratingTextView = (TextView) findViewById(R.id.ratingTextView);*/

        Intent intent = getIntent();
        restaurant = (Restaurant) intent.getParcelableExtra("botelho.afonso.ywait.RESTAURANT");
        /*imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), getImage(Integer.parseInt(restaurant.getId()) - 1)));
        nameTextView.setText(restaurant.getName());
        descTextView.setText(restaurant.getDesc());
        ratingTextView.setText(restaurant.getRating());*/

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
