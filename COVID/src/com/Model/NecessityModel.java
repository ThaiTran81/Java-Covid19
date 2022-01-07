package com.Model;

public class NecessityModel {
    private int id;
    private String name;
    private int limit;
    private int price;
    private int time_limit;
    private int consume;
    private String type;
    
     public NecessityModel() {
    }

    public NecessityModel(String name, int limit, int price, int time_limit, String type) {
        this.name = name;
        this.limit = limit;
        this.price = price;
        this.time_limit = time_limit;
        this.type = type;
    }

    public NecessityModel(int id, String name, int limit, int price, int time_limit, int consume) {
        this.id = id;
        this.name = name;
        this.limit = limit;
        this.price = price;
        this.time_limit = time_limit;
        this.consume = consume;
    }

    public NecessityModel(int id, String name, int limit, int price, int time_limit, String type) {
        this.id = id;
        this.name = name;
        this.limit = limit;
        this.price = price;
        this.time_limit = time_limit;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConsume() {
        return consume;
    }

    public void setConsume(int consume) {
        this.consume = consume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(int time_limit) {
        this.time_limit = time_limit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
