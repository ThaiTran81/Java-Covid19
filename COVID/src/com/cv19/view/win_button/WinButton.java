package com.cv19.view.win_button;

import com.cv19.swing.PanelBack;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class WinButton extends javax.swing.JPanel {

    public WinButton() {
        initComponents();
        setOpaque(false);
    }

    public void initEvent(JFrame fram) {
        cmdClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        cmdMi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fram.setState(JFrame.ICONIFIED);
            }
        });
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        cmdClose = new com.cv19.view.win_button.Button();
        cmdMi = new com.cv19.view.win_button.Button();
        cmdRe = new com.cv19.view.win_button.Button();

        setLayout(new java.awt.GridBagLayout());

        cmdClose.setBackground(new java.awt.Color(240, 61, 61));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 0);
        add(cmdClose, gridBagConstraints);

        cmdMi.setBackground(new java.awt.Color(227, 226, 68));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 6, 3, 0);
        add(cmdMi, gridBagConstraints);

        cmdRe.setBackground(new java.awt.Color(204, 204, 204));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 6, 3, 3);
        add(cmdRe, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.cv19.view.win_button.Button cmdClose;
    private com.cv19.view.win_button.Button cmdMi;
    private com.cv19.view.win_button.Button cmdRe;
    // End of variables declaration//GEN-END:variables
}
