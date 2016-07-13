package gen.mainclass;

import AdSuMuDiNetworks.TestEmailSender;
import AdSuMuDiNetworks.TestSMS;
import AdSuMuDiNetworks.TestSMSDTO;
import com.toedter.calendar.JDateChooser;
import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.dto.Label;
import gen.dto.Util;
import gen.other.AdSuMuDiSettingsOLD.AdSuMuDiSettingsDAO;
import gen.other.CompanySettings.CompanySettingsDAO;
import gen.other.CompanySettings.CompanySettingsDTO;
import gen.other.CompanySettings.UnzipUtility;
import gen.other.DatabaseBackupRestore.Company_InformationDTO;
import gen.other.print.PrinterSettings;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.miginfocom.swing.MigLayout;
import org.quartz.Scheduler;
import com.toedter.calendar.JDateChooser;
import exception.FieldValidationException;
import gen.accountvoucher.sale.SaleForm;

@SuppressWarnings("serial")
public class AdSuMuDiSettingsEclipseGUI extends JInternalFrame {

    private JTextField jTextFieldPaginationValue;
    private JTextField textField_1;
    private JPasswordField passwordField;
    private JTable tablecompanyList_Company_Settings;
    private JTable tableCompanyList_Database_Splitng;
    private JTextField txtNameOfGod1;
    private JTextField txtCompanyName1;
    private JTextField txtCompanyAlias1;
    private JTextField txtCompanyContact1;
    private JTextField txtCompanyMail1;
    private JTextField txtCompanyITN1;
    private JTextField txtCompanySTN1;
    private JTextField jtextFieldLBTNo;
    private JTextField txtCompanyCSTNo1;
    private JTextField txtCompanySignature1;
    private JTextField txtCompanyIdNo;
    private JTextField txtCompanyTagLine1;
    private JTextArea txtCompanyAddress1;
    private JTextArea txtCompanyDeclaration1;
    private JTextArea txtCompanyTermsConditions1;
    private JTable tablecompanyList_Company_Delete;
    private JTextField textField_15;
    private JTable tableCompanyList_Company_Back_UP;
    private JTable tableCompanyBackUp_Company_Back_UP;
    private JTextField txtSourcePath_Company_Restore;
    private JTable tableCompanyList_Company_Restore;
    private JTable tableCompanyRestoreUp_Company_Restore;
    private JTextField txtUser_Name1;
    private JPasswordField txtUser_Password1;
    private JPasswordField txtUser_ConfirmPassword1;
    private JPasswordField txtUser_Email_Password1 = new JPasswordField();
    private JTextField txtUser_EmailId1;
    private JTextField jPasswordFieldPassword = new JTextField();
//    private JPasswordField txtUser_Email_Password1;
    private JTable tblavailableRole;
    private JTable tblselectedRole;
    private JTable tblUser_Available_Company;
    private JTable tblUser_Selected_Company;
    private JTable tblUser_Details_for_Admin;
    private JTextField txt_Current_User_Name;
    private JTextField jTextFieldSender = new JTextField();
    private JTextField txtDestinationPath_Company_Back_UP;
    private JPasswordField txt_Current_User_Password;
    private JPasswordField txt_Current_User_ConfirmPassword;
    private JTextField txt_Current_User_EmailId;
    private JTextField txt_Current_User_Email_Password = new JTextField();
//    private JPasswordField txt_Current_User_Email_Password;
    private JTable table_12;
    private JTable tblCompany_Allocated_Current_User_Settings;
    private JTable tblCurrent_Company_For_Current_User_Settings;
    private JTable tblAllocatedRole_CurrentUser;
    private JTabbedPane jTabbedPane1;
    private JTabbedPane jTabbedPane2;
    private JTabbedPane jTabbedPane3;
    private JCheckBox texture;
    private JCheckBox aero;
    private JCheckBox luna;
    private JCheckBox aluminium;
    private JCheckBox smart;
    private JCheckBox fast;
    private JCheckBox graphite;
    private JCheckBox mint;
    private JCheckBox acryl;
    private JCheckBox macwin;
    private JLabel jPanelPreview;
    private JCheckBox jCheckBoxStockItemName;
    private JCheckBox jCheckBoxEnableSMSSend;
    private JCheckBox jCheckBoxDisableSMSSend;
    private JCheckBox jCheckBoxQuantity;
    private JCheckBox jCheckBoxEnableSMSSendWithAsk;
    private JCheckBox jCheckBoxAmount;
    private JCheckBox jCheckBoxEnableEMail;
    private JCheckBox jCheckBoxDisableEMail;
    private JCheckBox jCheckBoxAskToSendEMail;
    private JRadioButton optNo;
    private JRadioButton optYes;
    private JRadioButton optPdf;
    private JRadioButton optHtml;
    private JRadioButton optWord;
    private JRadioButton optExcel;
    private JRadioButton optA4;
    private JRadioButton optA5;
    private JRadioButton optAsk;
    private JRadioButton radio_User_Type_Admin_1;
    private JRadioButton radio_User_Type_NormalUser1;
    private JButton btnPath_Company_Back_UP;
    private JButton btnBackUP_Company_Back_UP;
    private JButton buttonFilePath_Company_Restore;
    private JButton btnRestore_Company_Restore;
    private JButton btnDataSpliting_Database_Splitng;
    private JButton btnDelete1;
    private JButton btnupdateLogo;
    private JButton btnClearLogo;
    private JButton buttonExit_Company_Delete;
    private JButton NUButtonCreateUser1;
    private JButton NUButtonBack1 = new JButton();
    private JButton btnCompanyCreate1;
    private JButton btncompanyClear1;
    private JButton btnUpdate1;
    private JButton buttonApply_Company_Settings;
    private JButton buttonExit_Company_Settings;
    private JButton buttonApply;
    private JButton buttonExit;
    private JDateChooser fromJDateChooser_Database_Splitng = new JDateChooser();
    private JLabel lblcompany_ID1;
    private JLabel label;
    private JLabel label_1;
    private JLabel lblApplicableForm;
    private JDateChooser JdateChooser_CompanyApplicableFrom;
    private JLabel LogoPanelView1 = new JLabel();
    private JLabel lblAlias;
    private JLabel lblUserType;
    private JLabel lbl_Current_User_Role = new JLabel();
    private JLabel jLabel2;
    private JLabel lblChangeThemeOf;
    private JLabel lblPreviewDisplay;
    private JLabel lblPagination;
    private JLabel lblSmsSettings;
    private JButton jButtonSMSSettingsOK;
    private JLabel lblEmailSettings;
    private JLabel lblEmailId;
    private JLabel lblPassword;
    private JButton jButtonEMailOK2;
    private JButton jButtonApply;
    private JButton jButtonExit;
    private JButton btnSave;
    private JButton btnBack_1;
    private JButton btnNewButton_4;
    private JLabel lblDestination;
    private JLabel lblExampleccompanydatafilesql;
    private JLabel lblSource;
    private JLabel lblExamplec;
    private JLabel lblNameOfCompany;
    private JLabel lblTagline;
    private JLabel lblAddress;
    private JLabel lblEmailId_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel lblCstNumber;
    private JLabel lblNewLabel_4;
    private JLabel lblLbtNumber;
    private JLabel lblTermsAndConditions;
    private JLabel lblSignature;
    private JLabel lblcompanyIdNo;
    private JLabel lblUsername;
    private JLabel lblNewLabel_5;
    private JLabel lblConfirmPassword;
    private JLabel lblEmailId_2;
    private JLabel lblEmailIdPassword;
    private JLabel lblRole;
    private JLabel lblNewLabel_6;
    private JLabel lblSelectedCompany;
    private JButton btnNewUser1;
    private JButton btnNewButton_12;
    private JButton NUButtonDeleteUser1;
    private JLabel lblUsername_1;
    private JLabel lblPassword_1;
    private JLabel lblConfirmPassword_1;
    private JLabel lblEmailId_3;
    private JLabel lblEmailIdPassword_1;
    private JLabel lblRole_1;
    private JLabel lblUserTypr;
    private JRadioButton rdbtnAdministrator_1;
    private JRadioButton rdbtnNormalUser_1;
    private JLabel lblAvailableCompany;
    private JLabel lblDefaultCompany;
    private JButton NUButtonCreateUser3;
    private JButton btnBack;
    private JPanel panel_14;
    private JPanel panel_15;
    private JPanel panel_16;
    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//	EventQueue.invokeLater(new Runnable() {
//	    public void run() {
//		try {
//		    AdSuMuDiSettingsEclipseGUI frame = new AdSuMuDiSettingsEclipseGUI();
//		    frame.setVisible(true);
//		} catch (Exception e) {
//		    e.printStackTrace();
//		}
//	    }
//	});
//    }
    static String operatingSystemDrive = System.getenv("SystemDrive");
    private static String installedServerPath = "";
    String choice = "";
    String SMSchoice = "";
    String EMailchoice = "";
    String stockItemName = "";
    String stockItemQuantity = "";
    String stockItemAmount = "";
    // String enableSMSSending = "";
    // String disableSMSSending = "";
    // String enableSMSSendingWithAsk = "";
    Map<String, String> map_Field_With_ID = new HashMap<String, String>();
    Map<String, String> map_checkBoxValue = new HashMap<String, String>();
    ResultSet resultSet;
    String q = "";
    JTextField tf;
    private boolean hide_flag = false;
    // ArrayList<String> GroupItems=new ArrayList<String>();
    // private final Vector<String> v = new Vector<String>();
    JTextField tfChangePassword = new JTextField();
    Vector<String> vChangePassword = new Vector<String>();
    JTextField tfChangeEMailSettings = new JTextField();
    Vector<String> vChangeEMailSettings = new Vector<String>();
    JTextField tfChangeUserRightSettings = new JTextField();
    Vector<String> vChangeUserRightSettings = new Vector<String>();
//    public static int flagDirectPrint = 1;
//    public static int flagPrintPageSize = 1;
//    public static int flagPrintPageFormat = 1;
    byte[] b1;
    private String source = null;
    static int control = 0;
    // Company Selection variable
    DefaultTableModel tableModelCompanyList_Company_Settings = new DefaultTableModel();
    public static String CURRENT_DATABASE_Company_Settings = "";
    public static String CURRENT_COMPANY_Company_Settings = "";
    private List<gen.other.CompanySettings.CompanySettingsDTO> companySettingsDTOList_Company_Settings = new ArrayList<gen.other.CompanySettings.CompanySettingsDTO>();
    // ///////////////////////// Company Back UP variables
    DefaultTableModel tablemodelCompany_Company_Back_UP,
            tablemodelBackUPCompany_Company_Back_UP;
    // company_list to store .SQL file
    List<String> company_SQLTxtFile_List_Company_Back_UP = new ArrayList<String>();
    // to store .Enc file
    List<String> company_List_Enc_Company_Back_UP = new ArrayList<String>();
    File fileChooser_Company_Back_UP = null;
    static int BUFFER_SIZE = 1048576;
    static String zipArchieveName_Company_Back_UP = "";
    // company_Information_DTOList call when Form open
    private List<CompanySettingsDTO> company_Information_DTOList_Company_Back_UP = new ArrayList<CompanySettingsDTO>();
    private static Cipher ecipher_Company_Back_UP;
    private static Cipher dcipher_Company_Back_UP;
    private static byte[] iv = {(byte) 0xB2, (byte) 0x12, (byte) 0xD5,
        (byte) 0xB2, (byte) 0x44, (byte) 0x21, (byte) 0xC3, (byte) 0xC3,
        (byte) 0xB2, (byte) 0x12, (byte) 0xD5, (byte) 0xB2, (byte) 0x44,
        (byte) 0x21, (byte) 0xC3, (byte) 0xC3,};
    // ///////////////////////// Company Restore variables
    DefaultTableModel tablemodelCompany_Company_Restore,
            tablemodelRestoreCompany_Company_Restore;
    List<String> company_List_Clicked_Company_Restore = new ArrayList<String>();
    List<CompanySettingsDTO> company_List_Restore_Company_Restore = new ArrayList<CompanySettingsDTO>();
    List<String> company_Database_List_Restore = new ArrayList<String>();
    List<String> company_SQLFile_List_Restore = new ArrayList<String>();
    // Map<String, String> map_Database_And_File_Name = new HashMap<String,
    // String>();
    List<Company_InformationDTO> company_InformationDTOList_Company_Restore = new ArrayList<Company_InformationDTO>();
    List<Company_InformationDTO> company_InformationDTO_Final_List_Company_Restore = new ArrayList<Company_InformationDTO>();
    List<CompanySettingsDTO> company_Full_InformationDTOList_Company_Restore = new ArrayList<CompanySettingsDTO>();
    List<String> unZip_Company_File_List_Company_Restore = new ArrayList<String>();
    String path_Company_Restore = "";
    File fileChooser_Company_Restore = null;
    private static Cipher ecipher_Company_Restore;
    private static Cipher dcipher_Company_Restore;
    // /////////////////// Database Splitings
    List<CompanySettingsDTO> company_ListDTO_Database_Splitng = new ArrayList<CompanySettingsDTO>();
    private boolean hide_flag_Database_Splitng = false;
    private JTextField tfDatePicker_Database_Splitng = null;
    private JTextField tfDatePicker_Company_Applicable_From;
    DefaultTableModel tablemodelCompany_Database_Splitng;
    private String selected_Company_Name_Database_Splitng = "";
    private String selected_Company_id_Database_Splitng = "";
    // /////////////////// Company Alter
    // company_Cliked_Name and company_Cliked_Database_Name --- use to alter
    // data of main database & image present in company database of update
    // company
    String database_Name_For_Newly_Company = "";
    String company_Cliked_Name = "";
    String company_Cliked_Database_Name = "";
    DefaultTableModel tableModelCompanyList_Company_Delete = new DefaultTableModel();
    private List<gen.other.CompanySettings.CompanySettingsDTO> companySettingsDTOList_Company_Delete = new ArrayList<gen.other.CompanySettings.CompanySettingsDTO>();
    gen.other.CompanySettings.CompanySettingsDTO companySettingsDTO = new gen.other.CompanySettings.CompanySettingsDTO();
    // ////////////////////////////// for timer
    // gen.mainclass.SetWaitTimer settimer = new gen.mainclass.SetWaitTimer();
    final String dir = System.getProperty("user.dir");
    JDialog dialog = new JDialog();
    JOptionPane optionPane = new JOptionPane("",
            JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null,
            new Object[]{}, null);
    // for use settings
    DefaultTableModel tablemodel_Company_Available_User_Settings,
            tablemodel_Company_Selected_User_Settings,
            tablemodel_User_Details_Settings,
            tablemodel_AvailableRole_User_Settings,
            tablemodel_SelectedRole_User_Settings;
    private List<gen.other.CompanySettings.CompanySettingsDTO> companySettingsDTOList_User_Settings = new ArrayList<gen.other.CompanySettings.CompanySettingsDTO>();
    private List<gen.mainclass.UserSettingDTO> user_SettingsDTOList_User_Settings = new ArrayList<gen.mainclass.UserSettingDTO>();
    private boolean hide_flag_User_Settings = false;
    // private JTextField tf_Role_User_Settings = null;
    Map<String, String> mapRoleandID = new HashMap<String, String>();
    Vector<String> roleVector = new Vector<String>();
    Boolean flag_For_Component_enable_Disable_User_Settings = true;
    private UserSettingDTO userSettingDTO_Class_Level = new UserSettingDTO();
    private String user_Cliked_For_Update = "";
    private String user_ID_Delete = "";
    // for Current User
    DefaultTableModel tablemodel_Company_Allocated_Current_User_Settings,
            tablemodel_Current_Company_For_Current_User_Settings,
            tablemodel_AllocatedRole_CurrentUser;
    private Scheduler sch;
    private List<gen.mainclass.UserSettingDTO> user_SettingsDTOList_Update_User_Settings = new ArrayList<gen.mainclass.UserSettingDTO>();
    public static String databaseClassName = "com.mysql.jdbc.Driver";
    public static String instantURL = "jdbc:mysql://localhost";
    public static String portNo = "3308";
    public static String databaseName = "aj11";
    public static String userName = "root";
    public static String password = "adm";
    public String framework = "embedded";
    private JPanel panel_21;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JDateChooser jdateChooser_SelectYear;
    private JButton jbuttonSubmitReceiptNo;
    private JTextField jtextFieldReceiptNo;
    private JLabel lblNewLabel_7;
    //private JLabel label;
    //private JLabel label_1;

    /**
     * Create the frame.
     */
    public AdSuMuDiSettingsEclipseGUI() {
        setTitle("AdSuMuDi Settings");
        setClosable(true);
        setSize(1366, 768);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(
                Util.getImageIconPath())));
//        this.setTitle("AdSuMuDiSettings                              "
//                + " Company Name : "
        // +
        // gen.other.CompanySettings.CompanysSettings.CURRENT_COMPANY_Company_Settings);
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameOpened(InternalFrameEvent arg0) {
                Constants.checkVisibilityOfSettingsWindow = true;
                jTabbedPane1.setSelectedIndex(0);
                ActivInactiveComponent();
                initialiseGraphicsSettings();
                getInstalledServerPath();
                deactivatingUnecessaryFeatures();
            }

            @Override
            public void internalFrameClosed(InternalFrameEvent arg0) {
                System.out.println("settingsframeclosed");
                Constants.checkVisibilityOfSettingsWindow = false;
            }

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                Constants.checkVisibilityOfSettingsWindow = true;
                try {
                    // TODO add your handling code here:
                    // map for role to ID which is require for 2 tab so call
                    // here dont remove
                    initRole();

                    System.out
                            .println("Frame Activated ----------------------------------");
                    ActivInactiveComponent();

                    SetWaitTimer s = new SetWaitTimer();
//		    s.resumeWaitTimer(AdSuMuDiSettingsEclipseGUI.this);
//		    System.out.println("Tabbed Pane InternalFrame Actived");
                    initialiseGraphicsSettings();
                    deactivatingUnecessaryFeatures();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        });
        //setBounds(100, 100, 1332, 674);
        getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));

        jTabbedPane1 = new JTabbedPane(JTabbedPane.TOP);
        jTabbedPane1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int seleted_company_Index = 0;
                seleted_company_Index = jTabbedPane1.getSelectedIndex();
                System.out.println("MOuse cliked -----------------------------"
                        + seleted_company_Index);
                if (seleted_company_Index == 0) {
                    initialiseGraphicsSettings();
                } else if (seleted_company_Index == 1) {
                    initialiseNetworkSettings();
                } else if (seleted_company_Index == 2) {
                    initialiseDeviceAndHardwareSettings();
                } else if (seleted_company_Index == 3) {
                    jTabbedPane2MouseClicked(e);
                } else if (seleted_company_Index == 4) {
//		    jTabbedPane3MouseClicked(e);
                    intialise_User_Settings();
                } else if (seleted_company_Index == 5) {
                    intialise_Current_User_Settings();
                }
            }
        });
        getContentPane().add(jTabbedPane1, "cell 0 0,grow");

        JPanel panel = new JPanel();
        jTabbedPane1.addTab("Graphics Settings", null, panel, null);
        panel.setLayout(new MigLayout(
                "",
                "[0px:525px:525px,grow,shrink 50,fill][0px:150px:150px,grow,shrink 50,fill][grow,shrink 50]",
                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][grow][0px:25px:25px,grow,shrink 50]"));

        JPanel panel_6 = new JPanel();
        panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.add(panel_6, "cell 0 0 1 3,grow");
        panel_6.setLayout(new MigLayout(
                "",
                "[0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill]",
                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        lblChangeThemeOf = new JLabel("Change Theme Of Your Application");
        panel_6.add(lblChangeThemeOf, "cell 0 0");

        jPanelPreview = new JLabel("");
        jPanelPreview.setMinimumSize(new Dimension(50, 50));
        jPanelPreview.setMaximumSize(new Dimension(325, 250));
        jPanelPreview.setPreferredSize(new Dimension(325, 250));
        jPanelPreview.setSize(350, 350);
        jPanelPreview.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_6.add(jPanelPreview, "cell 1 1 4 7");

        lblPreviewDisplay = new JLabel("Preview Display");
        panel_6.add(lblPreviewDisplay, "cell 3 8");

        texture = new JCheckBox("Texture");

        texture.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (texture.isSelected()) {
                    acryl.setSelected(false);
                    mint.setSelected(false);
                    fast.setSelected(false);
                    graphite.setSelected(false);
                    smart.setSelected(false);
                    aero.setSelected(false);
                    aluminium.setSelected(false);
                    macwin.setSelected(false);
                    luna.setSelected(false);

                    choice = "01";

                    Image img = new javax.swing.ImageIcon(dir
                            + "/images/Texture.png").getImage();
                    img = img.getScaledInstance(jPanelPreview.getWidth(),
                            jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledImageIcon = new ImageIcon(img);
                    jPanelPreview.setIcon(scaledImageIcon);
                }

            }
        });

//	texture.addMouseListener(new MouseAdapter() {
//	    @Override
//	    public void mouseClicked(MouseEvent arg0) {
//		if (texture.isSelected()) {
//		    acryl.setSelected(false);
//		    mint.setSelected(false);
//		    fast.setSelected(false);
//		    graphite.setSelected(false);
//		    smart.setSelected(false);
//		    aero.setSelected(false);
//		    aluminium.setSelected(false);
//		    macwin.setSelected(false);
//		    luna.setSelected(false);
//
//		    choice = "01";
//
//		    Image img = new javax.swing.ImageIcon(dir
//			    + "/images/Texture.png").getImage();
//		    img = img.getScaledInstance(jPanelPreview.getWidth(),
//			    jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
//		    ImageIcon scaledImageIcon = new ImageIcon(img);
//		    jPanelPreview.setIcon(scaledImageIcon);
//		}
//	    }
//	});
        panel_6.add(texture, "cell 1 10");

        acryl = new JCheckBox("Acryl");

        acryl.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (acryl.isSelected()) {

                    texture.setSelected(false);
                    aero.setSelected(false);
                    aluminium.setSelected(false);
                    macwin.setSelected(false);
                    luna.setSelected(false);
                    mint.setSelected(false);
                    fast.setSelected(false);
                    graphite.setSelected(false);
                    smart.setSelected(false);

                    choice = "06";

                    Image img = new javax.swing.ImageIcon(dir
                            + "/images/Acryl.png").getImage();
                    img = img.getScaledInstance(jPanelPreview.getWidth(),
                            jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledImageIcon = new ImageIcon(img);
                    jPanelPreview.setIcon(scaledImageIcon);
                }

            }
        });

//	acryl.addMouseListener(new MouseAdapter() {
//	    @Override
//	    public void mouseClicked(MouseEvent e) {
//		if (acryl.isSelected()) {
//
//		    texture.setSelected(false);
//		    aero.setSelected(false);
//		    aluminium.setSelected(false);
//		    macwin.setSelected(false);
//		    luna.setSelected(false);
//		    mint.setSelected(false);
//		    fast.setSelected(false);
//		    graphite.setSelected(false);
//		    smart.setSelected(false);
//
//		    choice = "06";
//
//		    Image img = new javax.swing.ImageIcon(dir
//			    + "/images/Acryl.png").getImage();
//		    img = img.getScaledInstance(jPanelPreview.getWidth(),
//			    jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
//		    ImageIcon scaledImageIcon = new ImageIcon(img);
//		    jPanelPreview.setIcon(scaledImageIcon);
//		}
//	    }
//	});
        panel_6.add(acryl, "cell 3 10");

        aero = new JCheckBox("Aero");

        aero.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (aero.isSelected()) {

                    acryl.setSelected(false);
                    mint.setSelected(false);
                    fast.setSelected(false);
                    graphite.setSelected(false);
                    smart.setSelected(false);
                    texture.setSelected(false);
                    aluminium.setSelected(false);
                    macwin.setSelected(false);
                    luna.setSelected(false);

                    choice = "02";

                    Image img = new javax.swing.ImageIcon(dir
                            + "/images/Aero.png").getImage();
                    img = img.getScaledInstance(jPanelPreview.getWidth(),
                            jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledImageIcon = new ImageIcon(img);
                    jPanelPreview.setIcon(scaledImageIcon);
                }
            }
        });

        aero.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//		if (aero.isSelected()) {
//
//		    acryl.setSelected(false);
//		    mint.setSelected(false);
//		    fast.setSelected(false);
//		    graphite.setSelected(false);
//		    smart.setSelected(false);
//		    texture.setSelected(false);
//		    aluminium.setSelected(false);
//		    macwin.setSelected(false);
//		    luna.setSelected(false);
//
//		    choice = "02";
//
//		    Image img = new javax.swing.ImageIcon(dir
//			    + "/images/Aero.png").getImage();
//		    img = img.getScaledInstance(jPanelPreview.getWidth(),
//			    jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
//		    ImageIcon scaledImageIcon = new ImageIcon(img);
//		    jPanelPreview.setIcon(scaledImageIcon);
//		}
            }
        });
        panel_6.add(aero, "cell 1 11");

        mint = new JCheckBox("Mint");

        mint.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (mint.isSelected()) {

                    texture.setSelected(false);
                    aero.setSelected(false);
                    aluminium.setSelected(false);
                    macwin.setSelected(false);
                    luna.setSelected(false);
                    acryl.setSelected(false);
                    fast.setSelected(false);
                    graphite.setSelected(false);
                    smart.setSelected(false);

                    choice = "07";

                    Image img = new javax.swing.ImageIcon(dir
                            + "/images/Mint.png").getImage();
                    img = img.getScaledInstance(jPanelPreview.getWidth(),
                            jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledImageIcon = new ImageIcon(img);
                    jPanelPreview.setIcon(scaledImageIcon);
                }
            }
        });

        mint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        panel_6.add(mint, "cell 3 11");

        aluminium = new JCheckBox("Aluminium");

        aluminium.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (aluminium.isSelected()) {

                    acryl.setSelected(false);
                    mint.setSelected(false);
                    fast.setSelected(false);
                    graphite.setSelected(false);
                    smart.setSelected(false);
                    texture.setSelected(false);
                    aero.setSelected(false);
                    macwin.setSelected(false);
                    luna.setSelected(false);

                    choice = "03";

                    Image img = new javax.swing.ImageIcon(dir
                            + "/images/Aluminium.png").getImage();
                    img = img.getScaledInstance(jPanelPreview.getWidth(),
                            jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledImageIcon = new ImageIcon(img);
                    jPanelPreview.setIcon(scaledImageIcon);
                }
            }
        });

        aluminium.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        panel_6.add(aluminium, "cell 1 12");

        fast = new JCheckBox("Fast");

        fast.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (fast.isSelected()) {

                    texture.setSelected(false);
                    aero.setSelected(false);
                    aluminium.setSelected(false);
                    macwin.setSelected(false);
                    luna.setSelected(false);
                    acryl.setSelected(false);
                    mint.setSelected(false);
                    graphite.setSelected(false);
                    smart.setSelected(false);

                    choice = "08";

                    Image img = new javax.swing.ImageIcon(dir
                            + "/images/Fast.png").getImage();
                    img = img.getScaledInstance(jPanelPreview.getWidth(),
                            jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledImageIcon = new ImageIcon(img);
                    jPanelPreview.setIcon(scaledImageIcon);
                }
            }
        });

        fast.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        panel_6.add(fast, "cell 3 12");

        macwin = new JCheckBox("MacWin");
        macwin.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (macwin.isSelected()) {

                    acryl.setSelected(false);
                    mint.setSelected(false);
                    fast.setSelected(false);
                    graphite.setSelected(false);
                    smart.setSelected(false);
                    texture.setSelected(false);
                    aluminium.setSelected(false);
                    aero.setSelected(false);
                    luna.setSelected(false);

                    choice = "04";

                    Image img = new javax.swing.ImageIcon(dir
                            + "/images/macwin.png").getImage();
                    img = img.getScaledInstance(jPanelPreview.getWidth(),
                            jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledImageIcon = new ImageIcon(img);
                    jPanelPreview.setIcon(scaledImageIcon);
                }
            }
        });
        macwin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        panel_6.add(macwin, "cell 1 13");

        graphite = new JCheckBox("Graphite");

        graphite.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (graphite.isSelected()) {

                    texture.setSelected(false);
                    aero.setSelected(false);
                    aluminium.setSelected(false);
                    macwin.setSelected(false);
                    luna.setSelected(false);
                    acryl.setSelected(false);
                    fast.setSelected(false);
                    mint.setSelected(false);
                    smart.setSelected(false);

                    choice = "09";

                    Image img = new javax.swing.ImageIcon(dir
                            + "/images/Graphite.png").getImage();
                    img = img.getScaledInstance(jPanelPreview.getWidth(),
                            jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledImageIcon = new ImageIcon(img);
                    jPanelPreview.setIcon(scaledImageIcon);
                }
            }
        });

        graphite.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        panel_6.add(graphite, "cell 3 13");

        luna = new JCheckBox("Luna");

        luna.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (luna.isSelected()) {

                    acryl.setSelected(false);
                    mint.setSelected(false);
                    fast.setSelected(false);
                    graphite.setSelected(false);
                    smart.setSelected(false);
                    texture.setSelected(false);
                    aluminium.setSelected(false);
                    aero.setSelected(false);
                    macwin.setSelected(false);

                    choice = "05";

                    Image img = new javax.swing.ImageIcon(dir
                            + "/images/luna.png").getImage();
                    img = img.getScaledInstance(jPanelPreview.getWidth(),
                            jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledImageIcon = new ImageIcon(img);
                    jPanelPreview.setIcon(scaledImageIcon);
                }
            }
        });

        luna.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        panel_6.add(luna, "cell 1 14");

        smart = new JCheckBox("Smart");

        smart.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (smart.isSelected()) {

                    texture.setSelected(false);
                    aero.setSelected(false);
                    aluminium.setSelected(false);
                    macwin.setSelected(false);
                    luna.setSelected(false);
                    acryl.setSelected(false);
                    fast.setSelected(false);
                    mint.setSelected(false);
                    graphite.setSelected(false);

                    choice = "10";

                    Image img = new javax.swing.ImageIcon(dir
                            + "/images/Smart.png").getImage();
                    img = img.getScaledInstance(jPanelPreview.getWidth(),
                            jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledImageIcon = new ImageIcon(img);
                    jPanelPreview.setIcon(scaledImageIcon);
                }
            }
        });

        smart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        panel_6.add(smart, "cell 3 14");

        buttonApply = new JButton("Apply");
        buttonApply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                if (!texture.isSelected() && !aero.isSelected()
                        && !luna.isSelected() && !aluminium.isSelected()
                        && !smart.isSelected() && !fast.isSelected()
                        && !graphite.isSelected() && !mint.isSelected()
                        && !acryl.isSelected() && !macwin.isSelected()) {
                    texture.setSelected(true);
                }
                try {
                    String pagination_Value = "";

                    if (jTextFieldPaginationValue.getText().toString().trim()
                            .length() > 4) {
                        JOptionPane.showMessageDialog(
                                AdSuMuDiSettingsEclipseGUI.this,
                                "Enter Pagination value should be less than "
                                + Constants.jTextAreaVATPERCENT);
                        jTextFieldPaginationValue.setText("");
                        jTextFieldPaginationValue.requestFocus();
                    } else if (Long.parseLong(jTextFieldPaginationValue
                            .getText().toString().trim()) > 100) {
                        JOptionPane.showMessageDialog(
                                AdSuMuDiSettingsEclipseGUI.this,
                                "Enter Pagination value should be less than "
                                + Constants.jTextAreaVATPERCENT);
                        jTextFieldPaginationValue.setText("");
                        jTextFieldPaginationValue.requestFocus();
                    } else if (jTextFieldPaginationValue.getText().toString()
                            .trim().equals("")
                            || Double.parseDouble(jTextFieldPaginationValue
                            .getText().toString().trim()) == 0D) {
                        // pagination_Value = "50";
                        JOptionPane.showMessageDialog(
                                AdSuMuDiSettingsEclipseGUI.this,
                                "Enter Pagination value greater than 0");
                        jTextFieldPaginationValue.setText("");
                        jTextFieldPaginationValue.requestFocus();
                    } else {
                        pagination_Value = jTextFieldPaginationValue.getText()
                                .toString().trim();
                        System.out
                                .println("RRRRRRRRRR     " + pagination_Value);
                        System.out
                                .println("w     "
                                + gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.PAGINATION);
                        map_Field_With_ID
                                .put(gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.PAGINATION,
                                pagination_Value);
                        AdSuMuDiSettingsDAO
                                .insert_Receipt_Settings(map_Field_With_ID);
                        gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant
                                .getValuesFromTable();
                    }

                    if (texture.isSelected()) {
                        choice = "01";
                    } else if (aero.isSelected()) {
                        choice = "02";
                    } else if (aluminium.isSelected()) {
                        choice = "03";
                    } else if (macwin.isSelected()) {
                        choice = "04";
                    } else if (luna.isSelected()) {
                        choice = "05";
                    } else if (acryl.isSelected()) {
                        choice = "06";
                    } else if (mint.isSelected()) {
                        choice = "07";
                    } else if (fast.isSelected()) {
                        choice = "08";
                    } else if (graphite.isSelected()) {
                        choice = "09";
                    } else if (smart.isSelected()) {
                        choice = "10";
                    }

                    FileWriter writer = null;
                    try {
                        // TODO add your handling code here:
                        // Connection conn =
                        // DatabaseConnection1.GetConnection();
                        // String query = "update tblothersetting set theme="+
                        // choice +"";
                        // PreparedStatement ps = conn.prepareStatement(query);
                        // ps.executeUpdate();

                        final String dir = System.getProperty("user.dir");
                        String fileName = dir + "\\others\\System.properties";
                        // String fileName =
                        // "C:\\Users\\pc5\\Desktop\\Sudeep IMP FILES\\SystemProperties\\System.properties";
                        String line = null;
                        String oldText = "";
                        String stringValueOfTheme = "";

                        FileReader fileReader = new FileReader(fileName);

                        BufferedReader bufferedReader = new BufferedReader(
                                fileReader);

                        while ((line = bufferedReader.readLine()) != null) {
                            System.out.println(line);
                            stringValueOfTheme = line.substring(8, 10);
                            System.out.println("SubString-->>"
                                    + stringValueOfTheme);
                            oldText += line + "\r\n";
                        }
                        bufferedReader.close();

                        String newtext = "";
                        if (choice.equalsIgnoreCase("01")) {
                            newtext = oldText.replaceAll(stringValueOfTheme,
                                    choice);
                        } else if (choice.equalsIgnoreCase("02")) {
                            newtext = oldText.replaceAll(stringValueOfTheme,
                                    choice);
                        } else if (choice.equalsIgnoreCase("03")) {
                            newtext = oldText.replaceAll(stringValueOfTheme,
                                    choice);
                        } else if (choice.equalsIgnoreCase("04")) {
                            newtext = oldText.replaceAll(stringValueOfTheme,
                                    choice);
                        } else if (choice.equalsIgnoreCase("05")) {
                            newtext = oldText.replaceAll(stringValueOfTheme,
                                    choice);
                        } else if (choice.equalsIgnoreCase("06")) {
                            newtext = oldText.replaceAll(stringValueOfTheme,
                                    choice);
                        } else if (choice.equalsIgnoreCase("07")) {
                            newtext = oldText.replaceAll(stringValueOfTheme,
                                    choice);
                        } else if (choice.equalsIgnoreCase("08")) {
                            newtext = oldText.replaceAll(stringValueOfTheme,
                                    choice);
                        } else if (choice.equalsIgnoreCase("09")) {
                            newtext = oldText.replaceAll(stringValueOfTheme,
                                    choice);
                        } else if (choice.equalsIgnoreCase("10")) {
                            newtext = oldText.replaceAll(stringValueOfTheme,
                                    choice);
                        }
                        writer = new FileWriter(fileName);
                        writer.write(newtext);
                        writer.close();
                        JOptionPane
                                .showMessageDialog(
                                AdSuMuDiSettingsEclipseGUI.this,
                                "Settings Saved Successfully,Please Restart Your Application");
                    } catch (IOException ex) {
                        Logger.getLogger(
                                AdSuMuDiSettingsEclipseGUI.class.getName())
                                .log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            writer.close();
                        } catch (IOException ex) {
                            Logger.getLogger(
                                    AdSuMuDiSettingsEclipseGUI.class.getName())
                                    .log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        });
        panel_6.add(buttonApply, "cell 1 17");

        buttonExit = new JButton("Exit");
        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdSuMuDiSettingsEclipseGUI.this.dispose();
            }
        });
        panel_6.add(buttonExit, "cell 4 17");

        JPanel panel_7 = new JPanel();
        panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.add(panel_7, "cell 1 0 1 2,grow");
        panel_7.setLayout(new MigLayout("", "[50px,grow]", "[14px][]"));

        lblPagination = new JLabel("Pagination");
        panel_7.add(lblPagination, "cell 0 0,alignx left,aligny top");

        jTextFieldPaginationValue = new JTextField();
        jTextFieldPaginationValue.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
                int flag = 0;
                String f = jTextFieldPaginationValue.getText().trim();
                int i = 0;
                while (i < f.length()) {
                    if (f.charAt(i) == '.') {
                        flag = 1;
                        break;
                    }
                    i++;
                }
                if (flag == 1 && c == '.') {
                    e.consume();
                }
            }
        });
        panel_7.add(jTextFieldPaginationValue, "cell 0 1,growx");
        jTextFieldPaginationValue.setColumns(10);

        JPanel panel_1 = new JPanel();
        jTabbedPane1.addTab("Network Settings", null, panel_1, null);
        panel_1.setLayout(new MigLayout(
                "",
                "[0px:500px:500px,grow,shrink 50,fill]",
                "[grow][0px:25px:25px,grow,shrink 50][0px:25px:25px,grow,shrink 50][0px:25px:25px,grow,shrink 50][0px:25px:25px,grow,shrink 50][0px:25px:25px,grow,shrink 50][0px:25px:25px,grow,shrink 50][0px:25px:25px,grow,shrink 50]"));

        JPanel panel_8 = new JPanel();
        panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_1.add(panel_8, "cell 0 0,grow");
        panel_8.setLayout(new MigLayout(
                "",
                "[0px:175px:175px,grow,shrink 50,fill][0px:150px:150px,grow,shrink 50,fill][0px:150px:150px,grow,shrink 50,fill][0px:150px:150px,grow,shrink 50,fill]",
                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        lblSmsSettings = new JLabel("SMS Settings");
        lblSmsSettings.setFont(new Font("Tahoma", Font.BOLD, 11));
        panel_8.add(lblSmsSettings, "cell 0 0");

        jCheckBoxStockItemName = new JCheckBox("Stock Item Name");
        jCheckBoxStockItemName.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (jCheckBoxStockItemName.isSelected()) {

                    stockItemName = "1";

                } else {

                    stockItemName = "0";

                }
            }
        });
        panel_8.add(jCheckBoxStockItemName, "cell 0 1");

        jCheckBoxEnableSMSSend = new JCheckBox("Enable SMS Sending");
        jCheckBoxEnableSMSSend.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jCheckBoxEnableSMSSend.isSelected()) {

                    jCheckBoxDisableSMSSend.setSelected(false);
                    jCheckBoxEnableSMSSendWithAsk.setSelected(false);

                    SMSchoice = "1";

                }
            }
        });
        jCheckBoxEnableSMSSend.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (jCheckBoxEnableSMSSend.isSelected()) {

                    jCheckBoxDisableSMSSend.setSelected(false);
                    jCheckBoxEnableSMSSendWithAsk.setSelected(false);

                    SMSchoice = "1";

                }
            }
        });
        panel_8.add(jCheckBoxEnableSMSSend, "cell 1 1");

        jCheckBoxQuantity = new JCheckBox("Quantity");
        jCheckBoxQuantity.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (jCheckBoxQuantity.isSelected()) {

                    stockItemQuantity = "1";

                } else {

                    stockItemQuantity = "0";

                }
            }
        });
        panel_8.add(jCheckBoxQuantity, "cell 0 2");

        jCheckBoxDisableSMSSend = new JCheckBox("Disable SMS Sending");
        jCheckBoxDisableSMSSend.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jCheckBoxDisableSMSSend.isSelected()) {

                    jCheckBoxEnableSMSSend.setSelected(false);
                    jCheckBoxEnableSMSSendWithAsk.setSelected(false);

                    SMSchoice = "2";

                }
            }
        });
        jCheckBoxDisableSMSSend.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jCheckBoxDisableSMSSend.isSelected()) {

                    jCheckBoxEnableSMSSend.setSelected(false);
                    jCheckBoxEnableSMSSendWithAsk.setSelected(false);

                    SMSchoice = "2";

                }
            }
        });
        panel_8.add(jCheckBoxDisableSMSSend, "cell 1 2");

        jCheckBoxAmount = new JCheckBox("Amount");
        jCheckBoxAmount.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (jCheckBoxAmount.isSelected()) {

                    stockItemAmount = "1";

                } else {

                    stockItemAmount = "0";

                }
            }
        });
        panel_8.add(jCheckBoxAmount, "cell 0 3");

        jCheckBoxEnableSMSSendWithAsk = new JCheckBox("Ask To Send SMS");
        jCheckBoxEnableSMSSendWithAsk.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jCheckBoxEnableSMSSendWithAsk.isSelected()) {

                    jCheckBoxEnableSMSSend.setSelected(false);
                    jCheckBoxDisableSMSSend.setSelected(false);

                    SMSchoice = "3";

                }
            }
        });
        jCheckBoxEnableSMSSendWithAsk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jCheckBoxEnableSMSSendWithAsk.isSelected()) {

                    jCheckBoxEnableSMSSend.setSelected(false);
                    jCheckBoxDisableSMSSend.setSelected(false);

                    SMSchoice = "3";

                }
            }
        });
        panel_8.add(jCheckBoxEnableSMSSendWithAsk, "cell 1 3");

        jButtonSMSSettingsOK = new JButton("OK");
        jButtonSMSSettingsOK.setMnemonic('K');
        jButtonSMSSettingsOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (!jCheckBoxStockItemName.isSelected()
                        && !jCheckBoxQuantity.isSelected()
                        && !jCheckBoxAmount.isSelected()
                        && !jCheckBoxEnableSMSSend.isSelected()
                        && !jCheckBoxDisableSMSSend.isSelected()
                        && !jCheckBoxEnableSMSSendWithAsk.isSelected()) {
                    jCheckBoxStockItemName.setSelected(true);
                    jCheckBoxEnableSMSSendWithAsk.setSelected(true);
                }
                FileWriter writer = null;
                try {
                    // String fileName =
                    // "C:\\Users\\pc5\\Desktop\\Sudeep IMP FILES\\SystemProperties\\System.properties";
                    final String dir = System.getProperty("user.dir");
                    String fileName = dir
                            + "\\others\\DataSMSSettings.properties";
                    String line = null;
                    String oldText = "";
                    String oldText1 = "";
                    String oldText2 = "";
                    String stringValueOfStkItem = "";
                    String stringValueOfQuantity = "";
                    String stringValueOfAmount = "";

                    FileReader fileReader = new FileReader(fileName);

                    BufferedReader bufferedReader = new BufferedReader(
                            fileReader);

                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.println(line);
                        if (line.startsWith("S")) {
                            stringValueOfStkItem = line.substring(16, 17);
                            System.out.println("stringValueOfStkItem-->>"
                                    + stringValueOfStkItem);
                            oldText += line + "\r\n";
                        }
                        if (line.startsWith("Q")) {
                            stringValueOfQuantity = line.substring(11, 12);
                            System.out.println("stringValueOfQuantity-->>"
                                    + stringValueOfQuantity);
                            oldText1 += line + "\r\n";
                        }
                        if (line.startsWith("A")) {
                            stringValueOfAmount = line.substring(9, 10);
                            System.out.println("stringValueOfAmount-->>"
                                    + stringValueOfAmount);
                            oldText2 += line + "\r\n";
                        }
                    }
                    bufferedReader.close();

                    String newText = "";
                    if (stockItemName.equalsIgnoreCase("1")) {

                        newText = oldText.replaceAll(stringValueOfStkItem,
                                stockItemName);

                    } else {

                        newText = oldText.replaceAll(stringValueOfStkItem, "0");

                    }

                    String newText1 = "";
                    if (stockItemQuantity.equalsIgnoreCase("1")) {

                        newText1 = oldText1.replaceAll(stringValueOfQuantity,
                                stockItemQuantity);

                    } else {

                        newText1 = oldText1.replaceAll(stringValueOfQuantity,
                                "0");

                    }

                    String newText2 = "";
                    if (stockItemAmount.equalsIgnoreCase("1")) {

                        newText2 = oldText2.replaceAll(stringValueOfAmount,
                                stockItemAmount);

                    } else {

                        newText2 = oldText2
                                .replaceAll(stringValueOfAmount, "0");

                    }

                    // writer = new
                    // FileWriter("C:\\Users\\pc5\\Desktop\\Sudeep IMP FILES\\SystemProperties\\System.properties");
                    writer = new FileWriter(fileName);
                    writer.write(newText);
                    writer.write(newText1);
                    writer.write(newText2);
                    writer.close();
                    JOptionPane.showMessageDialog(
                            AdSuMuDiSettingsEclipseGUI.this,
                            "Data Sending SMS settings saved");
                } catch (IOException ex) {
                    Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName())
                            .log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        writer.close();
                    } catch (IOException ex) {
                        Logger.getLogger(
                                AdSuMuDiSettingsEclipseGUI.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }
                }

                FileWriter writer1 = null;
                try {
                    final String dir = System.getProperty("user.dir");
                    String fileName = dir
                            + "\\others\\NetworkSMSSettings.properties";
                    String line = null;
                    String oldText = "";
                    String stringValueOfSMS = "";

                    FileReader fileReader = new FileReader(fileName);

                    BufferedReader bufferedReader = new BufferedReader(
                            fileReader);

                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.println(line);
                        stringValueOfSMS = line.substring(14, 15);
                        System.out.println("SubString-->>" + stringValueOfSMS);
                        oldText += line + "\r\n";
                    }
                    bufferedReader.close();

                    String newtext = "";
                    if (SMSchoice.equalsIgnoreCase("1")) {
                        newtext = oldText.replaceAll(stringValueOfSMS,
                                SMSchoice);
                    } else if (SMSchoice.equalsIgnoreCase("2")) {
                        newtext = oldText.replaceAll(stringValueOfSMS,
                                SMSchoice);
                    } else if (SMSchoice.equalsIgnoreCase("3")) {
                        newtext = oldText.replaceAll(stringValueOfSMS,
                                SMSchoice);
                    }
                    writer1 = new FileWriter(fileName);
                    writer1.write(newtext);
                    writer1.close();
                    JOptionPane.showMessageDialog(
                            AdSuMuDiSettingsEclipseGUI.this,
                            "SMS Network Settings Saved Successfully");
                } catch (IOException ex) {
                    Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName())
                            .log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        writer1.close();
                    } catch (IOException ex) {
                        Logger.getLogger(
                                AdSuMuDiSettingsEclipseGUI.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        panel_8.add(jButtonSMSSettingsOK, "cell 3 4");

        lblEmailSettings = new JLabel("E-Mail Settings");
        lblEmailSettings.setFont(new Font("Tahoma", Font.BOLD, 11));
        panel_8.add(lblEmailSettings, "cell 0 5");

        jCheckBoxEnableEMail = new JCheckBox("Enable E-Mail Sending");
        jCheckBoxEnableEMail.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jCheckBoxEnableEMail.isSelected()) {
                    jCheckBoxDisableEMail.setSelected(false);
                    jCheckBoxAskToSendEMail.setSelected(false);
                    textField_1.setEnabled(true);
                    passwordField.setEnabled(true);
                    textField_1.setEditable(true);
                    passwordField.setEditable(true);
                    System.out.println("jCheckBoxEnableEMail..............");
                    EMailchoice = "1";
                }
            }
        });
        jCheckBoxEnableEMail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jCheckBoxEnableEMail.isSelected()) {
                    jCheckBoxDisableEMail.setSelected(false);
                    jCheckBoxAskToSendEMail.setSelected(false);
                    textField_1.setEnabled(true);
                    passwordField.setEnabled(true);
                    textField_1.setEditable(true);
                    passwordField.setEditable(true);
                    System.out.println("jCheckBoxEnableEMail..............");
                    EMailchoice = "1";
                }
            }
        });
        panel_8.add(jCheckBoxEnableEMail, "cell 0 6");

        lblEmailId = new JLabel("E-Mail ID");
        lblEmailId.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_8.add(lblEmailId, "cell 1 6,alignx trailing");

        textField_1 = new JTextField();
        panel_8.add(textField_1, "cell 2 6 2 1,growx");
        textField_1.setColumns(10);

        jCheckBoxDisableEMail = new JCheckBox("Disable E-Mail Sending");
        jCheckBoxDisableEMail.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jCheckBoxDisableEMail.isSelected()) {
                    jCheckBoxEnableEMail.setSelected(false);
                    jCheckBoxAskToSendEMail.setSelected(false);
                    textField_1.setEnabled(false);
                    passwordField.setEnabled(false);
                    textField_1.setEditable(false);
                    passwordField.setEditable(false);
                    System.out.println("jCheckBoxDisableEMail..............");
                    EMailchoice = "2";
                }
            }
        });
        jCheckBoxDisableEMail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jCheckBoxDisableEMail.isSelected()) {
                    jCheckBoxEnableEMail.setSelected(false);
                    jCheckBoxAskToSendEMail.setSelected(false);
                    textField_1.setEnabled(false);
                    passwordField.setEnabled(false);
                    textField_1.setEditable(false);
                    passwordField.setEditable(false);
                    System.out.println("jCheckBoxDisableEMail..............");
                    EMailchoice = "2";
                }
            }
        });
        panel_8.add(jCheckBoxDisableEMail, "cell 0 7");

        lblPassword = new JLabel("Password");
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_8.add(lblPassword, "cell 1 7,alignx trailing");

        passwordField = new JPasswordField();
        panel_8.add(passwordField, "cell 2 7 2 1,growx");

        jButtonEMailOK2 = new JButton("OK");
        jButtonEMailOK2.setMnemonic('O');
        jButtonEMailOK2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!jCheckBoxEnableEMail.isSelected()
                        && !jCheckBoxDisableEMail.isSelected() && !jCheckBoxAskToSendEMail.isSelected()) {
                    jCheckBoxDisableEMail.setSelected(true);
                }
                FileWriter writer = null;
                try {
                    final String dir = System.getProperty("user.dir");
                    String fileName = dir
                            + "\\others\\NetworkEMailSettings.properties";
                    String line = null;
                    String oldText = "";
                    String stringValueOfEMail = "";

                    FileReader fileReader = new FileReader(fileName);

                    BufferedReader bufferedReader = new BufferedReader(
                            fileReader);

                    int lineCounter = 1;
                    while ((line = bufferedReader.readLine()) != null) {
                        if (lineCounter == 1) {
                            System.out.println(line);
                            stringValueOfEMail = line.substring(16, 17);
                            System.out.println("SubString-->>"
                                    + stringValueOfEMail);
                            oldText += line + "\r\n";
                        }
                        lineCounter++;
                    }
                    bufferedReader.close();

                    String newtext = "";
                    if (EMailchoice.equalsIgnoreCase("1")) {
                        newtext = oldText.replaceAll(stringValueOfEMail,
                                EMailchoice);
                    } else {
                        newtext = oldText.replaceAll(stringValueOfEMail, "2");
                    }
                    if (EMailchoice.equalsIgnoreCase("2")) {
                        newtext = oldText.replaceAll(stringValueOfEMail,
                                EMailchoice);
                    } else {
                        newtext = oldText.replaceAll(stringValueOfEMail, "1");
                    }
                    if (EMailchoice.equalsIgnoreCase("3")) {
                        newtext = oldText.replaceAll(stringValueOfEMail,
                                EMailchoice);
                    } else if (EMailchoice.equalsIgnoreCase("2")) {
                        newtext = oldText.replaceAll(stringValueOfEMail, EMailchoice);
                    } else {
                        newtext = oldText.replaceAll(stringValueOfEMail, "1");
                    }
                    writer = new FileWriter(fileName);
                    writer.write(newtext);
                    writer.close();
                    // JOptionPane.showMessageDialog(this,
                    // "E-Mail Network Settings Saved Successfully");
                } catch (IOException ex) {
                    Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName())
                            .log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        writer.close();
                    } catch (IOException ex) {
                        Logger.getLogger(
                                AdSuMuDiSettingsEclipseGUI.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }
                }

                FileWriter writerNew = null;
                try {
                    final String dir = System.getProperty("user.dir");
                    String fileName = dir
                            + "\\others\\NetworkEMailSender.properties";
                    String line = null;
                    String oldText = "";
                    String stringValueOfEMail = "";

                    FileReader fileReader = new FileReader(fileName);

                    BufferedReader bufferedReader = new BufferedReader(
                            fileReader);

                    int lineCounter = 1;
                    while ((line = bufferedReader.readLine()) != null) {
                        if (lineCounter == 1) {
                            System.out.println(line);
                            oldText += line + "\r\n";
                        }
                        lineCounter++;
                    }
                    bufferedReader.close();

                    String newtext = "";
                    newtext = oldText.replaceAll(oldText, textField_1.getText());
                    writerNew = new FileWriter(fileName);
                    writerNew.write(newtext);
                    writerNew.close();
                    // JOptionPane.showMessageDialog(this,
                    // "E-Mail Network Settings Saved Successfully");
                } catch (IOException ex) {
                    Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName())
                            .log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        writerNew.close();
                    } catch (IOException ex) {
                        Logger.getLogger(
                                AdSuMuDiSettingsEclipseGUI.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }
                }

                FileWriter writerNewPWD = null;
                try {
                    final String dir = System.getProperty("user.dir");
                    String fileName = dir
                            + "\\others\\NetworkEMailPWD.properties";
                    String line = null;
                    String oldText = "";
                    String stringValueOfEMailPWD = "";

                    FileReader fileReader = new FileReader(fileName);

                    BufferedReader bufferedReader = new BufferedReader(
                            fileReader);

                    int lineCounter = 1;
                    while ((line = bufferedReader.readLine()) != null) {
                        if (lineCounter == 1) {
                            System.out.println(line);
                            oldText += line + "\r\n";
                        }
                        lineCounter++;
                    }
                    bufferedReader.close();

                    String newtext = "";
                    newtext = oldText.replaceAll(oldText,
                            passwordField.getText());
                    writerNewPWD = new FileWriter(fileName);
                    writerNewPWD.write(newtext);
                    writerNewPWD.close();
                    // JOptionPane.showMessageDialog(this,
                    // "E-Mail Network Settings Saved Successfully");
                } catch (IOException ex) {
                    Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName())
                            .log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        writerNewPWD.close();
                    } catch (IOException ex) {
                        Logger.getLogger(
                                AdSuMuDiSettingsEclipseGUI.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }
                }
                JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                        "E-Mail Network Settings Saved Successfully");
            }
        });

        jCheckBoxAskToSendEMail = new JCheckBox("Ask To Send E-Mail");
        jCheckBoxAskToSendEMail.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jCheckBoxAskToSendEMail.isSelected()) {
                    jCheckBoxEnableEMail.setSelected(false);
                    jCheckBoxDisableEMail.setSelected(false);
                    textField_1.setEnabled(true);
                    passwordField.setEnabled(true);
                    textField_1.setEditable(true);
                    passwordField.setEditable(true);
                    System.out.println("jCheckBoxEnableEMail..............");
                    EMailchoice = "3";
                }
            }
        });
        jCheckBoxAskToSendEMail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jCheckBoxAskToSendEMail.isSelected()) {
                    jCheckBoxEnableEMail.setSelected(false);
                    jCheckBoxDisableEMail.setSelected(false);
                    textField_1.setEnabled(true);
                    passwordField.setEnabled(true);
                    textField_1.setEditable(true);
                    passwordField.setEditable(true);
                    System.out.println("jCheckBoxEnableEMail..............");
                    EMailchoice = "3";
                }
            }
        });
        panel_8.add(jCheckBoxAskToSendEMail, "cell 0 8");
        panel_8.add(jButtonEMailOK2, "cell 3 8");

        jButtonApply = new JButton("Apply");
        jButtonApply.setMnemonic('P');
        jButtonApply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    map_Field_With_ID.clear();
                    // if (checkBoxReceiptnoEditable.isSelected()) {
                    // map_Field_With_ID.put(gen.other.AdSuMuDiSettings.AdSuMuDiConstant.RECEIPT_SETTINGS,
                    // 1);
                    // } else {
                    // map_Field_With_ID.put(gen.other.AdSuMuDiSettings.AdSuMuDiConstant.RECEIPT_SETTINGS,
                    // 0);
                    // }
                    //
                    // if (checkBoxAutoInrementVoucherNumber.isSelected()) {
                    // map_Field_With_ID.put(gen.other.AdSuMuDiSettings.AdSuMuDiConstant.VOUCHER_NUMBER_SETTING,
                    // 1);
                    // } else {
                    // map_Field_With_ID.put(gen.other.AdSuMuDiSettings.AdSuMuDiConstant.VOUCHER_NUMBER_SETTING,
                    // 0);
                    // }
//		    String pagination_Value = "";
//		    if (jTextFieldPaginationValue.getText().toString().trim()
//			    .equals("")
//			    || Double.parseDouble(jTextFieldPaginationValue
//			    .getText().toString().trim()) == 0D) {
//			// pagination_Value = "50";
//			JOptionPane.showMessageDialog(
//				AdSuMuDiSettingsEclipseGUI.this,
//				"Enter Pagination value greater than 0");
//			jTextFieldPaginationValue.setText("");
//			jTextFieldPaginationValue.requestFocus();
//		    } else {
//			pagination_Value = jTextFieldPaginationValue.getText()
//				.toString().trim();
//			map_Field_With_ID
//				.put(gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.PAGINATION,
//				pagination_Value);
//			AdSuMuDiSettingsDAO
//				.insert_Receipt_Settings(map_Field_With_ID);
//			gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant
//				.getValuesFromTable();
//			JOptionPane.showMessageDialog(
//				AdSuMuDiSettingsEclipseGUI.this,
//				"Setting SuccessFully Submitted");
//		    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                // set_Value_CheckBox();
            }
        });
        panel_8.add(jButtonApply, "cell 1 19");

        jButtonExit = new JButton("Exit");
        jButtonExit.setMnemonic('E');
        jButtonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdSuMuDiSettingsEclipseGUI.this.dispose();
            }
        });
        panel_8.add(jButtonExit, "cell 2 19");

        JPanel panel_2 = new JPanel();
        jTabbedPane1.addTab("Device/Hardware Settings", null, panel_2, null);
        panel_2.setLayout(new MigLayout("", "[0px:500px:500px,grow,shrink 50,fill][][][][][grow]", "[grow][0px:25px:25px,grow,shrink 50][0px:25px:25px,grow,shrink 50][0px:25px:25px,grow,shrink 50][0px:25px:25px,grow,shrink 50][0px:25px:25px,grow,shrink 50][0px:25px:25px,grow,shrink 50][0px:25px:25px,grow,shrink 50]"));

        JPanel panel_9 = new JPanel();
        panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_2.add(panel_9, "cell 0 0,grow");
        panel_9.setLayout(new MigLayout(
                "",
                "[0px:80px:80px,grow,shrink 50,fill][0px:80px:80px,grow,shrink 50,fill][0px:80px:80px,grow,shrink 50,fill][0px:80px:80px,grow,shrink 50,fill][0px:80px:80px,grow,shrink 50,fill][0px:80px:80px,grow,shrink 50,fill]",
                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        JPanel panel_10 = new JPanel();
        panel_10.setBorder(new TitledBorder(null, "Show Print Preview",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_9.add(panel_10, "cell 1 1 4 3,grow");
        panel_10.setLayout(new MigLayout("", "[][][][][][][]", "[]"));

        optYes = new JRadioButton("Yes");
        optYes.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (optYes.isSelected()) {
                    optNo.setSelected(false);
                }
            }
        });
        panel_10.add(optYes, "cell 3 0");

        optNo = new JRadioButton("No");
        optNo.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (optNo.isSelected()) {
                    optYes.setSelected(false);
                }
            }
        });
        panel_10.add(optNo, "cell 5 0");

        JPanel panel_11 = new JPanel();
        panel_11.setBorder(new TitledBorder(null, "Print Display Format",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_9.add(panel_11, "cell 1 5 4 3,grow");
        panel_11.setLayout(new MigLayout(
                "",
                "[0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill]",
                "[]"));

        optPdf = new JRadioButton("PDF");
        optPdf.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (optPdf.isSelected()) {
                    optHtml.setSelected(false);
                    optWord.setSelected(false);
                    optExcel.setSelected(false);
                }
            }
        });
        panel_11.add(optPdf, "cell 0 0");

        optHtml = new JRadioButton("HTML");
        optHtml.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (optHtml.isSelected()) {
                    optPdf.setSelected(false);
                    optWord.setSelected(false);
                    optExcel.setSelected(false);
                }
            }
        });
        panel_11.add(optHtml, "cell 1 0");

        optWord = new JRadioButton("MS Word");
        optWord.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (optWord.isSelected()) {
                    optHtml.setSelected(false);
                    optPdf.setSelected(false);
                    optExcel.setSelected(false);
                }
            }
        });
        panel_11.add(optWord, "cell 2 0");

        optExcel = new JRadioButton("Excel");
        optExcel.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (optExcel.isSelected()) {
                    optHtml.setSelected(false);
                    optWord.setSelected(false);
                    optPdf.setSelected(false);
                }
            }
        });
        panel_11.add(optExcel, "cell 3 0");

        JPanel panel_12 = new JPanel();
        panel_12.setBorder(new TitledBorder(null, "Page Format",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_9.add(panel_12, "cell 1 9 4 3,grow");
        panel_12.setLayout(new MigLayout("", "[][][][][][][]", "[]"));

        optA4 = new JRadioButton("A4");
        optA4.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (optA4.isSelected()) {
                    optA5.setSelected(false);
                    optAsk.setSelected(false);
                }
            }
        });
        panel_12.add(optA4, "cell 2 0");

        optA5 = new JRadioButton("A5");
        optA5.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (optA5.isSelected()) {
                    optA4.setSelected(false);
                    optAsk.setSelected(false);
                }
            }
        });
        panel_12.add(optA5, "cell 4 0");

        optAsk = new JRadioButton("A6");
        optAsk.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (optAsk.isSelected()) {
                    optA5.setSelected(false);
                    optA4.setSelected(false);
                }
            }
        });
        panel_12.add(optAsk, "cell 6 0");

        btnSave = new JButton("Apply");
        btnSave.setMnemonic('P');
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection conn = null;
                try {
                    System.out
                            .println("Btn save calling --------------------      "
                            + gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING);
                    System.out
                            .println("Btn save 000000000000000 --------------------      "
                            + gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings);
                    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
                    conn = DatabaseConnection1.GetConnection();
                    Statement st = conn.createStatement();

                    String query = "select * from tblothersetting";
                    resultSet = st.executeQuery(query);
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

                    if (optYes.isSelected() == false
                            && optNo.isSelected() == false) {
                        JOptionPane.showMessageDialog(
                                AdSuMuDiSettingsEclipseGUI.this,
                                "Please Select Show Print Preview ");
                        optYes.requestFocus();
                    } else if (optPdf.isSelected() == false
                            && optHtml.isSelected() == false
                            && optWord.isSelected() == false
                            && optExcel.isSelected() == false) {
                        JOptionPane.showMessageDialog(
                                AdSuMuDiSettingsEclipseGUI.this,
                                "Please Select Print Display Format");
                        optPdf.requestFocus();
                    } else if (optA4.isSelected() == false
                            && optA5.isSelected() == false
                            && optAsk.isSelected() == false) {
                        JOptionPane.showMessageDialog(
                                AdSuMuDiSettingsEclipseGUI.this,
                                "Please Select Page Format ");
                        optA4.requestFocus();
                    } else {
                        if (resultSet.next()) {
                            System.out
                                    .println("Upadte Network Settings -------------------------");
                            query = "update tblothersetting set printpreview="
                                    + PrinterSettings.flagDirectPrint
                                    + ",printPageFormat="
                                    + PrinterSettings.flagPrintPageSize
                                    + ",printDisplayFormat="
                                    + PrinterSettings.flagPrintPageFormat + "";
                        } else {
                            System.out
                                    .println("Insert Network Settings -------------------------");
                            query = "insert into tblothersetting(printpreview,printPageFormat,printDisplayFormat) values("
                                    + PrinterSettings.flagDirectPrint
                                    + ","
                                    + PrinterSettings.flagPrintPageSize
                                    + ","
                                    + PrinterSettings.flagPrintPageFormat + ")";
                        }
                        st.executeUpdate(query);
                        System.out.println("Operation Performed");
                        JOptionPane.showMessageDialog(
                                AdSuMuDiSettingsEclipseGUI.this,
                                "Settings Saved Successfully");
                        gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
                        // btnBackActionPerformed(evt);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        Logger.getLogger(
                                AdSuMuDiSettingsEclipseGUI.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        panel_9.add(btnSave, "cell 2 17");

        btnBack_1 = new JButton("Exit");
        btnBack_1.setMnemonic('E');
        btnBack_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    MainClass m = new MainClass();
                    m.menuselection(5);
                    AdSuMuDiSettingsEclipseGUI.this.setClosed(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(AdSuMuDiSettingsEclipseGUI.this.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        });
        panel_9.add(btnBack_1, "cell 3 17");

        panel_21 = new JPanel();
        panel_2.add(panel_21, "cell 5 0,grow");
        panel_21.setLayout(new MigLayout("", "[0px:70px:70px,grow,shrink 50][0px:130px:130px,grow,shrink 50][0px:100px:100px,grow,shrink 50][0px:100px:100px,grow,shrink 50][100px:100px:100px,grow,shrink 50][]", "[0px:25px:25px,grow,shrink 50][0px:25px:25px,grow,shrink 50][0px:25px:25px,grow,shrink 50][0px:25px:25px,grow,shrink 50][0px:25px:25px,grow,shrink 50]"));

        lblNewLabel = new JLabel("Set ReceiptNo");
        panel_21.add(lblNewLabel, "cell 1 0");

        lblNewLabel_7 = new JLabel("Receipt No.");
        panel_21.add(lblNewLabel_7, "cell 0 2");

        jtextFieldReceiptNo = new JTextField();
        panel_21.add(jtextFieldReceiptNo, "cell 1 2,growx");
        jtextFieldReceiptNo.setColumns(10);

        lblNewLabel_1 = new JLabel("For Year");
        panel_21.add(lblNewLabel_1, "cell 0 3");

        jdateChooser_SelectYear = new JDateChooser();
        panel_21.add(jdateChooser_SelectYear, "cell 1 3,grow");

        jbuttonSubmitReceiptNo = new JButton("Submit");
        jbuttonSubmitReceiptNo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    if (validationReceiptNo()) {
                        System.out.println("In Submit ------------------- ");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this, ex.getMessage());
                }
            }
        });
        panel_21.add(jbuttonSubmitReceiptNo, "cell 1 4");

        JPanel panel_3 = new JPanel();
        jTabbedPane1.addTab("DATA Settings", null, panel_3, null);
        panel_3.setLayout(new MigLayout("", "[grow]", "[grow]"));

        jTabbedPane2 = new JTabbedPane(JTabbedPane.TOP);
        jTabbedPane2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int seleted_company_Index = 0;
                seleted_company_Index = jTabbedPane2.getSelectedIndex();

                if (seleted_company_Index == 0) {
                    intialise_Company_Settings();
                } //                else if (seleted_company_Index == 1) {
                //                    initialise_Company_Back_UP();
                //                } else if (seleted_company_Index == 2) {
                //                    initialise_Company_Restore();
                //                } else if (seleted_company_Index == 3) {
                //                    initialise_Database_Spliting();
                //                }
                else if (seleted_company_Index == 1) {
                    intialise_Company_Delete();
                }

                companySettingsDTO = gen.other.AdSuMuDiSettings.AdSuMuDiConstant
                        .getCurrentCompany_Details("");
                ActivInactiveComponent();
            }
        });
        panel_3.add(jTabbedPane2, "cell 0 0,grow");

        JPanel panel_13 = new JPanel();
        jTabbedPane2.addTab("Company Selection", null, panel_13, null);
        panel_13.setLayout(new MigLayout(
                "",
                "[0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][grow]",
                "[0px:25px:25px,grow,shrink 50,fill][grow,shrink 50][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50][0px:25px:25px,grow,shrink 50]"));

        JLabel lblChangeCompany = new JLabel("Change Company");
        panel_13.add(lblChangeCompany, "cell 0 0");

        JScrollPane scrollPane = new JScrollPane();
        panel_13.add(scrollPane, "cell 0 1 4 1,grow");

        tablecompanyList_Company_Settings = new JTable();
        tablecompanyList_Company_Settings.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    // setFocus(event);
                    //tablecompanyList_Company_SettingsMouseClicked(null);
                }
            }
        });
        tablecompanyList_Company_Settings.setModel(new DefaultTableModel(
                new Object[][]{ //                    {null, null}, {null, null},
                //		    {null, null}, {null, null}, {null, null},
                //		    {null, null}, {null, null}, {null, null},
                //		    {null, null}, {null, null},
                }, new String[]{"New column", "New column"}));
        scrollPane.setColumnHeaderView(tablecompanyList_Company_Settings);

        buttonApply_Company_Settings = new JButton("Apply");
        buttonApply_Company_Settings.setMnemonic('P');
        buttonApply_Company_Settings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tablecompanyList_Company_Settings.getSelectedRow() >= 0) {
                    for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList_Company_Settings) {
                        if (companySettingsDTO.getCompany_name().equals(
                                tableModelCompanyList_Company_Settings
                                .getValueAt(
                                tablecompanyList_Company_Settings
                                .getSelectedRow(), 0)
                                .toString())) {

                            CURRENT_DATABASE_Company_Settings = companySettingsDTO
                                    .getCompany_DatabaseName();
                            CURRENT_COMPANY_Company_Settings = tableModelCompanyList_Company_Settings
                                    .getValueAt(
                                    tablecompanyList_Company_Settings
                                    .getSelectedRow(), 0)
                                    .toString();

                            gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = companySettingsDTO
                                    .getCompany_DatabaseName();
                            System.out
                                    .println("Current database ---------      "
                                    + CURRENT_DATABASE_Company_Settings);
                            System.out
                                    .println("Current database 111111111   ---------      "
                                    + CURRENT_COMPANY_Company_Settings);
                            gen.dto.Constants.CURRENT_COMPANY_NAME = tableModelCompanyList_Company_Settings
                                    .getValueAt(
                                    tablecompanyList_Company_Settings
                                    .getSelectedRow(), 0)
                                    .toString(); // insert into currentcompany
                            // table
                            gen.dto.Constants.CURRENT_COMPANY_ID = tableModelCompanyList_Company_Settings
                                    .getValueAt(
                                    tablecompanyList_Company_Settings
                                    .getSelectedRow(), 1)
                                    .toString();

                            gen.dto.Constants.CURRENT_COMPANY_CREATED_DATE = companySettingsDTO.getCompany_ApplicableFrom_date();

                            gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = companySettingsDTO.getCompany_DatabaseName();
                            System.out.println("Current database ---------      " + CURRENT_DATABASE_Company_Settings);
                            System.out.println("Current database 111111111   ---------      " + CURRENT_COMPANY_Company_Settings);
                            gen.dto.Constants.CURRENT_COMPANY_NAME = tableModelCompanyList_Company_Settings.getValueAt(tablecompanyList_Company_Settings.getSelectedRow(), 0).toString();			    // insert into currentcompany table
                            gen.dto.Constants.CURRENT_COMPANY_ID = tableModelCompanyList_Company_Settings.getValueAt(tablecompanyList_Company_Settings.getSelectedRow(), 1).toString();

                            System.out
                                    .println("Cureent compnay ---- Name -----------------"
                                    + gen.dto.Constants.CURRENT_COMPANY_NAME);
                            System.out
                                    .println("Cureent compnay ---- ID -----------------"
                                    + gen.dto.Constants.CURRENT_COMPANY_ID);

                            System.out
                                    .println("Cureent compnay ---- DATE -----------------"
                                    + gen.dto.Constants.CURRENT_COMPANY_CREATED_DATE);

                            gen.other.CompanySettings.CompanySettingsDAO.insert_Current_Company(
                                    tableModelCompanyList_Company_Settings
                                    .getValueAt(
                                    tablecompanyList_Company_Settings
                                    .getSelectedRow(),
                                    0).toString(),
                                    tableModelCompanyList_Company_Settings
                                    .getValueAt(
                                    tablecompanyList_Company_Settings
                                    .getSelectedRow(),
                                    1).toString());
                        }
                    }
                    JOptionPane.showMessageDialog(
                            AdSuMuDiSettingsEclipseGUI.this,
                            "Company Selected    "
                            + tableModelCompanyList_Company_Settings
                            .getValueAt(
                            tablecompanyList_Company_Settings
                            .getSelectedRow(),
                            0).toString());
                    //jTabbedPane2MouseClicked(null);
                }
            }
        });

        tablecompanyList_Company_Settings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tablecompanyList_Company_Settings.getSelectedRow() >= 0) {
                    for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList_Company_Settings) {
                        if (companySettingsDTO.getCompany_name().equals(
                                tableModelCompanyList_Company_Settings
                                .getValueAt(
                                tablecompanyList_Company_Settings
                                .getSelectedRow(), 0)
                                .toString())) {

                            CURRENT_DATABASE_Company_Settings = companySettingsDTO
                                    .getCompany_DatabaseName();
                            CURRENT_COMPANY_Company_Settings = tableModelCompanyList_Company_Settings
                                    .getValueAt(
                                    tablecompanyList_Company_Settings
                                    .getSelectedRow(), 0)
                                    .toString();

                            gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = companySettingsDTO
                                    .getCompany_DatabaseName();
                            System.out
                                    .println("Current database ---------      "
                                    + CURRENT_DATABASE_Company_Settings);
                            System.out
                                    .println("Current database 111111111   ---------      "
                                    + CURRENT_COMPANY_Company_Settings);
                            gen.dto.Constants.CURRENT_COMPANY_NAME = tableModelCompanyList_Company_Settings
                                    .getValueAt(
                                    tablecompanyList_Company_Settings
                                    .getSelectedRow(), 0)
                                    .toString(); // insert into currentcompany
                            // table
                            gen.dto.Constants.CURRENT_COMPANY_ID = tableModelCompanyList_Company_Settings
                                    .getValueAt(
                                    tablecompanyList_Company_Settings
                                    .getSelectedRow(), 1)
                                    .toString();

                            gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = companySettingsDTO.getCompany_DatabaseName();
                            System.out.println("Current database ---------      " + CURRENT_DATABASE_Company_Settings);
                            System.out.println("Current database 111111111   ---------      " + CURRENT_COMPANY_Company_Settings);
                            gen.dto.Constants.CURRENT_COMPANY_NAME = tableModelCompanyList_Company_Settings.getValueAt(tablecompanyList_Company_Settings.getSelectedRow(), 0).toString();			    // insert into currentcompany table
                            gen.dto.Constants.CURRENT_COMPANY_ID = tableModelCompanyList_Company_Settings.getValueAt(tablecompanyList_Company_Settings.getSelectedRow(), 1).toString();

                            System.out
                                    .println("Cureent compnay ---- Name -----------------"
                                    + gen.dto.Constants.CURRENT_COMPANY_NAME);
                            System.out
                                    .println("Cureent compnay ---- ID -----------------"
                                    + gen.dto.Constants.CURRENT_COMPANY_ID);

                            System.out
                                    .println("Cureent compnay ---- ID -----------------"
                                    + gen.dto.Constants.CURRENT_COMPANY_CREATED_DATE);

                            gen.other.CompanySettings.CompanySettingsDAO.insert_Current_Company(
                                    tableModelCompanyList_Company_Settings
                                    .getValueAt(
                                    tablecompanyList_Company_Settings
                                    .getSelectedRow(),
                                    0).toString(),
                                    tableModelCompanyList_Company_Settings
                                    .getValueAt(
                                    tablecompanyList_Company_Settings
                                    .getSelectedRow(),
                                    1).toString());
                        }
                    }
                    JOptionPane.showMessageDialog(
                            AdSuMuDiSettingsEclipseGUI.this,
                            "Company Selected    "
                            + tableModelCompanyList_Company_Settings
                            .getValueAt(
                            tablecompanyList_Company_Settings
                            .getSelectedRow(),
                            0).toString());
                    //jTabbedPane2MouseClicked(null);
                }
            }
        });
        panel_13.add(buttonApply_Company_Settings, "cell 1 2");

        buttonExit_Company_Settings = new JButton("Exit");
        buttonExit_Company_Settings.setMnemonic('E');
        buttonExit_Company_Settings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    MainClass m = new MainClass();
                    m.menuselection(5);
                    AdSuMuDiSettingsEclipseGUI.this.setClosed(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(AdSuMuDiSettingsEclipseGUI.this.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        });
        panel_13.add(buttonExit_Company_Settings, "cell 2 2");

        panel_14 = new JPanel();
        jTabbedPane2.addTab("BackUp", null, panel_14, null);
        panel_14.setLayout(new MigLayout(
                "",
                "[0px:300px:300px,grow,shrink 50,fill][0px:300px:300px,grow,shrink 50,fill]",
                "[0px:25px:25px,grow,shrink 50,fill][grow][0px:25px:25px,grow,shrink 50,fill]"));

        lblDestination = new JLabel("Destination");
        panel_14.add(lblDestination, "flowx,cell 0 0");

        textField_15 = new JTextField();
        panel_14.add(textField_15, "cell 0 0");
        textField_15.setColumns(10);

        btnPath_Company_Back_UP = new JButton("Path");
        btnPath_Company_Back_UP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane
                        .showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                        "Space is not Allow in folder Name where You take a Back UP ");
//		btnPath_Company_Back_UPMouseClicked(null);

                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle("choosertitle");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);

                // if (chooser.showOpenDialog(null) ==
                // JFileChooser.APPROVE_OPTION) {
//		    fileChooser_Company_Back_UP = chooser.getSelectedFile();
//		    // /set selected file text ot txtDestinationPath
//		    txtDestinationPath_Company_Back_UP
//			    .setText(fileChooser_Company_Back_UP.toString());
//		} else {
//		    System.out.println("No Selection ");
//		}

            }
        });
        btnPath_Company_Back_UP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle("choosertitle");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    fileChooser_Company_Back_UP = chooser.getSelectedFile();
                    // /set selected file text ot txtDestinationPath
                    txtDestinationPath_Company_Back_UP
                            .setText(fileChooser_Company_Back_UP.toString());
                } else {
                    System.out.println("No Selection ");
                }
            }
        });
        panel_14.add(btnPath_Company_Back_UP, "cell 0 0");

        lblExampleccompanydatafilesql = new JLabel("Example:"
                + operatingSystemDrive + "\\CompanyData\\File1.sql");
        panel_14.add(lblExampleccompanydatafilesql, "cell 1 0");

        JScrollPane scrollPane_3 = new JScrollPane();
        panel_14.add(scrollPane_3, "cell 0 1,grow");

        tableCompanyList_Company_Back_UP = new JTable();
        tableCompanyList_Company_Back_UP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedCompany_index = tableCompanyList_Company_Back_UP
                        .getSelectedRow();

                // selected company_Name
                String selectedCompany_Name = ""
                        + tableCompanyList_Company_Back_UP.getValueAt(
                        selectedCompany_index, 0);
                // selected company_ID
                String selectedCompany_ID = ""
                        + tableCompanyList_Company_Back_UP.getValueAt(
                        selectedCompany_index, 1);

                // selected row must be greater than 0
                if (tableCompanyList_Company_Back_UP.getSelectedRow() >= 0) {
                    // getRowCount row must be greater than 0
                    if (tableCompanyList_Company_Back_UP.getRowCount() > 0) {
                        // find selected row from tableCompanyList
                        for (int i = 0; i < tableCompanyList_Company_Back_UP
                                .getRowCount(); i++) {
                            // selected row
                            if (tableCompanyList_Company_Back_UP
                                    .isRowSelected(i)) {
                                // move data of selected row from
                                // tableCompanyList to tableCompanyRestoreUp
                                tablemodelBackUPCompany_Company_Back_UP
                                        .setRowCount(tableCompanyBackUp_Company_Back_UP
                                        .getRowCount() + 1);
                                tableCompanyBackUp_Company_Back_UP.setValueAt(
                                        selectedCompany_Name,
                                        tablemodelBackUPCompany_Company_Back_UP
                                        .getRowCount() - 1, 0);
                                tableCompanyBackUp_Company_Back_UP.setValueAt(
                                        selectedCompany_ID,
                                        tablemodelBackUPCompany_Company_Back_UP
                                        .getRowCount() - 1, 1);
                                // remove row from table tableCompanyList
                                tablemodelCompany_Company_Back_UP.removeRow(i);
                                // decrease value of i(row) as one row removed
                                i--;
                            }
                        }
                        if (tableCompanyList_Company_Back_UP.getRowCount() > 0) {
                            tableCompanyList_Company_Back_UP.requestFocus();
                            tableCompanyList_Company_Back_UP
                                    .setRowSelectionInterval(0, 0);
                            tableCompanyBackUp_Company_Back_UP.clearSelection();
                        } else {
                            tableCompanyBackUp_Company_Back_UP.requestFocus();
                            tableCompanyBackUp_Company_Back_UP
                                    .setRowSelectionInterval(0, 0);
                        }
                    }
                }
            }
        });
        scrollPane_3.setColumnHeaderView(tableCompanyList_Company_Back_UP);

        JScrollPane scrollPane_4 = new JScrollPane();
        panel_14.add(scrollPane_4, "cell 1 1,grow");

        tableCompanyBackUp_Company_Back_UP = new JTable();
        tableCompanyBackUp_Company_Back_UP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedCompany_index = tableCompanyBackUp_Company_Back_UP
                        .getSelectedRow();
                // selected company_Name
                String selectedCompany_Name = ""
                        + tableCompanyBackUp_Company_Back_UP.getValueAt(
                        selectedCompany_index, 0);
                // selected company_ID
                String selectedCompany_ID = ""
                        + tableCompanyBackUp_Company_Back_UP.getValueAt(
                        selectedCompany_index, 1);

                // selected row must be greater than 0
                if (tableCompanyBackUp_Company_Back_UP.getSelectedRow() >= 0) {
                    // getRowCount row must be greater than 0
                    if (tableCompanyBackUp_Company_Back_UP.getRowCount() > 0) {
                        // find selected row from tableCompanyRestoreUp
                        for (int i = 0; i < tableCompanyBackUp_Company_Back_UP
                                .getRowCount(); i++) {
                            // selected Row
                            if (tableCompanyBackUp_Company_Back_UP
                                    .isRowSelected(i)) {
                                // move data of selected row from
                                // tableCompanyBackUp to tableCompanyList
                                tablemodelCompany_Company_Back_UP
                                        .setRowCount(tableCompanyList_Company_Back_UP
                                        .getRowCount() + 1);
                                tableCompanyList_Company_Back_UP.setValueAt(
                                        selectedCompany_Name,
                                        tableCompanyList_Company_Back_UP
                                        .getRowCount() - 1, 0);
                                tableCompanyList_Company_Back_UP.setValueAt(
                                        selectedCompany_ID,
                                        tableCompanyList_Company_Back_UP
                                        .getRowCount() - 1, 1);
                                // remove row from table tableCompanyBackUp
                                tablemodelBackUPCompany_Company_Back_UP
                                        .removeRow(i);
                                // decrease value of i(row) as one row removed
                                i--;
                            }
                        }
                        if (tableCompanyBackUp_Company_Back_UP.getRowCount() > 0) {
                            tableCompanyBackUp_Company_Back_UP.requestFocus();
                            tableCompanyBackUp_Company_Back_UP
                                    .setRowSelectionInterval(0, 0);
                            tableCompanyList_Company_Back_UP.clearSelection();
                        } else {
                            tableCompanyList_Company_Back_UP.requestFocus();
                            tableCompanyList_Company_Back_UP
                                    .setRowSelectionInterval(0, 0);
                        }
                    }
                }
            }
        });
        scrollPane_4.setColumnHeaderView(tableCompanyBackUp_Company_Back_UP);

        btnBackUP_Company_Back_UP = new JButton("BackUp");
        btnBackUP_Company_Back_UP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // check Validation
                if (validation_Company_Back_UP()) {
                    try {
                        Boolean flag = true;

                        // ExecutorService executor =
                        // Executors.newSingleThreadExecutor();
                        // Future<String> future = executor.submit(new Task());

                        // Thread thread = new MyThread();
                        // thread.start();
                        //
                        // thread.join();
                        //
                        // Thread thread1 = new MyThread1();
                        // thread1.start();

                        // for finding database of everyrow of back up table one
                        // by one
                        for (int i = 0; i < tableCompanyBackUp_Company_Back_UP
                                .getRowCount(); i++) {
                            System.out.println("row count");
                            String database = "";
                            for (CompanySettingsDTO companySettingsDTO : company_Information_DTOList_Company_Back_UP) {
                                System.out.println("row ");
                                // set Database Name of selected_Company for
                                // Database_BackUP
                                if (companySettingsDTO
                                        .getCompany_name()
                                        .equals(tableCompanyBackUp_Company_Back_UP
                                        .getValueAt(i, 0).toString()
                                        .trim())
                                        && companySettingsDTO
                                        .getCompany_id()
                                        .toString()
                                        .equals(tableCompanyBackUp_Company_Back_UP
                                        .getValueAt(i, 1)
                                        .toString().trim())) {
                                    database = companySettingsDTO
                                            .getCompany_DatabaseName();
                                    System.out.println("3453453454354"
                                            + database);
                                    // take a backUP and check Bolean value so
                                    // that remaining functinality should not
                                    // work
                                    // backupDB_Company_Back_UP(database,
                                    // "root","adm",
                                    // fileChooser_Company_Back_UP+ "/" +
                                    // database + ".sql");
                                    Connection conn = null;
                                    // conn =
                                    // DatabaseConnection1.GetConnection();

                                    databaseClassName = "org.hsqldb.jdbcDriver";
                                    instantURL = "jdbc:hsqldb:data/";
                                    //instantURL = "jdbc:hsqldb:";
//				    databaseName = "companies_details";
                                    databaseName = database;
                                    //		databaseName = "first";
                                    userName = "sa";
                                    password = "";
                                    String key = "604a6105889da65326bf35790a923932";
                                    String getColumnName = "false";

                                    // if
                                    // ("1".equalsIgnoreCase(gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING))
                                    // {
//					databaseName = "companies_details";
                                    // System.out.println("COMPANYDETAILS_DATABASE_CALLING   11111111  -------- ++++++++++ "
                                    // + databaseName);
                                    // } else if
                                    // (!gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings.isEmpty())
                                    // {
                                    // databaseName =
                                    // gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings;
                                    // System.out.println("Check in the gertFuncton -------- ++++++++++ "
                                    // + databaseName);
//				    }

                                    Class.forName(databaseClassName)
                                            .newInstance();
                                    Properties props = new Properties();

                                    System.out
                                            .println("Database NMae ----------"
                                            + databaseName);

                                    props.put("user", userName);
                                    props.put("password", password);
                                    props.put("jdbc.strict_md", "false");
                                    props.put("jdbc.get_column_name",
                                            getColumnName);
                                    conn = DriverManager.getConnection(
                                            instantURL + databaseName
                                            + "; crypt_key=" + key
                                            + ";crypt_type=AES", props);

                                    // String backup_Path =
                                    // txtDestinationPath_Company_Back_UP.getText().toString().trim();
                                    System.out.println("");
                                    // String q = "BACKUP DATABASE TO "+
                                    // backup_Path
                                    // +"\\"+databaseName+"Maximus.tgz" +
                                    // "BLOCKING";
                                    String check = "'D:/jasper_reports" + "\\"
                                            + databaseName + "Maximus.tgz'";
                                    // String q = "BACKUP DATABASE TO "+
                                    // "\""+check+"\""+ " BLOCKING";
                                    String q = "BACKUP DATABASE TO " + check
                                            + " BLOCKING";
                                    System.out.println("qqqqqqqqqqqqqqq =" + q);
                                    PreparedStatement prmt = conn
                                            .prepareStatement(q);
                                    prmt.execute();

                                }
                            }
                        }

                        // check Boolean value so that remaining functinality
                        // should not work
                        if (flag) {
                            // / create file with fileName of
                            // database.enc,database,company_name
                            createcompanyfile_Company_Back_UP(fileChooser_Company_Back_UP
                                    + "/");

                            // Encrypt the file
                            encryptFile_Company_Back_UP();

                            // Zip the Folder
                            Zipfile_Company_Back_UP(fileChooser_Company_Back_UP
                                    + "/");
                        }

                        initialise_Company_Back_UP();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Logger.getLogger(
                                AdSuMuDiSettingsEclipseGUI.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        panel_14.add(btnBackUP_Company_Back_UP, "cell 0 2");

        panel_15 = new JPanel();
        jTabbedPane2.addTab("Restore", null, panel_15, null);
        panel_15.setLayout(new MigLayout(
                "",
                "[0px:300px:300px,grow,shrink 50,fill][0px:300px:300px,grow,shrink 50,fill]",
                "[0px:25px:25px,grow,shrink 50,fill][grow][0px:25px:25px,grow,shrink 50,fill]"));

        lblSource = new JLabel("Source");
        panel_15.add(lblSource, "flowx,cell 0 0");

        txtSourcePath_Company_Restore = new JTextField();
        txtSourcePath_Company_Restore.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                initialise_Company_Restore();
                bindData2GUI_Company_Restore();
            }
        });
        panel_15.add(txtSourcePath_Company_Restore, "cell 0 0");
        txtSourcePath_Company_Restore.setColumns(10);

        buttonFilePath_Company_Restore = new JButton("Path");
        buttonFilePath_Company_Restore.setMnemonic('P');
        buttonFilePath_Company_Restore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //buttonFilePath_Company_RestoreMouseClicked(null);
            }
        });
        buttonFilePath_Company_Restore.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle("choosertitle");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    // Remove spaces between Name in Selecetd folder path
                    // String output =
                    // chooser.getSelectedFile().toString().replaceAll("\\s+","");
                    fileChooser_Company_Restore = chooser.getSelectedFile();
                    txtSourcePath_Company_Restore
                            .setText(fileChooser_Company_Restore.toString());
                    //txtSourcePath_Company_RestoreKeyPressed(null);
                } else {
                    System.out.println("No Selection ");
                }
            }
        });
        panel_15.add(buttonFilePath_Company_Restore, "cell 0 0");

        lblExamplec = new JLabel("Example:" + operatingSystemDrive
                + "\\CompanyData\\File1.sql");
        panel_15.add(lblExamplec, "cell 1 0");

        JScrollPane scrollPane_5 = new JScrollPane();
        panel_15.add(scrollPane_5, "cell 0 1,grow");

        tableCompanyList_Company_Restore = new JTable();
        tableCompanyList_Company_Restore.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedCompany_index = tableCompanyList_Company_Restore
                        .getSelectedRow();

                // selected company_Name
                String selectedCompany_Name = ""
                        + tableCompanyList_Company_Restore.getValueAt(
                        selectedCompany_index, 0);
                // selected company_ID
                String selectedCompany_ID = ""
                        + tableCompanyList_Company_Restore.getValueAt(
                        selectedCompany_index, 1);

                // selected row must be greater than 0
                if (tableCompanyList_Company_Restore.getSelectedRow() >= 0) {
                    // getRowCount row must be greater than 0
                    if (tableCompanyList_Company_Restore.getRowCount() > 0) {
                        // find selected row from tableCompanyList
                        for (int i = 0; i < tableCompanyList_Company_Restore
                                .getRowCount(); i++) {
                            // selected row
                            if (tableCompanyList_Company_Restore
                                    .isRowSelected(i)) {
                                // move data of selected row from
                                // tableCompanyList to tableCompanyRestoreUp
                                tablemodelRestoreCompany_Company_Restore
                                        .setRowCount(tableCompanyRestoreUp_Company_Restore
                                        .getRowCount() + 1);
                                tableCompanyRestoreUp_Company_Restore
                                        .setValueAt(selectedCompany_Name,
                                        tableCompanyRestoreUp_Company_Restore
                                        .getRowCount() - 1, 0);
                                tableCompanyRestoreUp_Company_Restore
                                        .setValueAt(selectedCompany_ID,
                                        tableCompanyRestoreUp_Company_Restore
                                        .getRowCount() - 1, 1);
                                // remove row from table tableCompanyList
                                tablemodelCompany_Company_Restore.removeRow(i);
                                // decrease value of i(row) as one row removed
                                i--;
                            }
                        }

                        if (tableCompanyList_Company_Restore.getRowCount() > 0) {
                            tableCompanyList_Company_Restore.requestFocus();
                            tableCompanyList_Company_Restore
                                    .setRowSelectionInterval(0, 0);
                            tableCompanyRestoreUp_Company_Restore
                                    .clearSelection();
                        } else {
                            tableCompanyRestoreUp_Company_Restore
                                    .requestFocus();
                            tableCompanyRestoreUp_Company_Restore
                                    .setRowSelectionInterval(0, 0);
                        }
                    }
                }
            }
        });
        scrollPane_5.setColumnHeaderView(tableCompanyList_Company_Restore);

        JScrollPane scrollPane_6 = new JScrollPane();
        panel_15.add(scrollPane_6, "cell 1 1,grow");

        tableCompanyRestoreUp_Company_Restore = new JTable();
        tableCompanyRestoreUp_Company_Restore
                .addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedCompany_index = tableCompanyRestoreUp_Company_Restore
                        .getSelectedRow();

                // selected company_Name
                String selectedCompany_Name = ""
                        + tableCompanyRestoreUp_Company_Restore
                        .getValueAt(selectedCompany_index, 0);

                // selected company_ID
                String selectedCompany_ID = ""
                        + tableCompanyRestoreUp_Company_Restore
                        .getValueAt(selectedCompany_index, 1);

                // selected row must be greater than 0
                if (tableCompanyRestoreUp_Company_Restore
                        .getSelectedRow() >= 0) {
                    // getRowCount row must be greater than 0
                    if (tableCompanyRestoreUp_Company_Restore
                            .getRowCount() > 0) {
                        // find selected row from tableCompanyRestoreUp
                        for (int i = 0; i < tableCompanyRestoreUp_Company_Restore
                                .getRowCount(); i++) {
                            // selected Row
                            if (tableCompanyRestoreUp_Company_Restore
                                    .isRowSelected(i)) {
                                // move data of selected row from
                                // tableCompanyRestoreUp to
                                // tableCompanyList
                                tablemodelCompany_Company_Restore
                                        .setRowCount(tableCompanyList_Company_Restore
                                        .getRowCount() + 1);
                                tableCompanyList_Company_Restore
                                        .setValueAt(
                                        selectedCompany_Name,
                                        tableCompanyList_Company_Restore
                                        .getRowCount() - 1,
                                        0);
                                tableCompanyList_Company_Restore
                                        .setValueAt(
                                        selectedCompany_ID,
                                        tableCompanyList_Company_Restore
                                        .getRowCount() - 1,
                                        1);
                                // remove row from table
                                // tableCompanyRestoreUp
                                tablemodelRestoreCompany_Company_Restore
                                        .removeRow(i);
                                // decrease value of i(row) as one row
                                // removed
                                i--;
                            }
                        }
                        if (tableCompanyRestoreUp_Company_Restore
                                .getRowCount() > 0) {
                            tableCompanyRestoreUp_Company_Restore
                                    .requestFocus();
                            tableCompanyRestoreUp_Company_Restore
                                    .setRowSelectionInterval(0, 0);
                            tableCompanyList_Company_Restore
                                    .clearSelection();
                        } else {
                            tableCompanyList_Company_Restore
                                    .requestFocus();
                            tableCompanyList_Company_Restore
                                    .setRowSelectionInterval(0, 0);
                        }
                    }
                }
            }
        });
        scrollPane_6.setColumnHeaderView(tableCompanyRestoreUp_Company_Restore);

        btnRestore_Company_Restore = new JButton("Restore");
        btnRestore_Company_Restore.setMnemonic('E');
        btnRestore_Company_Restore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // kept pending
            }
        });
        panel_15.add(btnRestore_Company_Restore, "cell 0 2");

        panel_16 = new JPanel();
        jTabbedPane2.addTab("Splitting", null, panel_16, null);
        panel_16.setLayout(new MigLayout(
                "",
                "[0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][grow]",
                "[0px:25px:25px,grow,fill][grow,shrink 50][0px:25px:25px,grow,shrink 50,fill]"));

        JDateChooser dateChooser = new JDateChooser();
        panel_16.add(dateChooser, "cell 1 0 2 1,grow");

        JScrollPane scrollPane_1 = new JScrollPane();
        panel_16.add(scrollPane_1, "cell 0 1 4 1,grow");

        tableCompanyList_Database_Splitng = new JTable();
        tableCompanyList_Database_Splitng.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    //tableCompanyList_Database_SplitngMouseClicked(null);
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    tfDatePicker_Database_Splitng.requestFocus();
                    tableCompanyList_Database_Splitng.clearSelection();
                }
            }
        });
        tableCompanyList_Database_Splitng.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedCompany_index = tableCompanyList_Database_Splitng
                        .getSelectedRow();
                selected_Company_Name_Database_Splitng = ""
                        + tableCompanyList_Database_Splitng
                        .getValueAt(selectedCompany_index, 0)
                        .toString().trim();
                selected_Company_id_Database_Splitng = ""
                        + tableCompanyList_Database_Splitng
                        .getValueAt(selectedCompany_index, 1)
                        .toString().trim();

                System.out.println("selectedCompany ------------------------ "
                        + selected_Company_Name_Database_Splitng);
                System.out.println("company_id         ----------------------"
                        + selected_Company_id_Database_Splitng);
            }
        });
        tableCompanyList_Database_Splitng.setModel(new DefaultTableModel(
                new Object[][]{{null, null}, {null, null},
                    {null, null}, {null, null}, {null, null},
                    {null, null}, {null, null}, {null, null},
                    {null, null}, {null, null}, {null, null},
                    {null, null}, {null, null}, {null, null},
                    {null, null},}, new String[]{"New column",
                    "New column"}));
        scrollPane_1.setColumnHeaderView(tableCompanyList_Database_Splitng);

        btnDataSpliting_Database_Splitng = new JButton("Database Splitting");
        btnDataSpliting_Database_Splitng.setMnemonic('T');
        btnDataSpliting_Database_Splitng
                .addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validation_Database_Splitng()) {
                    String fromDateOpenStr = "";
                    if (fromJDateChooser_Database_Splitng.getDate() != null) {
                        try {
                            fromDateOpenStr = Constants.simpleDateFormatDatabaseWithDash
                                    .format(fromJDateChooser_Database_Splitng
                                    .getDate());
                            Calendar c = Calendar.getInstance();
                            c.setTime(Constants.simpleDateFormatDatabaseWithDash
                                    .parse(Constants.simpleDateFormatDatabaseWithDash
                                    .format(fromJDateChooser_Database_Splitng
                                    .getDate())));
                            // number of days to add for calculation
                            // opening_Balances
                            c.add(Calendar.DATE, 1);
                            fromDateOpenStr = Constants.simpleDateFormatDatabaseWithDash
                                    .format(c.getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            Logger.getLogger(
                                    AdSuMuDiSettingsEclipseGUI.class
                                    .getName()).log(
                                    Level.SEVERE, null, ex);
                        }
                    }

                    List<String> new_CreateCompanyList = new ArrayList<String>();
                    List<Company_InformationDTO> new_databaseCreateList = new ArrayList<Company_InformationDTO>();

                    String new_Company_Create = "";

                    // StringTokenizer used to remove '-' from Date
                    StringTokenizer add_InDatabase_Name = new StringTokenizer(
                            tfDatePicker_Database_Splitng.getText()
                            .toString().trim(), "-");
                    String date = "";
                    String month = "";
                    String year = "";
                    int count = 0;
                    while (add_InDatabase_Name.hasMoreTokens()
                            && count < 3) {
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

                    // int check_Company_Name_count = 0;
                    // for (CompanySettingsDTO companyListDTO :
                    // company_ListDTO_Database_Splitng) {
                    // if
                    // (companyListDTO.getCompany_name().contentEquals(tfUnderField.getText().toString().trim()
                    // + add_Date_InComapnyName)) {
                    // // set check_Name_conter = 1 if company is
                    // already present
                    // check_Company_Name_count = 1;
                    // }
                    // }

                    // if check_Name_conter = 0 means company is not
                    // already present
                    // if (check_Company_Name_count == 0) {
                    try {

                        // ADDD date in Name of New Company
                        new_Company_Create = selected_Company_Name_Database_Splitng
                                + " from " + add_Date_InComapnyName;

                        // Database Name of OLD Company
                        String dataBase_OlD_Company = "";
                        String company_ID_OlD_Company = "";

                        // get the first Word from the company_name to
                        // add in database with id
                        String[] result = selected_Company_Name_Database_Splitng
                                .split(" ", 2);
                        String first_Word = "";
                        String rest_Words = "";
                        // use st that ArrayIndex of bound problem shuld
                        // not occcur if only 1 word is present
                        for (int i = 0; i < result.length; i++) {
                            if (i == 0) {
                                first_Word = result[0];
                            } else if (i == 1) {
                                rest_Words = result[1];
                            }
                        }
                        System.out.println("First: " + first_Word);
                        System.out.println("Rest: " + rest_Words);

                        for (CompanySettingsDTO company_settings : company_ListDTO_Database_Splitng) {
                            // take Back UP of Database of selected
                            // Company List
                            if (selected_Company_Name_Database_Splitng
                                    .toString()
                                    .trim()
                                    .equals(company_settings
                                    .getCompany_name())
                                    && selected_Company_id_Database_Splitng
                                    .toString()
                                    .trim()
                                    .equals(company_settings
                                    .getCompany_id()
                                    .toString().trim())) {
                                // take Back UP of Database of selected
                                // Company having databse NMAe
                                // company_settings.getCompany_DatabaseName()
                                // and company_settings.getCompany_id()
                                backupDB_Database_Splitng(
                                        company_settings
                                        .getCompany_DatabaseName(),
                                        "root",
                                        "adm",
                                        "D:\\databasecheck/"
                                        + first_Word
                                        + company_settings
                                        .getCompany_id()
                                        + ".sql");
                                dataBase_OlD_Company = company_settings
                                        .getCompany_DatabaseName();
                                company_ID_OlD_Company = company_settings
                                        .getCompany_id().toString()
                                        .trim();
                            }
                        }

                        // add New company Name in new_CreateCompanyList
                        new_CreateCompanyList.add(new_Company_Create);

                        // find id to ADD in Database Name of New
                        // Created Company
                        String add_ID_To_NewCompany = gen.other.CompanySettings.CompanySettingsDAO
                                .get_Max_Company_ID();

                        // get the first Word from the New_company_name
                        // to add in database with id
                        String[] result1 = new_Company_Create.split(
                                " ", 2);
                        String first_Word1 = "";
                        String rest_Words1 = "";
                        // use st that ArrayIndex of bound problem shuld
                        // not occcur if only 1 word is present
                        for (int i = 0; i < result1.length; i++) {
                            if (i == 0) {
                                first_Word1 = result1[0];
                            } else if (i == 1) {
                                rest_Words1 = result1[1];
                            }
                        }

                        String new_Company_Database = first_Word1
                                + add_ID_To_NewCompany;
                        Company_InformationDTO company_InformationDTO = new Company_InformationDTO();
                        // add New company Database Name in
                        // company_InformationDTO
                        company_InformationDTO
                                .setCompany_Database_Name(new_Company_Database);
                        new_databaseCreateList
                                .add(company_InformationDTO);
                        // Create New Database for Newly created Company
                        gen.other.DatabaseSpiliting.DatabaseSplitingDAO
                                .createNewDatabase(new_databaseCreateList);

                        // String pass_Database_Name =
                        // tfUnderField.getText().toString().trim()
                        // +"$"+ add_date;
                        // flag to check if restore Database correct or
                        // not
                        // if exception occur then flag = false
                        Boolean flag = restoreDB_Database_Splitng(
                                new_Company_Database, "root", "adm",
                                "D:\\databasecheck/" + first_Word
                                + company_ID_OlD_Company
                                + ".sql");

                        System.out
                                .println("Newl y Created Company ------------------------------");
                        // if flag true then add all information in
                        // Company_inormation table
                        if (flag) {
                            List<CompanySettingsDTO> companySettings_InformationDTOList = new ArrayList<CompanySettingsDTO>();
                            gen.other.DatabaseSpiliting.DatabaseSplitingDAO
                                    .insert_NewlyCreated_Company(
                                    new_CreateCompanyList,
                                    new_Company_Database,
                                    selected_Company_Name_Database_Splitng,
                                    selected_Company_id_Database_Splitng
                                    .toString(),
                                    companySettings_InformationDTOList);
                        } else {
                            JOptionPane.showMessageDialog(
                                    AdSuMuDiSettingsEclipseGUI.this,
                                    "Error in the Datbse Restore");
                        }

                        System.out
                                .println("Delete path-------------------------");
                        // delete the .SQL file
                        Path path = Paths.get("D:\\databasecheck/"
                                + first_Word + company_ID_OlD_Company
                                + ".sql");
                        Files.delete(path);

                        // delete record from table according to Date
                        gen.other.DatabaseSpiliting.DatabaseSplitingDAO
                                .delete_Records(new_Company_Database,
                                fromDateOpenStr);

                        initialise_Database_Spliting();

                        // gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings
                        // = "";
                        try {
                            AdSuMuDiSettingsEclipseGUI.this
                                    .setSelected(true);
                            MainClass mainClass = new MainClass();
                            mainClass.menuselection(1);
                            AdSuMuDiSettingsEclipseGUI.this
                                    .setClosed(true);
                        } catch (java.beans.PropertyVetoException ex) {
                            ex.printStackTrace();
                        }
                        call_Selection_Companies_Database_Splitng();
                    } catch (Exception ex) {
                        Logger.getLogger(
                                AdSuMuDiSettingsEclipseGUI.class
                                .getName()).log(Level.SEVERE,
                                null, ex);
                    }
                    gen.other.DatabaseSpiliting.DatabaseSplitingDAO
                            .delete_Records(null, fromDateOpenStr);

                    // }
                }
            }
        });
        panel_16.add(btnDataSpliting_Database_Splitng, "cell 1 2 2 1");

        JPanel panel_17 = new JPanel();
        jTabbedPane2.addTab("Create/Alter Company", null, panel_17, null);
        panel_17.setLayout(new MigLayout(
                "",
                "[0px:500px:500px,grow,shrink 50,fill][][0px:150px:150px,grow,shrink 50,fill][][0px:400px:400px,grow,shrink 50,fill][0px:230px:230px,grow,shrink 50,fill]",
                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));
        Calendar currentDate = Calendar.getInstance();

        JPanel panel_20 = new JPanel();
        panel_20.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_17.add(panel_20, "cell 0 0 1 18,grow");
        panel_20.setLayout(new MigLayout("", "[grow]", "[grow]"));

        JScrollPane scrollPane_2 = new JScrollPane();
        panel_20.add(scrollPane_2, "cell 0 0,grow");

        tablecompanyList_Company_Delete = new JTable();
        tablecompanyList_Company_Delete.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // setFocus(event);
                    // tablecompanyList_Company_DeleteMouseClicked(null);
                    for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList_Company_Delete) {
                        if (companySettingsDTO.getCompany_name().equals(
                                tableModelCompanyList_Company_Delete
                                .getValueAt(
                                tablecompanyList_Company_Delete
                                .getSelectedRow(), 0)
                                .toString())) {
                            alter_Company(
                                    tableModelCompanyList_Company_Delete
                                    .getValueAt(
                                    tablecompanyList_Company_Delete
                                    .getSelectedRow(),
                                    0).toString(),
                                    tableModelCompanyList_Company_Delete
                                    .getValueAt(
                                    tablecompanyList_Company_Delete
                                    .getSelectedRow(),
                                    1).toString());
                            // source = "";
                            btnCompanyCreate1.setEnabled(false);
                            btnUpdate1.setEnabled(true);
                            btnDelete1.setEnabled(true);
                            btnupdateLogo.setEnabled(true);

                        }
                    }
                }
            }
        });
        tablecompanyList_Company_Delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // set current selected Company DataBase
                for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList_Company_Delete) {
                    if (companySettingsDTO.getCompany_name().equals(
                            tableModelCompanyList_Company_Delete.getValueAt(
                            tablecompanyList_Company_Delete
                            .getSelectedRow(), 0).toString())) {
                        alter_Company(
                                tableModelCompanyList_Company_Delete
                                .getValueAt(
                                tablecompanyList_Company_Delete
                                .getSelectedRow(), 0)
                                .toString(),
                                tableModelCompanyList_Company_Delete
                                .getValueAt(
                                tablecompanyList_Company_Delete
                                .getSelectedRow(), 1)
                                .toString());
                        btnCompanyCreate1.setEnabled(false);
                        btnUpdate1.setEnabled(true);
                        btnDelete1.setEnabled(true);
                        btnupdateLogo.setEnabled(true);

                    }
                }
            }
        });
        tablecompanyList_Company_Delete.setModel(new DefaultTableModel(
                new Object[][]{ // {null, null}, {null, null},
                // {null, null}, {null, null}, {null, null},
                // {null, null}, {null, null}, {null, null},
                // {null, null}, {null, null}, {null, null},
                // {null, null}, {null, null}, {null, null},
                // {null, null},
                }, new String[]{"New column", "New column"}));
        scrollPane_2.setColumnHeaderView(tablecompanyList_Company_Delete);

        label = new JLabel("");
        panel_17.add(label, "cell 1 1");

        label_1 = new JLabel("");
        panel_17.add(label_1, "cell 3 1");

        LogoPanelView1 = new JLabel("");
        LogoPanelView1.setMinimumSize(new Dimension(50, 50));
        LogoPanelView1.setMaximumSize(new Dimension(250, 250));
        LogoPanelView1.setPreferredSize(new Dimension(250, 250));
        LogoPanelView1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_17.add(LogoPanelView1, "cell 2 0 1 5");

        JPanel panel_18 = new JPanel();
        panel_18.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_17.add(panel_18, "cell 4 0 1 18,grow");

        txtNameOfGod1 = new JTextField();
        txtNameOfGod1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtCompanyName1.requestFocus();
                }
            }
        });
        panel_18.setLayout(new MigLayout("", "[0px:50px:50px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));
        panel_18.add(txtNameOfGod1, "cell 0 0 3 1,growx,aligny top");
        txtNameOfGod1.setColumns(10);

        lblApplicableForm = new JLabel("Applicable From");
        panel_18.add(lblApplicableForm, "cell 0 1 2 1");

        JdateChooser_CompanyApplicableFrom = new JDateChooser();
        panel_18.add(JdateChooser_CompanyApplicableFrom, "cell 2 1,grow");

        JdateChooser_CompanyApplicableFrom.setDateFormatString("dd-MM-yyyy");
        //Calendar currentDate = Calendar.getInstance();
        JdateChooser_CompanyApplicableFrom.setDate(currentDate.getTime());

        JdateChooser_CompanyApplicableFrom
                .setDateFormatString(Constants.DATE_FORMAT_STRING);
        tfDatePicker_Company_Applicable_From = (JTextField) JdateChooser_CompanyApplicableFrom
                .getComponent(1);

        tfDatePicker_Company_Applicable_From.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_ENTER) {
                    //btnDataSpliting_Database_Splitng.requestFocus();
                }
                if (code == KeyEvent.VK_ESCAPE) {
                    // tfUnderField.requestFocus();
                }
            }
        });

        tfDatePicker_Company_Applicable_From
                .addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt,
                            tfDatePicker_Database_Splitng);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            AdSuMuDiSettingsEclipseGUI.this,
                            ex.getMessage());
                }
            }
        });

        lblcompany_ID1 = new JLabel("ID");
        lblcompany_ID1.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_18.add(lblcompany_ID1, "cell 3 1,alignx left,growy");

        lblNameOfCompany = new JLabel("Name Of Company");
        panel_18.add(lblNameOfCompany, "cell 0 2,grow");

        txtCompanyName1 = new JTextField();
        txtCompanyName1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // validation for Specical character and only number
                    if (validation_FOR_CreateCompany_Naming(txtCompanyName1
                            .getText().toString().trim())) {
                        txtCompanyAlias1.requestFocus();
                    } else {
                        txtCompanyName1.requestFocus();
                    }

                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtNameOfGod1.requestFocus();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                filterCharacter(e, txtCompanyName1);

            }
        });
        panel_18.add(txtCompanyName1, "cell 2 2 2 1,growx,aligny top");
        txtCompanyName1.setColumns(10);

        lblAlias = new JLabel("Alias");
        panel_18.add(lblAlias, "cell 0 3,grow");

        txtCompanyAlias1 = new JTextField();
        txtCompanyAlias1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtCompanyTagLine1.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtCompanyName1.requestFocus();
                }
            }
        });
        panel_18.add(txtCompanyAlias1, "cell 2 3 2 1,growx,aligny top");
        txtCompanyAlias1.setColumns(10);

        lblTagline = new JLabel("TagLine");
        panel_18.add(lblTagline, "cell 0 4,grow");

        txtCompanyTagLine1 = new JTextField();
        txtCompanyTagLine1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtCompanyAddress1.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtCompanyAlias1.requestFocus();
                }
            }
        });
        panel_18.add(txtCompanyTagLine1, "cell 2 4 2 1,growx,aligny top");
        txtCompanyTagLine1.setColumns(10);

        lblAddress = new JLabel("Address");
        panel_18.add(lblAddress, "cell 0 5,grow");

        //txtCompanyAddress1 = new JTextField();
        txtCompanyAddress1 = new JTextArea();
        txtCompanyAddress1.setBorder(javax.swing.BorderFactory
                .createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCompanyAddress1.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(txtCompanyAddress1);
        txtCompanyAddress1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtCompanyContact1.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtCompanyTagLine1.requestFocus();
                }
            }
        });
        panel_18.add(scroll, "cell 2 5 2 1,growx,aligny top");
        txtCompanyAddress1.setColumns(10);

        JLabel lblContactNumber = new JLabel("Contact Number");
        panel_18.add(lblContactNumber, "cell 0 6,grow");

        txtCompanyContact1 = new JTextField();
        txtCompanyContact1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtCompanyMail1.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtCompanyAddress1.requestFocus();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                Util.filterForContactNo(e, txtCompanyContact1);
            }
        });
        panel_18.add(txtCompanyContact1, "cell 2 6 2 1,growx,aligny top");
        txtCompanyContact1.setColumns(10);

        lblEmailId_1 = new JLabel("E-Mail ID");
        panel_18.add(lblEmailId_1, "cell 0 7,grow");

        txtCompanyMail1 = new JTextField();
        txtCompanyMail1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtCompanyITN1.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtCompanyContact1.requestFocus();
                }
            }
        });
        panel_18.add(txtCompanyMail1, "cell 2 7 2 1,grow");
        txtCompanyMail1.setColumns(10);

        lblNewLabel_2 = new JLabel("Income Tax Number");
        panel_18.add(lblNewLabel_2, "cell 0 8,grow");

        txtCompanyITN1 = new JTextField();
        txtCompanyITN1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtCompanySTN1.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtCompanyMail1.requestFocus();
                }
            }
        });
        panel_18.add(txtCompanyITN1, "cell 2 8 2 1,growx,aligny top");
        txtCompanyITN1.setColumns(10);

        lblNewLabel_3 = new JLabel("VAT TIN Number");
        panel_18.add(lblNewLabel_3, "cell 0 9,grow");

        txtCompanySTN1 = new JTextField();
        txtCompanySTN1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtCompanyCSTNo1.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtCompanyITN1.requestFocus();
                }
            }
        });
        panel_18.add(txtCompanySTN1, "cell 2 9 2 1,growx,aligny top");
        txtCompanySTN1.setColumns(10);

        lblCstNumber = new JLabel("CST Number");
        panel_18.add(lblCstNumber, "cell 0 10,grow");

        txtCompanyCSTNo1 = new JTextField();
        txtCompanyCSTNo1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    jtextFieldLBTNo.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtCompanySTN1.requestFocus();
                }
            }
        });
        panel_18.add(txtCompanyCSTNo1, "cell 2 10 2 1,growx,aligny top");
        txtCompanyCSTNo1.setColumns(10);

        lblLbtNumber = new JLabel("LBT Number");
        panel_18.add(lblLbtNumber, "cell 0 11,grow");

        jtextFieldLBTNo = new JTextField();
        jtextFieldLBTNo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtCompanyDeclaration1.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtCompanyCSTNo1.requestFocus();
                }
            }
        });
        panel_18.add(jtextFieldLBTNo, "cell 2 11 2 1,growx,aligny top");
        jtextFieldLBTNo.setColumns(10);

        lblNewLabel_4 = new JLabel("Declaration");
        panel_18.add(lblNewLabel_4, "cell 0 12,grow");

        //txtCompanyDeclaration1 = new JTextField();
        txtCompanyDeclaration1 = new JTextArea();
        txtCompanyDeclaration1.setBorder(javax.swing.BorderFactory
                .createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCompanyDeclaration1.setLineWrap(true);
        JScrollPane scrolltxtCompanyDeclaration1 = new JScrollPane(
                txtCompanyDeclaration1);
        txtCompanyDeclaration1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtCompanyTermsConditions1.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jtextFieldLBTNo.requestFocus();
                }
            }
        });
        panel_18.add(scrolltxtCompanyDeclaration1,
                "cell 2 12 2 1,growx,aligny top");
        txtCompanyDeclaration1.setColumns(10);

        lblTermsAndConditions = new JLabel("Terms/Conditions");
        panel_18.add(lblTermsAndConditions, "cell 0 13,grow");

        //txtCompanyTermsConditions1 = new JTextField();
        txtCompanyTermsConditions1 = new JTextArea();
        txtCompanyTermsConditions1.setBorder(javax.swing.BorderFactory
                .createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCompanyTermsConditions1.setLineWrap(true);
        JScrollPane scrolltxtCompanyTermsConditions1 = new JScrollPane(
                txtCompanyTermsConditions1);
        txtCompanyTermsConditions1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtCompanySignature1.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtCompanyDeclaration1.requestFocus();
                }
            }
        });
        panel_18.add(scrolltxtCompanyTermsConditions1,
                "cell 2 13 2 1,growx,aligny top");
        txtCompanyTermsConditions1.setColumns(10);

        lblSignature = new JLabel("Signing Authority");
        panel_18.add(lblSignature, "cell 0 14,grow");

        txtCompanySignature1 = new JTextField();
        txtCompanySignature1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtCompanyIdNo.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtCompanyTermsConditions1.requestFocus();
                }
            }
        });
        panel_18.add(txtCompanySignature1, "cell 2 14 2 1,growx,aligny top");
        txtCompanySignature1.setColumns(10);
        
        lblcompanyIdNo= new JLabel("Company Identification No");
        panel_18.add(lblcompanyIdNo, "cell 0 15,grow");

        txtCompanyIdNo = new JTextField();
        txtCompanyIdNo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnCompanyCreate1.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtCompanySignature1.requestFocus();
                }
            }
        });
        panel_18.add(txtCompanyIdNo, "cell 2 15 2 1,growx,aligny top");
        txtCompanyIdNo.setColumns(10);
        

        JPanel panel_19 = new JPanel();
        panel_18.add(panel_19, "cell 0 16 5 2,grow");
        panel_19.setLayout(new MigLayout(
                "",
                "[0px:75px:75px,grow,shrink 50,fill][0px:75px:75px,grow,shrink 50,fill][0px:75px:75px,grow,shrink 50,fill][0px:75px:75px,grow,shrink 50,fill][0px:75px:75px,grow,shrink 50,fill]",
                "[][][]"));

        btncompanyClear1 = new JButton("New");
        btncompanyClear1.setMnemonic('N');
        btncompanyClear1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    //btncompanyClear1ActionPerformed(null);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    btnCompanyCreate1.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtCompanySignature1.requestFocus();
                }
            }
        });
        btncompanyClear1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtNameOfGod1.setText("");
                txtCompanyName1.setText("");
                txtCompanyAddress1.setText("");
                txtCompanyAlias1.setText("");
                txtCompanyContact1.setText("");
                txtCompanyITN1.setText("");
                txtCompanyMail1.setText("");
                txtCompanySTN1.setText("");
                txtCompanyDeclaration1.setText("");
                txtCompanyTagLine1.setText("");
                txtCompanyCSTNo1.setText("");
                jtextFieldLBTNo.setText("");
                txtCompanyTermsConditions1.setText("");
                txtCompanySignature1.setText("");
                txtCompanyIdNo.setText("");
                lblcompany_ID1.setText("ID");

//                source = "";
                btnCompanyCreate1.setEnabled(true);
                btnUpdate1.setEnabled(false);
                btnDelete1.setEnabled(false);
                btnupdateLogo.setEnabled(false);

                LogoPanelView1.setIcon(null);
                LogoPanelView1.revalidate();

                txtCompanyName1.requestFocus();
            }
        });
        panel_19.add(btncompanyClear1, "cell 1 1");

        btnUpdate1 = new JButton("Update");
        btnUpdate1.setMnemonic('U');
        btnUpdate1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ledger_name = txtCompanyName1.getText().trim();
                Pattern p = Pattern.compile("[a-zA-Z0-9]");
                Matcher m = p.matcher(ledger_name);

                Pattern p1 = Pattern.compile("[^a-z0-9 ]",
                        Pattern.CASE_INSENSITIVE);
                Matcher m1 = p1.matcher(ledger_name);
                boolean flag = m1.find();

                String email = txtCompanyMail1.getText().toString().trim();
                String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                Pattern pattern = Pattern.compile(expression,
                        Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(email);

                if (lblcompany_ID1.getText().toString().trim().equals("ID")) {
                    JOptionPane.showMessageDialog(
                            AdSuMuDiSettingsEclipseGUI.this,
                            "Selete company to update");
                    txtCompanyName1.requestFocus();
                } else if (txtCompanyName1.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(
                            AdSuMuDiSettingsEclipseGUI.this,
                            "Enter Name Of Company");
                    txtCompanyName1.requestFocus();
                } else if (!m.find()) {
                    JOptionPane.showMessageDialog(
                            AdSuMuDiSettingsEclipseGUI.this,
                            "Enter Valid Name Of Company");
                    txtCompanyName1.requestFocus();
                } else if (flag) {
                    JOptionPane
                            .showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                            "Enter Only Alphabet and Number in  Name Of Company");
                    txtCompanyName1.requestFocus();
                } else if (!matcher.matches()
                        && !txtCompanyMail1.getText().toString().trim()
                        .equals("")) {
                    JOptionPane.showMessageDialog(
                            AdSuMuDiSettingsEclipseGUI.this,
                            Label.ENTER_VALUE_FOR_EMAIL_ID);
                    txtCompanyMail1.requestFocus();
                } else {
                    txtCompanyName1.transferFocus();
                    int current = 0;

                    try {
                        // create new company
                        // get the first Word from the company_name to add in
                        // database with id
                        String[] result = txtCompanyName1.getText().toString()
                                .trim().split(" ", 2);
                        String first_Word = "";
                        String rest_Words = "";
                        // use st that ArrayIndex of bound problem shuld not
                        // occcur if only 1 word is present
                        for (int i = 0; i < result.length; i++) {
                            if (i == 0) {
                                first_Word = result[0];
                            } else if (i == 1) {
                                rest_Words = result[1];
                            }
                        }
                        System.out.println("First: " + first_Word);
                        System.out.println("Rest: " + rest_Words);

                        List<CompanySettingsDTO> companySettings_InformationDTOList = new ArrayList<CompanySettingsDTO>();

                        CompanySettingsDTO companySettingsDTO = new CompanySettingsDTO();
                        companySettingsDTO.setNameOfGod(txtNameOfGod1.getText()
                                .toString().trim());
                        companySettingsDTO.setCompany_id(Long
                                .parseLong(lblcompany_ID1.getText().toString()
                                .trim()));
                        companySettingsDTO.setCompany_name(txtCompanyName1
                                .getText().toString().trim());
                        companySettingsDTO.setCompany_DatabaseName(company_Cliked_Database_Name.trim());
                        System.out
                                .println("Comapny _NMAE  ------------------------------ "
                                + txtCompanyName1.getText().toString()
                                .trim());
                        companySettingsDTO.setcompany_Alias(txtCompanyAlias1
                                .getText().toString().trim());
                        companySettingsDTO
                                .setcompany_Address(txtCompanyAddress1
                                .getText().toString().trim());
                        companySettingsDTO
                                .setcompany_ContactNo(txtCompanyContact1
                                .getText().toString().trim());
                        companySettingsDTO.setcompany_Created_by("");

                        companySettingsDTO.setcompany_Created_date("");
                        companySettingsDTO
                                .setcompany_Declaration(txtCompanyDeclaration1
                                .getText().toString().trim());
                        
                        companySettingsDTO.setCompany_Identification_No(txtCompanyIdNo.getText().toString().trim());

                        System.out.println("Company Declaraition ------- "
                                + txtCompanyDeclaration1.getText().toString()
                                .trim());

                        companySettingsDTO.setcompany_EmailId(txtCompanyMail1
                                .getText().toString().trim());
                        companySettingsDTO.setcompany_Field1("");
                        companySettingsDTO.setcompany_Field2("");
                        companySettingsDTO
                                .setcompany_IncomeTaxNo(txtCompanyITN1
                                .getText().toString().trim());
                        companySettingsDTO.setcompany_LBTNo("");
                        companySettingsDTO.setcompany_Modified_by("");
                        //todays Date
                        Date modify_Date = new Date();
                        System.out.println("Modified ------ " + gen.dto.Constants.simpleDateFormatDatabaseWithDash.format(modify_Date));
                        companySettingsDTO.setcompany_Modified_date(gen.dto.Constants.simpleDateFormatDatabaseWithDash.format(modify_Date));

                        // Apllicable from DAte

                        companySettingsDTO.setCompany_ApplicableFrom_date(gen.dto.Constants.simpleDateFormatDatabaseWithDash.format(JdateChooser_CompanyApplicableFrom.getDate()));

                        companySettingsDTO.setcompany_Note("");
                        System.out.println("------- "
                                + txtCompanyCSTNo1.getText().toString().trim());
                        companySettingsDTO
                                .setcompany_SaleTaxNo(txtCompanyCSTNo1
                                .getText().toString().trim());
                        companySettingsDTO.setcompany_LBTNo(jtextFieldLBTNo
                                .getText().toString().trim());
                        companySettingsDTO
                                .setcompany_SignAuthority(txtCompanySignature1
                                .getText().toString().trim());
                        System.out.println("------- 111"
                                + txtCompanySTN1.getText().toString().trim());
                        companySettingsDTO.setcompany_VatTinNo(txtCompanySTN1
                                .getText().toString().trim());
                        companySettingsDTO
                                .setcompany_TermCnditions(txtCompanyTermsConditions1
                                .getText().toString().trim());
                        companySettingsDTO
                                .setcompany_TagLine(txtCompanyTagLine1
                                .getText().toString().trim());
                        companySettings_InformationDTOList
                                .add(companySettingsDTO);

                        gen.other.DatabaseSpiliting.DatabaseSplitingDAO
                                .update_Company_Information(
                                companySettings_InformationDTOList,
                                company_Cliked_Name, true);



                        // if (control == 1) {
                        gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";

                        BufferedImage mImage;
                        final String dir = System.getProperty("user.dir");
                        File imgfile = null;
                        FileInputStream fin = null;
                        // if (source != null) {
                        // imgfile = new File(source);
                        // fin = new FileInputStream(imgfile);
                        // }


                        if (source != null && !source.isEmpty()) {
                            imgfile = new File(source);
                            fin = new FileInputStream(imgfile);

                            // File imgfile = new File(source);

                            // FileInputStream fin = new
                            // FileInputStream(imgfile);
                            Connection conn = DatabaseConnection1
                                    .GetConnection();

                            PreparedStatement prmt = conn
                                    .prepareStatement("delete from tblcompaniesimage where company_id = ? and company_name = ?");
                            prmt.setString(1, lblcompany_ID1.getText()
                                    .toString().trim());
                            prmt.setString(2, company_Cliked_Name);
                            prmt.executeUpdate();

                            PreparedStatement pre = conn
                                    .prepareStatement("insert into tblcompaniesimage(company_name,company_id,company_image) values(?,?,?)");
                            pre.setString(1, txtCompanyName1.getText()
                                    .toString().trim());
                            pre.setString(2, lblcompany_ID1.getText()
                                    .toString().trim());
                            if (source != null && !source.isEmpty()) {
                                pre.setBinaryStream(3, fin,
                                        (int) imgfile.length());
                            } else {
                                pre.setString(3, "NULL");
                            }
                            pre.executeUpdate();
                            pre.close();
                            conn.close();
                        } else {
                            Connection conn = DatabaseConnection1
                                    .GetConnection();
                            PreparedStatement pre = conn
                                    .prepareStatement("update tblcompaniesimage set company_name = ? ,company_id =? where company_id =? ");
                            System.out.println("Update Company ID --------- " + lblcompany_ID1.getText().toString().trim());
                            System.out.println("Update Company Nmae --------- " + txtCompanyName1.getText().toString().trim());

                            pre.setString(1, txtCompanyName1.getText()
                                    .toString().trim());
                            pre.setString(2, lblcompany_ID1.getText()
                                    .toString().trim());
                            pre.setString(3, lblcompany_ID1.getText()
                                    .toString().trim());

                            pre.executeUpdate();

                            pre.close();
                            conn.close();

                        }
                        gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";

//			insert_Imagein_Company_Database(company_Cliked_Database_Name);

                        JOptionPane.showMessageDialog(
                                AdSuMuDiSettingsEclipseGUI.this,
                                "Company Information Updated Successfully...");

                        gen.dto.Constants.CURRENT_COMPANY_NAME = companySettingsDTO
                                .getCompany_name();
                        gen.dto.Constants.CURRENT_COMPANY_ID = companySettingsDTO
                                .getCompany_id().toString();
                        gen.dto.Constants.CURRENT_COMPANY_CREATED_DATE = companySettingsDTO.getCompany_ApplicableFrom_date();
                        gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = companySettingsDTO
                                .getCompany_DatabaseName();


                        // Update Stockitem and Ledger available date
                        String current_database_temp = "";
                        current_database_temp = companySettingsDTO.getCompany_DatabaseName();
                        List<gen.other.CompanySettings.CompanySettingsDTO> getComapany_List = CompanySettingsDAO
                                .getComapany_List(gen.dto.Constants.CURRENT_COMPANY_NAME, gen.dto.Constants.CURRENT_COMPANY_ID);
                        for (CompanySettingsDTO companySettingsDTO1 : getComapany_List) {
                            System.out.println("-----------" + companySettingsDTO1.getCompany_DatabaseName());
                            gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = companySettingsDTO1.getCompany_DatabaseName();
                            gen.account.ledger.LedgerDAO.updateLedgerValidate_Date(null);
                            gen.account.stockitem.StockItemDAO.updateStockItemValidate_Date(null);
                        }

                        gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = companySettingsDTO.getCompany_DatabaseName();
                        System.out.println(" VVVVVVVVV              " + current_database_temp);


                        btncompanyClear1ActionPerformed(e);
                        initilizeGUIComponents_Company_Delete();
                        source = "";
                        btnCompanyCreate1.setEnabled(true);
                        btnUpdate1.setEnabled(false);
                        btnDelete1.setEnabled(false);
                        btnupdateLogo.setEnabled(false);

                        System.out
                                .println("4444444444444444444444444444444444444444444444444444444444444444444");
                        // Remove icon when button is clicked.
                        LogoPanelView1.setIcon(null);
                        // **IMPORTANT** to call revalidate() to cause JLabel to
                        // resize and be repaint
                        LogoPanelView1.revalidate();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.out.println("Exception On Save button Click : "
                                + ex);
                    }

                }
                // txtCompanyAddress1.setEditable(false);
                // txtCompanyAlias1.setEditable(false);
                // txtCompanyContact1.setEditable(false);
                // txtCompanyDeclaration1.setEditable(false);
                // txtCompanyMail1.setEditable(false);
                // txtCompanyITN1.setEditable(false);
                // txtCompanyName1.setEditable(false);
                // txtCompanySTN1.setEditable(false);
                // txtCompanyTagLine1.setEditable(false);
                // txtCompanyTermsConditions1.setEditable(false);
                // txtNameOfGod1.setEditable(false);
                // txtCompanySignature1.setEditable(false);
                // txtCompanyCSTNo1.setEditable(false);
            }
        });
        panel_19.add(btnUpdate1, "flowx,cell 3 1");

        btnDelete1 = new JButton("Delete");
        btnDelete1.setMnemonic('T');
        btnDelete1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.out.println("Current Company Nmae -------------"
                        + gen.dto.Constants.CURRENT_COMPANY_NAME);
                System.out.println("Current Company ID -------------"
                        + gen.dto.Constants.CURRENT_COMPANY_ID);
                System.out.println("Selected Company Nmae -------------"
                        + lblcompany_ID1.getText().toString().trim());
                System.out.println("Selected Company ID -------------"
                        + company_Cliked_Name);
                System.out.println("Condition For Nmae -------------"
                        + gen.dto.Constants.CURRENT_COMPANY_NAME
                        .equals(company_Cliked_Name));
                System.out.println("Condition For  ID -------------"
                        + gen.dto.Constants.CURRENT_COMPANY_ID
                        .equals(lblcompany_ID1.getText().toString()
                        .trim()));

                if (gen.dto.Constants.CURRENT_COMPANY_NAME
                        .equals(company_Cliked_Name)
                        && gen.dto.Constants.CURRENT_COMPANY_ID
                        .equals(lblcompany_ID1.getText().toString()
                        .trim())) {

                    JOptionPane.showMessageDialog(
                            AdSuMuDiSettingsEclipseGUI.this,
                            "Current Company can not be Delete");
                    btncompanyClear1ActionPerformed(e);
                    initilizeGUIComponents_Company_Delete();

                } else {
                    if (!lblcompany_ID1.getText().toString().trim()
                            .equals("ID")) {
                        try {
                            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
                            Connection conn = DatabaseConnection1
                                    .GetConnection();
                            conn.setAutoCommit(false);
                            PreparedStatement pre = conn
                                    .prepareStatement("delete from  tblcompaniesimage where company_name = ? and company_id = ?");
                            pre.setString(1, company_Cliked_Name);
                            pre.setString(2, lblcompany_ID1.getText()
                                    .toString().trim());
                            pre.executeUpdate();

                            PreparedStatement prete = conn
                                    .prepareStatement("delete from   tblcurrentcompany where company_name = ? and company_id = ? ");
                            prete.setString(1, company_Cliked_Name);
                            prete.setString(2, lblcompany_ID1.getText()
                                    .toString().trim());
                            prete.executeUpdate();

                            prete = conn
                                    .prepareStatement("delete from   tblusertocompany where company_name = ? and company_id = ? ");
                            prete.setString(1, company_Cliked_Name);
                            prete.setString(2, lblcompany_ID1.getText()
                                    .toString().trim());
                            prete.executeUpdate();

                            PreparedStatement pret = conn
                                    .prepareStatement("delete from   tblcompanyinfo where company_name = ? and company_id = ? ");
                            pret.setString(1, company_Cliked_Name);
                            pret.setString(2, lblcompany_ID1.getText()
                                    .toString().trim());
                            pret.executeUpdate();

                            JOptionPane.showMessageDialog(
                                    AdSuMuDiSettingsEclipseGUI.this,
                                    "Company Deleted Successfully...");
                            conn.commit();
                            conn.close();
                            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
                            source = "";
                            btncompanyClear1ActionPerformed(e);

                            initilizeGUIComponents_Company_Delete();

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            Logger.getLogger(
                                    AdSuMuDiSettingsEclipseGUI.class.getName())
                                    .log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                                AdSuMuDiSettingsEclipseGUI.this,
                                "First Select Company to Delete");
                    }
                }
            }
        });
        panel_19.add(btnDelete1, "cell 2 1");

        buttonExit_Company_Delete = new JButton("Exit");
        buttonExit_Company_Delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // TODO add your handling code here:
                    // this.dispose();
                    AdSuMuDiSettingsEclipseGUI.this.setClosed(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        });
        panel_19.add(buttonExit_Company_Delete, "cell 0 1");

        btnCompanyCreate1 = new JButton("Submit");
        btnCompanyCreate1.setMnemonic('S');
//        btnCompanyCreate1.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                    // btnCompanyCreate1ActionPerformed();
//
//                    String ledger_name = txtCompanyName1.getText().trim();
//                    Pattern p1 = Pattern.compile("[^a-z0-9 ]",
//                            Pattern.CASE_INSENSITIVE);
//                    Matcher m1 = p1.matcher(ledger_name);
//                    boolean b = m1.find();
//
//                    Pattern p = Pattern.compile("[a-zA-Z0-9]");
//                    Matcher m = p.matcher(ledger_name);
//
//                    String email = txtCompanyMail1.getText().toString().trim();
//                    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
//                    Pattern pattern = Pattern.compile(expression,
//                            Pattern.CASE_INSENSITIVE);
//                    Matcher matcher = pattern.matcher(email);
//
//                    if (txtCompanyName1.getText().trim().equals("")) {
//                        JOptionPane.showMessageDialog(
//                                AdSuMuDiSettingsEclipseGUI.this,
//                                "Enter Name Of Company");
//                        txtCompanyName1.requestFocus();
//                    } else if (!m.find()) {
//                        JOptionPane.showMessageDialog(
//                                AdSuMuDiSettingsEclipseGUI.this,
//                                "Enter Valid Name Of Company");
//                        txtCompanyName1.requestFocus();
//                    } else if (b) {
//                        JOptionPane
//                                .showMessageDialog(
//                                AdSuMuDiSettingsEclipseGUI.this,
//                                "Enter Only Alphabet and Number in  Name Of Company");
//                        txtCompanyName1.requestFocus();
//                    } else if (!lblcompany_ID1.getText().toString().trim()
//                            .equals("ID")) {
//                        JOptionPane.showMessageDialog(
//                                AdSuMuDiSettingsEclipseGUI.this,
//                                "Enter Name Of Company");
//                        txtCompanyName1.requestFocus();
//                    } else if (!matcher.matches()
//                            && !txtCompanyMail1.getText().toString().trim()
//                            .equals("")) {
//                        JOptionPane.showMessageDialog(
//                                AdSuMuDiSettingsEclipseGUI.this,
//                                Label.ENTER_VALUE_FOR_EMAIL_ID);
//                        txtCompanyMail1.requestFocus();
//                    } else {
//                        txtCompanyName1.transferFocus();
//                        int current = 0;
//                        Connection conn = null;
//                        try {
//                            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
//                            conn = DatabaseConnection1.GetConnection();
//                            conn.setAutoCommit(false);
//                            Statement st = conn.createStatement();
//
////                            // create new company
////
////                            // get the first Word from the company_name to add
////                            // in
////                            // database with id
////                            String[] result = txtCompanyName1.getText()
////                                    .toString().trim().split(" ", 2);
////                            String first_Word = "";
////                            String rest_Words = "";
////                            // use st that ArrayIndex of bound problem shuld not
////                            // occcur if only 1 word is present
////                            for (int i = 0; i < result.length; i++) {
////                                if (i == 0) {
////                                    first_Word = result[0];
////                                } else if (i == 1) {
////                                    rest_Words = result[1];
////                                }
////                            }
////                            System.out.println("First: " + first_Word);
////                            System.out.println("Rest: " + rest_Words);
////
////                            // find id to ADD in Database Name of New Created
////                            // Company
////                            String add_ID_To_NewCompany = gen.other.CompanySettings.CompanySettingsDAO
////                                    .get_Max_Company_ID();
////
////                            System.out.println("Max Compnay ID 11111 ---------------------"+add_ID_To_NewCompany);
////                            
////                            // pass database Name for Newly creted company
////                            database_Name_For_Newly_Company = first_Word
////                                    + add_ID_To_NewCompany;
////
////                            List<Company_InformationDTO> new_databaseCreateList = new ArrayList<Company_InformationDTO>();
////                            Company_InformationDTO company_InformationDTO = new Company_InformationDTO();
////                            // add New company Database Name in
////                            // company_InformationDTO
////                            company_InformationDTO
////                                    .setCompany_Database_Name(database_Name_For_Newly_Company);
////                            new_databaseCreateList.add(company_InformationDTO);
////                            // Create New Database for Newly created Company
////                            if (gen.dto.Constants.DATABASE_SERVER
////                                    .equals("com.mysql.jdbc.Driver")) {
////                                gen.other.DatabaseSpiliting.DatabaseSplitingDAO
////                                        .createNewDatabase(new_databaseCreateList);
////                            }
//
//                            List<CompanySettingsDTO> companySettings_InformationDTOList = new ArrayList<CompanySettingsDTO>();
//
//                            CompanySettingsDTO companySettingsDTO = new CompanySettingsDTO();
//                            // companySettingsDTO.setCompany_id(Long.parseLong(lblcompany_ID1.getText().toString().trim()));
//                            companySettingsDTO.setCompany_name(txtCompanyName1
//                                    .getText().toString().trim());
//                            System.out
//                                    .println("Comapny _NMAE  ------------------------------ "
//                                    + txtCompanyName1.getText()
//                                    .toString().trim());
//                            System.out
//                                .println("DATABASE _NMAE  ------------------------------ A"+database_Name_For_Newly_Company);
//                            companySettingsDTO
//                                    .setCompany_DatabaseName(database_Name_For_Newly_Company);
//                            companySettingsDTO
//                                    .setcompany_Alias(txtCompanyAlias1
//                                    .getText().toString().trim());
//                            companySettingsDTO
//                                    .setcompany_Address(txtCompanyAddress1
//                                    .getText().toString().trim());
//                            companySettingsDTO
//                                    .setcompany_ContactNo(txtCompanyContact1
//                                    .getText().toString().trim());
//                            companySettingsDTO.setcompany_Created_by("");
//                            System.out.println("Created Date --------- " + gen.dto.Constants.simpleDateFormatDatabaseWithDash.format(JdateChooser_CompanyApplicableFrom.getDate()));
//
//                            companySettingsDTO.setCompany_ApplicableFrom_date(gen.dto.Constants.simpleDateFormatDatabaseWithDash.format(JdateChooser_CompanyApplicableFrom.getDate()));
//
//                            companySettingsDTO.setcompany_Created_date(gen.dto.Constants.simpleDateFormatDatabaseWithDash.format(JdateChooser_CompanyApplicableFrom.getDate()));
//                            companySettingsDTO
//                                    .setcompany_Declaration(txtCompanyDeclaration1
//                                    .getText().toString().trim());
//                            companySettingsDTO
//                                    .setcompany_EmailId(txtCompanyMail1
//                                    .getText().toString().trim());
//                            companySettingsDTO.setcompany_Field1("");
//                            companySettingsDTO.setcompany_Field2("");
//                            companySettingsDTO
//                                    .setcompany_IncomeTaxNo(txtCompanyITN1
//                                    .getText().toString().trim());
//                            companySettingsDTO.setcompany_LBTNo("");
//                            companySettingsDTO.setcompany_Modified_by("");
//                            companySettingsDTO.setcompany_Modified_date("");
//                            companySettingsDTO.setcompany_Note("");
//                            companySettingsDTO
//                                    .setcompany_SaleTaxNo(txtCompanyCSTNo1
//                                    .getText().toString().trim());
//                            companySettingsDTO
//                                    .setcompany_SignAuthority(txtCompanySignature1
//                                    .getText().toString().trim());
//                            companySettingsDTO
//                                    .setcompany_VatTinNo(txtCompanySTN1
//                                    .getText().toString().trim());
//                            companySettingsDTO
//                                    .setcompany_TermCnditions(txtCompanyTermsConditions1
//                                    .getText().toString().trim());
//                            companySettingsDTO
//                                    .setcompany_TagLine(txtCompanyTagLine1
//                                    .getText().toString().trim());
//                            companySettings_InformationDTOList
//                                    .add(companySettingsDTO);
//                            // pass empty company_List so that it is use in
//                            // calling
//                            // function
//                            List<String> company_List = new ArrayList<String>();
//                            gen.other.DatabaseSpiliting.DatabaseSplitingDAO
//                                    .insert_NewlyCreated_Company(company_List,
//                                    database_Name_For_Newly_Company,
//                                    "", "",
//                                    companySettings_InformationDTOList);
//
//                            // get the first Word from the company_name to add
//                            // in
//                            // database with id
//                            String[] result = txtCompanyName1.getText()
//                                    .toString().trim().split(" ", 2);
//                            String first_Word = "";
//                            String rest_Words = "";
//                            // use st that ArrayIndex of bound problem shuld not
//                            // occcur if only 1 word is present
//                            for (int i = 0; i < result.length; i++) {
//                                if (i == 0) {
//                                    first_Word = result[0];
//                                } else if (i == 1) {
//                                    rest_Words = result[1];
//                                }
//                            }
//                            System.out.println("First: " + first_Word);
//                            System.out.println("Rest: " + rest_Words);
//
//                            // find id to ADD in Database Name of New Created
//                            // Company
//                            String add_ID_To_NewCompany = gen.other.CompanySettings.CompanySettingsDAO
//                                    .get_Max_Company_ID();
//
//                            System.out.println("Max Compnay ID ---------------------" + add_ID_To_NewCompany);
//
//                            // pass database Name for Newly creted company
//                            database_Name_For_Newly_Company = first_Word
//                                    + add_ID_To_NewCompany;
//
//                            List<Company_InformationDTO> new_databaseCreateList = new ArrayList<Company_InformationDTO>();
//                            Company_InformationDTO company_InformationDTO = new Company_InformationDTO();
//                            // add New company Database Name in
//                            // company_InformationDTO
//                            company_InformationDTO
//                                    .setCompany_Database_Name(database_Name_For_Newly_Company);
//                            new_databaseCreateList.add(company_InformationDTO);
//                            // Create New Database for Newly created Company
//                            if (gen.dto.Constants.DATABASE_SERVER
//                                    .equals("com.mysql.jdbc.Driver")) {
//                                gen.other.DatabaseSpiliting.DatabaseSplitingDAO
//                                        .createNewDatabase(new_databaseCreateList);
//                            }
//
//                            System.out.println("-------------- A"+companySettings_InformationDTOList.size());
//                            for (CompanySettingsDTO companySettingsDTOsetDatabaseName : companySettings_InformationDTOList) {
//                                System.out
//                                .println("DATABASE _NMAE For Loop  ------------------------------ A"+database_Name_For_Newly_Company);
//                                companySettingsDTOsetDatabaseName.setCompany_DatabaseName(database_Name_For_Newly_Company);
//                            }
//
//                            gen.other.DatabaseSpiliting.DatabaseSplitingDAO
//                                    .update_Company_Information(
//                                    companySettings_InformationDTOList,
//                                    txtCompanyName1
//                                    .getText().toString().trim(),false);
//
//
//                            // ////////////////////////////////////////
//                            BufferedImage mImage;
//                            final String dir = System.getProperty("user.dir");
//                            File imgfile = null;
//                            FileInputStream fin = null;
//
//                            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
//                            conn = DatabaseConnection1.GetConnection();
//                            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
//                            conn.setAutoCommit(false);
//                            if (source != null && !source.isEmpty()) {
//                                imgfile = new File(source);
//                                fin = new FileInputStream(imgfile);
//                                mImage = ImageIO.read(imgfile);
//                                System.out.println("						0" + source);
//                                PreparedStatement pre = conn
//                                        .prepareStatement("insert into tblcompaniesimage(company_name,company_id,company_image) values(?,?,?)");
//                                pre.setString(1, txtCompanyName1.getText()
//                                        .toString().trim());
//                                pre.setString(2, add_ID_To_NewCompany);
//                                if (source != null && !source.isEmpty()) {
//                                    System.out
//                                            .println("oooooooooooooooooooooooooooooooooooooooo");
//                                    pre.setBinaryStream(3, fin,
//                                            (int) imgfile.length());
//                                } else {
//                                    System.out
//                                            .println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
//                                    pre.setString(3, "NULL");
//                                }
//                                pre.executeUpdate();
//                            }
//
//                            conn.commit();
//
//                            // backupDB_Database_Delete("aj1", "root", "adm",
//                            // "D:/exe.sql");
//                            // Boolean flag = restoreDB_Database_Delete("w74",
//                            // "root", "adm", "D:/exe.sql");
//
//                            new Thread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    JInternalFrame newsas = new JInternalFrame();
//                                    startEmptyWaitTimerForFrame(newsas,
//                                            database_Name_For_Newly_Company);
//                                }
//                            }).start();
//
//                            // delete the file of All_Database_information.Enc
//                            Path target5 = Paths.get("D:/exe.sql");
//                            if (Files.exists(target5)) {
//                                Files.deleteIfExists(target5);
//                            }
//
//                            // conn.commit();
//                            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
//
//                            // btncompanyClear1ActionPerformed(e);
//                            txtNameOfGod1.setText("");
//                            txtCompanyName1.setText("");
//                            txtCompanyAddress1.setText("");
//                            txtCompanyAlias1.setText("");
//                            txtCompanyContact1.setText("");
//                            txtCompanyITN1.setText("");
//                            txtCompanyMail1.setText("");
//                            txtCompanySTN1.setText("");
//                            txtCompanyDeclaration1.setText("");
//                            txtCompanyTagLine1.setText("");
//                            txtCompanyCSTNo1.setText("");
//                            txtCompanyTermsConditions1.setText("");
//                            txtCompanySignature1.setText("");
//                            lblcompany_ID1.setText("ID");
//
//                            source = "";
//                            btnCompanyCreate1.setEnabled(true);
//                            btnUpdate1.setEnabled(false);
//                            btnDelete1.setEnabled(false);
//                            btnupdateLogo.setEnabled(false);
//
//                            LogoPanelView1.setIcon(null);
//                            LogoPanelView1.revalidate();
//
//                            txtCompanyName1.requestFocus();
//                            initilizeGUIComponents_Company_Delete();
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                            System.out.println("Exception : " + e);
//                        }
//
//                    }
//                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//                    btncompanyClear1.requestFocus();
//                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
//                    txtCompanySignature1.requestFocus();
//                }
//            }
//        });
        btnCompanyCreate1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ledger_name = txtCompanyName1.getText().trim();
                Pattern p1 = Pattern.compile("[^a-z0-9 ]",
                        Pattern.CASE_INSENSITIVE);
                Matcher m1 = p1.matcher(ledger_name);
                boolean b = m1.find();

                Pattern p = Pattern.compile("[a-zA-Z0-9]");
                Matcher m = p.matcher(ledger_name);

                String email = txtCompanyMail1.getText().toString().trim();
                String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                Pattern pattern = Pattern.compile(expression,
                        Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(email);

                if (txtCompanyName1.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(
                            AdSuMuDiSettingsEclipseGUI.this,
                            "Enter Name Of Company");
                    txtCompanyName1.requestFocus();
                } else if (!m.find()) {
                    JOptionPane.showMessageDialog(
                            AdSuMuDiSettingsEclipseGUI.this,
                            "Enter Valid Name Of Company");
                    txtCompanyName1.requestFocus();
                } else if (b) {
                    JOptionPane
                            .showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                            "Enter Only Alphabet and Number in  Name Of Company");
                    txtCompanyName1.requestFocus();
                } else if (!lblcompany_ID1.getText().toString().trim()
                        .equals("ID")) {
                    JOptionPane.showMessageDialog(
                            AdSuMuDiSettingsEclipseGUI.this,
                            "Enter Name Of Company");
                    txtCompanyName1.requestFocus();
                } else if (!matcher.matches()
                        && !txtCompanyMail1.getText().toString().trim()
                        .equals("")) {
                    JOptionPane.showMessageDialog(
                            AdSuMuDiSettingsEclipseGUI.this,
                            Label.ENTER_VALUE_FOR_EMAIL_ID);
                    txtCompanyMail1.requestFocus();
                } else {
                    txtCompanyName1.transferFocus();
                    int current = 0;
                    Connection conn = null;
                    try {
                        gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
                        conn = DatabaseConnection1.GetConnection();
                        conn.setAutoCommit(false);
                        Statement st = conn.createStatement();

//                        // create new company
//
//                        // get the first Word from the company_name to add in
//                        // database with id
//                        String[] result = txtCompanyName1.getText().toString()
//                                .trim().split(" ", 2);
//                        String first_Word = "";
//                        String rest_Words = "";
//                        // use st that ArrayIndex of bound problem shuld not
//                        // occcur if only 1 word is present
//                        for (int i = 0; i < result.length; i++) {
//                            if (i == 0) {
//                                first_Word = result[0];
//                            } else if (i == 1) {
//                                rest_Words = result[1];
//                            }
//                        }
//                        System.out.println("First: " + first_Word);
//                        System.out.println("Rest: " + rest_Words);
//
//                        // find id to ADD in Database Name of New Created
//                        // Company
//                        String add_ID_To_NewCompany = gen.other.CompanySettings.CompanySettingsDAO
//                                .get_Max_Company_ID();
//                        
//                        System.out.println("Max Compnay ID ---------------------"+add_ID_To_NewCompany);
//                        
//                        // pass database Name for Newly creted company
//                        database_Name_For_Newly_Company = first_Word
//                                + add_ID_To_NewCompany;
//
//                        List<Company_InformationDTO> new_databaseCreateList = new ArrayList<Company_InformationDTO>();
//                        Company_InformationDTO company_InformationDTO = new Company_InformationDTO();
//                        // add New company Database Name in
//                        // company_InformationDTO
//                        company_InformationDTO
//                                .setCompany_Database_Name(database_Name_For_Newly_Company);
//                        new_databaseCreateList.add(company_InformationDTO);
//                        // Create New Database for Newly created Company
//                        if (gen.dto.Constants.DATABASE_SERVER
//                                .equals("com.mysql.jdbc.Driver")) {
//                            gen.other.DatabaseSpiliting.DatabaseSplitingDAO
//                                    .createNewDatabase(new_databaseCreateList);
//                        }

                        List<CompanySettingsDTO> companySettings_InformationDTOList = new ArrayList<CompanySettingsDTO>();

                        CompanySettingsDTO companySettingsDTO = new CompanySettingsDTO();
                        // companySettingsDTO.setCompany_id(Long.parseLong(lblcompany_ID1.getText().toString().trim()));
                        companySettingsDTO.setNameOfGod(txtNameOfGod1.getText()
                                .toString().trim());
                        companySettingsDTO.setCompany_name(txtCompanyName1
                                .getText().toString().trim());
                        System.out
                                .println("Comapny _NMAE  ------------------------------ "
                                + txtCompanyName1.getText().toString()
                                .trim());
                        System.out
                                .println("DATABASE _NMAE  ------------------------------ 111" + database_Name_For_Newly_Company);
                        companySettingsDTO
                                .setCompany_DatabaseName(database_Name_For_Newly_Company);
                        companySettingsDTO.setcompany_Alias(txtCompanyAlias1
                                .getText().toString().trim());
                        companySettingsDTO
                                .setcompany_Address(txtCompanyAddress1
                                .getText().toString().trim());
                        companySettingsDTO
                                .setcompany_ContactNo(txtCompanyContact1
                                .getText().toString().trim());
                        companySettingsDTO.setcompany_Created_by("");
                        System.out.println("Created Date --------- " + gen.dto.Constants.simpleDateFormatDatabaseWithDash.format(JdateChooser_CompanyApplicableFrom.getDate()));

                        companySettingsDTO.setCompany_ApplicableFrom_date(gen.dto.Constants.simpleDateFormatDatabaseWithDash.format(JdateChooser_CompanyApplicableFrom.getDate()));
                        companySettingsDTO.setcompany_Created_date(gen.dto.Constants.simpleDateFormatDatabaseWithDash.format(JdateChooser_CompanyApplicableFrom.getDate()));
                        companySettingsDTO
                                .setcompany_Declaration(txtCompanyDeclaration1
                                .getText().toString().trim());
                        companySettingsDTO.setcompany_EmailId(txtCompanyMail1
                                .getText().toString().trim());
                        companySettingsDTO.setcompany_Field1("");
                        companySettingsDTO.setcompany_Field2("");
                        companySettingsDTO
                                .setcompany_IncomeTaxNo(txtCompanyITN1
                                .getText().toString().trim());
                        companySettingsDTO.setcompany_LBTNo("");
                        companySettingsDTO.setcompany_Modified_by("");
                        companySettingsDTO.setcompany_Modified_date("");
                        companySettingsDTO.setcompany_Note("");
                        companySettingsDTO
                                .setcompany_SaleTaxNo(txtCompanyCSTNo1
                                .getText().toString().trim());
                        companySettingsDTO.setcompany_LBTNo(jtextFieldLBTNo
                                .getText().toString().trim());
                        companySettingsDTO
                                .setcompany_SignAuthority(txtCompanySignature1
                                .getText().toString().trim());
                        companySettingsDTO.setcompany_VatTinNo(txtCompanySTN1
                                .getText().toString().trim());
                        companySettingsDTO
                                .setcompany_TermCnditions(txtCompanyTermsConditions1
                                .getText().toString().trim());
                        companySettingsDTO
                                .setcompany_TagLine(txtCompanyTagLine1
                                .getText().toString().trim());
                        companySettings_InformationDTOList
                                .add(companySettingsDTO);

                        // pass empty company_List so that it is use in calling
                        // function
                        List<String> company_List = new ArrayList<String>();
                        gen.other.DatabaseSpiliting.DatabaseSplitingDAO
                                .insert_NewlyCreated_Company(company_List,
                                database_Name_For_Newly_Company, "",
                                "", companySettings_InformationDTOList);


                        // get the first Word from the company_name to add in
                        // database with id
                        String[] result = txtCompanyName1.getText().toString()
                                .trim().split(" ", 2);
                        String first_Word = "";
                        String rest_Words = "";
                        // use st that ArrayIndex of bound problem shuld not
                        // occcur if only 1 word is present
                        for (int i = 0; i < result.length; i++) {
                            if (i == 0) {
                                first_Word = result[0];
                            } else if (i == 1) {
                                rest_Words = result[1];
                            }
                        }
                        System.out.println("First: " + first_Word);
                        System.out.println("Rest: " + rest_Words);

                        // find id to ADD in Database Name of New Created
                        // Company
                        String add_ID_To_NewCompany = gen.other.CompanySettings.CompanySettingsDAO
                                .get_Max_Company_ID();

                        System.out.println("Max Compnay ID 1111111111 ---------------------" + add_ID_To_NewCompany);

                        // pass database Name for Newly creted company
                        database_Name_For_Newly_Company = first_Word
                                + add_ID_To_NewCompany;

                        List<Company_InformationDTO> new_databaseCreateList = new ArrayList<Company_InformationDTO>();
                        Company_InformationDTO company_InformationDTO = new Company_InformationDTO();
                        // add New company Database Name in
                        // company_InformationDTO
                        company_InformationDTO
                                .setCompany_Database_Name(database_Name_For_Newly_Company);
                        new_databaseCreateList.add(company_InformationDTO);
                        // Create New Database for Newly created Company
                        if (gen.dto.Constants.DATABASE_SERVER
                                .equals("com.mysql.jdbc.Driver")) {
                            gen.other.DatabaseSpiliting.DatabaseSplitingDAO
                                    .createNewDatabase(new_databaseCreateList);
                        }

                        System.out.println("-------------- 111111" + companySettings_InformationDTOList.isEmpty());
                        for (CompanySettingsDTO companySettingsDTOsetDatabaseName : companySettings_InformationDTOList) {
                            System.out
                                    .println("DATABASE _NMAE For Loop 1111 ------------------------------ " + database_Name_For_Newly_Company);
                            companySettingsDTOsetDatabaseName.setCompany_DatabaseName(database_Name_For_Newly_Company);
                            companySettingsDTOsetDatabaseName.setCompany_id(Long.parseLong(add_ID_To_NewCompany));
                        }

                        for (CompanySettingsDTO companySettingsDTOsetDatabaseName : companySettings_InformationDTOList) {
                            System.out
                                    .println("DATABASE _NMAE For Loop 1111 333 ------------------------------ " + companySettingsDTOsetDatabaseName.getCompany_DatabaseName());
//                            companySettingsDTOsetDatabaseName.setCompany_DatabaseName(database_Name_For_Newly_Company);
                        }

                        gen.other.DatabaseSpiliting.DatabaseSplitingDAO
                                .update_Company_Information(
                                companySettings_InformationDTOList,
                                txtCompanyName1
                                .getText().toString().trim(), false);

                        // ////////////////////////////////////////
                        BufferedImage mImage;
                        final String dir = System.getProperty("user.dir");
                        File imgfile = null;
                        FileInputStream fin = null;
                        gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
                        conn = DatabaseConnection1.GetConnection();
                        gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
                        //conn.setAutoCommit(false);
                        if (source != null && !source.isEmpty()) {
                            imgfile = new File(source);
                            fin = new FileInputStream(imgfile);
                            mImage = ImageIO.read(imgfile);
                            System.out.println("						0" + source);
                            PreparedStatement pre = conn
                                    .prepareStatement("insert into tblcompaniesimage(company_name,company_id,company_image) values(?,?,?)");
                            pre.setString(1, txtCompanyName1.getText()
                                    .toString().trim());
                            pre.setString(2, add_ID_To_NewCompany);
                            if (source != null && !source.isEmpty()) {
                                System.out
                                        .println("oooooooooooooooooooooooooooooooooooooooo");
                                pre.setBinaryStream(3, fin,
                                        (int) imgfile.length());
                            } else {
                                System.out
                                        .println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
                                pre.setString(3, "NULL");
                            }
                            pre.executeUpdate();
                        }

//			PreparedStatement prmt = conn
//				.prepareStatement("delete from tblcompaniesimage where company_id = ? and company_name = ?");
//			System.out.println("");
                        // // prmt.setString(1,
                        // lblcompany_ID1.getText().toString()
//			prmt.setString(1, add_ID_To_NewCompany
//				.trim());
//			prmt.setString(2, company_Cliked_Name);
//			prmt.executeUpdate();
//
//			PreparedStatement pre = conn
//				.prepareStatement("insert into tblcompaniesimage(company_name,company_id,company_image) values(?,?,?)");
//			pre.setString(1, txtCompanyName1.getText().toString()
//				.trim());
//			System.out.println("TTTTTTTTTTTT  ----------- "+lblcompany_ID1.getText().toString().trim());
                        // // pre.setString(2,
                        // lblcompany_ID1.getText().toString().trim());
//			pre.setString(2, add_ID_To_NewCompany.trim());
//			if (source != null && !source.isEmpty()) {
//			    pre.setBinaryStream(3, fin, (int) imgfile.length());
//			} else {
//			    pre.setString(3, "NULL");
//			}
//			pre.executeUpdate();

                        // date24-01-2014
                        // BufferedImage mImage;
                        // final String dir = System.getProperty("user.dir");
                        // File imgfile = null;
                        // FileInputStream fin = null;
                        // if (source != null) {
                        // imgfile = new File(source);
                        // fin = new FileInputStream(imgfile);
                        //
                        // PreparedStatement pre = conn
                        // .prepareStatement("insert into tblcompaniesimage(company_name,company_id,company_image) values(?,?,?)");
                        // pre.setString(1, txtCompanyName1.getText()
                        // .toString().trim());
                        // pre.setString(2, add_ID_To_NewCompany);
                        // if (source != null) {
                        // pre.setBinaryStream(3, fin,
                        // (int) imgfile.length());
                        // } else {
                        // pre.setString(3, "");
                        // }
                        // pre.executeUpdate();
                        // }

                        // date24-01-2014
                        // backupDB_Database_Delete("aj1", "root", "adm",
                        // "D:/exe.sql");
                        // Boolean flag = restoreDB_Database_Delete("w74",
                        // "root", "adm", "D:/exe.sql");

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                JInternalFrame newsas = new JInternalFrame();
                                startEmptyWaitTimerForFrame(newsas,
                                        database_Name_For_Newly_Company);
                            }
                        }).start();
                        // date24-01-2014
                        // // delete the file of All_Database_information.Enc
                        // Path target5 = Paths.get("D:/exe.sql");
                        // if (Files.exists(target5)) {
                        // Files.deleteIfExists(target5);
                        // }

                        conn.commit();
                        gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";

                        btncompanyClear1ActionPerformed(e);
                        initilizeGUIComponents_Company_Delete();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.out.println("Exception : " + e);
                    }

                }
            }
        });
        panel_19.add(btnCompanyCreate1, "cell 4 1");

        btnupdateLogo = new JButton("Update");
        btnupdateLogo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int dialogButton1 = JOptionPane.YES_NO_OPTION;
                int dialogResult1 = JOptionPane
                        .showConfirmDialog(
                        AdSuMuDiSettingsEclipseGUI.this,
                        "If having logo then your Current Logo Will Be Removed ,Do You Want To Proceed ?",
                        "Warning", dialogButton1);
                if (dialogResult1 == 0) {
                    imageloading o = new imageloading();
                    o.setVisible(true);
                }
                // else {
                // formInternalFrameActivated(null);
                // }
            }
        });
        panel_17.add(btnupdateLogo, "cell 2 5");

        btnClearLogo = new JButton("Clear");
        btnClearLogo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LogoPanelView1.setIcon(null);
                LogoPanelView1.revalidate();

                if (!lblcompany_ID1.getText().toString().trim().equals("ID")) {
                    try {
                        gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
                        Connection conn = DatabaseConnection1.GetConnection();
                        PreparedStatement pre = conn
                                .prepareStatement("delete from  tblcompaniesimage where company_name = ? and company_id = ?");
                        pre.setString(1, company_Cliked_Name);
                        pre.setString(2, lblcompany_ID1.getText().toString()
                                .trim());
                        pre.executeUpdate();
                        gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        Logger.getLogger(
                                AdSuMuDiSettingsEclipseGUI.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            AdSuMuDiSettingsEclipseGUI.this,
                            "Selete company to Delete");
                    txtCompanyName1.requestFocus();
                }
            }
        });
        panel_17.add(btnClearLogo, "cell 2 6");

        JPanel panel_4 = new JPanel();
        jTabbedPane1.addTab("Administrator", null, panel_4, null);
        panel_4.setLayout(new MigLayout(
                "",
                "[0px:200px:200px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:300px:300px,grow,shrink 50,fill][grow,shrink 50]",
                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        JPanel panel_22 = new JPanel();
        panel_22.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_4.add(panel_22, "cell 0 0 5 18,grow");
        panel_22.setLayout(new MigLayout(
                "",
                "[0px:200px:200px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill]",
                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        lblUsername = new JLabel("Username");
        panel_22.add(lblUsername, "cell 0 0,alignx trailing");

        txtUser_Name1 = new JTextField();
        txtUser_Name1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtUser_Password1.requestFocus();
                }
            }
        });
        panel_22.add(txtUser_Name1, "cell 1 0 2 1,growx");
        txtUser_Name1.setColumns(10);

        lblNewLabel_5 = new JLabel("Password");
        panel_22.add(lblNewLabel_5, "cell 0 1,alignx trailing");

        txtUser_Password1 = new JPasswordField();
        txtUser_Password1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtUser_ConfirmPassword1.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtUser_Name1.requestFocus();
                }
            }
        });
        panel_22.add(txtUser_Password1, "cell 1 1 2 1,growx");

        lblConfirmPassword = new JLabel("Confirm Password");
        panel_22.add(lblConfirmPassword, "cell 0 2,alignx trailing");

        txtUser_ConfirmPassword1 = new JPasswordField();
        txtUser_ConfirmPassword1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtUser_EmailId1.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtUser_Password1.requestFocus();
                }
            }
        });
        panel_22.add(txtUser_ConfirmPassword1, "cell 1 2 2 1,growx");

        lblEmailId_2 = new JLabel("E-Mail ID");
        panel_22.add(lblEmailId_2, "cell 0 3,alignx trailing");

        txtUser_EmailId1 = new JTextField();
        txtUser_EmailId1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtUser_Email_Password1.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtUser_ConfirmPassword1.requestFocus();
                }
            }
        });
        panel_22.add(txtUser_EmailId1, "cell 1 3 2 1,growx");
        txtUser_EmailId1.setColumns(10);

        lblEmailIdPassword = new JLabel("E-Mail ID Password");
        panel_22.add(lblEmailIdPassword, "cell 0 4,alignx trailing");

        txtUser_Email_Password1 = new JPasswordField();
        txtUser_Email_Password1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtUser_EmailId1.requestFocus();
                }
            }
        });
        panel_22.add(txtUser_Email_Password1, "cell 1 4 2 1,growx");

        lblRole = new JLabel("Role");
        panel_22.add(lblRole, "cell 0 5");

        JPanel panel_23 = new JPanel();
        panel_23.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_22.add(panel_23, "cell 1 5 2 3,grow");
        panel_23.setLayout(new MigLayout("", "[grow]", "[grow]"));

        JScrollPane scrollPane_7 = new JScrollPane();
        panel_23.add(scrollPane_7, "cell 0 0,grow");

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
        scrollPane_7.setColumnHeaderView(tblavailableRole);

        JPanel panel_24 = new JPanel();
        panel_24.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_22.add(panel_24, "cell 3 5 2 3,grow");
        panel_24.setLayout(new MigLayout("", "[grow]", "[grow]"));

        JScrollPane scrollPane_8 = new JScrollPane();
        panel_24.add(scrollPane_8, "cell 0 0,grow");

        tblselectedRole = new JTable();
        tblselectedRole.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
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
        scrollPane_8.setColumnHeaderView(tblselectedRole);

//	lblUserType = new JLabel("User Type");
//	panel_22.add(lblUserType, "cell 0 8");
//
//	radio_User_Type_Admin_1 = new JRadioButton("Administrator");
//	radio_User_Type_Admin_1.addChangeListener(new ChangeListener() {
//	    public void stateChanged(ChangeEvent e) {
//		if (radio_User_Type_Admin_1.isSelected()) {
//		    radio_User_Type_NormalUser1.setSelected(false);
//		}
//		if (radio_User_Type_NormalUser1.isSelected() == false) {
//		    radio_User_Type_Admin_1.setSelected(true);
//		}
//	    }
//	});
//	panel_22.add(radio_User_Type_Admin_1, "cell 1 8");
//
//	radio_User_Type_NormalUser1 = new JRadioButton(
//		"Normal User");
//	radio_User_Type_NormalUser1.addChangeListener(new ChangeListener() {
//	    public void stateChanged(ChangeEvent e) {
//		if (radio_User_Type_NormalUser1.isSelected()) {
//		    radio_User_Type_Admin_1.setSelected(false);
//		}
//		if (radio_User_Type_Admin_1.isSelected() == false) {
//		    radio_User_Type_NormalUser1.setSelected(true);
//		}
//	    }
//	});
//	panel_22.add(radio_User_Type_NormalUser1, "cell 2 8");

        lblNewLabel_6 = new JLabel("Available Company");
        panel_22.add(lblNewLabel_6, "cell 0 9");

        JPanel panel_25 = new JPanel();
        panel_25.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_22.add(panel_25, "cell 1 9 4 3,grow");
        panel_25.setLayout(new MigLayout("", "[grow]", "[grow]"));

        JScrollPane scrollPane_9 = new JScrollPane();
        panel_25.add(scrollPane_9, "cell 0 0,grow");

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
        scrollPane_9.setColumnHeaderView(tblUser_Available_Company);

        lblSelectedCompany = new JLabel("Selected Company");
        panel_22.add(lblSelectedCompany, "cell 0 12");

        JPanel panel_26 = new JPanel();
        panel_26.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_22.add(panel_26, "cell 1 12 4 3,grow");
        panel_26.setLayout(new MigLayout("", "[grow]", "[grow]"));

        JScrollPane scrollPane_10 = new JScrollPane();
        panel_26.add(scrollPane_10, "cell 0 0,grow");

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

                // // check if table selected company is already present or not
                // // if not then ADD
                // Boolean flag_Row_present_Or_Not = true;
                // if (tblUser_Available_Company.getRowCount() > 0) {
                // for (int i = 0; i < tblUser_Available_Company.getRowCount();
                // i++) {
                // if
                // (selectedCompany_Name.equals(tblUser_Available_Company.getValueAt(i,
                // 0).toString()) &&
                // selectedCompany_ID.equals(tblUser_Available_Company.getValueAt(i,
                // 1).toString())) {
                // flag_Row_present_Or_Not = false;
                // break;
                // }
                // }
                // }

                // this purpose of if else is if admin come for new user then
                // add or remove company from respective copmany is right
                // but if he is in edit mode then already selected company
                // should remove if he want but should not add in availabe list
                // and also
                // 2 same selected compnay should not be add
                // if
                // (flag_For_Component_enable_Disable_User_Settings.equals(true))
                // {
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
                // // } else{
                // // getRowCount row must be greater than 0
                // if (tblUser_Selected_Company.getRowCount() > 0) {
                // // find selected row from tableCompanyList
                // for (int i = 0; i < tblUser_Selected_Company.getRowCount();
                // i++) {
                // // selected row
                // if (tblUser_Selected_Company.isRowSelected(i)) {
                // // remove row from table tableCompanyList
                // tablemodel_Company_Selected_User_Settings.removeRow(i);
                // // decrease value of i(row) as one row removed
                // i--;
                // }
                // }
                // }
                // }
            }
        });
        scrollPane_10.setColumnHeaderView(tblUser_Selected_Company);

        btnNewUser1 = new JButton("New");
        btnNewUser1.setMnemonic('N');
        btnNewUser1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flag_For_Component_enable_Disable_User_Settings = true;
                setEnable_Component_User_Settings();
                // claerfield_User_Settings();
                intialise_User_Settings();
                txtUser_Name1.requestFocus();
            }
        });
        panel_22.add(btnNewUser1, "cell 2 16");

        NUButtonCreateUser1 = new JButton("Submit");
        NUButtonCreateUser1.setMnemonic('S');
        NUButtonCreateUser1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Validation_For_User_Creation()) {
                    userSettingDTO_Class_Level = bindGUItoDTO();
                    System.out
                            .println("userSettingDTO_Class_Level =========================== "
                            + userSettingDTO_Class_Level.getUser_id());
                    List<UserSettingDTO> userSettingDTOList = new ArrayList<UserSettingDTO>();
                    userSettingDTOList.add(userSettingDTO_Class_Level);

                    // insert into table user id to company in table
                    // tblusertocompany
                    List<CompanySettingsDTO> companySettingsDTOList13454 = new ArrayList<CompanySettingsDTO>();

                    for (int i = 0; i < tblUser_Selected_Company.getRowCount(); i++) {
                        CompanySettingsDTO companySettingsDTO1 = new CompanySettingsDTO();
                        companySettingsDTO1
                                .setCompany_name(tblUser_Selected_Company
                                .getValueAt(i, 0).toString().trim());
                        companySettingsDTO1.setCompany_id(Long
                                .parseLong(tblUser_Selected_Company
                                .getValueAt(i, 1).toString().trim()));
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
                        gen.mainclass.UserSettingDAO
                                .insertUserRoles(userSettingDTOList);
                        gen.mainclass.UserSettingDAO.insertUsertoCompany(
                                companySettingsDTOList13454,
                                userSettingDTO_Class_Level.getUser_id());
                    }
                    user_ID_Delete = "";
                    JOptionPane.showMessageDialog(
                            AdSuMuDiSettingsEclipseGUI.this, "User Created");
                    intialise_User_Settings();
                }
            }
        });
        panel_22.add(NUButtonCreateUser1, "cell 4 16");

        btnNewButton_12 = new JButton("Back");
        btnNewButton_12.setMnemonic('B');
        btnNewButton_12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    MainClass m = new MainClass();
                    m.menuselection(5);
                    AdSuMuDiSettingsEclipseGUI.this.setClosed(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(AdSuMuDiSettingsEclipseGUI.this.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        });
        panel_22.add(btnNewButton_12, "cell 1 16");

        NUButtonDeleteUser1 = new JButton("Delete");
        NUButtonDeleteUser1.setMnemonic('T');
        NUButtonDeleteUser1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag_For_Component_enable_Disable_User_Settings == false
                        && !user_ID_Delete.toString().trim().equals("")) {

                    if (!user_ID_Delete.toString().trim().equals("1")) {
                        List<UserSettingDTO> userSettingList = new ArrayList<UserSettingDTO>();
                        UserSettingDTO userSettingDTO = new UserSettingDTO();
                        userSettingDTO.setUser_id(user_ID_Delete);
                        userSettingList.add(userSettingDTO);
                        gen.mainclass.UserSettingDAO
                                .Delete_Users(userSettingList);
                        user_ID_Delete = "";
                        JOptionPane.showMessageDialog(
                                AdSuMuDiSettingsEclipseGUI.this,
                                "Selected User Deleted");
                        intialise_User_Settings();
                    } else {
                        JOptionPane.showMessageDialog(
                                AdSuMuDiSettingsEclipseGUI.this,
                                "You can not delete Admin");
                        intialise_User_Settings();
                    }

                } else {
                    JOptionPane.showMessageDialog(
                            AdSuMuDiSettingsEclipseGUI.this,
                            "First Select User to Delete");
                    intialise_User_Settings();
                }
            }
        });
        panel_22.add(NUButtonDeleteUser1, "cell 3 16");

        JPanel panel_27 = new JPanel();
        panel_27.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_4.add(panel_27, "cell 5 0 1 18,grow");
        panel_27.setLayout(new MigLayout("", "[grow]", "[grow]"));

        JScrollPane scrollPane_11 = new JScrollPane();
        panel_27.add(scrollPane_11, "cell 0 0,grow");

        tblUser_Details_for_Admin = new JTable();
        tblUser_Details_for_Admin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // for diasble unnecessary Field
                // for diasble unnecessary Field
                flag_For_Component_enable_Disable_User_Settings = false;
                setDisable_Component_User_Setting();
                bindDTOTOGUI_User_Settings();
            }
        });
        scrollPane_11.setColumnHeaderView(tblUser_Details_for_Admin);

        JPanel panel_5 = new JPanel();
        jTabbedPane1.addTab("Current User", null, panel_5, null);
        panel_5.setLayout(new MigLayout(
                "",
                "[0px:200px:200px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][100px:100px:100px,grow,shrink 50,fill]",
                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        JPanel panel_28 = new JPanel();
        panel_28.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_5.add(panel_28, "cell 0 0 5 18,grow");
        panel_28.setLayout(new MigLayout(
                "",
                "[0px:200px:200px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill]",
                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        lblUsername_1 = new JLabel("Username");
        panel_28.add(lblUsername_1, "cell 0 1,alignx trailing");

        txt_Current_User_Name = new JTextField();
        txt_Current_User_Name.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txt_Current_User_Password.requestFocus();
                }
            }
        });
        panel_28.add(txt_Current_User_Name, "cell 1 1 2 1,growx");
        txt_Current_User_Name.setColumns(10);

        lblPassword_1 = new JLabel("Password");
        panel_28.add(lblPassword_1, "cell 0 2,alignx trailing");

        txt_Current_User_Password = new JPasswordField();
        txt_Current_User_Password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txt_Current_User_ConfirmPassword.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txt_Current_User_Name.requestFocus();
                }
            }
        });
        panel_28.add(txt_Current_User_Password, "cell 1 2 2 1,growx");

        lblConfirmPassword_1 = new JLabel("Confirm Password");
        panel_28.add(lblConfirmPassword_1, "cell 0 3,alignx trailing");

        txt_Current_User_ConfirmPassword = new JPasswordField();
        txt_Current_User_ConfirmPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txt_Current_User_EmailId.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txt_Current_User_Password.requestFocus();
                }
            }
        });
        panel_28.add(txt_Current_User_ConfirmPassword, "cell 1 3 2 1,growx");

        lblEmailId_3 = new JLabel("E-Mail ID");
        panel_28.add(lblEmailId_3, "cell 0 4,alignx trailing");

        txt_Current_User_EmailId = new JTextField();
        txt_Current_User_EmailId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txt_Current_User_Email_Password.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txt_Current_User_ConfirmPassword.requestFocus();
                }
            }
        });
        panel_28.add(txt_Current_User_EmailId, "cell 1 4 2 1,growx");
        txt_Current_User_EmailId.setColumns(10);

        lblEmailIdPassword_1 = new JLabel("E-Mail ID Password");
        panel_28.add(lblEmailIdPassword_1, "cell 0 5,alignx trailing");

        txt_Current_User_Email_Password = new JPasswordField();
        txt_Current_User_Email_Password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txt_Current_User_EmailId.requestFocus();
                }
            }
        });
        panel_28.add(txt_Current_User_Email_Password, "cell 1 5 2 1,growx");

        lblRole_1 = new JLabel("Role");
        panel_28.add(lblRole_1, "cell 0 6");

        JPanel panel_29 = new JPanel();
        panel_29.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_28.add(panel_29, "cell 1 6 3 3,grow");
        panel_29.setLayout(new MigLayout("", "[grow]", "[grow]"));

        JScrollPane scrollPane_12 = new JScrollPane();
        panel_29.add(scrollPane_12, "cell 0 0,grow");

        table_12 = new JTable();
        scrollPane_12.setColumnHeaderView(table_12);

//	lblUserTypr = new JLabel("User Type");
//	panel_28.add(lblUserTypr, "cell 0 9");

//	rdbtnAdministrator_1 = new JRadioButton("Administrator");
//	panel_28.add(rdbtnAdministrator_1, "cell 1 9");
//
//	rdbtnNormalUser_1 = new JRadioButton("Normal User");
//	panel_28.add(rdbtnNormalUser_1, "cell 2 9");

        lblAvailableCompany = new JLabel("Available Company");
        panel_28.add(lblAvailableCompany, "cell 0 10");

        JPanel panel_30 = new JPanel();
        panel_30.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_28.add(panel_30, "cell 1 10 3 3,grow");
        panel_30.setLayout(new MigLayout("", "[grow]", "[grow]"));

        JScrollPane scrollPane_13 = new JScrollPane();
        panel_30.add(scrollPane_13, "cell 0 0,grow");

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
        scrollPane_13
                .setColumnHeaderView(tblCompany_Allocated_Current_User_Settings);

        lblDefaultCompany = new JLabel("Default Company");
        panel_28.add(lblDefaultCompany, "cell 0 13");

        JPanel panel_31 = new JPanel();
        panel_31.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_28.add(panel_31, "cell 1 13 3 3,grow");
        panel_31.setLayout(new MigLayout("", "[grow]", "[grow]"));

        JScrollPane scrollPane_14 = new JScrollPane();
        panel_31.add(scrollPane_14, "cell 0 0,grow");

        tblCurrent_Company_For_Current_User_Settings = new JTable();
        tblCurrent_Company_For_Current_User_Settings
                .addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tablemodel_Current_Company_For_Current_User_Settings
                        .setRowCount(0);
            }
        });
        scrollPane_14
                .setColumnHeaderView(tblCurrent_Company_For_Current_User_Settings);

        NUButtonCreateUser3 = new JButton("Submit");
        NUButtonCreateUser3.setMnemonic('S');
        NUButtonCreateUser3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Validation_For_User_Updation()) {
                    String str = JOptionPane.showInputDialog(
                            AdSuMuDiSettingsEclipseGUI.this,
                            "Enter Password : ", "Password", 1).trim();

                    Boolean flag = false;
                    List<UserSettingDTO> userSettingDTOList = new ArrayList<UserSettingDTO>();
                    userSettingDTOList = gen.mainclass.UserSettingDAO
                            .get_User_Details(
                            gen.dto.Constants.CURRENT_USER_ID, "");
                    for (UserSettingDTO userSettingDTO : userSettingDTOList) {
                        if (str != null
                                && !str.isEmpty()
                                && userSettingDTO.getUser_Password()
                                .equals(str)) {
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

                        if (tblCurrent_Company_For_Current_User_Settings
                                .getRowCount() > 0) {
                            for (int i = 0; i < tablemodel_Current_Company_For_Current_User_Settings
                                    .getRowCount(); i++) {
                                gen.other.CompanySettings.CompanySettingsDAO
                                        .insert_Current_Company(
                                        tablemodel_Current_Company_For_Current_User_Settings
                                        .getValueAt(i, 0)
                                        .toString(),
                                        tablemodel_Current_Company_For_Current_User_Settings
                                        .getValueAt(i, 1)
                                        .toString());
                            }
                        }

                        intialise_Current_User_Settings();
                        JOptionPane.showMessageDialog(
                                AdSuMuDiSettingsEclipseGUI.this,
                                "Submitted Sucessfully ", "Password", 1);
                    } else {
                        JOptionPane.showMessageDialog(
                                AdSuMuDiSettingsEclipseGUI.this,
                                "Enter Correct Password.", "Password", 1);
                    }
                }
            }
        });
        panel_28.add(NUButtonCreateUser3, "cell 1 16");

        btnBack = new JButton("Back");
        btnBack.setMnemonic('B');
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    MainClass m = new MainClass();
                    m.menuselection(5);
                    AdSuMuDiSettingsEclipseGUI.this.setClosed(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(AdSuMuDiSettingsEclipseGUI.this.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        });
        NUButtonCreateUser3.setMnemonic('B');
        panel_28.add(btnBack, "cell 2 16");
    }

    private void set_Value_CheckBox() {
        map_checkBoxValue.clear();
        map_checkBoxValue = gen.other.AdSuMuDiSettings.AdSuMuDiSettingsDAO
                .getting_CheckBoxValue();
        System.out.println("MAp VAlue-----------"
                + map_checkBoxValue.entrySet());
        jTextFieldPaginationValue.setText(map_checkBoxValue
                .get(gen.other.AdSuMuDiSettings.AdSuMuDiConstant.PAGINATION));
    }

    // private boolean hide_flag = false;
    private static DefaultComboBoxModel getSuggestedModel1(
            java.util.List<String> list, String text) {
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        for (String s : list) {
            //if(s.startsWith(text)) m.addElement(s);
            if (s.toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
                m.addElement(s);
                System.out.println("Yes" + s);
            }
        }
        return m;
    }

    private static DefaultComboBoxModel getSuggestedModel2(
            java.util.List<String> list, String text) {
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        for (String s : list) {
            //if(s.startsWith(text)) m.addElement(s);
            if (s.toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
                m.addElement(s);
                System.out.println("Yes" + s);
            }
        }
        return m;
    }

    private static DefaultComboBoxModel getSuggestedModel3(
            java.util.List<String> list, String text) {
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        for (String s : list) {
            //if(s.startsWith(text)) m.addElement(s);
            if (s.toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
                m.addElement(s);
                System.out.println("Yes" + s);
            }
        }
        return m;
    }

    public static void printSettingsInitilise() {
        ResultSet rs2;
        Connection conn = null;
        try {
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            rs2 = st.executeQuery("select * from  tblothersetting");
            if (rs2.next()) {

                PrinterSettings.flagDirectPrint = rs2.getInt("printpreview");
                PrinterSettings.flagPrintPageFormat = rs2
                        .getInt("printDisplayFormat");
                PrinterSettings.flagPrintPageSize = rs2
                        .getInt("printPageFormat");
                System.out
                        .println("PrinterSettings---------------->>printSettingsInitilise------->>printer variables: Direct print:"
                        + PrinterSettings.flagDirectPrint
                        + " Page Format:"
                        + PrinterSettings.flagPrintPageFormat
                        + " Page Size:"
                        + PrinterSettings.flagPrintPageSize);
            }
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
        } catch (Exception e) {
            System.out
                    .println("PrinterSettings----------------->>formInternalFrameClosed :---->>Exception :"
                    + e);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    public final class imageloading extends javax.swing.JInternalFrame {

        BufferedImage mImage;
        Image scaledImage = null;
        ImageIcon scaledImageIcon = null;
        String name, name1;

        public imageloading() {
            //JFrame frm=new JFrame("image loading test");
            source = filechoose();
            System.out
                    .println("ViewEditCompany----->>imageloading -->>source: "
                    + source);
            File inputFile = new File(source);
            try {
                mImage = ImageIO.read(inputFile);
            } catch (IOException ex) {
                // Logger.getLogger(index.class.getName()).log(Level.SEVERE,
                // null, ex);
            }

            scaledImage = mImage.getScaledInstance(LogoPanelView1.getWidth(),
                    LogoPanelView1.getHeight(), Image.SCALE_SMOOTH);
            scaledImageIcon = new ImageIcon(scaledImage);
            FileInputStream fin = null;
            try {

                final String dir = System.getProperty("user.dir");

                Connection conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();

                File f1 = new File(source);// new File(dir +
                // "/images/unnati_hinges_packing1_1.jpg");

                fin = new FileInputStream(f1);

                b1 = new byte[(int) f1.length()];
                fin.read(b1);
                fin.close();

                // int ret =
                // st.executeUpdate("insert into registration(image) values('"+b1+"')");
                // System.out.println("ViewEditCompany----->>imageloading -->>return: "+
                // ret);
            } catch (Exception e) {
                System.out.println("Exception for---->>Image--->" + e);
            }
//    int x=Integer.parseInt(""+LogoPanelView.getAlignmentX());
//    int y=Integer.parseInt(""+LogoPanelView.getAlignmentY());
//    
//    int h=mImage.getTileHeight();
//    int w=mImage.getTileWidth();

            // LogoPanelView.setBorder(new
            // TempClassForImage(scaledImage,LogoPanelView.getSize()));
            LogoPanelView1.setIcon(scaledImageIcon);
            // LogoPanelView.setBounds(x, y, w, h);
            LogoPanelView1.show();

        }

        String filechoose() {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
            chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                public boolean accept(File f) {
                    name = f.getName().toLowerCase();
                    return name.endsWith(".jpg") || name.endsWith(".jpeg")
                            || f.isDirectory();
                }

                public String getDescription() {
                    return ".jpg";
                }
            });

            int r = chooser.showOpenDialog(AdSuMuDiSettingsEclipseGUI.this);

            if (r == JFileChooser.APPROVE_OPTION) {
                name1 = chooser.getSelectedFile().getAbsolutePath();
                StringBuffer sb = new StringBuffer();
                sb.append(name1);

                int l = sb.length();
                for (int i = 0; i < l; i++) {
                    if (sb.charAt(i) == '\\') {
                        sb.insert(i, "\\");
                        i++;
                    }
                }
            }

            return name1;
        }
    }

    private void ActivInactiveComponent() {
        // System.out.println("ALL ADSUMUDI SETTINGS _-----------------------------"
        // +
        // gen.other.AdSuMuDiSettings.AdSuMuDiConstant.map_Company_and_ID.keySet());
//
        // companySettingsDTO =
        // gen.other.AdSuMuDiSettings.AdSuMuDiConstant.getCurrentCompany_Details("");
//	if (companySettingsDTO.getCompany_name().trim().equals("")) {
        // System.out.println("IF -----------------------------------------------"
        // +
        // gen.other.AdSuMuDiSettings.AdSuMuDiConstant.map_Company_and_ID.isEmpty());
//	    jTabbedPane1.setEnabledAt(0, false);
//	    jTabbedPane1.setEnabledAt(1, false);
//	    jTabbedPane1.setEnabledAt(2, false);
//	    jTabbedPane1.setEnabledAt(3, false);
//	    // jTabbedPane1.setEnabledAt(4, false);
//	} else {
        // System.out.println("Else -----------------------------------------------"
        // +
        // gen.other.AdSuMuDiSettings.AdSuMuDiConstant.map_Company_and_ID.isEmpty());
//	    jTabbedPane1.setEnabledAt(0, true);
//	    jTabbedPane1.setEnabledAt(1, true);
//	    jTabbedPane1.setEnabledAt(2, true);
//	    jTabbedPane1.setEnabledAt(3, true);
//	    //  jTabbedPane1.setEnabledAt(4, true);
//	}
    }

    // ////////////// Company Settings Extra Code
    // ////////////////////////////////////////
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
        tableModelCompanyList_Company_Settings = new DefaultTableModel(data,
                col) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tablecompanyList_Company_Settings
                .setModel(tableModelCompanyList_Company_Settings);
        JTableHeader header = tablecompanyList_Company_Settings
                .getTableHeader();
        header.setBackground(Color.yellow);
        tableModelCompanyList_Company_Settings.setRowCount(0);
        tableModelCompanyList_Company_Settings.setColumnCount(2);
        setColumnWidth_Company_Settings(tablecompanyList_Company_Settings);
        //buttonApply_Company_Settings.setVisible(false);
    }

    private void setnemonic_Company_Settings() {
        //buttonApply_Company_Settings.setMnemonic(KeyEvent.VK_A);
        buttonExit_Company_Settings.setMnemonic(KeyEvent.VK_E);
    }

    private void bindDTOTOGUI_Company_Settings() {
        // gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings
        // = "";
        // get Company_List
        companySettingsDTOList_Company_Settings = CompanySettingsDAO
                .getComapany_List("", "");
        tableModelCompanyList_Company_Settings.setRowCount(0);
        for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList_Company_Settings) {
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

    }

    private void setColumnWidth_Company_Settings(JTable passedTable) {
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
        passedTable.getColumnModel().getColumn(1).setMinWidth(70);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(70);
    }

    // //////////////////////////////// Company Settings Extra Code End
    // ////////////////////////////////////////
    // //////////////////////////////// Company Back UP
    // ////////////////////////////////////////
    private void initialise_Company_Back_UP() {
        intilisecomponent_Company_Back_UP();
        bindData2GUI_Company_Back_UP();
        setnmenomics_Company_Back_UP();
    }

    private void setnmenomics_Company_Back_UP() {
        btnBackUP_Company_Back_UP.setMnemonic(KeyEvent.VK_B);
        btnPath_Company_Back_UP.setMnemonic(KeyEvent.VK_P);
    }

    private void intilisecomponent_Company_Back_UP() {

        String col[] = {"CompanyName", ""};
        String data[][] = {{"", ""}};
        tablemodelCompany_Company_Back_UP = new DefaultTableModel(data, col) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tableCompanyList_Company_Back_UP
                .setModel(tablemodelCompany_Company_Back_UP);
        JTableHeader header = tableCompanyList_Company_Back_UP.getTableHeader();
        header.setBackground(Color.yellow);
        tablemodelCompany_Company_Back_UP.setRowCount(0);
        tablemodelCompany_Company_Back_UP.setColumnCount(2);

        String col1[] = {"CompanyName", ""};
        String data1[][] = {{"", ""}};
        tablemodelBackUPCompany_Company_Back_UP = new DefaultTableModel(data1,
                col1) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tableCompanyBackUp_Company_Back_UP
                .setModel(tablemodelBackUPCompany_Company_Back_UP);
        JTableHeader header1 = tableCompanyBackUp_Company_Back_UP
                .getTableHeader();
        header1.setBackground(Color.yellow);
        tablemodelBackUPCompany_Company_Back_UP.setRowCount(0);
        tablemodelBackUPCompany_Company_Back_UP.setColumnCount(2);

        setColumnWidth_Company_Back_UP(tableCompanyList_Company_Back_UP);
        setColumnWidth_Company_Back_UP(tableCompanyBackUp_Company_Back_UP);

        company_SQLTxtFile_List_Company_Back_UP.clear();
        company_List_Enc_Company_Back_UP.clear();
        company_Information_DTOList_Company_Back_UP.clear();
        //backUP_Company_DTOList.clear();
    }

    // bind to GUI
    private void bindData2GUI_Company_Back_UP() {
        // call when Form open
        company_Information_DTOList_Company_Back_UP = CompanySettingsDAO
                .getComapany_List("", "");

        int rowCount = 0;
        for (CompanySettingsDTO companySettingsDTO : company_Information_DTOList_Company_Back_UP) {

            if (!"information_schema".equalsIgnoreCase(companySettingsDTO
                    .getCompany_name())
                    && !"performance_schema"
                    .equalsIgnoreCase(companySettingsDTO
                    .getCompany_name())) {
                rowCount = tableCompanyList_Company_Back_UP.getRowCount();
                tablemodelCompany_Company_Back_UP.setRowCount(rowCount + 1);
                tableCompanyList_Company_Back_UP.setValueAt(
                        companySettingsDTO.getCompany_name(), rowCount, 0);
                tableCompanyList_Company_Back_UP.setValueAt(companySettingsDTO
                        .getCompany_id().toString(), rowCount, 1);
                System.out
                        .println("++++++++++++++++++++++++++++++++++++++++++++     "
                        + companySettingsDTO.getCompany_name());

            }
        }
        tableCompanyList_Company_Back_UP.requestFocus();
        tableCompanyList_Company_Back_UP.changeSelection(0, 0, false, false);
    }

    //take a back up
    public static boolean backupDB_Company_Back_UP(String dbName,
            String dbUserName, String dbPassword, String path) {
        Boolean flag = false;
        Process runtimeProcess;

        String executeCmd1 = "D:/MetalCopies/10-10-2013uploadcopy/server-Copy(2)/bin/mysqldump -u "
                + dbUserName
                + " -p"
                + dbPassword
                + " "
                + dbName
                + " -r "
                + path;
        String executeCmd = installedServerPath + "/server/bin/mysqldump -u "
                + dbUserName + " -p" + dbPassword + " " + dbName + " -r "
                + path;

        System.out.println("PAth Getting      --------------------------- "
                + executeCmd1);
        System.out
                .println("PAth Getting BY regedit --------------------------- "
                + executeCmd);

        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            System.out.println("processComplete-->>" + processComplete);
            if (processComplete == 0) {
                String cmd = installedServerPath
                        + "/server/bin/mysqldump.exe exit"; // exit mysql

                System.out.println("Server Path +++++++++++++  " + cmd);

                Process proc1 = proc1 = Runtime.getRuntime().exec(cmd);
                int processComplete1 = proc1.waitFor();
                if (processComplete1 == 0) {
                    System.out
                            .println("Backup 111111111    created successfully");
                } else {
                    System.out
                            .println("Backup111111111     created successfully");
                }

                System.out.println("Backup created successfully");
                flag = true;
            } else {
                System.out.println("Could not create the backup");
            }
            //Runtime.getRuntime().exec("forfiles /p D:\\AdSuMuDi-1.0\\Thread /s /m *.sql /d -3 /c 'cmd /c del @file : date >=0days'");
            runtimeProcess.destroy();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    private void createcompanyfile_Company_Back_UP(String filepath) {
        // / create .SQL file with fileName of
        // database.enc,database,company_name
        company_SQLTxtFile_List_Company_Back_UP = textFilesforSQL_Company_Back_UP(fileChooser_Company_Back_UP);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filepath + "All_Database_information.txt",
                    "UTF-8");

            // all rows of tableCompanyBackUp
            for (int i = 0; i < tableCompanyBackUp_Company_Back_UP
                    .getRowCount(); i++) {

                // all data of company_Information_DTOList
                for (CompanySettingsDTO companySettingsDTO : company_Information_DTOList_Company_Back_UP) {

                    // if
                    // (backUP_Company_DTOList.get(i).equals(companySettingsDTO.getCompany_name()))
                    // {
                    if (companySettingsDTO.getCompany_name().equals(
                            tableCompanyBackUp_Company_Back_UP.getValueAt(i, 0)
                            .toString().trim())
                            && companySettingsDTO
                            .getCompany_id()
                            .toString()
                            .equals(tableCompanyBackUp_Company_Back_UP
                            .getValueAt(i, 1).toString().trim())) {
                        // add comapny name in text file
                        // space "$" to distingiush Name with ID
                        writer.print("$" + companySettingsDTO.getCompany_name());
                        // add database name  in text file 
                        // space "$" to distingiush Name with ID
                        writer.print("$"
                                + companySettingsDTO.getCompany_DatabaseName());
                        // add company ID in text file
                        writer.print("$" + companySettingsDTO.getCompany_id());

                        for (String company_file_Name : company_SQLTxtFile_List_Company_Back_UP) {
                            String database_name = "";
                            StringTokenizer st = new StringTokenizer(
                                    company_file_Name, ".");
                            while (st.hasMoreTokens()) {
                                database_name = st.nextToken();
                                break;
                            }

                            if (database_name.equals(companySettingsDTO
                                    .getCompany_DatabaseName())) {
                                // add file name where databse present in text
                                // file
                                writer.println("$" + company_file_Name);
                            }
                        }
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void encryptFile_Company_Back_UP() {
        try {
            byte[] encoded = "1234567890098765".getBytes();
            SecretKey key1 = new SecretKeySpec(encoded, "AES");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
            ecipher_Company_Back_UP = Cipher
                    .getInstance("AES/CBC/PKCS5Padding");
            dcipher_Company_Back_UP = Cipher
                    .getInstance("AES/CBC/PKCS5Padding");
            ecipher_Company_Back_UP.init(Cipher.ENCRYPT_MODE, key1, paramSpec);
            dcipher_Company_Back_UP.init(Cipher.DECRYPT_MODE, key1, paramSpec);

            // Encrypt  the .SQL files
            // all rows of tableCompanyBackUp
            for (int i = 0; i < tableCompanyBackUp_Company_Back_UP
                    .getRowCount(); i++) {
                // all data of company_Information_DTOList
                for (CompanySettingsDTO companySettingsDTO : company_Information_DTOList_Company_Back_UP) {
                    // if
                    // (backUP_Company_DTOList.get(i).equals(companySettingsDTO.getCompany_name()))
                    // {
                    if (companySettingsDTO.getCompany_name().equals(
                            tableCompanyBackUp_Company_Back_UP.getValueAt(i, 0)
                            .toString().trim())
                            && companySettingsDTO
                            .getCompany_id()
                            .toString()
                            .equals(tableCompanyBackUp_Company_Back_UP
                            .getValueAt(i, 1).toString().trim())) {
                        encrypt_Company_Back_UP(
                                new FileInputStream(
                                fileChooser_Company_Back_UP
                                + "/"
                                + companySettingsDTO
                                .getCompany_DatabaseName()
                                + ".sql"),
                                new FileOutputStream(
                                fileChooser_Company_Back_UP
                                + "/"
                                + companySettingsDTO
                                .getCompany_DatabaseName()
                                + ".enc"));
                    }
                }
            }
            // Encrypt the file of All All_Database_information.txt
            encrypt_Company_Back_UP(new FileInputStream(
                    fileChooser_Company_Back_UP + "/"
                    + "All_Database_information" + ".txt"),
                    new FileOutputStream(fileChooser_Company_Back_UP + "/"
                    + "All_Database_information" + ".enc"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File Not Found:" + e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
            System.out
                    .println("Invalid Alogorithm Parameter:" + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("No Such Algorithm:" + e.getMessage());
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            System.out.println("No Such Padding:" + e.getMessage());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            System.out.println("Invalid Key:" + e.getMessage());
        }
    }

    private void Zipfile_Company_Back_UP(String filepath) {
        try {
            Path target = Paths.get(fileChooser_Company_Back_UP + "/"
                    + "ffdfdsgfd" + ".abc");
            if (Files.exists(target)) {
                Files.delete(target);
            }

            zipArchieveName_Company_Back_UP = fileChooser_Company_Back_UP + "/"
                    + "ffdfdsgfd.abc";
            // Reference to the file we will be adding to the zipfile
            BufferedInputStream origin = null;
            // Reference to zip file
            FileOutputStream dest = new FileOutputStream(
                    zipArchieveName_Company_Back_UP);
            // Wrap our destination zipfile with a ZipOutputStream
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
                    dest));
            // Create a byte[] buffer that we will read data from the source
            // files into and then transfer it to the zip file
            byte[] data = new byte[BUFFER_SIZE];
            List files = new ArrayList();

            company_List_Enc_Company_Back_UP = textFilesforEnc_Company_Back_UP(fileChooser_Company_Back_UP);
            for (int i = 0; i < company_List_Enc_Company_Back_UP.size(); i++) {
                System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFF"
                        + company_List_Enc_Company_Back_UP.get(i));
                files.add(fileChooser_Company_Back_UP + "/"
                        + company_List_Enc_Company_Back_UP.get(i));
            }
            // Iterate over all of the files in list
            for (Iterator i = files.iterator(); i.hasNext();) {
                // Get a BufferedInputStream that we can use to read the source
                // file
                String filename = (String) i.next();
                FileInputStream fi = new FileInputStream(filename);
                origin = new BufferedInputStream(fi, BUFFER_SIZE);
                // Setup the entry in the zip file
                ZipEntry entry = new ZipEntry(filename);
                out.putNextEntry(entry);
                // Read data from the source file and write it out to the zip
                // file
                int count;
                while ((count = origin.read(data, 0, BUFFER_SIZE)) != -1) {
                    out.write(data, 0, count);
                }

                // Close the source file
                origin.close();
            }
            // Close the zip file
            out.close();

            // delete the file of .Enc 
            // all rows of tableCompanyBackUp
            for (int i = 0; i < tableCompanyBackUp_Company_Back_UP
                    .getRowCount(); i++) {
                // all data of company_Information_DTOList
                for (CompanySettingsDTO companySettingsDTO : company_Information_DTOList_Company_Back_UP) {
                    if (companySettingsDTO.getCompany_name().equals(
                            tableCompanyBackUp_Company_Back_UP.getValueAt(i, 0)
                            .toString().trim())
                            && companySettingsDTO
                            .getCompany_id()
                            .toString()
                            .equals(tableCompanyBackUp_Company_Back_UP
                            .getValueAt(i, 1).toString().trim())) {
                        Path target1 = Paths.get(fileChooser_Company_Back_UP
                                + "/"
                                + companySettingsDTO.getCompany_DatabaseName()
                                + ".enc");
                        Files.deleteIfExists(target1);
                    }
                }
            }

            // delete the file of All_Database_information.Enc 
            Path target5 = Paths.get(fileChooser_Company_Back_UP + "/"
                    + "All_Database_information" + ".enc");
            if (Files.exists(target5)) {
                Files.deleteIfExists(target5);
            }

            // delete the file of All_Database_information.txt 
            Path target2 = Paths.get(fileChooser_Company_Back_UP + "/"
                    + "All_Database_information" + ".txt");
            if (Files.exists(target2)) {
                try {
                    // proc.WaitForExit(); 
                    Files.deleteIfExists(target2);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }

            // delete the file of .SQL files 
            // all rows of tableCompanyBackUp
            for (int i = 0; i < tableCompanyBackUp_Company_Back_UP
                    .getRowCount(); i++) {
                // all data of company_Information_DTOList
                for (CompanySettingsDTO companySettingsDTO : company_Information_DTOList_Company_Back_UP) {
                    if (companySettingsDTO.getCompany_name().equals(
                            tableCompanyBackUp_Company_Back_UP.getValueAt(i, 0)
                            .toString().trim())
                            && companySettingsDTO
                            .getCompany_id()
                            .toString()
                            .equals(tableCompanyBackUp_Company_Back_UP
                            .getValueAt(i, 1).toString().trim())) {
                        Path target1 = Paths.get(fileChooser_Company_Back_UP
                                + "/"
                                + companySettingsDTO.getCompany_DatabaseName()
                                + ".sql");
                        Files.delete(target1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void encrypt_Company_Back_UP(InputStream is, OutputStream os) {
        try {
            byte[] buf = new byte[1048576];
            // bytes at this stream are first encoded
            os = new CipherOutputStream(os, ecipher_Company_Back_UP);
            // read in the clear text and write to out to encrypt
            int numRead = 0;
            while ((numRead = is.read(buf)) >= 0) {
                os.write(buf, 0, numRead);
            }
            // close all streams
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("I/O Error:" + e.getMessage());
        }
    }

    // find all files of .enc 
    List<String> textFilesforEnc_Company_Back_UP(File dir) {
        List<String> textFiles = new ArrayList<String>();
        if (dir.listFiles() != null) {
            for (File file : dir.listFiles()) {
                if (file.getName().endsWith((".enc"))) {
                    textFiles.add(file.getName());
                }
            }
        } else {
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "Enter valid path");
        }

        return textFiles;
    }

    // find all files of .SQL 
    List<String> textFilesforSQL_Company_Back_UP(File dir) {
        List<String> textFiles = new ArrayList<String>();
        if (dir.listFiles() != null) {
            for (File file : dir.listFiles()) {
                if (file.getName().endsWith((".sql"))) {
                    textFiles.add(file.getName());
                }
            }
        } else {
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "Enter valid path");
        }

        return textFiles;
    }

    private Boolean validation_Company_Back_UP() {
        Boolean flag = true;
        // if (tableCompanyBackUp_Company_Back_UP.getRowCount() == 0 ||
        // tableCompanyBackUp_Company_Back_UP.getRowCount() < 0) {
        // JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
        // "First Select Company To Bck UP");
//	    flag = false;
//	    btnPath_Company_Back_UP.requestFocus();
        // } else if
        // (txtDestinationPath_Company_Back_UP.getText().toString().trim().isEmpty())
        // {
        // JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
        // "First Select Destination Folder for  Back UP");
//	    flag = false;
//	    btnPath_Company_Back_UP.requestFocus();
//	}
        // else if
        // (!txtDestinationPath_Company_Back_UP.getText().toString().trim().isEmpty())
        // {
        // String path =
        // txtDestinationPath_Company_Back_UP.getText().toString().trim();
//	    System.out.println("Validation path for Back UP"+path);
//	    StringTokenizer token = new StringTokenizer(path," ");
//	    
//	    System.out.println("Has a token s ------------- "+token.hasMoreTokens());
//	    
//	    if (token.hasMoreTokens()) {
        // JOptionPane.showMessageDialog(null,
        // "Selected Destination Folder Contain Spaces which is Not Allowed ");
//		flag = false;
//		btnPath_Company_Back_UP.requestFocus();
//	    }
//	}

        return flag;
    }

    private void setColumnWidth_Company_Back_UP(JTable passedTable) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.LEFT);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        passedTable.getColumnModel().getColumn(0)
                .setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(380);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(380);

        passedTable.getColumnModel().getColumn(1)
                .setCellRenderer(centerRenderer);
        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(1).setMinWidth(70);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(70);

    }

    // //////////////// Company Back UP
    // over//////////////////////////////////////////////////
    // //////////////// Company Restore
    // //////////////////////////////////////////////////
    private void initialise_Company_Restore() {
        setActiveInactiveComponent_Company_Restore();
        intilisecomponent_Company_Restore();
        setnmenomics_Company_Restore();
    }

    private void setnmenomics_Company_Restore() {
        buttonFilePath_Company_Restore.setMnemonic(KeyEvent.VK_F);
        btnRestore_Company_Restore.setMnemonic(KeyEvent.VK_E);
    }

    private void setActiveInactiveComponent_Company_Restore() {
        //jLabel2.setVisible(false);

        String col[] = {"CompanyName", ""};
        String data[][] = {{"", ""}};
        tablemodelCompany_Company_Restore = new DefaultTableModel(data, col) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tableCompanyList_Company_Restore
                .setModel(tablemodelCompany_Company_Restore);
        JTableHeader header = tableCompanyList_Company_Restore.getTableHeader();
        header.setBackground(Color.yellow);
        tablemodelCompany_Company_Restore.setRowCount(0);
        tablemodelCompany_Company_Restore.setColumnCount(2);

        String col1[] = {"CompanyName", ""};
        String data1[][] = {{"", ""}};
        tablemodelRestoreCompany_Company_Restore = new DefaultTableModel(data1,
                col1) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tableCompanyRestoreUp_Company_Restore
                .setModel(tablemodelRestoreCompany_Company_Restore);
        JTableHeader header1 = tableCompanyRestoreUp_Company_Restore
                .getTableHeader();
        header1.setBackground(Color.yellow);
        tablemodelRestoreCompany_Company_Restore.setRowCount(0);
        tablemodelRestoreCompany_Company_Restore.setColumnCount(2);

        setColumnWidth_Company_Restore(tableCompanyList_Company_Restore);
        setColumnWidth_Company_Restore(tableCompanyRestoreUp_Company_Restore);

    }

    private void intilisecomponent_Company_Restore() {
        company_List_Clicked_Company_Restore.clear();
        company_List_Restore_Company_Restore.clear();
        company_Database_List_Restore.clear();
        company_SQLFile_List_Restore.clear();
        company_Full_InformationDTOList_Company_Restore.clear();
        //map_Database_And_File_Name.clear();
        company_InformationDTOList_Company_Restore.clear();
        company_InformationDTO_Final_List_Company_Restore.clear();
        unZip_Company_File_List_Company_Restore.clear();

        setActiveInactiveComponent_Company_Restore();
    }

    private void bindData2GUI_Company_Restore() {
        if (validation_Company_Restore()) {
            try {
                String zipFilePath = fileChooser_Company_Restore
                        + "/ffdfdsgfd.abc";
                String destDirectory = operatingSystemDrive + "/Check";
                UnzipUtility unzipper = new UnzipUtility();
                try {
                    unZip_Company_File_List_Company_Restore = unzipper.unzip(
                            zipFilePath, destDirectory);
                } catch (Exception ex) {
                    // some errors occurred
                    ex.printStackTrace();
                }

                // decryptfiles("D:/databasecheck/All_Database_information",
                // ".enc");
                decryptfiles_Company_Restore(fileChooser_Company_Restore + "/"
                        + "All_Database_information", ".enc", null);

                if (validation_For_All_SQLCompany_File_Company_Restore()) {
                    List<String> selected_List = new ArrayList<String>();
                    /// read Company Name present in file
                    BufferedReader brin;
                    brin = new BufferedReader(new FileReader(
                            fileChooser_Company_Restore + "/"
                            + "All_Database_information" + ".txt"));
                    String line = brin.readLine();
                    //   int count = 0;
                    while (line != null) {
                        if (!line.equals("")) {
                            Company_InformationDTO company_InformationDTO = new Company_InformationDTO();
                            String databaseName = line;
                            String last_name = "";
                            StringTokenizer st = new StringTokenizer(
                                    databaseName, "$");
                            int i = 0;
                            while (st.hasMoreTokens() && i == 0) {
                                last_name = st.nextToken();
                                switch (i) {
                                    case 0:
                                        company_InformationDTO
                                                .setCompany_Name(last_name);
                                        selected_List.add(last_name);
                                    case 1:
                                        company_InformationDTO
                                                .setCompany_Database_Name(st
                                                .nextToken());
                                    case 2:
                                        company_InformationDTO
                                                .setCompany_Unique_ID(st
                                                .nextToken());
                                    case 3:
                                        company_InformationDTO
                                                .setCompany_SQL_File_Name(st
                                                .nextToken());
                                }

                                i++;
                            }
                            company_InformationDTOList_Company_Restore
                                    .add(company_InformationDTO);
                        }
                        line = brin.readLine();
                    }
                    brin.close();

                    int rowCount = 0;
                    tablemodelCompany_Company_Restore.setRowCount(rowCount);

//		    for (int i = 0; i < selected_List.size(); i++) {
                    // if
                    // (!"information_schema".equalsIgnoreCase(selected_List.get(i))
                    // &&
                    // !"performance_schema".equalsIgnoreCase(selected_List.get(i)))
                    // {
//			    rowCount = tableCompanyList.getRowCount();
                    // tablemodelCompany_Company_Restore.setRowCount(rowCount +
                    // 1);
                    // tableCompanyList.setValueAt(selected_List.get(i),
                    // rowCount, 0);
                    // tableCompanyList.setValueAt(selected_List.get(i),
                    // rowCount, 1);
//			}
//		    }

                    for (Company_InformationDTO company_InformationDTO : company_InformationDTOList_Company_Restore) {
                        // if
                        // (!"information_schema".equalsIgnoreCase(selected_List.get(i))
                        // &&
                        // !"performance_schema".equalsIgnoreCase(selected_List.get(i)))
                        // {
                        rowCount = tableCompanyList_Company_Restore
                                .getRowCount();
                        tablemodelCompany_Company_Restore
                                .setRowCount(rowCount + 1);
                        tableCompanyList_Company_Restore.setValueAt(
                                company_InformationDTO.getCompany_Name(),
                                rowCount, 0);
                        tableCompanyList_Company_Restore.setValueAt(
                                company_InformationDTO.getCompany_Unique_ID(),
                                rowCount, 1);
//			}
                        System.out
                                .println("Daabase from company_InformationDTO ---------- "
                                + company_InformationDTO
                                .getCompany_Database_Name());
                    }

                    tableCompanyList_Company_Restore.requestFocus();
                    tableCompanyList_Company_Restore.changeSelection(0, 0,
                            false, false);

                    // Delete Extra Created .ENC files
                    for (int i = 0; i < unZip_Company_File_List_Company_Restore
                            .size(); i++) {
                        Path target5 = Paths.get(fileChooser_Company_Restore
                                + "/"
                                + unZip_Company_File_List_Company_Restore
                                .get(i));
                        if (Files.exists(target5)) {
                            Files.delete(target5);
                        }
                    }

                    // Delete Extra Created .txt files
                    Path target5 = Paths.get(fileChooser_Company_Restore + "/"
                            + "All_Database_information" + ".txt");
                    if (Files.exists(target5)) {
                        Files.delete(target5);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    public static boolean restoreDB_Company_Restore(String dbName,
            String dbUserName, String dbPassword, String source) {

        // String[] executeCmd = new
        // String[]{"D:/MetalCopies/10-10-2013uploadcopy/server-Copy(2)/bin/mysql",
        // "--user=" + dbUserName, "--password=" + dbPassword, dbName, "-e",
        // "source " + source};
        String[] executeCmd = new String[]{
            installedServerPath + "/server/bin/mysql",
            "--user=" + dbUserName, "--password=" + dbPassword, dbName,
            "-e", "source " + source};
        Process runtimeProcess;
        try {

            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                System.out.println("Backup restored successfully");
                return true;
            } else {
                System.out.println("Could not restore the backup");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    List<String> EncFiles_Company_Restore(File dir) {
        List<String> textFiles = new ArrayList<String>();
        if (dir.listFiles() != null) {
            for (File file : dir.listFiles()) {
                if (file.getName().endsWith((".enc"))) {
                    textFiles.add(file.getName());
                }
            }
        } else {
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "Enter valid path");
        }
        return textFiles;
    }

    List<String> TextFiles_Company_Restore(File dir) {
        List<String> textFiles = new ArrayList<String>();
        if (dir.listFiles() != null) {
            for (File file : dir.listFiles()) {
                if (file.getName().endsWith((".txt"))) {
                    textFiles.add(file.getName());
                }
            }
        } else {
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "Enter valid path");
        }
        return textFiles;
    }

    private void decryptfiles_Company_Restore(String calling, String Ext,
            List<String> unZip_Comapny_File_List) {
        try {
            byte[] encoded = "1234567890098765".getBytes();
            SecretKey key1 = new SecretKeySpec(encoded, "AES");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
            dcipher_Company_Restore = Cipher
                    .getInstance("AES/CBC/PKCS5Padding");
            dcipher_Company_Restore.init(Cipher.DECRYPT_MODE, key1, paramSpec);

            if (calling.isEmpty()) {
                for (int i = 0; i < unZip_Comapny_File_List.size(); i++) {
                    String only_Company_File_Name = "";
                    StringTokenizer only_Company_File_Token = new StringTokenizer(
                            unZip_Comapny_File_List.get(i), ".");
                    while (only_Company_File_Token.hasMoreTokens()) {
                        only_Company_File_Name = only_Company_File_Token
                                .nextToken();
                        break;
                    }

                    company_SQLFile_List_Restore.add(only_Company_File_Name
                            + ".sql");

                    if ((fileChooser_Company_Restore + "/" + unZip_Comapny_File_List
                            .get(i).toString())
                            .equals(fileChooser_Company_Restore + "/"
                            + "All_Database_information.enc")) {
                        decrypt_Company_Restore(new FileInputStream(
                                fileChooser_Company_Restore + "/"
                                + unZip_Comapny_File_List.get(i)),
                                new FileOutputStream(
                                fileChooser_Company_Restore + "/"
                                + only_Company_File_Name
                                + ".txt"));
                    } else {
                        decrypt_Company_Restore(new FileInputStream(
                                fileChooser_Company_Restore + "/"
                                + unZip_Comapny_File_List.get(i)),
                                new FileOutputStream(
                                fileChooser_Company_Restore + "/"
                                + only_Company_File_Name
                                + ".sql"));
                    }
                }
            } else {
                decrypt_Company_Restore(new FileInputStream(calling + Ext),
                        new FileOutputStream(calling + ".txt"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File Not Found:" + e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
            System.out
                    .println("Invalid Alogorithm Parameter:" + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("No Such Algorithm:" + e.getMessage());
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            System.out.println("No Such Padding:" + e.getMessage());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            System.out.println("Invalid Key:" + e.getMessage());
        }
    }

    private static void decrypt_Company_Restore(InputStream is, OutputStream os) {
        try {
            byte[] buf = new byte[1048576];

            // bytes read from stream will be decrypted
            CipherInputStream cis = new CipherInputStream(is,
                    dcipher_Company_Restore);
            // read in the decrypted bytes and write the clear text to out
            int numRead = 0;
            while ((numRead = cis.read(buf)) >= 0) {
                os.write(buf, 0, numRead);
            }
            // close all streams
            cis.close();
            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("I/O Error:" + e.getMessage());
        }
    }

    private void map_Database_FileName_Company_Restore(
            List<String> unZip_Company_File_List) {
        try {
            /// read Company Name present in All Unzipe file one by one
            BufferedReader brin;
            for (int j = 0; j < unZip_Company_File_List.size(); j++) {

                // Not for All_Database_information.enc file
                if (!unZip_Company_File_List.get(j).equals(
                        "All_Database_information.enc")) {
                    String only_Company_File_Name = "";
                    // find FileName of SQL
                    StringTokenizer only_Company_File_Token = new StringTokenizer(
                            unZip_Company_File_List.get(j), ".");
                    // find FileName of SQL and save in only_Company_File_Name
                    while (only_Company_File_Token.hasMoreTokens()) {
                        only_Company_File_Name = only_Company_File_Token
                                .nextToken();
                        break;
                    }

                    // select the file 
                    brin = new BufferedReader(new FileReader(
                            fileChooser_Company_Restore + "/"
                            + only_Company_File_Name + ".sql"));
                    String line = brin.readLine();
                    int count = 0;

                    // Read the database present in .SQL file so read only first
                    // 5 rows by using count
                    while (line != null && count < 5) {
                        // if line is not empty
                        if (!line.equals("")) {
                            String full_Line = line;
                            String dataBase_Name = "";
                            // if line is second then only read full line
                            if (count == 2) {
                                StringTokenizer st = new StringTokenizer(
                                        full_Line, ":");
                                while (st.hasMoreTokens()) {
                                    dataBase_Name = st.nextToken();
                                }

                                // List from Databse
                                for (CompanySettingsDTO company_NameandDatabaseDTO : company_Full_InformationDTOList_Company_Restore) {
                                    // check Database Name from File and DAtabse
                                    // is equal or not
                                    System.out
                                            .println("Company Database Nmae from database ::::::::::::"
                                            + company_NameandDatabaseDTO
                                            .getCompany_DatabaseName());
                                    System.out
                                            .println("Company Database Nmae from file     ::::::::::::"
                                            + company_NameandDatabaseDTO
                                            .getCompany_DatabaseName());
                                    if (company_NameandDatabaseDTO
                                            .getCompany_DatabaseName().equals(
                                            dataBase_Name.trim())) {
                                        // company_List_Restore_Company_Restore 
                                        for (CompanySettingsDTO companySettingsDTO : company_List_Restore_Company_Restore) {
                                            // check Compamny Nmae and 
                                            if (company_NameandDatabaseDTO
                                                    .getCompany_name()
                                                    .trim()
                                                    .equals(companySettingsDTO
                                                    .getCompany_name())
                                                    && company_NameandDatabaseDTO
                                                    .getCompany_id()
                                                    .toString()
                                                    .trim()
                                                    .equals(companySettingsDTO
                                                    .getCompany_id()
                                                    .toString())) {
                                                if (!dataBase_Name.isEmpty()) {
                                                    // map_Database_And_File_Name.put(last_name,
                                                    // unZip_Company_File_List_Company_Restore.get(j));
                                                    Company_InformationDTO company_InformationDTO = new Company_InformationDTO();
                                                    company_InformationDTO
                                                            .setCompany_Name(company_NameandDatabaseDTO
                                                            .getCompany_name());
                                                    company_InformationDTO
                                                            .setCompany_Database_Name(company_NameandDatabaseDTO
                                                            .getCompany_DatabaseName());
                                                    company_InformationDTO
                                                            .setCompany_SQL_File_Name(unZip_Company_File_List
                                                            .get(j));
                                                    company_InformationDTO
                                                            .setCompany_Unique_ID(company_NameandDatabaseDTO
                                                            .getCompany_id()
                                                            .toString());

                                                    company_InformationDTO_Final_List_Company_Restore
                                                            .add(company_InformationDTO);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        line = brin.readLine();
                        count++;
                    }
                    brin.close();
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName()).log(
                    Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    private Boolean validation_Company_Restore() {
        Boolean flag = true;
        System.out.println("Table Row cCount ------------------------"
                + tableCompanyRestoreUp_Company_Restore.getRowCount());
        if (!txtSourcePath_Company_Restore.getText().toString().trim()
                .isEmpty()
                && tableCompanyList_Company_Restore.getRowCount() == 0) {
            Path target5 = Paths.get(fileChooser_Company_Restore
                    + "/ffdfdsgfd.abc");
            if (!Files.exists(target5)) {
                flag = false;
                JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                        "Select Correct Path As No Company for Selection");
                buttonFilePath_Company_Restore.requestFocus();
            }
        } else if (tableCompanyRestoreUp_Company_Restore.getRowCount() == 0
                || tableCompanyRestoreUp_Company_Restore.getRowCount() < 0) {
            System.out.println("erwerwerewrewrew");
            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "Select Company First To Restore");
            buttonFilePath_Company_Restore.requestFocus();
        }
        return flag;
    }

    private Boolean validation_For_All_SQLCompany_File_Company_Restore() {
        Boolean flag = true;

        Path target5 = Paths.get(fileChooser_Company_Restore + "/"
                + "All_Database_information" + ".txt");
        if (!Files.exists(target5)) {
            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "S                 for Selection");
            buttonFilePath_Company_Restore.requestFocus();
        }
        return flag;
    }

    // set width to column of table
    private void setColumnWidth_Company_Restore(JTable passedTable) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.LEFT);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        passedTable.getColumnModel().getColumn(0)
                .setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(350);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(350);

        passedTable.getColumnModel().getColumn(1)
                .setCellRenderer(centerRenderer);
        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(1).setMinWidth(80);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(80);

    }

    // //////////////// Company Restore
    // over//////////////////////////////////////////////////
    // //////////////// Company Spliting
    // /////////////////////////////////////////////////
    private void initialise_Database_Spliting() {
        // initialise componenet 
        intilisecomponent_Database_Splitng();
        bindData2GUT_Database_Splitng();
        setnemonic_Database_Splitng();
    }

    // set nemonic
    private void setnemonic_Database_Splitng() {
        btnDataSpliting_Database_Splitng.setMnemonic(KeyEvent.VK_T);
    }

    // initialise componenet 
    private void intilisecomponent_Database_Splitng() {

        tablemodelCompany_Database_Splitng = (DefaultTableModel) tableCompanyList_Database_Splitng
                .getModel();
        tablemodelCompany_Database_Splitng.setRowCount(0);
        tablemodelCompany_Database_Splitng.setColumnCount(2);

        fromJDateChooser_Database_Splitng.setDateFormatString("dd-MM-yyyy");
        Calendar currentDate = Calendar.getInstance();
        fromJDateChooser_Database_Splitng.setDate(currentDate.getTime());

        fromJDateChooser_Database_Splitng
                .setDateFormatString(Constants.DATE_FORMAT_STRING);
        tfDatePicker_Database_Splitng = (JTextField) fromJDateChooser_Database_Splitng
                .getComponent(1);

        tfDatePicker_Database_Splitng.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_ENTER) {
                    btnDataSpliting_Database_Splitng.requestFocus();
                }
                if (code == KeyEvent.VK_ESCAPE) {
                    // tfUnderField.requestFocus();
                }
            }
        });

        tfDatePicker_Database_Splitng
                .addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt,
                            tfDatePicker_Database_Splitng);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            AdSuMuDiSettingsEclipseGUI.this,
                            ex.getMessage());
                }
            }
        });

        tfDatePicker_Database_Splitng.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // throw new
                // UnsupportedOperationException("Not supported yet.");
                tfDatePicker_Database_Splitng.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                // throw new
                // UnsupportedOperationException("Not supported yet.");
            }
        });

    }

    // BindToDataToGui
    private void bindData2GUT_Database_Splitng() {
        try {
            bindDataToTableCompany_Database_Splitng();

        } catch (SQLException ex) {
            Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    // set all Companies to vector
    private void bindDataToTableCompany_Database_Splitng() throws SQLException {
        company_ListDTO_Database_Splitng.clear();
        // set all_company_List to  company_ListDTO_Database_Splitng
        company_ListDTO_Database_Splitng = CompanySettingsDAO.getComapany_List(
                "", "");
        //vectorCompanyList.clear();
        tablemodelCompany_Database_Splitng.setRowCount(0);
        for (CompanySettingsDTO companySettingsDTO : company_ListDTO_Database_Splitng) {
            int row = tablemodelCompany_Database_Splitng.getRowCount() + 1;
            tablemodelCompany_Database_Splitng.setRowCount(row);
            //  Do not add Database
            tableCompanyList_Database_Splitng.setValueAt(
                    companySettingsDTO.getCompany_name(), row - 1, 0);
            tableCompanyList_Database_Splitng.setValueAt(
                    companySettingsDTO.getCompany_id(), row - 1, 1);

        }
        setColumnWidth_Database_Splitng(tableCompanyList_Database_Splitng);

    }

    // Take BackUp
    public static boolean backupDB_Database_Splitng(String dbName,
            String dbUserName, String dbPassword, String path) {
        Process runtimeProcess;

        // String executeCmd =
        // "D:/MetalCopies/10-10-2013uploadcopy/server-Copy(2)/bin/mysqldump -u "
        // + dbUserName + " -p" + dbPassword + " " + dbName + " -r " + path;
        String executeCmd = installedServerPath + "/server/bin/mysqldump -u "
                + dbUserName + " -p" + dbPassword + " " + dbName + " -r "
                + path;

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
    public static boolean restoreDB_Database_Splitng(String dbName,
            String dbUserName, String dbPassword, String source) {
        Boolean flag = false;
        // String[] executeCmd = new
        // String[]{"D:/MetalCopies/10-10-2013uploadcopy/server-Copy(2)/bin/mysql",
        // "--user=" + dbUserName, "--password=" + dbPassword, dbName, "-e",
        // "source " + source};
        String[] executeCmd = new String[]{
            installedServerPath + "/server/bin/mysql",
            "--user=" + dbUserName, "--password=" + dbPassword, dbName,
            "-e", "source " + source};
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
    private Boolean validation_Database_Splitng() {
        Boolean flag = true;

        //validation for company_Name present or not  
        if (selected_Company_Name_Database_Splitng.toString().trim().equals("")
                || selected_Company_Name_Database_Splitng.toString().trim() == null) {
            //validation for Company Name is valid or not  
            tfDatePicker_Database_Splitng.requestFocus();
            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "Select Exits Company");
        }
        if (fromJDateChooser_Database_Splitng.getDate() == null) {
            //validation for date is valid or not  
            tfDatePicker_Database_Splitng.requestFocus();
            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "Invalid Date Selection");
        }
        return flag;
    }

    // call Company Selection form after Database spliting is completed
    private void call_Selection_Companies_Database_Splitng() {
        gen.other.CompanySettings.CompanysSettings company_selection = new gen.other.CompanySettings.CompanysSettings(
                "Company Selection");
        try {
            company_selection.setVisible(true);
            company_selection.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        AdSuMuDiSettingsEclipseGUI.this.getParent().add(company_selection);
        AdSuMuDiSettingsEclipseGUI.this.getParent().setVisible(true);
        Dimension desktopSize = AdSuMuDiSettingsEclipseGUI.this.getParent()
                .getSize();
        Dimension jInternalFrameSize = company_selection.getSize();
        company_selection.setLocation(
                (desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        try {
            company_selection.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
        BasicInternalFrameUI basicInternalFrameUI = (BasicInternalFrameUI) company_selection
                .getUI();
        Component north = basicInternalFrameUI.getNorthPane();
        MouseMotionListener[] actions = (MouseMotionListener[]) north
                .getListeners(MouseMotionListener.class);

        for (int i = 0; i < actions.length; i++) {
            north.removeMouseMotionListener(actions[i]);
        }
    }

    private void setColumnWidth_Database_Splitng(JTable passedTable) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.LEFT);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        passedTable.getColumnModel().getColumn(0)
                .setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(500);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(500);

        passedTable.getColumnModel().getColumn(1)
                .setCellRenderer(centerRenderer);
        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(1).setMinWidth(80);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(80);
    }

    // //////////////// Company Spliting
    // over//////////////////////////////////////////////////
    // //////////////// Company Alter
    // //////////////////////////////////////////////////
    private void intialise_Company_Delete() {
        initilizeGUIComponents_Company_Delete();
        setnemonic_Company_Delete();
    }

    private void initilizeGUIComponents_Company_Delete() {
        initComponentActiveInActive_Company_Delete();
        bindDTOTOGUI_Company_Delete();
    }

    private void initComponentActiveInActive_Company_Delete() {
        String col[] = {"CompanyName", ""};
        String data[][] = {{"", ""}};
        tableModelCompanyList_Company_Delete = new DefaultTableModel(data, col) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tablecompanyList_Company_Delete
                .setModel(tableModelCompanyList_Company_Delete);
        JTableHeader header = tablecompanyList_Company_Delete.getTableHeader();
        header.setBackground(Color.yellow);
        tableModelCompanyList_Company_Delete.setRowCount(0);
        tableModelCompanyList_Company_Delete.setColumnCount(2);
        //setColumnWidth_Company_Delete(tablecompanyList_Company_Delete);
    }

    private void setnemonic_Company_Delete() {
        btnDelete1.setMnemonic(KeyEvent.VK_T);
        buttonExit_Company_Delete.setMnemonic(KeyEvent.VK_E);
        txtCompanyName1.requestFocus();
//        btnupdateLogo.setEnabled(false);
//	btnDelete1.setEnabled(false);
//	btnUpdate1.setEnabled(false);
    }

    private void bindDTOTOGUI_Company_Delete() {
        // gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings
        // = "";
        // get Company_List
        companySettingsDTOList_Company_Delete = CompanySettingsDAO
                .getComapany_List("", "");
        tableModelCompanyList_Company_Delete.setRowCount(0);
        for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList_Company_Delete) {
            tableModelCompanyList_Company_Delete
                    .setRowCount(tableModelCompanyList_Company_Delete
                    .getRowCount() + 1);
            // set into the table
            tableModelCompanyList_Company_Delete.setValueAt(
                    companySettingsDTO.getCompany_name(),
                    tableModelCompanyList_Company_Delete.getRowCount() - 1, 0);
            tableModelCompanyList_Company_Delete.setValueAt(
                    companySettingsDTO.getCompany_id(),
                    tableModelCompanyList_Company_Delete.getRowCount() - 1, 1);
        }
    }

    private void setColumnWidth_Company_Delete(JTable passedTable) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.LEFT);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        passedTable.getColumnModel().getColumn(0)
                .setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(300);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(300);

        passedTable.getColumnModel().getColumn(1)
                .setCellRenderer(centerRenderer);
        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(1).setMinWidth(80);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(80);
    }

    //////////// Delete Company /////////////////////////////////////////////
    private void alter_Company(String company_Name, String company_ID) {
        try {
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
            Connection con = DatabaseConnection1.GetConnection();

            // reomve logo
            LogoPanelView1.setIcon(null);
            LogoPanelView1.revalidate();

            String query = "select company_image from tblcompaniesimage where company_name = '"
                    + company_Name + "' and company_id = " + company_ID + "";
            System.out.println("************************************** "
                    + query);
            PreparedStatement prmt50 = con.prepareStatement(query);
            ResultSet rs20 = prmt50.executeQuery();
            while (rs20.next()) {

                Image img = null;
                BufferedImage mImage1 = null;
                byte[] bytes = null;
                System.out
                        .println("Set icon ---------------------------------");
//		    Blob blob = rs20.getBlob("company_image");
//		    InputStream in = blob.getBinaryStream();
//		    mImage1 = ImageIO.read(in);
                //byte[] imgdata=rs.getBytes("image");
                if (gen.dto.Constants.DATABASE_SERVER
                        .equals("com.mysql.jdbc.Driver")) {
                    bytes = rs20.getBytes("company_image");
                } else {
                    // bytes =
                    // IOUtils.toByteArray((rs20.getClob("company_image")).getAsciiStream());
                    bytes = rs20.getBytes("company_image");
                    System.out
                            .println("Image ----------------------------------------"
                            + bytes);
                }
//		bytes = rs20.getBytes("company_image");
                img = Toolkit.getDefaultToolkit().createImage(bytes);

                // Image
                // scaledImage=img.getScaledInstance(LogoPanelView.getWidth(),
                // LogoPanelView.getHeight(), Image.SCALE_SMOOTH);
                // Image
                // scaledImage=img.getScaledInstance(LogoPanelView.getWidth(),
                // LogoPanelView.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(img);
                Image img1 = icon.getImage();
//                Image newimg = img.getScaledInstance(141, 140,Image.SCALE_SMOOTH);
                Image newimg = img.getScaledInstance(LogoPanelView1.getWidth(), LogoPanelView1.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon newicon = new ImageIcon(newimg);
                LogoPanelView1.setIcon(newicon);
            }

            con.close();
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";

        } catch (SQLException ex) {
            Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName()).log(
                    Level.SEVERE, null, ex);
        } catch (Exception e) {
        }

        try {

            // set Company_ID to ID Label 
            lblcompany_ID1.setText(company_ID);

            String q = "";
            //Connection conn;
            ResultSet resultSet = null;
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
            Connection conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            Statement st = conn.createStatement();

            q = "select * from tblcompanyinfo where company_name ='"
                    + company_Name + "' and company_id = " + company_ID + "";
            resultSet = st.executeQuery(q);
            while (resultSet.next()) {
                System.out.println("DDD " + resultSet.getString("created_date"));
                System.out.println("DDD " + (java.util.Date) Constants.DATE_FORMATER.parse(resultSet.getString("applicable_From_date").trim()));
//                JdateChooser_CompanyApplicableFrom.setDate((java.util.Date) Constants.DATE_FORMATER.parse(resultSet.getString("created_date").trim()));
                tfDatePicker_Company_Applicable_From.setText(gen.dto.Constants.simpleDateFormatGUI.format(resultSet.getDate("applicable_From_date")));
                String name = resultSet.getString("company_name");
                company_Cliked_Name = name;
                company_Cliked_Database_Name = resultSet
                        .getString("company_database");
                txtCompanyName1.setText(name);

                //txtCompanyName.setEnabled(false);
                String alias = resultSet.getString("Alias");
                txtCompanyAlias1.setText(alias);
                //txtCompanyAlias.setEnabled(false);
                String address = resultSet.getString("Address");
                txtCompanyAddress1.setText(address);
                //txtCompanyAddress.setEnabled(false);
                String contact = "" + resultSet.getString("ContactNo");
                txtCompanyContact1.setText(contact);
                //txtCompanyContact.setEnabled(false);
                String emailid = resultSet.getString("EmailId");
                txtCompanyMail1.setText(emailid);
                //txtCompanyMail.setEnabled(false);
                String income = "" + resultSet.getString("IncomeTaxNo");
                txtCompanyITN1.setText(income);
                //txtCompanyITN.setEnabled(false);
                String sales = "" + resultSet.getString("VatTinNo");
                txtCompanySTN1.setText(sales);
                //txtCompanySTN.setEnabled(false);
                String declaration = resultSet.getString("Declaration");
                txtCompanyDeclaration1.setText(declaration);
                //txtCompanyDeclaration.setEnabled(false);
                String tagline = resultSet.getString("TagLine");
                txtCompanyTagLine1.setText(tagline);
                //txtCompanyTagLine.setEnabled(false);
                String termsConditions = resultSet.getString("TermCnditions");
                txtCompanyTermsConditions1.setText(termsConditions);
                //txtCompanyTermsConditions.setEnabled(false);
                String nameOfGod = resultSet.getString("NameOfGod");
                txtNameOfGod1.setText(nameOfGod);
                //txtNameOfGod.setEnabled(false);
                String signAuthority = resultSet.getString("SignAuthority");
                txtCompanySignature1.setText(signAuthority);
                //txtCompanySignature.setEnabled(false);
                String cstNewNumber = resultSet.getString("SaleTaxNo");
                txtCompanyCSTNo1.setText(cstNewNumber);
                //txtCompanyCSTNo.setEnabled(false);
                String LBTNumber = resultSet.getString("LBTNo");
                jtextFieldLBTNo.setText(LBTNumber);
                
                String companyNo = resultSet.getString("FIELD1");
                txtCompanyIdNo.setText(companyNo);
                //txtLBTNumber.setEnabled(false);
            }
            conn.commit();
            conn.close();
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
        } catch (ParseException ex) {
            Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

///////////////////////////////////////////////////////////////////////////
    /// create tables in Newly Created Database From generic Database.
    // Take BackUp
    public static boolean backupDB_Database_Delete(String dbName,
            String dbUserName, String dbPassword, String path) {
        Process runtimeProcess;

        // String executeCmd =
        // "D:/MetalCopies/10-10-2013uploadcopy/server-Copy(2)/bin/mysqldump -u "
        // + dbUserName + " -p" + dbPassword + " " + dbName + " -r " + path;
        String executeCmd = installedServerPath + "/server/bin/mysqldump -u "
                + dbUserName + " -p" + dbPassword + " " + dbName + " -r "
                + path;

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
    public static boolean restoreDB_Database_Delete(String dbName,
            String dbUserName, String dbPassword, String source) {
        Boolean flag = false;
        // String[] executeCmd = new
        // String[]{"D:/MetalCopies/10-10-2013uploadcopy/server-Copy(2)/bin/mysql",
        // "--user=" + dbUserName, "--password=" + dbPassword, dbName, "-e",
        // "source " + source};
        String[] executeCmd = new String[]{
            installedServerPath + "/server/bin/mysql",
            "--user=" + dbUserName, "--password=" + dbPassword, dbName,
            "-e", "source " + source};
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

    // //////////////// Company Alter
    // over//////////////////////////////////////////////////
    private void insert_Imagein_Company_Database(String database_Name) {
        try {
            // insert image into companies database table for printing purpose.
            BufferedImage mImage1;
            final String dir1 = System.getProperty("user.dir");
            File imgfile1 = null;
            FileInputStream fin1 = null;
            if (source != null) {
                try {
                    imgfile1 = new File(source);
                    fin1 = new FileInputStream(imgfile1);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            // gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings
            // = database_Name;
//	    System.out.println("DAtabase ---------------                   "
//		    + database_Name);
//	    Connection conn1 = DatabaseConnection1.GetConnection();
//	    conn1.setAutoCommit(false);
//	    // delete already exist image
//
//	    PreparedStatement prmt =
//		    conn1.prepareStatement("delete from registration");
//	    prmt.executeUpdate();
//
//
//	    PreparedStatement pre1 =
//		    conn1.prepareStatement("insert into registration(image) values(?)");
//	    if (source != null) {
//		pre1.setBinaryStream(1, fin1, (int) imgfile1.length());
//	    } else {
//		pre1.setString(1, "");
//	    }
//	    pre1.executeUpdate();

//	    conn1.commit();

            ////////// complted ///////////////////////////////////////////

        } catch (Exception ex) {
            Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    public void startEmptyWaitTimerForFrame(JInternalFrame frame,
            String database_Name) {
        // Image img = new javax.swing.ImageIcon(dir +
        // "/images/Kasturi-logo-1.png").getImage();
        //((java.awt.Frame) dialog.getOwner()).setIconImage(img);
        System.out.println("DDir ----------------------------------" + dir);
        optionPane.setIcon(new ImageIcon(new javax.swing.ImageIcon(dir
                + "/images/Thinfadingline.gif").getImage()));
        optionPane.setMessage("Wait..  Company is Creating...");
        dialog.getContentPane().add(optionPane);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(frame);
        //long endTime = System.currentTimeMillis() + 15000;
        if (gen.dto.Constants.DATABASE_SERVER.equals("com.mysql.jdbc.Driver")) {
            Boolean flag = true;
            int i = 0;
            while (flag && i == 0) {
                frame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                backupDB_Database_Delete("aj1", "root", "adm", "D:/exe.sql");
                flag = restoreDB_Database_Delete(database_Name, "root", "adm",
                        "D:/exe.sql");
                i++;
            }
        } else {
            DatabaseHelper.createCompanyDatabase(database_Name);
            gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = "";
        }

        // insert image into companies database table for printing purpose.
        //insert_Imagein_Company_Database(database_Name);
        ////////// complted ///////////////////////////////////////////

        // gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings
        // = "";
        frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        dialog.setVisible(false);
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));

        JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                "Company Successfully Created");
    }

    //////////////////////////check compnay name is only Number
    private Boolean validation_FOR_CreateCompany_Naming(String company_Name_Text) {
        Boolean flag = true;
        try {
            double d = Double.parseDouble(company_Name_Text);
            flag = false;
        } catch (NumberFormatException nfe) {
            System.out.println("nfe" + nfe);
        }
        return flag;
    }

    // Allow only number and character in CompanyName
    public static void filterCharacter(java.awt.event.KeyEvent evt,
            javax.swing.JTextField jtxtField) {
        char c = evt.getKeyChar();
        if (!Character.isLetterOrDigit(c) && c != ' ') {
            evt.consume();
        }
        int i;

        if (c == '.') {
            int flg = 0;
            i = 0;
            while (i < jtxtField.getText().trim().length()) {
                if (jtxtField.getText().trim().charAt(i) == '.') {
                    flg = 1;
                    break;
                }
                i++;
            }

            if (flg == 1) {
                evt.consume();
            }
        }
    }

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
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "User Name Data Exceeding "
                    + Constants.jTextFieldCharacterLengthMEDIUM
                    + " Character Limit");
            txtUser_Name1.requestFocus();
        } else if (txtUser_Password1.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthMEDIUM) {
            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "User Password Data Exceeding "
                    + Constants.jTextFieldCharacterLengthMEDIUM
                    + " Character Limit");
            txtUser_Password1.requestFocus();
        } else if (txtUser_ConfirmPassword1.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthMEDIUM) {
            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "User Confirm Password Data Exceeding "
                    + Constants.jTextFieldCharacterLengthMEDIUM
                    + " Character Limit");
            txtUser_ConfirmPassword1.requestFocus();
        } else if (txtUser_EmailId1.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthSMALL) {
            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "User Email-ID Data Exceeding "
                    + Constants.jTextFieldCharacterLengthSMALL
                    + " Character Limit");
            txtUser_EmailId1.requestFocus();
        } else if (txtUser_Email_Password1.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthSMALL) {
            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "User Email-ID Password Data Exceeding "
                    + Constants.jTextFieldCharacterLengthSMALL
                    + " Character Limit");
            txtUser_Email_Password1.requestFocus();
        } else if (!m.find()) {

            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "Enter Valid UserName");
            txtUser_Name1.requestFocus();

        } else if (txtUser_Name1.getText().toString().trim().equals("")) {

            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "Enter UserName");
            txtUser_Name1.requestFocus();

        } else if (txtUser_Password1.getText().toString().trim().equals("")) {

            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "Enter Password");
            txtUser_Password1.requestFocus();

        } else if (!txtUser_Password1.getText().toString().trim()
                .equals(txtUser_ConfirmPassword1.getText().toString().trim())) {

            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "Password MisMatch");
            txtUser_Password1.requestFocus();

        } // 
        else if ((!matcher.matches() && !txtUser_EmailId1.getText().toString()
                .trim().equals(""))) {

            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "Enter Valid Email ID");
            txtUser_EmailId1.requestFocus();

        } // else if (txtUser_EmailId1.getText().toString().trim().equals(""))
        // {
        //
        //	    if (!txtUser_Email_Password1.toString().trim().isEmpty()) {
        //		flag = false;
        // JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
        // "First Enter Email ID");
        //		txtUser_EmailId1.requestFocus();
        //	    }
        // // } else if
        // (!mapRoleandID.containsKey(tf_Role_User_Settings.getText().toLowerCase().toString()))
        // {
        //	} 
        else if (tblselectedRole.getRowCount() == 0
                || tblselectedRole.getRowCount() < 0) {

            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "Select valid Role");
//	    tf_Role_User_Settings.requestFocus();

        } // else if (!radio_User_Type_Admin_1.isSelected() &&
        // !radio_User_Type_NormalUser1.isSelected()) {
        //
        //	    flag = false;
        // JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
        // "Please select User Type");
        //	    radio_User_Type_Admin_1.requestFocus();
        //
        //	} 
        else if (tblUser_Selected_Company.getRowCount() == 0
                || tblUser_Selected_Company.getRowCount() < 0) {

            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "Please select Company");

        } else if (!txtUser_Name1.getText().toString().trim().equals("")
                && flag_For_Component_enable_Disable_User_Settings.equals(true)) {
            for (UserSettingDTO usersettingDTO : user_SettingsDTOList_User_Settings) {
                if (usersettingDTO.getUser_Name().equals(
                        txtUser_Name1.getText().toString().trim())) {
                    flag = false;
                    JOptionPane.showMessageDialog(
                            AdSuMuDiSettingsEclipseGUI.this,
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
//			flag = false;
        // JOptionPane.showMessageDialog(null,
        // "Already User present With this User Name");
//			txtUser_Name1.requestFocus();
//			break;
//		    }
//		}
//	}
        return flag;
    }

    ////////////////////// initialise 
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
                return false;//This causes all cells to be not editable
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
                return false;//This causes all cells to be not editable
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
                return false;//This causes all cells to be not editable
            }
        };
        tblUser_Details_for_Admin.setModel(tablemodel_User_Details_Settings);
        JTableHeader header_for_User_details = tblUser_Details_for_Admin
                .getTableHeader();
        header_for_User_details.setBackground(Color.yellow);
        tablemodel_User_Details_Settings.setRowCount(0);
        tablemodel_User_Details_Settings.setColumnCount(1);
        //setColumnWidth_Users_Settings(tblUser_Details_for_Admin);

        String col_for_Role_Available[] = {"Avaliable Role"};
        String data_for_Role_Available[][] = {{""}};
        tablemodel_AvailableRole_User_Settings = new DefaultTableModel(
                data_for_Role_Available, col_for_Role_Available) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tblavailableRole.setModel(tablemodel_AvailableRole_User_Settings);
        JTableHeader header_for_available_role = tblavailableRole
                .getTableHeader();
        header_for_available_role.setBackground(Color.yellow);
        tablemodel_AvailableRole_User_Settings.setRowCount(0);
        tablemodel_AvailableRole_User_Settings.setColumnCount(1);
        //setColumnWidth_Roles_Users_Settings(tblavailableRole);

        String col_for_Role_Selected[] = {"Selected Role"};
        String data_for_Role_Selected[][] = {{""}};
        tablemodel_SelectedRole_User_Settings = new DefaultTableModel(
                data_for_Role_Selected, col_for_Role_Selected) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tblselectedRole.setModel(tablemodel_SelectedRole_User_Settings);
        JTableHeader header_for_Selected_role = tblselectedRole
                .getTableHeader();
        header_for_Selected_role.setBackground(Color.yellow);
        tablemodel_SelectedRole_User_Settings.setRowCount(0);
        tablemodel_SelectedRole_User_Settings.setColumnCount(1);
        //setColumnWidth_Roles_Users_Settings(tblselectedRole);
    }

    private void setnemonic_User_Settings() {
        NUButtonCreateUser1.setMnemonic(KeyEvent.VK_S);
        NUButtonBack1.setMnemonic(KeyEvent.VK_B);

    }

    private void intilise_DATA_User_Settings() {
        try {
            initRole();
            // ADD into Table Available Company
            companySettingsDTOList_User_Settings = CompanySettingsDAO
                    .getComapany_List("", "");
            tablemodel_Company_Available_User_Settings.setRowCount(0);
            for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList_User_Settings) {
                System.out
                        .println("tablemodel_Company_Available_User_Settings ------------------ ");
                tablemodel_Company_Available_User_Settings
                        .setRowCount(tablemodel_Company_Available_User_Settings
                        .getRowCount() + 1);
                tablemodel_Company_Available_User_Settings.setValueAt(
                        companySettingsDTO.getCompany_name(),
                        tablemodel_Company_Available_User_Settings
                        .getRowCount() - 1, 0);
                tablemodel_Company_Available_User_Settings.setValueAt(
                        companySettingsDTO.getCompany_id(),
                        tablemodel_Company_Available_User_Settings
                        .getRowCount() - 1, 1);
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
            Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName()).log(
                    Level.SEVERE, null, ex);
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
//	choosen_Role = tf_Role_User_Settings.getText().toString().trim();

//	int role_id = 0;
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

//	if (choosen_Role.equalsIgnoreCase("Standard")) {
//	    role_id = 2;
//	} else if (choosen_Role.equalsIgnoreCase("Reports")) {
//	    role_id = 3;
//	} else if (choosen_Role.equalsIgnoreCase("AccountVoucher")) {
//	    role_id = 4;
//	} else if (choosen_Role.equalsIgnoreCase("Group")) {
//	    role_id = 5;
//	} else if (choosen_Role.equalsIgnoreCase("StockItem")) {
//	    role_id = 6;
//	}

        //userSettingDTO.setUser_Role(role_id);

//	int user_Type = 0;
//	if (radio_User_Type_Admin_1.isSelected()) {
//	    user_Type = 1;
//	} else if (radio_User_Type_NormalUser1.isSelected()) {
//	    user_Type = 2;
//	}
//	userSettingDTO.setUser_Type(user_Type);
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
                user_Cliked_For_Update = userSettingDTO_Class_Level
                        .getUser_Name();
                txtUser_Name1
                        .setText(userSettingDTO_Class_Level.getUser_Name());
                txtUser_Password1.setText(userSettingDTO_Class_Level
                        .getUser_Password());
                txtUser_ConfirmPassword1.setText(userSettingDTO_Class_Level
                        .getUser_Password());
                txtUser_EmailId1.setText(userSettingDTO_Class_Level
                        .getUser_Email_ID());
                txtUser_Email_Password1.setText(userSettingDTO_Class_Level
                        .getUser_Email_Password());

                String role = "";
                for (Map.Entry<String, String> e : mapRoleandID.entrySet()) {
                    if (e.getValue().equals(
                            "" + userSettingDTO_Class_Level.getUser_Role())) {
                        role = e.getKey();
                    }
                }
//		tf_Role_User_Settings.setText(role);

                //Set Data to table Selected role
                List<FeaturesDTO> featuresDTOList = new ArrayList<FeaturesDTO>();
                System.out.println("User -------------------------------- "
                        + userSettingDTO_Class_Level.getUser_id());
                featuresDTOList = gen.mainclass.UserSettingDAO
                        .getRoleByUsers(userSettingDTO_Class_Level.getUser_id());
                tablemodel_SelectedRole_User_Settings.setRowCount(0);
                for (FeaturesDTO featuresDTO : featuresDTOList) {
                    tablemodel_SelectedRole_User_Settings
                            .setRowCount(tablemodel_SelectedRole_User_Settings
                            .getRowCount() + 1);
                    tablemodel_SelectedRole_User_Settings
                            .setValueAt(featuresDTO.getRole_Name(),
                            tablemodel_SelectedRole_User_Settings
                            .getRowCount() - 1, 0);
                    System.out.println("Insert Compnies-----------"
                            + featuresDTO.getRole_Name());
                }
                // remove companies which are present in selected_company_table
                // from available_company table
                for (int i = 0; i < tblselectedRole.getRowCount(); i++) {
                    // company_nmae and company_id from seletecd table
                    String company_Name = tblselectedRole.getValueAt(i, 0)
                            .toString();
                    for (int j = 0; j < tblavailableRole.getRowCount(); j++) {
                        System.out.println("company_Name -------------------"
                                + company_Name);
                        System.out.println("---------------------------"
                                + tblavailableRole.getValueAt(j, 0));
                        System.out.println("------------"
                                + tblavailableRole.getValueAt(j, 0).equals(
                                company_Name));
                        if (company_Name.equals(tblavailableRole.getValueAt(j,
                                0))) {
                            // remove row from table tableCompanyList
                            System.out.println("------------                 "
                                    + j);
                            tablemodel_AvailableRole_User_Settings.removeRow(j);
                            // decrease value of i(row) as one row removed
                            j--;
                        }
                    }
                }

//                // call table available with all campany List as we already
//                // remove selected company from this list
//		intilise_DATA_User_Settings();

                List<CompanySettingsDTO> companySettingsDTOList = new ArrayList<CompanySettingsDTO>();
                companySettingsDTOList = gen.mainclass.UserSettingDAO
                        .getCurrent_Company_Details_By_User(userSettingDTO_Class_Level);

                tablemodel_Company_Selected_User_Settings.setRowCount(0);
                for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList) {
                    tablemodel_Company_Selected_User_Settings
                            .setRowCount(tablemodel_Company_Selected_User_Settings
                            .getRowCount() + 1);
                    tablemodel_Company_Selected_User_Settings.setValueAt(
                            companySettingsDTO.getCompany_name(),
                            tablemodel_Company_Selected_User_Settings
                            .getRowCount() - 1, 0);
                    tablemodel_Company_Selected_User_Settings.setValueAt(
                            companySettingsDTO.getCompany_id(),
                            tablemodel_Company_Selected_User_Settings
                            .getRowCount() - 1, 1);
                }

                // remove companies which are present in selected_company_table
                // from available_company table
                for (int i = 0; i < tblUser_Selected_Company.getRowCount(); i++) {
                    // company_nmae and company_id from seletecd table
                    String company_Name = tblUser_Selected_Company.getValueAt(
                            i, 0).toString();
                    String company_ID = tblUser_Selected_Company.getValueAt(i,
                            1).toString();
                    for (int j = 0; j < tblUser_Available_Company.getRowCount(); j++) {
                        if (company_Name.equals(tblUser_Available_Company
                                .getValueAt(j, 0))
                                && company_ID.equals(tblUser_Available_Company
                                .getValueAt(j, 1).toString())) {
                            // remove row from table tableCompanyList
                            tablemodel_Company_Available_User_Settings
                                    .removeRow(j);
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
            Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    private void claerfield_User_Settings() {
        txtUser_Name1.setText("");
        txtUser_ConfirmPassword1.setText("");
        txtUser_Password1.setText("");
        txtUser_EmailId1.setText("");
        txtUser_Email_Password1.setText("");
//	tf_Role_User_Settings.setText("");
        userSettingDTO_Class_Level = new UserSettingDTO();
        user_Cliked_For_Update = "";
        user_ID_Delete = "";
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
        passedTable.getColumnModel().getColumn(0).setMinWidth(280);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(280);

//	passedTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
//	passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
//	passedTable.getColumnModel().getColumn(1).setMinWidth(50);
//	passedTable.getColumnModel().getColumn(1).setMaxWidth(50);
    }

    private void setColumnWidth_Roles_Users_Settings(JTable passedTable) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.LEFT);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        passedTable.getColumnModel().getColumn(0)
                .setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(120);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(120);

    }

    // / Stop user Settings creation validation
    // //////////////////////////////////////////////////////////////
    // ///////////////////// Current User Call
    // /////////////////////////////////////////////////////////////
    private void intialise_Current_User_Settings() {
        initilizeGUIComponents_Current_User_Settings();
        setnemonic_Current_User_Settings();
    }

    private void initilizeGUIComponents_Current_User_Settings() {
        initComponentActiveInActive_Current_User_Settings();
        //bindDTOTOUser_Details_table();
        intiliase_DATA_Current_User_Settings();
        intiliase_Current_Company_Current_User_Settings();
        //claerfield_User_Settings();
        //setEnable_Component_User_Settings();
    }

    private void initComponentActiveInActive_Current_User_Settings() {
        String col[] = {"CompanyName", ""};
        String data[][] = {{"", ""}};
        tablemodel_Company_Allocated_Current_User_Settings = new DefaultTableModel(
                data, col) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
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
                return false;//This causes all cells to be not editable
            }
        };
        tblCurrent_Company_For_Current_User_Settings
                .setModel(tablemodel_Current_Company_For_Current_User_Settings);
        JTableHeader header_Current_Company_Current_User = tblCurrent_Company_For_Current_User_Settings
                .getTableHeader();
        header_Current_Company_Current_User.setBackground(Color.yellow);
        tablemodel_Current_Company_For_Current_User_Settings.setRowCount(0);
        tablemodel_Current_Company_For_Current_User_Settings.setColumnCount(2);

        tblAllocatedRole_CurrentUser = new JTable();
        String col_for_Role_Selected_CurrentUser[] = {"Allocated Role"};
        String data_for_Role_Selected_CurrentUser[][] = {{""}};
        tablemodel_AllocatedRole_CurrentUser = new DefaultTableModel(
                data_for_Role_Selected_CurrentUser,
                col_for_Role_Selected_CurrentUser) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tblAllocatedRole_CurrentUser
                .setModel(tablemodel_AllocatedRole_CurrentUser);
        JTableHeader header_for_Selected_role = tblAllocatedRole_CurrentUser
                .getTableHeader();
        header_for_Selected_role.setBackground(Color.yellow);
        tablemodel_AllocatedRole_CurrentUser.setRowCount(0);
        tablemodel_AllocatedRole_CurrentUser.setColumnCount(1);
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
            lbl_Current_User_Role.setText(role);
            //Set Data to table Selected role
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
        NUButtonCreateUser1.setMnemonic(KeyEvent.VK_C);
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
        passedTable.getColumnModel().getColumn(0).setMinWidth(250);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(250);

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

//	int user_Type = 0;
//	if (radio_User_Type_Admin_1.isSelected()) {
//	    user_Type = 1;
//	} else if (radio_User_Type_NormalUser1.isSelected()) {
//	    user_Type = 2;
//	}
//	userSettingDTO.setUser_Type(user_Type);
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
    }

    // ///////////////////// Current User Call
    // End/////////////////////////////////////////////////////////////
    private String getInstalledServerPath() {
        FindregeditEntry o = new FindregeditEntry();
//        String readRegistryEntry = o.read();
        installedServerPath = o.read();
        return installedServerPath;
    }

    // ///////////////////// Current User Call
    // End/////////////////////////////////////////////////////////////
    private void initialiseNetworkSettings() {

        try {

            // TODO add your handling code here:
            TestSMSDTO testSMSDTO = TestSMS.getSavedSettings();
            String chSMS = TestSMS.loadNetworkSMSSettings();
            String chEmail = TestEmailSender.loadNetworkEMailSettings();

            if (testSMSDTO.getName().equalsIgnoreCase("1")) {

                jCheckBoxStockItemName.setSelected(true);

            }
            if (testSMSDTO.getQuantity().equalsIgnoreCase("1")) {

                jCheckBoxQuantity.setSelected(true);

            }
            if (testSMSDTO.getAmount().equalsIgnoreCase("1")) {

                jCheckBoxAmount.setSelected(true);

            }
            if (chSMS.equalsIgnoreCase("1")) {

                jCheckBoxEnableSMSSend.setSelected(true);

            }
            if (chSMS.equalsIgnoreCase("2")) {

                jCheckBoxDisableSMSSend.setSelected(true);

            }
            if (chSMS.equalsIgnoreCase("3")) {

                jCheckBoxEnableSMSSendWithAsk.setSelected(true);

            }
            if (chEmail.equalsIgnoreCase("1")) {

                jCheckBoxEnableEMail.setSelected(true);

            }
            if (chEmail.equalsIgnoreCase("2")) {

                jCheckBoxDisableEMail.setSelected(true);

            }
            if (chEmail.equalsIgnoreCase("3")) {

                jCheckBoxAskToSendEMail.setSelected(true);

            }
            String dir = System.getProperty("user.dir");
            String readLine = "";
            FileReader fileRead = new FileReader(dir
                    + "\\others\\NetworkEMailSender.properties");
            BufferedReader bufferedRead = new BufferedReader(fileRead);

            String readEMail = "";
            int lineCounting = 1;
            while ((readLine = bufferedRead.readLine()) != null) {
                System.out.println("Reading NetworkEMailSettings readLine-->>>"
                        + readLine);
                if (lineCounting == 1) {
                    readEMail = readLine;
                    System.out.println("readEMail---->>" + readEMail);
                }
                lineCounting++;
            }
            bufferedRead.close();
//            jTextFieldSender.setText("" + readEMail);
            textField_1.setText("" + readEMail);

            FileReader fileReadNew = new FileReader(dir
                    + "\\others\\NetworkEMailPWD.properties");
            BufferedReader bufferedReadNew = new BufferedReader(fileReadNew);

            String readEMailPassword = "";
            int lineCountingCheck = 1;
            while ((readLine = bufferedReadNew.readLine()) != null) {
                System.out.println("Reading NetworkEMailSettings readLine-->>>"
                        + readLine);
                if (lineCountingCheck == 1) {
                    readEMailPassword = readLine;
                    System.out.println("readEMailPassword---->>"
                            + readEMailPassword);
                }
                lineCountingCheck++;
            }
            bufferedReadNew.close();
//            jPasswordFieldPassword.setText("" + readEMailPassword);
            passwordField.setText("" + readEMailPassword);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName()).log(
                    Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdSuMuDiSettingsEclipseGUI.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

    }

    public void initialiseGraphicsSettings() {

        set_Value_CheckBox();
        buttonApply.setMnemonic(KeyEvent.VK_Y);
        buttonExit.setMnemonic(KeyEvent.VK_E);

        final String dir = System.getProperty("user.dir");
        String fileName = dir + "\\others\\System.properties";
        System.out.println("Path-->>>Today-->>>" + fileName);
        // String fileName =
        // "C:\\Users\\pc5\\Desktop\\Sudeep IMP FILES\\SystemProperties\\System.properties";
        String line = null;
        int lineNo;
        String oldText = "";
        String stringValueOfTheme = "";

        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            LineNumberReader totalNumberOfLines = new LineNumberReader(
                    new FileReader(new File(fileName)));
            totalNumberOfLines.skip(Long.MAX_VALUE);
            System.out
                    .println("Total-->>" + totalNumberOfLines.getLineNumber());
            for (lineNo = 1; lineNo <= totalNumberOfLines.getLineNumber(); lineNo++) {
                if (lineNo == 1) {
                    line = bufferedReader.readLine();
                    System.out.println(line);
                    stringValueOfTheme = line.substring(8, 10);
                    System.out.println("SubString-->>" + stringValueOfTheme);
                    oldText += line + "\r\n";
                }
            }
            bufferedReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }

        String selected_Theme = "";
        if (stringValueOfTheme.equalsIgnoreCase("01")) {
            texture.setSelected(true);
            selected_Theme = "texture";
        } else if (stringValueOfTheme.equalsIgnoreCase("02")) {
            aero.setSelected(true);
            selected_Theme = "aero";
        } else if (stringValueOfTheme.equalsIgnoreCase("03")) {
            aluminium.setSelected(true);
            selected_Theme = "aluminium";
        } else if (stringValueOfTheme.equalsIgnoreCase("04")) {
            macwin.setSelected(true);
            selected_Theme = "macwin";
        } else if (stringValueOfTheme.equalsIgnoreCase("05")) {
            luna.setSelected(true);
            selected_Theme = "luna";
        } else if (stringValueOfTheme.equalsIgnoreCase("06")) {
            acryl.setSelected(true);
            selected_Theme = "acryl";
        } else if (stringValueOfTheme.equalsIgnoreCase("07")) {
            mint.setSelected(true);
            selected_Theme = "mint";
        } else if (stringValueOfTheme.equalsIgnoreCase("08")) {
            fast.setSelected(true);
            selected_Theme = "fast";
        } else if (stringValueOfTheme.equalsIgnoreCase("09")) {
            graphite.setSelected(true);
            selected_Theme = "graphite";
        } else if (stringValueOfTheme.equalsIgnoreCase("10")) {
            smart.setSelected(true);
            selected_Theme = "smart";
        }

        Image img = new javax.swing.ImageIcon(dir + "/images/" + selected_Theme
                + ".png").getImage();
        img = img.getScaledInstance(jPanelPreview.getWidth(),
                jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(img);
        jPanelPreview.setIcon(scaledImageIcon);

    }

    public void initialiseDeviceAndHardwareSettings() {

        printSettingsInitilise();
        ResultSet rs;
        Connection conn = null;
        try {
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            rs = st.executeQuery("select * from  tblothersetting");
            if (rs.next()) {
                System.out
                        .println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR     ---------------");
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
            conn.close();
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "User Name Data Exceeding "
                    + Constants.jTextFieldCharacterLengthMEDIUM
                    + " Character Limit");
            txt_Current_User_Name.requestFocus();
        } else if (txt_Current_User_Password.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthMEDIUM) {
            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "User Password Data Exceeding "
                    + Constants.jTextFieldCharacterLengthMEDIUM
                    + " Character Limit");
            txt_Current_User_Password.requestFocus();
        } else if (txt_Current_User_ConfirmPassword.getText().trim()
                .toCharArray().length > Constants.jTextFieldCharacterLengthMEDIUM) {
            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "User Confirm Password Data Exceeding "
                    + Constants.jTextFieldCharacterLengthMEDIUM
                    + " Character Limit");
            txt_Current_User_ConfirmPassword.requestFocus();
        } else if (txt_Current_User_EmailId.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthSMALL) {
            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "User Email-ID Data Exceeding "
                    + Constants.jTextFieldCharacterLengthSMALL
                    + " Character Limit");
            txt_Current_User_EmailId.requestFocus();
        } else if (txt_Current_User_Email_Password.getText().trim()
                .toCharArray().length > Constants.jTextFieldCharacterLengthSMALL) {
            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "User Email-ID Password Data Exceeding "
                    + Constants.jTextFieldCharacterLengthSMALL
                    + " Character Limit");
            txt_Current_User_Email_Password.requestFocus();
        } else // if (txtUser_Name1.getText().trim().toCharArray().length >
        // Constants.jTextFieldCharacterLengthMEDIUM) {
        //            flag = false;
        // JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
        // "User Name Data Exceeding " +
        // Constants.jTextFieldCharacterLengthMEDIUM + " Character Limit");
        //            txtUser_Name1.requestFocus();
        // } else if (txtUser_Password1.getText().trim().toCharArray().length >
        // Constants.jTextFieldCharacterLengthMEDIUM) {
        //            flag = false;
        // JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
        // "User Password Data Exceeding " +
        // Constants.jTextFieldCharacterLengthMEDIUM + " Character Limit");
        //            txtUser_Password1.requestFocus();
        // } else if
        // (txtUser_ConfirmPassword1.getText().trim().toCharArray().length >
        // Constants.jTextFieldCharacterLengthMEDIUM) {
        //            flag = false;
        // JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
        // "User Confirm Password Data Exceeding " +
        // Constants.jTextFieldCharacterLengthMEDIUM + " Character Limit");
        //            txtUser_ConfirmPassword1.requestFocus();
        // } else if (txtUser_EmailId1.getText().trim().toCharArray().length >
        // Constants.jTextFieldCharacterLengthSMALL) {
        //            flag = false;
        // JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
        // "User Email-ID Data Exceeding " +
        // Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
        //            txtUser_EmailId1.requestFocus();
        // } else if
        // (txtUser_Email_Password1.getText().trim().toCharArray().length >
        // Constants.jTextFieldCharacterLengthSMALL) {
        //            flag = false;
        // JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
        // "User Email-ID Password Data Exceeding " +
        // Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
        //            txtUser_Email_Password1.requestFocus();
        //        } else 
        if (!m.find()) {
            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "Enter Valid UserName");
            txt_Current_User_Name.requestFocus();
        } else if (txt_Current_User_Name.getText().toString().trim().equals("")) {

            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "Enter UserName");
            txt_Current_User_Name.requestFocus();

        } else if (txt_Current_User_Password.getText().toString().trim()
                .equals("")) {

            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "Enter Password");
            txt_Current_User_Password.requestFocus();

        } else if (!txt_Current_User_Password
                .getText()
                .toString()
                .trim()
                .equals(txt_Current_User_ConfirmPassword.getText().toString()
                .trim())) {

            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "Password MisMatch");
            txt_Current_User_Password.requestFocus();

        } else if ((!matcher.matches() && !txt_Current_User_EmailId.getText()
                .toString().trim().equals(""))) {

            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "Enter Valid Email ID");
            txt_Current_User_EmailId.requestFocus();

        }
//         else if
        // (txt_Current_User_EmailId.getText().toString().trim().equals("")) {
//
//	    if (!txtUser_Email_Password1.toString().trim().isEmpty()) {
//		flag = false;
        // JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
        // "First Enter Email ID");
//		txt_Current_User_EmailId.requestFocus();
//	    }
//
        // // } else if
        // (!mapRoleandID.containsKey(tf_Role_User_Settings.getText().toLowerCase().toString()))
        // {
//	}
//	
        // else if
        // (!txt_Current_User_Name.getText().toString().trim().equals("")) {
        // for (UserSettingDTO usersettingDTO :
        // user_SettingsDTOList_Update_User_Settings) {
        // if
        // (usersettingDTO.getUser_Name().equals(txt_Current_User_Name.getText().toString().trim()))
        // {
//                    flag = false;
        // JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
        // "Already User present With this User Name");
//                    txt_Current_User_Name.requestFocus();
//                    break;
//                }
//            }
//        }

        int count = 0;
        for (UserSettingDTO usersettingDTO : user_SettingsDTOList_Update_User_Settings) {
            if (usersettingDTO.getUser_Name().equals(
                    txt_Current_User_Name.getText().toString().trim())) {
                //flag = false;
                // JOptionPane.showMessageDialog(Currentuser.this,
                // "Already User present With this User Name");
                //txt_Current_User_Name.requestFocus();
                count++;
//		break;
            }
        }

        if (count == 2) {
            flag = false;
            JOptionPane.showMessageDialog(AdSuMuDiSettingsEclipseGUI.this,
                    "Already User present With this User Name");
            txt_Current_User_Name.requestFocus();
        }

        return flag;
    }

    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:

        int seleted_company_Index = 0;
        seleted_company_Index = jTabbedPane2.getSelectedIndex();

        if (seleted_company_Index == 0) {
            intialise_Company_Settings();
        } else if (seleted_company_Index == 1) {
            initialise_Company_Back_UP();
        } else if (seleted_company_Index == 2) {
            initialise_Company_Restore();
        } else if (seleted_company_Index == 3) {
            initialise_Database_Spliting();
        } else if (seleted_company_Index == 4) {
            intialise_Company_Delete();
        }

        companySettingsDTO = gen.other.AdSuMuDiSettings.AdSuMuDiConstant
                .getCurrentCompany_Details("");
        ActivInactiveComponent();

    }

    private void jTabbedPane3MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
//Pending in Eclipse
        int seleted_User_Index = 0;
        seleted_User_Index = jTabbedPane2.getSelectedIndex();

        System.out
                .println("jTabbedPane2 --------- mouse clicked ---------------------------------");
        if (seleted_User_Index == 0) {
            intialise_User_Settings();
        } else if (seleted_User_Index == 1) {
            //  intialise_Current_User_Settings();
        } else if (seleted_User_Index == 2) {
            // initialise_Company_Restore();
        } else if (seleted_User_Index == 3) {
            // initialise_Database_Spliting();
        } else if (seleted_User_Index == 4) {
            // intialise_Company_Delete();
        }

        // companySettingsDTO =
        // gen.other.AdSuMuDiSettings.AdSuMuDiConstant.getCurrentCompany_Details();
        //ActivInactiveComponent();

    }

    private void btncompanyClear1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        txtNameOfGod1.setText("");
        txtCompanyName1.setText("");
        txtCompanyAddress1.setText("");
        txtCompanyAlias1.setText("");
        txtCompanyContact1.setText("");
        txtCompanyITN1.setText("");
        txtCompanyMail1.setText("");
        txtCompanySTN1.setText("");
        txtCompanyDeclaration1.setText("");
        txtCompanyTagLine1.setText("");
        txtCompanyCSTNo1.setText("");
        jtextFieldLBTNo.setText("");
        txtCompanyTermsConditions1.setText("");
        txtCompanySignature1.setText("");
        lblcompany_ID1.setText("ID");

        source = "";
        btnCompanyCreate1.setEnabled(true);
        btnUpdate1.setEnabled(false);
        btnDelete1.setEnabled(false);
        btnupdateLogo.setEnabled(false);

        LogoPanelView1.setIcon(null);
        LogoPanelView1.revalidate();

        txtCompanyName1.requestFocus();
    }

    private void deactivatingUnecessaryFeatures() {

        jTabbedPane2.remove(panel_14);
        jTabbedPane2.remove(panel_15);
        jTabbedPane2.remove(panel_16);

    }

    private Boolean validationReceiptNo() throws Exception {
        Boolean flag = true;
        if (jtextFieldReceiptNo.getText().toString().trim().equalsIgnoreCase("") && Double.parseDouble(jtextFieldReceiptNo.getText().toString().trim()) == 0D) {
            flag = false;
            throw new FieldValidationException(Label.INVALID_FIELD_EXCEPTION);
        } else if (jdateChooser_SelectYear.equals(null)) {
            flag = false;
            throw new FieldValidationException(Label.DATE_VALUE_IS_NOT_VALID);
        }
        return flag;
    }
}