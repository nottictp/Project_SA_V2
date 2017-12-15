package models;

import java.io.Serializable;

public class Farmer implements Serializable{

    private int farmer_id, identity_id;
    private String name, surname, address, phoneNum,production_status,group;
    private double capacity_area;

    public Farmer(int farmer_id, int identity_id, String name, String surname, String address, String phoneNum, String production_status, String group, double capacity_area) {
        this.farmer_id = farmer_id;
        this.identity_id = identity_id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNum = phoneNum;
        this.production_status = production_status;
        this.group = group;
        this.capacity_area = capacity_area;
    }

    public int getFarmer_id() {
        return farmer_id;
    }

    public void setFarmer_id(int farmer_id) {
        this.farmer_id = farmer_id;
    }

    public int getIdentity_id() {
        return identity_id;
    }

    public void setIdentity_id(int identity_id) {
        this.identity_id = identity_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getProduction_status() {
        return production_status;
    }

    public void setProduction_status(String production_status) {
        this.production_status = production_status;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public double getCapacity_area() {
        return capacity_area;
    }

    public void setCapacity_area(double capacity_area) {
        this.capacity_area = capacity_area;
    }
}
