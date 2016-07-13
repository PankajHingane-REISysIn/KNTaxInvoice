package gen.account.ledger;

import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.mainclass.MainClass;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

public class LedgerDetails extends javax.swing.JInternalFrame {
    // private Object[][] RowData;

    static int focuscontrol=0;
    
    ArrayList<LedgerData> arrayListLedgerData = new ArrayList<LedgerData>();
    static long RowId = 0;

    public LedgerDetails(String s) {
        setClosable(true);
        initComponents();
        ledgergrid.setRowSelectionAllowed(true);
        ledgergrid.setColumnSelectionAllowed(false);
        this.setTitle(s);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelLedgerAlter1 = new javax.swing.JPanel();
        PanelLedgerAlter2 = new javax.swing.JPanel();
        txtLedgerAlter_grid = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ledgergrid = new javax.swing.JTable(

        );
        PanelDetails = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();
        labelAlias = new javax.swing.JLabel();
        labelUnder = new javax.swing.JLabel();
        labelOpeningBalance = new javax.swing.JLabel();
        labelDebCred = new javax.swing.JLabel();
        labelAddr = new javax.swing.JLabel();
        labelContact = new javax.swing.JLabel();
        labelEmail = new javax.swing.JLabel();
        labelIncomeTaxNo = new javax.swing.JLabel();
        labelSaleTaxNo = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        labelCreditLimit = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Symbol.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 582));
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

        txtLedgerAlter_grid.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtLedgerAlter_grid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLedgerAlter_gridFocusGained(evt);
            }
        });
        txtLedgerAlter_grid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLedgerAlter_gridKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLedgerAlter_gridKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLedgerAlter_gridKeyTyped(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        ledgergrid.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ledgergrid.setModel(new javax.swing.table.DefaultTableModel(
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
        ledgergrid.setColumnSelectionAllowed(true);
        ledgergrid.setRowHeight(20);
        ledgergrid.setTableHeader(null);
        ledgergrid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ledgergridMouseClicked(evt);
            }
        });
        ledgergrid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ledgergridKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(ledgergrid);

        javax.swing.GroupLayout PanelLedgerAlter2Layout = new javax.swing.GroupLayout(PanelLedgerAlter2);
        PanelLedgerAlter2.setLayout(PanelLedgerAlter2Layout);
        PanelLedgerAlter2Layout.setHorizontalGroup(
            PanelLedgerAlter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLedgerAlter2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelLedgerAlter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLedgerAlter_grid, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLedgerAlter2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );
        PanelLedgerAlter2Layout.setVerticalGroup(
            PanelLedgerAlter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLedgerAlter2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtLedgerAlter_grid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(17, 17, 17)
                .addComponent(btnBack))
        );

        PanelDetails.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Name");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Sale Tax Number");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Income Tax Number");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Address");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Opening Balance");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Under");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Alias");

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

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Email Id");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Contact No");

        labelName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelName.setText("jLabel10");

        labelAlias.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelAlias.setText("jLabel10");

        labelUnder.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelUnder.setText("jLabel10");

        labelOpeningBalance.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelOpeningBalance.setText("jLabel10");

        labelDebCred.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelDebCred.setText("jLabel10");

        labelAddr.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelAddr.setText("jLabel10");

        labelContact.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelContact.setText("jLabel10");

        labelEmail.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelEmail.setText("jLabel10");

        labelIncomeTaxNo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelIncomeTaxNo.setText("jLabel10");

        labelSaleTaxNo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelSaleTaxNo.setText("jLabel10");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Credit Limit");

        labelCreditLimit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelCreditLimit.setText("jLabel11");

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

        javax.swing.GroupLayout PanelDetailsLayout = new javax.swing.GroupLayout(PanelDetails);
        PanelDetails.setLayout(PanelDetailsLayout);
        PanelDetailsLayout.setHorizontalGroup(
            PanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDetailsLayout.createSequentialGroup()
                .addGroup(PanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDetailsLayout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete))
                    .addGroup(PanelDetailsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelDetailsLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel3))
                            .addGroup(PanelDetailsLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(PanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)))
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addGap(36, 36, 36)
                        .addGroup(PanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelContact, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(labelAddr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelAlias, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelUnder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelDetailsLayout.createSequentialGroup()
                                    .addComponent(labelOpeningBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(labelDebCred, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(labelIncomeTaxNo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(labelSaleTaxNo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                                .addComponent(labelCreditLimit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        PanelDetailsLayout.setVerticalGroup(
            PanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelName))
                .addGap(25, 25, 25)
                .addGroup(PanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(labelAlias))
                .addGap(20, 20, 20)
                .addGroup(PanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelUnder))
                .addGap(22, 22, 22)
                .addGroup(PanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(labelOpeningBalance)
                    .addComponent(labelDebCred))
                .addGap(22, 22, 22)
                .addGroup(PanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAddr))
                .addGap(18, 18, 18)
                .addGroup(PanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(labelContact))
                .addGap(19, 19, 19)
                .addGroup(PanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(labelEmail))
                .addGap(18, 18, 18)
                .addGroup(PanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(labelIncomeTaxNo))
                .addGap(28, 28, 28)
                .addGroup(PanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(labelSaleTaxNo))
                .addGap(18, 18, 18)
                .addGroup(PanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(labelCreditLimit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(PanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnEdit))
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelLedgerAlter1Layout = new javax.swing.GroupLayout(PanelLedgerAlter1);
        PanelLedgerAlter1.setLayout(PanelLedgerAlter1Layout);
        PanelLedgerAlter1Layout.setHorizontalGroup(
            PanelLedgerAlter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLedgerAlter1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(PanelLedgerAlter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelLedgerAlter1Layout.setVerticalGroup(
            PanelLedgerAlter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLedgerAlter1Layout.createSequentialGroup()
                .addComponent(PanelLedgerAlter2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
            .addGroup(PanelLedgerAlter1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(PanelLedgerAlter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(PanelLedgerAlter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtLedgerAlter_gridKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerAlter_gridKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (ledgergrid.getRowCount() > 0) {
                ledgergrid.requestFocus();
                ledgergrid.changeSelection(0, 0, false, false);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnBackActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (ledgergrid.getRowCount() > 0) {
                ledgergrid.requestFocus();
                ledgergrid.changeSelection(0, 0, false, false);
            }
        }
}//GEN-LAST:event_txtLedgerAlter_gridKeyPressed

    private void txtLedgerAlter_gridKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerAlter_gridKeyReleased
        try {
            DefaultTableModel d = (DefaultTableModel) ledgergrid.getModel();
            String text = txtLedgerAlter_grid.getText().trim();
            int size = txtLedgerAlter_grid.getText().trim().length();
            d.setRowCount(0);
            int i = 0;
            for (i = 0; i < arrayListLedgerData.size(); i++) {
                String record = arrayListLedgerData.get(i).getName();
                int id = arrayListLedgerData.get(i).getId();
                if (record.length() >= size) {
                    String s = record.substring(0, size);
                    if (s.equalsIgnoreCase(text)) {
                        int rows = d.getRowCount();
                        rows++;
                        d.setRowCount(rows);
                        ledgergrid.setValueAt(record, rows - 1, 0);
                        ledgergrid.setValueAt(id, rows - 1, 1);
                    }
                }
            }
        } catch (Exception e) {
        }
}//GEN-LAST:event_txtLedgerAlter_gridKeyReleased

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
         try {
                  this.setSelected(true);
                  MainClass mainClass = new MainClass();
                  mainClass.menuselection(1);
                  this.setClosed(true);
             } catch (java.beans.PropertyVetoException e) {
            }
}//GEN-LAST:event_btnBackActionPerformed

    private void ledgergridMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ledgergridMouseClicked
        if (ledgergrid.getRowCount() > 0) {
            int row = ledgergrid.getSelectedRow();
            RowId = Integer.parseInt(ledgergrid.getValueAt(row, 1).toString());
            txtLedgerAlter_grid.setText("");
            String name = (String) ledgergrid.getValueAt(row, 0);
            txtLedgerAlter_grid.setText(name);
            Connection conn = null;
            try {
                conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from tblledger where ledger_id=" + RowId + "");
                if (rs.next()) {
                    PanelDetails.setVisible(true);
                    labelName.setText(rs.getString("ledger_name"));
                    labelAlias.setText(rs.getString("ledger_alias"));
                    labelOpeningBalance.setText(Constants.DECIMAL_FORMAT.format(rs.getString("ledger_openingBalance")));
                    labelAddr.setText(rs.getString("ledger_address"));
                    labelContact.setText(rs.getString("ledger_contactNo"));
                    labelEmail.setText(rs.getString("ledger_emailId"));
                    labelIncomeTaxNo.setText(rs.getString("ledger_inTaxo"));
                    labelSaleTaxNo.setText(rs.getString("ledger_saleTaxNo"));
                    if (rs.getInt("opening_type") == 1) {
                        labelDebCred.setText("Debit");
                    } else {
                        labelDebCred.setText("Credit");
                    }
                    long under = rs.getLong("ledger_under");
                    ResultSet rs1 = st.executeQuery("select group_name from tblgroup where group_id=" + under + "");
                    if (rs1.next()) {
                        labelUnder.setText(rs1.getString("group_name"));
                    }
                    rs1.close();

                    System.out.println(RowId);
                    rs1 = st.executeQuery("select ledger_limit from tblledgercreditlimit where ledger_id=" + RowId + "");
                    if (rs1.next()) {
                        labelCreditLimit.setText("" + Constants.DECIMAL_FORMAT.format(rs1.getString("ledger_limit")));
                    } else {
                        labelCreditLimit.setText("0");
                    }
                    rs1.close();
                    btnEdit.requestFocus();
                }
            } catch (Exception e) {
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LedgerDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
}//GEN-LAST:event_ledgergridMouseClicked

    private void ledgergridKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ledgergridKeyPressed

//        
//         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            int r=ledgergrid.getSelectedRow();
//            ledgergridMouseClicked(null);
//             if(r>0){
//                 ledgergrid.changeSelection(r-1, 0, false, false);
//            }
//            else{
//                 ledgergrid.changeSelection(0, 0, false, false);
//            }
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
//            txtLedgerAlter_grid.requestFocus();
//        }
        
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int r = ledgergrid.getSelectedRow();
            ledgergridMouseClicked(null);
            if (r > 0) {
                ledgergrid.changeSelection(r - 1, 0, false, false);
            } else {
                ledgergrid.changeSelection(0, 0, false, false);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtLedgerAlter_grid.requestFocus();
        } else {
            int k = evt.getKeyCode();
            char c = evt.getKeyChar();
            if (k >= 48 && k <= 57) {
                txtLedgerAlter_grid.setText(txtLedgerAlter_grid.getText().trim() + "" + c);
                String text = txtLedgerAlter_grid.getText().trim();
                int size = text.length();
                DefaultTableModel model = new DefaultTableModel();
                ledgergrid.setModel(model);
                ledgergrid.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < arrayListLedgerData.size(); i++) {
                    String record = arrayListLedgerData.get(i).getName();

                    if (record.length() >= size) {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) {
                            int r = model.getRowCount();
                            model.setRowCount(r + 1);
                            model.setColumnCount(2);
                            ledgergrid.setValueAt(record, r, 0);
                            ledgergrid.setValueAt(arrayListLedgerData.get(i).getId(), r, 1);
                            ////////////////////////////////////////////////////////////        
                            ledgergrid.getColumnModel().getColumn(1).setWidth(1);
                            ledgergrid.getColumnModel().getColumn(1).setMinWidth(1);
                            ledgergrid.getColumnModel().getColumn(1).setMaxWidth(1);
                        }
                    }
                }
            } else if (k >= 65 && k <= 90) {
                txtLedgerAlter_grid.setText(txtLedgerAlter_grid.getText().trim() + "" + c);
                String text = txtLedgerAlter_grid.getText().trim();
                int size = text.length();
                DefaultTableModel model = new DefaultTableModel();
                ledgergrid.setModel(model);
                ledgergrid.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < arrayListLedgerData.size(); i++) {
                    String record = arrayListLedgerData.get(i).getName();

                    if (record.length() >= size) {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) {
                            int r = model.getRowCount();
                            model.setRowCount(r + 1);
                            model.setColumnCount(2);
                            ledgergrid.setValueAt(record, r, 0);
                            ledgergrid.setValueAt(arrayListLedgerData.get(i).getId(), r, 1);
                /////////////////////////////////////////////////////////////////////////////////////////
                                    
                            ledgergrid.getColumnModel().getColumn(1).setWidth(1);
                            ledgergrid.getColumnModel().getColumn(1).setMinWidth(1);
                            ledgergrid.getColumnModel().getColumn(1).setMaxWidth(1);
                            
                        }
                    }
                }
            } else if (k >= 96 && k <= 105) {
                txtLedgerAlter_grid.setText(txtLedgerAlter_grid.getText().trim() + "" + c);
                String text = txtLedgerAlter_grid.getText().trim();
                int size = text.length();
                DefaultTableModel model = new DefaultTableModel();
                ledgergrid.setModel(model);
                ledgergrid.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < arrayListLedgerData.size(); i++) {
                    String record = arrayListLedgerData.get(i).getName();

                    if (record.length() >= size) {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) {
                            int r = model.getRowCount();
                            model.setRowCount(r + 1);
                            model.setColumnCount(2);
                            ledgergrid.setValueAt(record, r, 0);
                            ledgergrid.setValueAt(arrayListLedgerData.get(i).getId(), r, 1);
           ////////////////////////////////////////////////////////////////////////////////////////////         
                            ledgergrid.getColumnModel().getColumn(1).setWidth(1);
                            ledgergrid.getColumnModel().getColumn(1).setMinWidth(1);
                            ledgergrid.getColumnModel().getColumn(1).setMaxWidth(1);
                        
                        }
                    }
                }
            } else if (k == 8) {
                //txt.getText().length()=txt.getText().length()-1;
                String text = txtLedgerAlter_grid.getText().trim();
                int size = text.length();
                String s1 = text.substring(0, size - 1);
                txtLedgerAlter_grid.setText(s1);
                size = txtLedgerAlter_grid.getText().trim().length();
                DefaultTableModel model = new DefaultTableModel();
                ledgergrid.setModel(model);
                ledgergrid.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < arrayListLedgerData.size(); i++) {
                    String record = arrayListLedgerData.get(i).getName();

                    if (record.length() >= size) {
                        String s2 = record.substring(0, size);
                        if (s2.equalsIgnoreCase(txtLedgerAlter_grid.getText().trim())) {
                            int r = model.getRowCount();
                            model.setRowCount(r + 1);
                            model.setColumnCount(2);
                            ledgergrid.setValueAt(record, r, 0);
                            ledgergrid.setValueAt(arrayListLedgerData.get(i).getId(), r, 1);
     ///////////////////////////////////////////////////////////////////////////////////////////////////////////
                            
        ledgergrid.getColumnModel().getColumn(1).setWidth(1);
        ledgergrid.getColumnModel().getColumn(1).setMinWidth(1);
        ledgergrid.getColumnModel().getColumn(1).setMaxWidth(1);
                        }
                    }
                }
            }
        }
}//GEN-LAST:event_ledgergridKeyPressed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
      
        
        
        String name=labelName.getText().trim(); 
        if (labelName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select the Ledger");
            txtLedgerAlter_grid.requestFocus();
        }
        else if(!name.equals(txtLedgerAlter_grid.getText().trim()))
        {
           JOptionPane.showMessageDialog(this, "Please select valid Ledger");
           txtLedgerAlter_grid.requestFocus();
        }
        else {
            LedgerAlterFromDetails l = new LedgerAlterFromDetails("Alter Ledger Details", RowId);
            
            this.removeAll();
            
            this.getParent().add(l);
            l.setVisible(true);
            Dimension desktopSize = this.getParent().getSize();
            Dimension jInternalFrameSize = l.getSize();
            l.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            
            try {
                l.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
                e.printStackTrace();
            }
            BasicInternalFrameUI ui = (BasicInternalFrameUI) l.getUI();

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

       
        
        
        //PanelDetails.setVisible(false);
        btnBack.setMnemonic(KeyEvent.VK_B);
        btnEdit.setMnemonic(KeyEvent.VK_E);
        btnDelete.setMnemonic(KeyEvent.VK_D);
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            // Statement st = conn.createStatement();
            GridList();
            labelName.setText("");
            labelAlias.setText("");
            labelUnder.setText("");
            labelAddr.setText("");
            labelContact.setText("");
            labelEmail.setText("");
            labelDebCred.setText("");
            labelOpeningBalance.setText("");
            labelIncomeTaxNo.setText("");
            labelSaleTaxNo.setText("");
            labelCreditLimit.setText("");
        } catch (Exception e) {
        } finally {
            try {
               conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(LedgerDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.GridList();

  
        ledgergrid.getColumnModel().getColumn(1).setWidth(1);
        ledgergrid.getColumnModel().getColumn(1).setMinWidth(1);
        ledgergrid.getColumnModel().getColumn(1).setMaxWidth(1);
       // this.setVisible(true);
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String name=labelName.getText().trim();
        if (labelName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select the Ledger");
            txtLedgerAlter_grid.requestFocus();
        }
        else if(!name.equals(txtLedgerAlter_grid.getText().trim()))
        {
          JOptionPane.showMessageDialog(this, "Selected Ledger does not exist");
            txtLedgerAlter_grid.requestFocus();
        }
        else {
            try {
                Connection conn = DatabaseConnection1.GetConnection();
                conn.setAutoCommit(false);
                Statement st = conn.createStatement();

                ResultSet rs = st.executeQuery("select trans_ledgerId from tbltransactionledger where trans_ledgerId=" + RowId + "");
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "This group can not be deleted as it is used in other entries");
                    txtLedgerAlter_grid.setText("");
                    txtLedgerAlter_grid.requestFocus();
                  //  GridList();
                    formInternalFrameOpened(null);
                } 
                else
                {
                     int dialogButton = JOptionPane.YES_NO_OPTION;
                     int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want delete the record permanently ?", "Warning", dialogButton);
                    
                    if(dialogResult==0)
                    {
                    
                      st.executeUpdate("delete from tblledgercreditlimit where ledger_id=" + RowId + "");

                      st.executeUpdate("delete from tblledgercurrentbalance where ledger_id=" + RowId + "");

                      st.executeUpdate("delete from tblledger where ledger_id=" + RowId + "");
                      conn.commit();
                      JOptionPane.showMessageDialog(this, "Ledger Is Deleted Permanently");
                      txtLedgerAlter_grid.requestFocus();;
                      txtLedgerAlter_grid.setText("");

                      formInternalFrameOpened(null);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "This Ledger can not be deleted as it is used in other entries.");
                btnBackActionPerformed(evt);
            }
        }
}//GEN-LAST:event_btnDeleteActionPerformed

    private void txtLedgerAlter_gridKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerAlter_gridKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && !Character.isDigit(c)) {
            // evt.consume();
        }
    }//GEN-LAST:event_txtLedgerAlter_gridKeyTyped

    private void btnDeleteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDeleteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnDeleteActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtLedgerAlter_grid.requestFocus();
            txtLedgerAlter_grid.selectAll();
        }
         if (evt.getKeyCode() == KeyEvent.VK_LEFT) 
        {
            btnEdit.requestFocus();
         
        }      
            if (evt.getKeyCode() == KeyEvent.VK_RIGHT) 
        {
             btnDelete.requestFocus();
         
        }      
    }//GEN-LAST:event_btnDeleteKeyPressed

    private void btnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEditKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) 
        {
               txtLedgerAlter_grid.requestFocus();
               txtLedgerAlter_grid.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) 
        {
            btnEditActionPerformed(null);
         
        }      
         if (evt.getKeyCode() == KeyEvent.VK_LEFT) 
        {
            btnEdit.requestFocus();
         
        }      
            if (evt.getKeyCode() == KeyEvent.VK_RIGHT) 
        {
             btnDelete.requestFocus();
         
        }      
    }//GEN-LAST:event_btnEditKeyPressed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:

        
        System.out.println("Frame Activated...");
        GridList();
        ledgergrid.getColumnModel().getColumn(1).setWidth(1);
        ledgergrid.getColumnModel().getColumn(1).setMinWidth(1);
        ledgergrid.getColumnModel().getColumn(1).setMaxWidth(1);
    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosed

    private void txtLedgerAlter_gridFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLedgerAlter_gridFocusGained
        // TODO add your handling code here:
        txtLedgerAlter_grid.selectAll();
    }//GEN-LAST:event_txtLedgerAlter_gridFocusGained

    public void GridList() {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select ledger_name,ledger_id from tblledger order by ledger_name");
            int i = 0;
            arrayListLedgerData.clear();
            while (rs.next()) {
                LedgerData ld = new LedgerData();
                ld.setId(rs.getInt(2));
                ld.setName(rs.getString(1));
                arrayListLedgerData.add(ld);
            }
            i = 0;
            ledgergrid.removeAll();
            try {
                DefaultTableModel d = (DefaultTableModel) ledgergrid.getModel();
                String text = txtLedgerAlter_grid.getText().trim();
                int size = txtLedgerAlter_grid.getText().trim().length();
                d.setRowCount(0);
                for (i = 0; i < arrayListLedgerData.size(); i++) {
                    String record = arrayListLedgerData.get(i).getName();
                    int id = arrayListLedgerData.get(i).getId();
                    if (record.length() >= size) {
                        int rows = d.getRowCount();
                        rows++;
                        d.setRowCount(rows);
                        d.setColumnCount(2);
                        ledgergrid.setValueAt(record, rows - 1, 0);
                        ledgergrid.setValueAt(id, rows - 1, 1);
        ///////////////////////////////////////////////////////////                
        ledgergrid.getColumnModel().getColumn(1).setWidth(1);
        ledgergrid.getColumnModel().getColumn(1).setMinWidth(1);
        ledgergrid.getColumnModel().getColumn(1).setMaxWidth(1);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(LedgerDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelDetails;
    private javax.swing.JPanel PanelLedgerAlter1;
    private javax.swing.JPanel PanelLedgerAlter2;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAddr;
    private javax.swing.JLabel labelAlias;
    private javax.swing.JLabel labelContact;
    private javax.swing.JLabel labelCreditLimit;
    private javax.swing.JLabel labelDebCred;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelIncomeTaxNo;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelOpeningBalance;
    private javax.swing.JLabel labelSaleTaxNo;
    private javax.swing.JLabel labelUnder;
    private javax.swing.JTable ledgergrid;
    private javax.swing.JTextField txtLedgerAlter_grid;
    // End of variables declaration//GEN-END:variables
}
