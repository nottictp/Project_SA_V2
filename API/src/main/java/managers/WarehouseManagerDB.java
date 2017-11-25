package managers;

import models.WarehouseProduct;
import models.WarehouseSeed;

public interface WarehouseManagerDB {

    WarehouseSeed getWarehouseSeed();

    WarehouseProduct getWarehouseProduct();


}
