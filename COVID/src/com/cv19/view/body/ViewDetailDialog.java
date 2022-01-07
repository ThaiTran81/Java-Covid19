/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cv19.view.body;

/**
 *
 * @author ThaiTran
 */
public class ViewDetailDialog extends javax.swing.JDialog {
    
    
    public ViewDetailDialog(String id) {
        
        this.add(new ViewDetailPanel(id));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }
    
}
