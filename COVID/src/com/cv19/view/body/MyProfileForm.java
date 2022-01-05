/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.cv19.view.body;

import Controller.CovidDAO;
import Model.profileModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ThaiTran
 */
public class MyProfileForm extends javax.swing.JPanel {

    profileModel user;

    public MyProfileForm(profileModel user) {
        this.user = user;
        initComponents();
        txtID.setText(user.getUsername());
        txtName.setText(user.getFullname());
        txtAdr.setText(user.getVillage() + ", " + user.getDistrict() + ", " + user.getProvince());
        txtType.setText(accountType(user.getType()));

    }

    String accountType(int i) {
        if (i == 0) {
            return "Admin";
        }
        if (i == 1) {
            return "Quản lý";
        }

        return "Người được quản lý";

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelHead = new javax.swing.JPanel();
        lbTitle = new javax.swing.JLabel();
        panelBottom = new javax.swing.JPanel();
        txtID = new javax.swing.JLabel();
        txtName = new javax.swing.JLabel();
        txtAdr = new javax.swing.JLabel();
        txtType = new javax.swing.JLabel();
        lbCurPass = new javax.swing.JLabel();
        lbNewPass2 = new javax.swing.JLabel();
        lbID = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        lbAddress = new javax.swing.JLabel();
        lbType = new javax.swing.JLabel();
        lbNewPass = new javax.swing.JLabel();
        txtCurPass = new javax.swing.JPasswordField();
        txtNewPass = new javax.swing.JPasswordField();
        txtNewPass2 = new javax.swing.JPasswordField();
        btnChange = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setLayout(null);

        panelHead.setMinimumSize(new java.awt.Dimension(266, 30));
        panelHead.setPreferredSize(new java.awt.Dimension(555, 20));

        lbTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("THÔNG TIN TÀI KHOẢN");
        panelHead.add(lbTitle);

        add(panelHead);
        panelHead.setBounds(0, 0, 555, 50);

        panelBottom.setLayout(new java.awt.GridBagLayout());

        txtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtID.setText("null");
        txtID.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 90;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 1);
        panelBottom.add(txtID, gridBagConstraints);

        txtName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtName.setText("null");
        txtName.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 90;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 1);
        panelBottom.add(txtName, gridBagConstraints);

        txtAdr.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAdr.setText("null");
        txtAdr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 90;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 1);
        panelBottom.add(txtAdr, gridBagConstraints);

        txtType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtType.setText("null");
        txtType.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 90;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 1);
        panelBottom.add(txtType, gridBagConstraints);

        lbCurPass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbCurPass.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCurPass.setText("Mật khẩu hiện tại:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 10);
        panelBottom.add(lbCurPass, gridBagConstraints);

        lbNewPass2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbNewPass2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNewPass2.setText("Xác nhận mật khẩu mới:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 10);
        panelBottom.add(lbNewPass2, gridBagConstraints);

        lbID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbID.setText("ID:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 10);
        panelBottom.add(lbID, gridBagConstraints);

        lbName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbName.setText("Họ tên:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 10);
        panelBottom.add(lbName, gridBagConstraints);

        lbAddress.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbAddress.setText("Địa chỉ:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 10);
        panelBottom.add(lbAddress, gridBagConstraints);

        lbType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbType.setText("Loại tài khoản:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 10);
        panelBottom.add(lbType, gridBagConstraints);

        lbNewPass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbNewPass.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNewPass.setText("Mật khẩu mới:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 10);
        panelBottom.add(lbNewPass, gridBagConstraints);

        txtCurPass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCurPass.setMargin(new java.awt.Insets(5, 10, 5, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 90;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 10);
        panelBottom.add(txtCurPass, gridBagConstraints);

        txtNewPass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNewPass.setMargin(new java.awt.Insets(5, 10, 5, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 90;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 10);
        panelBottom.add(txtNewPass, gridBagConstraints);

        txtNewPass2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNewPass2.setMargin(new java.awt.Insets(5, 10, 5, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 90;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 10);
        panelBottom.add(txtNewPass2, gridBagConstraints);

        btnChange.setText("Đổi mật khẩu");
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 10);
        panelBottom.add(btnChange, gridBagConstraints);

        btnCancel.setText("Huỷ");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 10);
        panelBottom.add(btnCancel, gridBagConstraints);

        add(panelBottom);
        panelBottom.setBounds(0, 50, 555, 360);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        txtCurPass.setText("");
        txtNewPass.setText("");
        txtNewPass2.setText("");
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        String curPass=new String(txtCurPass.getPassword());
        String newPass1 = new String(txtNewPass.getPassword());
        String newPass2 = new String(txtNewPass2.getPassword());
        if(!curPass.equals(user.getPassword())){
            notify("Mật khẩu hiện tại không hợp lệ");
            return;
        }
        if(newPass1.isBlank()){
            notify("Chưa nhập mật khẩu mới");
            return;
        }
        
        if(newPass1.equals(newPass2)){
            try {
                new CovidDAO().updateNewPassword(user.getUsername(), newPass1);
                return;
            } catch (SQLException ex) {
                notify("Thay đổi mật khẩu không thành công");
                Logger.getLogger(MyProfileForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            notify("Thay đổi mật khẩu thành công");
            btnCancelActionPerformed(null);
        }
        
        notify("Xác nhận mật khẩu mới không khớp!!!!");
        
    }//GEN-LAST:event_btnChangeActionPerformed

    void notify(String msg){
        JOptionPane.showMessageDialog(null, msg, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnChange;
    private javax.swing.JLabel lbAddress;
    private javax.swing.JLabel lbCurPass;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbNewPass;
    private javax.swing.JLabel lbNewPass2;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbType;
    private javax.swing.JPanel panelBottom;
    private javax.swing.JPanel panelHead;
    private javax.swing.JLabel txtAdr;
    private javax.swing.JPasswordField txtCurPass;
    private javax.swing.JLabel txtID;
    private javax.swing.JLabel txtName;
    private javax.swing.JPasswordField txtNewPass;
    private javax.swing.JPasswordField txtNewPass2;
    private javax.swing.JLabel txtType;
    // End of variables declaration//GEN-END:variables
}
