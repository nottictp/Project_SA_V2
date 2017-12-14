package managers;

import models.Warehouse;
import models.WarehouseProduct;
import models.WarehouseSeed;

import java.util.List;

public interface WarehouseManagerDB {

    List<Warehouse> getWarehouseSeed();

    List<Warehouse> getWarehouseProduct();

    List<Warehouse> getWarehouseSeedName(String name);

    List<Warehouse> getWarehouseSeedId(String id);

    List<Warehouse> getWarehouseProductName(String name);

    List<Warehouse> getWarehouseProductId(String id);
}
