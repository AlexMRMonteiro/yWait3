package botelho.afonso.ywait.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import botelho.afonso.ywait.R;
import botelho.afonso.ywait.adapters.DetailAdapter;
import botelho.afonso.ywait.models.Detail;
import botelho.afonso.ywait.models.Info;
import botelho.afonso.ywait.models.MenuCategory;
import botelho.afonso.ywait.models.MenuItem;
import botelho.afonso.ywait.models.Restaurant;

public class RestaurantDetail extends AppCompatActivity {

    private Restaurant restaurant;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Detail> items = new ArrayList<>();
    private DetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        Intent intent = getIntent();
        restaurant = (Restaurant) intent.getParcelableExtra("botelho.afonso.ywait.RESTAURANT");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DetailAdapter(items);
        getCategories();
    }

    private void getCategories() {
        Detail info = new Info(restaurant);
        items.add(info);

        /*items.add((Detail) new MenuCategory("Entradas"));
        items.add((Detail) new MenuCategory("Sopas"));
        items.add((Detail) new MenuCategory("Peixe"));
        items.add((Detail) new MenuCategory("Massa"));
        items.add((Detail) new MenuCategory("Sobremesa"));
        items.add((Detail) new MenuCategory("Bebidas"));*/


        GetData getData = new GetData();
        getData.execute();
    }

    private class GetData extends AsyncTask<String,String,String> {

        String msg = "";
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        static final String DB_URL = "jdbc:mysql://" + DbStrings.DATABASE_URL + "/" + DbStrings.DATABASE_NAME;


        @Override
        protected String doInBackground(String... strings) {

            Connection conn = null;
            PreparedStatement stmt1 = null;
            PreparedStatement stmt2 = null;

            try {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, DbStrings.USERNAME, DbStrings.PASSWORD);

                // Get categories
                stmt1 = conn.prepareStatement("SELECT distinct category FROM menu_item WHERE restaurant_id=?");
                stmt1.setString(1,restaurant.getId());

                ResultSet rs1 = stmt1.executeQuery();
                while(rs1.next()) {
                    String category = rs1.getString("category");
                    MenuCategory mc = new MenuCategory(category);

                    stmt2 = conn.prepareStatement("SELECT * FROM menu_item WHERE restaurant_id=? AND category =?");
                    stmt2.setString(1, restaurant.getId());
                    stmt2.setString(2, category);
                    ResultSet rs2 = stmt2.executeQuery();
                    while(rs2.next()) {
                        String itemName = rs2.getString("name");
                        mc.getItems().add(new MenuItem(itemName));
                    }

                    items.add((Detail) mc);
                    stmt2.close();
                    rs2.close();
                }

                msg = "Process complete.";
                rs1.close();
                stmt1.close();
                conn.close();

            } catch (SQLException connError) {
                msg = "An exception was thrown for JDBC.";
                connError.printStackTrace();
            } catch (ClassNotFoundException e) {
                msg = "A class not found exception was thrown.";
                e.printStackTrace();
            } finally {
                try {
                    if(stmt1 != null){
                        stmt1.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String msg) {
            recyclerView.setAdapter(adapter);
        }
    }
}

