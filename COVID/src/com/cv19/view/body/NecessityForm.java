/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.cv19.view.body;

import com.Controller.CovidDAO;
import com.Controller.HistoryDAO;
import com.Model.NecessityModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import com.utils.AlignTable;
import com.utils.NecessitySorter;
import java.sql.ResultSet;

/**
 *
 * @author ThaiTran
 */
public class NecessityForm extends javax.swing.JPanel {

    /**
     * Creates new form NecessityForm
     */
    private String limitTime[] = {"ngày", "tuần", "tháng"};
    private String sort[] = {"Sắp xếp theo bảng chữ cái tăng dần", "Sắp xếp theo bảng chữ cái giảm dần",
        "Sắp xếp theo giá tăng dần", "Sắp xếp theo giá giảm dần", "Sắp xếp theo lượng tiêu thụ tăng dần", "Sắp xếp theo lượng tiêu thụ giảm dần"};
    private String filter[] = {};
    private ArrayList<NecessityModel> lst;
    private JButton btnEdit;
    private int minPrice = 0;
    private int maxPrice = 0;
    private int step = 0;
    int previousClick;

    public NecessityForm() {
        lst = new ArrayList<>();
        btnEdit = new JButton();
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnEditEvent();
            }
        });
        initComponents();

        initFilterRange();

        comboTime.setModel(new DefaultComboBoxModel(limitTime));
        comboSort.setModel(new DefaultComboBoxModel(sort));

        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.setRowHeight(30);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(5);
        columnModel.getColumn(0).setCellRenderer(AlignTable.left());
        columnModel.getColumn(1).setPreferredWidth(180);
        columnModel.getColumn(2).setPreferredWidth(30);
        columnModel.getColumn(3).setPreferredWidth(20);
        columnModel.getColumn(4).setPreferredWidth(20);
        columnModel.getColumn(4).setCellRenderer(AlignTable.right());
        columnModel.getColumn(5).setPreferredWidth(20);
        columnModel.getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox(), "Chỉnh sửa", btnEdit));
        columnModel.getColumn(5).setCellRenderer(new ButtonRenderer("Chỉnh sửa"));

        btnFindActionPerformed(null);
    }

    void btnEditEvent() {
        int id = 0;
        int row = table.getSelectedRow();
        if(row ==-1) row=previousClick;
        else previousClick=row;
        id = (int) table.getValueAt(row, 0);
        JOptionPane.showMessageDialog(null, new NecessityEditPanel(id), Integer.toString(id), JOptionPane.UNDEFINED_CONDITION);
        btnFindActionPerformed(null);
    }

    void initFilterRange() {
        try {
            ResultSet rs = new CovidDAO().excuteQuery("SELECT  MIN(n.PRICE) AS minPrice, MAX(n.PRICE) AS maxPrice\n"
                    + "FROM NECESSITIES n WHERE n.deleted!=1");
            if (rs.next()) {
                minPrice = rs.getInt(1);
                maxPrice = rs.getInt(2);
                step = Math.round(maxPrice / 3);
                String filters[] = new String[3];
                int flag = minPrice;
                comboFilter.addItem("Tất cả");
                for (int i = 0; i < 3; i++) {
                    int tmp = flag + step;
                    comboFilter.addItem("Từ " + flag + " VNĐ đến " + tmp + " VNĐ");
                    flag = tmp;
                }

            }

        } catch (SQLServerException ex) {
            Logger.getLogger(NecessityForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NecessityForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelTop = new javax.swing.JPanel();
        panelInput = new javax.swing.JPanel();
        lbPackIn = new javax.swing.JLabel();
        txtPackAdd = new javax.swing.JTextField();
        lbLimitIn = new javax.swing.JLabel();
        txtLimitIn = new javax.swing.JSpinner();
        lbTimeIn = new javax.swing.JLabel();
        comboTime = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        lbPrice = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        btnReset = new javax.swing.JButton();
        taNotify = new javax.swing.JTextArea();
        panelFind = new javax.swing.JPanel();
        lbPack = new javax.swing.JLabel();
        txtPackFind = new javax.swing.JTextField();
        lbSort1 = new javax.swing.JLabel();
        comboSort = new javax.swing.JComboBox<>();
        lbFilter = new javax.swing.JLabel();
        comboFilter = new javax.swing.JComboBox<>();
        btnFind = new javax.swing.JButton();
        scTable = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        panelTop.setBorder(javax.swing.BorderFactory.createTitledBorder("Thêm"));
        panelTop.setMinimumSize(new java.awt.Dimension(390, 500));
        panelTop.setPreferredSize(new java.awt.Dimension(1001, 200));
        panelTop.setLayout(null);

        panelInput.setPreferredSize(new java.awt.Dimension(333, 200));

        lbPackIn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbPackIn.setText("Tên gói :");

        txtPackAdd.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 10, 4, 10));
        txtPackAdd.setMaximumSize(new java.awt.Dimension(0, 0));

        lbLimitIn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbLimitIn.setText("Mức giới hạn :");

        txtLimitIn.setPreferredSize(new java.awt.Dimension(30, 25));
        txtLimitIn.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                txtLimitInStateChanged(evt);
            }
        });

        lbTimeIn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTimeIn.setText("Thời gian giới hạn theo");

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lbPrice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbPrice.setText("Giá tiền:");

        txtPrice.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 10, 4, 10));
        txtPrice.setMaximumSize(new java.awt.Dimension(0, 0));

        btnReset.setText("Làm mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInputLayout = new javax.swing.GroupLayout(panelInput);
        panelInput.setLayout(panelInputLayout);
        panelInputLayout.setHorizontalGroup(
            panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInputLayout.createSequentialGroup()
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addComponent(lbPackIn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(txtPackAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addComponent(lbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(txtPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addComponent(lbLimitIn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(txtLimitIn, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addComponent(lbTimeIn)
                        .addGap(5, 5, 5)
                        .addComponent(comboTime, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelInputLayout.setVerticalGroup(
            panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInputLayout.createSequentialGroup()
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lbPackIn))
                    .addComponent(txtPackAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lbPrice))
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lbLimitIn))
                    .addComponent(txtLimitIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lbTimeIn))
                    .addComponent(comboTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(btnAdd)
                .addGap(0, 0, 0)
                .addComponent(btnReset))
        );

        panelTop.add(panelInput);
        panelInput.setBounds(26, 21, 333, 200);

        taNotify.setEditable(false);
        taNotify.setColumns(50);
        taNotify.setLineWrap(true);
        taNotify.setRows(10);
        taNotify.setText(">>>Thông tin");
        panelTop.add(taNotify);
        taNotify.setBounds(379, 37, 404, 160);

        add(panelTop);

        panelFind.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        panelFind.setPreferredSize(new java.awt.Dimension(755, 60));
        panelFind.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbPack.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbPack.setText("Tên gói :");
        panelFind.add(lbPack);

        txtPackFind.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50), null));
        txtPackFind.setMargin(new java.awt.Insets(2, 2, 2, 10));
        txtPackFind.setPreferredSize(new java.awt.Dimension(150, 25));
        panelFind.add(txtPackFind);

        lbSort1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbSort1.setText("Sắp xếp theo :");
        lbSort1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));
        panelFind.add(lbSort1);

        comboSort.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboSortItemStateChanged(evt);
            }
        });
        panelFind.add(comboSort);

        lbFilter.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbFilter.setText("Lọc:");
        lbFilter.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));
        panelFind.add(lbFilter);

        comboFilter.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboFilterItemStateChanged(evt);
            }
        });
        panelFind.add(comboFilter);

        btnFind.setText("Tìm");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });
        panelFind.add(btnFind);

        add(panelFind);

        scTable.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên gói", "Mức giới hạn", "Đơn giá", "Lượng tiêu thụ", "Tuỳ chọn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        scTable.setViewportView(table);

        add(scTable);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        try {
            if (txtPackFind.getText().isBlank()) {
                try {
                    lst = new CovidDAO().getAllNecessity();
                    sortLst();
                    addToTable();
                } catch (SQLServerException ex) {
                    Logger.getLogger(NecessityForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(NecessityForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(NecessityForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }
            lst = new CovidDAO().getNecessityByName(txtPackFind.getText());
            sortLst();
            addToTable();
        } catch (SQLException ex) {
            Logger.getLogger(NecessityForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(NecessityForm.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnFindActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            if (txtPackAdd.getText().isBlank() && txtPrice.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ các trường", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            NecessityModel model = new NecessityModel();
            model.setName(txtPackAdd.getText());
            model.setLimit((int) txtLimitIn.getValue());
            model.setPrice(Integer.parseInt(txtPrice.getText()));
            if (comboTime.getSelectedIndex() == 0) {
                model.setTime_limit(1);
            }
            if (comboTime.getSelectedIndex() == 1) {
                model.setTime_limit(7);
            }
            if (comboTime.getSelectedIndex() == 2) {
                model.setTime_limit(30);
            }

            new CovidDAO().addNecessity(model);
            
            taNotify.setText(">>>Thông báo\n" + "Tên gói: " + model.getName() + "\n" + "Giá: " + model.getPrice()
                    + "\n Mức giới hạn: " + model.getLimit() + " sản phẩm/"
                    + comboTime.getSelectedItem().toString() + "\n đã được thêm");
            HistoryDAO.AddHistory("Đã thêm gói nhu yếu phẩm mới: "+ model.getName());
        } catch (SQLException ex) {
            taNotify.setText(">>>Thông báo\n Có lỗi xảy ra, thêm không thành công");
            Logger.getLogger(NecessityForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtPackAdd.setText("");
        txtPrice.setText("");
        txtLimitIn.setValue(0);
        comboTime.setSelectedIndex(0);
        taNotify.setText(">>>Thông báo");
    }//GEN-LAST:event_btnResetActionPerformed

    private void comboSortItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboSortItemStateChanged
        try {
            sortLst();
            addToTable();
        } catch (ParseException ex) {
            Logger.getLogger(NecessityForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_comboSortItemStateChanged

    private void comboFilterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboFilterItemStateChanged
        try {
            sortLst();
            addToTable();
        } catch (ParseException ex) {
            Logger.getLogger(NecessityForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_comboFilterItemStateChanged

    private void txtLimitInStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_txtLimitInStateChanged
       if((int)txtLimitIn.getValue()<=0){
           txtLimitIn.setValue(0);
       }
    }//GEN-LAST:event_txtLimitInStateChanged

    void sortLst() throws ParseException {
        if(lst==null) return;
        int selectedItem = comboSort.getSelectedIndex();

        if (selectedItem == 0) {
            lst.sort(new NecessitySorter(true, false, false));
        } else if (selectedItem == 1) {
            lst.sort(new NecessitySorter(false, false, false));
        } else if (selectedItem == 2) {
            lst.sort(new NecessitySorter(true, true, false));
        } else if (selectedItem == 3) {
            lst.sort(new NecessitySorter(false, true, false));
        } else if (selectedItem == 3) {
            lst.sort(new NecessitySorter(true, false, true));
        } else if (selectedItem == 4) {
            lst.sort(new NecessitySorter(false, false, true));
        }

        int choice = comboFilter.getSelectedIndex();

        if (choice == 0) {
            return;
        } else {
            int min = minPrice + (choice - 1) * step;
            int max = min + step;
            System.out.println(max);
            System.out.println(min);

            for (int i = 0; i < lst.size(); i++) {
                if (lst.get(i).getPrice() < min || lst.get(i).getPrice() > max) {
                    lst.remove(lst.get(i));
                    i--;
                }
            }
        }

    }

    void addToTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        if (lst == null) {
            return;
        }
        for (int i = 0; i < lst.size(); i++) {
            NecessityModel obj;
            String unit = new String();
            obj = lst.get(i);
            if (obj.getTime_limit() == 7) {
                unit = "tuần";
            } else if (obj.getTime_limit() == 30) {
                unit = "tháng";
            } else {
                unit = "ngày";
            }
            Object[] content = {obj.getId(), obj.getName(), obj.getLimit() + " sản phẩm/" + unit, obj.getPrice() + " VNĐ", obj.getConsume()};
            model.addRow(content);

        }
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer(String text) {
            setText(text);
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {

        private String label;
        private JButton button;

        public ButtonEditor(JCheckBox checkBox, String label, JButton button) {
            super(checkBox);
            this.label = label;
            this.button = button;
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            this.button.setText(label);
            return this.button;
        }

        public Object getCellEditorValue() {
            return label;
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> comboFilter;
    private javax.swing.JComboBox<String> comboSort;
    private javax.swing.JComboBox<String> comboTime;
    private javax.swing.JLabel lbFilter;
    private javax.swing.JLabel lbLimitIn;
    private javax.swing.JLabel lbPack;
    private javax.swing.JLabel lbPackIn;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel lbSort1;
    private javax.swing.JLabel lbTimeIn;
    private javax.swing.JPanel panelFind;
    private javax.swing.JPanel panelInput;
    private javax.swing.JPanel panelTop;
    private javax.swing.JScrollPane scTable;
    private javax.swing.JTextArea taNotify;
    private javax.swing.JTable table;
    private javax.swing.JSpinner txtLimitIn;
    private javax.swing.JTextField txtPackAdd;
    private javax.swing.JTextField txtPackFind;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables
}
