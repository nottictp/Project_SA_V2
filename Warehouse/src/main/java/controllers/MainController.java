package controllers;

import managers.WarehouseManagerDB;
import models.WarehouseProduct;
import models.WarehouseSeed;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainController {
    private WarehouseManagerDB executor;

    public MainController() {
        ApplicationContext bf = new ClassPathXmlApplicationContext("warehouse_config.xml");
        executor = (WarehouseManagerDB) bf.getBean("WarehouseDbManager");
        System.out.println("E nott");
    }

    public List<WarehouseSeed> getWarehouseSeed(){
        return executor.getWarehouseSeed();
    }

    public List<WarehouseProduct> getWarehouseProduct(){
        return executor.getWarehouseProduct();
    }
}
