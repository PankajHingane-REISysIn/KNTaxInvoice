/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdSuMuDiSecurity;

import exception.FieldValidationException;
import exception.NetWorkValidationException;
import gen.dto.GUIConstants;
import gen.dto.Label;
import gen.dto.Util;
import gen.mainclass.MainClass;
import gen.other.login.CheckInternetConnection;
import gen.other.login.SampleWebServiceGet;
import gen.progressebar.ProgressBarWithNewGraphicsWithREST;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author : Sudeep K. Masare
 * @DOC : 14-11-2013
 * @TOC : 03:00 P.M.
 * @DOC : Thursday
 * @VOC : Kasturi NovaSoft Pvt. Ltd. Pune,Maharashtra,India.
 * @LMD : --- * Uptill now the option of Purchasing a licenced version of
 * AdSuMuDi is given inside the application in Accounts section. As soon as user
 * clicks the Purchase Licence menu, a form will be opened that automatically
 * fetches the details of the client given at the time of trial version
 * registration into the respective fields and the product key given by
 * dealer.As soon as user clicks on SUBMIT button following STEPS will be
 * executed.
 *
 * STEP 1 : * BACK button option will be disabled thus preventing client from
 * doing any unusual activity during process of online validation of
 * productkey.Thereafter a thread execution will be started showing a busy
 * cursor on screen to indicate the process is underway using-
 *
 * SetWaitTimer s = new SetWaitTimer(); s.startWaitTimer(PurchaseLicence.this);
 *
 * STEP 2 : * In if block,validateData() method will be called to ensure that
 * client have field all the details properly and nothing left empty.This method
 * returns either true/false as a result of validation.If the result is true
 * then only control enters inside and performs next steps.After validating data
 * submit() method will be called to do the further process.
 *
 * STEP 3 : * Object to the PurchaseLicenceDTO.java class will be created as-
 *
 * final PurchaseLicenceDTO purchaseLicenceDTO = new PurchaseLicenceDTO();
 *
 * for storing all the details given by client for the following fields in
 * respective manner- Company/Client name Contact details E-Mail ID
 *
 *
 * In if block,RegisterProductHelper.validateData(RegisterProductDTO
 * registrationDTO) method will be called to ensure that client have field all
 * the details properly and nothing left empty.This method returns either
 * true/false as a result of validation.If the result is true then only control
 * enters inside and performs next steps. * STEP 4 :
 *
 * In the same actionperformed method of SUBMIT button, a REST service will be
 * invoked to send the user details to the server,now for this purpose first of
 * all internet connection availability will be checked using-
 *
 * CheckInternetConnection check = new CheckInternetConnection(); int internet =
 * check.testInternetConnection();
 *
 * if internet == 0 then it means connection is available go ahead invoke REST
 * using * SampleWebServiceGet s = new SampleWebServiceGet(); String line =
 * s.callRestService("http://192.168.1.102:8084/KasturiNovasoft/products/GetKey/key");
 *
 * All the further REST service operations will be performed in following method
 * :
 *
 * String <returnsURL> =
 * PurchaseLicenceHelper.RESTServiceRelatedOperations(splitting,
 * purchaseLicenceDTO); * send details to the server and upon receiving positive
 * response from server write "Registered User For Licence Version" text into
 * UserLicence.dat file using following method as-
 *
 * PurchaseLicenceHelper.writingRegistrationTextToFile();
 *
 * Following method will take the backup of the UserLicence.dat file into the C:
 * for client safety purpose.
 *
 * PurchaseLicenceHelper.regeneratingUserLicenceFileCDriveBackup();
 *
 * During REST service execution,a Yes/No type reponse is returend by server as
 * a symbol of online validation using following method-
 *
 * PurchaseLicenceDTO authorizationPurchaseLicenceDTO =
 * PurchaseLicenceHelper.receivingAuthorizationResponse(splittingFinal);
 *
 *
 * STEP 5 : * As a next step object to the java.util.Date class and Calendar
 * class will be created and the date of 1 month from current date will be fixed
 * as a Trial version time period for client and message will be displayed to
 * the client as "Your Application Is Valid Till <Date>". Now for security
 * purpose the expiration date will be stored in encrypted format. File named
 * EDFSupport.dat will be created in our AdSuMuDi environment containing
 * encrypted date of trial version and DKFSupport.dat will be created in our
 * AdSuMuDi environment containing necessary key to decrypt the date using
 * following 3 statements as-
 *
 * 1)AdSuMuDiEncryptDecrypt.generateEncryptionForEDate(expirationDate);
 *
 * 2)CreateNewFile.createDecryptionKeyFile();
 *
 * 3)CreateNewFile.createExpirationDateFile(); * All the supporting functions
 * are stored in PurchaseLicenceHelper.java helper class. * STEP 6 : * Again as
 * response we have sent encrypted key + "ok" text to the server side using- *
 * authorizationPurchaseLicenceDTO.setSendingBackFinalResultOK(authorizationPurchaseLicenceDTO.getSendingBackFinalResultOK()
 * + "ok");
 * s.callRestService(authorizationPurchaseLicenceDTO.getSendingBackFinalResultOK());
 *
 *
 * All the supporting functions are stored in PurchaseLicenceHelper.java helper
 * class. *
 */
public class PurchaseLicence extends javax.swing.JInternalFrame implements java.awt.event.ActionListener {

    Integer selectedRow = 0;
    String fetchedUniqueKey = "";
    private int currentFocusValue = 0;
    static String staticSecurityKey = "SecurityKeyForAdSuMuDi";
    static String encryptedSecurityKey = "";
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    DateFormat dateToBeSentFormat = new SimpleDateFormat("yyyy-MM-dd");
    BigInteger saveExpirationBigInteger;
    static final String dir = System.getProperty("user.dir");
    Image img = new ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png")).getImage();

    public PurchaseLicence(String s) {
        try {
            this.setClosable(true);
            this.setVisible(true);
            this.setResizable(false);
            initComponents();
            this.setTitle(s);
            initilize();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseLicence.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PurchaseLicence.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initComponents() {
        container = getContentPane();
        toolkit = Toolkit.getDefaultToolkit();
        dimension = toolkit.getScreenSize();
        screenWidth = dimension.width;
        screenHeight = dimension.height;

        xCoordinate = (this.getWidth() / 2) - (screenWidth / 2);
        yCoordinate = (this.getHeight() / 2) - (screenHeight / 2);

        screenResolution = Toolkit.getDefaultToolkit().getScreenResolution();

        flowLblLocationX = (-(xCoordinate) / screenResolution);
        flowLblLocationY = (-(yCoordinate) / screenResolution);

        //fontValue = (-(xCoordinate) / screenResolution) + (-(yCoordinate) / screenResolution);
        fontSize = 11;

        Integer fontCase = Font.BOLD;

        if (screenWidth < 1200) {
            fontCase = Font.PLAIN;
        }
        font = new Font("Tahoma", fontCase, fontSize);

        jMainPanel = new JPanel(null);
        jMainPanel.setBounds(screenWidth / 40, screenHeight / 50, (screenWidth / 2) - 150, screenHeight - 350);
        jMainPanel.setBorder(new TitledBorder(new EtchedBorder(), Label.PURCHASE_LICENCE_FORM_NAME));
        jMainPanel.setFont(font);

        GUIConstants.init(screenWidth, screenHeight);

        labelCompanyName = new JLabel(Label.NAME);
        labelCompanyName.setFont(font);
        labelCompanyName.setBounds(GUIConstants.XAxis_LEVEL_1_1, GUIConstants.YAxis_LEVEL_1_2, GUIConstants.labelSizeMediumWidth, GUIConstants.labelSizeHeight);
        jMainPanel.add(labelCompanyName);

        jTextFieldCompanyName = new JTextField(6);
        jTextFieldCompanyName.setFont(font);
        jTextFieldCompanyName.setBounds(GUIConstants.XAxis_LEVEL_2_1, GUIConstants.YAxis_LEVEL_1_2, GUIConstants.jtextFieldSizeLargeWidth, GUIConstants.jtextFieldSizeHeight);
        jMainPanel.add(jTextFieldCompanyName);

        labelContactNumber = new JLabel(Label.CONTACT_NO);
        labelContactNumber.setFont(font);
        labelContactNumber.setBounds(GUIConstants.XAxis_LEVEL_1_1, GUIConstants.YAxis_LEVEL_2_1, GUIConstants.labelSizeMediumWidth, GUIConstants.labelSizeHeight);
        jMainPanel.add(labelContactNumber);

        jTextFieldContactNumber = new JTextField(6);
        jTextFieldContactNumber.setFont(font);
        jTextFieldContactNumber.setBounds(GUIConstants.XAxis_LEVEL_2_1, GUIConstants.YAxis_LEVEL_2_1, GUIConstants.jtextFieldSizeLargeWidth, GUIConstants.jtextFieldSizeHeight);
        jMainPanel.add(jTextFieldContactNumber);

        labelEmailID = new JLabel(Label.EMAIL_ID);
        labelEmailID.setFont(font);
        labelEmailID.setBounds(GUIConstants.XAxis_LEVEL_1_1, GUIConstants.YAxis_LEVEL_3, GUIConstants.labelSizeLargeWidth, GUIConstants.labelSizeHeight);
        jMainPanel.add(labelEmailID);

        jTextFieldEmailID = new JTextField(6);
        jTextFieldEmailID.setFont(font);
        jTextFieldEmailID.setBounds(GUIConstants.XAxis_LEVEL_2_1, GUIConstants.YAxis_LEVEL_3, GUIConstants.jtextFieldSizeLargeWidth, GUIConstants.jtextFieldSizeHeight);
        jMainPanel.add(jTextFieldEmailID);

        labelProductKey = new JLabel(Label.PRODUCT_KEY);
        labelProductKey.setFont(font);
        labelProductKey.setBounds(GUIConstants.XAxis_LEVEL_1_1, GUIConstants.YAxis_LEVEL_4_1, GUIConstants.labelSizeLargeWidth, GUIConstants.labelSizeHeight);
        jMainPanel.add(labelProductKey);

        jTextFieldProductKeyPart1 = new JTextField(5);
        jTextFieldProductKeyPart1.setFont(font);
        jTextFieldProductKeyPart1.setBounds(GUIConstants.XAxis_LEVEL_2_1, GUIConstants.YAxis_LEVEL_4_1, GUIConstants.jtextFieldSizeSmallWidth, GUIConstants.jtextFieldSizeHeight);
        jTextFieldProductKeyPart1.setDocument(new JTextFieldLimit(5));
        jMainPanel.add(jTextFieldProductKeyPart1);

        jTextFieldProductKeyPart2 = new JTextField(5);
        jTextFieldProductKeyPart2.setFont(font);
        jTextFieldProductKeyPart2.setDocument(new JTextFieldLimit(5));
        jTextFieldProductKeyPart2.setBounds(GUIConstants.XAxis_LEVEL_3_1, GUIConstants.YAxis_LEVEL_4_1, GUIConstants.jtextFieldSizeSmallWidth, GUIConstants.jtextFieldSizeHeight);
        jMainPanel.add(jTextFieldProductKeyPart2);

        jTextFieldProductKeyPart3 = new JTextField(5);
        jTextFieldProductKeyPart3.setFont(font);
        jTextFieldProductKeyPart3.setDocument(new JTextFieldLimit(5));
        jTextFieldProductKeyPart3.setBounds(GUIConstants.XAxis_LEVEL_4_1, GUIConstants.YAxis_LEVEL_4_1, GUIConstants.jtextFieldSizeSmallWidth, GUIConstants.jtextFieldSizeHeight);
        jMainPanel.add(jTextFieldProductKeyPart3);

        jTextFieldProductKeyPart4 = new JTextField(5);
        jTextFieldProductKeyPart4.setFont(font);
        jTextFieldProductKeyPart4.setDocument(new JTextFieldLimit(5));
        jTextFieldProductKeyPart4.setBounds(GUIConstants.XAxis_LEVEL_5_1, GUIConstants.YAxis_LEVEL_4_1, GUIConstants.jtextFieldSizeSmallWidth, GUIConstants.jtextFieldSizeHeight);
        jMainPanel.add(jTextFieldProductKeyPart4);

        jTextFieldProductKeyPart5 = new JTextField(5);
        jTextFieldProductKeyPart5.setFont(font);
        jTextFieldProductKeyPart5.setDocument(new JTextFieldLimit(5));
        jTextFieldProductKeyPart5.setBounds(GUIConstants.XAxis_LEVEL_6_1, GUIConstants.YAxis_LEVEL_4_1, GUIConstants.jtextFieldSizeSmallWidth, GUIConstants.jtextFieldSizeHeight);
        jMainPanel.add(jTextFieldProductKeyPart5);

        jButtonSubmit = new JButton(Label.BUTTON_SUBMIT);
        jButtonSubmit.setFont(font);
        jButtonSubmit.setBounds(GUIConstants.XAxis_LEVEL_4_1 + 10, GUIConstants.YAxis_LEVEL_7, GUIConstants.buttonnSizeMediumWidth, GUIConstants.jtextFieldSizeHeight);
        jButtonSubmit.setMnemonic(KeyEvent.VK_S);
        jMainPanel.add(jButtonSubmit);

        jButtonBack = new JButton(Label.BUTTON_BACK);
        jButtonBack.setFont(font);
        jButtonBack.setBounds(GUIConstants.XAxis_LEVEL_3, GUIConstants.YAxis_LEVEL_7, GUIConstants.buttonnSizeMediumWidth, GUIConstants.jtextFieldSizeHeight);
        jButtonBack.setMnemonic(KeyEvent.VK_B);
        jMainPanel.add(jButtonBack);

        this.setLayout(null);
        this.getContentPane().add(jMainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(screenWidth, screenHeight);
        //this.setVisible(true);

        initialiseActionListeners();
    }

    private void initialiseActionListeners() {

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(Util.getImageIconPath())));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                formInternalFrameOpened(e);
            }

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                formInternalFrameClosing(e);
            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameIconified(InternalFrameEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameDeiconified(InternalFrameEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                formInternalFrameActivated(e);
            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });


        jTextFieldCompanyName.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCompanyNameFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCompanyNameFocusLost(evt);
            }
        });
        jTextFieldCompanyName.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCompanyNameKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        jTextFieldContactNumber.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldContactNumberFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldContactNumberFocusLost(evt);
            }
        });
        jTextFieldContactNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldContactNumberKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        jTextFieldEmailID.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldEmailIDFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldEmailIDFocusLost(evt);
            }
        });
        jTextFieldEmailID.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldEmailIDKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        jTextFieldProductKeyPart1.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldProductKeyPart1KeyTyped(evt);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });


        jTextFieldProductKeyPart2.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldProductKeyPart2KeyTyped(evt);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });



        jTextFieldProductKeyPart3.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldProductKeyPart3KeyTyped(evt);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });



        jTextFieldProductKeyPart4.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldProductKeyPart4KeyTyped(evt);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });


        jTextFieldProductKeyPart5.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldProductKeyPart5KeyTyped(evt);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });


        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Back Button Clicked");
                jButtonBackActionPerformed(evt);
            }
        });

        jButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Submit Button Clicked");
                jButtonSubmitActionPerformed(evt);
            }
        });

        jButtonBack.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonBackKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        jButtonSubmit.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonSubmitKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });
    }

    private void jTextFieldContactNumberFocusGained(java.awt.event.FocusEvent evt) {
        jTextFieldContactNumber.selectAll();
        currentFocusValue = 1;
    }

    private void jTextFieldCompanyNameFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        jTextFieldCompanyName.selectAll();
        currentFocusValue = 0;
    }

    private void jTextFieldCompanyNameFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextFieldCompanyNameKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:

        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER) {
            setFocus(evt);
        }
        if (code == KeyEvent.VK_ESCAPE) {
            jButtonBackActionPerformed(null);
        }
    }

    private void jTextFieldEmailIDFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        jTextFieldEmailID.selectAll();
        currentFocusValue = 2;
    }

    private void jTextFieldEmailIDFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextFieldEmailIDKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:

        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER) {
            jButtonSubmit.requestFocus();
        }
        if (code == KeyEvent.VK_ESCAPE) {
            jTextFieldContactNumber.requestFocus();
        }
    }

    private void jTextFieldProductKeyPart1KeyTyped(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (jTextFieldProductKeyPart1.getText().length() >= 4) {
            //evt.consume();
            jTextFieldProductKeyPart2.requestFocus();
        }
    }

    private void jTextFieldProductKeyPart2KeyTyped(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (jTextFieldProductKeyPart2.getText().length() >= 4) {
            //evt.consume();
            jTextFieldProductKeyPart3.requestFocus();
        }
    }

    private void jTextFieldProductKeyPart3KeyTyped(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (jTextFieldProductKeyPart3.getText().length() >= 4) {
            //evt.consume();
            jTextFieldProductKeyPart4.requestFocus();
        }
    }

    private void jTextFieldProductKeyPart4KeyTyped(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (jTextFieldProductKeyPart4.getText().length() >= 4) {
            //evt.consume();
            jTextFieldProductKeyPart5.requestFocus();
        }
    }

    private void jTextFieldProductKeyPart5KeyTyped(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (jTextFieldProductKeyPart5.getText().length() >= 4) {
            //evt.consume();
            jButtonSubmit.requestFocus();
        }
    }

    private void jTextFieldContactNumberFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextFieldContactNumberKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {

        MainClass.setstaticvar();
        MainClass m = new MainClass();
        m.menuselection(1);
        this.dispose();

    }

    private void jButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {
        if (validateData()) {

            jButtonBack.setEnabled(false);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SetWaitTimer.startWaitTimerForFrame(PurchaseLicence.this);
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        submitDataToPurchaseLicence();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(PurchaseLicence.this, ex.getMessage());
                        Logger.getLogger(PurchaseLicence.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();
        }
    }

    private void jButtonBackKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {

            jButtonSubmit.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            jButtonBackActionPerformed(null);

        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            jTextFieldEmailID.requestFocus();

        }
    }

    private void jButtonSubmitKeyPressed(java.awt.event.KeyEvent evt) {

        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {

            jButtonBack.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            jButtonSubmitActionPerformed(null);

        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            jTextFieldEmailID.requestFocus();

        }
    }
    // GUI fields declaration
    private Container container;
    private Toolkit toolkit;
    private Dimension dimension;
    private Integer screenWidth;
    private Integer screenHeight;
    private Integer xCoordinate;
    private Integer yCoordinate;
    private Integer screenResolution;
    private Integer flowLblLocationX;
    private Integer flowLblLocationY;
    private Integer fontValue;
    private Integer fontSize;
    private Font font;
    private JPanel jMainPanel;
    private JLabel labelCompanyName;
    private JTextField jTextFieldCompanyName;
    private JLabel labelContactNumber;
    private JTextField jTextFieldContactNumber;
    private JLabel labelEmailID;
    private JLabel labelProductKey;
    private JTextField jTextFieldProductKeyPart1;
    private JTextField jTextFieldProductKeyPart2;
    private JTextField jTextFieldProductKeyPart3;
    private JTextField jTextFieldProductKeyPart4;
    private JTextField jTextFieldProductKeyPart5;
    private JTextField jTextFieldEmailID;
    private JButton jButtonSubmit;
    private JButton jButtonBack;

    private void initilize() throws SQLException, ParseException {
        bindTOGUI();
    }

    private void formInternalFrameOpened(InternalFrameEvent evt) {

        FileReader read = null;
        try {
            read = new FileReader(dir + "\\others\\Licence.dat");
            System.out.println("Path-->>>" + read);
            BufferedReader br = new BufferedReader(read);
            String readLine = "";
            String readContent = "";
            int lineCountingCheck = 1;
            while ((readLine = br.readLine()) != null) {
                System.out.println("Reading contents-->>>" + readLine);
                if (lineCountingCheck == 2) {
                    jTextFieldCompanyName.setText(readLine);
                } else if (lineCountingCheck == 3) {
                    jTextFieldContactNumber.setText(readLine);
                } else if (lineCountingCheck == 4) {
                    jTextFieldEmailID.setText(readLine);
                }
                lineCountingCheck++;
            }
        } catch (IOException ex) {
            Logger.getLogger(PurchaseLicence.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                read.close();
            } catch (IOException ex) {
                Logger.getLogger(PurchaseLicence.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void formInternalFrameClosing(InternalFrameEvent evt) {

        MainClass.setstaticvar();
        this.dispose();

    }

    private void formInternalFrameActivated(InternalFrameEvent evt) {

        jButtonBack.setEnabled(true);
        FileReader read = null;
        try {
            read = new FileReader(dir + "\\others\\Licence.dat");
            System.out.println("Path-->>>" + read);
            BufferedReader br = new BufferedReader(read);
            String readLine = "";
            String readContent = "";
            int lineCountingCheck = 1;
            while ((readLine = br.readLine()) != null) {
                System.out.println("Reading contents-->>>" + readLine);
                if (lineCountingCheck == 2) {
                    jTextFieldCompanyName.setText(readLine);
                } else if (lineCountingCheck == 3) {
                    jTextFieldContactNumber.setText(readLine);
                } else if (lineCountingCheck == 4) {
                    jTextFieldEmailID.setText(readLine);
                } else if (lineCountingCheck == 5) {
                    fetchedUniqueKey = readLine;
                    System.out.println("fetchedUniqueKey--->>" + fetchedUniqueKey);
                    System.out.println("fetchedUniqueKey readLine--->>" + readLine);
                }
                lineCountingCheck++;
            }
        } catch (IOException ex) {
            Logger.getLogger(PurchaseLicence.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                read.close();
            } catch (IOException ex) {
                Logger.getLogger(PurchaseLicence.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void validateData(PurchaseLicenceDTO purchaseLicenceDTO) throws FieldValidationException {

        if (purchaseLicenceDTO.getCompanyName().equalsIgnoreCase("")) {
            throw new FieldValidationException("Enter valid company name");
        }
        if (purchaseLicenceDTO.getCompanyContactDetails().equalsIgnoreCase("")) {
            throw new FieldValidationException("Enter valid contact details");
        }
        if (purchaseLicenceDTO.getCompanyEmailID().equalsIgnoreCase("")) {
            throw new FieldValidationException("Enter valid E-Mail ID");
        }
    }


    private void submitDataToPurchaseLicence() throws SQLException, ParseException, FieldValidationException, Exception {

        final PurchaseLicenceDTO purchaseLicenceDTO = new PurchaseLicenceDTO();
        purchaseLicenceDTO.setCompanyName(jTextFieldCompanyName.getText().toString().trim());
        purchaseLicenceDTO.setCompanyContactDetails(jTextFieldContactNumber.getText().toString().trim());
        purchaseLicenceDTO.setCompanyEmailID(jTextFieldEmailID.getText().toString().trim());

        validateData(purchaseLicenceDTO);
        String content = "";
        content = "" + "\n" + purchaseLicenceDTO.getCompanyName() + "\n" + purchaseLicenceDTO.getCompanyContactDetails() + "\n" + purchaseLicenceDTO.getCompanyEmailID();
        final String productKey = jTextFieldProductKeyPart1.getText() + "-" + jTextFieldProductKeyPart2.getText() + "-" + jTextFieldProductKeyPart3.getText() + "-" + jTextFieldProductKeyPart4.getText() + "-" + jTextFieldProductKeyPart5.getText();
        purchaseLicenceDTO.setEntireDetails(content);
        purchaseLicenceDTO.setUniqueKey(fetchedUniqueKey);
        purchaseLicenceDTO.setProductKey(productKey);
        try {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        CheckInternetConnection.testInternetConnection();
                        java.util.Date date = new java.util.Date();
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        final String dateToBeSent = dateToBeSentFormat.format(cal.getTime());
                        System.out.println("dateToBeSent-->>" + dateToBeSent);

                        SampleWebServiceGet webServiceRest = new SampleWebServiceGet();
                        String line = webServiceRest.callRestService(PurchaseLicenceHelper.readingURLDynamically());
                        System.out.println("Called URL Dynamically--->>>" + line);
                        System.out.println("Response Line--PurchaseLicence--->>" + line);
                        String splitting[] = line.split("/");

                        String finalHitURLViaREST = PurchaseLicenceHelper.RESTServiceRelatedOperations(dateToBeSent, splitting, purchaseLicenceDTO);

                        System.out.println("finalHitURLViaREST--->>>" + finalHitURLViaREST);

                        String splittingFinal[] = finalHitURLViaREST.split("/");

                        PurchaseLicenceDTO authorizationPurchaseLicenceDTO = PurchaseLicenceHelper.receivingAuthorizationResponse(splittingFinal);

                        //todo

                        if (authorizationPurchaseLicenceDTO.getDecisionResponse().equalsIgnoreCase("Yes")) {

                            //to remove

                            JOptionPane.showMessageDialog(PurchaseLicence.this, "Congratulations You Are Registered As Licenced User");

                            PurchaseLicenceHelper.writingRegistrationTextToFile();

                            PurchaseLicenceHelper.regeneratingUserLicenceFileCDriveBackup();

                            // gettting last entry of URL
                            String splittingFinal1[] = finalHitURLViaREST.split("/");
                            // gettting entry of Mobile Data only
                            String splitting_For_Moblie_Key = splittingFinal1[splittingFinal1.length - 1];
                            // generating .properties file for mobile_id and id fro respose
                            PurchaseLicenceDAO.writingMobile_Information_ID_TextToFile(splitting_For_Moblie_Key);


                            cal.setTime(date);
                            cal.add(Calendar.YEAR, 1);
                            String expirationDate = dateFormat.format(cal.getTime());

                            final String licenceExpireDateToBeSent = dateToBeSentFormat.format(cal.getTime());
                            System.out.println("dateToBeSent-->>" + licenceExpireDateToBeSent);
                            //to remove

                            JOptionPane.showMessageDialog(PurchaseLicence.this, "Your Licenced Version Is Activated With 1 Year Validity");

                            AdSuMuDiEncryptDecrypt.generateEncryptionForEDate(expirationDate);

                            CreateNewFile.createDecryptionKeyFile();

                            CreateNewFile.createExpirationDateFile();

                            jButtonBackActionPerformed(null);

                        } else {
                            throw new NetWorkValidationException("Authorisation failure contact your administrator");
                        }

                        authorizationPurchaseLicenceDTO.setSendingBackFinalResultOK(authorizationPurchaseLicenceDTO.getSendingBackFinalResultOK());
                        System.out.println("sendingBackFinalResultOK-->>>" + authorizationPurchaseLicenceDTO.getSendingBackFinalResultOK());
                        webServiceRest.callRestService(authorizationPurchaseLicenceDTO.getSendingBackFinalResultOK());

                        dispose();

                        File fileExistsCheck = new File(dir + "\\others\\Licence.dat");
                        boolean exists = fileExistsCheck.exists();
                        System.out.println("Directory " + fileExistsCheck.getPath() + " exists: " + exists);

                        if (!exists) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        ProgressBarWithNewGraphicsWithREST progressBarWithNewGraphicsWithREST = new ProgressBarWithNewGraphicsWithREST();
                                    } catch (FileNotFoundException ex) {
                                        Logger.getLogger(RegisterProduct.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IOException ex) {
                                        Logger.getLogger(RegisterProduct.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }).start();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(PurchaseLicence.this, ex.getMessage());
                    }
                }
            }).start();
        } catch (Exception ex) {
            Logger.getLogger(PurchaseLicence.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    private void setFocus(java.awt.event.KeyEvent evt) {
        if (evt != null) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                currentFocusValue++;
            } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                currentFocusValue--;
            }
        }
        if (currentFocusValue < 0) {
            currentFocusValue = 0;
        }
        if (currentFocusValue == 0) {
            jTextFieldCompanyName.requestFocus();
        } else if (currentFocusValue == 1) {
            jTextFieldContactNumber.requestFocus();
        } else if (currentFocusValue == 2) {
            jTextFieldEmailID.requestFocus();
        }
    }

    private Boolean validateData() {
        Boolean flag = true;
        if (jTextFieldCompanyName.getText().trim().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(PurchaseLicence.this, Label.ENTER_VALID_COMPANY_NAME);
            jTextFieldCompanyName.requestFocus();
            flag = false;
        } else if ((jTextFieldContactNumber.getText().trim().equalsIgnoreCase(""))) {
            JOptionPane.showMessageDialog(PurchaseLicence.this, Label.ENTER_VALID_CONTACT_NUMBER);
            jTextFieldContactNumber.requestFocus();
            flag = false;
        } else if ((jTextFieldEmailID.getText().trim().equalsIgnoreCase(""))) {
            JOptionPane.showMessageDialog(PurchaseLicence.this, Label.ENTER_VALID_EMAILID);
            jTextFieldEmailID.requestFocus();
            flag = false;
        } else if ((jTextFieldProductKeyPart1.getText().trim().equalsIgnoreCase("")) || (jTextFieldProductKeyPart2.getText().trim().equalsIgnoreCase(""))
                || (jTextFieldProductKeyPart3.getText().trim().equalsIgnoreCase("")) || (jTextFieldProductKeyPart4.getText().trim().equalsIgnoreCase(""))
                || (jTextFieldProductKeyPart5.getText().trim().equalsIgnoreCase(""))) {
            JOptionPane.showMessageDialog(PurchaseLicence.this, Label.ENTER_VALID_PRODUCT_KEY);
            jTextFieldProductKeyPart1.requestFocus();
            flag = false;
        }
        return flag;
    }

    private void bindTOGUI() throws ParseException, SQLException {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public class JTextFieldLimit extends PlainDocument {

        private int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) {
                return;
            }

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }
}
