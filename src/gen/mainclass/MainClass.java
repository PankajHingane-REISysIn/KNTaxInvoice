package gen.mainclass;

import AdSuMuDiProductRegistration.PurchaseLicence;
import AdSuMuDiProductRegistration.PurchaseLicenceDTO;
import AdSuMuDiProductRegistration.RegisterProductDTO;
import AdSuMuDiProductRegistration.RegisterProductHelper;
import AdSuMuDiSecurity.AdSuMuDiEncryptDecrypt;
import gen.ImpExp.TagHelper2;
import gen.ImpExp.TagsHelper1;
import gen.account.StockItemFormation.StockItemForm1;
import gen.account.StockItemFormation.StockItemFormFinal;
import gen.account.stockgroup.StockGroupForm;
import gen.account.stockitem.SetOpeningBalanceForm;
import gen.account.unitofmeasure.UnitOfMeasureForm;
import gen.accountvoucher.chalan.ChalanForm1;
import gen.accountvoucher.contra.ContraForm;
import gen.accountvoucher.grnote.GRNoteForm;
import gen.accountvoucher.payment.PaymentForm;
import gen.accountvoucher.purchase.PurchaseForm;
import gen.accountvoucher.purchase.TaxInvoicePurchaseForm;
import gen.accountvoucher.purchaseorder.PurchaseOrderForm;
import gen.accountvoucher.receipt.ReceiptForm;
import gen.accountvoucher.sale.SaleForm;
import gen.accountvoucher.sale.SaleForm_NoCal;
import gen.accountvoucher.sale.SaleForm_Old;
import gen.company.CompanysSettings_User;
import gen.company.DatabaseBackUp_User;
import gen.database.connection.DatabaseConnection1;
import gen.display.report.Daybooks.DaybookChalan;
import gen.display.report.Daybooks.DaybookSalePurchase;
import gen.display.report.GroupSummaryForm;
import gen.display.report.Ledger.LedgerReportForm1;
import gen.display.report.StockItem.StockItemReportForm1;
import gen.display.report.TrialBalanceForm;
import gen.dto.Constants;
import gen.other.CompanySettings.CompanySettingsDTO;
import gen.other.backup.Backup;
import gen.other.login.SampleWebServiceGet;
import gen.other.print.PrinterSettings;
import gen.progressebar.ProgressBarWithNewGraphicsWithREST;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLayer;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.miginfocom.swing.MigLayout;

public class MainClass extends javax.swing.JFrame implements Runnable {

    static class MyActionListener extends AbstractAction {

        MyActionListener(String s) {
            super(s);
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            System.out.println(getValue(Action.NAME));
        }
    }
    static String operatingSystemDrive = System.getenv("SystemDrive");
    static int temp = 0;
    private int flag_Create_company;
    public static int flag_login;
    static int flag_group_create, flag_group_details, flag_ledger_create, flag_ledger_opening_balance,
            flag_ledger_details, flag_group_alter, flag_ledger_alter;
    static int flag_stock_group_create, flag_stock_group_details,
            flag_stock_group_alter, flag_single_stock_item_create, flag_multiple_stock_item_create,
            flag_stock_item_details, flag_stock_item_alter, flag_finish_type,
            flag_stock_item_opening_balance;
    static int flag_stock_item_type;
    static int flag_uom_create, flag_uom_alter, flag_uom_details;
    static int flag_daybook, flag_daybook_rec_pay, flag_daybook_sales_purchase,
            flag_daybook_chalan, flag_cashdaybook, flag_daybookAdvance,
            flag_daybookNew;
    static int flag_ledger_report, flag_stock_item_report, flag_trial_report,
            flag_groupSummary_report, flag_newGroupSummary_report,
            flag_sale_report, flag_purchase_report, flag_ledger_reportadvance,
            flag_ledgerNew;
//    static int flag_sales, flag_sales_with_No_cal_form, flag_sales_old, flag_sales_with_discount, flag_purchase, flag_payment, flag_receipt,
    static int flag_sales, flag_sales_with_No_cal_form, flag_sales_old, flag_purchase, flag_payment, flag_receipt,
            flag_chalan, flag_contra, flag_journal;
    static int flag_addraw, flag_addprocess, flag_productiontoday;
    static int flag_new_user, flag_change_pass, flag_email_setting,
            flag_right_setting, flag_other_setting, flag_delete_user;
    static int flag_backup;
    static int flag_for_menu_selection = 0;
    static int flag_Network_Settings, flag_Graphics_Settings,
            flag_AllAdSuMuDi_Settings;
    static int flag_print;
    static Image img, img1;
    static int flag_ViewEditCompany = 0;
    static int flag_CreateCompany = 0;
    String path = "";
    SetWaitTimer s = new SetWaitTimer();
    Image img2 = new ImageIcon(getClass().getResource(
            "/images/business_travel.gif")).getImage();
    static String dir = System.getProperty("user.dir");
    static String getDate = "";
    //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    DateFormat dateToBeSentFormat = new SimpleDateFormat("yyyy-MM-dd");
//    public static int dialogButton = JOptionPane.YES_NO_OPTION;
//    public static int dialogResult = 1;

    /**
     * Launch the application.
     */
    public MainClass() {
        super("AdSuMuDi");
//        create_Tables_Company_Info();
//	create_Tables_Company();
//        setSize(1366, 768);
//	alter_Tables_Company();
//        DatabaseHelper.createCompanyDatabase("default");
//        DatabaseHelper.createCompanyDatabase("New42");


//        /*
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        double imageWidth = d.getWidth();
        int iw = (int) imageWidth;
        double imageHeight = d.getHeight();
        int ih = (int) imageHeight;
        img = new ImageIcon(getClass().getResource("/images/wallpaper-3-new.jpg")).getImage();//.getScaledInstance(iw, ih, 0);  //working
        img1 = new ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png")).getImage();
        initGUIComponents();
        //desktopMain.setBorder(new TempClassForImage(img, desktopMain.getSize()));
        this.setIconImage(img1);
        setAllcomponentInVisible();
//                * */
    }

    @SuppressWarnings("unchecked")
    private void initGUIComponents() {
        addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent arg0) {
                MainClass.this.setExtendedState(MainClass.this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent arg0) {

                jSeparator29.setVisible(false);
                jMenuItemBalanceSheet.setVisible(false);
                jSeparator50.setVisible(false);
                jMenuItemProfitandLoss.setVisible(false);

//                desktopMain.setLayout(new MigLayout("center panel",
//                        "[100px:100px:1366px,grow,shrink 50,center]",
//                        "[100px:100px:768px,grow,shrink 50,center]"));
                // set database of current company 
                List<gen.other.CompanySettings.CompanySettingsDTO> companySettingsDTOList = new ArrayList<CompanySettingsDTO>();
                System.out.println("gen.dto.Constants.CURRENT_COMPANY_NAME ---------------- " + gen.dto.Constants.CURRENT_COMPANY_NAME);
                System.out.println("gen.dto.Constants.CURRENT_COMPANY_ID -------------------" + gen.dto.Constants.CURRENT_COMPANY_ID);
                System.out.println("Current company database ----------------- " + gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings);

                // get role of current user
                System.out.println("gen.dto.Constants.MAIN_CLASS_OPEN_COUNT " + gen.dto.Constants.MAIN_CLASS_OPEN_COUNT);
                System.out.println("gen.dto.Constants.MAIN_CLASS_OPEN_COUNT " + gen.dto.Constants.MAIN_CLASS_OPEN_COUNT.equals("0"));

//		// get companies List and if one occure then dont show company selection form
                List<gen.other.CompanySettings.CompanySettingsDTO> CompanySettingsDTOList = new ArrayList<CompanySettingsDTO>();
                CompanySettingsDTOList = gen.other.CompanySettings.CompanySettingsDAO.getComapany_List("", "");
                if (CompanySettingsDTOList.size() == 1) {
                    for (CompanySettingsDTO companySettingsDTO : CompanySettingsDTOList) {
//                        gen.dto.Constants.CURRENT_COMPANY_NAME = "UNNATI SALES";
//                        gen.dto.Constants.CURRENT_COMPANY_ID = "1";
//                        gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = "unnatisales";
                        gen.dto.Constants.CURRENT_COMPANY_NAME = companySettingsDTO.getCompany_name();
                        gen.dto.Constants.CURRENT_COMPANY_ID = companySettingsDTO.getCompany_id().toString();
                        gen.dto.Constants.CURRENT_COMPANY_CREATED_DATE = companySettingsDTO.getCompany_ApplicableFrom_date().toString();

                        System.out.println("DATE -------       " + gen.dto.Constants.CURRENT_COMPANY_CREATED_DATE);

                        gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = companySettingsDTO.getCompany_DatabaseName().toString();
                        MainClass.this.setTitle("AdSuMuDi                                                                             Company  Name :  " + gen.dto.Constants.CURRENT_COMPANY_NAME);
                    }
                } else {
                    MainClass.this.setTitle("AdSuMuDi                                                                             Company  Name :  " + gen.dto.Constants.CURRENT_COMPANY_NAME);
                }
//                if (!gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings.isEmpty()) {
//                    MainClass.this.setTitle("AdSuMuDi                                                                             Company  Name :  " + gen.dto.Constants.CURRENT_COMPANY_NAME);
//                }

                getActiveInactiveComponent(gen.dto.Constants.Features_Call_Class);

                //jButtonQuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); 
                //MenuBarMain.add(Box.createGlue());
                //MenuBarMain.add(jButtonQuit);
                FileReader fileRead = null;
                try {
                    String dir = System.getProperty("user.dir");
                    fileRead = new FileReader(dir + "\\others\\UserLicence.properties");
                    BufferedReader br = new BufferedReader(fileRead);
                    String readLine = "";
                    String licenceLine = "";
                    int lineCounting = 0;
                    while ((readLine = br.readLine()) != null) {

                        System.out.println("readLine-->>" + readLine);
                        if (lineCounting == 1) {
                            licenceLine = readLine;
                        }
                        lineCounting++;

                    }
                    br.close();
                    if (licenceLine.equalsIgnoreCase("Registered User For Licenced Version")) {
                        jMenuLicence.setEnabled(false);
                    }
                    deactivatingAndHidingUnnecessaryFeatures();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        fileRead.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            @Override
            public void windowOpened(WindowEvent e) {

                jSeparator29.setVisible(false);
                jMenuItemBalanceSheet.setVisible(false);
                jSeparator50.setVisible(false);
                jMenuItemProfitandLoss.setVisible(false);

                FileReader fileRead = null;

                try {
                    System.out.println("dir path-->>>" + dir);
                    getDate = AdSuMuDiEncryptDecrypt.generateDecryptionForEDate();
                    System.out.println("Date::::" + getDate);
                    String readLine = "";
                    fileRead = new FileReader(dir + "\\others\\Licence.dat");
                    BufferedReader bufferedRead = new BufferedReader(fileRead);
                    String readLicence = "";
                    int lineCounting = 0;
                    while ((readLine = bufferedRead.readLine()) != null) {
                        System.out.println("Reading Licence readLine-->>>" + readLine);
                        if (lineCounting == 5) {
                            readLicence = readLine;
                        }
                        lineCounting++;
                    }
                    bufferedRead.close();
                    if (readLicence.equalsIgnoreCase("")) {

                        String CName = "";
                        String CContact = "";
                        String CEmail = "";
                        String CUniqueKey = "";

                        String line = "";
                        FileReader fileReader = new FileReader(dir + "\\others\\Licence.dat");

                        BufferedReader bufferedReader = new BufferedReader(fileReader);

                        int lineCounter = 0;
                        while ((line = bufferedReader.readLine()) != null) {
                            System.out.println("Reading Licence-->>>" + line);
                            if (lineCounter == 1) {
                                CName = line;
                            }
                            if (lineCounter == 2) {
                                CContact = line;
                            }
                            if (lineCounter == 3) {
                                CEmail = line;
                            }
                            if (lineCounter == 4) {
                                CUniqueKey = line;
                            }
                            lineCounter++;
                        }
                        bufferedReader.close();

                        final String companyName = CName.trim();
                        final String EmailID = CEmail.trim();
                        final String contactDetails = CContact.trim();
                        final String uniqueKey = CUniqueKey.trim();
                        Date date = new Date();
                        //date = dateFormat.parse(getDate);
                        date = dateToBeSentFormat.parse(getDate);
                        final String dateToBeSent = dateToBeSentFormat.format(date);
                        System.out.println("dateToBeSent--PP-->>" + dateToBeSent);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    //CheckInternetConnection.testInternetConnection();
                                    final RegisterProductDTO registrationDTO = new RegisterProductDTO();

                                    String companyNameSend = "";
                                    String EmailIDSend = "";
                                    String contactDetailsSend = "";

                                    companyNameSend = companyName.replace(" ", "-");
                                    EmailIDSend = EmailID.replace(" ", "-");
                                    contactDetailsSend = contactDetails.replace(" ", "-");

                                    registrationDTO.setCompanyName(companyNameSend.trim());
                                    registrationDTO.setCompanyEmailID(EmailIDSend.trim());
                                    registrationDTO.setCompanyContactDetails(contactDetailsSend.trim());
                                    registrationDTO.setUniqueKey(uniqueKey);

                                    String readLine = "";
                                    FileReader fileRead = new FileReader(dir + "\\others\\Licence.dat");
                                    BufferedReader bufferedRead = new BufferedReader(fileRead);

                                    String readLicence = "";
                                    int lineCounting = 0;
                                    while ((readLine = bufferedRead.readLine()) != null) {
                                        System.out.println("Reading Licence readLine-->>>" + readLine);
                                        if (lineCounting == 5) {
                                            readLicence = readLine;
                                            System.out.println("Reading Licence readLine-->>>PBAR--->>>readLicence--->>" + readLicence);
                                        }
                                        lineCounting++;
                                    }
                                    bufferedRead.close();

                                    if (readLicence.equalsIgnoreCase("")) {
                                        SampleWebServiceGet s = new SampleWebServiceGet();
                                        String line = s.callRestService(readingURLDynamically());
                                        System.out.println("Response Line---In ProgressBarWithREST-->>" + line);
                                        String splitting[] = line.split("/");

                                        RegisterProductHelper.RESTServiceRelatedOperations(dateToBeSent, splitting, registrationDTO);

                                        FileWriter fileWritter = new FileWriter(dir + "\\others\\Licence.dat", true);
                                        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                                        bufferWritter.write("\n" + "Registered User For Trial Version");
                                        bufferWritter.close();

                                        fileWritter = new FileWriter(operatingSystemDrive + "\\Program Files\\Common Files\\System\\Licence.dat", true);
                                        bufferWritter = new BufferedWriter(fileWritter);
                                        bufferWritter.write("\n" + "Registered User For Trial Version");
                                        bufferWritter.close();
                                    }
                                } catch (Exception ex) {
                                    Logger.getLogger(ProgressBarWithNewGraphicsWithREST.class.getName()).log(Level.SEVERE, null, ex);
                                    ex.printStackTrace();
                                    JOptionPane.showMessageDialog(MainClass.this, ex.getMessage());
                                }
                            }
                        }).start();
                    }
                    try {

                        // getting Mobile_key first time when application start
                        List<PurchaseLicenceDTO> purchaseLicenceDTOList = new ArrayList<PurchaseLicenceDTO>();
                        purchaseLicenceDTOList = AdSuMuDiProductRegistration.PurchaseLicenceDAO.get_Licence_Information();
                        for (PurchaseLicenceDTO purchaseLicenceDTO : purchaseLicenceDTOList) {
                            Schedular.Constants.mobile_key = purchaseLicenceDTO.getMobile_key();
                        }
                        System.out.println("TTTTTTTTTTTTTTTTTTTTTTTT           " + Schedular.Constants.mobile_key);
                        jMenuItemSubItemDaybookReceiptPayment.setVisible(true);

                        String dir = System.getProperty("user.dir");
                        fileRead = new FileReader(dir + "\\others\\UserLicence.properties");
                        BufferedReader br = new BufferedReader(fileRead);

                        String licenceLine = "";

                        while ((readLine = br.readLine()) != null) {

                            System.out.println("readLine-->>" + readLine);
                            if (lineCounting == 1) {
                                licenceLine = readLine;
                            }
                            lineCounting++;

                        }
                        br.close();
                        if (licenceLine.equalsIgnoreCase("Registered User For Licenced Version")) {
                            jMenuLicence.setEnabled(false);
                        }

                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                        MainClass.this.setPreferredSize(screenSize);
                        MainClass.this.setExtendedState(MainClass.this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                        jMenuInventoryVouchers.setMnemonic(KeyEvent.VK_V);
                        jMenuReportVouchers.setMnemonic(KeyEvent.VK_R);
                        jMenuAccoutingVouchers.setMnemonic(KeyEvent.VK_A);
                        jMenuSettings.setMnemonic(KeyEvent.VK_S);
                        jMenuLicence.setMnemonic(KeyEvent.VK_L);
                        jMenuCompany.setMnemonic(KeyEvent.VK_C);
                        jMenuUser.setMnemonic(KeyEvent.VK_U);
                        //-----------------Assign User Rights------------------
                        PrinterSettings.printSettingsInitilise();
                        // get companies List
                        List<gen.other.CompanySettings.CompanySettingsDTO> CompanySettingsDTOList = new ArrayList<CompanySettingsDTO>();
                        CompanySettingsDTOList = gen.other.CompanySettings.CompanySettingsDAO.getComapany_List("", "");
                        System.out.println("Check Company List ------------------------------" + CompanySettingsDTOList.size());
                        // if zero company created then dont call this class
                        if (CompanySettingsDTOList.size() > 1 && gen.dto.Constants.MAIN_CLASS_OPEN_COUNT.equals("0")) {
                            //if (CompanySettingsDTOList.size() > 0 && gen.dto.Constants.MAIN_CLASS_OPEN_COUNT.equals("0")) {
                            gen.company.CompanysSettings_User form = new gen.company.CompanysSettings_User();
                            desktopMain.removeAll();
                            desktopMain.repaint();
                            form.pack();
                            form.setVisible(true);
                            desktopMain.add(form);
                            dontmoveframe();
                            gen.dto.Constants.MAIN_CLASS_OPEN_COUNT = "1";
                        }


                        createReportDirectory();
                    } catch (IOException ex) {
                        Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    deactivatingAndHidingUnnecessaryFeatures();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(MainClass.this, ex.getMessage());
                }
            }

            @Override
            public void windowClosing(WindowEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(MainClass.this, "Do you want to Close the Application?", "Warning", dialogButton);
                if (dialogResult == 0) {
//                    /*
//                     * Stop mysql server
//                     */
//                    StopMysqlServer1 stopMysqlServer = new StopMysqlServer1();
//                    stopMysqlServer.start();

                    Thread t = new Thread(MainClass.this);
                    t.start();
                    createReportDirectory();
                } else {
                    jMenuInventoryVouchers.doClick();
                }
            }
        });

        setFontSize();
//        setAlwaysOnTop(true);
        //desktopMain = new JDesktopPane();
        desktopMain = new JDesktopPane() {
            protected void paintComponent(Graphics g) {
                g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
            }
        };
        desktopMain.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent arg0) {
                if (desktopMain.getComponentCount() == 0 && jMenuInventoryVouchers.isSelected() == false && jMenuReportVouchers.isSelected() == false && jMenuAccoutingVouchers.isSelected() == false) {
                    if (desktopMain.getComponentCount() == 0) {
                        if (flag_for_menu_selection == 1) {
                            jMenuInventoryVouchers.doClick();
                        }
                        if (flag_for_menu_selection == 2) {
                            //menuStockItem.doClick();
                        }
                        if (flag_for_menu_selection == 3) {
                            jMenuReportVouchers.doClick();
                        }
                        if (flag_for_menu_selection == 4) {
                            jMenuAccoutingVouchers.doClick();
                        }
                        if (flag_for_menu_selection == 5) {
                            //menuAccountSettings.doClick();
                        }
                        if (flag_for_menu_selection == 6) {
                            //menuNewCompany.doClick();
                        }
                    }
                }
            }
        });
        desktopMain.setLayout(new MigLayout("center panel",
                "[100px:100px:1366px,grow,shrink 50,center]",
                "[100px:100px:768px,grow,shrink 50,center]"));
        desktopMain.setBorder(new LineBorder(new Color(0, 0, 0)));
        //desktopMain.setBackground(Color.WHITE);
        //setForeground(Color.WHITE);
        setTitle("AdSuMuDi");
        setIconImage(Toolkit.getDefaultToolkit().getImage(MainClass.class.getResource("/images/Kasturi-logo-1.png")));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(screenSize);

        MenuBarMain = new JMenuBar();
        setJMenuBar(MenuBarMain);

        jMenuInventoryVouchers = new JMenu("Inventory Vouchers");
        jMenuInventoryVouchers.addMenuListener(new MenuListener() {
            public void menuCanceled(MenuEvent e) {
            }

            public void menuDeselected(MenuEvent e) {
            }

            public void menuSelected(MenuEvent e) {
                desktopMain.setLayout(new MigLayout("center panel",
                        "[100px:100px:1366px,grow,shrink 50,center]",
                        "[100px:100px:768px,grow,shrink 50,center]"));
            }
        });
        jMenuInventoryVouchers.setIcon(new ImageIcon(MainClass.class
                .getResource("/images/User-group1.png")));
        MenuBarMain.add(jMenuInventoryVouchers);

        jMenuItemGroups = new JMenuItem("Groups");
        jMenuItemGroups.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag_group_create == 0) {
                    gen.account.groupDTODAO.GroupForm form = new gen.account.groupDTODAO.GroupForm();
                    setstaticvar();
                    flag_group_create = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuInventoryVouchers.add(jMenuItemGroups);

        jSeparator20 = new JSeparator();
        jMenuInventoryVouchers.add(jSeparator20);

        jMenuItemLedgers = new JMenuItem("Ledgers");
        jMenuItemLedgers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag_ledger_create == 0) {
                    gen.account.ledger.LedgerGUIForm form = new gen.account.ledger.LedgerGUIForm();
                    setstaticvar();
                    flag_ledger_create = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuInventoryVouchers.add(jMenuItemLedgers);

        jSeparator20 = new JSeparator();
        jMenuInventoryVouchers.add(jSeparator20);

        jMenuItemLedgersOpeningBalance = new JMenuItem("Ledgers Opening Balance");
        jMenuItemLedgersOpeningBalance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag_ledger_opening_balance == 0) {
                    gen.account.ledger.SetLedgerOpeningBalanceForm form = new gen.account.ledger.SetLedgerOpeningBalanceForm();
                    setstaticvar();
                    flag_ledger_opening_balance = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuInventoryVouchers.add(jMenuItemLedgersOpeningBalance);

        jSeparator35 = new JSeparator();
        jMenuInventoryVouchers.add(jSeparator35);

        jMenuItemStockGroups = new JMenuItem("Stock Groups");
        jMenuItemStockGroups.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (flag_stock_group_create == 0) {
                    StockGroupForm form = new StockGroupForm();
                    setstaticvar();
                    flag_stock_group_create = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                        e.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuInventoryVouchers.add(jMenuItemStockGroups);

        jSeparator39 = new JSeparator();
        jMenuInventoryVouchers.add(jSeparator39);

        jMenuStockItems = new JMenu("Stock Items");
        jMenuStockItems.addMenuListener(new MenuListener() {
            public void menuCanceled(MenuEvent arg0) {
            }

            public void menuDeselected(MenuEvent arg0) {
            }

            public void menuSelected(MenuEvent arg0) {
            }
        });
        jMenuInventoryVouchers.add(jMenuStockItems);

        jMenuItemSingleStockItem = new JMenuItem("Single Stock Item");
        jMenuItemSingleStockItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (flag_single_stock_item_create == 0) {
                    StockItemForm1 form = new StockItemForm1();
                    setstaticvar();
                    flag_single_stock_item_create = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                        e.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuStockItems.add(jMenuItemSingleStockItem);

        jMenuItemSeparator = new JSeparator();
        jMenuStockItems.add(jMenuItemSeparator);

        jMenuItemMultipleStockItem = new JMenuItem("Multiple Stock Item");
        jMenuItemMultipleStockItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag_multiple_stock_item_create == 0) {
                    StockItemFormFinal form = new StockItemFormFinal();
                    setstaticvar();
                    flag_multiple_stock_item_create = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuStockItems.add(jMenuItemMultipleStockItem);

        jSeparator44 = new JSeparator();
        jMenuInventoryVouchers.add(jSeparator44);

        jMenuItemStockItemsOpeningBalance = new JMenuItem("Stock Items Opening Balance");
        jMenuItemStockItemsOpeningBalance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag_stock_item_opening_balance == 0) {
                    try {
                        //                    StockItemForm1 form = new StockItemForm1();
                        SetOpeningBalanceForm form = new SetOpeningBalanceForm();
                        setstaticvar();
                        flag_stock_item_opening_balance = 1;
                        desktopMain.removeAll();
                        desktopMain.repaint();
                        form.pack();
                        form.setVisible(true);
                        desktopMain.add(form);
                        try {
                            form.setSelected(true);
                        } catch (java.beans.PropertyVetoException ex) {
                            ex.printStackTrace();
                        }
                        dontmoveframe();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        jMenuInventoryVouchers.add(jMenuItemStockItemsOpeningBalance);

        jSeparator90 = new JSeparator();
        jMenuInventoryVouchers.add(jSeparator90);

        jMenuItemUnitOfMeasures = new JMenuItem("Unit Of Measure");
        jMenuItemUnitOfMeasures.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag_uom_create == 0) {
                    UnitOfMeasureForm form = new UnitOfMeasureForm();
                    setstaticvar();
                    flag_uom_create = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuInventoryVouchers.add(jMenuItemUnitOfMeasures);


        jSeparator45 = new JSeparator();
        jMenuInventoryVouchers.add(jSeparator45);

        jMenuAccoutingVouchers = new JMenu("Accounting Vouchers");
        jMenuAccoutingVouchers.addMenuListener(new MenuListener() {
            public void menuCanceled(MenuEvent e) {
            }

            public void menuDeselected(MenuEvent e) {
            }

            public void menuSelected(MenuEvent e) {
                desktopMain.setLayout(new CardLayout(0, 0));
            }
        });
        jMenuAccoutingVouchers.setIcon(new ImageIcon(MainClass.class
                .getResource("/images/Accouting vouchers.png")));
        MenuBarMain.add(jMenuAccoutingVouchers);


        jMenuItemSales = new JMenuItem("Sale Voucher");
        jMenuItemSales.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (flag_sales == 0) {
                    SaleForm form = new SaleForm("Sales", desktopMain.getPreferredSize());
                    setstaticvar();
                    flag_sales = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                        e.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuAccoutingVouchers.add(jMenuItemSales);

        jSeparator30 = new JSeparator();
        jMenuAccoutingVouchers.add(jSeparator30);

        jMenuItemSales_With_No_cal = new JMenuItem("Sale Voucher With No Cal");
        jMenuItemSales_With_No_cal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (flag_sales_with_No_cal_form == 0) {
                    SaleForm_NoCal form = new SaleForm_NoCal("Sales", desktopMain.getPreferredSize());
                    setstaticvar();
                    flag_sales_with_No_cal_form = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                        e.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuAccoutingVouchers.add(jMenuItemSales_With_No_cal);

        jSeparator30 = new JSeparator();
        jMenuAccoutingVouchers.add(jSeparator30);

        jMenuItemSales_No_Final_Amount = new JMenuItem("Sale Voucher Old(No Final Amount Cal)");
        jMenuItemSales_No_Final_Amount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (flag_sales_old == 0) {
                    SaleForm_Old form = new SaleForm_Old("Sale Voucher Old(No Final Amount Cal)", desktopMain.getPreferredSize());
                    setstaticvar();
                    flag_sales_old = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                        e.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuAccoutingVouchers.add(jMenuItemSales_No_Final_Amount);

//        jSeparator30 = new JSeparator();
//        jMenuAccoutingVouchers.add(jSeparator30);
//
//        jMenuItemSales_With_Discount = new JMenuItem("Sale Voucher With Discount");
//        jMenuItemSales_With_Discount.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent arg0) {
//                if (flag_sales_with_discount == 0) {
//                    SaleFormWithDiscount form = new SaleFormWithDiscount("Sale Voucher With Discount", desktopMain.getPreferredSize());
//                    setstaticvar();
//                    flag_sales_with_discount = 1;
//                    desktopMain.removeAll();
//                    desktopMain.repaint();
//                    Dimension dimension = desktopMain.getSize();
//                    form.setPreferredSize(dimension.getSize());
//                    form.pack();
//                    form.setVisible(true);
//                    desktopMain.add(form);
//                    try {
//                        form.setSelected(true);
//                    } catch (java.beans.PropertyVetoException e) {
//                        e.printStackTrace();
//                    }
//                    dontmoveframe();
//                }
//            }
//        });
//        jMenuAccoutingVouchers.add(jMenuItemSales_With_Discount);

        jSeparator30 = new JSeparator();
        jMenuAccoutingVouchers.add(jSeparator30);

        jMenuItemPurchase = new JMenuItem("Purchase Voucher");
        jMenuItemPurchase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (flag_purchase == 0) {
//                    PurchaseForm form = new PurchaseForm("Purchase Form", desktopMain.getPreferredSize());
                    TaxInvoicePurchaseForm form = new TaxInvoicePurchaseForm("Purchase Form", desktopMain.getPreferredSize());
                    setstaticvar();
                    flag_purchase = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                        e.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuAccoutingVouchers.add(jMenuItemPurchase);

        jSeparator31 = new JSeparator();
        jMenuAccoutingVouchers.add(jSeparator31);

        jMenuItemReceipt = new JMenuItem("Receipt Voucher");
        jMenuItemReceipt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag_receipt == 0) {
                    ReceiptForm form = new ReceiptForm("Receipt Form", desktopMain.getPreferredSize());
                    setstaticvar();
                    flag_receipt = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuAccoutingVouchers.add(jMenuItemReceipt);

        jSeparator32 = new JSeparator();
        jMenuAccoutingVouchers.add(jSeparator32);

        jMenuItemPayment = new JMenuItem("Payment Voucher");
        jMenuItemPayment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag_payment == 0) {
                    PaymentForm form = new PaymentForm("Payment Form", desktopMain.getPreferredSize());
                    setstaticvar();
                    flag_payment = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuAccoutingVouchers.add(jMenuItemPayment);

        jSeparator33 = new JSeparator();
        jMenuAccoutingVouchers.add(jSeparator33);

        jMenuItemChallan = new JMenuItem("Chalan Voucher");
        jMenuItemChallan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChalanForm1 form = new ChalanForm1("Chalan Form", desktopMain.getPreferredSize());
                setstaticvar();
                desktopMain.removeAll();
                desktopMain.repaint();
                Dimension dimension = desktopMain.getSize();
                form.setPreferredSize(dimension.getSize());
                form.pack();
                form.setVisible(true);
                desktopMain.add(form);
                try {
                    form.setSelected(true);
                } catch (java.beans.PropertyVetoException ex) {
                    ex.printStackTrace();
                }
                dontmoveframe();
            }
        });
        jMenuAccoutingVouchers.add(jMenuItemChallan);

        jSeparator34 = new JSeparator();
        jMenuAccoutingVouchers.add(jSeparator34);

        jMenuItemContra = new JMenuItem("Contra Voucher");
        jMenuItemContra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag_contra == 0) {
                    ContraForm form = new ContraForm("Contra Form", desktopMain.getPreferredSize());
                    setstaticvar();
                    flag_contra = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuAccoutingVouchers.add(jMenuItemContra);

        jSeparator34 = new JSeparator();
        jMenuAccoutingVouchers.add(jSeparator34);

        jMenuItemPurchaseOrder = new JMenuItem("Purchase Order");
        jMenuItemPurchaseOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                if (flag_contra == 0) {
                PurchaseOrderForm form = new PurchaseOrderForm("Purchase Order Form", desktopMain.getPreferredSize());
                setstaticvar();
//                    flag_contra = 1;
                desktopMain.removeAll();
                desktopMain.repaint();
                Dimension dimension = desktopMain.getSize();
                form.setPreferredSize(dimension.getSize());
                form.pack();
                form.setVisible(true);
                desktopMain.add(form);
                try {
                    form.setSelected(true);
                } catch (java.beans.PropertyVetoException ex) {
                    ex.printStackTrace();
                }
                dontmoveframe();
//                }
            }
        });
        jMenuAccoutingVouchers.add(jMenuItemPurchaseOrder);


        jSeparator35 = new JSeparator();
        jMenuAccoutingVouchers.add(jSeparator35);

        jMenuItemGRNote = new JMenuItem("GR Note Form");
        jMenuItemGRNote.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                if (flag_contra == 0) {
                GRNoteForm form = new GRNoteForm("GR Note Form", desktopMain.getPreferredSize());
                setstaticvar();
//                    flag_contra = 1;
                desktopMain.removeAll();
                desktopMain.repaint();
                Dimension dimension = desktopMain.getSize();
                form.setPreferredSize(dimension.getSize());
                form.pack();
                form.setVisible(true);
                desktopMain.add(form);
                try {
                    form.setSelected(true);
                } catch (java.beans.PropertyVetoException ex) {
                    ex.printStackTrace();
                }
                dontmoveframe();
//                }
            }
        });
        jMenuAccoutingVouchers.add(jMenuItemGRNote);


        jSeparator52 = new JSeparator();
        jMenuAccoutingVouchers.add(jSeparator52);

        jMenuReportVouchers = new JMenu("Reports Vouchers");
        jMenuReportVouchers.addMenuListener(new MenuListener() {
            public void menuCanceled(MenuEvent arg0) {
            }

            public void menuDeselected(MenuEvent arg0) {
            }

            public void menuSelected(MenuEvent arg0) {
                desktopMain.setLayout(new CardLayout(0, 0));
            }
        });
        jMenuReportVouchers.setIcon(new ImageIcon(MainClass.class
                .getResource("/images/Reports.png")));
        MenuBarMain.add(jMenuReportVouchers);

        jMenuItemDayBook = new JMenu("DayBooks");
        jMenuReportVouchers.add(jMenuItemDayBook);

        jMenuItemSubItemDailyCashFlow = new JMenuItem("Daily Cash Flow");
        jMenuItemSubItemDailyCashFlow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag_daybook == 0) {
                    //DayBook form = new DayBook("Daily Cash Flow");
                    gen.display.report.Daybooks.Daybook form = new gen.display.report.Daybooks.Daybook();
                    setstaticvar();
                    flag_daybook = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuItemDayBook.add(jMenuItemSubItemDailyCashFlow);

        separator_9 = new JSeparator();
        jMenuItemDayBook.add(separator_9);

        jMenuItemSubItemDaybookReceiptPayment = new JMenuItem(
                "Daybook For Receipt/Payment");
        jMenuItemSubItemDaybookReceiptPayment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (flag_daybook_rec_pay == 0) {
                    //DayBookReceiptPayment form = new DayBookReceiptPayment("DayBook For Receipt And Payment");
                    gen.display.report.Daybooks.DaybookReceiptPayment form = new gen.display.report.Daybooks.DaybookReceiptPayment();
                    setstaticvar();
                    flag_daybook_rec_pay = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                        e.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuItemDayBook.add(jMenuItemSubItemDaybookReceiptPayment);

        separator_10 = new JSeparator();
        jMenuItemDayBook.add(separator_10);

        jMenuItemSubItemDailySalePurchaseFlow = new JMenuItem(
                "Daily Sale/Purchase Flow");
        jMenuItemSubItemDailySalePurchaseFlow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag_daybook_sales_purchase == 0) {
                    //DayBookSalesPurchase form = new DayBookSalesPurchase("Daily Sale/Purchase Flow");
                    gen.display.report.Daybooks.DaybookSalePurchase form = new DaybookSalePurchase();
                    setstaticvar();
                    flag_daybook_sales_purchase = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuItemDayBook.add(jMenuItemSubItemDailySalePurchaseFlow);

        separator_11 = new JSeparator();
        jMenuItemDayBook.add(separator_11);

        jMenuItemSubItemDailyChalan = new JMenuItem("Daily Chalan");
        jMenuItemSubItemDailyChalan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag_daybook_chalan == 0) {
                    //DayBookChalan form = new DayBookChalan("Daily Chalan");
                    DaybookChalan form = new DaybookChalan();
                    setstaticvar();
                    flag_daybook_chalan = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuItemDayBook.add(jMenuItemSubItemDailyChalan);

        jSeparator23 = new JSeparator();
        jMenuReportVouchers.add(jSeparator23);

        jMenuItemSaleReport = new JMenuItem("Sale Report");
        jMenuItemSaleReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag_sale_report == 0) {
                    //SaleReport form = new SaleReport("Sale Report");
                    gen.display.report.SaleReport1.SaleReport form = new gen.display.report.SaleReport1.SaleReport();
                    setstaticvar();
                    flag_sale_report = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuReportVouchers.add(jMenuItemSaleReport);

        separator_61 = new JSeparator();
        jMenuItemDayBook.add(separator_61);

        jMenuItemSubItemDailyPO = new JMenuItem("Daily PO");
        jMenuItemSubItemDailyPO.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                if (flag_daybook_chalan == 0) {
                //DayBookChalan form = new DayBookChalan("Daily Chalan");
                gen.display.report.purchaserOrderReport.POReportFormWithLedger form = new gen.display.report.purchaserOrderReport.POReportFormWithLedger("POReportForm");
                setstaticvar();
//                    flag_daybook_chalan = 1;
                desktopMain.removeAll();
                desktopMain.repaint();
                Dimension dimension = desktopMain.getSize();
                form.setPreferredSize(dimension.getSize());
                form.pack();
                form.setVisible(true);
                desktopMain.add(form);
                try {
                    form.setSelected(true);
                } catch (java.beans.PropertyVetoException ex) {
                    ex.printStackTrace();
                }
                dontmoveframe();
//                }
            }
        });
        jMenuItemDayBook.add(jMenuItemSubItemDailyPO);

        jSeparator37 = new JSeparator();
        jMenuReportVouchers.add(jSeparator37);

        jMenuItemPurchaseReport = new JMenuItem("Purchase Report");
        jMenuItemPurchaseReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag_purchase_report == 0) {
                    //PurchaseReport1 form = new PurchaseReport1("Purchase Report");
//                    gen.display.report.PurchaseReport.PurchaseReport form = new gen.display.report.PurchaseReport.PurchaseReport();
                    gen.display.report.PurchaseReport.PurchaseReportWithAllTax form = new gen.display.report.PurchaseReport.PurchaseReportWithAllTax();
                    setstaticvar();
                    flag_purchase_report = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuReportVouchers.add(jMenuItemPurchaseReport);

        jSeparator26 = new JSeparator();
        jMenuReportVouchers.add(jSeparator26);

        jMenuItemLedgerReport = new JMenuItem("Ledger Report");
        jMenuItemLedgerReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag_ledger_report == 0) {
                    LedgerReportForm1 form = new LedgerReportForm1("Ledger Report");
                    setstaticvar();
                    flag_ledger_report = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuReportVouchers.add(jMenuItemLedgerReport);

        jSeparator24 = new JSeparator();
        jMenuReportVouchers.add(jSeparator24);

        jMenuItemStockItemReport = new JMenuItem("Stock Item Report");
        jMenuItemStockItemReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag_stock_item_report == 0) {
                    StockItemReportForm1 form = new StockItemReportForm1("Stock Item Report");
                    setstaticvar();
                    flag_stock_item_report = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuReportVouchers.add(jMenuItemStockItemReport);

        jSeparator25 = new JSeparator();
        jMenuReportVouchers.add(jSeparator25);

        jMenuItemTrialBalance = new JMenuItem("Trial Balance");
        jMenuItemTrialBalance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag_trial_report == 0) {
                    TrialBalanceForm form = new TrialBalanceForm("Trial Balance Form", desktopMain.getPreferredSize());
                    setstaticvar();
                    flag_trial_report = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                }
            }
        });
        jMenuReportVouchers.add(jMenuItemTrialBalance);

        jSeparator27 = new JSeparator();
        jMenuReportVouchers.add(jSeparator27);

        jMenuItemGroupSummary = new JMenuItem("Group Summary");
        jMenuItemGroupSummary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag_newGroupSummary_report == 0) {
                    try {
                        GroupSummaryForm form = new GroupSummaryForm("Group Summary", desktopMain.getPreferredSize());
                        setstaticvar();
                        flag_newGroupSummary_report = 1;
                        desktopMain.removeAll();
                        desktopMain.repaint();
                        Dimension dimension = desktopMain.getSize();
                        form.setPreferredSize(dimension.getSize());
                        form.pack();
                        form.setVisible(true);
                        desktopMain.add(form);
                        try {
                            form.setSelected(true);
                        } catch (java.beans.PropertyVetoException ex) {
                            ex.printStackTrace();
                        }
                        dontmoveframe();
                    } catch (Exception ex) {
                        Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        jMenuReportVouchers.add(jMenuItemGroupSummary);

        ///////////////////////////////////////////////////////////////////////////////////
        jSeparator29 = new JSeparator();
        jMenuReportVouchers.add(jSeparator29);

        jMenuItemBalanceSheet = new JMenuItem("Balance Sheet");
        jMenuItemBalanceSheet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                if (flag_newGroupSummary_report == 0) {
                try {
                    gen.balancesheet.BalanceSheetForm form = new gen.balancesheet.BalanceSheetForm("Balance Sheet", desktopMain.getPreferredSize());
                    setstaticvar();
//                        flag_newGroupSummary_report = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                } catch (Exception ex) {
                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                }
//                }
            }
        });
        jMenuReportVouchers.add(jMenuItemBalanceSheet);


        jSeparator50 = new JSeparator();
        jMenuReportVouchers.add(jSeparator50);

        jMenuItemProfitandLoss = new JMenuItem("Profit and Loss");
        jMenuItemProfitandLoss.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                if (flag_newGroupSummary_report == 0) {
                try {
                    gen.profitandloss.ProfitAndLossForm form = new gen.profitandloss.ProfitAndLossForm("Profit and Loss", desktopMain.getPreferredSize());
                    setstaticvar();
//                        flag_newGroupSummary_report = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                } catch (Exception ex) {
                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                }
//                }
            }
        });
        jMenuReportVouchers.add(jMenuItemProfitandLoss);


        ///////////////////////////////////////////////////////////////////////////////////
//////////////////////// ************************** //////////////////////////////////////
        jSeparator28 = new JSeparator();
        jMenuReportVouchers.add(jSeparator28);
        
        jMenuProduction = new JMenu("Production");
        jMenuProduction.addMenuListener(new MenuListener() {
            public void menuCanceled(MenuEvent arg0) {
            }

            public void menuDeselected(MenuEvent arg0) {
            }

            public void menuSelected(MenuEvent arg0) {
                desktopMain.setLayout(new CardLayout(0, 0));
            }
        });
        jMenuProduction.setIcon(new ImageIcon(MainClass.class
                .getResource("/images/Reports.png")));
        MenuBarMain.add(jMenuProduction);

        jMenuItemFinisnItemDesc = new JMenuItem("Finish Item Desc");
        jMenuItemFinisnItemDesc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                if (flag_newGroupSummary_report == 0) {
                try {
                    gen.feature.production.finisheditemdefination.AddFinishMaterialForm form = new gen.feature.production.finisheditemdefination.AddFinishMaterialForm("Finish Item Desc", desktopMain.getPreferredSize());
                    setstaticvar();
//                        flag_newGroupSummary_report = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                } catch (Exception ex) {
                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                }
//                }
            }
        });
        jMenuProduction.add(jMenuItemFinisnItemDesc);
        
        
        jMenuItemResinProd = new JMenuItem("Resin Prod");
        jMenuItemResinProd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                if (flag_newGroupSummary_report == 0) {
                try {
                    gen.feature.production.resinproduction.ResinProductionForm form = new gen.feature.production.resinproduction.ResinProductionForm("Resin Prod", desktopMain.getPreferredSize());
                    setstaticvar();
//                        flag_newGroupSummary_report = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                } catch (Exception ex) {
                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                }
//                }
            }
        });
        jMenuProduction.add(jMenuItemResinProd);
        
        
        jMenuItemIMpregnatedPaperProd = new JMenuItem("Impregnated Paper Prod");
        jMenuItemIMpregnatedPaperProd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                if (flag_newGroupSummary_report == 0) {
                try {
                    gen.feature.production.impregnatedpaperproduction.ImpregnatedPaperProduction form = new gen.feature.production.impregnatedpaperproduction.ImpregnatedPaperProduction("Impregnated Paper Prod", desktopMain.getPreferredSize());
                    setstaticvar();
//                        flag_newGroupSummary_report = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                } catch (Exception ex) {
                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                }
//                }
            }
        });
        jMenuProduction.add(jMenuItemIMpregnatedPaperProd);
        
        jMenuItemPrelanBoardProd = new JMenuItem("Prelan Board Prod");
        jMenuItemPrelanBoardProd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                if (flag_newGroupSummary_report == 0) {
                try {
                    gen.feature.production.prelanboardproduction.PrelanBoardProduction form = new gen.feature.production.prelanboardproduction.PrelanBoardProduction("Prelan Board Prod", desktopMain.getPreferredSize());
                    setstaticvar();
//                        flag_newGroupSummary_report = 1;
                    desktopMain.removeAll();
                    desktopMain.repaint();
                    Dimension dimension = desktopMain.getSize();
                    form.setPreferredSize(dimension.getSize());
                    form.pack();
                    form.setVisible(true);
                    desktopMain.add(form);
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                    dontmoveframe();
                } catch (Exception ex) {
                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                }
//                }
            }
        });
        jMenuProduction.add(jMenuItemPrelanBoardProd);
        
//        jMenuReportVouchers.add(jMenuProduction);
        
        //////////////////////// ************************** //////////////////////////////////////
        
        jSeparator28 = new JSeparator();
        jMenuReportVouchers.add(jSeparator28);

        jMenuSettings = new JMenu("Settings");
        jMenuSettings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                desktopMain.removeAll();
                desktopMain.repaint();
                desktopMain.setLayout(new MigLayout("center panel",
                        "[100px:100px:1366px,grow,shrink 50,center]",
                        "[100px:100px:768px,grow,shrink 50,center]"));
                if (flag_AllAdSuMuDi_Settings == 0) {
                    AdSuMuDiSettingsEclipseGUI form = new AdSuMuDiSettingsEclipseGUI();

                    final WaitTimerForAdSuMuDiJInternalFrame layerUI = new WaitTimerForAdSuMuDiJInternalFrame();
                    JLayer<JInternalFrame> jlayer = new JLayer<JInternalFrame>(form, layerUI);
                    layerUI.start();
                    desktopMain.add(jlayer);

                    flag_AllAdSuMuDi_Settings = 1;
                    setstaticvar();
                    form.setVisible(true);
                    Dimension desktopSize = desktopMain.getSize();
                    form.setSize(desktopSize);
                    form.setPreferredSize(desktopSize);
                    form.pack();
                    try {
                        form.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }

                    int counter = 10000;
                    Timer stopper = null;
                    for (int i = 500; i <= counter; i = i + 1000) {
                        System.out.println("i--------------->>>" + i);
                        System.out.println("form.isVisible()--->>>" + form.isVisible());
                        if (Constants.checkVisibilityOfSettingsWindow == true) {
                            System.out.println("Frame visible");
                            stopper = new Timer(i, new ActionListener() {
                                public void actionPerformed(ActionEvent ae) {
                                    layerUI.stop();
                                }
                            });
                            stopper.setRepeats(false);

                            if (!stopper.isRunning()) {
                                stopper.start();
                            }
                        } else {
                            System.out.println("Frame Invisible");
                        }
                    }
                }
                dontmoveframe();
            }
        });
        jMenuSettings.addMenuListener(new MenuListener() {
            public void menuCanceled(MenuEvent arg0) {
            }

            public void menuDeselected(MenuEvent arg0) {
            }

            public void menuSelected(MenuEvent arg0) {
//                desktopMain.setLayout(new MigLayout("center panel",
//                        "[100px:100px:1366px,grow,shrink 50,center]",
//                        "[100px:100px:768px,grow,shrink 50,center]"));
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        s.startEmptyWaitTimerForDesktop(desktopMain);
//                    }
//                }).start();
//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        AllAdSuMuDiSettingsNew1 form = new AllAdSuMuDiSettingsNew1();
//                        desktopMain.removeAll();
//                        desktopMain.repaint();
//                        setstaticvar();
//                        flag_AllAdSuMuDi_Settings = 1;
//                        form.setVisible(true);
//                        desktopMain.add(form);
//                        desktopMain.setVisible(true);
//                        Dimension desktopSize = desktopMain.getSize();
//                        form.setSize(desktopSize);
//                        form.setPreferredSize(desktopSize);
//                        form.pack();
//                    }
//                }).start();
            }
        });
        jMenuSettings.setIcon(new ImageIcon(MainClass.class
                .getResource("/images/Settings.png")));
        MenuBarMain.add(jMenuSettings);

        jMenuImport = new JMenu("Import");

        jMenuImport.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        desktopMain.setLayout(new MigLayout("center panel",
                                "[100px:100px:1366px,grow,shrink 50,center]",
                                "[100px:100px:768px,grow,shrink 50,center]"));
                        try {
                            path = "";
                            imageloading o = new imageloading();
//            o.setVisible(true); 

                            //File fXmlFile = new File("C:\\DayBook.xml");
                            System.out.println("Path ------------" + path);
                            File fXmlFile1 = new File(path);
                            System.out.println("Path ------------" + path);

                            if (!path.equalsIgnoreCase("")) {
                                TagsHelper1.initilise();

                                //TagHelper1.importXML(fXmlFile);
                                Map<String, Object> pass_objectMap = TagHelper2.importXML(fXmlFile1);
                                System.out.println("pass_objectMap    " + pass_objectMap.containsKey("mapValuePresent"));
                                if (pass_objectMap != null && pass_objectMap.containsKey("mapValuePresent")) {
//				    gen.ImpExp.selectImport.CopyPasteOperation form = new gen.ImpExp.selectImport.CopyPasteOperation(pass_objectMap);
                                    gen.ImpExp.selectImport.CopyPasteReplaceImportExport form = new gen.ImpExp.selectImport.CopyPasteReplaceImportExport(pass_objectMap);
//				setstaticvar();
//				flag_group_create = 1;
                                    desktopMain.removeAll();
                                    desktopMain.repaint();
                                    form.pack();
                                    form.setVisible(true);
                                    desktopMain.add(form);
                                    try {
                                        form.setSelected(true);
                                    } catch (java.beans.PropertyVetoException ex) {
                                        ex.printStackTrace();
                                    }
                                    dontmoveframe();
                                } else {
                                    JOptionPane.showMessageDialog(MainClass.this, "Import Successful");
                                }
                                //JOptionPane.showMessageDialog(MainClass.this, "Import Successful");
                            }
                            System.out.println("import XML file -----------------         " + fXmlFile1);


                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(MainClass.this, "Import Failure");
                            try {
                                throw ex;
                                // Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex1) {
                                ex1.printStackTrace();
                                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex1);
                            }
                        }
                    }
                });
        jMenuImport.setIcon(
                new ImageIcon(MainClass.class
                .getResource("/images/stock-options-icon.png")));
        MenuBarMain.add(jMenuImport);

        jMenuLicence = new JMenu("Licence");
        jMenuLicence.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                desktopMain.setLayout(new MigLayout("center panel",
                        "[100px:100px:1366px,grow,shrink 50,center]",
                        "[100px:100px:768px,grow,shrink 50,center]"));
                try {
                    // TODO add your handling code here:
                    FileReader fileRead = null;
                    String dir = System.getProperty("user.dir");
                    fileRead = new FileReader(dir + "\\others\\UserLicence.properties");
                    BufferedReader br = new BufferedReader(fileRead);
                    String readLine = "";
                    String licenceLine = "";
                    int lineCounting = 0;
                    while ((readLine = br.readLine()) != null) {

                        System.out.println("readLine-->>" + readLine);
                        if (lineCounting == 1) {
                            licenceLine = readLine;
                        }
                        lineCounting++;

                    }
                    br.close();
                    if (!licenceLine.equalsIgnoreCase("Registered User For Licenced Version")) {
                        PurchaseLicence form = new PurchaseLicence("Purchase Licence");
                        setstaticvar();
                        desktopMain.removeAll();
                        desktopMain.repaint();
                        desktopMain.removeAll();
                        desktopMain.repaint();
                        form.pack();
                        form.setVisible(true);
                        desktopMain.add(form);
                        try {
                            form.setSelected(true);
                        } catch (java.beans.PropertyVetoException ex) {
                            ex.printStackTrace();
                        }
                        dontmoveframe();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });


        jMenuLicence.addMenuListener(new MenuListener() {
            public void menuCanceled(MenuEvent e) {
            }

            public void menuDeselected(MenuEvent e) {
            }

            public void menuSelected(MenuEvent e) {
//                desktopMain.setLayout(new MigLayout("center panel",
//                        "[100px:100px:1366px,grow,shrink 50,center]",
//                        "[100px:100px:768px,grow,shrink 50,center]"));
//                try {
//                    // TODO add your handling code here:
//                    FileReader fileRead = null;
//                    String dir = System.getProperty("user.dir");
//                    fileRead = new FileReader(dir + "\\others\\UserLicence.properties");
//                    BufferedReader br = new BufferedReader(fileRead);
//                    String readLine = "";
//                    String licenceLine = "";
//                    int lineCounting = 0;
//                    while ((readLine = br.readLine()) != null) {
//
//                        System.out.println("readLine-->>" + readLine);
//                        if (lineCounting == 1) {
//                            licenceLine = readLine;
//                        }
//                        lineCounting++;
//
//                    }
//                    br.close();
//                    if (!licenceLine.equalsIgnoreCase("Registered User For Licenced Version")) {
//                        PurchaseLicence form = new PurchaseLicence("Purchase Licence");
//                        setstaticvar();
//                        desktopMain.removeAll();
//                        desktopMain.repaint();
//                        Dimension dimension = desktopMain.getSize();
//                        form.setPreferredSize(dimension.getSize());
//                        form.pack();
//                        form.setVisible(true);
//                        desktopMain.add(form);
//                        try {
//                            form.setSelected(true);
//                        } catch (java.beans.PropertyVetoException ex) {
//                            ex.printStackTrace();
//                        }
//                        dontmoveframe();
//                    }
//                } catch (IOException ex) {
//                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
        });
        jMenuLicence.setIcon(new ImageIcon(MainClass.class
                .getResource("/images/stock-options-icon.png")));
        MenuBarMain.add(jMenuLicence);

        jMenuCompany = new JMenu("Company");
        jMenuCompany.addMenuListener(new MenuListener() {
            public void menuCanceled(MenuEvent arg0) {
            }

            public void menuDeselected(MenuEvent arg0) {
            }

            public void menuSelected(MenuEvent arg0) {
                desktopMain.setLayout(new MigLayout("center panel",
                        "[100px:100px:1366px,grow,shrink 50,center]",
                        "[100px:100px:768px,grow,shrink 50,center]"));
            }
        });
        jMenuCompany.setIcon(new ImageIcon(MainClass.class
                .getResource("/images/companies-icon.png")));
        MenuBarMain.add(jMenuCompany);

//        jMenuItem1 = new JMenuItem("Create Company");
//        jMenuItem1.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//            }
//        });
//        jMenuItem1.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                //gen.other.login.Company.Create_Company form = new gen.other.login.Company.Create_Company();
//                gen.company.Create_Company form = new gen.company.Create_Company();
//                desktopMain.removeAll();
//                desktopMain.repaint();
//                Dimension dimension = desktopMain.getSize();
//                form.setPreferredSize(dimension.getSize());
//                form.pack();
//                form.setVisible(true);
//                desktopMain.add(form);
//                try {
//                    form.setSelected(true);
//                } catch (java.beans.PropertyVetoException ex) {
//                    ex.printStackTrace();
//                }
//                dontmoveframe();
//            }
//        });
//        jMenuCompany.add(jMenuItem1);

//        jSeparator2 = new JSeparator();
//        jMenuCompany.add(jSeparator2);

        jMenuItem2 = new JMenuItem("Company Selection");
        jMenuItem2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //jMenuItem2ActionPerformed(null);
            }
        });
        jMenuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //gen.other.login.CompanySelection.CompanysSettings_User form = new gen.other.login.CompanySelection.CompanysSettings_User("Group");
                gen.company.CompanysSettings_User form = new CompanysSettings_User();
                desktopMain.removeAll();
                desktopMain.repaint();
                form.pack();
                form.setVisible(true);
                desktopMain.add(form);
                try {
                    form.setSelected(true);
                } catch (java.beans.PropertyVetoException ex) {
                    ex.printStackTrace();
                }
                dontmoveframe();
            }
        });
        jMenuCompany.add(jMenuItem2);

        jSeparator3 = new JSeparator();
        jMenuCompany.add(jSeparator3);

        jMenuItem3 = new JMenuItem("Company BackUp");
        jMenuItem3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        jMenuItem3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //gen.other.login.CompanyBackUP.DatabaseBackUp_User form = new gen.other.login.CompanyBackUP.DatabaseBackUp_User("Company Back Up");
                gen.company.DatabaseBackUp_User form = new DatabaseBackUp_User();
                desktopMain.removeAll();
                desktopMain.repaint();
                Dimension dimension = desktopMain.getSize();
                form.setPreferredSize(dimension.getSize());
                form.pack();
                form.setVisible(true);
                desktopMain.add(form);
                try {
                    form.setSelected(true);
                } catch (java.beans.PropertyVetoException ex) {
                    ex.printStackTrace();
                }
                dontmoveframe();
                System.out.println("Print mouse clicked ------------------------");
            }
        });
        jMenuCompany.add(jMenuItem3);

        jSeparator4 = new JSeparator();
        jMenuCompany.add(jSeparator4);

        jMenuUser = new JMenu("User");
        jMenuUser.addMenuListener(new MenuListener() {
            public void menuCanceled(MenuEvent e) {
            }

            public void menuDeselected(MenuEvent e) {
            }

            public void menuSelected(MenuEvent e) {
                desktopMain.setLayout(new MigLayout("center panel",
                        "[100px:100px:1366px,grow,shrink 50,center]",
                        "[100px:100px:768px,grow,shrink 50,center]"));
            }
        });
        jMenuUser.setIcon(new ImageIcon(MainClass.class
                .getResource("/images/User-Files-icon.png")));
        MenuBarMain.add(jMenuUser);

//        jMenuItem4 = new JMenuItem("Create User");
//        jMenuItem4.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//            }
//        });
//        jMenuItem4.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                //gen.other.login.User.CreateUser form = new gen.other.login.User.CreateUser();
//                gen.User.CreateUser form = new gen.User.CreateUser();
//                desktopMain.removeAll();
//                desktopMain.repaint();
//                Dimension dimension = desktopMain.getSize();
//                form.setPreferredSize(dimension.getSize());
//                form.pack();
//                form.setVisible(true);
//                desktopMain.add(form);
//                try {
//                    form.setSelected(true);
//                } catch (java.beans.PropertyVetoException ex) {
//                    ex.printStackTrace();
//                }
//                dontmoveframe();
//            }
//        });
//        jMenuUser.add(jMenuItem4);
//
//        jSeparator6 = new JSeparator();
//        jMenuUser.add(jSeparator6);

        jMenuItem5 = new JMenuItem("Alter User");
        jMenuItem5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        jMenuItem5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //gen.other.login.User.Currentuser form = new gen.other.login.User.Currentuser("Current User");
                gen.User.CurrentUser form = new gen.User.CurrentUser();
                desktopMain.removeAll();
                desktopMain.repaint();
                Dimension dimension = desktopMain.getSize();
                form.setPreferredSize(dimension.getSize());
                form.pack();
                form.setVisible(true);
                desktopMain.add(form);
                try {
                    form.setSelected(true);
                } catch (java.beans.PropertyVetoException ex) {
                    ex.printStackTrace();
                }
                dontmoveframe();
            }
        });
        jMenuUser.add(jMenuItem5);

        jSeparator6 = new JSeparator();
        jMenuUser.add(jSeparator6);

        jMenuItem6 = new JMenuItem("Mobile Information");
        jMenuItem6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        jMenuItem6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Schedular.MobileAndRestDataClass form = new Schedular.MobileAndRestDataClass("Mobile Information");
                desktopMain.removeAll();
                desktopMain.repaint();
                Dimension dimension = desktopMain.getSize();
                form.setPreferredSize(dimension.getSize());
                form.pack();
                form.setVisible(true);
                desktopMain.add(form);
                try {
                    form.setSelected(true);
                } catch (java.beans.PropertyVetoException ex) {
                    ex.printStackTrace();
                }
                dontmoveframe();
            }
        });
        jMenuUser.add(jMenuItem6);


        jSeparator5 = new JSeparator();
        jMenuUser.add(jSeparator5);

        jMenuWetalk = new JMenu("WeTalk");
        jMenuWetalk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MakeACall form = new MakeACall();
                setstaticvar();
                desktopMain.removeAll();
                desktopMain.repaint();
                form.pack();
                form.setVisible(true);
                desktopMain.add(form);
                try {
                    form.setSelected(true);
                } catch (java.beans.PropertyVetoException ex) {
                    ex.printStackTrace();
                }
                dontmoveframe();
            }
        });
        jMenuWetalk.addMenuListener(new MenuListener() {
            public void menuCanceled(MenuEvent arg0) {
            }

            public void menuDeselected(MenuEvent arg0) {
            }

            public void menuSelected(MenuEvent arg0) {
                desktopMain.setLayout(new MigLayout("center panel",
                        "[100px:100px:1366px,grow,shrink 50,center]",
                        "[100px:100px:768px,grow,shrink 50,center]"));
            }
        });
        jMenuWetalk.setIcon(new ImageIcon(MainClass.class
                .getResource("/images/app-phone-icon.png")));
        MenuBarMain.add(jMenuWetalk);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("", "[grow,shrink 50,fill]",
                "[grow]"));

        contentPane.add(desktopMain, "cell 0 0,grow");

    }
    private JPanel contentPane;
    private JDesktopPane desktopMain;
    private Dimension screenSize;
    private JMenuBar MenuBarMain;
    private JMenu jMenuInventoryVouchers;
    private JMenuItem jMenuItemGroups;
    private JSeparator jSeparator20;
    private JMenuItem jMenuItemLedgers;
    private JMenuItem jMenuItemLedgersOpeningBalance;
    private JSeparator jSeparator35;
    private JMenuItem jMenuItemStockGroups;
    private JSeparator jSeparator39;
    private JSeparator jSeparator44;
    private JMenuItem jMenuItemStockItemsOpeningBalance;
    private JSeparator jSeparator90;
    private JMenuItem jMenuItemUnitOfMeasures;
    private JSeparator jSeparator45;
    private JMenu jMenuAccoutingVouchers;
    private JMenuItem jMenuItemSales_2;
    private JMenuItem jMenuItemPurchaseOrder;
    private JMenuItem jMenuItemGRNote;
    private JSeparator jSeparator30_2;
    private JMenuItem jMenuItemSales;
    private JMenuItem jMenuItemSales_With_No_cal;
    private JMenuItem jMenuItemSales_No_Final_Amount;
    private JMenuItem jMenuItemSales_With_Discount;
    private JSeparator jSeparator30;
    private JMenuItem jMenuItemPurchase;
    private JSeparator jSeparator31;
    private JMenuItem jMenuItemReceipt;
    private JSeparator jSeparator32;
    private JMenuItem jMenuItemPayment;
    private JSeparator jSeparator33;
    private JMenuItem jMenuItemChallan;
    private JSeparator jSeparator34;
    private JMenuItem jMenuItemContra;
    private JSeparator jSeparator52;
    private JMenu jMenuReportVouchers;
    private JMenu jMenuItemDayBook;
    private JMenuItem jMenuItemSubItemDailyCashFlow;
    private JSeparator separator_9;
    private JMenuItem jMenuItemSubItemDaybookReceiptPayment;
    private JSeparator separator_10;
    private JMenuItem jMenuItemSubItemDailySalePurchaseFlow;
    private JSeparator separator_11;
    private JMenuItem jMenuItemSubItemDailyChalan;
    private JSeparator jSeparator23;
    private JSeparator separator_61;
    private JMenuItem jMenuItemSubItemDailyPO;
    private JMenuItem jMenuItemSaleReport;
    private JSeparator jSeparator37;
    private JMenuItem jMenuItemPurchaseReport;
    private JSeparator jSeparator26;
    private JMenuItem jMenuItemLedgerReport;
    private JSeparator jSeparator24;
    private JMenuItem jMenuItemStockItemReport;
    private JSeparator jSeparator25;
    private JMenuItem jMenuItemTrialBalance;
    private JSeparator jSeparator27;
    private JMenuItem jMenuItemGroupSummary;
    private JSeparator jSeparator28;
    private JMenuItem jMenuItemBalanceSheet;
    private JSeparator jSeparator29;
    private JMenuItem jMenuItemProfitandLoss;
    private JMenuItem jMenuItemFinisnItemDesc;
    private JMenuItem jMenuItemResinProd;
    private JMenuItem jMenuItemIMpregnatedPaperProd;
    private JMenuItem jMenuItemPrelanBoardProd;
    private JSeparator jSeparator50;
    private JMenu jMenuProduction;
    private JMenu jMenuSettings;
    private JMenu jMenuLicence;
    private JMenu jMenuImport;
    private JMenu jMenuCompany;
    private JMenu jMenuUser;
    private JMenuItem jMenuItem1;
    private JSeparator jSeparator2;
    private JMenuItem jMenuItem2;
    private JSeparator jSeparator3;
    private JMenuItem jMenuItem3;
    private JSeparator jSeparator4;
    private JMenuItem jMenuItem4;
    private JSeparator jSeparator6;
    private JMenuItem jMenuItem5;
    private JMenuItem jMenuItem6;
    private JSeparator jSeparator5;
    private JButton jButtonQuit;
    private JMenu jMenuStockItems;
    private JMenuItem jMenuItemSingleStockItem;
    private JMenuItem jMenuItemMultipleStockItem;
    private JSeparator jMenuItemSeparator;
    private JMenu jMenuWetalk;

    public static void setstaticvar() {

        flag_login = 0;
        flag_group_create = 0;
        flag_group_details = 0;
        flag_group_alter = 0;
        flag_groupSummary_report = 0;
        flag_newGroupSummary_report = 0;

        flag_ledger_create = 0;
        flag_ledger_opening_balance = 0;
        flag_ledger_details = 0;
        flag_ledger_alter = 0;

        flag_stock_group_create = 0;
        flag_stock_group_details = 0;
        flag_stock_group_alter = 0;

        flag_single_stock_item_create = 0;
        flag_multiple_stock_item_create = 0;
        flag_stock_item_details = 0;
        flag_stock_item_alter = 0;
        flag_stock_item_opening_balance = 0;

        flag_finish_type = 0;

        flag_uom_create = 0;
        flag_uom_alter = 0;
        flag_uom_details = 0;

        flag_daybook = 0;
        flag_daybook_rec_pay = 0;
        flag_daybook_sales_purchase = 0;
        flag_daybook_chalan = 0;

        flag_ledger_report = 0;

        flag_stock_item_report = 0;

        flag_sales = 0;
        flag_sales_with_No_cal_form = 0;
        flag_sales_old = 0;
//        flag_sales_with_discount = 0;
        flag_purchase = 0;
        flag_payment = 0;
        flag_receipt = 0;
        flag_chalan = 0;
        flag_contra = 0;
        flag_journal = 0;

        flag_addraw = 0;
        flag_addprocess = 0;
        flag_productiontoday = 0;

        flag_new_user = 0;
        flag_change_pass = 0;
        flag_email_setting = 0;
        flag_right_setting = 0;

        flag_backup = 0;
        flag_print = 0;
        flag_trial_report = 0;

        flag_ViewEditCompany = 0;
        flag_CreateCompany = 0;

        flag_sale_report = 0;
        flag_purchase_report = 0;
        flag_cashdaybook = 0;

        flag_ledger_reportadvance = 0;
        flag_ledgerNew = 0;
        flag_daybookAdvance = 0;
        flag_daybookNew = 0;
        flag_AllAdSuMuDi_Settings = 0;
    }

    public void dontmoveframe() {
        try {
            JInternalFrame[] frames = desktopMain.getAllFrames();
            frames[0].setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }

        // Make first internal frame unmovable
        JInternalFrame[] frames = desktopMain.getAllFrames();
        JInternalFrame f = frames[0];
        BasicInternalFrameUI ui = (BasicInternalFrameUI) f.getUI();

        Component north = ui.getNorthPane();
        MouseMotionListener[] actions =
                (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

        for (int i = 0; i < actions.length; i++) {
            north.removeMouseMotionListener(actions[i]);
        }
    }

    public void menuselection(int mn) {
        flag_for_menu_selection = mn;
    }

    private void setAllcomponentInVisible() {
        jMenuItemGroups.setVisible(false);
        jMenuItemLedgers.setVisible(false);
        jMenuItemLedgersOpeningBalance.setVisible(false);
        jMenuItemStockGroups.setVisible(false);
        jMenuStockItems.setVisible(false);
        jMenuItemUnitOfMeasures.setVisible(false);

        jMenuItemSales.setVisible(true);
        jMenuItemPurchase.setVisible(true);
        jMenuItemReceipt.setVisible(true);
        jMenuItemPayment.setVisible(true);
        jMenuItemChallan.setVisible(true);
        jMenuItemContra.setVisible(true);

        jMenuSettings.setVisible(false);
        jMenuLicence.setVisible(false);
        jMenuReportVouchers.setVisible(false);
        jMenuInventoryVouchers.setVisible(false);
        jMenuAccoutingVouchers.setVisible(false);
    }

    public void drivebackupwhileclosing(String dir1) {
        String dbName = "aj";
        String dbUser = "root";
        String dbPass = "admin";
        String executeCmd = "";
        ResultSet rs = null;
        String query = "";
        try {
            executeCmd = operatingSystemDrive + "\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump -u " + dbUser + " -p" + dbPass + " " + dbName + " -r " + dir1 + "/backup.sql";
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.println("Backup taken successfully");
            } else {
                System.out.println("Could not take mysql backup");
            }
            String cmd = operatingSystemDrive + "\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump.exe exit";
            Runtime rt1 = Runtime.getRuntime();
            Process proc1 = rt1.exec(cmd);
            Connection conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            rs = st.executeQuery("select * from tblbackup");
            Calendar currentDate = Calendar.getInstance();
            SimpleDateFormat f2 = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat f3 = new SimpleDateFormat("HH:mm:ss");
            String dat = f2.format(currentDate.getTime());
            String tim = f3.format(currentDate.getTime());
            if (rs.next()) {
                query = "update tblbackup set drive_backup_date='" + dat + "',drive_backup_time='" + tim + "'";
                st.executeUpdate(query);
            } else {
                query = "insert into tblbackup(drive_backup_date,drive_backup_time) values('" + dat + "','" + tim + "')";
                st.executeUpdate(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class imageloading //extends JFrame
    {

        BufferedImage mImage;
        String name, name1;

        public imageloading() {
            String source = filechoose();
            if (source != null) {
                File inputFile = new File(source);
            }
        }

        String filechoose() {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            chooser.setDialogType(JFileChooser.OPEN_DIALOG);
            chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                public boolean accept(File f) {
                    name = f.getName().toLowerCase();
                    return name.endsWith(".xml") || name.endsWith(".xml")
                            || name.endsWith(".xml") || f.isDirectory();
                }

                public String getDescription() {
                    return ".xml";
                }
            });
            int r = chooser.showOpenDialog(MainClass.this);
            if (r == JFileChooser.APPROVE_OPTION) {
                name1 = chooser.getSelectedFile().getAbsolutePath();
                path = name1;
                System.out.println("Dynamic Path:::" + path);
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

    private void createReportDirectory() {
        try {
            String dir = System.getProperty("user.dir");
            String fileName = dir + "/server/report";
            File file = new File(fileName);
            System.out.println("MainClass2---------->>createReportDirectory----------->>File : " + file);
            if (!file.exists()) {
                if (file.mkdir()) {
                    System.out.println("MainClass2---------->>createReportDirectory----------->>Directory created");
                }
            } else {
                makeDirEmpty(file);
            }
        } catch (Exception ex) {
            System.out.println("MainClass2---------->>createReportDirectory----------->>Exception : " + ex);
            ex.printStackTrace();
        }
    }

    private void makeDirEmpty(File file) {
        try {
            if (file.exists()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    System.out.println("MainClass2---------->>makeDirEmpty----------->>File : " + files[i]);
                    if (files[i].isDirectory()) {
                        makeDirEmpty(files[i]);
                    } else {
                        files[i].delete();
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("MainClass2---------->>makeDirEmpty----------->>Exception : " + ex);
            ex.printStackTrace();
        }
    }

    public void getActiveInactiveComponent(String callableclass) {
        setAllcomponentInVisible();
        // if No company Created or First time user login
        List<gen.other.CompanySettings.CompanySettingsDTO> CompanySettingsDTOList = new ArrayList<CompanySettingsDTO>();
        CompanySettingsDTOList = gen.other.CompanySettings.CompanySettingsDAO.getComapany_List("", "");
        // get role for user as if user is Admin then then role is 1 so visible all company to him
        FeaturesDTO featuresDTO1 = new FeaturesDTO();
        featuresDTO1 = gen.mainclass.UserSettingDAO.get_Active_Inactive_By_User(gen.dto.Constants.CURRENT_USER_ID);
        String user_Role = featuresDTO1.getRole_Name();

        System.out.println("User Role -------------- ------      ---------     --------" + user_Role);
        System.out.println("User Role -------------- ------      ---------     --------" + CompanySettingsDTOList.isEmpty());

        if (user_Role.equalsIgnoreCase("admin") && CompanySettingsDTOList.isEmpty()) {

            jMenuSettings.setEnabled(true);
//            jMenuItem1.setEnabled(true);
            jMenuItem2.setEnabled(true);
            jMenuItem3.setEnabled(true);
//            jMenuItem4.setEnabled(true);
            jMenuItem5.setEnabled(true);
//            jMenuItem1.setVisible(true);
            jMenuItem2.setVisible(true);
            jMenuItem3.setVisible(true);
//            jMenuItem4.setVisible(true);
            jMenuItem5.setVisible(true);
            jMenuSettings.setVisible(true);
            jMenuLicence.setVisible(true);

            gen.company.Create_Company form = new gen.company.Create_Company();
            desktopMain.removeAll();
            desktopMain.repaint();
            Dimension dimension = desktopMain.getSize();
            form.setPreferredSize(dimension.getSize());
            form.pack();
            form.setVisible(true);
            desktopMain.add(form);
            try {
                form.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
                e.printStackTrace();
            }
            dontmoveframe();

        } else {
            if (!user_Role.equalsIgnoreCase("admin")) {
//                jMenuItem1.setVisible(false);
//                jMenuItem1.setEnabled(false);
//                jMenuItem4.setVisible(false);
//                jMenuItem4.setEnabled(false);
            }
        }

        System.out.println("Current DAtabaase Name ------------------- " + gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings);
        System.out.println("Current DAtabaase Name ------------------- " + gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings);
        if (!gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings.isEmpty()) {
            gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.getValuesFromTable();
            System.out.println("Calling Features -----------------------------");
            gen.mainclass.FeaturesDTO featuresDTO = gen.mainclass.UserSettingDAO.get_Active_Inactive_By_User(gen.dto.Constants.CURRENT_USER_ID);

            List<String> featureList = new ArrayList<String>();
            featureList = featuresDTO.getFeatureList();

            if (featureList.contains("Group")) {
                jMenuItemGroups.setEnabled(true);
                jMenuItemGroups.setVisible(true);
                jMenuInventoryVouchers.setVisible(true);
            }
            if (featureList.contains("Ledger")) {
                jMenuItemLedgers.setEnabled(true);
                jMenuItemLedgersOpeningBalance.setEnabled(true);
                jMenuItemLedgers.setVisible(true);
                jMenuItemLedgersOpeningBalance.setVisible(true);
                jMenuInventoryVouchers.setVisible(true);
            }
            if (featureList.contains("Stock Group")) {
                jMenuItemStockGroups.setEnabled(true);
                jMenuItemStockGroups.setVisible(true);
                jMenuInventoryVouchers.setVisible(true);
            }
            if (featureList.contains("Stock Item")) {
                jMenuStockItems.setEnabled(true);
                jMenuStockItems.setVisible(true);
                jMenuInventoryVouchers.setVisible(true);
            }
            if (featureList.contains("Unit Of measure")) {
                jMenuItemUnitOfMeasures.setEnabled(true);
                jMenuItemUnitOfMeasures.setVisible(true);
                jMenuInventoryVouchers.setVisible(true);
            }
            if (featureList.contains("Sale")) {
                jMenuItemSales.setEnabled(true);
                jMenuItemSales.setVisible(true);
                jMenuAccoutingVouchers.setVisible(true);
            }
            if (featureList.contains("Purchase")) {
                jMenuItemPurchase.setEnabled(true);
                jMenuItemPurchase.setVisible(true);
                jMenuAccoutingVouchers.setVisible(true);
            }
            if (featureList.contains("Receipt")) {
                jMenuItemReceipt.setEnabled(true);
                jMenuItemReceipt.setVisible(true);
                jMenuAccoutingVouchers.setVisible(true);
            }
            if (featureList.contains("Payment")) {
                jMenuItemPayment.setEnabled(true);
                jMenuItemPayment.setVisible(true);
                jMenuAccoutingVouchers.setVisible(true);
            }
            if (featureList.contains("Challan")) {
                jMenuItemChallan.setEnabled(true);
                jMenuItemChallan.setVisible(true);
                jMenuAccoutingVouchers.setVisible(true);
            }
            if (featureList.contains("Contra")) {
                jMenuItemContra.setEnabled(true);
                jMenuItemContra.setVisible(true);
                jMenuAccoutingVouchers.setVisible(true);
            }
            if (featureList.contains("Report")) {
                jMenuReportVouchers.setEnabled(true);
                jMenuReportVouchers.setVisible(true);
            }
            if (featureList.contains("CompanySettings")) {
                jMenuSettings.setEnabled(true);
                jMenuSettings.setVisible(true);
            }
            if (featureList.contains("NetworkSettings")) {
                jMenuLicence.setEnabled(true);
                jMenuLicence.setVisible(true);
            }
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        Backup b = new Backup("");
        int flag = 0;

        desktopMain.removeAll();
        desktopMain.repaint();
        this.setEnabled(false);
//        MenuBarMain.setEnabled(false);
//        jMenuAccoutingVouchers.setEnabled(false);
//        jMenuInventoryVouchers.setEnabled(false);
//        jMenuReportVouchers.setEnabled(false);

//        ResultSet rs1, rs2;
//        DatabaseConnection1 db1 = new DatabaseConnection1();
//        try {
//            Connection conn = db1.GetConnection();
//            Statement st = conn.createStatement();
//            rs1 = st.executeQuery("select * from tblcurrentlogin");
//            if (rs1.next()) {
//
//                rs2 = st.executeQuery("select * from  tblbackupsetting where username='" + rs1.getString("username") + "'");
//
//                if (rs2.next()) {
//
//                    if (rs2.getInt("backup_on_exit") == 1) {
//                        if (rs2.getInt("email_backup") == 1 && rs2.getInt("drive_backup") == 1) {
//                            //----------------------frame displayed-------------------------
//                            JInternalFrame jf = new JInternalFrame();
//                            JLabel jl = new JLabel();
//                            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                            jf.setSize(new Dimension(400, 100));
//                            jf.setBackground(Color.YELLOW);
//                            jf.setVisible(true);
//                            jl.setText("Please Wait...Backup is being taken...");
//                            jl.setVisible(true);
//                            jf.add(jl);
//                            Dimension desktopSize = desktopMain.getSize();
//                            Dimension jFrameSize = jf.getSize();
//                            jf.setLocation((desktopSize.width - jFrameSize.width) / 2,
//                                    (desktopSize.height - jFrameSize.height) / 2);
//                            jf.setVisible(true);
//                            desktopMain.add(jf);
//                            //----------------------------------------------
//                            b.sendmail(flag);
//                            jf.setVisible(false);
//                            //----------------drive backup
//                            String path = "";
//                            path = FilenameUtils.separatorsToWindows(rs2.getString("drive_backup_path"));
//                            drivebackupwhileclosing(path);
//                            //--------------------------------------
//                        } else if (rs2.getInt("drive_backup") == 1) {
//                            //----------------drive backup
//                            String path = "";
//                            path = FilenameUtils.separatorsToWindows(rs2.getString("drive_backup_path"));
//                            drivebackupwhileclosing(path);
//                            //--------------------------------------
//                        } else if (rs2.getInt("email_backup") == 1) {
//                            //----------------------frame displayed-------------------------
//                            JInternalFrame jf = new JInternalFrame();
//                            JLabel jl = new JLabel();
//                            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                            jf.setSize(new Dimension(400, 100));
//                            jf.setBackground(Color.YELLOW);
//                            jf.setVisible(true);
//                            jl.setText("Please Wait...Backup is being taken...");
//                            jl.setVisible(true);
//                            jf.add(jl);
//                            Dimension desktopSize = desktopMain.getSize();
//                            Dimension jFrameSize = jf.getSize();
//                            jf.setLocation((desktopSize.width - jFrameSize.width) / 2,
//                                    (desktopSize.height - jFrameSize.height) / 2);
//                            jf.setVisible(true);
//                            desktopMain.add(jf);
//                            //----------------------------------------------
//                            b.sendmail(flag);
//                        } else {
//                        }
//                    }
//
//                    if (rs2.getInt("backup_on_exit") == 3) {
//                        int dialogButton1 = JOptionPane.YES_NO_OPTION;
//                        int dialogResult1 = JOptionPane.showConfirmDialog(this, "Do you want to take Backup?", "Warning", dialogButton1);
//                        if (dialogResult1 == 0) {
//                            if (rs2.getInt("email_backup") == 1 && rs2.getInt("drive_backup") == 1) {
//                                b.sendmail(flag);
//                                //----------------drive backup
//                                String path = "";
//                                path = FilenameUtils.separatorsToWindows(rs2.getString("drive_backup_path"));
//                                drivebackupwhileclosing(path);
//                                //--------------------------------------
//                            } else if (rs2.getInt("drive_backup") == 1) {
//                                //----------------drive backup
//                                String path = "";
//                                path = FilenameUtils.separatorsToWindows(rs2.getString("drive_backup_path"));
//                                drivebackupwhileclosing(path);
//                                //--------------------------------------
//                            } else if (rs2.getInt("email_backup") == 1) {
//                                b.sendmail(flag);
//                            } else {
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            Connection conn = DatabaseConnection1.GetConnection();
//            conn.setAutoCommit(false);
//            PreparedStatement st;
//            String q = "delete from tblcurrentlogin";   // Log out the current user
//            st = conn.prepareStatement(q);
//            st.execute();
//            conn.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.exit(1);
    }

    @SuppressWarnings({"rawtypes", "unchecked", "unused"})
    private static void setFontSize() {
        int fontSize = 12;
        Hashtable defaults = UIManager.getDefaults();
        Enumeration keys = defaults.keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();

            if ((key instanceof String) && (((String) key).endsWith(".font"))) {
                FontUIResource font = (FontUIResource) UIManager.get(key);
//                defaults.put (key, new FontUIResource(font.getFontName(),
//                font.getStyle(), fontSize));
                defaults.put(key, new FontUIResource("Segoe UI", Font.BOLD,
                        fontSize));
            }
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("gen.other.AdSuMuDiSettings.AdSuMuDiConstant.getValuesFromTable :::::::::::;  gen.other.AdSuMuDiSettings.AdSuMuDiConstant.getValuesFromTable");
            System.out.println("gen.other.AdSuMuDiSettings.AdSuMuDiConstant.getValuesFromTable :::::::::::;  gen.other.AdSuMuDiSettings.AdSuMuDiConstant.getValuesFromTable");

            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                MainClass m = new MainClass();
                m.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                m.setVisible(true);
            }
        });
    }

    private void create_Tables_Company() {
        try {

            Connection conn = DatabaseConnection1.GetConnection();

            System.out.println("Conn --------- " + conn);
            Statement s = conn.createStatement();

            s.execute("create table registration (image Blob)");
            s.execute("create table tblbackup (email_backup_date date,drive_backup_date date,email_backup_time time,drive_backup_time time)");
            s.execute("create table tblbackupsetting (email_backup int,drive_backup int,backup_on_exit int, username varchar(100) , drive_backup_path varchar(800))");
            s.execute("CREATE TABLE tblaccountvouchersmaxid (salemaxid int DEFAULT NULL,purchasemaxid int DEFAULT NULL,receiptmaxid int DEFAULT NULL,paymentmaxid int DEFAULT NULL,challanmaxid int DEFAULT NULL,journalmaxid int DEFAULT NULL,contramaxid int DEFAULT NULL,field3 int DEFAULT NULL,field4 int DEFAULT NULL,field5 int DEFAULT NULL)");
            s.execute("insert into tblaccountvouchersmaxid (salemaxid,purchasemaxid, receiptmaxid,paymentmaxid,challanmaxid,contramaxid) values(0,0,0,0,0,0)");

            s.execute("CREATE TABLE tbladsumudisettings (name varchar(40) DEFAULT NULL,value int DEFAULT NULL)");
            s.execute(" insert into tbladsumudisettings values('pagination','50')");

            s.execute("Create table tblcompanyinfo ( company_id    int  GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null  ,primary key (company_id) , company_name  varchar(500), company_database  varchar(500) , created_by     varchar(500) , modified_by    varchar(500) , created_date    date , modified_date   date , Alias   varchar(100) , Address    varchar(500) , ContactNo   varchar(100) , EmailId   varchar(200) , IncomeTaxNo   varchar(60) , SaleTaxNo    varchar(60)  , Declaration   varchar(800) , TagLine     varchar(700) , TermCnditions   varchar(1000) , NameOfGod   varchar(200) , SignAuthority    varchar(200) , VatTinNo   varchar(60) , Note    varchar(500), LBTNo   varchar(60) , Field1   varchar(60) , Field2   varchar(60) )");
            s.execute("  CREATE TABLE tbllogin (username varchar(60) NOT NULL,password varchar(60) DEFAULT NULL,new_entry int DEFAULT NULL,acc_vouchers int DEFAULT NULL,report int DEFAULT NULL,production int DEFAULT NULL,user_type int DEFAULT NULL,email_id varchar(80) DEFAULT NULL,email_pass varchar(60) DEFAULT NULL,PRIMARY KEY (username))");
            s.execute("  CREATE TABLE tblcurrentlogin (username varchar(80) DEFAULT NULL, FOREIGN KEY (username) REFERENCES tbllogin (username))");

            s.execute("  CREATE TABLE tblgroup (group_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null, PRIMARY KEY (group_id), group_name varchar(40) DEFAULT NULL,group_under int DEFAULT NULL,  group_alias varchar(40) DEFAULT NULL,  group_isDeletable tinyint DEFAULT NULL,  created_date date DEFAULT NULL,  modified_date date DEFAULT NULL,  modified_user int DEFAULT NULL,  created_user int DEFAULT NULL ) ");
            s.execute("INSERT INTO tblgroup VALUES (50,'Primary',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(54,'Current Liability',50,'',NULL,NULL,NULL,NULL,NULL),(55,'Current Assets',50,'',NULL,NULL,NULL,NULL,NULL),(56,'Suspense Account',50,'',NULL,NULL,NULL,NULL,NULL),(57,'Sundry Debtors',54,'',NULL,NULL,NULL,NULL,NULL),(58,'Sundry Creditors',54,'',NULL,NULL,NULL,NULL,NULL),(59,'Stock-In-Hand',55,'',NULL,NULL,NULL,NULL,NULL),(60,'Sales Account',50,'',NULL,NULL,NULL,NULL,NULL),(61,'Purchase Account',50,'',NULL,NULL,NULL,NULL,NULL),(62,'Loan Liability',50,'',NULL,NULL,NULL,NULL,NULL),(63,'Loans and Advances',55,'',NULL,NULL,NULL,NULL,NULL),(64,'Indirect Expenses',50,'',NULL,NULL,NULL,NULL,NULL),(65,'Indirect Income',50,'',NULL,NULL,NULL,NULL,NULL),(66,'Income (Direct)',50,'',NULL,NULL,NULL,NULL,NULL),(67,'Income (Indirect)',50,'',NULL,NULL,NULL,NULL,NULL),(68,'Expense (Direct)',50,'',NULL,NULL,NULL,NULL,NULL),(69,'Expense (Indirect)',50,'',NULL,NULL,NULL,NULL,NULL),(70,'Bank Account',55,'',NULL,NULL,NULL,NULL,NULL),(71,'Cash In Hand',55,'',NULL,NULL,NULL,NULL,NULL);");

            s.execute(" CREATE TABLE tbltransactionmain (trans_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,trans_receiptNo int DEFAULT NULL, trans_date date DEFAULT NULL,  trans_typeIndex int DEFAULT NULL,  trans_narration varchar(1000) DEFAULT NULL,  trans_grandtotal decimal(40,2) DEFAULT NULL,  trans_transport int DEFAULT NULL,  trans_lessBillAmt decimal(40,2) DEFAULT NULL,  trans_payment varchar(40) DEFAULT NULL,  created_date date DEFAULT NULL,  modified_date date DEFAULT NULL,  created_user int DEFAULT NULL,  modified_user int DEFAULT NULL,  PRIMARY KEY (trans_id))");
            s.execute(" CREATE TABLE tblinventorytransaction ( invtrans_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  trans_id int DEFAULT NULL,  invtrans_type int DEFAULT NULL,  dat date DEFAULT NULL,  PRIMARY KEY (invtrans_id), FOREIGN KEY (trans_id) REFERENCES tbltransactionmain (trans_id))");

            s.execute(" CREATE TABLE tblstockgroup ( sg_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null, sg_name varchar(60) DEFAULT NULL,  sg_alias varchar(40) DEFAULT NULL,  sg_under int DEFAULT NULL,  sg_lbtpercent decimal(20,2) DEFAULT NULL, created_date date DEFAULT NULL,  modified_date date DEFAULT NULL,  created_user int DEFAULT NULL,  modified_user int DEFAULT NULL,PRIMARY KEY (sg_id))");
            s.execute("insert into tblStockGroup(sg_name,sg_alias,sg_under) values('Primary','',0)");

            s.execute("  CREATE TABLE tblstockitemtype(type_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,type_name varchar(40) DEFAULT NULL,PRIMARY KEY (type_id)) ");

            s.execute("  CREATE TABLE tbluomtype ( uomType_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  uomType_name varchar(40) DEFAULT NULL,  PRIMARY KEY (uomType_id))");
            s.execute("INSERT INTO tbluomtype VALUES (1,'Simple'),(2,'Complex')");

            s.execute("  CREATE TABLE tblunitofmeasure(  uomType_id int NOT NULL,  uom_type varchar(50) DEFAULT NULL,  uom_symbol varchar(50) DEFAULT NULL,  uom_formalName varchar(50) DEFAULT NULL,  uom_noOfDecimalPts int DEFAULT NULL,  uom_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null, created_date date DEFAULT NULL,  modified_date date DEFAULT NULL,  created_user int DEFAULT NULL,  modified_user int DEFAULT NULL ,PRIMARY KEY (uom_id),  FOREIGN KEY (uomType_id) REFERENCES tbluomtype (uomType_id))");

            s.execute("  CREATE TABLE tblstockitem(si_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  si_alias varchar(60) DEFAULT NULL,  si_under int DEFAULT NULL,  si_length decimal(40,2) DEFAULT NULL,  si_width decimal(40,2) DEFAULT NULL,  si_thickness decimal(40,2) DEFAULT NULL,  si_rate decimal(40,2) DEFAULT NULL,  si_unitOfMeasure int DEFAULT NULL,  si_openingBalance decimal(40,2) DEFAULT NULL,  si_unit varchar(40) DEFAULT NULL,  si_type int DEFAULT NULL,  si_name varchar(100) DEFAULT NULL,  created_date date DEFAULT NULL,  modified_date date DEFAULT NULL,  created_user int DEFAULT NULL,  modified_user int DEFAULT NULL, PRIMARY KEY (si_id),  FOREIGN KEY (si_under) REFERENCES tblstockgroup (sg_id), FOREIGN KEY (si_type) REFERENCES tblstockitemtype (type_id),  FOREIGN KEY (si_unitOfMeasure) REFERENCES tblunitofmeasure (uom_id))");

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

//            s.execute(" CREATE TABLE tbltranscactionchange (  sale_trans_id varchar(100),chalan_trans_id  varchar(100))");
            s.execute(" CREATE TABLE tbltranscactionchange (  sale_trans_id varchar(100),chalan_trans_id  varchar(100) , voucher_type int)");

            s.execute(" CREATE TABLE tblStockItemOPBlUpdateRecord (  si_id int ,FOREIGN KEY (si_id) REFERENCES tblstockitem (si_id),update_opening_balance_date date , si_openingBalance decimal(30,2), si_under int , FOREIGN KEY (si_under) REFERENCES tblstockgroup (sg_id) ,si_name varchar(60))");

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void alter_Tables_Company() {
        try {
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
            Connection conn = DatabaseConnection1.GetConnection();
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
            System.out.println("Conn --------- " + conn);
            Statement s = conn.createStatement();
//	    conn.setAutoCommit(false);
            System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
//	    s.executeQuery("alter table tblstockgroup Add created_date date");
//	    s.executeQuery("alter table tblstockgroup Add modified_date date");
//	    s.executeQuery("alter table tblstockgroup ADD created_user int");
//	    s.executeQuery("alter table tblstockgroup ADD modified_user int");
            s.executeQuery("alter table tblstockitem Add created_date date");
            s.executeQuery("alter table tblstockitem Add modified_date date");
            s.executeQuery("alter table tblstockitem ADD created_user int");
            s.executeQuery("alter table tblstockitem ADD modified_user int");
            /// Updated on 24-04-2014 BY ATUL
            // Adding Color,Catogary,BoardType,FinishType in tblinventorytransactionitems  
//            s.executeQuery("alter table tblinventorytransactionitems Add category_id int");
//            s.executeQuery("alter table tblinventorytransactionitems  ADD FOREIGN KEY(category_id) REFERENCES tblCategory(category_id)");
//
//            s.executeQuery("alter table tblinventorytransactionitems Add color_id int");
//            s.executeQuery("alter table tblinventorytransactionitems ADD FOREIGN KEY(color_id) REFERENCES tblcolor(color_id)");
//
//            s.executeQuery("alter table tblinventorytransactionitems Add f_id int");
//            s.executeQuery("alter table tblinventorytransactionitems ADD FOREIGN KEY(f_id) REFERENCES tblfinishtype(f_id)");
//
//            s.executeQuery("alter table tblinventorytransactionitems Add typeid int");
//            s.executeQuery("alter table tblinventorytransactionitems ADD FOREIGN KEY(typeid) REFERENCES tbltype(typeid)");

            /// Updated on 25-04-2014 BY ATUL
            // Adding No in tblinventorytransactionitems
            s.executeQuery("alter table tblinventorytransactionitems Add Nos int");

            s.close();
            System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
//	    conn.commit();
            System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private void create_Tables_Company_Info() {
//        try {
//            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
//            Connection conn = DatabaseConnection1.GetConnection();
//            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
//            System.out.println("Conn --------- " + conn);
//            Statement s = conn.createStatement();
//            // for updation of table dont use
////            s.execute("Alter table TBLCOMPANYINFO add column applicable_From_date date");
////             s.execute("update TBLCOMPANYINFO set  applicable_From_date ='2013-1-1' ");
//            /////   
//
//
//
//            s.execute("create table tblothersetting(printpreview int,printDisplayFormat int,printPageFormat int,customerrate int,theme int)");
//            s.execute("  CREATE TABLE tblLicence_Version_Details(application_ID varchar(50), mobile_Key varchar(50))");
//
//            s.execute("CREATE TABLE tblLicence_Version_User_Details(mobile_Key varchar(50),mobile_User_1 varchar(50))");
//
//            s.execute(" CREATE MEMORY TABLE TBLCOMPANYINFO(COMPANY_ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,COMPANY_NAME VARCHAR(500) DEFAULT NULL,COMPANY_DATABASE VARCHAR(500) DEFAULT NULL,CREATED_BY VARCHAR(500) DEFAULT NULL,MODIFIED_BY VARCHAR(500) DEFAULT NULL,CREATED_DATE DATE DEFAULT NULL,MODIFIED_DATE DATE DEFAULT NULL,ALIAS VARCHAR(100) DEFAULT NULL,ADDRESS VARCHAR(500) DEFAULT NULL,CONTACTNO VARCHAR(100) DEFAULT NULL,EMAILID VARCHAR(200) DEFAULT NULL,INCOMETAXNO VARCHAR(60) DEFAULT NULL,SALETAXNO VARCHAR(60) DEFAULT NULL,DECLARATION VARCHAR(800) DEFAULT NULL,TAGLINE VARCHAR(700) DEFAULT NULL,TERMCNDITIONS VARCHAR(1000) DEFAULT NULL,NAMEOFGOD VARCHAR(200) DEFAULT NULL,SIGNAUTHORITY VARCHAR(200) DEFAULT NULL,VATTINNO VARCHAR(60) DEFAULT NULL,NOTE VARCHAR(500) DEFAULT NULL,LBTNO VARCHAR(60) DEFAULT NULL,FIELD1 VARCHAR(60) DEFAULT NULL,FIELD2 VARCHAR(60) DEFAULT NULL , applicable_From_date date)");
//            s.execute("  CREATE MEMORY TABLE TBLROLE(ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,ROLE VARCHAR(200) DEFAULT NULL)");
//            s.execute("  CREATE MEMORY TABLE TBLUSERDETAILS(ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,USERNAME VARCHAR(200) DEFAULT NULL,PASSWORD VARCHAR(200) DEFAULT NULL,EMAILID VARCHAR(60) DEFAULT NULL,EMAILD_PASSWORD VARCHAR(60) DEFAULT NULL,ROLE_ID INTEGER DEFAULT NULL,TYPE INTEGER DEFAULT NULL,FOREIGN KEY(ROLE_ID) REFERENCES TBLROLE(ID))");
//            s.execute("  CREATE MEMORY TABLE TBLCOMPANIESIMAGE(COMPANY_ID INTEGER DEFAULT NULL,COMPANY_NAME VARCHAR(300) DEFAULT NULL,COMPANY_IMAGE BLOB(1G))");
//            s.execute(" CREATE MEMORY TABLE TBLCURRENTCOMPANY(COMPANY_NAME VARCHAR(300) DEFAULT NULL,COMPANY_ID INTEGER DEFAULT NULL,COMPANY_DATABASE VARCHAR(200) DEFAULT NULL,USER_ID INTEGER DEFAULT NULL,FOREIGN KEY(COMPANY_ID) REFERENCES TBLCOMPANYINFO(COMPANY_ID),FOREIGN KEY(USER_ID) REFERENCES TBLUSERDETAILS(ID))");
//            s.execute(" CREATE MEMORY TABLE TBLFEATURE(ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,FEATURE VARCHAR(200) DEFAULT NULL)");
//            s.execute("  CREATE MEMORY TABLE TBLROLEFEATURES(ROLE_ID INTEGER DEFAULT NULL,FEATURE_ID INTEGER DEFAULT NULL,ISACTIVE TINYINT DEFAULT NULL,FOREIGN KEY(ROLE_ID) REFERENCES TBLROLE(ID),FOREIGN KEY(FEATURE_ID) REFERENCES TBLFEATURE(ID))");
//            s.execute("  CREATE MEMORY TABLE TBLUSERTOCOMPANY(USER_ID INTEGER DEFAULT NULL,COMPANY_ID INTEGER DEFAULT NULL,COMPANY_NAME VARCHAR(200) DEFAULT NULL,FOREIGN KEY(USER_ID) REFERENCES TBLUSERDETAILS(ID),FOREIGN KEY(COMPANY_ID) REFERENCES TBLCOMPANYINFO(COMPANY_ID))");
//            s.execute("  CREATE MEMORY TABLE TBLUSERTOROLE(USER_ID INTEGER DEFAULT NULL,ROLE_ID INTEGER DEFAULT NULL,FOREIGN KEY(USER_ID) REFERENCES TBLUSERDETAILS(ID),FOREIGN KEY(ROLE_ID) REFERENCES TBLROLE(ID))");
////            s.execute(" INSERT INTO TBLCOMPANYINFO VALUES(1,'UNNATI SALES','unnatisales',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-1-1')");
////            s.execute(" INSERT INTO TBLCOMPANYINFO VALUES(1,'First','first',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-1-1')");
////            s.execute(" INSERT INTO TBLCOMPANYINFO VALUES(1,'First','first',NULL,NULL,NULL,NULL,NULL,'','','','','',NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,'2012-1-1')");
//            s.execute(" INSERT INTO TBLROLE VALUES(1,'Admin')");
//            s.execute(" INSERT INTO TBLROLE VALUES(2,'Standard')");
//            s.execute(" INSERT INTO TBLROLE VALUES(3,'Reports')");
//            s.execute(" INSERT INTO TBLROLE VALUES(4,'AccountVoucher')");
//            s.execute(" INSERT INTO TBLROLE VALUES(5,'Group')");
//            s.execute(" INSERT INTO TBLROLE VALUES(6,'StockItem')");
//            s.execute(" INSERT INTO TBLUSERDETAILS VALUES(1,'admin','admin',NULL,NULL,NULL,NULL)");
//            s.execute(" INSERT INTO TBLFEATURE VALUES(1,'Group')");
//            s.execute(" INSERT INTO TBLFEATURE VALUES(2,'Ledger')");
//            s.execute(" INSERT INTO TBLFEATURE VALUES(3,'Stock Group')");
//            s.execute(" INSERT INTO TBLFEATURE VALUES(4,'Stock Item')");
//            s.execute(" INSERT INTO TBLFEATURE VALUES(5,'Unit Of measure')");
//            s.execute(" INSERT INTO TBLFEATURE VALUES(7,'Sale')");
//            s.execute(" INSERT INTO TBLFEATURE VALUES(8,'Purchase')");
//            s.execute(" INSERT INTO TBLFEATURE VALUES(9,'Receipt')");
//            s.execute(" INSERT INTO TBLFEATURE VALUES(10,'Payment')");
//            s.execute(" INSERT INTO TBLFEATURE VALUES(11,'Challan')");
//            s.execute(" INSERT INTO TBLFEATURE VALUES(12,'Contra')");
//            s.execute(" INSERT INTO TBLFEATURE VALUES(13,'Report')");
//            s.execute(" INSERT INTO TBLFEATURE VALUES(14,'CompanySettings')");
//            s.execute(" INSERT INTO TBLFEATURE VALUES(15,'NetworkSettings')");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(1,1,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(1,2,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(1,3,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(1,4,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(1,5,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(1,7,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(1,8,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(1,9,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(1,10,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(1,11,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(1,12,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(1,13,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(1,14,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(1,15,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(2,1,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(2,2,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(2,3,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(2,4,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(2,5,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(2,7,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(2,8,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(2,9,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(2,10,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(2,11,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(2,12,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(2,13,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(2,14,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(2,15,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(3,1,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(3,2,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(3,3,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(3,4,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(3,5,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(3,7,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(3,8,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(3,9,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(3,10,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(3,11,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(3,12,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(3,13,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(3,14,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(3,15,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(4,1,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(4,2,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(4,3,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(4,4,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(4,5,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(4,7,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(4,8,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(4,9,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(4,10,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(4,11,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(4,12,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(4,13,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(4,14,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(4,15,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(5,1,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(5,2,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(5,3,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(5,4,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(5,5,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(5,7,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(5,8,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(5,9,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(5,10,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(5,11,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(5,12,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(5,13,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(5,14,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(5,15,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(6,1,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(6,2,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(6,3,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(6,4,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(6,5,1)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(6,7,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(6,8,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(6,9,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(6,10,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(6,11,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(6,12,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(6,13,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(6,14,0)");
//            s.execute(" INSERT INTO TBLROLEFEATURES VALUES(6,15,0)");
////            s.execute(" INSERT INTO TBLUSERTOCOMPANY VALUES(1,1,'UNNATI SALES')");
////            s.execute(" INSERT INTO TBLUSERTOCOMPANY VALUES(1,1,'First')");
//            s.execute(" INSERT INTO TBLUSERTOROLE VALUES(1,1)");
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public static void first_Company_Cretaion() throws Exception {
        insert_First_Company_Information();
        gen.mainclass.DatabaseHelper.createCompanyDatabase("default");
    }

    public static void insert_First_Company_Information() throws Exception {
        gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
        Connection conn = DatabaseConnection1.GetConnection();
        gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
        try {
//            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
//            Connection conn = DatabaseConnection1.GetConnection();
//            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
            System.out.println("Conn --------- " + conn);
            Statement s = conn.createStatement();
//            s.execute(" INSERT INTO TBLCOMPANYINFO VALUES(1,'First','first',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-1-1')");
            s.execute(" INSERT INTO TBLCOMPANYINFO VALUES(1,'Default','default',NULL,NULL,NULL,NULL,NULL,'','','','','',NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,'2012-1-1');");
            s.execute(" INSERT INTO TBLUSERTOCOMPANY VALUES(1,1,'default')");
        } catch (Exception ex) {
            conn.close();
            ex.printStackTrace();
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String readingURLDynamically() throws Exception {

        String dynamicURL = "";
        FileReader read = null;
        try {
            read = new FileReader(dir + "\\others\\DynamicURL.properties");
            System.out.println("Path-->>>" + read);
            BufferedReader br = new BufferedReader(read);
            String readLine = "";
            String readContent = "";
            int lineCountingCheck = 1;
            while ((readLine = br.readLine()) != null) {
                System.out.println("Reading contents-->>>" + readLine);
                if (lineCountingCheck == 3) {
                    dynamicURL = readLine;
                    System.out.println("dynamicURL---PBARWREST--->>>" + dynamicURL);
                }
                lineCountingCheck++;
            }
        } catch (IOException ex) {
            Logger.getLogger(ProgressBarWithNewGraphicsWithREST.class.getName()).log(Level.SEVERE, null, ex);
            read.close();
            throw ex;
        }
        return dynamicURL;
    }
//    public static String ABC() throws Exception {
//	//	    JFrame frame = new JFrame();
//	dialogResult = JOptionPane.showConfirmDialog(MainClass.this, "Your Message", "Title on Box", dialogButton);
////	dialogResult = JOptionPane.showConfirmDialog(frame, "Continue printing?");
//
//	System.out.println(
//		"Cancal ------------- " + dialogButton);
//	if (dialogResult == 0) {
//	    System.out.println("Yes option");
//	} else {
//	    System.out.println("No Option");
//	}
//	String str = "";
//	return str;
//	
//    }

    private void deactivatingAndHidingUnnecessaryFeatures() {

        //Deactivating company menu items

        jSeparator3.setVisible(false);
        jMenuItem3.setVisible(false);
        jSeparator4.setVisible(false);

    }
}