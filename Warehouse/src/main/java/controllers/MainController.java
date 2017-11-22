package controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainController {
    private WarehouseManagerDB
    public MainController() {
        ApplicationContext bf = new ClassPathXmlApplicationContext("warehouse_config.xml");

    }
}
