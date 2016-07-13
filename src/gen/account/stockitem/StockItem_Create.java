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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

public class StockItem_Create extends javax.swing.JInternalFrame {

    private Object[][] RowData;
    ArrayList<StockItemTemp> arrayListFinish = new ArrayList<StockItemTemp>();
    ArrayList<StockItemTemp> arrayListStockGroup = new ArrayList<StockItemTemp>();
    ArrayList<StockItemTemp> arrayListUOM = new ArrayList<StockItemTemp>();
    DefaultTableModel tableModel1 = new DefaultTableModel();
    DefaultTableModel tableModel2 = new DefaultTableModel();
    int i = 0;
    JTextField tf, tf1;
    private static int frameopened = 0;
    static int focuscontrol = 0;
    static int addComboItem = 0;
    static int comboFocusControl = 0;
    private final Vector<String> v = new Vector<String>();
    private final Vector<String> v1 = new Vector<String>();
    private final Vector<String> v2 = new Vector<String>();
    //ArrayList<String> GroupItems=new ArrayList<String>();
    //ArrayList<String> GroupItems1=new ArrayList<String>();

    public StockItem_Create(String s) {
        setClosable(true);
        initComponents();
        this.setTitle(s);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        balance = new javax.swing.JLabel();
        rate = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtStockItem_Name = new javax.swing.JTextField();
        txtStockItem_Alias = new javax.swing.JTextField();
        CmbStockItem_Under = new javax.swing.JComboBox();
        CmbStockItem_Unit = new javax.swing.JComboBox();
        btnStockItem_Back = new javax.swing.JButton();
        btnStockItem_Create = new javax.swing.JButton();
        btnAddStockGroup = new javax.swing.JButton();
        txtStockItem_Rate = new javax.swing.JFormattedTextField();
        txtStockItem_Balance = new javax.swing.JFormattedTextField();

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

        txtStockItem_Name.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtStockItem_Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStockItem_NameKeyPressed(evt);
            }
        });

        txtStockItem_Alias.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtStockItem_Alias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStockItem_AliasKeyPressed(evt);
            }
        });

        CmbStockItem_Under.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        CmbStockItem_Under.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CmbStockItem_UnderItemStateChanged(evt);
            }
        });
        CmbStockItem_Under.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                CmbStockItem_UnderFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                CmbStockItem_UnderFocusLost(evt);
            }
        });
        CmbStockItem_Under.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbStockItem_UnderKeyPressed(evt);
            }
        });

        CmbStockItem_Unit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        CmbStockItem_Unit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                CmbStockItem_UnitFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                CmbStockItem_UnitFocusLost(evt);
            }
        });
        CmbStockItem_Unit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbStockItem_UnitKeyPressed(evt);
            }
        });

        btnStockItem_Back.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnStockItem_Back.setText("Back");
        btnStockItem_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockItem_BackActionPerformed(evt);
            }
        });
        btnStockItem_Back.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnStockItem_BackKeyPressed(evt);
            }
        });

        btnStockItem_Create.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnStockItem_Create.setText("Create");
        btnStockItem_Create.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStockItem_CreateMouseClicked(evt);
            }
        });
        btnStockItem_Create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockItem_CreateActionPerformed(evt);
            }
        });
        btnStockItem_Create.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnStockItem_CreateKeyPressed(evt);
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

        txtStockItem_Rate.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtStockItem_Rate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockItem_RateActionPerformed(evt);
            }
        });
        txtStockItem_Rate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStockItem_RateKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockItem_RateKeyTyped(evt);
            }
        });

        txtStockItem_Balance.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtStockItem_Balance.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStockItem_BalanceFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtStockItem_BalanceFocusLost(evt);
            }
        });
        txtStockItem_Balance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStockItem_BalanceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockItem_BalanceKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(rate)
                    .addComponent(balance))
                .addGap(32, 32, 32)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addComponent(btnStockItem_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(btnStockItem_Create)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtStockItem_Name, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelMainLayout.createSequentialGroup()
                                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtStockItem_Alias, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(CmbStockItem_Under, javax.swing.GroupLayout.Alignment.LEADING, 0, 179, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addComponent(btnAddStockGroup))
                                    .addComponent(CmbStockItem_Unit, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtStockItem_Rate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtStockItem_Balance, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(52, 52, 52))))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtStockItem_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtStockItem_Alias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(CmbStockItem_Under, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAddStockGroup))
                                .addGap(18, 18, 18)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(CmbStockItem_Unit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addComponent(txtStockItem_Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(rate))
                        .addGap(18, 18, 18)
                        .addComponent(txtStockItem_Balance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(balance))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStockItem_Create)
                    .addComponent(btnStockItem_Back))
                .addGap(153, 153, 153))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(163, Short.MAX_VALUE)
                .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtStockItem_NameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockItem_NameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtStockItem_Alias.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnStockItem_BackActionPerformed(null);
        }
}//GEN-LAST:event_txtStockItem_NameKeyPressed

    private void txtStockItem_AliasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockItem_AliasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            CmbStockItem_Under.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtStockItem_Name.requestFocus();
        }
}//GEN-LAST:event_txtStockItem_AliasKeyPressed

    private void CmbStockItem_UnderItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CmbStockItem_UnderItemStateChanged
}//GEN-LAST:event_CmbStockItem_UnderItemStateChanged

    private void CmbStockItem_UnderFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CmbStockItem_UnderFocusGained
        CmbStockItem_Under.showPopup();
}//GEN-LAST:event_CmbStockItem_UnderFocusGained

    private void CmbStockItem_UnderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbStockItem_UnderKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            CmbStockItem_Unit.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtStockItem_Alias.requestFocus();
        }
}//GEN-LAST:event_CmbStockItem_UnderKeyPressed

    private void CmbStockItem_UnitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CmbStockItem_UnitFocusGained
        CmbStockItem_Unit.showPopup();
}//GEN-LAST:event_CmbStockItem_UnitFocusGained

    private void CmbStockItem_UnitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbStockItem_UnitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtStockItem_Rate.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            CmbStockItem_Under.requestFocus();
        }
}//GEN-LAST:event_CmbStockItem_UnitKeyPressed

    private void btnStockItem_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockItem_BackActionPerformed
        try {
            MainClass m = new MainClass();
            m.menuselection(2);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(StockItem_Create.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnStockItem_BackActionPerformed

    private void btnStockItem_CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockItem_CreateActionPerformed
        if (txtStockItem_Name.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Enter Data in Name  Fields");
            txtStockItem_Name.requestFocus();
        } else if (CmbStockItem_Under.getSelectedItem() == null || CmbStockItem_Under.getSelectedItem() == "")// || tf.getText().trim().equalsIgnoreCase("") || ComboGroupCreate_Under.getSelectedItem().toString().trim().equals("") ) 
        {
            System.out.println("eeeeeeeeeeeeeeeee");
            JOptionPane.showMessageDialog(this, "Select Data for Under Group Fields");
            CmbStockItem_Under.requestFocus();
        } else if (CmbStockItem_Unit.getSelectedItem() == null || CmbStockItem_Unit.getSelectedItem() == "")// || tf.getText().trim().equalsIgnoreCase("") || ComboGroupCreate_Under.getSelectedItem().toString().trim().equals("") ) 
        {
            System.out.println("eeeeeeeeeeeeeeeee");
            JOptionPane.showMessageDialog(this, "Select Data for Unit Group Fields");
            CmbStockItem_Unit.requestFocus();
        } else if (!checkElementPresence(v, tf.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Selected Value For Under Field is not valid");
            //    scrollpaneMain.getVerticalScrollBar().setValue(0);  //go to top of scrollpane
            CmbStockItem_Under.requestFocus();
        } else if (!checkElementPresence1(v1, tf1.getText())) {
            JOptionPane.showMessageDialog(this, "Selected Value For Unit Field is not valid");
            //    scrollpaneMain.getVerticalScrollBar().setValue(0);  //go to top of scrollpane
            CmbStockItem_Unit.requestFocus();
        } else {
            String Name = txtStockItem_Name.getText().trim();
            String Alias = txtStockItem_Alias.getText().trim();

            long under = 0;
            i = 0;
            while (i < arrayListStockGroup.size()) {
                if (arrayListStockGroup.get(i).getSg_name().equals(CmbStockItem_Under.getSelectedItem())) {
                    under = arrayListStockGroup.get(i).getSg_id();
                }
                i++;
            }


            long uom = 0;
            i = 0;
            while (i < arrayListUOM.size()) {


                if (arrayListUOM.get(i).getUom_name().equals(CmbStockItem_Unit.getSelectedItem())) {
                    uom = arrayListUOM.get(i).getUom_id();
                    break;
                }
                i++;
            }

            double Rate = 0;
            if (txtStockItem_Rate.getText().trim().equals("")) {
                Rate = 0;
            } else {
                Rate = Double.parseDouble(txtStockItem_Rate.getText().trim());
            }
            double Balance = 0;
            if (txtStockItem_Balance.getText().trim().equals("")) {
                Balance = 0;
            } else {
                Balance = Double.parseDouble(txtStockItem_Balance.getText().trim());
            }

//            double length = 0;
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
                long temp_id = 0;
                String q = "";
                ResultSet rs;
                rs = st.executeQuery("select si_id from tblstockitem where si_name='" + txtStockItem_Name.getText().trim() + "'");
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "This name is already taken");
                    txtStockItem_Name.selectAll();
                    txtStockItem_Name.requestFocus();
                } else {
                    System.out.println("Name:" + Name);
                    System.out.println("Alias:" + Alias);
                    System.out.println("under:" + under);
                    System.out.println("UOM:" + uom);
                    System.out.println("Balance:" + Balance);
                    System.out.println("RATE:" + rate);
                    //   q = "insert into tblstockitem(si_name,si_alias,si_under,si_rate,si_unitOfMeasure,si_openingBalance) values('" + Name + "','" + Alias + "'," + under + "," + Rate + "," + uom + "," + Balance + ")";

                    //  st.executeUpdate(q);


                    PreparedStatement ps;
                    q = "insert into tblstockitem(si_name,si_alias,si_under,si_rate,si_unitOfMeasure,si_openingBalance,si_length,si_width,si_thickness) values(?,?,?,?,?,?,?,?,?)";
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

                    ps.executeUpdate();
                    //directly inserted unit of measure and not id

                    q = "select last_insert_id() as id";
                    rs1 = st1.executeQuery(q);

                    if (rs1.next()) {
                        temp_id = rs1.getInt("id");
                    } else {
                        temp_id = 1;
                    }
                    q = "insert into tblStockItemCurrentBalance(si_id,si_currentBalance,si_DebitOrCredit) values(" + temp_id + "," + Balance + ",2)";  //2 for credit and 1 for debit
                    st1.executeUpdate(q);
                    rs1.close();

                    String dateConstant = "1500-09-23";
                    q = "insert tblStockItemOPBlUpdateRecord set si_id=?,si_openingBalance=?,update_opening_balance_date=?,si_under = ?,si_name=?";
                    ps = conn.prepareStatement(q);
                    ps.setDouble(1, temp_id);
                    ps.setDouble(2, Balance);
                    ps.setString(3, dateConstant);
                    ps.setLong(4, under);
                    ps.setString(5, Name);
                    ps.executeUpdate();


                    conn.commit();
                    Constants.STOCK_ITEM_TIME_STAMP++;
                    JOptionPane.showMessageDialog(this, "Stock Item added Successfully");

                }
                txtStockItem_Alias.setText("");
                txtStockItem_Balance.setText("");
                txtStockItem_Name.setText("");
                txtStockItem_Rate.setText("");
                //CmbStockItem_Under.setSelectedIndex(0);
                // CmbStockItem_Unit.setSelectedIndex(0);
                CmbStockItem_Under.setSelectedItem("");
                CmbStockItem_Unit.setSelectedItem("");

//                txtLength.setText("0");
//                txtWidth.setText("0");
//                txtThickness.setText("0");                

                //cmbStockItem_Type.setSelectedIndex(0);
                rate.setVisible(true);
                balance.setVisible(true);
                txtStockItem_Rate.setVisible(true);
                txtStockItem_Balance.setVisible(true);
                txtStockItem_Balance.setText("0");
                txtStockItem_Name.requestFocus();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StockItem_Create.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
}//GEN-LAST:event_btnStockItem_CreateActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
//        focuscontrol=0;
        btnStockItem_Create.setMnemonic(KeyEvent.VK_C);
        btnStockItem_Back.setMnemonic(KeyEvent.VK_B);
        btnAddStockGroup.setMnemonic(KeyEvent.VK_O);
        frameopened = 1;

        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();

            CmbStockItem_Under.removeAllItems();
            CmbStockItem_Under.addItem("");
            CmbStockItem_Under.setEditable(true);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
            /*Code Snippet added by Sudeep on 12-01-2013 At:12.45PM*/
            tf = (JTextField) CmbStockItem_Under.getEditor().getEditorComponent();
            CmbStockItem_Under.setEditable(true);
            ResultSet rs = st.executeQuery("select sg_id,sg_name from tblstockgroup where sg_name not in('Primary')");
            while (rs.next()) {
                if (rs.getString("sg_name").equals("Primary") == false) {
                    StockItemTemp s = new StockItemTemp();
                    s.setSg_id(rs.getInt("sg_id"));
                    System.out.println("StockGroup Value........." + rs.getString("sg_name"));
                    s.setSg_name(rs.getString("sg_name"));



                    arrayListStockGroup.add(s);
                    CmbStockItem_Under.addItem(rs.getString("sg_name"));


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
                CmbStockItem_Under.addItem(v.get(i));
            }

            tf.addKeyListener(new KeyAdapter() {
                public void keyTyped(final KeyEvent e) {
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            String text = tf.getText();
                            if (text.length() == 0) {
                                CmbStockItem_Under.hidePopup();
                                setModel(new DefaultComboBoxModel(v), "");
                            } else {
                                DefaultComboBoxModel m = getSuggestedModel(v, text);
                                if (m.getSize() == 0 || hide_flag) {
                                    CmbStockItem_Under.hidePopup();
                                    hide_flag = false;
                                } else {
                                    setModel(m, text);
                                    CmbStockItem_Under.showPopup();
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
                                        CmbStockItem_Under.setSelectedIndex(-1);
                                        tf.setText(str);
                                        return;
                                    }
                                }
                            }

                        }
                    });

                }
            });
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////             
            tf.addKeyListener(new KeyAdapter() {
                public void keyPressed(final KeyEvent event) {
                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {


                            if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                                // CmbStockItem_Under.showPopup();
                                if (CmbStockItem_Under.getSelectedItem().equals("")) {
                                    // JOptionPane.showMessageDialog(rootPane, "Enter data for Under Field");
                                    // CmbStockItem_Under.requestFocus();
                                    tf1.requestFocus();
                                } else {
                                    tf1.requestFocus();
                                }

                                //CmbStockItem_Unit.requestFocus();
                            }
                            if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                txtStockItem_Alias.requestFocus();
                            }
                        }
                    });
                }
            });

            tf.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    {
                        EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {


                                String text = tf.getText().trim();
                                if (text.length() == 0) {
                                    CmbStockItem_Under.hidePopup();
                                    setModel(new DefaultComboBoxModel(v), "");
                                } else {
                                    DefaultComboBoxModel m = getSuggestedModel(v, text);
                                    if (m.getSize() == 0 || hide_flag) {
                                        CmbStockItem_Under.hidePopup();
                                        hide_flag = false;
                                    } else {
                                        setModel(m, text);
                                        CmbStockItem_Under.showPopup();
                                    }
                                }


//                  String text1 = tf.getText().trim();
//                  int code = e.getKeyCode();
//                  if(code==KeyEvent.VK_ENTER)
//                  {
//                    System.out.println("Enter");
//                    if(!v.contains(text)) 
//                    {
//                     v.addElement(text);
//                     Collections.sort(v);
//                     setModel(getSuggestedModel(v, text), text);
//                     }
//                     hide_flag = true; 
//                   }
//                  else if(code==KeyEvent.VK_ESCAPE) 
//                  {
//                     hide_flag = true; 
//                  }
//                  else if(code==KeyEvent.VK_RIGHT) 
//                  {
//                     for(int i=0;i<v.size();i++)
//                     {
//                       String str = v.elementAt(i);
//                       if(str.startsWith(text))
//                       {
//                         CmbStockItem_Under.setSelectedIndex(-1);
//                         tf.setText(str);
//                         return;
//                       }
//                      }
//                   }




                            }
                        });
                    }
                    tf.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    //  throw new UnsupportedOperationException("Not supported yet.");
                }
            });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            /*Code Snippet added by Sudeep on 12-01-2013 At:12.55PM*/


            tf1 = (JTextField) CmbStockItem_Unit.getEditor().getEditorComponent();
            CmbStockItem_Unit.setEditable(true);
            CmbStockItem_Unit.removeAllItems();
            CmbStockItem_Unit.addItem("");
            rs = st.executeQuery("select uom_id,uom_formalName from tblunitofmeasure");
            while (rs.next()) {
                StockItemTemp u = new StockItemTemp();
                u.setUom_id(rs.getInt("uom_id"));
                u.setUom_name(rs.getString("uom_formalName"));
                arrayListUOM.add(u);
                // CmbStockItem_Unit.addItem(rs.getString("uom_formalName"));



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
            for (int i = 0; i < size2; i++) {
                CmbStockItem_Unit.addItem(v1.get(i));
            }

            tf1.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    String text = tf1.getText().trim();
                    if (text.length() == 0) {
                        CmbStockItem_Unit.hidePopup();
                        setModel1(new DefaultComboBoxModel(v1), "");
                    } else {
                        DefaultComboBoxModel m = getSuggestedModel1(v1, text);
                        if (m.getSize() == 0 || hide_flag) {
                            CmbStockItem_Unit.hidePopup();
                            hide_flag = false;
                        } else {
                            setModel1(m, text);
                            CmbStockItem_Unit.showPopup();
                        }
                    }
                    tf1.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet.");
                }
            });

            tf1.addKeyListener(new KeyAdapter() {
                public void keyTyped(final KeyEvent e) {
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            String text = tf1.getText().trim();
                            if (text.length() == 0) {
                                CmbStockItem_Unit.hidePopup();
                                setModel1(new DefaultComboBoxModel(v1), "");
                            } else {
                                DefaultComboBoxModel m = getSuggestedModel1(v1, text);
                                if (m.getSize() == 0 || hide_flag) {
                                    CmbStockItem_Unit.hidePopup();
                                    hide_flag = false;
                                } else {
                                    setModel1(m, text);
                                    CmbStockItem_Unit.showPopup();
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
                                        CmbStockItem_Unit.setSelectedIndex(-1);
                                        tf1.setText(str);
                                        return;
                                    }
                                }
                            }

                        }
                    });

                }
            });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            tf1.addKeyListener(new KeyAdapter() {
                public void keyPressed(final KeyEvent event) {
                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {


                            if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                                if (CmbStockItem_Unit.getSelectedItem().equals("")) {
                                    //    JOptionPane.showMessageDialog(rootPane, "Enter data for Unit Field");
                                    //  CmbStockItem_Unit.requestFocus();
                                    txtStockItem_Rate.requestFocus();
                                } else {
                                    txtStockItem_Rate.requestFocus();
                                }
                                //txtStockItem_Rate.requestFocus();
                            }
                            if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                tf.requestFocus();
                            }
                        }
                    });
                }
            });

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(StockItem_Create.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        txtStockItem_Name.requestFocus();
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        focuscontrol = 0;
        addComboItem = 0;
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


        //tf.requestFocus();
//        txtLength.setText("0");
//        txtWidth.setText("0");
//        txtThickness.setText("0");
        txtStockItem_Balance.setText("0");

        if (addComboItem == 1) {
            CmbStockItem_Under.removeAllItems();
            CmbStockItem_Under.addItem("");
            v2.clear();
            Connection conn = null;
            try {
                conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();
                String Primary = "Primary";
                ResultSet rs = st.executeQuery("select sg_id,sg_name from tblstockgroup where sg_name not in('Primary')");

                while (rs.next()) {
                    if (rs.getString("sg_name").equalsIgnoreCase("Primary") == false) {
                        StockItemTemp s = new StockItemTemp();
                        s.setSg_id(rs.getInt("sg_id"));
                        s.setSg_name(rs.getString("sg_name"));
                        arrayListStockGroup.add(s);
                        // CmbStockItem_Under.addItem(rs.getString("sg_name"));
                        // CmbStockItem_Under.setEditable(true);

                    }
                    v2.add(rs.getString("sg_name"));

                }
                Collections.sort(
                        v2,
                        new Comparator<String>() {
                            public int compare(String lhs, String rhs) {
                                return lhs.compareToIgnoreCase(rhs);
                            }
                        });
            } catch (Exception e) {
            }
            CmbStockItem_Under.removeAllItems();
            CmbStockItem_Under.addItem("");
            int size1 = v2.size();
            for (int i = 0; i < size1; i++) {
                CmbStockItem_Under.addItem(v2.get(i));
            }
        }

        if (comboFocusControl == 1) {
            tf.requestFocus();
        } else if (comboFocusControl == 0) {
            txtStockItem_Name.requestFocus();
        }
        comboFocusControl = 0;
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
//        if(focuscontrol==1)
//        {
//         tf.requestFocus();
//        }
//        txtStockItem_Balance.setText("0");
//        CmbStockItem_Under.removeAllItems();
//        CmbStockItem_Under.addItem("");
//        v2.clear();
//        
//        Connection conn = null;
//        try {
//            conn = DatabaseConnection1.GetConnection();
//            Statement st = conn.createStatement();
//
//           // CmbStockItem_Under.removeAllItems();
//          //  CmbStockItem_Under.addItem("");
//            ResultSet rs = st.executeQuery("select sg_id,sg_name from tblstockgroup");
//            while (rs.next()) {
//                if (rs.getString("sg_name").equals("Primary") == false) {
//                    StockItemTemp s = new StockItemTemp();
//                    s.setSg_id(rs.getInt("sg_id"));
//                    s.setSg_name(rs.getString("sg_name"));
//                    arrayListStockGroup.add(s);
//                    //CmbStockItem_Under.addItem(rs.getString("sg_name"));
//                     v2.add(rs.getString("sg_name"));
//                   //  CmbStockItem_Under.addItem(rs.getString("sg_name"));
//                }
//            }
//
//
//            tf=(JTextField) CmbStockItem_Under.getEditor().getEditorComponent();
//            CmbStockItem_Under.setEditable(true);
//           //  CmbStockItem_Under.removeAllItems();
//           // CmbStockItem_Under.addItem("");
//           
//            Collections.sort(
//                                    v2, 
//                                    new Comparator<String>() 
//                                     {
//                                       public int compare(String lhs, String rhs) 
//                                       {
//                                         return lhs.compareToIgnoreCase(rhs);
//                                        }
//                                      }
//                                   ); 
//                
//              int siz=v2.size(); 
//              for(int i=0;i<siz;i++)
//              {
//               CmbStockItem_Under.addItem(v2.get(i));
//              }
//            
//           
//             tf.addKeyListener(new KeyAdapter()
//                    {
//                public void keyTyped(final KeyEvent e) 
//                {
//               EventQueue.invokeLater(new Runnable() 
//               {
//            public void run() 
//            {
//                String text = tf.getText().trim();
//                        if(text.length()==0) 
//                        {
//                          CmbStockItem_Under.hidePopup();
//                          setModel(new DefaultComboBoxModel(v2), "");
//                        }
//                        else
//                        {
//                          DefaultComboBoxModel m = getSuggestedModel(v2, text);
//                          if(m.getSize()==0 || hide_flag)
//                          {
//                            CmbStockItem_Under.hidePopup();
//                            hide_flag = false;
//                          }
//                          else
//                          {
//                            setModel(m, text);
//                            CmbStockItem_Under.showPopup();
//                          }
//                        }
//                        
//                        
//                  String text1 = tf.getText().trim();
//                  int code = e.getKeyCode();
//                  if(code==KeyEvent.VK_ENTER)
//                  {
//                    System.out.println("Enter");
//                    if(!v2.contains(text)) 
//                    {
//                     v2.addElement(text);
//                     Collections.sort(v2);
//                     setModel(getSuggestedModel(v2, text), text);
//                     }
//                     hide_flag = true; 
//                   }
//                  else if(code==KeyEvent.VK_ESCAPE) 
//                  {
//                     hide_flag = true; 
//                  }
//                  else if(code==KeyEvent.VK_RIGHT) 
//                  {
//                     for(int i=0;i<v2.size();i++)
//                     {
//                       String str = v2.elementAt(i);
//                       if(str.startsWith(text))
//                       {
//                         CmbStockItem_Under.setSelectedIndex(-1);
//                         tf.setText(str);
//                         return;
//                       }
//                      }
//                   }
//                        
//            }
//               
//        });
//            
//                }
//              
//      }); 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////             
//          tf.addKeyListener(new KeyAdapter()
//                    {
//                public void keyPressed(final KeyEvent event) 
//                {
//               EventQueue.invokeLater(new Runnable() 
//               {
//
//                        @Override
//                        public void run() {
//                           
//                            
//                            if(event.getKeyCode()==KeyEvent.VK_ENTER)
//                            {
//                             if(CmbStockItem_Under.getSelectedItem().equals(""))
//                               {
//                                JOptionPane.showMessageDialog(rootPane, "Enter data for Under Field");
//                                CmbStockItem_Under.requestFocus();
//                               }
//                               else
//                               {
//                               CmbStockItem_Unit.requestFocus();
//                               }
//                                
//                                //CmbStockItem_Unit.requestFocus();
//                            }
//                            if(event.getKeyCode()==KeyEvent.VK_ESCAPE)
//                            {
//                             txtStockItem_Alias.requestFocus();
//                            }
//                        }
//                  
//                   
//               });
//                       }
//                
//               });       
//            
//            
//        
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                conn.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(StockItem_Create.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }//GEN-LAST:event_formInternalFrameActivated

    private void btnAddStockGroupKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAddStockGroupKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnAddStockGroupActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            CmbStockItem_Under.requestFocus();
        }
    }//GEN-LAST:event_btnAddStockGroupKeyPressed

    private void txtStockItem_RateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockItem_RateKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtStockItem_Balance.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            CmbStockItem_Unit.requestFocus();
        }
    }//GEN-LAST:event_txtStockItem_RateKeyPressed

    private void txtStockItem_RateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockItem_RateKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '.') {
            evt.consume();
        }
        int flag = 0;
        String f = txtStockItem_Rate.getText().trim();
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

    }//GEN-LAST:event_txtStockItem_RateKeyTyped

    private void txtStockItem_BalanceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockItem_BalanceKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnStockItem_Create.requestFocus();
            // btnStockItem_CreateActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtStockItem_Rate.requestFocus();
        }
    }//GEN-LAST:event_txtStockItem_BalanceKeyPressed

    private void txtStockItem_BalanceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockItem_BalanceKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '.') {
            evt.consume();
        }
        int flag = 0;
        String f = txtStockItem_Balance.getText().trim();
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

    }//GEN-LAST:event_txtStockItem_BalanceKeyTyped

    private void txtStockItem_RateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockItem_RateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockItem_RateActionPerformed

    private void txtStockItem_BalanceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStockItem_BalanceFocusLost
        if (txtStockItem_Balance.getText().trim().equals("")) {
            txtStockItem_Balance.setText("0");
        }
    }//GEN-LAST:event_txtStockItem_BalanceFocusLost

    private void txtStockItem_BalanceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStockItem_BalanceFocusGained
        if (txtStockItem_Balance.getText().trim().equals("0")) {
            txtStockItem_Balance.setText("");
        }
    }//GEN-LAST:event_txtStockItem_BalanceFocusGained

    private void btnStockItem_CreateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnStockItem_CreateKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnStockItem_CreateActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtStockItem_Balance.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnStockItem_Back.requestFocus();
        }
    }//GEN-LAST:event_btnStockItem_CreateKeyPressed

    private void btnStockItem_BackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnStockItem_BackKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnStockItem_BackActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtStockItem_Balance.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnStockItem_Create.requestFocus();
        }
    }//GEN-LAST:event_btnStockItem_BackKeyPressed

    private void btnStockItem_CreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStockItem_CreateMouseClicked
        // TODO add your handling code here:
        btnStockItem_CreateActionPerformed(null);
    }//GEN-LAST:event_btnStockItem_CreateMouseClicked

    private void formInternalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeactivated
        // TODO add your handling code here:
        focuscontrol = 1;
        addComboItem = 1;
        CmbStockItem_Under.hidePopup();

    }//GEN-LAST:event_formInternalFrameDeactivated

    private void txtStockItem_NameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStockItem_NameFocusGained
        // TODO add your handling code here:
        txtStockItem_Name.selectAll();
    }//GEN-LAST:event_txtStockItem_NameFocusGained

    private void txtStockItem_AliasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStockItem_AliasFocusGained
        // TODO add your handling code here:
        txtStockItem_Alias.selectAll();
    }//GEN-LAST:event_txtStockItem_AliasFocusGained

    private void CmbStockItem_UnderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CmbStockItem_UnderFocusLost
        // TODO add your handling code here:
        CmbStockItem_Under.hidePopup();
    }//GEN-LAST:event_CmbStockItem_UnderFocusLost

    private void CmbStockItem_UnitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CmbStockItem_UnitFocusLost
        // TODO add your handling code here:
        CmbStockItem_Unit.hidePopup();
    }//GEN-LAST:event_CmbStockItem_UnitFocusLost
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CmbStockItem_Under;
    private javax.swing.JComboBox CmbStockItem_Unit;
    private javax.swing.JLabel balance;
    private javax.swing.JButton btnAddStockGroup;
    private javax.swing.JButton btnStockItem_Back;
    private javax.swing.JButton btnStockItem_Create;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel panelMain;
    private javax.swing.JLabel rate;
    private javax.swing.JTextField txtStockItem_Alias;
    private javax.swing.JFormattedTextField txtStockItem_Balance;
    private javax.swing.JTextField txtStockItem_Name;
    private javax.swing.JFormattedTextField txtStockItem_Rate;
    // End of variables declaration//GEN-END:variables

    public void onlyNumber(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }
    private boolean hide_flag = false;

    private void setModel(DefaultComboBoxModel mdl, String str) {
        CmbStockItem_Under.setModel(mdl);
        CmbStockItem_Under.setSelectedIndex(-1);
        CmbStockItem_Under.setPopupVisible(true);
        tf.setText(str);
        // tf.selectAll();
    }

    private void setModel1(DefaultComboBoxModel mdl, String str) {
        CmbStockItem_Unit.setModel(mdl);
        CmbStockItem_Unit.setSelectedIndex(-1);
        CmbStockItem_Unit.setPopupVisible(true);
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
            if (tf.getText().trim().equals(CmbStockItem_Under.getItemAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkElementPresence1(Vector combo1, String str) {
        for (int i = 0; i < combo1.size(); i++) {
            if (tf1.getText().trim().equals(CmbStockItem_Unit.getItemAt(i))) {
                return true;
            }
        }
        return false;
    }
}
