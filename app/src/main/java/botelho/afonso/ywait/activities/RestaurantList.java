package botelho.afonso.ywait.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import botelho.afonso.ywait.R;
import botelho.afonso.ywait.adapters.RestaurantAdapter;
import botelho.afonso.ywait.models.Restaurant;

public class RestaurantList extends AppCompatActivity {

    private RecyclerView restaurantRecyclerView;
    private RestaurantAdapter restaurantAdapter;
    List<Restaurant> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        restaurantList = new ArrayList<>();
        restaurantRecyclerView = (RecyclerView) findViewById(R.id.restaurantRecyclerView);
        restaurantRecyclerView.setHasFixedSize(true);
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        restaurantAdapter = new RestaurantAdapter(this, restaurantList);
        GetData retrieveData = new GetData();
        retrieveData.execute();

    }

    private class GetData extends AsyncTask<String,String,String> {

        String msg = "";
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        static final String DB_URL = "jdbc:mysql://" + DbStrings.DATABASE_URL + "/" + DbStrings.DATABASE_NAME;


        @Override
        protected String doInBackground(String... strings) {

            Connection conn = null;
            Statement stmt = null;

            try {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, DbStrings.USERNAME, DbStrings.PASSWORD);

                stmt = conn.createStatement();

                // *** SQL QUERY ***
                String sql = "SELECT * FROM restaurant;";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()) {
                    String id = rs.getString("restaurant_id");
                    String name = rs.getString("restaurant_name");
                    String desc = rs.getString("restaurant_desc");
                    String rating = String.valueOf(rs.getBigDecimal("restaurant_rating"));
                    restaurantList.add(new Restaurant(id, name, desc, rating));
                }

                msg = "Process complete.";
                rs.close();
                stmt.close();
                conn.close();

            } catch (SQLException connError) {
                msg = "An exception was thrown for JDBC.";
                connError.printStackTrace();
            } catch (ClassNotFoundException e) {
                msg = "A class not found exception was thrown.";
                e.printStackTrace();
            } finally {
                try {
                    if(stmt != null){
                        stmt.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String msg) {
            restaurantRecyclerView.setAdapter(restaurantAdapter);
        }
    }
}
