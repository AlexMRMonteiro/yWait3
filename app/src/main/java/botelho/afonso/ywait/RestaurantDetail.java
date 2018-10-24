package botelho.afonso.ywait;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RestaurantDetail extends AppCompatActivity {

    private ImageView imageView;
    private TextView nameTextView;
    private TextView descTextView;
    private TextView ratingTextView;
    private Restaurant restaurant;

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

        /*String id = intent.getStringExtra("botelho.afonso.ywait.RESTAURANT_ID");
        String name = intent.getStringExtra("botelho.afonso.ywait.RESTAURANT_NAME");
        String desc = intent.getStringExtra("botelho.afonso.ywait.RESTAURANT_DESC");
        String rating = intent.getStringExtra("botelho.afonso.ywait.RESTAURANT_RATING");
        nameTextView.setText(name);
        descTextView.setText(desc);
        ratingTextView.setText(rating);*/
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), getImage(Integer.parseInt(restaurant.getId()) - 1)));
        nameTextView.setText(restaurant.getName());
        descTextView.setText(restaurant.getDesc());
        ratingTextView.setText(restaurant.getRating());

    }

    private int getImage(int index) {
        switch (index) {
            case 0: return R.drawable.pizza;
            case 1: return R.drawable.sushi;
            case 2: return R.drawable.bitoque;
            default: return -1;
        }
    }
}
