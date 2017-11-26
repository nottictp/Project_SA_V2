package controllers;

import managers.WarehouseManagerDB;
import models.Warehouse;
import models.WarehouseProduct;
import models.WarehouseSeed;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    private WarehouseManagerDB executor;

    public MainController() {
        ApplicationContext bf = new ClassPathXmlApplicationContext("warehouse_config.xml");
        executor = (WarehouseManagerDB) bf.getBean("WarehouseDbManager");
        System.out.println("E nott");
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
        System.out.println("test");
        warehouseList.addAll(getWarehouseSeed());
        warehouseList.addAll(getWarehouseProduct());
        System.out.println("get");
        return warehouseList;
    }

}
