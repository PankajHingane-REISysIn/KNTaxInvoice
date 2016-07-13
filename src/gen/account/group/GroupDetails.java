package gen.account.group;

import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import gen.ImpExp.TagHelper2;
import gen.ImpExp.TagsHelper1;
import gen.database.connection.DatabaseConnection1;
import gen.dto.GroupDTO;
import gen.mainclass.MainClass;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class GroupDetails extends javax.swing.JInternalFrame {

    //GroupData groupData=new GroupData();
    ArrayList<GroupData> arrayListGroupData = new ArrayList<GroupData>();
    static long RowId = 0;
    static ArrayList<String> MainGroups = new ArrayList<String>();
    ArrayList<String> GroupItems = new ArrayList<String>();//
    ArrayList<GroupData> GroupNames = new ArrayList<GroupData>();

    public GroupDetails(String s) {

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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        labelName = new javax.swing.JLabel();
        labelAlias = new javax.swing.JLabel();
        labelUnder = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtGroupAlter_Group = new javax.swing.JTextField();
        btnGroupAlter_Back = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Groupgrid = new javax.swing.JTable();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(679, 519));
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

        panelEntry.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Name Of Group");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Alias");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Under Group Of");

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

        labelName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelName.setText("jLabel4");

        labelAlias.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelAlias.setText("jLabel4");

        labelUnder.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelUnder.setText("jLabel4");

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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

        javax.swing.GroupLayout panelEntryLayout = new javax.swing.GroupLayout(panelEntry);
        panelEntry.setLayout(panelEntryLayout);
        panelEntryLayout.setHorizontalGroup(
            panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEntryLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelName, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUnder, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
            .addGroup(panelEntryLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEntryLayout.setVerticalGroup(
            panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEntryLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelName))
                .addGap(39, 39, 39)
                .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labelAlias))
                .addGap(34, 34, 34)
                .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelUnder))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(panelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelete)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59))
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
        });

        btnGroupAlter_Back.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGroupAlter_Back.setText("Back");
        btnGroupAlter_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGroupAlter_BackActionPerformed(evt);
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(btnGroupAlter_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                            .addComponent(txtGroupAlter_Group, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(txtGroupAlter_Group, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(61, 61, 61)
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
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        String name = txtGroupAlter_Group.getText();
        if (labelName.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please select the Group");
            txtGroupAlter_Group.requestFocus();
        } else if (!labelName.getText().equals(name)) {
            JOptionPane.showMessageDialog(null, "Please select valid Group");
            txtGroupAlter_Group.requestFocus();
        } else {
            GroupAlterFromDetails g = new GroupAlterFromDetails(RowId, "Update Group Details");
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
            } catch (java.beans.PropertyVetoException e) {
            }
            BasicInternalFrameUI ui = (BasicInternalFrameUI) g.getUI();
            Component north = ui.getNorthPane();
            MouseMotionListener[] actions =
                    (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

            for (int i = 0; i < actions.length; i++) {
                north.removeMouseMotionListener(actions[i]);
            }
            btnGroupAlter_BackActionPerformed(evt);
        }
}//GEN-LAST:event_btnEditActionPerformed

    private void txtGroupAlter_GroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGroupAlter_GroupActionPerformed
        //    Groupgrid.requestFocus();
}//GEN-LAST:event_txtGroupAlter_GroupActionPerformed

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

    private void txtGroupAlter_GroupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGroupAlter_GroupKeyReleased
        try {
            DefaultTableModel defaultTableModel = (DefaultTableModel) Groupgrid.getModel();
            String text = txtGroupAlter_Group.getText();
            int size = txtGroupAlter_Group.getText().length();
            defaultTableModel.setRowCount(0);

            Groupgrid.getColumnModel().getColumn(1).setWidth(1);
            Groupgrid.getColumnModel().getColumn(1).setMinWidth(1);
            Groupgrid.getColumnModel().getColumn(1).setMaxWidth(1);

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

    private void btnGroupAlter_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGroupAlter_BackActionPerformed
        //  GroupDetails g=new GroupDetails("");
        try {
            this.setSelected(true);
            MainClass mainClass = new MainClass();
            mainClass.menuselection(1);
            this.setClosed(true);
        } catch (java.beans.PropertyVetoException e) {
        }

        //     try {
        //         MainClass mainClass = new MainClass();
        //        mainClass.menuselection(1);
        //        this.setClosed(true);
        //   } catch (PropertyVetoException ex) {
        //       Logger.getLogger(Group_Alter.class.getName()).log(Level.SEVERE, null, ex);
        //   }
}//GEN-LAST:event_btnGroupAlter_BackActionPerformed

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
                    panelEntry.setVisible(true);
                    labelName.setText(rs.getString("group_name"));
                    labelAlias.setText(rs.getString("group_alias"));

                    long under = rs.getLong("group_under");
                    ResultSet rs1 = st.executeQuery("select group_name from tblgroup where group_id=" + under + "");
                    if (rs1.next()) {
                        labelUnder.setText(rs1.getString("group_name"));
                    }
                    btnEdit.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Record is not Found");
                    txtGroupAlter_Group.requestFocus();
                }
            } catch (Exception e) {
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GroupDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
}//GEN-LAST:event_GroupgridMouseClicked

    private void GroupgridKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GroupgridKeyPressed

//         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
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

                defaultTableModel.setColumnCount(2);
                Groupgrid.getColumnModel().getColumn(1).setWidth(1);
                Groupgrid.getColumnModel().getColumn(1).setMinWidth(1);
                Groupgrid.getColumnModel().getColumn(1).setMaxWidth(1);

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
                            /////////////////////////////////////////////////////                 
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
                DefaultTableModel model = new DefaultTableModel();
                Groupgrid.setModel(model);

                model.setColumnCount(2);
                Groupgrid.getColumnModel().getColumn(1).setWidth(1);
                Groupgrid.getColumnModel().getColumn(1).setMinWidth(1);
                Groupgrid.getColumnModel().getColumn(1).setMaxWidth(1);

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

                            //////////////////////////////////////////////////////////////////
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
                DefaultTableModel defaultTableModel = new DefaultTableModel();
                Groupgrid.setModel(defaultTableModel);

                defaultTableModel.setColumnCount(2);
                Groupgrid.getColumnModel().getColumn(1).setWidth(1);
                Groupgrid.getColumnModel().getColumn(1).setMinWidth(1);
                Groupgrid.getColumnModel().getColumn(1).setMaxWidth(1);

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
                            ///////////////////////////////////////////////////////////////////  
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
                    DefaultTableModel defaultTableModel = new DefaultTableModel();
                    Groupgrid.setModel(defaultTableModel);

                    defaultTableModel.setColumnCount(2);
                    Groupgrid.getColumnModel().getColumn(1).setWidth(1);
                    Groupgrid.getColumnModel().getColumn(1).setMinWidth(1);
                    Groupgrid.getColumnModel().getColumn(1).setMaxWidth(1);

                    Groupgrid.setTableHeader(null);
                    //defaultTableModel.setRowCount(0);
                    for (int i = 0; i < arrayListGroupData.size(); i++) {
                        String record = arrayListGroupData.get(i).getName();

                        if (record.length() >= size) {
                            String s2 = record.substring(0, size);
                            if (s2.equalsIgnoreCase(txtGroupAlter_Group.getText())) {
                                int r = defaultTableModel.getRowCount();
                                defaultTableModel.setRowCount(r + 1);
                                defaultTableModel.setColumnCount(2);
                                Groupgrid.setValueAt(record, r, 0);
                                Groupgrid.setValueAt(arrayListGroupData.get(i).getId(), r, 1);
                                ////////////////////////////////////////////////////////////////
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
        //panelEntry.setVisible(false);

        System.out.println("Call Of Duty");

        gridlist();
        txtGroupAlter_Group.requestFocus();
        labelName.setText("");
        labelAlias.setText("");
        labelUnder.setText("");
        btnEdit.setMnemonic(KeyEvent.VK_E);
        btnGroupAlter_Back.setMnemonic(KeyEvent.VK_B);
        btnDelete.setMnemonic(KeyEvent.VK_D);


        Groupgrid.getColumnModel().getColumn(1).setWidth(1);
        Groupgrid.getColumnModel().getColumn(1).setMinWidth(1);
        Groupgrid.getColumnModel().getColumn(1).setMaxWidth(1);
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String name = labelName.getText();
        if (labelName.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please select the Group");
            txtGroupAlter_Group.requestFocus();
        } else if (!name.equals(txtGroupAlter_Group.getText())) {
            JOptionPane.showMessageDialog(null, "Seleceted Group does not exist");
            txtGroupAlter_Group.requestFocus();
        } else {
            int i = 0;
            int flag = 0;
            while (i < MainGroups.size()) {
                if (labelName.getText().equalsIgnoreCase(MainGroups.get(i))) {
                    flag = 1;
                    break;
                }
                i++;
            }





            if (flag == 1) {
                JOptionPane.showMessageDialog(null, "This is Predefined Group. You are not allowed to delete this group");
                txtGroupAlter_Group.requestFocus();
            } else {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want delete the record permanently ?", "Warning", dialogButton);
                if (dialogResult == 0) {


                    try {
                        Connection conn = DatabaseConnection1.GetConnection();
                        conn.setAutoCommit(false);
                        Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery("select group_id from tblgroup where group_under=" + RowId + "");
                        if (rs.next()) {
                            JOptionPane.showMessageDialog(null, "This group can not be deleted as it is used in other entries");
                            txtGroupAlter_Group.setText("");
                            //   gridlist();
                            txtGroupAlter_Group.requestFocus();
                        } else {
                            ResultSet rs1 = st.executeQuery("select ledger_id from tblledger where ledger_under=" + RowId + "");
                            if (rs1.next()) {
                                JOptionPane.showMessageDialog(null, "This group can not be deleted as it is used in other entries");
                                txtGroupAlter_Group.setText("");
                                //   gridlist();
                                txtGroupAlter_Group.requestFocus();
                            } else {
                                st.executeUpdate("delete from tblgroup where group_id=" + RowId + "");
                                conn.commit();
                                JOptionPane.showMessageDialog(null, "Group Is Deleted Permanently");
                                txtGroupAlter_Group.setText("");
                                formInternalFrameOpened(null);
                            }
                        }

                        /*     ResultSet rs2=st.executeQuery("select group_name from tblgroup");
                         while(rs2.next())
                         {
                         System.out.println(""+rs2.getString("group_name"));  
                         GroupItems.add(rs2.getString("group_name"));
                         }
                         Collections.sort(GroupItems);
                         for(String s:GroupItems)
                         {
                         System.out.println(s);
                         }
                
                         */ //Sudeep's code written om 8-01-2013.   :12.30PM   



                    } catch (Exception e) {
                        e.printStackTrace();
                        txtGroupAlter_Group.setText("");
                        txtGroupAlter_Group.requestFocus();
                        formInternalFrameOpened(null);
                    }


                }
//        else 
//        {
//         
//        }
            }
        }
//     String nameList=arrayListGroupData.toString();
//     Groupgrid.setValueAt(nameList, 0, 0);
        //formInternalFrameOpened(null);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtGroupAlter_Group.requestFocus();
            txtGroupAlter_Group.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnEditActionPerformed(null);

        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnEdit.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnDelete.requestFocus();

        }
    }//GEN-LAST:event_btnEditKeyPressed

    private void btnDeleteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDeleteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtGroupAlter_Group.requestFocus();
            txtGroupAlter_Group.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnDeleteActionPerformed(null);

        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnEdit.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnDelete.requestFocus();

        }
    }//GEN-LAST:event_btnDeleteKeyPressed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:

        gridlist();
        Groupgrid.getColumnModel().getColumn(1).setWidth(1);
        Groupgrid.getColumnModel().getColumn(1).setMinWidth(1);
        Groupgrid.getColumnModel().getColumn(1).setMaxWidth(1);
    }//GEN-LAST:event_formInternalFrameActivated

    private void txtGroupAlter_GroupFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGroupAlter_GroupFocusGained
        // TODO add your handling code here:
        txtGroupAlter_Group.selectAll();
    }//GEN-LAST:event_txtGroupAlter_GroupFocusGained

    public void gridlist() {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select group_name,group_id from tblgroup where group_name!='Primary' order by group_name");
            int i = 0;
            arrayListGroupData.clear();
            while (rs.next()) {
                GroupData gr1 = new GroupData();

                // GroupData gr2 = new GroupData();

                gr1.setId(rs.getInt("group_id"));
                gr1.setName(rs.getString("group_name"));

                // gr2.setName(rs.getString("group_name"));
                // GroupNames.add(gr2);

                arrayListGroupData.add(gr1);

                //Collections.sort(MainGroups);
            }

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            //  arrayListGroupData = (ArrayList<GroupData>)MainGroups.clone();

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////          
            //Collections.sort(arrayListGroupData);
            i = 0;
            Groupgrid.removeAll();
            Groupgrid.setTableHeader(null);
            try {
                DefaultTableModel d = (DefaultTableModel) Groupgrid.getModel();
                Groupgrid.setTableHeader(null);
                // String text = txtGroupAlter_Group.getText();
                int size = txtGroupAlter_Group.getText().length();
                d.setRowCount(0);

                d.setColumnCount(2);
                Groupgrid.getColumnModel().getColumn(1).setWidth(1);
                Groupgrid.getColumnModel().getColumn(1).setMinWidth(1);
                Groupgrid.getColumnModel().getColumn(1).setMaxWidth(1);

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
                Logger.getLogger(GroupDetails.class.getName()).log(Level.SEVERE, null, ex);
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

        Collections.sort(MainGroups);
        for (String s : MainGroups) {
            System.out.println(s);
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Groupgrid;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnGroupAlter_Back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAlias;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelUnder;
    private javax.swing.JPanel panelEntry;
    private javax.swing.JTextField txtGroupAlter_Group;
    // End of variables declaration//GEN-END:variables
}
