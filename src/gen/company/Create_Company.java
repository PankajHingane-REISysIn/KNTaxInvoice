package gen.company;

import java.awt.EventQueue;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import gen.database.connection.DatabaseConnection1;
import gen.dto.Label;
import gen.mainclass.FindregeditEntry;
import gen.other.CompanySettings.CompanySettingsDAO;
import gen.other.CompanySettings.CompanySettingsDTO;
import gen.other.DatabaseBackupRestore.Company_InformationDTO;
import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Create_Company extends JInternalFrame {

    private JTable tablecompanyList_Company_Delete;
    private JTextField txtNameOfGod1;
    private JTextField txtCompanyName1;
    private JTextField txtCompanyAlias1;
    private JTextField txtCompanyTagLine1;
    private JTextField txtCompanyAddress1;
    private JTextField txtCompanyContact1;
    private JTextField txtCompanyMail1;
    private JTextField txtCompanyITN1;
    private JTextField txtCompanySTN1;
    private JTextField txtCompanyCSTNo1;
    private JTextField txtCompanyDeclaration1;
    private JTextField txtCompanyTermsConditions1;
    private JTextField txtCompanySignature1;
    private static String installedServerPath = "";
    String database_Name_For_Newly_Company = "";
    String company_Cliked_Name = "";
    String company_Cliked_Database_Name = "";
    DefaultTableModel tableModelCompanyList_Company_Delete = new DefaultTableModel();
    private List<gen.other.CompanySettings.CompanySettingsDTO> companySettingsDTOList_Company_Delete = new ArrayList<gen.other.CompanySettings.CompanySettingsDTO>();
    gen.other.CompanySettings.CompanySettingsDTO companySettingsDTO = new gen.other.CompanySettings.CompanySettingsDTO();
    gen.mainclass.SetWaitTimer settimer = new gen.mainclass.SetWaitTimer();
    final String dir = System.getProperty("user.dir");
    JDialog dialog = new JDialog();
    JOptionPane optionPane = new JOptionPane("",
            JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null,
            new Object[]{}, null);
    private String source = "";
    byte[] b1;
    /**
     * Launch the application.
     */
    private JButton btnCompanyCreate1;
    private JButton btnUpdate1;
    private JButton btncompanyClear1;
    private JLabel lblcompany_ID1;
    private JLabel LogoPanelView1;
    private JPanel applicationSettingsPanel2;
    private JPanel panel_1;
    private JLabel lblNameOfCompany;
    private JLabel lblAlias;
    private JLabel lblTagline;
    private JLabel lblAddress;
    private JLabel lblContactNumber;
    private JLabel lblEmailId;
    private JLabel lblIncomeTaxNumber;
    private JLabel lblVatTinNumber;
    private JLabel lblCstNumber;
    private JLabel lblDeclarations;
    private JLabel lblTermsconditions;
    private JLabel lblSigningAuthority;
    private JPanel panel;
    private JScrollPane scrollPane;
    private JButton btnClear;
    private JButton jButton3;
    private JButton btnDelete1;
    private JButton buttonExit_Company_Delete;
    private JButton buttonApply_Company_Delete;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Create_Company frame = new Create_Company();
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
    public Create_Company() {
        try {
            setClosable(true);
            setTitle("Create/Alter Company Information");
            //setBounds(100, 100, 1332, 674);
            setFrameIcon(new ImageIcon(Create_Company.class.getResource("/images/Kasturi-logo-1.png")));
            applicationSettingsPanel2 = new JPanel();
            applicationSettingsPanel2.setBorder(new LineBorder(new Color(0, 0, 0)));
            getContentPane().add(applicationSettingsPanel2, BorderLayout.CENTER);
            applicationSettingsPanel2
                    .setLayout(new MigLayout(
                    "",
                    "[0px:115px:115px,grow,shrink 50,fill][0px:400px:400px,grow,shrink 50,fill][0px:150px:150px,grow,shrink 50,fill][0px:500px:500px,grow,shrink 50,fill][0px:115px:115px,grow,shrink 50,fill]",
                    "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

            panel_1 = new JPanel();
            panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
            applicationSettingsPanel2.add(panel_1, "cell 1 1 1 18,grow");
            panel_1.setLayout(new MigLayout(
                    "",
                    "[0px:50px:50px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:200px:200px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill]",
                    "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

            txtNameOfGod1 = new JTextField();
            txtNameOfGod1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        txtCompanyName1.requestFocus();
                    }
                }
            });
            panel_1.add(txtNameOfGod1, "cell 1 0 2 1,growx");
            txtNameOfGod1.setColumns(10);

            lblcompany_ID1 = new JLabel("ID");
            lblcompany_ID1.setHorizontalAlignment(SwingConstants.RIGHT);
            panel_1.add(lblcompany_ID1, "cell 2 1");

            lblNameOfCompany = new JLabel("Name Of Company");
            panel_1.add(lblNameOfCompany, "cell 0 2 2 1");

            txtCompanyName1 = new JTextField();
            txtCompanyName1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        try {
                            // validation for Specical character and only number
                            if (validation_FOR_CreateCompany_Naming(txtCompanyName1.getText().toString().trim())) {
                                txtCompanyAlias1.requestFocus();
                            } else {
                                txtCompanyName1.requestFocus();
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(Create_Company.this, ex.getMessage());
                        }

                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        txtNameOfGod1.requestFocus();
                    }
                }

                @Override
                public void keyTyped(KeyEvent evt) {
                    filterCharacter(evt, txtCompanyName1);
                }
            });
            panel_1.add(txtCompanyName1, "cell 2 2,growx");
            txtCompanyName1.setColumns(10);

            lblAlias = new JLabel("Alias");
            panel_1.add(lblAlias, "cell 0 3 2 1");

            txtCompanyAlias1 = new JTextField();
            txtCompanyAlias1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        txtCompanyTagLine1.requestFocus();
                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        txtCompanyName1.requestFocus();
                    }
                }
            });
            panel_1.add(txtCompanyAlias1, "cell 2 3,growx");
            txtCompanyAlias1.setColumns(10);

            lblTagline = new JLabel("Tagline");
            panel_1.add(lblTagline, "cell 0 4 2 1");

            txtCompanyTagLine1 = new JTextField();
            txtCompanyTagLine1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        txtCompanyAddress1.requestFocus();
                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        txtCompanyAlias1.requestFocus();
                    }
                }
            });
            panel_1.add(txtCompanyTagLine1, "cell 2 4,growx");
            txtCompanyTagLine1.setColumns(10);

            lblAddress = new JLabel("Address");
            panel_1.add(lblAddress, "cell 0 5 2 1");

            txtCompanyAddress1 = new JTextField();
            txtCompanyAddress1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        txtCompanyContact1.requestFocus();
                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        txtCompanyTagLine1.requestFocus();
                    }
                }
            });
            panel_1.add(txtCompanyAddress1, "cell 2 5,growx");
            txtCompanyAddress1.setColumns(10);

            lblContactNumber = new JLabel("Contact Number");
            panel_1.add(lblContactNumber, "cell 0 6 2 1");

            txtCompanyContact1 = new JTextField();
            txtCompanyContact1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        txtCompanyMail1.requestFocus();
                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        txtCompanyAddress1.requestFocus();
                    }
                }

                @Override
                public void keyTyped(KeyEvent evt) {
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
                }
            });
            panel_1.add(txtCompanyContact1, "cell 2 6,growx");
            txtCompanyContact1.setColumns(10);

            lblEmailId = new JLabel("E-Mail ID");
            panel_1.add(lblEmailId, "cell 0 7 2 1");

            txtCompanyMail1 = new JTextField();
            txtCompanyMail1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        txtCompanyITN1.requestFocus();
                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        txtCompanyContact1.requestFocus();
                    }
                }
            });
            panel_1.add(txtCompanyMail1, "cell 2 7,growx");
            txtCompanyMail1.setColumns(10);

            lblIncomeTaxNumber = new JLabel("Income Tax Number");
            panel_1.add(lblIncomeTaxNumber, "cell 0 8 2 1");

            txtCompanyITN1 = new JTextField();
            txtCompanyITN1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        txtCompanySTN1.requestFocus();
                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        txtCompanyMail1.requestFocus();
                    }
                }
            });
            panel_1.add(txtCompanyITN1, "cell 2 8,growx");
            txtCompanyITN1.setColumns(10);

            lblVatTinNumber = new JLabel("VAT TIN Number");
            panel_1.add(lblVatTinNumber, "cell 0 9 2 1");

            txtCompanySTN1 = new JTextField();
            txtCompanySTN1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        txtCompanyCSTNo1.requestFocus();
                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        txtCompanyITN1.requestFocus();
                    }
                }
            });
            panel_1.add(txtCompanySTN1, "cell 2 9,growx");
            txtCompanySTN1.setColumns(10);

            lblCstNumber = new JLabel("CST Number");
            panel_1.add(lblCstNumber, "cell 0 10 2 1");

            txtCompanyCSTNo1 = new JTextField();
            txtCompanyCSTNo1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        txtCompanyDeclaration1.requestFocus();
                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        txtCompanySTN1.requestFocus();
                    }
                }
            });
            panel_1.add(txtCompanyCSTNo1, "cell 2 10,growx");
            txtCompanyCSTNo1.setColumns(10);

            lblDeclarations = new JLabel("Declarations");
            panel_1.add(lblDeclarations, "cell 0 11 2 1");

            txtCompanyDeclaration1 = new JTextField();
            txtCompanyDeclaration1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        txtCompanyTermsConditions1.requestFocus();
                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        txtCompanyCSTNo1.requestFocus();
                    }
                }
            });
            panel_1.add(txtCompanyDeclaration1, "cell 2 11,growx");
            txtCompanyDeclaration1.setColumns(10);

            lblTermsconditions = new JLabel("Terms/Conditions");
            panel_1.add(lblTermsconditions, "cell 0 12 2 1");

            txtCompanyTermsConditions1 = new JTextField();
            txtCompanyTermsConditions1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        txtCompanySignature1.requestFocus();
                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        txtCompanyDeclaration1.requestFocus();
                    }
                }
            });
            panel_1.add(txtCompanyTermsConditions1, "cell 2 12,growx");
            txtCompanyTermsConditions1.setColumns(10);

            lblSigningAuthority = new JLabel("Signing Authority");
            panel_1.add(lblSigningAuthority, "cell 0 13 2 1");

            txtCompanySignature1 = new JTextField();
            txtCompanySignature1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (btnCompanyCreate1.isEnabled()) {
                            btnCompanyCreate1.requestFocus();
                        } else {
                            btnUpdate1.requestFocus();
                        }
                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        txtCompanyTermsConditions1.requestFocus();
                    }
                }
            });
            panel_1.add(txtCompanySignature1, "cell 2 13,growx");
            txtCompanySignature1.setColumns(10);

            panel = new JPanel();
            panel.setBorder(new LineBorder(new Color(0, 0, 0)));
            applicationSettingsPanel2.add(panel, "cell 3 1 1 18,grow");
            panel.setLayout(new MigLayout("", "[grow]", "[grow]"));

            scrollPane = new JScrollPane();
            panel.add(scrollPane, "cell 0 0,grow");

            tablecompanyList_Company_Delete = new JTable();
            tablecompanyList_Company_Delete.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        // setFocus(event);
                        //tablecompanyList_Company_DeleteMouseClicked(null);
                        for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList_Company_Delete) {
                            if (companySettingsDTO.getCompany_name().equals(
                                    tableModelCompanyList_Company_Delete.getValueAt(
                                    tablecompanyList_Company_Delete
                                    .getSelectedRow(), 0).toString())) {
                                try {
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
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(Create_Company.this, ex.getMessage());
                                }
                            }
                        }
                    }
                }
            });
            tablecompanyList_Company_Delete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    for (CompanySettingsDTO companySettingsDTO : companySettingsDTOList_Company_Delete) {
                        if (companySettingsDTO.getCompany_name().equals(
                                tableModelCompanyList_Company_Delete.getValueAt(
                                tablecompanyList_Company_Delete
                                .getSelectedRow(), 0).toString())) {
                            try {
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
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(Create_Company.this, ex.getMessage());
                            }
                        }
                    }
                }
            });
            scrollPane.setViewportView(tablecompanyList_Company_Delete);

            LogoPanelView1 = new JLabel("");
            LogoPanelView1.setBorder(new LineBorder(new Color(0, 0, 0)));
            applicationSettingsPanel2.add(LogoPanelView1, "cell 2 1 1 5");

            jButton3 = new JButton("Update");
            jButton3.setMnemonic('D');
            jButton3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        jButton3ActionPerformed(e);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(Create_Company.this, ex.getMessage());
                    }
                }
            });
            applicationSettingsPanel2.add(jButton3, "cell 2 6");

            btnClear = new JButton("Clear");
            btnClear.setMnemonic('R');
            btnClear.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        btnClearActionPerformed(e);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(Create_Company.this, ex.getMessage());
                    }
                }
            });
            applicationSettingsPanel2.add(btnClear, "cell 2 7");

            btncompanyClear1 = new JButton("New");
            btncompanyClear1.setMnemonic('N');
            btncompanyClear1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        try {
                            btncompanyClear1ActionPerformed(null);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(Create_Company.this, ex.getMessage());
                        }
                    } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                        if (btnCompanyCreate1.isEnabled()) {
                            btnCompanyCreate1.requestFocus();
                        } else {
                            btnUpdate1.requestFocus();
                        }
                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        txtCompanySignature1.requestFocus();
                    }
                }
            });
            btncompanyClear1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        btncompanyClear1ActionPerformed(e);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(Create_Company.this, ex.getMessage());
                    }
                }
            });
            applicationSettingsPanel2.add(btncompanyClear1, "flowx,cell 1 19");

            btnCompanyCreate1 = new JButton("Submit");
            btnCompanyCreate1.setMnemonic('S');
            btnCompanyCreate1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        try {
                            btnCompanyCreate1ActionPerformed(null);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(Create_Company.this, ex.getMessage());
                        }
                    } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                        btncompanyClear1.requestFocus();
                    } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                        if (btnUpdate1.isEnabled()) {
                            btnUpdate1.requestFocus();
                        } else {
                            btnDelete1.requestFocus();
                        }
                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        txtCompanySignature1.requestFocus();
                    }
                }
            });
            btnCompanyCreate1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        btnCompanyCreate1ActionPerformed(e);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(Create_Company.this, ex.getMessage());
                    }
                }
            });
            applicationSettingsPanel2.add(btnCompanyCreate1, "cell 1 19");

            btnUpdate1 = new JButton("Update");
            btnUpdate1.setMnemonic('P');
            btnUpdate1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        btnUpdate1ActionPerformed(e);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(Create_Company.this, ex.getMessage());
                    }
                }
            });
            applicationSettingsPanel2.add(btnUpdate1, "cell 1 19");

            btnUpdate1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        try {
                            btnUpdate1ActionPerformed(null);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(Create_Company.this, ex.getMessage());
                        }
                    } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                        if (btnCompanyCreate1.isEnabled()) {
                            btnCompanyCreate1.requestFocus();
                        } else {
                            btncompanyClear1.requestFocus();
                        }
                    } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                        btnDelete1.requestFocus();
                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        txtCompanySignature1.requestFocus();
                    }
                }
            });

            btnDelete1 = new JButton("Delete");
            btnDelete1.setMnemonic('T');
            btnDelete1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        btnDelete1ActionPerformed(e);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(Create_Company.this, ex.getMessage());
                    }
                }
            });
            applicationSettingsPanel2.add(btnDelete1, "cell 1 19");

            btnDelete1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        try {
                            btnDelete1ActionPerformed(null);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(Create_Company.this, ex.getMessage());
                        }
                    } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                        if (btnUpdate1.isEnabled()) {
                            btnUpdate1.requestFocus();
                        } else {
                            btnCompanyCreate1.requestFocus();
                        }
                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        txtCompanySignature1.requestFocus();
                    }
                }
            });

            buttonApply_Company_Delete = new JButton("Apply");
            buttonApply_Company_Delete.setMnemonic('A');
            applicationSettingsPanel2.add(buttonApply_Company_Delete,
                    "flowx,cell 3 19");

            buttonExit_Company_Delete = new JButton("Exit");
            buttonExit_Company_Delete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        buttonExit_Company_DeleteActionPerformed(e);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(Create_Company.this, ex.getMessage());
                    }
                }
            });
            applicationSettingsPanel2.add(buttonExit_Company_Delete, "cell 3 19");

            intialise_Company_Delete();
            getInstalledServerPath();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Create_Company.this, ex.getMessage());
        }

    }

    private void buttonExit_Company_DeleteActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:
        // this.dispose();
        Create_Company.this.setClosed(true);
    }

    private void btncompanyClear1ActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
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
        txtCompanyTermsConditions1.setText("");
        txtCompanySignature1.setText("");
        lblcompany_ID1.setText("ID");

        btnCompanyCreate1.setEnabled(true);
        btnUpdate1.setEnabled(false);
	btnDelete1.setEnabled(false);

        LogoPanelView1.setIcon(null);
        LogoPanelView1.revalidate();

        txtCompanyName1.requestFocus();
        source = "";
    }

    private void btnCompanyCreate1ActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
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
            JOptionPane.showMessageDialog(null, "Enter Name Of Company");
            txtCompanyName1.requestFocus();
        } else if (!m.find()) {
            JOptionPane.showMessageDialog(null, "Enter Valid Name Of Company");
            txtCompanyName1.requestFocus();
        } else if (flag) {
            JOptionPane.showMessageDialog(null,"Enter Only Alphabet and Number in  Name Of Company");
            txtCompanyName1.requestFocus();
        } else if (!lblcompany_ID1.getText().toString().trim().equals("ID")) {
            JOptionPane.showMessageDialog(null, "Enter Name Of Company");
            txtCompanyName1.requestFocus();
        } else if (!matcher.matches()&& !txtCompanyMail1.getText().toString().trim().equals("")) {
            JOptionPane.showMessageDialog(null, Label.ENTER_VALUE_FOR_EMAIL_ID);
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

                // get the first Word from the company_name to add in database
                // with id
                String[] result = txtCompanyName1.getText().toString().trim()
                        .split(" ", 2);
                String first_Word = "";
                String rest_Words = "";
                // use st that ArrayIndex of bound problem shuld not occcur if
                // only 1 word is present
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
                String add_ID_To_NewCompany = gen.other.CompanySettings.CompanySettingsDAO
                        .get_Max_Company_ID();

                // pass database Name for Newly creted company
                database_Name_For_Newly_Company = first_Word
                        + add_ID_To_NewCompany;

                List<Company_InformationDTO> new_databaseCreateList = new ArrayList<Company_InformationDTO>();
                Company_InformationDTO company_InformationDTO = new Company_InformationDTO();
                // add New company Database Name in company_InformationDTO
                company_InformationDTO
                        .setCompany_Database_Name(database_Name_For_Newly_Company);
                new_databaseCreateList.add(company_InformationDTO);
                // Create New Database for Newly created Company
                if (gen.dto.Constants.DATABASE_SERVER
                        .equals("com.mysql.jdbc.Driver")) {
                    gen.other.DatabaseSpiliting.DatabaseSplitingDAO
                            .createNewDatabase(new_databaseCreateList);
                }
                List<CompanySettingsDTO> companySettings_InformationDTOList = new ArrayList<CompanySettingsDTO>();

                CompanySettingsDTO companySettingsDTO = new CompanySettingsDTO();
                // companySettingsDTO.setCompany_id(Long.parseLong(lblcompany_ID1.getText().toString().trim()));
                companySettingsDTO.setCompany_name(txtCompanyName1.getText()
                        .toString().trim());
                System.out
                        .println("Comapny _NMAE  ------------------------------ "
                        + txtCompanyName1.getText().toString().trim());

                companySettingsDTO.setNameOfGod(txtNameOfGod1.getText()
                        .toString().trim());
                companySettingsDTO
                        .setCompany_DatabaseName(database_Name_For_Newly_Company);
                companySettingsDTO.setcompany_Alias(txtCompanyAlias1.getText()
                        .toString().trim());
                companySettingsDTO.setcompany_Address(txtCompanyAddress1
                        .getText().toString().trim());
                companySettingsDTO.setcompany_ContactNo(txtCompanyContact1
                        .getText().toString().trim());
                companySettingsDTO.setcompany_Created_by("");
                companySettingsDTO.setcompany_Created_date("");
                companySettingsDTO
                        .setcompany_Declaration(txtCompanyDeclaration1
                        .getText().toString().trim());
                companySettingsDTO.setcompany_EmailId(txtCompanyMail1.getText()
                        .toString().trim());
                companySettingsDTO.setcompany_Field1("");
                companySettingsDTO.setcompany_Field2("");
                companySettingsDTO.setcompany_IncomeTaxNo(txtCompanyITN1
                        .getText().toString().trim());
                companySettingsDTO.setcompany_LBTNo("");
                companySettingsDTO.setcompany_Modified_by("");
                companySettingsDTO.setcompany_Modified_date("");
                companySettingsDTO.setcompany_Note("");
                companySettingsDTO.setcompany_SaleTaxNo(txtCompanyCSTNo1
                        .getText().toString().trim());
                companySettingsDTO
                        .setcompany_SignAuthority(txtCompanySignature1
                        .getText().toString().trim());
                companySettingsDTO.setcompany_VatTinNo(txtCompanySTN1.getText()
                        .toString().trim());
                companySettingsDTO
                        .setcompany_TermCnditions(txtCompanyTermsConditions1
                        .getText().toString().trim());
                companySettingsDTO.setcompany_TagLine(txtCompanyTagLine1
                        .getText().toString().trim());
                companySettings_InformationDTOList.add(companySettingsDTO);

                // pass empty company_List so that it is use in calling function
                List<String> company_List = new ArrayList<String>();
                gen.other.DatabaseSpiliting.DatabaseSplitingDAO
                        .insert_NewlyCreated_Company(company_List,
                        database_Name_For_Newly_Company, "", "",
                        companySettings_InformationDTOList);
                // ////////////////////////////////////////

                BufferedImage mImage;
                final String dir = System.getProperty("user.dir");
                File imgfile = new File("");
                FileInputStream fin = null;
                // source = "";
                if (source != null && !source.isEmpty()) {
                    imgfile = new File(source);
                    fin = new FileInputStream(imgfile);
                    mImage = ImageIO.read(imgfile);

                    PreparedStatement pre = conn
                            .prepareStatement("insert into tblcompaniesimage(company_name,company_id,company_image) values(?,?,?)");
                    pre.setString(1, txtCompanyName1.getText().toString()
                            .trim());
                    pre.setString(2, add_ID_To_NewCompany);
                    if (source != null && !source.isEmpty()) {
                        pre.setBinaryStream(3, fin, (int) imgfile.length());
                    } else {
                        pre.setString(3, "NULL");
                    }
                    pre.executeUpdate();
                }

                // backupDB_Database_Delete("aj1", "root", "adm", "D:/exe.sql");
                // Boolean flag = restoreDB_Database_Delete("w74", "root",
                // "adm", "D:/exe.sql");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JInternalFrame newsas = new JInternalFrame();
                            startEmptyWaitTimerForFrame(newsas,
                                    database_Name_For_Newly_Company);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(Create_Company.this, ex.getMessage());
                        }
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
                throw e;
            }

        }
    }

    private void btnUpdate1ActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
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
            JOptionPane.showMessageDialog(Create_Company.this,
                    "Enter Name Of Company");
            txtCompanyName1.requestFocus();
        } else if (!m.find()) {
            JOptionPane.showMessageDialog(Create_Company.this,
                    "Enter Valid Name Of Company");
            txtCompanyName1.requestFocus();
        } else if (flag) {
            JOptionPane.showMessageDialog(Create_Company.this,
                    "Enter Only Alphabet and Number in  Name Of Company");
            txtCompanyName1.requestFocus();
        } else if (lblcompany_ID1.getText().toString().trim().equals("ID")) {
            JOptionPane.showMessageDialog(Create_Company.this,
                    "Selete company to update");
            txtCompanyName1.requestFocus();
        } else if (!matcher.matches()
                && !txtCompanyMail1.getText().toString().trim().equals("")) {
            JOptionPane.showMessageDialog(Create_Company.this,
                    Label.ENTER_VALUE_FOR_EMAIL_ID);
            txtCompanyMail1.requestFocus();
        } else {
            txtCompanyName1.transferFocus();
            int current = 0;
            Connection conn = null;
            try {
                // create new company
                // get the first Word from the company_name to add in database
                // with id
                String[] result = txtCompanyName1.getText().toString().trim()
                        .split(" ", 2);
                String first_Word = "";
                String rest_Words = "";
                // use st that ArrayIndex of bound problem shuld not occcur if
                // only 1 word is present
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
                companySettingsDTO.setCompany_id(Long.parseLong(lblcompany_ID1
                        .getText().toString().trim()));
                companySettingsDTO.setCompany_name(txtCompanyName1.getText()
                        .toString().trim());
                System.out
                        .println("Comapny _NMAE  ------------------------------ "
                        + txtCompanyName1.getText().toString().trim());
                companySettingsDTO.setcompany_Alias(txtCompanyAlias1.getText()
                        .toString().trim());
                companySettingsDTO.setcompany_Address(txtCompanyAddress1
                        .getText().toString().trim());
                companySettingsDTO.setcompany_ContactNo(txtCompanyContact1
                        .getText().toString().trim());
                companySettingsDTO.setcompany_Created_by("");
                companySettingsDTO.setcompany_Created_date("");
                companySettingsDTO
                        .setcompany_Declaration(txtCompanyDeclaration1
                        .getText().toString().trim());
                companySettingsDTO.setcompany_EmailId(txtCompanyMail1.getText()
                        .toString().trim());
                companySettingsDTO.setcompany_Field1("");
                companySettingsDTO.setcompany_Field2("");
                companySettingsDTO.setcompany_IncomeTaxNo(txtCompanyITN1
                        .getText().toString().trim());
                companySettingsDTO.setcompany_LBTNo("");
                companySettingsDTO.setcompany_Modified_by("");
                companySettingsDTO.setcompany_Modified_date("");
                companySettingsDTO.setcompany_Note("");
                companySettingsDTO.setcompany_SaleTaxNo(txtCompanyCSTNo1
                        .getText().toString().trim());
                companySettingsDTO
                        .setcompany_SignAuthority(txtCompanySignature1
                        .getText().toString().trim());
                companySettingsDTO.setcompany_VatTinNo(txtCompanySTN1.getText()
                        .toString().trim());
                companySettingsDTO
                        .setcompany_TermCnditions(txtCompanyTermsConditions1
                        .getText().toString().trim());
                companySettingsDTO.setcompany_TagLine(txtCompanyTagLine1
                        .getText().toString().trim());
                companySettings_InformationDTOList.add(companySettingsDTO);

                gen.other.DatabaseSpiliting.DatabaseSplitingDAO
                        .update_Company_Information(
                        companySettings_InformationDTOList,
                        company_Cliked_Name,false);

                // if (control == 1) {
                gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";

                BufferedImage mImage;
                final String dir = System.getProperty("user.dir");
                File imgfile = null;
                FileInputStream fin = null;
                if (source != null && !source.isEmpty()) {
                    imgfile = new File(source);
                    fin = new FileInputStream(imgfile);

                    // File imgfile = new File(source);

                    // FileInputStream fin = new FileInputStream(imgfile);
                    conn = DatabaseConnection1.GetConnection();
                    conn.setAutoCommit(false);
                    PreparedStatement prmt = conn
                            .prepareStatement("delete from tblcompaniesimage where company_id = ? and company_name = ?");
                    prmt.setString(1, lblcompany_ID1.getText().toString()
                            .trim());
                    prmt.setString(2, company_Cliked_Name);
                    prmt.executeUpdate();

                    PreparedStatement pre = conn
                            .prepareStatement("insert into tblcompaniesimage(company_name,company_id,company_image) values(?,?,?)");
                    pre.setString(1, txtCompanyName1.getText().toString()
                            .trim());
                    pre.setString(2, lblcompany_ID1.getText().toString().trim());
                    if (source != null && !source.isEmpty()) {
                        pre.setBinaryStream(3, fin, (int) imgfile.length());
                    } else {
                        pre.setString(3, "NULL");
                    }
                    pre.executeUpdate();

                    // String query =
                    // "update tblcompaniesimage set  company_name = ?, company_image = ? where company_id = ? and company_name = ?";
                    // PreparedStatement pre = conn.prepareStatement(query);
                    //
                    // pre.setString(1,
                    // txtCompanyName1.getText().toString().trim());
                    // System.out.println("Compnay clivked -----------" +
                    // company_Cliked_Name);
                    // System.out.println("Compnay txtCompanyName1 -----------"
                    // + txtCompanyName1.getText().toString().trim());
                    // if (source != null) {
                    // // pre.setBinaryStream(2, fin, (int) imgfile.length());
                    // pre.setBinaryStream(2, fin, (long) imgfile.length());
                    // } else {
                    // pre.setString(2, "");
                    // }
                    // pre.setString(3,
                    // lblcompany_ID1.getText().toString().trim());
                    // pre.setString(4, company_Cliked_Name);
                    // pre.executeUpdate();
                    // }
                    pre.close();
                    conn.commit();
                    conn.close();
                }
                gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";

                insert_Imagein_Company_Database(company_Cliked_Database_Name);

                JOptionPane.showMessageDialog(Create_Company.this,
                        "Company Information Updated Successfully...");

                btncompanyClear1ActionPerformed(evt);
                initilizeGUIComponents_Company_Delete();

                btnCompanyCreate1.setEnabled(true);
                btnUpdate1.setEnabled(false);
        		btnDelete1.setEnabled(false);

                btncompanyClear1ActionPerformed(evt);
                initilizeGUIComponents_Company_Delete();

                System.out
                        .println("4444444444444444444444444444444444444444444444444444444444444444444");
                // Remove icon when button is clicked.
                LogoPanelView1.setIcon(null);
                // **IMPORTANT** to call revalidate() to cause JLabel to resize
                // and be repaint
                LogoPanelView1.revalidate();
                ;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
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

    private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:

        if (!lblcompany_ID1.getText().toString().trim().equals("ID")) {
//            JOptionPane optionPane = new JOptionPane(
//                    "Do you want to Delete  Company" + " ?",
//                    JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
//            JDialog dialog = optionPane.createDialog("Delete");
//            Set<AWTKeyStroke> focusTraversalKeys = new HashSet<AWTKeyStroke>(
//                    dialog.getFocusTraversalKeys(0));
//            focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(
//                    KeyEvent.VK_RIGHT, KeyEvent.VK_UNDEFINED));
//            focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(
//                    KeyEvent.VK_LEFT, KeyEvent.VK_UNDEFINED));
//            dialog.setFocusTraversalKeys(0, focusTraversalKeys);
//
//            dialog.setVisible(true);
//            dialog.dispose();
//
//            System.out.println(" v optionPane.getValue()         "
//                    + optionPane.getValue());
//
//            // if click on yes button
//            if ("0".equals(optionPane.getValue().toString())) {
            Connection conn = null;
            try {
                gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
                conn = DatabaseConnection1.GetConnection();
                conn.setAutoCommit(false);
                PreparedStatement pre = conn
                        .prepareStatement("delete from  tblcompaniesimage where company_name = ? and company_id = ?");
                pre.setString(1, company_Cliked_Name);
                pre.setString(2, lblcompany_ID1.getText().toString().trim());
                pre.executeUpdate();

                PreparedStatement prete = conn
                        .prepareStatement("delete from   tblcurrentcompany where company_name = ? and company_id = ? ");
                prete.setString(1, company_Cliked_Name);
                prete.setString(2, lblcompany_ID1.getText().toString()
                        .trim());
                prete.executeUpdate();

                prete = conn
                        .prepareStatement("delete from   tblusertocompany where company_name = ? and company_id = ? ");
                prete.setString(1, company_Cliked_Name);
                prete.setString(2, lblcompany_ID1.getText().toString()
                        .trim());
                prete.executeUpdate();

                PreparedStatement pret = conn
                        .prepareStatement("delete from   tblcompanyinfo where company_name = ? and company_id = ? ");
                pret.setString(1, company_Cliked_Name);
                pret.setString(2, lblcompany_ID1.getText().toString()
                        .trim());
                pret.executeUpdate();

                System.out
                        .println("!!!!!!!!3444444444444444444444444444444444444444444444444");

                // JOptionPane.showMessageDialog(this,
                // "Company Deleted Successfully...");

                conn.commit();
                conn.close();

                gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
                btncompanyClear1ActionPerformed(evt);
                initilizeGUIComponents_Company_Delete();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(Create_Company.this, ex.getMessage());
            }
//            }

        }
//        else {
//            // JOptionPane.showMessageDialog(null, "Selete company to Delete");
//            txtCompanyName1.requestFocus();
//        }
        btnCompanyCreate1.setEnabled(true);
        btnUpdate1.setEnabled(false);
    	btnDelete1.setEnabled(false);

    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:

        int dialogButton1 = JOptionPane.YES_NO_OPTION;
        int dialogResult1 = JOptionPane.showConfirmDialog(Create_Company.this,
                "Your Current Logo Will Be Removed,Do You Want To Proceed ?",
                "Warning", dialogButton1);
        if (dialogResult1 == 0) {
            imageloading o = new imageloading();
            o.setVisible(true);
        }
    }

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:
        // clear Image
        LogoPanelView1.setIcon(null);
        LogoPanelView1.revalidate();

        if (!lblcompany_ID1.getText().toString().trim().equals("ID")) {
            try {
                gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
                Connection conn = DatabaseConnection1.GetConnection();
                PreparedStatement pre = conn
                        .prepareStatement("delete from  tblcompaniesimage where company_name = ? and company_id = ?");
                pre.setString(1, company_Cliked_Name);
                pre.setString(2, lblcompany_ID1.getText().toString().trim());
                pre.executeUpdate();
                gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
            } catch (Exception ex) {
                ex.printStackTrace();
                throw ex;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selete company to Delete");
            txtCompanyName1.requestFocus();
        }
    }

    private void intialise_Company_Delete() throws Exception {
        initilizeGUIComponents_Company_Delete();
        setnemonic_Company_Delete();
    }

    private void initilizeGUIComponents_Company_Delete() throws Exception {
        initComponentActiveInActive_Company_Delete();
        bindDTOTOGUI_Company_Delete();
    }

    private void initComponentActiveInActive_Company_Delete() throws Exception {
        String col[] = {"CompanyName", ""};
        String data[][] = {{"", ""}};
        tableModelCompanyList_Company_Delete = new DefaultTableModel(data, col) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;// This causes all cells to be not editable
            }
        };
        tablecompanyList_Company_Delete
                .setModel(tableModelCompanyList_Company_Delete);
        JTableHeader header = tablecompanyList_Company_Delete.getTableHeader();
        header.setBackground(Color.yellow);
        tableModelCompanyList_Company_Delete.setRowCount(0);
        tableModelCompanyList_Company_Delete.setColumnCount(2);
        buttonApply_Company_Delete.setVisible(false);
        btnCompanyCreate1.setEnabled(true);
        btnUpdate1.setEnabled(false);
    	btnDelete1.setEnabled(false);

    }

    private void setnemonic_Company_Delete() throws Exception {
        buttonApply_Company_Delete.setMnemonic(KeyEvent.VK_A);
        buttonExit_Company_Delete.setMnemonic(KeyEvent.VK_E);
        txtCompanyName1.requestFocus();
    }

    private void bindDTOTOGUI_Company_Delete() throws Exception {
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
        setColumnWidth_Company_Delete(tablecompanyList_Company_Delete);
    }

    private void setColumnWidth_Company_Delete(JTable passedTable) throws Exception {
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

    // ////////// Delete Company /////////////////////////////////////////////
    private void alter_Company(String company_Name, String company_ID) throws Exception {
        Connection con = null;
        try {
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
            con = DatabaseConnection1.GetConnection();
            con.setAutoCommit(false);
            // Remove icon when button is clicked.
            LogoPanelView1.setIcon(null);
            // **IMPORTANT** to call revalidate() to cause JLabel to resize and
            // be repaint
            LogoPanelView1.revalidate();
            ;

            Statement stat = con.createStatement();
            ResultSet rs = stat
                    .executeQuery("SELECT company_image FROM tblcompaniesimage where company_name = '"
                    + company_Name
                    + "' and company_id = "
                    + company_ID
                    + "");
            while (rs.next()) {

                Image img = null;
                BufferedImage mImage1 = null;
                byte[] bytes = null;
                System.out
                        .println("Set icon ---------------------------------");
                if (gen.dto.Constants.DATABASE_SERVER
                        .equals("com.mysql.jdbc.Driver")) {
                    bytes = rs.getBytes("company_image");
                    System.out
                            .println("MYSQL ----------------------------------------"
                            + bytes);
                } else {
                    // bytes =
                    // IOUtils.toByteArray((rs.getBlob("company_image")));
                    bytes = rs.getBytes("company_image");
                    System.out
                            .println("Image ----------------------------------------"
                            + bytes);
                }

                img = Toolkit.getDefaultToolkit().createImage(bytes);

                ImageIcon icon = new ImageIcon(img);
                Image img1 = icon.getImage();
                Image newimg = img.getScaledInstance(151, 150,
                        Image.SCALE_SMOOTH);
                ImageIcon newicon = new ImageIcon(newimg);
                LogoPanelView1.setIcon(newicon);
            }

            con.commit();
            con.close();
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        Connection conn = null;
        try {

            // set Company_ID to ID Label
            lblcompany_ID1.setText(company_ID);

            String q = "";
            // Connection conn;
            ResultSet resultSet = null;
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            Statement st = conn.createStatement();

            q = "select * from tblcompanyinfo where company_name ='"
                    + company_Name + "' and company_id = " + company_ID + "";
            resultSet = st.executeQuery(q);
            while (resultSet.next()) {
                String name = resultSet.getString("company_name");
                company_Cliked_Name = name;
                company_Cliked_Database_Name = resultSet
                        .getString("company_database");

                txtNameOfGod1.setText(resultSet.getString("NameOfGod"));

                txtCompanyName1.setText(name);

                // txtCompanyName.setEnabled(false);
                String alias = resultSet.getString("Alias");
                txtCompanyAlias1.setText(alias);
                // txtCompanyAlias.setEnabled(false);
                String address = resultSet.getString("Address");
                txtCompanyAddress1.setText(address);
                // txtCompanyAddress.setEnabled(false);
                String contact = "" + resultSet.getString("ContactNo");
                txtCompanyContact1.setText(contact);
                // txtCompanyContact.setEnabled(false);
                String emailid = resultSet.getString("EmailId");
                txtCompanyMail1.setText(emailid);
                // txtCompanyMail.setEnabled(false);
                String income = "" + resultSet.getString("IncomeTaxNo");
                txtCompanyITN1.setText(income);
                // txtCompanyITN.setEnabled(false);
                String sales = "" + resultSet.getString("VatTinNo");
                txtCompanySTN1.setText(sales);
                // txtCompanySTN.setEnabled(false);
                String declaration = resultSet.getString("Declaration");
                txtCompanyDeclaration1.setText(declaration);
                // txtCompanyDeclaration.setEnabled(false);
                String tagline = resultSet.getString("TagLine");
                txtCompanyTagLine1.setText(tagline);
                // txtCompanyTagLine.setEnabled(false);
                String termsConditions = resultSet.getString("TermCnditions");
                txtCompanyTermsConditions1.setText(termsConditions);
                // txtCompanyTermsConditions.setEnabled(false);
                String nameOfGod = resultSet.getString("NameOfGod");
                txtNameOfGod1.setText(nameOfGod);
                // txtNameOfGod.setEnabled(false);
                String signAuthority = resultSet.getString("SignAuthority");
                txtCompanySignature1.setText(signAuthority);
                // txtCompanySignature.setEnabled(false);
                String cstNewNumber = resultSet.getString("SaleTaxNo");
                txtCompanyCSTNo1.setText(cstNewNumber);
                // txtCompanyCSTNo.setEnabled(false);
                // String LBTNumber = resultSet.getString("LBTNo");
                // txt.setText(LBTNumber);
                // txtLBTNumber.setEnabled(false);
            }
            conn.commit();
            conn.close();
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    // /////////////////////////////////////////////////////////////////////////
    // / create tables in Newly Created Database From generic Database.
    // Take BackUp
    public static boolean backupDB_Database_Delete(String dbName, String dbUserName, String dbPassword, String path) throws Exception {
        Process runtimeProcess;

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
            throw ex;
        }
        return false;
    }

    // Restore DataBase
    public static boolean restoreDB_Database_Delete(String dbName, String dbUserName, String dbPassword, String source) throws Exception {
        Boolean flag = false;
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
            throw ex;
        }

        return flag;
    }

    private Boolean validation_FOR_CreateCompany_Naming(String company_Name_Text) throws Exception {
        Boolean flag = true;
        try {
            double d = Double.parseDouble(company_Name_Text);
            flag = false;
        } catch (Exception ex) {
            throw ex;
        }
        return flag;
    }

    // Allow only number and character in CompanyName
    public static void filterCharacter(java.awt.event.KeyEvent evt,
            javax.swing.JTextField jtxtField) {
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

    public void startEmptyWaitTimerForFrame(JInternalFrame frame, String database_Name) throws Exception {
        System.out.println("DDir ----------------------------------" + dir);
        optionPane.setIcon(new ImageIcon(new javax.swing.ImageIcon(dir
                + "/images/Thinfadingline.gif").getImage()));
        optionPane.setMessage("Wait..  Company is Creating...");
        dialog.add(optionPane);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(frame);
        // long endTime = System.currentTimeMillis() + 15000;

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
            gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = database_Name;
            create_tables();
            gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = "";
        }

        // insert image into companies database table for printing purpose.
        insert_Imagein_Company_Database(database_Name);
        // //////// complted ///////////////////////////////////////////

        // gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings
        // = "";
        frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        dialog.setVisible(false);
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));

        JOptionPane.showMessageDialog(Create_Company.this,
                "Company Successfully Created");
    }

    private void insert_Imagein_Company_Database(String database_Name) throws Exception {
        Connection conn = null;
        try {
            // insert image into companies database table for printing purpose.
            BufferedImage mImage1;
            final String dir1 = System.getProperty("user.dir");
            File imgfile1 = null;
            FileInputStream fin1 = null;
            if (source != null && !source.isEmpty()) {
                try {
                    imgfile1 = new File(source);
                    fin1 = new FileInputStream(imgfile1);

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Create_Company.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            }
            gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = database_Name;
            System.out.println("DAtabase ---------------                   "
                    + database_Name);
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = "";
            conn.commit();

            // //////// complted ///////////////////////////////////////////

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public final class imageloading extends javax.swing.JInternalFrame {

        BufferedImage mImage;
        Image scaledImage = null;
        ImageIcon scaledImageIcon = null;
        String name, name1;

        public imageloading() throws Exception {
            // JFrame frm=new JFrame("image loading test");
            source = filechoose();
            System.out
                    .println("ViewEditCompany----->>imageloading -->>source: "
                    + source);
            File inputFile = new File(source);
            try {
                mImage = ImageIO.read(inputFile);
            } catch (Exception ex) {
                throw ex;
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

            } catch (Exception e) {
                throw e;
            }
            LogoPanelView1.setIcon(scaledImageIcon);
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

            int r = chooser.showOpenDialog(Create_Company.this);

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

    private String getInstalledServerPath() throws Exception {
        FindregeditEntry o = new FindregeditEntry();
        // String readRegistryEntry = o.read();
        installedServerPath = o.read();
        return installedServerPath;
    }

    private void create_tables() throws Exception {
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            Statement s = conn.createStatement();

//            s.execute("CREATE TABLE REGISTRATION (image  BLOB)");
//            s.execute("CREATE TABLE tblaccountvouchersmaxid (salemaxid int DEFAULT NULL,purchasemaxid int DEFAULT NULL,receiptmaxid int DEFAULT NULL,paymentmaxid int DEFAULT NULL,challanmaxid int DEFAULT NULL,journalmaxid int DEFAULT NULL,contramaxid int DEFAULT NULL,field3 int DEFAULT NULL,field4 int DEFAULT NULL,field5 int DEFAULT NULL)");
//            s.execute("insert into tblaccountvouchersmaxid (salemaxid,purchasemaxid, receiptmaxid,paymentmaxid,challanmaxid,contramaxid) values(1,1,1,1,1,1)");
//
//    	    s.execute("CREATE TABLE tbladsumudisettings (name varchar(40) DEFAULT NULL,value int DEFAULT NULL)");
//            s.execute(" insert into tbladsumudisettings values('pagination','50')");
//
//            s.execute("Create table tblcompanyinfo (company_id    int  GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null  ,primary key (company_id) , company_name  varchar(500), company_database  varchar(500) , created_by     varchar(500) , modified_by    varchar(500) , created_date    date , modified_date   date , Alias   varchar(100) , Address    varchar(500) , ContactNo   varchar(100) , EmailId   varchar(200) , IncomeTaxNo   varchar(60) , SaleTaxNo    varchar(60)  , Declaration   varchar(800) , TagLine     varchar(700) , TermCnditions   varchar(1000) , NameOfGod   varchar(200) , SignAuthority    varchar(200) , VatTinNo   varchar(60) , Note    varchar(500), LBTNo   varchar(60) , Field1   varchar(60) , Field2   varchar(60) )");
//            s.execute("  CREATE TABLE tbllogin (username varchar(60) NOT NULL,password varchar(60) DEFAULT NULL,new_entry int DEFAULT NULL,acc_vouchers int DEFAULT NULL,report int DEFAULT NULL,production int DEFAULT NULL,user_type int DEFAULT NULL,email_id varchar(80) DEFAULT NULL,email_pass varchar(60) DEFAULT NULL,PRIMARY KEY (username))");
//            s.execute("  CREATE TABLE tblcurrentlogin (username varchar(80) DEFAULT NULL, FOREIGN KEY (username) REFERENCES tbllogin (username))");
//
//            s.execute("  CREATE TABLE tblgroup (group_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null, PRIMARY KEY (group_id), group_name varchar(40) DEFAULT NULL,group_under int DEFAULT NULL,  group_alias varchar(40) DEFAULT NULL,  group_isDeletable tinyint DEFAULT NULL,  created_date date DEFAULT NULL,  modified_date date DEFAULT NULL,  modified_user int DEFAULT NULL,  created_user int DEFAULT NULL ) ");
//            s.execute("INSERT INTO tblgroup VALUES (50,'Primary',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(54,'Current Liability',50,'',NULL,NULL,NULL,NULL,NULL),(55,'Current Assets',50,'',NULL,NULL,NULL,NULL,NULL),(56,'Suspense Account',50,'',NULL,NULL,NULL,NULL,NULL),(57,'Sundry Debtors',54,'',NULL,NULL,NULL,NULL,NULL),(58,'Sundry Creditors',54,'',NULL,NULL,NULL,NULL,NULL),(59,'Stock-In-Hand',55,'',NULL,NULL,NULL,NULL,NULL),(60,'Sales Account',50,'',NULL,NULL,NULL,NULL,NULL),(61,'Purchase Account',50,'',NULL,NULL,NULL,NULL,NULL),(62,'Loan Liability',50,'',NULL,NULL,NULL,NULL,NULL),(63,'Loans and Advances',55,'',NULL,NULL,NULL,NULL,NULL),(64,'Indirect Expenses',50,'',NULL,NULL,NULL,NULL,NULL),(65,'Indirect Income',50,'',NULL,NULL,NULL,NULL,NULL),(66,'Income (Direct)',50,'',NULL,NULL,NULL,NULL,NULL),(67,'Income (Indirect)',50,'',NULL,NULL,NULL,NULL,NULL),(68,'Expense (Direct)',50,'',NULL,NULL,NULL,NULL,NULL),(69,'Expense (Indirect)',50,'',NULL,NULL,NULL,NULL,NULL),(70,'Bank Account',55,'',NULL,NULL,NULL,NULL,NULL),(71,'Cash In Hand',55,'',NULL,NULL,NULL,NULL,NULL);");
//
//            s.execute(" CREATE TABLE tbltransactionmain (trans_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,trans_receiptNo int DEFAULT NULL, trans_date date DEFAULT NULL,  trans_typeIndex int DEFAULT NULL,  trans_narration varchar(1000) DEFAULT NULL,  trans_grandtotal decimal(40,2) DEFAULT NULL,  trans_transport int DEFAULT NULL,  trans_lessBillAmt decimal(40,2) DEFAULT NULL,  trans_payment varchar(40) DEFAULT NULL,  created_date date DEFAULT NULL,  modified_date date DEFAULT NULL,  created_user int DEFAULT NULL,  modified_user int DEFAULT NULL,  PRIMARY KEY (trans_id))");
//            s.execute(" CREATE TABLE tblinventorytransaction ( invtrans_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  trans_id int DEFAULT NULL,  invtrans_type int DEFAULT NULL,  dat date DEFAULT NULL,  PRIMARY KEY (invtrans_id), FOREIGN KEY (trans_id) REFERENCES tbltransactionmain (trans_id))");
//
//            s.execute(" CREATE TABLE tblstockgroup ( sg_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null, sg_name varchar(60) DEFAULT NULL,  sg_alias varchar(40) DEFAULT NULL,  sg_under int DEFAULT NULL,  sg_lbtpercent decimal(20,2) DEFAULT NULL,  PRIMARY KEY (sg_id))");
//            s.execute("insert into tblStockGroup(sg_name,sg_alias,sg_under) values('Primary','',0)");
//
//            s.execute("  CREATE TABLE tblstockitemtype(type_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,type_name varchar(40) DEFAULT NULL,PRIMARY KEY (type_id)) ");
//            s.execute("  CREATE TABLE tbluomtype ( uomType_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  uomType_name varchar(40) DEFAULT NULL,  PRIMARY KEY (uomType_id))");
//            s.execute("INSERT INTO tbluomtype VALUES (1,'Simple'),(2,'Complex')");
//            s.execute("  CREATE TABLE tblunitofmeasure(  uomType_id int NOT NULL,  uom_type varchar(50) DEFAULT NULL,  uom_symbol varchar(50) DEFAULT NULL,  uom_formalName varchar(50) DEFAULT NULL,  uom_noOfDecimalPts int DEFAULT NULL,  uom_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  PRIMARY KEY (uom_id),  FOREIGN KEY (uomType_id) REFERENCES tbluomtype (uomType_id))");
//            s.execute("  CREATE TABLE tblstockitem(si_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  si_alias varchar(60) DEFAULT NULL,  si_under int DEFAULT NULL,  si_length decimal(40,2) DEFAULT NULL,  si_width decimal(40,2) DEFAULT NULL,  si_thickness decimal(40,2) DEFAULT NULL,  si_rate decimal(40,2) DEFAULT NULL,  si_unitOfMeasure int DEFAULT NULL,  si_openingBalance decimal(40,2) DEFAULT NULL,  si_unit varchar(40) DEFAULT NULL,  si_type int DEFAULT NULL,  si_name varchar(100) DEFAULT NULL,  PRIMARY KEY (si_id),  FOREIGN KEY (si_under) REFERENCES tblstockgroup (sg_id), FOREIGN KEY (si_type) REFERENCES tblstockitemtype (type_id),  FOREIGN KEY (si_unitOfMeasure) REFERENCES tblunitofmeasure (uom_id))");
//            s.execute("  CREATE TABLE tblfinishtype (f_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,PRIMARY KEY (f_id),f_name varchar(60) DEFAULT NULL)");
//            s.execute("  CREATE TABLE tblinventorytransactionitems (  invtrans_id int NOT NULL,  invtrans_qty decimal(40,2) DEFAULT NULL,  invtrans_rate decimal(40,2) DEFAULT NULL,  invtrans_amount decimal(40,2) DEFAULT NULL,  invtrans_totalSize decimal(40,2) DEFAULT NULL,  invtrans_index int DEFAULT NULL,  invtrans_itemId int DEFAULT NULL,  invtrans_finishType int DEFAULT NULL,  invtrans_length decimal(40,2) DEFAULT NULL,  invtrans_width decimal(40,2) DEFAULT NULL,  invtrans_thickness decimal(40,2) DEFAULT NULL,  invtrans_making decimal(40,2) DEFAULT NULL,    FOREIGN KEY (invtrans_id) REFERENCES tblinventorytransaction (invtrans_id),  FOREIGN KEY (invtrans_itemId) REFERENCES tblstockitem (si_id),   FOREIGN KEY (invtrans_finishType) REFERENCES tblfinishtype (f_id))");
//            s.execute("  CREATE TABLE tblledger(ledger_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  ledger_name varchar(60) DEFAULT NULL,  ledger_under int DEFAULT NULL,  ledger_address varchar(200) DEFAULT NULL,  ledger_contactno varchar(40) DEFAULT NULL,  ledger_emailId varchar(100) DEFAULT NULL,  ledger_openingBalance decimal(40,2) DEFAULT NULL,  ledger_alias varchar(40) DEFAULT NULL,  opening_type int DEFAULT NULL,  dat date DEFAULT NULL,  ledger_inTaxo varchar(60) DEFAULT NULL,  ledger_saleTaxNo varchar(60) DEFAULT NULL,  created_date date DEFAULT NULL,  modified_date date DEFAULT NULL,  created_user int DEFAULT NULL,  modified_user int DEFAULT NULL, PRIMARY KEY (ledger_id),  FOREIGN KEY (ledger_under) REFERENCES tblgroup (group_id))");
//            s.execute("  CREATE TABLE tblledgercreditlimit (  ledger_id int DEFAULT NULL,  ledger_limit decimal(40,2) DEFAULT NULL,   FOREIGN KEY (ledger_id) REFERENCES tblledger (ledger_id))	    ");
//            s.execute(" CREATE TABLE tblledgercurrentbalance(  ledger_id int DEFAULT NULL,  ledger_currentBalance decimal(40,2) DEFAULT NULL,  ledger_DebitOrCredit int DEFAULT NULL,   FOREIGN KEY (ledger_id) REFERENCES tblledger (ledger_id)) ");
//            s.execute(" CREATE TABLE tblstockitemcurrentbalance(  si_id int DEFAULT NULL,  si_currentBalance decimal(40,2) DEFAULT NULL,  si_DebitOrCredit int DEFAULT NULL,  FOREIGN KEY (si_id) REFERENCES tblstockitem (si_id))");
//            s.execute(" CREATE TABLE tblstockitemrate (  custid int DEFAULT NULL,  stkid int DEFAULT NULL,  rate decimal(50,2) DEFAULT NULL,   FOREIGN KEY (custid ) REFERENCES tblledger (ledger_id),   FOREIGN KEY (stkid) REFERENCES tblstockitem (si_id))");
//            s.execute("  CREATE TABLE tbltempopenclosebalance(  opening decimal(40,2) DEFAULT NULL,  closing decimal(40,2) DEFAULT NULL,  openType int DEFAULT NULL,  closeType int DEFAULT NULL)");
//            s.execute("  CREATE TABLE tbltransactionledger(  trans_id int NOT NULL,  trans_ledgerId int DEFAULT NULL,  trans_type int DEFAULT NULL,  trans_index int DEFAULT NULL,  trans_narration varchar(1000) DEFAULT NULL,  trans_amt decimal(40,2) DEFAULT NULL,  trans_checkNo varchar(80) DEFAULT NULL,  cashinhand_flag int DEFAULT NULL,   FOREIGN KEY (trans_ledgerId) REFERENCES tblledger (ledger_id),  FOREIGN KEY (trans_id) REFERENCES tbltransactionmain (trans_id))");
//            s.execute("  CREATE TABLE tbltransactionotherdetails (  trans_id int NOT NULL,  trans_reference varchar(100) DEFAULT NULL,  trans_buyerOrderNo varchar(50) DEFAULT NULL,  trans_dispatchDocThrough varchar(50) DEFAULT NULL,  trans_dispatchDocNo varchar(50) DEFAULT NULL,  trans_amt decimal(40,2) DEFAULT NULL,  trans_payment varchar(40) DEFAULT NULL,  trans_vehicleNo varchar(50) DEFAULT NULL,  trans_challanNo varchar(40) DEFAULT NULL,  trans_poNo varchar(40) DEFAULT NULL,  trans_challanDate date DEFAULT NULL,  trans_poDate date DEFAULT NULL,   FOREIGN KEY (trans_id) REFERENCES tbltransactionmain (trans_id))");
//            s.execute(" CREATE TABLE tbltransactiontype ( transType_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  transType_name varchar(80) DEFAULT NULL,  PRIMARY KEY (transType_id))");
//            s.execute(" CREATE TABLE tbltransactionvat (  trans_id int DEFAULT NULL,  vat_rate decimal(40,2) DEFAULT NULL,  vat_amt decimal(40,2) DEFAULT NULL,  discount_rate decimal(40,2) DEFAULT NULL,  discount_amt decimal(40,2) DEFAULT NULL,  shipping int DEFAULT NULL,  lbt_amt decimal(40,2) DEFAULT NULL,  FOREIGN KEY (trans_id) REFERENCES tbltransactionmain (trans_id))");

	    // commented on 28-01-2014
	    //	    s.execute("CREATE TABLE REGISTRATION (image  BLOB)");
//	    s.execute("create table tblbackup (email_backup_date date,drive_backup_date date,email_backup_time time,drive_backup_time time)");
//	    s.execute("create table tblbackupsetting (email_backup int,drive_backup int,backup_on_exit int, username varchar(100) , drive_backup_path varchar(800))");
//            s.execute("CREATE TABLE tblaccountvouchersmaxid (salemaxid int DEFAULT NULL,purchasemaxid int DEFAULT NULL,receiptmaxid int DEFAULT NULL,paymentmaxid int DEFAULT NULL,challanmaxid int DEFAULT NULL,journalmaxid int DEFAULT NULL,contramaxid int DEFAULT NULL,field3 int DEFAULT NULL,field4 int DEFAULT NULL,field5 int DEFAULT NULL)");
//	    s.execute("insert into tblaccountvouchersmaxid (salemaxid,purchasemaxid, receiptmaxid,paymentmaxid,challanmaxid,contramaxid) values(0,0,0,0,0,0)");
//
//	    s.execute("CREATE TABLE tbladsumudisettings (name varchar(40) DEFAULT NULL,value tinyint DEFAULT NULL)");
//            s.execute(" insert into tbladsumudisettings values('pagination','50')");
//
//            s.execute("Create table tblcompanyinfo (company_id    int  GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null  ,primary key (company_id) , company_name  varchar(500), company_database  varchar(500) , created_by     varchar(500) , modified_by    varchar(500) , created_date    date , modified_date   date , Alias   varchar(100) , Address    varchar(500) , ContactNo   varchar(100) , EmailId   varchar(200) , IncomeTaxNo   varchar(60) , SaleTaxNo    varchar(60)  , Declaration   varchar(800) , TagLine     varchar(700) , TermCnditions   varchar(1000) , NameOfGod   varchar(200) , SignAuthority    varchar(200) , VatTinNo   varchar(60) , Note    varchar(500), LBTNo   varchar(60) , Field1   varchar(60) , Field2   varchar(60) )");
//            s.execute("  CREATE TABLE tbllogin (username varchar(60) NOT NULL,password varchar(60) DEFAULT NULL,new_entry int DEFAULT NULL,acc_vouchers int DEFAULT NULL,report int DEFAULT NULL,production int DEFAULT NULL,user_type int DEFAULT NULL,email_id varchar(80) DEFAULT NULL,email_pass varchar(60) DEFAULT NULL,PRIMARY KEY (username))");
//            s.execute("  CREATE TABLE tblcurrentlogin (username varchar(80) DEFAULT NULL, FOREIGN KEY (username) REFERENCES tbllogin (username))");
//
//            s.execute("  CREATE TABLE tblgroup (group_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null, PRIMARY KEY (group_id), group_name varchar(40) DEFAULT NULL,group_under int DEFAULT NULL,  group_alias varchar(40) DEFAULT NULL,  group_isDeletable tinyint DEFAULT NULL,  created_date date DEFAULT NULL,  modified_date date DEFAULT NULL,  modified_user int DEFAULT NULL,  created_user int DEFAULT NULL ) ");
//            s.execute("INSERT INTO tblgroup VALUES (50,'Primary',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(54,'Current Liability',50,'',NULL,NULL,NULL,NULL,NULL),(55,'Current Assets',50,'',NULL,NULL,NULL,NULL,NULL),(56,'Suspense Account',50,'',NULL,NULL,NULL,NULL,NULL),(57,'Sundry Debtors',54,'',NULL,NULL,NULL,NULL,NULL),(58,'Sundry Creditors',54,'',NULL,NULL,NULL,NULL,NULL),(59,'Stock-In-Hand',55,'',NULL,NULL,NULL,NULL,NULL),(60,'Sales Account',50,'',NULL,NULL,NULL,NULL,NULL),(61,'Purchase Account',50,'',NULL,NULL,NULL,NULL,NULL),(62,'Loan Liability',50,'',NULL,NULL,NULL,NULL,NULL),(63,'Loans and Advances',55,'',NULL,NULL,NULL,NULL,NULL),(64,'Indirect Expenses',50,'',NULL,NULL,NULL,NULL,NULL),(65,'Indirect Income',50,'',NULL,NULL,NULL,NULL,NULL),(66,'Income (Direct)',50,'',NULL,NULL,NULL,NULL,NULL),(67,'Income (Indirect)',50,'',NULL,NULL,NULL,NULL,NULL),(68,'Expense (Direct)',50,'',NULL,NULL,NULL,NULL,NULL),(69,'Expense (Indirect)',50,'',NULL,NULL,NULL,NULL,NULL),(70,'Bank Account',55,'',NULL,NULL,NULL,NULL,NULL),(71,'Cash In Hand',55,'',NULL,NULL,NULL,NULL,NULL);");
//
//            s.execute(" CREATE TABLE tbltransactionmain (trans_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,trans_receiptNo int DEFAULT NULL, trans_date date DEFAULT NULL,  trans_typeIndex int DEFAULT NULL,  trans_narration varchar(1000) DEFAULT NULL,  trans_grandtotal decimal(40,2) DEFAULT NULL,  trans_transport int DEFAULT NULL,  trans_lessBillAmt decimal(40,2) DEFAULT NULL,  trans_payment varchar(40) DEFAULT NULL,  created_date date DEFAULT NULL,  modified_date date DEFAULT NULL,  created_user int DEFAULT NULL,  modified_user int DEFAULT NULL,  PRIMARY KEY (trans_id))");
//            s.execute(" CREATE TABLE tblinventorytransaction ( invtrans_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  trans_id int DEFAULT NULL,  invtrans_type int DEFAULT NULL,  dat date DEFAULT NULL,  PRIMARY KEY (invtrans_id), FOREIGN KEY (trans_id) REFERENCES tbltransactionmain (trans_id))");
//
//            s.execute(" CREATE TABLE tblstockgroup ( sg_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null, sg_name varchar(60) DEFAULT NULL,  sg_alias varchar(40) DEFAULT NULL,  sg_under int DEFAULT NULL,  sg_lbtpercent decimal(20,2) DEFAULT NULL,  PRIMARY KEY (sg_id))");
//            s.execute("insert into tblStockGroup(sg_name,sg_alias,sg_under) values('Primary','',0)");
//
//            s.execute("  CREATE TABLE tblstockitemtype(type_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,type_name varchar(40) DEFAULT NULL,PRIMARY KEY (type_id)) ");
//            s.execute("  CREATE TABLE tbluomtype ( uomType_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  uomType_name varchar(40) DEFAULT NULL,  PRIMARY KEY (uomType_id))");
//            s.execute("INSERT INTO tbluomtype VALUES (1,'Simple'),(2,'Complex')");
//            s.execute("  CREATE TABLE tblunitofmeasure(  uomType_id int NOT NULL,  uom_type varchar(50) DEFAULT NULL,  uom_symbol varchar(50) DEFAULT NULL,  uom_formalName varchar(50) DEFAULT NULL,  uom_noOfDecimalPts int DEFAULT NULL,  uom_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  PRIMARY KEY (uom_id),  FOREIGN KEY (uomType_id) REFERENCES tbluomtype (uomType_id))");
//            s.execute("  CREATE TABLE tblstockitem(si_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  si_alias varchar(60) DEFAULT NULL,  si_under int DEFAULT NULL,  si_length decimal(40,2) DEFAULT NULL,  si_width decimal(40,2) DEFAULT NULL,  si_thickness decimal(40,2) DEFAULT NULL,  si_rate decimal(40,2) DEFAULT NULL,  si_unitOfMeasure int DEFAULT NULL,  si_openingBalance decimal(40,2) DEFAULT NULL,  si_unit varchar(40) DEFAULT NULL,  si_type int DEFAULT NULL,  si_name varchar(100) DEFAULT NULL,  PRIMARY KEY (si_id),  FOREIGN KEY (si_under) REFERENCES tblstockgroup (sg_id), FOREIGN KEY (si_type) REFERENCES tblstockitemtype (type_id),  FOREIGN KEY (si_unitOfMeasure) REFERENCES tblunitofmeasure (uom_id))");
//            s.execute("  CREATE TABLE tblfinishtype (f_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,PRIMARY KEY (f_id),f_name varchar(60) DEFAULT NULL)");
//            s.execute("  CREATE TABLE tblinventorytransactionitems (  invtrans_id int NOT NULL,  invtrans_qty decimal(40,2) DEFAULT NULL,  invtrans_rate decimal(40,2) DEFAULT NULL,  invtrans_amount decimal(40,2) DEFAULT NULL,  invtrans_totalSize decimal(40,2) DEFAULT NULL,  invtrans_index int DEFAULT NULL,  invtrans_itemId int DEFAULT NULL,  invtrans_finishType int DEFAULT NULL,  invtrans_length decimal(40,2) DEFAULT NULL,  invtrans_width decimal(40,2) DEFAULT NULL,  invtrans_thickness decimal(40,2) DEFAULT NULL,  invtrans_making decimal(40,2) DEFAULT NULL,    FOREIGN KEY (invtrans_id) REFERENCES tblinventorytransaction (invtrans_id),  FOREIGN KEY (invtrans_itemId) REFERENCES tblstockitem (si_id),   FOREIGN KEY (invtrans_finishType) REFERENCES tblfinishtype (f_id))");
//            s.execute("  CREATE TABLE tblledger(ledger_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  ledger_name varchar(60) DEFAULT NULL,  ledger_under int DEFAULT NULL,  ledger_address varchar(200) DEFAULT NULL,  ledger_contactno varchar(40) DEFAULT NULL,  ledger_emailId varchar(100) DEFAULT NULL,  ledger_openingBalance decimal(40,2) DEFAULT NULL,  ledger_alias varchar(40) DEFAULT NULL,  opening_type int DEFAULT NULL,  dat date DEFAULT NULL,  ledger_inTaxo varchar(60) DEFAULT NULL,  ledger_saleTaxNo varchar(60) DEFAULT NULL,  created_date date DEFAULT NULL,  modified_date date DEFAULT NULL,  created_user int DEFAULT NULL,  modified_user int DEFAULT NULL, PRIMARY KEY (ledger_id),  FOREIGN KEY (ledger_under) REFERENCES tblgroup (group_id))");
//            s.execute("  CREATE TABLE tblledgercreditlimit (  ledger_id int DEFAULT NULL,  ledger_limit decimal(40,2) DEFAULT NULL,   FOREIGN KEY (ledger_id) REFERENCES tblledger (ledger_id))	    ");
//            s.execute(" CREATE TABLE tblledgercurrentbalance(  ledger_id int DEFAULT NULL,  ledger_currentBalance decimal(40,2) DEFAULT NULL,  ledger_DebitOrCredit int DEFAULT NULL,   FOREIGN KEY (ledger_id) REFERENCES tblledger (ledger_id)) ");
//            s.execute(" CREATE TABLE tblstockitemcurrentbalance(  si_id int DEFAULT NULL,  si_currentBalance decimal(40,2) DEFAULT NULL,  si_DebitOrCredit int DEFAULT NULL,  FOREIGN KEY (si_id) REFERENCES tblstockitem (si_id))");
//            s.execute(" CREATE TABLE tblstockitemrate (  custid int DEFAULT NULL,  stkid int DEFAULT NULL,  rate decimal(50,2) DEFAULT NULL,   FOREIGN KEY (custid ) REFERENCES tblledger (ledger_id),   FOREIGN KEY (stkid) REFERENCES tblstockitem (si_id))");
//            s.execute("  CREATE TABLE tbltempopenclosebalance(  opening decimal(40,2) DEFAULT NULL,  closing decimal(40,2) DEFAULT NULL,  openType int DEFAULT NULL,  closeType int DEFAULT NULL)");
//            s.execute("  CREATE TABLE tbltransactionledger(  trans_id int NOT NULL,  trans_ledgerId int DEFAULT NULL,  trans_type int DEFAULT NULL,  trans_index int DEFAULT NULL,  trans_narration varchar(1000) DEFAULT NULL,  trans_amt decimal(40,2) DEFAULT NULL,  trans_checkNo varchar(80) DEFAULT NULL,  cashinhand_flag int DEFAULT NULL,   FOREIGN KEY (trans_ledgerId) REFERENCES tblledger (ledger_id),  FOREIGN KEY (trans_id) REFERENCES tbltransactionmain (trans_id))");
//            s.execute("  CREATE TABLE tbltransactionotherdetails (  trans_id int NOT NULL,  trans_reference varchar(100) DEFAULT NULL,  trans_buyerOrderNo varchar(50) DEFAULT NULL,  trans_dispatchDocThrough varchar(50) DEFAULT NULL,  trans_dispatchDocNo varchar(50) DEFAULT NULL,  trans_amt decimal(40,2) DEFAULT NULL,  trans_payment varchar(40) DEFAULT NULL,  trans_vehicleNo varchar(50) DEFAULT NULL,  trans_challanNo varchar(40) DEFAULT NULL,  trans_poNo varchar(40) DEFAULT NULL,  trans_challanDate date DEFAULT NULL,  trans_poDate date DEFAULT NULL,   FOREIGN KEY (trans_id) REFERENCES tbltransactionmain (trans_id))");
//            s.execute(" CREATE TABLE tbltransactiontype ( transType_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  transType_name varchar(80) DEFAULT NULL,  PRIMARY KEY (transType_id))");
//            s.execute(" CREATE TABLE tbltransactionvat (  trans_id int DEFAULT NULL,  vat_rate decimal(40,2) DEFAULT NULL,  vat_amt decimal(40,2) DEFAULT NULL,  discount_rate decimal(40,2) DEFAULT NULL,  discount_amt decimal(40,2) DEFAULT NULL,  shipping int DEFAULT NULL,  lbt_amt decimal(40,2) DEFAULT NULL,  FOREIGN KEY (trans_id) REFERENCES tbltransactionmain (trans_id))");
//	    s.execute(" CREATE TABLE TBLTRANSCACTIONCHANGE (  sale_trans_id varchar(100),chalan_trans_id  varchar(100))");
//
//	    s.execute(" CREATE TABLE tblStockItemOPBlUpdateRecord (  si_id int ,FOREIGN KEY (si_id) REFERENCES tblstockitem (si_id),update_opening_balance_date date , si_openingBalance decimal(30,2), si_under int , FOREIGN KEY (si_under) REFERENCES tblstockgroup (sg_id) ,si_name varchar(60))");


	    // Added on 28-01-2014

	    s.execute("create table registration (image Blob)");
	    s.execute("create table tblbackup (email_backup_date date,drive_backup_date date,email_backup_time time,drive_backup_time time)");
	    s.execute("create table tblbackupsetting (email_backup int,drive_backup int,backup_on_exit int, username varchar(100) , drive_backup_path varchar(800))");
            s.execute("CREATE TABLE tblaccountvouchersmaxid (salemaxid int DEFAULT NULL,purchasemaxid int DEFAULT NULL,receiptmaxid int DEFAULT NULL,paymentmaxid int DEFAULT NULL,challanmaxid int DEFAULT NULL,journalmaxid int DEFAULT NULL,contramaxid int DEFAULT NULL,field3 int DEFAULT NULL,field4 int DEFAULT NULL,field5 int DEFAULT NULL)");
	    s.execute("insert into tblaccountvouchersmaxid (salemaxid,purchasemaxid, receiptmaxid,paymentmaxid,challanmaxid,contramaxid) values(0,0,0,0,0,0)");

	    s.execute("CREATE TABLE tbladsumudisettings (name varchar(40) DEFAULT NULL,value int DEFAULT NULL)");
            s.execute(" insert into tbladsumudisettings values('pagination','50')");

            s.execute("Create table tblcompanyinfo (company_id    int  GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null  ,primary key (company_id) , company_name  varchar(500), company_database  varchar(500) , created_by     varchar(500) , modified_by    varchar(500) , created_date    date , modified_date   date , Alias   varchar(100) , Address    varchar(500) , ContactNo   varchar(100) , EmailId   varchar(200) , IncomeTaxNo   varchar(60) , SaleTaxNo    varchar(60)  , Declaration   varchar(800) , TagLine     varchar(700) , TermCnditions   varchar(1000) , NameOfGod   varchar(200) , SignAuthority    varchar(200) , VatTinNo   varchar(60) , Note    varchar(500), LBTNo   varchar(60) , Field1   varchar(60) , Field2   varchar(60) )");
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

            s.execute("  CREATE TABLE tblunitofmeasure(  uomType_id int NOT NULL,  uom_type varchar(50) DEFAULT NULL,  uom_symbol varchar(50) DEFAULT NULL,  uom_formalName varchar(50) DEFAULT NULL,  uom_noOfDecimalPts int DEFAULT NULL,  uom_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null,  PRIMARY KEY (uom_id),  FOREIGN KEY (uomType_id) REFERENCES tbluomtype (uomType_id))");

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

//	    s.execute(" CREATE TABLE tbltranscactionchange (  sale_trans_id varchar(100),chalan_trans_id  varchar(100))");
            s.execute(" CREATE TABLE tbltranscactionchange (  sale_trans_id varchar(100),chalan_trans_id  varchar(100), voucher_type int)");

	    s.execute(" CREATE TABLE tblStockItemOPBlUpdateRecord (  si_id int ,FOREIGN KEY (si_id) REFERENCES tblstockitem (si_id),update_opening_balance_date date , si_openingBalance decimal(30,2), si_under int , FOREIGN KEY (si_under) REFERENCES tblstockgroup (sg_id) ,si_name varchar(60))");

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
