package controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteDatabase implements WarehouseManagerDB{
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
                String sql = "select seed_id from warehouse_seed";
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

//    public void addSeed(){
//        List<Seed> seeds = new ArrayList<Seed>();
//        Connection connection = null;
//        connection = prepareConnection();
//        try {
//            if(connection != null){
//                String sql = "select * from seed";
//                Statement statement = connection.createStatement();
//                ResultSet resultSet = statement.executeQuery(sql);
//
//                while (resultSet.next()){
//                    int seedId = resultSet.getInt("seed_id");
//                    String name = resultSet.getString("name");
//                    int quantity = resultSet.getInt("quantity");
//                    double weightPerUnit = resultSet.getDouble("weight_per_unit");
//                    System.out.println(seedId + " "+ name+" "+quantity+" "+weightPerUnit);
//                    Seed seed = new Seed(seedId, name, quantity, weightPerUnit);
//                    seeds.add(seed);
//
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            if (connection != null){
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }


}
