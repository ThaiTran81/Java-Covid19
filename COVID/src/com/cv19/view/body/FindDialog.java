/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cv19.view.body;

import com.cv19.view.event.EventFindCallBack;
import com.cv19.view.event.EventFindSelected;

/**
 *
 * @author ThaiTran
 */
public class FindDialog extends javax.swing.JDialog {
    
    EventFindCallBack callback;
    
    public FindDialog(EventFindCallBack callback) {
        this.callback = callback;
        this.add(new FindForm(new EventFindSelected(){
            @Override
            public void selected(String id, String status) {
                FindDialog.this.callback.setRelatedAddForm(id, status);
                FindDialog.this.dispose();
            }
            
        }));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }
    
}
