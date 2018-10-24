package botelho.afonso.ywait;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class MenuViewHolder extends GroupViewHolder {

    private TextView categoryNameTextView;

    public MenuViewHolder(View itemView) {
        super(itemView);
        categoryNameTextView = (TextView) itemView.findViewById(R.id.categoryNameTextView);
    }

    public void setCategoryName(String categoryName) {
        categoryNameTextView.setText(categoryName);
    }
}
