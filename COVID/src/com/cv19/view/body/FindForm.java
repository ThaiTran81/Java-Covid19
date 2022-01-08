/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.cv19.view.body;

import com.Controller.CovidDAO;
import com.Model.profileModel;
import com.cv19.view.event.EventFindSelected;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 * @author ThaiTran
 */
public class FindForm extends javax.swing.JPanel {

    /**
     * Creates new form FindForm
     */

    EventFindSelected callback;

    public FindForm(EventFindSelected callback) {

        this.callback = callback;
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSearch = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        lbName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lbGap = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        panelResult = new javax.swing.JPanel();
        tbRelate = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnSelect = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        lbID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbID.setText("CMND/CCCD");
        jPanel1.add(lbID);

        txtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtID.setMargin(new java.awt.Insets(2, 10, 2, 10));
        txtID.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel1.add(txtID);

        lbName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbName.setText("Họ tên");
        lbName.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));
        jPanel1.add(lbName);

        txtName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtName.setMargin(new java.awt.Insets(2, 10, 2, 10));
        txtName.setMinimumSize(new java.awt.Dimension(100, 20));
        txtName.setPreferredSize(new java.awt.Dimension(150, 25));
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });
        jPanel1.add(txtName);

        lbGap.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 40));
        jPanel1.add(lbGap);

        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch);

        panelSearch.add(jPanel1);

        add(panelSearch);

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout();
        flowLayout1.setAlignOnBaseline(true);
        panelResult.setLayout(flowLayout1);

        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

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
        tbRelate.setViewportView(table);

        panelResult.add(tbRelate);

        btnSelect.setText("Chọn");
        btnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectActionPerformed(evt);
            }
        });
        panelResult.add(btnSelect);

        add(panelResult);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:

        try {
            List<profileModel> lst = new CovidDAO().getStatusUser(txtID.getText(), txtName.getText());
            if (lst == null) {
                return;
            }
            addTable(lst);

        } catch (SQLServerException ex) {
            Logger.getLogger(FindForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FindForm.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectActionPerformed
        // TODO add your handling code here:
        if(table.getSelectedRow()==-1) return;
        String idRelated = (String) table.getValueAt(table.getSelectedRow(), 0);
        String status = (String) table.getValueAt(table.getSelectedRow(), 2);
        if (idRelated.isBlank() || status.isBlank()) return;
        callback.selected(idRelated, status);

    }//GEN-LAST:event_btnSelectActionPerformed

    void addTable(List<profileModel> lst) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (profileModel profile :
                lst) {
            model.addRow(new Object[]{profile.getUsername(), profile.getFullname(), profile.getStatus()});
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSelect;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbGap;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbName;
    private javax.swing.JPanel panelResult;
    private javax.swing.JPanel panelSearch;
    private javax.swing.JTable table;
    private javax.swing.JScrollPane tbRelate;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
