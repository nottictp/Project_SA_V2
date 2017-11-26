package models;

import java.io.Serializable;

public class Product implements Serializable {

    private String product_ID, product_name, unit, packaging;
    private int seed_ID, contain, qualtity;
    private float price;

    public Product(String product_ID, String product_name, String unit, String packaging, int seed_ID, int contain, int qualtity, float price) {
        this.product_ID = product_ID;
        this.product_name = product_name;
        this.unit = unit;
        this.packaging = packaging;
        this.seed_ID = seed_ID;
        this.contain = contain;
        this.qualtity = qualtity;
        this.price = price;
    }

    public String getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(String product_ID) {
        this.product_ID = product_ID;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public int getSeed_ID() {
        return seed_ID;
    }

    public void setSeed_ID(int seed_ID) {
        this.seed_ID = seed_ID;
    }

    public int getContain() {
        return contain;
    }

    public void setContain(int contain) {
        this.contain = contain;
    }

    public int getQualtity() {
        return qualtity;
    }

    public void setQualtity(int qualtity) {
        this.qualtity = qualtity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
