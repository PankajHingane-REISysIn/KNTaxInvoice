package gen.account.stockgroup;

import gen.database.connection.DatabaseConnection1;
import gen.mainclass.MainClass;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

public class StockGroupDetails extends javax.swing.JInternalFrame {

    private Object[][] RowData;
    ArrayList<stockgroupdata> sgarray = new ArrayList<stockgroupdata>();
    static ArrayList<String> MainGroups = new ArrayList<String>();
    long RowId = 0;

    public StockGroupDetails(String s) {
        setClosable(true);
        initComponents();
        this.setTitle(s);
        stockgroupgrid.setRowSelectionAllowed(true);
        stockgroupgrid.setColumnSelectionAllowed(false);
        addMainGroups();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtStockGroupAlter_grid = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        stockgroupgrid = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();
        labelAlias = new javax.swing.JLabel();
        labelUnder = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(679, 519));
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

        txtStockGroupAlter_grid.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtStockGroupAlter_grid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStockGroupAlter_gridFocusGained(evt);
            }
        });
        txtStockGroupAlter_grid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStockGroupAlter_gridKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStockGroupAlter_gridKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockGroupAlter_gridKeyTyped(evt);
            }
        });

        stockgroupgrid.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        stockgroupgrid.setModel(new javax.swing.table.DefaultTableModel(
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
        stockgroupgrid.setColumnSelectionAllowed(true);
        stockgroupgrid.setRowHeight(20);
        stockgroupgrid.setTableHeader(null);
        stockgroupgrid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stockgroupgridMouseClicked(evt);
            }
        });
        stockgroupgrid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                stockgroupgridKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(stockgroupgrid);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtStockGroupAlter_grid, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(txtStockGroupAlter_grid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setPreferredSize(new java.awt.Dimension(260, 254));

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

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Under");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Alias");

        labelName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelName.setText("jLabel4");

        labelAlias.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelAlias.setText("jLabel4");

        labelUnder.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelUnder.setText("jLabel4");

        btnDelete.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        btnDelete.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDeleteKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelUnder, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelAlias, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                .addComponent(labelName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelName)
                            .addComponent(jLabel1))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelAlias)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addComponent(labelUnder)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete))
                .addGap(25, 25, 25))
        );

        btnBack.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(btnBack)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)))
                .addComponent(btnBack))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtStockGroupAlter_gridKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockGroupAlter_gridKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (stockgroupgrid.getRowCount() > 0) {
                stockgroupgrid.requestFocus();
                stockgroupgrid.changeSelection(0, 0, false, false);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnBackActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (stockgroupgrid.getRowCount() > 0) {
                stockgroupgrid.requestFocus();
                stockgroupgrid.changeSelection(0, 0, false, false);
            }
        }
}//GEN-LAST:event_txtStockGroupAlter_gridKeyPressed

    private void txtStockGroupAlter_gridKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockGroupAlter_gridKeyReleased
        // TODO add your handling code here:

        try {
            DefaultTableModel d = (DefaultTableModel) stockgroupgrid.getModel();

            String text = txtStockGroupAlter_grid.getText().trim();
            int size = txtStockGroupAlter_grid.getText().length();
            d.setRowCount(0);
            int i = 0;
            for (i = 0; i < sgarray.size(); i++) {
                String record = sgarray.get(i).getName();
                int id = sgarray.get(i).getId();
                if (record.length() >= size) {
                    String s = record.substring(0, size);

                    if (s.equalsIgnoreCase(text)) {
                        int rows = d.getRowCount();
                        rows++;
                        d.setRowCount(rows);
                        stockgroupgrid.setValueAt(record, rows - 1, 0);
                        stockgroupgrid.setValueAt(id, rows - 1, 1);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}//GEN-LAST:event_txtStockGroupAlter_gridKeyReleased

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        try {
            this.setSelected(true);
            MainClass mainClass = new MainClass();
            mainClass.menuselection(2);
            this.setClosed(true);
        } catch (java.beans.PropertyVetoException e) {
        }
}//GEN-LAST:event_btnBackActionPerformed

    private void stockgroupgridMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stockgroupgridMouseClicked
        if (stockgroupgrid.getRowCount() > 0) {
            int row = stockgroupgrid.getSelectedRow();
            RowId = Integer.parseInt(stockgroupgrid.getValueAt(row, 1).toString());
            txtStockGroupAlter_grid.setText("");
            String name = (String) stockgroupgrid.getValueAt(row, 0);
            txtStockGroupAlter_grid.setText(name);
            Connection conn = null;
            try {
                conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from tblstockgroup where sg_id=" + RowId + "");
                if (rs.next()) {
                    labelName.setText(rs.getString("sg_name"));
                    labelAlias.setText(rs.getString("sg_alias"));

                    long id = rs.getLong("sg_under");
                    ResultSet rs1 = st.executeQuery("select sg_name from tblstockgroup where sg_id=" + id + "");
                    if (rs1.next()) {
                        labelUnder.setText(rs1.getString("sg_name"));
                    }
                    btnEdit.requestFocus();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StockGroupDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
}//GEN-LAST:event_stockgroupgridMouseClicked

    private void stockgroupgridKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stockgroupgridKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int r = stockgroupgrid.getSelectedRow();
            stockgroupgridMouseClicked(null);
            if (r > 0) {
                stockgroupgrid.changeSelection(r - 1, 0, false, false);
            } else {
                stockgroupgrid.changeSelection(0, 0, false, false);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtStockGroupAlter_grid.requestFocus();
        }
}//GEN-LAST:event_stockgroupgridKeyPressed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        String name = labelName.getText().trim();
        if (labelName.getText().equals("")) {

            JOptionPane.showMessageDialog(this, "Please select the Stock Group");
            txtStockGroupAlter_grid.requestFocus();
        } else if (!name.equals(txtStockGroupAlter_grid.getText())) {
            JOptionPane.showMessageDialog(this, "Please select valid Stock Group");
            txtStockGroupAlter_grid.requestFocus();
        } else {
            StockGroupAlterFromDetails g = new StockGroupAlterFromDetails(RowId, "Alter Stock Group");
            this.getParent().add(g);
            g.setVisible(true);
            Dimension desktopSize = this.getParent().getSize();
            Dimension jInternalFrameSize = g.getSize();
            g.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
//        g.setSize(desktopSize);
//        g.setPreferredSize(desktopSize);
            try {
                g.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
                e.printStackTrace();
            }
            BasicInternalFrameUI ui = (BasicInternalFrameUI) g.getUI();

            Component north = ui.getNorthPane();
            MouseMotionListener[] actions =
                    (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

            for (int i = 0; i < actions.length; i++) {
                north.removeMouseMotionListener(actions[i]);
            }
            btnBackActionPerformed(evt);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        btnEdit.setMnemonic(KeyEvent.VK_E);
        btnBack.setMnemonic(KeyEvent.VK_B);
        btnDelete.setMnemonic(KeyEvent.VK_D);
        gridlist();
        labelName.setText("");
        labelAlias.setText("");
        labelUnder.setText("");

        stockgroupgrid.getColumnModel().getColumn(1).setWidth(1);
        stockgroupgrid.getColumnModel().getColumn(1).setMinWidth(1);
        stockgroupgrid.getColumnModel().getColumn(1).setMaxWidth(1);
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String name = labelName.getText().trim();
        if (labelName.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please select the Stock Group");
            txtStockGroupAlter_grid.requestFocus();
        } else if (!name.equals(txtStockGroupAlter_grid.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Selected group does not exist");
            txtStockGroupAlter_grid.requestFocus();
        } else {
            int i = 0;
            int flag = 0;
            while (i < MainGroups.size()) {
                if (labelName.getText().equalsIgnoreCase(MainGroups.get(i))) {
                    flag = 1;
                    break;
                }
                i++;
            }
            if (flag == 1) {
                JOptionPane.showMessageDialog(null, "This is Predefined Group. You are not allowed to delete this group");
                txtStockGroupAlter_grid.requestFocus();
            } else {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want delete the record permanently ?", "Warning", dialogButton);
                if (dialogResult == 0) {

                    try {
                        Connection conn = DatabaseConnection1.GetConnection();
                        conn.setAutoCommit(false);
                        Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery("select sg_id from tblstockgroup where sg_under=" + RowId + "");
                        if (rs.next()) {
                            JOptionPane.showMessageDialog(null, "This group can not be deleted as it is used in other entries");
                            txtStockGroupAlter_grid.setText("");
                            //  gridlist();
                            txtStockGroupAlter_grid.requestFocus();
                        } else {
                            ResultSet rs1 = st.executeQuery("select si_id from tblstockitem where si_under=" + RowId + "");
                            if (rs1.next()) {
                                JOptionPane.showMessageDialog(null, "This group can not be deleted as it is used in other entries");
                                txtStockGroupAlter_grid.setText("");
                                // gridlist();
                                txtStockGroupAlter_grid.requestFocus();
                            } else {
                                st.executeUpdate("delete from tblstockgroup where sg_id=" + RowId + "");
                                conn.commit();
                                JOptionPane.showMessageDialog(null, "Stock Group Is Deleted Permanently");
                                //  formInternalFrameOpened(null);
                                labelName.setText("");
                                labelAlias.setText("");
                                labelUnder.setText("");
                                gridlist();
                                stockgroupgrid.getColumnModel().getColumn(1).setWidth(1);
                                stockgroupgrid.getColumnModel().getColumn(1).setMinWidth(1);
                                stockgroupgrid.getColumnModel().getColumn(1).setMaxWidth(1);
                                txtStockGroupAlter_grid.setText("");
                                txtStockGroupAlter_grid.requestFocus();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "This Stock group can not be deleted as it is used in other entries.");
                        gridlist();
                        txtStockGroupAlter_grid.requestFocus();
                    }
                }
            }
        }
        gridlist();
        stockgroupgrid.getColumnModel().getColumn(1).setWidth(1);
        stockgroupgrid.getColumnModel().getColumn(1).setMinWidth(1);
        stockgroupgrid.getColumnModel().getColumn(1).setMaxWidth(1);
}//GEN-LAST:event_btnDeleteActionPerformed

    private void txtStockGroupAlter_gridKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockGroupAlter_gridKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && !Character.isDigit(c)) {
            //evt.consume();
        }
    }//GEN-LAST:event_txtStockGroupAlter_gridKeyTyped

    private void btnDeleteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDeleteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnDeleteActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtStockGroupAlter_grid.requestFocus();
            txtStockGroupAlter_grid.selectAll();
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnEdit.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnDelete.requestFocus();

        }

    }//GEN-LAST:event_btnDeleteKeyPressed

    private void btnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtStockGroupAlter_grid.requestFocus();
            txtStockGroupAlter_grid.selectAll();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnEditActionPerformed(null);

        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnEdit.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnDelete.requestFocus();

        }
    }//GEN-LAST:event_btnEditKeyPressed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        gridlist();
        stockgroupgrid.getColumnModel().getColumn(1).setWidth(1);
        stockgroupgrid.getColumnModel().getColumn(1).setMinWidth(1);
        stockgroupgrid.getColumnModel().getColumn(1).setMaxWidth(1);
    }//GEN-LAST:event_formInternalFrameActivated

    private void txtStockGroupAlter_gridFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStockGroupAlter_gridFocusGained
        // TODO add your handling code here:
        txtStockGroupAlter_grid.selectAll();
    }//GEN-LAST:event_txtStockGroupAlter_gridFocusGained

    public void gridlist() {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select sg_name,sg_id from tblstockgroup where sg_name!='Primary' order by sg_name");
            int i = 0;
            sgarray.clear();
            while (rs.next()) {
                stockgroupdata gr1 = new stockgroupdata();
                gr1.setId(rs.getInt("sg_id"));
                gr1.setName(rs.getString("sg_name"));
                sgarray.add(gr1);
            }
            i = 0;
            stockgroupgrid.removeAll();
            try {
                DefaultTableModel d = (DefaultTableModel) stockgroupgrid.getModel();
                String text = txtStockGroupAlter_grid.getText().trim();
                int size = txtStockGroupAlter_grid.getText().length();
                d.setRowCount(0);

                for (i = 0; i < sgarray.size(); i++) {
                    String record = sgarray.get(i).getName();
                    int id = sgarray.get(i).getId();
                    if (record.length() >= size) {
                        int rows = d.getRowCount();
                        rows++;
                        d.setRowCount(rows);
                        d.setColumnCount(2);
                        stockgroupgrid.setValueAt(record, rows - 1, 0);
                        stockgroupgrid.setValueAt(id, rows - 1, 1);
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
                Logger.getLogger(StockGroupDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void addMainGroups() {
        MainGroups.add("Primary");
        MainGroups.add("Raw Material");
        MainGroups.add("Finished Material");
        MainGroups.add("Raw Board");
        MainGroups.add("Chemical");
        MainGroups.add("Raw Paper");
        MainGroups.add("Chemical Procedure");
        MainGroups.add("Paper Procedure");
        MainGroups.add("Board Procedure");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAlias;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelUnder;
    private javax.swing.JTable stockgroupgrid;
    private javax.swing.JTextField txtStockGroupAlter_grid;
    // End of variables declaration//GEN-END:variables
}
