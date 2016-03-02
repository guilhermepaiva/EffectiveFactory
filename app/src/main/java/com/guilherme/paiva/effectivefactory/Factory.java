package com.guilherme.paiva.effectivefactory;

/**
 * Created by guilhermepaiva on 13/02/16.
 */
public class Factory {

    int id;
    String factoryName;
    String factoryAddress;

    public Factory() {}

    public Factory(int id, String factoryName, String factoryAddress) {
        this.id = id;
        this.factoryName = factoryName;
        this.factoryAddress = factoryAddress;
    }

    public Factory(String factoryName, String factoryAddress) {
        this.factoryName = factoryName;
        this.factoryAddress = factoryAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getFactoryAddress() {
        return factoryAddress;
    }

    public void setFactoryAddress(String factoryAddress) {
        this.factoryAddress = factoryAddress;
    }

}
