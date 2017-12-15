package controllers;

import managers.WarehouseManagerDB;
import models.Warehouse;
import models.WarehouseProduct;
import models.WarehouseSeed;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainWarehouseController {
    private WarehouseManagerDB executor;

    public MainWarehouseController() {
        ApplicationContext bf = new ClassPathXmlApplicationContext("warehouse_config.xml");
        executor = (WarehouseManagerDB) bf.getBean("WarehouseDbManager");
    }

    public List<Warehouse> getWarehouseSeed(){
        return executor.getWarehouseSeed();
    }

    public List<Warehouse> getWarehouseProduct(){
        return executor.getWarehouseProduct();
    }

    public List<Warehouse> getWarehouse(){
        List<Warehouse> warehouseList= new ArrayList<Warehouse>();
        System.out.println("++");
        List<Warehouse> wh1 = getWarehouseSeed();
        List<Warehouse> wh2 = getWarehouseProduct();
        warehouseList.addAll(getWarehouseSeed());
        warehouseList.addAll(getWarehouseProduct());
        return warehouseList;
    }

    public List<Warehouse> getWarehouseSeedName(String name){
        return executor.getWarehouseSeedName(name);
    }

    public List<Warehouse> getWarehouseSeedId(String id){
        return executor.getWarehouseSeedId(id);
    }

    public List<Warehouse> getWarehouseProductName(String name){
        return executor.getWarehouseProductName(name);
    }

    public List<Warehouse> getWarehouseProductId(String id){
        return executor.getWarehouseProductId(id);
    }

    public void insertToWarehouseSeed(WarehouseSeed warehouse) throws Exception {

        executor.insertToWarehouseSeed(warehouse);
    }

    public void insertToWarehouseProduct(WarehouseProduct warehouse){
        executor.insertToWarehouseProduct(warehouse);
    }

    public void updateWarehouseSeed(WarehouseSeed warehouse){
        executor.updateWarehouseSeed(warehouse);
    }
    public void updateWarehouseProduct(WarehouseProduct warehouse){
        executor.updateWarehouseProduct(warehouse);
    }


}
