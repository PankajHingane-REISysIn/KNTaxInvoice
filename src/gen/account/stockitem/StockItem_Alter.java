package gen.account.stockitem;

import gen.account.stockgroup.StockGroup_Create;
import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.mainclass.MainClass;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

public class StockItem_Alter extends javax.swing.JInternalFrame {

    ArrayList<StockItemTemp> arrayListFinish = new ArrayList<StockItemTemp>();
    ArrayList<StockItemTemp> arrayListStockgroup = new ArrayList<StockItemTemp>();
    ArrayList<StockItemTemp> arrayListUOM = new ArrayList<StockItemTemp>();
    ArrayList<StockItemData> arrayListStockItem = new ArrayList<StockItemData>();
    DefaultTableModel tableModel1 = new DefaultTableModel();
    DefaultTableModel tableModel2 = new DefaultTableModel();
    static int window = 0;
    static String under = "", unit = "";
    static String opening_balance = "";
    int i = 0;
    long RowId = 0;
    JTextField tf, tf1;
    private final Vector<String> v = new Vector<String>();
    private final Vector<String> v1 = new Vector<String>();
    private final Vector<String> v2 = new Vector<String>();
    String cunder = "";
    String cunit = "";
    static int activation = 0;
    static int combocontrol = 0;
    static int comboFocusControl = 0;

    // ArrayList<String> GroupItems=new ArrayList<String>();
    //ArrayList<String> GroupItems1=new ArrayList<String>();
    public StockItem_Alter(String s) {
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
        jPanel1 = new javax.swing.JPanel();
        balance = new javax.swing.JLabel();
        rate = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtAlias = new javax.swing.JTextField();
        comboUnder = new javax.swing.JComboBox();
        comboUnit = new javax.swing.JComboBox();
        btnUpdate = new javax.swing.JButton();
        btnAddStockGroup = new javax.swing.JButton();
        txtRate = new javax.swing.JFormattedTextField();
        txtBalance = new javax.swing.JFormattedTextField();
        PanelStockItemAlter2 = new javax.swing.JPanel();
        txtStockItemAlter_grid = new javax.swing.JTextField();
        btnStockItemAlter_Back = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableStockItem = new javax.swing.JTable();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 590));
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
                formInternalFrameDeactivated(evt);
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        balance.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        balance.setText("Opening Balance");

        rate.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rate.setText(" Rate");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Unit");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Under");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Alias");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Name");

        txtName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNameKeyPressed(evt);
            }
        });

        txtAlias.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtAlias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAliasKeyPressed(evt);
            }
        });

        comboUnder.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        comboUnder.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboUnderItemStateChanged(evt);
            }
        });
        comboUnder.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                comboUnderFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                comboUnderFocusLost(evt);
            }
        });
        comboUnder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboUnderKeyPressed(evt);
            }
        });

        comboUnit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        comboUnit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                comboUnitFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                comboUnitFocusLost(evt);
            }
        });
        comboUnit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboUnitKeyPressed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        btnUpdate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnUpdateKeyPressed(evt);
            }
        });

        btnAddStockGroup.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAddStockGroup.setText("Add Group");
        btnAddStockGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStockGroupActionPerformed(evt);
            }
        });
        btnAddStockGroup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAddStockGroupKeyPressed(evt);
            }
        });

        txtRate.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRateActionPerformed(evt);
            }
        });
        txtRate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRateFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRateFocusLost(evt);
            }
        });
        txtRate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRateKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRateKeyTyped(evt);
            }
        });

        txtBalance.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtBalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBalanceActionPerformed(evt);
            }
        });
        txtBalance.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBalanceFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBalanceFocusLost(evt);
            }
        });
        txtBalance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBalanceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBalanceKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(btnUpdate))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(rate)
                            .addComponent(balance))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboUnder, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtRate, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnAddStockGroup))
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAlias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(comboUnder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddStockGroup))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rate))
                        .addGap(18, 18, 18)
                        .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(balance))
                .addGap(52, 52, 52)
                .addComponent(btnUpdate)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        txtStockItemAlter_grid.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtStockItemAlter_grid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockItemAlter_gridActionPerformed(evt);
            }
        });
        txtStockItemAlter_grid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStockItemAlter_gridKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStockItemAlter_gridKeyReleased(evt);
            }
        });

        btnStockItemAlter_Back.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnStockItemAlter_Back.setText("Back");
        btnStockItemAlter_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockItemAlter_BackActionPerformed(evt);
            }
        });
        btnStockItemAlter_Back.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnStockItemAlter_BackKeyPressed(evt);
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tableStockItemMouseEntered(evt);
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
            .addGroup(PanelStockItemAlter2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelStockItemAlter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                    .addComponent(txtStockItemAlter_grid, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(PanelStockItemAlter2Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(btnStockItemAlter_Back)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelStockItemAlter2Layout.setVerticalGroup(
            PanelStockItemAlter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelStockItemAlter2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(txtStockItemAlter_grid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnStockItemAlter_Back))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelStockItemAlter2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelStockItemAlter2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtAlias.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtStockItemAlter_grid.requestFocus();
        }
}//GEN-LAST:event_txtNameKeyPressed

    private void txtAliasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAliasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            comboUnder.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtName.requestFocus();
        }
}//GEN-LAST:event_txtAliasKeyPressed

    private void comboUnderItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboUnderItemStateChanged
}//GEN-LAST:event_comboUnderItemStateChanged

    private void comboUnderFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboUnderFocusGained
        comboUnder.showPopup();
}//GEN-LAST:event_comboUnderFocusGained

    private void comboUnderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboUnderKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            comboUnit.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtAlias.requestFocus();
        }
}//GEN-LAST:event_comboUnderKeyPressed

    private void comboUnitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboUnitFocusGained
        comboUnit.showPopup();
}//GEN-LAST:event_comboUnitFocusGained

    private void comboUnitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboUnitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtRate.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            comboUnder.requestFocus();
        }
}//GEN-LAST:event_comboUnitKeyPressed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        String name1 = txtName.getText().trim();
        if (txtName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Enter Data in Name  Fields");
            txtName.requestFocus();
        } //        else if(!name1.equals(txtStockItemAlter_grid.getText().trim()))
        //        {
        //            JOptionPane.showMessageDialog(this, "Selected Stock Item does not exist");
        //            txtStockItemAlter_grid.requestFocus();
        //        }
        else if (comboUnder.getSelectedItem() == null || comboUnder.getSelectedItem() == "")// || tf.getText().trim().equalsIgnoreCase("") || ComboGroupCreate_Under.getSelectedItem().toString().trim().equals("") ) 
        {
            System.out.println("eeeeeeeeeeeeeeeee");
            JOptionPane.showMessageDialog(this, "Select Data for Under Group Fields");
            comboUnder.requestFocus();
        } else if (comboUnit.getSelectedItem() == null || comboUnit.getSelectedItem() == "")// || tf.getText().trim().equalsIgnoreCase("") || ComboGroupCreate_Under.getSelectedItem().toString().trim().equals("") ) 
        {
            System.out.println("eeeeeeeeeeeeeeeee");
            JOptionPane.showMessageDialog(this, "Select Data for Unit Group Fields");
            comboUnit.requestFocus();
        } else if (!checkElementPresence(v, tf.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Selected Value For Under Field is not valid");
            //    scrollpaneMain.getVerticalScrollBar().setValue(0);  //go to top of scrollpane
            tf.setText("");
            comboUnder.requestFocus();
        } else if (!checkElementPresence1(v1, tf1.getText())) {
            JOptionPane.showMessageDialog(this, "Selected Value For Unit Field is not valid");
            //    scrollpaneMain.getVerticalScrollBar().setValue(0);  //go to top of scrollpane
            tf1.setText("");
            comboUnit.requestFocus();
        } else {
            String Name = txtName.getText().trim();
            String Alias = txtAlias.getText().trim();
            long under = 0;
            i = 0;
            while (i < arrayListStockgroup.size()) {
                if (arrayListStockgroup.get(i).getSg_name().equals(comboUnder.getSelectedItem())) {
                    under = arrayListStockgroup.get(i).getSg_id();
                }
                i++;
            }
            long uom = 0;
            i = 0;
            while (i < arrayListUOM.size()) {
                if (arrayListUOM.get(i).getUom_name().equals(comboUnit.getSelectedItem())) {
                    uom = arrayListUOM.get(i).getUom_id();
                }
                i++;
            }

            double Rate = 0;
            if (txtRate.getText().equals("")) {
                Rate = 0;
            } else {
                Rate = Double.parseDouble(txtRate.getText().trim());
            }
            double Balance = 0;
            if (txtBalance.getText().trim().equals("")) {
                Balance = 0;
            } else {
                Balance = Double.parseDouble(txtBalance.getText().trim());
            }

//                 double length = 0;
//            if (txtLength.getText().trim().equals("")) {
//                length = 0;
//            } else {
//                length = Double.parseDouble(txtLength.getText().trim());
//            }
//            
//            double width = 0;
//             if (txtWidth.getText().trim().equals("")) {
//                width = 0;
//            } else {
//                width = Double.parseDouble(txtWidth.getText().trim());
//            }
//             
//            double thickness = 0;
//            if (txtThickness.getText().trim().equals("")) {
//                thickness = 0;
//            } else {
//                thickness = Double.parseDouble(txtThickness.getText().trim());
//            }




            Connection conn = null;
            try {
                conn = DatabaseConnection1.GetConnection();
                conn.setAutoCommit(false);
                Statement st = conn.createStatement();
                Statement st1 = conn.createStatement();
                ResultSet rs1;
                int temp_id = 0;
                String q = "";

                // q = "update tblstockitem set si_name='" + Name + "',si_alias='" + Alias + "',si_under=" + under + ",si_rate=" + Rate + ",si_unitOfMeasure=" + uom + ",si_openingBalance=" + Balance + " where si_id=" + RowId + "";

                // st.executeUpdate(q);
                PreparedStatement ps;
                q = "update tblstockitem set si_name=?,si_alias=?,si_under=?,si_rate=?,si_unitOfMeasure=?,si_openingBalance=?,si_length=?,si_width=?,si_thickness=? where si_id=?";
                // q="insert into tblstockitem(si_name,si_alias,si_under,si_rate,si_unitOfMeasure,si_openingBalance) values(?,?,?,?,?,?)";
                ps = conn.prepareStatement(q);
                ps.setString(1, Name);
                ps.setString(2, Alias);
                ps.setLong(3, under);
                ps.setDouble(4, Rate);
                ps.setLong(5, uom);
                ps.setDouble(6, Balance);
                ps.setDouble(7, 0);
                ps.setDouble(8, 0);
                ps.setDouble(9, 0);
                ps.setLong(10, RowId);
                ps.executeUpdate();

                String date1234 = "1500-09-23";
                q = "update tblStockItemOPBlUpdateRecord set si_openingBalance=?,si_under = ?,si_name=?where si_id=? and update_opening_balance_date=?";
                System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDD" + q + Balance + "  " + RowId + "  " + date1234);
                ps = conn.prepareStatement(q);
                ps.setDouble(1, Balance);
                ps.setLong(2, under);
                ps.setString(3, Name);
                ps.setLong(4, RowId);
                ps.setString(5, date1234);
                ps.executeUpdate();

                q = "update tblStockItemOPBlUpdateRecord set si_under = ?,si_name=?where si_id=? ";
                ps = conn.prepareStatement(q);
                ps.setLong(1, under);
                ps.setString(2, Name);
                ps.setLong(3, RowId);
                ps.executeUpdate();


                //directly inserted unit of measure and not id
//                q = "insert into tblStockItemCurrentBalance(si_id,si_currentBalance,si_DebitOrCredit) values(" + temp_id + "," + Integer.parseInt(txtBalance.getText()) + ",2)";  //2 for credit and 1 for debit
//                st1.executeUpdate(q);
                conn.commit();
                Constants.STOCK_ITEM_TIME_STAMP++;
                JOptionPane.showMessageDialog(this, "Stock Item Modified Successfully");
                txtAlias.setText("");
                txtBalance.setText("");
                txtName.setText("");
                txtRate.setText("");
                tf.setText("");
                tf1.setText("");

//                txtLength.setText("");
//                txtWidth.setText("");
//                txtThickness.setText(""); 

                //comboUnder.setSelectedIndex(0);
                //comboUnit.setSelectedIndex(0);
                //cmbStockItem_Type.setSelectedIndex(0);
                rate.setVisible(true);
                balance.setVisible(true);
                txtRate.setVisible(true);
                txtBalance.setVisible(true);

                txtName.setEnabled(false);
                txtAlias.setEnabled(false);
                txtRate.setEnabled(false);
                txtBalance.setEnabled(false);
                comboUnder.setEnabled(false);
                comboUnit.setEnabled(false);
                btnUpdate.setEnabled(false);
                btnAddStockGroup.setEnabled(false);

                txtStockItemAlter_grid.setText("");
                txtStockItemAlter_grid.requestFocus();
                gridList();
                tableStockItem.getColumnModel().getColumn(1).setWidth(1);
                tableStockItem.getColumnModel().getColumn(1).setMinWidth(1);
                tableStockItem.getColumnModel().getColumn(1).setMaxWidth(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StockItem_Alter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        gridList();
        tableStockItem.getColumnModel().getColumn(1).setWidth(1);
        tableStockItem.getColumnModel().getColumn(1).setMinWidth(1);
        tableStockItem.getColumnModel().getColumn(1).setMaxWidth(1);

}//GEN-LAST:event_btnUpdateActionPerformed

    private void txtStockItemAlter_gridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockItemAlter_gridActionPerformed
        tableStockItem.requestFocus();
}//GEN-LAST:event_txtStockItemAlter_gridActionPerformed

    private void txtStockItemAlter_gridKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockItemAlter_gridKeyReleased
        try {
            DefaultTableModel d = (DefaultTableModel) tableStockItem.getModel();

            String text = txtStockItemAlter_grid.getText().trim();
            int size = txtStockItemAlter_grid.getText().trim().length();
            d.setRowCount(0);
            int i = 0;
            for (i = 0; i < arrayListStockItem.size(); i++) {
                String record = arrayListStockItem.get(i).getName();
                int id = arrayListStockItem.get(i).getId();
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
            System.out.println(e);
        }
}//GEN-LAST:event_txtStockItemAlter_gridKeyReleased

    private void btnStockItemAlter_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockItemAlter_BackActionPerformed
        try {
            MainClass m = new MainClass();
            m.menuselection(2);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(StockItem_Alter.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnStockItemAlter_BackActionPerformed

    private void tableStockItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableStockItemMouseClicked
        if (tableStockItem.isColumnSelected(0)) {
            txtName.setText("");
            txtAlias.setText("");
            txtBalance.setText("");
            txtRate.setText("");
            comboUnder.setSelectedIndex(0);
            comboUnit.setSelectedIndex(0);
            tableModel1.setRowCount(0);
            tableModel1.setColumnCount(1);
            tableModel2.setRowCount(0);
            tableModel2.setColumnCount(1);

            int row = tableStockItem.getSelectedRow();
            RowId = Long.parseLong(tableStockItem.getValueAt(row, 1).toString());

            txtStockItemAlter_grid.setText("");
            String name = (String) tableStockItem.getValueAt(row, 0);
            txtStockItemAlter_grid.setText(name);
            Connection conn = null;
            try {
                conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();
                Statement st1 = conn.createStatement();
                Statement st2 = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from tblstockitem where si_id=" + RowId + "");
                if (rs.next()) {

                    txtName.setEnabled(true);
                    txtAlias.setEnabled(true);
                    txtRate.setEnabled(true);
                    txtBalance.setEnabled(true);
                    comboUnder.setEnabled(true);
                    comboUnit.setEnabled(true);
                    btnUpdate.setEnabled(true);
                    btnAddStockGroup.setEnabled(true);

                    txtName.setText(rs.getString("si_name"));
                    txtAlias.setText(rs.getString("si_alias"));

                    // txtBalance.setText(rs.getString("si_openingBalance"));

                    /////////// Atul Code ////////////////////////////              
                    if (Double.parseDouble("" + rs.getString("si_rate")) == 0.00) {
                        txtRate.setText("" + 0);
                    } else {
                        txtRate.setText(rs.getString("si_rate"));
                    }


                    if (Double.parseDouble("" + rs.getString("si_openingBalance")) == 0.00) {
                        txtBalance.setText("" + 0);
                    } else {
                        txtBalance.setText(Constants.DECIMAL_FORMAT.format(rs.getDouble("si_openingBalance")));
                    }

//               if(Double.parseDouble(""+rs.getString("si_length"))==0.00)
//               {
//                txtLength.setText(""+0);
//               }
//               else
//               {
//                txtLength.setText(rs.getString("si_length"));
//               }
//               
//               if(Double.parseDouble(""+rs.getString("si_width"))==0.00)
//               {
//                txtWidth.setText(""+0);
//               }
//               else
//               {
//                txtWidth.setText(rs.getString("si_width"));
//               }
//               
//               if(Double.parseDouble(""+rs.getString("si_thickness"))==0.00)
//               {
//                txtThickness.setText(""+0);
//               }
//               else
//               {
//                txtThickness.setText(rs.getString("si_thickness"));
//               }
                    // txtRate.setText(rs.getString("si_rate"));
                    long id = rs.getLong("si_under");

                    String unitname = "";
                    i = 0;
                    System.out.println(arrayListUOM);
                    while (i < arrayListUOM.size()) {

                        if (arrayListUOM.get(i).getUom_id() == rs.getLong("si_unitOfMeasure")) {
                            unitname = arrayListUOM.get(i).getUom_name();
                            System.out.println("umit name:" + unitname);
                        }
                        i++;
                    }

                    //comboUnit.removeAllItems();
                    comboUnit.setSelectedItem(unitname);
                    tf1.setText("" + unitname);
                    ResultSet rs1 = st1.executeQuery("select sg_name from tblstockgroup where sg_id=" + id + "");
                    if (rs1.next()) {
                        comboUnder.setSelectedItem(rs1.getString("sg_name"));
                        tf.setText(rs1.getString("sg_name"));
                    }



                    if (window == 1) {
                        if (Double.parseDouble("" + rs.getString("si_openingBalance")) == 0.00) {
                            opening_balance = "" + 0;
                        } else {
                            opening_balance = "" + rs.getString("si_openingBalance");
                        }

                        unit = "" + unitname;
                        under = "" + rs1.getString("sg_name");

                    }
                    rs1.close();
                    txtName.requestFocus();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StockItem_Alter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        cunder = tf.getText().trim();
        cunit = tf1.getText().trim();
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
//            txtStockItemAlter_grid.requestFocus();
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
            txtStockItemAlter_grid.requestFocus();
        } else {
            int k = evt.getKeyCode();
            char c = evt.getKeyChar();
            if (k >= 48 && k <= 57) {
                txtStockItemAlter_grid.setText(txtStockItemAlter_grid.getText().trim() + "" + c);
                String text = txtStockItemAlter_grid.getText().trim();
                int size = text.length();
                DefaultTableModel model = new DefaultTableModel();
                tableStockItem.setModel(model);
                tableStockItem.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < arrayListStockItem.size(); i++) {
                    String record = arrayListStockItem.get(i).getName();

                    if (record.length() >= size) {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) {
                            int r = model.getRowCount();
                            model.setRowCount(r + 1);
                            model.setColumnCount(2);
                            tableStockItem.setValueAt(record, r, 0);
                            tableStockItem.setValueAt(arrayListStockItem.get(i).getId(), r, 1);
                        }
                    }
                }
            } else if (k >= 65 && k <= 90) {
                txtStockItemAlter_grid.setText(txtStockItemAlter_grid.getText().trim() + "" + c);
                String text = txtStockItemAlter_grid.getText().trim();
                int size = text.length();
                DefaultTableModel model = new DefaultTableModel();
                tableStockItem.setModel(model);
                tableStockItem.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < arrayListStockItem.size(); i++) {
                    String record = arrayListStockItem.get(i).getName();

                    if (record.length() >= size) {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) {
                            int r = model.getRowCount();
                            model.setRowCount(r + 1);
                            model.setColumnCount(2);
                            tableStockItem.setValueAt(record, r, 0);
                            tableStockItem.setValueAt(arrayListStockItem.get(i).getId(), r, 1);
                        }
                    }
                }
            } else if (k >= 96 && k <= 105) {
                txtStockItemAlter_grid.setText(txtStockItemAlter_grid.getText().trim() + "" + c);
                String text = txtStockItemAlter_grid.getText().trim();
                int size = text.length();
                DefaultTableModel model = new DefaultTableModel();
                tableStockItem.setModel(model);
                tableStockItem.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < arrayListStockItem.size(); i++) {
                    String record = arrayListStockItem.get(i).getName();

                    if (record.length() >= size) {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) {
                            int r = model.getRowCount();
                            model.setRowCount(r + 1);
                            model.setColumnCount(2);
                            tableStockItem.setValueAt(record, r, 0);
                            tableStockItem.setValueAt(arrayListStockItem.get(i).getId(), r, 1);
                        }
                    }
                }
            } else if (k == 8) {
                //txt.getText().length()=txt.getText().length()-1;
                String text = txtStockItemAlter_grid.getText().trim();
                int size = text.length();
                String s1 = text.substring(0, size - 1);
                txtStockItemAlter_grid.setText(s1);
                size = txtStockItemAlter_grid.getText().trim().length();
                DefaultTableModel model = new DefaultTableModel();
                tableStockItem.setModel(model);
                tableStockItem.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < arrayListStockItem.size(); i++) {
                    String record = arrayListStockItem.get(i).getName();

                    if (record.length() >= size) {
                        String s2 = record.substring(0, size);
                        if (s2.equalsIgnoreCase(txtStockItemAlter_grid.getText().trim())) {
                            int r = model.getRowCount();
                            model.setRowCount(r + 1);
                            model.setColumnCount(2);
                            tableStockItem.setValueAt(record, r, 0);
                            tableStockItem.setValueAt(arrayListStockItem.get(i).getId(), r, 1);
                        }
                    }
                }
            }
        }



        //------------------------------------------------
}//GEN-LAST:event_tableStockItemKeyPressed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        btnUpdate.setMnemonic(KeyEvent.VK_U);
        btnStockItemAlter_Back.setMnemonic(KeyEvent.VK_B);
        btnAddStockGroup.setMnemonic(KeyEvent.VK_O);

        txtName.setEnabled(false);
        txtAlias.setEnabled(false);
        txtRate.setEnabled(false);
        txtBalance.setEnabled(false);
        comboUnder.setEnabled(false);
        comboUnit.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnAddStockGroup.setEnabled(false);

        window = 1;

        this.gridList();
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();

            comboUnder.removeAllItems();
            comboUnder.addItem("");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
            /*Code Snippet added by Sudeep on 12-01-2013 At:12.30PM*/
            tf = (JTextField) comboUnder.getEditor().getEditorComponent();
            comboUnder.setEditable(true);
            ResultSet rs = st.executeQuery("select sg_id,sg_name from tblstockgroup where sg_name not in ('Primary')");
            while (rs.next()) {
                if (rs.getString("sg_name").equals("Primary") == false) {
                    StockItemTemp s = new StockItemTemp();
                    s.setSg_id(rs.getInt("sg_id"));
                    s.setSg_name(rs.getString("sg_name"));
                    arrayListStockgroup.add(s);
                    //    comboUnder.addItem(rs.getString("sg_name"));


                }
                v.add(rs.getString("sg_name"));
            }

            Collections.sort(
                    v,
                    new Comparator<String>() {
                        public int compare(String lhs, String rhs) {
                            return lhs.compareToIgnoreCase(rhs);
                        }
                    });


            //cmbGroupAlter_Under.addItem(GroupItems);
            int size1 = v.size();
            for (int i = 0; i < size1; i++) {
                comboUnder.addItem(v.get(i));
            }

            tf.addKeyListener(new KeyAdapter() {
                public void keyTyped(final KeyEvent e) {
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            String text = tf.getText();
                            if (text.length() == 0) {
                                comboUnder.hidePopup();
                                setModel(new DefaultComboBoxModel(v), "");
                            } else {
                                DefaultComboBoxModel m = getSuggestedModel(v, text);
                                if (m.getSize() == 0 || hide_flag) {
                                    comboUnder.hidePopup();
                                    hide_flag = false;
                                } else {
                                    setModel(m, text);
                                    comboUnder.showPopup();
                                }
                            }


                            String text1 = tf.getText().trim();
                            int code = e.getKeyCode();
                            if (code == KeyEvent.VK_ENTER) {
                                System.out.println("Enter");
                                if (!v.contains(text)) {
                                    v.addElement(text);
                                    Collections.sort(v);
                                    setModel(getSuggestedModel(v, text), text);
                                }
                                hide_flag = true;
                            } else if (code == KeyEvent.VK_ESCAPE) {
                                hide_flag = true;
                            } else if (code == KeyEvent.VK_RIGHT) {
                                for (int i = 0; i < v.size(); i++) {
                                    String str = v.elementAt(i);
                                    if (str.startsWith(text)) {
                                        comboUnder.setSelectedIndex(-1);
                                        tf.setText(str);
                                        return;
                                    }
                                }
                            }

                        }
                    });

                }
            });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            tf.addKeyListener(new KeyAdapter() {
                public void keyPressed(final KeyEvent event) {
                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {


                            if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                                if (comboUnder.getSelectedItem().equals("")) // if(tf.getText().equals(""))
                                {
                                    //JOptionPane.showMessageDialog(rootPane, "Enter data for Under Field");
                                    // comboUnder.requestFocus();
                                    tf1.requestFocus();
                                } else {
                                    tf1.requestFocus();
                                }
                                //comboUnit.requestFocus();
                            }
                            if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                txtAlias.requestFocus();
                            }
                        }
                    });
                }
            });

            tf.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    //  throw new UnsupportedOperationException("Not supported yet.");
                    String text = tf.getText().trim();
                    if (text.length() == 0) {
                        comboUnder.hidePopup();
                        setModel(new DefaultComboBoxModel(v), "");
                    } else {
                        DefaultComboBoxModel m = getSuggestedModel(v, text);
                        if (m.getSize() == 0 || hide_flag) {
                            comboUnder.hidePopup();
                            hide_flag = false;
                        } else {
                            setModel(m, text);
                            comboUnder.showPopup();
                        }
                    }
                    tf.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    //  throw new UnsupportedOperationException("Not supported yet.");
                }
            });

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            rs = st.executeQuery("select f_id,f_name from tblfinishtype");
            int i = 0;
            while (rs.next()) {
                StockItemTemp f = new StockItemTemp();
                f.setId(rs.getInt("f_id"));
                f.setName(rs.getString("f_name"));
                arrayListFinish.add(f);
//                int row = tbl1.getRowCount();
//                tableModel1.setRowCount(row + 1);
//                tbl1.setValueAt(rs.getString("f_name"), row, 0);
                i++;
            }

            comboUnit.removeAllItems();
            comboUnit.addItem("");
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            

            /*Code Snippet added by Sudeep on 12-01-2013 At:12.35PM*/

            tf1 = (JTextField) comboUnit.getEditor().getEditorComponent();
            comboUnit.setEditable(true);
            rs = st.executeQuery("select uom_id,uom_formalName from tblunitofmeasure");
            while (rs.next()) {
                StockItemTemp u = new StockItemTemp();
                u.setUom_id(rs.getInt("uom_id"));
                u.setUom_name(rs.getString("uom_formalName"));
                arrayListUOM.add(u);
                //  comboUnit.addItem(rs.getString("uom_formalName"));



                v1.add(rs.getString("uom_formalName"));
            }

            Collections.sort(
                    v1,
                    new Comparator<String>() {
                        public int compare(String lhs, String rhs) {
                            return lhs.compareToIgnoreCase(rhs);
                        }
                    });


            //cmbGroupAlter_Under.addItem(GroupItems);
            int size2 = v1.size();
            for (int j = 0; j < size2; j++) {
                comboUnit.addItem(v1.get(i));
            }

            tf1.addKeyListener(new KeyAdapter() {
                public void keyTyped(final KeyEvent e) {
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            String text = tf1.getText().trim();
                            if (text.length() == 0) {
                                comboUnit.hidePopup();
                                setModel1(new DefaultComboBoxModel(v1), "");
                            } else {
                                DefaultComboBoxModel m = getSuggestedModel1(v1, text);
                                if (m.getSize() == 0 || hide_flag) {
                                    comboUnit.hidePopup();
                                    hide_flag = false;
                                } else {
                                    setModel1(m, text);
                                    comboUnit.showPopup();
                                }
                            }


                            String text1 = tf1.getText().trim();
                            int code = e.getKeyCode();
                            if (code == KeyEvent.VK_ENTER) {
                                System.out.println("Enter");
                                if (!v1.contains(text)) {
                                    v1.addElement(text);
                                    Collections.sort(v1);
                                    setModel1(getSuggestedModel1(v1, text), text);
                                }
                                hide_flag = true;
                            } else if (code == KeyEvent.VK_ESCAPE) {
                                hide_flag = true;
                            } else if (code == KeyEvent.VK_RIGHT) {
                                for (int i = 0; i < v1.size(); i++) {
                                    String str = v1.elementAt(i);
                                    if (str.startsWith(text)) {
                                        comboUnit.setSelectedIndex(-1);
                                        tf1.setText(str);
                                        return;
                                    }
                                }
                            }

                        }
                    });

                }
            });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            tf1.addKeyListener(new KeyAdapter() {
                public void keyPressed(final KeyEvent event) {
                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {


                            if (event.getKeyCode() == KeyEvent.VK_ENTER) {

                                if (comboUnit.getSelectedItem().equals("")) {
                                    //   JOptionPane.showMessageDialog(rootPane, "Enter data for Under Field");
                                    //  comboUnit.requestFocus();
                                    txtRate.requestFocus();
                                } else {
                                    txtRate.requestFocus();
                                }
                                //txtRate.requestFocus();
                            }
                            if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                tf.requestFocus();
                            }
                        }
                    });
                }
            });

            tf1.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    //  throw new UnsupportedOperationException("Not supported yet.");
                    String text = tf1.getText().trim();
                    if (text.length() == 0) {
                        comboUnit.hidePopup();
                        setModel1(new DefaultComboBoxModel(v1), "");
                    } else {
                        DefaultComboBoxModel m = getSuggestedModel1(v1, text);
                        if (m.getSize() == 0 || hide_flag) {
                            comboUnit.hidePopup();
                            hide_flag = false;
                        } else {
                            setModel1(m, text);
                            comboUnit.showPopup();
                        }
                    }
                    tf1.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet.");
                }
            });


            tf1.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet.");
                    String text = tf1.getText().trim();
                    if (text.length() == 0) {
                        comboUnit.hidePopup();
                        setModel1(new DefaultComboBoxModel(v1), "");
                    } else {
                        DefaultComboBoxModel m = getSuggestedModel1(v1, text);
                        if (m.getSize() == 0 || hide_flag) {
                            comboUnit.hidePopup();
                            hide_flag = false;
                        } else {
                            setModel1(m, text);
                            comboUnit.showPopup();
                        }
                    }
                    tf1.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet.");
                }
            });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            




            rs = st.executeQuery("select uom_id,uom_formalName from tblunitofmeasure");
            while (rs.next()) {
                StockItemTemp u = new StockItemTemp();
                u.setUom_id(rs.getInt("uom_id"));
                u.setUom_name(rs.getString("uom_formalName"));
                arrayListUOM.add(u);
                comboUnit.addItem(rs.getString("uom_formalName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(StockItem_Alter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        txtStockItemAlter_grid.requestFocus();

        tableStockItem.getColumnModel().getColumn(1).setWidth(1);
        tableStockItem.getColumnModel().getColumn(1).setMinWidth(1);
        tableStockItem.getColumnModel().getColumn(1).setMaxWidth(1);
    }//GEN-LAST:event_formInternalFrameOpened

    private void txtStockItemAlter_gridKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockItemAlter_gridKeyPressed
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
    }//GEN-LAST:event_txtStockItemAlter_gridKeyPressed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        combocontrol = 0;
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnAddStockGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStockGroupActionPerformed
        StockGroup_Create group = new StockGroup_Create("Create New Stock Group");
        group.setVisible(true);
        group.pack();
        group.setVisible(true);
        this.getParent().add(group);
        this.getParent().setVisible(true);
        Dimension desktopSize = this.getParent().getSize();
        Dimension jInternalFrameSize = group.getSize();
        group.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
//            group.setSize(desktopSize);
//            group.setPreferredSize(desktopSize);
        try {
            group.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }

        BasicInternalFrameUI ui = (BasicInternalFrameUI) group.getUI();

        Component north = ui.getNorthPane();
        MouseMotionListener[] actions =
                (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

        for (int i = 0; i < actions.length; i++) {
            north.removeMouseMotionListener(actions[i]);
        }

        comboFocusControl = 1;
    }//GEN-LAST:event_btnAddStockGroupActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        Connection conn = null;
        try {
            if (combocontrol == 1) {
                comboUnder.requestFocus();
            }

            txtStockItemAlter_grid.requestFocus();

            // txtBalance.setText("0");
            comboUnder.removeAllItems();
            comboUnder.addItem("");
            v2.clear();

//        Connection conn = null;
//        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            comboUnder.removeAllItems();
            comboUnder.addItem("");
            ResultSet rs = st.executeQuery("select sg_id,sg_name from tblstockgroup where sg_name not in ('Primary')");
            while (rs.next()) {
                if (rs.getString("sg_name").equals("Primary") == false) {
                    StockItemTemp s = new StockItemTemp();
                    s.setSg_id(rs.getInt("sg_id"));
                    s.setSg_name(rs.getString("sg_name"));
                    arrayListStockgroup.add(s);
                    //CmbStockItem_Under.addItem(rs.getString("sg_name"));
                    v2.add(rs.getString("sg_name"));
                }
            }


            tf = (JTextField) comboUnder.getEditor().getEditorComponent();
            comboUnder.setEditable(true);


            Collections.sort(
                    v2,
                    new Comparator<String>() {
                        public int compare(String lhs, String rhs) {
                            return lhs.compareToIgnoreCase(rhs);
                        }
                    });

            int siz = v2.size();
            for (int i = 0; i < siz; i++) {
                comboUnder.addItem(v2.get(i));
            }
            //}


            tf.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet.");
                    String text = tf.getText().trim();
                    if (text.length() == 0) {
                        comboUnder.hidePopup();
                        setModel(new DefaultComboBoxModel(v2), "");
                    } else {
                        DefaultComboBoxModel m = getSuggestedModel(v2, text);
                        if (m.getSize() == 0 || hide_flag) {
                            comboUnder.hidePopup();
                            hide_flag = false;
                        } else {
                            setModel(m, text);
                            comboUnder.showPopup();
                        }
                    }
                    tf.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet.");
                }
            });



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(StockItem_Create.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (comboFocusControl == 1) {
            tf.requestFocus();
        } else if (comboFocusControl == 0) {
            txtName.requestFocus();
        }
        comboFocusControl = 0;

    }//GEN-LAST:event_formInternalFrameActivated

    private void btnAddStockGroupKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAddStockGroupKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnAddStockGroupActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            comboUnder.requestFocus();
        }
    }//GEN-LAST:event_btnAddStockGroupKeyPressed

    private void txtRateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRateKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtBalance.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            comboUnit.requestFocus();
        }
    }//GEN-LAST:event_txtRateKeyPressed

    private void txtRateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRateKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '.') {
            evt.consume();
        }
        int flag = 0;
        String f = txtRate.getText().trim();
        int i = 0;
        while (i < f.length()) {
            if (f.charAt(i) == '.') {
                flag = 1;
                break;
            }
            i++;
        }
        if (flag == 1 && c == '.') {
            evt.consume();
        }

    }//GEN-LAST:event_txtRateKeyTyped

    private void txtBalanceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBalanceKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnUpdateActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtRate.requestFocus();
        }
    }//GEN-LAST:event_txtBalanceKeyPressed

    private void txtBalanceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBalanceKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '.') {
            evt.consume();
        }
        int flag = 0;
        String f = txtBalance.getText().trim();
        int i = 0;
        while (i < f.length()) {
            if (f.charAt(i) == '.') {
                flag = 1;
                break;
            }
            i++;
        }
        if (flag == 1 && c == '.') {
            evt.consume();
        }

    }//GEN-LAST:event_txtBalanceKeyTyped

    private void txtBalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBalanceActionPerformed
    }//GEN-LAST:event_txtBalanceActionPerformed

    private void txtRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRateActionPerformed

    private void btnUpdateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnUpdateKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtStockItemAlter_grid.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            btnUpdateActionPerformed(null);
        }
    }//GEN-LAST:event_btnUpdateKeyPressed

    private void btnStockItemAlter_BackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnStockItemAlter_BackKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnStockItemAlter_BackActionPerformed(null);
        }
    }//GEN-LAST:event_btnStockItemAlter_BackKeyPressed

    private void tableStockItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableStockItemMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tableStockItemMouseEntered

    private void formInternalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeactivated
        // TODO add your handling code here:
        combocontrol = 1;
        comboUnder.hidePopup();
    }//GEN-LAST:event_formInternalFrameDeactivated

    private void txtRateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRateFocusGained
        // TODO add your handling code here:
        if (txtRate.getText().equals("0")) {
            txtRate.setText("");
        }
    }//GEN-LAST:event_txtRateFocusGained

    private void txtRateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRateFocusLost
        // TODO add your handling code here:
        if (txtRate.getText().equals("")) {
            txtRate.setText("0");
        }
    }//GEN-LAST:event_txtRateFocusLost

    private void txtBalanceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBalanceFocusGained
        // TODO add your handling code here:
        if (txtBalance.getText().equals("0")) {
            txtBalance.setText("");
        }
    }//GEN-LAST:event_txtBalanceFocusGained

    private void txtBalanceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBalanceFocusLost
        // TODO add your handling code here:
        if (txtBalance.getText().equals("")) {
            txtBalance.setText("0");
        }
    }//GEN-LAST:event_txtBalanceFocusLost

    private void txtStockItemAlter_gridFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStockItemAlter_gridFocusGained
        // TODO add your handling code here:
        txtStockItemAlter_grid.selectAll();
    }//GEN-LAST:event_txtStockItemAlter_gridFocusGained

    private void txtNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNameFocusGained
        // TODO add your handling code here:
        txtName.selectAll();
    }//GEN-LAST:event_txtNameFocusGained

    private void txtAliasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAliasFocusGained
        // TODO add your handling code here:
        txtAlias.selectAll();
    }//GEN-LAST:event_txtAliasFocusGained

    private void comboUnderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboUnderFocusLost
        // TODO add your handling code here:
        comboUnder.hidePopup();
    }//GEN-LAST:event_comboUnderFocusLost

    private void comboUnitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboUnitFocusLost
        // TODO add your handling code here:
        comboUnit.hidePopup();
    }//GEN-LAST:event_comboUnitFocusLost

    public void gridList() {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select si_name,si_id from tblstockitem order by si_name");
            int i = 0;
            arrayListStockItem.clear();
            while (rs.next()) {
                StockItemData gr1 = new StockItemData();
                gr1.setId(rs.getInt(2));
                gr1.setName(rs.getString(1));
                arrayListStockItem.add(gr1);
            }
            i = 0;
            tableStockItem.removeAll();
            try {
                DefaultTableModel d = (DefaultTableModel) tableStockItem.getModel();

                String text = txtStockItemAlter_grid.getText().trim();
                int size = txtStockItemAlter_grid.getText().trim().length();
                d.setRowCount(0);

                for (i = 0; i < arrayListStockItem.size(); i++) {
                    String record = arrayListStockItem.get(i).getName();
                    int id = arrayListStockItem.get(i).getId();
                    if (record.length() >= size) {
                        int rows = d.getRowCount();
                        rows++;
                        d.setRowCount(rows);
                        d.setColumnCount(2);
                        tableStockItem.setValueAt(record, rows - 1, 0);
                        tableStockItem.setValueAt(id, rows - 1, 1);
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
                Logger.getLogger(StockItem_Alter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void onlyNumber(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelStockItemAlter2;
    private javax.swing.JLabel balance;
    private javax.swing.JButton btnAddStockGroup;
    private javax.swing.JButton btnStockItemAlter_Back;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox comboUnder;
    private javax.swing.JComboBox comboUnit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel rate;
    private javax.swing.JTable tableStockItem;
    private javax.swing.JTextField txtAlias;
    private javax.swing.JFormattedTextField txtBalance;
    private javax.swing.JTextField txtName;
    private javax.swing.JFormattedTextField txtRate;
    private javax.swing.JTextField txtStockItemAlter_grid;
    // End of variables declaration//GEN-END:variables
    private boolean hide_flag = false;

    private void setModel(DefaultComboBoxModel mdl, String str) {
        comboUnder.setModel(mdl);
        comboUnder.setSelectedIndex(-1);
        comboUnder.setPopupVisible(true);
        tf.setText(str);
        //  tf.selectAll();
    }

    private void setModel1(DefaultComboBoxModel mdl, String str) {
        comboUnit.setModel(mdl);
        comboUnit.setSelectedIndex(-1);
        comboUnit.setPopupVisible(true);
        tf1.setText(str);
        // tf1.selectAll();
    }

    private static DefaultComboBoxModel getSuggestedModel(java.util.List<String> list, String text) {
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        for (String s : list) {
            //if(s.startsWith(text)) m.addElement(s);
            if (s.toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
                m.addElement(s);
                System.out.println("Yes" + s);
            }
        }
        return m;
    }

    private static DefaultComboBoxModel getSuggestedModel1(java.util.List<String> list, String text) {
        DefaultComboBoxModel m1 = new DefaultComboBoxModel();
        for (String s : list) {
            //if(s.startsWith(text)) m.addElement(s);
            if (s.toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
                m1.addElement(s);
                System.out.println("Yes" + s);
            }
        }
        return m1;
    }

    private boolean checkElementPresence(Vector combo, String str) {
        for (int i = 0; i < combo.size(); i++) {
            if (tf.getText().trim().equals(comboUnder.getItemAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkElementPresence1(Vector combo1, String str1) {
        for (int i = 0; i < combo1.size(); i++) {
            if (tf1.getText().trim().equals(comboUnit.getItemAt(i))) {
                return true;
            }
        }
        return false;
    }
}
