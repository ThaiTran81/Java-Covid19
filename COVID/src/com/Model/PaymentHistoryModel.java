package com.Model;

import java.sql.Date;

public class PaymentHistoryModel {
    private String username;
    private java.sql.Date date;
    private String pay;

    public PaymentHistoryModel() {
    }

    public PaymentHistoryModel(String username, Date date, String pay) {
        this.username = username;
        this.date = date;
        this.pay = pay;
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

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }
}
