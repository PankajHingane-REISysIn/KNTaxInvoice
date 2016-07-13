package AdSuMuDiProductRegistration;

import AdSuMuDiSecurity.AdSuMuDiEncryptDecrypt;
import AdSuMuDiSecurity.AdSuMuDiRegistryEntry;
import AdSuMuDiSecurity.CreateNewFile;
import gen.dto.Constants;
import gen.other.login.CheckInternetConnection;
import gen.other.login.SampleWebServiceGet;
import gen.progressebar.ProgressBarWithNewGraphicsWithREST;
import gen.progressebar.ProgressBarWithNewGraphicsWithoutREST;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

/*@author : Sudeep K. Masare
 *@DOC    : 14-11-2013
 *@TOC    : 11:41 A.M.
 *@DOC    : Thursday
 *@VOC    : Kasturi NovaSoft Pvt. Ltd. Pune,Maharashtra,India.
 *@LMD    : --- 
  
 * As soon as the object is created in AdSuMuDiExecution.java, the constructor of RegisterProduct.java class will be 
 called, asking user to fill up the details of Company Name,Contact Details and E-mail ID. All of these fields 
 are mandatory. There are two options in button format are present over this form as TRIAL VERSION and 
 LICENCED VERSION. 
 * When user clicks on TRIAL VERSION button then execution flow goes through following steps :

 * STEP 1 : 

 Object to the RegisterProductDTO.java class will be created as-
 
 final RegisterProductDTO registrationDTO = new RegisterProductDTO();
 
 for storing all the details given by client for the following fields in respective manner-
 * Company/Client name
 * Contact details
 * E-Mail ID
 
 * STEP 2 : 
 
 In if block,RegisterProductHelper.validateData(RegisterProductDTO registrationDTO) method will be called to ensure that client have field all the details properly and
 nothing left empty.This method returns either true/false as a result of validation.If the result is true then only
 control enters inside and performs next steps.
 
 * STEP 3 : 
 
 As soon as control enters the if block, Licence.dat file will be generated in our AdSuMuDi environment using following
 statement-
 
 File licencefile = CreateNewFile.createLicenceFile();
 
 and all the details given by the client will be written into Licence.dat file.
  
 * STEP 4 :
  
 As a next step object to the java.util.Date class and Calendar class will be created and the date of 1 month from
 current date will be fixed as a Trial version time period for client and message will be displayed to the client as
 "Your Application Is Valid Till <Date>". Now for security purpose the expiration date will be stored in encrypted
 format.
 File named EDFSupport.dat will be created in our AdSuMuDi environment containing encrypted date of trial
 version and DKFSupport.dat will be created in our AdSuMuDi environment containing necessary key to decrypt the date
 using following 3 statements as-
 
 1)AdSuMuDiEncryptDecrypt.generateEncryptionForEDate(expirationDate);

 2)CreateNewFile.createDecryptionKeyFile();

 3)CreateNewFile.createExpirationDateFile();
  
 * STEP 5 : 
  
 In the same actionperformed method of TRIAL VERSION button, a REST service will be invoked to send 
 the user details to the server,now for this purpose first of all internet connection availability will be checked 
 using-
		
 CheckInternetConnection check = new CheckInternetConnection();
 int internet = check.testInternetConnection();
								
 if internet == 0 then it means connection is available go ahead invoke REST using 

 SampleWebServiceGet s = new SampleWebServiceGet();
 String line = s.callRestService("http://192.168.1.102:8084/KasturiNovasoft/products/GetKey/key");

 All the further REST service operations will be performed in following method :

 RegisterProductHelper.RESTServiceRelatedOperations(dateToBeSent, splitting, registrationDTO); 

 send details to the server and upon receiving positive response from server write "Registered User For Trial Version" 
 text into Licence.dat file using following method as-
 
 RegisterProductHelper.writingRegistrationTextToFile();
  
 * STEP 6 :

 Then after this, entry in registry will be made as 1 using :
	
 AdSuMuDiRegistryEntry are = new AdSuMuDiRegistryEntry();
 are.firstEntry();

 * STEP 7 :

 In case user uninstalls the software,backup of all the necessary files created up till now will be taken using
 following methods-
 
 * RegisterProductHelper.copyEDFSupportFileFromEnvironmentToCDrive();
 * RegisterProductHelper.copyDKFSupportFileFromEnvironmentToCDrive();
 * RegisterProductHelper.copyLicenceFileFromEnvironmentToCDrive();
 * RegisterProductHelper.copyUserLicenceFileFromEnvironmentToCDrive();
 
 there after current frame will be disposed and object to the class ProgressBarWithNewGrphics.java will be called
 thus beginning the application for the very first time.

 This entire process is included in thread for time being operation.
 
 * All the supporting functions are stored in RegisterProductHelper.java helper class.
  
 */
@SuppressWarnings("serial")
public class RegisterProduct extends JFrame {

    private JPanel contentPane;
    private JTextField jTextFieldCompanyName;
    private JTextField jTextFieldContactDetails;
    private JTextField jTextFieldEmailAddress;
    /**
     * Launch the application.
     */
    static String staticSecurityKey = "SecurityKeyForAdSuMuDi";
    //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    DateFormat dateToBeSentFormat = new SimpleDateFormat("yyyy-MM-dd");
    BigInteger saveExpirationDateBigInteger;
    static String dir = System.getProperty("user.dir");
    Image img = new ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png")).getImage();
    String response = "";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegisterProduct frame = new RegisterProduct();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private JButton jButtonTrialVersion;

    /**
     * Create the frame.
     */
    public RegisterProduct() {
        setTitle("AdSuMuDi Trial Version Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image img = new ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png")).getImage();
        RegisterProduct.this.setIconImage(img);
        setBounds(100, 100, 750, 540);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension jInternalFrameSize = RegisterProduct.this.getSize();
        RegisterProduct.this.setLocation(((screenSize.width) - (jInternalFrameSize.width)) / 2,//Changes made by sudeep just put brackets:Date:7-01-2013
                ((screenSize.height) - (jInternalFrameSize.height)) / 2);

        RegisterProduct.this.setVisible(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new MigLayout("", "[0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        JLabel jLabelCompanyName = new JLabel("Client/Company Name");
        panel.add(jLabelCompanyName, "cell 1 4 2 1");

        jTextFieldCompanyName = new JTextField();
        jTextFieldCompanyName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                    jTextFieldContactDetails.requestFocus();

                }
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

                    RegisterProduct.this.dispose();

                }
            }

            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (Character.isSpaceChar(c)) {
                    evt.consume();
                }
            }
        });
        jTextFieldCompanyName.setText("");
        panel.add(jTextFieldCompanyName, "cell 3 4 3 1,growx");
        jTextFieldCompanyName.setColumns(10);

        JLabel jLabelContactDetails = new JLabel("Contact Details");
        panel.add(jLabelContactDetails, "cell 1 6 2 1");

        jTextFieldContactDetails = new JTextField();
        jTextFieldContactDetails.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                    jTextFieldEmailAddress.requestFocus();

                }
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

                    jTextFieldCompanyName.requestFocus();

                }
            }

            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) {
                    evt.consume();
                }
                int flag = 0;
                String f = jTextFieldContactDetails.getText().trim();
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
            }
        });
        panel.add(jTextFieldContactDetails, "cell 3 6 3 1,growx");
        jTextFieldContactDetails.setColumns(10);

        JLabel jLabel1 = new JLabel("E-Mail ID");
        panel.add(jLabel1, "cell 1 8 2 1");

        jTextFieldEmailAddress = new JTextField();
        jTextFieldEmailAddress.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                    jButtonTrialVersion.requestFocus();

                }
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

                    jTextFieldContactDetails.requestFocus();

                }
            }

            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (Character.isSpaceChar(c)) {
                    evt.consume();
                }
            }
        });
        panel.add(jTextFieldEmailAddress, "cell 3 8 3 1,growx");
        jTextFieldEmailAddress.setColumns(10);

        jButtonTrialVersion = new JButton("Register");
        jButtonTrialVersion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                    //jButtonTrialVersionActionPerformed(null);
                    jButtonTrialVersion.getAction();

                }
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

                    jTextFieldEmailAddress.requestFocus();

                }
                if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                    //jButtonLicencedVersion.requestFocus();
                }
            }
        });
        jButtonTrialVersion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // TODO add your handling code here:
                    validations();

                    final RegisterProductDTO registrationDTO = new RegisterProductDTO();

                    registrationDTO.setCompanyName(jTextFieldCompanyName.getText().toString().trim());
                    registrationDTO.setCompanyContactDetails(jTextFieldContactDetails.getText().toString().trim());
                    registrationDTO.setCompanyEmailID(jTextFieldEmailAddress.getText().toString().trim());

                    if (RegisterProductHelper.validateData(registrationDTO)) {

                        String content = "";
                        //String uniqueKey = RegisterProductHelper.generateKey(registrationDTO);
                        registrationDTO.setUniqueKey(RegisterProductHelper.generateKey(registrationDTO));

                        content = "" + "\n" + registrationDTO.getCompanyName() + "\n" + registrationDTO.getCompanyContactDetails() + "\n" + registrationDTO.getCompanyEmailID() + "\n" + registrationDTO.getUniqueKey();

                        File licencefile = CreateNewFile.createLicenceFile();
                        FileWriter licencefilewriter = new FileWriter(licencefile.getAbsoluteFile());
                        BufferedWriter licencefilebufferwriter = new BufferedWriter(licencefilewriter);
                        licencefilebufferwriter.write(content);
                        licencefilebufferwriter.close();

                        java.util.Date date = new java.util.Date();
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        cal.add(Calendar.MONTH, 1);
                        //String expirationDate = dateFormat.format(cal.getTime());
                        String expirationDate = dateToBeSentFormat.format(cal.getTime());

                        final String dateToBeSent = dateToBeSentFormat.format(cal.getTime());
                        System.out.println("dateToBeSent-->>" + dateToBeSent);

                        JOptionPane.showMessageDialog(null, "Your Trial Version Is Valid Till\n" + expirationDate);

                        AdSuMuDiEncryptDecrypt.generateEncryptionForEDate(expirationDate);

                        CreateNewFile.createDecryptionKeyFile();

                        CreateNewFile.createExpirationDateFile();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    CheckInternetConnection.testInternetConnection();

                                    SampleWebServiceGet s = new SampleWebServiceGet();
                                    String line = s.callRestService(readingURLDynamically());
                                    //String line = s.callRestService("http://www.kasturinovasoft.com/products/GetKey/key");
                                    //String line = s.callRestService("http://192.168.1.102:8084/KasturiNovasoft/products/GetKey/key");
                                    System.out.println("Response Line---RegisterProduct1.java--->>" + line);
                                    String splitting[] = line.split("/");

                                    response = RegisterProductHelper.RESTServiceRelatedOperations(dateToBeSent, splitting, registrationDTO);
                                    if (response.equalsIgnoreCase("Inserted Successfully")) {
                                        RegisterProductHelper.writingRegistrationTextToFile();
                                    }
                                } catch (Exception ex) {
                                    Logger.getLogger(RegisterProduct.class.getName()).log(Level.SEVERE, null, ex);
                                    JOptionPane.showMessageDialog(RegisterProduct.this, ex.getMessage());
                                }
                            }
                        }).start();

                        AdSuMuDiRegistryEntry are = new AdSuMuDiRegistryEntry();
                        are.firstEntry();

                        RegisterProductHelper.copyEDFSupportFileFromEnvironmentToCDrive();
                        RegisterProductHelper.copyDKFSupportFileFromEnvironmentToCDrive();
                        RegisterProductHelper.copyLicenceFileFromEnvironmentToCDrive();
                        RegisterProductHelper.copyUserLicenceFileFromEnvironmentToCDrive();

                        RegisterProduct.this.dispose();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    //throw new UnsupportedOperationException("Not supported yet.");
                                    new ProgressBarWithNewGraphicsWithoutREST();
                                } catch (Exception ex) {
                                    Logger.getLogger(RegisterProduct.class.getName()).log(Level.SEVERE, null, ex);
                                    JOptionPane.showMessageDialog(RegisterProduct.this, ex.getMessage());
                                }
                            }
                        }).start();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(RegisterProduct.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(RegisterProduct.this, ex.getMessage());
                }
            }
        });
        panel.add(jButtonTrialVersion, "cell 3 11");
    }

    public static String readingURLDynamically() {

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
                }
                lineCountingCheck++;
            }
        } catch (IOException ex) {
            Logger.getLogger(ProgressBarWithNewGraphicsWithREST.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                read.close();
            } catch (IOException ex) {
                Logger.getLogger(ProgressBarWithNewGraphicsWithREST.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dynamicURL;
    }

    private void validations() throws Exception {

        String companyName = jTextFieldCompanyName.getText().trim();
        Pattern p = Pattern.compile("[a-zA-Z0-9]");
        Matcher matchCompanyName = p.matcher(companyName);

        String email = jTextFieldEmailAddress.getText().toString().trim();
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matchCompanyEmailID = pattern.matcher(email);
        System.out.println("sajfkljfksdfkldsflkd----" + matchCompanyName.matches());
        if (matchCompanyName.matches() || jTextFieldCompanyName.getText().trim().equalsIgnoreCase("")) {
            jTextFieldCompanyName.requestFocus();
            throw new Exception("Enter Valid Company Name");
        } else if (jTextFieldCompanyName.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthMEDIUM) {
            jTextFieldCompanyName.requestFocus();
            throw new Exception("Company Name Exceeding " + Constants.jTextFieldCharacterLengthMEDIUM + " Character Limit");
        } else if (jTextFieldContactDetails.getText().trim().equalsIgnoreCase("")) {
            jTextFieldContactDetails.requestFocus();
            throw new Exception("Enter Valid Contact Details");
        } else if (jTextFieldContactDetails.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthMEDIUM) {
            jTextFieldContactDetails.requestFocus();
            throw new Exception("Contact Details Exceeding " + Constants.jTextFieldCharacterLengthMEDIUM + " Character Limit");
        } else if (!matchCompanyEmailID.matches() || jTextFieldEmailAddress.getText().trim().equalsIgnoreCase("")) {
            jTextFieldEmailAddress.requestFocus();
            throw new Exception("Enter Valid E-Mail Details");
        } else if (jTextFieldEmailAddress.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthMEDIUM) {
            jTextFieldEmailAddress.requestFocus();
            throw new Exception("E-Mail Details Exceeding " + Constants.jTextFieldCharacterLengthMEDIUM + " Character Limit");
        }
    }
}
