package gen.account.stockgroup;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class StockGroup_Create extends javax.swing.JInternalFrame {

    JTextField tf;
    //ArrayList<String> GroupItems=new ArrayList<String>();
    private final Vector<String> v = new Vector<String>();

    public StockGroup_Create(String s) {
        setClosable(true);
        initComponents();
        this.setTitle(s);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Stock_Group_Panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtStockGroupName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ComboStockGroupUnder = new javax.swing.JComboBox();
        txtStockGroupAlias = new javax.swing.JTextField();
        btnStockGroupBack = new javax.swing.JButton();
        btnStockGroupCreate = new javax.swing.JButton();

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

        txtStockGroupName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtStockGroupName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStockGroupNameFocusGained(evt);
            }
        });
        txtStockGroupName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStockGroupNameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockGroupNameKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
        jLabel2.setText("Alias");

        jLabel3.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
        jLabel3.setText("Under");

        ComboStockGroupUnder.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ComboStockGroupUnder.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ComboStockGroupUnderFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ComboStockGroupUnderFocusLost(evt);
            }
        });
        ComboStockGroupUnder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ComboStockGroupUnderKeyPressed(evt);
            }
        });

        txtStockGroupAlias.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtStockGroupAlias.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStockGroupAliasFocusGained(evt);
            }
        });
        txtStockGroupAlias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStockGroupAliasKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockGroupAliasKeyTyped(evt);
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

        btnStockGroupCreate.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnStockGroupCreate.setText("Create");
        btnStockGroupCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockGroupCreateActionPerformed(evt);
            }
        });
        btnStockGroupCreate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnStockGroupCreateKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout Stock_Group_PanelLayout = new javax.swing.GroupLayout(Stock_Group_Panel);
        Stock_Group_Panel.setLayout(Stock_Group_PanelLayout);
        Stock_Group_PanelLayout.setHorizontalGroup(
            Stock_Group_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Stock_Group_PanelLayout.createSequentialGroup()
                .addContainerGap(149, Short.MAX_VALUE)
                .addGroup(Stock_Group_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Stock_Group_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1))
                    .addGroup(Stock_Group_PanelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3)))
                .addGap(56, 56, 56)
                .addGroup(Stock_Group_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtStockGroupAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStockGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboStockGroupUnder, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(138, 138, 138))
            .addGroup(Stock_Group_PanelLayout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addComponent(btnStockGroupBack, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btnStockGroupCreate)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Stock_Group_PanelLayout.setVerticalGroup(
            Stock_Group_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Stock_Group_PanelLayout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addGroup(Stock_Group_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStockGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(28, 28, 28)
                .addGroup(Stock_Group_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStockGroupAlias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(Stock_Group_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboStockGroupUnder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(Stock_Group_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStockGroupBack)
                    .addComponent(btnStockGroupCreate))
                .addGap(148, 148, 148))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(Stock_Group_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Stock_Group_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStockGroupBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockGroupBackActionPerformed
        try {
            MainClass m = new MainClass();
            m.menuselection(2);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(StockGroup_Create.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnStockGroupBackActionPerformed

    private void btnStockGroupCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockGroupCreateActionPerformed
        if (txtStockGroupName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Enter Data in Name  Fields");
            txtStockGroupName.requestFocus();
        } else if (ComboStockGroupUnder.getSelectedItem() == null || ComboStockGroupUnder.getSelectedItem() == "")// || tf.getText().trim().equalsIgnoreCase("") || ComboGroupCreate_Under.getSelectedItem().toString().trim().equals("") ) 
        {
            System.out.println("eeeeeeeeeeeeeeeee");
            JOptionPane.showMessageDialog(this, "Select Data for Under Group Fields");
            ComboStockGroupUnder.requestFocus();
        } else if (!checkElementPresence(v, tf.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Selected Value For Under Field is not valid");
            //    scrollpaneMain.getVerticalScrollBar().setValue(0);  //go to top of scrollpane
            ComboStockGroupUnder.requestFocus();
        } else {
            btnStockGroupCreate.transferFocus();
            String Name = txtStockGroupName.getText().trim();
            String Alias = txtStockGroupAlias.getText().trim();
            String Under = (String) ComboStockGroupUnder.getSelectedItem();
            Connection conn = null;
            try {
                conn = DatabaseConnection1.GetConnection();
                conn.setAutoCommit(false);
                Statement st = conn.createStatement();
                ResultSet rs;
                rs = st.executeQuery("select sg_id from tblstockgroup where sg_name='" + txtStockGroupName.getText().trim() + "'");

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "This name is already taken");
                    txtStockGroupName.selectAll();
                    txtStockGroupName.requestFocus();
                } else {

                    rs = st.executeQuery("select sg_id from tblstockgroup where sg_name='" + Under + "'");
                    if (rs.next()) {
                        //String sql="insert into tblstockgroup(sg_name,sg_alias,sg_under) values('"+Name+"','"+Alias+"',"+rs.getInt("sg_id")+")";
                        //st.executeUpdate(sql);

                        PreparedStatement ps;
                        String sql = "insert into tblstockgroup(sg_name,sg_alias,sg_under) values(?,?,?)";

                        ps = conn.prepareStatement(sql);
                        ps.setString(1, Name);
                        ps.setString(2, Alias);
                        ps.setInt(3, rs.getInt("sg_id"));

                        ps.executeUpdate();

                        conn.commit();
                        JOptionPane.showMessageDialog(this, "Stock Group Successfully Created");
                        /////////////////////////////////////////////////////////////////////////////////////
                              /*Code Snippet Added By Sudeep on 11-03-2013*/

                        v.clear();
                        ComboStockGroupUnder.removeAllItems();
                        formInternalFrameOpened(null);

                        /////////////////////////////////////////////////////////////////////////////////////     

                    }
                }
                txtStockGroupName.setText("");
                txtStockGroupAlias.setText("");
                //ComboStockGroupUnder.setSelectedIndex(0);
                ComboStockGroupUnder.setSelectedItem("");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StockGroup_Create.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
}//GEN-LAST:event_btnStockGroupCreateActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        btnStockGroupCreate.setMnemonic(KeyEvent.VK_C);
        btnStockGroupBack.setMnemonic(KeyEvent.VK_B);
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select sg_name from tblstockgroup");
            ComboStockGroupUnder.removeAllItems();
            ComboStockGroupUnder.addItem("");
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                
            ComboStockGroupUnder.setEditable(true);
            tf = (JTextField) ComboStockGroupUnder.getEditor().getEditorComponent();


            while (rs.next()) {
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
                ComboStockGroupUnder.addItem(v.get(i));
            }

            tf.addKeyListener(new KeyAdapter() {
                public void keyTyped(final KeyEvent e) {
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            String text = tf.getText();
                            if (text.length() == 0) {
                                ComboStockGroupUnder.hidePopup();
                                setModel(new DefaultComboBoxModel(v), "");
                            } else {
                                DefaultComboBoxModel m = getSuggestedModel(v, text);
                                if (m.getSize() == 0 || hide_flag) {
                                    ComboStockGroupUnder.hidePopup();
                                    hide_flag = false;
                                } else {
                                    setModel(m, text);
                                    ComboStockGroupUnder.showPopup();
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
                                        ComboStockGroupUnder.setSelectedIndex(-1);
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
            tf.addKeyListener(new KeyAdapter() {
                public void keyPressed(final KeyEvent event) {
                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {


                            if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                                if (ComboStockGroupUnder.getSelectedItem().equals("")) {
                                    // JOptionPane.showMessageDialog(rootPane, "Enter data for Under Group Of Field");
                                    //ComboStockGroupUnder.requestFocus();
                                    btnStockGroupCreate.requestFocus();
                                } else {
                                    btnStockGroupCreate.requestFocus();
                                }

                                //btnStockGroupCreate.requestFocus();
                            }
                            if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                txtStockGroupAlias.requestFocus();
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
                    if (text.length() == 0) {
                        ComboStockGroupUnder.hidePopup();
                        setModel(new DefaultComboBoxModel(v), "");
                    } else {
                        DefaultComboBoxModel m = getSuggestedModel(v, text);
                        if (m.getSize() == 0 || hide_flag) {
                            ComboStockGroupUnder.hidePopup();
                            hide_flag = false;
                        } else {
                            setModel(m, text);
                            ComboStockGroupUnder.showPopup();
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(StockGroup_Create.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void txtStockGroupNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockGroupNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtStockGroupAlias.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnStockGroupBackActionPerformed(null);
        }
    }//GEN-LAST:event_txtStockGroupNameKeyPressed

    private void txtStockGroupAliasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockGroupAliasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ComboStockGroupUnder.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtStockGroupName.requestFocus();
        }
    }//GEN-LAST:event_txtStockGroupAliasKeyPressed

    private void ComboStockGroupUnderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ComboStockGroupUnderKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtStockGroupAlias.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnStockGroupCreateActionPerformed(null);
        }
    }//GEN-LAST:event_ComboStockGroupUnderKeyPressed

    private void txtStockGroupNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockGroupNameKeyTyped
//        char c = evt.getKeyChar();
//        if (!Character.isLetter(c) && !Character.isDigit(c)) 
//        {
//            //evt.consume();
//        }
    }//GEN-LAST:event_txtStockGroupNameKeyTyped

    private void txtStockGroupAliasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockGroupAliasKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && !Character.isDigit(c)) {
            //evt.consume();
        }
    }//GEN-LAST:event_txtStockGroupAliasKeyTyped

    private void ComboStockGroupUnderFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ComboStockGroupUnderFocusGained
        ComboStockGroupUnder.showPopup();
    }//GEN-LAST:event_ComboStockGroupUnderFocusGained

    private void btnStockGroupCreateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnStockGroupCreateKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnStockGroupCreateActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            ComboStockGroupUnder.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnStockGroupBack.requestFocus();
        }
    }//GEN-LAST:event_btnStockGroupCreateKeyPressed

    private void btnStockGroupBackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnStockGroupBackKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnStockGroupBackActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnStockGroupCreate.requestFocus();
        }
    }//GEN-LAST:event_btnStockGroupBackKeyPressed

    private void txtStockGroupNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStockGroupNameFocusGained
        // TODO add your handling code here:
        txtStockGroupName.selectAll();
    }//GEN-LAST:event_txtStockGroupNameFocusGained

    private void txtStockGroupAliasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStockGroupAliasFocusGained
        // TODO add your handling code here:
        txtStockGroupAlias.selectAll();
    }//GEN-LAST:event_txtStockGroupAliasFocusGained

    private void ComboStockGroupUnderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ComboStockGroupUnderFocusLost
        // TODO add your handling code here:
        ComboStockGroupUnder.hidePopup();
    }//GEN-LAST:event_ComboStockGroupUnderFocusLost
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboStockGroupUnder;
    private javax.swing.JPanel Stock_Group_Panel;
    private javax.swing.JButton btnStockGroupBack;
    private javax.swing.JButton btnStockGroupCreate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtStockGroupAlias;
    private javax.swing.JTextField txtStockGroupName;
    // End of variables declaration//GEN-END:variables
    private boolean hide_flag = false;

    private void setModel(DefaultComboBoxModel mdl, String str) {
        ComboStockGroupUnder.setModel(mdl);
        ComboStockGroupUnder.setSelectedIndex(-1);
        ComboStockGroupUnder.setPopupVisible(true);
        tf.setText(str);
        // tf.selectAll();
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

    private boolean checkElementPresence(Vector combo, String str) {
        for (int i = 0; i < combo.size(); i++) {
            if (tf.getText().trim().equals(ComboStockGroupUnder.getItemAt(i))) {
                return true;
            }
        }
        return false;
    }
}