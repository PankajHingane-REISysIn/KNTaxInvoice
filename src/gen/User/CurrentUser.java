package gen.User;

import gen.company.Create_Company;
import gen.dto.Constants;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gen.mainclass.FeaturesDTO;
import gen.mainclass.UserSettingDTO;
import gen.other.CompanySettings.CompanySettingsDTO;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class CurrentUser extends JInternalFrame {

    private JTextField txt_Current_User_Name;
    private JPasswordField txt_Current_User_Password;
    private JPasswordField txt_Current_User_ConfirmPassword;
    private JTextField txt_Current_User_EmailId;
    private JPasswordField txt_Current_User_Email_Password;
    private JTable tblAllocatedRole_CurrentUser;
    private JTable tblCompany_Allocated_Current_User_Settings;
    private JTable tblCurrent_Company_For_Current_User_Settings;
    DefaultTableModel tablemodel_Company_Allocated_Current_User_Settings,
            tablemodel_Current_Company_For_Current_User_Settings,
            tablemodel_AllocatedRole_CurrentUser;
    Map<String, String> mapRoleandID = new HashMap<String, String>();
    Vector<String> roleVector = new Vector<String>();
    private List<gen.mainclass.UserSettingDTO> user_SettingsDTOList_Update_User_Settings = new ArrayList<gen.mainclass.UserSettingDTO>();
    private String current_User = "";
    /**
     * Launch the application.
     */
    private JButton btnCompanyCreate1;
    private JButton btnUpdate1;
    private JButton btncompanyClear1;
    private JLabel lblPassword;
    private JLabel lbl_Current_User_Role;
    private JLabel lblUsername;
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
                    CurrentUser frame = new CurrentUser();
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
    public CurrentUser() {
        setClosable(true);
        setTitle("Showing Current User Status");
        //setBounds(100, 100, 1332, 674);
        setFrameIcon(new ImageIcon(Create_Company.class.getResource("/images/Kasturi-logo-1.png")));
        panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new MigLayout(
                "",
                "[0px:200px:200px,grow,shrink 50,fill][0px:300px:300px,grow,shrink 50,fill]",
                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        lblUsername = new JLabel("Username");
        lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblUsername, "cell 0 1,alignx trailing");

        txt_Current_User_Name = new JTextField();
        txt_Current_User_Name.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    txt_Current_User_Password.requestFocus();
                }
            }
        });
        panel.add(txt_Current_User_Name, "cell 1 1,growx");
        txt_Current_User_Name.setColumns(10);

        lblPassword = new JLabel("Password");
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblPassword, "cell 0 2,alignx trailing");

        txt_Current_User_Password = new JPasswordField();
        txt_Current_User_Password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    txt_Current_User_ConfirmPassword.requestFocus();
                } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txt_Current_User_Name.requestFocus();
                }
            }
        });
        panel.add(txt_Current_User_Password, "cell 1 2,growx");

        lblConfirmPassword = new JLabel("Confirm Password");
        lblConfirmPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblConfirmPassword, "cell 0 3,alignx trailing");

        txt_Current_User_ConfirmPassword = new JPasswordField();
        txt_Current_User_ConfirmPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    txt_Current_User_EmailId.requestFocus();
                } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txt_Current_User_Password.requestFocus();
                }
            }
        });
        panel.add(txt_Current_User_ConfirmPassword, "cell 1 3,growx");

        lblEmailId = new JLabel("E-Mail ID");
        lblEmailId.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblEmailId, "cell 0 4,alignx trailing");

        txt_Current_User_EmailId = new JTextField();
        txt_Current_User_EmailId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txt_Current_User_ConfirmPassword.requestFocus();
                }
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
		    txt_Current_User_Email_Password.requestFocus();
		}
            }
        });
        panel.add(txt_Current_User_EmailId, "cell 1 4,growx");
        txt_Current_User_EmailId.setColumns(10);

        lblEmailIdPassword = new JLabel("E-Mail ID Password");
        lblEmailIdPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblEmailIdPassword, "cell 0 5,alignx trailing");

        txt_Current_User_Email_Password = new JPasswordField();
	txt_Current_User_Email_Password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txt_Current_User_EmailId.requestFocus();
                }
            }
        });
        panel.add(txt_Current_User_Email_Password, "cell 1 5,growx");

        lblRole = new JLabel("Role");
        lblRole.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblRole, "cell 0 6");

        panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.add(panel_1, "cell 1 6 1 3,grow");
        panel_1.setLayout(new MigLayout("", "[grow]", "[grow]"));

        scrollPane = new JScrollPane();
        panel_1.add(scrollPane, "cell 0 0,grow");

        tblAllocatedRole_CurrentUser = new JTable();
        scrollPane.setViewportView(tblAllocatedRole_CurrentUser);

//        lblUserType = new JLabel("User Type");
//        lblUserType.setHorizontalAlignment(SwingConstants.RIGHT);
//        panel.add(lblUserType, "cell 0 9");
//
//        radio_User_Type_Admin_1 = new JRadioButton("Administator");
//        panel.add(radio_User_Type_Admin_1, "flowx,cell 1 9");

        lblAvailableCompany = new JLabel("Available Company");
        lblAvailableCompany.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblAvailableCompany, "cell 0 10");

        panel_2 = new JPanel();
        panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.add(panel_2, "cell 1 10 1 3,grow");
        panel_2.setLayout(new MigLayout("", "[grow]", "[grow]"));

        scrollPane_1 = new JScrollPane();
        panel_2.add(scrollPane_1, "cell 0 0,grow");

        tblCompany_Allocated_Current_User_Settings = new JTable();
        tblCompany_Allocated_Current_User_Settings
                .addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedCompany_index = tblCompany_Allocated_Current_User_Settings
                        .getSelectedRow();
                // selected company_Name
                String selectedCompany_Name = tblCompany_Allocated_Current_User_Settings
                        .getValueAt(selectedCompany_index, 0)
                        .toString();
                // selected company_ID
                String selectedCompany_ID = tblCompany_Allocated_Current_User_Settings
                        .getValueAt(selectedCompany_index, 1)
                        .toString();

                // selected row must be greater than 0
                if (tblCompany_Allocated_Current_User_Settings
                        .getSelectedRow() >= 0) {
                    // getRowCount row must be greater than 0
                    if (tblCompany_Allocated_Current_User_Settings
                            .getRowCount() > 0) {
                        tablemodel_Current_Company_For_Current_User_Settings
                                .setRowCount(0);
                        // find selected row from tableCompanyList
                        // move data of selected row from
                        // tableCompanyList to tableCompanyRestoreUp
                        tablemodel_Current_Company_For_Current_User_Settings
                                .setRowCount(tablemodel_Current_Company_For_Current_User_Settings
                                .getRowCount() + 1);
                        tablemodel_Current_Company_For_Current_User_Settings
                                .setValueAt(selectedCompany_Name,
                                tablemodel_Current_Company_For_Current_User_Settings
                                .getRowCount() - 1, 0);
                        tablemodel_Current_Company_For_Current_User_Settings
                                .setValueAt(selectedCompany_ID,
                                tablemodel_Current_Company_For_Current_User_Settings
                                .getRowCount() - 1, 1);
                    }
                }
            }
        });
        scrollPane_1
                .setViewportView(tblCompany_Allocated_Current_User_Settings);

//        radio_User_Type_NormalUser1 = new JRadioButton(
//                "Normal User");
//        panel.add(radio_User_Type_NormalUser1, "cell 1 9");

        lblSelectedCompany = new JLabel("Selected Company");
        lblSelectedCompany.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblSelectedCompany, "cell 0 13");

        panel_3 = new JPanel();
        panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.add(panel_3, "cell 1 13 1 3,grow");
        panel_3.setLayout(new MigLayout("", "[grow]", "[grow]"));

        scrollPane_2 = new JScrollPane();
        panel_3.add(scrollPane_2, "cell 0 0,grow");

        tblCurrent_Company_For_Current_User_Settings = new JTable();
        tblCurrent_Company_For_Current_User_Settings
                .addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tablemodel_Current_Company_For_Current_User_Settings
                        .setRowCount(0);
            }
        });
        scrollPane_2
                .setViewportView(tblCurrent_Company_For_Current_User_Settings);

        NUButtonCreateUser1 = new JButton("Submit");
        NUButtonCreateUser1.setMnemonic('S');
        NUButtonCreateUser1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NUButtonCreateUser1ActionPerformed(e);
            }
        });
        panel.add(NUButtonCreateUser1, "flowx,cell 1 17");

        NUButtonBack1 = new JButton("Back");
        NUButtonBack1.setMnemonic('B');
        NUButtonBack1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NUButtonBack1ActionPerformed(e);
            }
        });
        panel.add(NUButtonBack1, "cell 1 17");

        intialise_Current_User_Settings();

    }

    private void NUButtonCreateUser1ActionPerformed(
            java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        if (Validation_For_User_Updation()) {

	    //Using a JPanel as the message for the JOptionPane
	    JPanel userPanel = new JPanel();
	    userPanel.setLayout(new GridLayout(2, 2));

	    JLabel usernameLbl = new JLabel("Username:");
	    JLabel passwordLbl = new JLabel("Password:");
	    JTextField username = new JTextField();
	    JPasswordField passwordFld = new JPasswordField();

	    userPanel.add(usernameLbl);
	    userPanel.add(username);
	    userPanel.add(passwordLbl);
	    userPanel.add(passwordFld);

//	    String str = JOptionPane.showInputDialog(gen.User.CurrentUser.this,
//		    "Enter Password : ", "Password", 1).trim();
	    username.requestFocus();
	    int input = JOptionPane.showConfirmDialog(gen.User.CurrentUser.this, userPanel, "Enter your password:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

	    if (input == 0) //OK Button = 0
	    {
		char[] user_Entered_Password_Char = passwordFld.getPassword();
		String user_Entered_Password = new String(user_Entered_Password_Char);
            Boolean flag = false;
            List<UserSettingDTO> userSettingDTOList = new ArrayList<UserSettingDTO>();
            userSettingDTOList = gen.mainclass.UserSettingDAO.get_User_Details(
                    gen.dto.Constants.CURRENT_USER_ID, "");
            for (UserSettingDTO userSettingDTO : userSettingDTOList) {
		    if (user_Entered_Password != null && !user_Entered_Password.isEmpty() && userSettingDTO.getUser_Password().equals(user_Entered_Password)) {
                    flag = true;
                }
            }

            if (flag) {
                List<UserSettingDTO> userSettingDTOList_Pass = new ArrayList<UserSettingDTO>();
                UserSettingDTO userSettingDTO = new UserSettingDTO();
                userSettingDTO = bindGUItoDTO_Current_Users();
                userSettingDTOList_Pass.add(userSettingDTO);
                gen.mainclass.UserSettingDAO
                        .updateUserSetting_By_User(userSettingDTOList_Pass);

                gen.other.CompanySettings.CompanySettingsDAO
                        .delete_Current_Company_By_User("");

                if (tblCurrent_Company_For_Current_User_Settings.getRowCount() > 0) {
                    for (int i = 0; i < tablemodel_Current_Company_For_Current_User_Settings
                            .getRowCount(); i++) {
                        gen.other.CompanySettings.CompanySettingsDAO
                                .insert_Current_Company(
                                tablemodel_Current_Company_For_Current_User_Settings
                                .getValueAt(i, 0).toString(),
                                tablemodel_Current_Company_For_Current_User_Settings
                                .getValueAt(i, 1).toString());
                    }
                }
                intialise_Current_User_Settings();
                JOptionPane.showMessageDialog(CurrentUser.this,
                        "Submitted Successfully ", "Password", 1);
            } else {
                JOptionPane.showMessageDialog(CurrentUser.this,
                        "Enter Correct Password.", "Password", 1);
            }
        }
    }
	txt_Current_User_Name.requestFocus();
    }

    private void NUButtonBack1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:

            CurrentUser.this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    private void intialise_Current_User_Settings() {
        initilizeGUIComponents_Current_User_Settings();
        setnemonic_Current_User_Settings();
    }

    private void initilizeGUIComponents_Current_User_Settings() {
        initComponentActiveInActive_Current_User_Settings();
        // bindDTOTOUser_Details_table();
        intiliase_DATA_Current_User_Settings();
        intiliase_Current_Company_Current_User_Settings();
        // claerfield_User_Settings();
        // setEnable_Component_User_Settings();
    }

    private void initComponentActiveInActive_Current_User_Settings() {
        String col[] = {"CompanyName", ""};
        String data[][] = {{"", ""}};
        tablemodel_Company_Allocated_Current_User_Settings = new DefaultTableModel(
                data, col) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;// This causes all cells to be not editable
            }
        };
        tblCompany_Allocated_Current_User_Settings
                .setModel(tablemodel_Company_Allocated_Current_User_Settings);
        JTableHeader header = tblCompany_Allocated_Current_User_Settings
                .getTableHeader();
        header.setBackground(Color.yellow);
        tablemodel_Company_Allocated_Current_User_Settings.setRowCount(0);
        tablemodel_Company_Allocated_Current_User_Settings.setColumnCount(2);

        String col_Current_Company_Current_User[] = {"CompanyName", ""};
        String data_Current_Company_Current_User[][] = {{"", ""}};
        tablemodel_Current_Company_For_Current_User_Settings = new DefaultTableModel(
                data_Current_Company_Current_User,
                col_Current_Company_Current_User) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;// This causes all cells to be not editable
            }
        };
        tblCurrent_Company_For_Current_User_Settings
                .setModel(tablemodel_Current_Company_For_Current_User_Settings);
        JTableHeader header_Current_Company_Current_User = tblCurrent_Company_For_Current_User_Settings
                .getTableHeader();
        header_Current_Company_Current_User.setBackground(Color.yellow);
        tablemodel_Current_Company_For_Current_User_Settings.setRowCount(0);
        tablemodel_Current_Company_For_Current_User_Settings.setColumnCount(2);

        String col_for_Role_Selected_CurrentUser[] = {"Allocated Role"};
        String data_for_Role_Selected_CurrentUser[][] = {{""}};
        tablemodel_AllocatedRole_CurrentUser = new DefaultTableModel(
                data_for_Role_Selected_CurrentUser,
                col_for_Role_Selected_CurrentUser) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;// This causes all cells to be not editable
            }
        };
        tblAllocatedRole_CurrentUser
                .setModel(tablemodel_AllocatedRole_CurrentUser);
        JTableHeader header_for_Selected_role = tblAllocatedRole_CurrentUser
                .getTableHeader();
        header_for_Selected_role.setBackground(Color.yellow);
        tablemodel_AllocatedRole_CurrentUser.setRowCount(0);
        tablemodel_AllocatedRole_CurrentUser.setColumnCount(2);
        setColumnWidth_Roles_Users_Settings(tblAllocatedRole_CurrentUser);

        setColumnWidth_Current_Users_Settings(tblCompany_Allocated_Current_User_Settings);
        setColumnWidth_Current_Users_Settings(tblCurrent_Company_For_Current_User_Settings);
        setColumnWidth_Allocated_Roles_Current_Users_Settings(tblAllocatedRole_CurrentUser);
    }

    private void intiliase_DATA_Current_User_Settings() {
        user_SettingsDTOList_Update_User_Settings = gen.mainclass.UserSettingDAO
                .get_User_Details("", "");
        List<UserSettingDTO> userSettingDTOList = gen.mainclass.UserSettingDAO
                .get_User_Details(gen.dto.Constants.CURRENT_USER_ID.toString(),
                "");

        for (UserSettingDTO userSettingDTO : userSettingDTOList) {
            current_User = userSettingDTO.getUser_Name();
            txt_Current_User_Name.setText(userSettingDTO.getUser_Name());
            txt_Current_User_Password
                    .setText(userSettingDTO.getUser_Password());
            txt_Current_User_ConfirmPassword.setText(userSettingDTO
                    .getUser_Password());
            txt_Current_User_EmailId.setText(userSettingDTO.getUser_Email_ID());
            txt_Current_User_Email_Password.setText(userSettingDTO
                    .getUser_Email_Password());

            String role = "";
            for (Map.Entry<String, String> e : mapRoleandID.entrySet()) {

                if (e.getValue().equals("" + userSettingDTO.getUser_Role())) {
                    role = e.getKey();
                }

            }
            //lbl_Current_User_Role.setText(role);
            // Set Data to table Selected role
            List<FeaturesDTO> featuresDTOList = new ArrayList<FeaturesDTO>();
            featuresDTOList = gen.mainclass.UserSettingDAO
                    .getRoleByUsers(userSettingDTO.getUser_id());
            tablemodel_AllocatedRole_CurrentUser.setRowCount(0);
            for (FeaturesDTO featuresDTO : featuresDTOList) {
                tablemodel_AllocatedRole_CurrentUser
                        .setRowCount(tablemodel_AllocatedRole_CurrentUser
                        .getRowCount() + 1);
                tablemodel_AllocatedRole_CurrentUser.setValueAt(
                        featuresDTO.getRole_Name(),
                        tablemodel_AllocatedRole_CurrentUser.getRowCount() - 1,
                        0);
            }

            List<CompanySettingsDTO> companySettingsDTOList = new ArrayList<CompanySettingsDTO>();
            companySettingsDTOList = gen.mainclass.UserSettingDAO
                    .getCurrent_Company_Details_By_User(userSettingDTO);

            tablemodel_Company_Allocated_Current_User_Settings.setRowCount(0);
            for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList) {
                tablemodel_Company_Allocated_Current_User_Settings
                        .setRowCount(tablemodel_Company_Allocated_Current_User_Settings
                        .getRowCount() + 1);
                tablemodel_Company_Allocated_Current_User_Settings.setValueAt(
                        companySettingsDTO.getCompany_name(),
                        tablemodel_Company_Allocated_Current_User_Settings
                        .getRowCount() - 1, 0);
                tablemodel_Company_Allocated_Current_User_Settings.setValueAt(
                        companySettingsDTO.getCompany_id(),
                        tablemodel_Company_Allocated_Current_User_Settings
                        .getRowCount() - 1, 1);
            }
        }
    }

    private void setnemonic_Current_User_Settings() {
        NUButtonCreateUser1.setMnemonic(KeyEvent.VK_S);
        NUButtonBack1.setMnemonic(KeyEvent.VK_B);
    }

    private void setColumnWidth_Current_Users_Settings(JTable passedTable) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.LEFT);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        passedTable.getColumnModel().getColumn(0)
                .setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(280);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(280);

        passedTable.getColumnModel().getColumn(1)
                .setCellRenderer(centerRenderer);
        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(1).setMinWidth(50);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(50);
    }

    private void setColumnWidth_Allocated_Roles_Current_Users_Settings(
            JTable passedTable) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.LEFT);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        passedTable.getColumnModel().getColumn(0)
                .setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(350);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(350);
    }

    private UserSettingDTO bindGUItoDTO_Current_Users() {
        UserSettingDTO userSettingDTO = new UserSettingDTO();
        userSettingDTO.setUser_id(gen.dto.Constants.CURRENT_USER_ID);
        userSettingDTO.setUser_Name(txt_Current_User_Name.getText().toString()
                .trim());
        userSettingDTO.setUser_Password(txt_Current_User_Password.getText()
                .toString().trim());
        userSettingDTO.setUser_Email_ID(txt_Current_User_EmailId.getText()
                .toString().trim());
        userSettingDTO.setUser_Email_Password(txt_Current_User_Email_Password
                .getText().toString().trim());

//        int user_Type = 0;
//        if (radio_User_Type_Admin_1.isSelected()) {
//            user_Type = 1;
//        } else if (radio_User_Type_NormalUser1.isSelected()) {
//            user_Type = 2;
//        }
//        userSettingDTO.setUser_Type(user_Type);
        return userSettingDTO;
    }

    private void intiliase_Current_Company_Current_User_Settings() {
        List<CompanySettingsDTO> companySettingsDTOList = new ArrayList<CompanySettingsDTO>();
        companySettingsDTOList = gen.mainclass.UserSettingDAO
                .getCurrentCompany_UserID(gen.dto.Constants.CURRENT_USER_ID);

        tablemodel_Current_Company_For_Current_User_Settings.setRowCount(0);
        for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList) {
            tablemodel_Current_Company_For_Current_User_Settings
                    .setRowCount(tablemodel_Current_Company_For_Current_User_Settings
                    .getRowCount() + 1);
            tablemodel_Current_Company_For_Current_User_Settings.setValueAt(
                    companySettingsDTO.getCompany_name(),
                    tablemodel_Current_Company_For_Current_User_Settings
                    .getRowCount() - 1, 0);
            tablemodel_Current_Company_For_Current_User_Settings.setValueAt(
                    companySettingsDTO.getCompany_id(),
                    tablemodel_Current_Company_For_Current_User_Settings
                    .getRowCount() - 1, 1);
        }
        txt_Current_User_Name.requestFocus();
    }

    private void setColumnWidth_Roles_Users_Settings(JTable passedTable) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.LEFT);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        passedTable.getColumnModel().getColumn(0)
                .setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(350);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(350);

    }

    private Boolean Validation_For_User_Updation() {
        Boolean flag = true;

        String email = txt_Current_User_EmailId.getText().toString().trim();
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        String ledger_name = txt_Current_User_Name.getText().trim();
        Pattern p = Pattern.compile("[a-zA-Z0-9]");
        Matcher m = p.matcher(ledger_name);

	if (txt_Current_User_Name.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthMEDIUM) {
	    flag = false;
	    JOptionPane.showMessageDialog(CurrentUser.this, "User Name Data Exceeding " + Constants.jTextFieldCharacterLengthMEDIUM + " Character Limit");
	    txt_Current_User_Name.requestFocus();
	} else if (txt_Current_User_Password.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthMEDIUM) {
	    flag = false;
	    JOptionPane.showMessageDialog(CurrentUser.this, "User Password Data Exceeding " + Constants.jTextFieldCharacterLengthMEDIUM + " Character Limit");
	    txt_Current_User_Password.requestFocus();
	} else if (txt_Current_User_ConfirmPassword.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthMEDIUM) {
	    flag = false;
	    JOptionPane.showMessageDialog(CurrentUser.this, "User Confirm Password Data Exceeding " + Constants.jTextFieldCharacterLengthMEDIUM + " Character Limit");
	    txt_Current_User_ConfirmPassword.requestFocus();
	} else if (txt_Current_User_EmailId.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthSMALL) {
	    flag = false;
	    JOptionPane.showMessageDialog(CurrentUser.this, "User Email-ID Data Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
	    txt_Current_User_EmailId.requestFocus();
	} else if (txt_Current_User_Email_Password.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthSMALL) {
	    flag = false;
	    JOptionPane.showMessageDialog(CurrentUser.this, "User Email-ID Password Data Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
	    txt_Current_User_Email_Password.requestFocus();
	} else if (!m.find()) {
            flag = false;
            JOptionPane.showMessageDialog(CurrentUser.this,
                    "Enter Valid UserName");
            txt_Current_User_Name.requestFocus();
        } else if (txt_Current_User_Name.getText().toString().trim().equals("")) {

            flag = false;
            JOptionPane.showMessageDialog(CurrentUser.this, "Enter UserName");
            txt_Current_User_Name.requestFocus();

        } else if (txt_Current_User_Password.getText().toString().trim()
                .equals("")) {

            flag = false;
            JOptionPane.showMessageDialog(CurrentUser.this, "Enter Password");
            txt_Current_User_Password.requestFocus();

        } else if (!txt_Current_User_Password
                .getText()
                .toString()
                .trim()
                .equals(txt_Current_User_ConfirmPassword.getText().toString()
                .trim())) {

            flag = false;
            JOptionPane
                    .showMessageDialog(CurrentUser.this, "Password MisMatch");
            txt_Current_User_Password.requestFocus();

	} else if ((!matcher.matches() && !txt_Current_User_EmailId.getText().toString().trim().equals(""))) {
            flag = false;
            JOptionPane.showMessageDialog(CurrentUser.this,"Enter Valid Email ID");
            txt_Current_User_EmailId.requestFocus();

        } 
//	else if (txt_Current_User_EmailId.getText().toString().trim()
//                .equals("")) {
//
//            if (!txt_Current_User_Email_Password.toString().trim().isEmpty()) {
//                flag = false;
//                JOptionPane.showMessageDialog(CurrentUser.this,
//                        "First Enter Email ID");
//                txt_Current_User_EmailId.requestFocus();
//            }
//
//            // } else if
//            // (!mapRoleandID.containsKey(tf_Role_User_Settings.getText().toLowerCase().toString()))
//            // {
//        }

        // else if
        // (!txt_Current_User_Name.getText().toString().trim().equals("")) {
        // for (UserSettingDTO usersettingDTO :
        // user_SettingsDTOList_Update_User_Settings) {
        // if
        // (usersettingDTO.getUser_Name().equals(txt_Current_User_Name.getText().toString().trim()))
        // {
        // flag = false;
        // JOptionPane.showMessageDialog(Currentuser.this,
        // "Already User present With this User Name");
        // txt_Current_User_Name.requestFocus();
        // break;
        // }
        // }
        // }

        int count = 0;
        for (UserSettingDTO usersettingDTO : user_SettingsDTOList_Update_User_Settings) {
            if (usersettingDTO.getUser_Name().equals(
                    txt_Current_User_Name.getText().toString().trim())) {
                // flag = false;
                // JOptionPane.showMessageDialog(Currentuser.this,
                // "Already User present With this User Name");
                // txt_Current_User_Name.requestFocus();
                count++;
                // break;
            }
        }

        if (count == 2) {
            flag = false;
            JOptionPane.showMessageDialog(CurrentUser.this,
                    "Already User present With this User Name");
            txt_Current_User_Name.requestFocus();
        }

        return flag;
    }
    // ///////////////////// Current User Call
    // End/////////////////////////////////////////////////////////////
}
