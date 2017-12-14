package models;

import java.io.Serializable;

public class WarehouseProduct extends Warehouse implements Serializable{
    private String productId;
    private double capacity;

    public WarehouseProduct(int quantity, String shelf,
                            int docNo, String name,
                            String unit, String docDate,
                            String recorder, String recipient,String form,
                            int type, String productId,double capacity) {
        super(quantity,shelf,  docNo,  name,  unit, docDate, recorder, recipient, form,type);
        this.capacity = capacity;
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }
}
