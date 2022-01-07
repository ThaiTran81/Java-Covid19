/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Model;

/**
 *
 * @author ThaiTran
 */
public class CurrentStateModel {
    String id;
    String fullname;
    String state;
    String quaratine;
    String dob;

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public CurrentStateModel() {
    }

    public CurrentStateModel(String id, String fullname,String dob,  String state, String quaratine) {
        this.id = id;
        this.fullname = fullname;
        this.dob = dob;
        this.state = state;
        this.quaratine = quaratine;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getQuaratine() {
        return quaratine;
    }

    public void setQuaratine(String quaratine) {
        this.quaratine = quaratine;
    }
    
}
