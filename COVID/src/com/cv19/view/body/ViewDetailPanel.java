/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.cv19.view.body;

import Controller.CovidDAO;
import Model.AddressModel;
import Model.QuarantineModel;
import Model.profileModel;
import com.cv19.view.components.AddressCombobox;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author ThaiTran
 */
public class ViewDetailPanel extends javax.swing.JPanel {

    String dates[]
            = {"1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25",
                "26", "27", "28", "29", "30",
                "31"};

    String months[]
            = {"1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10",
                "11", "12"};
    String years[]
            = {"1987", "1988", "1989", "1990",
                "1991", "1992", "1993", "1994",
                "1995", "1996", "1997", "1998",
                "1999", "2000", "2001", "2002",
                "2003", "2004", "2005", "2006",
                "2007", "2008", "2009", "2010",
                "2011", "2012", "2013", "2014",
                "2015", "2016", "2017", "2018",
                "2019", "2020", "2021", "2022"};
    String status[] = {"F0", "F1", "F2", "F3", "F4+", "Khỏi bệnh"};
    AddressCombobox comboAddr;
    ArrayList<QuarantineModel> lstQua;
    profileModel user;
    QuarantineModel nullQua;

    public ViewDetailPanel(String id) {
        try {
            initComponents();

            nullQua = new QuarantineModel(-1, "Chọn khu điều trị", "", -1, -1, -1);

            txtID.setText(id);
            comboDay.setModel(new DefaultComboBoxModel(dates));
            comboMonth.setModel(new DefaultComboBoxModel(months));
            comboYear.setModel(new DefaultComboBoxModel(years));
            comboSta.setModel(new DefaultComboBoxModel(status));
            comboAddr = new AddressCombobox(comboCity, comboDist, comboVill);
            lstQua = new CovidDAO().getAllQuarantine();

            comboQua.addItem(nullQua);
            for (int i = 0; i < lstQua.size(); i++) {
                comboQua.addItem(lstQua.get(i));
            }

            user = new CovidDAO().getProfileUser(id);
            txtName.setText(user.getFullname());
            txtPhone.setText(user.getPhone());
            comboQua.setSelectedItem(findQua(user.getId_qua()));
            comboSta.setSelectedItem(user.getStatus());
             
            System.out.println(user.getId_qua());
//            String date = "2015-04-12";
//            java.sql.Date dat = java.sql.Date.valueOf(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(user.getDob());
            String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
            String day = Integer.toHexString(cal.get(Calendar.DAY_OF_MONTH));
            String year = Integer.toString(cal.get(Calendar.YEAR));

            comboDay.setSelectedItem(day);
            comboMonth.setSelectedItem(month);
            comboYear.setSelectedItem(year);
            comboGender.setSelectedItem(user.getGender());
            
            setAddr();

        } catch (SQLServerException ex) {
            Logger.getLogger(ViewDetailPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ViewDetailPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    QuarantineModel findQua(int id) {
        for (int i = 0; i < lstQua.size(); i++) {
            if (id == lstQua.get(i).getId()) {
                return lstQua.get(i);
            }
        }
        return nullQua;
    }
    
    void setAddr(){
        System.out.println(user.getProvince());
        comboAddr.setProvince(user.getProvince());
        comboAddr.setDist(user.getDistrict());
        comboAddr.setVill(user.getVillage());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTop = new javax.swing.JPanel();
        lbHeader = new javax.swing.JLabel();
        panelMid = new javax.swing.JPanel();
        panelInput = new javax.swing.JPanel();
        lbID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        lbName = new javax.swing.JLabel();
        txtSrc = new javax.swing.JTextField();
        lbAddr = new javax.swing.JLabel();
        comboCity = new javax.swing.JComboBox<>();
        comboDist = new javax.swing.JComboBox<>();
        comboVill = new javax.swing.JComboBox<>();
        lbSta = new javax.swing.JLabel();
        comboSta = new javax.swing.JComboBox<>();
        lbTreat = new javax.swing.JLabel();
        comboQua = new javax.swing.JComboBox<>();
        lbSrc = new javax.swing.JLabel();
        btnSrc = new javax.swing.JButton();
        btnDelSrc = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        lbPhone = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        lbDob = new javax.swing.JLabel();
        comboDay = new javax.swing.JComboBox<>();
        comboYear = new javax.swing.JComboBox<>();
        comboMonth = new javax.swing.JComboBox<>();
        lbGender = new javax.swing.JLabel();
        comboGender = new javax.swing.JComboBox<>();
        panelBtn = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        tableRelatedPanel = new javax.swing.JPanel();
        lbTable = new javax.swing.JLabel();
        scTable = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        panelTitle = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        scTable2 = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();

        setMaximumSize(new java.awt.Dimension(32767, 850));
        setPreferredSize(new java.awt.Dimension(1100, 661));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        lbHeader.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbHeader.setText("Thông tin chi tiết");
        panelTop.add(lbHeader);

        add(panelTop);

        panelMid.setMaximumSize(new java.awt.Dimension(32767, 500));
        panelMid.setPreferredSize(new java.awt.Dimension(800, 400));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT);
        flowLayout1.setAlignOnBaseline(true);
        panelMid.setLayout(flowLayout1);

        lbID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbID.setText("CMND/CCCD:");

        txtID.setEditable(false);
        txtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtID.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 10, 4, 10));

        lbName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbName.setText("Họ tên:");

        txtSrc.setEditable(false);
        txtSrc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSrc.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 10, 4, 10));

        lbAddr.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbAddr.setText("Địa chỉ:");

        lbSta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbSta.setText("Trạng thái:");

        lbTreat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTreat.setText("Nơi điều trị:");

        lbSrc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbSrc.setText("Nguồn lây nhiễm (nếu có)");

        btnSrc.setText("Chọn");
        btnSrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSrcActionPerformed(evt);
            }
        });

        btnDelSrc.setText("Xoá");

        txtName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtName.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 10, 4, 10));

        lbPhone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbPhone.setText("Số điện thoại:");

        txtPhone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPhone.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 10, 4, 10));

        lbDob.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbDob.setText("Ngày sinh:");

        lbGender.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbGender.setText("Giới tính");

        comboGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        javax.swing.GroupLayout panelInputLayout = new javax.swing.GroupLayout(panelInput);
        panelInput.setLayout(panelInputLayout);
        panelInputLayout.setHorizontalGroup(
            panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInputLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addComponent(lbPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addComponent(lbSta, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(comboSta, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addComponent(lbTreat, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(comboQua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addComponent(lbSrc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSrc, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106)
                        .addComponent(btnSrc)
                        .addGap(12, 12, 12)
                        .addComponent(btnDelSrc))
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addComponent(lbAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboCity, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboDist, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboVill, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelInputLayout.createSequentialGroup()
                                .addComponent(lbID)
                                .addGap(4, 4, 4)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelInputLayout.createSequentialGroup()
                                .addComponent(lbDob, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(comboDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(comboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(comboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbName)
                            .addComponent(lbGender))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        panelInputLayout.setVerticalGroup(
            panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInputLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbID)
                            .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbName)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDob)
                    .addComponent(comboDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbGender)
                        .addComponent(comboGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbPhone))
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbAddr)
                    .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboDist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboVill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSta)
                    .addComponent(comboSta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTreat)
                    .addComponent(comboQua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbSrc))
                    .addComponent(txtSrc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSrc, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelSrc, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        panelMid.add(panelInput);

        panelBtn.setLayout(new java.awt.GridLayout(4, 1, 0, 10));

        btnUpdate.setText("Cập nhật");
        panelBtn.add(btnUpdate);

        btnDel.setText("Xoá");
        panelBtn.add(btnDel);

        panelMid.add(panelBtn);

        tableRelatedPanel.setMaximumSize(new java.awt.Dimension(400, 300));
        tableRelatedPanel.setPreferredSize(new java.awt.Dimension(350, 300));
        tableRelatedPanel.setLayout(new javax.swing.BoxLayout(tableRelatedPanel, javax.swing.BoxLayout.Y_AXIS));

        lbTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTable.setText("Danh sách người liên quan");
        tableRelatedPanel.add(lbTable);

        scTable.setMaximumSize(new java.awt.Dimension(700, 400));
        scTable.setPreferredSize(new java.awt.Dimension(400, 500));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "CMND/CCCD", "Họ tên", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scTable.setViewportView(table);

        tableRelatedPanel.add(scTable);

        panelMid.add(tableRelatedPanel);

        add(panelMid);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Lịch sử chuyển trạng thái");
        panelTitle.add(jLabel1);

        add(panelTitle);

        scTable2.setMaximumSize(new java.awt.Dimension(1000, 300));
        scTable2.setPreferredSize(new java.awt.Dimension(452, 300));

        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Thời gian", "Trạng thái", "Nơi điều trị"
            }
        ));
        historyTable.setFillsViewportHeight(true);
        historyTable.setShowGrid(true);
        scTable2.setViewportView(historyTable);

        add(scTable2);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSrcActionPerformed

    }//GEN-LAST:event_btnSrcActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnDelSrc;
    private javax.swing.JButton btnSrc;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<AddressModel> comboCity;
    private javax.swing.JComboBox<String> comboDay;
    private javax.swing.JComboBox<AddressModel> comboDist;
    private javax.swing.JComboBox<String> comboGender;
    private javax.swing.JComboBox<String> comboMonth;
    private javax.swing.JComboBox<QuarantineModel> comboQua;
    private javax.swing.JComboBox<String> comboSta;
    private javax.swing.JComboBox<AddressModel> comboVill;
    private javax.swing.JComboBox<String> comboYear;
    private javax.swing.JTable historyTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbAddr;
    private javax.swing.JLabel lbDob;
    private javax.swing.JLabel lbGender;
    private javax.swing.JLabel lbHeader;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPhone;
    private javax.swing.JLabel lbSrc;
    private javax.swing.JLabel lbSta;
    private javax.swing.JLabel lbTable;
    private javax.swing.JLabel lbTreat;
    private javax.swing.JPanel panelBtn;
    private javax.swing.JPanel panelInput;
    private javax.swing.JPanel panelMid;
    private javax.swing.JPanel panelTitle;
    private javax.swing.JPanel panelTop;
    private javax.swing.JScrollPane scTable;
    private javax.swing.JScrollPane scTable2;
    private javax.swing.JTable table;
    private javax.swing.JPanel tableRelatedPanel;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSrc;
    // End of variables declaration//GEN-END:variables
}