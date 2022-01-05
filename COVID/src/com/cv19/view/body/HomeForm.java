/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.cv19.view.body;

import Controller.AddressDAO;
import Controller.CovidDAO;
import Model.AddressModel;
import Model.FModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import utils.SortByValue;

/**
 *
 * @author ThaiTran
 */
public class HomeForm extends javax.swing.JPanel {

    String dates[]
            = {"Ngày", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25",
                "26", "27", "28", "29", "30",
                "31"};

    String months[]
            = {"Tháng", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10",
                "11", "12"};
    private String years[]
            = {"Năm", "1987", "1988", "1989", "1990",
                "1991", "1992", "1993", "1994",
                "1995", "1996", "1997", "1998",
                "1999", "2000", "2001", "2002",
                "2003", "2004", "2005", "2006",
                "2007", "2008", "2009", "2010",
                "2011", "2012", "2013", "2014",
                "2015", "2016", "2017", "2018",
                "2019", "2020", "2021", "2022"};
    String sort[] = {"Sắp xếp theo ngày mới nhất", "Sắp xếp theo ngày cũ nhất",
        "Sắp xếp theo số ca khỏi bệnh tăng dần", "sắp xếp theo số ca khỏi bệnh giảm dần",
        "Sắp xếp theo số F0 tăng dần", "Sắp xếp theo số F0 giảm dần"};

    public HomeForm() {
        initComponents();

        comboDay.setModel(new DefaultComboBoxModel(dates));
        comboMonth.setModel(new DefaultComboBoxModel(months));
        comboYear.setModel(new DefaultComboBoxModel(years));
        comboSort.setModel(new DefaultComboBoxModel(sort));
        init();

    }

    void init() {
        try {
            statusTotal.setIcon("virus");
            statusTotal.setLbTitle("TỔNG NHIỄM");
            statusTotal.setLbNum("" + new CovidDAO().getNumByF("F0"));

            statusHealth.setIcon("healthy");
            statusHealth.setLbTitle("KHỎI BỆNH");
            statusHealth.setLbNum("" + new CovidDAO().getNumByF("OK"));
            statusHealth.setColor(new Color(159, 219, 8));

            statusDeath.setIcon("emergency");
            statusDeath.setLbTitle("Số F0 mới");
            statusDeath.setLbNum("" + new CovidDAO().getNumNewF0());
            statusDeath.setColor(new Color(138, 148, 166));

            query(null, 0, false);
        } catch (SQLException ex) {
            Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void query(String time, int sort, boolean asc) {
        try {
            TreeMap<String, FModel> lst = (TreeMap<String, FModel>) SortByValue.valueSort(new CovidDAO().getNumFDate(time), sort, asc);
            addToTable(lst);
        } catch (SQLServerException ex) {
            Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch(java.lang.NullPointerException ex){
            JOptionPane.showMessageDialog(null, "Không tìm thấy", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }

    void addToTable(TreeMap<String, FModel> lst) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Map.Entry<String, FModel> entry : lst.entrySet()) {
            String time = entry.getKey();
            FModel val = entry.getValue();
            Object[] content = {time, val.getF0(), val.getF1(), val.getF2(), val.getF3(), val.getGood()};
            model.addRow(content);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelStatus = new javax.swing.JPanel();
        statusTotal = new com.cv19.view.components.StatusPan();
        statusHealth = new com.cv19.view.components.StatusPan();
        statusDeath = new com.cv19.view.components.StatusPan();
        lbTitle = new javax.swing.JLabel();
        panelFilter = new javax.swing.JPanel();
        panelDate = new javax.swing.JPanel();
        comboDay = new javax.swing.JComboBox<>();
        comboMonth = new javax.swing.JComboBox<>();
        comboYear = new javax.swing.JComboBox<>();
        comboSort = new javax.swing.JComboBox<>();
        btnViewAll = new javax.swing.JButton();
        btnGet = new javax.swing.JButton();
        scTable = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        panelStatus.setBackground(new java.awt.Color(255, 255, 255));
        panelStatus.setLayout(new java.awt.GridLayout(1, 3, 15, 0));
        panelStatus.add(statusTotal);
        panelStatus.add(statusHealth);
        panelStatus.add(statusDeath);

        lbTitle.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(138, 148, 166));
        lbTitle.setText("TỔNG QUAN");

        panelFilter.setLayout(new java.awt.GridBagLayout());

        panelDate.setLayout(new java.awt.GridLayout(1, 3, 5, 0));

        panelDate.add(comboDay);

        panelDate.add(comboMonth);

        panelDate.add(comboYear);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        panelFilter.add(panelDate, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        panelFilter.add(comboSort, gridBagConstraints);

        btnViewAll.setText("Xem tất cả");
        btnViewAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewAllActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        panelFilter.add(btnViewAll, gridBagConstraints);

        btnGet.setText("Truy suất");
        btnGet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        panelFilter.add(btnGet, gridBagConstraints);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Ngày", "Số F0", "Số F1", "Số F2", "Số F3", "Khỏi bệnh"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scTable.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelFilter, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE)
                    .addComponent(scTable)
                    .addComponent(lbTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scTable, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAllActionPerformed
        // TODO add your handling code here:
        if (comboSort.getSelectedIndex() == 0) {
            query(null, 0, false);
        }
        if (comboSort.getSelectedIndex() == 1) {
            query(null, 0, true);
        }
        if (comboSort.getSelectedIndex() == 2) {
            query(null, 5, true);
        }
        if (comboSort.getSelectedIndex() == 3) {
            query(null, 5, false);
        }
        if (comboSort.getSelectedIndex() == 4) {
            query(null, 1, true);
        }
        if (comboSort.getSelectedIndex() == 5) {
            query(null, 1, false);
        }
    }//GEN-LAST:event_btnViewAllActionPerformed

    private void btnGetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetActionPerformed
        // TODO add your handling code here:
        String time;
        String day = (String) comboDay.getSelectedItem();
        String month = (String) comboMonth.getSelectedItem();
        String year = (String) comboYear.getSelectedItem();
        int count = 0;
        if (day.equalsIgnoreCase("ngày")) {
            count++;
        }
        if (month.equalsIgnoreCase("tháng")) {
            count++;
        }
        if (year.equalsIgnoreCase("năm")) {
            count++;
        }

        if (count > 0 && count < 3) {
            JOptionPane.showMessageDialog(null, "vui lòng điền đầy đủ thời gian nếu muôn truy suất theo ngày", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (count == 0) {
            time = year + "/" + month + "/" + day;
            query(time, 0, false);
            return;
        }
        btnViewAllActionPerformed(null);
    }//GEN-LAST:event_btnGetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGet;
    private javax.swing.JButton btnViewAll;
    private javax.swing.JComboBox<String> comboDay;
    private javax.swing.JComboBox<String> comboMonth;
    private javax.swing.JComboBox<String> comboSort;
    private javax.swing.JComboBox<String> comboYear;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPanel panelDate;
    private javax.swing.JPanel panelFilter;
    private javax.swing.JPanel panelStatus;
    private javax.swing.JScrollPane scTable;
    private com.cv19.view.components.StatusPan statusDeath;
    private com.cv19.view.components.StatusPan statusHealth;
    private com.cv19.view.components.StatusPan statusTotal;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
