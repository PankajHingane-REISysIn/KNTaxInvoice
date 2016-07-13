package gen.account.stockitem;

import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.mainclass.MainClass;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class StockItemDetails extends javax.swing.JInternalFrame {

    ArrayList<StockItemTemp> finish = new ArrayList<StockItemTemp>();
    ArrayList<StockItemTemp> stockgroup = new ArrayList<StockItemTemp>();
    ArrayList<StockItemTemp> uom = new ArrayList<StockItemTemp>();
    ArrayList<StockItemData> siArray = new ArrayList<StockItemData>();
    DatabaseConnection1 db = new DatabaseConnection1();
    int i = 0;
    static long RowId = 0;
    static int flg = 0;
    String lblunder = "";
    String strLabelBalance = "";

    public StockItemDetails(String s) {
        setClosable(true);
        initComponents();
        tableStockItem.setRowSelectionAllowed(true);
        tableStockItem.setColumnSelectionAllowed(false);
        this.setTitle(s);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        panelEntry = new javax.swing.JPanel();
        balance = new javax.swing.JLabel();
        rate = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        labelName = new javax.swing.JLabel();
        labelAlias = new javax.swing.JLabel();
        labelUnder = new javax.swing.JLabel();
        labelUnit = new javax.swing.JLabel();
        labelRate = new javax.swing.JLabel();
        labelBalance = new javax.swing.JLabel();
        btnDelete1 = new javax.swing.JButton();
        labelLength = new javax.swing.JLabel();
        labelWidth = new javax.swing.JLabel();
        labelThickness = new javax.swing.JLabel();
        PanelStockItemAlter2 = new javax.swing.JPanel();
        txtStockItem = new javax.swing.JTextField();
        btnStockItemAlter_Back = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableStockItem = new javax.swing.JTable();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
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

        panelEntry.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        balance.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        balance.setText("Opening Balance");

        rate.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rate.setText("P. Rate");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Unit");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Under");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Alias");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Name");

        btnEdit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        btnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnEditKeyPressed(evt);
            }
        });

        labelName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelName.setText("jLabel5");

        labelAlias.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelAlias.setText("jLabel5");

        labelUnder.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelUnder.setText("jLabel5");

        labelUnit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelUnit.setText("jLabel5");

        labelRate.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelRate.setText("jLabel5");

        labelBalance.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelBalance.setText("jLabel5");

        btnDelete1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnDelete1.setText("Delete");
        btnDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete1ActionPerformed(evt);
            }
        });
        btnDelete1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDelete1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout panelEntryLayout = new javax.swing.GroupLayout(panelEntry);
        panelEntry.setLayout(panelEntryLayout);
        panelEntryLayout.setHorizontalGroup(
            panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEntryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rate)
                .addGap(88, 88, 88)
                .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelRate, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelThickness, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelBalance, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                .addGap(49, 49, 49))
            .addGroup(panelEntryLayout.createSequentialGroup()
                .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEntryLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btnDelete1))
                    .addGroup(panelEntryLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(94, 94, 94)
                        .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelName, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelUnder, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelLength, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelEntryLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(balance)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEntryLayout.setVerticalGroup(
            panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEntryLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelName))
                .addGap(18, 18, 18)
                .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labelAlias))
                .addGap(18, 18, 18)
                .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelUnder))
                .addGap(18, 18, 18)
                .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelLength)
                    .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(labelUnit)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelWidth)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rate)
                    .addComponent(labelRate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelThickness)
                .addGap(13, 13, 13)
                .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(balance)
                    .addComponent(labelBalance))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete1)
                    .addComponent(btnEdit))
                .addGap(35, 35, 35))
        );

        txtStockItem.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtStockItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStockItemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStockItemKeyReleased(evt);
            }
        });

        btnStockItemAlter_Back.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnStockItemAlter_Back.setText("Back");
        btnStockItemAlter_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockItemAlter_BackActionPerformed(evt);
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
        tableStockItem.setTableHeader(null);
        tableStockItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableStockItemMouseClicked(evt);
            }
        });
        tableStockItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableStockItemKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(tableStockItem);

        javax.swing.GroupLayout PanelStockItemAlter2Layout = new javax.swing.GroupLayout(PanelStockItemAlter2);
        PanelStockItemAlter2.setLayout(PanelStockItemAlter2Layout);
        PanelStockItemAlter2Layout.setHorizontalGroup(
            PanelStockItemAlter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelStockItemAlter2Layout.createSequentialGroup()
                .addGroup(PanelStockItemAlter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelStockItemAlter2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelStockItemAlter2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtStockItem, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelStockItemAlter2Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(btnStockItemAlter_Back)))
                .addContainerGap())
        );
        PanelStockItemAlter2Layout.setVerticalGroup(
            PanelStockItemAlter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelStockItemAlter2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(txtStockItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnStockItemAlter_Back)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelStockItemAlter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelStockItemAlter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(panelEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        String name1 = labelName.getText().trim();
        if (labelName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select the Stock Group");
            txtStockItem.requestFocus();
        } else if (!name1.equals(txtStockItem.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Please select a valid Stock Item");
            txtStockItem.requestFocus();
        } else {
            StockItemAlterFromDetails s = new StockItemAlterFromDetails("Alter Stock Item", RowId);
            this.getParent().add(s);
            s.setVisible(true);
            Dimension desktopSize = this.getParent().getSize();
            Dimension jInternalFrameSize = s.getSize();
            s.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
//        s.setSize(desktopSize);
//        s.setPreferredSize(desktopSize);
            try {
                s.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
                e.printStackTrace();
            }
            btnStockItemAlter_BackActionPerformed(evt);
        }
}//GEN-LAST:event_btnEditActionPerformed

    private void txtStockItemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockItemKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (tableStockItem.getRowCount() > 0) {
                tableStockItem.requestFocus();
                tableStockItem.changeSelection(0, 0, false, false);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnStockItemAlter_BackActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tableStockItem.getRowCount() > 0) {
                tableStockItem.requestFocus();
                tableStockItem.changeSelection(0, 0, false, false);
            }
        }
}//GEN-LAST:event_txtStockItemKeyPressed

    private void txtStockItemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockItemKeyReleased
        try {
            DefaultTableModel d = (DefaultTableModel) tableStockItem.getModel();

            String text = txtStockItem.getText().trim();
            int size = txtStockItem.getText().trim().length();
            d.setRowCount(0);
            int i = 0;
            for (i = 0; i < siArray.size(); i++) {
                String record = siArray.get(i).getName();
                int id = siArray.get(i).getId();
                if (record.length() >= size) {
                    String s = record.substring(0, size);
                    if (s.equalsIgnoreCase(text)) {
                        int rows = d.getRowCount();
                        rows++;
                        d.setRowCount(rows);
                        tableStockItem.setValueAt(record, rows - 1, 0);
                        tableStockItem.setValueAt(id, rows - 1, 1);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}//GEN-LAST:event_txtStockItemKeyReleased

    private void btnStockItemAlter_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockItemAlter_BackActionPerformed
        try {
            this.setSelected(true);
            MainClass mainClass = new MainClass();
            mainClass.menuselection(2);
            this.setClosed(true);
        } catch (java.beans.PropertyVetoException e) {
        }
}//GEN-LAST:event_btnStockItemAlter_BackActionPerformed

    private void tableStockItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableStockItemMouseClicked
        if (tableStockItem.isColumnSelected(0)) {
            flg = 0;
            labelName.setText("");
            labelAlias.setText("");
            labelBalance.setText("");
            labelRate.setText("");
            labelUnder.setText("");
            labelUnit.setText("");
            labelLength.setText("");
            labelWidth.setText("");
            labelThickness.setText("");

            int row = tableStockItem.getSelectedRow();
            RowId = Long.parseLong(tableStockItem.getValueAt(row, 1).toString());

            txtStockItem.setText("");
            String name = (String) tableStockItem.getValueAt(row, 0);
            txtStockItem.setText(name);
            try {
                Connection conn = db.GetConnection();
                Statement st = conn.createStatement();
                Statement st1 = conn.createStatement();
                Statement st2 = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from tblstockitem where si_id=" + RowId + "");
                if (rs.next()) {
                    labelName.setText(rs.getString("si_name"));
                    labelAlias.setText(rs.getString("si_alias"));
                    labelBalance.setText(Constants.DECIMAL_FORMAT.format(rs.getDouble("si_openingBalance")));
                    labelRate.setText(rs.getString("si_rate"));
                    //labelLength.setText(rs.getString("si_length"));
                    //labelWidth.setText(rs.getString("si_width"));
                    //labelThickness.setText(rs.getString("si_thickness"));
                    long id = rs.getLong("si_under");

                    String unitname = "";
                    i = 0;
                    System.out.println();
                    while (i < uom.size()) {
                        if (uom.get(i).getUom_id() == rs.getLong("si_unitOfMeasure")) {
                            unitname = uom.get(i).getUom_name();

                        }
                        i++;
                    }
                    labelUnit.setText(unitname);
                    ResultSet rs1 = st1.executeQuery("select sg_name from tblstockgroup where sg_id=" + id + "");
                    if (rs1.next()) {
                        labelUnder.setText(rs1.getString("sg_name"));
                    }
                    rs1.close();
                    btnEdit.requestFocus();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        strLabelBalance = labelBalance.getText();
}//GEN-LAST:event_tableStockItemMouseClicked

    private void tableStockItemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableStockItemKeyPressed
        //-----------------------------------------------


//         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            int r=tableStockItem.getSelectedRow();
//            tableStockItemMouseClicked(null);
//             if(r>0){
//                 tableStockItem.changeSelection(r-1, 0, false, false);
//            }
//            else{
//                 tableStockItem.changeSelection(0, 0, false, false);
//            }
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
//            txtStockItem.requestFocus();
//        }


        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int r = tableStockItem.getSelectedRow();
            tableStockItemMouseClicked(null);
            if (r > 0) {
                tableStockItem.changeSelection(r - 1, 0, false, false);
            } else {
                tableStockItem.changeSelection(0, 0, false, false);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtStockItem.requestFocus();
        } else {
            int k = evt.getKeyCode();
            char c = evt.getKeyChar();
            if (k >= 48 && k <= 57) {
                txtStockItem.setText(txtStockItem.getText().trim() + "" + c);
                String text = txtStockItem.getText().trim();
                int size = text.length();
                DefaultTableModel model = new DefaultTableModel();
                tableStockItem.setModel(model);
                tableStockItem.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < siArray.size(); i++) {
                    String record = siArray.get(i).getName();

                    if (record.length() >= size) {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) {
                            int r = model.getRowCount();
                            model.setRowCount(r + 1);
                            model.setColumnCount(2);
                            tableStockItem.setValueAt(record, r, 0);
                            tableStockItem.setValueAt(siArray.get(i).getId(), r, 1);
                            tableStockItem.getColumnModel().getColumn(1).setWidth(1);
                            tableStockItem.getColumnModel().getColumn(1).setMinWidth(1);
                            tableStockItem.getColumnModel().getColumn(1).setMaxWidth(1);
                        }
                    }
                }
            } else if (k >= 65 && k <= 90) {
                txtStockItem.setText(txtStockItem.getText().trim() + "" + c);
                String text = txtStockItem.getText().trim();
                int size = text.length();
                DefaultTableModel model = new DefaultTableModel();
                tableStockItem.setModel(model);
                tableStockItem.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < siArray.size(); i++) {
                    String record = siArray.get(i).getName();

                    if (record.length() >= size) {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) {
                            int r = model.getRowCount();
                            model.setRowCount(r + 1);
                            model.setColumnCount(2);
                            tableStockItem.setValueAt(record, r, 0);
                            tableStockItem.setValueAt(siArray.get(i).getId(), r, 1);
                            tableStockItem.getColumnModel().getColumn(1).setWidth(1);
                            tableStockItem.getColumnModel().getColumn(1).setMinWidth(1);
                            tableStockItem.getColumnModel().getColumn(1).setMaxWidth(1);
                        }
                    }
                }
            } else if (k >= 96 && k <= 105) {
                txtStockItem.setText(txtStockItem.getText().trim() + "" + c);
                String text = txtStockItem.getText().trim();
                int size = text.length();
                DefaultTableModel model = new DefaultTableModel();
                tableStockItem.setModel(model);
                tableStockItem.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < siArray.size(); i++) {
                    String record = siArray.get(i).getName();

                    if (record.length() >= size) {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) {
                            int r = model.getRowCount();
                            model.setRowCount(r + 1);
                            model.setColumnCount(2);
                            tableStockItem.setValueAt(record, r, 0);
                            tableStockItem.setValueAt(siArray.get(i).getId(), r, 1);
                            tableStockItem.getColumnModel().getColumn(1).setWidth(1);
                            tableStockItem.getColumnModel().getColumn(1).setMinWidth(1);
                            tableStockItem.getColumnModel().getColumn(1).setMaxWidth(1);
                        }
                    }
                }
            } else if (k == 8) {
                //txt.getText().trim().length()=txt.getText().trim().length()-1;
                String text = txtStockItem.getText().trim();
                int size = text.length();
                String s1 = text.substring(0, size - 1);
                txtStockItem.setText(s1);
                size = txtStockItem.getText().trim().length();
                DefaultTableModel model = new DefaultTableModel();
                tableStockItem.setModel(model);
                tableStockItem.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < siArray.size(); i++) {
                    String record = siArray.get(i).getName();

                    if (record.length() >= size) {
                        String s2 = record.substring(0, size);
                        if (s2.equalsIgnoreCase(txtStockItem.getText().trim())) {
                            int r = model.getRowCount();
                            model.setRowCount(r + 1);
                            model.setColumnCount(2);
                            tableStockItem.setValueAt(record, r, 0);
                            tableStockItem.setValueAt(siArray.get(i).getId(), r, 1);
                            tableStockItem.getColumnModel().getColumn(1).setWidth(1);
                            tableStockItem.getColumnModel().getColumn(1).setMinWidth(1);
                            tableStockItem.getColumnModel().getColumn(1).setMaxWidth(1);
                        }
                    }
                }
            }
        }
        strLabelBalance = labelBalance.getText();


        //------------------------------------------------
}//GEN-LAST:event_tableStockItemKeyPressed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        btnEdit.setMnemonic(KeyEvent.VK_E);
        btnDelete1.setMnemonic(KeyEvent.VK_D);
        btnStockItemAlter_Back.setMnemonic(KeyEvent.VK_B);
        gridlist();
        labelName.setText("");
        labelAlias.setText("");
        labelUnder.setText("");
        labelUnit.setText("");
        labelRate.setText("");
        labelBalance.setText("");

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
        txtStockItem.requestFocus();

        tableStockItem.getColumnModel().getColumn(1).setWidth(1);
        tableStockItem.getColumnModel().getColumn(1).setMinWidth(1);
        tableStockItem.getColumnModel().getColumn(1).setMaxWidth(1);
        lblunder = labelUnder.getText().trim();
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete1ActionPerformed
        String name1 = labelName.getText().trim();
        if (labelName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select the Stock Item");
            txtStockItem.requestFocus();
        } else if (!name1.equals(txtStockItem.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Selected Stock Item does not exist");
            txtStockItem.requestFocus();
        } else {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want delete the record permanently ?", "Warning", dialogButton);
            if (dialogResult == 0) {

                try {
                    Connection conn = db.GetConnection();
                    conn.setAutoCommit(false);
                    Statement st = conn.createStatement();

                    st.executeUpdate("delete from tblstockitemcurrentbalance where si_id=" + RowId + "");

                    st.executeUpdate("delete from tblstockitemopblupdaterecord where si_id=" + RowId + "");

                    st.executeUpdate("delete from tblstockitem where si_id=" + RowId + "");
                    conn.commit();
                    JOptionPane.showMessageDialog(this, "Stock Item Is Deleted Permanently");
                    formInternalFrameOpened(null);
                    txtStockItem.setText("");
                    txtStockItem.requestFocus();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "This Stock Item can not be deleted as it is used in other entries.");
                    txtStockItem.requestFocus();
                }
            }
        }
}//GEN-LAST:event_btnDelete1ActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnDelete1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDelete1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnDelete1ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtStockItem.requestFocus();
            txtStockItem.selectAll();
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnEdit.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnDelete1.requestFocus();

        }
    }//GEN-LAST:event_btnDelete1KeyPressed

    private void btnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtStockItem.requestFocus();
            txtStockItem.selectAll();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnEditActionPerformed(null);

        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnEdit.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnDelete1.requestFocus();

        }
    }//GEN-LAST:event_btnEditKeyPressed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        gridlist();
        tableStockItem.getColumnModel().getColumn(1).setWidth(1);
        tableStockItem.getColumnModel().getColumn(1).setMinWidth(1);
        tableStockItem.getColumnModel().getColumn(1).setMaxWidth(1);
    }//GEN-LAST:event_formInternalFrameActivated

    private void txtStockItemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStockItemFocusGained
        // TODO add your handling code here:
        txtStockItem.selectAll();
    }//GEN-LAST:event_txtStockItemFocusGained

    public void gridlist() {
        try {
            Connection conn = db.GetConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select si_name,si_id from tblstockitem");
            int i = 0;
            siArray.clear();
            while (rs.next()) {
                StockItemData gr1 = new StockItemData();
                gr1.setId(rs.getInt(2));
                gr1.setName(rs.getString(1));
                siArray.add(gr1);
            }
            i = 0;
            tableStockItem.removeAll();
            try {
                DefaultTableModel d = (DefaultTableModel) tableStockItem.getModel();
                d.setRowCount(0);

                for (i = 0; i < siArray.size(); i++) {
                    String record = siArray.get(i).getName();
                    int id = siArray.get(i).getId();
                    int rows = d.getRowCount();
                    rows++;
                    d.setRowCount(rows);
                    d.setColumnCount(2);
                    tableStockItem.setValueAt(record, rows - 1, 0);
                    tableStockItem.setValueAt(id, rows - 1, 1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelStockItemAlter2;
    private javax.swing.JLabel balance;
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnStockItemAlter_Back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelAlias;
    private javax.swing.JLabel labelBalance;
    private javax.swing.JLabel labelLength;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelRate;
    private javax.swing.JLabel labelThickness;
    private javax.swing.JLabel labelUnder;
    private javax.swing.JLabel labelUnit;
    private javax.swing.JLabel labelWidth;
    private javax.swing.JPanel panelEntry;
    private javax.swing.JLabel rate;
    private javax.swing.JTable tableStockItem;
    private javax.swing.JTextField txtStockItem;
    // End of variables declaration//GEN-END:variables
}