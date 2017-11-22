package controllers;

import managers.WarehouseManagerDB;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainController {
    private WarehouseManagerDB executor;

    public MainController() {
        ApplicationContext bf = new ClassPathXmlApplicationContext("warehouse_config.xml");
        executor = (WarehouseManagerDB) bf.getBean("WarehouseDbManager");
        System.out.println("E nott");

    }
}
