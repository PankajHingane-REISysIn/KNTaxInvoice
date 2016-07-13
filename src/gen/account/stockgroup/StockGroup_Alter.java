package gen.account.stockgroup;

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

public class StockGroup_Alter extends javax.swing.JInternalFrame {
    private Object[][] RowData;
    ArrayList<stockgroupdata> arrayListtockGroup = new ArrayList<stockgroupdata>();
   static ArrayList<String> MainGroups=new ArrayList<String>();
   JTextField tf;
    //ArrayList<String> GroupItems=new ArrayList<String>();
   private final Vector<String> v = new Vector<String>();
    long RowId=0;

    public StockGroup_Alter(String s) 
    {
        setClosable(true);
        initComponents();
        this.setTitle(s);
        stockgroupgrid.setRowSelectionAllowed(true);
        stockgroupgrid.setColumnSelectionAllowed(false);
          addMainGroups();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtStockGroupAlter_grid = new javax.swing.JTextField();
        btnStockGroupAlter_Back = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        stockgroupgrid = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtStockGroupAlter_Name = new javax.swing.JTextField();
        txtStockGroupAlter_Alias = new javax.swing.JTextField();
        cmbStockGroupAlter_Under = new javax.swing.JComboBox();
        btnStockGroupAlter_Update = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

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

        txtStockGroupAlter_grid.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtStockGroupAlter_grid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStockGroupAlter_gridFocusGained(evt);
            }
        });
        txtStockGroupAlter_grid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStockGroupAlter_gridKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStockGroupAlter_gridKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockGroupAlter_gridKeyTyped(evt);
            }
        });

        btnStockGroupAlter_Back.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnStockGroupAlter_Back.setText("Back");
        btnStockGroupAlter_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockGroupAlter_BackActionPerformed(evt);
            }
        });
        btnStockGroupAlter_Back.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnStockGroupAlter_BackKeyPressed(evt);
            }
        });

        stockgroupgrid.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        stockgroupgrid.setModel(new javax.swing.table.DefaultTableModel(
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
        stockgroupgrid.setColumnSelectionAllowed(true);
        stockgroupgrid.setRowHeight(20);
        stockgroupgrid.setTableHeader(null);
        stockgroupgrid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stockgroupgridMouseClicked(evt);
            }
        });
        stockgroupgrid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                stockgroupgridKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(stockgroupgrid);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtStockGroupAlter_grid))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(btnStockGroupAlter_Back)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtStockGroupAlter_grid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnStockGroupAlter_Back)
                .addGap(18, 18, 18))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Name");

        txtStockGroupAlter_Name.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtStockGroupAlter_Name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStockGroupAlter_NameFocusGained(evt);
            }
        });
        txtStockGroupAlter_Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStockGroupAlter_NameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockGroupAlter_NameKeyTyped(evt);
            }
        });

        txtStockGroupAlter_Alias.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtStockGroupAlter_Alias.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStockGroupAlter_AliasFocusGained(evt);
            }
        });
        txtStockGroupAlter_Alias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStockGroupAlter_AliasKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockGroupAlter_AliasKeyTyped(evt);
            }
        });

        cmbStockGroupAlter_Under.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cmbStockGroupAlter_Under.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbStockGroupAlter_UnderFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbStockGroupAlter_UnderFocusLost(evt);
            }
        });
        cmbStockGroupAlter_Under.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbStockGroupAlter_UnderKeyPressed(evt);
            }
        });

        btnStockGroupAlter_Update.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnStockGroupAlter_Update.setText("Update");
        btnStockGroupAlter_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockGroupAlter_UpdateActionPerformed(evt);
            }
        });
        btnStockGroupAlter_Update.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnStockGroupAlter_UpdateKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Under");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Alias");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbStockGroupAlter_Under, 0, 158, Short.MAX_VALUE)
                            .addComponent(txtStockGroupAlter_Name)
                            .addComponent(txtStockGroupAlter_Alias)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(btnStockGroupAlter_Update)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtStockGroupAlter_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtStockGroupAlter_Alias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cmbStockGroupAlter_Under, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnStockGroupAlter_Update)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtStockGroupAlter_gridKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockGroupAlter_gridKeyReleased
        // TODO add your handling code here:
        
        try{
            DefaultTableModel d=(DefaultTableModel)stockgroupgrid.getModel();
            
            String text=txtStockGroupAlter_grid.getText().trim();
            int size = txtStockGroupAlter_grid.getText().trim().length();
            d.setRowCount(0);
            int i=0;
            for(i=0;i<arrayListtockGroup.size();i++) {
                String record=arrayListtockGroup.get(i).getName();
                int id=arrayListtockGroup.get(i).getId();
                if(record.length()>=size && (!record.trim().equalsIgnoreCase("Primary"))) {
                    String s=record.substring(0, size);
                    
                    if(s.equalsIgnoreCase(text)) {
                        int rows=d.getRowCount();
                        rows++;
                        d.setRowCount(rows);
                        stockgroupgrid.setValueAt(record, rows-1, 0);
                        stockgroupgrid.setValueAt(id, rows-1, 1);
                    }
                    
                }
            }
        }catch(Exception e){System.out.println(e);}
}//GEN-LAST:event_txtStockGroupAlter_gridKeyReleased

    private void stockgroupgridMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stockgroupgridMouseClicked
        if(stockgroupgrid.getRowCount()>0) 
        {
            txtStockGroupAlter_Name.requestFocus();
            int row=stockgroupgrid.getSelectedRow();
            RowId=Integer.parseInt(stockgroupgrid.getValueAt(row, 1).toString());            
            txtStockGroupAlter_grid.setText("");
            String name=(String) stockgroupgrid.getValueAt(row, 0);
            txtStockGroupAlter_grid.setText(name);
            Connection conn=null;
            try
            {                
                conn=DatabaseConnection1.GetConnection();
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("select * from tblstockgroup where sg_id="+RowId+"");
                if(rs.next()) 
                {
                    txtStockGroupAlter_Name.setEnabled(true);
                    txtStockGroupAlter_Alias.setEnabled(true);
                    cmbStockGroupAlter_Under.setEnabled(true);
                    btnStockGroupAlter_Update.setEnabled(true);
                    
                    txtStockGroupAlter_Name.setText(rs.getString("sg_name"));
                    txtStockGroupAlter_Alias.setText(rs.getString("sg_alias"));
                    
                    long id=Long.parseLong(""+rs.getLong("sg_under"));
                    ResultSet rs1=st.executeQuery("select sg_name from tblstockgroup where sg_id="+ id +"");
                    if(rs1.next()) 
                    {
                        cmbStockGroupAlter_Under.setSelectedItem(rs1.getString("sg_name"));                        
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
                    Logger.getLogger(StockGroup_Alter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
}//GEN-LAST:event_stockgroupgridMouseClicked

    private void stockgroupgridKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stockgroupgridKeyPressed

        
        
//         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            int r=stockgroupgrid.getSelectedRow();
//            stockgroupgridMouseClicked(null);
//             if(r>0){
//                 stockgroupgrid.changeSelection(r-1, 0, false, false);
//            }
//            else{
//                 stockgroupgrid.changeSelection(0, 0, false, false);
//            }
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
//            txtStockGroupAlter_grid.requestFocus();
//        }
        
        
                if (evt.getKeyCode()==KeyEvent.VK_ENTER) 
        {
            int r=stockgroupgrid.getSelectedRow();
            
            stockgroupgridMouseClicked(null);
             if(r>0){
                 stockgroupgrid.changeSelection(r-1, 0, false, false);
            }
            else{
                stockgroupgrid.changeSelection(0, 0, false, false);
            }
            
        }
      else  if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) 
        {
            txtStockGroupAlter_grid.requestFocus();
        }
                else
        {
            int k=evt.getKeyCode();
            char c=evt.getKeyChar();
            if(k>=48 && k<=57)
            {
                txtStockGroupAlter_grid.setText(txtStockGroupAlter_grid.getText().trim()+""+c);
                String text = txtStockGroupAlter_grid.getText().trim();
                int size = text.length();
                DefaultTableModel defaultTableModel = new DefaultTableModel();
                stockgroupgrid.setModel(defaultTableModel);
                stockgroupgrid.setTableHeader(null);
                //defaultTableModel.setRowCount(0);
                for (int i = 0; i < arrayListtockGroup.size(); i++) {
                    String record = arrayListtockGroup.get(i).getName();
                    
                    if (record.length() >= size && (!record.trim().equalsIgnoreCase("Primary"))) 
                    {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) 
                        {
                            int r=defaultTableModel.getRowCount();
                            defaultTableModel.setRowCount(r+1);
                            defaultTableModel.setColumnCount(2);
                            stockgroupgrid.setValueAt(record, r, 0);
                            stockgroupgrid.setValueAt(arrayListtockGroup.get(i).getId(), r, 1);
                        }
                    }
                }
            }
            else if(k>=65 && k<=90)
            {
                txtStockGroupAlter_grid.setText(txtStockGroupAlter_grid.getText().trim()+""+c);
                String text = txtStockGroupAlter_grid.getText().trim();
                int size = text.length();
                DefaultTableModel defaultTableModel = new DefaultTableModel();
                stockgroupgrid.setModel(defaultTableModel);
                stockgroupgrid.setTableHeader(null);
                //defaultTableModel.setRowCount(0);
                for (int i = 0; i < arrayListtockGroup.size(); i++) {
                    String record = arrayListtockGroup.get(i).getName();
                    
                    if (record.length() >= size && (!record.trim().equalsIgnoreCase("Primary"))) 
                    {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) 
                        {
                            int r=defaultTableModel.getRowCount();
                            defaultTableModel.setRowCount(r+1);
                            defaultTableModel.setColumnCount(2);
                            stockgroupgrid.setValueAt(record, r, 0);
                            stockgroupgrid.setValueAt(arrayListtockGroup.get(i).getId(), r, 1);
                        }
                    }
                }
            }
            else if(k>=96 && k<=105)
            {
                txtStockGroupAlter_grid.setText(txtStockGroupAlter_grid.getText().trim()+""+c);
                String text = txtStockGroupAlter_grid.getText().trim();
                int size = text.length();
                DefaultTableModel model = new DefaultTableModel();
                stockgroupgrid.setModel(model);
                stockgroupgrid.setTableHeader(null);
                //defaultTableModel.setRowCount(0);
                for (int i = 0; i < arrayListtockGroup.size(); i++) {
                    String record = arrayListtockGroup.get(i).getName();
                    
                    if (record.length() >= size && (!record.trim().equalsIgnoreCase("Primary"))) 
                    {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) 
                        {
                            int r=model.getRowCount();
                            model.setRowCount(r+1);
                            model.setColumnCount(2);
                            stockgroupgrid.setValueAt(record, r, 0);
                            stockgroupgrid.setValueAt(arrayListtockGroup.get(i).getId(), r, 1);
                        }
                    }
                }
            }
            else if(k==8)
            {
                //txt.getText().length()=txt.getText().length()-1;
                if(txtStockGroupAlter_grid.getText().trim().equals("")==false)
                {
                String text = txtStockGroupAlter_grid.getText().trim();
                int size = text.length();
                String s1=text.substring(0, size-1);
                txtStockGroupAlter_grid.setText(s1);
                size = txtStockGroupAlter_grid.getText().trim().length();
                DefaultTableModel model = new DefaultTableModel();
                stockgroupgrid.setModel(model);
                stockgroupgrid.setTableHeader(null);
                //defaultTableModel.setRowCount(0);
                for (int i = 0; i < arrayListtockGroup.size(); i++)
                {
                    String record = arrayListtockGroup.get(i).getName();

                    if (record.length() >= size && (!record.trim().equalsIgnoreCase("Primary"))) 
                    {
                        String s2 = record.substring(0, size);
                        if (s2.equalsIgnoreCase(txtStockGroupAlter_grid.getText().trim())) 
                        {
                            int r=model.getRowCount();
                            model.setRowCount(r+1);
                            model.setColumnCount(2);
                            stockgroupgrid.setValueAt(record, r, 0);
                            stockgroupgrid.setValueAt(arrayListtockGroup.get(i).getId(), r, 1);
                        }
                    }
                }
                }
            }
        }

}//GEN-LAST:event_stockgroupgridKeyPressed

    private void txtStockGroupAlter_AliasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockGroupAlter_AliasKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) 
        {
            cmbStockGroupAlter_Under.requestFocus();
        }
        if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) 
        {
            txtStockGroupAlter_Name.requestFocus();
        }
}//GEN-LAST:event_txtStockGroupAlter_AliasKeyPressed

    private void cmbStockGroupAlter_UnderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbStockGroupAlter_UnderKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) 
        {
            btnStockGroupAlter_UpdateActionPerformed(null);
        }
        if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) 
        {
            txtStockGroupAlter_Alias.requestFocus();
        }
}//GEN-LAST:event_cmbStockGroupAlter_UnderKeyPressed

    private void btnStockGroupAlter_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockGroupAlter_UpdateActionPerformed
        String name1=txtStockGroupAlter_Name.getText().trim();
        if(txtStockGroupAlter_Name.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Enter Data in Name Fields");
            txtStockGroupAlter_Name.requestFocus();
        }
        else  if(txtStockGroupAlter_Name.getText().trim().equals(tf.getText().trim()))
        {
            JOptionPane.showMessageDialog(null,"Group name and Under can not be same");
            tf.setText("");
            tf.requestFocus();
        }
//        else if(!name1.equals(txtStockGroupAlter_grid.getText().trim()))
//        {
//         JOptionPane.showMessageDialog(null,"Please select a valid Group");
//         txtStockGroupAlter_grid.requestFocus();
//        }
        else if (cmbStockGroupAlter_Under.getSelectedItem()==null || cmbStockGroupAlter_Under.getSelectedItem()=="")// || tf.getText().trim().equalsIgnoreCase("") || ComboGroupCreate_Under.getSelectedItem().toString().trim().equals("") ) 
        {
            System.out.println("eeeeeeeeeeeeeeeee");
            JOptionPane.showMessageDialog(this, "Select Data for Under Group Fields");
            cmbStockGroupAlter_Under.requestFocus();
        } 
 ////////////// Atul Code //////////////////////////////////////////////////////////////////////////////////////       
       else if(!checkElementPresence(v, tf.getText().trim() )){
            JOptionPane.showMessageDialog(this, "Selected Value For Under Field is not valid");
        //    scrollpaneMain.getVerticalScrollBar().setValue(0);  //go to top of scrollpane
            
            cmbStockGroupAlter_Under.requestFocus();
        } 
 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////       
        else
        {
            int i=0;
            String nm="";
            while(i< arrayListtockGroup.size())
            {
                if( arrayListtockGroup.get(i).getId()==RowId)
                {
                    nm= arrayListtockGroup.get(i).getName();
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
                String name=txtStockGroupAlter_Name.getText().trim();
                String alias=txtStockGroupAlter_Alias.getText().trim();
                String under=(String) cmbStockGroupAlter_Under.getSelectedItem();

//                int flg=checkChildDirectory(under);
//                if(flg==0)
//                {
                try
                {
                Connection conn=DatabaseConnection1.GetConnection();
                conn.setAutoCommit(false);
                Statement st = conn.createStatement();



                ResultSet rs=st.executeQuery("select sg_id from tblstockgroup where sg_name='"+under+"'");
                if(rs.next())
                {
                   // String sql="update tblstockgroup set sg_alias='"+alias+"',sg_under="+rs.getLong("sg_id")+" where sg_id="+RowId+"";
                   // st.executeUpdate(sql);
                   
                     PreparedStatement ps;
                    String sql="update tblstockgroup set sg_alias=?,sg_under=? where sg_id=?";
                  
                    ps=conn.prepareStatement(sql);
                    ps.setString(1, alias);
                    ps.setLong(2, rs.getLong("sg_id"));
                    ps.setLong(3, RowId);
                    
                    ps.executeUpdate();
                    
                    conn.commit();
                    JOptionPane.showMessageDialog(null, "Fields Updated Successfully except group name as it is predefined group");
                }
                
                tf.setText("");
                stockgroupgrid.clearSelection();
                 arrayListtockGroup.clear();
                txtStockGroupAlter_Name.setText("");
                txtStockGroupAlter_Alias.setText("");
                cmbStockGroupAlter_Under.setSelectedIndex(0);
                txtStockGroupAlter_grid.setText("");
                txtStockGroupAlter_grid.requestFocus();
             //   gridlist();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
//                }
//                else
//                {
//                    JOptionPane.showMessageDialog(this,"You have selected Parent group which in child directory of current group. Please change it.");
//                    cmbStockGroupAlter_Under.requestFocus();
//                }
            }
            else
            {
                String name=txtStockGroupAlter_Name.getText().trim();
                String alias=txtStockGroupAlter_Alias.getText().trim();
                String under=(String) cmbStockGroupAlter_Under.getSelectedItem();
//                int flg=checkChildDirectory(under.toString());
//                if(flg==0)
//                {

            try
            {
                Connection conn=DatabaseConnection1.GetConnection();
                conn.setAutoCommit(false);
                Statement st = conn.createStatement();


                ResultSet rs=st.executeQuery("select sg_id from tblstockgroup where sg_name='"+under+"'");
                if(rs.next())
                {
                    String sql="update tblstockgroup set sg_name='"+name+"',sg_alias='"+alias+"',sg_under="+rs.getLong("sg_id")+" where sg_id="+RowId+"";
                    st.executeUpdate(sql);
                    conn.commit();
                     JOptionPane.showMessageDialog(null,"Record Updated Successfully");
                }
               
               arrayListtockGroup.clear();
                tf.setText("");
                txtStockGroupAlter_Name.setText("");
                txtStockGroupAlter_Alias.setText("");
                cmbStockGroupAlter_Under.setSelectedIndex(0);
                txtStockGroupAlter_grid.setText("");
                txtStockGroupAlter_grid.requestFocus();
              // gridlist();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
//                }
//                else
//                {
//                    JOptionPane.showMessageDialog(this,"You have selected Parent group which in child directory of current group. Please change it.");
//                    cmbStockGroupAlter_Under.requestFocus();
//                }
            }
        }
        gridlist();
        stockgroupgrid.getColumnModel().getColumn(1).setWidth(1);
        stockgroupgrid.getColumnModel().getColumn(1).setMinWidth(1);
        stockgroupgrid.getColumnModel().getColumn(1).setMaxWidth(1);
    }//GEN-LAST:event_btnStockGroupAlter_UpdateActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        btnStockGroupAlter_Update.setMnemonic(KeyEvent.VK_U);
        btnStockGroupAlter_Back.setMnemonic(KeyEvent.VK_B);
        
        txtStockGroupAlter_Name.setEnabled(false);
        txtStockGroupAlter_Alias.setEnabled(false);
        cmbStockGroupAlter_Under.setEnabled(false);
        btnStockGroupAlter_Update.setEnabled(false);
        
        gridlist();
        
        stockgroupgrid.getColumnModel().getColumn(1).setWidth(1);
        stockgroupgrid.getColumnModel().getColumn(1).setMinWidth(1);
        stockgroupgrid.getColumnModel().getColumn(1).setMaxWidth(1);
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
       MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnStockGroupAlter_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockGroupAlter_BackActionPerformed
        try {
            MainClass m=new MainClass();
            m.menuselection(2);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(StockGroup_Alter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnStockGroupAlter_BackActionPerformed

    private void txtStockGroupAlter_gridKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockGroupAlter_gridKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_DOWN)
        {
            if(stockgroupgrid.getRowCount()>0)
            {
                stockgroupgrid.requestFocus();
                stockgroupgrid.changeSelection(0, 0, false, false);
            }
        }
        if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) 
        {
            btnStockGroupAlter_BackActionPerformed(null);
        }
        if (evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if(stockgroupgrid.getRowCount()>0)
            {
                stockgroupgrid.requestFocus();
                stockgroupgrid.changeSelection(0, 0, false, false);
            }
        }
    }//GEN-LAST:event_txtStockGroupAlter_gridKeyPressed

    private void txtStockGroupAlter_NameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockGroupAlter_NameKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) 
        {
            txtStockGroupAlter_Alias.requestFocus();
        }
        if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) 
        {
            txtStockGroupAlter_grid.requestFocus();
        }
    }//GEN-LAST:event_txtStockGroupAlter_NameKeyPressed

    private void cmbStockGroupAlter_UnderFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbStockGroupAlter_UnderFocusGained
        cmbStockGroupAlter_Under.showPopup();
    }//GEN-LAST:event_cmbStockGroupAlter_UnderFocusGained

    private void txtStockGroupAlter_gridKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockGroupAlter_gridKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && !Character.isDigit(c)) 
        {
            //evt.consume();
        }
    }//GEN-LAST:event_txtStockGroupAlter_gridKeyTyped

    private void txtStockGroupAlter_NameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockGroupAlter_NameKeyTyped
//        char c = evt.getKeyChar();
//        if (!Character.isLetter(c) && !Character.isDigit(c)) 
//        {
//            //evt.consume();
//        }
    }//GEN-LAST:event_txtStockGroupAlter_NameKeyTyped

    private void txtStockGroupAlter_AliasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockGroupAlter_AliasKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && !Character.isDigit(c)) 
        {
            //evt.consume();
        }
    }//GEN-LAST:event_txtStockGroupAlter_AliasKeyTyped

    private void btnStockGroupAlter_UpdateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnStockGroupAlter_UpdateKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)
       {
         //txtStockGroupAlter_grid.requestFocus();
           tf.requestFocus();
       }
       else if(evt.getKeyCode()==KeyEvent.VK_ENTER)
       {
       
    btnStockGroupAlter_UpdateActionPerformed(null);
       }
    }//GEN-LAST:event_btnStockGroupAlter_UpdateKeyPressed

    private void btnStockGroupAlter_BackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnStockGroupAlter_BackKeyPressed
       
       if(evt.getKeyCode()==KeyEvent.VK_ENTER)
       {
        btnStockGroupAlter_BackActionPerformed(null);
       }
    }//GEN-LAST:event_btnStockGroupAlter_BackKeyPressed

    private void txtStockGroupAlter_gridFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStockGroupAlter_gridFocusGained
        // TODO add your handling code here:
        txtStockGroupAlter_grid.selectAll();
    }//GEN-LAST:event_txtStockGroupAlter_gridFocusGained

    private void txtStockGroupAlter_NameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStockGroupAlter_NameFocusGained
        // TODO add your handling code here:
        txtStockGroupAlter_Name.selectAll();
    }//GEN-LAST:event_txtStockGroupAlter_NameFocusGained

    private void txtStockGroupAlter_AliasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStockGroupAlter_AliasFocusGained
        // TODO add your handling code here:
        txtStockGroupAlter_Alias.selectAll();
    }//GEN-LAST:event_txtStockGroupAlter_AliasFocusGained

    private void cmbStockGroupAlter_UnderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbStockGroupAlter_UnderFocusLost
        // TODO add your handling code here:
        cmbStockGroupAlter_Under.hidePopup();
    }//GEN-LAST:event_cmbStockGroupAlter_UnderFocusLost

    public void gridlist() 
    {
        Connection conn=null;
        try 
        {
             conn= DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select sg_name,sg_id from tblstockgroup order by sg_name");
            int i = 0;
            arrayListtockGroup.clear();
            cmbStockGroupAlter_Under.removeAllItems();
            cmbStockGroupAlter_Under.addItem("");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
             cmbStockGroupAlter_Under.setEditable(true);
             tf=(JTextField) cmbStockGroupAlter_Under.getEditor().getEditorComponent();
         //   final ArrayList<String> GroupItems1 = GroupItems;
            v.clear();
            
            while(rs.next())
            {
                stockgroupdata gr1 = new stockgroupdata();
                gr1.setId(rs.getInt("sg_id"));
                gr1.setName(rs.getString("sg_name"));
                arrayListtockGroup.add(gr1);
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
            for(int j=0;j<size1;j++)
              {
               cmbStockGroupAlter_Under.addItem(v.get(j));
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
                          cmbStockGroupAlter_Under.hidePopup();
                          setModel(new DefaultComboBoxModel(v), "");
                        }
                        else
                        {
                          DefaultComboBoxModel m = getSuggestedModel(v, text);
                          if(m.getSize()==0 || hide_flag)
                          {
                            cmbStockGroupAlter_Under.hidePopup();
                            hide_flag = false;
                          }
                          else
                          {
                            setModel(m, text);
                            cmbStockGroupAlter_Under.showPopup();
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
                         cmbStockGroupAlter_Under.setSelectedIndex(-1);
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
                             if(cmbStockGroupAlter_Under.getSelectedItem().equals(""))
                               {
                               // JOptionPane.showMessageDialog(rootPane, "Enter data for Under Field");
                              //  cmbStockGroupAlter_Under.requestFocus();
                                     btnStockGroupAlter_Update.requestFocus();  
                               }
                               else
                               {
                               btnStockGroupAlter_Update.requestFocus();
                               }
                                //btnStockGroupAlter_Update.requestFocus();
                            }
                            if(event.getKeyCode()==KeyEvent.VK_ESCAPE)
                            {
                             txtStockGroupAlter_Alias.requestFocus();
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
                          cmbStockGroupAlter_Under.hidePopup();
                          setModel(new DefaultComboBoxModel(v), "");
                        }
                        else
                        {
                          DefaultComboBoxModel m = getSuggestedModel(v, text);
                          if(m.getSize()==0 || hide_flag)
                          {
                            cmbStockGroupAlter_Under.hidePopup();
                            hide_flag = false;
                          }
                          else
                          {
                            setModel(m, text);
                            cmbStockGroupAlter_Under.showPopup();
                          }
                        }
                        
                        tf.selectAll();
                        
                }

                @Override
                public void focusLost(FocusEvent e) {
                   // throw new UnsupportedOperationException("Not supported yet.");
                }
            });
             
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
            i = 0;
            stockgroupgrid.removeAll();
            try
            {
                DefaultTableModel d = (DefaultTableModel) stockgroupgrid.getModel();
                String text = txtStockGroupAlter_grid.getText().trim();
                int size = txtStockGroupAlter_grid.getText().trim().length();
                
                d.setRowCount(0);

                for (i = 0; i < arrayListtockGroup.size(); i++)
                {
                    String record = arrayListtockGroup.get(i).getName();
                    
                    int id = arrayListtockGroup.get(i).getId();
                    if (record.length() >= size && (!record.trim().equalsIgnoreCase("Primary")))
                    {
                        int rows = d.getRowCount();
                        rows++;
                        d.setRowCount(rows);
                        d.setColumnCount(2);
                        stockgroupgrid.setValueAt(record, rows - 1, 0);
                        stockgroupgrid.setValueAt(id, rows - 1, 1);
                    }
                }
            }
            catch (Exception e) 
            {
                e.printStackTrace();                
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(StockGroup_Alter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }    
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
        ArrayList<Long> ids = new ArrayList<Long>();    //for child ids
        int i=0;
        long ug_id=0;
        while(i< arrayListtockGroup.size())
        {
            if( arrayListtockGroup.get(i).getName().equalsIgnoreCase(g))
            {
                ug_id= arrayListtockGroup.get(i).getId();
                break;
            }
            i++;
        }

        ids.add(RowId);
        int ind=0,flag=0;
        try
            {
                Connection conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();

                do
                {
                    String q="select sg_name,sg_id from tblstockgroup where sg_under="+ids.get(ind) +"";
                    ResultSet rs = st.executeQuery(q);
                    while(rs.next())
                    {
                        System.out.println("sg_name:"+rs.getString("sg_name"));
                        if(rs.getString("sg_name").equalsIgnoreCase("Primary")){
                            flag=1;
                            break;
                        }
                        ids.add(rs.getLong("sg_id"));
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
    private javax.swing.JButton btnStockGroupAlter_Back;
    private javax.swing.JButton btnStockGroupAlter_Update;
    private javax.swing.JComboBox cmbStockGroupAlter_Under;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable stockgroupgrid;
    private javax.swing.JTextField txtStockGroupAlter_Alias;
    private javax.swing.JTextField txtStockGroupAlter_Name;
    private javax.swing.JTextField txtStockGroupAlter_grid;
    // End of variables declaration//GEN-END:variables

 private boolean hide_flag = false;
       private void setModel(DefaultComboBoxModel mdl, String str) {
        cmbStockGroupAlter_Under.setModel(mdl);
        cmbStockGroupAlter_Under.setSelectedIndex(-1);
        cmbStockGroupAlter_Under.setPopupVisible(true);
        tf.setText(str);
      //  tf.selectAll();
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
           if(tf.getText().trim().equals(cmbStockGroupAlter_Under.getItemAt(i)))
           {
             return true;
           }
          }
       return false;
         }



}
