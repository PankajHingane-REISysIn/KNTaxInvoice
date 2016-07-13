package gen.other.login;

import gen.database.connection.StopMysqlServer1;
import gen.mainclass.DatabaseHelper;
import gen.mainclass.MainClass;
import gen.mainclass.UserSettingDTO;
import gen.other.CompanySettings.CompanySettingsDTO;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Login1 extends javax.swing.JFrame {

    private static Boolean checkpwanduser = false;
    ResultSet rs1, rs2;
    //static JProgressBar jp = new JProgressBar(0, 100);
    final String dir = System.getProperty("user.dir");
    Image img = new ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png")).getImage();

    public Login1() {
        super("AdSuMuDi Login...");
        this.setIconImage(img);
        initComponents();
        //this.setExtendedState(this.getExtendedState() | JFrame.NORMAL);
        // jPanel1.add(jp);
        // jp.setBounds(10, 10, 280, 20);
        // jp.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUname = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("Login");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("Username");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setText("Password");

        txtUname.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtUname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUnameFocusGained(evt);
            }
        });
        txtUname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUnameKeyPressed(evt);
            }
        });

        txtPassword.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPasswordFocusGained(evt);
            }
        });
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });

        btnLogin.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        btnLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLoginKeyPressed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        btnExit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnExitKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(22, 22, 22)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtPassword)
                    .addComponent(txtUname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(130, 130, 130)))
                .addGap(29, 29, 29))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnLogin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUname, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExit)
                    .addComponent(btnLogin))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
//        StopMysqlServer1 stopMysqlServer = new StopMysqlServer1();
//        stopMysqlServer.start();


        this.setVisible(false);
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void txtUnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnExitActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPassword.requestFocus();
        }
    }//GEN-LAST:event_txtUnameKeyPressed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed

        try {
            char[] a = txtPassword.getPassword();
            String password = new String(a);

//	List<gen.other.CompanySettings.CompanySettingsDTO> companySettingsDTOList = new ArrayList<CompanySettingsDTO>();
//	companySettingsDTOList = gen.other.CompanySettings.CompanySettingsDAO.getComapany_List("", "");
//	String company_id = "";
//	for (gen.other.CompanySettings.CompanySettingsDTO companySettingsDTO : companySettingsDTOList) {
//	    if (gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings.equals(companySettingsDTO.getCompany_DatabaseName())) {
//		company_id = companySettingsDTO.getCompany_id().toString();
//	    }
//	}

            // Check data Folder is Empty or Not
            // if Empty then add Company Database in it.
            // dir is a project path
            File file = new File(dir + "\\data");
            if (file.isDirectory()) {
                // if files are present or not in data folder
                if (file.list().length <= 0) {
                    DatabaseHelper.create_Tables_Company_Info();
                    System.out.println("Directory is empty!");
                }
            }

            if (Validation_For_Login(password)) {

                // get User details
                List<UserSettingDTO> userSettingDTOList = new ArrayList<UserSettingDTO>();
                userSettingDTOList = gen.mainclass.UserSettingDAO.get_User_Details("", txtUname.getText().toString().trim());

                for (UserSettingDTO userSettingDTO : userSettingDTOList) {
                    if (userSettingDTO.getUser_Name().equalsIgnoreCase(txtUname.getText().toString().trim()) && password.equals(userSettingDTO.getUser_Password())) {
                        gen.dto.Constants.CURRENT_USER_ID = userSettingDTO.getUser_id();
                        gen.dto.Constants.CURRENT_USER_NAME = userSettingDTO.getUser_Name();
                        // Database Details
                        CompanySettingsDTO companySettingsDTO = new CompanySettingsDTO();
                        companySettingsDTO.setCompany_id(Long.MIN_VALUE);
                        gen.mainclass.UserSettingDAO.getCurrent_Company_Details_By_User(userSettingDTO);
                    }
                }


                // check user_id is present in tblcurrentcompnay table
                List<CompanySettingsDTO> CompanySettingsDTOList = new ArrayList<CompanySettingsDTO>();
                CompanySettingsDTOList = gen.mainclass.UserSettingDAO.getCurrentCompany_UserID(gen.dto.Constants.CURRENT_USER_ID);
//	    for (CompanySettingsDTO companySettingsDTO : CompanySettingsDTOList) {
//		gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = companySettingsDTO.getCompany_DatabaseName();
//		gen.dto.Constants.CURRENT_COMPANY_NAME = companySettingsDTO.getCompany_name();
//		System.out.println("Current Copmany ::::::::;  -------- "+gen.dto.Constants.CURRENT_COMPANY_NAME);
//	    }


//	    // Database of select company
//	    List<gen.other.CompanySettings.CompanySettingsDTO> companySettingsDTOList = new ArrayList<CompanySettingsDTO>();
//	    companySettingsDTOList = gen.other.CompanySettings.CompanySettingsDAO.getComapany_List("", "");
//
//	    for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList) {
//		if (gen.dto.Constants.CURRENT_COMPANY_ID.equals(companySettingsDTO.getCompany_id().toString())) {
//		    gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = companySettingsDTO.getCompany_DatabaseName();
//		    gen.dto.Constants.CURRENT_COMPANY_NAME = companySettingsDTO.getCompany_name();
//		    break;
//		}
//	    }


                if (MainClass.flag_login == 0) {
                    // insert company if not present
                    List<gen.other.CompanySettings.CompanySettingsDTO> CompanySettingsDTOList1 = new ArrayList<CompanySettingsDTO>();
                    CompanySettingsDTOList1 = gen.other.CompanySettings.CompanySettingsDAO.getComapany_List("", "");
                    System.out.println("List --------- " + CompanySettingsDTOList1.size());
                    if (CompanySettingsDTOList1.size() == 0) {
                        MainClass.first_Company_Cretaion();
                    }

                    MainClass.flag_login = 1;
                    MainClass m = new MainClass();
                    m.setVisible(true);
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    this.setPreferredSize(screenSize);
                    this.setVisible(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(Login1.this, "Database Error Occur ");

        }


    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtUname.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnLoginActionPerformed(null);
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        btnLogin.setMnemonic(KeyEvent.VK_L);
        btnExit.setMnemonic(KeyEvent.VK_E);


        //---------------------pankaj-----------------------
//        USBRunnable usbRunnable=new USBRunnable();
//        usbRunnable.runApps();
////        
//        StartMysqlServer mysqlConnection=new StartMysqlServer();
//        mysqlConnection.start();//start mysql Server

        /*   no need     RunJavaFile runJavaFile=new RunJavaFile();
         //        runJavaFile.start();*/
        //--------------------------------------------------

    }//GEN-LAST:event_formWindowOpened

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        this.setExtendedState(this.getExtendedState() | JFrame.NORMAL);
    }//GEN-LAST:event_formWindowStateChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void btnLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLoginKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnLoginActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnExit.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtPassword.requestFocus();
        }
    }//GEN-LAST:event_btnLoginKeyPressed

    private void btnExitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnExitKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnExitActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnLogin.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtPassword.requestFocus();
        }
    }//GEN-LAST:event_btnExitKeyPressed

    private void txtUnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUnameFocusGained
        // TODO add your handling code here:
        txtUname.selectAll();
    }//GEN-LAST:event_txtUnameFocusGained

    private void txtPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordFocusGained
        // TODO add your handling code here:
        txtPassword.selectAll();
    }//GEN-LAST:event_txtPasswordFocusGained

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login1 l = new Login1();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                //l.setSize(screenSize);
                Dimension jInternalFrameSize = l.getSize();
                l.setLocation(((screenSize.width) - (jInternalFrameSize.width)) / 2,//Changes made by sudeep just put brackets:Date:7-01-2013
                        ((screenSize.height) - (jInternalFrameSize.height)) / 2);

                l.setVisible(true);


            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUname;
    // End of variables declaration//GEN-END:variables

//    public static class thread1 implements Runnable {
//
//        public void run() {
//            for (int i = 0; i <= 100; i++) { //Progressively increment variable i
//                //  jp.setValue(i); //Set value
//                //  jp.repaint(); //Refresh graphics
//                try {
//                    Thread.sleep(50);
//                } //Sleep 50 milliseconds
//                catch (InterruptedException err) {
//                }
//            }
//        }
//    }
    private Boolean Validation_For_Login(String password) {


        Boolean flag = true;

        if (txtUname.getText().toString().trim().equals("")) {

            JOptionPane.showMessageDialog(null, "Enter User Name");
            flag = false;
            txtUname.requestFocus();

        } else if (password.equals("")) {

            JOptionPane.showMessageDialog(null, "Enter Password ");
            flag = false;
            txtPassword.requestFocus();

        } else if (!txtUname.getText().toString().trim().equals("") && !password.equals("")) {


            List<UserSettingDTO> userSettingDTOList = new ArrayList<UserSettingDTO>();
            userSettingDTOList = gen.other.login.LoginDAO.validete_User_For_Company("", txtUname.getText().toString().trim(), password, "");

            if (!userSettingDTOList.isEmpty()) {
                for (UserSettingDTO userSettingDTO : userSettingDTOList) {
                    if (txtUname.getText().toString().trim().equalsIgnoreCase(userSettingDTO.getUser_Name().toString().trim()) && password.equals(userSettingDTO.getUser_Password().toString().trim())) {
                        gen.dto.Constants.CURRENT_COMPANY_ID = userSettingDTO.getCompany_id();
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong UserName or  Password ");
                        flag = false;
                        txtUname.requestFocus();
                    }
                }
            } else {

                JOptionPane.showMessageDialog(null, "Wrong UserName or  Password ");
                flag = false;
                txtUname.requestFocus();

            }


//	    List<UserSettingDTO> UserSettingDTOList = new ArrayList<UserSettingDTO>();
//	    UserSettingDTOList = gen.other.login.LoginDAO.validete_User_For_Company("", txtUname.getText().toString().trim(), password);
//	    String match_Password = "";
//	    for (UserSettingDTO usersettingsDTO : UserSettingDTOList) {
//		gen.dto.Constants.CURRENT_USER_ID = usersettingsDTO.getUser_id();
//		match_Password = usersettingsDTO.getUser_Password();
//	    }
//	    if (UserSettingDTOList.isEmpty() || !match_Password.equals(password)) {
//		JOptionPane.showMessageDialog(null, "Wrong UserName or  Password ");
//		flag = false;
//		txtUname.requestFocus();
//	    }
        }
        return flag;
    }
}
