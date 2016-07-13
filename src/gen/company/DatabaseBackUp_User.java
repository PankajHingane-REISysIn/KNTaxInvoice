package gen.company;

import gen.mainclass.FeaturesDTO;
import gen.mainclass.FindregeditEntry;
import gen.mainclass.UserSettingDTO;
import gen.other.CompanySettings.CompanySettingsDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class DatabaseBackUp_User extends JInternalFrame {

    static String operatingSystemDrive = System.getenv("SystemDrive");
    private JTextField txtDestinationPath_Company_Back_UP;
    private JTable tableCompanyList_Company_Back_UP;
    private JTable tableCompanyBackUp_Company_Back_UP;
    private static String installedServerPath = "";
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
    private List<gen.other.CompanySettings.CompanySettingsDTO> companySettingsDTOList_Company_Settings = new ArrayList<gen.other.CompanySettings.CompanySettingsDTO>();
    /**
     * Launch the application.
     */
    private JPanel panel;
    private JLabel lblDestination;
    private JButton btnPath_Company_Back_UP;
    private JButton btnBackUP_Company_Back_UP;
    private JLabel lblExcprogramFilesadsumudi;
    private JPanel panel_1;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane_1;
    private JPanel panel_2;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DatabaseBackUp_User frame = new DatabaseBackUp_User();
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
    public DatabaseBackUp_User() {
        try {
            setClosable(true);
            setTitle("Back Up Your Database Make It Secure");
            setFrameIcon(new ImageIcon(DatabaseBackUp_User.class.getResource("/images/Kasturi-logo-1.png")));
            //setBounds(100, 100, 1332, 674);

            panel = new JPanel();
            getContentPane().add(panel, BorderLayout.CENTER);
            panel.setLayout(new MigLayout(
                    "",
                    "[0px:145px:145px,grow,shrink 50,fill][0px:500px:500px,grow,shrink 50,fill][][0px:500px:500px,grow,shrink 50,fill][0px:145px:145px,grow,shrink 50,fill]",
                    "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

            lblDestination = new JLabel("Destination");
            panel.add(lblDestination, "cell 1 1,alignx trailing");

            txtDestinationPath_Company_Back_UP = new JTextField();
            panel.add(txtDestinationPath_Company_Back_UP, "flowx,cell 1 2,growx");
            txtDestinationPath_Company_Back_UP.setColumns(10);

            btnPath_Company_Back_UP = new JButton("Path");
            btnPath_Company_Back_UP.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        btnPath_Company_Back_UPActionPerformed(e);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(DatabaseBackUp_User.this, ex.getMessage());
                    }
                }
            });
            panel.add(btnPath_Company_Back_UP, "cell 1 2");

            lblExcprogramFilesadsumudi = new JLabel(
                    "Ex:" + operatingSystemDrive + "\\Program Files\\AdSuMuDi 9.0\\");
            panel.add(lblExcprogramFilesadsumudi, "cell 1 2");

            panel_1 = new JPanel();
            panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
            panel.add(panel_1, "cell 1 4 1 15,grow");
            panel_1.setLayout(new MigLayout("", "[grow]", "[grow]"));

            scrollPane = new JScrollPane();
            panel_1.add(scrollPane, "cell 0 0,grow");

            tableCompanyList_Company_Back_UP = new JTable();
            tableCompanyList_Company_Back_UP.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
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
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(DatabaseBackUp_User.this, ex.getMessage());
                    }
                }
            });
            scrollPane.setViewportView(tableCompanyList_Company_Back_UP);

            panel_2 = new JPanel();
            panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
            panel.add(panel_2, "cell 3 4 1 15,grow");
            panel_2.setLayout(new MigLayout("", "[grow]", "[grow]"));

            scrollPane_1 = new JScrollPane();
            panel_2.add(scrollPane_1, "cell 0 0,grow");

            tableCompanyBackUp_Company_Back_UP = new JTable();
            tableCompanyBackUp_Company_Back_UP.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
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
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(DatabaseBackUp_User.this, ex.getMessage());
                    }
                }
            });
            scrollPane_1.setViewportView(tableCompanyBackUp_Company_Back_UP);

            btnBackUP_Company_Back_UP = new JButton("BackUp");
            btnBackUP_Company_Back_UP.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        btnBackUP_Company_Back_UPActionPerformed(e);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(DatabaseBackUp_User.this, ex.getMessage());
                    }
                }
            });
            panel.add(btnBackUP_Company_Back_UP, "cell 2 19");

            initialise_Company_Back_UP();
            getInstalledServerPath();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(DatabaseBackUp_User.this, ex.getMessage());
        }

    }

    private void btnBackUP_Company_Back_UPActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:

        // check Validation
        if (validation_Company_Back_UP()) {
            try {
                Boolean flag = true;
                // for finding database of everyrow of back up table one by one
                for (int i = 0; i < tableCompanyBackUp_Company_Back_UP
                        .getRowCount(); i++) {
                    System.out.println("row count");
                    String database = "";
                    for (CompanySettingsDTO companySettingsDTO : company_Information_DTOList_Company_Back_UP) {
                        System.out.println("row ");
                        // set Database Name of selected_Company for
                        // Database_BackUP
                        if (companySettingsDTO.getCompany_name().equals(
                                tableCompanyBackUp_Company_Back_UP
                                .getValueAt(i, 0).toString().trim())
                                && companySettingsDTO
                                .getCompany_id()
                                .toString()
                                .equals(tableCompanyBackUp_Company_Back_UP
                                .getValueAt(i, 1).toString()
                                .trim())) {
                            database = companySettingsDTO
                                    .getCompany_DatabaseName();
                            System.out.println("3453453454354");
                            // take a backUP and check Bolean value so that
                            // remaining functinality should not work
                            backupDB_Company_Back_UP(database, "root", "adm",
                                    fileChooser_Company_Back_UP + "/"
                                    + database + ".sql");
                        }
                    }
                }

                // check Boolean value so that remaining functinality should not
                // work
                if (flag) {
                    // / create file with fileName of
                    // database.enc,database,company_name
                    createcompanyfile_Company_Back_UP(fileChooser_Company_Back_UP
                            + "/");

                    // Encrypt the file
                    encryptFile_Company_Back_UP();

                    // Zip the Folder
                    Zipfile_Company_Back_UP(fileChooser_Company_Back_UP + "/");
                }

                initialise_Company_Back_UP();
            } catch (Exception ex) {
                ex.printStackTrace();
                throw ex;
            }
        }
    }

    private void btnPath_Company_Back_UPActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(DatabaseBackUp_User.this,
                "Space is not Allow in folder Name where You take a Back UP ");
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("choosertitle");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(DatabaseBackUp_User.this) == JFileChooser.APPROVE_OPTION) {
            fileChooser_Company_Back_UP = chooser.getSelectedFile();
            // /set selected file text ot txtDestinationPath
            txtDestinationPath_Company_Back_UP
                    .setText(fileChooser_Company_Back_UP.toString());
        } else {
            System.out.println("No Selection ");
        }
    }

    private void initialise_Company_Back_UP() throws Exception {
        intilisecomponent_Company_Back_UP();
        bindData2GUI_Company_Back_UP();
        setnmenomics_Company_Back_UP();
    }

    private void setnmenomics_Company_Back_UP() throws Exception {
        btnBackUP_Company_Back_UP.setMnemonic(KeyEvent.VK_B);
        btnPath_Company_Back_UP.setMnemonic(KeyEvent.VK_P);
    }

    private void intilisecomponent_Company_Back_UP() throws Exception {

        System.out
                .println("intilisecomponent_Company_Back_UP ----------------------          ------- ");

        String col[] = {"CompanyName", ""};
        String data[][] = {{"", ""}};
        tablemodelCompany_Company_Back_UP = new DefaultTableModel(data, col) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;// This causes all cells to be not editable
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
                return false;// This causes all cells to be not editable
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
        company_Information_DTOList_Company_Back_UP = gen.other.CompanySettings.CompanySettingsDAO
                .getComapany_List("", "");
        // backUP_Company_DTOList.clear();
    }

    // bind to GUI
    private void bindData2GUI_Company_Back_UP() throws Exception {
        // call when Form open
        // gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings
        // = "";
        // get Company_List
        UserSettingDTO usersettingdto = new UserSettingDTO();
        usersettingdto.setUser_id(gen.dto.Constants.CURRENT_USER_ID);

        // get role for user as if user is Admin then then role is 1 so visible
        // all company to him
        FeaturesDTO featuresDTO = new FeaturesDTO();
        featuresDTO = gen.mainclass.UserSettingDAO
                .get_Active_Inactive_By_User(gen.dto.Constants.CURRENT_USER_ID);

        String user_Role = featuresDTO.getRole_Name();

        if (!user_Role.equalsIgnoreCase("admin")) {
            companySettingsDTOList_Company_Settings = gen.mainclass.UserSettingDAO
                    .getCurrent_Company_Details_By_User(usersettingdto);
        } else {
            companySettingsDTOList_Company_Settings = gen.other.CompanySettings.CompanySettingsDAO
                    .getComapany_List("", "");
        }

        tablemodelCompany_Company_Back_UP.setRowCount(0);
        for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList_Company_Settings) {
            tablemodelCompany_Company_Back_UP
                    .setRowCount(tablemodelCompany_Company_Back_UP
                    .getRowCount() + 1);
            // set into the table
            tablemodelCompany_Company_Back_UP.setValueAt(
                    companySettingsDTO.getCompany_name(),
                    tablemodelCompany_Company_Back_UP.getRowCount() - 1, 0);
            tablemodelCompany_Company_Back_UP.setValueAt(
                    companySettingsDTO.getCompany_id(),
                    tablemodelCompany_Company_Back_UP.getRowCount() - 1, 1);
        }
        setColumnWidth_Company_Settings(tableCompanyList_Company_Back_UP);
    }

    // take a back up
    public static boolean backupDB_Company_Back_UP(String dbName, String dbUserName, String dbPassword, String path) throws Exception {
        Boolean flag = false;
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
                flag = true;
            } else {
                System.out.println("Could not create the backup");
            }
            runtimeProcess.destroy();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return flag;
    }

    private void createcompanyfile_Company_Back_UP(String filepath) throws Exception {
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
                        // add database name in text file
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
                throw ex;
            }
        }
    }

    private void encryptFile_Company_Back_UP() throws Exception {
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

            // Encrypt the .SQL files
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
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void Zipfile_Company_Back_UP(String filepath) throws Exception {
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
                    Logger.getLogger(DatabaseBackUp_User.class.getName()).log(
                            Level.SEVERE, null, ex);
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
            throw e;
        }
    }

    private static void encrypt_Company_Back_UP(InputStream is, OutputStream os) throws Exception {
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
            throw e;
        }
    }

    // find all files of .enc
    List<String> textFilesforEnc_Company_Back_UP(File dir) throws Exception {
        List<String> textFiles = new ArrayList<String>();
        if (dir.listFiles() != null) {
            for (File file : dir.listFiles()) {
                if (file.getName().endsWith((".enc"))) {
                    textFiles.add(file.getName());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Enter valid path");
        }

        return textFiles;
    }

    // find all files of .SQL
    List<String> textFilesforSQL_Company_Back_UP(File dir) throws Exception {
        List<String> textFiles = new ArrayList<String>();
        if (dir.listFiles() != null) {
            for (File file : dir.listFiles()) {
                if (file.getName().endsWith((".sql"))) {
                    textFiles.add(file.getName());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Enter valid path");
        }

        return textFiles;
    }

    private Boolean validation_Company_Back_UP() throws Exception {
        Boolean flag = true;
        if (tableCompanyBackUp_Company_Back_UP.getRowCount() == 0
                || tableCompanyBackUp_Company_Back_UP.getRowCount() < 0) {
            JOptionPane.showMessageDialog(null,
                    "First Select Company To Bck UP");
            flag = false;
            btnPath_Company_Back_UP.requestFocus();
        } else if (txtDestinationPath_Company_Back_UP.getText().toString()
                .trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "First Select Destination Folder for  Back UP");
            flag = false;
            btnPath_Company_Back_UP.requestFocus();
        }

        return flag;
    }

    // set width to column of table
    private void setColumnWidth_Company_Back_UP(JTable passedTable) throws Exception {
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
        passedTable.getColumnModel().getColumn(1).setMinWidth(50);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(50);

    }

    // public static void main(String... arg) {
    // DatabaseBackUp_User a = new DatabaseBackUp_User("");
    // a.setSize(600, 600);
    // a.setVisible(true);
    // }
    private void setColumnWidth_Company_Settings(JTable passedTable) throws Exception {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.LEFT);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        passedTable.getColumnModel().getColumn(0)
                .setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(320);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(320);

        passedTable.getColumnModel().getColumn(1)
                .setCellRenderer(centerRenderer);
        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(1).setMinWidth(80);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(80);
    }

    private String getInstalledServerPath() throws Exception {
        FindregeditEntry o = new FindregeditEntry();
        // String readRegistryEntry = o.read();
        installedServerPath = o.read();
        return installedServerPath;
    }
}
