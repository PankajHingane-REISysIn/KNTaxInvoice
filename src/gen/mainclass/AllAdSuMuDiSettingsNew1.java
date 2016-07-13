/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.mainclass;

import AdSuMuDiNetworks.TestEmailSender;
import AdSuMuDiNetworks.TestSMS;
import AdSuMuDiNetworks.TestSMSDTO;
import AdSuMuDiSecurity.SetWaitTimer;
import gen.account.ledger.LedgerAlterFromDetails;
import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.dto.Label;
import gen.dto.Util;
import gen.other.AdSuMuDiSettingsOLD.AdSuMuDiSettingsDAO;
import gen.other.CompanySettings.CompanySettingsDAO;
import gen.other.CompanySettings.CompanySettingsDTO;
import gen.other.CompanySettings.UnzipUtility;
import gen.other.DatabaseBackupRestore.Company_InformationDTO;
import gen.other.DatabaseBackupRestore.DatabaseBackUp1;
import gen.other.DatabaseBackupRestore.DatabaseRestore;
import gen.other.DatabaseSpiliting.DatabaseSpilting_old;
import gen.other.login.ChangePass;
import gen.other.login.Company.Create_Company;
import gen.other.login.NetworkSettings;
import gen.other.login.User.Currentuser;
import gen.other.print.PrinterSettings;
import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import org.apache.commons.io.IOUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import Schedular.CronTrigger;

/**
 *
 * @author pc5
 */
public class AllAdSuMuDiSettingsNew1 extends javax.swing.JInternalFrame {

    /**
     * Creates new form AllAdSuMuDiSettingsNew1
     */
    private static String installedServerPath = "";
    String choice = "";
    String SMSchoice = "";
    String EMailchoice = "";
    String stockItemName = "";
    String stockItemQuantity = "";
    String stockItemAmount = "";
    //String enableSMSSending = "";
    //String disableSMSSending = "";
    //String enableSMSSendingWithAsk = "";
    Map<String, String> map_Field_With_ID = new HashMap<String, String>();
    Map<String, String> map_checkBoxValue = new HashMap<String, String>();
    ResultSet resultSet;
    String q = "";
    JTextField tf;
    private boolean hide_flag = false;
    //ArrayList<String> GroupItems=new ArrayList<String>();
    //private final Vector<String> v = new Vector<String>();
    JTextField tfChangePassword = new JTextField();
    Vector<String> vChangePassword = new Vector<String>();
    JTextField tfChangeEMailSettings = new JTextField();
    Vector<String> vChangeEMailSettings = new Vector<String>();
    JTextField tfChangeUserRightSettings = new JTextField();
    Vector<String> vChangeUserRightSettings = new Vector<String>();
    public static int flagDirectPrint = 1;
    public static int flagPrintPageSize = 1;
    public static int flagPrintPageFormat = 1;
    byte[] b1;
    private String source = null;
    static int control = 0;
    // Company Selection variable 
    DefaultTableModel tableModelCompanyList_Company_Settings = new DefaultTableModel();
    public static String CURRENT_DATABASE_Company_Settings = "";
    public static String CURRENT_COMPANY_Company_Settings = "";
    private List<gen.other.CompanySettings.CompanySettingsDTO> companySettingsDTOList_Company_Settings = new ArrayList<gen.other.CompanySettings.CompanySettingsDTO>();
/////////////////////////// Company Back UP variables
    DefaultTableModel tablemodelCompany_Company_Back_UP, tablemodelBackUPCompany_Company_Back_UP;
    // company_list to store .SQL file
    List<String> company_SQLTxtFile_List_Company_Back_UP = new ArrayList<String>();
    //to store .Enc file
    List<String> company_List_Enc_Company_Back_UP = new ArrayList<String>();
    File fileChooser_Company_Back_UP = null;
    static int BUFFER_SIZE = 1048576;
    static String zipArchieveName_Company_Back_UP = "";
    // company_Information_DTOList call when Form open
    private List<CompanySettingsDTO> company_Information_DTOList_Company_Back_UP = new ArrayList<CompanySettingsDTO>();
    private static Cipher ecipher_Company_Back_UP;
    private static Cipher dcipher_Company_Back_UP;
    private static byte[] iv = {
        (byte) 0xB2, (byte) 0x12, (byte) 0xD5, (byte) 0xB2,
        (byte) 0x44, (byte) 0x21, (byte) 0xC3, (byte) 0xC3,
        (byte) 0xB2, (byte) 0x12, (byte) 0xD5, (byte) 0xB2,
        (byte) 0x44, (byte) 0x21, (byte) 0xC3, (byte) 0xC3,};
/////////////////////////// Company Restore variables
    DefaultTableModel tablemodelCompany_Company_Restore, tablemodelRestoreCompany_Company_Restore;
    List<String> company_List_Clicked_Company_Restore = new ArrayList<String>();
    List<CompanySettingsDTO> company_List_Restore_Company_Restore = new ArrayList<CompanySettingsDTO>();
    List<String> company_Database_List_Restore = new ArrayList<String>();
    List<String> company_SQLFile_List_Restore = new ArrayList<String>();
    //Map<String, String> map_Database_And_File_Name = new HashMap<String, String>();
    List<Company_InformationDTO> company_InformationDTOList_Company_Restore = new ArrayList<Company_InformationDTO>();
    List<Company_InformationDTO> company_InformationDTO_Final_List_Company_Restore = new ArrayList<Company_InformationDTO>();
    List<CompanySettingsDTO> company_Full_InformationDTOList_Company_Restore = new ArrayList<CompanySettingsDTO>();
    List<String> unZip_Company_File_List_Company_Restore = new ArrayList<String>();
    String path_Company_Restore = "";
    File fileChooser_Company_Restore = null;
    private static Cipher ecipher_Company_Restore;
    private static Cipher dcipher_Company_Restore;
///////////////////// Database Splitings
    List<CompanySettingsDTO> company_ListDTO_Database_Splitng = new ArrayList<CompanySettingsDTO>();
    private boolean hide_flag_Database_Splitng = false;
    private JTextField tfDatePicker_Database_Splitng = null;
    DefaultTableModel tablemodelCompany_Database_Splitng;
    private String selected_Company_Name_Database_Splitng = "";
    private String selected_Company_id_Database_Splitng = "";
///////////////////// Company Alter
    // company_Cliked_Name and company_Cliked_Database_Name --- use to alter data of main database & image present in company database of update company
    String database_Name_For_Newly_Company = "";
    String company_Cliked_Name = "";
    String company_Cliked_Database_Name = "";
    DefaultTableModel tableModelCompanyList_Company_Delete = new DefaultTableModel();
    private List<gen.other.CompanySettings.CompanySettingsDTO> companySettingsDTOList_Company_Delete = new ArrayList<gen.other.CompanySettings.CompanySettingsDTO>();
    gen.other.CompanySettings.CompanySettingsDTO companySettingsDTO = new gen.other.CompanySettings.CompanySettingsDTO();
//////////////////////////////// for timer
    gen.mainclass.SetWaitTimer settimer = new gen.mainclass.SetWaitTimer();
    final String dir = System.getProperty("user.dir");
    JDialog dialog = new JDialog();
    JOptionPane optionPane = new JOptionPane("", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
    // for use settings 
    DefaultTableModel tablemodel_Company_Available_User_Settings, tablemodel_Company_Selected_User_Settings, tablemodel_User_Details_Settings, tablemodel_AvailableRole_User_Settings, tablemodel_SelectedRole_User_Settings;
    private List<gen.other.CompanySettings.CompanySettingsDTO> companySettingsDTOList_User_Settings = new ArrayList<gen.other.CompanySettings.CompanySettingsDTO>();
    private List<gen.mainclass.UserSettingDTO> user_SettingsDTOList_User_Settings = new ArrayList<gen.mainclass.UserSettingDTO>();
    private boolean hide_flag_User_Settings = false;
//    private JTextField tf_Role_User_Settings = null;
    Map<String, String> mapRoleandID = new HashMap<String, String>();
    Vector<String> roleVector = new Vector<String>();
    Boolean flag_For_Component_enable_Disable_User_Settings = true;
    private UserSettingDTO userSettingDTO_Class_Level = new UserSettingDTO();
    private String user_Cliked_For_Update = "";
    private String user_ID_Delete = "";
    // for Current User
    DefaultTableModel tablemodel_Company_Allocated_Current_User_Settings, tablemodel_Current_Company_For_Current_User_Settings, tablemodel_AllocatedRole_CurrentUser;
    private Scheduler sch;
    private List<gen.mainclass.UserSettingDTO> user_SettingsDTOList_Update_User_Settings = new ArrayList<gen.mainclass.UserSettingDTO>();

    public AllAdSuMuDiSettingsNew1() {
        initComponents();
        this.setTitle("AdSuMuDiSettings                              " + " Company Name : " + gen.other.CompanySettings.CompanysSettings.CURRENT_COMPANY_Company_Settings);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        applicationSettingsPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        texture = new javax.swing.JCheckBox();
        aero = new javax.swing.JCheckBox();
        aluminium = new javax.swing.JCheckBox();
        macwin = new javax.swing.JCheckBox();
        luna = new javax.swing.JCheckBox();
        acryl = new javax.swing.JCheckBox();
        mint = new javax.swing.JCheckBox();
        fast = new javax.swing.JCheckBox();
        graphite = new javax.swing.JCheckBox();
        smart = new javax.swing.JCheckBox();
        buttonApply = new javax.swing.JButton();
        buttonExit = new javax.swing.JButton();
        jPanelPreview = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldPaginationValue = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jCheckBoxStockItemName = new javax.swing.JCheckBox();
        jCheckBoxQuantity = new javax.swing.JCheckBox();
        jCheckBoxAmount = new javax.swing.JCheckBox();
        jButtonParticularOK = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jCheckBoxEnableSMSSend = new javax.swing.JCheckBox();
        jCheckBoxDisableSMSSend = new javax.swing.JCheckBox();
        jCheckBoxEnableSMSSendWithAsk = new javax.swing.JCheckBox();
        jButtonEnableDisableOK = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jCheckBoxEnableEMail = new javax.swing.JCheckBox();
        jCheckBoxDisableEMail = new javax.swing.JCheckBox();
        jButtonEMailOK2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldSender = new javax.swing.JTextField();
        jPasswordFieldPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jButtonApply = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        optYes = new javax.swing.JRadioButton();
        optNo = new javax.swing.JRadioButton();
        jPanel16 = new javax.swing.JPanel();
        optPdf = new javax.swing.JRadioButton();
        optHtml = new javax.swing.JRadioButton();
        optWord = new javax.swing.JRadioButton();
        optExcel = new javax.swing.JRadioButton();
        jPanel17 = new javax.swing.JPanel();
        optA4 = new javax.swing.JRadioButton();
        optA5 = new javax.swing.JRadioButton();
        optAsk = new javax.swing.JRadioButton();
        btnSave = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel20 = new javax.swing.JPanel();
        applicationSettingsPanel1 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        buttonApply_Company_Settings = new javax.swing.JButton();
        buttonExit_Company_Settings = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablecompanyList_Company_Settings = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableCompanyBackUp_Company_Back_UP = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tableCompanyList_Company_Back_UP = new javax.swing.JTable();
        btnBackUP_Company_Back_UP = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        txtDestinationPath_Company_Back_UP = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        btnPath_Company_Back_UP = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tableCompanyRestoreUp_Company_Restore = new javax.swing.JTable();
        btnRestore_Company_Restore = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        txtSourcePath_Company_Restore = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        buttonFilePath_Company_Restore = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tableCompanyList_Company_Restore = new javax.swing.JTable();
        jPanel23 = new javax.swing.JPanel();
        Panel_Database_Splitng = new javax.swing.JPanel();
        fromJDateChooser_Database_Splitng = new com.toedter.calendar.JDateChooser();
        jScrollPane11 = new javax.swing.JScrollPane();
        tableCompanyList_Database_Splitng = new javax.swing.JTable();
        btnDataSpliting_Database_Splitng = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        applicationSettingsPanel2 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        buttonExit_Company_Delete = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        tablecompanyList_Company_Delete = new javax.swing.JTable();
        jPanel27 = new javax.swing.JPanel();
        txtNameOfGod1 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        txtCompanyName1 = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtCompanyAlias1 = new javax.swing.JTextField();
        jScrollPane13 = new javax.swing.JScrollPane();
        txtCompanyTagLine1 = new javax.swing.JTextArea();
        jScrollPane14 = new javax.swing.JScrollPane();
        txtCompanyAddress1 = new javax.swing.JTextArea();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtCompanyContact1 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        txtCompanyMail1 = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        txtCompanyITN1 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        txtCompanySTN1 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        txtCompanyCSTNo1 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        txtCompanyDeclaration1 = new javax.swing.JTextArea();
        jLabel56 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        txtCompanyTermsConditions1 = new javax.swing.JTextArea();
        jLabel57 = new javax.swing.JLabel();
        txtCompanySignature1 = new javax.swing.JTextField();
        btncompanyClear1 = new javax.swing.JButton();
        btnCompanyCreate1 = new javax.swing.JButton();
        btnUpdate1 = new javax.swing.JButton();
        btnDelete1 = new javax.swing.JButton();
        lblcompany_ID1 = new javax.swing.JLabel();
        LogoPanelView1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel28 = new javax.swing.JPanel();
        createUserPanel1 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tblUser_Details_for_Admin = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        txtUser_Name1 = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        txtUser_Password1 = new javax.swing.JPasswordField();
        jLabel61 = new javax.swing.JLabel();
        txtUser_ConfirmPassword1 = new javax.swing.JPasswordField();
        jLabel64 = new javax.swing.JLabel();
        txtUser_EmailId1 = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        txtUser_Email_Password1 = new javax.swing.JPasswordField();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        radio_User_Type_Admin_1 = new javax.swing.JRadioButton();
        radio_User_Type_NormalUser1 = new javax.swing.JRadioButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblUser_Available_Company = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tblUser_Selected_Company = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        NUButtonBack1 = new javax.swing.JButton();
        NUButtonCreateUser1 = new javax.swing.JButton();
        btnNewUser1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblavailableRole = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblselectedRole = new javax.swing.JTable();
        NUButtonDeleteUser1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        createUserPanel3 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        txt_Current_User_Name = new javax.swing.JTextField();
        txt_Current_User_Password = new javax.swing.JPasswordField();
        txt_Current_User_ConfirmPassword = new javax.swing.JPasswordField();
        txt_Current_User_EmailId = new javax.swing.JTextField();
        txt_Current_User_Email_Password = new javax.swing.JPasswordField();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        lbl_Current_User_Role = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        radio_User_Type_Admin_3 = new javax.swing.JRadioButton();
        radio_User_Type_NormalUser3 = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        tblCompany_Allocated_Current_User_Settings = new javax.swing.JTable();
        jScrollPane23 = new javax.swing.JScrollPane();
        tblCurrent_Company_For_Current_User_Settings = new javax.swing.JTable();
        NUButtonBack3 = new javax.swing.JButton();
        NUButtonCreateUser3 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAllocatedRole_CurrentUser = new javax.swing.JTable();
        jButtonStart = new javax.swing.JButton();
        jButtonStop = new javax.swing.JButton();

        setClosable(true);
        setTitle(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.title")); // NOI18N
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        applicationSettingsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(texture, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.texture.text")); // NOI18N
        texture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textureMouseClicked(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(aero, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.aero.text")); // NOI18N
        aero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aeroMouseClicked(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(aluminium, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.aluminium.text")); // NOI18N
        aluminium.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aluminiumMouseClicked(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(macwin, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.macwin.text")); // NOI18N
        macwin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                macwinMouseClicked(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(luna, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.luna.text")); // NOI18N
        luna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lunaMouseClicked(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(acryl, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.acryl.text")); // NOI18N
        acryl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acrylMouseClicked(evt);
            }
        });
        acryl.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                acrylStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(mint, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.mint.text")); // NOI18N
        mint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mintMouseClicked(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(fast, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.fast.text")); // NOI18N
        fast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fastMouseClicked(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(graphite, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.graphite.text")); // NOI18N
        graphite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                graphiteMouseClicked(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(smart, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.smart.text")); // NOI18N
        smart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                smartMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(macwin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(aluminium, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addComponent(aero, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(texture, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(luna, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(graphite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(smart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(acryl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 325, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texture)
                    .addComponent(acryl))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aero)
                    .addComponent(mint))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aluminium)
                    .addComponent(fast))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(macwin)
                    .addComponent(graphite))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(luna)
                    .addComponent(smart)))
        );

        org.openide.awt.Mnemonics.setLocalizedText(buttonApply, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.buttonApply.text")); // NOI18N
        buttonApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonApplyActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(buttonExit, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.buttonExit.text")); // NOI18N
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jPanelPreview, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jPanelPreview.text")); // NOI18N
        jPanelPreview.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel66, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel66.text")); // NOI18N

        javax.swing.GroupLayout applicationSettingsPanelLayout = new javax.swing.GroupLayout(applicationSettingsPanel);
        applicationSettingsPanel.setLayout(applicationSettingsPanelLayout);
        applicationSettingsPanelLayout.setHorizontalGroup(
            applicationSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(applicationSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(applicationSettingsPanelLayout.createSequentialGroup()
                .addGroup(applicationSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(applicationSettingsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(applicationSettingsPanelLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jPanelPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(applicationSettingsPanelLayout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(buttonApply)
                        .addGap(42, 42, 42)
                        .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, applicationSettingsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel66)
                .addGap(238, 238, 238))
        );
        applicationSettingsPanelLayout.setVerticalGroup(
            applicationSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(applicationSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel66)
                .addGroup(applicationSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(applicationSettingsPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 77, Short.MAX_VALUE))
                    .addGroup(applicationSettingsPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(applicationSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonApply)
                            .addComponent(buttonExit))
                        .addGap(28, 28, 28))))
        );

        jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel6.text")); // NOI18N

        jTextFieldPaginationValue.setText(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jTextFieldPaginationValue.text")); // NOI18N
        jTextFieldPaginationValue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPaginationValueKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldPaginationValue, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(55, 55, 55))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldPaginationValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(applicationSettingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(533, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(applicationSettingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel3.text")); // NOI18N

        jPanel6.setPreferredSize(new java.awt.Dimension(220, 220));

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxStockItemName, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jCheckBoxStockItemName.text")); // NOI18N
        jCheckBoxStockItemName.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxStockItemNameItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxQuantity, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jCheckBoxQuantity.text")); // NOI18N
        jCheckBoxQuantity.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxQuantityItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxAmount, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jCheckBoxAmount.text")); // NOI18N
        jCheckBoxAmount.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxAmountItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonParticularOK, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jButtonParticularOK.text")); // NOI18N
        jButtonParticularOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonParticularOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jCheckBoxQuantity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBoxStockItemName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(jCheckBoxAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jButtonParticularOK)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jCheckBoxStockItemName)
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxQuantity)
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxAmount)
                .addGap(18, 18, 18)
                .addComponent(jButtonParticularOK)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel7.setPreferredSize(new java.awt.Dimension(220, 220));

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxEnableSMSSend, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jCheckBoxEnableSMSSend.text")); // NOI18N
        jCheckBoxEnableSMSSend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxEnableSMSSendMouseClicked(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxDisableSMSSend, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jCheckBoxDisableSMSSend.text")); // NOI18N
        jCheckBoxDisableSMSSend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxDisableSMSSendMouseClicked(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxEnableSMSSendWithAsk, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jCheckBoxEnableSMSSendWithAsk.text")); // NOI18N
        jCheckBoxEnableSMSSendWithAsk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxEnableSMSSendWithAskMouseClicked(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonEnableDisableOK, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jButtonEnableDisableOK.text")); // NOI18N
        jButtonEnableDisableOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnableDisableOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonEnableDisableOK)
                .addGap(84, 84, 84))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCheckBoxEnableSMSSendWithAsk, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(jCheckBoxDisableSMSSend, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(jCheckBoxEnableSMSSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jCheckBoxEnableSMSSend)
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxDisableSMSSend)
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxEnableSMSSendWithAsk)
                .addGap(18, 18, 18)
                .addComponent(jButtonEnableDisableOK)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(218, 218, 218)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxEnableEMail, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jCheckBoxEnableEMail.text")); // NOI18N
        jCheckBoxEnableEMail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxEnableEMailMouseClicked(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxDisableEMail, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jCheckBoxDisableEMail.text")); // NOI18N
        jCheckBoxDisableEMail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxDisableEMailMouseClicked(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonEMailOK2, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jButtonEMailOK2.text")); // NOI18N
        jButtonEMailOK2.setDoubleBuffered(true);
        jButtonEMailOK2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEMailOK2ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel5.text")); // NOI18N

        jTextFieldSender.setText(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jTextFieldSender.text")); // NOI18N

        jPasswordFieldPassword.setText(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jPasswordFieldPassword.text")); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxEnableEMail)
                    .addComponent(jCheckBoxDisableEMail))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldSender)
                            .addComponent(jPasswordFieldPassword))
                        .addGap(30, 30, 30))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jButtonEMailOK2)
                        .addContainerGap(205, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(140, 140, 140))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxEnableEMail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxDisableEMail))
                .addGap(18, 18, 18)
                .addComponent(jButtonEMailOK2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(209, 209, 209))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jButtonApply, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jButtonApply.text")); // NOI18N
        jButtonApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApplyActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonExit, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jButtonExit.text")); // NOI18N
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonApply, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(272, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(156, 156, 156)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonExit)
                    .addComponent(jButtonApply))
                .addGap(46, 46, 46))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jPanel15.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(optYes, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.optYes.text")); // NOI18N
        optYes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optYesStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(optNo, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.optNo.text")); // NOI18N
        optNo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optNoStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(optYes)
                .addGap(26, 26, 26)
                .addComponent(optNo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optYes)
                    .addComponent(optNo))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jPanel16.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(optPdf, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.optPdf.text")); // NOI18N
        optPdf.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optPdfStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(optHtml, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.optHtml.text")); // NOI18N
        optHtml.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optHtmlStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(optWord, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.optWord.text")); // NOI18N
        optWord.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optWordStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(optExcel, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.optExcel.text")); // NOI18N
        optExcel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optExcelStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(optPdf)
                .addGap(18, 18, 18)
                .addComponent(optHtml)
                .addGap(18, 18, 18)
                .addComponent(optWord)
                .addGap(18, 18, 18)
                .addComponent(optExcel)
                .addGap(23, 23, 23))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optPdf)
                    .addComponent(optHtml)
                    .addComponent(optWord)
                    .addComponent(optExcel))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jPanel17.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(optA4, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.optA4.text")); // NOI18N
        optA4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optA4StateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(optA5, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.optA5.text")); // NOI18N
        optA5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optA5StateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(optAsk, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.optAsk.text")); // NOI18N
        optAsk.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optAskStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(optA4)
                .addGap(34, 34, 34)
                .addComponent(optA5)
                .addGap(50, 50, 50)
                .addComponent(optAsk)
                .addGap(26, 26, 26))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optA4)
                    .addComponent(optA5)
                    .addComponent(optAsk))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(btnSave, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.btnSave.text")); // NOI18N
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnBack, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.btnBack.text")); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(btnSave)
                        .addGap(57, 57, 57)
                        .addComponent(btnBack)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnBack))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(851, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(172, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jPanel12.TabConstraints.tabTitle"), jPanel12); // NOI18N

        jTabbedPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane2MouseClicked(evt);
            }
        });
        jTabbedPane2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane2FocusGained(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel40, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel40.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(buttonApply_Company_Settings, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.buttonApply_Company_Settings.text")); // NOI18N
        buttonApply_Company_Settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonApply_Company_SettingsActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(buttonExit_Company_Settings, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.buttonExit_Company_Settings.text")); // NOI18N
        buttonExit_Company_Settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExit_Company_SettingsActionPerformed(evt);
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
                "", "", "", ""
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
        jScrollPane6.setViewportView(tablecompanyList_Company_Settings);

        javax.swing.GroupLayout applicationSettingsPanel1Layout = new javax.swing.GroupLayout(applicationSettingsPanel1);
        applicationSettingsPanel1.setLayout(applicationSettingsPanel1Layout);
        applicationSettingsPanel1Layout.setHorizontalGroup(
            applicationSettingsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(applicationSettingsPanel1Layout.createSequentialGroup()
                .addGroup(applicationSettingsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(applicationSettingsPanel1Layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addGroup(applicationSettingsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40)))
                    .addGroup(applicationSettingsPanel1Layout.createSequentialGroup()
                        .addGap(344, 344, 344)
                        .addComponent(buttonApply_Company_Settings)
                        .addGap(36, 36, 36)
                        .addComponent(buttonExit_Company_Settings, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(695, Short.MAX_VALUE))
        );
        applicationSettingsPanel1Layout.setVerticalGroup(
            applicationSettingsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(applicationSettingsPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(applicationSettingsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonApply_Company_Settings)
                    .addComponent(buttonExit_Company_Settings))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(applicationSettingsPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(applicationSettingsPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jPanel20.TabConstraints.tabTitle"), jPanel20); // NOI18N

        tableCompanyBackUp_Company_Back_UP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "", "", ""
            }
        ));
        tableCompanyBackUp_Company_Back_UP.getTableHeader().setResizingAllowed(false);
        tableCompanyBackUp_Company_Back_UP.getTableHeader().setReorderingAllowed(false);
        tableCompanyBackUp_Company_Back_UP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCompanyBackUp_Company_Back_UPMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tableCompanyBackUp_Company_Back_UP);

        tableCompanyList_Company_Back_UP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                ""
            }
        ));
        tableCompanyList_Company_Back_UP.getTableHeader().setResizingAllowed(false);
        tableCompanyList_Company_Back_UP.getTableHeader().setReorderingAllowed(false);
        tableCompanyList_Company_Back_UP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCompanyList_Company_Back_UPMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tableCompanyList_Company_Back_UP);

        org.openide.awt.Mnemonics.setLocalizedText(btnBackUP_Company_Back_UP, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.btnBackUP_Company_Back_UP.text")); // NOI18N
        btnBackUP_Company_Back_UP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackUP_Company_Back_UPActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel41, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel41.text")); // NOI18N

        txtDestinationPath_Company_Back_UP.setEditable(false);
        txtDestinationPath_Company_Back_UP.setText(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.txtDestinationPath_Company_Back_UP.text")); // NOI18N
        txtDestinationPath_Company_Back_UP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDestinationPath_Company_Back_UPMouseClicked(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel42, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel42.text")); // NOI18N
        jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel42MouseClicked(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnPath_Company_Back_UP, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.btnPath_Company_Back_UP.text")); // NOI18N
        btnPath_Company_Back_UP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPath_Company_Back_UPMouseClicked(evt);
            }
        });
        btnPath_Company_Back_UP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPath_Company_Back_UPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDestinationPath_Company_Back_UP, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPath_Company_Back_UP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(77, 77, 77)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(352, 352, 352)
                        .addComponent(btnBackUP_Company_Back_UP, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(156, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDestinationPath_Company_Back_UP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(btnPath_Company_Back_UP)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBackUP_Company_Back_UP)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jPanel21.TabConstraints.tabTitle"), jPanel21); // NOI18N

        tableCompanyRestoreUp_Company_Restore.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "", "", ""
            }
        ));
        tableCompanyRestoreUp_Company_Restore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCompanyRestoreUp_Company_RestoreMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tableCompanyRestoreUp_Company_Restore);

        org.openide.awt.Mnemonics.setLocalizedText(btnRestore_Company_Restore, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.btnRestore_Company_Restore.text")); // NOI18N
        btnRestore_Company_Restore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestore_Company_RestoreActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel43, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel43.text")); // NOI18N

        txtSourcePath_Company_Restore.setEditable(false);
        txtSourcePath_Company_Restore.setText(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.txtSourcePath_Company_Restore.text")); // NOI18N
        txtSourcePath_Company_Restore.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSourcePath_Company_RestoreKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel44, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel44.text")); // NOI18N
        jLabel44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel44MouseClicked(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(buttonFilePath_Company_Restore, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.buttonFilePath_Company_Restore.text")); // NOI18N
        buttonFilePath_Company_Restore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonFilePath_Company_RestoreMouseClicked(evt);
            }
        });
        buttonFilePath_Company_Restore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFilePath_Company_RestoreActionPerformed(evt);
            }
        });

        tableCompanyList_Company_Restore.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "", "", ""
            }
        ));
        tableCompanyList_Company_Restore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCompanyList_Company_RestoreMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tableCompanyList_Company_Restore);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSourcePath_Company_Restore, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonFilePath_Company_Restore)
                        .addGap(78, 78, 78)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155))))
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(348, 348, 348)
                .addComponent(btnRestore_Company_Restore, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSourcePath_Company_Restore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonFilePath_Company_Restore)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRestore_Company_Restore)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jPanel22.TabConstraints.tabTitle"), jPanel22); // NOI18N

        Panel_Database_Splitng.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableCompanyList_Database_Splitng.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Company Name", ""
            }
        ));
        tableCompanyList_Database_Splitng.getTableHeader().setResizingAllowed(false);
        tableCompanyList_Database_Splitng.getTableHeader().setReorderingAllowed(false);
        tableCompanyList_Database_Splitng.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCompanyList_Database_SplitngMouseClicked(evt);
            }
        });
        tableCompanyList_Database_Splitng.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableCompanyList_Database_SplitngKeyPressed(evt);
            }
        });
        jScrollPane11.setViewportView(tableCompanyList_Database_Splitng);

        org.openide.awt.Mnemonics.setLocalizedText(btnDataSpliting_Database_Splitng, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.btnDataSpliting_Database_Splitng.text")); // NOI18N
        btnDataSpliting_Database_Splitng.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDataSpliting_Database_SplitngMouseClicked(evt);
            }
        });
        btnDataSpliting_Database_Splitng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataSpliting_Database_SplitngActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_Database_SplitngLayout = new javax.swing.GroupLayout(Panel_Database_Splitng);
        Panel_Database_Splitng.setLayout(Panel_Database_SplitngLayout);
        Panel_Database_SplitngLayout.setHorizontalGroup(
            Panel_Database_SplitngLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_Database_SplitngLayout.createSequentialGroup()
                .addGroup(Panel_Database_SplitngLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_Database_SplitngLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addGroup(Panel_Database_SplitngLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fromJDateChooser_Database_Splitng, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Panel_Database_SplitngLayout.createSequentialGroup()
                        .addGap(369, 369, 369)
                        .addComponent(btnDataSpliting_Database_Splitng, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(571, Short.MAX_VALUE))
        );
        Panel_Database_SplitngLayout.setVerticalGroup(
            Panel_Database_SplitngLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_Database_SplitngLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fromJDateChooser_Database_Splitng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDataSpliting_Database_Splitng)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(Panel_Database_Splitng, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_Database_Splitng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jPanel23.TabConstraints.tabTitle"), jPanel23); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel45, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel45.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(buttonExit_Company_Delete, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.buttonExit_Company_Delete.text")); // NOI18N
        buttonExit_Company_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExit_Company_DeleteActionPerformed(evt);
            }
        });

        tablecompanyList_Company_Delete.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "", "", ""
            }
        ));
        tablecompanyList_Company_Delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablecompanyList_Company_DeleteMouseClicked(evt);
            }
        });
        tablecompanyList_Company_Delete.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablecompanyList_Company_DeleteKeyPressed(evt);
            }
        });
        jScrollPane12.setViewportView(tablecompanyList_Company_Delete);

        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtNameOfGod1.setText(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.txtNameOfGod1.text")); // NOI18N
        txtNameOfGod1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNameOfGod1KeyPressed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel46, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel46.text")); // NOI18N

        txtCompanyName1.setText(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.txtCompanyName1.text")); // NOI18N
        txtCompanyName1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCompanyName1FocusGained(evt);
            }
        });
        txtCompanyName1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCompanyName1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCompanyName1KeyTyped(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel47, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel47.text")); // NOI18N

        jLabel48.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel48, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel48.text")); // NOI18N

        txtCompanyAlias1.setText(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.txtCompanyAlias1.text")); // NOI18N
        txtCompanyAlias1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCompanyAlias1FocusGained(evt);
            }
        });
        txtCompanyAlias1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCompanyAlias1KeyPressed(evt);
            }
        });

        txtCompanyTagLine1.setColumns(20);
        txtCompanyTagLine1.setRows(5);
        txtCompanyTagLine1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCompanyTagLine1FocusGained(evt);
            }
        });
        txtCompanyTagLine1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCompanyTagLine1KeyPressed(evt);
            }
        });
        jScrollPane13.setViewportView(txtCompanyTagLine1);

        txtCompanyAddress1.setColumns(20);
        txtCompanyAddress1.setRows(5);
        txtCompanyAddress1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCompanyAddress1FocusGained(evt);
            }
        });
        txtCompanyAddress1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCompanyAddress1KeyPressed(evt);
            }
        });
        jScrollPane14.setViewportView(txtCompanyAddress1);

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel49, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel49.text")); // NOI18N

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel50, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel50.text")); // NOI18N

        txtCompanyContact1.setText(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.txtCompanyContact1.text")); // NOI18N
        txtCompanyContact1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCompanyContact1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCompanyContact1KeyTyped(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel51, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel51.text")); // NOI18N

        txtCompanyMail1.setText(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.txtCompanyMail1.text")); // NOI18N
        txtCompanyMail1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCompanyMail1FocusGained(evt);
            }
        });
        txtCompanyMail1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCompanyMail1KeyPressed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel52, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel52.text")); // NOI18N

        txtCompanyITN1.setText(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.txtCompanyITN1.text")); // NOI18N
        txtCompanyITN1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCompanyITN1KeyPressed(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel53, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel53.text")); // NOI18N

        txtCompanySTN1.setText(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.txtCompanySTN1.text")); // NOI18N
        txtCompanySTN1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCompanySTN1KeyPressed(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel54, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel54.text")); // NOI18N

        txtCompanyCSTNo1.setText(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.txtCompanyCSTNo1.text")); // NOI18N
        txtCompanyCSTNo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCompanyCSTNo1KeyPressed(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel55, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel55.text")); // NOI18N

        txtCompanyDeclaration1.setColumns(20);
        txtCompanyDeclaration1.setRows(5);
        txtCompanyDeclaration1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCompanyDeclaration1FocusGained(evt);
            }
        });
        txtCompanyDeclaration1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCompanyDeclaration1KeyPressed(evt);
            }
        });
        jScrollPane15.setViewportView(txtCompanyDeclaration1);

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel56, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel56.text")); // NOI18N

        txtCompanyTermsConditions1.setColumns(20);
        txtCompanyTermsConditions1.setRows(5);
        txtCompanyTermsConditions1.setPreferredSize(new java.awt.Dimension(168, 94));
        txtCompanyTermsConditions1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCompanyTermsConditions1FocusGained(evt);
            }
        });
        txtCompanyTermsConditions1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCompanyTermsConditions1KeyPressed(evt);
            }
        });
        jScrollPane16.setViewportView(txtCompanyTermsConditions1);

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel57, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel57.text")); // NOI18N

        txtCompanySignature1.setText(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.txtCompanySignature1.text")); // NOI18N
        txtCompanySignature1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCompanySignature1FocusGained(evt);
            }
        });
        txtCompanySignature1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCompanySignature1KeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btncompanyClear1, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.btncompanyClear1.text")); // NOI18N
        btncompanyClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncompanyClear1ActionPerformed(evt);
            }
        });
        btncompanyClear1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btncompanyClear1KeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnCompanyCreate1, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.btnCompanyCreate1.text")); // NOI18N
        btnCompanyCreate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompanyCreate1ActionPerformed(evt);
            }
        });
        btnCompanyCreate1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCompanyCreate1KeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnUpdate1, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.btnUpdate1.text")); // NOI18N
        btnUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnDelete1, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.btnDelete1.text")); // NOI18N
        btnDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblcompany_ID1, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.lblcompany_ID1.text")); // NOI18N

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCompanyAlias1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addComponent(txtCompanyName1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblcompany_ID1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCompanyContact1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCompanyMail1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCompanyITN1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCompanySTN1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCompanyCSTNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCompanySignature1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNameOfGod1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel48)
                    .addComponent(jLabel49)
                    .addComponent(jLabel50)
                    .addComponent(jLabel51)
                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel27Layout.createSequentialGroup()
                            .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel52)
                                .addComponent(jLabel53)
                                .addComponent(jLabel54)
                                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel57))
                            .addGap(270, 270, 270))
                        .addGroup(jPanel27Layout.createSequentialGroup()
                            .addComponent(btncompanyClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCompanyCreate1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnUpdate1)
                            .addGap(32, 32, 32)
                            .addComponent(btnDelete1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNameOfGod1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel46))
                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCompanyName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblcompany_ID1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47)
                    .addComponent(txtCompanyAlias1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addGap(25, 25, 25))
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(txtCompanyContact1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(txtCompanyMail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(txtCompanyITN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(txtCompanySTN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(txtCompanyCSTNo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel55)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCompanySignature1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57)))
                    .addComponent(jLabel56))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncompanyClear1)
                    .addComponent(btnCompanyCreate1)
                    .addComponent(btnUpdate1)
                    .addComponent(btnDelete1))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(LogoPanelView1, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.LogoPanelView1.text")); // NOI18N
        LogoPanelView1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        org.openide.awt.Mnemonics.setLocalizedText(jButton3, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jButton3.text")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnClear, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.btnClear.text")); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout applicationSettingsPanel2Layout = new javax.swing.GroupLayout(applicationSettingsPanel2);
        applicationSettingsPanel2.setLayout(applicationSettingsPanel2Layout);
        applicationSettingsPanel2Layout.setHorizontalGroup(
            applicationSettingsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(applicationSettingsPanel2Layout.createSequentialGroup()
                .addGroup(applicationSettingsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(applicationSettingsPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addGroup(applicationSettingsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LogoPanelView1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(applicationSettingsPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(applicationSettingsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(48, 48, 48)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(applicationSettingsPanel2Layout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(buttonExit_Company_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        applicationSettingsPanel2Layout.setVerticalGroup(
            applicationSettingsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(applicationSettingsPanel2Layout.createSequentialGroup()
                .addComponent(LogoPanelView1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClear)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(applicationSettingsPanel2Layout.createSequentialGroup()
                .addGroup(applicationSettingsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel45)
                    .addComponent(jScrollPane12)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonExit_Company_Delete)
                .addGap(0, 21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(applicationSettingsPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(applicationSettingsPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jPanel24.TabConstraints.tabTitle"), jPanel24); // NOI18N

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jPanel19.TabConstraints.tabTitle"), jPanel19); // NOI18N

        jTabbedPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane3MouseClicked(evt);
            }
        });

        createUserPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        createUserPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createUserPanel1MouseClicked(evt);
            }
        });
        createUserPanel1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                createUserPanel1FocusLost(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel58, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel58.text")); // NOI18N

        tblUser_Details_for_Admin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "", "", ""
            }
        ));
        tblUser_Details_for_Admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUser_Details_for_AdminMouseClicked(evt);
            }
        });
        jScrollPane19.setViewportView(tblUser_Details_for_Admin);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtUser_Name1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtUser_Name1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUser_Name1FocusGained(evt);
            }
        });
        txtUser_Name1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUser_Name1KeyPressed(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel59, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel59.text")); // NOI18N

        jLabel60.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel60, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel60.text")); // NOI18N

        txtUser_Password1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtUser_Password1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUser_Password1FocusGained(evt);
            }
        });
        txtUser_Password1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUser_Password1KeyPressed(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel61, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel61.text")); // NOI18N

        txtUser_ConfirmPassword1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtUser_ConfirmPassword1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUser_ConfirmPassword1FocusGained(evt);
            }
        });
        txtUser_ConfirmPassword1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUser_ConfirmPassword1KeyPressed(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel64, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel64.text")); // NOI18N

        txtUser_EmailId1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtUser_EmailId1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUser_EmailId1FocusGained(evt);
            }
        });
        txtUser_EmailId1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUser_EmailId1KeyPressed(evt);
            }
        });

        jLabel65.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel65, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel65.text")); // NOI18N

        txtUser_Email_Password1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtUser_Email_Password1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUser_Email_Password1KeyPressed(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel62, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel62.text")); // NOI18N

        jLabel63.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel63, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel63.text")); // NOI18N

        radio_User_Type_Admin_1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(radio_User_Type_Admin_1, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.radio_User_Type_Admin_1.text")); // NOI18N
        radio_User_Type_Admin_1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radio_User_Type_Admin_1StateChanged(evt);
            }
        });
        radio_User_Type_Admin_1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                radio_User_Type_Admin_1KeyPressed(evt);
            }
        });

        radio_User_Type_NormalUser1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(radio_User_Type_NormalUser1, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.radio_User_Type_NormalUser1.text")); // NOI18N
        radio_User_Type_NormalUser1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radio_User_Type_NormalUser1StateChanged(evt);
            }
        });
        radio_User_Type_NormalUser1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                radio_User_Type_NormalUser1KeyPressed(evt);
            }
        });

        tblUser_Available_Company.setModel(new javax.swing.table.DefaultTableModel(
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
        tblUser_Available_Company.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUser_Available_CompanyMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblUser_Available_Company);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel7.text")); // NOI18N

        tblUser_Selected_Company.setModel(new javax.swing.table.DefaultTableModel(
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
        tblUser_Selected_Company.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUser_Selected_CompanyMouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(tblUser_Selected_Company);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel8.text")); // NOI18N

        NUButtonBack1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(NUButtonBack1, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.NUButtonBack1.text")); // NOI18N
        NUButtonBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NUButtonBack1ActionPerformed(evt);
            }
        });

        NUButtonCreateUser1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(NUButtonCreateUser1, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.NUButtonCreateUser1.text")); // NOI18N
        NUButtonCreateUser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NUButtonCreateUser1ActionPerformed(evt);
            }
        });

        btnNewUser1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnNewUser1, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.btnNewUser1.text")); // NOI18N
        btnNewUser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewUser1ActionPerformed(evt);
            }
        });

        tblavailableRole.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Available"
            }
        ));
        tblavailableRole.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblavailableRoleMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblavailableRole);

        tblselectedRole.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Selected Role"
            }
        ));
        tblselectedRole.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblselectedRoleMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblselectedRole);

        NUButtonDeleteUser1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(NUButtonDeleteUser1, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.NUButtonDeleteUser1.text")); // NOI18N
        NUButtonDeleteUser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NUButtonDeleteUser1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel63)
                            .addComponent(jLabel62)
                            .addComponent(jLabel65)
                            .addComponent(jLabel64)
                            .addComponent(jLabel61)
                            .addComponent(jLabel60)
                            .addComponent(jLabel59))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(radio_User_Type_Admin_1)
                                .addGap(18, 18, 18)
                                .addComponent(radio_User_Type_NormalUser1)
                                .addGap(0, 192, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtUser_ConfirmPassword1)
                                    .addComponent(jScrollPane18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(btnNewUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(NUButtonCreateUser1)
                                            .addGap(18, 18, 18)
                                            .addComponent(NUButtonBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(NUButtonDeleteUser1)))
                                    .addComponent(txtUser_Name1)
                                    .addComponent(txtUser_Password1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUser_EmailId1)
                                    .addComponent(txtUser_Email_Password1, javax.swing.GroupLayout.Alignment.LEADING))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUser_Name1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(txtUser_Password1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(txtUser_ConfirmPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(txtUser_EmailId1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUser_Email_Password1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel62)
                        .addGap(0, 72, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radio_User_Type_Admin_1)
                        .addComponent(radio_User_Type_NormalUser1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewUser1)
                    .addComponent(NUButtonCreateUser1)
                    .addComponent(NUButtonBack1)
                    .addComponent(NUButtonDeleteUser1)))
        );

        javax.swing.GroupLayout createUserPanel1Layout = new javax.swing.GroupLayout(createUserPanel1);
        createUserPanel1.setLayout(createUserPanel1Layout);
        createUserPanel1Layout.setHorizontalGroup(
            createUserPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createUserPanel1Layout.createSequentialGroup()
                .addGroup(createUserPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createUserPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(createUserPanel1Layout.createSequentialGroup()
                        .addGap(614, 614, 614)
                        .addComponent(jLabel58)))
                .addContainerGap(244, Short.MAX_VALUE))
        );
        createUserPanel1Layout.setVerticalGroup(
            createUserPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createUserPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(createUserPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane19)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(createUserPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(createUserPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jPanel28.TabConstraints.tabTitle"), jPanel28); // NOI18N

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jTabbedPane3.TabConstraints.tabTitle"), jTabbedPane3); // NOI18N

        createUserPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        createUserPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createUserPanel3MouseClicked(evt);
            }
        });
        createUserPanel3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                createUserPanel3FocusLost(evt);
            }
        });

        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel74.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel74, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel74.text")); // NOI18N

        txt_Current_User_Name.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txt_Current_User_Name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_Current_User_NameFocusGained(evt);
            }
        });
        txt_Current_User_Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_Current_User_NameKeyPressed(evt);
            }
        });

        txt_Current_User_Password.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txt_Current_User_Password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_Current_User_PasswordFocusGained(evt);
            }
        });
        txt_Current_User_Password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_Current_User_PasswordKeyPressed(evt);
            }
        });

        txt_Current_User_ConfirmPassword.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txt_Current_User_ConfirmPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_Current_User_ConfirmPasswordFocusGained(evt);
            }
        });
        txt_Current_User_ConfirmPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_Current_User_ConfirmPasswordKeyPressed(evt);
            }
        });

        txt_Current_User_EmailId.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txt_Current_User_EmailId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_Current_User_EmailIdFocusGained(evt);
            }
        });
        txt_Current_User_EmailId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_Current_User_EmailIdKeyPressed(evt);
            }
        });

        txt_Current_User_Email_Password.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        jLabel75.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel75, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel75.text")); // NOI18N

        jLabel76.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel76, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel76.text")); // NOI18N

        jLabel77.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel77, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel77.text")); // NOI18N

        jLabel80.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel80, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel80.text")); // NOI18N

        jLabel81.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel81, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel81.text")); // NOI18N

        lbl_Current_User_Role.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lbl_Current_User_Role, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.lbl_Current_User_Role.text")); // NOI18N

        jLabel78.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel78, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel78.text")); // NOI18N

        jLabel79.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel79, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel79.text")); // NOI18N

        radio_User_Type_Admin_3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(radio_User_Type_Admin_3, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.radio_User_Type_Admin_3.text")); // NOI18N
        radio_User_Type_Admin_3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radio_User_Type_Admin_3StateChanged(evt);
            }
        });
        radio_User_Type_Admin_3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                radio_User_Type_Admin_3KeyPressed(evt);
            }
        });

        radio_User_Type_NormalUser3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(radio_User_Type_NormalUser3, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.radio_User_Type_NormalUser3.text")); // NOI18N
        radio_User_Type_NormalUser3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radio_User_Type_NormalUser3StateChanged(evt);
            }
        });
        radio_User_Type_NormalUser3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                radio_User_Type_NormalUser3KeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel11.text")); // NOI18N

        tblCompany_Allocated_Current_User_Settings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CompanyName", "CompanyID"
            }
        ));
        tblCompany_Allocated_Current_User_Settings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCompany_Allocated_Current_User_SettingsMouseClicked(evt);
            }
        });
        jScrollPane22.setViewportView(tblCompany_Allocated_Current_User_Settings);

        tblCurrent_Company_For_Current_User_Settings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CompanyName", "CompanyID"
            }
        ));
        tblCurrent_Company_For_Current_User_Settings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCurrent_Company_For_Current_User_SettingsMouseClicked(evt);
            }
        });
        jScrollPane23.setViewportView(tblCurrent_Company_For_Current_User_Settings);

        NUButtonBack3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(NUButtonBack3, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.NUButtonBack3.text")); // NOI18N
        NUButtonBack3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NUButtonBack3ActionPerformed(evt);
            }
        });

        NUButtonCreateUser3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(NUButtonCreateUser3, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.NUButtonCreateUser3.text")); // NOI18N
        NUButtonCreateUser3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NUButtonCreateUser3ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jLabel12.text")); // NOI18N

        tblAllocatedRole_CurrentUser.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblAllocatedRole_CurrentUser);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel76)
                    .addComponent(jLabel75)
                    .addComponent(jLabel77)
                    .addComponent(jLabel80)
                    .addComponent(jLabel81)
                    .addComponent(jLabel78)
                    .addComponent(jLabel79)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Current_User_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Current_User_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Current_User_ConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Current_User_EmailId, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(txt_Current_User_Email_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(lbl_Current_User_Role)))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(radio_User_Type_Admin_3)
                                .addGap(37, 37, 37)
                                .addComponent(radio_User_Type_NormalUser3)
                                .addContainerGap())
                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel18Layout.createSequentialGroup()
                                            .addComponent(NUButtonCreateUser3)
                                            .addGap(18, 18, 18)
                                            .addComponent(NUButtonBack3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap())
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(210, 210, 210))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap()))))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel74)
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Current_User_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Current_User_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel76))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Current_User_ConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Current_User_EmailId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel80))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Current_User_Email_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel81)
                    .addComponent(lbl_Current_User_Role))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel78)
                        .addGap(0, 66, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel79)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radio_User_Type_Admin_3)
                            .addComponent(radio_User_Type_NormalUser3))
                        .addGap(9, 9, 9)))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NUButtonCreateUser3)
                            .addComponent(NUButtonBack3)))
                    .addComponent(jLabel11))
                .addContainerGap())
        );

        org.openide.awt.Mnemonics.setLocalizedText(jButtonStart, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jButtonStart.text")); // NOI18N
        jButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonStop, org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jButtonStop.text")); // NOI18N
        jButtonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout createUserPanel3Layout = new javax.swing.GroupLayout(createUserPanel3);
        createUserPanel3.setLayout(createUserPanel3Layout);
        createUserPanel3Layout.setHorizontalGroup(
            createUserPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createUserPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 320, Short.MAX_VALUE)
                .addComponent(jButtonStart)
                .addGap(41, 41, 41)
                .addComponent(jButtonStop)
                .addGap(165, 165, 165))
        );
        createUserPanel3Layout.setVerticalGroup(
            createUserPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createUserPanel3Layout.createSequentialGroup()
                .addGroup(createUserPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createUserPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(createUserPanel3Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(createUserPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonStop)
                            .addComponent(jButtonStart))))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(createUserPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(createUserPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(189, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(AllAdSuMuDiSettingsNew1.class, "AllAdSuMuDiSettingsNew1.jTabbedPane1.AccessibleContext.accessibleName")); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxStockItemNameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxStockItemNameItemStateChanged
        // TODO add your handling code here:
        if (jCheckBoxStockItemName.isSelected()) {

            stockItemName = "1";

        } else {

            stockItemName = "0";

        }
    }//GEN-LAST:event_jCheckBoxStockItemNameItemStateChanged

    private void jCheckBoxQuantityItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxQuantityItemStateChanged
        // TODO add your handling code here:
        if (jCheckBoxQuantity.isSelected()) {

            stockItemQuantity = "1";

        } else {

            stockItemQuantity = "0";

        }
    }//GEN-LAST:event_jCheckBoxQuantityItemStateChanged

    private void jCheckBoxAmountItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxAmountItemStateChanged
        // TODO add your handling code here:
        if (jCheckBoxAmount.isSelected()) {

            stockItemAmount = "1";

        } else {

            stockItemAmount = "0";

        }
    }//GEN-LAST:event_jCheckBoxAmountItemStateChanged

    private void jButtonParticularOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonParticularOKActionPerformed
        // TODO add your handling code here:
        FileWriter writer = null;
        try {
            //String fileName = "C:\\Users\\pc5\\Desktop\\Sudeep IMP FILES\\SystemProperties\\System.properties";
            final String dir = System.getProperty("user.dir");
            String fileName = dir + "\\others\\DataSMSSettings.properties";
            String line = null;
            String oldText = "";
            String oldText1 = "";
            String oldText2 = "";
            String stringValueOfStkItem = "";
            String stringValueOfQuantity = "";
            String stringValueOfAmount = "";

            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                if (line.startsWith("S")) {
                    stringValueOfStkItem = line.substring(16, 17);
                    System.out.println("stringValueOfStkItem-->>" + stringValueOfStkItem);
                    oldText += line + "\r\n";
                }
                if (line.startsWith("Q")) {
                    stringValueOfQuantity = line.substring(11, 12);
                    System.out.println("stringValueOfQuantity-->>" + stringValueOfQuantity);
                    oldText1 += line + "\r\n";
                }
                if (line.startsWith("A")) {
                    stringValueOfAmount = line.substring(9, 10);
                    System.out.println("stringValueOfAmount-->>" + stringValueOfAmount);
                    oldText2 += line + "\r\n";
                }
            }
            bufferedReader.close();

            String newText = "";
            if (stockItemName.equalsIgnoreCase("1")) {

                newText = oldText.replaceAll(stringValueOfStkItem, stockItemName);

            } else {

                newText = oldText.replaceAll(stringValueOfStkItem, "0");

            }

            String newText1 = "";
            if (stockItemQuantity.equalsIgnoreCase("1")) {

                newText1 = oldText1.replaceAll(stringValueOfQuantity, stockItemQuantity);

            } else {

                newText1 = oldText1.replaceAll(stringValueOfQuantity, "0");

            }

            String newText2 = "";
            if (stockItemAmount.equalsIgnoreCase("1")) {

                newText2 = oldText2.replaceAll(stringValueOfAmount, stockItemAmount);

            } else {

                newText2 = oldText2.replaceAll(stringValueOfAmount, "0");

            }

            //writer = new FileWriter("C:\\Users\\pc5\\Desktop\\Sudeep IMP FILES\\SystemProperties\\System.properties");
            writer = new FileWriter(fileName);
            writer.write(newText);
            writer.write(newText1);
            writer.write(newText2);
            writer.close();
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Data Sending SMS settings saved");
        } catch (IOException ex) {
            Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonParticularOKActionPerformed

    private void jCheckBoxEnableSMSSendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxEnableSMSSendMouseClicked
        // TODO add your handling code here:
        if (jCheckBoxEnableSMSSend.isSelected()) {

            jCheckBoxDisableSMSSend.setSelected(false);
            jCheckBoxEnableSMSSendWithAsk.setSelected(false);

            SMSchoice = "1";

        }
    }//GEN-LAST:event_jCheckBoxEnableSMSSendMouseClicked

    private void jCheckBoxDisableSMSSendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxDisableSMSSendMouseClicked
        // TODO add your handling code here:
        if (jCheckBoxDisableSMSSend.isSelected()) {

            jCheckBoxEnableSMSSend.setSelected(false);
            jCheckBoxEnableSMSSendWithAsk.setSelected(false);

            SMSchoice = "2";

        }
    }//GEN-LAST:event_jCheckBoxDisableSMSSendMouseClicked

    private void jCheckBoxEnableSMSSendWithAskMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxEnableSMSSendWithAskMouseClicked
        // TODO add your handling code here:
        if (jCheckBoxEnableSMSSendWithAsk.isSelected()) {

            jCheckBoxEnableSMSSend.setSelected(false);
            jCheckBoxDisableSMSSend.setSelected(false);

            SMSchoice = "3";

        }
    }//GEN-LAST:event_jCheckBoxEnableSMSSendWithAskMouseClicked

    private void jButtonEnableDisableOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnableDisableOKActionPerformed
        // TODO add your handling code here:
        FileWriter writer = null;
        try {
            final String dir = System.getProperty("user.dir");
            String fileName = dir + "\\others\\NetworkSMSSettings.properties";
            String line = null;
            String oldText = "";
            String stringValueOfSMS = "";

            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                stringValueOfSMS = line.substring(14, 15);
                System.out.println("SubString-->>" + stringValueOfSMS);
                oldText += line + "\r\n";
            }
            bufferedReader.close();

            String newtext = "";
            if (SMSchoice.equalsIgnoreCase("1")) {
                newtext = oldText.replaceAll(stringValueOfSMS, SMSchoice);
            } else if (SMSchoice.equalsIgnoreCase("2")) {
                newtext = oldText.replaceAll(stringValueOfSMS, SMSchoice);
            } else if (SMSchoice.equalsIgnoreCase("3")) {
                newtext = oldText.replaceAll(stringValueOfSMS, SMSchoice);
            }
            writer = new FileWriter(fileName);
            writer.write(newtext);
            writer.close();
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "SMS Network Settings Saved Successfully");
        } catch (IOException ex) {
            Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonEnableDisableOKActionPerformed

    private void jCheckBoxEnableEMailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxEnableEMailMouseClicked
        // TODO add your handling code here:
        jCheckBoxDisableEMail.setSelected(false);
        EMailchoice = "1";
    }//GEN-LAST:event_jCheckBoxEnableEMailMouseClicked

    private void jCheckBoxDisableEMailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxDisableEMailMouseClicked
        // TODO add your handling code here:
        jCheckBoxEnableEMail.setSelected(false);
        EMailchoice = "2";
    }//GEN-LAST:event_jCheckBoxDisableEMailMouseClicked

    private void jButtonEMailOK2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEMailOK2ActionPerformed
        // TODO add your handling code here:
        FileWriter writer = null;
        try {
            final String dir = System.getProperty("user.dir");
            String fileName = dir + "\\others\\NetworkEMailSettings.properties";
            String line = null;
            String oldText = "";
            String stringValueOfEMail = "";

            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int lineCounter = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if (lineCounter == 1) {
                    System.out.println(line);
                    stringValueOfEMail = line.substring(16, 17);
                    System.out.println("SubString-->>" + stringValueOfEMail);
                    oldText += line + "\r\n";
                }
                lineCounter++;
            }
            bufferedReader.close();

            String newtext = "";
            if (EMailchoice.equalsIgnoreCase("1")) {
                newtext = oldText.replaceAll(stringValueOfEMail, EMailchoice);
            } else {
                newtext = oldText.replaceAll(stringValueOfEMail, "2");
            }
            if (EMailchoice.equalsIgnoreCase("2")) {
                newtext = oldText.replaceAll(stringValueOfEMail, EMailchoice);
            } else {
                newtext = oldText.replaceAll(stringValueOfEMail, "1");
            }
            writer = new FileWriter(fileName);
            writer.write(newtext);
            writer.close();
            //JOptionPane.showMessageDialog(this, "E-Mail Network Settings Saved Successfully");
        } catch (IOException ex) {
            Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        FileWriter writerNew = null;
        try {
            final String dir = System.getProperty("user.dir");
            String fileName = dir + "\\others\\NetworkEMailSender.properties";
            String line = null;
            String oldText = "";
            String stringValueOfEMail = "";

            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

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
            newtext = oldText.replaceAll(oldText, jTextFieldSender.getText());
            writerNew = new FileWriter(fileName);
            writerNew.write(newtext);
            writerNew.close();
            //JOptionPane.showMessageDialog(this, "E-Mail Network Settings Saved Successfully");
        } catch (IOException ex) {
            Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writerNew.close();
            } catch (IOException ex) {
                Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        FileWriter writerNewPWD = null;
        try {
            final String dir = System.getProperty("user.dir");
            String fileName = dir + "\\others\\NetworkEMailPWD.properties";
            String line = null;
            String oldText = "";
            String stringValueOfEMailPWD = "";

            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

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
            newtext = oldText.replaceAll(oldText, jPasswordFieldPassword.getText());
            writerNewPWD = new FileWriter(fileName);
            writerNewPWD.write(newtext);
            writerNewPWD.close();
            //JOptionPane.showMessageDialog(this, "E-Mail Network Settings Saved Successfully");
        } catch (IOException ex) {
            Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writerNewPWD.close();
            } catch (IOException ex) {
                Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "E-Mail Network Settings Saved Successfully");
    }//GEN-LAST:event_jButtonEMailOK2ActionPerformed

    private void jButtonApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApplyActionPerformed
        try {
            map_Field_With_ID.clear();
            //        if (checkBoxReceiptnoEditable.isSelected()) {
            //            map_Field_With_ID.put(gen.other.AdSuMuDiSettings.AdSuMuDiConstant.RECEIPT_SETTINGS, 1);
            //        } else {
            //            map_Field_With_ID.put(gen.other.AdSuMuDiSettings.AdSuMuDiConstant.RECEIPT_SETTINGS, 0);
            //        }
            //
            //        if (checkBoxAutoInrementVoucherNumber.isSelected()) {
            //            map_Field_With_ID.put(gen.other.AdSuMuDiSettings.AdSuMuDiConstant.VOUCHER_NUMBER_SETTING, 1);
            //        } else {
            //            map_Field_With_ID.put(gen.other.AdSuMuDiSettings.AdSuMuDiConstant.VOUCHER_NUMBER_SETTING, 0);
            //        }
            String pagination_Value = "";
            if (jTextFieldPaginationValue.getText().toString().trim().equals("") || Double.parseDouble(jTextFieldPaginationValue.getText().toString().trim()) == 0D) {
                //	    pagination_Value = "50";
                JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Enter Pagination value greater than 0");
                jTextFieldPaginationValue.setText("");
                jTextFieldPaginationValue.requestFocus();
            } else {
                pagination_Value = jTextFieldPaginationValue.getText().toString().trim();
                map_Field_With_ID.put(gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.PAGINATION, pagination_Value);
                AdSuMuDiSettingsDAO.insert_Receipt_Settings(map_Field_With_ID);
                gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.getValuesFromTable();
                JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Setting SuccessFully Submitted");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //        set_Value_CheckBox();
    }//GEN-LAST:event_jButtonApplyActionPerformed

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        // TODO add your handling code here:
        AllAdSuMuDiSettingsNew1.this.dispose();
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);

        ActivInactiveComponent();
        getInstalledServerPath();
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        try {
            // TODO add your handling code here:
            // map for role to ID which is require for 2 tab so call here dont remove
            initRole();

            System.out.println("Frame Activated ----------------------------------");
            ActivInactiveComponent();

            SetWaitTimer s = new SetWaitTimer();
            s.resumeWaitTimer(AllAdSuMuDiSettingsNew1.this);
            System.out.println("Tabbed Pane InternalFrame Actived");

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_formInternalFrameActivated

    private void optYesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optYesStateChanged
        // TODO add your handling code here:
        if (optYes.isSelected()) {
            optNo.setSelected(false);
        }
    }//GEN-LAST:event_optYesStateChanged

    private void optNoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optNoStateChanged
        // TODO add your handling code here:
        if (optNo.isSelected()) {
            optYes.setSelected(false);
        }
    }//GEN-LAST:event_optNoStateChanged

    private void optPdfStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optPdfStateChanged
        // TODO add your handling code here:
        if (optPdf.isSelected()) {
            optHtml.setSelected(false);
            optWord.setSelected(false);
            optExcel.setSelected(false);
        }
    }//GEN-LAST:event_optPdfStateChanged

    private void optHtmlStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optHtmlStateChanged
        // TODO add your handling code here:
        if (optHtml.isSelected()) {
            optPdf.setSelected(false);
            optWord.setSelected(false);
            optExcel.setSelected(false);
        }
    }//GEN-LAST:event_optHtmlStateChanged

    private void optWordStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optWordStateChanged
        // TODO add your handling code here:
        if (optWord.isSelected()) {
            optHtml.setSelected(false);
            optPdf.setSelected(false);
            optExcel.setSelected(false);
        }
    }//GEN-LAST:event_optWordStateChanged

    private void optExcelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optExcelStateChanged
        // TODO add your handling code here:
        if (optExcel.isSelected()) {
            optHtml.setSelected(false);
            optWord.setSelected(false);
            optPdf.setSelected(false);
        }
    }//GEN-LAST:event_optExcelStateChanged

    private void optA4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optA4StateChanged
        // TODO add your handling code here:
        if (optA4.isSelected()) {
            optA5.setSelected(false);
            optAsk.setSelected(false);
        }
    }//GEN-LAST:event_optA4StateChanged

    private void optA5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optA5StateChanged
        // TODO add your handling code here:
        if (optA5.isSelected()) {
            optA4.setSelected(false);
            optAsk.setSelected(false);
        }
    }//GEN-LAST:event_optA5StateChanged

    private void optAskStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optAskStateChanged
        // TODO add your handling code here:
        if (optAsk.isSelected()) {
            optA5.setSelected(false);
            optA4.setSelected(false);
        }
    }//GEN-LAST:event_optAskStateChanged

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        Connection conn = null;
        try {
            System.out.println("Btn save calling --------------------      " + gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING);
            System.out.println("Btn save 000000000000000 --------------------      " + gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings);
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();

            String query = "select * from tblOtherSetting";
            resultSet = st.executeQuery(query);
            AllAdSuMuDiSettingsNew1.flagDirectPrint = 0;
            AllAdSuMuDiSettingsNew1.flagPrintPageSize = 0;
            AllAdSuMuDiSettingsNew1.flagPrintPageFormat = 0;
            if (optYes.isSelected()) {
                AllAdSuMuDiSettingsNew1.flagDirectPrint = 1;
            } else if (optNo.isSelected()) {
                AllAdSuMuDiSettingsNew1.flagDirectPrint = 0;
            }
            if (optPdf.isSelected()) {
                AllAdSuMuDiSettingsNew1.flagPrintPageFormat = 1;
            } else if (optHtml.isSelected()) {
                AllAdSuMuDiSettingsNew1.flagPrintPageFormat = 2;
            } else if (optWord.isSelected()) {
                AllAdSuMuDiSettingsNew1.flagPrintPageFormat = 3;
            } else if (optExcel.isSelected()) {
                AllAdSuMuDiSettingsNew1.flagPrintPageFormat = 4;
            }
            if (optA4.isSelected()) {
                AllAdSuMuDiSettingsNew1.flagPrintPageSize = 1;
            } else if (optA5.isSelected()) {
                AllAdSuMuDiSettingsNew1.flagPrintPageSize = 2;
            } else if (optAsk.isSelected()) {
                AllAdSuMuDiSettingsNew1.flagPrintPageSize = 3;
            }

            if (optYes.isSelected() == false && optNo.isSelected() == false) {
                JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Please Select Show Print Preview ");
                optYes.requestFocus();
            } else if (optPdf.isSelected() == false && optHtml.isSelected() == false && optWord.isSelected() == false && optExcel.isSelected() == false) {
                JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Please Select Print Display Format");
                optPdf.requestFocus();
            } else if (optA4.isSelected() == false && optA5.isSelected() == false && optAsk.isSelected() == false) {
                JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Please Select Page Format ");
                optA4.requestFocus();
            } else {
                if (resultSet.next()) {
                    query = "update tblOtherSetting set printpreview=" + AllAdSuMuDiSettingsNew1.flagDirectPrint + ",printPageFormat=" + AllAdSuMuDiSettingsNew1.flagPrintPageSize + ",printDisplayFormat=" + AllAdSuMuDiSettingsNew1.flagPrintPageFormat + "";
                } else {
                    query = "insert into tblOtherSetting(printpreview,printPageFormat,printDisplayFormat) values(" + AllAdSuMuDiSettingsNew1.flagDirectPrint + "," + AllAdSuMuDiSettingsNew1.flagPrintPageSize + "," + AllAdSuMuDiSettingsNew1.flagPrintPageFormat + ")";
                }
                st.executeUpdate(query);
                System.out.println("Operation Performed");
                JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Settings Saved Successfully");
                //btnBackActionPerformed(evt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        try {
            MainClass m = new MainClass();
            m.menuselection(5);
            AllAdSuMuDiSettingsNew1.this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AllAdSuMuDiSettingsNew1.this.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        int seleted_company_Index = 0;
        seleted_company_Index = jTabbedPane1.getSelectedIndex();
        System.out.println("MOuse cliked -----------------------------" + seleted_company_Index);
        if (seleted_company_Index == 0) {
            initialiseGraphicsSettings();
        } else if (seleted_company_Index == 1) {
            initialiseNetworkSettings();
        } else if (seleted_company_Index == 2) {
            initialiseDeviceAndHardwareSettings();
        } else if (seleted_company_Index == 3) {
            jTabbedPane2MouseClicked(evt);
        } else if (seleted_company_Index == 4) {
            jTabbedPane3MouseClicked(evt);
        } else if (seleted_company_Index == 5) {
            intialise_Current_User_Settings();
        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:

        System.out.println("Frame Mouse clicled event cal -----------------------------------");
    }//GEN-LAST:event_formMouseClicked

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentHidden

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        // TODO add your handling code here:
        //ActivInactiveComponent();
    }//GEN-LAST:event_formMouseEntered

    private void buttonApply_Company_SettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonApply_Company_SettingsActionPerformed
    }//GEN-LAST:event_buttonApply_Company_SettingsActionPerformed

    private void buttonExit_Company_SettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExit_Company_SettingsActionPerformed
        // TODO add your handling code here:

        if (tablecompanyList_Company_Settings.getSelectedRow() >= 0) {
            for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList_Company_Settings) {
                if (companySettingsDTO.getCompany_name().equals(tableModelCompanyList_Company_Settings.getValueAt(tablecompanyList_Company_Settings.getSelectedRow(), 0).toString())) {
                    CURRENT_DATABASE_Company_Settings = companySettingsDTO.getCompany_DatabaseName();
                    CURRENT_COMPANY_Company_Settings = tableModelCompanyList_Company_Settings.getValueAt(tablecompanyList_Company_Settings.getSelectedRow(), 0).toString();
                    gen.dto.Constants.CURRENT_COMPANY_NAME = tableModelCompanyList_Company_Settings.getValueAt(tablecompanyList_Company_Settings.getSelectedRow(), 0).toString();
                    // insert into currentcompany table
                    gen.other.CompanySettings.CompanySettingsDAO.insert_Current_Company(tableModelCompanyList_Company_Settings.getValueAt(tablecompanyList_Company_Settings.getSelectedRow(), 0).toString(), tableModelCompanyList_Company_Settings.getValueAt(tablecompanyList_Company_Settings.getSelectedRow(), 1).toString());
                }
            }
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Company Selected    " + tableModelCompanyList_Company_Settings.getValueAt(tablecompanyList_Company_Settings.getSelectedRow(), 0).toString());
            jTabbedPane2MouseClicked(null);
        }


    }//GEN-LAST:event_buttonExit_Company_SettingsActionPerformed

    private void tablecompanyList_Company_SettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablecompanyList_Company_SettingsMouseClicked
        // TODO add your handling code here:
        // set current selected Company DataBase
//	for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList_Company_Settings) {
//	    if (companySettingsDTO.getCompany_name().equals(tableModelCompanyList_Company_Settings.getValueAt(tablecompanyList_Company_Settings.getSelectedRow(), 0).toString())) {
//		CURRENT_DATABASE_Company_Settings = companySettingsDTO.getCompany_DatabaseName();
//		CURRENT_COMPANY_Company_Settings = tableModelCompanyList_Company_Settings.getValueAt(tablecompanyList_Company_Settings.getSelectedRow(), 0).toString();
//		 //insert into currentcompany table
//		gen.other.CompanySettings.CompanySettingsDAO.insert_Current_Company(tableModelCompanyList_Company_Settings.getValueAt(tablecompanyList_Company_Settings.getSelectedRow(), 0).toString(), tableModelCompanyList_Company_Settings.getValueAt(tablecompanyList_Company_Settings.getSelectedRow(), 1).toString());
//	    }
//	}
//
//	JOptionPane.showMessageDialog(null, "Company Selected    " + tableModelCompanyList_Company_Settings.getValueAt(tablecompanyList_Company_Settings.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_tablecompanyList_Company_SettingsMouseClicked

    private void tablecompanyList_Company_SettingsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablecompanyList_Company_SettingsKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //setFocus(event);
            tablecompanyList_Company_SettingsMouseClicked(null);
        }
    }//GEN-LAST:event_tablecompanyList_Company_SettingsKeyPressed

    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked
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

        companySettingsDTO = gen.other.AdSuMuDiSettings.AdSuMuDiConstant.getCurrentCompany_Details("");
        ActivInactiveComponent();

    }//GEN-LAST:event_jTabbedPane2MouseClicked

    private void tableCompanyRestoreUp_Company_RestoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCompanyRestoreUp_Company_RestoreMouseClicked
        // TODO add your handling code here:

        int selectedCompany_index = tableCompanyRestoreUp_Company_Restore.getSelectedRow();

        // selected company_Name
        String selectedCompany_Name = "" + tableCompanyRestoreUp_Company_Restore.getValueAt(selectedCompany_index, 0);

        // selected company_ID
        String selectedCompany_ID = "" + tableCompanyRestoreUp_Company_Restore.getValueAt(selectedCompany_index, 1);

        // selected row must be greater than 0
        if (tableCompanyRestoreUp_Company_Restore.getSelectedRow() >= 0) {
            // getRowCount row must be greater than 0
            if (tableCompanyRestoreUp_Company_Restore.getRowCount() > 0) {
                // find selected row from tableCompanyRestoreUp
                for (int i = 0; i < tableCompanyRestoreUp_Company_Restore.getRowCount(); i++) {
                    // selected Row
                    if (tableCompanyRestoreUp_Company_Restore.isRowSelected(i)) {
                        // move data of selected row from tableCompanyRestoreUp to  tableCompanyList
                        tablemodelCompany_Company_Restore.setRowCount(tableCompanyList_Company_Restore.getRowCount() + 1);
                        tableCompanyList_Company_Restore.setValueAt(selectedCompany_Name, tableCompanyList_Company_Restore.getRowCount() - 1, 0);
                        tableCompanyList_Company_Restore.setValueAt(selectedCompany_ID, tableCompanyList_Company_Restore.getRowCount() - 1, 1);
                        // remove row from table tableCompanyRestoreUp
                        tablemodelRestoreCompany_Company_Restore.removeRow(i);
                        // decrease value of i(row) as one row removed
                        i--;
                    }
                }
                if (tableCompanyRestoreUp_Company_Restore.getRowCount() > 0) {
                    tableCompanyRestoreUp_Company_Restore.requestFocus();
                    tableCompanyRestoreUp_Company_Restore.setRowSelectionInterval(0, 0);
                    tableCompanyList_Company_Restore.clearSelection();
                } else {
                    tableCompanyList_Company_Restore.requestFocus();
                    tableCompanyList_Company_Restore.setRowSelectionInterval(0, 0);
                }
            }
        }
    }//GEN-LAST:event_tableCompanyRestoreUp_Company_RestoreMouseClicked

    private void btnRestore_Company_RestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestore_Company_RestoreActionPerformed
//	try {
//	    // TODO add your handling code here:
//	    if (validation_Company_Restore()) {
//		//insert seletected company_Name and ID for Restore in company_List_Restore_Company_Restore List
//		for (int i = 0; i < tableCompanyRestoreUp_Company_Restore.getRowCount(); i++) {
//		    CompanySettingsDTO companySettingsDTO_Company_Restore = new CompanySettingsDTO();
//		    // company_List_Restore_Company_Restore.add(tableCompanyRestoreUp.getValueAt(i, 0).toString().trim());
//		    companySettingsDTO_Company_Restore.setCompany_name(tableCompanyRestoreUp_Company_Restore.getValueAt(i, 0).toString().trim());
//		    companySettingsDTO_Company_Restore.setCompany_id(Long.parseLong(tableCompanyRestoreUp_Company_Restore.getValueAt(i, 1).toString().trim()));
//		    company_List_Restore_Company_Restore.add(companySettingsDTO_Company_Restore);
//		}
//
//		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//		// already code is present when comapny name fetch
//		// ask to Pankaj Sir
//
//		//	    //Decrypt the Company information files to see company,database,companyfile name
//		//	    decryptfiles(fileChooser_Company_Restore + "/" + "All_Database_information", ".enc");
//		//
//		//	    /// Read all content from company information file and save it in company_InformationDTO
//		//	    BufferedReader brin;
//		//	    brin = new BufferedReader(new FileReader(fileChooser_Company_Restore + "/" + "All_Database_information.txt"));
//		//	    String line = brin.readLine();
//		//	    //	int count = 0;
//		//	    while (line != null) {
//		//		if (!line.equals("")) {
//		//		    // DTO to store all information of company
//		//		    Company_InformationDTO company_InformationDTO = new Company_InformationDTO();
//		//		    String company_information = line;
//		//		    String last_name = "";
//		////			if (count == 2) {
//		//		    StringTokenizer st = new StringTokenizer(company_information, " ");
//		//
//		//		    // count to save cocompany,database,companyfile name in respective DTO field
//		//		    int count = 0;
//		//		    while (st.hasMoreTokens()) {
//		//			switch (count) {
//		//			    case 0:
//		//				company_InformationDTO.setCompany_Name(st.nextToken());
//		//			    case 1:
//		//				company_InformationDTO.setCompany_Database_Name(st.nextToken());
//		//			    case 2:
//		//				company_InformationDTO.setCompany_SQL_File_Name(st.nextToken());
//		//			}
//		////			last_name = st.nextToken();
//		//			count++;
//		//		    }
//		////			}
//		//		    System.out.println("fdfsdfd         " + last_name);
//		//		    company_InformationDTOList_Company_Restore.add(company_InformationDTO);
//		//		}
//		//		line = brin.readLine();
//		////		    count++;
//		//
//		//	    }
//		//	    brin.close();
//		//
//		//
//		//	    for(Company_InformationDTO company_InformationDTO : company_InformationDTOList_Company_Restore){
//		//		System.out.println("Comany NMAe 0000-    ----------- "+company_InformationDTO.getCompany_Name() );
//		//		System.out.println("Comany Datqabase  0000-    ----------- "+company_InformationDTO.getCompany_Database_Name() );
//		//		System.out.println("Comany File NAme 0000-    ----------- "+company_InformationDTO.getCompany_SQL_File_Name() );
//		//	    }
//		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//		// unzip file here and getting all .enc files
//		String zipFilePath = fileChooser_Company_Restore + "/" + "ffdfdsgfd.abc";
//		String destDirectory = "C:/Check";
//
//		UnzipUtility unzipper = new UnzipUtility();
//		try {
//		    unZip_Company_File_List_Company_Restore = unzipper.unzip(zipFilePath, destDirectory);
//		} catch (Exception ex) {
//		    // some errors occurred
//		    ex.printStackTrace();
//		}
//
//		//Decrypt the .SQL files
//		decryptfiles_Company_Restore("", "", unZip_Company_File_List_Company_Restore);
//
//		if (validation_For_All_SQLCompany_File_Company_Restore()) {
//
//		    // retrive all information of Company From database
//		    company_Full_InformationDTOList_Company_Restore = gen.other.CompanySettings.CompanySettingsDAO.getComapany_List("", "");
//
//		    // function to read .SQL file and put database and FileName in MAP
//		    map_Database_FileName_Company_Restore(unZip_Company_File_List_Company_Restore);
//
//		    // drop Database for selected company for restore
//		    gen.other.DatabaseSpiliting.DatabaseSplitingDAO.dropDatabases(company_InformationDTO_Final_List_Company_Restore);
//
//		    // Create Database for selected company for restore
//		    gen.other.DatabaseSpiliting.DatabaseSplitingDAO.createNewDatabase(company_InformationDTO_Final_List_Company_Restore);
//
//		    // restore databse ..............
//		    for (Company_InformationDTO company_InformationDTO_Company_Restore : company_InformationDTO_Final_List_Company_Restore) {
//			String database = company_InformationDTO_Company_Restore.getCompany_Database_Name();
//			String pathName = txtSourcePath_Company_Restore.getText().toString().trim();
//			restoreDB_Company_Restore(database, "root", "adm", pathName + "/" + database + ".sql");
//		    }
//
//		    // Delete Extra Created .ENC files
//		    for (int i = 0; i < unZip_Company_File_List_Company_Restore.size(); i++) {
//			Path target5 = Paths.get(fileChooser_Company_Restore + "/" + unZip_Company_File_List_Company_Restore.get(i));
//			if (Files.exists(target5)) {
//			    Files.delete(target5);
//			}
//		    }
//
//		    // Delete Extra Created .SQL files
//		    for (int i = 0; i < company_SQLFile_List_Restore.size(); i++) {
//			Path target5 = Paths.get(fileChooser_Company_Restore + "/" + company_SQLFile_List_Restore.get(i));
//			if (Files.exists(target5)) {
//			    Files.delete(target5);
//			}
//		    }
//
//		    // Delete Extra Created .txt files
//		    Path target5 = Paths.get(fileChooser_Company_Restore + "/" + "All_Database_information.txt");
//		    if (Files.exists(target5)) {
//			Files.delete(target5);
//		    }
//
//		    initialise_Company_Restore();
//		}
//	    }
//	} catch (Exception ex) {
//	    ex.printStackTrace();
//	    Logger.getLogger(DatabaseRestore.class.getName()).log(Level.SEVERE, null, ex);
//	}

        try {
            // TODO add your handling code here:
            if (validation_Company_Restore()) {
                //insert seletected company_Name and ID for Restore in company_List_Restore_Company_Restore List
                for (int i = 0; i < tableCompanyRestoreUp_Company_Restore.getRowCount(); i++) {
                    CompanySettingsDTO companySettingsDTO_Company_Restore = new CompanySettingsDTO();
                    // company_List_Restore_Company_Restore.add(tableCompanyRestoreUp.getValueAt(i, 0).toString().trim());
                    companySettingsDTO_Company_Restore.setCompany_name(tableCompanyRestoreUp_Company_Restore.getValueAt(i, 0).toString().trim());
                    companySettingsDTO_Company_Restore.setCompany_id(Long.parseLong(tableCompanyRestoreUp_Company_Restore.getValueAt(i, 1).toString().trim()));
                    company_List_Restore_Company_Restore.add(companySettingsDTO_Company_Restore);
                }

                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                // already code is present when comapny name fetch
                // ask to Pankaj Sir

                //	    //Decrypt the Company information files to see company,database,companyfile name
                //	    decryptfiles(fileChooser_Company_Restore + "/" + "All_Database_information", ".enc");
                //
                //	    /// Read all content from company information file and save it in company_InformationDTO
                //	    BufferedReader brin;
                //	    brin = new BufferedReader(new FileReader(fileChooser_Company_Restore + "/" + "All_Database_information.txt"));
                //	    String line = brin.readLine();
                //	    //	int count = 0;
                //	    while (line != null) {
                //		if (!line.equals("")) {
                //		    // DTO to store all information of company
                //		    Company_InformationDTO company_InformationDTO = new Company_InformationDTO();
                //		    String company_information = line;
                //		    String last_name = "";
                ////			if (count == 2) {
                //		    StringTokenizer st = new StringTokenizer(company_information, " ");
                //
                //		    // count to save cocompany,database,companyfile name in respective DTO field
                //		    int count = 0;
                //		    while (st.hasMoreTokens()) {
                //			switch (count) {
                //			    case 0:
                //				company_InformationDTO.setCompany_Name(st.nextToken());
                //			    case 1:
                //				company_InformationDTO.setCompany_Database_Name(st.nextToken());
                //			    case 2:
                //				company_InformationDTO.setCompany_SQL_File_Name(st.nextToken());
                //			}
                ////			last_name = st.nextToken();
                //			count++;
                //		    }
                ////			}
                //		    System.out.println("fdfsdfd         " + last_name);
                //		    company_InformationDTOList_Company_Restore.add(company_InformationDTO);
                //		}
                //		line = brin.readLine();
                ////		    count++;
                //
                //	    }
                //	    brin.close();
                //
                //
                //	    for(Company_InformationDTO company_InformationDTO : company_InformationDTOList_Company_Restore){
                //		System.out.println("Comany NMAe 0000-    ----------- "+company_InformationDTO.getCompany_Name() );
                //		System.out.println("Comany Datqabase  0000-    ----------- "+company_InformationDTO.getCompany_Database_Name() );
                //		System.out.println("Comany File NAme 0000-    ----------- "+company_InformationDTO.getCompany_SQL_File_Name() );
                //	    }
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                // unzip file here and getting all .enc files
                String zipFilePath = fileChooser_Company_Restore + "/" + "ffdfdsgfd.abc";
                String destDirectory = "C:/Check";

                UnzipUtility unzipper = new UnzipUtility();
                try {
                    unZip_Company_File_List_Company_Restore = unzipper.unzip(zipFilePath, destDirectory);
                } catch (Exception ex) {
                    // some errors occurred
                    ex.printStackTrace();
                }

                //Decrypt the .SQL files
                decryptfiles_Company_Restore("", "", unZip_Company_File_List_Company_Restore);

                if (validation_For_All_SQLCompany_File_Company_Restore()) {

                    // retrive all information of Company From database
                    company_Full_InformationDTOList_Company_Restore = gen.other.CompanySettings.CompanySettingsDAO.getComapany_List("", "");
                    System.out.println("find the database ------------------------- " + company_Full_InformationDTOList_Company_Restore.size());


                    // function to read .SQL file and put database and FileName in MAP
                    map_Database_FileName_Company_Restore(unZip_Company_File_List_Company_Restore);

                    // drop Database for selected company for restore
                    System.out.println("Drop database --------- From class --------          " + company_InformationDTO_Final_List_Company_Restore.size());
                    gen.other.DatabaseSpiliting.DatabaseSplitingDAO.dropDatabases(company_InformationDTO_Final_List_Company_Restore);

                    // Create Database for selected company for restore
                    System.out.println("Create database ----------------------- " + company_InformationDTO_Final_List_Company_Restore.size());
                    gen.other.DatabaseSpiliting.DatabaseSplitingDAO.createNewDatabase(company_InformationDTO_Final_List_Company_Restore);

                    // restore databse ..............
                    System.out.println("Restore DAtabaseas --------------------- " + company_InformationDTO_Final_List_Company_Restore.size());
                    for (Company_InformationDTO company_InformationDTO_Company_Restore : company_InformationDTO_Final_List_Company_Restore) {
                        String database = company_InformationDTO_Company_Restore.getCompany_Database_Name();
                        String pathName = txtSourcePath_Company_Restore.getText().toString().trim();
                        System.out.println("Database For Restore +---------------               " + database);
                        System.out.println("Path Files  :::::::::::::::::::  " + pathName + "/" + database + ".sql");
                        restoreDB_Company_Restore(database, "root", "adm", pathName + "/" + database + ".sql");
                    }

                    // Delete Extra Created .ENC files
                    for (int i = 0; i < unZip_Company_File_List_Company_Restore.size(); i++) {
                        Path target5 = Paths.get(fileChooser_Company_Restore + "/" + unZip_Company_File_List_Company_Restore.get(i));
                        if (Files.exists(target5)) {
                            Files.delete(target5);
                        }
                    }

                    // Delete Extra Created .SQL files
                    for (int i = 0; i < company_SQLFile_List_Restore.size(); i++) {
                        Path target5 = Paths.get(fileChooser_Company_Restore + "/" + company_SQLFile_List_Restore.get(i));
                        if (Files.exists(target5)) {
                            Files.delete(target5);
                        }
                    }

                    // Delete Extra Created .txt files
                    Path target5 = Paths.get(fileChooser_Company_Restore + "/" + "All_Database_information.txt");
                    if (Files.exists(target5)) {
                        Files.delete(target5);
                    }

                    initialise_Company_Restore();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRestore_Company_RestoreActionPerformed

    private void txtSourcePath_Company_RestoreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSourcePath_Company_RestoreKeyPressed
        // TODO add your handling code here:

        initialise_Company_Restore();
        bindData2GUI_Company_Restore();

    }//GEN-LAST:event_txtSourcePath_Company_RestoreKeyPressed

    private void jLabel44MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel44MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel44MouseClicked

    private void buttonFilePath_Company_RestoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonFilePath_Company_RestoreMouseClicked
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("choosertitle");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            // Remove spaces between Name in Selecetd folder path
            // String output = chooser.getSelectedFile().toString().replaceAll("\\s+","");
            fileChooser_Company_Restore = chooser.getSelectedFile();
            txtSourcePath_Company_Restore.setText(fileChooser_Company_Restore.toString());
            txtSourcePath_Company_RestoreKeyPressed(null);
        } else {
            System.out.println("No Selection ");
        }
    }//GEN-LAST:event_buttonFilePath_Company_RestoreMouseClicked

    private void buttonFilePath_Company_RestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFilePath_Company_RestoreActionPerformed
        // TODO add your handling code here:
        buttonFilePath_Company_RestoreMouseClicked(null);
    }//GEN-LAST:event_buttonFilePath_Company_RestoreActionPerformed

    private void tableCompanyList_Company_RestoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCompanyList_Company_RestoreMouseClicked
        // TODO add your handling code here:
        int selectedCompany_index = tableCompanyList_Company_Restore.getSelectedRow();

        // selected company_Name
        String selectedCompany_Name = "" + tableCompanyList_Company_Restore.getValueAt(selectedCompany_index, 0);
        // selected company_ID
        String selectedCompany_ID = "" + tableCompanyList_Company_Restore.getValueAt(selectedCompany_index, 1);

        // selected row must be greater than 0
        if (tableCompanyList_Company_Restore.getSelectedRow() >= 0) {
            // getRowCount row must be greater than 0
            if (tableCompanyList_Company_Restore.getRowCount() > 0) {
                // find selected row from tableCompanyList
                for (int i = 0; i < tableCompanyList_Company_Restore.getRowCount(); i++) {
                    // selected row
                    if (tableCompanyList_Company_Restore.isRowSelected(i)) {
                        // move data of selected row from tableCompanyList to tableCompanyRestoreUp
                        tablemodelRestoreCompany_Company_Restore.setRowCount(tableCompanyRestoreUp_Company_Restore.getRowCount() + 1);
                        tableCompanyRestoreUp_Company_Restore.setValueAt(selectedCompany_Name, tableCompanyRestoreUp_Company_Restore.getRowCount() - 1, 0);
                        tableCompanyRestoreUp_Company_Restore.setValueAt(selectedCompany_ID, tableCompanyRestoreUp_Company_Restore.getRowCount() - 1, 1);
                        // remove row from table tableCompanyList
                        tablemodelCompany_Company_Restore.removeRow(i);
                        // decrease value of i(row) as one row removed
                        i--;
                    }
                }

                if (tableCompanyList_Company_Restore.getRowCount() > 0) {
                    tableCompanyList_Company_Restore.requestFocus();
                    tableCompanyList_Company_Restore.setRowSelectionInterval(0, 0);
                    tableCompanyRestoreUp_Company_Restore.clearSelection();
                } else {
                    tableCompanyRestoreUp_Company_Restore.requestFocus();
                    tableCompanyRestoreUp_Company_Restore.setRowSelectionInterval(0, 0);
                }
            }
        }
    }//GEN-LAST:event_tableCompanyList_Company_RestoreMouseClicked

    private void tableCompanyList_Database_SplitngMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCompanyList_Database_SplitngMouseClicked
        // TODO add your handling code here:

        int selectedCompany_index = tableCompanyList_Database_Splitng.getSelectedRow();
        selected_Company_Name_Database_Splitng = "" + tableCompanyList_Database_Splitng.getValueAt(selectedCompany_index, 0).toString().trim();
        selected_Company_id_Database_Splitng = "" + tableCompanyList_Database_Splitng.getValueAt(selectedCompany_index, 1).toString().trim();

        System.out.println("selectedCompany ------------------------ " + selected_Company_Name_Database_Splitng);
        System.out.println("company_id         ----------------------" + selected_Company_id_Database_Splitng);
    }//GEN-LAST:event_tableCompanyList_Database_SplitngMouseClicked

    private void tableCompanyList_Database_SplitngKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableCompanyList_Database_SplitngKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tableCompanyList_Database_SplitngMouseClicked(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            tfDatePicker_Database_Splitng.requestFocus();
            tableCompanyList_Database_Splitng.clearSelection();
        }
    }//GEN-LAST:event_tableCompanyList_Database_SplitngKeyPressed

    private void btnDataSpliting_Database_SplitngMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDataSpliting_Database_SplitngMouseClicked
        // TODO add your handling code here:
        //	if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        //	    btnDataSplitingActionPerformed(null);
        //	} else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        //	    tfDatePicker.requestFocus();
        //	}
    }//GEN-LAST:event_btnDataSpliting_Database_SplitngMouseClicked

    private void btnDataSpliting_Database_SplitngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataSpliting_Database_SplitngActionPerformed
        // TODO add your handling code here:

        if (validation_Database_Splitng()) {
            String fromDateOpenStr = "";
            if (fromJDateChooser_Database_Splitng.getDate() != null) {
                try {
                    fromDateOpenStr = Constants.simpleDateFormatDatabaseWithDash.format(fromJDateChooser_Database_Splitng.getDate());
                    Calendar c = Calendar.getInstance();
                    c.setTime(Constants.simpleDateFormatDatabaseWithDash.parse(Constants.simpleDateFormatDatabaseWithDash.format(fromJDateChooser_Database_Splitng.getDate())));
                    // number of days to add for calculation opening_Balances
                    c.add(Calendar.DATE, 1);
                    fromDateOpenStr = Constants.simpleDateFormatDatabaseWithDash.format(c.getTime());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            List<String> new_CreateCompanyList = new ArrayList<String>();
            List<Company_InformationDTO> new_databaseCreateList = new ArrayList<Company_InformationDTO>();

            String new_Company_Create = "";

            // StringTokenizer used to remove '-' from Date
            StringTokenizer add_InDatabase_Name = new StringTokenizer(tfDatePicker_Database_Splitng.getText().toString().trim(), "-");
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

            //	    int check_Company_Name_count = 0;
            //	    for (CompanySettingsDTO companyListDTO : company_ListDTO_Database_Splitng) {
            //		if (companyListDTO.getCompany_name().contentEquals(tfUnderField.getText().toString().trim() + add_Date_InComapnyName)) {
            //		    // set check_Name_conter = 1 if company is already present
            //		    check_Company_Name_count = 1;
            //		}
            //	    }

            // if check_Name_conter = 0 means company is not already present
            //	    if (check_Company_Name_count == 0) {
            try {

                // ADDD date in Name of New Company
                new_Company_Create = selected_Company_Name_Database_Splitng + " from " + add_Date_InComapnyName;

                // Database Name of OLD Company
                String dataBase_OlD_Company = "";
                String company_ID_OlD_Company = "";

                // get the first Word from the company_name to add in database with id
                String[] result = selected_Company_Name_Database_Splitng.split(" ", 2);
                String first_Word = "";
                String rest_Words = "";
                // use st that ArrayIndex of bound problem shuld not occcur if only 1 word is present
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
                    // take Back UP of Database of selected Company List
                    if (selected_Company_Name_Database_Splitng.toString().trim().equals(company_settings.getCompany_name()) && selected_Company_id_Database_Splitng.toString().trim().equals(company_settings.getCompany_id().toString().trim())) {
                        // take Back UP of Database of selected Company having databse NMAe company_settings.getCompany_DatabaseName() and company_settings.getCompany_id()
                        backupDB_Database_Splitng(company_settings.getCompany_DatabaseName(), "root", "adm", "D:\\databasecheck/" + first_Word + company_settings.getCompany_id() + ".sql");
                        dataBase_OlD_Company = company_settings.getCompany_DatabaseName();
                        company_ID_OlD_Company = company_settings.getCompany_id().toString().trim();
                    }
                }

                // add New company Name in new_CreateCompanyList
                new_CreateCompanyList.add(new_Company_Create);

                // find id to ADD in Database Name of New Created Company
                String add_ID_To_NewCompany = gen.other.CompanySettings.CompanySettingsDAO.get_Max_Company_ID();

                // get the first Word from the New_company_name to add in database with id
                String[] result1 = new_Company_Create.split(" ", 2);
                String first_Word1 = "";
                String rest_Words1 = "";
                // use st that ArrayIndex of bound problem shuld not occcur if only 1 word is present
                for (int i = 0; i < result1.length; i++) {
                    if (i == 0) {
                        first_Word1 = result1[0];
                    } else if (i == 1) {
                        rest_Words1 = result1[1];
                    }
                }

                String new_Company_Database = first_Word1 + add_ID_To_NewCompany;
                Company_InformationDTO company_InformationDTO = new Company_InformationDTO();
                // add New company Database Name in company_InformationDTO
                company_InformationDTO.setCompany_Database_Name(new_Company_Database);
                new_databaseCreateList.add(company_InformationDTO);
                // Create New Database for Newly created Company
                gen.other.DatabaseSpiliting.DatabaseSplitingDAO.createNewDatabase(new_databaseCreateList);

                //	String pass_Database_Name = tfUnderField.getText().toString().trim()  +"$"+ add_date;
                // flag to check if restore Database correct or not
                // if exception occur then flag = false
                Boolean flag = restoreDB_Database_Splitng(new_Company_Database, "root", "adm", "D:\\databasecheck/" + first_Word + company_ID_OlD_Company + ".sql");

                System.out.println("Newl y Created Company ------------------------------");
                // if flag true then add all information in Company_inormation table
                if (flag) {
                    List<CompanySettingsDTO> companySettings_InformationDTOList = new ArrayList<CompanySettingsDTO>();
                    gen.other.DatabaseSpiliting.DatabaseSplitingDAO.insert_NewlyCreated_Company(new_CreateCompanyList, new_Company_Database, selected_Company_Name_Database_Splitng, selected_Company_id_Database_Splitng.toString(), companySettings_InformationDTOList);
                } else {
                    JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Error in the Datbse Restore");
                }

                System.out.println("Delete path-------------------------");
                // delete the .SQL file
                Path path = Paths.get("D:\\databasecheck/" + first_Word + company_ID_OlD_Company + ".sql");
                Files.delete(path);

                // delete record from table according to Date
                gen.other.DatabaseSpiliting.DatabaseSplitingDAO.delete_Records(new_Company_Database, fromDateOpenStr);

                initialise_Database_Spliting();

//		gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = "";
                try {
                    AllAdSuMuDiSettingsNew1.this.setSelected(true);
                    MainClass mainClass = new MainClass();
                    mainClass.menuselection(1);
                    AllAdSuMuDiSettingsNew1.this.setClosed(true);
                } catch (java.beans.PropertyVetoException e) {
                    e.printStackTrace();
                }
                call_Selection_Companies_Database_Splitng();
            } catch (Exception ex) {
                Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
            }
            gen.other.DatabaseSpiliting.DatabaseSplitingDAO.delete_Records(null, fromDateOpenStr);

            //	    }
        }
    }//GEN-LAST:event_btnDataSpliting_Database_SplitngActionPerformed

    private void buttonExit_Company_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExit_Company_DeleteActionPerformed
        // TODO add your handling code here:
//	this.dispose();
        try {
            // TODO add your handling code here:
            //	this.dispose();
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonExit_Company_DeleteActionPerformed

    private void tablecompanyList_Company_DeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablecompanyList_Company_DeleteMouseClicked
        // TODO add your handling code here:
        // set current selected Company DataBase
        for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList_Company_Delete) {
            if (companySettingsDTO.getCompany_name().equals(tableModelCompanyList_Company_Delete.getValueAt(tablecompanyList_Company_Delete.getSelectedRow(), 0).toString())) {
                alter_Company(tableModelCompanyList_Company_Delete.getValueAt(tablecompanyList_Company_Delete.getSelectedRow(), 0).toString(), tableModelCompanyList_Company_Delete.getValueAt(tablecompanyList_Company_Delete.getSelectedRow(), 1).toString());
                btnCompanyCreate1.setEnabled(false);
                btnUpdate1.setEnabled(true);

            }
        }
    }//GEN-LAST:event_tablecompanyList_Company_DeleteMouseClicked

    private void tablecompanyList_Company_DeleteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablecompanyList_Company_DeleteKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //setFocus(event);
            tablecompanyList_Company_DeleteMouseClicked(null);
        }
    }//GEN-LAST:event_tablecompanyList_Company_DeleteKeyPressed

    private void jTabbedPane2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane2FocusGained

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
//	JOptionPane.showMessageDialog(null, "Settings Save Successfully");
    }//GEN-LAST:event_formInternalFrameClosed

    private void txtNameOfGod1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameOfGod1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCompanyName1.requestFocus();
        }
    }//GEN-LAST:event_txtNameOfGod1KeyPressed

    private void txtCompanyName1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCompanyName1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCompanyName1FocusGained

    private void txtCompanyName1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompanyName1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // validation for Specical character and only number
            if (validation_FOR_CreateCompany_Naming(txtCompanyName1.getText().toString().trim())) {
                txtCompanyAlias1.requestFocus();
            } else {
                txtCompanyName1.requestFocus();
            }

        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtNameOfGod1.requestFocus();
        }
    }//GEN-LAST:event_txtCompanyName1KeyPressed

    private void txtCompanyAlias1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCompanyAlias1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCompanyAlias1FocusGained

    private void txtCompanyAlias1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompanyAlias1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCompanyTagLine1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtCompanyName1.requestFocus();
        }
    }//GEN-LAST:event_txtCompanyAlias1KeyPressed

    private void txtCompanyTagLine1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCompanyTagLine1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCompanyTagLine1FocusGained

    private void txtCompanyTagLine1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompanyTagLine1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCompanyAddress1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtCompanyAlias1.requestFocus();
        }
    }//GEN-LAST:event_txtCompanyTagLine1KeyPressed

    private void txtCompanyAddress1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCompanyAddress1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCompanyAddress1FocusGained

    private void txtCompanyAddress1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompanyAddress1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCompanyContact1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtCompanyTagLine1.requestFocus();
        }
    }//GEN-LAST:event_txtCompanyAddress1KeyPressed

    private void txtCompanyContact1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompanyContact1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCompanyMail1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtCompanyAddress1.requestFocus();
        }
    }//GEN-LAST:event_txtCompanyContact1KeyPressed

    private void txtCompanyMail1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCompanyMail1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCompanyMail1FocusGained

    private void txtCompanyMail1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompanyMail1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCompanyITN1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtCompanyContact1.requestFocus();
        }
    }//GEN-LAST:event_txtCompanyMail1KeyPressed

    private void txtCompanyITN1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompanyITN1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCompanySTN1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtCompanyMail1.requestFocus();
        }
    }//GEN-LAST:event_txtCompanyITN1KeyPressed

    private void txtCompanySTN1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompanySTN1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCompanyCSTNo1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtCompanyITN1.requestFocus();
        }
    }//GEN-LAST:event_txtCompanySTN1KeyPressed

    private void txtCompanyCSTNo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompanyCSTNo1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCompanyDeclaration1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtCompanySTN1.requestFocus();
        }
    }//GEN-LAST:event_txtCompanyCSTNo1KeyPressed

    private void txtCompanyDeclaration1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCompanyDeclaration1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCompanyDeclaration1FocusGained

    private void txtCompanyDeclaration1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompanyDeclaration1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCompanyTermsConditions1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtCompanyCSTNo1.requestFocus();
        }
    }//GEN-LAST:event_txtCompanyDeclaration1KeyPressed

    private void txtCompanyTermsConditions1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCompanyTermsConditions1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCompanyTermsConditions1FocusGained

    private void txtCompanyTermsConditions1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompanyTermsConditions1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCompanySignature1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtCompanyDeclaration1.requestFocus();
        }
    }//GEN-LAST:event_txtCompanyTermsConditions1KeyPressed

    private void txtCompanySignature1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCompanySignature1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCompanySignature1FocusGained

    private void txtCompanySignature1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompanySignature1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnCompanyCreate1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtCompanyTermsConditions1.requestFocus();
        }
    }//GEN-LAST:event_txtCompanySignature1KeyPressed

    private void btncompanyClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncompanyClear1ActionPerformed
        // TODO add your handling code here:
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
        txtCompanyTermsConditions1.setText("");
        txtCompanySignature1.setText("");
        lblcompany_ID1.setText("ID");

        source = "";
        btnCompanyCreate1.setEnabled(true);
        btnUpdate1.setEnabled(false);

        LogoPanelView1.setIcon(null);
        LogoPanelView1.revalidate();

        txtCompanyName1.requestFocus();
    }//GEN-LAST:event_btncompanyClear1ActionPerformed

    private void btncompanyClear1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btncompanyClear1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btncompanyClear1ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnCompanyCreate1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtCompanySignature1.requestFocus();
        }
    }//GEN-LAST:event_btncompanyClear1KeyPressed

    private void btnCompanyCreate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompanyCreate1ActionPerformed
        // TODO add your handling code here:

        String ledger_name = txtCompanyName1.getText().trim();
        Pattern p1 = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m1 = p1.matcher(ledger_name);
        boolean b = m1.find();

        Pattern p = Pattern.compile("[a-zA-Z0-9]");
        Matcher m = p.matcher(ledger_name);

        String email = txtCompanyMail1.getText().toString().trim();
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        if (txtCompanyName1.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Enter Name Of Company");
            txtCompanyName1.requestFocus();
        } else if (!m.find()) {
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Enter Valid Name Of Company");
            txtCompanyName1.requestFocus();
        } else if (b) {
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Enter Only Alphabet and Number in  Name Of Company");
            txtCompanyName1.requestFocus();
        } else if (!lblcompany_ID1.getText().toString().trim().equals("ID")) {
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Enter Name Of Company");
            txtCompanyName1.requestFocus();
        } else if (!matcher.matches() && !txtCompanyMail1.getText().toString().trim().equals("")) {
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, Label.ENTER_VALUE_FOR_EMAIL_ID);
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

                // create new company

                // get the first Word from the company_name to add in database with id
                String[] result = txtCompanyName1.getText().toString().trim().split(" ", 2);
                String first_Word = "";
                String rest_Words = "";
                // use st that ArrayIndex of bound problem shuld not occcur if only 1 word is present
                for (int i = 0; i < result.length; i++) {
                    if (i == 0) {
                        first_Word = result[0];
                    } else if (i == 1) {
                        rest_Words = result[1];
                    }
                }
                System.out.println("First: " + first_Word);
                System.out.println("Rest: " + rest_Words);

                // find id to ADD in Database Name of New Created Company
                String add_ID_To_NewCompany = gen.other.CompanySettings.CompanySettingsDAO.get_Max_Company_ID();


                // pass database Name for Newly creted company
                database_Name_For_Newly_Company = first_Word + add_ID_To_NewCompany;

                List<Company_InformationDTO> new_databaseCreateList = new ArrayList<Company_InformationDTO>();
                Company_InformationDTO company_InformationDTO = new Company_InformationDTO();
                // add New company Database Name in company_InformationDTO
                company_InformationDTO.setCompany_Database_Name(database_Name_For_Newly_Company);
                new_databaseCreateList.add(company_InformationDTO);
                // Create New Database for Newly created Company
                if (gen.dto.Constants.DATABASE_SERVER.equals("com.mysql.jdbc.Driver")) {
                    gen.other.DatabaseSpiliting.DatabaseSplitingDAO.createNewDatabase(new_databaseCreateList);
                }

                List<CompanySettingsDTO> companySettings_InformationDTOList = new ArrayList<CompanySettingsDTO>();


                CompanySettingsDTO companySettingsDTO = new CompanySettingsDTO();
                //companySettingsDTO.setCompany_id(Long.parseLong(lblcompany_ID1.getText().toString().trim()));
                companySettingsDTO.setCompany_name(txtCompanyName1.getText().toString().trim());
                System.out.println("Comapny _NMAE  ------------------------------ " + txtCompanyName1.getText().toString().trim());
                companySettingsDTO.setCompany_DatabaseName(database_Name_For_Newly_Company);
                companySettingsDTO.setcompany_Alias(txtCompanyAlias1.getText().toString().trim());
                companySettingsDTO.setcompany_Address(txtCompanyAddress1.getText().toString().trim());
                companySettingsDTO.setcompany_ContactNo(txtCompanyContact1.getText().toString().trim());
                companySettingsDTO.setcompany_Created_by("");
                companySettingsDTO.setcompany_Created_date("");
                companySettingsDTO.setcompany_Declaration(txtCompanyDeclaration1.getText().toString().trim());
                companySettingsDTO.setcompany_EmailId(txtCompanyMail1.getText().toString().trim());
                companySettingsDTO.setcompany_Field1("");
                companySettingsDTO.setcompany_Field2("");
                companySettingsDTO.setcompany_IncomeTaxNo(txtCompanyITN1.getText().toString().trim());
                companySettingsDTO.setcompany_LBTNo("");
                companySettingsDTO.setcompany_Modified_by("");
                companySettingsDTO.setcompany_Modified_date("");
                companySettingsDTO.setcompany_Note("");
                companySettingsDTO.setcompany_SaleTaxNo(txtCompanyCSTNo1.getText().toString().trim());
                companySettingsDTO.setcompany_SignAuthority(txtCompanySignature1.getText().toString().trim());
                companySettingsDTO.setcompany_VatTinNo(txtCompanySTN1.getText().toString().trim());
                companySettingsDTO.setcompany_TermCnditions(txtCompanyTermsConditions1.getText().toString().trim());
                companySettingsDTO.setcompany_TagLine(txtCompanyTagLine1.getText().toString().trim());
                companySettings_InformationDTOList.add(companySettingsDTO);


                // pass empty company_List so that it is use in calling function
                List<String> company_List = new ArrayList<String>();
                gen.other.DatabaseSpiliting.DatabaseSplitingDAO.insert_NewlyCreated_Company(company_List, database_Name_For_Newly_Company, "", "", companySettings_InformationDTOList);
                //////////////////////////////////////////



                BufferedImage mImage;
                final String dir = System.getProperty("user.dir");
                File imgfile = null;
                FileInputStream fin = null;
                if (source != null) {
                    imgfile = new File(source);
                    fin = new FileInputStream(imgfile);

                    PreparedStatement pre = conn.prepareStatement("insert into tblcompaniesimage(company_name,company_id,company_image) values(?,?,?)");
                    pre.setString(1, txtCompanyName1.getText().toString().trim());
                    pre.setString(2, add_ID_To_NewCompany);
                    if (source != null) {
                        pre.setBinaryStream(3, fin, (int) imgfile.length());
                    } else {
                        pre.setString(3, "");
                    }
                    pre.executeUpdate();
                }
//		backupDB_Database_Delete("aj1", "root", "adm", "D:/exe.sql");
//		Boolean flag = restoreDB_Database_Delete("w74", "root", "adm", "D:/exe.sql");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        JInternalFrame newsas = new JInternalFrame();
                        startEmptyWaitTimerForFrame(newsas, database_Name_For_Newly_Company);
                    }
                }).start();






                // delete the file of All_Database_information.Enc 
                Path target5 = Paths.get("D:/exe.sql");
                if (Files.exists(target5)) {
                    Files.deleteIfExists(target5);
                }

                conn.commit();
                gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";


                btncompanyClear1ActionPerformed(evt);
                initilizeGUIComponents_Company_Delete();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Exception : " + e);
            }

        }
    }//GEN-LAST:event_btnCompanyCreate1ActionPerformed

    private void btnCompanyCreate1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCompanyCreate1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnCompanyCreate1ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btncompanyClear1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtCompanySignature1.requestFocus();
        }
    }//GEN-LAST:event_btnCompanyCreate1KeyPressed

    private void btnUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate1ActionPerformed
        // TODO add your handling code here:

        String ledger_name = txtCompanyName1.getText().trim();
        Pattern p = Pattern.compile("[a-zA-Z0-9]");
        Matcher m = p.matcher(ledger_name);

        Pattern p1 = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m1 = p1.matcher(ledger_name);
        boolean flag = m1.find();


        String email = txtCompanyMail1.getText().toString().trim();
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);


        if (txtCompanyName1.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Enter Name Of Company");
            txtCompanyName1.requestFocus();
        } else if (!m.find()) {
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Enter Valid Name Of Company");
            txtCompanyName1.requestFocus();
        } else if (flag) {
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Enter Only Alphabet and Number in  Name Of Company");
            txtCompanyName1.requestFocus();
        } else if (lblcompany_ID1.getText().toString().trim().equals("ID")) {
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Selete company to update");
            txtCompanyName1.requestFocus();
        } else if (!matcher.matches() && !txtCompanyMail1.getText().toString().trim().equals("")) {
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, Label.ENTER_VALUE_FOR_EMAIL_ID);
            txtCompanyMail1.requestFocus();
        } else {
            txtCompanyName1.transferFocus();
            int current = 0;



            try {
                // create new company
                // get the first Word from the company_name to add in database with id
                String[] result = txtCompanyName1.getText().toString().trim().split(" ", 2);
                String first_Word = "";
                String rest_Words = "";
                // use st that ArrayIndex of bound problem shuld not occcur if only 1 word is present
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
                companySettingsDTO.setCompany_id(Long.parseLong(lblcompany_ID1.getText().toString().trim()));
                companySettingsDTO.setCompany_name(txtCompanyName1.getText().toString().trim());
                System.out.println("Comapny _NMAE  ------------------------------ " + txtCompanyName1.getText().toString().trim());
                companySettingsDTO.setcompany_Alias(txtCompanyAlias1.getText().toString().trim());
                companySettingsDTO.setcompany_Address(txtCompanyAddress1.getText().toString().trim());
                companySettingsDTO.setcompany_ContactNo(txtCompanyContact1.getText().toString().trim());
                companySettingsDTO.setcompany_Created_by("");
                companySettingsDTO.setcompany_Created_date("");
                companySettingsDTO.setcompany_Declaration(txtCompanyDeclaration1.getText().toString().trim());
                companySettingsDTO.setcompany_EmailId(txtCompanyMail1.getText().toString().trim());
                companySettingsDTO.setcompany_Field1("");
                companySettingsDTO.setcompany_Field2("");
                companySettingsDTO.setcompany_IncomeTaxNo(txtCompanyITN1.getText().toString().trim());
                companySettingsDTO.setcompany_LBTNo("");
                companySettingsDTO.setcompany_Modified_by("");
                companySettingsDTO.setcompany_Modified_date("");
                companySettingsDTO.setcompany_Note("");
                companySettingsDTO.setcompany_SaleTaxNo(txtCompanyCSTNo1.getText().toString().trim());
                companySettingsDTO.setcompany_SignAuthority(txtCompanySignature1.getText().toString().trim());
                companySettingsDTO.setcompany_VatTinNo(txtCompanySTN1.getText().toString().trim());
                companySettingsDTO.setcompany_TermCnditions(txtCompanyTermsConditions1.getText().toString().trim());
                companySettingsDTO.setcompany_TagLine(txtCompanyTagLine1.getText().toString().trim());
                companySettings_InformationDTOList.add(companySettingsDTO);

                gen.other.DatabaseSpiliting.DatabaseSplitingDAO.update_Company_Information(companySettings_InformationDTOList, company_Cliked_Name,false);

//		if (control == 1) {
                gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";

                BufferedImage mImage;
                final String dir = System.getProperty("user.dir");
                File imgfile = null;
                FileInputStream fin = null;
//		if (source != null) {
//		    imgfile = new File(source);
//		    fin = new FileInputStream(imgfile);
//		}

                if (source != null && !source.isEmpty()) {
                    imgfile = new File(source);
                    fin = new FileInputStream(imgfile);

                    //File imgfile = new File(source);

                    //FileInputStream fin = new FileInputStream(imgfile);
                    Connection conn = DatabaseConnection1.GetConnection();

                    PreparedStatement prmt = conn.prepareStatement("delete from tblcompaniesimage where company_id = ? and company_name = ?");
                    prmt.setString(1, lblcompany_ID1.getText().toString().trim());
                    prmt.setString(2, company_Cliked_Name);
                    prmt.executeUpdate();

                    PreparedStatement pre = conn.prepareStatement("insert into tblcompaniesimage(company_name,company_id,company_image) values(?,?,?)");
                    pre.setString(1, txtCompanyName1.getText().toString().trim());
                    pre.setString(2, lblcompany_ID1.getText().toString().trim());
                    if (source != null && !source.isEmpty()) {
                        pre.setBinaryStream(3, fin, (int) imgfile.length());
                    } else {
                        pre.setString(3, "NULL");
                    }
                    pre.executeUpdate();
                    //File imgfile = new File(source);

                    //FileInputStream fin = new FileInputStream(imgfile);
//		Connection conn = DatabaseConnection1.GetConnection();
//		String query = "update tblcompaniesimage set  company_name = ?, company_image = ? where company_id = ? and company_name = ?";
//		PreparedStatement pre = conn.prepareStatement(query);
//		pre.setString(1, txtCompanyName1.getText().toString().trim());
//		if (source != null) {
//		    pre.setBinaryStream(2, fin, (int) imgfile.length());
//		} else {
//		    pre.setString(2, "");
//		}
//		pre.setString(3, lblcompany_ID1.getText().toString().trim());
//		pre.setString(4, company_Cliked_Name);
//		pre.executeUpdate();
//		}
                    pre.close();
                    conn.close();
                }
                gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";

                insert_Imagein_Company_Database(company_Cliked_Database_Name);

                JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Company Information Updated Successfully...");


                btncompanyClear1ActionPerformed(evt);
                initilizeGUIComponents_Company_Delete();

                btnCompanyCreate1.setEnabled(true);
                btnUpdate1.setEnabled(false);

                System.out.println("4444444444444444444444444444444444444444444444444444444444444444444");
                // Remove icon when button is clicked.
                LogoPanelView1.setIcon(null);
                // **IMPORTANT** to call revalidate() to cause JLabel to resize and be repaint
                LogoPanelView1.revalidate();;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Exception On Save button Click : " + e);
            }

        }
//        txtCompanyAddress1.setEditable(false);
//        txtCompanyAlias1.setEditable(false);
//        txtCompanyContact1.setEditable(false);
//        txtCompanyDeclaration1.setEditable(false);
//        txtCompanyMail1.setEditable(false);
//        txtCompanyITN1.setEditable(false);
//        txtCompanyName1.setEditable(false);
//        txtCompanySTN1.setEditable(false);
//        txtCompanyTagLine1.setEditable(false);
//        txtCompanyTermsConditions1.setEditable(false);
//        txtNameOfGod1.setEditable(false);
//        txtCompanySignature1.setEditable(false);
//        txtCompanyCSTNo1.setEditable(false);

    }//GEN-LAST:event_btnUpdate1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        int dialogButton1 = JOptionPane.YES_NO_OPTION;
        int dialogResult1 = JOptionPane.showConfirmDialog(AllAdSuMuDiSettingsNew1.this, "Your Current Logo Will Be Removed,Do You Want To Proceed ?", "Warning", dialogButton1);
        if (dialogResult1 == 0) {
            imageloading o = new imageloading();
            o.setVisible(true);
        }
//	else {
//	    formInternalFrameActivated(null);
//	}
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete1ActionPerformed
        // TODO add your handling code here:

        if (!lblcompany_ID1.getText().toString().trim().equals("ID")) {
//            JOptionPane optionPane = new JOptionPane("Do you want to Delete  Company" + " ?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
//            JDialog dialog = optionPane.createDialog("Delete");
//            Set<AWTKeyStroke> focusTraversalKeys = new HashSet<AWTKeyStroke>(dialog.getFocusTraversalKeys(0));
//            focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.VK_UNDEFINED));
//            focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_LEFT, KeyEvent.VK_UNDEFINED));
//            dialog.setFocusTraversalKeys(0, focusTraversalKeys);
//
//            dialog.setVisible(true);
//            dialog.dispose();
//
//            System.out.println(" v optionPane.getValue()         " + optionPane.getValue());
//
//            // if click on yes button
//            if ("0".equals(optionPane.getValue().toString())) {
            try {
                gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
                Connection conn = DatabaseConnection1.GetConnection();
                conn.setAutoCommit(false);
                PreparedStatement pre = conn.prepareStatement("delete from  tblcompaniesimage where company_name = ? and company_id = ?");
                pre.setString(1, company_Cliked_Name);
                pre.setString(2, lblcompany_ID1.getText().toString().trim());
                pre.executeUpdate();

                PreparedStatement prete = conn.prepareStatement("delete from   tblcurrentcompany where company_name = ? and company_id = ? ");
                prete.setString(1, company_Cliked_Name);
                prete.setString(2, lblcompany_ID1.getText().toString().trim());
                prete.executeUpdate();

                prete = conn.prepareStatement("delete from   tblusertocompany where company_name = ? and company_id = ? ");
                prete.setString(1, company_Cliked_Name);
                prete.setString(2, lblcompany_ID1.getText().toString().trim());
                prete.executeUpdate();

                PreparedStatement pret = conn.prepareStatement("delete from   tblcompanyinfo where company_name = ? and company_id = ? ");
                pret.setString(1, company_Cliked_Name);
                pret.setString(2, lblcompany_ID1.getText().toString().trim());
                pret.executeUpdate();

                JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Company Deleted Successfully...");
                conn.commit();
                conn.close();
                gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
                btncompanyClear1ActionPerformed(evt);
                initilizeGUIComponents_Company_Delete();

            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        } else {
//            // JOptionPane.showMessageDialog(null, "Selete company to Delete");
//            txtCompanyName1.requestFocus();
//        }


    }//GEN-LAST:event_btnDelete1ActionPerformed

    private void tableCompanyBackUp_Company_Back_UPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCompanyBackUp_Company_Back_UPMouseClicked
        // TODO add your handling code here:

        int selectedCompany_index = tableCompanyBackUp_Company_Back_UP.getSelectedRow();
        // selected company_Name
        String selectedCompany_Name = "" + tableCompanyBackUp_Company_Back_UP.getValueAt(selectedCompany_index, 0);
        // selected company_ID
        String selectedCompany_ID = "" + tableCompanyBackUp_Company_Back_UP.getValueAt(selectedCompany_index, 1);

        // selected row must be greater than 0
        if (tableCompanyBackUp_Company_Back_UP.getSelectedRow() >= 0) {
            // getRowCount row must be greater than 0
            if (tableCompanyBackUp_Company_Back_UP.getRowCount() > 0) {
                // find selected row from tableCompanyRestoreUp
                for (int i = 0; i < tableCompanyBackUp_Company_Back_UP.getRowCount(); i++) {
                    // selected Row
                    if (tableCompanyBackUp_Company_Back_UP.isRowSelected(i)) {
                        // move data of selected row from tableCompanyBackUp to  tableCompanyList
                        tablemodelCompany_Company_Back_UP.setRowCount(tableCompanyList_Company_Back_UP.getRowCount() + 1);
                        tableCompanyList_Company_Back_UP.setValueAt(selectedCompany_Name, tableCompanyList_Company_Back_UP.getRowCount() - 1, 0);
                        tableCompanyList_Company_Back_UP.setValueAt(selectedCompany_ID, tableCompanyList_Company_Back_UP.getRowCount() - 1, 1);
                        // remove row from table tableCompanyBackUp
                        tablemodelBackUPCompany_Company_Back_UP.removeRow(i);
                        // decrease value of i(row) as one row removed
                        i--;
                    }
                }
                if (tableCompanyBackUp_Company_Back_UP.getRowCount() > 0) {
                    tableCompanyBackUp_Company_Back_UP.requestFocus();
                    tableCompanyBackUp_Company_Back_UP.setRowSelectionInterval(0, 0);
                    tableCompanyList_Company_Back_UP.clearSelection();
                } else {
                    tableCompanyList_Company_Back_UP.requestFocus();
                    tableCompanyList_Company_Back_UP.setRowSelectionInterval(0, 0);
                }
            }
        }
    }//GEN-LAST:event_tableCompanyBackUp_Company_Back_UPMouseClicked

    private void tableCompanyList_Company_Back_UPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCompanyList_Company_Back_UPMouseClicked
        // TODO add your handling code here:

        int selectedCompany_index = tableCompanyList_Company_Back_UP.getSelectedRow();

        // selected company_Name
        String selectedCompany_Name = "" + tableCompanyList_Company_Back_UP.getValueAt(selectedCompany_index, 0);
        // selected company_ID
        String selectedCompany_ID = "" + tableCompanyList_Company_Back_UP.getValueAt(selectedCompany_index, 1);

        // selected row must be greater than 0
        if (tableCompanyList_Company_Back_UP.getSelectedRow() >= 0) {
            // getRowCount row must be greater than 0
            if (tableCompanyList_Company_Back_UP.getRowCount() > 0) {
                // find selected row from tableCompanyList
                for (int i = 0; i < tableCompanyList_Company_Back_UP.getRowCount(); i++) {
                    // selected row
                    if (tableCompanyList_Company_Back_UP.isRowSelected(i)) {
                        // move data of selected row from tableCompanyList to tableCompanyRestoreUp
                        tablemodelBackUPCompany_Company_Back_UP.setRowCount(tableCompanyBackUp_Company_Back_UP.getRowCount() + 1);
                        tableCompanyBackUp_Company_Back_UP.setValueAt(selectedCompany_Name, tablemodelBackUPCompany_Company_Back_UP.getRowCount() - 1, 0);
                        tableCompanyBackUp_Company_Back_UP.setValueAt(selectedCompany_ID, tablemodelBackUPCompany_Company_Back_UP.getRowCount() - 1, 1);
                        // remove row from table tableCompanyList
                        tablemodelCompany_Company_Back_UP.removeRow(i);
                        // decrease value of i(row) as one row removed
                        i--;
                    }
                }
                if (tableCompanyList_Company_Back_UP.getRowCount() > 0) {
                    tableCompanyList_Company_Back_UP.requestFocus();
                    tableCompanyList_Company_Back_UP.setRowSelectionInterval(0, 0);
                    tableCompanyBackUp_Company_Back_UP.clearSelection();
                } else {
                    tableCompanyBackUp_Company_Back_UP.requestFocus();
                    tableCompanyBackUp_Company_Back_UP.setRowSelectionInterval(0, 0);
                }
            }
        }
    }//GEN-LAST:event_tableCompanyList_Company_Back_UPMouseClicked

    private void btnBackUP_Company_Back_UPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackUP_Company_Back_UPActionPerformed
        // TODO add your handling code here:

        // check Validation
        if (validation_Company_Back_UP()) {
            try {
                Boolean flag = true;

                //	    ExecutorService executor = Executors.newSingleThreadExecutor();
                //	    Future<String> future = executor.submit(new Task());

                //		Thread thread = new MyThread();
                //		thread.start();
                //
                //		thread.join();
                //
                //		Thread thread1 = new MyThread1();
                //		thread1.start();

                // for finding database of everyrow of back up table one by one
                for (int i = 0; i < tableCompanyBackUp_Company_Back_UP.getRowCount(); i++) {
                    System.out.println("row count");
                    String database = "";
                    for (CompanySettingsDTO companySettingsDTO : company_Information_DTOList_Company_Back_UP) {
                        System.out.println("row ");
                        // set Database Name of selected_Company for Database_BackUP
                        if (companySettingsDTO.getCompany_name().equals(tableCompanyBackUp_Company_Back_UP.getValueAt(i, 0).toString().trim()) && companySettingsDTO.getCompany_id().toString().equals(tableCompanyBackUp_Company_Back_UP.getValueAt(i, 1).toString().trim())) {
                            database = companySettingsDTO.getCompany_DatabaseName();
                            System.out.println("3453453454354"+database);
                            // take a backUP and check Bolean value so that remaining functinality should not work
                            //backupDB_Company_Back_UP(database, "root", "adm", fileChooser_Company_Back_UP + "/" + database + ".sql");
                        }
                    }
                }

                //check Boolean value so that remaining functinality should not work
                if (flag) {
                    /// create file with fileName of database.enc,database,company_name
                    createcompanyfile_Company_Back_UP(fileChooser_Company_Back_UP + "/");

                    // Encrypt the file
                    encryptFile_Company_Back_UP();

                    // Zip the Folder
                    Zipfile_Company_Back_UP(fileChooser_Company_Back_UP + "/");
                }

                initialise_Company_Back_UP();
            } catch (Exception ex) {
                ex.printStackTrace();
                Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnBackUP_Company_Back_UPActionPerformed

    private void txtDestinationPath_Company_Back_UPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDestinationPath_Company_Back_UPMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDestinationPath_Company_Back_UPMouseClicked

    private void jLabel42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel42MouseClicked

    private void btnPath_Company_Back_UPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPath_Company_Back_UPMouseClicked
        // TODO add your handling code here:

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("choosertitle");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            fileChooser_Company_Back_UP = chooser.getSelectedFile();
            ///set selected file text ot txtDestinationPath
            txtDestinationPath_Company_Back_UP.setText(fileChooser_Company_Back_UP.toString());
        } else {
            System.out.println("No Selection ");
        }
    }//GEN-LAST:event_btnPath_Company_Back_UPMouseClicked

    private void btnPath_Company_Back_UPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPath_Company_Back_UPActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Space is not Allow in folder Name where You take a Back UP ");
        btnPath_Company_Back_UPMouseClicked(null);
    }//GEN-LAST:event_btnPath_Company_Back_UPActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        // clear Image
        LogoPanelView1.setIcon(null);
        LogoPanelView1.revalidate();

        if (!lblcompany_ID1.getText().toString().trim().equals("ID")) {
            try {
                gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
                Connection conn = DatabaseConnection1.GetConnection();
                PreparedStatement pre = conn.prepareStatement("delete from  tblcompaniesimage where company_name = ? and company_id = ?");
                pre.setString(1, company_Cliked_Name);
                pre.setString(2, lblcompany_ID1.getText().toString().trim());
                pre.executeUpdate();
                gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Selete company to Delete");
            txtCompanyName1.requestFocus();
        }
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtCompanyName1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompanyName1KeyTyped
        // TODO add your handling code here:

        filterCharacter(evt, txtCompanyName1);

    }//GEN-LAST:event_txtCompanyName1KeyTyped

    private void txtUser_Name1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUser_Name1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUser_Name1FocusGained

    private void txtUser_Name1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUser_Name1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtUser_Password1.requestFocus();
        }
    }//GEN-LAST:event_txtUser_Name1KeyPressed

    private void txtUser_Password1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUser_Password1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUser_Password1FocusGained

    private void txtUser_Password1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUser_Password1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtUser_ConfirmPassword1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtUser_Name1.requestFocus();
        }
    }//GEN-LAST:event_txtUser_Password1KeyPressed

    private void txtUser_ConfirmPassword1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUser_ConfirmPassword1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUser_ConfirmPassword1FocusGained

    private void txtUser_ConfirmPassword1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUser_ConfirmPassword1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtUser_EmailId1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtUser_Password1.requestFocus();
        }
    }//GEN-LAST:event_txtUser_ConfirmPassword1KeyPressed

    private void NUButtonCreateUser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NUButtonCreateUser1ActionPerformed
        // TODO add your handling code here:

        /// Add user information in tbluserdetails table

        if (Validation_For_User_Creation()) {
            userSettingDTO_Class_Level = bindGUItoDTO();
            System.out.println("userSettingDTO_Class_Level =========================== " + userSettingDTO_Class_Level.getUser_id());
            List<UserSettingDTO> userSettingDTOList = new ArrayList<UserSettingDTO>();
            userSettingDTOList.add(userSettingDTO_Class_Level);

            // insert into table user id to company in table tblusertocompany
            List<CompanySettingsDTO> companySettingsDTOList13454 = new ArrayList<CompanySettingsDTO>();

            for (int i = 0; i < tblUser_Selected_Company.getRowCount(); i++) {
                CompanySettingsDTO companySettingsDTO1 = new CompanySettingsDTO();
                companySettingsDTO1.setCompany_name(tblUser_Selected_Company.getValueAt(i, 0).toString().trim());
                companySettingsDTO1.setCompany_id(Long.parseLong(tblUser_Selected_Company.getValueAt(i, 1).toString().trim()));
                companySettingsDTOList13454.add(companySettingsDTO1);
            }
            if (flag_For_Component_enable_Disable_User_Settings) {
                System.out.println("Insert ====================================================");
                gen.mainclass.UserSettingDAO.insertUserSetting(userSettingDTOList);
                gen.mainclass.UserSettingDAO.insertUsertoCompany(companySettingsDTOList13454, "");
            } else {
                System.out.println("UPDATE ====================================================");
                gen.mainclass.UserSettingDAO.updateUserSetting_By_Admin(userSettingDTOList);
                gen.mainclass.UserSettingDAO.insertUsertoCompany(companySettingsDTOList13454, userSettingDTO_Class_Level.getUser_id());
            }
            user_ID_Delete = "";
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "User Created");
            intialise_User_Settings();
        }
    }//GEN-LAST:event_NUButtonCreateUser1ActionPerformed

    private void NUButtonBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NUButtonBack1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NUButtonBack1ActionPerformed

    private void radio_User_Type_Admin_1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radio_User_Type_Admin_1StateChanged
        // TODO add your handling code here:
        if (radio_User_Type_Admin_1.isSelected()) {
            radio_User_Type_NormalUser1.setSelected(false);
        }
        if (radio_User_Type_NormalUser1.isSelected() == false) {
            radio_User_Type_Admin_1.setSelected(true);
        }
    }//GEN-LAST:event_radio_User_Type_Admin_1StateChanged

    private void radio_User_Type_Admin_1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_radio_User_Type_Admin_1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_User_Type_Admin_1KeyPressed

    private void radio_User_Type_NormalUser1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radio_User_Type_NormalUser1StateChanged
        // TODO add your handling code here:
        if (radio_User_Type_NormalUser1.isSelected()) {
            radio_User_Type_Admin_1.setSelected(false);
        }
        if (radio_User_Type_Admin_1.isSelected() == false) {
            radio_User_Type_NormalUser1.setSelected(true);
        }
    }//GEN-LAST:event_radio_User_Type_NormalUser1StateChanged

    private void radio_User_Type_NormalUser1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_radio_User_Type_NormalUser1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_User_Type_NormalUser1KeyPressed

    private void txtUser_EmailId1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUser_EmailId1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUser_EmailId1FocusGained

    private void txtUser_EmailId1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUser_EmailId1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtUser_Email_Password1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
//	    tf_Role_User_Settings.requestFocus();
        }
    }//GEN-LAST:event_txtUser_EmailId1KeyPressed

    private void createUserPanel1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_createUserPanel1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_createUserPanel1FocusLost

    private void jTabbedPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane3MouseClicked
        // TODO add your handling code here:

        int seleted_User_Index = 0;
        seleted_User_Index = jTabbedPane2.getSelectedIndex();

        System.out.println("jTabbedPane2 --------- mouse clicked ---------------------------------");
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

        //companySettingsDTO = gen.other.AdSuMuDiSettings.AdSuMuDiConstant.getCurrentCompany_Details();
        //ActivInactiveComponent();

    }//GEN-LAST:event_jTabbedPane3MouseClicked

    private void tblUser_Available_CompanyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUser_Available_CompanyMouseClicked
        // TODO add your handling code here:

        int selectedCompany_index = tblUser_Available_Company.getSelectedRow();
        // selected company_Name
        String selectedCompany_Name = tblUser_Available_Company.getValueAt(selectedCompany_index, 0).toString();
        // selected company_ID
        String selectedCompany_ID = tblUser_Available_Company.getValueAt(selectedCompany_index, 1).toString();

        // check if table selected company is already present or not
        // if not then ADD
        Boolean flag_Row_present_Or_Not = true;
        if (tblUser_Selected_Company.getRowCount() > 0) {
            for (int i = 0; i < tblUser_Selected_Company.getRowCount(); i++) {
                if (selectedCompany_Name.equals(tblUser_Selected_Company.getValueAt(i, 0).toString()) && selectedCompany_ID.equals(tblUser_Selected_Company.getValueAt(i, 1).toString())) {
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
                    for (int i = 0; i < tblUser_Available_Company.getRowCount(); i++) {
                        // selected row
                        if (tblUser_Available_Company.isRowSelected(i)) {
                            // move data of selected row from tableCompanyList to tableCompanyRestoreUp
                            tablemodel_Company_Selected_User_Settings.setRowCount(tblUser_Selected_Company.getRowCount() + 1);
                            tblUser_Selected_Company.setValueAt(selectedCompany_Name, tablemodel_Company_Selected_User_Settings.getRowCount() - 1, 0);
                            tblUser_Selected_Company.setValueAt(selectedCompany_ID, tablemodel_Company_Selected_User_Settings.getRowCount() - 1, 1);
                            // remove row from table tableCompanyList
                            tablemodel_Company_Available_User_Settings.removeRow(i);
                            // decrease value of i(row) as one row removed
                            i--;
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_tblUser_Available_CompanyMouseClicked

    private void tblUser_Selected_CompanyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUser_Selected_CompanyMouseClicked
        // TODO add your handling code here:
        int selectedCompany_index = tblUser_Selected_Company.getSelectedRow();

        // selected company_Name
        String selectedCompany_Name = "" + tblUser_Selected_Company.getValueAt(selectedCompany_index, 0);
        // selected company_ID
        String selectedCompany_ID = "" + tblUser_Selected_Company.getValueAt(selectedCompany_index, 1);

//	// check if table selected company is already present or not
//	// if not then ADD
//	Boolean flag_Row_present_Or_Not = true;
//	if (tblUser_Available_Company.getRowCount() > 0) {
//	    for (int i = 0; i < tblUser_Available_Company.getRowCount(); i++) {
//		if (selectedCompany_Name.equals(tblUser_Available_Company.getValueAt(i, 0).toString()) && selectedCompany_ID.equals(tblUser_Available_Company.getValueAt(i, 1).toString())) {
//		    flag_Row_present_Or_Not = false;
//		    break;
//		}
//	    }
//	}


        // this purpose of if else is if admin come for new user then add or remove company from respective copmany is right
        // but if he is in edit mode then already selected company should remove if he want but should not add in availabe list and  also
        // 2 same selected compnay should not be add
//	if (flag_For_Component_enable_Disable_User_Settings.equals(true)) {
        // selected row must be greater than 0
        if (tblUser_Selected_Company.getSelectedRow() >= 0) {
            // getRowCount row must be greater than 0
            if (tblUser_Selected_Company.getRowCount() > 0) {
                // find selected row from tableCompanyList
                for (int i = 0; i < tblUser_Selected_Company.getRowCount(); i++) {
                    // selected row
                    if (tblUser_Selected_Company.isRowSelected(i)) {
                        // move data of selected row from tableCompanyList to tableCompanyRestoreUp
                        tablemodel_Company_Available_User_Settings.setRowCount(tblUser_Available_Company.getRowCount() + 1);
                        tblUser_Available_Company.setValueAt(selectedCompany_Name, tablemodel_Company_Available_User_Settings.getRowCount() - 1, 0);
                        tblUser_Available_Company.setValueAt(selectedCompany_ID, tablemodel_Company_Available_User_Settings.getRowCount() - 1, 1);
                        // remove row from table tableCompanyList
                        tablemodel_Company_Selected_User_Settings.removeRow(i);
                        // decrease value of i(row) as one row removed
                        i--;
                    }
                }
            }
        }
////	} else{
//	     // getRowCount row must be greater than 0
//	    if (tblUser_Selected_Company.getRowCount() > 0) {
//		// find selected row from tableCompanyList
//		for (int i = 0; i < tblUser_Selected_Company.getRowCount(); i++) {
//		    // selected row
//		    if (tblUser_Selected_Company.isRowSelected(i)) {
//			// remove row from table tableCompanyList
//			tablemodel_Company_Selected_User_Settings.removeRow(i);
//			// decrease value of i(row) as one row removed
//			i--;
//		    }
//		}
//	    }
//	}
    }//GEN-LAST:event_tblUser_Selected_CompanyMouseClicked

    private void tblUser_Details_for_AdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUser_Details_for_AdminMouseClicked
        // TODO add your handling code here:

        // for diasble unnecessary Field
        // for diasble unnecessary Field
        flag_For_Component_enable_Disable_User_Settings = false;
        setDisable_Component_User_Setting();
        bindDTOTOGUI_User_Settings();

    }//GEN-LAST:event_tblUser_Details_for_AdminMouseClicked

    private void btnNewUser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewUser1ActionPerformed
        // TODO add your handling code here:
        flag_For_Component_enable_Disable_User_Settings = true;
        setEnable_Component_User_Settings();
        //claerfield_User_Settings();
        intialise_User_Settings();
        txtUser_Name1.requestFocus();
    }//GEN-LAST:event_btnNewUser1ActionPerformed

    private void createUserPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createUserPanel1MouseClicked
        // TODO add your handling code here:

        //Dont remove as mouse clicked Activated of Internal so dont remove this Event

        System.out.println("Panel --------- mouse clicked ---------------------------------");

    }//GEN-LAST:event_createUserPanel1MouseClicked

    private void txt_Current_User_NameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_Current_User_NameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Current_User_NameFocusGained

    private void txt_Current_User_NameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Current_User_NameKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_Current_User_Password.requestFocus();
        }
    }//GEN-LAST:event_txt_Current_User_NameKeyPressed

    private void txt_Current_User_PasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_Current_User_PasswordFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Current_User_PasswordFocusGained

    private void txt_Current_User_PasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Current_User_PasswordKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_Current_User_ConfirmPassword.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txt_Current_User_Name.requestFocus();
        }
    }//GEN-LAST:event_txt_Current_User_PasswordKeyPressed

    private void txt_Current_User_ConfirmPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_Current_User_ConfirmPasswordFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Current_User_ConfirmPasswordFocusGained

    private void txt_Current_User_ConfirmPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Current_User_ConfirmPasswordKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_Current_User_EmailId.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txt_Current_User_Password.requestFocus();
        }
    }//GEN-LAST:event_txt_Current_User_ConfirmPasswordKeyPressed

    private void NUButtonCreateUser3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NUButtonCreateUser3ActionPerformed
        // TODO add your handling code here:

        if (Validation_For_User_Updation()) {
            String str = JOptionPane.showInputDialog(AllAdSuMuDiSettingsNew1.this, "Enter Password : ", "Password", 1).trim();

            Boolean flag = false;
            List<UserSettingDTO> userSettingDTOList = new ArrayList<UserSettingDTO>();
            userSettingDTOList = gen.mainclass.UserSettingDAO.get_User_Details(gen.dto.Constants.CURRENT_USER_ID, "");
            for (UserSettingDTO userSettingDTO : userSettingDTOList) {
                if (str != null && !str.isEmpty() && userSettingDTO.getUser_Password().equals(str)) {
                    flag = true;
                }
            }


            if (flag) {

                List<UserSettingDTO> userSettingDTOList_Pass = new ArrayList<UserSettingDTO>();
                UserSettingDTO userSettingDTO = new UserSettingDTO();
                userSettingDTO = bindGUItoDTO_Current_Users();
                userSettingDTOList_Pass.add(userSettingDTO);
                gen.mainclass.UserSettingDAO.updateUserSetting_By_User(userSettingDTOList_Pass);

                gen.other.CompanySettings.CompanySettingsDAO.delete_Current_Company_By_User("");

                if (tblCurrent_Company_For_Current_User_Settings.getRowCount() > 0) {
                    for (int i = 0; i < tablemodel_Current_Company_For_Current_User_Settings.getRowCount(); i++) {
                        gen.other.CompanySettings.CompanySettingsDAO.insert_Current_Company(tablemodel_Current_Company_For_Current_User_Settings.getValueAt(i, 0).toString(), tablemodel_Current_Company_For_Current_User_Settings.getValueAt(i, 1).toString());
                    }
                }

                intialise_Current_User_Settings();
                JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Submitted Sucessfully ", "Password", 1);
            } else {
                JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Enter Correct Password.", "Password", 1);
            }
        }
    }//GEN-LAST:event_NUButtonCreateUser3ActionPerformed
    private void NUButtonBack3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NUButtonBack3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NUButtonBack3ActionPerformed

    private void radio_User_Type_Admin_3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radio_User_Type_Admin_3StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_User_Type_Admin_3StateChanged

    private void radio_User_Type_Admin_3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_radio_User_Type_Admin_3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_User_Type_Admin_3KeyPressed

    private void radio_User_Type_NormalUser3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radio_User_Type_NormalUser3StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_User_Type_NormalUser3StateChanged

    private void radio_User_Type_NormalUser3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_radio_User_Type_NormalUser3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_User_Type_NormalUser3KeyPressed

    private void txt_Current_User_EmailIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_Current_User_EmailIdFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Current_User_EmailIdFocusGained

    private void txt_Current_User_EmailIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Current_User_EmailIdKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txt_Current_User_ConfirmPassword.requestFocus();
        }
    }//GEN-LAST:event_txt_Current_User_EmailIdKeyPressed

    private void tblCompany_Allocated_Current_User_SettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCompany_Allocated_Current_User_SettingsMouseClicked
        // TODO add your handling code here:

        int selectedCompany_index = tblCompany_Allocated_Current_User_Settings.getSelectedRow();
        // selected company_Name
        String selectedCompany_Name = tblCompany_Allocated_Current_User_Settings.getValueAt(selectedCompany_index, 0).toString();
        // selected company_ID
        String selectedCompany_ID = tblCompany_Allocated_Current_User_Settings.getValueAt(selectedCompany_index, 1).toString();

        // selected row must be greater than 0
        if (tblCompany_Allocated_Current_User_Settings.getSelectedRow() >= 0) {
            // getRowCount row must be greater than 0
            if (tblCompany_Allocated_Current_User_Settings.getRowCount() > 0) {
                tablemodel_Current_Company_For_Current_User_Settings.setRowCount(0);
                // find selected row from tableCompanyList
                // move data of selected row from tableCompanyList to tableCompanyRestoreUp
                tablemodel_Current_Company_For_Current_User_Settings.setRowCount(tablemodel_Current_Company_For_Current_User_Settings.getRowCount() + 1);
                tablemodel_Current_Company_For_Current_User_Settings.setValueAt(selectedCompany_Name, tablemodel_Current_Company_For_Current_User_Settings.getRowCount() - 1, 0);
                tablemodel_Current_Company_For_Current_User_Settings.setValueAt(selectedCompany_ID, tablemodel_Current_Company_For_Current_User_Settings.getRowCount() - 1, 1);
            }
        }
    }//GEN-LAST:event_tblCompany_Allocated_Current_User_SettingsMouseClicked

    private void tblCurrent_Company_For_Current_User_SettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCurrent_Company_For_Current_User_SettingsMouseClicked
        // TODO add your handling code here:

        tablemodel_Current_Company_For_Current_User_Settings.setRowCount(0);

    }//GEN-LAST:event_tblCurrent_Company_For_Current_User_SettingsMouseClicked

    private void createUserPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createUserPanel3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_createUserPanel3MouseClicked

    private void createUserPanel3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_createUserPanel3FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_createUserPanel3FocusLost

    private void txtUser_Email_Password1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUser_Email_Password1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUser_Email_Password1KeyPressed

    private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartActionPerformed
        // TODO add your handling code here:
        new Thread(new Runnable() {
            @Override
            public void run() {
                sch = CronTrigger.callTrigger();
            }
        }).start();
    }//GEN-LAST:event_jButtonStartActionPerformed

    private void jButtonStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStopActionPerformed
        try {
            // TODO add your handling code here:
            sch.shutdown();
        } catch (SchedulerException ex) {
            Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonStopActionPerformed

    private void tblavailableRoleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblavailableRoleMouseClicked
        // TODO add your handling code here:

        int selectedCompany_index = tblavailableRole.getSelectedRow();
        // selected company_Name
        String selectedCompany_Name = tblavailableRole.getValueAt(selectedCompany_index, 0).toString();
        // selected company_ID

        // check if table selected company is already present or not
        // if not then ADD
        Boolean flag_Row_present_Or_Not = true;
        if (tblselectedRole.getRowCount() > 0) {
            for (int i = 0; i < tblselectedRole.getRowCount(); i++) {
                if (selectedCompany_Name.equals(tblselectedRole.getValueAt(i, 0).toString())) {
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
                            // move data of selected row from tableCompanyList to tableCompanyRestoreUp
                            tablemodel_SelectedRole_User_Settings.setRowCount(tablemodel_SelectedRole_User_Settings.getRowCount() + 1);
                            tablemodel_SelectedRole_User_Settings.setValueAt(selectedCompany_Name, tablemodel_SelectedRole_User_Settings.getRowCount() - 1, 0);
                            // remove row from table tableCompanyList
                            tablemodel_AvailableRole_User_Settings.removeRow(i);
                            // decrease value of i(row) as one row removed
                            i--;
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_tblavailableRoleMouseClicked

    private void tblselectedRoleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblselectedRoleMouseClicked
        // TODO add your handling code here:
        int selectedCompany_index = tblselectedRole.getSelectedRow();

        // selected company_Name
        String selectedCompany_Name = tblselectedRole.getValueAt(selectedCompany_index, 0).toString();
        // selected company_ID

        // selected row must be greater than 0
        if (tblselectedRole.getSelectedRow() >= 0) {
            // getRowCount row must be greater than 0
            if (tblselectedRole.getRowCount() > 0) {
                // find selected row from tableCompanyList
                for (int i = 0; i < tblselectedRole.getRowCount(); i++) {
                    // selected row
                    if (tblselectedRole.isRowSelected(i)) {
                        // move data of selected row from tableCompanyList to tableCompanyRestoreUp
                        tablemodel_AvailableRole_User_Settings.setRowCount(tablemodel_AvailableRole_User_Settings.getRowCount() + 1);
                        tablemodel_AvailableRole_User_Settings.setValueAt(selectedCompany_Name, tablemodel_AvailableRole_User_Settings.getRowCount() - 1, 0);
                        // remove row from table tableCompanyList
                        tablemodel_SelectedRole_User_Settings.removeRow(i);
                        // decrease value of i(row) as one row removed
                        i--;
                    }
                }
            }
        }
    }//GEN-LAST:event_tblselectedRoleMouseClicked

    private void textureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textureMouseClicked
        // TODO add your handling code here:
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

            Image img = new javax.swing.ImageIcon(dir + "/images/Texture.png").getImage();
            img = img.getScaledInstance(jPanelPreview.getWidth(), jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(img);
            jPanelPreview.setIcon(scaledImageIcon);
        }
    }//GEN-LAST:event_textureMouseClicked

    private void aeroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aeroMouseClicked
        // TODO add your handling code here:
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

            Image img = new javax.swing.ImageIcon(dir + "/images/Aero.png").getImage();
            img = img.getScaledInstance(jPanelPreview.getWidth(), jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(img);
            jPanelPreview.setIcon(scaledImageIcon);
        }
    }//GEN-LAST:event_aeroMouseClicked

    private void aluminiumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aluminiumMouseClicked
        // TODO add your handling code here:
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

            Image img = new javax.swing.ImageIcon(dir + "/images/Aluminium.png").getImage();
            img = img.getScaledInstance(jPanelPreview.getWidth(), jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(img);
            jPanelPreview.setIcon(scaledImageIcon);
        }
    }//GEN-LAST:event_aluminiumMouseClicked

    private void macwinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_macwinMouseClicked
        // TODO add your handling code here:
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

            Image img = new javax.swing.ImageIcon(dir + "/images/macwin.png").getImage();
            img = img.getScaledInstance(jPanelPreview.getWidth(), jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(img);
            jPanelPreview.setIcon(scaledImageIcon);
        }
    }//GEN-LAST:event_macwinMouseClicked

    private void lunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lunaMouseClicked
        // TODO add your handling code here:
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

            Image img = new javax.swing.ImageIcon(dir + "/images/luna.png").getImage();
            img = img.getScaledInstance(jPanelPreview.getWidth(), jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(img);
            jPanelPreview.setIcon(scaledImageIcon);
        }
    }//GEN-LAST:event_lunaMouseClicked

    private void acrylMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acrylMouseClicked
        // TODO add your handling code here:
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

            Image img = new javax.swing.ImageIcon(dir + "/images/Acryl.png").getImage();
            img = img.getScaledInstance(jPanelPreview.getWidth(), jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(img);
            jPanelPreview.setIcon(scaledImageIcon);
        }
    }//GEN-LAST:event_acrylMouseClicked

    private void mintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mintMouseClicked
        // TODO add your handling code here:
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

            Image img = new javax.swing.ImageIcon(dir + "/images/Mint.png").getImage();
            img = img.getScaledInstance(jPanelPreview.getWidth(), jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(img);
            jPanelPreview.setIcon(scaledImageIcon);
        }
    }//GEN-LAST:event_mintMouseClicked

    private void fastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fastMouseClicked
        // TODO add your handling code here:
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

            Image img = new javax.swing.ImageIcon(dir + "/images/Fast.png").getImage();
            img = img.getScaledInstance(jPanelPreview.getWidth(), jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(img);
            jPanelPreview.setIcon(scaledImageIcon);
        }
    }//GEN-LAST:event_fastMouseClicked

    private void graphiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_graphiteMouseClicked
        // TODO add your handling code here:
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

            Image img = new javax.swing.ImageIcon(dir + "/images/Graphite.png").getImage();
            img = img.getScaledInstance(jPanelPreview.getWidth(), jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(img);
            jPanelPreview.setIcon(scaledImageIcon);
        }
    }//GEN-LAST:event_graphiteMouseClicked

    private void smartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_smartMouseClicked
        // TODO add your handling code here:
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

            Image img = new javax.swing.ImageIcon(dir + "/images/Smart.png").getImage();
            img = img.getScaledInstance(jPanelPreview.getWidth(), jPanelPreview.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(img);
            jPanelPreview.setIcon(scaledImageIcon);
        }
    }//GEN-LAST:event_smartMouseClicked

    private void buttonApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonApplyActionPerformed

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
            //            Connection conn = DatabaseConnection1.GetConnection();
            //            String query = "update tblothersetting set theme="+ choice +"";
            //            PreparedStatement ps = conn.prepareStatement(query);
            //            ps.executeUpdate();

            final String dir = System.getProperty("user.dir");
            String fileName = dir + "\\others\\System.properties";
            //String fileName = "C:\\Users\\pc5\\Desktop\\Sudeep IMP FILES\\SystemProperties\\System.properties";
            String line = null;
            String oldText = "";
            String stringValueOfTheme = "";

            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                stringValueOfTheme = line.substring(8, 10);
                System.out.println("SubString-->>" + stringValueOfTheme);
                oldText += line + "\r\n";
            }
            bufferedReader.close();

            String newtext = "";
            if (choice.equalsIgnoreCase("01")) {
                newtext = oldText.replaceAll(stringValueOfTheme, choice);
            } else if (choice.equalsIgnoreCase("02")) {
                newtext = oldText.replaceAll(stringValueOfTheme, choice);
            } else if (choice.equalsIgnoreCase("03")) {
                newtext = oldText.replaceAll(stringValueOfTheme, choice);
            } else if (choice.equalsIgnoreCase("04")) {
                newtext = oldText.replaceAll(stringValueOfTheme, choice);
            } else if (choice.equalsIgnoreCase("05")) {
                newtext = oldText.replaceAll(stringValueOfTheme, choice);
            } else if (choice.equalsIgnoreCase("06")) {
                newtext = oldText.replaceAll(stringValueOfTheme, choice);
            } else if (choice.equalsIgnoreCase("07")) {
                newtext = oldText.replaceAll(stringValueOfTheme, choice);
            } else if (choice.equalsIgnoreCase("08")) {
                newtext = oldText.replaceAll(stringValueOfTheme, choice);
            } else if (choice.equalsIgnoreCase("09")) {
                newtext = oldText.replaceAll(stringValueOfTheme, choice);
            } else if (choice.equalsIgnoreCase("10")) {
                newtext = oldText.replaceAll(stringValueOfTheme, choice);
            }
            writer = new FileWriter(fileName);
            writer.write(newtext);
            writer.close();
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Settings Saved Successfully,Please Restart Your Application");
        } catch (IOException ex) {
            Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_buttonApplyActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        // TODO add your handling code here:
        AllAdSuMuDiSettingsNew1.this.dispose();
    }//GEN-LAST:event_buttonExitActionPerformed

    private void jTextFieldPaginationValueKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPaginationValueKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
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
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldPaginationValueKeyTyped

    private void txtCompanyContact1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompanyContact1KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '-') {
            evt.consume();
        }
        int flag = 0;
        String f = txtCompanyContact1.getText().trim();
        int i = 0;
        while (i < f.length()) {
            if (f.charAt(i) == '-') {
                flag = 1;
                break;
            }
            i++;
        }
        if (flag == 1 && c == '-') {
            evt.consume();
        }
    }//GEN-LAST:event_txtCompanyContact1KeyTyped

    private void NUButtonDeleteUser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NUButtonDeleteUser1ActionPerformed
        // TODO add your handling code here:
        if (flag_For_Component_enable_Disable_User_Settings == false && !user_ID_Delete.toString().trim().equals("")) {

            List<UserSettingDTO> userSettingList = new ArrayList<UserSettingDTO>();
            UserSettingDTO userSettingDTO = new UserSettingDTO();
            userSettingDTO.setUser_id(user_ID_Delete);
            userSettingList.add(userSettingDTO);
            gen.mainclass.UserSettingDAO.Delete_Users(userSettingList);
            user_ID_Delete = "";
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Selected User Deleted");
            intialise_User_Settings();
        }
    }//GEN-LAST:event_NUButtonDeleteUser1ActionPerformed

    private void acrylStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_acrylStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_acrylStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LogoPanelView1;
    private javax.swing.JButton NUButtonBack1;
    private javax.swing.JButton NUButtonBack3;
    private javax.swing.JButton NUButtonCreateUser1;
    private javax.swing.JButton NUButtonCreateUser3;
    private javax.swing.JButton NUButtonDeleteUser1;
    private javax.swing.JPanel Panel_Database_Splitng;
    private javax.swing.JCheckBox acryl;
    private javax.swing.JCheckBox aero;
    private javax.swing.JCheckBox aluminium;
    private javax.swing.JPanel applicationSettingsPanel;
    private javax.swing.JPanel applicationSettingsPanel1;
    private javax.swing.JPanel applicationSettingsPanel2;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBackUP_Company_Back_UP;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnCompanyCreate1;
    private javax.swing.JButton btnDataSpliting_Database_Splitng;
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnNewUser1;
    private javax.swing.JButton btnPath_Company_Back_UP;
    private javax.swing.JButton btnRestore_Company_Restore;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.JButton btncompanyClear1;
    private javax.swing.JButton buttonApply;
    private javax.swing.JButton buttonApply_Company_Settings;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonExit_Company_Delete;
    private javax.swing.JButton buttonExit_Company_Settings;
    private javax.swing.JButton buttonFilePath_Company_Restore;
    private javax.swing.JPanel createUserPanel1;
    private javax.swing.JPanel createUserPanel3;
    private javax.swing.JCheckBox fast;
    private com.toedter.calendar.JDateChooser fromJDateChooser_Database_Splitng;
    private javax.swing.JCheckBox graphite;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonApply;
    private javax.swing.JButton jButtonEMailOK2;
    private javax.swing.JButton jButtonEnableDisableOK;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonParticularOK;
    private javax.swing.JButton jButtonStart;
    private javax.swing.JButton jButtonStop;
    private javax.swing.JCheckBox jCheckBoxAmount;
    private javax.swing.JCheckBox jCheckBoxDisableEMail;
    private javax.swing.JCheckBox jCheckBoxDisableSMSSend;
    private javax.swing.JCheckBox jCheckBoxEnableEMail;
    private javax.swing.JCheckBox jCheckBoxEnableSMSSend;
    private javax.swing.JCheckBox jCheckBoxEnableSMSSendWithAsk;
    private javax.swing.JCheckBox jCheckBoxQuantity;
    private javax.swing.JCheckBox jCheckBoxStockItemName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel jPanelPreview;
    private javax.swing.JPasswordField jPasswordFieldPassword;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextField jTextFieldPaginationValue;
    private javax.swing.JTextField jTextFieldSender;
    private javax.swing.JLabel lbl_Current_User_Role;
    private javax.swing.JLabel lblcompany_ID1;
    private javax.swing.JCheckBox luna;
    private javax.swing.JCheckBox macwin;
    private javax.swing.JCheckBox mint;
    private javax.swing.JRadioButton optA4;
    private javax.swing.JRadioButton optA5;
    private javax.swing.JRadioButton optAsk;
    private javax.swing.JRadioButton optExcel;
    private javax.swing.JRadioButton optHtml;
    private javax.swing.JRadioButton optNo;
    private javax.swing.JRadioButton optPdf;
    private javax.swing.JRadioButton optWord;
    private javax.swing.JRadioButton optYes;
    private javax.swing.JRadioButton radio_User_Type_Admin_1;
    private javax.swing.JRadioButton radio_User_Type_Admin_3;
    private javax.swing.JRadioButton radio_User_Type_NormalUser1;
    private javax.swing.JRadioButton radio_User_Type_NormalUser3;
    private javax.swing.JCheckBox smart;
    private javax.swing.JTable tableCompanyBackUp_Company_Back_UP;
    private javax.swing.JTable tableCompanyList_Company_Back_UP;
    private javax.swing.JTable tableCompanyList_Company_Restore;
    private javax.swing.JTable tableCompanyList_Database_Splitng;
    private javax.swing.JTable tableCompanyRestoreUp_Company_Restore;
    private javax.swing.JTable tablecompanyList_Company_Delete;
    private javax.swing.JTable tablecompanyList_Company_Settings;
    private javax.swing.JTable tblAllocatedRole_CurrentUser;
    private javax.swing.JTable tblCompany_Allocated_Current_User_Settings;
    private javax.swing.JTable tblCurrent_Company_For_Current_User_Settings;
    private javax.swing.JTable tblUser_Available_Company;
    private javax.swing.JTable tblUser_Details_for_Admin;
    private javax.swing.JTable tblUser_Selected_Company;
    private javax.swing.JTable tblavailableRole;
    private javax.swing.JTable tblselectedRole;
    private javax.swing.JCheckBox texture;
    private javax.swing.JTextArea txtCompanyAddress1;
    private javax.swing.JTextField txtCompanyAlias1;
    private javax.swing.JTextField txtCompanyCSTNo1;
    private javax.swing.JTextField txtCompanyContact1;
    private javax.swing.JTextArea txtCompanyDeclaration1;
    private javax.swing.JTextField txtCompanyITN1;
    private javax.swing.JTextField txtCompanyMail1;
    private javax.swing.JTextField txtCompanyName1;
    private javax.swing.JTextField txtCompanySTN1;
    private javax.swing.JTextField txtCompanySignature1;
    private javax.swing.JTextArea txtCompanyTagLine1;
    private javax.swing.JTextArea txtCompanyTermsConditions1;
    private javax.swing.JTextField txtDestinationPath_Company_Back_UP;
    private javax.swing.JTextField txtNameOfGod1;
    private javax.swing.JTextField txtSourcePath_Company_Restore;
    private javax.swing.JPasswordField txtUser_ConfirmPassword1;
    private javax.swing.JTextField txtUser_EmailId1;
    private javax.swing.JPasswordField txtUser_Email_Password1;
    private javax.swing.JTextField txtUser_Name1;
    private javax.swing.JPasswordField txtUser_Password1;
    private javax.swing.JPasswordField txt_Current_User_ConfirmPassword;
    private javax.swing.JTextField txt_Current_User_EmailId;
    private javax.swing.JPasswordField txt_Current_User_Email_Password;
    private javax.swing.JTextField txt_Current_User_Name;
    private javax.swing.JPasswordField txt_Current_User_Password;
    // End of variables declaration//GEN-END:variables

    private void set_Value_CheckBox() {
        map_checkBoxValue.clear();
        map_checkBoxValue = gen.other.AdSuMuDiSettings.AdSuMuDiSettingsDAO.getting_CheckBoxValue();
        jTextFieldPaginationValue.setText(map_checkBoxValue.get(gen.other.AdSuMuDiSettings.AdSuMuDiConstant.PAGINATION));
    }
    //  private boolean hide_flag = false;

    private static DefaultComboBoxModel getSuggestedModel1(java.util.List<String> list, String text) {
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

    private static DefaultComboBoxModel getSuggestedModel2(java.util.List<String> list, String text) {
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

    private static DefaultComboBoxModel getSuggestedModel3(java.util.List<String> list, String text) {
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
                Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
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
            System.out.println("ViewEditCompany----->>imageloading -->>source: " + source);
            File inputFile = new File(source);
            try {
                mImage = ImageIO.read(inputFile);
            } catch (IOException ex) {
                //Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
            }

            scaledImage = mImage.getScaledInstance(LogoPanelView1.getWidth(), LogoPanelView1.getHeight(), Image.SCALE_SMOOTH);
            scaledImageIcon = new ImageIcon(scaledImage);
            FileInputStream fin = null;
            try {

                final String dir = System.getProperty("user.dir");

                Connection conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();



                File f1 = new File(source);//new File(dir + "/images/unnati_hinges_packing1_1.jpg");

                fin = new FileInputStream(f1);

                b1 = new byte[(int) f1.length()];
                fin.read(b1);
                fin.close();

                //  int ret = st.executeUpdate("insert into registration(image) values('"+b1+"')");
                // System.out.println("ViewEditCompany----->>imageloading -->>return: "+ ret);
            } catch (Exception e) {
                System.out.println("Exception for---->>Image--->" + e);
            }
//    int x=Integer.parseInt(""+LogoPanelView.getAlignmentX());
//    int y=Integer.parseInt(""+LogoPanelView.getAlignmentY());
//    
//    int h=mImage.getTileHeight();
//    int w=mImage.getTileWidth();

            //LogoPanelView.setBorder(new TempClassForImage(scaledImage,LogoPanelView.getSize()));
            LogoPanelView1.setIcon(scaledImageIcon);
            // LogoPanelView.setBounds(x, y, w, h);
            LogoPanelView1.show();


        }

        String filechoose() {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));

            chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                public boolean accept(File f) {
                    name = f.getName().toLowerCase();
                    return name.endsWith(".gif") || name.endsWith(".jpg")
                            || name.endsWith(".jpeg") || f.isDirectory();
                }

                public String getDescription() {
                    return "Image files";
                }
            });

            int r = chooser.showOpenDialog(AllAdSuMuDiSettingsNew1.this);



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
//	System.out.println("ALL ADSUMUDI SETTINGS _-----------------------------" + gen.other.AdSuMuDiSettings.AdSuMuDiConstant.map_Company_and_ID.keySet());
//
//	companySettingsDTO = gen.other.AdSuMuDiSettings.AdSuMuDiConstant.getCurrentCompany_Details("");
//	if (companySettingsDTO.getCompany_name().trim().equals("")) {
//	    System.out.println("IF -----------------------------------------------" + gen.other.AdSuMuDiSettings.AdSuMuDiConstant.map_Company_and_ID.isEmpty());
//	    jTabbedPane1.setEnabledAt(0, false);
//	    jTabbedPane1.setEnabledAt(1, false);
//	    jTabbedPane1.setEnabledAt(2, false);
//	    jTabbedPane1.setEnabledAt(3, false);
//	    // jTabbedPane1.setEnabledAt(4, false);
//	} else {
//	    System.out.println("Else -----------------------------------------------" + gen.other.AdSuMuDiSettings.AdSuMuDiConstant.map_Company_and_ID.isEmpty());
//	    jTabbedPane1.setEnabledAt(0, true);
//	    jTabbedPane1.setEnabledAt(1, true);
//	    jTabbedPane1.setEnabledAt(2, true);
//	    jTabbedPane1.setEnabledAt(3, true);
//	    //  jTabbedPane1.setEnabledAt(4, true);
//	}
    }

//////////////// Company Settings Extra Code ////////////////////////////////////////
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
        setColumnWidth_Company_Settings(tablecompanyList_Company_Settings);
        buttonApply_Company_Settings.setVisible(false);
    }

    private void setnemonic_Company_Settings() {
        buttonApply_Company_Settings.setMnemonic(KeyEvent.VK_A);
        buttonExit_Company_Settings.setMnemonic(KeyEvent.VK_E);
    }

    private void bindDTOTOGUI_Company_Settings() {
//	gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = "";
        // get Company_List
        companySettingsDTOList_Company_Settings = CompanySettingsDAO.getComapany_List("", "");
        tableModelCompanyList_Company_Settings.setRowCount(0);
        for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList_Company_Settings) {
            tableModelCompanyList_Company_Settings.setRowCount(tableModelCompanyList_Company_Settings.getRowCount() + 1);
            // set into the table
            tableModelCompanyList_Company_Settings.setValueAt(companySettingsDTO.getCompany_name(), tableModelCompanyList_Company_Settings.getRowCount() - 1, 0);
            tableModelCompanyList_Company_Settings.setValueAt(companySettingsDTO.getCompany_id(), tableModelCompanyList_Company_Settings.getRowCount() - 1, 1);
        }

    }

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
        passedTable.getColumnModel().getColumn(1).setMinWidth(70);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(70);
    }
    ////////////////////////////////// Company Settings Extra Code End ////////////////////////////////////////
    ////////////////////////////////// Company Back UP ////////////////////////////////////////

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
        tableCompanyList_Company_Back_UP.setModel(tablemodelCompany_Company_Back_UP);
        JTableHeader header = tableCompanyList_Company_Back_UP.getTableHeader();
        header.setBackground(Color.yellow);
        tablemodelCompany_Company_Back_UP.setRowCount(0);
        tablemodelCompany_Company_Back_UP.setColumnCount(2);

        String col1[] = {"CompanyName", ""};
        String data1[][] = {{"", ""}};
        tablemodelBackUPCompany_Company_Back_UP = new DefaultTableModel(data1, col1) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tableCompanyBackUp_Company_Back_UP.setModel(tablemodelBackUPCompany_Company_Back_UP);
        JTableHeader header1 = tableCompanyBackUp_Company_Back_UP.getTableHeader();
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
        company_Information_DTOList_Company_Back_UP = CompanySettingsDAO.getComapany_List("", "");

        int rowCount = 0;
        for (CompanySettingsDTO companySettingsDTO : company_Information_DTOList_Company_Back_UP) {

            if (!"information_schema".equalsIgnoreCase(companySettingsDTO.getCompany_name()) && !"performance_schema".equalsIgnoreCase(companySettingsDTO.getCompany_name())) {
                rowCount = tableCompanyList_Company_Back_UP.getRowCount();
                tablemodelCompany_Company_Back_UP.setRowCount(rowCount + 1);
                tableCompanyList_Company_Back_UP.setValueAt(companySettingsDTO.getCompany_name(), rowCount, 0);
                tableCompanyList_Company_Back_UP.setValueAt(companySettingsDTO.getCompany_id().toString(), rowCount, 1);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++     " + companySettingsDTO.getCompany_name());

            }
        }
        tableCompanyList_Company_Back_UP.requestFocus();
        tableCompanyList_Company_Back_UP.changeSelection(0, 0, false, false);
    }

    //take a back up
    public static boolean backupDB_Company_Back_UP(String dbName, String dbUserName, String dbPassword, String path) {
        Boolean flag = false;
        Process runtimeProcess;

        String executeCmd1 = "D:/MetalCopies/10-10-2013uploadcopy/server-Copy(2)/bin/mysqldump -u " + dbUserName + " -p" + dbPassword + " " + dbName + " -r " + path;
        String executeCmd = installedServerPath + "/server/bin/mysqldump -u " + dbUserName + " -p" + dbPassword + " " + dbName + " -r " + path;

        System.out.println("PAth Getting      --------------------------- " + executeCmd1);
        System.out.println("PAth Getting BY regedit --------------------------- " + executeCmd);

        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            System.out.println("processComplete-->>" + processComplete);
            if (processComplete == 0) {
                String cmd = installedServerPath + "/server/bin/mysqldump.exe exit";   //exit mysql

                System.out.println("Server Path +++++++++++++  " + cmd);

                Process proc1 =
                        proc1 = Runtime.getRuntime().exec(cmd);
                int processComplete1 = proc1.waitFor();
                if (processComplete1 == 0) {
                    System.out.println("Backup 111111111    created successfully");
                } else {
                    System.out.println("Backup111111111     created successfully");
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
        /// create .SQL file with fileName of database.enc,database,company_name
        company_SQLTxtFile_List_Company_Back_UP = textFilesforSQL_Company_Back_UP(fileChooser_Company_Back_UP);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filepath + "All_Database_information.txt", "UTF-8");

            // all rows of tableCompanyBackUp
            for (int i = 0; i < tableCompanyBackUp_Company_Back_UP.getRowCount(); i++) {

                // all data of company_Information_DTOList
                for (CompanySettingsDTO companySettingsDTO : company_Information_DTOList_Company_Back_UP) {

//		    if (backUP_Company_DTOList.get(i).equals(companySettingsDTO.getCompany_name())) {
                    if (companySettingsDTO.getCompany_name().equals(tableCompanyBackUp_Company_Back_UP.getValueAt(i, 0).toString().trim()) && companySettingsDTO.getCompany_id().toString().equals(tableCompanyBackUp_Company_Back_UP.getValueAt(i, 1).toString().trim())) {
                        // add comapny name in text file
                        // space "$" to distingiush Name with ID
                        writer.print("$" + companySettingsDTO.getCompany_name());
                        // add database name  in text file 
                        // space "$" to distingiush Name with ID
                        writer.print("$" + companySettingsDTO.getCompany_DatabaseName());
                        // add company ID in text file
                        writer.print("$" + companySettingsDTO.getCompany_id());

                        for (String company_file_Name : company_SQLTxtFile_List_Company_Back_UP) {
                            String database_name = "";
                            StringTokenizer st = new StringTokenizer(company_file_Name, ".");
                            while (st.hasMoreTokens()) {
                                database_name = st.nextToken();
                                break;
                            }

                            if (database_name.equals(companySettingsDTO.getCompany_DatabaseName())) {
                                // add file name where databse present in text file
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
            ecipher_Company_Back_UP = Cipher.getInstance("AES/CBC/PKCS5Padding");
            dcipher_Company_Back_UP = Cipher.getInstance("AES/CBC/PKCS5Padding");
            ecipher_Company_Back_UP.init(Cipher.ENCRYPT_MODE, key1, paramSpec);
            dcipher_Company_Back_UP.init(Cipher.DECRYPT_MODE, key1, paramSpec);

            // Encrypt  the .SQL files
            // all rows of tableCompanyBackUp
            for (int i = 0; i < tableCompanyBackUp_Company_Back_UP.getRowCount(); i++) {
                // all data of company_Information_DTOList
                for (CompanySettingsDTO companySettingsDTO : company_Information_DTOList_Company_Back_UP) {
//		    if (backUP_Company_DTOList.get(i).equals(companySettingsDTO.getCompany_name())) {
                    if (companySettingsDTO.getCompany_name().equals(tableCompanyBackUp_Company_Back_UP.getValueAt(i, 0).toString().trim()) && companySettingsDTO.getCompany_id().toString().equals(tableCompanyBackUp_Company_Back_UP.getValueAt(i, 1).toString().trim())) {
                        encrypt_Company_Back_UP(new FileInputStream(fileChooser_Company_Back_UP + "/" + companySettingsDTO.getCompany_DatabaseName() + ".sql"), new FileOutputStream(fileChooser_Company_Back_UP + "/" + companySettingsDTO.getCompany_DatabaseName() + ".enc"));
                    }
                }
            }
            // Encrypt the file of All All_Database_information.txt
            encrypt_Company_Back_UP(new FileInputStream(fileChooser_Company_Back_UP + "/" + "All_Database_information" + ".txt"), new FileOutputStream(fileChooser_Company_Back_UP + "/" + "All_Database_information" + ".enc"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File Not Found:" + e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
            System.out.println("Invalid Alogorithm Parameter:" + e.getMessage());
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
            Path target = Paths.get(fileChooser_Company_Back_UP + "/" + "ffdfdsgfd" + ".abc");
            if (Files.exists(target)) {
                Files.delete(target);
            }

            zipArchieveName_Company_Back_UP = fileChooser_Company_Back_UP + "/" + "ffdfdsgfd.abc";
            // Reference to the file we will be adding to the zipfile
            BufferedInputStream origin = null;
            // Reference to zip file
            FileOutputStream dest = new FileOutputStream(zipArchieveName_Company_Back_UP);
            // Wrap our destination zipfile with a ZipOutputStream
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
            // Create a byte[] buffer that we will read data from the source
            // files into and then transfer it to the zip file
            byte[] data = new byte[BUFFER_SIZE];
            List files = new ArrayList();

            company_List_Enc_Company_Back_UP = textFilesforEnc_Company_Back_UP(fileChooser_Company_Back_UP);
            for (int i = 0; i < company_List_Enc_Company_Back_UP.size(); i++) {
                System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFF" + company_List_Enc_Company_Back_UP.get(i));
                files.add(fileChooser_Company_Back_UP + "/" + company_List_Enc_Company_Back_UP.get(i));
            }
            // Iterate over all of the files in list
            for (Iterator i = files.iterator(); i.hasNext();) {
                // Get a BufferedInputStream that we can use to read the source file
                String filename = (String) i.next();
                FileInputStream fi = new FileInputStream(filename);
                origin = new BufferedInputStream(fi, BUFFER_SIZE);
                // Setup the entry in the zip file
                ZipEntry entry = new ZipEntry(filename);
                out.putNextEntry(entry);
                // Read data from the source file and write it out to the zip file
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
            for (int i = 0; i < tableCompanyBackUp_Company_Back_UP.getRowCount(); i++) {
                // all data of company_Information_DTOList
                for (CompanySettingsDTO companySettingsDTO : company_Information_DTOList_Company_Back_UP) {
                    if (companySettingsDTO.getCompany_name().equals(tableCompanyBackUp_Company_Back_UP.getValueAt(i, 0).toString().trim()) && companySettingsDTO.getCompany_id().toString().equals(tableCompanyBackUp_Company_Back_UP.getValueAt(i, 1).toString().trim())) {
                        Path target1 = Paths.get(fileChooser_Company_Back_UP + "/" + companySettingsDTO.getCompany_DatabaseName() + ".enc");
                        Files.deleteIfExists(target1);
                    }
                }
            }

            // delete the file of All_Database_information.Enc 
            Path target5 = Paths.get(fileChooser_Company_Back_UP + "/" + "All_Database_information" + ".enc");
            if (Files.exists(target5)) {
                Files.deleteIfExists(target5);
            }

            // delete the file of All_Database_information.txt 
            Path target2 = Paths.get(fileChooser_Company_Back_UP + "/" + "All_Database_information" + ".txt");
            if (Files.exists(target2)) {
                try {
                    // proc.WaitForExit(); 
                    Files.deleteIfExists(target2);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // delete the file of .SQL files 
            // all rows of tableCompanyBackUp
            for (int i = 0; i < tableCompanyBackUp_Company_Back_UP.getRowCount(); i++) {
                // all data of company_Information_DTOList
                for (CompanySettingsDTO companySettingsDTO : company_Information_DTOList_Company_Back_UP) {
                    if (companySettingsDTO.getCompany_name().equals(tableCompanyBackUp_Company_Back_UP.getValueAt(i, 0).toString().trim()) && companySettingsDTO.getCompany_id().toString().equals(tableCompanyBackUp_Company_Back_UP.getValueAt(i, 1).toString().trim())) {
                        Path target1 = Paths.get(fileChooser_Company_Back_UP + "/" + companySettingsDTO.getCompany_DatabaseName() + ".sql");
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
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Enter valid path");
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
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Enter valid path");
        }

        return textFiles;
    }

    private Boolean validation_Company_Back_UP() {
        Boolean flag = true;
        if (tableCompanyBackUp_Company_Back_UP.getRowCount() == 0 || tableCompanyBackUp_Company_Back_UP.getRowCount() < 0) {
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "First Select Company To Bck UP");
            flag = false;
            btnPath_Company_Back_UP.requestFocus();
        } else if (txtDestinationPath_Company_Back_UP.getText().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "First Select Destination Folder for  Back UP");
            flag = false;
            btnPath_Company_Back_UP.requestFocus();
        }
//	else if (!txtDestinationPath_Company_Back_UP.getText().toString().trim().isEmpty()) {
//	    String path = txtDestinationPath_Company_Back_UP.getText().toString().trim();
//	    System.out.println("Validation path for Back UP"+path);
//	    StringTokenizer token = new StringTokenizer(path," ");
//	    
//	    System.out.println("Has a token s ------------- "+token.hasMoreTokens());
//	    
//	    if (token.hasMoreTokens()) {
//		JOptionPane.showMessageDialog(null, "Selected Destination Folder Contain Spaces which is Not Allowed ");
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

        passedTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(380);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(380);

        passedTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(1).setMinWidth(70);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(70);

    }
    ////////////////// Company Back UP over////////////////////////////////////////////////// 
    ////////////////// Company Restore ////////////////////////////////////////////////// 

    private void initialise_Company_Restore() {
        setActiveInactiveComponent_Company_Restore();
        intilisecomponent_Company_Restore();
        setnmenomics_Company_Restore();
    }

    private void setnmenomics_Company_Restore() {
        buttonFilePath_Company_Restore.setMnemonic(KeyEvent.VK_F);
        btnRestore_Company_Restore.setMnemonic(KeyEvent.VK_R);
    }

    private void setActiveInactiveComponent_Company_Restore() {
        jLabel2.setVisible(false);

        String col[] = {"CompanyName", ""};
        String data[][] = {{"", ""}};
        tablemodelCompany_Company_Restore = new DefaultTableModel(data, col) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tableCompanyList_Company_Restore.setModel(tablemodelCompany_Company_Restore);
        JTableHeader header = tableCompanyList_Company_Restore.getTableHeader();
        header.setBackground(Color.yellow);
        tablemodelCompany_Company_Restore.setRowCount(0);
        tablemodelCompany_Company_Restore.setColumnCount(2);

        String col1[] = {"CompanyName", ""};
        String data1[][] = {{"", ""}};
        tablemodelRestoreCompany_Company_Restore = new DefaultTableModel(data1, col1) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tableCompanyRestoreUp_Company_Restore.setModel(tablemodelRestoreCompany_Company_Restore);
        JTableHeader header1 = tableCompanyRestoreUp_Company_Restore.getTableHeader();
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
                String zipFilePath = fileChooser_Company_Restore + "/ffdfdsgfd.abc";
                String destDirectory = "C:/Check";
                UnzipUtility unzipper = new UnzipUtility();
                try {
                    unZip_Company_File_List_Company_Restore = unzipper.unzip(zipFilePath, destDirectory);
                } catch (Exception ex) {
                    // some errors occurred
                    ex.printStackTrace();
                }


                // decryptfiles("D:/databasecheck/All_Database_information", ".enc");
                decryptfiles_Company_Restore(fileChooser_Company_Restore + "/" + "All_Database_information", ".enc", null);

                if (validation_For_All_SQLCompany_File_Company_Restore()) {
                    List<String> selected_List = new ArrayList<String>();
                    /// read Company Name present in file
                    BufferedReader brin;
                    brin = new BufferedReader(new FileReader(fileChooser_Company_Restore + "/" + "All_Database_information" + ".txt"));
                    String line = brin.readLine();
                    //   int count = 0;
                    while (line != null) {
                        if (!line.equals("")) {
                            Company_InformationDTO company_InformationDTO = new Company_InformationDTO();
                            String databaseName = line;
                            String last_name = "";
                            StringTokenizer st = new StringTokenizer(databaseName, "$");
                            int i = 0;
                            while (st.hasMoreTokens() && i == 0) {
                                last_name = st.nextToken();
                                switch (i) {
                                    case 0:
                                        company_InformationDTO.setCompany_Name(last_name);
                                        selected_List.add(last_name);
                                    case 1:
                                        company_InformationDTO.setCompany_Database_Name(st.nextToken());
                                    case 2:
                                        company_InformationDTO.setCompany_Unique_ID(st.nextToken());
                                    case 3:
                                        company_InformationDTO.setCompany_SQL_File_Name(st.nextToken());
                                }

                                i++;
                            }
                            company_InformationDTOList_Company_Restore.add(company_InformationDTO);
                        }
                        line = brin.readLine();
                    }
                    brin.close();

                    int rowCount = 0;
                    tablemodelCompany_Company_Restore.setRowCount(rowCount);

//		    for (int i = 0; i < selected_List.size(); i++) {
//			if (!"information_schema".equalsIgnoreCase(selected_List.get(i)) && !"performance_schema".equalsIgnoreCase(selected_List.get(i))) {
//			    rowCount = tableCompanyList.getRowCount();
//			    tablemodelCompany_Company_Restore.setRowCount(rowCount + 1);
//			    tableCompanyList.setValueAt(selected_List.get(i), rowCount, 0);
//			    tableCompanyList.setValueAt(selected_List.get(i), rowCount, 1);
//			}
//		    }

                    for (Company_InformationDTO company_InformationDTO : company_InformationDTOList_Company_Restore) {
//			if (!"information_schema".equalsIgnoreCase(selected_List.get(i)) && !"performance_schema".equalsIgnoreCase(selected_List.get(i))) {
                        rowCount = tableCompanyList_Company_Restore.getRowCount();
                        tablemodelCompany_Company_Restore.setRowCount(rowCount + 1);
                        tableCompanyList_Company_Restore.setValueAt(company_InformationDTO.getCompany_Name(), rowCount, 0);
                        tableCompanyList_Company_Restore.setValueAt(company_InformationDTO.getCompany_Unique_ID(), rowCount, 1);
//			}
                        System.out.println("Daabase from company_InformationDTO ---------- " + company_InformationDTO.getCompany_Database_Name());
                    }



                    tableCompanyList_Company_Restore.requestFocus();
                    tableCompanyList_Company_Restore.changeSelection(0, 0, false, false);

                    // Delete Extra Created .ENC files
                    for (int i = 0; i < unZip_Company_File_List_Company_Restore.size(); i++) {
                        Path target5 = Paths.get(fileChooser_Company_Restore + "/" + unZip_Company_File_List_Company_Restore.get(i));
                        if (Files.exists(target5)) {
                            Files.delete(target5);
                        }
                    }

                    // Delete Extra Created .txt files
                    Path target5 = Paths.get(fileChooser_Company_Restore + "/" + "All_Database_information" + ".txt");
                    if (Files.exists(target5)) {
                        Files.delete(target5);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                Logger
                        .getLogger(AllAdSuMuDiSettingsNew1.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static boolean restoreDB_Company_Restore(String dbName, String dbUserName, String dbPassword, String source) {

//	String[] executeCmd = new String[]{"D:/MetalCopies/10-10-2013uploadcopy/server-Copy(2)/bin/mysql", "--user=" + dbUserName, "--password=" + dbPassword, dbName, "-e", "source " + source};
        String[] executeCmd = new String[]{installedServerPath + "/server/bin/mysql", "--user=" + dbUserName, "--password=" + dbPassword, dbName, "-e", "source " + source};
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
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Enter valid path");
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
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Enter valid path");
        }
        return textFiles;
    }

    private void decryptfiles_Company_Restore(String calling, String Ext, List<String> unZip_Comapny_File_List) {
        try {
            byte[] encoded = "1234567890098765".getBytes();
            SecretKey key1 = new SecretKeySpec(encoded, "AES");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
            dcipher_Company_Restore = Cipher.getInstance("AES/CBC/PKCS5Padding");
            dcipher_Company_Restore.init(Cipher.DECRYPT_MODE, key1, paramSpec);

            if (calling.isEmpty()) {
                for (int i = 0; i < unZip_Comapny_File_List.size(); i++) {
                    String only_Company_File_Name = "";
                    StringTokenizer only_Company_File_Token = new StringTokenizer(unZip_Comapny_File_List.get(i), ".");
                    while (only_Company_File_Token.hasMoreTokens()) {
                        only_Company_File_Name = only_Company_File_Token.nextToken();
                        break;
                    }

                    company_SQLFile_List_Restore.add(only_Company_File_Name + ".sql");

                    if ((fileChooser_Company_Restore + "/" + unZip_Comapny_File_List.get(i).toString()).equals(fileChooser_Company_Restore + "/" + "All_Database_information.enc")) {
                        decrypt_Company_Restore(new FileInputStream(fileChooser_Company_Restore + "/" + unZip_Comapny_File_List.get(i)), new FileOutputStream(fileChooser_Company_Restore + "/" + only_Company_File_Name + ".txt"));
                    } else {
                        decrypt_Company_Restore(new FileInputStream(fileChooser_Company_Restore + "/" + unZip_Comapny_File_List.get(i)), new FileOutputStream(fileChooser_Company_Restore + "/" + only_Company_File_Name + ".sql"));
                    }
                }
            } else {
                decrypt_Company_Restore(new FileInputStream(calling + Ext), new FileOutputStream(calling + ".txt"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File Not Found:" + e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
            System.out.println("Invalid Alogorithm Parameter:" + e.getMessage());
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
            CipherInputStream cis = new CipherInputStream(is, dcipher_Company_Restore);
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

    private void map_Database_FileName_Company_Restore(List<String> unZip_Company_File_List) {
        try {
            /// read Company Name present in All Unzipe file one by one
            BufferedReader brin;
            for (int j = 0; j < unZip_Company_File_List.size(); j++) {

                // Not for All_Database_information.enc file
                if (!unZip_Company_File_List.get(j).equals("All_Database_information.enc")) {
                    String only_Company_File_Name = "";
                    // find FileName of SQL
                    StringTokenizer only_Company_File_Token = new StringTokenizer(unZip_Company_File_List.get(j), ".");
                    // find FileName of SQL and save in only_Company_File_Name
                    while (only_Company_File_Token.hasMoreTokens()) {
                        only_Company_File_Name = only_Company_File_Token.nextToken();
                        break;
                    }

                    // select the file 
                    brin = new BufferedReader(new FileReader(fileChooser_Company_Restore + "/" + only_Company_File_Name + ".sql"));
                    String line = brin.readLine();
                    int count = 0;

                    // Read the database present in .SQL file so read only first 5 rows by using count 
                    while (line != null && count < 5) {
                        // if line is not empty
                        if (!line.equals("")) {
                            String full_Line = line;
                            String dataBase_Name = "";
                            // if line is second then only read full line
                            if (count == 2) {
                                StringTokenizer st = new StringTokenizer(full_Line, ":");
                                while (st.hasMoreTokens()) {
                                    dataBase_Name = st.nextToken();
                                }

                                // List from Databse
                                for (CompanySettingsDTO company_NameandDatabaseDTO : company_Full_InformationDTOList_Company_Restore) {
                                    // check Database Name from File and DAtabse is equal or not
                                    System.out.println("Company Database Nmae from database ::::::::::::" + company_NameandDatabaseDTO.getCompany_DatabaseName());
                                    System.out.println("Company Database Nmae from file     ::::::::::::" + company_NameandDatabaseDTO.getCompany_DatabaseName());
                                    if (company_NameandDatabaseDTO.getCompany_DatabaseName().equals(dataBase_Name.trim())) {
                                        // company_List_Restore_Company_Restore 
                                        for (CompanySettingsDTO companySettingsDTO : company_List_Restore_Company_Restore) {
                                            // check Compamny Nmae and 
                                            if (company_NameandDatabaseDTO.getCompany_name().trim().equals(companySettingsDTO.getCompany_name()) && company_NameandDatabaseDTO.getCompany_id().toString().trim().equals(companySettingsDTO.getCompany_id().toString())) {
                                                if (!dataBase_Name.isEmpty()) {
                                                    //    map_Database_And_File_Name.put(last_name, unZip_Company_File_List_Company_Restore.get(j));
                                                    Company_InformationDTO company_InformationDTO = new Company_InformationDTO();
                                                    company_InformationDTO.setCompany_Name(company_NameandDatabaseDTO.getCompany_name());
                                                    company_InformationDTO.setCompany_Database_Name(company_NameandDatabaseDTO.getCompany_DatabaseName());
                                                    company_InformationDTO.setCompany_SQL_File_Name(unZip_Company_File_List.get(j));
                                                    company_InformationDTO.setCompany_Unique_ID(company_NameandDatabaseDTO.getCompany_id().toString());

                                                    company_InformationDTO_Final_List_Company_Restore.add(company_InformationDTO);
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
            Logger
                    .getLogger(AllAdSuMuDiSettingsNew1.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(AllAdSuMuDiSettingsNew1.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Boolean validation_Company_Restore() {
        Boolean flag = true;
        System.out.println("Table Row cCount ------------------------" + tableCompanyRestoreUp_Company_Restore.getRowCount());
        if (!txtSourcePath_Company_Restore.getText().toString().trim().isEmpty() && tableCompanyList_Company_Restore.getRowCount() == 0) {
            Path target5 = Paths.get(fileChooser_Company_Restore + "/ffdfdsgfd.abc");
            if (!Files.exists(target5)) {
                flag = false;
                JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Select Correct Path As No Company for Selection");
                buttonFilePath_Company_Restore.requestFocus();
            }
        } else if (tableCompanyRestoreUp_Company_Restore.getRowCount() == 0 || tableCompanyRestoreUp_Company_Restore.getRowCount() < 0) {
            System.out.println("erwerwerewrewrew");
            flag = false;
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Select Company First To Restore");
            buttonFilePath_Company_Restore.requestFocus();
        }
        return flag;
    }

    private Boolean validation_For_All_SQLCompany_File_Company_Restore() {
        Boolean flag = true;

        Path target5 = Paths.get(fileChooser_Company_Restore + "/" + "All_Database_information" + ".txt");
        if (!Files.exists(target5)) {
            flag = false;
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "S                 for Selection");
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

        passedTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(350);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(350);

        passedTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(1).setMinWidth(80);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(80);

    }
    ////////////////// Company Restore  over////////////////////////////////////////////////// 
    ////////////////// Company Spliting  ///////////////////////////////////////////////// 

    private void initialise_Database_Spliting() {
        // initialise componenet 
        intilisecomponent_Database_Splitng();
        bindData2GUT_Database_Splitng();
        setnemonic_Database_Splitng();
    }

    // set nemonic
    private void setnemonic_Database_Splitng() {
        btnDataSpliting_Database_Splitng.setMnemonic(KeyEvent.VK_S);
    }

    // initialise componenet 
    private void intilisecomponent_Database_Splitng() {

        tablemodelCompany_Database_Splitng = (DefaultTableModel) tableCompanyList_Database_Splitng.getModel();
        tablemodelCompany_Database_Splitng.setRowCount(0);
        tablemodelCompany_Database_Splitng.setColumnCount(2);


        fromJDateChooser_Database_Splitng.setDateFormatString("dd-MM-yyyy");
        Calendar currentDate = Calendar.getInstance();
        fromJDateChooser_Database_Splitng.setDate(currentDate.getTime());

        fromJDateChooser_Database_Splitng.setDateFormatString(Constants.DATE_FORMAT_STRING);
        tfDatePicker_Database_Splitng = (JTextField) fromJDateChooser_Database_Splitng.getComponent(1);

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

        tfDatePicker_Database_Splitng.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, tfDatePicker_Database_Splitng);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, ex.getMessage());
                }
            }
        });

        tfDatePicker_Database_Splitng.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
                tfDatePicker_Database_Splitng.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }
        });

    }

    // BindToDataToGui
    private void bindData2GUT_Database_Splitng() {
        try {
            bindDataToTableCompany_Database_Splitng();




        } catch (SQLException ex) {
            Logger.getLogger(AllAdSuMuDiSettingsNew1.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    // set all Companies to vector
    private void bindDataToTableCompany_Database_Splitng() throws SQLException {
        company_ListDTO_Database_Splitng.clear();
        // set all_company_List to  company_ListDTO_Database_Splitng
        company_ListDTO_Database_Splitng = CompanySettingsDAO.getComapany_List("", "");
        //vectorCompanyList.clear();
        tablemodelCompany_Database_Splitng.setRowCount(0);
        for (CompanySettingsDTO companySettingsDTO : company_ListDTO_Database_Splitng) {
            int row = tablemodelCompany_Database_Splitng.getRowCount() + 1;
            tablemodelCompany_Database_Splitng.setRowCount(row);
            //  Do not add Database
            tableCompanyList_Database_Splitng.setValueAt(companySettingsDTO.getCompany_name(), row - 1, 0);
            tableCompanyList_Database_Splitng.setValueAt(companySettingsDTO.getCompany_id(), row - 1, 1);

        }
        setColumnWidth_Database_Splitng(tableCompanyList_Database_Splitng);

    }

    // Take BackUp
    public static boolean backupDB_Database_Splitng(String dbName, String dbUserName, String dbPassword, String path) {
        Process runtimeProcess;

//	String executeCmd = "D:/MetalCopies/10-10-2013uploadcopy/server-Copy(2)/bin/mysqldump -u " + dbUserName + " -p" + dbPassword + " " + dbName + " -r " + path;
        String executeCmd = installedServerPath + "/server/bin/mysqldump -u " + dbUserName + " -p" + dbPassword + " " + dbName + " -r " + path;

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
    public static boolean restoreDB_Database_Splitng(String dbName, String dbUserName, String dbPassword, String source) {
        Boolean flag = false;
//	String[] executeCmd = new String[]{"D:/MetalCopies/10-10-2013uploadcopy/server-Copy(2)/bin/mysql", "--user=" + dbUserName, "--password=" + dbPassword, dbName, "-e", "source " + source};
        String[] executeCmd = new String[]{installedServerPath + "/server/bin/mysql", "--user=" + dbUserName, "--password=" + dbPassword, dbName, "-e", "source " + source};
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
        if (selected_Company_Name_Database_Splitng.toString().trim().equals("") || selected_Company_Name_Database_Splitng.toString().trim() == null) {
            //validation for Company Name is valid or not  
            tfDatePicker_Database_Splitng.requestFocus();
            flag = false;
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Select Exits Company");
        }
        if (fromJDateChooser_Database_Splitng.getDate() == null) {
            //validation for date is valid or not  
            tfDatePicker_Database_Splitng.requestFocus();
            flag = false;
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Invalid Date Selection");
        }
        return flag;
    }

    // call Company Selection form after Database spliting is completed
    private void call_Selection_Companies_Database_Splitng() {
        gen.other.CompanySettings.CompanysSettings company_selection = new gen.other.CompanySettings.CompanysSettings("Company Selection");
        try {
            company_selection.setVisible(true);
            company_selection.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AllAdSuMuDiSettingsNew1.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        AllAdSuMuDiSettingsNew1.this.getParent().add(company_selection);
        AllAdSuMuDiSettingsNew1.this.getParent().setVisible(true);
        Dimension desktopSize = AllAdSuMuDiSettingsNew1.this.getParent().getSize();
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

        for (int i = 0;
                i < actions.length;
                i++) {
            north.removeMouseMotionListener(actions[i]);
        }
    }

    private void setColumnWidth_Database_Splitng(JTable passedTable) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.LEFT);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        passedTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(500);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(500);

        passedTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(1).setMinWidth(80);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(80);
    }
////////////////// Company Spliting  over////////////////////////////////////////////////// 
////////////////// Company Alter ////////////////////////////////////////////////// 

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
        tablecompanyList_Company_Delete.setModel(tableModelCompanyList_Company_Delete);
        JTableHeader header = tablecompanyList_Company_Delete.getTableHeader();
        header.setBackground(Color.yellow);
        tableModelCompanyList_Company_Delete.setRowCount(0);
        tableModelCompanyList_Company_Delete.setColumnCount(2);
        setColumnWidth_Company_Delete(tablecompanyList_Company_Delete);
    }

    private void setnemonic_Company_Delete() {
        btnDelete1.setMnemonic(KeyEvent.VK_A);
        buttonExit_Company_Delete.setMnemonic(KeyEvent.VK_E);
        txtCompanyName1.requestFocus();
    }

    private void bindDTOTOGUI_Company_Delete() {
//	gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = "";
        // get Company_List
        companySettingsDTOList_Company_Delete = CompanySettingsDAO.getComapany_List("", "");
        tableModelCompanyList_Company_Delete.setRowCount(0);
        for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList_Company_Delete) {
            tableModelCompanyList_Company_Delete.setRowCount(tableModelCompanyList_Company_Delete.getRowCount() + 1);
            // set into the table
            tableModelCompanyList_Company_Delete.setValueAt(companySettingsDTO.getCompany_name(), tableModelCompanyList_Company_Delete.getRowCount() - 1, 0);
            tableModelCompanyList_Company_Delete.setValueAt(companySettingsDTO.getCompany_id(), tableModelCompanyList_Company_Delete.getRowCount() - 1, 1);
        }
    }

    private void setColumnWidth_Company_Delete(JTable passedTable) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.LEFT);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        passedTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(300);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(300);

        passedTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
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

            String query = "select company_image from tblcompaniesimage where company_name = '" + company_Name + "' and company_id = " + company_ID + "";
            System.out.println("************************************** " + query);
            PreparedStatement prmt50 = con.prepareStatement(query);
            ResultSet rs20 = prmt50.executeQuery();
            while (rs20.next()) {

                Image img = null;
                BufferedImage mImage1 = null;
                byte[] bytes = null;
                System.out.println("Set icon ---------------------------------");
//		    Blob blob = rs20.getBlob("company_image");
//		    InputStream in = blob.getBinaryStream();
//		    mImage1 = ImageIO.read(in);
                //byte[] imgdata=rs.getBytes("image");
                if (gen.dto.Constants.DATABASE_SERVER.equals("com.mysql.jdbc.Driver")) {
                    bytes = rs20.getBytes("company_image");
                } else {
                    bytes = IOUtils.toByteArray((rs20.getClob("company_image")).getAsciiStream());
                    System.out.println("Image ----------------------------------------" + bytes);
                }
//		bytes = rs20.getBytes("company_image");
                img = Toolkit.getDefaultToolkit().createImage(bytes);

                //  Image scaledImage=img.getScaledInstance(LogoPanelView.getWidth(), LogoPanelView.getHeight(), Image.SCALE_SMOOTH);  
                //   Image scaledImage=img.getScaledInstance(LogoPanelView.getWidth(), LogoPanelView.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(img);
                Image img1 = icon.getImage();
                Image newimg = img.getScaledInstance(151, 150, Image.SCALE_SMOOTH);
                ImageIcon newicon = new ImageIcon(newimg);
                LogoPanelView1.setIcon(newicon);
            }

            con.close();
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";

        } catch (SQLException ex) {
            Logger.getLogger(AllAdSuMuDiSettingsNew1.class
                    .getName()).log(Level.SEVERE, null, ex);
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

            q = "select * from tblcompanyinfo where company_name ='" + company_Name + "' and company_id = " + company_ID + "";
            resultSet = st.executeQuery(q);
            while (resultSet.next()) {
                String name = resultSet.getString("company_name");
                company_Cliked_Name = name;
                company_Cliked_Database_Name = resultSet.getString("company_database");
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
                //String LBTNumber = resultSet.getString("LBTNo");
                //txt.setText(LBTNumber);
                //txtLBTNumber.setEnabled(false);
            }
            conn.commit();
            conn.close();
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(AllAdSuMuDiSettingsNew1.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
///////////////////////////////////////////////////////////////////////////
    /// create tables in Newly Created Database From generic Database.
    // Take BackUp

    public static boolean backupDB_Database_Delete(String dbName, String dbUserName, String dbPassword, String path) {
        Process runtimeProcess;

//	String executeCmd = "D:/MetalCopies/10-10-2013uploadcopy/server-Copy(2)/bin/mysqldump -u " + dbUserName + " -p" + dbPassword + " " + dbName + " -r " + path;
        String executeCmd = installedServerPath + "/server/bin/mysqldump -u " + dbUserName + " -p" + dbPassword + " " + dbName + " -r " + path;

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
    public static boolean restoreDB_Database_Delete(String dbName, String dbUserName, String dbPassword, String source) {
        Boolean flag = false;
//	String[] executeCmd = new String[]{"D:/MetalCopies/10-10-2013uploadcopy/server-Copy(2)/bin/mysql", "--user=" + dbUserName, "--password=" + dbPassword, dbName, "-e", "source " + source};
        String[] executeCmd = new String[]{installedServerPath + "/server/bin/mysql", "--user=" + dbUserName, "--password=" + dbPassword, dbName, "-e", "source " + source};
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
////////////////// Company Alter  over////////////////////////////////////////////////// 

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
                    Logger.getLogger(AllAdSuMuDiSettingsNew1.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = database_Name;
            System.out.println("DAtabase ---------------                   " + database_Name);
            Connection conn1 = DatabaseConnection1.GetConnection();
            conn1.setAutoCommit(false);
            // delete already exist image
	  /*  
             * PreparedStatement prmt = conn1.prepareStatement("delete from registration");
             prmt.executeUpdate();


             PreparedStatement pre1 = conn1.prepareStatement("insert into registration(image) values(?)");
             if (source != null) {
             pre1.setBinaryStream(1, fin1, (int) imgfile1.length());
             } else {
             pre1.setString(1, "");
             }
             pre1.executeUpdate();
             */

            conn1.commit();

            ////////// complted ///////////////////////////////////////////


        } catch (SQLException ex) {
            Logger.getLogger(AllAdSuMuDiSettingsNew1.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startEmptyWaitTimerForFrame(JInternalFrame frame, String database_Name) {
        //Image img = new javax.swing.ImageIcon(dir + "/images/Kasturi-logo-1.png").getImage();
        //((java.awt.Frame) dialog.getOwner()).setIconImage(img);
        System.out.println("DDir ----------------------------------" + dir);
        optionPane.setIcon(new ImageIcon(new javax.swing.ImageIcon(dir + "/images/Thinfadingline.gif").getImage()));
        optionPane.setMessage("Wait..  Company is Creating...");
        dialog.add(optionPane);
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
                flag = restoreDB_Database_Delete(database_Name, "root", "adm", "D:/exe.sql");
                i++;
            }
        } else {
            gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = database_Name;
            create_tables();
            gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = "";
        }

        // insert image into companies database table for printing purpose.
        insert_Imagein_Company_Database(database_Name);
        ////////// complted ///////////////////////////////////////////

//	gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = "";
        frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        dialog.setVisible(false);
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));

        JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Company Successfully Created");
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
    public static void filterCharacter(java.awt.event.KeyEvent evt, javax.swing.JTextField jtxtField) {
        char c = evt.getKeyChar();
        if (!Character.isLetterOrDigit(c)) {
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

    /// user creation validation //////////////////////////////////////////////////////////////
    private Boolean Validation_For_User_Creation() {
        Boolean flag = true;

        String email = txtUser_EmailId1.getText().toString().trim();
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);


        String ledger_name = txtUser_Name1.getText().trim();
        Pattern p = Pattern.compile("[a-zA-Z0-9]");
        Matcher m = p.matcher(ledger_name);

        if (!m.find()) {

            flag = false;
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Enter Valid UserName");
            txtUser_Name1.requestFocus();

        } else if (txtUser_Name1.getText().toString().trim().equals("")) {

            flag = false;
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Enter UserName");
            txtUser_Name1.requestFocus();

        } else if (txtUser_Password1.getText().toString().trim().equals("")) {

            flag = false;
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Enter Password");
            txtUser_Password1.requestFocus();

        } else if (!txtUser_Password1.getText().toString().trim().equals(txtUser_ConfirmPassword1.getText().toString().trim())) {

            flag = false;
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Password MisMatch");
            txtUser_Password1.requestFocus();

        } else if ((!matcher.matches() && !txtUser_EmailId1.getText().toString().trim().equals(""))) {

            flag = false;
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Enter Valid Email ID");
            txtUser_EmailId1.requestFocus();

        } else if (txtUser_EmailId1.getText().toString().trim().equals("")) {

            if (!txtUser_Email_Password1.toString().trim().isEmpty()) {
                flag = false;
                JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "First Enter Email ID");
                txtUser_EmailId1.requestFocus();
            }

//	} else if (!mapRoleandID.containsKey(tf_Role_User_Settings.getText().toLowerCase().toString())) {
        } else if (tblselectedRole.getRowCount() == 0 || tblselectedRole.getRowCount() < 0) {

            flag = false;
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Select valid Role");
//	    tf_Role_User_Settings.requestFocus();

        } else if (!radio_User_Type_Admin_1.isSelected() && !radio_User_Type_NormalUser1.isSelected()) {

            flag = false;
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Please select User Type");
            radio_User_Type_Admin_1.requestFocus();

        } else if (tblUser_Selected_Company.getRowCount() == 0 || tblUser_Selected_Company.getRowCount() < 0) {

            flag = false;
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Please select Company");

        } else if (!txtUser_Name1.getText().toString().trim().equals("") && flag_For_Component_enable_Disable_User_Settings.equals(true)) {
            for (UserSettingDTO usersettingDTO : user_SettingsDTOList_User_Settings) {
                if (usersettingDTO.getUser_Name().equals(txtUser_Name1.getText().toString().trim())) {
                    flag = false;
                    JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Already User present With this User Name");
                    txtUser_Name1.requestFocus();
                    break;
                }
            }
        }
//	else if(!user_Cliked_For_Update.equals(txtUser_Name1.getText().toString().trim()) && flag_For_Component_enable_Disable_User_Settings.equals(false)){
//		for (UserSettingDTO usersettingDTO : user_SettingsDTOList_User_Settings) {
//		    System.out.println("Check Conditoin value ---------------- " + usersettingDTO.getUser_Name().equals(txtUser_Name1.getText().toString().trim()));
//		    if (usersettingDTO.getUser_Name().equals(txtUser_Name1.getText().toString().trim())) {
//			flag = false;
//			JOptionPane.showMessageDialog(null, "Already User present With this User Name");
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
        tablemodel_Company_Available_User_Settings = new DefaultTableModel(data, col) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tblUser_Available_Company.setModel(tablemodel_Company_Available_User_Settings);
        JTableHeader header = tblUser_Available_Company.getTableHeader();
        header.setBackground(Color.yellow);
        tablemodel_Company_Available_User_Settings.setRowCount(0);
        tablemodel_Company_Available_User_Settings.setColumnCount(2);


        String col_for_Selected_Companies[] = {"CompanyName", ""};
        String data_for_Selected_Companies[][] = {{"", ""}};
        tablemodel_Company_Selected_User_Settings = new DefaultTableModel(data_for_Selected_Companies, col_for_Selected_Companies) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tblUser_Selected_Company.setModel(tablemodel_Company_Selected_User_Settings);
        JTableHeader header1 = tblUser_Selected_Company.getTableHeader();
        header1.setBackground(Color.yellow);
        tablemodel_Company_Selected_User_Settings.setRowCount(0);
        tablemodel_Company_Selected_User_Settings.setColumnCount(2);
        setColumnWidth_Users_Settings(tblUser_Available_Company);
        setColumnWidth_Users_Settings(tblUser_Selected_Company);


        String col_for_User_details[] = {"User Name"};
        String data_for_User_details[][] = {{""}};
        tablemodel_User_Details_Settings = new DefaultTableModel(data_for_User_details, col_for_User_details) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tblUser_Details_for_Admin.setModel(tablemodel_User_Details_Settings);
        JTableHeader header_for_User_details = tblUser_Details_for_Admin.getTableHeader();
        header_for_User_details.setBackground(Color.yellow);
        tablemodel_User_Details_Settings.setRowCount(0);
        tablemodel_User_Details_Settings.setColumnCount(1);
        //setColumnWidth_Users_Settings(tblUser_Details_for_Admin);


        String col_for_Role_Available[] = {"Avaliable Role"};
        String data_for_Role_Available[][] = {{""}};
        tablemodel_AvailableRole_User_Settings = new DefaultTableModel(data_for_Role_Available, col_for_Role_Available) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tblavailableRole.setModel(tablemodel_AvailableRole_User_Settings);
        JTableHeader header_for_available_role = tblavailableRole.getTableHeader();
        header_for_available_role.setBackground(Color.yellow);
        tablemodel_AvailableRole_User_Settings.setRowCount(0);
        tablemodel_AvailableRole_User_Settings.setColumnCount(1);
        //setColumnWidth_Roles_Users_Settings(tblavailableRole);


        String col_for_Role_Selected[] = {"Selected Role"};
        String data_for_Role_Selected[][] = {{""}};
        tablemodel_SelectedRole_User_Settings = new DefaultTableModel(data_for_Role_Selected, col_for_Role_Selected) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tblselectedRole.setModel(tablemodel_SelectedRole_User_Settings);
        JTableHeader header_for_Selected_role = tblselectedRole.getTableHeader();
        header_for_Selected_role.setBackground(Color.yellow);
        tablemodel_SelectedRole_User_Settings.setRowCount(0);
        tablemodel_SelectedRole_User_Settings.setColumnCount(1);
        //setColumnWidth_Roles_Users_Settings(tblselectedRole);
    }

    private void setnemonic_User_Settings() {
        NUButtonCreateUser1.setMnemonic(KeyEvent.VK_C);
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
                    tablemodel_AvailableRole_User_Settings.setRowCount(tablemodel_AvailableRole_User_Settings.getRowCount() + 1);
                    tablemodel_AvailableRole_User_Settings.setValueAt(str, tablemodel_AvailableRole_User_Settings.getRowCount() - 1, 0);
                }
            }
            mapRoleandID = Util.getSmallCaseMap(mapRoleandID);

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private UserSettingDTO bindGUItoDTO() {
        UserSettingDTO userSettingDTO = new UserSettingDTO();
        userSettingDTO.setUser_id(userSettingDTO_Class_Level.getUser_id());
        userSettingDTO.setUser_Name(txtUser_Name1.getText().toString().trim());
        userSettingDTO.setUser_Password(txtUser_Password1.getText().toString().trim());
        userSettingDTO.setUser_Email_ID(txtUser_EmailId1.getText().toString().trim());
        userSettingDTO.setUser_Email_Password(txtUser_Email_Password1.getText().toString().trim());

        String choosen_Role = "";
//	choosen_Role = tf_Role_User_Settings.getText().toString().trim();

//	int role_id = 0;
//	role_id = Integer.parseInt(mapRoleandID.get(tf_Role_User_Settings.getText().toString().trim().toLowerCase()));

        List<FeaturesDTO> featuresDTOList = new ArrayList<FeaturesDTO>();
        for (int i = 0; i < tblselectedRole.getRowCount(); i++) {
            FeaturesDTO featuresDTO = new FeaturesDTO();

            String role = tblselectedRole.getValueAt(i, 0).toString().trim().toLowerCase();
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

        int user_Type = 0;
        if (radio_User_Type_Admin_1.isSelected()) {
            user_Type = 1;
        } else if (radio_User_Type_NormalUser1.isSelected()) {
            user_Type = 2;
        }
        userSettingDTO.setUser_Type(user_Type);
        return userSettingDTO;
    }

    private void bindDTOTOGUI_User_Settings() {

        try {
            int selectet_Row = tblUser_Details_for_Admin.getSelectedRow();

            if (selectet_Row > -1) {
                String user_Name = tblUser_Details_for_Admin.getValueAt(selectet_Row, 0).toString().trim();
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
//		tf_Role_User_Settings.setText(role);

                //Set Data to table Selected role
                List<FeaturesDTO> featuresDTOList = new ArrayList<FeaturesDTO>();
                featuresDTOList = gen.mainclass.UserSettingDAO.getRoleByUsers(userSettingDTO_Class_Level.getUser_id());
                tablemodel_SelectedRole_User_Settings.setRowCount(0);
                for (FeaturesDTO featuresDTO : featuresDTOList) {
                    tablemodel_SelectedRole_User_Settings.setRowCount(tablemodel_SelectedRole_User_Settings.getRowCount() + 1);
                    tablemodel_SelectedRole_User_Settings.setValueAt(featuresDTO.getRole_Name(), tablemodel_SelectedRole_User_Settings.getRowCount() - 1, 0);
                }
		
                // remove companies which are present in selected_company_table from available_company table
                for (int i = 0; i < tblselectedRole.getRowCount(); i++) {
                    // company_nmae and company_id from seletecd table
                    String company_Name = tblselectedRole.getValueAt(i, 0).toString();
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


                // call table available with all campany List as we already  remove selected company from this list
                intilise_DATA_User_Settings();

                List<CompanySettingsDTO> companySettingsDTOList = new ArrayList<CompanySettingsDTO>();
                companySettingsDTOList = gen.mainclass.UserSettingDAO.getCurrent_Company_Details_By_User(userSettingDTO_Class_Level);

                tablemodel_Company_Selected_User_Settings.setRowCount(0);
                for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList) {
                    tablemodel_Company_Selected_User_Settings.setRowCount(tablemodel_Company_Selected_User_Settings.getRowCount() + 1);
                    tablemodel_Company_Selected_User_Settings.setValueAt(companySettingsDTO.getCompany_name(), tablemodel_Company_Selected_User_Settings.getRowCount() - 1, 0);
                    tablemodel_Company_Selected_User_Settings.setValueAt(companySettingsDTO.getCompany_id(), tablemodel_Company_Selected_User_Settings.getRowCount() - 1, 1);
                }

                // remove companies which are present in selected_company_table from available_company table
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
    }

    private void bindDTOTOUser_Details_table() {
        try {
            // ADD into Table User Deatils
            user_SettingsDTOList_User_Settings = gen.mainclass.UserSettingDAO.get_User_Details("", "");
            tablemodel_User_Details_Settings.setRowCount(0);
            for (UserSettingDTO usersettingDTO : user_SettingsDTOList_User_Settings) {
                tablemodel_User_Details_Settings.setRowCount(tablemodel_User_Details_Settings.getRowCount() + 1);
                tablemodel_User_Details_Settings.setValueAt(usersettingDTO.getUser_Name(), tablemodel_User_Details_Settings.getRowCount() - 1, 0);
//		tablemodel_User_Details_Settings.setValueAt(usersettingDTO.getUser_id(), tablemodel_User_Details_Settings.getRowCount() - 1, 1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(AllAdSuMuDiSettingsNew1.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void claerfield_User_Settings() {
        txtUser_Name1.setText("");
        txtUser_ConfirmPassword1.setText("");
        txtUser_Password1.setText("");
        txtUser_EmailId1.setText("");
        txtUser_Email_Password1.setText("");
//	tf_Role_User_Settings.setText("");
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

        Collections.sort(
                roleVector,
                new Comparator<String>() {
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

        passedTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
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

        passedTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(120);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(120);

    }

    /// Stop user Settings  creation validation //////////////////////////////////////////////////////////////
    ///////////////////////   Current User Call /////////////////////////////////////////////////////////////
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
        tablemodel_Company_Allocated_Current_User_Settings = new DefaultTableModel(data, col) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tblCompany_Allocated_Current_User_Settings.setModel(tablemodel_Company_Allocated_Current_User_Settings);
        JTableHeader header = tblCompany_Allocated_Current_User_Settings.getTableHeader();
        header.setBackground(Color.yellow);
        tablemodel_Company_Allocated_Current_User_Settings.setRowCount(0);
        tablemodel_Company_Allocated_Current_User_Settings.setColumnCount(2);


        String col_Current_Company_Current_User[] = {"CompanyName", ""};
        String data_Current_Company_Current_User[][] = {{"", ""}};
        tablemodel_Current_Company_For_Current_User_Settings = new DefaultTableModel(data_Current_Company_Current_User, col_Current_Company_Current_User) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tblCurrent_Company_For_Current_User_Settings.setModel(tablemodel_Current_Company_For_Current_User_Settings);
        JTableHeader header_Current_Company_Current_User = tblCurrent_Company_For_Current_User_Settings.getTableHeader();
        header_Current_Company_Current_User.setBackground(Color.yellow);
        tablemodel_Current_Company_For_Current_User_Settings.setRowCount(0);
        tablemodel_Current_Company_For_Current_User_Settings.setColumnCount(2);

        String col_for_Role_Selected_CurrentUser[] = {"Allocated Role"};
        String data_for_Role_Selected_CurrentUser[][] = {{""}};
        tablemodel_AllocatedRole_CurrentUser = new DefaultTableModel(data_for_Role_Selected_CurrentUser, col_for_Role_Selected_CurrentUser) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tblAllocatedRole_CurrentUser.setModel(tablemodel_AllocatedRole_CurrentUser);
        JTableHeader header_for_Selected_role = tblAllocatedRole_CurrentUser.getTableHeader();
        header_for_Selected_role.setBackground(Color.yellow);
        tablemodel_AllocatedRole_CurrentUser.setRowCount(0);
        tablemodel_AllocatedRole_CurrentUser.setColumnCount(1);
        setColumnWidth_Roles_Users_Settings(tblAllocatedRole_CurrentUser);

        setColumnWidth_Current_Users_Settings(tblCompany_Allocated_Current_User_Settings);
        setColumnWidth_Current_Users_Settings(tblCurrent_Company_For_Current_User_Settings);
        setColumnWidth_Allocated_Roles_Current_Users_Settings(tblAllocatedRole_CurrentUser);
    }

    private void intiliase_DATA_Current_User_Settings() {
        user_SettingsDTOList_Update_User_Settings = gen.mainclass.UserSettingDAO.get_User_Details("", "");
        List<UserSettingDTO> userSettingDTOList = gen.mainclass.UserSettingDAO.get_User_Details(gen.dto.Constants.CURRENT_USER_ID.toString(), "");

        for (UserSettingDTO userSettingDTO : userSettingDTOList) {
            txt_Current_User_Name.setText(userSettingDTO.getUser_Name());
            txt_Current_User_Password.setText(userSettingDTO.getUser_Password());
            txt_Current_User_ConfirmPassword.setText(userSettingDTO.getUser_Password());
            txt_Current_User_EmailId.setText(userSettingDTO.getUser_Email_ID());
            txt_Current_User_Email_Password.setText(userSettingDTO.getUser_Email_Password());

            String role = "";
            for (Map.Entry<String, String> e : mapRoleandID.entrySet()) {

                if (e.getValue().equals("" + userSettingDTO.getUser_Role())) {
                    role = e.getKey();
                }

            }
            lbl_Current_User_Role.setText(role);
            //Set Data to table Selected role
            List<FeaturesDTO> featuresDTOList = new ArrayList<FeaturesDTO>();
            featuresDTOList = gen.mainclass.UserSettingDAO.getRoleByUsers(userSettingDTO.getUser_id());
            tablemodel_AllocatedRole_CurrentUser.setRowCount(0);
            for (FeaturesDTO featuresDTO : featuresDTOList) {
                tablemodel_AllocatedRole_CurrentUser.setRowCount(tablemodel_AllocatedRole_CurrentUser.getRowCount() + 1);
                tablemodel_AllocatedRole_CurrentUser.setValueAt(featuresDTO.getRole_Name(), tablemodel_AllocatedRole_CurrentUser.getRowCount() - 1, 0);
            }

            List<CompanySettingsDTO> companySettingsDTOList = new ArrayList<CompanySettingsDTO>();
            companySettingsDTOList = gen.mainclass.UserSettingDAO.getCurrent_Company_Details_By_User(userSettingDTO);

            tablemodel_Company_Allocated_Current_User_Settings.setRowCount(0);
            for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList) {
                tablemodel_Company_Allocated_Current_User_Settings.setRowCount(tablemodel_Company_Allocated_Current_User_Settings.getRowCount() + 1);
                tablemodel_Company_Allocated_Current_User_Settings.setValueAt(companySettingsDTO.getCompany_name(), tablemodel_Company_Allocated_Current_User_Settings.getRowCount() - 1, 0);
                tablemodel_Company_Allocated_Current_User_Settings.setValueAt(companySettingsDTO.getCompany_id(), tablemodel_Company_Allocated_Current_User_Settings.getRowCount() - 1, 1);
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

        passedTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(250);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(250);

        passedTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(1).setMinWidth(50);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(50);
    }

    private void setColumnWidth_Allocated_Roles_Current_Users_Settings(JTable passedTable) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.LEFT);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        passedTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(350);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(350);
    }

    private UserSettingDTO bindGUItoDTO_Current_Users() {
        UserSettingDTO userSettingDTO = new UserSettingDTO();
        userSettingDTO.setUser_id(gen.dto.Constants.CURRENT_USER_ID);
        userSettingDTO.setUser_Name(txt_Current_User_Name.getText().toString().trim());
        userSettingDTO.setUser_Password(txt_Current_User_Password.getText().toString().trim());
        userSettingDTO.setUser_Email_ID(txt_Current_User_EmailId.getText().toString().trim());
        userSettingDTO.setUser_Email_Password(txt_Current_User_Email_Password.getText().toString().trim());

        int user_Type = 0;
        if (radio_User_Type_Admin_1.isSelected()) {
            user_Type = 1;
        } else if (radio_User_Type_NormalUser1.isSelected()) {
            user_Type = 2;
        }
        userSettingDTO.setUser_Type(user_Type);
        return userSettingDTO;
    }

    private void intiliase_Current_Company_Current_User_Settings() {
        List<CompanySettingsDTO> companySettingsDTOList = new ArrayList<CompanySettingsDTO>();
        companySettingsDTOList = gen.mainclass.UserSettingDAO.getCurrentCompany_UserID(gen.dto.Constants.CURRENT_USER_ID);

        tablemodel_Current_Company_For_Current_User_Settings.setRowCount(0);
        for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList) {
            tablemodel_Current_Company_For_Current_User_Settings.setRowCount(tablemodel_Current_Company_For_Current_User_Settings.getRowCount() + 1);
            tablemodel_Current_Company_For_Current_User_Settings.setValueAt(companySettingsDTO.getCompany_name(), tablemodel_Current_Company_For_Current_User_Settings.getRowCount() - 1, 0);
            tablemodel_Current_Company_For_Current_User_Settings.setValueAt(companySettingsDTO.getCompany_id(), tablemodel_Current_Company_For_Current_User_Settings.getRowCount() - 1, 1);
        }
    }
    ///////////////////////   Current User Call End/////////////////////////////////////////////////////////////

    private String getInstalledServerPath() {
        FindregeditEntry o = new FindregeditEntry();
//        String readRegistryEntry = o.read();
        installedServerPath = o.read();
        return installedServerPath;
    }
    ///////////////////////   Current User Call End/////////////////////////////////////////////////////////////

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
            String dir = System.getProperty("user.dir");
            String readLine = "";
            FileReader fileRead = new FileReader(dir + "\\others\\NetworkEMailSender.properties");
            BufferedReader bufferedRead = new BufferedReader(fileRead);

            String readEMail = "";
            int lineCounting = 1;
            while ((readLine = bufferedRead.readLine()) != null) {
                System.out.println("Reading NetworkEMailSettings readLine-->>>" + readLine);
                if (lineCounting == 1) {
                    readEMail = readLine;
                    System.out.println("readEMail---->>" + readEMail);
                }
                lineCounting++;
            }
            bufferedRead.close();
            jTextFieldSender.setText(readEMail);

            FileReader fileReadNew = new FileReader(dir + "\\others\\NetworkEMailPWD.properties");
            BufferedReader bufferedReadNew = new BufferedReader(fileReadNew);

            String readEMailPassword = "";
            int lineCountingCheck = 1;
            while ((readLine = bufferedReadNew.readLine()) != null) {
                System.out.println("Reading NetworkEMailSettings readLine-->>>" + readLine);
                if (lineCountingCheck == 1) {
                    readEMailPassword = readLine;
                    System.out.println("readEMailPassword---->>" + readEMailPassword);
                }
                lineCountingCheck++;
            }
            bufferedReadNew.close();
            jPasswordFieldPassword.setText(readEMailPassword);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void initialiseGraphicsSettings() {

        set_Value_CheckBox();

        buttonApply.setMnemonic(KeyEvent.VK_Y);
        buttonExit.setMnemonic(KeyEvent.VK_E);

        final String dir = System.getProperty("user.dir");
        String fileName = dir + "\\others\\System.properties";
        System.out.println("Path-->>>Today-->>>" + fileName);
        //String fileName = "C:\\Users\\pc5\\Desktop\\Sudeep IMP FILES\\SystemProperties\\System.properties";
        String line = null;
        int lineNo;
        String oldText = "";
        String stringValueOfTheme = "";

        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            LineNumberReader totalNumberOfLines = new LineNumberReader(new FileReader(new File(fileName)));
            totalNumberOfLines.skip(Long.MAX_VALUE);
            System.out.println("Total-->>" + totalNumberOfLines.getLineNumber());
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

        if (stringValueOfTheme.equalsIgnoreCase("01")) {
            texture.setSelected(true);
        } else if (stringValueOfTheme.equalsIgnoreCase("02")) {
            aero.setSelected(true);
        } else if (stringValueOfTheme.equalsIgnoreCase("03")) {
            aluminium.setSelected(true);
        } else if (stringValueOfTheme.equalsIgnoreCase("04")) {
            macwin.setSelected(true);
        } else if (stringValueOfTheme.equalsIgnoreCase("05")) {
            luna.setSelected(true);
        } else if (stringValueOfTheme.equalsIgnoreCase("06")) {
            acryl.setSelected(true);
        } else if (stringValueOfTheme.equalsIgnoreCase("07")) {
            mint.setSelected(true);
        } else if (stringValueOfTheme.equalsIgnoreCase("08")) {
            fast.setSelected(true);
        } else if (stringValueOfTheme.equalsIgnoreCase("09")) {
            graphite.setSelected(true);
        } else if (stringValueOfTheme.equalsIgnoreCase("10")) {
            smart.setSelected(true);
        }

    }

    public void initialiseDeviceAndHardwareSettings() {

        printSettingsInitilise();
        ResultSet rs;
        Connection conn = DatabaseConnection1.GetConnection();
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            rs = st.executeQuery("select * from  tblOtherSetting");
            if (rs.next()) {
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


        if (!m.find()) {
            flag = false;
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Enter Valid UserName");
            txt_Current_User_Name.requestFocus();
        } else if (txt_Current_User_Name.getText().toString().trim().equals("")) {

            flag = false;
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Enter UserName");
            txt_Current_User_Name.requestFocus();

        } else if (txt_Current_User_Password.getText().toString().trim().equals("")) {

            flag = false;
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Enter Password");
            txt_Current_User_Password.requestFocus();

        } else if (!txt_Current_User_Password.getText().toString().trim().equals(txt_Current_User_ConfirmPassword.getText().toString().trim())) {

            flag = false;
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Password MisMatch");
            txt_Current_User_Password.requestFocus();

        } else if ((!matcher.matches() && !txt_Current_User_EmailId.getText().toString().trim().equals(""))) {

            flag = false;
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Enter Valid Email ID");
            txt_Current_User_EmailId.requestFocus();

        } else if (txt_Current_User_EmailId.getText().toString().trim().equals("")) {

            if (!txtUser_Email_Password1.toString().trim().isEmpty()) {
                flag = false;
                JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "First Enter Email ID");
                txt_Current_User_EmailId.requestFocus();
            }

//	} else if (!mapRoleandID.containsKey(tf_Role_User_Settings.getText().toLowerCase().toString())) {
        }
//	else if (!txt_Current_User_Name.getText().toString().trim().equals("")) {
//            for (UserSettingDTO usersettingDTO : user_SettingsDTOList_Update_User_Settings) {
//                if (usersettingDTO.getUser_Name().equals(txt_Current_User_Name.getText().toString().trim())) {
//                    flag = false;
//                    JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Already User present With this User Name");
//                    txt_Current_User_Name.requestFocus();
//                    break;
//                }
//            }
//        }

        int count = 0;
        for (UserSettingDTO usersettingDTO : user_SettingsDTOList_Update_User_Settings) {
            if (usersettingDTO.getUser_Name().equals(txt_Current_User_Name.getText().toString().trim())) {
                //flag = false;
                //JOptionPane.showMessageDialog(Currentuser.this, "Already User present With this User Name");
                //txt_Current_User_Name.requestFocus();
                count++;
//		break;
            }
        }

        if (count == 2) {
            flag = false;
            JOptionPane.showMessageDialog(AllAdSuMuDiSettingsNew1.this, "Already User present With this User Name");
            txt_Current_User_Name.requestFocus();
        }

        return flag;
    }

    private void create_tables() {
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            Statement s = conn.createStatement();

            s.execute("CREATE TABLE REGISTRATION (image  CLOB)");
            s.execute("CREATE TABLE tblaccountvouchersmaxid (salemaxid int DEFAULT NULL,purchasemaxid int DEFAULT NULL,receiptmaxid int DEFAULT NULL,paymentmaxid int DEFAULT NULL,challanmaxid int DEFAULT NULL,journalmaxid int DEFAULT NULL,contramaxid int DEFAULT NULL,field3 int DEFAULT NULL,field4 int DEFAULT NULL,field5 int DEFAULT NULL)");
            s.execute("insert into tblaccountvouchersmaxid (salemaxid,purchasemaxid, receiptmaxid,paymentmaxid,challanmaxid,contramaxid) values(1,1,1,1,1,1)");

            s.execute("CREATE TABLE tbladsumudisettings (name varchar(40) DEFAULT NULL,value tinyint DEFAULT NULL)");
            s.execute(" insert into tbladsumudisettings values('pagination','50')");

            s.execute("Create table tblcompanyinfo ( company_id    int  GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null  ,primary key (company_id) , company_name  varchar(500), company_database  varchar(500) , created_by     varchar(500) , modified_by    varchar(500) , created_date    date , modified_date   date , Alias   varchar(100) , Address    varchar(500) , ContactNo   varchar(100) , EmailId   varchar(200) , IncomeTaxNo   varchar(60) , SaleTaxNo    varchar(60)  , Declaration   varchar(800) , TagLine     varchar(700) , TermCnditions   varchar(1000) , NameOfGod   varchar(200) , SignAuthority    varchar(200) , VatTinNo   varchar(60) , Note    varchar(500), LBTNo   varchar(60) , Field1   varchar(60) , Field2   varchar(60) )");
            s.execute("  CREATE TABLE tbllogin (username varchar(60) NOT NULL,password varchar(60) DEFAULT NULL,new_entry int DEFAULT NULL,acc_vouchers int DEFAULT NULL,report int DEFAULT NULL,production int DEFAULT NULL,user_type int DEFAULT NULL,email_id varchar(80) DEFAULT NULL,email_pass varchar(60) DEFAULT NULL,PRIMARY KEY (username))");
            s.execute("  CREATE TABLE tblcurrentlogin (username varchar(80) DEFAULT NULL, FOREIGN KEY (username) REFERENCES tbllogin (username))");

            s.execute("  CREATE TABLE tblgroup (group_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null, PRIMARY KEY (group_id), group_name varchar(40) DEFAULT NULL,group_under int DEFAULT NULL,  group_alias varchar(40) DEFAULT NULL,  group_isDeletable tinyint DEFAULT NULL,  created_date date DEFAULT NULL,  modified_date date DEFAULT NULL,  modified_user int DEFAULT NULL,  created_user int DEFAULT NULL ) ");
            s.execute("INSERT INTO tblgroup VALUES (50,'Primary',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(54,'Current Liability',50,'',NULL,NULL,NULL,NULL,NULL),(55,'Current Assets',50,'',NULL,NULL,NULL,NULL,NULL),(56,'Suspense Account',50,'',NULL,NULL,NULL,NULL,NULL),(57,'Sundry Debtors',54,'',NULL,NULL,NULL,NULL,NULL),(58,'Sundry Creditors',54,'',NULL,NULL,NULL,NULL,NULL),(59,'Stock-In-Hand',55,'',NULL,NULL,NULL,NULL,NULL),(60,'Sales Account',50,'',NULL,NULL,NULL,NULL,NULL),(61,'Purchase Account',50,'',NULL,NULL,NULL,NULL,NULL),(62,'Loan Liability',50,'',NULL,NULL,NULL,NULL,NULL),(63,'Loans and Advances',55,'',NULL,NULL,NULL,NULL,NULL),(64,'Indirect Expenses',50,'',NULL,NULL,NULL,NULL,NULL),(65,'Indirect Income',50,'',NULL,NULL,NULL,NULL,NULL),(66,'Income (Direct)',50,'',NULL,NULL,NULL,NULL,NULL),(67,'Income (Indirect)',50,'',NULL,NULL,NULL,NULL,NULL),(68,'Expense (Direct)',50,'',NULL,NULL,NULL,NULL,NULL),(69,'Expense (Indirect)',50,'',NULL,NULL,NULL,NULL,NULL),(70,'Bank Account',55,'',NULL,NULL,NULL,NULL,NULL),(71,'Cash In Hand',55,'',NULL,NULL,NULL,NULL,NULL);");

            s.execute(" CREATE TABLE tbltransactionmain (trans_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,trans_receiptNo int DEFAULT NULL, trans_date date DEFAULT NULL,  trans_typeIndex int DEFAULT NULL,  trans_narration varchar(1000) DEFAULT NULL,  trans_grandtotal decimal(40,2) DEFAULT NULL,  trans_transport int DEFAULT NULL,  trans_lessBillAmt decimal(40,2) DEFAULT NULL,  trans_payment varchar(40) DEFAULT NULL,  created_date date DEFAULT NULL,  modified_date date DEFAULT NULL,  created_user int DEFAULT NULL,  modified_user int DEFAULT NULL,  PRIMARY KEY (trans_id))");
            s.execute(" CREATE TABLE tblinventorytransaction ( invtrans_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  trans_id int DEFAULT NULL,  invtrans_type int DEFAULT NULL,  dat date DEFAULT NULL,  PRIMARY KEY (invtrans_id), FOREIGN KEY (trans_id) REFERENCES tbltransactionmain (trans_id))");

            s.execute(" CREATE TABLE tblstockgroup ( sg_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null, sg_name varchar(60) DEFAULT NULL,  sg_alias varchar(40) DEFAULT NULL,  sg_under int DEFAULT NULL,  sg_lbtpercent decimal(20,2) DEFAULT NULL,  PRIMARY KEY (sg_id))");
            s.execute("insert into tblStockGroup(sg_name,sg_alias,sg_under) values('Primary','',0)");

            s.execute("  CREATE TABLE tblstockitemtype(type_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,type_name varchar(40) DEFAULT NULL,PRIMARY KEY (type_id)) ");
            s.execute("  CREATE TABLE tbluomtype ( uomType_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  uomType_name varchar(40) DEFAULT NULL,  PRIMARY KEY (uomType_id))");
            s.execute("INSERT INTO tbluomtype VALUES (1,'Simple'),(2,'Complex')");
            s.execute("  CREATE TABLE tblunitofmeasure(  uomType_id int NOT NULL,  uom_type varchar(50) DEFAULT NULL,  uom_symbol varchar(50) DEFAULT NULL,  uom_formalName varchar(50) DEFAULT NULL,  uom_noOfDecimalPts int DEFAULT NULL,  uom_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  PRIMARY KEY (uom_id),  FOREIGN KEY (uomType_id) REFERENCES tbluomtype (uomType_id))");
            s.execute("  CREATE TABLE tblstockitem(si_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  si_alias varchar(60) DEFAULT NULL,  si_under int DEFAULT NULL,  si_length decimal(40,2) DEFAULT NULL,  si_width decimal(40,2) DEFAULT NULL,  si_thickness decimal(40,2) DEFAULT NULL,  si_rate decimal(40,2) DEFAULT NULL,  si_unitOfMeasure int DEFAULT NULL,  si_openingBalance decimal(40,2) DEFAULT NULL,  si_unit varchar(40) DEFAULT NULL,  si_type int DEFAULT NULL,  si_name varchar(100) DEFAULT NULL,  PRIMARY KEY (si_id),  FOREIGN KEY (si_under) REFERENCES tblstockgroup (sg_id), FOREIGN KEY (si_type) REFERENCES tblstockitemtype (type_id),  FOREIGN KEY (si_unitOfMeasure) REFERENCES tblunitofmeasure (uom_id))");
            s.execute("  CREATE TABLE tblfinishtype (f_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,PRIMARY KEY (f_id),f_name varchar(60) DEFAULT NULL)");
            s.execute("  CREATE TABLE tblinventorytransactionitems (  invtrans_id int NOT NULL,  invtrans_qty decimal(40,2) DEFAULT NULL,  invtrans_rate decimal(40,2) DEFAULT NULL,  invtrans_amount decimal(40,2) DEFAULT NULL,  invtrans_totalSize decimal(40,2) DEFAULT NULL,  invtrans_index int DEFAULT NULL,  invtrans_itemId int DEFAULT NULL,  invtrans_finishType int DEFAULT NULL,  invtrans_length decimal(40,2) DEFAULT NULL,  invtrans_width decimal(40,2) DEFAULT NULL,  invtrans_thickness decimal(40,2) DEFAULT NULL,  invtrans_making decimal(40,2) DEFAULT NULL,    FOREIGN KEY (invtrans_id) REFERENCES tblinventorytransaction (invtrans_id),  FOREIGN KEY (invtrans_itemId) REFERENCES tblstockitem (si_id),   FOREIGN KEY (invtrans_finishType) REFERENCES tblfinishtype (f_id))");
            s.execute("  CREATE TABLE tblledger(ledger_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  ledger_name varchar(60) DEFAULT NULL,  ledger_under int DEFAULT NULL,  ledger_address varchar(200) DEFAULT NULL,  ledger_contactno varchar(40) DEFAULT NULL,  ledger_emailId varchar(100) DEFAULT NULL,  ledger_openingBalance decimal(40,2) DEFAULT NULL,  ledger_alias varchar(40) DEFAULT NULL,  opening_type int DEFAULT NULL,  dat date DEFAULT NULL,  ledger_inTaxo varchar(60) DEFAULT NULL,  ledger_saleTaxNo varchar(60) DEFAULT NULL,  created_date date DEFAULT NULL,  modified_date date DEFAULT NULL,  created_user int DEFAULT NULL,  modified_user int DEFAULT NULL, PRIMARY KEY (ledger_id),  FOREIGN KEY (ledger_under) REFERENCES tblgroup (group_id))");
            s.execute("  CREATE TABLE tblledgercreditlimit (  ledger_id int DEFAULT NULL,  ledger_limit decimal(40,2) DEFAULT NULL,   FOREIGN KEY (ledger_id) REFERENCES tblledger (ledger_id))	    ");
            s.execute(" CREATE TABLE tblledgercurrentbalance(  ledger_id int DEFAULT NULL,  ledger_currentBalance decimal(40,2) DEFAULT NULL,  ledger_DebitOrCredit int DEFAULT NULL,   FOREIGN KEY (ledger_id) REFERENCES tblledger (ledger_id)) ");
            s.execute(" CREATE TABLE tblstockitemcurrentbalance(  si_id int DEFAULT NULL,  si_currentBalance decimal(40,2) DEFAULT NULL,  si_DebitOrCredit int DEFAULT NULL,  FOREIGN KEY (si_id) REFERENCES tblstockitem (si_id))");
            s.execute(" CREATE TABLE tblstockitemrate (  custid int DEFAULT NULL,  stkid int DEFAULT NULL,  rate decimal(50,2) DEFAULT NULL,   FOREIGN KEY (custid ) REFERENCES tblledger (ledger_id),   FOREIGN KEY (stkid) REFERENCES tblstockitem (si_id))");
            s.execute("  CREATE TABLE tbltempopenclosebalance(  opening decimal(40,2) DEFAULT NULL,  closing decimal(40,2) DEFAULT NULL,  openType int DEFAULT NULL,  closeType int DEFAULT NULL)");
            s.execute("  CREATE TABLE tbltransactionledger(  trans_id int NOT NULL,  trans_ledgerId int DEFAULT NULL,  trans_type int DEFAULT NULL,  trans_index int DEFAULT NULL,  trans_narration varchar(1000) DEFAULT NULL,  trans_amt decimal(40,2) DEFAULT NULL,  trans_checkNo varchar(80) DEFAULT NULL,  cashinhand_flag int DEFAULT NULL,   FOREIGN KEY (trans_ledgerId) REFERENCES tblledger (ledger_id),  FOREIGN KEY (trans_id) REFERENCES tbltransactionmain (trans_id))");
            s.execute("  CREATE TABLE tbltransactionotherdetails (  trans_id int NOT NULL,  trans_reference varchar(100) DEFAULT NULL,  trans_buyerOrderNo varchar(50) DEFAULT NULL,  trans_dispatchDocThrough varchar(50) DEFAULT NULL,  trans_dispatchDocNo varchar(50) DEFAULT NULL,  trans_amt decimal(40,2) DEFAULT NULL,  trans_payment varchar(40) DEFAULT NULL,  trans_vehicleNo varchar(50) DEFAULT NULL,  trans_challanNo varchar(40) DEFAULT NULL,  trans_poNo varchar(40) DEFAULT NULL,  trans_challanDate date DEFAULT NULL,  trans_poDate date DEFAULT NULL,   FOREIGN KEY (trans_id) REFERENCES tbltransactionmain (trans_id))");
            s.execute(" CREATE TABLE tbltransactiontype ( transType_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  transType_name varchar(80) DEFAULT NULL,  PRIMARY KEY (transType_id))");
            s.execute(" CREATE TABLE tbltransactionvat (  trans_id int DEFAULT NULL,  vat_rate decimal(40,2) DEFAULT NULL,  vat_amt decimal(40,2) DEFAULT NULL,  discount_rate decimal(40,2) DEFAULT NULL,  discount_amt decimal(40,2) DEFAULT NULL,  shipping int DEFAULT NULL,  lbt_amt decimal(40,2) DEFAULT NULL,  FOREIGN KEY (trans_id) REFERENCES tbltransactionmain (trans_id))");

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(AllAdSuMuDiSettingsNew1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
