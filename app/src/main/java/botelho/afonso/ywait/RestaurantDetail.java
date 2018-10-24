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

    private ImageView imageView;
    private TextView nameTextView;
    private TextView descTextView;
    private TextView ratingTextView;
    private Restaurant restaurant;
    private RecyclerView mRecyclerView;
    private MenuAdapter mAdapter;
    private List<Menu> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        imageView = (ImageView) findViewById(R.id.imageView);
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        descTextView = (TextView) findViewById(R.id.descTextView);
        ratingTextView = (TextView) findViewById(R.id.ratingTextView);

        Intent intent = getIntent();
        restaurant = (Restaurant) intent.getParcelableExtra("botelho.afonso.ywait.RESTAURANT");
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), getImage(Integer.parseInt(restaurant.getId()) - 1)));
        nameTextView.setText(restaurant.getName());
        descTextView.setText(restaurant.getDesc());
        ratingTextView.setText(restaurant.getRating());


        mRecyclerView = (RecyclerView) findViewById(R.id.menuRecyclerView);
        getCategories();
        mAdapter = new MenuAdapter(categories);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(RestaurantDetail.this));
        mRecyclerView.setAdapter(mAdapter);

    }

    private int getImage(int index) {
        switch (index) {
            case 0: return R.drawable.pizza;
            case 1: return R.drawable.sushi;
            case 2: return R.drawable.bitoque;
            default: return -1;
        }
    }

    private void getCategories() {
        categories = new ArrayList<>(3);
        for(int i = 0; i < 3; i++) {
            List<MenuItem> items = new ArrayList<>(3);
            items.add(new MenuItem("Sopa de tomate"));
            items.add(new MenuItem("Nigiri salmão"));
            items.add(new MenuItem("Bitoque"));
            categories.add(new Menu("Category "+i, items));
        }
    }
}
