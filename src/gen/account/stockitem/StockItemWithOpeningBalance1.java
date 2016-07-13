package gen.account.stockitem;

import gen.accountvoucher.TableCellListener;
import gen.database.connection.DatabaseConnection1;
import gen.dto.StockItemDTO;
import gen.dto.Util;
import gen.mainclass.MainClass;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class StockItemWithOpeningBalance1 extends javax.swing.JInternalFrame {

    ArrayList<StockItemTemp> finish = new ArrayList<StockItemTemp>();
    ArrayList<StockItemTemp> stockgroup = new ArrayList<StockItemTemp>();
    ArrayList<StockItemTemp> uom = new ArrayList<StockItemTemp>();
    ArrayList<StockItemData> siArray = new ArrayList<StockItemData>();
    DatabaseConnection1 db = new DatabaseConnection1();
    int i = 0;
    static long RowId = 0;
    static int flg = 0, check_For_Dot = 0;
    String lblUnder = "", strLabelBalance = "";
    private DefaultTableModel tableModel, tableModelHistory;
    List<StockItemDTO> finalStockItemDTOTransactionList = new ArrayList<StockItemDTO>();
    List<StockItemDTO> CheckStockItemDTOTransactionList = new ArrayList<StockItemDTO>();
    List<StockItemDTO> historyOfStockItemList = new ArrayList<StockItemDTO>();
    private JTextField tfDatePicker;
    private JTextField tfStockGroupItemText;
    private Vector<String> stockGroupVector = new Vector<String>();
    private Map<String, String> map_StockGroup_With_ID = new HashMap<String, String>();
    private Map<String, String> map_StockItem_With_ID = new HashMap<String, String>();
    private Map<String, String> map_StockItem_With_Group = new HashMap<String, String>();
    DefaultTableModel tableModelFinishType = new DefaultTableModel();
    /// VAlue Assign to below stings are Name of Column in Table of Database so dont change these.
    static String key_Check_Box_Length = "si_length", key_Check_Box_Width = "si_width", key_Check_Box_Thickness = "si_thickness", key_Check_Box_SI_Name = "si_name";
    List<String> list = new ArrayList<String>();

    public StockItemWithOpeningBalance1(String s) {
        try {
            setClosable(true);
            initComponents();
            tableStockItem.setRowSelectionAllowed(false);
            tableStockItem.setColumnSelectionAllowed(false);
            this.setTitle(s);
            initilize();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableStockItem = new javax.swing.JTable();
        txtStockItem = new javax.swing.JTextField();
        txtDate = new com.toedter.calendar.JDateChooser();
        comboGroupName = new javax.swing.JComboBox();
        panelJtableHistory = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHistory = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        checkBoxLength = new javax.swing.JCheckBox();
        checkBoxWidth = new javax.swing.JCheckBox();
        checkBoxThickness = new javax.swing.JCheckBox();
        checkBoxSIName = new javax.swing.JCheckBox();
        btnClear = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        tableStockItem.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tableStockItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        tableStockItem.setColumnSelectionAllowed(true);
        tableStockItem.setRowHeight(20);
        tableStockItem.setRowSelectionAllowed(false);
        tableStockItem.setTableHeader(null);
        tableStockItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableStockItemMouseClicked(evt);
            }
        });
        tableStockItem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tableStockItemFocusGained(evt);
            }
        });
        tableStockItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableStockItemKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableStockItemKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tableStockItem);

        txtStockItem.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtStockItem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStockItemFocusGained(evt);
            }
        });
        txtStockItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStockItemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStockItemKeyReleased(evt);
            }
        });

        txtDate.setDateFormatString("d MMM yyyy");

        comboGroupName.setEditable(true);
        comboGroupName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        tableHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableHistory.setFocusable(false);
        tableHistory.setRowSelectionAllowed(false);
        tableHistory.getTableHeader().setResizingAllowed(false);
        tableHistory.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableHistory);

        javax.swing.GroupLayout panelJtableHistoryLayout = new javax.swing.GroupLayout(panelJtableHistory);
        panelJtableHistory.setLayout(panelJtableHistoryLayout);
        panelJtableHistoryLayout.setHorizontalGroup(
            panelJtableHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
        );
        panelJtableHistoryLayout.setVerticalGroup(
            panelJtableHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJtableHistoryLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel1.setText("Sorting Of Stock Item By Select Order");

        checkBoxLength.setText(" Length");
        checkBoxLength.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxLengthMouseClicked(evt);
            }
        });
        checkBoxLength.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                checkBoxLengthStateChanged(evt);
            }
        });

        checkBoxWidth.setText("Width");
        checkBoxWidth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxWidthMouseClicked(evt);
            }
        });

        checkBoxThickness.setText("Thickness");
        checkBoxThickness.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxThicknessMouseClicked(evt);
            }
        });

        checkBoxSIName.setText(" Stock Item Name");
        checkBoxSIName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxSINameMouseClicked(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane3)
                                    .addComponent(txtStockItem)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(checkBoxThickness)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkBoxSIName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkBoxLength)
                                .addGap(11, 11, 11)
                                .addComponent(checkBoxWidth)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(49, 49, 49)
                        .addComponent(panelJtableHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 45, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(comboGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxSIName)
                    .addComponent(btnClear)
                    .addComponent(checkBoxThickness)
                    .addComponent(checkBoxWidth)
                    .addComponent(checkBoxLength))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(txtStockItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelJtableHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnSubmit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        btnSubmit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnSubmitFocusGained(evt);
            }
        });
        btnSubmit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSubmitKeyPressed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        btnBack.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnBackFocusGained(evt);
            }
        });
        btnBack.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBackKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit)
                    .addComponent(btnBack))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        List<StockItemDTO> stockItemDTOTransactionList = finalStockItemDTOTransactionList;
        if (stockItemDTOTransactionList != null && !stockItemDTOTransactionList.isEmpty()) {
            if (txtDate.getDate() == null) {
                JOptionPane.showMessageDialog(StockItemWithOpeningBalance1.this, gen.dto.Label.DATE_VALUE_IS_NOT_VALID);
            } else {
                //StockItemDAO.insert(stockItemDTOTransactionList);
                JOptionPane.showMessageDialog(this, gen.dto.Label.RECORD_SUBMITTED_SUCCESSFULLY);
                finalStockItemDTOTransactionList.clear();
                CheckStockItemDTOTransactionList.clear();
                gridlist("");
                txtStockItem.setText("");
                callDate();
                tfDatePicker.requestFocus();
                tableModelHistory.setRowCount(0);
                txtDate.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(this, gen.dto.Label.EDIT_FIRST_QTY);
            tfDatePicker.requestFocus();
        }
}//GEN-LAST:event_btnSubmitActionPerformed

    private void txtStockItemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockItemKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (tableStockItem.getRowCount() > 0) {
                tableStockItem.requestFocus();
                tableStockItem.setRowSelectionInterval(0, 0);
                tableStockItem.changeSelection(0, 1, false, false);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            tfStockGroupItemText.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tableStockItem.getRowCount() > 0) {
                tableStockItem.requestFocus();
                tableStockItem.setRowSelectionInterval(0, 0);
                tableStockItem.changeSelection(0, 1, false, false);
            }
        }
}//GEN-LAST:event_txtStockItemKeyPressed

    private void txtStockItemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockItemKeyReleased
        try {
            DecimalFormat df = new DecimalFormat("#.##");
            String text = txtStockItem.getText().trim();
            int size = txtStockItem.getText().trim().length();
            tableModel.setRowCount(0);
            int i = 0;

            for (StockItemDTO stockItemDTO1 : CheckStockItemDTOTransactionList) {
                Double op_balance = stockItemDTO1.getOpeningBal();
                String record = stockItemDTO1.getName();
                Long id = stockItemDTO1.getID();

                for (StockItemDTO stockItemDTO : finalStockItemDTOTransactionList) {
                    if (stockItemDTO.getName().toString().trim().equals(stockItemDTO1.getName().toString().trim())) {
                        op_balance = stockItemDTO.getOpeningBal();
                        break;
                    }
                }

                if (record.length() >= size) {
                    String s = record.substring(0, size);
                    if (s.equalsIgnoreCase(text)) {
                        int rows = tableModel.getRowCount();
                        rows++;
                        tableModel.setRowCount(rows);
                        tableStockItem.setValueAt(record, rows - 1, 0);
                        tableStockItem.setValueAt(df.format(op_balance), rows - 1, 1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}//GEN-LAST:event_txtStockItemKeyReleased

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        try {
            this.setSelected(true);
            MainClass mainClass = new MainClass();
            mainClass.menuselection(2);
            this.setClosed(true);
        } catch (java.beans.PropertyVetoException e) {
        }
}//GEN-LAST:event_btnBackActionPerformed

    private void tableStockItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableStockItemMouseClicked
        if (tableStockItem.isColumnSelected(1)) {
            flg = 0;
            int row = tableStockItem.getSelectedRow();
            for (Map.Entry<String, String> e : map_StockItem_With_ID.entrySet()) {
                if (tableStockItem.getValueAt(row, 0).toString().equals(e.getKey())) {
                    RowId = Long.parseLong(e.getValue());
                }
            }

            txtStockItem.setText("");
            try {
                Connection conn = db.GetConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from tblstockitem where si_id=" + RowId + "");
                if (rs.next()) {
                    long id = rs.getLong("si_under");
                    String unitname = "";
                    i = 0;
                    while (i < uom.size()) {
                        if (uom.get(i).getUom_id() == rs.getLong("si_unitOfMeasure")) {
                            unitname = uom.get(i).getUom_name();
                        }
                        i++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}//GEN-LAST:event_tableStockItemMouseClicked

    private void tableStockItemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableStockItemKeyPressed
        //-----------------------------------------------
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tableStockItem.getRowCount() - 1 == tableStockItem.getSelectedRow()) {
                btnSubmit.requestFocus();
            }
            check_For_Dot = 0;
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtStockItem.requestFocus();
            tableStockItem.setRowSelectionInterval(0, 0);
            tableStockItem.changeSelection(0, 0, false, false);
            tableStockItem.clearSelection();
            check_For_Dot = 0;
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if ((tableStockItem.getRowCount() - 1) == tableStockItem.getSelectedRow()) {
                btnSubmit.requestFocus();
            }
            panelJtableHistory.setVisible(false);
            tableModelHistory.setRowCount(0);
            check_For_Dot = 0;
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (tableStockItem.getSelectedRow() == 0) {
                txtStockItem.requestFocus();
            }
            panelJtableHistory.setVisible(false);
            tableModelHistory.setRowCount(0);
            check_For_Dot = 0;
        } else if (tableStockItem.getSelectedRow() == 0) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                txtStockItem.requestFocus();
            }
            check_For_Dot = 0;
        }
        //------------------------------------------------
}//GEN-LAST:event_tableStockItemKeyPressed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        callMapStockItemWithID();
        callMapStockItemWithGroup();

        btnSubmit.setMnemonic(KeyEvent.VK_S);
        btnBack.setMnemonic(KeyEvent.VK_B);
        try {
            Connection conn = db.GetConnection();
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("select sg_id,sg_name from tblstockgroup");
            while (rs.next()) {
                if (rs.getString("sg_name").equals("Primary") == false) {
                    StockItemTemp s = new StockItemTemp();
                    s.setSg_id(rs.getInt("sg_id"));
                    s.setSg_name(rs.getString("sg_name"));
                    stockgroup.add(s);
                }
            }

            rs = st.executeQuery("select f_id,f_name from tblfinishtype");
            int i = 0;
            while (rs.next()) {
                StockItemTemp f = new StockItemTemp();
                f.setId(rs.getInt("f_id"));
                f.setName(rs.getString("f_name"));
                finish.add(f);
                i++;
            }

            rs = st.executeQuery("select uom_id,uom_formalName from tblunitofmeasure");
            while (rs.next()) {
                StockItemTemp u = new StockItemTemp();
                u.setUom_id(rs.getInt("uom_id"));
                u.setUom_name(rs.getString("uom_formalName"));
                uom.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tfDatePicker.requestFocus();
        txtDate.requestFocus();
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnSubmitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSubmitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (tableStockItem.getRowCount() > 0) {
                tableStockItem.requestFocus();
                // Select a last row in tableRawMaterialItems
                tableStockItem.setRowSelectionInterval(tableStockItem.getRowCount() - 1, tableStockItem.getRowCount() - 1);
            } else {
                txtStockItem.requestFocus();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnSubmitActionPerformed(null);
            tfDatePicker.requestFocus();
            txtDate.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnBack.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (tableStockItem.getRowCount() > 0) {
                tableStockItem.requestFocus();
                // Select a last row in tableRawMaterialItems
                tableStockItem.setRowSelectionInterval(tableStockItem.getRowCount() - 1, tableStockItem.getRowCount() - 1);
            } else {
                txtStockItem.requestFocus();
            }
        }

    }//GEN-LAST:event_btnSubmitKeyPressed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        gridlist("");
        comboStockGroupCall();
        callDate();
        panelJtableHistory.setVisible(false);
        tfDatePicker.requestFocus();
        txtDate.requestFocus();
        System.out.println("Frame Activated.........................");
    }//GEN-LAST:event_formInternalFrameActivated

    private void txtStockItemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStockItemFocusGained
        // TODO add your handling code here:
        txtStockItem.selectAll();
        panelJtableHistory.setVisible(false);
        tableModelHistory.setRowCount(0);
        check_For_Dot = 0;
    }//GEN-LAST:event_txtStockItemFocusGained

    private void tableStockItemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableStockItemKeyTyped
        // TODO add your handling code here:

        String value = tableStockItem.getValueAt(tableStockItem.getSelectedRow(), 1).toString();
        char c = evt.getKeyChar();


        if (c == '.') {
            check_For_Dot++;
        }

        if (!Character.isDigit(c) && c != '.') {
            evt.consume();
        } else if (c == '.') {
            if (!value.contains(".")) {
                if (check_For_Dot != 1) {
                    evt.consume();
                }
            } else {
                evt.consume();
            }
        }


        if (tableStockItem.getSelectedRow() >= 0) {
            if (tableStockItem.getSelectedColumn() == 1) {
                panelJtableHistory.setVisible(true);
                tableModelHistory.setRowCount(0);
                historyTabel(tableStockItem.getSelectedRow());
            }
        }
    }//GEN-LAST:event_tableStockItemKeyTyped

    private void btnBackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBackKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (tableStockItem.getRowCount() > 0) {
                tableStockItem.requestFocus();
                tableStockItem.setRowSelectionInterval(tableStockItem.getRowCount() - 1, tableStockItem.getRowCount() - 1);
            } else {
                txtStockItem.requestFocus();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnBackActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnSubmit.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (tableStockItem.getRowCount() > 0) {
                tableStockItem.requestFocus();
                // Select a last row in tableRawMaterialItems
                tableStockItem.setRowSelectionInterval(tableStockItem.getRowCount() - 1, tableStockItem.getRowCount() - 1);
            } else {
                txtStockItem.requestFocus();
            }
        }
    }//GEN-LAST:event_btnBackKeyPressed

    private void btnSubmitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnSubmitFocusGained
        panelJtableHistory.setVisible(false);
        tableModelHistory.setRowCount(0);
        check_For_Dot = 0;
    }//GEN-LAST:event_btnSubmitFocusGained

    private void btnBackFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnBackFocusGained
        panelJtableHistory.setVisible(false);
        tableModelHistory.setRowCount(0);
        check_For_Dot = 0;
    }//GEN-LAST:event_btnBackFocusGained

    private void tableStockItemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tableStockItemFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tableStockItemFocusGained

    private void checkBoxLengthMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkBoxLengthMouseClicked
        // TODO add your handling code here:


        if (checkBoxLength.isSelected()) {
            list.add(key_Check_Box_Length);
        } else {
            list.remove(key_Check_Box_Length);
        }
        getStockItemCall();

    }//GEN-LAST:event_checkBoxLengthMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:

        list.clear();
        getStockItemCall();
        checkBoxLength.setSelected(false);
        checkBoxWidth.setSelected(false);
        checkBoxThickness.setSelected(false);
        checkBoxSIName.setSelected(false);
    }//GEN-LAST:event_btnClearActionPerformed

    private void checkBoxLengthStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_checkBoxLengthStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxLengthStateChanged

    private void checkBoxWidthMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkBoxWidthMouseClicked
        // TODO add your handling code here:

        if (checkBoxWidth.isSelected()) {
            list.add(key_Check_Box_Width);
        } else {
            list.remove(key_Check_Box_Width);
        }
        getStockItemCall();
    }//GEN-LAST:event_checkBoxWidthMouseClicked

    private void checkBoxThicknessMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkBoxThicknessMouseClicked
        // TODO add your handling code here:

        if (checkBoxThickness.isSelected()) {
            list.add(key_Check_Box_Thickness);
        } else {
            list.remove(key_Check_Box_Thickness);
        }

        getStockItemCall();

    }//GEN-LAST:event_checkBoxThicknessMouseClicked

    private void checkBoxSINameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkBoxSINameMouseClicked
        // TODO add your handling code here:

        if (checkBoxSIName.isSelected()) {
            list.add(key_Check_Box_SI_Name);
        } else {
            list.remove(key_Check_Box_SI_Name);
        }
        getStockItemCall();
    }//GEN-LAST:event_checkBoxSINameMouseClicked

    public void gridlist(String stockGroupName) {
        Connection conn = null;
        CheckStockItemDTOTransactionList.clear();
        DecimalFormat df = new DecimalFormat("#.##");
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            long stkgrpid = 0L;
            String tempsykgrp = "";
            for (Map.Entry<String, String> e : map_StockGroup_With_ID.entrySet()) {
                if (e.getKey().toString().equalsIgnoreCase(stockGroupName)) {
                    tempsykgrp = e.getValue();
                }
            }
//
            if (tempsykgrp.trim().length() != 0) {
                stkgrpid = Long.parseLong(tempsykgrp);
                //CheckStockItemDTOTransactionList = StockItemDAO.getStockItemWithUpdateBLWithDateList(tempsykgrp,list);
            } else {
                //CheckStockItemDTOTransactionList = StockItemDAO.getStockItemWithUpdateBLWithDateList("",list);
            }

            int i = 0;
            tableStockItem.removeAll();
            try {
                int size = txtStockItem.getText().trim().length();
                tableModel.setRowCount(0);
                for (StockItemDTO stockItemDTO1 : CheckStockItemDTOTransactionList) {
                    String record = stockItemDTO1.getName();
                    Long id = stockItemDTO1.getID();
                    Double op_balance = stockItemDTO1.getOpeningBal();
                    if (record.length() >= size) {
                        int rows = tableModel.getRowCount();
                        rows++;
                        tableModel.setRowCount(rows);
                        tableStockItem.setValueAt(record, rows - 1, 0);
                        tableStockItem.setValueAt(df.format(op_balance), rows - 1, 1);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(StockItemWithOpeningBalance1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
////////////////////      Didnot Use Below Code       /////////////////////////////////////
//    public void gridlist(String stockGroupName) {
//        Connection conn = null;
//        DecimalFormat df = new DecimalFormat("#.##");
//        try {
//            conn = DatabaseConnection1.GetConnection();
//            Statement st = conn.createStatement();
//            int i = 0;
//            if (stockGroupName.length()==0) {
//                ResultSet rs = st.executeQuery("select * from tblstockitem order by si_name Asc");
//                siArray.clear();
//                while (rs.next()) {
//                    StockItemData gr1 = new StockItemData();
//                    gr1.setId(rs.getInt("si_id"));
//                    gr1.setName(rs.getString("si_name"));
//                    gr1.setOpening_balance(rs.getDouble("si_openingBalance"));
//                    siArray.add(gr1);
//                }
//            } else {
//                long stkgrpid = 0L;
//                String tempsykgrp = "";
//                for (Map.Entry<String,String> e : map_StockGroup_With_ID.entrySet()) {
//                     if(e.getKey().toString().equalsIgnoreCase(stockGroupName)){
//                         tempsykgrp = e.getValue();
//                     }
//                }
//                
//                stkgrpid = Long.parseLong(tempsykgrp);
//                String query = "select * from tblstockitem where  si_under =" + stkgrpid + "";
//                System.out.println("asasasas"+query);
//                ResultSet rs = st.executeQuery(query);
//                siArray.clear();
//                while (rs.next()) {
//                    StockItemData gr1 = new StockItemData();
//                    gr1.setId(rs.getInt("si_id"));
//                    gr1.setName(rs.getString("si_name"));
//                    gr1.setOpening_balance(rs.getDouble("si_openingBalance"));
//                    siArray.add(gr1);
//                }
//            }
//
//
//            i = 0;
//            tableStockItem.removeAll();
//            try {
//                int size = txtStockItem.getText().trim().length();
//                tableModel.setRowCount(0);
//
//                for (i = 0; i < siArray.size(); i++) {
//                    String record = siArray.get(i).getName();
//                    int id = siArray.get(i).getId();
//                    Double op_balance = siArray.get(i).getOpening_balance();
//
//                    if (record.length() >= size) {
//                        int rows = tableModel.getRowCount();
//                        rows++;
//                        tableModel.setRowCount(rows);
//                        tableStockItem.setValueAt(record, rows - 1, 0);
//                        tableStockItem.setValueAt(id, rows - 1, 1);
//                        tableStockItem.setValueAt(df.format(siArray.get(i).getOpening_balance()), rows - 1, 2);
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            tableStockItem.getColumnModel().getColumn(1).setWidth(0);
//            tableStockItem.getColumnModel().getColumn(1).setMinWidth(0);
//            tableStockItem.getColumnModel().getColumn(1).setMaxWidth(0);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                conn.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(StockItem_Alter1.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
////////////////////      Didnot Use Above Code       /////////////////////////////////////    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JCheckBox checkBoxLength;
    private javax.swing.JCheckBox checkBoxSIName;
    private javax.swing.JCheckBox checkBoxThickness;
    private javax.swing.JCheckBox checkBoxWidth;
    private javax.swing.JComboBox comboGroupName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel panelJtableHistory;
    private javax.swing.JTable tableHistory;
    private javax.swing.JTable tableStockItem;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JTextField txtStockItem;
    // End of variables declaration//GEN-END:variables

    //private methods
    private void initilize() throws SQLException {
        initilizeGUIComponents();
        setnemonic();
    }

    private void initilizeGUIComponents() {
        initJTable();
        initHistoryJTable();
        initilizeDatePickerField();
        initlizeFinishItemField();
    }
    private boolean hide_flag = false;

    private void initJTable() {
        String col[] = {"", ""};
        String data[][] = {{"", ""}};
        tableModel = new DefaultTableModel(data, col) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 1) {
                    return true;
                } else {
                    return false;//This causes all cells to be not editable
                }
            }
        };

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableCellListener tcl = (TableCellListener) e.getSource();
                int row = tcl.getRow();
                if (!tableStockItem.getValueAt(row, 1).toString().trim().equalsIgnoreCase("")) {
                    if (tableStockItem.getEditingRow() >= 0) {
                        GUITODTO(row);
                        tableModelHistory.setRowCount(0);
                        historyTabel(row);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You can not enter Blank Value");
                    tableStockItem.setRowSelectionInterval(row - 1, 0);
                }
                check_For_Dot = 0;
            }
        };

        TableCellListener tcl = new TableCellListener(tableStockItem, action);


        tableStockItem.setModel(tableModel);
        tableModel.setRowCount(0);
        tableModel.setColumnCount(2);
        tableStockItem.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    }

    private void initHistoryJTable() {
        String col[] = {"Date", "Actual Opening Bal", "Physical Openinig", "Difference"};
        String data[][] = {{"", "", "", ""}};
        tableModelHistory = new DefaultTableModel(data, col) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };

        tableHistory.setModel(tableModelHistory);
        JTableHeader header = tableHistory.getTableHeader();
        header.setBackground(Color.yellow);
        tableModelHistory.setRowCount(0);
        tableModelHistory.setColumnCount(4);
        tableHistory.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        Enumeration<TableColumn> en = tableHistory.getColumnModel().getColumns();
        while (en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new MyTableCellRenderer());
        }
    }

    private void setnemonic() {
        btnSubmit.setMnemonic(KeyEvent.VK_S);
        btnBack.setMnemonic(KeyEvent.VK_B);
        btnClear.setMnemonic(KeyEvent.VK_C);
    }

    private void GUITODTO(int row) {
        DecimalFormat df = new DecimalFormat("#.##");
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            StockItemDTO stockitemDTO = new StockItemDTO();
            stockitemDTO.setName(tableStockItem.getValueAt(row, 0).toString().trim());

            Long si_id = 0L;
            for (Map.Entry<String, String> e : map_StockItem_With_ID.entrySet()) {
                if (e.getKey().equals(tableStockItem.getValueAt(row, 0).toString().trim())) {
                    stockitemDTO.setID(Long.parseLong(e.getValue()));
                    si_id = Long.parseLong(e.getValue());
                }
            }


            String si_under = "";
            for (Map.Entry<String, String> e : map_StockItem_With_Group.entrySet()) {
                if (si_id.toString().trim().equalsIgnoreCase(e.getKey())) {
                    si_under = e.getValue();
                }
            }

            stockitemDTO.setUnder(si_under);
            String opening_balance = df.format(Double.parseDouble(tableStockItem.getValueAt(row, 1).toString().trim()));
            stockitemDTO.setOpeningBal(Double.parseDouble(opening_balance));

            if (txtDate.getDate() != null) {
                String date = format.format(txtDate.getDate());
                stockitemDTO.setUpdate_opening_balance_date(date);
            } else {
                JOptionPane.showMessageDialog(this, "Enter Correct Date");
                tfDatePicker.requestFocus();
            }

            finalStockItemDTOTransactionList.add(stockitemDTO);

        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(StockItemWithOpeningBalance1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initilizeDatePickerField() {
        tfDatePicker = (JTextField) txtDate.getComponent(1);
        tfDatePicker.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_ENTER) {
                    if (txtDate.getDate() == null) {
                        JOptionPane.showMessageDialog(rootPane, gen.dto.Label.DATE_VALUE_IS_NOT_VALID);
                        tfDatePicker.requestFocus();
                    } else {
                        tfStockGroupItemText.requestFocus();
                    }
                } else if (code == KeyEvent.VK_ESCAPE) {
                    btnBackActionPerformed(null);
                }
            }
        });

        tfDatePicker.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tfDatePicker.selectAll();
                panelJtableHistory.setVisible(false);
                tableModelHistory.setRowCount(0);
                check_For_Dot = 0;
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
    }

    private void initlizeFinishItemField() {
        tfStockGroupItemText = (JTextField) comboGroupName.getEditor().getEditorComponent();
        tfStockGroupItemText.setEditable(true);
        tfStockGroupItemText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String text = tfStockGroupItemText.getText();
                        if (text.length() == 0) {
                            comboGroupName.hidePopup();
                            setfinishingItemModel(new DefaultComboBoxModel(stockGroupVector), "");
                        } else {
                            DefaultComboBoxModel m = Util.getSuggestedModel(stockGroupVector, text);
                            if (m.getSize() == 0 || hide_flag) {
                                comboGroupName.hidePopup();
                                hide_flag = false;
                            } else {
                                setfinishingItemModel(m, text);
                                comboGroupName.showPopup();
                            }
                        }
                    }
                });
            }
        });
        tfStockGroupItemText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent event) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                            if (!(tfStockGroupItemText.getText().equalsIgnoreCase(""))) {
                                getStockItemCall();
                            } else {
                                getStockItemCall();
                            }
                            txtStockItem.setText("");
                            txtStockItem.requestFocus();
                        } else if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            tfDatePicker.requestFocus();
                        }
                    }
                });
            }
        });

        tfStockGroupItemText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String text = tfStockGroupItemText.getText();
                if (text.length() == 0) {
                    comboGroupName.hidePopup();
                    setfinishingItemModel(new DefaultComboBoxModel(stockGroupVector), "");
                } else {
                    DefaultComboBoxModel m = Util.getSuggestedModel(stockGroupVector, text);
                    if (m.getSize() == 0 || hide_flag) {
                        comboGroupName.hidePopup();
                        hide_flag = false;
                    } else {
                        setfinishingItemModel(m, text);
                        comboGroupName.showPopup();
                    }
                }
                tfStockGroupItemText.selectAll();
                panelJtableHistory.setVisible(false);
                tableModelHistory.setRowCount(0);
                check_For_Dot = 0;
            }

            @Override
            public void focusLost(FocusEvent e) {
                comboGroupName.hidePopup();
                if (!(tfStockGroupItemText.getText().equalsIgnoreCase(""))) {
                } else {
                }
            }
        });
    }

    private void setfinishingItemModel(DefaultComboBoxModel mdl, String str) {
        comboGroupName.setModel(mdl);
        comboGroupName.setSelectedIndex(-1);
        tfStockGroupItemText.setText(str);
    }

    /* 
     * set Items in ComboFinishItem 
     */
    private void comboStockGroupCall() {
        try {
            comboGroupName.removeAllItems();
            map_StockGroup_With_ID.clear();
            stockGroupVector.removeAllElements();

            // map_StockGroup_With_ID = StockItemDAO.getStockGroupWithIDList();

            comboGroupName.addItem("");

            if (map_StockGroup_With_ID != null && map_StockGroup_With_ID.size() > 0) {
                for (String finishStockItem : map_StockGroup_With_ID.keySet()) {
                    comboGroupName.addItem(finishStockItem);
                    stockGroupVector.addElement(finishStockItem);
                }
            }
        } catch (Exception ex) {
            //Logger.getLogger(StockItemWithOpeningBalance1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* 
     * set Items in ComboFinishItem 
     */
    private void getStockItemCall() {
        //try {
        gridlist(tfStockGroupItemText.getText().toString());
    }

    private void callDate() {
        txtDate.setDateFormatString("dd-MM-yyyy");
        Calendar currentDate = Calendar.getInstance();
        txtDate.setDate(currentDate.getTime());
    }

    private void historyTabel(int row) {
        try {
            DecimalFormat df = new DecimalFormat("#.##");
            Long si_id = 0l;
            for (Map.Entry<String, String> e : map_StockItem_With_ID.entrySet()) {
                if (tableStockItem.getValueAt(row, 0).toString().equals(e.getKey())) {
                    si_id = Long.parseLong(e.getValue());
                }
            }

            int i = 1;
            //historyOfStockItemList = StockItemDAO.getStockItemHistory(si_id);
            tableHistory.removeAll();
            try {
                for (StockItemDTO stockItemDTO : historyOfStockItemList) {


                    if (i < historyOfStockItemList.size()) {
                        tableModelHistory.setRowCount(tableHistory.getRowCount() + 1);
                        tableHistory.setValueAt(stockItemDTO.getUpdate_opening_balance_date(), tableHistory.getRowCount() - 1, 0);

                        calculate_Closing_Balance(stockItemDTO.getUpdate_opening_balance_date(), si_id);
                        tableHistory.setValueAt(df.format(stockItemDTO.getOpeningBal()), tableHistory.getRowCount() - 1, 2);

                        Double difference_between_Opening = Double.parseDouble(tableHistory.getValueAt(tableHistory.getRowCount() - 1, 2).toString()) - Double.parseDouble(tableHistory.getValueAt(tableHistory.getRowCount() - 1, 1).toString());
                        tableHistory.setValueAt(df.format(difference_between_Opening), tableHistory.getRowCount() - 1, 3);
                    } else {
                        tableModelHistory.setRowCount(tableHistory.getRowCount() + 1);
                        tableHistory.setValueAt("Stk OPl Balance", tableHistory.getRowCount() - 1, 0);
                        tableHistory.setValueAt(df.format(stockItemDTO.getOpeningBal()), tableHistory.getRowCount() - 1, 1);
                        tableHistory.setValueAt("", tableHistory.getRowCount() - 1, 2);
                        tableHistory.setValueAt("", tableHistory.getRowCount() - 1, 3);
                    }
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //Logger.getLogger(StockItemWithOpeningBalance1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void callMapStockItemWithID() {
        try {
            map_StockItem_With_ID.clear();
            //map_StockItem_With_ID = StockItemDAO.getStockItemWithIDList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callMapStockItemWithGroup() {
        try {
            map_StockItem_With_Group.clear();
            //map_StockItem_With_Group = StockItemDAO.getStockItem_With_Group();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void calculate_Closing_Balance(String date, Long item_id) {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            Statement st1 = conn.createStatement();
            Statement st2 = conn.createStatement();
            Statement st3 = conn.createStatement();
            long deb_qty1 = 0;
            long deb_qty2 = 0;
            long cred_qty3 = 0;
            ResultSet rs1, rs2, rs3, rs4;
            String q = "", last_But_OneDate = "";
            Double openBal = 0D;
            DecimalFormat df = new DecimalFormat("#.##");

            q = "select * from tblstockitemopblupdaterecord where si_id = " + item_id + " and update_opening_balance_date < '" + date + "' order by update_opening_balance_date ASC";
            rs4 = st.executeQuery(q);
            while (rs4.next()) {
                last_But_OneDate = rs4.getString("update_opening_balance_date");
                openBal = rs4.getDouble("si_openingBalance");
            }

            q = "select trans_id,trans_receiptNo,trans_typeIndex,trans_date from tbltransactionmain where trans_date >= '" + last_But_OneDate + "' and trans_date < '" + date + "' order by trans_date";
            rs1 = st1.executeQuery(q);
            while (rs1.next()) {
                q = "select invtrans_id,invtrans_type from tblinventorytransaction where trans_id=" + rs1.getLong("trans_id") + "";
                rs2 = st2.executeQuery(q);
                if (rs2.next()) {
                    q = "select invtrans_qty from tblinventorytransactionitems where invtrans_id=" + rs2.getLong("invtrans_id") + " and invtrans_itemId=" + item_id + "";
                    rs3 = st3.executeQuery(q);
                    while (rs3.next()) {
                        //Debitted Amount
                        if (rs2.getInt("invtrans_type") == 1) {
                            deb_qty1 = deb_qty1 + rs3.getLong("invtrans_qty");
                        } else if (rs2.getInt("invtrans_type") == 2) {
                            cred_qty3 = cred_qty3 + rs3.getLong("invtrans_qty");
                        } else if (rs2.getInt("invtrans_type") == 7) {
                            deb_qty2 = deb_qty2 + rs3.getLong("invtrans_qty");
                        }
                    }
                }
            }
            Long str = deb_qty1 + deb_qty2;
            double cl = 0F;
            cl = openBal + cred_qty3 - str;
            tableHistory.setValueAt(df.format(cl), tableHistory.getRowCount() - 1, 1);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(StockItemWithOpeningBalance1.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public class MyTableCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setBackground(null);
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            c.setBackground(Color.WHITE);

            return this;
        }
    }

    public static void filterCharacter(java.awt.event.KeyEvent evt, javax.swing.JTextField jtxtField) {
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '.' && c != '-') {
            evt.consume();
        }
        int i;

        if (c == '.') {
            int flg = 0;
            i = 0;
            while (i < jtxtField.getText().trim().length()) {
                if (jtxtField.getText().trim().charAt(i) == '.') {
                    flg = 1;
                    break;
                }
                i++;
            }

            if (flg == 1) {
                evt.consume();
            }
        }
    }
}