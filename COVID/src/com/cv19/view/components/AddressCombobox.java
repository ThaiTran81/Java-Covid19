/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cv19.view.components;

import Controller.AddressDAO;
import Model.AddressModel;
import com.cv19.view.body.HomeForm;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author ThaiTran
 */
public class AddressCombobox implements ItemListener {
    
    private javax.swing.JComboBox<AddressModel> comboCity;
    private javax.swing.JComboBox<AddressModel> comboDistrict;
    private javax.swing.JComboBox<AddressModel> comboVillage;
    AddressModel itemsCity = new AddressModel(-1, "-- Chọn tỉnh/thành phố --");
    AddressModel itemsDis = new AddressModel(-1, "-- Chọn quận/huyện --");
    AddressModel itemsVill = new AddressModel(-1, "-- Chọn phường/xã --");
    ArrayList<AddressModel> lstProv;
    ArrayList<AddressModel> lstDist;
    ArrayList<AddressModel> lstVill;
    
    public AddressCombobox(JComboBox<AddressModel> comboCity, JComboBox<AddressModel> comboDistrict, JComboBox<AddressModel> comboVillage) {
        this.comboCity = comboCity;
        this.comboDistrict = comboDistrict;
        this.comboVillage = comboVillage;
        init();
        initProvince();
    }
    
    public void init() {
        comboDistrict.addItem(itemsDis);
        comboVillage.addItem(itemsVill);
        comboCity.addItemListener(this);
        comboDistrict.addItemListener(this);
    }
    
    void initProvince() {
        try {
            lstProv = new AddressDAO().getAllProvince();
            comboCity.removeAllItems();
            comboCity.addItem(itemsCity);
            for (int i = 0; i < lstProv.size(); i++) {
                comboCity.addItem(lstProv.get(i));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void eventCity() {
        try {
            AddressModel province_model = (AddressModel) comboCity.getSelectedItem();
            if (province_model == null || province_model.getId() == -1) {
                comboDistrict.removeAllItems();
                comboDistrict.addItem(itemsDis);
                return;
            }
            lstDist = new AddressDAO().getDistrict(province_model.getId());
            if (lstDist == null) {
                return;
            }
            comboDistrict.removeAllItems();
            comboDistrict.addItem(itemsDis);
            for (int i = 0; i < lstDist.size(); i++) {
                comboDistrict.addItem(lstDist.get(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void eventDistrict() {
        try {
            AddressModel dist_model = (AddressModel) comboDistrict.getSelectedItem();
            if (dist_model == null || dist_model.getId() == -1) {
                comboVillage.removeAllItems();
                comboVillage.addItem(itemsVill);
                return;
            }
            lstVill = new AddressDAO().getVillage(dist_model.getId());
            if (lstVill == null) {
                return;
            }
            comboVillage.removeAllItems();
            comboVillage.addItem(itemsVill);
            for (int i = 0; i < lstVill.size(); i++) {
                comboVillage.addItem(lstVill.get(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == comboCity) {
            eventCity();
        }
        if (e.getSource() == comboDistrict) {
            eventDistrict();
        }
    }
    
    public void setProvince(String name) {
        for (int i = 0; i < lstProv.size(); i++) {
            if (lstProv.get(i).getName().equals(name)) {
                comboCity.setSelectedItem(lstProv.get(i));
                return;
            }
        }
        comboCity.setSelectedItem(lstProv.get(0));
    }
    
    public void setDist(String name) {
        for (int i = 0; i < lstDist.size(); i++) {
            if (lstDist.get(i).getName().equals(name)) {
                comboDistrict.setSelectedItem(lstDist.get(i));
                return;
            }
        }
        comboDistrict.setSelectedItem(lstDist.get(0));
    }
    
    public void setVill(String name) {
        for (int i = 0; i < lstDist.size(); i++) {
            if (lstVill.get(i).getName().equals(name)) {
                comboVillage.setSelectedItem(lstVill.get(i));
                return;
            }
        }
        comboVillage.setSelectedItem(lstVill.get(0));
    }
}
