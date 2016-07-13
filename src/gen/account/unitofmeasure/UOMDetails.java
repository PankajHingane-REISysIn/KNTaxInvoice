package gen.account.unitofmeasure;

import gen.database.connection.DatabaseConnection1;
import gen.mainclass.MainClass;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class UOMDetails extends javax.swing.JInternalFrame {
    //  private Object[][] RowData;

    ArrayList<UOMData> arrayListUOM = new ArrayList<UOMData>();
    int RowId = 0;

    public UOMDetails(String s) {
        setClosable(true);
        initComponents();
        unitofmeasuregrid.setRowSelectionAllowed(true);
        unitofmeasuregrid.setColumnSelectionAllowed(false);
        this.setTitle(s);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelUnitOfMeasureAlter1 = new javax.swing.JPanel();
        PanelUnitOfMeasureAlter2 = new javax.swing.JPanel();
        btnUnitOfMeasureAlter_Back = new javax.swing.JButton();
        txtUnitOfMeasureAlter_grid = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        unitofmeasuregrid = new javax.swing.JTable();
        PanelUnitOfMeasureAlter3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        labelType = new javax.swing.JLabel();
        labelSymbol = new javax.swing.JLabel();
        labelFormalName = new javax.swing.JLabel();
        labelDecimalPlaces = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();

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

        btnUnitOfMeasureAlter_Back.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnUnitOfMeasureAlter_Back.setText("Back");
        btnUnitOfMeasureAlter_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnitOfMeasureAlter_BackActionPerformed(evt);
            }
        });
        btnUnitOfMeasureAlter_Back.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnUnitOfMeasureAlter_BackKeyPressed(evt);
            }
        });

        txtUnitOfMeasureAlter_grid.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtUnitOfMeasureAlter_grid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnitOfMeasureAlter_gridActionPerformed(evt);
            }
        });
        txtUnitOfMeasureAlter_grid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUnitOfMeasureAlter_gridFocusGained(evt);
            }
        });
        txtUnitOfMeasureAlter_grid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUnitOfMeasureAlter_gridKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUnitOfMeasureAlter_gridKeyReleased(evt);
            }
        });

        unitofmeasuregrid.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        unitofmeasuregrid.setModel(new javax.swing.table.DefaultTableModel(
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
        unitofmeasuregrid.setColumnSelectionAllowed(true);
        unitofmeasuregrid.setRowHeight(20);
        unitofmeasuregrid.setTableHeader(null);
        unitofmeasuregrid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                unitofmeasuregridMouseClicked(evt);
            }
        });
        unitofmeasuregrid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                unitofmeasuregridKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(unitofmeasuregrid);

        javax.swing.GroupLayout PanelUnitOfMeasureAlter2Layout = new javax.swing.GroupLayout(PanelUnitOfMeasureAlter2);
        PanelUnitOfMeasureAlter2.setLayout(PanelUnitOfMeasureAlter2Layout);
        PanelUnitOfMeasureAlter2Layout.setHorizontalGroup(
            PanelUnitOfMeasureAlter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelUnitOfMeasureAlter2Layout.createSequentialGroup()
                .addGroup(PanelUnitOfMeasureAlter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelUnitOfMeasureAlter2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btnUnitOfMeasureAlter_Back))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelUnitOfMeasureAlter2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(PanelUnitOfMeasureAlter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUnitOfMeasureAlter_grid, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))))
                .addContainerGap())
        );
        PanelUnitOfMeasureAlter2Layout.setVerticalGroup(
            PanelUnitOfMeasureAlter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelUnitOfMeasureAlter2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtUnitOfMeasureAlter_grid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnUnitOfMeasureAlter_Back)
                .addContainerGap())
        );

        PanelUnitOfMeasureAlter3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Type");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Symbol");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Formal Name");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Number Of Decimal Places");

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

        labelType.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelType.setText("jLabel5");

        labelSymbol.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelSymbol.setText("jLabel5");

        labelFormalName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelFormalName.setText("jLabel5");

        labelDecimalPlaces.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelDecimalPlaces.setText("jLabel5");

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

        javax.swing.GroupLayout PanelUnitOfMeasureAlter3Layout = new javax.swing.GroupLayout(PanelUnitOfMeasureAlter3);
        PanelUnitOfMeasureAlter3.setLayout(PanelUnitOfMeasureAlter3Layout);
        PanelUnitOfMeasureAlter3Layout.setHorizontalGroup(
            PanelUnitOfMeasureAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelUnitOfMeasureAlter3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(PanelUnitOfMeasureAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelUnitOfMeasureAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelType, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSymbol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelFormalName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelDecimalPlaces, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelUnitOfMeasureAlter3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnDelete)
                .addGap(71, 71, 71))
        );
        PanelUnitOfMeasureAlter3Layout.setVerticalGroup(
            PanelUnitOfMeasureAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelUnitOfMeasureAlter3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(PanelUnitOfMeasureAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(labelType))
                .addGap(32, 32, 32)
                .addGroup(PanelUnitOfMeasureAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelSymbol))
                .addGap(26, 26, 26)
                .addGroup(PanelUnitOfMeasureAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labelFormalName))
                .addGap(33, 33, 33)
                .addGroup(PanelUnitOfMeasureAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelDecimalPlaces))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(PanelUnitOfMeasureAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete))
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout PanelUnitOfMeasureAlter1Layout = new javax.swing.GroupLayout(PanelUnitOfMeasureAlter1);
        PanelUnitOfMeasureAlter1.setLayout(PanelUnitOfMeasureAlter1Layout);
        PanelUnitOfMeasureAlter1Layout.setHorizontalGroup(
            PanelUnitOfMeasureAlter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelUnitOfMeasureAlter1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelUnitOfMeasureAlter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelUnitOfMeasureAlter3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelUnitOfMeasureAlter1Layout.setVerticalGroup(
            PanelUnitOfMeasureAlter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelUnitOfMeasureAlter1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelUnitOfMeasureAlter2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelUnitOfMeasureAlter1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelUnitOfMeasureAlter3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(PanelUnitOfMeasureAlter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(PanelUnitOfMeasureAlter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUnitOfMeasureAlter_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnitOfMeasureAlter_BackActionPerformed
        try {
                  this.setSelected(true);
                  MainClass mainClass = new MainClass();
                  mainClass.menuselection(2);
                  this.setClosed(true);
             } catch (java.beans.PropertyVetoException e) {
            }
}//GEN-LAST:event_btnUnitOfMeasureAlter_BackActionPerformed

    private void btnUnitOfMeasureAlter_BackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnUnitOfMeasureAlter_BackKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnUnitOfMeasureAlter_Back.transferFocus();
        }
    }//GEN-LAST:event_btnUnitOfMeasureAlter_BackKeyPressed

    private void txtUnitOfMeasureAlter_gridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureAlter_gridActionPerformed
        // TODO add your handling code here:
        unitofmeasuregrid.requestFocus();
}//GEN-LAST:event_txtUnitOfMeasureAlter_gridActionPerformed

    private void txtUnitOfMeasureAlter_gridKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureAlter_gridKeyReleased
        // TODO add your handling code here:

        try {
            DefaultTableModel d = (DefaultTableModel) unitofmeasuregrid.getModel();

            String text = txtUnitOfMeasureAlter_grid.getText().trim();
            int size = txtUnitOfMeasureAlter_grid.getText().trim().length();
            d.setRowCount(0);
            int i = 0;
            for (i = 0; i < arrayListUOM.size(); i++) {
                String record = arrayListUOM.get(i).getName();
                int id = arrayListUOM.get(i).getId();
                if (record.length() >= size) {
                    String s = record.substring(0, size);
                    if (s.equalsIgnoreCase(text)) {
                        int rows = d.getRowCount();
                        rows++;
                        d.setRowCount(rows);
                        unitofmeasuregrid.setValueAt(record, rows - 1, 0);
                        unitofmeasuregrid.setValueAt(id, rows - 1, 1);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}//GEN-LAST:event_txtUnitOfMeasureAlter_gridKeyReleased

    private void unitofmeasuregridMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_unitofmeasuregridMouseClicked
        // TODO add your handling code here:
        if (unitofmeasuregrid.isColumnSelected(0)) {

            int row = unitofmeasuregrid.getSelectedRow();

            RowId = Integer.parseInt(unitofmeasuregrid.getValueAt(row, 1).toString());

            txtUnitOfMeasureAlter_grid.setText("");

            String name = (String) unitofmeasuregrid.getValueAt(row, 0);

            txtUnitOfMeasureAlter_grid.setText(name);
            Connection conn = null;
            try {

                conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();

                ResultSet rs = st.executeQuery("select * from tblunitofmeasure where uom_id=" + RowId + "");
                if (rs.next()) {
                    labelSymbol.setText(rs.getString("uom_symbol"));
                    labelFormalName.setText(rs.getString("uom_formalName"));
                    labelDecimalPlaces.setText(rs.getString(5));
                    String uom_type = rs.getString("uom_type");
                    labelType.setText(uom_type);
                    btnEdit.requestFocus();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UOMDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
}//GEN-LAST:event_unitofmeasuregridMouseClicked

    private void unitofmeasuregridKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unitofmeasuregridKeyPressed
        //-----------------------------------------------

        
//         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            int r=unitofmeasuregrid.getSelectedRow();
//            unitofmeasuregridMouseClicked(null);
//             if(r>0){
//                 unitofmeasuregrid.changeSelection(r-1, 0, false, false);
//            }
//            else{
//                 unitofmeasuregrid.changeSelection(0, 0, false, false);
//            }
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
//            txtUnitOfMeasureAlter_grid.requestFocus();
//        }
        
        
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int r = unitofmeasuregrid.getSelectedRow();
            unitofmeasuregridMouseClicked(null);
            if (r > 0) {
                unitofmeasuregrid.changeSelection(r - 1, 0, false, false);
            } else {
                unitofmeasuregrid.changeSelection(0, 0, false, false);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtUnitOfMeasureAlter_grid.requestFocus();
        } else {
            int k = evt.getKeyCode();
            char c = evt.getKeyChar();
            if (k >= 48 && k <= 57) {
                txtUnitOfMeasureAlter_grid.setText(txtUnitOfMeasureAlter_grid.getText().trim() + "" + c);
                String text = txtUnitOfMeasureAlter_grid.getText().trim();
                int size = text.length();
                DefaultTableModel model = new DefaultTableModel();
                unitofmeasuregrid.setModel(model);
                unitofmeasuregrid.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < arrayListUOM.size(); i++) {
                    String record = arrayListUOM.get(i).getName();

                    if (record.length() >= size) {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) {
                            int r = model.getRowCount();
                            model.setRowCount(r + 1);
                            model.setColumnCount(2);
                            unitofmeasuregrid.setValueAt(record, r, 0);
                            unitofmeasuregrid.setValueAt(arrayListUOM.get(i).getId(), r, 1);
                                    unitofmeasuregrid.getColumnModel().getColumn(1).setWidth(1);
        unitofmeasuregrid.getColumnModel().getColumn(1).setMinWidth(1);
        unitofmeasuregrid.getColumnModel().getColumn(1).setMaxWidth(1);
                        }
                    }
                }
            } else if (k >= 65 && k <= 90) {
                txtUnitOfMeasureAlter_grid.setText(txtUnitOfMeasureAlter_grid.getText().trim() + "" + c);
                String text = txtUnitOfMeasureAlter_grid.getText().trim();
                int size = text.length();
                DefaultTableModel model = new DefaultTableModel();
                unitofmeasuregrid.setModel(model);
                unitofmeasuregrid.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < arrayListUOM.size(); i++) {
                    String record = arrayListUOM.get(i).getName();

                    if (record.length() >= size) {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) {
                            int r = model.getRowCount();
                            model.setRowCount(r + 1);
                            model.setColumnCount(2);
                            unitofmeasuregrid.setValueAt(record, r, 0);
                            unitofmeasuregrid.setValueAt(arrayListUOM.get(i).getId(), r, 1);
                                    unitofmeasuregrid.getColumnModel().getColumn(1).setWidth(1);
        unitofmeasuregrid.getColumnModel().getColumn(1).setMinWidth(1);
        unitofmeasuregrid.getColumnModel().getColumn(1).setMaxWidth(1);
                        }
                    }
                }
            } else if (k >= 96 && k <= 105) {
                txtUnitOfMeasureAlter_grid.setText(txtUnitOfMeasureAlter_grid.getText().trim() + "" + c);
                String text = txtUnitOfMeasureAlter_grid.getText().trim();
                int size = text.length();
                DefaultTableModel model = new DefaultTableModel();
                unitofmeasuregrid.setModel(model);
                unitofmeasuregrid.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < arrayListUOM.size(); i++) {
                    String record = arrayListUOM.get(i).getName();

                    if (record.length() >= size) {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) {
                            int r = model.getRowCount();
                            model.setRowCount(r + 1);
                            model.setColumnCount(2);
                            unitofmeasuregrid.setValueAt(record, r, 0);
                            unitofmeasuregrid.setValueAt(arrayListUOM.get(i).getId(), r, 1);
                                    unitofmeasuregrid.getColumnModel().getColumn(1).setWidth(1);
        unitofmeasuregrid.getColumnModel().getColumn(1).setMinWidth(1);
        unitofmeasuregrid.getColumnModel().getColumn(1).setMaxWidth(1);
                        }
                    }
                }
            } else if (k == 8) {
                //txt.getText().trim().length()=txt.getText().trim().length()-1;
                String text = txtUnitOfMeasureAlter_grid.getText().trim();
                int size = text.length();
                String s1 = text.substring(0, size - 1);
                txtUnitOfMeasureAlter_grid.setText(s1);
                size = txtUnitOfMeasureAlter_grid.getText().trim().length();
                DefaultTableModel model = new DefaultTableModel();
                unitofmeasuregrid.setModel(model);
                unitofmeasuregrid.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < arrayListUOM.size(); i++) {
                    String record = arrayListUOM.get(i).getName();

                    if (record.length() >= size) {
                        String s2 = record.substring(0, size);
                        if (s2.equalsIgnoreCase(txtUnitOfMeasureAlter_grid.getText().trim())) {
                            int r = model.getRowCount();
                            model.setRowCount(r + 1);
                            model.setColumnCount(2);
                            unitofmeasuregrid.setValueAt(record, r, 0);
                            unitofmeasuregrid.setValueAt(arrayListUOM.get(i).getId(), r, 1);
                                    unitofmeasuregrid.getColumnModel().getColumn(1).setWidth(1);
        unitofmeasuregrid.getColumnModel().getColumn(1).setMinWidth(1);
        unitofmeasuregrid.getColumnModel().getColumn(1).setMaxWidth(1);
                        }
                    }
                }
            }
        }
        //------------------------------------------------
}//GEN-LAST:event_unitofmeasuregridKeyPressed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

        String name1=labelFormalName.getText().trim();
        if (labelFormalName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select the Unit of Measure");
            txtUnitOfMeasureAlter_grid.requestFocus();
        }
        else if(!name1.equals(txtUnitOfMeasureAlter_grid.getText().trim()))
                {
                 JOptionPane.showMessageDialog(this, "Please select valid Unit of Measure");
                  txtUnitOfMeasureAlter_grid.requestFocus();
                }
        else {
            UOMAlterFromDetails g = new UOMAlterFromDetails(RowId, "Alter Unit of Measure");
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
            btnUnitOfMeasureAlter_BackActionPerformed(evt);
        }
}//GEN-LAST:event_btnEditActionPerformed

    private void btnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtUnitOfMeasureAlter_grid.requestFocus();
            txtUnitOfMeasureAlter_grid.selectAll();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnEditActionPerformed(null);

        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnEdit.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnDelete.requestFocus();

        }
}//GEN-LAST:event_btnEditKeyPressed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        btnEdit.setMnemonic(KeyEvent.VK_E);
        btnDelete.setMnemonic(KeyEvent.VK_D);
        btnUnitOfMeasureAlter_Back.setMnemonic(KeyEvent.VK_B);
        labelType.setText("");
        labelDecimalPlaces.setText("");
        labelFormalName.setText("");
        labelSymbol.setText("");
        txtUnitOfMeasureAlter_grid.requestFocus();
        gridList();

        unitofmeasuregrid.getColumnModel().getColumn(1).setWidth(1);
        unitofmeasuregrid.getColumnModel().getColumn(1).setMinWidth(1);
        unitofmeasuregrid.getColumnModel().getColumn(1).setMaxWidth(1);
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
       MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        String name1=labelFormalName.getText().trim();
        if (labelFormalName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select the Stock Group");
            txtUnitOfMeasureAlter_grid.requestFocus();
        } 
        else if(!name1.equals(txtUnitOfMeasureAlter_grid.getText().trim()))
        {
          JOptionPane.showMessageDialog(this, "Selected Stock Group does not exist");
          txtUnitOfMeasureAlter_grid.requestFocus();
        }
        else {
            Connection conn = null;
            try {
                conn = DatabaseConnection1.GetConnection();
                conn.setAutoCommit(false);
                Statement st = conn.createStatement();
                String s = "select si_unitOfMeasure from tblstockitem where si_under=" + RowId + "";
                ResultSet rs = st.executeQuery(s);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "This Unit of Measure can not be deleted as it is used in other entries.");
                    txtUnitOfMeasureAlter_grid.setText("");
                    txtUnitOfMeasureAlter_grid.requestFocus();
                    gridList();

                } else {

                        int dialogButton = JOptionPane.YES_NO_OPTION;
                        int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want delete the record permanently ?", "Warning", dialogButton);
                    if (dialogResult == 0)
                    
                    { 
                    
                    st.executeUpdate("delete from tblunitofmeasure where uom_id=" + RowId + "");
                    conn.commit();
                    JOptionPane.showMessageDialog(this, "Unit of Measure is Deleted");
                    //formInternalFrameOpened(null);
                    
                    txtUnitOfMeasureAlter_grid.setText("");
                    txtUnitOfMeasureAlter_grid.requestFocus();
                    gridList();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "This Unit of Measure can not be deleted as it is used in other entries.");
                txtUnitOfMeasureAlter_grid.setText("");
                txtUnitOfMeasureAlter_grid.requestFocus();
                gridList();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UOMDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtUnitOfMeasureAlter_gridKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureAlter_gridKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (unitofmeasuregrid.getRowCount() > 0) {
                unitofmeasuregrid.requestFocus();
                unitofmeasuregrid.changeSelection(0, 0, false, false);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnUnitOfMeasureAlter_BackActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (unitofmeasuregrid.getRowCount() > 0) {
                unitofmeasuregrid.requestFocus();
                unitofmeasuregrid.changeSelection(0, 0, false, false);
            }
        }
    }//GEN-LAST:event_txtUnitOfMeasureAlter_gridKeyPressed

    private void btnDeleteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDeleteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtUnitOfMeasureAlter_grid.requestFocus();
            txtUnitOfMeasureAlter_grid.selectAll();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnDeleteActionPerformed(null);

        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnEdit.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnDelete.requestFocus();

        }
    }//GEN-LAST:event_btnDeleteKeyPressed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        txtUnitOfMeasureAlter_grid.requestFocus();
    }//GEN-LAST:event_formInternalFrameActivated

    private void txtUnitOfMeasureAlter_gridFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureAlter_gridFocusGained
        // TODO add your handling code here:
        txtUnitOfMeasureAlter_grid.selectAll();
    }//GEN-LAST:event_txtUnitOfMeasureAlter_gridFocusGained

    public void gridList() {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select uom_formalname,uom_id from tblunitofmeasure order by uom_formalname");
            int i = 0;
            arrayListUOM.clear();
            while (rs.next()) {
                UOMData gr1 = new UOMData();
                gr1.setId(rs.getInt("uom_id"));
                gr1.setName(rs.getString("uom_formalname"));
                arrayListUOM.add(gr1);

            }
            i = 0;
            unitofmeasuregrid.removeAll();
            try {
                DefaultTableModel d = (DefaultTableModel) unitofmeasuregrid.getModel();

                String text = txtUnitOfMeasureAlter_grid.getText().trim();
                int size = txtUnitOfMeasureAlter_grid.getText().trim().length();
                d.setRowCount(0);

                for (i = 0; i < arrayListUOM.size(); i++) {
                    String record = arrayListUOM.get(i).getName();
                    int id = arrayListUOM.get(i).getId();
                    if (record.length() >= size) {

                        int rows = d.getRowCount();
                        rows++;
                        d.setRowCount(rows);

                        unitofmeasuregrid.setValueAt(record, rows - 1, 0);
                        unitofmeasuregrid.setValueAt(id, rows - 1, 1);
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
                Logger.getLogger(UOMDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelUnitOfMeasureAlter1;
    private javax.swing.JPanel PanelUnitOfMeasureAlter2;
    private javax.swing.JPanel PanelUnitOfMeasureAlter3;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnUnitOfMeasureAlter_Back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDecimalPlaces;
    private javax.swing.JLabel labelFormalName;
    private javax.swing.JLabel labelSymbol;
    private javax.swing.JLabel labelType;
    private javax.swing.JTextField txtUnitOfMeasureAlter_grid;
    private javax.swing.JTable unitofmeasuregrid;
    // End of variables declaration//GEN-END:variables
}
