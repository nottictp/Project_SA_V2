package DatabaseManager;

import Warehouse.WarehouseDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteDatabase implements WarehouseDatabase{
    public String url = "SeedProduct.db";

    private Connection prepareConnection(){
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:" + url;
            Connection conn = DriverManager.getConnection(dbURL);
            return conn;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Integer> getSeedId(){
        List<Integer> seed_ids = new ArrayList<Integer>();
        Connection connection = null;
        connection = prepareConnection();
        try {
            if(connection != null){
                String sql = "select warehouse_seed from seed_id";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    int seed_id = resultSet.getInt("seed_id");
                    seed_ids.add(seed_id);
                    System.out.println("seed_id = " + seed_id);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }return seed_ids;
    }


}
