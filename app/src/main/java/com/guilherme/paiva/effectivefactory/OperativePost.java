package com.guilherme.paiva.effectivefactory;

/**
 * Created by guilhermepaiva on 16/02/16.
 */
public class OperativePost {

    int id;
    String operativePostName;
    String operativePostNumber;

    public OperativePost(int id, String operativePostName, String operativePostNumber) {
        this.id = id;
        this.operativePostName = operativePostName;
        this.operativePostNumber = operativePostNumber;
    }

    public OperativePost(String operativePostName, String operativePostNumber) {
        this.operativePostName = operativePostName;
        this.operativePostNumber = operativePostNumber;
    }

    public OperativePost() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperativePostName() {
        return operativePostName;
    }

    public void setOperativePostName(String operativePostName) {
        this.operativePostName = operativePostName;
    }

    public String getOperativePostNumber() {
        return operativePostNumber;
    }

    public void setOperativePostNumber(String operativePostNumber) {
        this.operativePostNumber = operativePostNumber;
    }


}
