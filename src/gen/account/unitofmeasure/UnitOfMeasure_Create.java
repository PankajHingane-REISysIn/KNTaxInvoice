package gen.account.unitofmeasure;

import gen.database.connection.DatabaseConnection1;
import gen.mainclass.MainClass;
import java.awt.EventQueue;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.Connection;
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

public class UnitOfMeasure_Create extends javax.swing.JInternalFrame {
    JTextField tf;
    private final Vector<String> v = new Vector<String>();
  //  ArrayList<String> GroupItems=new ArrayList<String>();
  
    public UnitOfMeasure_Create(String s) 
    {
        setClosable(true);
        initComponents();
        this.setTitle(s);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUnitOfMeasureCreate_Symbol = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtUnitOfMeasureCreate_FName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbUnitOfMeasure_Type = new javax.swing.JComboBox();
        btnUnitOfMeasureCreate_Back = new javax.swing.JButton();
        btnUnitOfMeasureCreate_Create = new javax.swing.JButton();
        txtUnitOfMeasureCreate_DecPlace = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png"))); // NOI18N
        setRequestFocusEnabled(false);
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
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

        jPanel1.setFocusCycleRoot(true);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Symbol");

        txtUnitOfMeasureCreate_Symbol.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtUnitOfMeasureCreate_Symbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnitOfMeasureCreate_SymbolActionPerformed(evt);
            }
        });
        txtUnitOfMeasureCreate_Symbol.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUnitOfMeasureCreate_SymbolFocusGained(evt);
            }
        });
        txtUnitOfMeasureCreate_Symbol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUnitOfMeasureCreate_SymbolKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUnitOfMeasureCreate_SymbolKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Formal Name");

        txtUnitOfMeasureCreate_FName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtUnitOfMeasureCreate_FName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnitOfMeasureCreate_FNameActionPerformed(evt);
            }
        });
        txtUnitOfMeasureCreate_FName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUnitOfMeasureCreate_FNameFocusGained(evt);
            }
        });
        txtUnitOfMeasureCreate_FName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUnitOfMeasureCreate_FNameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUnitOfMeasureCreate_FNameKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Number Of Decimal Places");

        cmbUnitOfMeasure_Type.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cmbUnitOfMeasure_Type.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbUnitOfMeasure_Type.setVerifyInputWhenFocusTarget(false);
        cmbUnitOfMeasure_Type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUnitOfMeasure_TypeActionPerformed(evt);
            }
        });
        cmbUnitOfMeasure_Type.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbUnitOfMeasure_TypeFocusLost(evt);
            }
        });
        cmbUnitOfMeasure_Type.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbUnitOfMeasure_TypeKeyPressed(evt);
            }
        });

        btnUnitOfMeasureCreate_Back.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnUnitOfMeasureCreate_Back.setText("Back");
        btnUnitOfMeasureCreate_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnitOfMeasureCreate_BackActionPerformed(evt);
            }
        });
        btnUnitOfMeasureCreate_Back.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnUnitOfMeasureCreate_BackKeyPressed(evt);
            }
        });

        btnUnitOfMeasureCreate_Create.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnUnitOfMeasureCreate_Create.setText("Create");
        btnUnitOfMeasureCreate_Create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnitOfMeasureCreate_CreateActionPerformed(evt);
            }
        });
        btnUnitOfMeasureCreate_Create.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnUnitOfMeasureCreate_CreateKeyPressed(evt);
            }
        });

        txtUnitOfMeasureCreate_DecPlace.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtUnitOfMeasureCreate_DecPlace.setFocusTraversalPolicyProvider(true);
        txtUnitOfMeasureCreate_DecPlace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnitOfMeasureCreate_DecPlaceActionPerformed(evt);
            }
        });
        txtUnitOfMeasureCreate_DecPlace.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUnitOfMeasureCreate_DecPlaceFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUnitOfMeasureCreate_DecPlaceFocusLost(evt);
            }
        });
        txtUnitOfMeasureCreate_DecPlace.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUnitOfMeasureCreate_DecPlaceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUnitOfMeasureCreate_DecPlaceKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Type");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(txtUnitOfMeasureCreate_DecPlace, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(103, 103, 103)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbUnitOfMeasure_Type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUnitOfMeasureCreate_Symbol)
                            .addComponent(txtUnitOfMeasureCreate_FName)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnUnitOfMeasureCreate_Back)
                        .addGap(54, 54, 54)
                        .addComponent(btnUnitOfMeasureCreate_Create)
                        .addGap(105, 105, 105)))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbUnitOfMeasure_Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUnitOfMeasureCreate_FName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtUnitOfMeasureCreate_Symbol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUnitOfMeasureCreate_DecPlace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUnitOfMeasureCreate_Back)
                    .addComponent(btnUnitOfMeasureCreate_Create))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUnitOfMeasureCreate_SymbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureCreate_SymbolActionPerformed
        txtUnitOfMeasureCreate_Symbol.transferFocus();
}//GEN-LAST:event_txtUnitOfMeasureCreate_SymbolActionPerformed

    private void txtUnitOfMeasureCreate_SymbolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureCreate_SymbolKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) 
        {
            txtUnitOfMeasureCreate_Symbol.transferFocusBackward();
        }        
}//GEN-LAST:event_txtUnitOfMeasureCreate_SymbolKeyPressed

    private void txtUnitOfMeasureCreate_SymbolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureCreate_SymbolKeyTyped
        
    }//GEN-LAST:event_txtUnitOfMeasureCreate_SymbolKeyTyped

    private void txtUnitOfMeasureCreate_FNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureCreate_FNameActionPerformed
        
        txtUnitOfMeasureCreate_FName.transferFocus();
}//GEN-LAST:event_txtUnitOfMeasureCreate_FNameActionPerformed

    private void txtUnitOfMeasureCreate_FNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureCreate_FNameKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) 
        {
            txtUnitOfMeasureCreate_FName.transferFocusBackward();
        }        
    }//GEN-LAST:event_txtUnitOfMeasureCreate_FNameKeyPressed

    private void txtUnitOfMeasureCreate_FNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureCreate_FNameKeyTyped
        
    }//GEN-LAST:event_txtUnitOfMeasureCreate_FNameKeyTyped

    private void cmbUnitOfMeasure_TypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUnitOfMeasure_TypeActionPerformed
        
    }//GEN-LAST:event_cmbUnitOfMeasure_TypeActionPerformed

    private void cmbUnitOfMeasure_TypeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbUnitOfMeasure_TypeKeyPressed
      /*  if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) 
        {
            btnUnitOfMeasureCreate_BackActionPerformed(null);
        }
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) 
        {
            cmbUnitOfMeasure_Type.transferFocus();
        }*/
    }//GEN-LAST:event_cmbUnitOfMeasure_TypeKeyPressed

    private void btnUnitOfMeasureCreate_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnitOfMeasureCreate_BackActionPerformed
        try {
            MainClass m=new MainClass();
            m.menuselection(2);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(UnitOfMeasure_Create.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnUnitOfMeasureCreate_BackActionPerformed

    private void btnUnitOfMeasureCreate_BackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnUnitOfMeasureCreate_BackKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER) 
        {
         btnUnitOfMeasureCreate_BackActionPerformed(null);
        }
        else if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) 
        {
            txtUnitOfMeasureCreate_DecPlace.transferFocus();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_RIGHT)
        {
          btnUnitOfMeasureCreate_Create.requestFocus();
        }
            
        
}//GEN-LAST:event_btnUnitOfMeasureCreate_BackKeyPressed

    private void btnUnitOfMeasureCreate_CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnitOfMeasureCreate_CreateActionPerformed
        // TODO add your handling code here:
        if(txtUnitOfMeasureCreate_DecPlace.getText().trim().equals(""))
        {
        txtUnitOfMeasureCreate_DecPlace.setText("0");
        }
         if (cmbUnitOfMeasure_Type.getSelectedItem()==null || cmbUnitOfMeasure_Type.getSelectedItem()=="")// || tf.getText().trim().trim().equalsIgnoreCase("") || ComboGroupCreate_Under.getSelectedItem().toString().trim().equals("") ) 
        {
            System.out.println("eeeeeeeeeeeeeeeee");
            JOptionPane.showMessageDialog(this, "Select Data for Type Fields");
            cmbUnitOfMeasure_Type.requestFocus();
        } 
        else if(txtUnitOfMeasureCreate_Symbol.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this,"Enter Data in Symbol Fields");
            txtUnitOfMeasureCreate_Symbol.requestFocus();
        } else if(txtUnitOfMeasureCreate_FName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this,"Enter Data in Formal Name Fields");
            txtUnitOfMeasureCreate_FName.requestFocus();
        }
         else if(!checkElementPresence(v, tf.getText().trim() )){
            JOptionPane.showMessageDialog(this, "Type Field Value is not valid");
            //scrollpaneMain.getVerticalScrollBar().setValue(0);  //go to top of scrollpane
            cmbUnitOfMeasure_Type.requestFocus();
        }
        /*else if(txtUnitOfMeasureCreate_DecPlace.getText().trim().trim().equals(""))
        {
            JOptionPane.showMessageDialog(this,"Enter Data in Decimal Place Fields");
            txtUnitOfMeasureCreate_DecPlace.requestFocus();
        }*/
        else {
            String type = (String) cmbUnitOfMeasure_Type.getSelectedItem();
            String for_name = txtUnitOfMeasureCreate_FName.getText().trim();
            String symbol = txtUnitOfMeasureCreate_Symbol.getText().trim();
            int dec_place = Integer.parseInt(txtUnitOfMeasureCreate_DecPlace.getText().trim());
             Connection conn=null;
            try{
                conn=DatabaseConnection1.GetConnection();
                conn.setAutoCommit(false);
                Statement st=conn.createStatement();
                
                ResultSet rs = st.executeQuery("select uomType_id from tblunitofmeasure where uom_formalName='" + txtUnitOfMeasureCreate_FName.getText().trim() + "'");
                  
                    if (rs.next()) 
                    {
                        JOptionPane.showMessageDialog(null, "This name is already taken");
                        txtUnitOfMeasureCreate_FName.selectAll();
                        txtUnitOfMeasureCreate_FName.requestFocus();
                    }
                    else
                    {   
                
                rs=st.executeQuery("select uomType_id from tbluomtype where uomtype_name='"+type+"'");
                if(rs.next()) {
                    st.executeUpdate("insert into tblunitofmeasure(uomtype_id,uom_type,uom_symbol,uom_formalName,uom_noOfDecimalPts) values('"+rs.getInt("uomType_id")+"','"+type+"','"+symbol+"','"+for_name+"',"+dec_place+")");
                    conn.commit();
                     JOptionPane.showMessageDialog(this,"Record Created Successfully");
                }
                    }
                cmbUnitOfMeasure_Type.setSelectedItem("");
                //cmbUnitOfMeasure_Type.setSelectedIndex(0);
                txtUnitOfMeasureCreate_FName.setText("");
                txtUnitOfMeasureCreate_Symbol.setText("");
                txtUnitOfMeasureCreate_DecPlace.setText("");
                cmbUnitOfMeasure_Type.requestFocus();
            }catch(Exception e) {
                e.printStackTrace();
            }finally{
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UnitOfMeasure_Create.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    }//GEN-LAST:event_btnUnitOfMeasureCreate_CreateActionPerformed

    private void btnUnitOfMeasureCreate_CreateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnUnitOfMeasureCreate_CreateKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            if(cmbUnitOfMeasure_Type.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(this,"Enter Data in Type Fields");
                cmbUnitOfMeasure_Type.requestFocus();
            } else if(txtUnitOfMeasureCreate_Symbol.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this,"Enter Data in Symbol Fields");
                txtUnitOfMeasureCreate_Symbol.requestFocus();
            } /*else if(txtUnitOfMeasureCreate_FName.getText().trim().trim().equals("")) {
                JOptionPane.showMessageDialog(this,"Enter Data in Formal Name Fields");
                txtUnitOfMeasureCreate_FName.requestFocus();
            } *//*else if(txtUnitOfMeasureCreate_DecPlace.getText().trim().trim().equals("")) {
                JOptionPane.showMessageDialog(this,"Enter Data in Decimal Place Fields");
                txtUnitOfMeasureCreate_DecPlace.requestFocus();
            }*/
            
            else {
                String type = (String) cmbUnitOfMeasure_Type.getSelectedItem();
                String for_name = txtUnitOfMeasureCreate_FName.getText().trim();
                String symbol = txtUnitOfMeasureCreate_Symbol.getText().trim();
                int dec_place = Integer.parseInt(txtUnitOfMeasureCreate_DecPlace.getText().trim());
                 Connection conn=null;
                try{
                    conn=DatabaseConnection1.GetConnection();
                    Statement st=conn.createStatement();
                    ResultSet rs=st.executeQuery("select uomType_id from tbluomtype where uomtype_name='"+type+"'");
                    if(rs.next()) {
                        st.executeUpdate("insert into tblunitofmeasure(uomtype_id,uom_type,uom_symbol,uom_formalName,uom_noOfDecimalPts) values('"+rs.getInt("uomType_id")+"','"+type+"','"+for_name+"','"+symbol+"',"+dec_place+")");                        
                    }
                    JOptionPane.showMessageDialog(this,"Record Created Successfully");
                    cmbUnitOfMeasure_Type.setSelectedIndex(0);
                    txtUnitOfMeasureCreate_FName.setText("");
                    txtUnitOfMeasureCreate_Symbol.setText("");
                    txtUnitOfMeasureCreate_DecPlace.setText("");
                    //cmbUnitOfMeasure_Type.requestFocus();
                    
                }catch(Exception e) {
                    e.printStackTrace();
                }finally{
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(UnitOfMeasure_Create.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
            
        } else
            if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) {
                //btnUnitOfMeasureCreate_Create.transferFocusBackward();
                txtUnitOfMeasureCreate_DecPlace.requestFocus();
            }
            else if(evt.getKeyCode()==KeyEvent.VK_LEFT)
            {
             btnUnitOfMeasureCreate_Back.requestFocus();
            }
                
}//GEN-LAST:event_btnUnitOfMeasureCreate_CreateKeyPressed

    private void txtUnitOfMeasureCreate_DecPlaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureCreate_DecPlaceActionPerformed
        // TODO add your handling code here:
        txtUnitOfMeasureCreate_DecPlace.transferFocus();
}//GEN-LAST:event_txtUnitOfMeasureCreate_DecPlaceActionPerformed

    private void txtUnitOfMeasureCreate_DecPlaceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureCreate_DecPlaceKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) {
            txtUnitOfMeasureCreate_DecPlace.transferFocusBackward();
        }
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) 
        {
            btnUnitOfMeasureCreate_Create.requestFocus();
            btnUnitOfMeasureCreate_CreateActionPerformed(null);
        }
}//GEN-LAST:event_txtUnitOfMeasureCreate_DecPlaceKeyPressed

    private void txtUnitOfMeasureCreate_DecPlaceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureCreate_DecPlaceKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!Character.isDigit(c)) {
            evt.consume();
        }
}//GEN-LAST:event_txtUnitOfMeasureCreate_DecPlaceKeyTyped

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        txtUnitOfMeasureCreate_DecPlace.setText("0");
        //cmbUnitOfMeasure_Type.requestFocus();
        cmbUnitOfMeasure_Type.requestFocus();
       // tf.requestFocus();
        txtUnitOfMeasureCreate_Symbol.requestFocus();
        btnUnitOfMeasureCreate_Create.setMnemonic(KeyEvent.VK_C);
        btnUnitOfMeasureCreate_Back.setMnemonic(KeyEvent.VK_B);
        Connection conn=null;
       try{
                conn=DatabaseConnection1.GetConnection();
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("select * from tbluomtype");  
                cmbUnitOfMeasure_Type.removeAllItems();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                
                     /*Code Snippet added by Sudeep on 12-01-2013 At:01.30PM*/
                
                
                
                   cmbUnitOfMeasure_Type.setEditable(true);
                   tf=(JTextField) cmbUnitOfMeasure_Type.getEditor().getEditorComponent();
                    
                    
                    while(rs.next()) 
                    {
                        v.add(rs.getString("uomtype_name"));
                        
                    }
                    
                    
                    Collections.sort(
                                    v, 
                                    new Comparator<String>() 
                                     {
                                       public int compare(String lhs, String rhs) 
                                       {
                                         return lhs.compareToIgnoreCase(rhs);
                                        }
                                      }
                                   ); 
                
                
                //cmbGroupAlter_Under.addItem(GroupItems);
               
              for(int i=0;i<v.size();i++)
              {
               cmbUnitOfMeasure_Type.addItem(v.get(i));
              }
                            
             tf.addKeyListener(new KeyAdapter()
                    {
                public void keyTyped(final KeyEvent e) 
                {
               EventQueue.invokeLater(new Runnable() 
               {
            public void run() 
            {
                String text = tf.getText();
                        if(text.length()==0) 
                        {
                          cmbUnitOfMeasure_Type.hidePopup();
                          setModel(new DefaultComboBoxModel(v), "");
                        }
                        else
                        {
                          DefaultComboBoxModel m = getSuggestedModel(v, text);
                          if(m.getSize()==0 || hide_flag)
                          {
                            cmbUnitOfMeasure_Type.hidePopup();
                            hide_flag = false;
                          }
                          else
                          {
                            setModel(m, text);
                            cmbUnitOfMeasure_Type.showPopup();
                          }
                        }
                        
                        
                  String text1 = tf.getText().trim();
                  int code = e.getKeyCode();
                  if(code==KeyEvent.VK_ENTER)
                  {
                    System.out.println("Enter");
                    if(!v.contains(text)) 
                    {
                     v.addElement(text);
                     Collections.sort(v);
                     setModel(getSuggestedModel(v, text), text);
                     }
                     hide_flag = true; 
                   }
                  else if(code==KeyEvent.VK_ESCAPE) 
                  {
                     hide_flag = true; 
                  }
                  else if(code==KeyEvent.VK_RIGHT) 
                  {
                     for(int i=0;i<v.size();i++)
                     {
                       String str = v.elementAt(i);
                       if(str.startsWith(text))
                       {
                         cmbUnitOfMeasure_Type.setSelectedIndex(-1);
                         tf.setText(str);
                         return;
                       }
                      }
                   }
                        
            }
               
        });
            
                }
              
      }); 
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
            tf.addKeyListener(new KeyAdapter()
                    {
                public void keyPressed(final KeyEvent event) 
                {
               EventQueue.invokeLater(new Runnable() 
               {

                        @Override
                        public void run() {
                           
                            
                            if(event.getKeyCode()==KeyEvent.VK_ENTER)
                            {
                             
                                if(cmbUnitOfMeasure_Type.getSelectedItem().equals(""))
                               {
                               // JOptionPane.showMessageDialog(rootPane, "Enter data for Type Field");
                                //cmbUnitOfMeasure_Type.requestFocus();
                                   txtUnitOfMeasureCreate_Symbol.requestFocus();
                               }
                               else
                               {
                               txtUnitOfMeasureCreate_Symbol.requestFocus();
                               }
                                //txtUnitOfMeasureCreate_Symbol.requestFocus();
                            }
                            if(event.getKeyCode()==KeyEvent.VK_ESCAPE)
                            {
                             btnUnitOfMeasureCreate_BackActionPerformed(null);
                            }
                            
                        }

                       
                  
                   
               });
                       }
                
                
               });
            
            
            tf.addFocusListener(new FocusListener() {

               @Override
               public void focusGained(FocusEvent e) {
                  // throw new UnsupportedOperationException("Not supported yet.");
                     String text = tf.getText().trim();
                        if(text.length()==0) 
                        {
                          cmbUnitOfMeasure_Type.hidePopup();
                          setModel(new DefaultComboBoxModel(v), "");
                        }
                        else
                        {
                          DefaultComboBoxModel m = getSuggestedModel(v, text);
                          if(m.getSize()==0 || hide_flag)
                          {
                            cmbUnitOfMeasure_Type.hidePopup();
                            hide_flag = false;
                          }
                          else
                          {
                            setModel(m, text);
                            cmbUnitOfMeasure_Type.showPopup();
                          }
                        }
                        tf.selectAll();
               }

               @Override
               public void focusLost(FocusEvent e) {
                 //  throw new UnsupportedOperationException("Not supported yet.");
               }
           });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UnitOfMeasure_Create.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void txtUnitOfMeasureCreate_DecPlaceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureCreate_DecPlaceFocusLost
         if (txtUnitOfMeasureCreate_DecPlace.getText().trim().equals("")) {
            txtUnitOfMeasureCreate_DecPlace.setText("0");
        }
    }//GEN-LAST:event_txtUnitOfMeasureCreate_DecPlaceFocusLost

    private void txtUnitOfMeasureCreate_DecPlaceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureCreate_DecPlaceFocusGained
       if (txtUnitOfMeasureCreate_DecPlace.getText().trim().equals("0")) {
            txtUnitOfMeasureCreate_DecPlace.setText("");
        }
    }//GEN-LAST:event_txtUnitOfMeasureCreate_DecPlaceFocusGained

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
      cmbUnitOfMeasure_Type.requestFocus();
    }//GEN-LAST:event_formInternalFrameActivated

    private void txtUnitOfMeasureCreate_SymbolFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureCreate_SymbolFocusGained
        // TODO add your handling code here:
        txtUnitOfMeasureCreate_Symbol.selectAll();
    }//GEN-LAST:event_txtUnitOfMeasureCreate_SymbolFocusGained

    private void txtUnitOfMeasureCreate_FNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureCreate_FNameFocusGained
        // TODO add your handling code here:
        txtUnitOfMeasureCreate_FName.selectAll();
    }//GEN-LAST:event_txtUnitOfMeasureCreate_FNameFocusGained

    private void cmbUnitOfMeasure_TypeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbUnitOfMeasure_TypeFocusLost
        // TODO add your handling code here:
        cmbUnitOfMeasure_Type.hidePopup();
    }//GEN-LAST:event_cmbUnitOfMeasure_TypeFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUnitOfMeasureCreate_Back;
    private javax.swing.JButton btnUnitOfMeasureCreate_Create;
    private javax.swing.JComboBox cmbUnitOfMeasure_Type;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtUnitOfMeasureCreate_DecPlace;
    private javax.swing.JTextField txtUnitOfMeasureCreate_FName;
    private javax.swing.JTextField txtUnitOfMeasureCreate_Symbol;
    // End of variables declaration//GEN-END:variables
 private boolean hide_flag = false;
       private void setModel(DefaultComboBoxModel mdl, String str) {
        cmbUnitOfMeasure_Type.setModel(mdl);
        cmbUnitOfMeasure_Type.setSelectedIndex(0);
//        cmbUnitOfMeasure_Type.setPopupVisible(true);
        cmbUnitOfMeasure_Type.showPopup();
        tf.setText(str);
     //   tf.selectAll();
    }
    
    
private static DefaultComboBoxModel getSuggestedModel(java.util.List<String> list, String text) {
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        for(String s: list) {
            //if(s.startsWith(text)) m.addElement(s);
            if(s.toLowerCase().matches("(.*)"+text.toLowerCase()+"(.*)")) {
                m.addElement(s);
                System.out.println("Yes"+s);
            }
        }
        return m;
    }


     private boolean checkElementPresence(Vector combo,String str) 
         {
          for(int i=0;i<combo.size();i++)
          {
           if(tf.getText().trim().equals(cmbUnitOfMeasure_Type.getItemAt(i)))
           {
             return true;
           }
          }
       return false;
         }


}
