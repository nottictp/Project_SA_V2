package controllers;

import javafx.scene.control.DatePicker;
import managers.ManufactorManagerDB;
import managers.MarketingManagerDB;
import managers.WarehouseManagerDB;
import models.*;
import views.MarketingController;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public DataToMarketing getSeedRatio(String productId){
        System.out.println("Request for seed ratio");

        Connection connection = null;
        try {
            connection = prepareConnection();
            if(connection != null) {
                String sql = "select product_id, ratio, fname, fquantity, mname, mquantity\n" +
                        "from product\n" +
                        "join seed_ratio\n" +
                        "on product.seed_id=seed_ratio.seed_id\n" +
                        "join (select seed.seed_id as fid, quantity as fquantity, seed.name as fname\n" +
                        "\t\tfrom Seed_ratio\n" +
                        "\t\tjoin seed\n" +
                        "\t\ton seed_ratio.father_id=seed.seed_id) as fqq\n" +
                        "on Seed_ratio.father_id=fqq.fid\n" +
                        "join (select seed.seed_id as mid, quantity as mquantity, seed.name as mname\n" +
                        "\t\tfrom Seed_ratio\n" +
                        "\t\tjoin seed\n" +
                        "\t\ton seed_ratio.mother_id=seed.seed_id) as mqq\n" +
                        "on Seed_ratio.mother_id=mqq.mid\n" +
                        "where product_id=\"" + productId + "\"";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                System.out.println(sql);
                if(resultSet.next()){
                    System.out.println("เข้า if มั้ย");
                    String ratio = resultSet.getString("ratio");
                    int fatherQuantity = resultSet.getInt("fquantity");
                    int motherQuantity = resultSet.getInt("mquantity");
                    String fatherName = resultSet.getString("fname");
                    String motherName = resultSet.getString("mname");
                    DataToMarketing dataToMarketing = new DataToMarketing(ratio,fatherQuantity,motherQuantity,fatherName,motherName);

                    return dataToMarketing;
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
        }return null;
    }

    public List<Warehouse> getWarehouseSeedName(String name){
        System.out.println("request warehouse seed name");
        List<Warehouse> warehouseSeedNames = new ArrayList<Warehouse>();
        Connection connection = null;
        try {
            connection = prepareConnection();
            if(connection != null){
                String sql = "select * " +
                        "from warehouse_seed "+
                        "where name like '%"+name+"%'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()){
                    int docNo = resultSet.getInt("doc_no");
                    String docDate = resultSet.getString("doc_date");
                    String unit = resultSet.getString("unit");
                    String seedId = resultSet.getString("seed_id");
                    String name2 = resultSet.getString("name");
                    int quantity = resultSet.getInt("quantity");
                    String shelf = resultSet.getString("shelf");
                    String recorder = resultSet.getString("recorder");
                    String recipient = resultSet.getString("recipient");
                    String form = resultSet.getString("form");

                    WarehouseSeed seed = new WarehouseSeed(quantity,shelf,docNo,name2,unit,docDate,recorder,recipient,form,1,seedId);
                    warehouseSeedNames.add(seed);
                    System.out.println("response warehouse seed name");
                    System.out.println("w = " + seed);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }return warehouseSeedNames;
    }

    public List<Warehouse> getWarehouseSeedId(String id) {
        System.out.println("request warehouse seed id");
        List<Warehouse> warehouseSeedIds = new ArrayList<Warehouse>();
        Connection connection = null;
        try {
            connection = prepareConnection();
            if(connection != null){
                String sql = "select * " +
                        "from warehouse_seed "+
                        "where seed_id like '%"+id+"%'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()){
                    int docNo = resultSet.getInt("doc_no");
                    String docDate = resultSet.getString("doc_date");
                    String unit = resultSet.getString("unit");
                    String seedId = resultSet.getString("seed_id");
                    String name2 = resultSet.getString("name");
                    int quantity = resultSet.getInt("quantity");
                    String shelf = resultSet.getString("shelf");
                    String recorder = resultSet.getString("recorder");
                    String recipient = resultSet.getString("recipient");
                    String form = resultSet.getString("form");

                    WarehouseSeed seed = new WarehouseSeed(quantity,shelf,docNo,name2,unit,docDate,recorder,recipient,form,1,seedId);
                    warehouseSeedIds.add(seed);
                    System.out.println("response warehouse seed id");
                    System.out.println("w = " + seed);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }return warehouseSeedIds;
    }

    public List<Warehouse> getWarehouseProductName(String name) {
        System.out.println("request warehouse product name");
        List<Warehouse> warehouseProductNames = new ArrayList<Warehouse>();
        Connection connection = null;
        try {
            connection = prepareConnection();
            if(connection != null){
                String sql = "select * " +
                        "from warehouse_product "+
                        "join product on warehouse_product.product_id = product.product_id"+
                        "where name like '%"+name+"%'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()){
                    int docNo = resultSet.getInt("doc_no");
                    String docDate = resultSet.getString("doc_date");
                    String unit = resultSet.getString("unit");
                    String productId = resultSet.getString("product_id");
                    String name2 = resultSet.getString("name");
                    int quantity = resultSet.getInt("quantity");
                    String shelf = resultSet.getString("shelf");
                    String recorder = resultSet.getString("recorder");
                    String recipient = resultSet.getString("recipient");
                    String form = resultSet.getString("form");
                    double capacity = resultSet.getDouble("contain");

                    WarehouseProduct product = new WarehouseProduct(quantity,shelf,docNo,name2,unit,docDate,recorder,recipient,form,1,productId);
                    warehouseProductNames.add(product);
                    System.out.println("response warehouse product name");
                    System.out.println("w = " + product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }return warehouseProductNames;
    }

    public List<Warehouse> getWarehouseProductId(String id) {
        System.out.println("request warehouse product id");
        List<Warehouse> warehouseProductIds = new ArrayList<Warehouse>();
        Connection connection = null;
        try {
            connection = prepareConnection();
            if(connection != null){
                String sql = "select * " +
                        "from warehouse_product "+
                        "where product_id like '%"+id+"%'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()){
                    int docNo = resultSet.getInt("doc_no");
                    String docDate = resultSet.getString("doc_date");
                    String unit = resultSet.getString("unit");
                    String productId = resultSet.getString("product_id");
                    String name2 = resultSet.getString("name");
                    int quantity = resultSet.getInt("quantity");
                    String shelf = resultSet.getString("shelf");
                    String recorder = resultSet.getString("recorder");
                    String recipient = resultSet.getString("recipient");
                    String form = resultSet.getString("form");

                    WarehouseProduct product = new WarehouseProduct(quantity,shelf,docNo,name2,unit,docDate,recorder,recipient,form,1,productId);
                    warehouseProductIds.add(product);
                    System.out.println("response warehouse product id");
                    System.out.println("w = " + product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }return warehouseProductIds;
    }

    public void insertToWarehouseSeed(WarehouseSeed w) {
        System.out.println("Insert to Warehouse seed");
        Connection connection = null;
        try{
            connection = prepareConnection();
            if(connection != null){
                String sql = String.format("insert into warehouse_seed "+
                        "values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                        w.getDocNo()+"", w.getDocDate(), w.getSeedId(), w.getName(), w.getUnit(),
                        w.getQuantity()+"", w.getShelf(), w.getRecorder(), w.getRecipient(), w.getForm());
                Statement statement = connection.createStatement();
                int result = statement.executeUpdate(sql);
                System.out.println(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void insertToWarehouseProduct(WarehouseProduct w) {
        System.out.println("Insert to Warehouse product");
        Connection connection = null;
        try{
            connection = prepareConnection();
            if(connection != null){
                String sql = String.format("insert into warehouse_product "+
                                "values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                        w.getDocNo()+"", w.getDocDate(), w.getProductId(), w.getName(), w.getUnit(),
                        w.getQuantity()+"", w.getShelf(), w.getRecorder(), w.getRecipient(), w.getForm());
                Statement statement = connection.createStatement();
                int result = statement.executeUpdate(sql);
                System.out.println(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void UpdateWarehouseSeed(WarehouseSeed warehouse) {

    }

    public void UpdateWarehouseProduct(WarehouseProduct warehouse) {

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
