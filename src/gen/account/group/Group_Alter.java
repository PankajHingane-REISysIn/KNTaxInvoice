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
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Group_Alter extends javax.swing.JInternalFrame {

    GroupData groupData = new GroupData();
    ArrayList<GroupData> arrayListGroupData = new ArrayList<GroupData>();
    ArrayList<String> MainGroups = new ArrayList<String>();
    JTextField tf;
    private final Vector<String> v = new Vector<String>();
    //  ArrayList<String> GroupItems=new ArrayList<String>();
    long RowId = 0;

    public Group_Alter(String s) {
        setClosable(true);
        initComponents();
        this.setTitle(s);
        Groupgrid.setRowSelectionAllowed(true);
        Groupgrid.setColumnSelectionAllowed(false);
        addMainGroups();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        panelEntry = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtGroupAlter_Name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtGroupAlter_Alias = new javax.swing.JTextField();
        cmbGroupAlter_Under = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        btnGroupAlter_Update = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtGroupAlter_Group = new javax.swing.JTextField();
        btnGroupAlter_Back = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Groupgrid = new javax.swing.JTable();

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

        panelEntry.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Name Of Group"); // NOI18N

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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGroupAlter_NameKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Alias"); // NOI18N

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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGroupAlter_AliasKeyTyped(evt);
            }
        });

        cmbGroupAlter_Under.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Under Group Of"); // NOI18N

        btnGroupAlter_Update.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGroupAlter_Update.setText("Update"); // NOI18N
        btnGroupAlter_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGroupAlter_UpdateActionPerformed(evt);
            }
        });
        btnGroupAlter_Update.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnGroupAlter_UpdateKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout panelEntryLayout = new javax.swing.GroupLayout(panelEntry);
        panelEntry.setLayout(panelEntryLayout);
        panelEntryLayout.setHorizontalGroup(
            panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEntryLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(26, 26, 26)
                .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtGroupAlter_Name)
                    .addComponent(txtGroupAlter_Alias)
                    .addComponent(cmbGroupAlter_Under, 0, 174, Short.MAX_VALUE))
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEntryLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGroupAlter_Update)
                .addGap(145, 145, 145))
        );
        panelEntryLayout.setVerticalGroup(
            panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEntryLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtGroupAlter_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGroupAlter_Alias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(34, 34, 34)
                .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbGroupAlter_Under, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(btnGroupAlter_Update)
                .addGap(44, 44, 44))
        );

        txtGroupAlter_Group.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtGroupAlter_Group.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGroupAlter_GroupActionPerformed(evt);
            }
        });
        txtGroupAlter_Group.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGroupAlter_GroupFocusGained(evt);
            }
        });
        txtGroupAlter_Group.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGroupAlter_GroupKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGroupAlter_GroupKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGroupAlter_GroupKeyTyped(evt);
            }
        });

        btnGroupAlter_Back.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGroupAlter_Back.setText("Back"); // NOI18N
        btnGroupAlter_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGroupAlter_BackActionPerformed(evt);
            }
        });
        btnGroupAlter_Back.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnGroupAlter_BackKeyPressed(evt);
            }
        });

        Groupgrid.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Groupgrid.setModel(new javax.swing.table.DefaultTableModel(
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
        Groupgrid.setColumnSelectionAllowed(true);
        Groupgrid.setRowHeight(20);
        Groupgrid.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Groupgrid.setTableHeader(null);
        Groupgrid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GroupgridMouseClicked(evt);
            }
        });
        Groupgrid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GroupgridKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(Groupgrid);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(btnGroupAlter_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(txtGroupAlter_Group, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(txtGroupAlter_Group, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnGroupAlter_Back)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(panelEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(panelEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtGroupAlter_NameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGroupAlter_NameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtGroupAlter_Alias.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (Groupgrid.getRowCount() > 0) {
                Groupgrid.requestFocus();
                Groupgrid.changeSelection(0, 0, false, false);
            } else {
                txtGroupAlter_Group.requestFocus();
            }
        }
}//GEN-LAST:event_txtGroupAlter_NameKeyPressed

    private void txtGroupAlter_AliasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGroupAlter_AliasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbGroupAlter_Under.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtGroupAlter_Name.requestFocus();
        }
}//GEN-LAST:event_txtGroupAlter_AliasKeyPressed

    private void cmbGroupAlter_UnderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbGroupAlter_UnderKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtGroupAlter_Alias.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGroupAlter_UpdateActionPerformed(null);
        }
}//GEN-LAST:event_cmbGroupAlter_UnderKeyPressed

    private void btnGroupAlter_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGroupAlter_UpdateActionPerformed
        String name1 = txtGroupAlter_Group.getText();
        if (txtGroupAlter_Name.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter Group Name");
            txtGroupAlter_Name.requestFocus();
        } //        else if(!txtGroupAlter_Name.getText().equals(name1))
        //        {
        //         JOptionPane.showMessageDialog(null,"Enter Valid Group Name");
        //            txtGroupAlter_Group.requestFocus();
        //        }
        else if (cmbGroupAlter_Under.getSelectedItem() == null || cmbGroupAlter_Under.getSelectedItem() == "")// || tf.getText().trim().equalsIgnoreCase("") || ComboGroupCreate_Under.getSelectedItem().toString().trim().equals("") ) 
        {
            System.out.println("eeeeeeeeeeeeeeeee");
            JOptionPane.showMessageDialog(this, "Select Data for Under Group Fields");
            cmbGroupAlter_Under.requestFocus();
        } else if (!checkElementPresence(v, tf.getText())) {
            JOptionPane.showMessageDialog(this, "Selected Value For Under Field is not valid");
            //    scrollpaneMain.getVerticalScrollBar().setValue(0);  //go to top of scrollpane
            cmbGroupAlter_Under.requestFocus();
        } else {
            int i = 0;
            String nm = "";
            while (i < arrayListGroupData.size()) {
                if (arrayListGroupData.get(i).getId() == RowId) {
                    nm = arrayListGroupData.get(i).getName();
                    break;
                }
                i++;
            }

            i = 0;
            int flag = 0;
            while (i < MainGroups.size()) {
                if (nm.equalsIgnoreCase(MainGroups.get(i))) {
                    flag = 1;
                    break;
                }
                i++;
            }
            if (flag == 1) {
                String name = txtGroupAlter_Name.getText();
                String alias = txtGroupAlter_Alias.getText();
                Object under = cmbGroupAlter_Under.getSelectedItem();
                int flg = checkChildDirectory(under.toString());
                if (flg == 0) {
                    try {
                        Connection conn = DatabaseConnection1.GetConnection();
                        Statement st = conn.createStatement();

                        ResultSet rs1 = st.executeQuery("select group_id from tblgroup where group_name='" + under + "'");
                        if (rs1.next()) {
                            //  st.executeUpdate("UPDATE tblgroup SET  group_alias='" + alias.trim() + "',group_under=" + rs1.getInt("group_id") + " where group_id=" + RowId + "");

                            PreparedStatement ps;
                            String q = "UPDATE tblgroup SET group_alias=?,group_under=? where group_id=?";
                            ps = conn.prepareStatement(q);

                            ps.setString(1, alias.trim());
                            ps.setInt(2, rs1.getInt("group_id"));
                            ps.setLong(3, RowId);

                            ps.executeUpdate();
                        }
                        JOptionPane.showMessageDialog(null, "Fields Updated Successfully except group name as it is predefined group");
                        Groupgrid.clearSelection();

                        txtGroupAlter_Name.setText("");
                        txtGroupAlter_Alias.setText("");
                        cmbGroupAlter_Under.setSelectedIndex(-1);
                        txtGroupAlter_Group.setText("");
                        txtGroupAlter_Name.setEnabled(false);
                        txtGroupAlter_Alias.setEnabled(false);
                        cmbGroupAlter_Under.setEnabled(false);
                        btnGroupAlter_Update.setEnabled(false);
                        txtGroupAlter_Group.requestFocus();
                        conn.close();
                        //gridList();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "You have selected Parent group which in child directory of current group. Please change it.");
                    cmbGroupAlter_Under.requestFocus();
                }
            } else {
                String name = txtGroupAlter_Name.getText();
                String alias = txtGroupAlter_Alias.getText();
                Object under = cmbGroupAlter_Under.getSelectedItem();
                int flg = checkChildDirectory(under.toString());
                if (flg == 0) {
                    try {


                        Connection conn = DatabaseConnection1.GetConnection();
                        conn.setAutoCommit(false);
                        Statement st = conn.createStatement();

                        ResultSet rs1 = st.executeQuery("select group_id from tblgroup where group_name='" + under + "'");
                        if (rs1.next()) {
                            // st.executeUpdate("UPDATE tblgroup SET  group_name='" + name.trim() + "',group_alias='" + alias.trim() + "',group_under=" + rs1.getInt("group_id") + " where group_id=" + RowId + "");

                            PreparedStatement ps;
                            String q = "UPDATE tblgroup SET group_name=?,group_alias=?,group_under=? where group_id=?";
                            ps = conn.prepareStatement(q);

                            ps.setString(1, name.trim());
                            ps.setString(2, alias.trim());
                            ps.setInt(3, rs1.getInt("group_id"));
                            ps.setLong(4, RowId);

                            ps.executeUpdate();
                        }
                        conn.commit();
                        JOptionPane.showMessageDialog(null, "Record Updated Successfully");

                        Groupgrid.clearSelection();

                        txtGroupAlter_Name.setText("");
                        txtGroupAlter_Alias.setText("");
                        //   cmbGroupAlter_Under.setSelectedIndex(0);
                        cmbGroupAlter_Under.setSelectedItem("");
                        txtGroupAlter_Group.setText("");
                        txtGroupAlter_Name.setEnabled(false);
                        txtGroupAlter_Alias.setEnabled(false);
                        cmbGroupAlter_Under.setEnabled(false);
                        btnGroupAlter_Update.setEnabled(false);
                        txtGroupAlter_Group.requestFocus();
                        conn.close();
                        gridList();
                        Groupgrid.getColumnModel().getColumn(1).setWidth(1);
                        Groupgrid.getColumnModel().getColumn(1).setMinWidth(1);
                        Groupgrid.getColumnModel().getColumn(1).setMaxWidth(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "You have selected Parent group which in child directory of current group. Please change it.");
                    cmbGroupAlter_Under.requestFocus();
                }
            }
        }
        //  formInternalFrameOpened(null);

        gridList();
        Groupgrid.getColumnModel().getColumn(1).setWidth(1);
        Groupgrid.getColumnModel().getColumn(1).setMinWidth(1);
        Groupgrid.getColumnModel().getColumn(1).setMaxWidth(1);
}//GEN-LAST:event_btnGroupAlter_UpdateActionPerformed

    private void txtGroupAlter_GroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGroupAlter_GroupActionPerformed
//        Groupgrid.requestFocus();
}//GEN-LAST:event_txtGroupAlter_GroupActionPerformed

    private void txtGroupAlter_GroupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGroupAlter_GroupKeyReleased
        try {
            DefaultTableModel defaultTableModel = (DefaultTableModel) Groupgrid.getModel();
            String text = txtGroupAlter_Group.getText();
            int size = txtGroupAlter_Group.getText().length();
            defaultTableModel.setRowCount(0);

            for (int i = 0; i < arrayListGroupData.size(); i++) {
                String record = arrayListGroupData.get(i).getName();
                int id = arrayListGroupData.get(i).getId();

                if (record.length() >= size) {
                    String s = record.substring(0, size);
                    if (s.equalsIgnoreCase(text)) {
                        int rows = defaultTableModel.getRowCount();
                        rows++;
                        defaultTableModel.setRowCount(rows);

                        Groupgrid.setValueAt(record, rows - 1, 0);
                        Groupgrid.setValueAt(id, rows - 1, 1);
                    }
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtGroupAlter_GroupKeyReleased

    private void GroupgridMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GroupgridMouseClicked
        int row = 0;
        if (Groupgrid.getRowCount() > 0) {
            row = Groupgrid.getSelectedRow();
            RowId = Integer.parseInt(Groupgrid.getValueAt(row, 1).toString());

            txtGroupAlter_Group.setText("");
            String name = (String) Groupgrid.getValueAt(row, 0);
            txtGroupAlter_Group.setText(name);
            Connection conn = null;
            try {
                conn = DatabaseConnection1.GetConnection();

                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select group_name,group_alias,group_under from tblgroup where group_id=" + RowId + "");
                if (rs.next()) {
                    txtGroupAlter_Name.setEnabled(true);
                    txtGroupAlter_Alias.setEnabled(true);
                    cmbGroupAlter_Under.setEnabled(true);
                    btnGroupAlter_Update.setEnabled(true);
                    txtGroupAlter_Name.setText(rs.getString("group_name"));
                    txtGroupAlter_Alias.setText(rs.getString("group_alias"));

                    long under = rs.getLong("group_under");
                    ResultSet rs1 = st.executeQuery("select * from tblgroup");
                    cmbGroupAlter_Under.removeAllItems();
                    cmbGroupAlter_Under.setSelectedItem("");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                    
                    //Code snippet built by sudeep on 8-01-2013 : 6.25 P.M.
                    //   tf = (JTextField) cmbGroupAlter_Under.getEditor().getEditorComponent();
                    //  DefaultComboBoxModel m = new DefaultComboBoxModel();
                    cmbGroupAlter_Under.setEditable(true);
                    tf = (JTextField) cmbGroupAlter_Under.getEditor().getEditorComponent();


                    v.clear();
                    while (rs1.next()) {
                        v.add(rs1.getString("group_name"));

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
                        cmbGroupAlter_Under.addItem(v.get(i));
                    }

                    tf.addKeyListener(new KeyAdapter() {
                        public void keyTyped(final KeyEvent e) {
                            EventQueue.invokeLater(new Runnable() {
                                public void run() {
                                    String text = tf.getText();
                                    if (text.length() == 0) {
                                        cmbGroupAlter_Under.hidePopup();
                                        setModel(new DefaultComboBoxModel(v), "");
                                    } else {
                                        DefaultComboBoxModel m = getSuggestedModel(v, text);
                                        if (m.getSize() == 0 || hide_flag) {
                                            cmbGroupAlter_Under.hidePopup();
                                            hide_flag = false;
                                        } else {
                                            setModel(m, text);
                                            cmbGroupAlter_Under.showPopup();
                                        }
                                    }


                                    String text1 = tf.getText();
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
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    tf.addKeyListener(new KeyAdapter() {
                        public void keyPressed(final KeyEvent event) {
                            EventQueue.invokeLater(new Runnable() {
                                @Override
                                public void run() {


                                    if (event.getKeyCode() == KeyEvent.VK_ENTER) {
//                            if(cmbGroupAlter_Under.getSelectedItem().equals(""))
//                               {
//                                JOptionPane.showMessageDialog(rootPane, "Enter data for Under Group Of Field");
//                                cmbGroupAlter_Under.requestFocus();
//                               }
//                               else
//                               {
                                        btnGroupAlter_Update.requestFocus();
//                               }
                                    }
                                    if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
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
                            if (text.length() == 0) {
                                cmbGroupAlter_Under.hidePopup();
                                setModel(new DefaultComboBoxModel(v), "");
                            } else {
                                DefaultComboBoxModel m = getSuggestedModel(v, text);
                                if (m.getSize() == 0 || hide_flag) {
                                    cmbGroupAlter_Under.hidePopup();
                                    hide_flag = false;
                                } else {
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

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                    
                    rs1.first();
                    while (rs1.next()) {
                        if (Integer.parseInt(rs1.getString("group_id")) == under) {
                            cmbGroupAlter_Under.setSelectedItem(rs1.getString("group_name"));
                        }
                    }
                    txtGroupAlter_Name.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(this, "Record is not Found");
                    txtGroupAlter_Group.requestFocus();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Group_Alter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_GroupgridMouseClicked

    private void GroupgridKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GroupgridKeyPressed

//          if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            int r=Groupgrid.getSelectedRow();
//            GroupgridMouseClicked(null);
//             if(r>0){
//                 Groupgrid.changeSelection(r-1, 0, false, false);
//            }
//            else{
//                 Groupgrid.changeSelection(0, 0, false, false);
//            }
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
//            txtGroupAlter_Group.requestFocus();
//        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int r = Groupgrid.getSelectedRow();
            GroupgridMouseClicked(null);
            if (r > 0) {
                Groupgrid.changeSelection(r - 1, 0, false, false);
            } else {
                Groupgrid.changeSelection(0, 0, false, false);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtGroupAlter_Group.requestFocus();
        } else {
            int k = evt.getKeyCode();
            char c = evt.getKeyChar();
            if (k >= 48 && k <= 57) {
                txtGroupAlter_Group.setText(txtGroupAlter_Group.getText() + "" + c);
                String text = txtGroupAlter_Group.getText();
                int size = text.length();
                DefaultTableModel defaultTableModel = new DefaultTableModel();
                Groupgrid.setModel(defaultTableModel);
                Groupgrid.setTableHeader(null);
                //defaultTableModel.setRowCount(0);
                for (int i = 0; i < arrayListGroupData.size(); i++) {
                    String record = arrayListGroupData.get(i).getName();

                    if (record.length() >= size) {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) {
                            int r = defaultTableModel.getRowCount();
                            defaultTableModel.setRowCount(r + 1);
                            defaultTableModel.setColumnCount(2);
                            Groupgrid.setValueAt(record, r, 0);
                            Groupgrid.setValueAt(arrayListGroupData.get(i).getId(), r, 1);
                            /////////////////////////////////////////////////////////////
                            Groupgrid.getColumnModel().getColumn(1).setWidth(1);
                            Groupgrid.getColumnModel().getColumn(1).setMinWidth(1);
                            Groupgrid.getColumnModel().getColumn(1).setMaxWidth(1);
                        }
                    }
                }
            } else if (k >= 65 && k <= 90) {
                txtGroupAlter_Group.setText(txtGroupAlter_Group.getText() + "" + c);
                String text = txtGroupAlter_Group.getText();
                int size = text.length();
                DefaultTableModel defaultTableModel = new DefaultTableModel();
                Groupgrid.setModel(defaultTableModel);
                Groupgrid.setTableHeader(null);
                //defaultTableModel.setRowCount(0);
                for (int i = 0; i < arrayListGroupData.size(); i++) {
                    String record = arrayListGroupData.get(i).getName();

                    if (record.length() >= size) {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) {
                            int r = defaultTableModel.getRowCount();
                            defaultTableModel.setRowCount(r + 1);
                            defaultTableModel.setColumnCount(2);
                            Groupgrid.setValueAt(record, r, 0);
                            Groupgrid.setValueAt(arrayListGroupData.get(i).getId(), r, 1);
                            ///////////////////////////////////////////////////////////////
                            Groupgrid.getColumnModel().getColumn(1).setWidth(1);
                            Groupgrid.getColumnModel().getColumn(1).setMinWidth(1);
                            Groupgrid.getColumnModel().getColumn(1).setMaxWidth(1);
                        }
                    }
                }
            } else if (k >= 96 && k <= 105) {
                txtGroupAlter_Group.setText(txtGroupAlter_Group.getText() + "" + c);
                String text = txtGroupAlter_Group.getText();
                int size = text.length();
                DefaultTableModel model = new DefaultTableModel();
                Groupgrid.setModel(model);
                Groupgrid.setTableHeader(null);
                //defaultTableModel.setRowCount(0);
                for (int i = 0; i < arrayListGroupData.size(); i++) {
                    String record = arrayListGroupData.get(i).getName();

                    if (record.length() >= size) {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) {
                            int r = model.getRowCount();
                            model.setRowCount(r + 1);
                            model.setColumnCount(2);
                            Groupgrid.setValueAt(record, r, 0);
                            Groupgrid.setValueAt(arrayListGroupData.get(i).getId(), r, 1);
                            /////////////////////////////////////////////////////////////
                            Groupgrid.getColumnModel().getColumn(1).setWidth(1);
                            Groupgrid.getColumnModel().getColumn(1).setMinWidth(1);
                            Groupgrid.getColumnModel().getColumn(1).setMaxWidth(1);
                        }
                    }
                }
            } else if (k == 8) {
                //txt.getText().length()=txt.getText().length()-1;
                if (txtGroupAlter_Group.getText().equals("") == false) {
                    String text = txtGroupAlter_Group.getText();
                    int size = text.length();
                    String s1 = text.substring(0, size - 1);
                    txtGroupAlter_Group.setText(s1);
                    size = txtGroupAlter_Group.getText().length();
                    DefaultTableModel model = new DefaultTableModel();
                    Groupgrid.setModel(model);
                    Groupgrid.setTableHeader(null);
                    //defaultTableModel.setRowCount(0);
                    for (int i = 0; i < arrayListGroupData.size(); i++) {
                        String record = arrayListGroupData.get(i).getName();

                        if (record.length() >= size) {
                            String s2 = record.substring(0, size);
                            if (s2.equalsIgnoreCase(txtGroupAlter_Group.getText())) {
                                int r = model.getRowCount();
                                model.setRowCount(r + 1);
                                model.setColumnCount(2);

                                Groupgrid.setValueAt(record, r, 0);
                                Groupgrid.setValueAt(arrayListGroupData.get(i).getId(), r, 1);
                                /////////////////////////////////////////////////////////////////
                                Groupgrid.getColumnModel().getColumn(1).setWidth(1);
                                Groupgrid.getColumnModel().getColumn(1).setMinWidth(1);
                                Groupgrid.getColumnModel().getColumn(1).setMaxWidth(1);
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_GroupgridKeyPressed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        panelEntry.setEnabled(false);
        gridList();
        txtGroupAlter_Group.requestFocus();
        btnGroupAlter_Back.setMnemonic(KeyEvent.VK_B);
        btnGroupAlter_Update.setMnemonic(KeyEvent.VK_U);
        txtGroupAlter_Name.setEnabled(false);
        txtGroupAlter_Alias.setEnabled(false);
        cmbGroupAlter_Under.setEnabled(false);
        btnGroupAlter_Update.setEnabled(false);

        Groupgrid.getColumnModel().getColumn(1).setWidth(1);
        Groupgrid.getColumnModel().getColumn(1).setMinWidth(1);
        Groupgrid.getColumnModel().getColumn(1).setMaxWidth(1);
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnGroupAlter_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGroupAlter_BackActionPerformed
        try {
            MainClass mainClass = new MainClass();
            mainClass.menuselection(1);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Group_Alter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGroupAlter_BackActionPerformed

    private void txtGroupAlter_GroupKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGroupAlter_GroupKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (Groupgrid.getRowCount() > 0) {
                Groupgrid.requestFocus();
                Groupgrid.changeSelection(0, 0, false, false);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnGroupAlter_BackActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (Groupgrid.getRowCount() > 0) {
                Groupgrid.requestFocus();
                Groupgrid.changeSelection(0, 0, false, false);
            }
        }
    }//GEN-LAST:event_txtGroupAlter_GroupKeyPressed

    private void txtGroupAlter_GroupKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGroupAlter_GroupKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && !Character.isDigit(c)) {
            //evt.consume();
        }
    }//GEN-LAST:event_txtGroupAlter_GroupKeyTyped

    private void txtGroupAlter_NameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGroupAlter_NameKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && !Character.isDigit(c)) {
            //evt.consume();
        }
    }//GEN-LAST:event_txtGroupAlter_NameKeyTyped

    private void txtGroupAlter_AliasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGroupAlter_AliasKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && !Character.isDigit(c)) {
            //evt.consume();
        }
    }//GEN-LAST:event_txtGroupAlter_AliasKeyTyped

    private void btnGroupAlter_UpdateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGroupAlter_UpdateKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            // txtGroupAlter_Group.requestFocus();

            cmbGroupAlter_Under.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            btnGroupAlter_UpdateActionPerformed(null);
        }

    }//GEN-LAST:event_btnGroupAlter_UpdateKeyPressed

    private void btnGroupAlter_BackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGroupAlter_BackKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGroupAlter_BackActionPerformed(null);
        }
    }//GEN-LAST:event_btnGroupAlter_BackKeyPressed

    private void txtGroupAlter_GroupFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGroupAlter_GroupFocusGained
        // TODO add your handling code here:
        txtGroupAlter_Group.selectAll();

    }//GEN-LAST:event_txtGroupAlter_GroupFocusGained

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

    public void gridList() {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select group_name,group_id from tblgroup order by group_name");
            int i = 0;
            arrayListGroupData.clear();
            while (rs.next()) {
                GroupData gr1 = new GroupData();
                gr1.setId(rs.getInt("group_id"));
                gr1.setName(rs.getString("group_name"));
                arrayListGroupData.add(gr1);
            }
            i = 0;
            Groupgrid.removeAll();
            Groupgrid.setTableHeader(null);
            try {
                DefaultTableModel d = (DefaultTableModel) Groupgrid.getModel();
                Groupgrid.setTableHeader(null);
                // String text = txtGroupAlter_Group.getText();
                int size = txtGroupAlter_Group.getText().length();
                d.setRowCount(0);

                for (i = 0; i < arrayListGroupData.size(); i++) {
                    String record = arrayListGroupData.get(i).getName();
                    int id = arrayListGroupData.get(i).getId();
                    if (record.length() >= size) {
                        int rows = d.getRowCount();
                        rows++;
                        d.setRowCount(rows);
                        d.setColumnCount(2);
                        Groupgrid.setValueAt(record, rows - 1, 0);
                        Groupgrid.setValueAt(id, rows - 1, 1);
                    }
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Group_Alter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void addMainGroups() {


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

    public int checkChildDirectory(String g) {
        ArrayList<Long> ids = new ArrayList<Long>();    //for child ids
        int i = 0;
        long ug_id = 0;
        while (i < arrayListGroupData.size()) {
            if (arrayListGroupData.get(i).getName().equalsIgnoreCase(g)) {
                ug_id = arrayListGroupData.get(i).getId();
                break;
            }
            i++;
        }

        ids.add(RowId);
        int ind = 0, flag = 0;
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();

            do {
                String q = "select group_name,group_id from tblgroup where group_under=" + ids.get(ind) + "";
                ResultSet rs = st.executeQuery(q);
                while (rs.next()) {
                    if (rs.getString("group_name").equalsIgnoreCase("Primary")) {
                        flag = 1;
                        break;
                    }
                    ids.add(rs.getLong("group_id"));
                }
                if (ind == ids.size() - 1) {
                    flag = 1;
                }
                ind++;
            } while (flag == 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        i = 0;
        flag = 0;
        while (i < ids.size()) {
            if (ids.get(i) == ug_id) {
                flag = 1;
                break;
            }
            i++;
        }
        if (flag == 1) //if found in child list
        {
            return (1);
        }
        return (0);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Groupgrid;
    private javax.swing.JButton btnGroupAlter_Back;
    private javax.swing.JButton btnGroupAlter_Update;
    private javax.swing.JComboBox cmbGroupAlter_Under;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelEntry;
    private javax.swing.JTextField txtGroupAlter_Alias;
    private javax.swing.JTextField txtGroupAlter_Group;
    private javax.swing.JTextField txtGroupAlter_Name;
    // End of variables declaration//GEN-END:variables
    private boolean hide_flag = false;

    private void setModel(DefaultComboBoxModel mdl, String str) {
        cmbGroupAlter_Under.setModel(mdl);
        cmbGroupAlter_Under.setSelectedIndex(-1);
        cmbGroupAlter_Under.setPopupVisible(true);
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
            if (tf.getText().trim().equals(cmbGroupAlter_Under.getItemAt(i))) {
                return true;
            }
        }
        return false;
    }
}