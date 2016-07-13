package gen.account.group;

import gen.database.connection.DatabaseConnection1;
import gen.mainclass.MainClass;
import java.awt.EventQueue;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Group_Create extends javax.swing.JInternalFrame {

    private final Vector<String> v = new Vector<String>();
    ResultSet rs;
    static int k = 0;
    JTextField tf;
    // ArrayList<String> GroupItems=new ArrayList<String>();

    public Group_Create(String s) {
        setClosable(true);
        initComponents();
        this.setTitle(s);


    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Group_Create_Panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ComboGroupCreate_Under = new javax.swing.JComboBox();
        txtGroupCreate_Name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtGroupCreate_Alias = new javax.swing.JTextField();
        btnGroupCreate_Back = new javax.swing.JButton();
        btnGroupCreate_Create = new javax.swing.JButton();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png"))); // NOI18N
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

        jLabel1.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
        jLabel1.setText("Name Of Group");

        ComboGroupCreate_Under.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ComboGroupCreate_Under.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboGroupCreate_UnderActionPerformed(evt);
            }
        });
        ComboGroupCreate_Under.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ComboGroupCreate_UnderFocusLost(evt);
            }
        });
        ComboGroupCreate_Under.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ComboGroupCreate_UnderKeyPressed(evt);
            }
        });

        txtGroupCreate_Name.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtGroupCreate_Name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGroupCreate_NameMouseClicked(evt);
            }
        });
        txtGroupCreate_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGroupCreate_NameActionPerformed(evt);
            }
        });
        txtGroupCreate_Name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGroupCreate_NameFocusGained(evt);
            }
        });
        txtGroupCreate_Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGroupCreate_NameKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
        jLabel3.setText("Under");

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
        jLabel2.setText("Alias");

        txtGroupCreate_Alias.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtGroupCreate_Alias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGroupCreate_AliasActionPerformed(evt);
            }
        });
        txtGroupCreate_Alias.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGroupCreate_AliasFocusGained(evt);
            }
        });
        txtGroupCreate_Alias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGroupCreate_AliasKeyPressed(evt);
            }
        });

        btnGroupCreate_Back.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGroupCreate_Back.setText("Back");
        btnGroupCreate_Back.setFocusable(false);
        btnGroupCreate_Back.setRequestFocusEnabled(false);
        btnGroupCreate_Back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGroupCreate_BackMouseClicked(evt);
            }
        });
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

        btnGroupCreate_Create.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGroupCreate_Create.setText("Create");
        btnGroupCreate_Create.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGroupCreate_CreateMouseClicked(evt);
            }
        });
        btnGroupCreate_Create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGroupCreate_CreateActionPerformed(evt);
            }
        });
        btnGroupCreate_Create.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnGroupCreate_CreateKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout Group_Create_PanelLayout = new javax.swing.GroupLayout(Group_Create_Panel);
        Group_Create_Panel.setLayout(Group_Create_PanelLayout);
        Group_Create_PanelLayout.setHorizontalGroup(
            Group_Create_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Group_Create_PanelLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(Group_Create_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(Group_Create_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Group_Create_PanelLayout.createSequentialGroup()
                        .addComponent(ComboGroupCreate_Under, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))
                    .addGroup(Group_Create_PanelLayout.createSequentialGroup()
                        .addGroup(Group_Create_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGroupCreate_Alias, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGroupCreate_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(Group_Create_PanelLayout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(btnGroupCreate_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btnGroupCreate_Create)
                .addContainerGap())
        );
        Group_Create_PanelLayout.setVerticalGroup(
            Group_Create_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Group_Create_PanelLayout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addGroup(Group_Create_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGroupCreate_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(38, 38, 38)
                .addGroup(Group_Create_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGroupCreate_Alias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(37, 37, 37)
                .addGroup(Group_Create_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboGroupCreate_Under, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(59, 59, 59)
                .addGroup(Group_Create_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGroupCreate_Back)
                    .addComponent(btnGroupCreate_Create))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(Group_Create_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(Group_Create_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboGroupCreate_UnderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ComboGroupCreate_UnderKeyPressed
        /* if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) 
         {
         txtGroupCreate_Alias.requestFocus();
         }
         if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) 
         {
         btnGroupCreate_CreateActionPerformed(null);
         ComboGroupCreate_Under.transferFocusBackward();
         }*/
}//GEN-LAST:event_ComboGroupCreate_UnderKeyPressed

    private void txtGroupCreate_NameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGroupCreate_NameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnGroupCreate_BackActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtGroupCreate_Alias.requestFocus();
        }
}//GEN-LAST:event_txtGroupCreate_NameKeyPressed

    private void btnGroupCreate_CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGroupCreate_CreateActionPerformed
        // System.out.println("Combo Under :"+ComboGroupCreate_Under.getSelectedItem()+ " actual value: "+(ComboGroupCreate_Under.getSelectedItem()==null));
        // System.out.println("Combo Under TF :"+tf.getText());
        if (txtGroupCreate_Name.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Enter Valid Name of Group");
            System.out.println("txt.................");
            txtGroupCreate_Name.requestFocus();
        } else if (ComboGroupCreate_Under.getSelectedItem() == null || ComboGroupCreate_Under.getSelectedItem() == "")// || tf.getText().trim().equalsIgnoreCase("") || ComboGroupCreate_Under.getSelectedItem().toString().trim().equals("") ) 
        {
            System.out.println("eeeeeeeeeeeeeeeee");
            JOptionPane.showMessageDialog(this, "Select Data for Under Group Fields");
            ComboGroupCreate_Under.requestFocus();
        } else if (!checkElementPresence(v, tf.getText())) {
            JOptionPane.showMessageDialog(this, "Selected Value For Under Field is not valid");

            //    scrollpaneMain.getVerticalScrollBar().setValue(0);  //go to top of scrollpane
            ComboGroupCreate_Under.requestFocus();
        } /*  else if(txtGroupCreate_Alias.getText().trim().equals(""))
         {
         JOptionPane.showMessageDialog(this, "Select Alias For Group");
         txtGroupCreate_Alias.requestFocus();
         }*/ else {
            String Gname = txtGroupCreate_Name.getText();
            //String spl="\\";
            // Gname.concat("\\");
            String Galias = txtGroupCreate_Alias.getText();
            String Gunder = (String) ComboGroupCreate_Under.getSelectedItem();
            Connection conn = null;
            try {
                conn = DatabaseConnection1.GetConnection();
                conn.setAutoCommit(false);
                Statement st = conn.createStatement();

                rs = st.executeQuery("select group_id from tblgroup where group_name='" + txtGroupCreate_Name.getText() + "'");

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "This name is already taken");
                    txtGroupCreate_Name.selectAll();
                    txtGroupCreate_Name.requestFocus();
                } else {
                    rs = st.executeQuery("select group_id from tblgroup where group_name='" + Gunder + "'");
                    if (rs.next()) {
                        // String sql = "insert into tblgroup(group_name,group_under,group_alias) values('" + Gname.trim() + "'," + rs.getInt("group_id") + ",'" + Galias.trim() + "');";
                        //  st.executeUpdate(sql);
                        PreparedStatement ps;
                        String sql = "insert into tblgroup(group_name,group_under,group_alias) values(?,?,?)";
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, Gname.trim());
                        ps.setInt(2, rs.getInt("group_id"));
                        if (Galias.equals("")) {
                            ps.setString(3, "");
                        } else {
                            ps.setString(3, Galias.trim());
                        }
                        ps.executeUpdate();

                        conn.commit();
                        JOptionPane.showMessageDialog(this, "Group Created Successfully");
                        /////////////////////////////////////////////////////////////////////////////////
                      /*Code Snippet Added By Sudeep on 11-03-2013*/

                        v.clear();
                        ComboGroupCreate_Under.removeAllItems();
                        formInternalFrameOpened(null);

                        /////////////////////////////////////////////////////////////////////////////////     
                    }
                }
                txtGroupCreate_Name.setText("");
                txtGroupCreate_Alias.setText("");
                ComboGroupCreate_Under.setSelectedItem("");
                // ComboGroupCreate_Under.setSelectedIndex(0);
                txtGroupCreate_Name.requestFocus();
            } catch (SQLException e) {
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Group_Create.class.getName()).log(Level.SEVERE, null, ex);
                }
            }



        }
    }//GEN-LAST:event_btnGroupCreate_CreateActionPerformed

    private void txtGroupCreate_AliasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGroupCreate_AliasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtGroupCreate_Name.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ComboGroupCreate_Under.requestFocus();
        }
    }//GEN-LAST:event_txtGroupCreate_AliasKeyPressed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened


        btnGroupCreate_Create.setMnemonic(KeyEvent.VK_C);
        btnGroupCreate_Back.setMnemonic(KeyEvent.VK_B);
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            rs = st.executeQuery("select group_name from tblgroup");
            ComboGroupCreate_Under.removeAllItems();
            ComboGroupCreate_Under.addItem("");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////             
            ComboGroupCreate_Under.setEditable(true);
            tf = (JTextField) ComboGroupCreate_Under.getEditor().getEditorComponent();

            while (rs.next()) {
                v.add(rs.getString("group_name"));
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
                ComboGroupCreate_Under.addItem(v.get(i));
            }

            tf.addKeyListener(new KeyAdapter() {
                public void keyTyped(final KeyEvent e) {
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            String text = tf.getText();
                            if (text.length() == 0) {
                                ComboGroupCreate_Under.hidePopup();
                                setModel(new DefaultComboBoxModel(v), "");
                            } else {
                                DefaultComboBoxModel m = getSuggestedModel(v, text);
                                if (m.getSize() == 0 || hide_flag) {
                                    ComboGroupCreate_Under.hidePopup();
                                    hide_flag = false;
                                } else {
                                    setModel(m, text);
                                    ComboGroupCreate_Under.showPopup();

                                }

                            }


                            //  String text1 = tf.getText();

                            /*      int code = e.getKeyCode();
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
                     
                             }*/
                            /*   else if(code==KeyEvent.VK_ESCAPE) 
                             {
                             hide_flag = true; 
                             }*/
                            /*       else if(code==KeyEvent.VK_RIGHT) 
                             {
                             for(int i=0;i<v.size();i++)
                             {
                             String str = v.elementAt(i);
                         
                             if(str.startsWith(text))
                             {
                             ComboGroupCreate_Under.setSelectedIndex(-1);
                             tf.setText(str);
                             return;
                                                  
                             }
                       
                             }
                     
                             }*/

                        }
                    });

                }
            });
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
            tf.addKeyListener(new KeyAdapter() {
                public void keyPressed(final KeyEvent event) {
                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {


                            if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                                /*if(ComboGroupCreate_Under.getSelectedItem().equals(""))
                                 {
                                 JOptionPane.showMessageDialog(rootPane,"Select Value For Under Field");
                                 ComboGroupCreate_Under.requestFocus();
                                 }
                                 else*/
                                {
                                    btnGroupCreate_Create.requestFocus();
                                }

                            }
                            if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                txtGroupCreate_Alias.requestFocus();
                            }

                        }
                    });
                }
            });

            tf.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet.");

                    String text = tf.getText();
                    if (text.length() == 0) {
                        ComboGroupCreate_Under.hidePopup();
                        setModel(new DefaultComboBoxModel(v), "");
                    } else {
                        DefaultComboBoxModel m = getSuggestedModel(v, text);
                        if (m.getSize() == 0 || hide_flag) {
                            ComboGroupCreate_Under.hidePopup();
                            hide_flag = false;
                        } else {
                            setModel(m, text);
                            ComboGroupCreate_Under.showPopup();

                        }

                    }

                    tf.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet.");
                }
            });
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                   
            /*    tf.addKeyListener(new KeyAdapter()
             {
             public void keyReleased(final KeyEvent event) 
             {
             EventQueue.invokeLater(new Runnable() 
             {

             @Override
             public void run() {
                           
                            
             if(event.getKeyCode()==KeyEvent.VK_ENTER)
             {
             System.out.println("Enter Released.....");
             }
             if(event.getKeyCode()==KeyEvent.VK_ESCAPE)
             {
             System.out.println("Escape Released.....");
             }
             }
                  
                   
             });
             }
                
             });   */
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////           
            // txtGroupCreate_Name.requestFocus();
            // btnGroupCreate_Create.requestFocus();
        } catch (Exception e) {
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Group_Create.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //System.out.println("2. Focus owner: "+this.getFocusOwner());
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnGroupCreate_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGroupCreate_BackActionPerformed
        try {
            MainClass mainClass = new MainClass();
            mainClass.menuselection(1);

            //  mainClass.menuselection();
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Group_Create.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGroupCreate_BackActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void txtGroupCreate_NameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGroupCreate_NameMouseClicked
        System.out.println("mouse clicked");
    }//GEN-LAST:event_txtGroupCreate_NameMouseClicked

    private void txtGroupCreate_AliasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGroupCreate_AliasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGroupCreate_AliasActionPerformed

    private void ComboGroupCreate_UnderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboGroupCreate_UnderActionPerformed
    }//GEN-LAST:event_ComboGroupCreate_UnderActionPerformed

    private void txtGroupCreate_NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGroupCreate_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGroupCreate_NameActionPerformed

    private void btnGroupCreate_CreateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGroupCreate_CreateKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            /* if(ComboGroupCreate_Under.getSelectedItem().equals(""))
             {
             JOptionPane.showMessageDialog(rootPane,"Select Value For Under Field");
             ComboGroupCreate_Under.requestFocus();
             }*/

            btnGroupCreate_CreateActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            ComboGroupCreate_Under.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnGroupCreate_Back.requestFocus();
        }


    }//GEN-LAST:event_btnGroupCreate_CreateKeyPressed

    private void btnGroupCreate_BackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGroupCreate_BackKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGroupCreate_BackActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnGroupCreate_Create.requestFocus();
        }
    }//GEN-LAST:event_btnGroupCreate_BackKeyPressed

    private void btnGroupCreate_CreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGroupCreate_CreateMouseClicked
        // TODO add your handling code here:
        btnGroupCreate_CreateActionPerformed(null);
    }//GEN-LAST:event_btnGroupCreate_CreateMouseClicked

    private void btnGroupCreate_BackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGroupCreate_BackMouseClicked
        // TODO add your handling code here:
        btnGroupCreate_BackActionPerformed(null);
    }//GEN-LAST:event_btnGroupCreate_BackMouseClicked

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameActivated

    private void txtGroupCreate_NameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGroupCreate_NameFocusGained
        // TODO add your handling code here:
        txtGroupCreate_Name.selectAll();
    }//GEN-LAST:event_txtGroupCreate_NameFocusGained

    private void txtGroupCreate_AliasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGroupCreate_AliasFocusGained
        // TODO add your handling code here:
        txtGroupCreate_Alias.selectAll();
    }//GEN-LAST:event_txtGroupCreate_AliasFocusGained

    private void ComboGroupCreate_UnderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ComboGroupCreate_UnderFocusLost
        // TODO add your handling code here:
        ComboGroupCreate_Under.hidePopup();
    }//GEN-LAST:event_ComboGroupCreate_UnderFocusLost

    public void mouseclick() {
        txtGroupCreate_NameMouseClicked(null);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboGroupCreate_Under;
    private javax.swing.JPanel Group_Create_Panel;
    private javax.swing.JButton btnGroupCreate_Back;
    private javax.swing.JButton btnGroupCreate_Create;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtGroupCreate_Alias;
    public javax.swing.JTextField txtGroupCreate_Name;
    // End of variables declaration//GEN-END:variables
    private boolean hide_flag = false;

    private void setModel(DefaultComboBoxModel mdl, String str) {
        ComboGroupCreate_Under.setModel(mdl);
        ComboGroupCreate_Under.setSelectedIndex(-1);
        // String txt1=ComboGroupCreate_Under.getSelectedItem().toString();
        // ComboGroupCreate_Under.setSelectedItem(txt);
        tf.setText(str);
        //  tf.selectAll();
        ComboGroupCreate_Under.setPopupVisible(true);

        /*    if(ComboGroupCreate_Under.getSelectedItem().equals(str))
         {
       
         {
         btnGroupCreate_Create.requestFocus();
         }     
         }*/

    }

    private static DefaultComboBoxModel getSuggestedModel(java.util.List<String> list, String text) {
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        for (String s : list) {
            //if(s.startsWith(text)) m.addElement(s);

            if (s.toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {

                m.addElement(s);
                //System.out.println("Yes"+s);
            }
        }
        return m;
    }

    private boolean checkElementPresence(Vector combo, String str) {
        for (int i = 0; i < combo.size(); i++) {
            if (tf.getText().trim().equals(ComboGroupCreate_Under.getItemAt(i))) {
                return true;
            }
        }
        return false;
    }
}
