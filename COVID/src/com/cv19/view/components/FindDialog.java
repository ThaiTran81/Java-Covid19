/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cv19.view.components;

import com.cv19.view.body.FindForm;

/**
 *
 * @author ThaiTran
 */
public class FindDialog extends javax.swing.JDialog {

    public FindDialog() {
        this.add(new FindForm());
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        
    }
    
}
