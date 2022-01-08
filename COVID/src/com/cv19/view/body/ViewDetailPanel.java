/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.cv19.view.body;

import com.Controller.CovidDAO;
import com.Controller.HistoryDAO;
import com.Model.QuarantineModel;
import com.Model.f_historyModel;
import com.Model.profileModel;
import com.cv19.view.components.AddressCombobox;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.utils.FUtil;

/**
 * @author ThaiTran
 */
public class ViewDetailPanel extends javax.swing.JPanel {

    String status[] = {"F0", "OK"};
    AddressCombobox comboAddr;
    ArrayList<QuarantineModel> lstQua;
    profileModel user;
    QuarantineModel nullQua;
    List<profileModel> relateLst = null;
    List<f_historyModel> historyLst = null;
    int preSta;
    int preQua;

    public ViewDetailPanel(String id) {
        try {
            initComponents();

            nullQua = new QuarantineModel(-1, "Chọn khu điều trị", "", -1, -1, -1);

            txtID.setText(id);

            comboSta.setModel(new DefaultComboBoxModel(status));
            lstQua = new CovidDAO().getAllQuarantine();

            comboQua.addItem(nullQua);
            for (int i = 0; i < lstQua.size(); i++) {
                comboQua.addItem(lstQua.get(i));
            }

            user = new CovidDAO().getProfileUser(id);
            txtName.setText(user.getFullname());
            txtPhone.setText(user.getPhone());
            txtGender.setText(user.getGender());
            txtAddress.setText(user.getVillage() + ", " + user.getDistrict() + ", " + user.getProvince());
            txtDob.setText(user.getDob().toString());
            comboQua.setSelectedItem(findQua(user.getId_qua()));
            preQua = comboQua.getSelectedIndex();
            if (checkExistStatus(user.getStatus())) {
                comboSta.setSelectedItem(user.getStatus());
            } else {
                String[] newStatus = new String[status.length + 1];
                newStatus[0] = user.getStatus();
                for (int i = 1; i < newStatus.length; i++) {
                    newStatus[i] = status[i - 1];
                }
                status = newStatus;
                comboSta.setModel(new DefaultComboBoxModel(status));
                comboSta.setSelectedItem(user.getStatus());
            }

            preSta = comboSta.getSelectedIndex();

//            String date = "2015-04-12";
//            java.sql.Date dat = java.sql.Date.valueOf(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(user.getDob());
            String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
            String day = Integer.toHexString(cal.get(Calendar.DAY_OF_MONTH));
            String year = Integer.toString(cal.get(Calendar.YEAR));

            relateLst = new CovidDAO().getRelatedUser(user);
            addToRelateTable();

            historyLst = new CovidDAO().getChangeList(user.getUsername());
            addToHistoryTable();

        } catch (SQLServerException ex) {
            Logger.getLogger(ViewDetailPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ViewDetailPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    QuarantineModel findQua(int id) {
        for (int i = 0; i < lstQua.size(); i++) {
            if (id == lstQua.get(i).getId()) {
                return lstQua.get(i);
            }
        }
        return nullQua;
    }

    boolean checkExistStatus(String state) {
        for (int i = 0; i < status.length; i++) {
            if (status[i].equalsIgnoreCase(state)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTop = new javax.swing.JPanel();
        lbHeader = new javax.swing.JLabel();
        panelMid = new javax.swing.JPanel();
        panelInput = new javax.swing.JPanel();
        lbID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        lbName = new javax.swing.JLabel();
        lbAddr = new javax.swing.JLabel();
        lbSta = new javax.swing.JLabel();
        comboSta = new javax.swing.JComboBox<>();
        lbTreat = new javax.swing.JLabel();
        comboQua = new javax.swing.JComboBox<>();
        txtName = new javax.swing.JTextField();
        lbPhone = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        lbDob = new javax.swing.JLabel();
        lbGender = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        txtGender = new javax.swing.JTextField();
        txtDob = new javax.swing.JTextField();
        panelBtn = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        tableRelatedPanel = new javax.swing.JPanel();
        lbTable = new javax.swing.JLabel();
        scTable = new javax.swing.JScrollPane();
        relateTable = new javax.swing.JTable();
        panelTitle = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        scTable2 = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();

        setMaximumSize(new java.awt.Dimension(32767, 850));
        setPreferredSize(new java.awt.Dimension(1100, 661));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        lbHeader.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbHeader.setText("Thông tin chi tiết");
        panelTop.add(lbHeader);

        add(panelTop);

        panelMid.setMaximumSize(new java.awt.Dimension(32767, 500));
        panelMid.setPreferredSize(new java.awt.Dimension(800, 400));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT);
        flowLayout1.setAlignOnBaseline(true);
        panelMid.setLayout(flowLayout1);

        lbID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbID.setText("CMND/CCCD:");

        txtID.setEditable(false);
        txtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtID.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 10, 4, 10));

        lbName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbName.setText("Họ tên:");

        lbAddr.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbAddr.setText("Địa chỉ:");

        lbSta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbSta.setText("Trạng thái:");

        lbTreat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTreat.setText("Nơi điều trị:");

        txtName.setEditable(false);
        txtName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtName.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 10, 4, 10));

        lbPhone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbPhone.setText("Số điện thoại:");

        txtPhone.setEditable(false);
        txtPhone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPhone.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 10, 4, 10));

        lbDob.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbDob.setText("Ngày sinh:");

        lbGender.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbGender.setText("Giới tính");

        txtAddress.setEditable(false);
        txtAddress.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAddress.setMargin(new java.awt.Insets(5, 10, 5, 10));

        txtGender.setEditable(false);
        txtGender.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtGender.setMargin(new java.awt.Insets(5, 10, 5, 10));

        txtDob.setEditable(false);
        txtDob.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDob.setMargin(new java.awt.Insets(5, 10, 5, 10));

        javax.swing.GroupLayout panelInputLayout = new javax.swing.GroupLayout(panelInput);
        panelInput.setLayout(panelInputLayout);
        panelInputLayout.setHorizontalGroup(
                panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelInputLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(panelInputLayout.createSequentialGroup()
                                                .addComponent(lbPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(4, 4, 4)
                                                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelInputLayout.createSequentialGroup()
                                                .addComponent(lbSta, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(4, 4, 4)
                                                .addComponent(comboSta, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelInputLayout.createSequentialGroup()
                                                .addComponent(lbTreat, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(2, 2, 2)
                                                .addComponent(comboQua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelInputLayout.createSequentialGroup()
                                                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panelInputLayout.createSequentialGroup()
                                                                .addComponent(lbID)
                                                                .addGap(4, 4, 4)
                                                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(panelInputLayout.createSequentialGroup()
                                                                .addComponent(lbDob, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtDob, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 18, Short.MAX_VALUE)
                                                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lbName)
                                                        .addComponent(lbGender))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(panelInputLayout.createSequentialGroup()
                                                .addComponent(lbAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtAddress)))
                                .addGap(78, 78, 78))
        );
        panelInputLayout.setVerticalGroup(
                panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelInputLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panelInputLayout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lbID)
                                                        .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(lbName)
                                                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(6, 6, 6)
                                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(lbDob)
                                                .addComponent(txtDob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(lbGender)
                                                .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(28, 28, 28)
                                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelInputLayout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addComponent(lbPhone))
                                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbAddr)
                                        .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbSta)
                                        .addComponent(comboSta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbTreat)
                                        .addComponent(comboQua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        panelMid.add(panelInput);

        panelBtn.setLayout(new java.awt.GridLayout(4, 1, 0, 10));

        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        panelBtn.add(btnUpdate);


        panelMid.add(panelBtn);

        tableRelatedPanel.setMaximumSize(new java.awt.Dimension(400, 300));
        tableRelatedPanel.setPreferredSize(new java.awt.Dimension(350, 300));
        tableRelatedPanel.setLayout(new javax.swing.BoxLayout(tableRelatedPanel, javax.swing.BoxLayout.Y_AXIS));

        lbTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTable.setText("Danh sách người liên quan");
        tableRelatedPanel.add(lbTable);

        scTable.setMaximumSize(new java.awt.Dimension(700, 400));
        scTable.setPreferredSize(new java.awt.Dimension(400, 500));

        relateTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null}
                },
                new String[]{
                        "CMND/CCCD", "Họ tên", "Trạng thái"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        scTable.setViewportView(relateTable);

        tableRelatedPanel.add(scTable);

        panelMid.add(tableRelatedPanel);

        add(panelMid);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Lịch sử chuyển trạng thái");
        panelTitle.add(jLabel1);

        add(panelTitle);

        scTable2.setMaximumSize(new java.awt.Dimension(1000, 300));
        scTable2.setPreferredSize(new java.awt.Dimension(452, 300));

        historyTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null}
                },
                new String[]{
                        "Thời gian", "Trạng thái", "Nơi điều trị"
                }
        ));
        historyTable.setFillsViewportHeight(true);
        historyTable.setShowGrid(true);
        scTable2.setViewportView(historyTable);

        add(scTable2);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        try {
            user.setStatus(comboSta.getSelectedItem().toString());
            QuarantineModel quarantine = (QuarantineModel) comboQua.getSelectedItem();
            user.setId_qua(quarantine.getId());
            Object params[] = {user.getStatus(), user.getId_qua(), user.getUsername()};
            new CovidDAO().updateStateAndQua(params);
            if (preQua != comboQua.getSelectedIndex()) {
                HistoryDAO.AddHistory("Đã chuyển nơi điều trị của " + user.getUsername() + " sang " + ((QuarantineModel) comboQua.getSelectedItem()).toString() + " [" + user.getId_qua() + "]");
            }

            if (preSta != comboSta.getSelectedIndex()) {
                preSta = comboSta.getSelectedIndex();
                FUtil.updateUserCovidByState(user.getUsername(), comboSta.getSelectedItem().toString(), user.getStatus());
                HistoryDAO.AddHistory("Đã chuyển trạng thái " + user.getUsername() + " sang " + user.getStatus());
            }
            JOptionPane.showMessageDialog(null, "Cập nhật thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(ViewDetailPanel.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnUpdateActionPerformed

    void addToRelateTable() {
        if (relateLst == null) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) relateTable.getModel();
        model.setRowCount(0);

        for (int i = 0; i < relateLst.size(); i++) {
            profileModel obj = relateLst.get(i);
            Object content[] = {obj.getUsername(), obj.getFullname(), obj.getStatus()};
            model.addRow(content);
        }

    }

    void addToHistoryTable() {
        if (historyLst == null) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) historyTable.getModel();
        model.setRowCount(0);

        for (int i = 0; i < historyLst.size(); i++) {
            f_historyModel obj = historyLst.get(i);
            Object content[] = {obj.getDate(), obj.getF_kind(), obj.getQuarantine()};
            model.addRow(content);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<QuarantineModel> comboQua;
    private javax.swing.JComboBox<String> comboSta;
    private javax.swing.JTable historyTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbAddr;
    private javax.swing.JLabel lbDob;
    private javax.swing.JLabel lbGender;
    private javax.swing.JLabel lbHeader;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPhone;
    private javax.swing.JLabel lbSta;
    private javax.swing.JLabel lbTable;
    private javax.swing.JLabel lbTreat;
    private javax.swing.JPanel panelBtn;
    private javax.swing.JPanel panelInput;
    private javax.swing.JPanel panelMid;
    private javax.swing.JPanel panelTitle;
    private javax.swing.JPanel panelTop;
    private javax.swing.JTable relateTable;
    private javax.swing.JScrollPane scTable;
    private javax.swing.JScrollPane scTable2;
    private javax.swing.JPanel tableRelatedPanel;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtDob;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
