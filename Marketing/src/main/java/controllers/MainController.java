package controllers;

import managers.MarketingManagerDB;
import managers.WarehouseManagerDB;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainController {
    private MarketingManagerDB executor;
    public MainController() {
        ApplicationContext bf = new ClassPathXmlApplicationContext("marketing_config.xml");
        executor = (MarketingManagerDB) bf.getBean("MarketingDbManager");
    }
}
