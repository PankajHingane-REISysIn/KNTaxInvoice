package gen.feature.production;

import gen.database.connection.DatabaseConnection1;
import gen.mainclass.MainClass;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class ProductionToday extends javax.swing.JInternalFrame {

    String q = "";
    int i = 0;
    ResultSet rs1, rs2, rs3;
    JTextField tf;
    //private final JTextField tfDatePicker;

    public ProductionToday(String s) {
        setClosable(true);
        initComponents();
        dat.setDateFormatString("dd-MM-yyyy");
        this.setTitle(s);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProd = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        dat = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();

        setFrameIcon(new javax.swing.ImageIcon("E:\\Sudeep\\customer Copy\\07-01-2013\\General Copy\\images\\Symbol.jpg")); // NOI18N
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tableProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        )

        {
            public boolean isCellEditable(int row, int col)
            {
                return false;
            }
        }

    );
    tableProd.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    tableProd.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tableProdMouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(tableProd);

    jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
    jLabel1.setText("Today's Production");

    btnPrint.setText("Print");
    btnPrint.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnPrintActionPerformed(evt);
        }
    });
    btnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            btnPrintKeyPressed(evt);
        }
    });

    btnBack.setText("Back");
    btnBack.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnBackActionPerformed(evt);
        }
    });

    dat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

    jLabel2.setText("Date");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(310, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(dat, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(165, 165, 165)
                            .addComponent(btnBack)
                            .addGap(18, 18, 18)
                            .addComponent(btnPrint))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(152, 152, 152)
                            .addComponent(jLabel1))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel2)
                .addComponent(dat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnPrint)
                .addComponent(btnBack))
            .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(43, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        String dt = "";
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        String dateNow = f.format(currentDate.getTime());
        btnBack.setMnemonic(KeyEvent.VK_B);
        btnPrint.setMnemonic(KeyEvent.VK_I);
        Connection conn = null;
        try {
            String col[] = {"Prod.ID", "Finishing Item", "Quantity"};
            String data[][] = {{"", "", ""}};
            DefaultTableModel model = new DefaultTableModel(data, col);
            tableProd.setModel(model);

            model.setColumnCount(3);
            JTableHeader header = tableProd.getTableHeader();
            header.setBackground(Color.yellow);

            DefaultTableModel tableModel = (DefaultTableModel) tableProd.getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(3);

            tableProd.setRowSelectionAllowed(true);

            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            Statement st1 = conn.createStatement();
            Statement st2 = conn.createStatement();

            //Adding list of ledgers to Particulars
            q = "select Prod_id from tblProductionDetails where dat='" + dateNow + "'";
            rs1 = st.executeQuery(q);
            i = 0;
            while (rs1.next()) {
                long p_id = rs1.getInt("Prod_id");
                q = "select * from tblProductionItems where Prod_id=" + rs1.getInt("Prod_id") + " and type='Production'";
                rs2 = st1.executeQuery(q);
                if (rs2.next()) {
                    DefaultTableModel tableModel1 = (DefaultTableModel) tableProd.getModel();
                    i = tableProd.getRowCount();
                    tableModel1.setRowCount(tableProd.getRowCount() + 1);
                    tableProd.setValueAt(p_id, i, 0);
                    tableProd.setValueAt(rs2.getInt("qty"), i, 2);
                    q = "select si_name from tblStockItem where si_id=" + rs2.getInt("item") + "";
                    rs3 = st2.executeQuery(q);
                    if (rs3.next()) {
                        tableProd.setValueAt(rs3.getString("si_name"), i, 1);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddRawMaterial.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductionToday.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (tableProd.getRowCount() > 0) {
            i = 0;
            int qty = 0;
            while (i < tableProd.getRowCount()) {
                qty = qty + Integer.parseInt(tableProd.getValueAt(i, 2).toString());
                i++;
            }
            DefaultTableModel tableModel = (DefaultTableModel) tableProd.getModel();
            tableModel.setRowCount(tableProd.getRowCount() + 2);
            tableProd.setValueAt("Total", tableProd.getRowCount() - 1, 1);
            tableProd.setValueAt(qty, tableProd.getRowCount() - 1, 2);

        }
        dat.setDate(currentDate.getTime());
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        try {
            MainClass m = new MainClass();
            m.menuselection(5);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ProductionToday.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnPrintActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnBack.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnBack.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnBack.requestFocus();
        }
    }//GEN-LAST:event_btnPrintKeyPressed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrintActionPerformed

    private void tableProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProdMouseClicked
    }//GEN-LAST:event_tableProdMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnPrint;
    private com.toedter.calendar.JDateChooser dat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableProd;
    // End of variables declaration//GEN-END:variables
}