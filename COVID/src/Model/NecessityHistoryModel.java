package Model;

import java.sql.Date;

public class NecessityHistoryModel {
    private String username;
    private java.sql.Date date;
    private String name;
    private String price;
    private int time_limit;
    private int quantity;
    private String total;

    public NecessityHistoryModel() {
    }

    public NecessityHistoryModel(String username, Date date, String name, String price, int quantity, String total) {
        this.username = username;
        this.date = date;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public NecessityHistoryModel(String username, Date date, String name, int time_limit, int quantity) {
        this.username = username;
        this.date = date;
        this.name = name;
        this.time_limit = time_limit;
        this.quantity = quantity;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(int time_limit) {
        this.time_limit = time_limit;
    }
}
