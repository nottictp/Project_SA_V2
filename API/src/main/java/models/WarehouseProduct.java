package models;

import java.io.Serializable;

public class WarehouseProduct extends Warehouse implements Serializable{
    private String productId;

    public WarehouseProduct(int quantity, int shelf, int docNo, String name, String unit, String docDate, String recorder, String recipient, String form, String productId) {
        super(quantity,shelf,  docNo,  name,  unit, docDate, recorder, recipient, form);
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
