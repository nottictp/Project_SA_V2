package models;

public class MarketingInfo {

    private static MarketingInfo marketingInfo;
    WarehouseProduct warehouseProduct;

    public MarketingInfo() {
    }

    public static MarketingInfo getInstance(){
        if (marketingInfo == null){
            marketingInfo = new MarketingInfo();
        }return marketingInfo;
    }

    public WarehouseProduct getWarehouseProduct() {
        return warehouseProduct;
    }

    public void setWarehouseProduct(WarehouseProduct warehouseProduct) {
        this.warehouseProduct = warehouseProduct;
    }
}
