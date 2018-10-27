package botelho.afonso.ywait.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import botelho.afonso.ywait.R;
import botelho.afonso.ywait.models.MenuItem;

public class MenuItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<MenuItem> items;
    Context context;

    public MenuItemAdapter(List<MenuItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.menu_item_layout, viewGroup, false);
        return new MenuItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        MenuItemViewHolder viewHolder = (MenuItemViewHolder) holder;

        viewHolder.nameTextView.setText(items.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class MenuItemViewHolder extends RecyclerView.ViewHolder {

    TextView nameTextView;

    public MenuItemViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
    }
}
