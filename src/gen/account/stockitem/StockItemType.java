package gen.account.stockitem;

//
import gen.database.connection.DatabaseConnection1;
import gen.mainclass.MainClass;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class StockItemType extends javax.swing.JInternalFrame {
    //Database db=new Database();

    public StockItemType(String s) {
        setClosable(true);
        initComponents();
        this.setTitle(s);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtTypeName = new javax.swing.JTextField();
        btnsubmit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

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

        txtTypeName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTypeNameKeyPressed(evt);
            }
        });

        btnsubmit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnsubmit.setText("Create");
        btnsubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsubmitActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Type");

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtTypeName, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(btnBack)
                        .addGap(18, 18, 18)
                        .addComponent(btnsubmit)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTypeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsubmit)
                    .addComponent(btnBack))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTypeNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTypeNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnsubmitActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnBackActionPerformed(null);
        }
}//GEN-LAST:event_txtTypeNameKeyPressed

    private void btnsubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsubmitActionPerformed
        if (txtTypeName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter Stock Item Type");
            txtTypeName.requestFocus();
        } else {
            Connection conn = null;
            try {
                String name = txtTypeName.getText().trim();
                conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();

                st.executeUpdate("insert into tblStockItemType(type_name)value('" + name + "')");

                JOptionPane.showMessageDialog(null, "Successfully Created");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            txtTypeName.setText("");
            txtTypeName.requestFocus();
        }
}//GEN-LAST:event_btnsubmitActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        try {
            MainClass m = new MainClass();
            m.menuselection(2);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(StockItemType.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnBackActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        btnBack.setMnemonic(KeyEvent.VK_B);
        btnsubmit.setMnemonic(KeyEvent.VK_C);
        txtTypeName.requestFocus();
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass m = new MainClass();
        m.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnsubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtTypeName;
    // End of variables declaration//GEN-END:variables
}
