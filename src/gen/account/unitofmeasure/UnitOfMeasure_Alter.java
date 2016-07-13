package gen.account.unitofmeasure;

import gen.database.connection.DatabaseConnection1;
import gen.mainclass.MainClass;
import java.awt.EventQueue;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class UnitOfMeasure_Alter extends javax.swing.JInternalFrame {
   // private Object[][] RowData;
    ArrayList<UOMData> arrayListUOM = new ArrayList<UOMData>();
    int RowId=0;
    JTextField tf;
    private final Vector<String> v = new Vector<String>();
    //ArrayList<String> GroupItems=new ArrayList<String>();
    
    public UnitOfMeasure_Alter(String s) 
    {
        setClosable(true);
        initComponents();
        unitofmeasuregrid.setRowSelectionAllowed(true);
        unitofmeasuregrid.setColumnSelectionAllowed(false);
        this.setTitle(s);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelUnitOfMeasureAlter1 = new javax.swing.JPanel();
        PanelUnitOfMeasureAlter2 = new javax.swing.JPanel();
        btnUnitOfMeasureAlter_Back = new javax.swing.JButton();
        txtUnitOfMeasureAlter_grid = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        unitofmeasuregrid = new javax.swing.JTable();
        PanelUnitOfMeasureAlter3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmbUnitOfMeasureAlter_Type = new javax.swing.JComboBox();
        txtUnitOfMeasureAlter_symbol = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUnitOfMeasureAlter_DecPlace = new javax.swing.JTextField();
        txtUnitOfMeasureAlter_forName = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png"))); // NOI18N
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

        btnUnitOfMeasureAlter_Back.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnUnitOfMeasureAlter_Back.setText("Back");
        btnUnitOfMeasureAlter_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnitOfMeasureAlter_BackActionPerformed(evt);
            }
        });
        btnUnitOfMeasureAlter_Back.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnUnitOfMeasureAlter_BackKeyPressed(evt);
            }
        });

        txtUnitOfMeasureAlter_grid.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtUnitOfMeasureAlter_grid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnitOfMeasureAlter_gridActionPerformed(evt);
            }
        });
        txtUnitOfMeasureAlter_grid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUnitOfMeasureAlter_gridFocusGained(evt);
            }
        });
        txtUnitOfMeasureAlter_grid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUnitOfMeasureAlter_gridKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUnitOfMeasureAlter_gridKeyReleased(evt);
            }
        });

        unitofmeasuregrid.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        unitofmeasuregrid.setModel(new javax.swing.table.DefaultTableModel(
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
        unitofmeasuregrid.setColumnSelectionAllowed(true);
        unitofmeasuregrid.setRowHeight(20);
        unitofmeasuregrid.setTableHeader(null);
        unitofmeasuregrid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                unitofmeasuregridMouseClicked(evt);
            }
        });
        unitofmeasuregrid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                unitofmeasuregridKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(unitofmeasuregrid);

        javax.swing.GroupLayout PanelUnitOfMeasureAlter2Layout = new javax.swing.GroupLayout(PanelUnitOfMeasureAlter2);
        PanelUnitOfMeasureAlter2.setLayout(PanelUnitOfMeasureAlter2Layout);
        PanelUnitOfMeasureAlter2Layout.setHorizontalGroup(
            PanelUnitOfMeasureAlter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelUnitOfMeasureAlter2Layout.createSequentialGroup()
                .addGroup(PanelUnitOfMeasureAlter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelUnitOfMeasureAlter2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtUnitOfMeasureAlter_grid))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelUnitOfMeasureAlter2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelUnitOfMeasureAlter2Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(btnUnitOfMeasureAlter_Back)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        PanelUnitOfMeasureAlter2Layout.setVerticalGroup(
            PanelUnitOfMeasureAlter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelUnitOfMeasureAlter2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtUnitOfMeasureAlter_grid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUnitOfMeasureAlter_Back)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        PanelUnitOfMeasureAlter3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Type");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Symbol");

        cmbUnitOfMeasureAlter_Type.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cmbUnitOfMeasureAlter_Type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUnitOfMeasureAlter_TypeActionPerformed(evt);
            }
        });
        cmbUnitOfMeasureAlter_Type.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbUnitOfMeasureAlter_TypeFocusLost(evt);
            }
        });
        cmbUnitOfMeasureAlter_Type.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbUnitOfMeasureAlter_TypeKeyPressed(evt);
            }
        });

        txtUnitOfMeasureAlter_symbol.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtUnitOfMeasureAlter_symbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnitOfMeasureAlter_symbolActionPerformed(evt);
            }
        });
        txtUnitOfMeasureAlter_symbol.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUnitOfMeasureAlter_symbolFocusGained(evt);
            }
        });
        txtUnitOfMeasureAlter_symbol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUnitOfMeasureAlter_symbolKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUnitOfMeasureAlter_symbolKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Formal Name");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Number Of Decimal Places");

        txtUnitOfMeasureAlter_DecPlace.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtUnitOfMeasureAlter_DecPlace.setFocusTraversalPolicyProvider(true);
        txtUnitOfMeasureAlter_DecPlace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnitOfMeasureAlter_DecPlaceActionPerformed(evt);
            }
        });
        txtUnitOfMeasureAlter_DecPlace.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUnitOfMeasureAlter_DecPlaceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUnitOfMeasureAlter_DecPlaceKeyTyped(evt);
            }
        });

        txtUnitOfMeasureAlter_forName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtUnitOfMeasureAlter_forName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnitOfMeasureAlter_forNameActionPerformed(evt);
            }
        });
        txtUnitOfMeasureAlter_forName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUnitOfMeasureAlter_forNameFocusGained(evt);
            }
        });
        txtUnitOfMeasureAlter_forName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUnitOfMeasureAlter_forNameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUnitOfMeasureAlter_forNameKeyTyped(evt);
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

        javax.swing.GroupLayout PanelUnitOfMeasureAlter3Layout = new javax.swing.GroupLayout(PanelUnitOfMeasureAlter3);
        PanelUnitOfMeasureAlter3.setLayout(PanelUnitOfMeasureAlter3Layout);
        PanelUnitOfMeasureAlter3Layout.setHorizontalGroup(
            PanelUnitOfMeasureAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelUnitOfMeasureAlter3Layout.createSequentialGroup()
                .addGroup(PanelUnitOfMeasureAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelUnitOfMeasureAlter3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(PanelUnitOfMeasureAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(81, 81, 81))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelUnitOfMeasureAlter3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(PanelUnitOfMeasureAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtUnitOfMeasureAlter_symbol)
                    .addComponent(cmbUnitOfMeasureAlter_Type, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUnitOfMeasureAlter_forName, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUnitOfMeasureAlter_DecPlace, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelUnitOfMeasureAlter3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnUpdate)
                .addGap(132, 132, 132))
        );
        PanelUnitOfMeasureAlter3Layout.setVerticalGroup(
            PanelUnitOfMeasureAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelUnitOfMeasureAlter3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(PanelUnitOfMeasureAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbUnitOfMeasureAlter_Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(32, 32, 32)
                .addGroup(PanelUnitOfMeasureAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUnitOfMeasureAlter_symbol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(26, 26, 26)
                .addGroup(PanelUnitOfMeasureAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUnitOfMeasureAlter_forName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(30, 30, 30)
                .addGroup(PanelUnitOfMeasureAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUnitOfMeasureAlter_DecPlace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(btnUpdate)
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelUnitOfMeasureAlter1Layout = new javax.swing.GroupLayout(PanelUnitOfMeasureAlter1);
        PanelUnitOfMeasureAlter1.setLayout(PanelUnitOfMeasureAlter1Layout);
        PanelUnitOfMeasureAlter1Layout.setHorizontalGroup(
            PanelUnitOfMeasureAlter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelUnitOfMeasureAlter1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelUnitOfMeasureAlter2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelUnitOfMeasureAlter3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelUnitOfMeasureAlter1Layout.setVerticalGroup(
            PanelUnitOfMeasureAlter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelUnitOfMeasureAlter1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelUnitOfMeasureAlter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PanelUnitOfMeasureAlter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelUnitOfMeasureAlter3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelUnitOfMeasureAlter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(PanelUnitOfMeasureAlter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUnitOfMeasureAlter_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnitOfMeasureAlter_BackActionPerformed
        try {
            MainClass m=new MainClass();
            m.menuselection(2);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(UnitOfMeasure_Alter.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnUnitOfMeasureAlter_BackActionPerformed

    private void btnUnitOfMeasureAlter_BackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnUnitOfMeasureAlter_BackKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) {
            btnUnitOfMeasureAlter_Back.transferFocus();
        }
        
}//GEN-LAST:event_btnUnitOfMeasureAlter_BackKeyPressed

    private void txtUnitOfMeasureAlter_gridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureAlter_gridActionPerformed
        // TODO add your handling code here:
        unitofmeasuregrid.requestFocus();
}//GEN-LAST:event_txtUnitOfMeasureAlter_gridActionPerformed

    private void txtUnitOfMeasureAlter_gridKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureAlter_gridKeyReleased
        // TODO add your handling code here:
        
        try{
            DefaultTableModel d=(DefaultTableModel)unitofmeasuregrid.getModel();
            
            String text=txtUnitOfMeasureAlter_grid.getText().trim();
            int size = txtUnitOfMeasureAlter_grid.getText().trim().length();
            d.setRowCount(0);
            int i=0;
            for(i=0;i<arrayListUOM.size();i++) {
                String record=arrayListUOM.get(i).getName();
                int id=arrayListUOM.get(i).getId();
                if(record.length()>=size) {
                    String s=record.substring(0, size);
                    if(s.equalsIgnoreCase(text)) {
                        int rows=d.getRowCount();
                        rows++;
                        d.setRowCount(rows);
                        unitofmeasuregrid.setValueAt(record, rows-1, 0);
                        unitofmeasuregrid.setValueAt(id, rows-1, 1);
                    }
                    
                }
            }
        }catch(Exception e){e.printStackTrace();}
}//GEN-LAST:event_txtUnitOfMeasureAlter_gridKeyReleased

    private void unitofmeasuregridMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_unitofmeasuregridMouseClicked
        // TODO add your handling code here:
        cmbUnitOfMeasureAlter_Type.requestFocus();
        if(unitofmeasuregrid.isColumnSelected(0)) {
            
            int row=unitofmeasuregrid.getSelectedRow();
            
            RowId=Integer.parseInt(unitofmeasuregrid.getValueAt(row, 1).toString());
            
            txtUnitOfMeasureAlter_grid.setText("");
            
            String name=(String) unitofmeasuregrid.getValueAt(row, 0);
            
            txtUnitOfMeasureAlter_grid.setText(name);
            Connection conn=null;
            try{
                
                conn=DatabaseConnection1.GetConnection();
                Statement st=conn.createStatement();
                
                ResultSet rs=st.executeQuery("select * from tblunitofmeasure where uom_id="+RowId+"");
                if(rs.next()) {
                    
                    cmbUnitOfMeasureAlter_Type.setEnabled(true);
                    txtUnitOfMeasureAlter_symbol.setEnabled(true);
                    txtUnitOfMeasureAlter_DecPlace.setEnabled(true);
                    txtUnitOfMeasureAlter_forName.setEnabled(true);
                    btnUpdate.setEnabled(true);
                    
                    txtUnitOfMeasureAlter_symbol.setText(rs.getString(3));
                    txtUnitOfMeasureAlter_forName.setText(rs.getString(4));
                    txtUnitOfMeasureAlter_DecPlace.setText(rs.getString(5));
                    String uom_type=rs.getString("uom_type");
                    
                    int id=Integer.parseInt(rs.getString("uom_id"));
                    ResultSet rs1=st.executeQuery("select * from tbluomtype");
                    cmbUnitOfMeasureAlter_Type.removeAllItems();
                    cmbUnitOfMeasureAlter_Type.addItem("");
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                     /*Code Snippet added by Sudeep on 12-01-2013 At:01.15PM*/
                    
                     v.clear();
                    
                   cmbUnitOfMeasureAlter_Type.setEditable(true);
                   tf=(JTextField) cmbUnitOfMeasureAlter_Type.getEditor().getEditorComponent();
                    
                    
                    while(rs1.next()) 
                    {
                        v.add(rs1.getString("uomtype_name"));
                        
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
              int size=v.size(); 
              for(int i=0;i<size;i++)
              {
               cmbUnitOfMeasureAlter_Type.addItem(v.get(i));
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
                          cmbUnitOfMeasureAlter_Type.hidePopup();
                          setModel(new DefaultComboBoxModel(v), "");
                        }
                        else
                        {
                          DefaultComboBoxModel m = getSuggestedModel(v, text);
                          if(m.getSize()==0 || hide_flag)
                          {
                            cmbUnitOfMeasureAlter_Type.hidePopup();
                            hide_flag = false;
                          }
                          else
                          {
                            setModel(m, text);
                            cmbUnitOfMeasureAlter_Type.showPopup();
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
                         cmbUnitOfMeasureAlter_Type.setSelectedIndex(-1);
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
                             
                                if(cmbUnitOfMeasureAlter_Type.getSelectedItem().equals(""))
                               {
                               // JOptionPane.showMessageDialog(rootPane, "Enter data for Type Field");
                               // cmbUnitOfMeasureAlter_Type.requestFocus();
                                    txtUnitOfMeasureAlter_symbol.requestFocus();
                               }
                               else
                               {
                               txtUnitOfMeasureAlter_symbol.requestFocus();
                               }
                                //txtUnitOfMeasureAlter_symbol.requestFocus();
                            }
                            if(event.getKeyCode()==KeyEvent.VK_ESCAPE)
                            {
                             txtUnitOfMeasureAlter_grid.requestFocus();
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
                          cmbUnitOfMeasureAlter_Type.hidePopup();
                          setModel(new DefaultComboBoxModel(v), "");
                        }
                        else
                        {
                          DefaultComboBoxModel m = getSuggestedModel(v, text);
                          if(m.getSize()==0 || hide_flag)
                          {
                            cmbUnitOfMeasureAlter_Type.hidePopup();
                            hide_flag = false;
                          }
                          else
                          {
                            setModel(m, text);
                            cmbUnitOfMeasureAlter_Type.showPopup();
                          }
                        }
                        
                        tf.selectAll();
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                            //throw new UnsupportedOperationException("Not supported yet.");
                        }
                    });
             
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                    
                    cmbUnitOfMeasureAlter_Type.setSelectedItem(uom_type);
                    
                    
                }
                
            }catch(Exception e){e.printStackTrace();}finally{
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UnitOfMeasure_Alter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
}//GEN-LAST:event_unitofmeasuregridMouseClicked

    private void unitofmeasuregridKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unitofmeasuregridKeyPressed
        //-----------------------------------------------

//           if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            int r=unitofmeasuregrid.getSelectedRow();
//            unitofmeasuregridMouseClicked(null);
//             if(r>0){
//                 unitofmeasuregrid.changeSelection(r-1, 0, false, false);
//            }
//            else{
//                 unitofmeasuregrid.changeSelection(0, 0, false, false);
//            }
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
//            txtUnitOfMeasureAlter_grid.requestFocus();
//        }
        
        
                if (evt.getKeyCode()==KeyEvent.VK_ENTER) 
        {
            int r=unitofmeasuregrid.getSelectedRow();
            unitofmeasuregridMouseClicked(null);
            if(r>0){
                 unitofmeasuregrid.changeSelection(r-1, 0, false, false);
            }
            else{
                 unitofmeasuregrid.changeSelection(0, 0, false, false);
            }
        }
        if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) 
        {
            txtUnitOfMeasureAlter_grid.requestFocus();
        }
        else
        {
            int k=evt.getKeyCode();
            char c=evt.getKeyChar();
            if(k>=48 && k<=57)
            {
                txtUnitOfMeasureAlter_grid.setText(txtUnitOfMeasureAlter_grid.getText().trim()+""+c);
                String text = txtUnitOfMeasureAlter_grid.getText().trim();
                int size = text.length();
                DefaultTableModel model = new DefaultTableModel();
                unitofmeasuregrid.setModel(model);
                unitofmeasuregrid.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < arrayListUOM.size(); i++) {
                    String record = arrayListUOM.get(i).getName();
                    
                    if (record.length() >= size) 
                    {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) 
                        {
                            int r=model.getRowCount();
                            model.setRowCount(r+1);
                            model.setColumnCount(2);
                            unitofmeasuregrid.setValueAt(record, r, 0);
                            unitofmeasuregrid.setValueAt(arrayListUOM.get(i).getId(), r, 1);
                        }
                    }
                }
            }
            else if(k>=65 && k<=90)
            {
                txtUnitOfMeasureAlter_grid.setText(txtUnitOfMeasureAlter_grid.getText().trim()+""+c);
                String text = txtUnitOfMeasureAlter_grid.getText().trim();
                int size = text.length();
                DefaultTableModel model = new DefaultTableModel();
                unitofmeasuregrid.setModel(model);
                unitofmeasuregrid.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < arrayListUOM.size(); i++) {
                    String record = arrayListUOM.get(i).getName();
                    
                    if (record.length() >= size) 
                    {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) 
                        {
                            int r=model.getRowCount();
                            model.setRowCount(r+1);
                            model.setColumnCount(2);
                            unitofmeasuregrid.setValueAt(record, r, 0);
                            unitofmeasuregrid.setValueAt(arrayListUOM.get(i).getId(), r, 1);
                        }
                    }
                }
            }
            else if(k>=96 && k<=105)
            {
                txtUnitOfMeasureAlter_grid.setText(txtUnitOfMeasureAlter_grid.getText().trim()+""+c);
                String text = txtUnitOfMeasureAlter_grid.getText().trim();
                int size = text.length();
                DefaultTableModel model = new DefaultTableModel();
                unitofmeasuregrid.setModel(model);
                unitofmeasuregrid.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < arrayListUOM.size(); i++) {
                    String record = arrayListUOM.get(i).getName();
                    
                    if (record.length() >= size) 
                    {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) 
                        {
                            int r=model.getRowCount();
                            model.setRowCount(r+1);
                            model.setColumnCount(2);
                            unitofmeasuregrid.setValueAt(record, r, 0);
                            unitofmeasuregrid.setValueAt(arrayListUOM.get(i).getId(), r, 1);
                        }
                    }
                }
            }
            else if(k==8)
            {
                //txt.getText().trim().length()=txt.getText().trim().length()-1;
                String text = txtUnitOfMeasureAlter_grid.getText().trim();
                int size = text.length();
                String s1=text.substring(0, size-1);
                txtUnitOfMeasureAlter_grid.setText(s1);
                size = txtUnitOfMeasureAlter_grid.getText().trim().length();
                DefaultTableModel model = new DefaultTableModel();
                unitofmeasuregrid.setModel(model);
                unitofmeasuregrid.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < arrayListUOM.size(); i++)
                {
                    String record = arrayListUOM.get(i).getName();

                    if (record.length() >= size) 
                    {
                        String s2 = record.substring(0, size);
                        if (s2.equalsIgnoreCase(txtUnitOfMeasureAlter_grid.getText().trim())) 
                        {
                            int r=model.getRowCount();
                            model.setRowCount(r+1);
                            model.setColumnCount(2);
                            unitofmeasuregrid.setValueAt(record, r, 0);
                            unitofmeasuregrid.setValueAt(arrayListUOM.get(i).getId(), r, 1);
                        }
                    }
                }
            }
        }
        //------------------------------------------------
}//GEN-LAST:event_unitofmeasuregridKeyPressed

    private void cmbUnitOfMeasureAlter_TypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUnitOfMeasureAlter_TypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbUnitOfMeasureAlter_TypeActionPerformed

    private void cmbUnitOfMeasureAlter_TypeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbUnitOfMeasureAlter_TypeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            cmbUnitOfMeasureAlter_Type.transferFocus();
        }
        if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) {
            unitofmeasuregrid.requestFocus();
            unitofmeasuregrid.changeSelection(0, 0, false, false);
        }
        
}//GEN-LAST:event_cmbUnitOfMeasureAlter_TypeKeyPressed

    private void txtUnitOfMeasureAlter_symbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureAlter_symbolActionPerformed
        // TODO add your handling code here:
        txtUnitOfMeasureAlter_symbol.transferFocus();
}//GEN-LAST:event_txtUnitOfMeasureAlter_symbolActionPerformed

    private void txtUnitOfMeasureAlter_symbolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureAlter_symbolKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) {
            txtUnitOfMeasureAlter_symbol.transferFocusBackward();
        }
        
}//GEN-LAST:event_txtUnitOfMeasureAlter_symbolKeyPressed

    private void txtUnitOfMeasureAlter_symbolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureAlter_symbolKeyTyped
        // TODO add your handling code here:
}//GEN-LAST:event_txtUnitOfMeasureAlter_symbolKeyTyped

    private void txtUnitOfMeasureAlter_DecPlaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureAlter_DecPlaceActionPerformed
        // TODO add your handling code here:
        txtUnitOfMeasureAlter_DecPlace.transferFocus();
}//GEN-LAST:event_txtUnitOfMeasureAlter_DecPlaceActionPerformed

    private void txtUnitOfMeasureAlter_DecPlaceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureAlter_DecPlaceKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) {
            txtUnitOfMeasureAlter_DecPlace.transferFocusBackward();
        }
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            btnUpdateActionPerformed(null);
        }
}//GEN-LAST:event_txtUnitOfMeasureAlter_DecPlaceKeyPressed

    private void txtUnitOfMeasureAlter_DecPlaceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureAlter_DecPlaceKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!Character.isDigit(c))
            evt.consume();
}//GEN-LAST:event_txtUnitOfMeasureAlter_DecPlaceKeyTyped

    private void txtUnitOfMeasureAlter_forNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureAlter_forNameActionPerformed
        // TODO add your handling code here:
        txtUnitOfMeasureAlter_forName.transferFocus();
}//GEN-LAST:event_txtUnitOfMeasureAlter_forNameActionPerformed

    private void txtUnitOfMeasureAlter_forNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureAlter_forNameKeyPressed
        
        if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) {
            txtUnitOfMeasureAlter_forName.transferFocusBackward();
        }
       
}//GEN-LAST:event_txtUnitOfMeasureAlter_forNameKeyPressed

    private void txtUnitOfMeasureAlter_forNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureAlter_forNameKeyTyped
        // TODO add your handling code here:
}//GEN-LAST:event_txtUnitOfMeasureAlter_forNameKeyTyped

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        
        String name1=txtUnitOfMeasureAlter_forName.getText().trim();
        String name2=tf.getText().trim();
        if (cmbUnitOfMeasureAlter_Type.getSelectedItem()==null || cmbUnitOfMeasureAlter_Type.getSelectedItem()=="")// || tf.getText().trim().trim().equalsIgnoreCase("") || ComboGroupCreate_Under.getSelectedItem().toString().trim().equals("") ) 
        {
            System.out.println("eeeeeeeeeeeeeeeee");
            JOptionPane.showMessageDialog(this, "Select Data for Under Group Fields");
            cmbUnitOfMeasureAlter_Type.requestFocus();
        } 
//        else if(!name1.equals(txtUnitOfMeasureAlter_grid.getText().trim()))
//        {
//        JOptionPane.showMessageDialog(this, "Please select valid Unit of Measure");
//        txtUnitOfMeasureAlter_grid.requestFocus();
//        }
         else if((!name2.equals("Complex"))&&(!name2.equals("Simple")) )
        {
        JOptionPane.showMessageDialog(this, "Please select valid Type");
        cmbUnitOfMeasureAlter_Type.requestFocus();
        }
        else if(txtUnitOfMeasureAlter_symbol.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this,"Enter Data in Symbol Fields");
            txtUnitOfMeasureAlter_symbol.requestFocus();
        } else if(txtUnitOfMeasureAlter_forName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this,"Enter Data in Formal Name Fields");
            txtUnitOfMeasureAlter_forName.requestFocus();
        } else if(txtUnitOfMeasureAlter_DecPlace.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this,"Enter Data in Decimal Place Fields");
            txtUnitOfMeasureAlter_DecPlace.requestFocus();
        } else {
            Connection conn=null;
            try{
                
                String symbol=txtUnitOfMeasureAlter_symbol.getText().trim();
                String fName=txtUnitOfMeasureAlter_forName.getText().trim();
                String decplace=txtUnitOfMeasureAlter_DecPlace.getText().trim();
                String type=(String) cmbUnitOfMeasureAlter_Type.getSelectedItem();
                
                //String under=comboLedgerAlter_Under.getSelectedItem();
                
                
                 conn=DatabaseConnection1.GetConnection();
                 conn.setAutoCommit(false);
                Statement st=conn.createStatement();
                
                st.executeUpdate("UPDATE tblunitofmeasure SET uom_symbol='"+symbol+"',uom_formalname='"+fName+"',uom_noofdecimalpts='"+decplace+"',uom_type='"+type+"' where uom_id="+RowId+"");
                conn.commit();
                JOptionPane.showMessageDialog(this,"Record Updated Successfully");
                //arrayListUOM.clear();
                txtUnitOfMeasureAlter_symbol.setText("");
                txtUnitOfMeasureAlter_forName.setText("");
                txtUnitOfMeasureAlter_DecPlace.setText("");
                cmbUnitOfMeasureAlter_Type.setSelectedIndex(0);
                
                cmbUnitOfMeasureAlter_Type.setEnabled(false);
                txtUnitOfMeasureAlter_symbol.setEnabled(false);
                txtUnitOfMeasureAlter_DecPlace.setEnabled(false);
                txtUnitOfMeasureAlter_forName.setEnabled(false);
                btnUpdate.setEnabled(false);
                
                txtUnitOfMeasureAlter_grid.setText("");
                txtUnitOfMeasureAlter_grid.requestFocus();
                
                gridlist();
                
            } catch(Exception e) {
                e.printStackTrace();
            }finally{
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UnitOfMeasure_Alter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        tf.setText("");
        //btnUnitOfMeasureAlter_Create.transferFocus();
}//GEN-LAST:event_btnUpdateActionPerformed

    private void btnUpdateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnUpdateKeyPressed
        // TODO add your handling code here:
    /*    if (evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if(cmbUnitOfMeasureAlter_Type.getSelectedItem().equals(""))
            {
                JOptionPane.showMessageDialog(this,"Enter Data in Type Fields");
                cmbUnitOfMeasureAlter_Type.requestFocus();
            } 
            else if(txtUnitOfMeasureAlter_symbol.getText().trim().trim().equals(""))
            {
                JOptionPane.showMessageDialog(this,"Enter Data in Symbol Fields");
                txtUnitOfMeasureAlter_symbol.requestFocus();
            }
            else if(txtUnitOfMeasureAlter_forName.getText().trim().trim().equals(""))
            {
                JOptionPane.showMessageDialog(this,"Enter Data in Formal Name Fields");
                txtUnitOfMeasureAlter_forName.requestFocus();
            }
            else if(txtUnitOfMeasureAlter_DecPlace.getText().trim().trim().equals("")) 
            {
                JOptionPane.showMessageDialog(this,"Enter Data in Decimal Place Fields");
                txtUnitOfMeasureAlter_DecPlace.requestFocus();
            }
            else {
                Connection conn=null;
                try{
                    
                    String symbol=txtUnitOfMeasureAlter_symbol.getText().trim();
                    String fName=txtUnitOfMeasureAlter_forName.getText().trim();
                    String decplace=txtUnitOfMeasureAlter_DecPlace.getText().trim();
                    String type=(String) cmbUnitOfMeasureAlter_Type.getSelectedItem();
                    
                    //String under=comboLedgerAlter_Under.getSelectedItem();
                    
                    
                    conn=DatabaseConnection1.GetConnection();
                    Statement st=conn.createStatement();
                    
                    st.executeUpdate("UPDATE tblunitofmeasure SET uom_symbol='"+symbol+"',uom_formalname='"+fName+"',uom_noofdecimalpts='"+decplace+"',uom_type='"+type+"' where uom_id="+RowId+"");
                    JOptionPane.showMessageDialog(this,"Record Updated Successfully");
                    arrayListUOM.clear();
                    txtUnitOfMeasureAlter_symbol.setText("");
                    txtUnitOfMeasureAlter_forName.setText("");
                    txtUnitOfMeasureAlter_DecPlace.setText("");
                    cmbUnitOfMeasureAlter_Type.setSelectedIndex(0);
                    
                    txtUnitOfMeasureAlter_grid.setText("");
                    txtUnitOfMeasureAlter_grid.requestFocus();
                    
                    gridlist();
                } catch(Exception e) {
                    e.printStackTrace();
                }finally{
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(UnitOfMeasure_Alter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
            //btnUnitOfMeasureAlter_Create.transferFocus();
        } else if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) {
            txtUnitOfMeasureAlter_DecPlace.requestFocus();
        }*/
}//GEN-LAST:event_btnUpdateKeyPressed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        btnUpdate.setMnemonic(KeyEvent.VK_U);
        btnUnitOfMeasureAlter_Back.setMnemonic(KeyEvent.VK_B);
        cmbUnitOfMeasureAlter_Type.setEnabled(false);
        txtUnitOfMeasureAlter_symbol.setEnabled(false);
        txtUnitOfMeasureAlter_DecPlace.setEnabled(false);
        txtUnitOfMeasureAlter_forName.setEnabled(false);
        btnUpdate.setEnabled(false);
        gridlist();
        
        unitofmeasuregrid.getColumnModel().getColumn(1).setWidth(1);
        unitofmeasuregrid.getColumnModel().getColumn(1).setMinWidth(1);
        unitofmeasuregrid.getColumnModel().getColumn(1).setMaxWidth(1);
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void txtUnitOfMeasureAlter_gridKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureAlter_gridKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_DOWN)
        {
            if(unitofmeasuregrid.getRowCount()>0)
            {
                unitofmeasuregrid.requestFocus();
                unitofmeasuregrid.changeSelection(0, 0, false, false);
            }
        }
        if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) 
        {
            btnUnitOfMeasureAlter_BackActionPerformed(null);
        }
        if (evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if(unitofmeasuregrid.getRowCount()>0)
            {
                unitofmeasuregrid.requestFocus();
                unitofmeasuregrid.changeSelection(0, 0, false, false);
            }
        }
    }//GEN-LAST:event_txtUnitOfMeasureAlter_gridKeyPressed

    private void txtUnitOfMeasureAlter_gridFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureAlter_gridFocusGained
        // TODO add your handling code here:
        txtUnitOfMeasureAlter_grid.selectAll();
    }//GEN-LAST:event_txtUnitOfMeasureAlter_gridFocusGained

    private void txtUnitOfMeasureAlter_symbolFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureAlter_symbolFocusGained
        // TODO add your handling code here:
        txtUnitOfMeasureAlter_symbol.selectAll();
    }//GEN-LAST:event_txtUnitOfMeasureAlter_symbolFocusGained

    private void txtUnitOfMeasureAlter_forNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUnitOfMeasureAlter_forNameFocusGained
        // TODO add your handling code here:
        txtUnitOfMeasureAlter_forName.selectAll();
    }//GEN-LAST:event_txtUnitOfMeasureAlter_forNameFocusGained

    private void cmbUnitOfMeasureAlter_TypeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbUnitOfMeasureAlter_TypeFocusLost
        // TODO add your handling code here:
        cmbUnitOfMeasureAlter_Type.hidePopup();
    }//GEN-LAST:event_cmbUnitOfMeasureAlter_TypeFocusLost

    public void gridlist()
    {
        Connection conn=null;
        try
        {
                conn=DatabaseConnection1.GetConnection();
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("select uom_formalname,uom_id from tblunitofmeasure order by uom_formalname");  
                int i=0;
               arrayListUOM.clear();
                while(rs.next())
                {                                     
                    UOMData gr1=new UOMData();
                    gr1.setId(rs.getInt("uom_id"));
                    gr1.setName(rs.getString("uom_formalname"));
                    arrayListUOM.add(gr1);
                   
                }
                i=0;
                 unitofmeasuregrid.removeAll();
       try{
        DefaultTableModel d=(DefaultTableModel)unitofmeasuregrid.getModel();
        
        String text=txtUnitOfMeasureAlter_grid.getText().trim();
        int size = txtUnitOfMeasureAlter_grid.getText().trim().length();
        d.setRowCount(0);
       
        for(i=0;i<arrayListUOM.size();i++)
        {
             String record=arrayListUOM.get(i).getName();
             int id=arrayListUOM.get(i).getId();
            if(record.length()>=size)
            {
                
                    int rows=d.getRowCount();
                    rows++;
                    d.setRowCount(rows);
                    
                    unitofmeasuregrid.setValueAt(record, rows-1, 0);
                    unitofmeasuregrid.setValueAt(id, rows-1, 1);
             }
            
        }
         
}catch(Exception e){
    e.printStackTrace();
    
}
        }catch(Exception e){e.printStackTrace();}finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UnitOfMeasure_Alter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelUnitOfMeasureAlter1;
    private javax.swing.JPanel PanelUnitOfMeasureAlter2;
    private javax.swing.JPanel PanelUnitOfMeasureAlter3;
    private javax.swing.JButton btnUnitOfMeasureAlter_Back;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cmbUnitOfMeasureAlter_Type;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtUnitOfMeasureAlter_DecPlace;
    private javax.swing.JTextField txtUnitOfMeasureAlter_forName;
    private javax.swing.JTextField txtUnitOfMeasureAlter_grid;
    private javax.swing.JTextField txtUnitOfMeasureAlter_symbol;
    private javax.swing.JTable unitofmeasuregrid;
    // End of variables declaration//GEN-END:variables

     private boolean hide_flag = false;
       private void setModel(DefaultComboBoxModel mdl, String str) {
        cmbUnitOfMeasureAlter_Type.setModel(mdl);
        cmbUnitOfMeasureAlter_Type.setSelectedIndex(-1);
        cmbUnitOfMeasureAlter_Type.setPopupVisible(true);
        tf.setText(str);
       // tf.selectAll();
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


}