/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Model;

/**
 *
 * @author ThaiTran
 */
public class OutBalanceModel {
    String id;
    String name;
    String address;
    int outBalance;

    public OutBalanceModel() {
    }

    public OutBalanceModel(String id, String name, String address, int outBalance) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.outBalance = outBalance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOutBalance() {
        return outBalance;
    }

    public void setOutBalance(int outBalance) {
        this.outBalance = outBalance;
    }
    
    
}
