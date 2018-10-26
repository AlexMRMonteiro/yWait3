package botelho.afonso.ywait;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLayoutListener;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;

import java.util.List;

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
                viewHolder.expandableLayout.setInRecyclerView(true);
                viewHolder.expandableLayout.setListener(new ExpandableLayoutListener() {
                    @Override
                    public void onAnimationStart() {

                    }

                    @Override
                    public void onAnimationEnd() {

                    }

                    @Override
                    public void onPreOpen() {

                    }

                    @Override
                    public void onPreClose() {

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
                    }
                });

            } break;
            default:
        }
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

    TextView categoryTextView, menuItemTextView;
    RelativeLayout relativeLayout;
    ExpandableLinearLayout expandableLayout;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        categoryTextView = (TextView) itemView.findViewById(R.id.categoryTextView);
        menuItemTextView = (TextView) itemView.findViewById(R.id.menuItemTextView);
        relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        expandableLayout = (ExpandableLinearLayout) itemView.findViewById(R.id.expandableLayout);
    }
}
