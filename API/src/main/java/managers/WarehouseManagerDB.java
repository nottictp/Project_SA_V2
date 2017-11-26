package managers;

import models.Warehouse;
import models.WarehouseProduct;
import models.WarehouseSeed;

import java.util.List;

public interface WarehouseManagerDB {

    List<Warehouse> getWarehouseSeed();

    List<Warehouse> getWarehouseProduct();


}
