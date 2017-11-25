package controllers;

import javafx.scene.control.DatePicker;
import managers.ManufactorManagerDB;
import managers.MarketingManagerDB;
import managers.WarehouseManagerDB;
import models.WarehouseProduct;
import models.WarehouseSeed;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteDatabase implements WarehouseManagerDB, ManufactorManagerDB, MarketingManagerDB {
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

    public List<WarehouseSeed> getWarehouseSeed() {
        System.out.println("request warehouse seed");
        List<WarehouseSeed> warehouseSeeds = new ArrayList<WarehouseSeed>();
        Connection connection = null;
        try {
        connection = prepareConnection();
        if(connection != null){

                String sql = "select * from warehouse_seed";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()){
                    int docNo = resultSet.getInt("doc_no");
                    String docDate = resultSet.getString("doc_date");
                    int seedId = resultSet.getInt("seed_id");
                    String name = resultSet.getString("name");
                    String unit = resultSet.getString("unit");
                    int quantity = resultSet.getInt("quantity");
                    int shelf = resultSet.getInt("shelf");
                    String recorder = resultSet.getString("recorder");
                    String recipient = resultSet.getString("recipient");
                    String form = resultSet.getString("form");

                    WarehouseSeed warehouseSeed = new WarehouseSeed(docNo, docDate, seedId, name, unit
                            , quantity, shelf, recorder, recipient, form);
                    warehouseSeeds.add(warehouseSeed);
                    System.out.println("response");
                    System.out.println("warehouseSeed = " + warehouseSeed);
                }
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }return warehouseSeeds;
    }

    public WarehouseProduct getWarehouseProduct() {
        System.out.println("request warehouse product");

        Connection connection = null;
        try {
            connection = prepareConnection();
            if(connection != null){

                String sql = "select * from warehouse_product";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()){
                    int docNo = resultSet.getInt("doc_no");
                    String docDate = resultSet.getString("doc_date");
                    int productId = resultSet.getInt("product_id_id");
                    String name = resultSet.getString("name");
                    String unit = resultSet.getString("unit");
                    int quantity = resultSet.getInt("quantity");
                    int shelf = resultSet.getInt("shelf");
                    String recorder = resultSet.getString("recorder");
                    String recipient = resultSet.getString("recipient");
                    String form = resultSet.getString("form");

                    WarehouseProduct warehouseProduct = new WarehouseProduct(docNo, docDate, productId, name, unit
                            , quantity, shelf, recorder, recipient, form);
                    System.out.println("response");
                    System.out.println("warehouseProduct = " + warehouseProduct);
                    return warehouseProduct;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
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
