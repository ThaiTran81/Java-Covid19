/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.cv19.view.components;

import java.awt.Color;

/**
 *
 * @author ThaiTran
 */
public class Profile extends javax.swing.JPanel {

    /**
     * Creates new form Profile
     */
    public Profile() {
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

        imageAvatar = new com.cv19.swing.ImageAvatar();
        lbName = new javax.swing.JLabel();
        lbRole = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 187, 249));

        imageAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cv19/icon/Profile.png"))); // NOI18N

        lbName.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setText("MyName");

        lbRole.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lbRole.setForeground(new java.awt.Color(243, 244, 246));
        lbRole.setText("Quản lý");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addComponent(lbRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageAvatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lbName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbRole)
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    public void setNameP(String name){
        lbName.setText(name);
    };
    
    public void setNameP(String name, Color color){
        lbName.setText(name);
        lbName.setForeground(color);
    }
    public void setRole(String role){
        lbRole.setText(role);
    }
    public void setRole(String role, Color color){
        lbRole.setText(role);
        lbRole.setForeground(color);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.cv19.swing.ImageAvatar imageAvatar;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbRole;
    // End of variables declaration//GEN-END:variables
}
