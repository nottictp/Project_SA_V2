package models;

import javafx.beans.property.SimpleStringProperty;

public class WarehouseInfo {

    private static WarehouseInfo warehouseInfo;
    private WarehouseProduct warehouseProduct;
    private WarehouseSeed warehouseSeed;

    public WarehouseInfo() {
    }

    public static WarehouseInfo getInstance(){
        if (warehouseInfo == null){
            warehouseInfo = new WarehouseInfo();
        }return warehouseInfo;
    }

    public WarehouseProduct getWarehouseProduct() {
        return warehouseProduct;
    }

    public void setWarehouseProduct(WarehouseProduct warehouseProduct) {
        this.warehouseProduct = warehouseProduct;
    }

    public WarehouseSeed getWarehouseSeed() {
        return warehouseSeed;
    }

    public void setWarehouseSeed(WarehouseSeed warehouseSeed) {
        this.warehouseSeed = warehouseSeed;
    }
}
