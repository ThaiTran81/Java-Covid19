/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.cv19.view.components;

import com.cv19.swing.ListMenu;
import com.cv19.swing.PanelBorder;
import com.cv19.view.event.EventMenu;
import com.cv19.view.event.EventMenuCallBack;
import com.cv19.view.event.EventMenuSelected;
import com.cv19.view.model.Model_Menu;

/**
 *
 * @author ThaiTran
 */
public class Menu extends PanelBorder {

    /**
     * Creates new form Menu
     */
    private int selectedIndex = -1;
    private EventMenuCallBack callBack;
    private EventMenu event = null;

    public Menu() {
        initComponents();
        init();
        listMenu.addEventSelectedMenu(new EventMenuSelected() {
            @Override
            public void menuSelected(int index) {
                selectedIndex = index;
                if (event != null) {
                    event.menuIndexChange(selectedIndex);
                }
            }

        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        profile = new com.cv19.view.components.Profile();
        listMenu = new com.cv19.swing.ListMenu<>();

        listMenu.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profile, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(listMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    void init() {
//        listMenu.addItem(new Model_Menu("1", "THỐNG KÊ CA MẮC", Model_Menu.MenuType.MENU));
//        listMenu.addItem(new Model_Menu("2", "THÊM", Model_Menu.MenuType.MENU));
//        listMenu.addItem(new Model_Menu("3", "QUẢN LÝ CA MẮC", Model_Menu.MenuType.MENU));
//        listMenu.addItem(new Model_Menu("4", "NHU YẾU PHẨM", Model_Menu.MenuType.MENU));
//        listMenu.addItem(new Model_Menu("5", "THỐNG KÊ DƯ NỢ", Model_Menu.MenuType.MENU));
//        listMenu.addItem(new Model_Menu("6", "THÔNG TIN CỦA TÔI", Model_Menu.MenuType.MENU));
//        listMenu.addItem(new Model_Menu("exit", "THOÁT", Model_Menu.MenuType.EXIT));

    }

    public void setSelectedIndex(int index) {
        listMenu.setSelectedIndex(index);
    }

    public void addEvent(EventMenu e) {
        this.event = e;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.cv19.swing.ListMenu<String> listMenu;
    private com.cv19.view.components.Profile profile;
    // End of variables declaration//GEN-END:variables
    public void addItemToMenu(Model_Menu mMenu){
        listMenu.addItem(mMenu);
    }
}
