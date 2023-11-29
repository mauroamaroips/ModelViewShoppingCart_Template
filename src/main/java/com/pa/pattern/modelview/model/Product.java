/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa.pattern.modelview.model;

/**
 *
 * @author patriciamacedo
 */
public class Product {
    private int id;
    private String name;
    private double cost;

    public Product(int id, String name, double cost) {
        this.name = name;
        this.cost = cost;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("%d:%s- %.1f", id,name,cost);
    }
    
    
}
