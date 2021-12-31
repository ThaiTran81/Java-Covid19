package com.cv19.view.body;

import Controller.CovidDAO;
import Model.OutBalanceModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.OutBalanceSorter;

/**
 *
 * @author ThaiTran
 */
public class OutBalanceForm extends javax.swing.JPanel {

    int total;
    List<OutBalanceModel> lst;
    String sorts[] = {"Sắp xếp theo số dư nợ tăng dần",
        "Sắp xếp theo số dư nợ giảm dần", "Sắp xếp theo bảng chữ cái tăng dần", "Sắp xếp theo bảng chữ cái giảm dần"};

    public OutBalanceForm() {
        lst = new ArrayList<>();
        initComponents();
        comboSort.setModel(new DefaultComboBoxModel(sorts));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTop = new javax.swing.JPanel();
        lbID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        lbSort = new javax.swing.JLabel();
        comboSort = new javax.swing.JComboBox<>();
        lbSpace = new javax.swing.JLabel();
        btnFind = new javax.swing.JButton();
        btnViewAll = new javax.swing.JButton();
        scTable = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        panelBottom = new javax.swing.JPanel();
        lbTotal = new javax.swing.JLabel();
        lbTotalCalc = new javax.swing.JLabel();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        lbID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbID.setText("Tìm dư nợ theo cmnd/cccd:");
        panelTop.add(lbID);

        txtID.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtID.setPreferredSize(new java.awt.Dimension(100, 20));
        panelTop.add(txtID);

        lbSort.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbSort.setText("Sắp xếp theo:");
        lbSort.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));
        panelTop.add(lbSort);

        comboSort.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboSortItemStateChanged(evt);
            }
        });
        panelTop.add(comboSort);

        lbSpace.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 30));
        panelTop.add(lbSpace);

        btnFind.setText("Truy xuất");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });
        panelTop.add(btnFind);

        btnViewAll.setText("Xem tất cả");
        btnViewAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewAllActionPerformed(evt);
            }
        });
        panelTop.add(btnViewAll);

        add(panelTop);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CMND/CCCD", "HỌ TÊN", "ĐỊA CHỈ", "DƯ NỢ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scTable.setViewportView(table);

        add(scTable);

        panelBottom.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTotal.setText("Tổng dư nợ :");
        panelBottom.add(lbTotal);

        lbTotalCalc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTotalCalc.setText("0đ");
        panelBottom.add(lbTotalCalc);

        add(panelBottom);
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAllActionPerformed
        txtID.setText("");
        getOutBalance();
        sort();
        addToTable();
    }//GEN-LAST:event_btnViewAllActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        getOutBalance();
        sort();
        addToTable();
    }//GEN-LAST:event_btnFindActionPerformed

    private void comboSortItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboSortItemStateChanged
        sort();
        addToTable();
    }//GEN-LAST:event_comboSortItemStateChanged

    void sort() {
        try {
            int choice = comboSort.getSelectedIndex();
            if (choice == 0) {

                lst.sort(new OutBalanceSorter(true, true));
            }
            if(choice ==1){
                lst.sort(new OutBalanceSorter(false, true));
            }
            if (choice == 2) {

                lst.sort(new OutBalanceSorter(true, false));
            }
            if(choice ==3){
                lst.sort(new OutBalanceSorter(false, false));
            }
        } catch (ParseException ex) {
            Logger.getLogger(OutBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void getOutBalance() {
        lst.removeAll(lst);
        if (txtID.getText().isBlank()) {
            try {
                lst = new CovidDAO().getAllOutBalance();
            } catch (SQLServerException ex) {
                Logger.getLogger(OutBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(OutBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                OutBalanceModel model = new CovidDAO().getOutBalanceByID(txtID.getText());
                if (model != null) {
                    lst.add(model);
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLServerException ex) {
                Logger.getLogger(OutBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(OutBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    void addToTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        if (lst == null) {
            return;
        }
        total = 0;
        for (int i = 0; i < lst.size(); i++) {
            OutBalanceModel obj = lst.get(i);
            total += obj.getOutBalance();
            Object[] content = {obj.getId(), obj.getName(), obj.getAddress(), obj.getOutBalance()};
            model.addRow(content);

        }
        lbTotalCalc.setText(total + " VNĐ");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnViewAll;
    private javax.swing.JComboBox<String> comboSort;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbSort;
    private javax.swing.JLabel lbSpace;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JLabel lbTotalCalc;
    private javax.swing.JPanel panelBottom;
    private javax.swing.JPanel panelTop;
    private javax.swing.JScrollPane scTable;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtID;
    // End of variables declaration//GEN-END:variables
}
