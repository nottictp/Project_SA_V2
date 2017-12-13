package controllers;

import javafx.scene.control.DatePicker;
import managers.ManufactorManagerDB;
import managers.MarketingManagerDB;
import managers.WarehouseManagerDB;
import models.Warehouse;
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


    public List<Warehouse> getWarehouseSeed() {
        System.out.println("request warehouse seed");
        List<Warehouse> warehouseSeeds = new ArrayList<Warehouse>();
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
                    String seedId = resultSet.getString("seed_id");
                    String name = resultSet.getString("name");
                    String unit = resultSet.getString("unit");
                    int quantity = resultSet.getInt("quantity");
                    String shelf = resultSet.getString("shelf");
                    String recorder = resultSet.getString("recorder");
                    String recipient = resultSet.getString("recipient");
                    String form = resultSet.getString("form");

                    WarehouseSeed warehouseSeed = new WarehouseSeed(quantity,shelf,  docNo,  name,  unit, docDate, recorder, recipient, form,1,seedId);
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

    public List<Warehouse> getWarehouseProduct() {
        System.out.println("request warehouse product");
        List<Warehouse> warehouseProducts = new ArrayList<Warehouse>();
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
                    String productId = resultSet.getString("product_id");
                    String name = resultSet.getString("name");
                    String unit = resultSet.getString("unit");
                    int quantity = resultSet.getInt("quantity");
                    String shelf = resultSet.getString("shelf");
                    String recorder = resultSet.getString("recorder");
                    String recipient = resultSet.getString("recipient");
                    String form = resultSet.getString("form");

                    WarehouseProduct warehouseProduct = new WarehouseProduct(quantity,shelf,  docNo,  name,  unit, docDate, recorder, recipient, form,2,productId);
                    warehouseProducts.add(warehouseProduct);
                    System.out.println("response");
                    System.out.println("warehouseProduct = " + warehouseProduct);
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
        return warehouseProducts;
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
