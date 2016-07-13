package gen.account.group;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class GroupAlterFromDetails extends javax.swing.JInternalFrame {
    
      
    ResultSet rs;
    static long g_id=0;
    ArrayList<String> MainGroups=new ArrayList<String>();
     ArrayList<GroupData> grp=new ArrayList<GroupData>();
     JTextField tf;
     private final Vector<String> v = new Vector<String>();
    //ArrayList<String> GroupItems=new ArrayList<String>();
    public GroupAlterFromDetails(long id,String s) 
    {
        
        setClosable(true);
        initComponents();
        g_id=id;
        this.setTitle(s);
        addMainGroups();
        
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Group_Create_Panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbGroupAlter_Under = new javax.swing.JComboBox();
        txtGroupAlter_Name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnGroupCreate_Back = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        txtGroupAlter_Alias = new javax.swing.JTextField();

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
        jLabel1.setText("Name Of Group");

        cmbGroupAlter_Under.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cmbGroupAlter_Under.setEditable(true);
        cmbGroupAlter_Under.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbGroupAlter_UnderFocusLost(evt);
            }
        });
        cmbGroupAlter_Under.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbGroupAlter_UnderKeyPressed(evt);
            }
        });

        txtGroupAlter_Name.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtGroupAlter_Name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGroupAlter_NameFocusGained(evt);
            }
        });
        txtGroupAlter_Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGroupAlter_NameKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
        jLabel3.setText("Under");

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
        jLabel2.setText("Alias");

        btnGroupCreate_Back.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGroupCreate_Back.setText("Back");
        btnGroupCreate_Back.setFocusable(false);
        btnGroupCreate_Back.setRequestFocusEnabled(false);
        btnGroupCreate_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGroupCreate_BackActionPerformed(evt);
            }
        });
        btnGroupCreate_Back.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnGroupCreate_BackKeyPressed(evt);
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

        txtGroupAlter_Alias.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtGroupAlter_Alias.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGroupAlter_AliasFocusGained(evt);
            }
        });
        txtGroupAlter_Alias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGroupAlter_AliasKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout Group_Create_PanelLayout = new javax.swing.GroupLayout(Group_Create_Panel);
        Group_Create_Panel.setLayout(Group_Create_PanelLayout);
        Group_Create_PanelLayout.setHorizontalGroup(
            Group_Create_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Group_Create_PanelLayout.createSequentialGroup()
                .addGroup(Group_Create_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Group_Create_PanelLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(Group_Create_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(34, 34, 34)
                        .addGroup(Group_Create_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGroupAlter_Name)
                            .addComponent(txtGroupAlter_Alias)
                            .addComponent(cmbGroupAlter_Under, 0, 260, Short.MAX_VALUE)))
                    .addGroup(Group_Create_PanelLayout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(btnGroupCreate_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnUpdate)))
                .addContainerGap(165, Short.MAX_VALUE))
        );
        Group_Create_PanelLayout.setVerticalGroup(
            Group_Create_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Group_Create_PanelLayout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(Group_Create_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtGroupAlter_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(Group_Create_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtGroupAlter_Alias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(Group_Create_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbGroupAlter_Under, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(Group_Create_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGroupCreate_Back)
                    .addComponent(btnUpdate))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(Group_Create_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(Group_Create_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbGroupAlter_UnderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbGroupAlter_UnderKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) 
        {
            txtGroupAlter_Alias.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) 
        {
            btnUpdateActionPerformed(null);
        }

}//GEN-LAST:event_cmbGroupAlter_UnderKeyPressed

    private void txtGroupAlter_NameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGroupAlter_NameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnGroupCreate_BackActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtGroupAlter_Alias.requestFocus();
        }
}//GEN-LAST:event_txtGroupAlter_NameKeyPressed

    private void btnGroupCreate_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGroupCreate_BackActionPerformed
               
             //   if (labelName.getText().equals("")) {
           // JOptionPane.showMessageDialog(null, "Please select the Group");
           //txtGroupAlter_Group.requestFocus();
       // } else {
           
            GroupDetails g = new GroupDetails("View Group Details");
            // this.getParent().add(g);      
           
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
                  this.setSelected(true);
                  this.setClosed(true);
                } catch (java.beans.PropertyVetoException e) {
            }
      //       try {
            //MainClass2 mainClass = new MainClass();
            //mainClass.menuselection(1);
        //    this.setSelected(true);
       //     this.setClosed(true);
      //  } catch (PropertyVetoException ex) {
      //      Logger.getLogger(Group_Alter.class.getName()).log(Level.SEVERE, null, ex);
      //  }
     //       BasicInternalFrameUI ui = (BasicInternalFrameUI) g.getUI();
   //         Component north = ui.getNorthPane();
     //       MouseMotionListener[] actions =
       //             (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);
//
      //      for (int i = 0; i < actions.length; i++) {
       //         north.removeMouseMotionListener(actions[i]);
           // }
            //btnGroupAlter_BackActionPerformed(evt);
        
         
    
             
        
}//GEN-LAST:event_btnGroupCreate_BackActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
         if(txtGroupAlter_Name.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(this,"Enter Group Name");
            txtGroupAlter_Name.requestFocus();
        }
         else if (cmbGroupAlter_Under.getSelectedItem()==null || cmbGroupAlter_Under.getSelectedItem()=="")// || tf.getText().trim().equalsIgnoreCase("") || ComboGroupCreate_Under.getSelectedItem().toString().trim().equals("") ) 
        {
            System.out.println("eeeeeeeeeeeeeeeee");
            JOptionPane.showMessageDialog(this, "Select Data for Under Group Fields");
            cmbGroupAlter_Under.requestFocus();
        } 
           else if(!checkElementPresence(v, tf.getText() )){
            JOptionPane.showMessageDialog(this, "Selected Value For Under Field is not valid");
        //    scrollpaneMain.getVerticalScrollBar().setValue(0);  //go to top of scrollpane
            cmbGroupAlter_Under.requestFocus();
        }
        
        else
        {

            int i=0;
            String nm="";
            while(i<grp.size())
            {
                if(grp.get(i).getId()==g_id)
                {
                    nm=grp.get(i).getName();
                    break;
                }
                i++;
            }

            i=0;
            int flag=0;
            while(i<MainGroups.size())
            {
                if(nm.equalsIgnoreCase(MainGroups.get(i)))
                {
                    flag=1;
                    break;
                }
                i++;
            }
            if(flag==1)
            {
                String name = txtGroupAlter_Name.getText();
                String alias = txtGroupAlter_Alias.getText();
                Object under = cmbGroupAlter_Under.getSelectedItem();
                int flg=checkChildDirectory(under.toString());
                if(flg==0)
                {
                    try
                    {
                    Connection conn = DatabaseConnection1.GetConnection();
                    conn.setAutoCommit(false);
                    Statement st = conn.createStatement();

                    ResultSet rs1 = st.executeQuery("select group_id from tblgroup where group_name='" + under + "'");
                    if (rs1.next())
                    {
                       // st.executeUpdate("UPDATE tblgroup SET  group_alias='" + alias.trim() + "',group_under=" + rs1.getLong("group_id") + " where group_id=" + g_id + "");
                         
                        PreparedStatement ps;
                        String q="UPDATE tblgroup SET group_alias=?,group_under=? where group_id=?";
                        ps=conn.prepareStatement(q);
                        
                        ps.setString(1, alias.trim());
                        ps.setLong(2, rs1.getLong("group_id"));
                        ps.setLong(3, g_id);
                        
                        ps.executeUpdate();
                    
                    }
                    JOptionPane.showMessageDialog(this, "Fields Updated Successfully except group name as it is predefined group");
                    conn.close();
                   btnGroupCreate_BackActionPerformed(evt);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"You have selected Parent group which in child directory of current group. Please change it.");
                    cmbGroupAlter_Under.requestFocus();
                }
            }
            else
            {
                String name = txtGroupAlter_Name.getText();
                String alias = txtGroupAlter_Alias.getText();
                Object under = cmbGroupAlter_Under.getSelectedItem();
                int flg=checkChildDirectory(under.toString());
                if(flg==0)
                {
                try
                {


                    Connection conn = DatabaseConnection1.GetConnection();
                    Statement st = conn.createStatement();

                    ResultSet rs1 = st.executeQuery("select group_id from tblgroup where group_name='" + under + "'");
                    if (rs1.next())
                    {
                      //  st.executeUpdate("UPDATE tblgroup SET  group_name='" + name.trim() + "',group_alias='" + alias.trim() + "',group_under=" + rs1.getInt("group_id") + " where group_id=" + g_id + "");
                    
                        PreparedStatement ps;
                        String q="UPDATE tblgroup SET group_name=?,group_alias=?,group_under=? where group_id=?";
                        ps=conn.prepareStatement(q);
                        
                        ps.setString(1, name.trim());
                        ps.setString(2, alias.trim());
                        ps.setInt(3, rs1.getInt("group_id"));
                        ps.setLong(4, g_id);
                        
                        ps.executeUpdate();
                    
                    }
                    //conn.commit();
                    JOptionPane.showMessageDialog(this, "Record Updated Successfully");
                   btnGroupCreate_BackActionPerformed(evt);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"You have selected Parent group which in child directory of current group. Please change it.");
                    cmbGroupAlter_Under.requestFocus();
                }
            }

        }
}//GEN-LAST:event_btnUpdateActionPerformed

    private void txtGroupAlter_AliasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGroupAlter_AliasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtGroupAlter_Name.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbGroupAlter_Under.requestFocus();
        }
}//GEN-LAST:event_txtGroupAlter_AliasKeyPressed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        btnUpdate.setMnemonic(KeyEvent.VK_U);
        btnGroupCreate_Back.setMnemonic(KeyEvent.VK_B);
        Connection conn=null;
        try 
        {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            Statement st1 = conn.createStatement();
            rs = st.executeQuery("select group_name from tblgroup");
            cmbGroupAlter_Under.removeAllItems();
            cmbGroupAlter_Under.addItem("");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
           //Code snippet built by sudeep on 8-01-2013 : 6.25 P.M.
         //   tf = (JTextField) cmbGroupAlter_Under.getEditor().getEditorComponent();
          //  DefaultComboBoxModel m = new DefaultComboBoxModel();
            cmbGroupAlter_Under.setEditable(true);
            tf=(JTextField) cmbGroupAlter_Under.getEditor().getEditorComponent();
            
            while (rs.next()) 
            {
                v.add(rs.getString("group_name"));
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
               cmbGroupAlter_Under.addItem(v.get(i));
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
                          cmbGroupAlter_Under.hidePopup();
                          setModel(new DefaultComboBoxModel(v), "");
                        }
                        else
                        {
                          DefaultComboBoxModel m = getSuggestedModel(v, text);
                          if(m.getSize()==0 || hide_flag)
                          {
                            cmbGroupAlter_Under.hidePopup();
                            hide_flag = false;
                          }
                          else
                          {
                            setModel(m, text);
                            cmbGroupAlter_Under.showPopup();
                          }
                        }
                        
                        
                  String text1 = tf.getText();
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
                         cmbGroupAlter_Under.setSelectedIndex(-1);
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
//                             if(cmbGroupAlter_Under.getSelectedItem().equals(""))
//                               {
//                                JOptionPane.showMessageDialog(rootPane, "Enter data for Under Group Of Field");
//                                cmbGroupAlter_Under.requestFocus();
//                               }
//                               else
//                               {
                               btnUpdate.requestFocus();
//                               }
                            }
                            if(event.getKeyCode()==KeyEvent.VK_ESCAPE)
                            {
                             txtGroupAlter_Alias.requestFocus();
                            }
                        }
                  
                   
               });
                       }
                
               });    
             
             tf.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {
                   // throw new UnsupportedOperationException("Not supported yet.");
                     String text = tf.getText();
                        if(text.length()==0) 
                        {
                          cmbGroupAlter_Under.hidePopup();
                          setModel(new DefaultComboBoxModel(v), "");
                        }
                        else
                        {
                          DefaultComboBoxModel m = getSuggestedModel(v, text);
                          if(m.getSize()==0 || hide_flag)
                          {
                            cmbGroupAlter_Under.hidePopup();
                            hide_flag = false;
                          }
                          else
                          {
                            setModel(m, text);
                            cmbGroupAlter_Under.showPopup();
                          }
                        }
                        tf.selectAll();
                        
                }

                @Override
                public void focusLost(FocusEvent e) {
                   // throw new UnsupportedOperationException("Not supported yet.");
                }
            });
             
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
            rs.close();
            
            ResultSet rs1 = st1.executeQuery("select * from tblgroup where group_id="+ g_id +"");
            if(rs1.next())
            {
                txtGroupAlter_Name.setText(rs1.getString("group_name"));
                txtGroupAlter_Alias.setText(rs1.getString("group_alias"));
                rs = st.executeQuery("select group_name from tblgroup where group_id="+rs1.getLong("group_under") +"");
                if(rs.next())
                {
                    cmbGroupAlter_Under.setSelectedItem(rs.getString("group_name"));
                }
            }
            txtGroupAlter_Name.requestFocus();
        } 
        catch (Exception e) 
        {
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(GroupAlterFromDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnUpdateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnUpdateKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)
       {
        cmbGroupAlter_Under.requestFocus();
       }
       else if(evt.getKeyCode()==KeyEvent.VK_ENTER)
       {
       
        btnUpdateActionPerformed(null);
       }
       else if(evt.getKeyCode()==KeyEvent.VK_LEFT)
       {
        btnGroupCreate_Back.requestFocus();
       }
    }//GEN-LAST:event_btnUpdateKeyPressed

    private void btnGroupCreate_BackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGroupCreate_BackKeyPressed
         if(evt.getKeyCode()==KeyEvent.VK_RIGHT)
       {
        btnUpdate.requestFocus();
       }
       else if(evt.getKeyCode()==KeyEvent.VK_ENTER)
       {
        btnGroupCreate_BackActionPerformed(null);
       }
    }//GEN-LAST:event_btnGroupCreate_BackKeyPressed

    private void txtGroupAlter_NameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGroupAlter_NameFocusGained
        // TODO add your handling code here:
        txtGroupAlter_Name.selectAll();
    }//GEN-LAST:event_txtGroupAlter_NameFocusGained

    private void txtGroupAlter_AliasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGroupAlter_AliasFocusGained
        // TODO add your handling code here:
        txtGroupAlter_Alias.selectAll();
    }//GEN-LAST:event_txtGroupAlter_AliasFocusGained

    private void cmbGroupAlter_UnderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbGroupAlter_UnderFocusLost
        // TODO add your handling code here:
        cmbGroupAlter_Under.hidePopup();
    }//GEN-LAST:event_cmbGroupAlter_UnderFocusLost
public void addMainGroups()
    {
        MainGroups.add("Primary");
        MainGroups.add("Current Liability");
        MainGroups.add("Current Assets");
        MainGroups.add("Suspense Account");
        MainGroups.add("Sundry Debtors");
        MainGroups.add("Sundry Creditors");
        MainGroups.add("Stock-In-Hand");
        MainGroups.add("Sales Account");
        MainGroups.add("Purchase Account");
        MainGroups.add("Loan Liability");
        MainGroups.add("Loans and Advances");
        MainGroups.add("Indirect Expenses");
        MainGroups.add("Indirect Income");
        MainGroups.add("Income (Direct)");
        MainGroups.add("Income (Indirect)");
        MainGroups.add("Expense (Direct)");
        MainGroups.add("Expense (Indirect)");
        MainGroups.add("Bank Account");
        MainGroups.add("Cash In Hand");
    }

    public int checkChildDirectory(String g)
    {
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

        ids.add(g_id);
        int ind=0,flag=0;
        try
            {
                Connection conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();

                do
                {
                    String q="select group_id from tblgroup where group_under="+ids.get(ind) +"";
                    ResultSet rs = st.executeQuery(q);
                    while(rs.next())
                    {
                        ids.add(rs.getLong("group_id"));
                    }
                    if(ind==ids.size()-1)
                        flag=1;
                    ind++;
                }while(flag==0);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

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
        if(flag==1) //if found in child list
            return(1);
        return(0);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Group_Create_Panel;
    private javax.swing.JButton btnGroupCreate_Back;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cmbGroupAlter_Under;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtGroupAlter_Alias;
    private javax.swing.JTextField txtGroupAlter_Name;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JDesktopPane desktopMain;
    
    
    private boolean hide_flag = false;
       private void setModel(DefaultComboBoxModel mdl, String str) {
        cmbGroupAlter_Under.setModel(mdl);
        cmbGroupAlter_Under.setSelectedIndex(-1);
        cmbGroupAlter_Under.setPopupVisible(true);
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
          for(int i=0;i<combo.size();i++)
          {
           if(tf.getText().trim().equals(cmbGroupAlter_Under.getItemAt(i)))
           {
             return true;
           }
          }
       return false;
         }


}