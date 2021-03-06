/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.other.login.CompanySelection;

import gen.account.group.Group_Create;
import gen.database.connection.DatabaseConnection1;
import gen.mainclass.FeaturesDTO;
import gen.mainclass.MainClass;
import gen.mainclass.UserSettingDTO;
import gen.other.CompanySettings.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author pc5
 */
public class CompanysSettings_User extends javax.swing.JInternalFrame {

    /**
     * Creates new form ApplicationsSettings
     */
    //  String selectedCompany = "";
    DefaultTableModel tableModelCompanyList_Company_Settings = new DefaultTableModel();
    public static String CURRENT_DATABASE_Company_Settings = "";
    //public static String CURRENT_COMPANY_Company_Settings = "";
    private List<gen.other.CompanySettings.CompanySettingsDTO> companySettingsDTOList_Company_Settings = new ArrayList<gen.other.CompanySettings.CompanySettingsDTO>();

    public CompanysSettings_User(String str) {
	initComponents();
	setTitle("Select Company");
	intialise_Company_Settings();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        applicationSettingsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        buttonApply_Company_Settings = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablecompanyList_Company_Settings = new javax.swing.JTable();

        setClosable(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(CompanysSettings_User.class, "CompanysSettings_User.jLabel1.text")); // NOI18N

        buttonApply_Company_Settings.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(buttonApply_Company_Settings, org.openide.util.NbBundle.getMessage(CompanysSettings_User.class, "CompanysSettings_User.buttonApply_Company_Settings.text")); // NOI18N
        buttonApply_Company_Settings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonApply_Company_SettingsMouseClicked(evt);
            }
        });
        buttonApply_Company_Settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonApply_Company_SettingsActionPerformed(evt);
            }
        });

        tablecompanyList_Company_Settings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablecompanyList_Company_Settings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablecompanyList_Company_SettingsMouseClicked(evt);
            }
        });
        tablecompanyList_Company_Settings.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablecompanyList_Company_SettingsKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablecompanyList_Company_Settings);

        javax.swing.GroupLayout applicationSettingsPanelLayout = new javax.swing.GroupLayout(applicationSettingsPanel);
        applicationSettingsPanel.setLayout(applicationSettingsPanelLayout);
        applicationSettingsPanelLayout.setHorizontalGroup(
            applicationSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, applicationSettingsPanelLayout.createSequentialGroup()
                .addContainerGap(165, Short.MAX_VALUE)
                .addGroup(applicationSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(applicationSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, applicationSettingsPanelLayout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(157, 157, 157))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, applicationSettingsPanelLayout.createSequentialGroup()
                            .addComponent(buttonApply_Company_Settings)
                            .addGap(345, 345, 345)))))
        );
        applicationSettingsPanelLayout.setVerticalGroup(
            applicationSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(applicationSettingsPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonApply_Company_Settings)
                .addContainerGap(181, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(applicationSettingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(applicationSettingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void intialise_Company_Settings() {
	initilizeGUIComponents_Company_Settings();
	setnemonic_Company_Settings();
    }

    private void initilizeGUIComponents_Company_Settings() {
	initComponentActiveInActive_Company_Settings();
	bindDTOTOGUI_Company_Settings();
    }

    private void initComponentActiveInActive_Company_Settings() {
	String col[] = {"CompanyName", ""};
	String data[][] = {{"", ""}};
	tableModelCompanyList_Company_Settings = new DefaultTableModel(data, col) {
	    @Override
	    public boolean isCellEditable(int row, int column) {
		return false;//This causes all cells to be not editable
	    }
	};
	tablecompanyList_Company_Settings.setModel(tableModelCompanyList_Company_Settings);
	JTableHeader header = tablecompanyList_Company_Settings.getTableHeader();
	header.setBackground(Color.yellow);
	tableModelCompanyList_Company_Settings.setRowCount(0);
	tableModelCompanyList_Company_Settings.setColumnCount(2);
    }

    private void setnemonic_Company_Settings() {
	buttonApply_Company_Settings.setMnemonic(KeyEvent.VK_A);
    }

    private void bindDTOTOGUI_Company_Settings() {
//	gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = "";
	// get Company_List
	UserSettingDTO usersettingdto = new UserSettingDTO();
	usersettingdto.setUser_id(gen.dto.Constants.CURRENT_USER_ID);

	System.out.println("E#eEEEEEEEEEEEEEEEEE             ");
	
	// get role for user as if user is Admin then then role is 1 so visible all company to him
	FeaturesDTO featuresDTO = new FeaturesDTO();
	featuresDTO = gen.mainclass.UserSettingDAO.get_Active_Inactive_By_User(gen.dto.Constants.CURRENT_USER_ID );
	
	String user_Role = featuresDTO.getRole_Name();
	System.out.println("E#eEEEEEEEEEEEEEEEEE        666666666666                   "+user_Role);
	
	if (!user_Role.equalsIgnoreCase("admin")) {
	    System.out.println("E#eEEEEEEEEEEEEEEEEE        666666666666     ");
	    companySettingsDTOList_Company_Settings = gen.mainclass.UserSettingDAO.getCurrent_Company_Details_By_User(usersettingdto);
	} else {
	    System.out.println("E#eEEEEEEEEEEEEEEEEE        77777777777777777     ");
	    companySettingsDTOList_Company_Settings = gen.other.CompanySettings.CompanySettingsDAO.getComapany_List("", "");
	}

	System.out.println("E#eEEEEEEEEEEEEEEEEE        1111111111     ");
	
	tableModelCompanyList_Company_Settings.setRowCount(0);
	for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList_Company_Settings) {
	    System.out.println("E#eEEEEEEEEEEEEEEEEE        2222222222222222     ");
	    tableModelCompanyList_Company_Settings.setRowCount(tableModelCompanyList_Company_Settings.getRowCount() + 1);
	    // set into the table
	    tableModelCompanyList_Company_Settings.setValueAt(companySettingsDTO.getCompany_name(), tableModelCompanyList_Company_Settings.getRowCount() - 1, 0);
	    tableModelCompanyList_Company_Settings.setValueAt(companySettingsDTO.getCompany_id(), tableModelCompanyList_Company_Settings.getRowCount() - 1, 1);
	}
	setColumnWidth_Company_Settings(tablecompanyList_Company_Settings);
    }

    private void tablecompanyList_Company_SettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablecompanyList_Company_SettingsMouseClicked
	// TODO add your handling code here:
	buttonApply_Company_SettingsActionPerformed(null);
    }//GEN-LAST:event_tablecompanyList_Company_SettingsMouseClicked

    private void tablecompanyList_Company_SettingsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablecompanyList_Company_SettingsKeyPressed
	// TODO add your handling code here:
	if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
	    //setFocus(event);
	    tablecompanyList_Company_SettingsMouseClicked(null);
	}
    }//GEN-LAST:event_tablecompanyList_Company_SettingsKeyPressed

    private void buttonApply_Company_SettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonApply_Company_SettingsMouseClicked
	// TODO add your handling code here:
	// set current selected Company DataBase
	//buttonApply_Company_SettingsActionPerformed(null);
    }//GEN-LAST:event_buttonApply_Company_SettingsMouseClicked

    private void buttonApply_Company_SettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonApply_Company_SettingsActionPerformed
	// TODO add your handling code here:
	// set current selected Company DataBase
//	for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList_Company_Settings) {
//	    if (companySettingsDTO.getCompany_name().equals(tableModelCompanyList_Company_Settings.getValueAt(tablecompanyList_Company_Settings.getSelectedRow(), 0).toString())) {
	if (tablecompanyList_Company_Settings.getSelectedRow() > -1) {
	    gen.dto.Constants.CURRENT_COMPANY_ID = tableModelCompanyList_Company_Settings.getValueAt(tablecompanyList_Company_Settings.getSelectedRow(), 1).toString();;
	    gen.dto.Constants.CURRENT_COMPANY_NAME = tableModelCompanyList_Company_Settings.getValueAt(tablecompanyList_Company_Settings.getSelectedRow(), 0).toString();

	    List<gen.other.CompanySettings.CompanySettingsDTO> companySettingsDTOList = new ArrayList<CompanySettingsDTO>();
	    System.out.println("gen.dto.Constants.CURRENT_COMPANY_NAME ---------------- " + gen.dto.Constants.CURRENT_COMPANY_NAME);
	    System.out.println("gen.dto.Constants.CURRENT_COMPANY_ID -------------------" + gen.dto.Constants.CURRENT_COMPANY_ID);
	    companySettingsDTOList = gen.other.CompanySettings.CompanySettingsDAO.getComapany_List(gen.dto.Constants.CURRENT_COMPANY_NAME, gen.dto.Constants.CURRENT_COMPANY_ID);

	    for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList) {
//                try {
                    //		if (!gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings.isEmpty()) {
                                    gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = companySettingsDTO.getCompany_DatabaseName();
                gen.dto.Constants.CURRENT_COMPANY_CREATED_DATE = companySettingsDTO.getCompany_ApplicableFrom_date();
                    //		}
//                                    
//                                    Connection conn = null;
//                                    conn = DatabaseConnection1.GetConnection();
//                                    String q = "BACKUP DATABASE TO 'C:\\Users\\pc5\\Desktop\\New folder\\Maximus.tgz' BLOCKING";
//                                    PreparedStatement prmt = conn.prepareStatement(q);
//                                    prmt.execute();
//                } catch (SQLException ex) {
//                    Logger.getLogger(CompanysSettings_User.class.getName()).log(Level.SEVERE, null, ex);
//                }
	    }
	}
//	}
//	


	System.out.println("Company ID ::::::   ------------- " + gen.dto.Constants.CURRENT_COMPANY_ID);
	System.out.println("Company Name  ::::::   ------------- " + gen.dto.Constants.CURRENT_COMPANY_NAME);


	JOptionPane.showMessageDialog(this, "Selected Company : " + gen.dto.Constants.CURRENT_COMPANY_NAME);


	try {
	    this.setClosed(true);
	    gen.dto.Constants.Features_Call_Class = "CompanySettings_User";
//	    MainClass mainClass = new MainClass();
//	    mainClass.getActiveInactiveComponent("CompanySettings_User");

//	    mainClass.setAllcomponentInactive();
	} catch (PropertyVetoException ex) {
	    ex.printStackTrace();
	    Logger.getLogger(Group_Create.class.getName()).log(Level.SEVERE, null, ex);
	}
    }//GEN-LAST:event_buttonApply_Company_SettingsActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel applicationSettingsPanel;
    private javax.swing.JButton buttonApply_Company_Settings;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablecompanyList_Company_Settings;
    // End of variables declaration//GEN-END:variables

    private void setColumnWidth_Company_Settings(JTable passedTable) {
	DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
	rightRenderer.setHorizontalAlignment(JLabel.LEFT);

	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	centerRenderer.setHorizontalAlignment(JLabel.CENTER);

	passedTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
	passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
	passedTable.getColumnModel().getColumn(0).setMinWidth(360);
	passedTable.getColumnModel().getColumn(0).setMaxWidth(360);

	passedTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
	passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
	passedTable.getColumnModel().getColumn(1).setMinWidth(80);
	passedTable.getColumnModel().getColumn(1).setMaxWidth(80);
    }
//    
//    public static void main(String args[]){
//	CompanysSettings_User user = new CompanysSettings_User("dasds");
//	user.setVisible(true);
//	user.setSize(500, 800);
//    }
//    
}
