package botelho.afonso.ywait;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class MenuItemViewHolder extends ChildViewHolder {

    private TextView itemNameTextView;

    public MenuItemViewHolder(View itemView) {
        super(itemView);
        itemNameTextView = (TextView) itemView.findViewById(R.id.itemNameTextView);
    }

    public void setItemName(String itemName) {
        itemNameTextView.setText(itemName);
    }
}
