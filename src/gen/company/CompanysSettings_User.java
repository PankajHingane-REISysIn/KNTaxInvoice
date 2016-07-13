package gen.company;

import gen.mainclass.FeaturesDTO;
import gen.mainclass.UserSettingDTO;
import gen.other.CompanySettings.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.miginfocom.swing.MigLayout;

public class CompanysSettings_User extends JInternalFrame {

    private JTable tablecompanyList_Company_Settings;
    DefaultTableModel tableModelCompanyList_Company_Settings = new DefaultTableModel();
    public static String CURRENT_DATABASE_Company_Settings = "";
    // public static String CURRENT_COMPANY_Company_Settings = "";
    private List<gen.other.CompanySettings.CompanySettingsDTO> companySettingsDTOList_Company_Settings = new ArrayList<gen.other.CompanySettings.CompanySettingsDTO>();
    /**
     * Launch the application.
     */
    private JPanel applicationSettingsPanel;
    private JPanel panel_1;
    private JLabel lblSelectCompanyFrom;
    private JScrollPane scrollPane;
    private JButton buttonApply_Company_Settings;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CompanysSettings_User frame = new CompanysSettings_User();
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
    public CompanysSettings_User() {
        try {
            setClosable(true);
            setTitle("Company Selection Window");
            setFrameIcon(new ImageIcon(CompanysSettings_User.class.getResource("/images/Kasturi-logo-1.png")));
            applicationSettingsPanel = new JPanel();
            getContentPane().add(applicationSettingsPanel, BorderLayout.CENTER);
            applicationSettingsPanel
                    .setLayout(new MigLayout(
                    "",
                    "[0px:100px:100px,grow,shrink 50,fill][0px:200px:200px,grow,shrink 50,fill][][0px:200px:200px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill]",
                    "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

            lblSelectCompanyFrom = new JLabel(
                    "Select Company From The Avaliable List");
            applicationSettingsPanel.add(lblSelectCompanyFrom, "cell 1 2");

            panel_1 = new JPanel();
            panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
            applicationSettingsPanel.add(panel_1, "cell 1 3 3 11,grow");
            panel_1.setLayout(new MigLayout("", "[grow]", "[grow]"));

            scrollPane = new JScrollPane();
            panel_1.add(scrollPane, "cell 0 0,grow");

            tablecompanyList_Company_Settings = new JTable();
            tablecompanyList_Company_Settings.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
                        // setFocus(event);
			    buttonApply_Company_SettingsActionPerformed(null);
			} catch (Exception ex) {
			    ex.printStackTrace();
			    Logger.getLogger(CompanysSettings_User.class.getName()).log(Level.SEVERE, null, ex);
			}
                    }
                }
            });
            tablecompanyList_Company_Settings.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    try {
                        buttonApply_Company_SettingsActionPerformed(null);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(CompanysSettings_User.this, ex.getMessage());
                    }
                }
            });
            scrollPane.setViewportView(tablecompanyList_Company_Settings);

            buttonApply_Company_Settings = new JButton("Apply");
            buttonApply_Company_Settings.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        buttonApply_Company_SettingsActionPerformed(e);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(CompanysSettings_User.this, ex.getMessage());
                    }
                }
            });
            applicationSettingsPanel.add(buttonApply_Company_Settings, "cell 2 14");

            intialise_Company_Settings();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(CompanysSettings_User.this, ex.getMessage());
        }

    }

    private void intialise_Company_Settings() throws Exception {
        initilizeGUIComponents_Company_Settings();
        setnemonic_Company_Settings();
    }

    private void initilizeGUIComponents_Company_Settings() throws Exception {
        initComponentActiveInActive_Company_Settings();
        bindDTOTOGUI_Company_Settings();
    }

    private void initComponentActiveInActive_Company_Settings() throws Exception {
        String col[] = {"CompanyName", ""};
        String data[][] = {{"", ""}};
        tableModelCompanyList_Company_Settings = new DefaultTableModel(data,
                col) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;// This causes all cells to be not editable
            }
        };
        tablecompanyList_Company_Settings
                .setModel(tableModelCompanyList_Company_Settings);
        JTableHeader header = tablecompanyList_Company_Settings
                .getTableHeader();
        header.setBackground(Color.yellow);
        tableModelCompanyList_Company_Settings.setRowCount(0);
        tableModelCompanyList_Company_Settings.setColumnCount(2);
    }

    private void setnemonic_Company_Settings() {
        buttonApply_Company_Settings.setMnemonic(KeyEvent.VK_A);
    }

    private void bindDTOTOGUI_Company_Settings() throws Exception {
        // get Company_List
        UserSettingDTO usersettingdto = new UserSettingDTO();
        usersettingdto.setUser_id(gen.dto.Constants.CURRENT_USER_ID);

        System.out.println("E#eEEEEEEEEEEEEEEEEE             ");

        // get role for user as if user is Admin then then role is 1 so visible
        // all company to him
        FeaturesDTO featuresDTO = new FeaturesDTO();
        featuresDTO = gen.mainclass.UserSettingDAO
                .get_Active_Inactive_By_User(gen.dto.Constants.CURRENT_USER_ID);

        String user_Role = featuresDTO.getRole_Name();
        System.out
                .println("E#eEEEEEEEEEEEEEEEEE        666666666666                   "
                + user_Role);

        if (!user_Role.equalsIgnoreCase("admin")) {
            System.out.println("E#eEEEEEEEEEEEEEEEEE        666666666666     ");
            companySettingsDTOList_Company_Settings = gen.mainclass.UserSettingDAO
                    .getCurrent_Company_Details_By_User(usersettingdto);
        } else {
            System.out
                    .println("E#eEEEEEEEEEEEEEEEEE        77777777777777777     ");
            companySettingsDTOList_Company_Settings = gen.other.CompanySettings.CompanySettingsDAO
                    .getComapany_List("", "");
        }

        System.out.println("E#eEEEEEEEEEEEEEEEEE        1111111111     ");

        tableModelCompanyList_Company_Settings.setRowCount(0);
        for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList_Company_Settings) {
            System.out
                    .println("E#eEEEEEEEEEEEEEEEEE        2222222222222222     ");
            tableModelCompanyList_Company_Settings
                    .setRowCount(tableModelCompanyList_Company_Settings
                    .getRowCount() + 1);
            // set into the table
            tableModelCompanyList_Company_Settings
                    .setValueAt(companySettingsDTO.getCompany_name(),
                    tableModelCompanyList_Company_Settings
                    .getRowCount() - 1, 0);
            tableModelCompanyList_Company_Settings
                    .setValueAt(companySettingsDTO.getCompany_id(),
                    tableModelCompanyList_Company_Settings
                    .getRowCount() - 1, 1);
        }
        //setColumnWidth_Company_Settings(tablecompanyList_Company_Settings);
    }

    private void buttonApply_Company_SettingsActionPerformed(java.awt.event.ActionEvent evt) throws java.lang.Exception {
        try {
            // TODO add your handling code here:
            // set current selected Company DataBase
	    System.out.println("          " + tablecompanyList_Company_Settings.getSelectedRow());
            if (tablecompanyList_Company_Settings.getSelectedRow() > -1) {
		gen.dto.Constants.CURRENT_COMPANY_ID = tableModelCompanyList_Company_Settings.getValueAt(tablecompanyList_Company_Settings.getSelectedRow(), 1).toString();

		gen.dto.Constants.CURRENT_COMPANY_NAME = tableModelCompanyList_Company_Settings.getValueAt(tablecompanyList_Company_Settings.getSelectedRow(), 0).toString();

                List<gen.other.CompanySettings.CompanySettingsDTO> companySettingsDTOList = new ArrayList<CompanySettingsDTO>();
		System.out.println("gen.dto.Constants.CURRENT_COMPANY_NAME ---------------- " + gen.dto.Constants.CURRENT_COMPANY_NAME);
		System.out.println("gen.dto.Constants.CURRENT_COMPANY_ID -------------------" + gen.dto.Constants.CURRENT_COMPANY_ID);
		companySettingsDTOList = gen.other.CompanySettings.CompanySettingsDAO.getComapany_List(gen.dto.Constants.CURRENT_COMPANY_NAME, gen.dto.Constants.CURRENT_COMPANY_ID);

                for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList) {
		    gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = companySettingsDTO.getCompany_DatabaseName();
                    gen.dto.Constants.CURRENT_COMPANY_CREATED_DATE = companySettingsDTO.getCompany_ApplicableFrom_date();
                }
		JOptionPane.showMessageDialog(this, "Selected Company : " + gen.dto.Constants.CURRENT_COMPANY_NAME);
		this.setClosed(true);
		gen.dto.Constants.Features_Call_Class = "CompanySettings_User";
	    } else {
		JOptionPane.showMessageDialog(CompanysSettings_User.this, "Select Company");
            }




        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    private void setColumnWidth_Company_Settings(JTable passedTable) throws Exception {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.LEFT);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        passedTable.getColumnModel().getColumn(0)
                .setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(360);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(360);

        passedTable.getColumnModel().getColumn(1)
                .setCellRenderer(centerRenderer);
        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(1).setMinWidth(80);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(80);
    }
}
