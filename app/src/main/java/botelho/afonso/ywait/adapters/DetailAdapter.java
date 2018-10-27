package botelho.afonso.ywait.adapters;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLayoutListener;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;

import java.util.ArrayList;
import java.util.List;

import botelho.afonso.ywait.R;
import botelho.afonso.ywait.models.Detail;
import botelho.afonso.ywait.models.Info;
import botelho.afonso.ywait.models.MenuCategory;
import botelho.afonso.ywait.models.MenuItem;

public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Detail> items;
    Context context;

    public DetailAdapter(List<Detail> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return (position>0)? 1:0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        if(i == 0) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.restaurant_info_layout, viewGroup, false);
            return new InfoViewHolder(view);
        } else {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.menu_category_layout, viewGroup, false);
            return new CategoryViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        switch (holder.getItemViewType()) {
            case 0:
            {
                InfoViewHolder viewHolder = (InfoViewHolder) holder;
                Info info = (Info) items.get(i);
                viewHolder.setIsRecyclable(false);
                viewHolder.nameTextView.setText(info.getRestaurant().getName());
                viewHolder.imageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), getImage(Integer.parseInt(info.getRestaurant().getId()) - 1)));
                viewHolder.descTextView.setText(info.getRestaurant().getDesc());
                viewHolder.ratingTextView.setText(info.getRestaurant().getRating());
            } break;
            case 1:
            {
                final CategoryViewHolder viewHolder = (CategoryViewHolder) holder;
                MenuCategory category = (MenuCategory) items.get(i);
                viewHolder.setIsRecyclable(false);
                viewHolder.categoryTextView.setText(category.getName());
                viewHolder.expandableLayout.setInRecyclerView(false);
                viewHolder.expandableLayout.setListener(new ExpandableLayoutListener() {
                    @Override
                    public void onAnimationStart() {

                    }

                    @Override
                    public void onAnimationEnd() {

                    }

                    @Override
                    public void onPreOpen() {
                        changeRotate(viewHolder.arrowView,0f,180f).start();
                    }

                    @Override
                    public void onPreClose() {
                        changeRotate(viewHolder.arrowView,180f,0f).start();
                    }

                    @Override
                    public void onOpened() {

                    }

                    @Override
                    public void onClosed() {

                    }
                });
                viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewHolder.expandableLayout.toggle();
                        if(viewHolder.arrowView.getRotation() == 180f)
                            viewHolder.arrowView.setRotation(0f);
                        else
                            viewHolder.arrowView.setRotation(180f);
                    }
                });

                viewHolder.menuItemRecyclerView.setHasFixedSize(true);
                viewHolder.layoutManager = new LinearLayoutManager(context);
                viewHolder.menuItemRecyclerView.setLayoutManager(viewHolder.layoutManager);
                getMenuItems(viewHolder.menuItemRecyclerView);

            } break;
            default:
                break;
        }
    }

    private ObjectAnimator changeRotate(View arrowView, float from, float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(arrowView,"rotation",from,to);
        animator.setDuration(100);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private int getImage(int index) {
        switch (index) {
            case 0: return R.drawable.pizza;
            case 1: return R.drawable.sushi;
            case 2: return R.drawable.bitoque;
            default: return -1;
        }
    }

    private void getMenuItems(RecyclerView rv) {
        List<MenuItem> menuItems = new ArrayList<>();

        menuItems.add(new MenuItem("Sopa de Tomate"));
        menuItems.add(new MenuItem("Pizza margarita"));
        menuItems.add(new MenuItem("Bitoque"));

        MenuItemAdapter adapter = new MenuItemAdapter(menuItems);
        rv.setAdapter(adapter);
    }

}

class InfoViewHolder extends RecyclerView.ViewHolder {

    TextView nameTextView, descTextView, ratingTextView;
    ImageView imageView;

    public InfoViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        descTextView = (TextView) itemView.findViewById(R.id.descTextView);
        ratingTextView = (TextView) itemView.findViewById(R.id.ratingTextView);
    }
}

class CategoryViewHolder extends RecyclerView.ViewHolder {

    TextView categoryTextView;
    RelativeLayout relativeLayout;
    View arrowView;
    ExpandableLinearLayout expandableLayout;
    RecyclerView menuItemRecyclerView;
    RecyclerView.LayoutManager layoutManager;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        categoryTextView = (TextView) itemView.findViewById(R.id.categoryTextView);
        relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        expandableLayout = (ExpandableLinearLayout) itemView.findViewById(R.id.expandableLayout);
        arrowView = (View) itemView.findViewById(R.id.arrowView);
        menuItemRecyclerView = (RecyclerView) itemView.findViewById(R.id.menuItemRecyclerView);
    }
}
