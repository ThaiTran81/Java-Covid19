package com.View;


import com.Controller.CovidDAO;
import com.View.User.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangeFirstPassView extends javax.swing.JFrame {

    public ChangeFirstPassView(String id) {
        initComponents();
        txtName.setText(id);
    }

    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        lbHeader = new javax.swing.JLabel();
        panelMain = new javax.swing.JPanel();
        lbName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lbPass = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        lbConfirmPass = new javax.swing.JLabel();
        txtConfirmPass = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        cbShowPass = new javax.swing.JCheckBox();
        btnCancel = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        lbHeader.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbHeader.setText("ĐỔI MẬT KHẨU");
        panelHeader.add(lbHeader);

        getContentPane().add(panelHeader);

        panelMain.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelMain.setLayout(new java.awt.GridLayout(5, 2, 0, 10));

        lbName.setText("TÀI KHOẢN:");
        panelMain.add(lbName);

        txtName.setEditable(false);
        txtName.setMargin(new java.awt.Insets(5, 10, 5, 10));
        panelMain.add(txtName);

        lbPass.setText("MẬT KHẨU MỚI");
        panelMain.add(lbPass);

        txtPass.setMargin(new java.awt.Insets(5, 10, 5, 10));
        panelMain.add(txtPass);

        lbConfirmPass.setText("XÁC NHẬN MẬT KHẨU MỚI");
        panelMain.add(lbConfirmPass);

        txtConfirmPass.setMargin(new java.awt.Insets(5, 10, 5, 10));
        txtConfirmPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConfirmPassActionPerformed(evt);
            }
        });
        panelMain.add(txtConfirmPass);
        panelMain.add(jLabel5);

        cbShowPass.setText("Show password");
        cbShowPass.addItemListener(eventShowpass());
        panelMain.add(cbShowPass);

        btnCancel.setText("HUỶ");
        panelMain.add(btnCancel);

        btnSubmit.setText("XÁC NHẬN");
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePass();
            }
        });
        panelMain.add(btnSubmit);

        getContentPane().add(panelMain);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

    private void txtConfirmPassActionPerformed(java.awt.event.ActionEvent evt) {
        updatePass();
    }


    void updatePass() {
        String username = txtName.getText();
        String password = String.valueOf(txtPass.getPassword());
        String rePassword = String.valueOf(txtConfirmPass.getPassword());

        if (username.isBlank() || password.isBlank() || rePassword.isBlank()) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ các trường", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if(!password.equals(rePassword)){
                JOptionPane.showMessageDialog(null, "Mật khẩu xác nhận không khớp", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            try {
                new CovidDAO().updateNewPassword(username, password);
                new MainFrame(username);
                this.dispose();
            } catch (SQLException e) {
                Logger.getLogger(ChangeFirstPassView.class.getName()).log(Level.SEVERE, "Thay đổi MK thất bại", e);
                JOptionPane.showMessageDialog(null,"Thay đổi thất bại vui lòng thử lại sau","Thông báo", JOptionPane.ERROR);
            }

        }
    }

    ItemListener eventShowpass() {
        ItemListener event = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (cbShowPass.isSelected()) {
                    txtPass.setEchoChar((char) 0);
                } else {
                    txtPass.setEchoChar('•');
                }
            }
        };
        return event;
    }

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangeFirstPassView(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JCheckBox cbShowPass;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lbConfirmPass;
    private javax.swing.JLabel lbHeader;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPass;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPasswordField txtConfirmPass;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPass;
    // End of variables declaration
}

