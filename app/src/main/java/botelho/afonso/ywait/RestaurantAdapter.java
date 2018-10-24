package botelho.afonso.ywait;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private Context mCtx;
    private List<Restaurant> restaurantList;

    public RestaurantAdapter(Context mCtx, List<Restaurant> restaurantList) {
        this.mCtx = mCtx;
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.restaurant_item_layout,  null);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder restaurantViewHolder, final int i) {
        Restaurant restaurant = restaurantList.get(i);

        restaurantViewHolder.nameTextView.setText(restaurant.getName());
        restaurantViewHolder.descTextView.setText(restaurant.getDesc());
        restaurantViewHolder.ratingTextView.setText(restaurant.getRating());
        restaurantViewHolder.imageView.setImageBitmap(BitmapFactory.decodeResource(mCtx.getResources(), getImage(i)));
        restaurantViewHolder.restaurantItemConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showRestaurantDetail = new Intent(mCtx.getApplicationContext(),RestaurantDetail.class);
                showRestaurantDetail.putExtra("botelho.afonso.ywait.RESTAURANT", (Parcelable) restaurantList.get(i));
                /*showRestaurantDetail.putExtra("botelho.afonso.ywait.RESTAURANT_ID", restaurantList.get(i).getId());
                showRestaurantDetail.putExtra("botelho.afonso.ywait.RESTAURANT_NAME", restaurantList.get(i).getName());
                showRestaurantDetail.putExtra("botelho.afonso.ywait.RESTAURANT_DESC", restaurantList.get(i).getDesc());
                showRestaurantDetail.putExtra("botelho.afonso.ywait.RESTAURANT_RATING", restaurantList.get(i).getRating());*/
                mCtx.startActivity(showRestaurantDetail);
            }
        });

    }

    private int getImage(int index) {
        switch (index) {
            case 0: return R.drawable.pizza;
            case 1: return R.drawable.sushi;
            case 2: return R.drawable.bitoque;
            default: return -1;
        }
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameTextView, descTextView, ratingTextView;
        ConstraintLayout restaurantItemConstraintLayout;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            descTextView = (TextView) itemView.findViewById(R.id.descTextView);
            ratingTextView = (TextView) itemView.findViewById(R.id.ratingTextView);
            restaurantItemConstraintLayout = (ConstraintLayout) itemView.findViewById(R.id.restaurantItemConstraintLayout);
        }
    }
}
