package gen.account.stockgroup;

import gen.account.group.Group_Alter;
import gen.account.ledger.LedgerDetails;
import gen.database.connection.DatabaseConnection1;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
public class StockGroupAlterFromDetails extends javax.swing.JInternalFrame {
   
    ArrayList<stockgroupdata> grp=new ArrayList<stockgroupdata>();
    ArrayList<String> MainGroups=new ArrayList<String>();
    JTextField tf;
    private final Vector<String> v = new Vector<String>();
    //ArrayList<String> GroupItems=new ArrayList<String>();
     static long s_id=0;
    public StockGroupAlterFromDetails(long id,String s) 
    {
        setClosable(true);
        initComponents();
        s_id=id;
        this.setTitle(s);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Stock_Group_Panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboUnder = new javax.swing.JComboBox();
        txtAlias = new javax.swing.JTextField();
        btnStockGroupBack = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(679, 519));
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

        jLabel1.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
        jLabel1.setText("Name");

        txtName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNameFocusGained(evt);
            }
        });
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNameKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
        jLabel2.setText("Alias");

        jLabel3.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
        jLabel3.setText("Under");

        comboUnder.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        comboUnder.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                comboUnderFocusLost(evt);
            }
        });
        comboUnder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboUnderKeyPressed(evt);
            }
        });

        txtAlias.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtAlias.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAliasFocusGained(evt);
            }
        });
        txtAlias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAliasKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAliasKeyTyped(evt);
            }
        });

        btnStockGroupBack.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnStockGroupBack.setText("Back");
        btnStockGroupBack.setFocusCycleRoot(true);
        btnStockGroupBack.setFocusable(false);
        btnStockGroupBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockGroupBackActionPerformed(evt);
            }
        });
        btnStockGroupBack.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnStockGroupBackKeyPressed(evt);
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

        javax.swing.GroupLayout Stock_Group_PanelLayout = new javax.swing.GroupLayout(Stock_Group_Panel);
        Stock_Group_Panel.setLayout(Stock_Group_PanelLayout);
        Stock_Group_PanelLayout.setHorizontalGroup(
            Stock_Group_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Stock_Group_PanelLayout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addGroup(Stock_Group_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Stock_Group_PanelLayout.createSequentialGroup()
                        .addComponent(btnStockGroupBack, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btnUpdate)
                        .addGap(176, 176, 176))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Stock_Group_PanelLayout.createSequentialGroup()
                        .addGroup(Stock_Group_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Stock_Group_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1))
                            .addGroup(Stock_Group_PanelLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel3)))
                        .addGap(51, 51, 51)
                        .addGroup(Stock_Group_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboUnder, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(121, 121, 121))))
        );
        Stock_Group_PanelLayout.setVerticalGroup(
            Stock_Group_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Stock_Group_PanelLayout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addGroup(Stock_Group_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(Stock_Group_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAlias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(38, 38, 38)
                .addGroup(Stock_Group_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboUnder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(62, 62, 62)
                .addGroup(Stock_Group_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnStockGroupBack))
                .addGap(91, 91, 91))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(Stock_Group_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Stock_Group_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtAlias.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnStockGroupBackActionPerformed(null);
        }
}//GEN-LAST:event_txtNameKeyPressed

    private void comboUnderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboUnderKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) {
            txtAlias.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnUpdateActionPerformed(null);
        }
}//GEN-LAST:event_comboUnderKeyPressed

    private void txtAliasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAliasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            comboUnder.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtName.requestFocus();
        }
}//GEN-LAST:event_txtAliasKeyPressed

    private void btnStockGroupBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockGroupBackActionPerformed
        StockGroupDetails g = new StockGroupDetails("Stock Group Details");
            // this.getParent().add(g);      
           
            this.getParent().add(g);
            g.setVisible(true);
            Dimension desktopSize = this.getParent().getSize();
            Dimension jInternalFrameSize = g.getSize();
            g.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
             
//        g.setSize(desktopSize);
//        g.setPreferredSize(desktopSize);
                 g.setPreferredSize(desktopSize);
      try {
                  g.setSelected(true);
                  this.setSelected(true);
                  this.setClosed(true);
                } catch (java.beans.PropertyVetoException e) {
            }
}//GEN-LAST:event_btnStockGroupBackActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
       if (txtName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter Data in Name Fields");
            txtName.requestFocus();
        } else if (txtName.getText().trim().equalsIgnoreCase(tf.getText())) {
            JOptionPane.showMessageDialog(null, "Name and Under field can not be same");
            tf.requestFocus();
        } else if (comboUnder.getSelectedItem()==null || comboUnder.getSelectedItem()=="")// || tf.getText().trim().equalsIgnoreCase("") || ComboGroupCreate_Under.getSelectedItem().toString().trim().equals("") ) 
        {
            System.out.println("eeeeeeeeeeeeeeeee");
            JOptionPane.showMessageDialog(this, "Select Data for Under Group Fields");
            //comboUnder.requestFocus();
            tf.requestFocus();
        }   /////////// Atul Code //////////////////////////////////////////////////////////////////////////////////////////      
         else if(!checkElementPresence(v, tf.getText() )){
            JOptionPane.showMessageDialog(this, "Selected Value For Under Field is not valid");
        //    scrollpaneMain.getVerticalScrollBar().setValue(0);  //go to top of scrollpane
            comboUnder.requestFocus();
        }
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////       
        else
        {
            System.out.println("StockGroupAlterFromDetails--------------->>btnUpdateActionPerformed");
            int i = 0;
            String nm = "";
            while (i < grp.size())
            {
                if (grp.get(i).getId() == s_id)
                {
                    nm = grp.get(i).getName();
                    break;
                }
                i++;
            }

            i = 0;
            int flag = 0;
            while (i < MainGroups.size())
            {
                if (nm.equalsIgnoreCase(MainGroups.get(i)))
                {
                    flag = 1;
                    break;
                }
                i++;
            }
 System.out.println("StockGroupAlterFromDetails--------------->>btnUpdateActionPerformed1");
            if (flag == 1)
            {
                String alias = txtAlias.getText().trim();
                String under = (String) comboUnder.getSelectedItem();
                int flg = checkChildDirectory(under);
                if (flg == 0)
                {
                    try
                    {
                        Connection conn = DatabaseConnection1.GetConnection();
                        conn.setAutoCommit(false);
                        Statement st = conn.createStatement();
 System.out.println("StockGroupAlterFromDetails--------------->>btnUpdateActionPerformed2");
                        ResultSet rs = st.executeQuery("select sg_id from tblstockgroup where sg_name='" + under + "'");
                        if (rs.next())
                        {
                           // String sql = "update tblstockgroup set sg_alias='" + alias + "',sg_under=" + rs.getInt("sg_id") + " where sg_id=" + s_id + "";
                           // st.executeUpdate(sql);
                          
                     PreparedStatement ps;
                    String sql="update tblstockgroup set sg_alias=?,sg_under=? where sg_id=?";
                  
                    ps=conn.prepareStatement(sql);
                    ps.setString(1, alias);
                    ps.setInt(2, rs.getInt("sg_id"));
                    ps.setLong(3, s_id);
                    
                    ps.executeUpdate();
                            
                            
                            conn.commit();
                            JOptionPane.showMessageDialog(null, "Fields Updated Successfully except group name as it is predefined group");
                        }
                        
                        txtName.setText("");
                        txtAlias.setText("");
                        comboUnder.setSelectedIndex(0);
                        btnStockGroupBackActionPerformed(evt);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "You have selected Parent group which in child directory of current group. Please change it.");
                    comboUnder.requestFocus();
                }
            }
            else
            {
                 System.out.println("StockGroupAlterFromDetails--------------->>btnUpdateActionPerformed3");
                String name = txtName.getText().trim();
                String alias = txtAlias.getText().trim();
                String under = (String) tf.getText();
                  System.out.println("StockGroupAlterFromDetails--------------->>btnUpdateActionPerformed31");
               
                int flg = checkChildDirectory(under);
               System.out.println("StockGroupAlterFromDetails--------------->>btnUpdateActionPerformed33");
                if (flg == 0)
                {
                    try
                    {
                        Connection conn = DatabaseConnection1.GetConnection();
                        Statement st = conn.createStatement();
 System.out.println("StockGroupAlterFromDetails--------------->>btnUpdateActionPerformed4");
                        ResultSet rs = st.executeQuery("select sg_id from tblstockgroup where sg_name='" + under + "'");
                        if (rs.next())
                        {
                            String sql = "update tblstockgroup set sg_name='" + name + "',sg_alias='" + alias + "',sg_under=" + rs.getInt("sg_id") + " where sg_id=" + s_id + "";
                            st.executeUpdate(sql);
                        }
                        JOptionPane.showMessageDialog(null, "Record Updated Successfully");
                        txtName.setText("");
                        txtAlias.setText("");
                        comboUnder.setSelectedIndex(0);
                        btnStockGroupBackActionPerformed(evt);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "You have selected Parent group which in child directory of current group. Please change it.");
                    comboUnder.requestFocus();
                }
            }
        }
}//GEN-LAST:event_btnUpdateActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        btnUpdate.setMnemonic(KeyEvent.VK_U);
        btnStockGroupBack.setMnemonic(KeyEvent.VK_B);
         Connection conn=null;
        try
        {
               conn=DatabaseConnection1.GetConnection();
                Statement st=conn.createStatement();
                Statement st1=conn.createStatement();
                ResultSet rs=st.executeQuery("select sg_name from tblstockgroup");  
                comboUnder.removeAllItems();
                comboUnder.addItem("");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                
                comboUnder.setEditable(true);
                tf=(JTextField) comboUnder.getEditor().getEditorComponent();
                
                
                while(rs.next())
                {
                    v.add(rs.getString("sg_name"));
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
               int size1=v.size();
              for(int i=0;i<size1;i++)
              {
               comboUnder.addItem(v.get(i));
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
                          comboUnder.hidePopup();
                          setModel(new DefaultComboBoxModel(v), "");
                        }
                        else
                        {
                          DefaultComboBoxModel m = getSuggestedModel(v, text);
                          if(m.getSize()==0 || hide_flag)
                          {
                            comboUnder.hidePopup();
                            hide_flag = false;
                          }
                          else
                          {
                            setModel(m, text);
                            comboUnder.showPopup();
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
                            if(comboUnder.getSelectedItem().equals(""))
                               {
                                //JOptionPane.showMessageDialog(rootPane, "Enter data for Under Field");
                                //comboUnder.requestFocus();
                                    btnUpdate.requestFocus();
                               }
                               else
                               {
                               btnUpdate.requestFocus();
                               }
                                // btnUpdate.requestFocus();
                            }
                            if(event.getKeyCode()==KeyEvent.VK_ESCAPE)
                            {
                             txtAlias.requestFocus();
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
                          comboUnder.hidePopup();
                          setModel(new DefaultComboBoxModel(v), "");
                        }
                        else
                        {
                          DefaultComboBoxModel m = getSuggestedModel(v, text);
                          if(m.getSize()==0 || hide_flag)
                          {
                            comboUnder.hidePopup();
                            hide_flag = false;
                          }
                          else
                          {
                            setModel(m, text);
                            comboUnder.showPopup();
                          }
                        }
                        
                        tf.selectAll();
                        
                }

                @Override
                public void focusLost(FocusEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet.");
                }
            });
             
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                
                rs.close();
                
                rs=st.executeQuery("select sg_name,sg_alias,sg_under from tblstockgroup where sg_id="+ s_id +"");
                if(rs.next())
                {
                    txtName.setText(rs.getString("sg_name"));
                    txtAlias.setText(rs.getString("sg_alias"));
                    ResultSet rs1=st1.executeQuery("select sg_name from tblstockgroup where sg_id="+ rs.getLong("sg_under") +""); 
                    if(rs1.next())
                    {
                        comboUnder.setSelectedItem(rs1.getString("sg_name"));
                    }
                }
                
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(StockGroupAlterFromDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        txtName.requestFocus();
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void txtNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyTyped
//        char c = evt.getKeyChar();
//        if (!Character.isLetter(c) && !Character.isDigit(c)) 
//        {
//            //evt.consume();
//        }
    }//GEN-LAST:event_txtNameKeyTyped

    private void txtAliasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAliasKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && !Character.isDigit(c)) 
        {
            //evt.consume();
        }
    }//GEN-LAST:event_txtAliasKeyTyped

    private void btnUpdateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnUpdateKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)
       {
        comboUnder.requestFocus();
       }
       else if(evt.getKeyCode()==KeyEvent.VK_ENTER)
       {
       
       btnUpdateActionPerformed(null);
       }
       else if(evt.getKeyCode()==KeyEvent.VK_LEFT)
       {
        btnStockGroupBack.requestFocus();
       }
    }//GEN-LAST:event_btnUpdateKeyPressed

    private void btnStockGroupBackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnStockGroupBackKeyPressed
         if(evt.getKeyCode()==KeyEvent.VK_RIGHT)
       {
        btnUpdate.requestFocus();
       }
       else if(evt.getKeyCode()==KeyEvent.VK_ENTER)
       {
        btnStockGroupBackActionPerformed(null);
       }
    }//GEN-LAST:event_btnStockGroupBackKeyPressed

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
public void addMainGroups()
    {
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

    public int checkChildDirectory(String g)
    {
         System.out.println("StockGroupAlterFromDetails--------------->>btnUpdateActionPerformed331");
        ArrayList<Long> ids = new ArrayList<Long>();    //for child ids
        int i=0;
        long ug_id=0;
        while(i<grp.size())
        {
            if(grp.get(i).getName().equalsIgnoreCase(g))
            {
                ug_id=grp.get(i).getId();
                break;
            }
            i++;
        }
 System.out.println("StockGroupAlterFromDetails--------------->>btnUpdateActionPerformed332");
        ids.add(s_id);
        int ind=0,flag=0;
        try
            {
                Connection conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();
 System.out.println("StockGroupAlterFromDetails--------------->>btnUpdateActionPerformed332 : child ids " + ids.get(ind));
                do
                {
                    String q="select sg_id from tblstockgroup where sg_under="+ids.get(ind) +"";
                    ResultSet rs = st.executeQuery(q);
                    while(rs.next())
                    {
                        ids.add(rs.getLong("sg_id"));
                    }
                    if(ind==ids.size()-1) {
                        flag=1;
                    }
                    ind++;
                }while(flag==0);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
 System.out.println("StockGroupAlterFromDetails--------------->>btnUpdateActionPerformed333");
        i=0;
        flag=0;
        while(i<ids.size())
        {
            if(ids.get(i)==ug_id)
            {
                flag=1;
                break;
            }
            i++;
        }
         System.out.println("StockGroupAlterFromDetails--------------->>btnUpdateActionPerformed334StockGroupAlterFromDetails--------------->>btnUpdateActionPerformed332");
        if(flag==1) //if found in child list
            return(1);
        return(0);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Stock_Group_Panel;
    private javax.swing.JButton btnStockGroupBack;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox comboUnder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtAlias;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
     
 private boolean hide_flag = false;
       private void setModel(DefaultComboBoxModel mdl, String str) {
        comboUnder.setModel(mdl);
        comboUnder.setSelectedIndex(-1);
        comboUnder.setPopupVisible(true);
        tf.setText(str);
       //tf.selectAll();
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
             System.out.println("StockGroupAlterFromDetails--------------->>checkElementPresence");
          for(int i=0;i<combo.size();i++)
          {
           if(tf.getText().trim().equalsIgnoreCase(combo.get(i).toString().trim()))
           {
             return true;
           }
          }
       return false;
         }


}
