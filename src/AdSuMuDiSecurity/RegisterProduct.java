/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdSuMuDiSecurity;

import exception.FieldValidationException;
import exception.NetWorkValidationException;
import gen.other.login.CheckInternetConnection;
import gen.other.login.SampleWebServiceGet;
import gen.progressebar.ProgressBarWithNewGraphicsWithREST;
import gen.progressebar.ProgressBarWithNewGraphicsWithoutREST;
import java.awt.Image;
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
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

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
public class RegisterProduct extends javax.swing.JFrame {

    /**
     * Creates new form RegisterProduct
     */
    static String staticSecurityKey = "SecurityKeyForAdSuMuDi";
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    DateFormat dateToBeSentFormat = new SimpleDateFormat("yyyy-MM-dd");
    BigInteger saveExpirationDateBigInteger;
    static String dir = System.getProperty("user.dir");
    Image img = new ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png")).getImage();
    String response = "";

    public RegisterProduct() {
        this.setIconImage(img);
        this.setSize(600, 500);
        this.setLocation(400, 150);
        this.setVisible(true);
        this.setResizable(false);
        this.pack();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        registerProductPanel = new javax.swing.JPanel();
        jLabelCompanyName = new javax.swing.JLabel();
        jTextFieldCompanyName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldEmailAddress = new javax.swing.JTextField();
        jButtonTrialVersion = new javax.swing.JButton();
        jButtonLicencedVersion = new javax.swing.JButton();
        jLabelContactDetails = new javax.swing.JLabel();
        jTextFieldContactDetails = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(RegisterProduct.class, "RegisterProduct.title")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCompanyName, org.openide.util.NbBundle.getMessage(RegisterProduct.class, "RegisterProduct.jLabelCompanyName.text")); // NOI18N

        jTextFieldCompanyName.setText(org.openide.util.NbBundle.getMessage(RegisterProduct.class, "RegisterProduct.jTextFieldCompanyName.text")); // NOI18N
        jTextFieldCompanyName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCompanyNameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCompanyNameKeyTyped(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(RegisterProduct.class, "RegisterProduct.jLabel1.text")); // NOI18N

        jTextFieldEmailAddress.setText(org.openide.util.NbBundle.getMessage(RegisterProduct.class, "RegisterProduct.jTextFieldEmailAddress.text")); // NOI18N
        jTextFieldEmailAddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldEmailAddressKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldEmailAddressKeyTyped(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonTrialVersion, org.openide.util.NbBundle.getMessage(RegisterProduct.class, "RegisterProduct.jButtonTrialVersion.text")); // NOI18N
        jButtonTrialVersion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTrialVersionActionPerformed(evt);
            }
        });
        jButtonTrialVersion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonTrialVersionKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonLicencedVersion, org.openide.util.NbBundle.getMessage(RegisterProduct.class, "RegisterProduct.jButtonLicencedVersion.text")); // NOI18N
        jButtonLicencedVersion.setEnabled(false);
        jButtonLicencedVersion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLicencedVersionActionPerformed(evt);
            }
        });
        jButtonLicencedVersion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonLicencedVersionKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelContactDetails, org.openide.util.NbBundle.getMessage(RegisterProduct.class, "RegisterProduct.jLabelContactDetails.text")); // NOI18N

        jTextFieldContactDetails.setText(org.openide.util.NbBundle.getMessage(RegisterProduct.class, "RegisterProduct.jTextFieldContactDetails.text")); // NOI18N
        jTextFieldContactDetails.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldContactDetailsKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldContactDetailsKeyTyped(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(RegisterProduct.class, "RegisterProduct.jLabel2.text")); // NOI18N

        javax.swing.GroupLayout registerProductPanelLayout = new javax.swing.GroupLayout(registerProductPanel);
        registerProductPanel.setLayout(registerProductPanelLayout);
        registerProductPanelLayout.setHorizontalGroup(
            registerProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerProductPanelLayout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(registerProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabelCompanyName)
                    .addComponent(jLabelContactDetails))
                .addGap(18, 18, 18)
                .addGroup(registerProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldEmailAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                    .addComponent(jTextFieldCompanyName)
                    .addComponent(jTextFieldContactDetails))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registerProductPanelLayout.createSequentialGroup()
                .addContainerGap(123, Short.MAX_VALUE)
                .addGroup(registerProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registerProductPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(185, 185, 185))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registerProductPanelLayout.createSequentialGroup()
                        .addComponent(jButtonTrialVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonLicencedVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84))))
        );
        registerProductPanelLayout.setVerticalGroup(
            registerProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerProductPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(47, 47, 47)
                .addGroup(registerProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCompanyName)
                    .addComponent(jTextFieldCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(registerProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelContactDetails)
                    .addComponent(jTextFieldContactDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(registerProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldEmailAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(60, 60, 60)
                .addGroup(registerProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonTrialVersion)
                    .addComponent(jButtonLicencedVersion))
                .addContainerGap(161, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(registerProductPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(registerProductPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonTrialVersionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTrialVersionActionPerformed
        try {
            // TODO add your handling code here:
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
                String expirationDate = dateFormat.format(cal.getTime());

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
                            System.out.println("Response Line---RegisterProduct.java--->>" + line);
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

                this.dispose();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //throw new UnsupportedOperationException("Not supported yet.");
                            new ProgressBarWithNewGraphicsWithoutREST();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(RegisterProduct.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(RegisterProduct.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }).start();
            }
        } catch (Exception ex) {
            Logger.getLogger(RegisterProduct.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(RegisterProduct.this, ex.getMessage());
        }
    }//GEN-LAST:event_jButtonTrialVersionActionPerformed

    private void jButtonLicencedVersionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLicencedVersionActionPerformed
        // TODO add your handling code here:
        dispose();
        PurchaseLicence p = new PurchaseLicence("");
        p.setVisible(true);
    }//GEN-LAST:event_jButtonLicencedVersionActionPerformed

    private void jTextFieldCompanyNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCompanyNameKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            jTextFieldContactDetails.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            this.dispose();

        }
    }//GEN-LAST:event_jTextFieldCompanyNameKeyPressed

    private void jTextFieldContactDetailsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldContactDetailsKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            jTextFieldEmailAddress.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            jTextFieldCompanyName.requestFocus();

        }
    }//GEN-LAST:event_jTextFieldContactDetailsKeyPressed

    private void jTextFieldEmailAddressKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldEmailAddressKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            jButtonTrialVersion.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            jTextFieldContactDetails.requestFocus();

        }
    }//GEN-LAST:event_jTextFieldEmailAddressKeyPressed

    private void jButtonTrialVersionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonTrialVersionKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            jButtonTrialVersionActionPerformed(null);

        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            jTextFieldEmailAddress.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {

            jButtonLicencedVersion.requestFocus();

        }
    }//GEN-LAST:event_jButtonTrialVersionKeyPressed

    private void jButtonLicencedVersionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonLicencedVersionKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            jButtonLicencedVersionActionPerformed(null);

        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            jTextFieldEmailAddress.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {

            jButtonTrialVersion.requestFocus();

        }
    }//GEN-LAST:event_jButtonLicencedVersionKeyPressed

    private void jTextFieldContactDetailsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldContactDetailsKeyTyped
        // TODO add your handling code here:
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
    }//GEN-LAST:event_jTextFieldContactDetailsKeyTyped

    private void jTextFieldCompanyNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCompanyNameKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isSpaceChar(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldCompanyNameKeyTyped

    private void jTextFieldEmailAddressKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldEmailAddressKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isSpaceChar(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldEmailAddressKeyTyped
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLicencedVersion;
    private javax.swing.JButton jButtonTrialVersion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelCompanyName;
    private javax.swing.JLabel jLabelContactDetails;
    private javax.swing.JTextField jTextFieldCompanyName;
    private javax.swing.JTextField jTextFieldContactDetails;
    private javax.swing.JTextField jTextFieldEmailAddress;
    private javax.swing.JPanel registerProductPanel;
    // End of variables declaration//GEN-END:variables

    public static void main(String args[]) {

        RegisterProduct rp = new RegisterProduct();
        rp.setSize(600, 500);
        rp.setVisible(true);

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
}
