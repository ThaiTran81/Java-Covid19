/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.cv19.view.body;

/**
 *
 * @author ThaiTran
 */
public class MyProfileForm extends javax.swing.JPanel {

    /**
     * Creates new form MyProfileForm
     */
    public MyProfileForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHead = new javax.swing.JPanel();
        lbTitle = new javax.swing.JLabel();
        panelBottom = new javax.swing.JPanel();
        lbID = new javax.swing.JLabel();
        txtID = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        txtName = new javax.swing.JLabel();
        lbAddr = new javax.swing.JLabel();
        txtAdr = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        txtStatus = new javax.swing.JLabel();
        lbType = new javax.swing.JLabel();
        txtType = new javax.swing.JLabel();

        setLayout(null);

        panelHead.setPreferredSize(new java.awt.Dimension(555, 0));

        lbTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("THÔNG TIN TÀI KHOẢN");
        panelHead.add(lbTitle);

        add(panelHead);
        panelHead.setBounds(0, 0, 555, 50);

        panelBottom.setLayout(new java.awt.GridLayout(5, 2));

        lbID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbID.setText("CMND/CCCD:");
        lbID.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        panelBottom.add(lbID);

        txtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtID.setText("null");
        txtID.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        panelBottom.add(txtID);

        lbName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbName.setText("Họ tên:");
        lbName.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        panelBottom.add(lbName);

        txtName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtName.setText("null");
        txtName.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        panelBottom.add(txtName);

        lbAddr.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbAddr.setText("Địa chỉ:");
        lbAddr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        panelBottom.add(lbAddr);

        txtAdr.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAdr.setText("null");
        txtAdr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        panelBottom.add(txtAdr);

        lbStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbStatus.setText("Trạng thái:");
        lbStatus.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        panelBottom.add(lbStatus);

        txtStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtStatus.setText("null");
        txtStatus.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        panelBottom.add(txtStatus);

        lbType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbType.setText("Loại tài khoản:");
        lbType.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        panelBottom.add(lbType);

        txtType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtType.setText("null");
        txtType.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        panelBottom.add(txtType);

        add(panelBottom);
        panelBottom.setBounds(0, 42, 555, 290);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbAddr;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbType;
    private javax.swing.JPanel panelBottom;
    private javax.swing.JPanel panelHead;
    private javax.swing.JLabel txtAdr;
    private javax.swing.JLabel txtID;
    private javax.swing.JLabel txtName;
    private javax.swing.JLabel txtStatus;
    private javax.swing.JLabel txtType;
    // End of variables declaration//GEN-END:variables
}