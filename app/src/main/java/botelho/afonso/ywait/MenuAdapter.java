package botelho.afonso.ywait;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;
import java.util.MissingResourceException;

public class MenuAdapter extends ExpandableRecyclerViewAdapter<MenuViewHolder,MenuItemViewHolder> {


    public MenuAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public MenuViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_menu_category, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public MenuItemViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_menu_item, parent, false);
        return new MenuItemViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(MenuItemViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        MenuItem item = (MenuItem) group.getItems().get(childIndex);

        holder.setItemName(item.getName());
    }

    @Override
    public void onBindGroupViewHolder(MenuViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setCategoryName(group.getTitle());
    }
}
