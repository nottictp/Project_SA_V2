package controllers;

import managers.MarketingManagerDB;
import managers.WarehouseManagerDB;
import models.WarehouseProduct;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainController {

    private MarketingManagerDB executor;
    public MainController() {
        ApplicationContext bf = new ClassPathXmlApplicationContext("marketing_config.xml");
        executor = (MarketingManagerDB) bf.getBean("MarketingDbManager");
    }

    public List<WarehouseProduct> getWarehouseProduct(){
        return executor.getWarehouseProduct();
    }
}
