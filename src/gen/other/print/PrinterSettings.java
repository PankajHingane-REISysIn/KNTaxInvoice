/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.other.print;

import gen.database.connection.DatabaseConnection1;
import gen.mainclass.MainClass;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Kasturi_NOvasoft
 */
public class PrinterSettings extends javax.swing.JInternalFrame {

    /**
     * Creates new form PrinterSettings
     */
    ResultSet rs;
    public static int flagDirectPrint = 1;
    public static int flagPrintPageSize = 1;
    public static int flagPrintPageFormat = 1;
    final String dir = System.getProperty("user.dir");
    Image img1 = new ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png")).getImage();
    ImageIcon img = new ImageIcon(img1);

    public PrinterSettings(String name) {



        initComponents();
        this.setTitle(name);
        try {
            //ImageIcon img=new ImageIcon("");
            this.setIcon(true);
            this.setIconifiable(true);
            this.setFrameIcon(this.getFrameIcon());
        } catch (Exception e) {
            System.out.println("" + e);
        }
        this.setFrameIcon(img);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        optYes = new javax.swing.JRadioButton();
        optNo = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        optPdf = new javax.swing.JRadioButton();
        optHtml = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        optExcel = new javax.swing.JRadioButton();
        optWord = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        optA4 = new javax.swing.JRadioButton();
        optA5 = new javax.swing.JRadioButton();
        optAsk = new javax.swing.JRadioButton();
        btnSave = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            @Override
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }

            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }

            @Override
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }

            @Override
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }

            @Override
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }

            @Override
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }

            @Override
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Show Print Preview"));

        optYes.setText("Yes");
        optYes.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optYesStateChanged(evt);
            }
        });
        optYes.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                optYesKeyPressed(evt);
            }
        });

        optNo.setText("No");
        optNo.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optNoStateChanged(evt);
            }
        });
        optNo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                optNoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(optYes)
                .addGap(43, 43, 43)
                .addComponent(optNo)
                .addContainerGap(61, Short.MAX_VALUE)));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(optYes)
                .addComponent(optNo))));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Print Display Format"));

        optPdf.setText("PDF");
        optPdf.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optPdfStateChanged(evt);
            }
        });
        optPdf.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                optPdfKeyPressed(evt);
            }
        });

        optHtml.setText("HTML");
        optHtml.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optHtmlStateChanged(evt);
            }
        });
        optHtml.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                optHtmlKeyPressed(evt);
            }
        });

        jRadioButton7.setText("jRadioButton7");

        optExcel.setText("Excel");
        optExcel.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optExcelStateChanged(evt);
            }
        });
        optExcel.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optExcelActionPerformed(evt);
            }
        });
        optExcel.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                optExcelKeyPressed(evt);
            }
        });

        optWord.setText("MS Word");
        optWord.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optWordStateChanged(evt);
            }
        });
        optWord.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                optWordKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(optPdf)
                .addGap(18, 18, 18)
                .addComponent(optHtml)
                .addGap(18, 18, 18)
                .addComponent(optWord)
                .addGap(18, 18, 18)
                .addComponent(optExcel)
                .addGap(110, 110, 110)
                .addComponent(jRadioButton7)
                .addGap(0, 0, Short.MAX_VALUE)));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jRadioButton7)
                .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(optPdf)
                .addComponent(optHtml)
                .addComponent(optWord)
                .addComponent(optExcel))
                .addContainerGap()));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Page Format"));

        optA4.setText("A4");
        optA4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optA4StateChanged(evt);
            }
        });
        optA4.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optA4ActionPerformed(evt);
            }
        });
        optA4.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                optA4KeyPressed(evt);
            }
        });

        optA5.setText("A5");
        optA5.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optA5StateChanged(evt);
            }
        });
        optA5.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                optA5KeyPressed(evt);
            }
        });

        optAsk.setText("Ask");
        optAsk.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optAskStateChanged(evt);
            }
        });
        optAsk.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                optAskKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(optA4)
                .addGap(18, 18, 18)
                .addComponent(optA5)
                .addGap(18, 18, 18)
                .addComponent(optAsk)
                .addContainerGap(36, Short.MAX_VALUE)));
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(optA4)
                .addComponent(optA5)
                .addComponent(optAsk))));

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            @Override
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
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(btnSave)
                .addGap(18, 18, 18)
                .addComponent(btnBack))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))))
                .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnSave)
                .addComponent(btnBack))
                .addContainerGap()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            String query = "select * from tblOtherSetting";
            rs = st.executeQuery(query);
            PrinterSettings.flagDirectPrint = 0;
            PrinterSettings.flagPrintPageSize = 0;
            PrinterSettings.flagPrintPageFormat = 0;
            if (optYes.isSelected()) {
                PrinterSettings.flagDirectPrint = 1;
            } else if (optNo.isSelected()) {
                PrinterSettings.flagDirectPrint = 0;
            }
            if (optPdf.isSelected()) {
                PrinterSettings.flagPrintPageFormat = 1;
            } else if (optHtml.isSelected()) {
                PrinterSettings.flagPrintPageFormat = 2;
            } else if (optWord.isSelected()) {
                PrinterSettings.flagPrintPageFormat = 3;
            } else if (optExcel.isSelected()) {
                PrinterSettings.flagPrintPageFormat = 4;
            }
            if (optA4.isSelected()) {
                PrinterSettings.flagPrintPageSize = 1;
            } else if (optA5.isSelected()) {
                PrinterSettings.flagPrintPageSize = 2;
            } else if (optAsk.isSelected()) {
                PrinterSettings.flagPrintPageSize = 3;
            }

            if (optYes.isSelected() == false && optNo.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Please Select Show Print Preview ");
                optYes.requestFocus();
            } else if (optPdf.isSelected() == false && optHtml.isSelected() == false && optWord.isSelected() == false && optExcel.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Please Select Print Display Format");
                optPdf.requestFocus();
            } else if (optA4.isSelected() == false && optA5.isSelected() == false && optAsk.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Please Select Page Format ");
                optA4.requestFocus();
            } else {
                if (rs.next()) {

                    query = "update tblOtherSetting set printpreview=" + PrinterSettings.flagDirectPrint + ",printPageFormat=" + PrinterSettings.flagPrintPageSize + ",printDisplayFormat=" + PrinterSettings.flagPrintPageFormat + "";
                } else {
                    query = "insert into tblOtherSetting(printpreview,printPageFormat,printDisplayFormat) values(" + PrinterSettings.flagDirectPrint + "," + PrinterSettings.flagPrintPageSize + "," + PrinterSettings.flagPrintPageFormat + ")";
                }
                st.executeUpdate(query);
                System.out.println("Operation Performed");
                JOptionPane.showMessageDialog(null, "Settings Saved Successfully");
                btnBackActionPerformed(evt);
            }
        } catch (Exception e) {
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(PrinterSettings.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        try {
            MainClass m = new MainClass();
            m.menuselection(5);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }//GEN-LAST:event_btnBackActionPerformed
    }

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
        MainClass.setstaticvar();
    }

    public static void printSettingsInitilise() {
        ResultSet rs2;
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            rs2 = st.executeQuery("select * from  tblOtherSetting");
            if (rs2.next()) {

                PrinterSettings.flagDirectPrint = rs2.getInt("printpreview");
                PrinterSettings.flagPrintPageFormat = rs2.getInt("printDisplayFormat");
                PrinterSettings.flagPrintPageSize = rs2.getInt("printPageFormat");
                System.out.println("PrinterSettings---------------->>printSettingsInitilise------->>printer variables: Direct print:" + PrinterSettings.flagDirectPrint + " Page Format:" + PrinterSettings.flagPrintPageFormat + " Page Size:" + PrinterSettings.flagPrintPageSize);
            }
        } catch (Exception e) {
            System.out.println("PrinterSettings----------------->>formInternalFrameClosed :---->>Exception :" + e);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(PrinterSettings.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        btnBack.setMnemonic(KeyEvent.VK_B);
        btnSave.setMnemonic(KeyEvent.VK_V);
        printSettingsInitilise();
        ResultSet rs2;
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            rs2 = st.executeQuery("select * from  tblOtherSetting");
            if (rs2.next()) {
                if (PrinterSettings.flagDirectPrint == 1) {
                    optYes.setSelected(true);
                    optNo.setSelected(false);
                } else if (PrinterSettings.flagDirectPrint == 0) {
                    optNo.setSelected(true);
                    optYes.setSelected(false);
                }

                if (PrinterSettings.flagPrintPageFormat == 1) {
                    optPdf.setSelected(true);
                    optHtml.setSelected(false);
                    optWord.setSelected(false);
                    optExcel.setSelected(false);
                } else if (PrinterSettings.flagPrintPageFormat == 2) {
                    optHtml.setSelected(true);
                    optPdf.setSelected(false);
                    optWord.setSelected(false);
                    optExcel.setSelected(false);
                } else if (PrinterSettings.flagPrintPageFormat == 3) {
                    optWord.setSelected(true);
                    optPdf.setSelected(false);
                    optHtml.setSelected(false);
                    optExcel.setSelected(false);
                } else if (PrinterSettings.flagPrintPageFormat == 4) {
                    optExcel.setSelected(true);
                    optPdf.setSelected(false);
                    optHtml.setSelected(false);
                    optWord.setSelected(false);
                }
                if (PrinterSettings.flagPrintPageSize == 1) {
                    optA4.setSelected(true);
                    optA5.setSelected(false);
                    optAsk.setSelected(false);
                } else if (PrinterSettings.flagPrintPageSize == 2) {
                    optA5.setSelected(true);
                    optA4.setSelected(false);
                    optAsk.setSelected(false);

                } else if (PrinterSettings.flagPrintPageSize == 3) {
                    optAsk.setSelected(true);
                    optA5.setSelected(false);
                    optA4.setSelected(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(PrinterSettings.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_formInternalFrameOpened

    private void optYesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optYesStateChanged
        if (optYes.isSelected()) {
            optNo.setSelected(false);
        }
    }//GEN-LAST:event_optYesStateChanged

    private void optNoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optNoStateChanged
        if (optNo.isSelected()) {
            optYes.setSelected(false);
        }//GEN-LAST:event_optNoStateChanged
    }

    private void optPdfStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optPdfStateChanged
        if (optPdf.isSelected()) {
            optHtml.setSelected(false);
            optWord.setSelected(false);
            optExcel.setSelected(false);
        }
    }//GEN-LAST:event_optPdfStateChanged

    private void optHtmlStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optHtmlStateChanged
        if (optHtml.isSelected()) {
            optPdf.setSelected(false);
            optWord.setSelected(false);
            optExcel.setSelected(false);
        }//GEN-LAST:event_optHtmlStateChanged
    }

    private void optWordStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optWordStateChanged
        if (optWord.isSelected()) {
            optHtml.setSelected(false);
            optPdf.setSelected(false);
            optExcel.setSelected(false);
        }
    }//GEN-LAST:event_optWordStateChanged

    private void optExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optExcelActionPerformed
    }//GEN-LAST:event_optExcelActionPerformed

    private void optA4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optA4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optA4ActionPerformed

    private void optA4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optA4StateChanged
        if (optA4.isSelected()) {
            optA5.setSelected(false);
            optAsk.setSelected(false);
        }
    }//GEN-LAST:event_optA4StateChanged

    private void optA5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optA5StateChanged
        if (optA5.isSelected()) {
            optA4.setSelected(false);
            optAsk.setSelected(false);
        }
    }//GEN-LAST:event_optA5StateChanged

    private void optAskStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optAskStateChanged
        if (optAsk.isSelected()) {
            optA5.setSelected(false);
            optA4.setSelected(false);
        }
    }//GEN-LAST:event_optAskStateChanged

    private void optYesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optYesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            btnBackActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            optNo.requestFocus();
        }
    }//GEN-LAST:event_optYesKeyPressed

    private void optNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optNoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            optYes.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            optPdf.requestFocus();
        }
    }//GEN-LAST:event_optNoKeyPressed

    private void optPdfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optPdfKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            optNo.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            optHtml.requestFocus();
        }
    }//GEN-LAST:event_optPdfKeyPressed

    private void optHtmlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optHtmlKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            optPdf.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            optWord.requestFocus();
        }
    }//GEN-LAST:event_optHtmlKeyPressed

    private void optWordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optWordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            optHtml.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            optExcel.requestFocus();
        }
    }//GEN-LAST:event_optWordKeyPressed

    private void optExcelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optExcelKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            optWord.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            optA4.requestFocus();
        }
    }//GEN-LAST:event_optExcelKeyPressed

    private void optA4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optA4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            optExcel.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            optA5.requestFocus();
        }
    }//GEN-LAST:event_optA4KeyPressed

    private void optA5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optA5KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            optA4.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            optAsk.requestFocus();
        }
    }//GEN-LAST:event_optA5KeyPressed

    private void optAskKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optAskKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            optA5.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnSaveActionPerformed(null);
        }
    }//GEN-LAST:event_optAskKeyPressed

    private void optExcelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optExcelStateChanged
        if (optExcel.isSelected()) {
            optHtml.setSelected(false);
            optWord.setSelected(false);
            optPdf.setSelected(false);
        }
    }//GEN-LAST:event_optExcelStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSave;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton optA4;
    private javax.swing.JRadioButton optA5;
    private javax.swing.JRadioButton optAsk;
    private javax.swing.JRadioButton optExcel;
    private javax.swing.JRadioButton optHtml;
    private javax.swing.JRadioButton optNo;
    private javax.swing.JRadioButton optPdf;
    private javax.swing.JRadioButton optWord;
    private javax.swing.JRadioButton optYes;
    // End of variables declaration//GEN-END:variables
}
