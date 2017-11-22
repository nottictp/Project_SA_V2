package controllers;

import managers.ManufactorManagerDB;
import managers.WarehouseManagerDB;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainController {
    private ManufactorManagerDB executor;
    public MainController() {
        ApplicationContext bf = new ClassPathXmlApplicationContext("manufactor_config.xml");
        executor = (ManufactorManagerDB) bf.getBean("ManufactoryDbManager");
    }
}
