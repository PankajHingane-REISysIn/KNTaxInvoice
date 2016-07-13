/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.account.ledger;

import gen.ImpExp.ImpExpUtil;
import gen.accountvoucher.sale.SaleForm;
import gen.dto.Constants;
import gen.dto.Label;
import gen.dto.Util;
import gen.mainclass.MainClass;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author pc5
 */
public class LedgerGUIForm extends javax.swing.JInternalFrame implements java.awt.event.ActionListener {

    private LedgerDTO ledgerDTO = new LedgerDTO();
    private List<LedgerDTO> ledgerDTOList;
    private Boolean isEdit = false;
    private Boolean isTotalGroupTableLoad = false;
    Integer selectedRow = 0;
    int result = 0, result1 = 0;
    private int currentFocusValue = 0;
    private List<LedgerDTO> ledgerDTONameList;
    private Vector<String> vectorLedger = new Vector<String>();
    private Map<String, String> mapGroup_Name_ID;
    private Map<String, String> mapLedger;
    private boolean hide_flag = false;
    //time stamp
    private Long groupStamp;
    private static Integer edit_ID = 0;
    private int focus_After_Group_Created = 0;
    private static String edit_Ledger_Name = "";

    public LedgerGUIForm() {
        try {
            initComponents();
            initilize();
            setClosable(true);
        } catch (Exception ex) {
            Logger.getLogger(LedgerGUIForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(LedgerGUIForm.this, ex.getMessage());
        }
    }

    private void initComponents() {

        try {
            setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        setResizable(true);
        setTitle("Ledger Information Details");
        setClosable(true);
        setBounds(100, 100, 791, 539);

        jMainPanel = new JPanel();
        getContentPane().add(jMainPanel, BorderLayout.CENTER);

        JPanel availableLedgersPanel = new JPanel();
        availableLedgersPanel.setBorder(new TitledBorder(null, "Available Ledgers",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel ledgerInformationPanel = new JPanel();
        ledgerInformationPanel.setBorder(new TitledBorder(null, "Ledger Information",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel controlsPanel = new JPanel();
        controlsPanel.setBorder(new TitledBorder(null, "",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GroupLayout gl_jMainPanel = new GroupLayout(jMainPanel);
        gl_jMainPanel.setHorizontalGroup(gl_jMainPanel
                .createParallelGroup(Alignment.LEADING)
                .addGroup(
                gl_jMainPanel.createSequentialGroup()
                .addGroup(
                gl_jMainPanel.createParallelGroup(
                Alignment.LEADING, false)
                .addGroup(
                gl_jMainPanel.createSequentialGroup()
                .addContainerGap()
                .addComponent(
                controlsPanel,
                GroupLayout.DEFAULT_SIZE,
                GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE))
                .addGroup(
                gl_jMainPanel.createSequentialGroup()
                .addGap(14)
                .addComponent(
                ledgerInformationPanel,
                GroupLayout.PREFERRED_SIZE,
                512,
                GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(availableLedgersPanel,
                GroupLayout.DEFAULT_SIZE, 243,
                Short.MAX_VALUE)));
        gl_jMainPanel.setVerticalGroup(gl_jMainPanel
                .createParallelGroup(Alignment.TRAILING)
                .addGroup(
                gl_jMainPanel.createSequentialGroup()
                .addContainerGap()
                .addGroup(
                gl_jMainPanel.createParallelGroup(
                Alignment.BASELINE)
                .addComponent(
                ledgerInformationPanel,
                GroupLayout.DEFAULT_SIZE,
                429, Short.MAX_VALUE)
                .addComponent(
                availableLedgersPanel,
                GroupLayout.DEFAULT_SIZE,
                429, Short.MAX_VALUE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(controlsPanel,
                GroupLayout.PREFERRED_SIZE, 54,
                GroupLayout.PREFERRED_SIZE).addGap(10)));
        ledgerInformationPanel.setLayout(new MigLayout("", "[100px:100px:100px,grow,fill][25px:25px:25px,grow,shrink 50,fill][225px:225px:225px,grow,fill][grow]", "[][][0px:2px:2px,grow,shrink 50,fill][][0px:2px:2px,grow,shrink 50,fill][][0px:2px:2px,grow,shrink 50,fill][][0px:2px:2px,grow,shrink 50,fill][][0px:2px:2px,grow,shrink 50,fill][][0px:2px:2px,grow,shrink 50,fill][][0px:2px:2px,grow,shrink 50,fill][][0px:2px:2px,grow,shrink 50,fill][][0px:2px:2px,grow,shrink 50,fill][][0px:2px:2px,grow,shrink 50,fill][][0px:2px:2px,grow,shrink 50,fill][][0px:2px:2px,grow,shrink 50,fill][][][]"));

        labelName = new JLabel("Name");
        ledgerInformationPanel.add(labelName, "cell 0 1,alignx trailing");

        jTextFieldName = new JTextField();
        ledgerInformationPanel.add(jTextFieldName, "cell 2 1,growx");
        jTextFieldName.setColumns(10);

        labelAlias = new JLabel("Alias");
        ledgerInformationPanel.add(labelAlias, "cell 0 3");

        jTextFieldAlias = new JTextField();
        ledgerInformationPanel.add(jTextFieldAlias, "cell 2 3,growx");
        jTextFieldAlias.setColumns(10);

        labelUnder = new JLabel("Under");
        ledgerInformationPanel.add(labelUnder, "cell 0 5");

        jComboBoxUnder = new JComboBox();
        jComboBoxUnder.setPrototypeDisplayValue("xxxxxx");
        jComboBoxUnder.setEditable(true);
        tfUnderField = (JTextField) jComboBoxUnder.getEditor().getEditorComponent();
        ledgerInformationPanel.add(jComboBoxUnder, "cell 2 5,growx");

        jbuttonCreateGroup = new JButton("Create Group");
        jbuttonCreateGroup.setMnemonic('O');
        ledgerInformationPanel.add(jbuttonCreateGroup, "cell 3 5");

        labelOpeningBalance = new JLabel("Opening Balance ");
        ledgerInformationPanel.add(labelOpeningBalance, "cell 0 7");

        jTextFieldOpeningBalance = new JTextField();
        ledgerInformationPanel.add(jTextFieldOpeningBalance, "cell 2 7,growx");
        jTextFieldOpeningBalance.setTransferHandler(null);
        jTextFieldOpeningBalance.setColumns(10);

        radioButtonDebit = new JRadioButton("Debit");
        radioButtonDebit.setToolTipText("Enter Space Tab for selection");
        ledgerInformationPanel.add(radioButtonDebit, "flowx,cell 3 7");

        labelAddress = new JLabel("Address");
        ledgerInformationPanel.add(labelAddress, "cell 0 9");

        jTextFieldAddress = new JTextField();
        ledgerInformationPanel.add(jTextFieldAddress, "cell 2 9,growx");
        jTextFieldAddress.setColumns(10);

        labelContactNo = new JLabel("Contact Number");
        ledgerInformationPanel.add(labelContactNo, "cell 0 11");

        jTextFieldContactNo = new JTextField();
        ledgerInformationPanel.add(jTextFieldContactNo, "cell 2 11,growx");
        jTextFieldContactNo.setTransferHandler(null);
        jTextFieldContactNo.setColumns(10);

        labelEmailID = new JLabel("E-Mail ID");
        ledgerInformationPanel.add(labelEmailID, "cell 0 13");

        jTextFieldEmailID = new JTextField();
        ledgerInformationPanel.add(jTextFieldEmailID, "cell 2 13,growx");
        jTextFieldEmailID.setTransferHandler(null);
        jTextFieldEmailID.setColumns(10);

        labelCreditLimit = new JLabel("Credit Limit");
        ledgerInformationPanel.add(labelCreditLimit, "cell 0 15");

        jTextFieldCreditLimit = new JTextField();
        ledgerInformationPanel.add(jTextFieldCreditLimit, "cell 2 15,growx");
        jTextFieldCreditLimit.setTransferHandler(null);
        jTextFieldCreditLimit.setColumns(10);

        labelIncomeTaxNo = new JLabel("Income Tax Number");
        ledgerInformationPanel.add(labelIncomeTaxNo, "cell 0 17");

        jTextFieldIncomeTaxNo = new JTextField();
        ledgerInformationPanel.add(jTextFieldIncomeTaxNo, "cell 2 17,growx");
        jTextFieldIncomeTaxNo.setColumns(10);

        labelSaleTaxNo = new JLabel("Sales Tax Number");
        ledgerInformationPanel.add(labelSaleTaxNo, "cell 0 19");

        radioButtonCredit = new JRadioButton("Credit");
        ledgerInformationPanel.add(radioButtonCredit, "cell 3 7");
        radioButtonCredit.setToolTipText("Enter Space Tab for selection");

        jTextFieldSaleTaxNo = new JTextField();
        ledgerInformationPanel.add(jTextFieldSaleTaxNo, "cell 2 19,growx");
        jTextFieldSaleTaxNo.setColumns(10);

        JLabel lblEcsNumber = new JLabel("E.C.S. Number");
        ledgerInformationPanel.add(lblEcsNumber, "cell 0 21");

        jTextFieldECSNumber = new JTextField();
        ledgerInformationPanel.add(jTextFieldECSNumber, "cell 2 21,growx");
        jTextFieldECSNumber.setColumns(10);

        JLabel lblNewLabel = new JLabel("C.VAT Number");
        ledgerInformationPanel.add(lblNewLabel, "cell 0 23");

        jTextFieldCVATNumber = new JTextField();
        ledgerInformationPanel.add(jTextFieldCVATNumber, "cell 2 23,growx");
        jTextFieldCVATNumber.setColumns(10);

        JLabel lblCcstNumber = new JLabel("C.CST Number");
        ledgerInformationPanel.add(lblCcstNumber, "cell 0 25");

        jTextFieldCSTNumber = new JTextField();
        ledgerInformationPanel.add(jTextFieldCSTNumber, "cell 2 25,growx");
        jTextFieldCSTNumber.setColumns(10);

        controlsPanel.setLayout(new MigLayout("wrap",
                "[grow,left][grow,center][grow,right][grow,fill,center]",
                "[]unrel[][]"));

        jButtonBack = new JButton("Back");
        jButtonBack.setMnemonic('B');
        controlsPanel.add(jButtonBack, new CC().spanX(5).split(5).tag("other").growX());

        jButtonNew = new JButton("New");
        jButtonNew.setMnemonic('N');
        controlsPanel.add(jButtonNew, new CC().tag("other").growX());

        jButtonDelete = new JButton("Delete");
        jButtonDelete.setMnemonic('T');
        controlsPanel.add(jButtonDelete, new CC().tag("other").growX());

        jButtonSubmit = new JButton("Submit");
        jButtonSubmit.setMnemonic('S');
        controlsPanel.add(jButtonSubmit, new CC().tag("other").growX());
        availableLedgersPanel.setLayout(new MigLayout("", "[][][grow]", "[][][grow]"));

        labelSearch = new JLabel("Search");
        availableLedgersPanel.add(labelSearch, "cell 1 0,alignx trailing");

        jTextFieldSearch = new JTextField();
        availableLedgersPanel.add(jTextFieldSearch, "cell 0 1 3 1,growx");
        jTextFieldSearch.setColumns(10);

        JScrollPane pane1 = new JScrollPane();
        availableLedgersPanel.add(pane1, "cell 0 2 3 1,grow");

        jTableLedgerList = new JTable();
        jTableLedgerList.setBorder(new LineBorder(new Color(0, 0, 0)));
        jTableLedgerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        String col1[] = {Label.LEDGER_NAME};
        String data1[][] = {{"", ""}};

        jTableLedgerListModel = new DefaultTableModel(data1, col1) {
            public Class getColumnClass(int row) {
                Class returnValue;
                if ((row >= 0) && (row < getRowCount())) {
                    returnValue = getValueAt(0, row).getClass();
                } else {
                    returnValue = Object.class;
                }
                return returnValue;
            }
        };

        jTableLedgerList = new JTable() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;
        };
        
        jTableLedgerList.setModel(jTableLedgerListModel);
        JTableHeader header1 = jTableLedgerList.getTableHeader();
        jTableLedgerList.getTableHeader().setReorderingAllowed(false);
        header1.setBackground(Color.yellow);
        header1.setFont(font);
        jTableLedgerListModel = (DefaultTableModel) jTableLedgerList.getModel();
        jTableLedgerListModel.setRowCount(0);
        jTableLedgerListModel.setColumnCount(2);
        jTableLedgerList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jTableLedgerListModel);
        jTableLedgerList.setRowSorter(sorter);
        jTableLedgerList.getColumnModel().getColumn(1).setPreferredWidth(0);
        jTableLedgerList.getColumnModel().getColumn(1).setMinWidth(0);
        jTableLedgerList.getColumnModel().getColumn(1).setMaxWidth(0);
        pane1.setViewportView(jTableLedgerList);
        jMainPanel.setLayout(gl_jMainPanel);

        initialiseActionListeners();
    }

    private void initialiseActionListeners() {
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(Util.getImageIconPath())));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            @Override
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {

                try {
                    System.out.println("Frame Activated call.........--------->>>>>>>>>>>");
                    formInternalFrameActivated(evt);
                } catch (Exception ex) {
                    Logger.getLogger(LedgerGUIForm.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(LedgerGUIForm.this, ex.getMessage());
                }

            }

            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                try {
                    formInternalFrameClosing(evt);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameIconified(InternalFrameEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameDeiconified(InternalFrameEvent e) {
                //  throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        jTextFieldName.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNameFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldNameFocusLost(evt);
            }
        });
        jTextFieldName.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldNameKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        jTextFieldAlias.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldAliasFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldAliasFocusLost(evt);
            }
        });
        jTextFieldAlias.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAliasKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        tfUnderField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfUnderFieldFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfUnderFieldFocusLost(evt);
                jComboBoxUnder.hidePopup();
            }
        });


//	tfUnderField.addKeyListener(new java.awt.event.KeyAdapter() {
//	    @Override
//	    public void keyPressed(java.awt.event.KeyEvent evt) {
//		tfUnderFieldKeyPressed(evt);
//	    }
//
//	    @Override
//	    public void keyTyped(java.awt.event.KeyEvent evt) {
//		EventQueue.invokeLater(new Runnable() {
//		    @Override
//		    public void run() {
//			String text = tfUnderField.getText();
//			if (text.length() == 0) {
//			    jComboBoxUnder.hidePopup();
//			    setGroupLedgerModel(new DefaultComboBoxModel(LedgerVector), "");
//			} else {
//			    DefaultComboBoxModel m = Util.getSuggestedModel(LedgerVector, text);
//			    if (m.getSize() == 0 || hide_flag) {
//				jComboBoxUnder.hidePopup();
//				hide_flag = false;
//			    } else {
//				setGroupLedgerModel(m, text);
//				jComboBoxUnder.showPopup();
//			    }
//			}
//		    }
//		});
//	    }
//
//	    @Override
//	    public void keyReleased(java.awt.event.KeyEvent evt) {
//	    }
//	});


        jComboBoxUnder.setEditable(true);
        tfUnderField = (JTextField) jComboBoxUnder.getEditor().getEditorComponent();
        tfUnderField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String text = tfUnderField.getText();
                        if (text.length() == 0) {
                            jComboBoxUnder.hidePopup();
                            setGroupLedgerModel(new DefaultComboBoxModel(vectorLedger), "");
                        } else {
                            DefaultComboBoxModel m = Util.getSuggestedModel(vectorLedger, text);
                            if (m.getSize() == 0 || hide_flag) {
                                jComboBoxUnder.hidePopup();
                                hide_flag = false;
                            } else {
                                //setAccountLedgetModel(m, text);
                                setGroupLedgerModel(m, text);
                                jComboBoxUnder.showPopup();
                            }
                        }
                    }
                });

            }
        });
        tfUnderField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent event) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (event.getKeyCode() == KeyEvent.VK_ENTER || event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            setFocus(event);
                        }
                    }
                });
            }
        });

        tfUnderField = (JTextField) jComboBoxUnder.getEditor().getEditorComponent();
        tfUnderField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                currentFocusValue = 2;
                String text = tfUnderField.getText();
                if (text.length() == 0) {
                    jComboBoxUnder.hidePopup();
                    setGroupLedgerModel(new DefaultComboBoxModel(vectorLedger), "");
                } else {
                    DefaultComboBoxModel m = Util.getSuggestedModel(vectorLedger, text);
                    if (m.getSize() == 0 || hide_flag) {
                        jComboBoxUnder.hidePopup();
                        hide_flag = false;
                    } else {
                        setGroupLedgerModel(m, text);
                        //setAccountLedgetModel(m, text);
                        jComboBoxUnder.showPopup();
                    }
                }
                tfUnderField.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }
        });


        jTextFieldOpeningBalance.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldOpeningBalanceFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldOpeningBalanceFocusLost(evt);
            }
        });
        jTextFieldOpeningBalance.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldOpeningBalanceKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jTextFieldOpeningBalance);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LedgerGUIForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        radioButtonDebit.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                radioButtonDebitFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                radioButtonDebitFocusLost(evt);
            }
        });

        radioButtonDebit.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radioButtonDebitStateChanged(evt);
            }
        });

        radioButtonDebit.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                radioButtonDebitKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        radioButtonCredit.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radioButtonCreditStateChanged(evt);
            }
        });

        radioButtonCredit.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                radioButtonCreditFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                radioButtonCreditFocusLost(evt);
            }
        });

        radioButtonCredit.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                radioButtonCreditKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        jTextFieldAddress.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldAddressFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldAddressFocusLost(evt);
            }
        });
        jTextFieldAddress.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAddressKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        jTextFieldContactNo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldContactNoFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldContactNoFocusLost(evt);
            }
        });
        jTextFieldContactNo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldContactNoKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
//		Util.filterCharacter(evt, jTextFieldContactNo);
                Util.filterForDigitAndDash(evt, jTextFieldContactNo);
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

        jTextFieldIncomeTaxNo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldIncomeTaxNoFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldIncomeTaxNoFocusLost(evt);
            }
        });
        jTextFieldIncomeTaxNo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldIncomeTaxNoKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                //Util.filterCharacter(evt, jTextFieldIncomeTaxNo);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        jTextFieldSaleTaxNo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldSaleTaxNoFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldSaleTaxNoFocusLost(evt);
            }
        });
        jTextFieldSaleTaxNo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldSaleTaxNoKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                //Util.filterCharacter(evt, jTextFieldSaleTaxNo);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        jTextFieldECSNumber.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldECSNumberFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldECSNumberFocusLost(evt);
            }
        });
        jTextFieldECSNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldECSNumberKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                //Util.filterCharacter(evt, jTextFieldSaleTaxNo);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        jTextFieldCVATNumber.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCVATNumberFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCVATNumberFocusLost(evt);
            }
        });
        jTextFieldCVATNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCVATNumberKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                //Util.filterCharacter(evt, jTextFieldSaleTaxNo);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });


        jTextFieldCSTNumber.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCSTNumberFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCSTNumberFocusLost(evt);
            }
        });
        jTextFieldCSTNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCSTNumberKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                //Util.filterCharacter(evt, jTextFieldSaleTaxNo);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        jTextFieldCreditLimit.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCreditLimitFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCreditLimitFocusLost(evt);
            }
        });
        jTextFieldCreditLimit.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCreditLimitKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jTextFieldCreditLimit);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LedgerGUIForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        jTableLedgerList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jTableLedgerListMouseClicked(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LedgerGUIForm.this, ex.getMessage());
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }
        });

        jTableLedgerList.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                try {
                    jTableLedgerListKeyPressed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(LedgerGUIForm.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(LedgerGUIForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyPressed(evt);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    jTextFieldSearchKeyReleased(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
            }
        });

        jbuttonCreateGroup.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jbuttonCreateGroupActionPerformed(evt);
            }
        });

        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        jButtonBack.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    jButtonNew.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldCSTNumber.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    jButtonNewActionPerformed(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LedgerGUIForm.this, ex.getMessage());
                }
            }
        });

        jButtonNew.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    jButtonBack.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (jButtonDelete.isEnabled()) {
                        jButtonDelete.requestFocus();
                    } else {
                        jButtonSubmit.requestFocus();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldCSTNumber.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        jButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    jButtonSubmitActionPerformed(evt);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(LedgerGUIForm.this, e.getMessage());
                }
            }
        });

        jButtonSubmit.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldCSTNumber.requestFocus();
                } //                else if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                //                    try {
                //                        jButtonSubmitActionPerformed(null);
                //                    } catch (Exception ex) {
                //                        Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
                //                    }
                //                } 
                else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (jButtonDelete.isEnabled()) {
                        jButtonDelete.requestFocus();
                    } else {
                        jButtonNew.requestFocus();
                    }
                } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (jButtonDelete.isEnabled()) {
                        jButtonDelete.requestFocus();
                    }
                }
            }
        });



        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    jButtonDeleteActionPerformed(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LedgerGUIForm.this, ex.getMessage());
                }

            }
        });

        jButtonDelete.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    jButtonNew.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    jButtonSubmit.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldCSTNumber.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });

    }

    private void jTextFieldNameFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        jTextFieldName.selectAll();
        currentFocusValue = 0;
    }

    private void jTextFieldNameFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextFieldNameKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:

        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER) {
            setFocus(evt);
        }
        if (code == KeyEvent.VK_ESCAPE) {
            jButtonBackActionPerformed(null);
        }
    }

    private void jTextFieldAliasFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        jTextFieldAlias.selectAll();
        currentFocusValue = 1;
    }

    private void jTextFieldAliasFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextFieldAliasKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void tfUnderFieldFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        tfUnderField.selectAll();
        currentFocusValue = 2;
        String text = tfUnderField.getText();
        if (text.length() == 0) {
            jComboBoxUnder.hidePopup();
            setGroupLedgerModel(new DefaultComboBoxModel(vectorLedger), "");
        } else {
            DefaultComboBoxModel m = Util.getSuggestedModel(vectorLedger, text);
            if (m.getSize() == 0 || hide_flag) {
                jComboBoxUnder.hidePopup();
                hide_flag = false;
            } else {
                setGroupLedgerModel(m, text);
                jComboBoxUnder.showPopup();
            }

        }
    }

    private void tfUnderFieldFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void tfUnderFieldKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            System.out.println("YYYYY   tfUnderFieldKeyPressed  ");
            setFocus(evt);
        }
    }

    private void jTextFieldOpeningBalanceFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        jTextFieldOpeningBalance.selectAll();
        currentFocusValue = 3;
        if (jTextFieldOpeningBalance.getText().toString().equals("" + 0D)) {
            jTextFieldOpeningBalance.setText("");
        }
    }

    private void jTextFieldOpeningBalanceFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:

        if (jTextFieldOpeningBalance.getText().toString().isEmpty() || jTextFieldOpeningBalance.getText().toString().equals(".")) {
            jTextFieldOpeningBalance.setText("" + 0D);
        }
    }

    private void radioButtonDebitStateChanged(javax.swing.event.ChangeEvent evt) {

        if (radioButtonDebit.isSelected()) {
            radioButtonCredit.setSelected(false);
        }
        if (radioButtonCredit.isSelected() == false) {
            radioButtonDebit.setSelected(true);
        }

    }

    private void radioButtonCreditStateChanged(javax.swing.event.ChangeEvent evt) {

        if (radioButtonCredit.isSelected()) {
            radioButtonDebit.setSelected(false);
        }
        if (radioButtonDebit.isSelected() == false) {
            radioButtonCredit.setSelected(true);
        }

    }

    private void radioButtonDebitFocusGained(java.awt.event.FocusEvent evt) {
        currentFocusValue = 4;
    }

    private void radioButtonDebitFocusLost(java.awt.event.FocusEvent evt) {
    }

    private void radioButtonCreditFocusGained(java.awt.event.FocusEvent evt) {
        currentFocusValue = 5;
    }

    private void radioButtonCreditFocusLost(java.awt.event.FocusEvent evt) {
    }

    private void jTextFieldOpeningBalanceKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {

            setFocus(evt);
        }
    }

    private void radioButtonDebitKeyPressed(java.awt.event.KeyEvent evt) {

        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void radioButtonCreditKeyPressed(java.awt.event.KeyEvent evt) {

        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldAddressFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        jTextFieldAddress.selectAll();
        currentFocusValue = 6;
    }

    private void jTextFieldAddressFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextFieldAddressKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldContactNoFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        jTextFieldContactNo.selectAll();
        currentFocusValue = 7;
    }

    private void jTextFieldContactNoFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextFieldContactNoKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldEmailIDFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        jTextFieldEmailID.selectAll();
        currentFocusValue = 8;
    }

    private void jTextFieldEmailIDFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextFieldEmailIDKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldIncomeTaxNoFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        jTextFieldIncomeTaxNo.selectAll();
        currentFocusValue = 10;

    }

    private void jTextFieldIncomeTaxNoFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextFieldIncomeTaxNoKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldSaleTaxNoFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        jTextFieldSaleTaxNo.selectAll();
        currentFocusValue = 11;
    }

    private void jTextFieldSaleTaxNoFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextFieldSaleTaxNoKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldECSNumberFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        jTextFieldECSNumber.selectAll();
        currentFocusValue = 12;
    }

    private void jTextFieldECSNumberFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextFieldECSNumberKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldCVATNumberFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        jTextFieldCVATNumber.selectAll();
        currentFocusValue = 13;
    }

    private void jTextFieldCVATNumberFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextFieldCVATNumberKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldCSTNumberFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        jTextFieldCSTNumber.selectAll();
        currentFocusValue = 14;
    }

    private void jTextFieldCSTNumberFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextFieldCSTNumberKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER) {
            jButtonSubmit.requestFocus();
        }
        if (code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldCreditLimitFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        jTextFieldCreditLimit.selectAll();
        currentFocusValue = 9;
        if (jTextFieldCreditLimit.getText().toString().equals("" + 0D)) {
            jTextFieldCreditLimit.setText("");
        }
    }

    private void jTextFieldCreditLimitFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        if (jTextFieldCreditLimit.getText().toString().isEmpty() || jTextFieldCreditLimit.getText().toString().equals(".")) {
            jTextFieldCreditLimit.setText("" + 0D);
        }
    }

    private void jTextFieldCreditLimitKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
//	    jButtonSubmitActionPerformed(null);
        }

    }

    private void jTextFieldSearchKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (jTableLedgerList.getRowCount() > 0) {
                jTableLedgerList.requestFocus();
                jTableLedgerList.changeSelection(0, 0, false, false);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            //btnGroupAlter_BackActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (jTableLedgerList.getRowCount() > 0) {
                jTableLedgerList.requestFocus();
                jTableLedgerList.changeSelection(0, 0, false, false);
            }
        }
    }

    private void jTableLedgerListKeyPressed(java.awt.event.KeyEvent evt) throws SQLException, Exception {
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (jTableLedgerList.getSelectedRow() == 0) {
                jTextFieldSearch.requestFocus();
                jTableLedgerList.clearSelection();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            //btnGroupAlter_BackActionPerformed(null);
            jTextFieldSearch.requestFocus();
            jTableLedgerList.clearSelection();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jTableLedgerListMouseClicked(null);
        }
    }

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) throws SQLException {

        List<LedgerDTO> ledgerList = new ArrayList<LedgerDTO>();
        String text = jTextFieldSearch.getText().trim();

        for (LedgerDTO GroupDTOEntity : ledgerDTOList) {
            if (GroupDTOEntity.getLedger_Name().toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
                ledgerList.add(GroupDTOEntity);
            }
        }
        bindDTOTojTableLedgerList(ledgerList);

    }

    private void jbuttonCreateGroupActionPerformed(java.awt.event.ActionEvent evt) {

        Container container = SwingUtilities.getAncestorOfClass(
                JDesktopPane.class, (Component) evt.getSource());

        if (container != null) {
            JDesktopPane desktopPane = getDesktopPane();
            desktopPane.setLayout(null);
            gen.account.groupDTODAO.GroupForm form = new gen.account.groupDTODAO.GroupForm();
            desktopPane.add(form);
            Dimension desktopSize = getDesktopPane().getSize();
            Dimension jInternalFrameSize = form.getSize();
            int width = (desktopSize.width - jInternalFrameSize.width) / 2;
            int height = (desktopSize.height - jInternalFrameSize.height) / 2;
            form.setLocation(width, height);
            form.setVisible(true);
        }

//        gen.account.groupDTODAO.GroupForm group = new gen.account.groupDTODAO.GroupForm();
//        try {
//            group.setSelected(true);
//        } catch (PropertyVetoException ex) {
//            Logger.getLogger(Ledger_Create.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        group.setVisible(true);
//        group.pack();
//        group.setVisible(true);
//        this.getParent().add(group);
//        this.getParent().setVisible(true);
//        Dimension desktopSize = this.getParent().getSize();
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//
//// Determine the new location of the window
//        int w = desktopSize.getSize().width;
//        int h = desktopSize.getSize().height;
//        int x = (dim.width - w + 575) / 2;
//        int y = (dim.height - h + 75) / 2;
//        group.setLocation(x, y);
//        group.setSize(840, 535);
//        //group.sets
//        group.setPreferredSize(desktopSize);
//        try {
//            group.setSelected(true);
//        } catch (java.beans.PropertyVetoException e) {
//            e.printStackTrace();
//        }
//        // dontmoveframe();
//
//        BasicInternalFrameUI ui = (BasicInternalFrameUI) group.getUI();
//
//        Component north = ui.getNorthPane();
//        MouseMotionListener[] actions =
//                (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);
//
//        for (int i = 0; i < actions.length; i++) {
//            north.removeMouseMotionListener(actions[i]);
//        }

    }

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {

        try {
            MainClass.setstaticvar();
            MainClass m = new MainClass();
            m.menuselection(1);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        newButton();
        clearFieldData();
        initComponentActiveInActive();
    }

    private void jButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        try {
            if (validateData()) {
                submit();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LedgerGUIForm.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (ParseException ex) {
            Logger.getLogger(LedgerGUIForm.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) throws SQLException, Exception {
        delete();
    }

    private void jTableLedgerListMouseClicked(java.awt.event.MouseEvent evt) throws SQLException, Exception {
        try {
            String ledger_ID = jTableLedgerList.getValueAt(jTableLedgerList.getSelectedRow(), 1).toString();
            //  selectedRow = Integer.parseInt(jTableLedgerList.getValueAt(jTableLedgerList.getSelectedRow(), 1).toString());

            edit_Ledger_Name = jTableLedgerList.getValueAt(jTableLedgerList.getSelectedRow(), 0).toString();

            loadEditForm(ledger_ID, selectedRow);

            //edit_ID = selectedRow;
            isEdit = true;

            jTextFieldName.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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
    private JLabel labelName;
    private JTextField jTextFieldName;
    private JLabel labelSearch;
    private JTextField jTextFieldSearch;
    private JLabel labelAlias;
    private JTextField jTextFieldAlias;
    private JLabel labelUnder;
    private JComboBox jComboBoxUnder;
    private JTextField tfUnderField;
    private JLabel labelOpeningBalance;
    private JTextField jTextFieldOpeningBalance;
    private JLabel labelAddress;
    private JTextField jTextFieldAddress;
    private JLabel labelContactNo;
    private JTextField jTextFieldContactNo;
    private JLabel labelEmailID;
    private JTextField jTextFieldEmailID;
    private JLabel labelIncomeTaxNo;
    private JTextField jTextFieldIncomeTaxNo;
    private JLabel labelSaleTaxNo;
    private JTextField jTextFieldSaleTaxNo;
    private JLabel labelCreditLimit;
    private JTextField jTextFieldCreditLimit;
    private JButton jbuttonCreateGroup;
    private JRadioButton radioButtonDebit;
    private JRadioButton radioButtonCredit;
    private JButton jButtonSubmit;
    private JButton jButtonBack;
    private JButton jButtonNew;
    private JButton jButtonDelete;
    private JTable jTableLedgerList;
    private DefaultTableModel jTableLedgerListModel;
    private JTextField jTextFieldECSNumber;
    private JTextField jTextFieldCVATNumber;
    private JTextField jTextFieldCSTNumber;

    private void initilize() throws SQLException, ParseException, Exception {
        initilizeGUIComponents();
        initVariables();
        bindTOGUI();
    }

    private void initilizeGUIComponents() {
        initComponentActiveInActive();
    }

    private void initVariables() throws SQLException, Exception {
        initGroup();

        groupStamp = Constants.GROUP_TIME_STAMP;
        // stockItemTimeStamp = Constants.STOCK_ITEM_TIME_STAMP;

    }

    private void initComponentActiveInActive() {
        jButtonDelete.setEnabled(false);
        // jTextFieldReceiptNo.setEditable(true);
    }

    private void setGroupLedgerModel(DefaultComboBoxModel mdl, String str) {
        jComboBoxUnder.setModel(mdl);
        jComboBoxUnder.setSelectedIndex(-1);
        //jComboBoxUnder.showPopup();
        tfUnderField.setText(str);
    }

    private void initGroup() throws SQLException, Exception {
        List<String> groups = new ArrayList<String>();
        //groups.add(Constants.SALES_ACCOUNT);

//	GroupMap.clear();
        mapGroup_Name_ID = gen.account.groupDTODAO.GroupDAO.getGroupName(groups, false);//gen.database.connection.LedgerDAO.getLedgerFromGroupName(groups, false);
        groups = new ArrayList<String>();
        vectorLedger.clear();
        for (String str : mapGroup_Name_ID.keySet()) {
            vectorLedger.add(str);
        }

        Collections.sort(
                vectorLedger,
                new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        return lhs.compareToIgnoreCase(rhs);
                    }
                });

        mapGroup_Name_ID = Util.getSmallCaseMap(mapGroup_Name_ID);
    }

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) throws PropertyVetoException {
        MainClass.setstaticvar();
//        getDesktopPane().setLayout(
//                new MigLayout("center panel",
//                "[100px:100px:1366px,grow,shrink 50,center]",
//                "[100px:100px:768px,grow,shrink 50,center]"));
//	this.dispose();
    }

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) throws PropertyVetoException, Exception {
        System.out.println("Frame Activated ---------------->>>>>>>>>>>>>>>>>>>>>");
        initGroup();
        setFocus(null);
    }

    private void newButton() throws Exception {
        try {
            ledgerDTO = new LedgerDTO();
            isEdit = false;
            bindTOGUI();
            initComponentActiveInActive();
        } catch (ParseException ex) {
            Logger.getLogger(LedgerGUIForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LedgerGUIForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void submit() throws SQLException, ParseException, Exception {
        ledgerDTO = bindGUITODTO();

        ledgerDTO = ImpExpUtil.valiDateAndRepairLedgerDTO(ledgerDTO);
        //ledgerDTO.setUnder(GroupMap.get(tfUnderField.getText().toString().toLowerCase().trim()));
        ledgerDTO.setLedger_Under(mapGroup_Name_ID.get(tfUnderField.getText().toString().toLowerCase().trim()));

        if (!isEdit) {
            insertLedger();
        } else {
            updateLedger();
        }
        JOptionPane.showMessageDialog(this, Label.RECORD_SUBMITTED_SUCCESSFULLY);
        jButtonNewActionPerformed(null);
        jTextFieldName.requestFocus();
    }

    private void delete() throws SQLException, Exception {
        //Integer selectedRow = 0;
        selectedRow = Integer.parseInt(jTableLedgerList.getValueAt(jTableLedgerList.getSelectedRow(), 1).toString());
        System.out.println("Ledgetr DTO -------------- >>>>>>>>>>>>>>>" + ledgerDTO.getLedgerID());
        if (ledgerDTO.getLedger_isdeletable().equalsIgnoreCase("1")) {
            jTextFieldName.requestFocus();
            throw new Exception("User can not delete this ledger");
        } else if (LedgerDAO.deleteLedger(ledgerDTO)) {
            JOptionPane.showMessageDialog(this, "This Ledger Can Not Be Deleted As It Is Used In Other Transactions");
        } else {
            JOptionPane.showMessageDialog(this, Label.LEDGER_DELETED_SUCCESSFULLY);
            initLedgerNameList();
            jButtonNewActionPerformed(null);
        }

    }

    private void clearFieldData() {
        jTextFieldSearch.setText("");
        jTextFieldName.setText("");
        jTextFieldAlias.setText("");
        tfUnderField.setText("");
        jTextFieldOpeningBalance.setText("0.0");
        jTextFieldAddress.setText("");
        jTextFieldContactNo.setText("");
        jTextFieldEmailID.setText("");
        jTextFieldIncomeTaxNo.setText("");
        jTextFieldSaleTaxNo.setText("");
        jTextFieldECSNumber.setText("");
        jTextFieldCVATNumber.setText("");
        jTextFieldCSTNumber.setText("");
        jTextFieldCreditLimit.setText("0.0");
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
        if (currentFocusValue > 16) {
            currentFocusValue = 16;
        }

        System.out.println("currentFocusValue ------------ " + currentFocusValue);

//	if (currentFocusValue == 0) {
//	    jTextFieldName.requestFocus();
//	} else if (currentFocusValue == 1) {
//	    jTextFieldAlias.requestFocus();
//	} else if (currentFocusValue == 2) {
//	    tfUnderField.requestFocus();
//	} else if (currentFocusValue == 3) {
//	    jTextFieldOpeningBalance.requestFocus();
//	} else if (currentFocusValue == 4) {
//	    radioButtonDebit.requestFocus();
//	} else if (currentFocusValue == 5) {
//	    radioButtonCredit.requestFocus();
//	} else if (currentFocusValue == 6) {
//	    jTextFieldAddress.requestFocus();
//	} else if (currentFocusValue == 7) {
//	    jTextFieldContactNo.requestFocus();
//	} else if (currentFocusValue == 8) {
//	    jTextFieldEmailID.requestFocus();
//	} else if (currentFocusValue == 9) {
//	    jTextFieldIncomeTaxNo.requestFocus();
//	} else if (currentFocusValue == 10) {
//	    jTextFieldSaleTaxNo.requestFocus();
//	} else if (currentFocusValue == 11) {
//	    jTextFieldCreditLimit.requestFocus();
//	}

        if (currentFocusValue == 0) {
            jTextFieldName.requestFocus();
        } else if (currentFocusValue == 1) {
            jTextFieldAlias.requestFocus();
        } else if (currentFocusValue == 2) {
            tfUnderField.requestFocus();
        } else if (currentFocusValue == 3) {
            jTextFieldOpeningBalance.requestFocus();
        } else if (currentFocusValue == 4) {
            radioButtonDebit.requestFocus();
        } else if (currentFocusValue == 5) {
            radioButtonCredit.requestFocus();
        } else if (currentFocusValue == 6) {
            jTextFieldAddress.requestFocus();
        } else if (currentFocusValue == 7) {
            jTextFieldContactNo.requestFocus();
        } else if (currentFocusValue == 8) {
            jTextFieldEmailID.requestFocus();
        } else if (currentFocusValue == 9) {
            jTextFieldCreditLimit.requestFocus();
        } else if (currentFocusValue == 10) {
            jTextFieldIncomeTaxNo.requestFocus();
        } else if (currentFocusValue == 11) {
            jTextFieldSaleTaxNo.requestFocus();
        } else if (currentFocusValue == 12) {
            jTextFieldECSNumber.requestFocus();
        } else if (currentFocusValue == 13) {
            jTextFieldCVATNumber.requestFocus();
        } else if (currentFocusValue == 14) {
            jTextFieldCSTNumber.requestFocus();
        }

    }

    private Boolean validateData() throws Exception {
        Boolean flag = true;

        String email = jTextFieldEmailID.getText().toString().trim();
        //String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        String expression = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        String ledger_name = jTextFieldName.getText().trim();
        Pattern p = Pattern.compile("[a-zA-Z0-9]");
        Matcher m = p.matcher(ledger_name);

//	if(!jTextFieldOpeningBalance.getText().toString().equals("") || !jTextFieldOpeningBalance.getText().toString().equals(".")){
        result = new BigDecimal(jTextFieldOpeningBalance.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
        result1 = new BigDecimal(jTextFieldCreditLimit.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
//	}
        if (ledgerDTO.getLedger_isdeletable() != null
                && ledgerDTO.getLedger_isdeletable().equalsIgnoreCase("1")) {
            flag = false;
            jTextFieldName.requestFocus();
            throw new Exception("User can not alter this ledger");
        } else if (jTextFieldName.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthSMALL) {
            flag = false;
            jTextFieldName.requestFocus();
            throw new Exception("Ledger Name Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
        } else if (jTextFieldAlias.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthSMALL) {
            flag = false;
            jTextFieldAlias.requestFocus();
            throw new Exception("Ledger Alias Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
        } else if (jTextFieldOpeningBalance.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            flag = false;
            jTextFieldOpeningBalance.requestFocus();
            throw new Exception("Opening Balance only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (result == 1) {
            flag = false;
            jTextFieldOpeningBalance.requestFocus();
            throw new Exception("Opening Balance only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldAddress.getText().trim().toCharArray().length >= Constants.jTextAreaCharacterLengthMEDIUM) {
            flag = false;

            jTextFieldAddress.requestFocus();
            throw new Exception("Ledger Address Exceeding " + Constants.jTextAreaCharacterLengthMEDIUM + " Character Limit");
        } else if (jTextFieldContactNo.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthSMALL) {
            flag = false;
            jTextFieldContactNo.requestFocus();
            throw new Exception("Ledger Contact Details Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
        } else if (jTextFieldEmailID.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthMEDIUM) {
            flag = false;
            jTextFieldEmailID.requestFocus();
            throw new Exception("Ledger E-Mail Details Exceeding " + Constants.jTextFieldCharacterLengthMEDIUM + " Character Limit");
        } else if (jTextFieldCreditLimit.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            flag = false;
            jTextFieldCreditLimit.requestFocus();
            throw new Exception("Credit Limit only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (result1 == 1) {
            flag = false;
            jTextFieldOpeningBalance.requestFocus();
            throw new Exception("Credit Limit only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldIncomeTaxNo.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthSMALL) {
            flag = false;
            jTextFieldIncomeTaxNo.requestFocus();
            throw new Exception("Ledger Income Tax Number Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
        } else if (jTextFieldSaleTaxNo.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthSMALL) {
            flag = false;
            jTextFieldSaleTaxNo.requestFocus();
            throw new Exception("Ledger Sales Tax Number Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
        } else if (jTextFieldName.getText().trim().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(jMainPanel, Label.ENTER_VALID_LEDGER_NAME);
            jTextFieldName.requestFocus();
            flag = false;
        } else if (!m.find()) {
            JOptionPane.showMessageDialog(jMainPanel, Label.ENTER_VALID_LEDGER_NAME);
            jTextFieldName.requestFocus();
            flag = false;
        } else if (mapLedger.get(jTextFieldName.getText().trim()) != null && isEdit == false) {
            JOptionPane.showMessageDialog(jMainPanel, Label.ALREADY_LEDGER_CREATED);
            jTextFieldName.requestFocus();
            flag = false;
        } else if (mapGroup_Name_ID.get(tfUnderField.getText().trim().toLowerCase()) == null) {
            JOptionPane.showMessageDialog(jMainPanel, Label.ENTER_VALUE_FOR_UNDER_FIELD);
            tfUnderField.requestFocus();
            flag = false;
        } else if (jTextFieldOpeningBalance.getText().trim().equalsIgnoreCase(".")) {
            JOptionPane.showMessageDialog(jMainPanel, Label.ENTER_CORRECT_OPENING_BALANCE);
            jTextFieldOpeningBalance.requestFocus();
            flag = false;
        } else if (jTextFieldCreditLimit.getText().trim().equalsIgnoreCase(".")) {
            JOptionPane.showMessageDialog(jMainPanel, Label.ENTER_CORRECT_CREDIT_BALANCE);
            jTextFieldCreditLimit.requestFocus();
            flag = false;
        } else if (!matcher.matches() && !jTextFieldEmailID.getText().toString().trim().equals("")) {
            JOptionPane.showMessageDialog(jMainPanel, Label.ENTER_VALUE_FOR_EMAIL_ID);
            jTextFieldEmailID.requestFocus();
            flag = false;
        }


        if (isEdit == true) {
            if (!jTextFieldName.getText().toString().trim().equalsIgnoreCase(edit_Ledger_Name)) {

                if (mapLedger.containsKey(jTextFieldName.getText().toString().trim())) {
                    JOptionPane.showMessageDialog(jMainPanel, Label.ALREADY_LEDGER_CREATED);
                    jTextFieldName.requestFocus();
                    flag = false;
                }
            }
        }

//        if (jTextFieldName.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthSMALL) {
//            flag = false;
//            throw new Exception("Ledger Name Exceeding 40 Character Limit");
//        }
//
//        if (jTextFieldAlias.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthSMALL) {
//            flag = false;
//            throw new Exception("Ledger Alias Exceeding 40 Character Limit");
//        }
//
//        if (jTextFieldOpeningBalance.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
//            flag = false;
//            throw new Exception("Opening Balance only valid upto 1 Trillion");
//        }
//
//        result = new BigDecimal(jTextFieldOpeningBalance.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
//        if (result == 1) {
//            flag = false;
//            throw new Exception("Opening Balance only valid upto 1 Trillion");
//        }
//
//        if (jTextFieldAddress.getText().trim().toCharArray().length >= Constants.jTextAreaCharacterLengthMEDIUM) {
//            flag = false;
//            throw new Exception("Ledger Address Exceeding 200 Character Limit");
//        }
//
//        if (jTextFieldContactNo.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthSMALL) {
//            flag = false;
//            throw new Exception("Ledger Contact Details Exceeding 40 Character Limit");
//        }
//
//        if (jTextFieldEmailID.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthMEDIUM) {
//            flag = false;
//            throw new Exception("Ledger E-Mail Details Exceeding 100 Character Limit");
//        }
//
//        if (jTextFieldCreditLimit.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
//            flag = false;
//            throw new Exception("Credit Limit only valid upto 1 Trillion");
//        }
//
//        result = new BigDecimal(jTextFieldCreditLimit.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
//        if (result == 1) {
//            flag = false;
//            throw new Exception("Credit Limit only valid upto 1 Trillion");
//        }
//
//        if (jTextFieldIncomeTaxNo.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthSMALL) {
//            flag = false;
//            throw new Exception("Ledger Income Tax Number Exceeding 40 Character Limit");
//        }
//
//        if (jTextFieldSaleTaxNo.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthSMALL) {
//            flag = false;
//            throw new Exception("Ledger Sales Tax Number Exceeding 40 Character Limit");
//        }

        return flag;
    }

    private LedgerDTO bindGUITODTO() {

        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        Calendar currentDate = Calendar.getInstance();
        String dateNow = f.format(currentDate.getTime());

        ledgerDTO.setLedger_Date(dateNow);
        ledgerDTO.setLedger_user("1");

        if (!jTextFieldName.getText().trim().equalsIgnoreCase("")) {
            ledgerDTO.setLedger_Name(jTextFieldName.getText().trim());
        }
        ledgerDTO.setLedger_Alias(jTextFieldAlias.getText().trim());
        if (!tfUnderField.getText().trim().equalsIgnoreCase("")) {
            ledgerDTO.setLedger_Under((tfUnderField.getText().trim()));
        }


        if (!jTextFieldOpeningBalance.getText().toString().trim().equals("") && !jTextFieldOpeningBalance.getText().toString().trim().equals(".")) {
            ledgerDTO.setLedger_OpeningBalence(Double.parseDouble(jTextFieldOpeningBalance.getText().trim()));
        } else {
            ledgerDTO.setLedger_OpeningBalence(0.0);
        }


        ledgerDTO.setLedger_Address(jTextFieldAddress.getText().trim());
        ledgerDTO.setLedger_ContactNo((jTextFieldContactNo.getText().trim()));
        ledgerDTO.setLedger_EmailID((jTextFieldEmailID.getText().trim()));
        ledgerDTO.setLedger_IncomeTaxNo(jTextFieldIncomeTaxNo.getText().trim());
        ledgerDTO.setLedger_SaleTaxNo((jTextFieldSaleTaxNo.getText().trim()));
        ledgerDTO.setLedger_ECSNumber(jTextFieldECSNumber.getText().trim());
        ledgerDTO.setLedger_CVATNumber(jTextFieldCVATNumber.getText().trim());
        ledgerDTO.setLedger_CSTNumber(jTextFieldCSTNumber.getText().trim());


        if (ledgerDTO.getLedger_isdeletable() != null
                && !ledgerDTO.getLedger_isdeletable().equalsIgnoreCase("")) {
            ledgerDTO.setLedger_isdeletable(ledgerDTO.getLedger_isdeletable());
        } else {
            ledgerDTO.setLedger_isdeletable("1");
        }


        if (!jTextFieldCreditLimit.getText().toString().trim().equals("") && !jTextFieldCreditLimit.getText().toString().trim().equals(".")) {
            ledgerDTO.setLedger_CreditLimit(Double.parseDouble(jTextFieldCreditLimit.getText().trim()));
        } else {
            ledgerDTO.setLedger_CreditLimit(0.0);
        }


        if (radioButtonCredit.isSelected()) {
            ledgerDTO.setDebitOrCredit(2);
        } else {
            ledgerDTO.setDebitOrCredit(1);
        }

        return ledgerDTO;
    }

    private void insertLedger() throws SQLException, ParseException, Exception {
        List<LedgerDTO> ledgerDTOList = new ArrayList<LedgerDTO>();
        ledgerDTO.setLedger_Under(mapGroup_Name_ID.get(tfUnderField.getText().toString().toLowerCase().trim()));
        ledgerDTOList.add(ledgerDTO);
        LedgerDAO.insertLedger(ledgerDTOList);
        //LedgerDAO.insertLedger(ledgerDTO, ledgerDTO.getLedger_Under());
    }

    private void updateLedger() throws SQLException, ParseException, Exception {
        //selectedRow = Integer.parseInt(jTableLedgerList.getValueAt(jTableLedgerList.getSelectedRow(), 1).toString());

        List<LedgerDTO> LedgerDTOList = new ArrayList<LedgerDTO>();

//	ledgerDTO.setLedgerID(edit_ID + "");


        ledgerDTO.setLedger_Under(mapGroup_Name_ID.get(tfUnderField.getText().toString().toLowerCase().trim()));
        LedgerDTOList.add(ledgerDTO);

        LedgerDAO.UpdateLedger(LedgerDTOList);
        //LedgerDAO.UpdateLedger(ledgerDTO, ledgerDTO.getLedger_Under().toString(), selectedRow);
    }

    private void bindDTOTojTableLedgerList(List<LedgerDTO> ledgerDTONameList) throws SQLException {
        try {
            if (ledgerDTONameList != null && !ledgerDTONameList.isEmpty()) {
                jTableLedgerListModel.setRowCount(0);
                for (LedgerDTO ledgerDTOEntity : ledgerDTONameList) {
                    jTableLedgerListModel.setRowCount(jTableLedgerListModel.getRowCount() + 1);
                    jTableLedgerListModel.setValueAt(ledgerDTOEntity.getLedger_Name(), jTableLedgerListModel.getRowCount() - 1, 0);
                    jTableLedgerListModel.setValueAt(ledgerDTOEntity.getLedgerID(), jTableLedgerListModel.getRowCount() - 1, 1);
                }
            } else {
                jTableLedgerListModel.setRowCount(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(LedgerGUIForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadEditForm(String id, Integer selectedRow) throws SQLException, ParseException {
        Set<String> idSet = new HashSet<String>();
        idSet.add(id);
        List<LedgerDTO> ledgerDTOListLocal = LedgerDAO.getLedgerList(idSet, Constants.LEDGER_ID);
        if (ledgerDTOListLocal != null && !ledgerDTOListLocal.isEmpty()) {
            ledgerDTO = ledgerDTOListLocal.get(0);
            isEdit = true;
            //bindTOGUI();
            bindDTOtoGUI();
            jButtonDelete.setEnabled(true);
            currentFocusValue = 0;
            setFocus(null);
        }
    }

    private void bindTOGUI() throws ParseException, SQLException, Exception {
        //LedgerMap.clear();
        mapLedger = gen.account.ledger.LedgerDAO.getLedgerNameMap(null, true);
        bindDTOtoGUI();
        if (!isEdit || isTotalGroupTableLoad) {
            initLedgerNameList();
        }
        isTotalGroupTableLoad = false;
        jTextFieldName.requestFocus();
    }

    private void bindDTOtoGUI() throws ParseException {
        jTextFieldName.setText(ledgerDTO.getLedger_Name());
        jTextFieldAlias.setText(ledgerDTO.getLedger_Alias());
        for (Map.Entry<String, String> e_map : mapGroup_Name_ID.entrySet()) {
            if (e_map.getValue().equals(ledgerDTO.getLedger_Under().toString().trim())) {
                tfUnderField.setText(e_map.getKey());
            }
        }

//	tfUnderField.setText(mapGroup_Name_ID.ledgerDTO.getLedger_Under().toString());

        DecimalFormat df = new DecimalFormat("#.##");

        System.out.println("            " + ledgerDTO.getLedger_OpeningBalence().toString());
        if (Double.parseDouble(ledgerDTO.getLedger_OpeningBalence().toString()) != 0D) {
            jTextFieldOpeningBalance.setText(df.format(Double.parseDouble(ledgerDTO.getLedger_OpeningBalence().toString())));
        } else {
            jTextFieldOpeningBalance.setText(ledgerDTO.getLedger_OpeningBalence().toString());
        }
        jTextFieldAddress.setText(ledgerDTO.getLedger_Address());
        jTextFieldContactNo.setText(ledgerDTO.getLedger_ContactNo());
        jTextFieldEmailID.setText(ledgerDTO.getLedger_EmailID());
        jTextFieldIncomeTaxNo.setText(ledgerDTO.getLedger_IncomeTaxNo().toString());
        jTextFieldSaleTaxNo.setText(ledgerDTO.getLedger_SaleTaxNo().toString());
        jTextFieldECSNumber.setText(ledgerDTO.getLedger_ECSNumber().toString());
        jTextFieldCVATNumber.setText(ledgerDTO.getLedger_CVATNumber().toString());
        jTextFieldCSTNumber.setText(ledgerDTO.getLedger_CSTNumber().toString());
        if (Double.parseDouble(ledgerDTO.getLedger_CreditLimit().toString()) != 0D) {
            jTextFieldCreditLimit.setText(df.format(Double.parseDouble(ledgerDTO.getLedger_CreditLimit().toString())));
        } else {
            jTextFieldCreditLimit.setText(ledgerDTO.getLedger_CreditLimit().toString());
        }


        if (ledgerDTO.getDebitOrCredit() == 1) {
            radioButtonDebit.setSelected(true);
            radioButtonCredit.setSelected(false);
        } else if (ledgerDTO.getDebitOrCredit() == 2) {
            radioButtonDebit.setSelected(false);
            radioButtonCredit.setSelected(true);
        }
    }

    private void initLedgerNameList() throws Exception {
//        new Thread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
        Set<String> idSet = new HashSet<String>();
        ledgerDTOList = LedgerDAO.getLedgerList(idSet, "");
        bindDTOTojTableLedgerList(ledgerDTOList);
//                        } catch (SQLException ex) {
//                            Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
//                            ex.printStackTrace();
//                        }
//                    }
//                }).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
