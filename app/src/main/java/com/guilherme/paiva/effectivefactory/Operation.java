package com.guilherme.paiva.effectivefactory;

/**
 * Created by guilhermepaiva on 20/02/16.
 */
public class Operation {

    private int id;
    private String type;
    private float cost;

    public Operation(){}

    public Operation(int id, String type, float cost) {
        this.id = id;
        this.type = type;
        this.cost = cost;
    }

    public Operation(String type, float cost) {
        this.type = type;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Operação{" +
                "id = " + id +
                ", tipo ='" + type + '\'' +
                ", custo =" + cost +
                '}';
    }

}
