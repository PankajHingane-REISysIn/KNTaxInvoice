package gen.feature.production;

import gen.database.connection.DatabaseConnection1;
import gen.mainclass.MainClass;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class AddProductionProcess extends javax.swing.JInternalFrame {

    TemporaryClass t = new TemporaryClass();
    ArrayList<TemporaryClass> finisheditem = new ArrayList<TemporaryClass>();
    ArrayList<TemporaryClass> rawitem = new ArrayList<TemporaryClass>();
    static int temp = 1000;  //for storing index of selected row of table
    int i = 0, flag = 0;
    ResultSet rs1, rs2, rs3;
    String q = "", str = "";
    private final JTextField tfDatePicker;
    JTextField tf;
    ArrayList<String> GroupItems = new ArrayList<String>();

    public AddProductionProcess(String s) {
        setClosable(true);
        initComponents();
        dat.setDateFormatString("dd-MM-yyyy");

        this.setTitle(s);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        tfDatePicker = (JTextField) dat.getComponent(1);
        System.out.println(tfDatePicker.toString());


        tf = (JTextField) comboItem.getEditor().getEditorComponent();//Edits the component in combobox.



        tfDatePicker.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("code:" + e.getKeyCode());
                comboItem.requestFocus();
            }

            @Override
            public void keyTyped(final KeyEvent e) {
                System.out.println("Key pressed");
                int code = e.getKeyCode();
                System.out.println("code" + code);
                if (code == KeyEvent.VK_ENTER) {
                    System.out.println("Enter");

                }
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int code = e.getKeyCode();
                        System.out.println("code" + code);
                        if (code == KeyEvent.VK_ENTER) {
                            System.out.println("Enter");
                        } else if (code == KeyEvent.VK_ESCAPE) {
                            System.out.println("Escape");
                            // hide_flag = true; 
                        } else if (code == KeyEvent.VK_RIGHT) {
                            System.out.println("Right");
                        }
                    }
                });
            }
        });

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboItem = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableRaw = new javax.swing.JTable();
        btnProcess = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        dat = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();

        setFrameIcon(new javax.swing.ImageIcon("E:\\Sudeep\\customer Copy\\07-01-2013\\General Copy\\images\\Symbol.jpg")); // NOI18N
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Finishing Item");

        comboItem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboItemItemStateChanged(evt);
            }
        });
        comboItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboItemKeyPressed(evt);
            }
        });

        jLabel2.setText("Quantity");

        txtQty.setText("1");
        txtQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQtyActionPerformed(evt);
            }
        });
        txtQty.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtQtyFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtQtyFocusLost(evt);
            }
        });
        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtyKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQtyKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQtyKeyTyped(evt);
            }
        });

        tableRaw.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        )
        {
            public boolean isCellEditable(int row, int col)
            {
                return false;
            }
        }

    );
    tableRaw.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            tableRawKeyPressed(evt);
        }
    });
    jScrollPane1.setViewportView(tableRaw);

    btnProcess.setText("SUBMIT");
    btnProcess.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnProcessActionPerformed(evt);
        }
    });

    btnBack.setText("BACK");
    btnBack.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnBackActionPerformed(evt);
        }
    });

    jLabel3.setText("Date");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(219, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(dat, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboItem, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(115, 115, 115)
                            .addComponent(btnBack)
                            .addGap(18, 18, 18)
                            .addComponent(btnProcess)))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel3)
                .addComponent(dat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(25, 25, 25)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(comboItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2))
            .addGap(21, 21, 21)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnProcess)
                .addComponent(btnBack))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(47, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboItemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboItemItemStateChanged
        if (comboItem.getSelectedItem().equals("") == false) {
            DefaultTableModel tableModel1 = (DefaultTableModel) tableRaw.getModel();
            tableModel1.setRowCount(0);
            Connection conn = null;
            try {
                conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();

                i = 0;
                long item_id = 0;

                while (i < finisheditem.size()) {
                    if (finisheditem.get(i).getFinish_item_name().equals(comboItem.getSelectedItem())) {
                        item_id = finisheditem.get(i).getFinish_item_id();
                    }
                    i++;
                }

                txtQty.setText("1");
                //Adding list of ledgers to Particulars
                q = "select * from tblproductionrawmaterial where FinishedItem=" + item_id + "";
                rs1 = st.executeQuery(q);

                while (rs1.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tableRaw.getModel();
                    int j = tableRaw.getRowCount();
                    tableModel.setRowCount(tableRaw.getRowCount() + 1);
                    i = 0;
                    String item_name = "";
                    while (i < rawitem.size()) {
                        if (rawitem.get(i).getRaw_item_id() == rs1.getLong("RawItem")) {
                            item_name = rawitem.get(i).getRaw_item_name();
                        }
                        i++;
                    }
                    tableRaw.setValueAt(j + 1, j, 0);
                    tableRaw.setValueAt(item_name, j, 1);
                    tableRaw.setValueAt(rs1.getInt("qty"), j, 2);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddProductionProcess.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AddProductionProcess.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
}//GEN-LAST:event_comboItemItemStateChanged

    private void comboItemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboItemKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (comboItem.getSelectedItem().equals("")) {
                comboItem.requestFocus();
            } else {
                txtQty.requestFocus();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnBackActionPerformed(null);
        }
}//GEN-LAST:event_comboItemKeyPressed

    private void txtQtyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQtyFocusGained
}//GEN-LAST:event_txtQtyFocusGained

    private void txtQtyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQtyFocusLost
        if (txtQty.getText().equals("")) {
            txtQty.setText("1");
            if (comboItem.getSelectedItem().equals("") == false) {
                DefaultTableModel tableModel1 = (DefaultTableModel) tableRaw.getModel();
                tableModel1.setRowCount(0);
                Connection conn = null;
                try {
                    conn = DatabaseConnection1.GetConnection();
                    Statement st = conn.createStatement();

                    i = 0;
                    long item_id = 0;

                    while (i < finisheditem.size()) {
                        if (finisheditem.get(i).getFinish_item_name().equals(comboItem.getSelectedItem())) {
                            item_id = finisheditem.get(i).getFinish_item_id();
                        }
                        i++;
                    }

                    //Adding list of ledgers to Particulars
                    q = "select * from tblproductionrawmaterial where FinishedItem=" + item_id + "";
                    rs1 = st.executeQuery(q);

                    while (rs1.next()) {
                        DefaultTableModel tableModel = (DefaultTableModel) tableRaw.getModel();
                        int j = tableRaw.getRowCount();
                        tableModel.setRowCount(tableRaw.getRowCount() + 1);
                        i = 0;
                        String item_name = "";
                        while (i < rawitem.size()) {
                            if (rawitem.get(i).getRaw_item_id() == rs1.getLong("RawItem")) {
                                item_name = rawitem.get(i).getRaw_item_name();
                            }
                            i++;
                        }
                        tableRaw.setValueAt(j + 1, j, 0);
                        tableRaw.setValueAt(item_name, j, 1);
                        tableRaw.setValueAt(rs1.getInt("qty"), j, 2);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AddProductionProcess.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(AddProductionProcess.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
}//GEN-LAST:event_txtQtyFocusLost

    private void txtQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DefaultTableModel tableModel = (DefaultTableModel) tableRaw.getModel();
            tableModel.setRowCount(tableRaw.getRowCount() + 1);
            tableRaw.requestFocus();
            tableRaw.changeSelection(0, 0, false, false);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            comboItem.requestFocus();
        }
}//GEN-LAST:event_txtQtyKeyPressed

    private void txtQtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyReleased
        if (txtQty.getText().equals("") == false) {
            DefaultTableModel tableModel1 = (DefaultTableModel) tableRaw.getModel();
            tableModel1.setRowCount(0);
            Connection conn = null;
            try {
                conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();

                i = 0;
                long item_id = 0;

                while (i < finisheditem.size()) {
                    if (finisheditem.get(i).getFinish_item_name().equals(comboItem.getSelectedItem())) {
                        item_id = finisheditem.get(i).getFinish_item_id();
                    }
                    i++;
                }

                //Adding list of ledgers to Particulars
                q = "select * from tblproductionrawmaterial where FinishedItem=" + item_id + "";
                rs1 = st.executeQuery(q);

                while (rs1.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tableRaw.getModel();
                    int j = tableRaw.getRowCount();
                    tableModel.setRowCount(tableRaw.getRowCount() + 1);
                    i = 0;
                    String item_name = "";
                    while (i < rawitem.size()) {
                        if (rawitem.get(i).getRaw_item_id() == rs1.getLong("RawItem")) {
                            item_name = rawitem.get(i).getRaw_item_name();
                        }
                        i++;
                    }
                    tableRaw.setValueAt(j + 1, j, 0);
                    tableRaw.setValueAt(item_name, j, 1);
                    int qty = 0;
                    qty = (Integer.parseInt(txtQty.getText())) * (rs1.getInt("qty"));
                    tableRaw.setValueAt(qty, j, 2);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddProductionProcess.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AddProductionProcess.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            DefaultTableModel tableModel1 = (DefaultTableModel) tableRaw.getModel();
            tableModel1.setRowCount(0);
            Connection conn = null;
            try {
                conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();

                i = 0;
                long item_id = 0;

                while (i < finisheditem.size()) {
                    if (finisheditem.get(i).getFinish_item_name().equals(comboItem.getSelectedItem())) {
                        item_id = finisheditem.get(i).getFinish_item_id();
                    }
                    i++;
                }

                //Adding list of ledgers to Particulars
                q = "select * from tblproductionrawmaterial where FinishedItem=" + item_id + "";
                rs1 = st.executeQuery(q);

                while (rs1.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tableRaw.getModel();
                    int j = tableRaw.getRowCount();
                    tableModel.setRowCount(tableRaw.getRowCount() + 1);
                    i = 0;
                    String item_name = "";
                    while (i < rawitem.size()) {
                        if (rawitem.get(i).getRaw_item_id() == rs1.getLong("RawItem")) {
                            item_name = rawitem.get(i).getRaw_item_name();
                        }
                        i++;
                    }
                    tableRaw.setValueAt(j + 1, j, 0);
                    tableRaw.setValueAt(item_name, j, 1);
                    tableRaw.setValueAt(0, j, 2);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddProductionProcess.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AddProductionProcess.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
}//GEN-LAST:event_txtQtyKeyReleased

    private void txtQtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
}//GEN-LAST:event_txtQtyKeyTyped

    private void btnProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessActionPerformed
        if (comboItem.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Finishing Item is not selected");
            comboItem.requestFocus();
        } else if (tableRaw.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Raw Material for this Item is not added");
            comboItem.requestFocus();
        } else {
            Connection conn = null;
            try {
                conn = DatabaseConnection1.GetConnection();
                PreparedStatement st;

                SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
                String dateNow = f.format(dat.getDate());

                q = "insert into tblProductionDetails(dat) values(?)";
                st = conn.prepareStatement(q);
                st.setString(1, dateNow);
                st.executeUpdate();

                int temp_id = 0;
                q = "select last_insert_id() as id";
                rs1 = st.executeQuery(q);
                if (rs1.next()) {
                    temp_id = rs1.getInt("id");
                } else {
                    temp_id = 1;
                }

                q = "insert into tblProductionItems(Prod_id,item,qty,type) values(?,?,?,?)";
                st = conn.prepareStatement(q);
                st.setString(1, "" + temp_id);
                i = 0;
                long item_id = 0;
                while (i < finisheditem.size()) {
                    if (finisheditem.get(i).getFinish_item_name().equals(comboItem.getSelectedItem())) {
                        item_id = finisheditem.get(i).getFinish_item_id();
                    }
                    i++;
                }
                st.setString(2, "" + item_id);
                st.setString(3, "" + Integer.parseInt(txtQty.getText()));
                st.setString(4, "Production");
                st.executeUpdate();

                //*******************************************************
                int DebCred = 0;
                Statement st2 = conn.createStatement();
                q = "select si_currentBalance,si_DebitOrCredit from tblStockItemCurrentBalance where si_id=" + item_id + "";
                rs1 = st2.executeQuery(q);
                if (rs1.next()) {
                    if (rs1.getInt("si_DebitOrCredit") == 2) //if prev transaction is credit
                    {
                        DebCred = rs1.getInt("si_currentBalance") + Integer.parseInt(txtQty.getText());
                        q = "update tblStockItemCurrentBalance set si_currentBalance=" + DebCred + ",si_DebitOrCredit=2 where si_id=" + item_id + "";
                    } else {
                        DebCred = rs1.getInt("si_currentBalance") - Integer.parseInt(txtQty.getText());
                        if (DebCred < 0) {
                            q = "update tblStockItemCurrentBalance set si_currentBalance=" + Math.abs(DebCred) + ",si_DebitOrCredit=2 where si_id=" + item_id + "";
                        } else {
                            q = "update tblStockItemCurrentBalance set si_currentBalance=" + DebCred + ",si_DebitOrCredit=1 where si_id=" + item_id + "";
                        }
                    }
                    st2.executeUpdate(q);
                }
                //*******************************************************


                i = 0;
                while (i < tableRaw.getRowCount()) {
                    if (tableRaw.getValueAt(i, 1) != null) {
                        q = "insert into tblProductionItems(Prod_id,item,qty,type) values(?,?,?,?)";
                        st = conn.prepareStatement(q);
                        st.setString(1, "" + temp_id);
                        int j = 0;
                        while (j < rawitem.size()) {
                            if (rawitem.get(j).getRaw_item_name().equals(tableRaw.getValueAt(i, 1))) {
                                item_id = rawitem.get(j).getRaw_item_id();
                            }
                            j++;
                        }
                        st.setString(2, "" + item_id);
                        st.setString(3, "" + Integer.parseInt(tableRaw.getValueAt(i, 2).toString()));
                        st.setString(4, "Debit");
                        st.executeUpdate();

                        //*******************************************************
                        DebCred = 0;
                        st2 = conn.createStatement();
                        q = "select si_currentBalance,si_DebitOrCredit from tblStockItemCurrentBalance where si_id=" + item_id + "";
                        rs1 = st2.executeQuery(q);
                        if (rs1.next()) {
                            if (rs1.getInt("si_DebitOrCredit") == 1) //if prev transaction is debit
                            {
                                DebCred = rs1.getInt("si_currentBalance") + Integer.parseInt(tableRaw.getValueAt(i, 2).toString());
                                q = "update tblStockItemCurrentBalance set si_currentBalance=" + DebCred + ",si_DebitOrCredit=1 where si_id=" + item_id + "";
                            } else {
                                DebCred = rs1.getInt("si_currentBalance") - Integer.parseInt(tableRaw.getValueAt(i, 2).toString());
                                if (DebCred < 0) {
                                    q = "update tblStockItemCurrentBalance set si_currentBalance=" + Math.abs(DebCred) + ",si_DebitOrCredit=1 where si_id=" + item_id + "";
                                } else {
                                    q = "update tblStockItemCurrentBalance set si_currentBalance=" + DebCred + ",si_DebitOrCredit=2 where si_id=" + item_id + "";
                                }
                            }
                            st2.executeUpdate(q);
                        }
                        //*******************************************************


                    }
                    i++;
                }
                JOptionPane.showMessageDialog(this, "Process is submitted");

                comboItem.setSelectedIndex(0);
                txtQty.setText("1");
                comboItem.requestFocus();
                DefaultTableModel tableModel = (DefaultTableModel) tableRaw.getModel();
                tableModel.setRowCount(0);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AddProductionProcess.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
}//GEN-LAST:event_btnProcessActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        try {
            MainClass m = new MainClass();
            m.menuselection(5);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AddProductionProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnBackActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        btnBack.setMnemonic(KeyEvent.VK_B);
        btnProcess.setMnemonic(KeyEvent.VK_S);
        Connection conn = null;
        try {
            String col[] = {"Index", "Raw Item", "Quantity"};
            String data[][] = {{"", "", ""}};
            DefaultTableModel model = new DefaultTableModel(data, col);
            tableRaw.setModel(model);

            model.setColumnCount(3);
            JTableHeader header = tableRaw.getTableHeader();
            header.setBackground(Color.yellow);

            DefaultTableModel tableModel = (DefaultTableModel) tableRaw.getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(3);

            tableRaw.setRowSelectionAllowed(true);
            i = 0;
            while (i < finisheditem.size()) {
                finisheditem.remove(i);
            }
            i = 0;
            while (i < rawitem.size()) {
                rawitem.remove(i);
            }

            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            Statement st1 = conn.createStatement();

            long g_id = 0;    //find id of finished board group
            q = "select sg_id from tblstockgroup where sg_name='Finished Board'";
            rs1 = st.executeQuery(q);
            if (rs1.next()) {
                g_id = rs1.getLong("sg_id");
            }
            rs1.close();

            //Adding list of ledgers to Particulars
            q = "select si_id,si_name from tblstockitem where si_under=" + g_id + "";
            rs1 = st.executeQuery(q);
            i = 0;
            comboItem.removeAllItems();
            comboItem.addItem("");
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
             /*Code Snippet added by Sudeep on 12-01-2013 At:05.10PM*/


            while (rs1.next()) {
                TemporaryClass t1 = new TemporaryClass();
                t1.setFinish_item_id(Integer.parseInt(rs1.getString("si_id")));
                t1.setFinish_item_name(rs1.getString("si_name"));
                finisheditem.add(t1);
                comboItem.addItem(rs1.getString("si_name"));
                comboItem.setEditable(true);
                tf = (JTextField) comboItem.getEditor().getEditorComponent();

                GroupItems.add(rs1.getString("si_name"));

            }
            Collections.sort(
                    GroupItems,
                    new Comparator<String>() {
                        public int compare(String lhs, String rhs) {
                            return lhs.compareToIgnoreCase(rhs);
                        }
                    });


            //cmbGroupAlter_Under.addItem(GroupItems);

            for (int i = 0; i < GroupItems.size(); i++) {
                comboItem.addItem(GroupItems.get(i));
            }

            comboItem.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() != 38 && e.getKeyCode() != 40 && e.getKeyCode() != 10) {
                        String a = comboItem.getEditor().getItem().toString();
                        comboItem.removeAllItems();
                        int st = 0;

                        for (int i = 0; i < GroupItems.size(); i++) {
                            if (GroupItems.get(i).startsWith(a)) {
                                comboItem.addItem(GroupItems.get(i));
                                st++;
                            }
                        }
                        comboItem.getEditor().setItem(new String(a));
                        comboItem.hidePopup();
                        if (st != 0) {
                            comboItem.showPopup();
                        }
                    }
                }
            });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            

            q = "select si_id,si_name from tblstockitem";
            rs1 = st.executeQuery(q);
            i = 0;
            while (rs1.next()) {
                TemporaryClass t1 = new TemporaryClass();
                t1.setRaw_item_id(Integer.parseInt(rs1.getString("si_id")));
                t1.setRaw_item_name(rs1.getString("si_name"));
                rawitem.add(t1);
            }
            Calendar currentDate = Calendar.getInstance();
            dat.setDate(currentDate.getTime());
            comboItem.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(AddRawMaterial.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AddProductionProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_formInternalFrameOpened

    private void txtQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtyActionPerformed

    private void tableRawKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableRawKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tableRaw.getValueAt(tableRaw.getSelectedRow(), 1) == null) {
                btnProcessActionPerformed(null);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtQty.requestFocus();
        }
    }//GEN-LAST:event_tableRawKeyPressed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnProcess;
    private javax.swing.JComboBox comboItem;
    private com.toedter.calendar.JDateChooser dat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableRaw;
    private javax.swing.JTextField txtQty;
    // End of variables declaration//GEN-END:variables
}
