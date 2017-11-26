package models;

import java.io.Serializable;

public class WarehouseProduct implements Serializable{
    private int order,quantity,shelf,docNo;
    private String name,docDate,recorder,recipient,form, productId;
    private String unit;

    public WarehouseProduct(int docNo, String docDate, String productId, String name
            , String unit, int quantity, int shelf, String recorder, String recipient, String form) {
        this.order = order;
        this.productId = productId;
        this.quantity = quantity;
        this.shelf = shelf;
        this.docNo = docNo;
        this.name = name;
        this.docDate = docDate;
        this.recorder = recorder;
        this.recipient = recipient;
        this.form = form;
        this.unit = unit;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getShelf() {
        return shelf;
    }

    public void setShelf(int shelf) {
        this.shelf = shelf;
    }

    public int getDocNo() {
        return docNo;
    }

    public void setDocNo(int docNo) {
        this.docNo = docNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
