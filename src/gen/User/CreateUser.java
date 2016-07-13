package gen.User;

import gen.company.Create_Company;
import gen.dto.Constants;
import gen.dto.Util;
import gen.mainclass.AdSuMuDiSettingsEclipseGUI;
import gen.mainclass.FeaturesDTO;
import gen.mainclass.UserSettingDTO;
import gen.other.CompanySettings.CompanySettingsDAO;
import gen.other.CompanySettings.CompanySettingsDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.miginfocom.swing.MigLayout;

public class CreateUser extends JInternalFrame {

    private JTextField txtUser_Name1;
    private JPasswordField txtUser_Password1;
    private JPasswordField txtUser_ConfirmPassword1;
    private JTextField txtUser_EmailId1;
    private JPasswordField txtUser_Email_Password1;
    private JTable tblavailableRole;
    private JTable tblUser_Available_Company;
    private JTable tblUser_Selected_Company;
    private JTable tblselectedRole;
    private JTable tblUser_Details_for_Admin;
    DefaultTableModel tablemodel_Company_Available_User_Settings,
            tablemodel_Company_Selected_User_Settings,
            tablemodel_User_Details_Settings,
            tablemodel_AvailableRole_User_Settings,
            tablemodel_SelectedRole_User_Settings;
    private List<gen.other.CompanySettings.CompanySettingsDTO> companySettingsDTOList_User_Settings = new ArrayList<gen.other.CompanySettings.CompanySettingsDTO>();
    private List<gen.mainclass.UserSettingDTO> user_SettingsDTOList_User_Settings = new ArrayList<gen.mainclass.UserSettingDTO>();
    private boolean hide_flag_User_Settings = false;
    Map<String, String> mapRoleandID = new HashMap<String, String>();
    Vector<String> roleVector = new Vector<String>();
    Boolean flag_For_Component_enable_Disable_User_Settings = true;
    private UserSettingDTO userSettingDTO_Class_Level = new UserSettingDTO();
    private String user_Cliked_For_Update = "";
    private String user_ID_Delete = "";
    /**
     * Launch the application.
     */
    private JButton btnCompanyCreate1;
    private JButton btnUpdate1;
    private JButton btncompanyClear1;
    private JLabel lblPassword;
    private JLabel lblUser;
    private JPanel panel;
    private JPanel panel_5;
    private JPanel panel_1;
    private JPanel panel_4;
    private JPanel panel_2;
    private JPanel panel_3;
    private JLabel lblId;
    private JLabel lblEmailId;
    private JLabel lblConfirmPassword;
    private JLabel lblEmailIdPassword;
    private JLabel lblRole;
    private JLabel lblContactNumber;
    private JLabel lblUserType;
    private JRadioButton radio_User_Type_Admin_1;
    private JRadioButton radio_User_Type_NormalUser1;
    private JLabel lblAvailableCompany;
    private JLabel lblVatTinNumber;
    private JLabel lblCstNumber;
    private JLabel lblDeclarations;
    private JLabel lblSelectedCompany;
    private JLabel lblSigningAuthority;
    private JScrollPane scrollPane_4;
    private JScrollPane scrollPane_3;
    private JScrollPane scrollPane_1;
    private JScrollPane scrollPane_2;
    private JScrollPane scrollPane;
    private JButton NUButtonBack1;
    private JButton NUButtonCreateUser1;
    private JButton btnNewUser1;
    private JButton btnDelete;
    private JButton btnDelete1;
    private JButton buttonExit_Company_Delete;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreateUser frame = new CreateUser();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public CreateUser() {
        setClosable(true);
        setTitle("Create New User");
        //setBounds(100, 100, 1332, 674);
        setFrameIcon(new ImageIcon(Create_Company.class.getResource("/images/Kasturi-logo-1.png")));
        panel = new JPanel();
        getContentPane().add(panel, BorderLayout.WEST);
        panel.setLayout(new MigLayout(
                "",
                "[0px:150px:150px,grow,shrink 50,fill][0px:150px:150px,fill][0px:150px:150px,grow,shrink 50,fill][0px:150px:150px,grow,shrink 50,fill][0px:300px:300px,grow,shrink 50,fill][0px:300px:300px,grow,shrink 50,fill][grow,shrink 50,fill]",
                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        lblUser = new JLabel("User");
        lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblUser, "cell 0 1 2 1,alignx trailing");

        txtUser_Name1 = new JTextField();
        txtUser_Name1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtUser_Password1.requestFocus();
                }
            }
        });
        panel.add(txtUser_Name1, "cell 2 1 2 1,growx");
        txtUser_Name1.setColumns(10);

        panel_5 = new JPanel();
        panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.add(panel_5, "cell 4 1 1 17,grow");
        panel_5.setLayout(new MigLayout("", "[grow]", "[grow]"));

        scrollPane_4 = new JScrollPane();
        panel_5.add(scrollPane_4, "cell 0 0,grow");

        tblUser_Details_for_Admin = new JTable();
        tblUser_Details_for_Admin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                flag_For_Component_enable_Disable_User_Settings = false;
                setDisable_Component_User_Setting();
                bindDTOTOGUI_User_Settings();
            }
        });
        scrollPane_4.setViewportView(tblUser_Details_for_Admin);

        lblPassword = new JLabel("Password");
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblPassword, "cell 0 2 2 1,alignx trailing");

        txtUser_Password1 = new JPasswordField();
        txtUser_Password1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtUser_ConfirmPassword1.requestFocus();
                } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtUser_Name1.requestFocus();
                }
            }
        });
        panel.add(txtUser_Password1, "cell 2 2 2 1,growx");

        lblConfirmPassword = new JLabel("Confirm Password");
        lblConfirmPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblConfirmPassword, "cell 0 3 2 1,alignx trailing");

        txtUser_ConfirmPassword1 = new JPasswordField();
        txtUser_ConfirmPassword1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtUser_EmailId1.requestFocus();
                } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtUser_Password1.requestFocus();
                }
            }
        });
        panel.add(txtUser_ConfirmPassword1, "cell 2 3 2 1,growx");

        lblEmailId = new JLabel("E-Mail ID");
        lblEmailId.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblEmailId, "cell 0 4 2 1,alignx trailing");

        txtUser_EmailId1 = new JTextField();
        txtUser_EmailId1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
		    txtUser_ConfirmPassword1.requestFocus();
		}
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtUser_Email_Password1.requestFocus();
                }
            }
        });
        panel.add(txtUser_EmailId1, "cell 2 4 2 1,growx");
        txtUser_EmailId1.setColumns(10);

        lblEmailIdPassword = new JLabel("E-Mail ID Password");
        lblEmailIdPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblEmailIdPassword, "cell 0 5 2 1,alignx trailing");

        txtUser_Email_Password1 = new JPasswordField();
	txtUser_Email_Password1.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
		    txtUser_EmailId1.requestFocus();
		}
	    }
	});
        panel.add(txtUser_Email_Password1, "cell 2 5 2 1,growx");

        lblRole = new JLabel("Role");
        lblRole.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblRole, "cell 0 6 2 1");

        panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.add(panel_1, "cell 2 6 1 3,grow");
        panel_1.setLayout(new MigLayout("", "[grow]", "[grow]"));

        scrollPane = new JScrollPane();
        panel_1.add(scrollPane, "cell 0 0,grow");

        tblavailableRole = new JTable();
        tblavailableRole.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedCompany_index = tblavailableRole.getSelectedRow();
                // selected company_Name
                String selectedCompany_Name = tblavailableRole.getValueAt(
                        selectedCompany_index, 0).toString();
                // selected company_ID

                // check if table selected company is already present or not
                // if not then ADD
                Boolean flag_Row_present_Or_Not = true;
                if (tblselectedRole.getRowCount() > 0) {
                    for (int i = 0; i < tblselectedRole.getRowCount(); i++) {
                        if (selectedCompany_Name.equals(tblselectedRole
                                .getValueAt(i, 0).toString())) {
                            flag_Row_present_Or_Not = false;
                            break;
                        }
                    }
                }

                if (flag_Row_present_Or_Not) {
                    // selected row must be greater than 0
                    if (tblavailableRole.getSelectedRow() >= 0) {
                        // getRowCount row must be greater than 0
                        if (tblavailableRole.getRowCount() > 0) {
                            // find selected row from tableCompanyList
                            for (int i = 0; i < tblavailableRole.getRowCount(); i++) {
                                // selected row
                                if (tblavailableRole.isRowSelected(i)) {
                                    // move data of selected row from
                                    // tableCompanyList to tableCompanyRestoreUp
                                    tablemodel_SelectedRole_User_Settings
                                            .setRowCount(tablemodel_SelectedRole_User_Settings
                                            .getRowCount() + 1);
                                    tablemodel_SelectedRole_User_Settings
                                            .setValueAt(selectedCompany_Name,
                                            tablemodel_SelectedRole_User_Settings
                                            .getRowCount() - 1,
                                            0);
                                    // remove row from table tableCompanyList
                                    tablemodel_AvailableRole_User_Settings
                                            .removeRow(i);
                                    // decrease value of i(row) as one row
                                    // removed
                                    i--;
                                }
                            }
                        }
                    }
                }
            }
        });
        scrollPane.setViewportView(tblavailableRole);

        panel_4 = new JPanel();
        panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.add(panel_4, "cell 3 6 1 3,grow");
        panel_4.setLayout(new MigLayout("", "[grow]", "[grow]"));

        scrollPane_3 = new JScrollPane();
        panel_4.add(scrollPane_3, "cell 0 0,grow");

        tblselectedRole = new JTable();
        tblselectedRole.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedCompany_index = tblselectedRole.getSelectedRow();

                // selected company_Name
                String selectedCompany_Name = tblselectedRole.getValueAt(
                        selectedCompany_index, 0).toString();
                // selected company_ID

                // selected row must be greater than 0
                if (tblselectedRole.getSelectedRow() >= 0) {
                    // getRowCount row must be greater than 0
                    if (tblselectedRole.getRowCount() > 0) {
                        // find selected row from tableCompanyList
                        for (int i = 0; i < tblselectedRole.getRowCount(); i++) {
                            // selected row
                            if (tblselectedRole.isRowSelected(i)) {
                                // move data of selected row from
                                // tableCompanyList to tableCompanyRestoreUp
                                tablemodel_AvailableRole_User_Settings
                                        .setRowCount(tablemodel_AvailableRole_User_Settings
                                        .getRowCount() + 1);
                                tablemodel_AvailableRole_User_Settings
                                        .setValueAt(selectedCompany_Name,
                                        tablemodel_AvailableRole_User_Settings
                                        .getRowCount() - 1, 0);
                                // remove row from table tableCompanyList
                                tablemodel_SelectedRole_User_Settings
                                        .removeRow(i);
                                // decrease value of i(row) as one row removed
                                i--;
                            }
                        }
                    }
                }
            }
        });
        scrollPane_3.setViewportView(tblselectedRole);

//        lblUserType = new JLabel("User Type");
//        lblUserType.setHorizontalAlignment(SwingConstants.RIGHT);
//        panel.add(lblUserType, "cell 0 9 2 1");
//
//        radio_User_Type_Admin_1 = new JRadioButton("Administrator");
//        radio_User_Type_Admin_1.addChangeListener(new ChangeListener() {
//            public void stateChanged(ChangeEvent arg0) {
//                if (radio_User_Type_Admin_1.isSelected()) {
//                    radio_User_Type_NormalUser1.setSelected(false);
//                }
//                if (radio_User_Type_NormalUser1.isSelected() == false) {
//                    radio_User_Type_Admin_1.setSelected(true);
//                }
//            }
//        });
//        panel.add(radio_User_Type_Admin_1, "cell 2 9");

        lblAvailableCompany = new JLabel("Available Company");
        lblAvailableCompany.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblAvailableCompany, "cell 0 10 2 1");

        panel_2 = new JPanel();
        panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.add(panel_2, "cell 2 10 2 3,grow");
        panel_2.setLayout(new MigLayout("", "[grow]", "[grow]"));

        scrollPane_1 = new JScrollPane();
        panel_2.add(scrollPane_1, "cell 0 0,grow");

        tblUser_Available_Company = new JTable();
        tblUser_Available_Company.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedCompany_index = tblUser_Available_Company
                        .getSelectedRow();
                // selected company_Name
                String selectedCompany_Name = tblUser_Available_Company
                        .getValueAt(selectedCompany_index, 0).toString();
                // selected company_ID
                String selectedCompany_ID = tblUser_Available_Company
                        .getValueAt(selectedCompany_index, 1).toString();

                // check if table selected company is already present or not
                // if not then ADD
                Boolean flag_Row_present_Or_Not = true;
                if (tblUser_Selected_Company.getRowCount() > 0) {
                    for (int i = 0; i < tblUser_Selected_Company.getRowCount(); i++) {
                        if (selectedCompany_Name
                                .equals(tblUser_Selected_Company.getValueAt(i,
                                0).toString())
                                && selectedCompany_ID
                                .equals(tblUser_Selected_Company
                                .getValueAt(i, 1).toString())) {
                            flag_Row_present_Or_Not = false;
                            break;
                        }
                    }
                }

                if (flag_Row_present_Or_Not) {
                    // selected row must be greater than 0
                    if (tblUser_Available_Company.getSelectedRow() >= 0) {
                        // getRowCount row must be greater than 0
                        if (tblUser_Available_Company.getRowCount() > 0) {
                            // find selected row from tableCompanyList
                            for (int i = 0; i < tblUser_Available_Company
                                    .getRowCount(); i++) {
                                // selected row
                                if (tblUser_Available_Company.isRowSelected(i)) {
                                    // move data of selected row from
                                    // tableCompanyList to tableCompanyRestoreUp
                                    tablemodel_Company_Selected_User_Settings
                                            .setRowCount(tblUser_Selected_Company
                                            .getRowCount() + 1);
                                    tblUser_Selected_Company.setValueAt(
                                            selectedCompany_Name,
                                            tablemodel_Company_Selected_User_Settings
                                            .getRowCount() - 1, 0);
                                    tblUser_Selected_Company.setValueAt(
                                            selectedCompany_ID,
                                            tablemodel_Company_Selected_User_Settings
                                            .getRowCount() - 1, 1);
                                    // remove row from table tableCompanyList
                                    tablemodel_Company_Available_User_Settings
                                            .removeRow(i);
                                    // decrease value of i(row) as one row
                                    // removed
                                    i--;
                                }
                            }
                        }
                    }
                }
            }
        });
        scrollPane_1.setViewportView(tblUser_Available_Company);

        lblSelectedCompany = new JLabel("Selected Company");
        lblSelectedCompany.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblSelectedCompany, "cell 0 13 2 1");

//        radio_User_Type_NormalUser1 = new JRadioButton(
//                "Normal User");
//        radio_User_Type_NormalUser1.addChangeListener(new ChangeListener() {
//            public void stateChanged(ChangeEvent e) {
//                if (radio_User_Type_NormalUser1.isSelected()) {
//                    radio_User_Type_Admin_1.setSelected(false);
//                }
//                if (radio_User_Type_Admin_1.isSelected() == false) {
//                    radio_User_Type_NormalUser1.setSelected(true);
//                }
//            }
//        });
//        panel.add(radio_User_Type_NormalUser1, "cell 3 9");

        panel_3 = new JPanel();
        panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.add(panel_3, "cell 2 13 2 3,grow");
        panel_3.setLayout(new MigLayout("", "[grow]", "[grow]"));

        scrollPane_2 = new JScrollPane();
        panel_3.add(scrollPane_2, "cell 0 0,grow");

        tblUser_Selected_Company = new JTable();
        tblUser_Selected_Company.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedCompany_index = tblUser_Selected_Company
                        .getSelectedRow();

                // selected company_Name
                String selectedCompany_Name = ""
                        + tblUser_Selected_Company.getValueAt(
                        selectedCompany_index, 0);
                // selected company_ID
                String selectedCompany_ID = ""
                        + tblUser_Selected_Company.getValueAt(
                        selectedCompany_index, 1);

                // selected row must be greater than 0
                if (tblUser_Selected_Company.getSelectedRow() >= 0) {
                    // getRowCount row must be greater than 0
                    if (tblUser_Selected_Company.getRowCount() > 0) {
                        // find selected row from tableCompanyList
                        for (int i = 0; i < tblUser_Selected_Company
                                .getRowCount(); i++) {
                            // selected row
                            if (tblUser_Selected_Company.isRowSelected(i)) {
                                // move data of selected row from
                                // tableCompanyList to tableCompanyRestoreUp
                                tablemodel_Company_Available_User_Settings
                                        .setRowCount(tblUser_Available_Company
                                        .getRowCount() + 1);
                                tblUser_Available_Company.setValueAt(
                                        selectedCompany_Name,
                                        tablemodel_Company_Available_User_Settings
                                        .getRowCount() - 1, 0);
                                tblUser_Available_Company.setValueAt(
                                        selectedCompany_ID,
                                        tablemodel_Company_Available_User_Settings
                                        .getRowCount() - 1, 1);
                                // remove row from table tableCompanyList
                                tablemodel_Company_Selected_User_Settings
                                        .removeRow(i);
                                // decrease value of i(row) as one row removed
                                i--;
                            }
                        }
                    }
                }
            }
        });
        scrollPane_2.setViewportView(tblUser_Selected_Company);

        NUButtonBack1 = new JButton("Back");
        NUButtonBack1.setMnemonic('B');
        NUButtonBack1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NUButtonBack1ActionPerformed(e);
            }
        });
        panel.add(NUButtonBack1, "cell 0 17");

        NUButtonCreateUser1 = new JButton("Submit");
        NUButtonCreateUser1.setMnemonic('S');
        NUButtonCreateUser1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NUButtonCreateUser1ActionPerformed(e);
            }
        });
        panel.add(NUButtonCreateUser1, "cell 1 17");

        btnNewUser1 = new JButton("New");
        btnNewUser1.setMnemonic('N');
        btnNewUser1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnNewUser1ActionPerformed(e);
            }
        });
        panel.add(btnNewUser1, "cell 2 17");

        btnDelete = new JButton("Delete");
        btnDelete.setMnemonic('D');
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnDeleteActionPerformed(e);
            }
        });
        panel.add(btnDelete, "cell 3 17");

        intialise_User_Settings();

    }

    private void NUButtonCreateUser1ActionPerformed(
            java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        // / Add user information in tbluserdetails table

        if (Validation_For_User_Creation()) {
            userSettingDTO_Class_Level = bindGUItoDTO();
            System.out
                    .println("userSettingDTO_Class_Level =========================== "
                    + userSettingDTO_Class_Level.getUser_id());
            List<UserSettingDTO> userSettingDTOList = new ArrayList<UserSettingDTO>();
            userSettingDTOList.add(userSettingDTO_Class_Level);

            // insert into table user id to company in table tblusertocompany
            List<CompanySettingsDTO> companySettingsDTOList13454 = new ArrayList<CompanySettingsDTO>();

            for (int i = 0; i < tblUser_Selected_Company.getRowCount(); i++) {
                CompanySettingsDTO companySettingsDTO1 = new CompanySettingsDTO();
                companySettingsDTO1.setCompany_name(tblUser_Selected_Company
                        .getValueAt(i, 0).toString().trim());
                companySettingsDTO1.setCompany_id(Long
                        .parseLong(tblUser_Selected_Company.getValueAt(i, 1)
                        .toString().trim()));
                companySettingsDTOList13454.add(companySettingsDTO1);
            }
            if (flag_For_Component_enable_Disable_User_Settings) {
                System.out
                        .println("Insert ====================================================");
		gen.mainclass.UserSettingDAO
                        .insertUserSetting(userSettingDTOList);
		gen.mainclass.UserSettingDAO
                        .insertUserRoles(userSettingDTOList);
		gen.mainclass.UserSettingDAO.insertUsertoCompany(
                        companySettingsDTOList13454, "");
            } else {
                System.out
                        .println("UPDATE ====================================================");
		gen.mainclass.UserSettingDAO
                        .updateUserSetting_By_Admin(userSettingDTOList);
		gen.mainclass.UserSettingDAO.insertUsertoCompany(
                        companySettingsDTOList13454,
                        userSettingDTO_Class_Level.getUser_id());
		gen.mainclass.UserSettingDAO
                        .insertUserRoles(userSettingDTOList);
            }
            user_ID_Delete = "";
            JOptionPane.showMessageDialog(CreateUser.this,
                    "User Information Submitted");
            intialise_User_Settings();
        }
    }

    private void NUButtonBack1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // TODO add your handling code here:

            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    private void btnNewUser1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        flag_For_Component_enable_Disable_User_Settings = true;
        setEnable_Component_User_Settings();
        // claerfield_User_Settings();
        intialise_User_Settings();
        txtUser_Name1.requestFocus();
        user_ID_Delete = "";
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (flag_For_Component_enable_Disable_User_Settings == false
                && !user_ID_Delete.toString().trim().equals("")) {

            System.out.println("TRRRRRRRRRRRRR " + user_ID_Delete);
            List<UserSettingDTO> userSettingList = new ArrayList<UserSettingDTO>();
            UserSettingDTO userSettingDTO = new UserSettingDTO();
            userSettingDTO.setUser_id(user_ID_Delete);
            userSettingList.add(userSettingDTO);
	    gen.mainclass.UserSettingDAO.Delete_Users(userSettingList);
            user_ID_Delete = "";
            JOptionPane.showMessageDialog(CreateUser.this,
                    "Selected User Deleted");
            intialise_User_Settings();
        }

    }

    private void intialise_User_Settings() {
        initilizeGUIComponents_User_Settings();
        setnemonic_User_Settings();
    }

    private void initilizeGUIComponents_User_Settings() {
        initComponentActiveInActive_User_Settings();
        bindDTOTOUser_Details_table();
        intilise_DATA_User_Settings();
        claerfield_User_Settings();
        setEnable_Component_User_Settings();
    }

    private void initComponentActiveInActive_User_Settings() {

        flag_For_Component_enable_Disable_User_Settings = true;
        String col[] = {"CompanyName", ""};
        String data[][] = {{"", ""}};
        tablemodel_Company_Available_User_Settings = new DefaultTableModel(
                data, col) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;// This causes all cells to be not editable
            }
        };
        tblUser_Available_Company
                .setModel(tablemodel_Company_Available_User_Settings);
        JTableHeader header = tblUser_Available_Company.getTableHeader();
        header.setBackground(Color.yellow);
        tablemodel_Company_Available_User_Settings.setRowCount(0);
        tablemodel_Company_Available_User_Settings.setColumnCount(2);

        String col_for_Selected_Companies[] = {"CompanyName", ""};
        String data_for_Selected_Companies[][] = {{"", ""}};
        tablemodel_Company_Selected_User_Settings = new DefaultTableModel(
                data_for_Selected_Companies, col_for_Selected_Companies) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;// This causes all cells to be not editable
            }
        };
        tblUser_Selected_Company
                .setModel(tablemodel_Company_Selected_User_Settings);
        JTableHeader header1 = tblUser_Selected_Company.getTableHeader();
        header1.setBackground(Color.yellow);
        tablemodel_Company_Selected_User_Settings.setRowCount(0);
        tablemodel_Company_Selected_User_Settings.setColumnCount(2);
        setColumnWidth_Users_Settings(tblUser_Available_Company);
        setColumnWidth_Users_Settings(tblUser_Selected_Company);

        String col_for_User_details[] = {"User Name"};
        String data_for_User_details[][] = {{""}};
        tablemodel_User_Details_Settings = new DefaultTableModel(
                data_for_User_details, col_for_User_details) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;// This causes all cells to be not editable
            }
        };
        tblUser_Details_for_Admin.setModel(tablemodel_User_Details_Settings);
        JTableHeader header_for_User_details = tblUser_Details_for_Admin
                .getTableHeader();
        header_for_User_details.setBackground(Color.yellow);
        tablemodel_User_Details_Settings.setRowCount(0);
        tablemodel_User_Details_Settings.setColumnCount(1);
        // setColumnWidth_Users_Settings(tblUser_Details_for_Admin);

        String col_for_Role_Available[] = {"Avaliable Role"};
        String data_for_Role_Available[][] = {{""}};
        tablemodel_AvailableRole_User_Settings = new DefaultTableModel(
                data_for_Role_Available, col_for_Role_Available) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;// This causes all cells to be not editable
            }
        };
        tblavailableRole.setModel(tablemodel_AvailableRole_User_Settings);
        JTableHeader header_for_available_role = tblavailableRole
                .getTableHeader();
        header_for_available_role.setBackground(Color.yellow);
        tablemodel_AvailableRole_User_Settings.setRowCount(0);
        tablemodel_AvailableRole_User_Settings.setColumnCount(1);
        // setColumnWidth_Roles_Users_Settings(tblavailableRole);

        String col_for_Role_Selected[] = {"Selected Role"};
        String data_for_Role_Selected[][] = {{""}};
        tablemodel_SelectedRole_User_Settings = new DefaultTableModel(
                data_for_Role_Selected, col_for_Role_Selected) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;// This causes all cells to be not editable
            }
        };
        tblselectedRole.setModel(tablemodel_SelectedRole_User_Settings);
        JTableHeader header_for_Selected_role = tblselectedRole
                .getTableHeader();
        header_for_Selected_role.setBackground(Color.yellow);
        tablemodel_SelectedRole_User_Settings.setRowCount(0);
        tablemodel_SelectedRole_User_Settings.setColumnCount(1);
        // setColumnWidth_Roles_Users_Settings(tblselectedRole);
    }

    private void setnemonic_User_Settings() {
        NUButtonCreateUser1.setMnemonic(KeyEvent.VK_S);
        NUButtonBack1.setMnemonic(KeyEvent.VK_B);
    }

    private void intilise_DATA_User_Settings() {
        try {
            initRole();
            // ADD into Table Available Company
	    companySettingsDTOList_User_Settings = CompanySettingsDAO.getComapany_List("", "");
            tablemodel_Company_Available_User_Settings.setRowCount(0);
            for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList_User_Settings) {
		System.out.println("tablemodel_Company_Available_User_Settings ------------------ ");
		tablemodel_Company_Available_User_Settings.setRowCount(tablemodel_Company_Available_User_Settings.getRowCount() + 1);
		tablemodel_Company_Available_User_Settings.setValueAt(companySettingsDTO.getCompany_name(), tablemodel_Company_Available_User_Settings.getRowCount() - 1, 0);
		tablemodel_Company_Available_User_Settings.setValueAt(companySettingsDTO.getCompany_id(), tablemodel_Company_Available_User_Settings.getRowCount() - 1, 1);
            }

            mapRoleandID.clear();
	    mapRoleandID = gen.mainclass.UserSettingDAO.get_Role_ID("", "");
            roleVector.clear();
            tablemodel_AvailableRole_User_Settings.setRowCount(0);
            for (String str : mapRoleandID.keySet()) {
                if (!"Admin".equals(str)) {
                    roleVector.add(str);
                    tablemodel_AvailableRole_User_Settings
                            .setRowCount(tablemodel_AvailableRole_User_Settings
                            .getRowCount() + 1);
                    tablemodel_AvailableRole_User_Settings.setValueAt(str,
                            tablemodel_AvailableRole_User_Settings
                            .getRowCount() - 1, 0);
                }
            }
            mapRoleandID = Util.getSmallCaseMap(mapRoleandID);

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    private UserSettingDTO bindGUItoDTO() {
        UserSettingDTO userSettingDTO = new UserSettingDTO();
        userSettingDTO.setUser_id(userSettingDTO_Class_Level.getUser_id());
        userSettingDTO.setUser_Name(txtUser_Name1.getText().toString().trim());
        userSettingDTO.setUser_Password(txtUser_Password1.getText().toString()
                .trim());
        userSettingDTO.setUser_Email_ID(txtUser_EmailId1.getText().toString()
                .trim());
        userSettingDTO.setUser_Email_Password(txtUser_Email_Password1.getText()
                .toString().trim());

        String choosen_Role = "";

        // int role_id = 0;
        // role_id =
        // Integer.parseInt(mapRoleandID.get(tf_Role_User_Settings.getText().toString().trim().toLowerCase()));

        List<FeaturesDTO> featuresDTOList = new ArrayList<FeaturesDTO>();
        for (int i = 0; i < tblselectedRole.getRowCount(); i++) {
            FeaturesDTO featuresDTO = new FeaturesDTO();

            String role = tblselectedRole.getValueAt(i, 0).toString().trim()
                    .toLowerCase();
            int role_id = 0;
            role_id = Integer.parseInt(mapRoleandID.get(role));

            featuresDTO.setRoleID("" + role_id);
            featuresDTO.setRole_Name(role);
            featuresDTOList.add(featuresDTO);
        }
        userSettingDTO.setFeaturesDTOList(featuresDTOList);

        // if (choosen_Role.equalsIgnoreCase("Standard")) {
        // role_id = 2;
        // } else if (choosen_Role.equalsIgnoreCase("Reports")) {
        // role_id = 3;
        // } else if (choosen_Role.equalsIgnoreCase("AccountVoucher")) {
        // role_id = 4;
        // } else if (choosen_Role.equalsIgnoreCase("Group")) {
        // role_id = 5;
        // } else if (choosen_Role.equalsIgnoreCase("StockItem")) {
        // role_id = 6;
        // }

        // userSettingDTO.setUser_Role(role_id);

//        int user_Type = 0;
//        if (radio_User_Type_Admin_1.isSelected()) {
//            user_Type = 1;
//        } else if (radio_User_Type_NormalUser1.isSelected()) {
//            user_Type = 2;
//        }
//        userSettingDTO.setUser_Type(user_Type);
        return userSettingDTO;
    }

    private void bindDTOTOGUI_User_Settings() {

        try {
            int selectet_Row = tblUser_Details_for_Admin.getSelectedRow();

            if (selectet_Row > -1) {

		// call table available with all campany List as we already
		// remove selected company from this list
		intilise_DATA_User_Settings();

                String user_Name = tblUser_Details_for_Admin
                        .getValueAt(selectet_Row, 0).toString().trim();
                for (UserSettingDTO userSettingDTO : user_SettingsDTOList_User_Settings) {
                    if (userSettingDTO.getUser_Name().contentEquals(user_Name)) {
                        userSettingDTO_Class_Level = userSettingDTO;
                    }
                }

                user_ID_Delete = userSettingDTO_Class_Level.getUser_id();
		user_Cliked_For_Update = userSettingDTO_Class_Level.getUser_Name();
		txtUser_Name1.setText(userSettingDTO_Class_Level.getUser_Name());
		txtUser_Password1.setText(userSettingDTO_Class_Level.getUser_Password());
		txtUser_ConfirmPassword1.setText(userSettingDTO_Class_Level.getUser_Password());
		txtUser_EmailId1.setText(userSettingDTO_Class_Level.getUser_Email_ID());
		txtUser_Email_Password1.setText(userSettingDTO_Class_Level.getUser_Email_Password());

                String role = "";
                for (Map.Entry<String, String> e : mapRoleandID.entrySet()) {
		    if (e.getValue().equals("" + userSettingDTO_Class_Level.getUser_Role())) {
                        role = e.getKey();
                    }
                }

                // Set Data to table Selected role
                List<FeaturesDTO> featuresDTOList = new ArrayList<FeaturesDTO>();
		featuresDTOList = gen.mainclass.UserSettingDAO.getRoleByUsers(userSettingDTO_Class_Level.getUser_id());
                tablemodel_SelectedRole_User_Settings.setRowCount(0);
                for (FeaturesDTO featuresDTO : featuresDTOList) {
		    tablemodel_SelectedRole_User_Settings.setRowCount(tablemodel_SelectedRole_User_Settings.getRowCount() + 1);
		    tablemodel_SelectedRole_User_Settings.setValueAt(featuresDTO.getRole_Name(), tablemodel_SelectedRole_User_Settings.getRowCount() - 1, 0);
                }
                // remove companies which are present in selected_company_table
                // from available_company table
                for (int i = 0; i < tblselectedRole.getRowCount(); i++) {
                    // company_nmae and company_id from seletecd table
                    String company_Name = tblselectedRole.getValueAt(i, 0)
                            .toString();
                    for (int j = 0; j < tblavailableRole.getRowCount(); j++) {
			System.out.println("company_Name -------------------" + company_Name);
			System.out.println("---------------------------" + tblavailableRole.getValueAt(j, 0));
			System.out.println("------------" + tblavailableRole.getValueAt(j, 0).equals(company_Name));
			if (company_Name.equals(tblavailableRole.getValueAt(j, 0))) {
                            // remove row from table tableCompanyList
			    System.out.println("------------                 " + j);
                            tablemodel_AvailableRole_User_Settings.removeRow(j);
                            // decrease value of i(row) as one row removed
                            j--;
                        }
                    }
                }

                List<CompanySettingsDTO> companySettingsDTOList = new ArrayList<CompanySettingsDTO>();
		companySettingsDTOList = gen.mainclass.UserSettingDAO.getCurrent_Company_Details_By_User(userSettingDTO_Class_Level);

                tablemodel_Company_Selected_User_Settings.setRowCount(0);
                for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList) {
		    tablemodel_Company_Selected_User_Settings.setRowCount(tablemodel_Company_Selected_User_Settings.getRowCount() + 1);
		    tablemodel_Company_Selected_User_Settings.setValueAt(companySettingsDTO.getCompany_name(), tablemodel_Company_Selected_User_Settings.getRowCount() - 1, 0);
		    tablemodel_Company_Selected_User_Settings.setValueAt(companySettingsDTO.getCompany_id(), tablemodel_Company_Selected_User_Settings.getRowCount() - 1, 1);
                }

                // remove companies which are present in selected_company_table
                // from available_company table
                for (int i = 0; i < tblUser_Selected_Company.getRowCount(); i++) {
                    // company_nmae and company_id from seletecd table
		    String company_Name = tblUser_Selected_Company.getValueAt(i, 0).toString();
		    String company_ID = tblUser_Selected_Company.getValueAt(i, 1).toString();
                    for (int j = 0; j < tblUser_Available_Company.getRowCount(); j++) {
			if (company_Name.equals(tblUser_Available_Company.getValueAt(j, 0)) && company_ID.equals(tblUser_Available_Company.getValueAt(j, 1).toString())) {
                            // remove row from table tableCompanyList
			    tablemodel_Company_Available_User_Settings.removeRow(j);
                            // decrease value of i(row) as one row removed
                            j--;
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDisable_Component_User_Setting() {
        txtUser_Name1.setEnabled(false);
        txtUser_ConfirmPassword1.setEnabled(false);
        txtUser_Password1.setEnabled(false);
        txtUser_EmailId1.setEnabled(false);
        txtUser_Email_Password1.setEnabled(false);
    }

    private void setEnable_Component_User_Settings() {
        txtUser_Name1.setEnabled(true);
        txtUser_ConfirmPassword1.setEnabled(true);
        txtUser_Password1.setEnabled(true);
        txtUser_EmailId1.setEnabled(true);
        txtUser_Email_Password1.setEnabled(true);
        txtUser_Name1.requestFocus();
    }

    private void bindDTOTOUser_Details_table() {
        try {
            // ADD into Table User Deatils
	    user_SettingsDTOList_User_Settings = gen.mainclass.UserSettingDAO
                    .get_User_Details("", "");
            tablemodel_User_Details_Settings.setRowCount(0);
            for (UserSettingDTO usersettingDTO : user_SettingsDTOList_User_Settings) {
                tablemodel_User_Details_Settings
                        .setRowCount(tablemodel_User_Details_Settings
                        .getRowCount() + 1);
                tablemodel_User_Details_Settings.setValueAt(
                        usersettingDTO.getUser_Name(),
                        tablemodel_User_Details_Settings.getRowCount() - 1, 0);
                // tablemodel_User_Details_Settings.setValueAt(usersettingDTO.getUser_id(),
                // tablemodel_User_Details_Settings.getRowCount() - 1, 1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    private void claerfield_User_Settings() {
        txtUser_Name1.setText("");
        txtUser_ConfirmPassword1.setText("");
        txtUser_Password1.setText("");
        txtUser_EmailId1.setText("");
        txtUser_Email_Password1.setText("");
        user_Cliked_For_Update = "";
	userSettingDTO_Class_Level = new UserSettingDTO();
    }

    private void initRole() throws SQLException {
        // ADD Role List in Combo
        List<String> groups = new ArrayList<String>();

        mapRoleandID.clear();

	mapRoleandID = gen.mainclass.UserSettingDAO.get_Role_ID("", "");
        groups = new ArrayList<String>();

        roleVector.clear();
        for (String str : mapRoleandID.keySet()) {
            if (!"Admin".equals(str)) {
                roleVector.add(str);
            }
        }

        Collections.sort(roleVector, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareToIgnoreCase(rhs);
            }
        });

        mapRoleandID = Util.getSmallCaseMap(mapRoleandID);
    }

    private void setColumnWidth_Users_Settings(JTable passedTable) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.LEFT);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        passedTable.getColumnModel().getColumn(0)
                .setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(260);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(260);

        passedTable.getColumnModel().getColumn(1)
                .setCellRenderer(centerRenderer);
        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(1).setMinWidth(65);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(65);
    }

    private void setColumnWidth_Roles_Users_Settings(JTable passedTable) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.LEFT);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        passedTable.getColumnModel().getColumn(0)
                .setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(140);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(140);

    }

    // / Stop user Settings creation validation
    // //////////////////////////////////////////////////////////////
    // / user creation validation
    // //////////////////////////////////////////////////////////////
    private Boolean Validation_For_User_Creation() {
        Boolean flag = true;

        String email = txtUser_EmailId1.getText().toString().trim();
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        String ledger_name = txtUser_Name1.getText().trim();
        Pattern p = Pattern.compile("[a-zA-Z0-9]");
        Matcher m = p.matcher(ledger_name);

	if (txtUser_Name1.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthMEDIUM) {
	    flag = false;
	    JOptionPane.showMessageDialog(CreateUser.this, "User Name Data Exceeding " + Constants.jTextFieldCharacterLengthMEDIUM + " Character Limit");
	    txtUser_Name1.requestFocus();
	} else if (txtUser_Password1.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthMEDIUM) {
	    flag = false;
	    JOptionPane.showMessageDialog(CreateUser.this, "User Password Data Exceeding " + Constants.jTextFieldCharacterLengthMEDIUM + " Character Limit");
	    txtUser_Password1.requestFocus();
	} else if (txtUser_ConfirmPassword1.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthMEDIUM) {
	    flag = false;
	    JOptionPane.showMessageDialog(CreateUser.this, "User Confirm Password Data Exceeding " + Constants.jTextFieldCharacterLengthMEDIUM + " Character Limit");
	    txtUser_ConfirmPassword1.requestFocus();
	} else if (txtUser_EmailId1.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthSMALL) {
	    flag = false;
	    JOptionPane.showMessageDialog(CreateUser.this, "User Email-ID Data Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
	    txtUser_EmailId1.requestFocus();
	} else if (txtUser_Email_Password1.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthSMALL) {
	    flag = false;
	    JOptionPane.showMessageDialog(CreateUser.this, "User Email-ID Password Data Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
	    txtUser_Email_Password1.requestFocus();
	} else if (!m.find()) {
	    
            flag = false;
            JOptionPane.showMessageDialog(CreateUser.this,
                    "Enter Valid UserName");
            txtUser_Name1.requestFocus();

        } else if (txtUser_Name1.getText().toString().trim().equals("")) {

            flag = false;
            JOptionPane.showMessageDialog(CreateUser.this, "Enter UserName");
            txtUser_Name1.requestFocus();

        } else if (txtUser_Password1.getText().toString().trim().equals("")) {

            flag = false;
            JOptionPane.showMessageDialog(CreateUser.this, "Enter Password");
            txtUser_Password1.requestFocus();

	} else if (!txtUser_Password1.getText().toString().trim().equals(txtUser_ConfirmPassword1.getText().toString().trim())) {

            flag = false;
            JOptionPane.showMessageDialog(CreateUser.this, "Password MisMatch");
            txtUser_Password1.requestFocus();

	} else if ((!matcher.matches() && !txtUser_EmailId1.getText().toString().trim().equals(""))) {

	    flag = false;
	    JOptionPane.showMessageDialog(CreateUser.this,"Enter Valid Email ID");
	    txtUser_EmailId1.requestFocus();

	} //	else if (txtUser_EmailId1.getText().toString().trim().equals("")) {
	//
	//            if (!txtUser_Email_Password1.toString().trim().isEmpty()) {
	//                flag = false;
	//                JOptionPane.showMessageDialog(CreateUser.this,
	//                        "First Enter Email ID");
	//                txtUser_EmailId1.requestFocus();
	//            }
	//
	//            // } else if
	//            // (!mapRoleandID.containsKey(tf_Role_User_Settings.getText().toLowerCase().toString()))
	//            // {
	//        }
	else if (tblselectedRole.getRowCount() == 0
                || tblselectedRole.getRowCount() < 0) {

            flag = false;
            JOptionPane.showMessageDialog(CreateUser.this, "Select valid Role");
            txtUser_EmailId1.requestFocus();

	} //	else if (!radio_User_Type_Admin_1.isSelected()
	//                && !radio_User_Type_NormalUser1.isSelected()) {
	//
	//            flag = false;
	//            JOptionPane.showMessageDialog(CreateUser.this,
	//                    "Please select User Type");
	//            radio_User_Type_Admin_1.requestFocus();
	//
	//        }
	else if (tblUser_Selected_Company.getRowCount() == 0
                || tblUser_Selected_Company.getRowCount() < 0) {

            flag = false;
            JOptionPane.showMessageDialog(CreateUser.this,
                    "Please select Company");

        } else if (!txtUser_Name1.getText().toString().trim().equals("")
                && flag_For_Component_enable_Disable_User_Settings.equals(true)) {
            for (UserSettingDTO usersettingDTO : user_SettingsDTOList_User_Settings) {
                if (usersettingDTO.getUser_Name().equals(
                        txtUser_Name1.getText().toString().trim())) {
                    flag = false;
                    JOptionPane.showMessageDialog(CreateUser.this,
                            "Already User present With this User Name");
                    txtUser_Name1.requestFocus();
                    break;
                }
            }
        }
        // else
        // if(!user_Cliked_For_Update.equals(txtUser_Name1.getText().toString().trim())
        // && flag_For_Component_enable_Disable_User_Settings.equals(false)){
        // for (UserSettingDTO usersettingDTO :
        // user_SettingsDTOList_User_Settings) {
        // System.out.println("Check Conditoin value ---------------- " +
        // usersettingDTO.getUser_Name().equals(txtUser_Name1.getText().toString().trim()));
        // if
        // (usersettingDTO.getUser_Name().equals(txtUser_Name1.getText().toString().trim()))
        // {
        // flag = false;
        // JOptionPane.showMessageDialog(null,
        // "Already User present With this User Name");
        // txtUser_Name1.requestFocus();
        // break;
        // }
        // }
        // }

        return flag;
    }
}
