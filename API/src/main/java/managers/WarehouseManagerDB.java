package managers;

import models.Warehouse;
import models.WarehouseProduct;
import models.WarehouseSeed;

import java.sql.SQLException;
import java.util.List;

public interface WarehouseManagerDB {

    List<Warehouse> getWarehouseSeed();

    List<Warehouse> getWarehouseProduct();

    List<Warehouse> getWarehouseSeedName(String name);

    List<Warehouse> getWarehouseSeedId(String id);

    List<Warehouse> getWarehouseProductName(String name);

    List<Warehouse> getWarehouseProductId(String id);

    void insertToWarehouseSeed(WarehouseSeed warehouse) throws Exception;

    void insertToWarehouseProduct(WarehouseProduct warehouse);

    void updateWarehouseSeed(WarehouseSeed warehouse);

    void updateWarehouseProduct(WarehouseProduct warehouse);
}
