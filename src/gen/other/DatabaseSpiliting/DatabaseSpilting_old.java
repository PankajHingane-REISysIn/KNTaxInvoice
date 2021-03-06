/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.other.DatabaseSpiliting;

import gen.account.group.Group_Create;
import gen.account.ledger.LedgerAlterFromDetails;
import gen.dto.Constants;
import gen.dto.Util;
import gen.mainclass.MainClass;
import gen.other.CompanySettings.CompanySettingsDAO;
import gen.other.CompanySettings.CompanySettingsDTO;
import gen.other.DatabaseBackupRestore.Company_InformationDTO;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author admin
 */
public class DatabaseSpilting_old extends javax.swing.JInternalFrame {

    JTextField tfUnderField;
    // List for Total Company
    List<CompanySettingsDTO> company_ListDTO = new ArrayList<CompanySettingsDTO>();
    private boolean hide_flag = false;
    // Vector Company_List
    Vector<String> vectorCompanyList = new Vector<String>();
    private JTextField tfDatePicker = null;

    /**
     * Creates new form DatabaseSpilting_old
     */
    public DatabaseSpilting_old(String title) {
        try {
            initComponents();
            initialise();
            // setClosable(true);
            //   initcomponent();
            this.setTitle(title);
            // this.setClosed(true);

        } catch (Exception ex) {
            Logger.getLogger(DatabaseSpilting_old.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBoxUnder = new javax.swing.JComboBox();
        fromJDateChooser = new com.toedter.calendar.JDateChooser();
        btnDataSpliting = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(500, 500));

        jComboBoxUnder.setEditable(true);
        jComboBoxUnder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxUnderActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnDataSpliting, org.openide.util.NbBundle.getMessage(DatabaseSpilting_old.class, "DatabaseSpilting_old.btnDataSpliting.text")); // NOI18N
        btnDataSpliting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataSplitingActionPerformed(evt);
            }
        });
        btnDataSpliting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDataSplitingKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxUnder, 0, 177, Short.MAX_VALUE)
                            .addComponent(fromJDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(btnDataSpliting)))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addComponent(jComboBoxUnder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(fromJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(btnDataSpliting)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxUnderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxUnderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxUnderActionPerformed

    private void btnDataSplitingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataSplitingActionPerformed
        // TODO add your handling code here:

        if (validation()) {
            String fromDateOpenStr = "";
            if (fromJDateChooser.getDate() != null) {
                try {
                    fromDateOpenStr = Constants.simpleDateFormatDatabaseWithDash.format(fromJDateChooser.getDate());
                    Calendar c = Calendar.getInstance();
                    c.setTime(Constants.simpleDateFormatDatabaseWithDash.parse(Constants.simpleDateFormatDatabaseWithDash.format(fromJDateChooser.getDate())));
                    // number of days to add for calculation opening_Balances
                    c.add(Calendar.DATE, 1);
                    fromDateOpenStr = Constants.simpleDateFormatDatabaseWithDash.format(c.getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(DatabaseSpilting_old.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            List<String> new_CreateCompanyList = new ArrayList<String>();
            List<Company_InformationDTO> new_databaseCreateList = new ArrayList<Company_InformationDTO>();

            String new_Company_Create = "";

            // StringTokenizer used to remove '-' from Date
            StringTokenizer add_InDatabase_Name = new StringTokenizer(tfDatePicker.getText().toString().trim(), "-");
            String date = "";
            String month = "";
            String year = "";
            int count = 0;
            while (add_InDatabase_Name.hasMoreTokens() && count < 3) {
                if (count == 0) {
                    date = add_InDatabase_Name.nextToken();
                } else if (count == 1) {
                    month = add_InDatabase_Name.nextToken();
                } else if (count == 2) {
                    year = add_InDatabase_Name.nextToken();
                }
                count++;
            }

            // make a string to add in Name to Company
            String add_Date_InComapnyName = date + month + year;


            int check_Company_Name_count = 0;
            for (CompanySettingsDTO companyListDTO : company_ListDTO) {
                if (companyListDTO.getCompany_name().contentEquals(tfUnderField.getText().toString().trim() + add_Date_InComapnyName)) {
                    // set check_Name_conter = 1 if company is already present
                    check_Company_Name_count = 1;
                }
            }

            // if check_Name_conter = 0 means company is not already present
            if (check_Company_Name_count == 0) {
                try {
                    new_Company_Create = tfUnderField.getText().toString().trim() + add_Date_InComapnyName;


                    for (CompanySettingsDTO company_settings : company_ListDTO) {
                        // take Back UP of Database of selected Company List
                        if (tfUnderField.getText().toString().trim().equals(company_settings.getCompany_name())) {
                            // take Back UP of Database of selected Company having databse NMAe company_settings.getCompany_DatabaseName()
                            backupDB(company_settings.getCompany_DatabaseName(), "root", "adm", "D:\\databasecheck/" + new_Company_Create + ".sql");
                        }
                    }

                    new_Company_Create = tfUnderField.getText().toString().trim() + add_Date_InComapnyName;
                    // add New company Name in new_CreateCompanyList
                    new_CreateCompanyList.add(new_Company_Create);

                    Company_InformationDTO company_InformationDTO = new Company_InformationDTO();
                    // add New company Database Name in company_InformationDTO
                    company_InformationDTO.setCompany_Database_Name(new_Company_Create);
                    new_databaseCreateList.add(company_InformationDTO);
                    // Create New Database for Newly created Company
                    gen.other.DatabaseSpiliting.DatabaseSplitingDAO.createNewDatabase(new_databaseCreateList);

                    //	String pass_Database_Name = tfUnderField.getText().toString().trim()  +"$"+ add_date;
                    // flag to check if restore Database correct or not 
                    // if exception occur then flag = false
                    Boolean flag = restoreDB(new_Company_Create, "root", "adm", "D:\\databasecheck/" + new_Company_Create + ".sql");

                    // if flag true then add all information in Company_inormation table
                    if (flag) {
                        List<CompanySettingsDTO> companySettings_InformationDTOList = new ArrayList<CompanySettingsDTO>();
                        gen.other.DatabaseSpiliting.DatabaseSplitingDAO.insert_NewlyCreated_Company(new_CreateCompanyList, new_Company_Create, tfUnderField.getText().toString().trim(), "", companySettings_InformationDTOList);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error in the Datbse Restore");
                    }

                    // delete the .SQL file 
                    Path path = Paths.get("D:\\databasecheck/" + new_Company_Create + ".sql");
                    Files.delete(path);

                    // delete record from table according to Date
                    gen.other.DatabaseSpiliting.DatabaseSplitingDAO.delete_Records(new_Company_Create, fromDateOpenStr);

                    initialise();

//		    gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = "";
                    try {
                        this.setSelected(true);
                        MainClass mainClass = new MainClass();
                        mainClass.menuselection(1);
                        this.setClosed(true);
                    } catch (java.beans.PropertyVetoException e) {
                        e.printStackTrace();
                    }
                    call_Selection_Companies();
                } catch (IOException ex) {
                    Logger.getLogger(DatabaseSpilting_old.class.getName()).log(Level.SEVERE, null, ex);
                }
                gen.other.DatabaseSpiliting.DatabaseSplitingDAO.delete_Records(null, fromDateOpenStr);

            }
        }
    }//GEN-LAST:event_btnDataSplitingActionPerformed

    private void btnDataSplitingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDataSplitingKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnDataSplitingActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            tfDatePicker.requestFocus();
        }
    }//GEN-LAST:event_btnDataSplitingKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDataSpliting;
    private com.toedter.calendar.JDateChooser fromJDateChooser;
    private javax.swing.JComboBox jComboBoxUnder;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    private void initialise() {
        // initialise componenet 
        intilisecomponent();
        bindData2GUT();
        setnemonic();
    }

    // set nemonic
    private void setnemonic() {
        btnDataSpliting.setMnemonic(KeyEvent.VK_S);
    }

    // initialise componenet 
    private void intilisecomponent() {
        fromJDateChooser.setDateFormatString("dd-MM-yyyy");
        Calendar currentDate = Calendar.getInstance();
        fromJDateChooser.setDate(currentDate.getTime());

        jComboBoxUnder.setEditable(true);
        tfUnderField = (JTextField) jComboBoxUnder.getEditor().getEditorComponent();
        tfUnderField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String text = tfUnderField.getText();
                        if (text.length() == 0) {
                            jComboBoxUnder.hidePopup();
                            setGroupLedgerModel(new DefaultComboBoxModel(vectorCompanyList), "");
                        } else {
                            DefaultComboBoxModel m = Util.getSuggestedModel(vectorCompanyList, text);
                            if (m.getSize() == 0 || hide_flag) {
                                jComboBoxUnder.hidePopup();
                                hide_flag = false;
                            } else {
                                //setAccountLedgetModel(m, text);
                                setGroupLedgerModel(m, text);
                                jComboBoxUnder.showPopup();
                            }
                        }
                    }
                });
            }
        });
        tfUnderField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent event) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                            //setFocus(event);
                            tfDatePicker.requestFocus();
                        }
                    }
                });
            }
        });

        tfUnderField = (JTextField) jComboBoxUnder.getEditor().getEditorComponent();
        tfUnderField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                //currentFocusValue = 2;
                String text = tfUnderField.getText();
                if (text.length() == 0) {
                    jComboBoxUnder.hidePopup();
                    setGroupLedgerModel(new DefaultComboBoxModel(vectorCompanyList), "");
                } else {
                    DefaultComboBoxModel m = Util.getSuggestedModel(vectorCompanyList, text);
                    if (m.getSize() == 0 || hide_flag) {
                        jComboBoxUnder.hidePopup();
                        hide_flag = false;
                    } else {
                        setGroupLedgerModel(m, text);
                        //setAccountLedgetModel(m, text);
                        jComboBoxUnder.showPopup();
                    }
                }
                tfUnderField.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        fromJDateChooser.setDateFormatString(Constants.DATE_FORMAT_STRING);
        tfDatePicker = (JTextField) fromJDateChooser.getComponent(1);

        tfDatePicker.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_ENTER) {
                    btnDataSpliting.requestFocus();
                }
                if (code == KeyEvent.VK_ESCAPE) {
                    tfUnderField.requestFocus();
                }
            }
        });

        tfDatePicker.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, tfDatePicker);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(DatabaseSpilting_old.this, ex.getMessage());
                }
            }
        });

        tfDatePicker.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
                tfDatePicker.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }
        });

    }

    // BindToDataToGui
    private void bindData2GUT() {
        try {
            initGroup();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseSpilting_old.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setGroupLedgerModel(DefaultComboBoxModel mdl, String str) {
        jComboBoxUnder.setModel(mdl);
        jComboBoxUnder.setSelectedIndex(-1);
        //jComboBoxUnder.showPopup();
        tfUnderField.setText(str);
    }

    // set all Companies to vector
    private void initGroup() throws SQLException {
        company_ListDTO.clear();
        // set all_company_List to  company_ListDTO
        company_ListDTO = CompanySettingsDAO.getComapany_List("", "");
        vectorCompanyList.clear();
        for (CompanySettingsDTO companySettingsDTO : company_ListDTO) {
            //  Do not add Database
            vectorCompanyList.add(companySettingsDTO.getCompany_name());
        }
        Collections.sort(
                vectorCompanyList,
                new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        return lhs.compareToIgnoreCase(rhs);
                    }
                });
    }

    // Take BackUp
    public static boolean backupDB(String dbName, String dbUserName, String dbPassword, String path) {
        Process runtimeProcess;

        String executeCmd = "D:/MetalCopies/10-10-2013uploadcopy/server-Copy(2)/bin/mysqldump -u " + dbUserName + " -p" + dbPassword + " " + dbName + " -r " + path;

        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                System.out.println("Backup created successfully");
                return true;
            } else {
                System.out.println("Could not create the backup");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Restore  DataBase
    public static boolean restoreDB(String dbName, String dbUserName, String dbPassword, String source) {
        Boolean flag = false;
        String[] executeCmd = new String[]{"D:/MetalCopies/10-10-2013uploadcopy/server-Copy(2)/bin/mysql", "--user=" + dbUserName, "--password=" + dbPassword, dbName, "-e", "source " + source};
        Process runtimeProcess;
        try {

            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                System.out.println("Backup restored successfully");
                flag = true;
            } else {
                System.out.println("Could not restore the backup");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return flag;
    }

    //validation   
    private Boolean validation() {
        Boolean flag = true;

        //validation for company_Name present or not  
        if (!vectorCompanyList.contains(tfUnderField.getText().toString().trim())) {
            tfUnderField.requestFocus();
            flag = false;
            JOptionPane.showMessageDialog(null, "Select Exits Company");
        } else if (fromJDateChooser.getDate() == null) {
            //validation for date is valid or not  
            tfDatePicker.requestFocus();
            flag = false;
            JOptionPane.showMessageDialog(null, "Invalid Date Selection");
        }
        return flag;
    }

    // call Company Selection form after Database spliting is completed
    private void call_Selection_Companies() {
        gen.other.CompanySettings.CompanysSettings company_selection = new gen.other.CompanySettings.CompanysSettings("Company Selection");
        try {
            company_selection.setVisible(true);
            company_selection.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(LedgerAlterFromDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.getParent().add(company_selection);
        this.getParent().setVisible(true);
        Dimension desktopSize = this.getParent().getSize();
        Dimension jInternalFrameSize = company_selection.getSize();
        company_selection.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        try {
            company_selection.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
        BasicInternalFrameUI basicInternalFrameUI = (BasicInternalFrameUI) company_selection.getUI();
        Component north = basicInternalFrameUI.getNorthPane();
        MouseMotionListener[] actions =
                (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

        for (int i = 0; i < actions.length; i++) {
            north.removeMouseMotionListener(actions[i]);
        }
    }
}
