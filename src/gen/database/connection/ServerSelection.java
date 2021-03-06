/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.database.connection;

import com.jtattoo.plaf.aero.AeroLookAndFeel;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.luna.LunaLookAndFeel;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import com.jtattoo.plaf.texture.TextureLookAndFeel;
import gen.other.login.Login1;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author admin
 */
public class ServerSelection extends javax.swing.JFrame {

    /**
     * Creates new form ServerSelection
     */
    Image img = new ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png")).getImage();

    public ServerSelection() {
	this.setIconImage(img);
	try {
	    //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    String ch = loadingThemeSettings();
	    System.out.println("  Ch  " + ch);
	    if (ch.equalsIgnoreCase("1") || ch.equalsIgnoreCase("2") || ch.equalsIgnoreCase("3") || ch.equalsIgnoreCase("4") || ch.equalsIgnoreCase("5")) {
		try {
		    changeThemeOfApplication(ch);
		} catch (ClassNotFoundException ex) {
		    Logger.getLogger(ServerSelection.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
		    Logger.getLogger(ServerSelection.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
		    Logger.getLogger(ServerSelection.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
		    Logger.getLogger(ServerSelection.class.getName()).log(Level.SEVERE, null, ex);
		}

	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        optMySQL = new javax.swing.JRadioButton();
        optHSQLDB = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ServerSelection.class, "ServerSelection.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(optMySQL, org.openide.util.NbBundle.getMessage(ServerSelection.class, "ServerSelection.optMySQL.text")); // NOI18N
        optMySQL.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optMySQLStateChanged(evt);
            }
        });
        optMySQL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                optMySQLKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(optHSQLDB, org.openide.util.NbBundle.getMessage(ServerSelection.class, "ServerSelection.optHSQLDB.text")); // NOI18N
        optHSQLDB.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optHSQLDBStateChanged(evt);
            }
        });
        optHSQLDB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                optHSQLDBKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(ServerSelection.class, "ServerSelection.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(168, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(optHSQLDB, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(optMySQL, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(optMySQL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(optHSQLDB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
	// TODO add your handling code here:
	if (optMySQL.isSelected() == false && optHSQLDB.isSelected() == false) {
	    JOptionPane.showMessageDialog(null, "Select ny option first");
	} else {
	 
		if (optMySQL.isSelected()) {
		    gen.dto.Constants.DATABASE_SERVER = "com.mysql.jdbc.Driver";
		} else {
		    gen.dto.Constants.DATABASE_SERVER = "org.hsqldb.jdbcDriver";
		}

		this.setVisible(false);

//		new ProgressBarWithNewGraphicsWithREST1();
		Login1 m = new Login1();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Dimension jInternalFrameSize = m.getSize();
                m.setLocation(((screenSize.width) - (jInternalFrameSize.width)) / 2,//Changes made by sudeep just put brackets:Date:7-01-2013
                        ((screenSize.height) - (jInternalFrameSize.height)) / 2);
                m.setVisible(true);
	   
	}

	System.out.println("VAlue ----------------------- " + gen.dto.Constants.DATABASE_SERVER);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void optMySQLStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optMySQLStateChanged
	// TODO add your handling code here:
	if (optMySQL.isSelected()) {
	    optHSQLDB.setSelected(false);
	    //btnSave.setEnabled(true);
	}
    }//GEN-LAST:event_optMySQLStateChanged

    private void optHSQLDBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optHSQLDBStateChanged
	// TODO add your handling code here:
	if (optHSQLDB.isSelected()) {
	    optMySQL.setSelected(false);
	    //btnSave.setEnabled(true);
	}
    }//GEN-LAST:event_optHSQLDBStateChanged

    private void optMySQLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optMySQLKeyPressed
	// TODO add your handling code here:
//	 if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            optNever.requestFocus();
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
//            checkDrive.requestFocus();
//        }
    }//GEN-LAST:event_optMySQLKeyPressed

    private void optHSQLDBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optHSQLDBKeyPressed
	// TODO add your handling code here:
//	 if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            optNever.requestFocus();
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
//            checkDrive.requestFocus();
//        }
    }//GEN-LAST:event_optHSQLDBKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
	/* Set the Nimbus look and feel */
	//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
	 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
	 */
	try {
	    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		if ("Nimbus".equals(info.getName())) {
		    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	} catch (ClassNotFoundException ex) {
	    java.util.logging.Logger.getLogger(ServerSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(ServerSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(ServerSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(ServerSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
//		new ServerSelection().setVisible(true);
		ServerSelection l = new ServerSelection();
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton optHSQLDB;
    private javax.swing.JRadioButton optMySQL;
    // End of variables declaration//GEN-END:variables

    public static void changeThemeOfApplication(String choiceNumber) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

	System.out.println("choiceNumber-->>" + choiceNumber);

	if (choiceNumber.equalsIgnoreCase("1")) {
	    Properties props = new Properties();
	    props.put("logoString", "");
	    TextureLookAndFeel.setCurrentTheme(props);
	    javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
	} else if (choiceNumber.equalsIgnoreCase("2")) {
	    Properties props = new Properties();
	    props.put("logoString", "");
	    AeroLookAndFeel.setCurrentTheme(props);
	    javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
	} else if (choiceNumber.equalsIgnoreCase("3")) {
	    Properties props = new Properties();
	    props.put("logoString", "");
	    AluminiumLookAndFeel.setCurrentTheme(props);
	    javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
	} else if (choiceNumber.equalsIgnoreCase("4")) {
	    Properties props = new Properties();
	    props.put("logoString", "");
	    McWinLookAndFeel.setCurrentTheme(props);
	    javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
	} else if (choiceNumber.equalsIgnoreCase("5")) {
	    Properties props = new Properties();
	    props.put("logoString", "");
	    LunaLookAndFeel.setCurrentTheme(props);
	    javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
	}

    }

    private static String loadingThemeSettings() {

	//String fileName = "C:\\Users\\pc5\\Desktop\\Sudeep IMP FILES\\SystemProperties\\System.properties";
	final String dir = System.getProperty("user.dir");
	String fileName = dir + "\\others\\System.properties";
	String line = null;
	String oldText = "";
	String stringValueOfTheme = "";

	try {
	    FileReader fileReader = new FileReader(fileName);

	    BufferedReader bufferedReader = new BufferedReader(fileReader);

	    while ((line = bufferedReader.readLine()) != null) {
		System.out.println(line);
		stringValueOfTheme = line.substring(8, 9);
		System.out.println("SubString-->>" + stringValueOfTheme);
		oldText += line + "\r\n";
	    }
	    bufferedReader.close();

	} catch (FileNotFoundException ex) {
	    System.out.println(
		    "Unable to open file '" + fileName + "'");
	} catch (IOException ex) {
	    System.out.println("Error reading file '" + fileName + "'");
	}

	return stringValueOfTheme;
    }
}
