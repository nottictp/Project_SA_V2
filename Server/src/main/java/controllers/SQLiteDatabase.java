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

                    WarehouseProduct warehouseProduct = new WarehouseProduct(quantity,shelf,  docNo,  name,  unit, docDate, recorder, recipient, form,2,productId,0);
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
                        "join product on warehouse_product.product_id = product.product_id "+
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

                    WarehouseProduct product = new WarehouseProduct(quantity,shelf,docNo,name2,unit,docDate,recorder,recipient,form,1,productId,capacity);
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
                        "join product on warehouse_product.product_id = product.product_id "+
                        "where product_id like '%"+id+"%'";
                System.out.println("sql "+sql);
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

                    WarehouseProduct product = new WarehouseProduct(quantity,shelf,docNo,name2,unit,docDate,recorder,recipient,form,1,productId,capacity);
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

    public void insertToWarehouseSeed(WarehouseSeed w) throws Exception {
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
            throw e;
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

                System.out.println("sql = " + sql);
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

    public void updateWarehouseSeed(WarehouseSeed warehouse) {
        System.out.println("Update warehouse seed quantity");
        Connection connection = null;
        try{
            connection = prepareConnection();
            if(connection != null){
                String docNo = warehouse.getDocNo() + "";
                String sId = warehouse.getSeedId()+"";
                String quantity = warehouse.getQuantity() + "";
                String sql = "update warehouse_seed " +
                            "set quantity=((select quantity " +
                                            "from warehouse_seed " +
                                            "where seed_id='"+sId+"')-"+quantity+") " +
                            "where seed_id='"+sId+"'";
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

    public void updateWarehouseProduct(WarehouseProduct warehouse) {
        System.out.println("Update warehouse product quantity");
        Connection connection = null;
        try{
            connection = prepareConnection();
            if(connection != null){
                String docNo = warehouse.getDocNo() + "";
                String pId = warehouse.getProductId()+"";
                String quantity = warehouse.getQuantity() + "";
                String sql = "update warehouse_product " +
                        "set quantity=((select quantity " +
                        "from warehouse_product " +
                        "where product_id='"+pId+"')-"+quantity+") " +
                        "where product_id='"+pId+"'";
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

    public Map<String, Double> getGroupArea(){
        System.out.println("get group area");
        Map<String, Double> areas = new HashMap<String, Double>();
        Connection connection = null;
        try{
            connection = prepareConnection();
            if(connection != null){
                String sql = "select farmer.group_farmer as \"group\", sum(farmer.capacity_area) as areas\n" +
                        "from farmer\n" +
                        "group by farmer.group_farmer";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    String group = resultSet.getString("group");
                    double area = resultSet.getDouble("areas");
                    areas.put(group, area);
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
        }
        return areas;
    }

    public List<Farmer> getGroupFarmer(String group){
        System.out.println("get group farmer");
        List<Farmer> farmers = new ArrayList<Farmer>();
        Connection connection = null;
        try{
            connection = prepareConnection();
            if(connection != null){
                String sql = "select *\n" +
                        "from farmer\n" +
                        "where group_farmer = '" + group + "'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    int farmerId = resultSet.getInt("farmer_id");
                    int identityId = resultSet.getInt("identity_id");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String address = resultSet.getString("address");
                    String phoneNo = resultSet.getString("phonenumber");
                    Double capacityArea = resultSet.getDouble("capacity_area");
                    String status = resultSet.getString("production_status");
                    String groupFarmer = resultSet.getString("group_farmer");

                    Farmer farmer = new Farmer(farmerId,identityId,name,surname,address,phoneNo,status,groupFarmer,capacityArea);

                    farmers.add(farmer);
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
        }
        return farmers;
    }

    public List<Seed> getSeed(){
        System.out.println("get seeds");
        List<Seed> seeds = new ArrayList<Seed>();
        Connection connection = null;
        try{
            connection = prepareConnection();
            if(connection != null){
                String sql = "select *\n" +
                        "from seed\n" +
                        "where not name like '%พ่อ' and not name like '%แม่'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    int id = resultSet.getInt("seed_id");
                    String name = resultSet.getString("name");
                    int quantity = resultSet.getInt("quantity");
                    double wpu = resultSet.getDouble("weight_per_unit");
                    double upa = resultSet.getDouble("unit_per_area");
                    Seed seed = new Seed(id, quantity, name, wpu, upa);
                    seeds.add(seed);
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
        }
        return seeds;
    }


    public void insertIdFarmer(List<Farmer> farmers, Seed seed, int quantity) {
        Connection connection = null;
        try{
            connection = prepareConnection();
            if(connection != null){
                String sql1 = String.format("insert into Seed_lot(seed_id, quantity)\n" +
                                            "values (%d, %d)", seed.getSeedID(), quantity);
                Statement statement = connection.createStatement();
                int result = statement.executeUpdate(sql1);
                if (result != 0){
                    for (Farmer farmer : farmers){
                        String sql2 = String.format("insert into Producer(lot_id, farmer_id)\n" +
                                "values ((select max(lot_id) from Seed_lot), %d)", farmer.getFarmer_id());
                        result = statement.executeUpdate(sql2);
                    }
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
        }
    }

    public List<Integer> getLotIdNotQuantity() {
        List<Integer> lotIds = new ArrayList<>();
        Connection connection = null;
        try{
            connection = prepareConnection();
            if(connection != null){
                String sql = "select lot_id\n" +
                        "from producer\n" +
                        "where quantity is null\n" +
                        "group by lot_id";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next())
                    lotIds.add(resultSet.getInt("lot_id"));
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
        return lotIds;
    }

    @Override
    public void insertSeedLot(List<Producer> producers, String expire, String plantDate, String harvestDate, String testDate) {
        Connection connection = null;
        try{
            connection = prepareConnection();
            if(connection != null){
                String sql1 = String.format("update seed_lot\n" +
                                            "set expire=\"%s\", plant_date=\"%s\", harvest_date=\"%s\", test_date=\"%s\"\n" +
                                            "where lot_id=%d", expire, plantDate, harvestDate, testDate, producers.get(0).getLot_id());
                Statement statement = connection.createStatement();
                int result = statement.executeUpdate(sql1);
                if (result != 0){
                    for(Producer producer : producers){
                        String sql2 = "update producer " +
                                    "set quantity="+producer.getQualtity()+
                                    " where lot_id="+producer.getLot_id()+" and farmer_id="+producer.getFarmer_id();
                        result = statement.executeUpdate(sql2);
                    }
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
        }
    }


    @Override
    public List<Producer> getProducer(int lotId) {
        List<Producer> producers = new ArrayList<>();
        Connection connection = null;
        try{
            connection = prepareConnection();
            if(connection != null){
                String sql = "select * " +
                        "from producer\n" +
                        "join farmer\n" +
                        "on producer.farmer_id = farmer.farmer_id\n" +
                        "where lot_id=" + lotId;
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    int farmerId = resultSet.getInt("farmer_id");
                    String name = resultSet.getString("name") + " " + resultSet.getString("surname");
                    Producer producer = new Producer(lotId, farmerId, name, 0);
                    producers.add(producer);
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
        }
        return producers;
    }


}
