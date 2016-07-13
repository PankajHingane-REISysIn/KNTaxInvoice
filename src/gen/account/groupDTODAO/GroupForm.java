 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.account.groupDTODAO;

import gen.ImpExp.ImpExpUtil;
import gen.ImpExp.InventoryTagHelper;
import gen.ImpExp.TagsHelper1;
import gen.accountvoucher.sale.SaleForm;
import gen.database.connection.DatabaseConnection1;
import gen.display.report.DayBook;
import gen.dto.Constants;
import gen.dto.Label;
import gen.dto.Util;
import gen.mainclass.MainClass;
import java.awt.BorderLayout;
import java.awt.Color;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.HashMap;
import javax.swing.JFileChooser;

/**
 *
 * @author admin
 */
public class GroupForm extends javax.swing.JInternalFrame implements java.awt.event.ActionListener {

    private gen.account.groupDTODAO.GroupDTO groupDTO;
    private List<gen.account.groupDTODAO.GroupDTO> groupDTOList;
    private Boolean isEdit = false;
    private Vector<String> groupUnderVector = new Vector<String>();
    //time stamp
    private Long groupTimeStamp;
    //variables For Calculation
    private Map<String, String> mapGroupNameID, mapGroupNameUnder;
    //variable for focus
    private int currentFocusValue = 0;
    //for Primay Group
    static ArrayList<String> MainGroups = new ArrayList<String>();
    private int temp = 1000;
    private int indexForDeletion = 0;
    private JPopupMenu popUpMenu = new JPopupMenu();
    private JMenuItem Copy_Transaction = new JMenuItem();
    private Boolean isTotalGroupTableLoad = false;
    /// check group if enter already present group when editing
    private static String enter_Present_Group_Editing = "";
    // check primary group if enter already present primary group when editing
    private static String edit_Group_Name = "";

    public GroupForm() {
        try {
            initComponents();
            groupDTO = new gen.account.groupDTODAO.GroupDTO();
            initilize();
            setClosable(true);
        } catch (Exception ex) {
            Logger.getLogger(GroupForm.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(GroupForm.this, ex.getMessage());
        }
    }

    public GroupForm(String s, Dimension d, gen.account.groupDTODAO.GroupDTO groupDTO, Boolean isEdit) {
        initComponents();
        this.isEdit = isEdit;
        isTotalGroupTableLoad = true;
        setClosable(true);
    }

    private void initComponents() {

        try {
            setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        setResizable(true);
        setTitle("Group Information Details");
        setClosable(true);
        setBounds(100, 100, 791, 539);

        jMainPanel = new JPanel();
        getContentPane().add(jMainPanel, BorderLayout.CENTER);

        JPanel availableGroupsPanel = new JPanel();
        availableGroupsPanel.setBorder(new TitledBorder(null, "Available Groups",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel groupInformationPanel = new JPanel();
        groupInformationPanel.setBorder(new TitledBorder(null, "Group Information",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel controlsPanel = new JPanel();
        controlsPanel.setBorder(new TitledBorder(null, "",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GroupLayout gl_jMainPanel = new GroupLayout(jMainPanel);
        gl_jMainPanel.setHorizontalGroup(
                gl_jMainPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_jMainPanel.createSequentialGroup()
                .addGroup(gl_jMainPanel.createParallelGroup(Alignment.TRAILING)
                .addGroup(Alignment.LEADING, gl_jMainPanel.createSequentialGroup()
                .addContainerGap()
                .addComponent(controlsPanel, GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE))
                .addGroup(Alignment.LEADING, gl_jMainPanel.createSequentialGroup()
                .addGap(14)
                .addComponent(groupInformationPanel, GroupLayout.PREFERRED_SIZE, 512, GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(availableGroupsPanel, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)));
        gl_jMainPanel.setVerticalGroup(
                gl_jMainPanel.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_jMainPanel.createSequentialGroup()
                .addContainerGap()
                .addGroup(gl_jMainPanel.createParallelGroup(Alignment.BASELINE)
                .addComponent(groupInformationPanel, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                .addComponent(availableGroupsPanel, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(controlsPanel, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                .addGap(10)));
        groupInformationPanel.setLayout(new MigLayout(
                "",
                "[100px:100px:100px,grow,fill][25px:25px:25px,grow,shrink 50,fill][225px:225px:225px,grow,fill][grow]",
                "[][][][][][][][10px:10px:10px,grow,fill][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][10px:10px:10px,grow,fill][][][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][]"));

        labelGroupName = new JLabel("Name");
        groupInformationPanel.add(labelGroupName, "cell 0 6,alignx trailing");

        jTextFieldGroupName = new JTextField();
        groupInformationPanel.add(jTextFieldGroupName, "cell 2 6,growx");
        jTextFieldGroupName.setColumns(10);

        labelAlias = new JLabel("Alias");
        groupInformationPanel.add(labelAlias, "cell 0 9");

        jTextFieldGroupAlias = new JTextField();
        groupInformationPanel.add(jTextFieldGroupAlias, "cell 2 9,growx");
        jTextFieldGroupAlias.setColumns(10);

        labelGroupUnder = new JLabel("Under");
        groupInformationPanel.add(labelGroupUnder, "cell 0 12");

        jComboBoxGroupUnder = new JComboBox();
        jComboBoxGroupUnder.setPrototypeDisplayValue("xxxxxx");
        groupInformationPanel.add(jComboBoxGroupUnder, "cell 2 12,growx");
        
        controlsPanel.setLayout(new MigLayout("wrap", "[0px:95px:95px,grow,shrink 50][0px:95px:95px,grow,shrink 50][0px:95px:95px,grow,shrink 50][0px:95px:95px,grow,shrink 50][0px:95px:95px,grow,shrink 50]", "[0px:25px:25px,grow,shrink 50]"));

        buttonBack = new JButton("Back");
        controlsPanel.add(buttonBack, "cell 0 0,growx");
        buttonBack.setMnemonic('B');

        buttonNew = new JButton("New");
        controlsPanel.add(buttonNew, "cell 1 0,growx");
        buttonNew.setMnemonic('N');

        buttonDelete = new JButton("Delete");
        controlsPanel.add(buttonDelete, "cell 2 0,growx");
        buttonDelete.setMnemonic('T');

        buttonSubmit = new JButton("Submit");
        controlsPanel.add(buttonSubmit, "cell 3 0,growx");
        buttonSubmit.setMnemonic('S');

        buttonExport = new JButton("Export");
        buttonExport.setMnemonic('X');
        buttonExport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dataExport();
            }
        });
        controlsPanel.add(buttonExport, "cell 4 0,growx");
        availableGroupsPanel.setLayout(new MigLayout("", "[][][grow]", "[][][grow]"));

        labelSearch = new JLabel("Search");
        availableGroupsPanel.add(labelSearch, "cell 1 0,alignx trailing");

        jTextFieldSearch = new JTextField();
        availableGroupsPanel.add(jTextFieldSearch, "cell 0 1 3 1,growx");
        jTextFieldSearch.setColumns(10);

        JScrollPane pane1 = new JScrollPane();
        availableGroupsPanel.add(pane1, "cell 0 2 3 1,grow");

        jTableGroupList = new JTable();
        jTableGroupList.setBorder(new LineBorder(new Color(0, 0, 0)));
        jTableGroupList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        String col1[] = {Label.GROUP_NAME, Label.GROUP_ID};
        String data1[][] = {{"", ""}};

        transactionTableModel1 = new DefaultTableModel(data1, col1) {
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

        jTableGroupList = new JTable() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;
        };
        
        jTableGroupList.setModel(transactionTableModel1);
        JTableHeader header1 = jTableGroupList.getTableHeader();
        jTableGroupList.getTableHeader().setReorderingAllowed(false);
        header1.setBackground(Color.yellow);
        header1.setFont(font);
        transactionTableModel1 = (DefaultTableModel) jTableGroupList.getModel();
        transactionTableModel1.setRowCount(0);
        transactionTableModel1.setColumnCount(2);
        jTableGroupList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(transactionTableModel1);
        jTableGroupList.setRowSorter(sorter);
        jTableGroupList.getColumnModel().getColumn(1).setPreferredWidth(0);
        jTableGroupList.getColumnModel().getColumn(1).setMinWidth(0);
        jTableGroupList.getColumnModel().getColumn(1).setMaxWidth(0);
        pane1.setViewportView(jTableGroupList);
        jMainPanel.setLayout(gl_jMainPanel);


        initialiseActionListeners();
    }

    private void initialiseActionListeners() {
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(Util.getImageIconPath())));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            @Override
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("Focus requested to name field");
                            jTextFieldGroupName.setFocusable(true);
                            jTextFieldGroupName.setRequestFocusEnabled(true);
                            jTextFieldGroupName.requestFocus();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                });

                try {
                    formInternalFrameActivated(evt);
                    // formInternalFrameActivated(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(GroupForm.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(GroupForm.this, ex.getMessage());
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
                    ex.printStackTrace();
                    Logger.getLogger(GroupForm.class.getName()).log(Level.SEVERE, null, ex);
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


        jComboBoxGroupUnder.setEditable(true);
        tfGroupUnderText = (JTextField) jComboBoxGroupUnder.getEditor().getEditorComponent();
        tfGroupUnderText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String text = tfGroupUnderText.getText();
                        if (text.length() == 0) {
                            jComboBoxGroupUnder.hidePopup();
                            setAccountLedgetModel(new DefaultComboBoxModel(groupUnderVector), "");
                        } else {
                            DefaultComboBoxModel m = Util.getSuggestedModel(groupUnderVector, text);
                            if (m.getSize() == 0 || hide_flag) {
                                jComboBoxGroupUnder.hidePopup();
                                hide_flag = false;
                            } else {
                                setAccountLedgetModel(m, text);
                                jComboBoxGroupUnder.showPopup();
                            }
                        }
                    }
                });

            }
        });
        tfGroupUnderText.addKeyListener(new KeyAdapter() {
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

        tfGroupUnderText = (JTextField) jComboBoxGroupUnder.getEditor().getEditorComponent();
        tfGroupUnderText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                currentFocusValue = 2;
                String text = tfGroupUnderText.getText();
                if (text.length() == 0) {
                    jComboBoxGroupUnder.hidePopup();
                    setAccountLedgetModel(new DefaultComboBoxModel(groupUnderVector), "");
                } else {
                    DefaultComboBoxModel m = Util.getSuggestedModel(groupUnderVector, text);
                    if (m.getSize() == 0 || hide_flag) {
                        jComboBoxGroupUnder.hidePopup();
                        hide_flag = false;
                    } else {
                        setAccountLedgetModel(m, text);
                        jComboBoxGroupUnder.showPopup();
                    }
                }
                tfGroupUnderText.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }
        });


        jTextFieldGroupName.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldGroupNameFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                //jTextFieldQuantityFocusLost(evt);
            }
        });

        jTextFieldGroupName.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldGroupNameKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                //Util.filterCharacter(evt, jTextFieldQuantity);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                //calculatePartiAmount();
            }
        });

        jTextFieldGroupAlias.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldGroupAliasFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                //jTextFieldGroupAliasFocusLost(evt);
            }
        });

        jTextFieldGroupAlias.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldGroupAliasKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                //Util.filterCharacter(evt, jTextFieldSquareFeet);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculatePartiAmount();
            }
        });


        jTableGroupList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTransactionListMouseClicked(evt);
            }

            public void mousePressed(MouseEvent me) {
                //showPopup(me);
            }

            public void mouseReleased(MouseEvent me) {
                //showPopup(me);
            }
        });

        jTableGroupList.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableLedgerListKeyPressed(evt);
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
                    Logger.getLogger(GroupForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
            }
        });

        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });

        buttonBack.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    buttonNew.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    tfGroupUnderText.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        buttonSubmit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonSubmitActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(GroupForm.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(GroupForm.this, ex.getMessage());
//                    if (ex.getMessage().equalsIgnoreCase("Group Name Exceeding 40 Character Limit")) {
//                        jTextFieldGroupName.requestFocus();
//                    } else if (ex.getMessage().equalsIgnoreCase("Group Alias Exceeding 40 Character Limit")) {
//                        jTextFieldGroupAlias.requestFocus();
//                    }
                }
            }
        });

        buttonSubmit.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    tfGroupUnderText.requestFocus();
                } //                else if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                //                    try {
                //                        buttonSubmitActionPerformed(null);
                //                    } catch (Exception ex) {
                //                        ex.printStackTrace();
                //                        Logger.getLogger(GroupForm.class.getName()).log(Level.SEVERE, null, ex);
                //                    }
                //                } 
                else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (buttonDelete.isEnabled()) {
                        buttonDelete.requestFocus();
                    } else {
                        buttonNew.requestFocus();
                    }
                }
            }
        });

        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonDeleteActionPerformed(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(GroupForm.this, ex.getMessage());
                }

            }
        });

        buttonDelete.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    buttonNew.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    buttonSubmit.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    tfGroupUnderText.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });


        buttonNew.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonNewActionPerformed(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(GroupForm.this, ex.getMessage());
                }

            }
        });

        buttonNew.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    buttonBack.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (buttonDelete.isEnabled()) {
                        buttonDelete.requestFocus();
                    } else {
                        buttonSubmit.requestFocus();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    tfGroupUnderText.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });

    }

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) throws SQLException, Exception {
//
        if (this.groupTimeStamp != Constants.GROUP_TIME_STAMP) {
            initGroup();
        }
//        if (this.stockItemTimeStamp != Constants.STOCK_ITEM_TIME_STAMP) {
//            initStockItem();
//        }

        setFocus(null);

    }

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) throws PropertyVetoException {
        MainClass.setstaticvar();
//	this.dispose();
    }

    private void jTextFieldGroupNameFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        currentFocusValue = 0;
//        Util.checkForZero(jTextFieldGroupName);
        jTextFieldGroupName.selectAll();
    }

    private void jTextFieldGroupNameKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            setFocus(evt);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            buttonBackActionPerformed(null);
        }
//        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
//            tableParti.requestFocus();
//        }
    }

    private void jTextFieldGroupAliasFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        currentFocusValue = 1;
        //Util.checkForZero(jTextFieldSquareFeet);
        jTextFieldGroupAlias.selectAll();
    }

    private void jTextFieldGroupAliasKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
//        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
//            tableParti.requestFocus();
//        }
    }

    private void jTableLedgerListKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (jTableGroupList.getSelectedRow() == 0) {
                jTextFieldSearch.requestFocus();
                jTableGroupList.clearSelection();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            //btnGroupAlter_BackActionPerformed(null);
            jTextFieldSearch.requestFocus();
            jTableGroupList.clearSelection();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jTableTransactionListMouseClicked(null);
        }
    }

    private void jTextFieldSearchKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (jTableGroupList.getRowCount() > 0) {
                jTableGroupList.requestFocus();
                jTableGroupList.changeSelection(0, 0, false, false);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            //btnGroupAlter_BackActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (jTableGroupList.getRowCount() > 0) {
                jTableGroupList.requestFocus();
                jTableGroupList.changeSelection(0, 0, false, false);
            }
        }
    }

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) throws SQLException {

        List<gen.account.groupDTODAO.GroupDTO> groupList = new ArrayList<gen.account.groupDTODAO.GroupDTO>();
        String text = jTextFieldSearch.getText().trim();

        for (gen.account.groupDTODAO.GroupDTO GroupDTOEntity : groupDTOList) {
            if (GroupDTOEntity.getGroup_Name().toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
                groupList.add(GroupDTOEntity);
            }
        }
        bindDTOToTransactionTable(groupList);

    }

    private void jTableTransactionListMouseClicked(java.awt.event.MouseEvent evt) {
        try {
            edit_Group_Name = jTableGroupList.getValueAt(jTableGroupList.getSelectedRow(), 0).toString().trim();
            String id = jTableGroupList.getValueAt(jTableGroupList.getSelectedRow(), 1).toString();
            loadEditForm(id);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(GroupForm.this, ex.getMessage());
            Logger.getLogger(GroupForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            MainClass.setstaticvar();
            //  this.dispose();
            MainClass m = new MainClass();
            m.menuselection(1);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            Logger.getLogger(GroupForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buttonSubmitActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:
//        try {
            // TODO add your handling code here:
            if (validateData()) {
                submit();
                jTextFieldGroupName.requestFocus();
                jComboBoxGroupUnder.hidePopup();
                //clearFormData();
                buttonNewActionPerformed(evt);
            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            Logger.getLogger(GroupForm.class.getName()).log(Level.SEVERE, null, ex);
//            throw ex;
//        } catch (ParseException ex) {
//            ex.printStackTrace();
//            Logger.getLogger(GroupForm.class.getName()).log(Level.SEVERE, null, ex);
//            throw ex;
//        }
    }

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        try {
            // System.out.println("saleDTO.getTrans_ID()----------------->>" + saleDTO.getTrans_ID());

            if (validateDelete()) {
		//gen.account.groupDTODAO.GroupDAO.deleteGroup(Long.parseLong(mapGroupNameID.get(groupDTO.getGroup_Name().toLowerCase())));
                gen.account.groupDTODAO.GroupDAO.deleteGroup(groupDTO);
                JOptionPane.showMessageDialog(this, "Group deleted completly");
                //newButton();
                buttonNewActionPerformed(evt);
                jTextFieldSearch.setText("");
                jTextFieldGroupName.requestFocus();
                jComboBoxGroupUnder.hidePopup();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(GroupForm.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

    }

    private void buttonNewActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        try {
            newButton();
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(GroupForm.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        clearFormData();
        temp = 1000;
        jTextFieldGroupName.requestFocus();
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
    private JLabel labelGroupName;
    private JTextField jTextFieldGroupName;
    private JLabel labelAlias;
    private JTextField jTextFieldGroupAlias;
    private JLabel labelGroupUnder;
    private JComboBox jComboBoxGroupUnder;
    private JButton buttonBack;
    private JButton buttonSubmit;
    private JButton buttonDelete;
    private JButton buttonNew;
    private JLabel labelSearch;
    private JTextField jTextFieldSearch;
    private JTable jTableGroupList;
    private DefaultTableModel transactionTableModel1;
    private JTextField tfGroupUnderText;

    private void initilize() throws SQLException, ParseException, Exception {
        initilizeGUIComponents();
        initVariables();
        bindTOGUI();
    }

    private void initilizeGUIComponents() {
        initComponentActiveInActive();
    }

    private void initComponentActiveInActive() {
        buttonDelete.setEnabled(false);
        // jTextFieldReceiptNo.setEditable(true);
    }

    private void initVariables() throws SQLException, Exception {
        initGroup();
        groupTimeStamp = Constants.GROUP_TIME_STAMP;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    private boolean hide_flag = false;
    private JButton buttonExport;

    private void setAccountLedgetModel(DefaultComboBoxModel mdl, String str) {
        jComboBoxGroupUnder.setModel(mdl);
        jComboBoxGroupUnder.setSelectedIndex(-1);
        jComboBoxGroupUnder.showPopup();
        tfGroupUnderText.setText(str);
    }

    public void loadEditForm(String id) throws SQLException, ParseException, Exception {
        Set<String> idSet = new HashSet<String>();
        idSet.add(id);

        List<gen.account.groupDTODAO.GroupDTO> GroupDTOList = gen.account.groupDTODAO.GroupDAO.getGroupListForGroupForm(idSet, Constants.GROUP_ID);

        if (GroupDTOList != null && !GroupDTOList.isEmpty()) {
            groupDTO = GroupDTOList.get(0);
            isEdit = true;
            buttonDelete.setEnabled(true);
            bindTOGUI();
            currentFocusValue = 0;
            setFocus(null);
        }
    }

    private void bindTOGUI() throws ParseException, SQLException, Exception {
        bindDTOtoGUI();
        addMainGroups();
	mapGroupNameID = GroupDAO.getGroupName(null, false);

	mapGroupNameUnder = GroupDAO.getGroupNameWithUnder(null, false);
        initGroup();
        //bindDTOtoJtable();
        if (!isEdit || isTotalGroupTableLoad) {
            initTransactionList();
        }
        isTotalGroupTableLoad = false;
        jTextFieldGroupName.requestFocus();
    }

    private void bindDTOtoGUI() throws ParseException {

        //tfGroupUnderText.setText(groupDTO.getGroup_Under());
	for (Map.Entry<String, String> e : mapGroupNameID.entrySet()) {
            if (isEdit) {
                if (e.getValue().equals(groupDTO.getGroup_Under())) {
                    tfGroupUnderText.setText(e.getKey());
                }
            } else {
                tfGroupUnderText.setText("");
            }

        }

        jTextFieldGroupAlias.setText(groupDTO.getGroup_Alias());
        jTextFieldGroupName.setText("" + groupDTO.getGroup_Name());


    }

    private void bindDTOToTransactionTable(List<gen.account.groupDTODAO.GroupDTO> groupDTOTransactionList) throws SQLException {
        if (groupDTOTransactionList != null && !groupDTOTransactionList.isEmpty()) {
            transactionTableModel1.setRowCount(0);
            for (gen.account.groupDTODAO.GroupDTO GroupDTOEntity : groupDTOTransactionList) {
                try {
                    if (!GroupDTOEntity.getGroup_Name().toString().equalsIgnoreCase("Primary")) {
                        transactionTableModel1.setRowCount(transactionTableModel1.getRowCount() + 1);
                        transactionTableModel1.setValueAt(GroupDTOEntity.getGroup_Name(), transactionTableModel1.getRowCount() - 1, 0);
                        //java.util.Date date = (java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(saleDTOEntity.getDate().trim());
                        //transactionTableModel1.setValueAt(Constants.DATE_FORMATER.format(date), transactionTableModel1.getRowCount() - 1, 1);
                        transactionTableModel1.setValueAt(GroupDTOEntity.getGroup_id(), transactionTableModel1.getRowCount() - 1, 1);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(GroupForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
	} else {
	    transactionTableModel1.setRowCount(0);
        }
    }

    private void newButton() throws ParseException, SQLException, Exception {
        groupDTO = new gen.account.groupDTODAO.GroupDTO();
        isEdit = false;
        bindTOGUI();
        initComponentActiveInActive();
    }

    private Boolean validateData() throws Exception {

        String ledger_name = jTextFieldGroupName.getText().trim();
        Pattern p = Pattern.compile("[a-zA-Z0-9]");
        Matcher m = p.matcher(ledger_name);
	Boolean flag = true;

	List<String> check_List = new ArrayList<String>();
	if (!groupDTO.getGroup_id().isEmpty() && groupDTO.getGroup_id() != null) {
	    if (mapGroupNameID.get(tfGroupUnderText.getText().toLowerCase().trim()) != null) {
		if (!groupDTO.getGroup_Under().equals(mapGroupNameID.get(tfGroupUnderText.getText().toLowerCase().trim()))) {
		    check_List = gen.account.groupDTODAO.GroupDAO.getGroupIDListForGroupHeirarchy(groupDTO.getGroup_id().toString());
	     }
	     }
	     }

    	if (jTextFieldGroupName.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthSMALL) {
	    flag = false;
	    jTextFieldGroupName.requestFocus();
	    throw new Exception("Group Name Exceeding " + Constants.jTextFieldCharacterLengthSMALL + "  Character Limit");
    	} else if (jTextFieldGroupAlias.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthSMALL) {
	    flag = false;
	    jTextFieldGroupAlias.requestFocus();
	    throw new Exception("Group Alias Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
    	} else if (MainGroups.contains(jTextFieldGroupName.getText().toString().trim()) || MainGroups.contains(edit_Group_Name)) {
            /// Primary Group can not change  
            /// 2nd condition if user edit name and then submit   
//	    JOptionPane.showMessageDialog(jMainPanel, Label.PRIMARY_GROUP_CAN_NOT_CHANGE);
            jTextFieldGroupName.requestFocus();
            flag = false;
	    throw new Exception(Label.PRIMARY_GROUP_CAN_NOT_CHANGE);
//	} else if ( jTextFieldGroupName.getText().toString().trim().equalsIgnoreCase(".") || jTextFieldGroupName.getText().toString().trim().equalsIgnoreCase("") && (mapGroupNameID.containsKey(jTextFieldGroupName.getText().toLowerCase().toString().trim()) && isEdit == false)) {
        } else if ((mapGroupNameID.containsKey(jTextFieldGroupName.getText().toLowerCase().toString().trim()) && isEdit == false)) {
            /// if Group Name is already created or groupName is empty.
	    System.out.println("In First -------------");
//	    JOptionPane.showMessageDialog(jMainPanel, Label.GROUP_IS_ALREADY_CREATED);
            jTextFieldGroupName.requestFocus();
            flag = false;
	    throw new Exception(Label.GROUP_IS_ALREADY_CREATED);
//        } else if (!m.find() || jTextFieldGroupName.getText().toString().trim().equalsIgnoreCase(".") || jTextFieldGroupName.getText().toString().trim().equalsIgnoreCase("")) {
        } else if (!m.find() || jTextFieldGroupName.getText().toString().trim().equalsIgnoreCase(".") || jTextFieldGroupName.getText().toString().trim().equalsIgnoreCase("")) {
	    System.out.println("In Second -------------");
//	    JOptionPane.showMessageDialog(jMainPanel, Label.GROUPNAME_IS_INVALID);
            jTextFieldGroupName.requestFocus();
            flag = false;
	    throw new Exception(Label.GROUPNAME_IS_INVALID);
        } else if (tfGroupUnderText.getText().trim().equalsIgnoreCase("")) {
            /// if Group Under is empty.
            JOptionPane.showMessageDialog(jMainPanel, Label.ENTER_VALUE_FOR_GROUP_UNDER_FIELD);
            tfGroupUnderText.requestFocus();
            flag = false;
            //throw new Exception(Label.ENTER_VALUE_FOR_GROUP_UNDER_FIELD);
	} else if (tfGroupUnderText.getText().toString().toLowerCase().trim().equalsIgnoreCase(jTextFieldGroupName.getText().toString().toLowerCase().trim())) {
	    /// if Group Name and Under is Same.
	    System.out.println("--- In ELse If ----- ");
//	    JOptionPane.showMessageDialog(jMainPanel, Label.SAME_NAME_GROUP_UNDER_ERROR);
	    jTextFieldGroupName.requestFocus();
	    flag = false;
	    throw new Exception(Label.SAME_NAME_GROUP_UNDER_ERROR);
        } else if (!tfGroupUnderText.getText().trim().equalsIgnoreCase("")) {
            /// if Group Under is not Correct.
	    if (!mapGroupNameID.containsKey(tfGroupUnderText.getText().trim().toLowerCase())) {
//		JOptionPane.showMessageDialog(jMainPanel, Label.ENTER_VALUE_FOR_GROUP_UNDER_FIELD);
                tfGroupUnderText.requestFocus();
                flag = false;
		throw new Exception(Label.ENTER_VALUE_FOR_GROUP_UNDER_FIELD);
            }
        } //	else if (isEdit == true) {
        //	    if (!jTextFieldGroupName.getText().toString().trim().equals(edit_Group_Name)) {
	//		if (mapGroupNameID.containsKey(jTextFieldGroupName.getText().toLowerCase().toString().trim())) {
        //		    JOptionPane.showMessageDialog(jMainPanel, Label.GROUP_IS_ALREADY_CREATED);
        //		    jTextFieldGroupName.requestFocus();
        //		    flag = false;
        //		}
        //	    }
        //	} 


	System.out.println("IS       Edit ========== " + tfGroupUnderText.getText().toString().toLowerCase().trim());
	System.out.println("IS       Edit 1111========== " + jTextFieldGroupName.getText().toString().toLowerCase().trim());
	System.out.println(tfGroupUnderText.getText().toString().toLowerCase().trim().equalsIgnoreCase(jTextFieldGroupName.getText().toString().toLowerCase().trim()));
        if (isEdit == true) {
            if (!jTextFieldGroupName.getText().toString().trim().equals(edit_Group_Name)) {
		System.out.println("AAAAAAAAAA       " + mapGroupNameID.containsKey(jTextFieldGroupName.getText().toLowerCase().toString().trim()));
		if (mapGroupNameID.containsKey(jTextFieldGroupName.getText().toLowerCase().toString().trim())) {
//		    JOptionPane.showMessageDialog(jMainPanel, Label.GROUP_IS_ALREADY_CREATED);
                    jTextFieldGroupName.requestFocus();
//		    check_if = 1;
                    flag = false;
		    throw new Exception(Label.GROUP_IS_ALREADY_CREATED);
                }
//		else if (mapGroupNameID.containsKey(tfGroupUnderText.getText().toLowerCase().toString().trim())) {
//		    JOptionPane.showMessageDialog(jMainPanel, Label.GROUP_IS_ALREADY_CREATED);
//		    tfGroupUnderText.requestFocus();
//		    flag = false;
//                }
            }

	    if (check_List.contains(mapGroupNameID.get(tfGroupUnderText.getText().toLowerCase().trim()))) {
//		JOptionPane.showMessageDialog(jMainPanel, "Can no create group under this heirarchy....");
		jTextFieldGroupName.requestFocus();
		flag = false;
		throw new Exception("Can not create group heirarchy");
	    }
        }



        return flag;
    }

    private Boolean validateDelete() throws Exception {
        Boolean flag = true;
        try {
            if (!checkPriGroup() || MainGroups.contains(edit_Group_Name)) {
                // Primary Group
                // 2nd condition for If user edit and entered name with already present name
		JOptionPane.showMessageDialog(jMainPanel, Label.PRIMARY_GROUP_CAN_NOT_DELETE);
                jTextFieldGroupName.requestFocus();
                flag = false;
	    } else if (mapGroupNameUnder.containsValue(groupDTO.getGroup_id().toString())) {
		// Group under is Created By this Group
		JOptionPane.showMessageDialog(jMainPanel, Label.GROUP_IS_CREATED);
		jTextFieldGroupName.requestFocus();
		flag = false;
	    } else if (mapGroupNameUnder.containsValue(groupDTO.getGroup_id().toString())) {
		// first check wheather group Nmae is already present or not
		if (!gen.account.groupDTODAO.GroupDAO.getLedgerUnderGroupList(Long.parseLong(mapGroupNameID.get(jTextFieldGroupName.getText().toLowerCase().toString().trim())))) {
                // Already Ledger is Created By this Group
                JOptionPane.showMessageDialog(jMainPanel, Label.LEDGER_IS_CREATED_BY_THIS_GROUP);
                jTextFieldGroupName.requestFocus();
                flag = false;
            }
	    }
	} catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(GroupForm.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return flag;
    }

    private void initGroup() throws SQLException, Exception {
        List<String> groups = new ArrayList<String>();

	mapGroupNameID = GroupDAO.getGroupName(groups, false);
	mapGroupNameUnder = GroupDAO.getGroupNameWithUnder(groups, false);


        groupUnderVector.clear();
//        groupUnderVector.add("");
	for (String str : mapGroupNameID.keySet()) {
            groupUnderVector.add(str);
        }

        Collections.sort(
                groupUnderVector,
                new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        return lhs.compareToIgnoreCase(rhs);
                    }
                });

	mapGroupNameID = Util.getSmallCaseMap(mapGroupNameID);
	mapGroupNameUnder = Util.getSmallCaseMap(mapGroupNameUnder);

    }

    private void submit() throws SQLException, ParseException, Exception {
        groupDTO = bindGUITODTO();
        groupDTO = ImpExpUtil.valiDateAndRepairgroupDTO(groupDTO);
	groupDTO.setGroup_Under(mapGroupNameID.get(tfGroupUnderText.getText().toString().toLowerCase().trim()));

        if (!isEdit) {
            insertGroup();
        } else {
            updateGroup();
        }
        JOptionPane.showMessageDialog(jMainPanel, Label.RECORD_SUBMITTED_SUCCESSFULLY);
        //newButton();

        jTextFieldGroupName.requestFocus();
    }

    private gen.account.groupDTODAO.GroupDTO bindGUITODTO() {
        gen.account.groupDTODAO.GroupDTO GroupDTOEntity = new gen.account.groupDTODAO.GroupDTO();
        GroupDTOEntity.setGroup_id(groupDTO.getGroup_id());

        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        Calendar currentDate = Calendar.getInstance();
        String dateNow = f.format(currentDate.getTime());

        GroupDTOEntity.setGroup_Date(dateNow);

        GroupDTOEntity.setGroup_User("1");

        if (!jTextFieldGroupName.getText().trim().equalsIgnoreCase("")) {
            GroupDTOEntity.setGroup_Name(jTextFieldGroupName.getText());
        }

        if (!jTextFieldGroupAlias.getText().trim().equalsIgnoreCase("")) {
            GroupDTOEntity.setGroup_Alias(jTextFieldGroupAlias.getText());
        }

        if (!tfGroupUnderText.getText().trim().equalsIgnoreCase("")) {
            GroupDTOEntity.setGroup_Under(tfGroupUnderText.getText().trim());
        }

        return GroupDTOEntity;
    }

    private void insertGroup() throws SQLException, ParseException {
        List<gen.account.groupDTODAO.GroupDTO> GroupDTOList = new ArrayList<gen.account.groupDTODAO.GroupDTO>();
        GroupDTOList.add(groupDTO);
        gen.account.groupDTODAO.GroupDAO.upsertGroup(GroupDTOList);
        //AccountingVoucherHelper.updateVoucherNumber(Constants.SALE_TYPE_INDEX, (saleDTO.getReceiptNo()));
    }

    private void updateGroup() throws SQLException, ParseException {
        List<gen.account.groupDTODAO.GroupDTO> GroupDTOList = new ArrayList<gen.account.groupDTODAO.GroupDTO>();
	//groupDTO.setGroup_id(mapGroupNameID.get(jTextFieldGroupName.getText().toString().toLowerCase().trim()));
	groupDTO.setGroup_Under(mapGroupNameID.get(tfGroupUnderText.getText().toString().toLowerCase().trim()));

        GroupDTOList.add(groupDTO);

        gen.account.groupDTODAO.GroupDAO.upsertGroup(GroupDTOList);
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
        if (currentFocusValue > 3) {
            currentFocusValue = 3;
        }

        if (currentFocusValue == 0) {
            jTextFieldGroupName.requestFocus();
        } else if (currentFocusValue == 1) {
            jTextFieldGroupAlias.requestFocus();
        } else if (currentFocusValue == 2) {
            tfGroupUnderText.requestFocus();
        } else if (currentFocusValue == 3) {
            buttonSubmit.requestFocus();
        }
    }

    private void clearFormData() {
        jTextFieldSearch.setText("");
        jTextFieldGroupAlias.setText("");
        jTextFieldGroupName.setText("");
        tfGroupUnderText.setText("");
        edit_Group_Name = "";
    }

    private void initTransactionList() throws SQLException {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Set<String> idSet = new HashSet<String>();
                            groupDTOList = gen.account.groupDTODAO.GroupDAO.getGroupListForGroupForm(idSet, "");
                            bindDTOToTransactionTable(groupDTOList);
                        } catch (Exception ex) {
                            Logger.getLogger(GroupForm.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(GroupForm.this, ex.getMessage());
                        }
                    }
                }).start();

    }

    public void addMainGroups() {
        MainGroups.clear();
        MainGroups.add("Primary");
        MainGroups.add("Current Liability");
        MainGroups.add("Current Assets");
        MainGroups.add("Suspense Account");
        MainGroups.add("Sundry Debtors");
        MainGroups.add("Sundry Creditors");
        MainGroups.add("Stock-In-Hand");
        MainGroups.add("Sales Account");
        MainGroups.add("Purchase Account");
        MainGroups.add("Loan Liability");
        MainGroups.add("Loans and Advances");
        MainGroups.add("Indirect Expenses");
        MainGroups.add("Indirect Income");
        MainGroups.add("Income (Direct)");
        MainGroups.add("Income (Indirect)");
        MainGroups.add("Expense (Direct)");
        MainGroups.add("Expense (Indirect)");
        MainGroups.add("Bank Account");
        MainGroups.add("Cash In Hand");
        MainGroups.add("Duties and Taxes");
        MainGroups.add("Capital Account");

        Collections.sort(MainGroups);
        for (String s : MainGroups) {
            //    System.out.println(s);
        }

    }

    private Boolean checkPriGroup() {
        Boolean flag = true;
        int i = 0;
        while (i < MainGroups.size()) {
            if (jTextFieldGroupName.getText().equalsIgnoreCase(MainGroups.get(i))) {
                flag = false;
            }
            i++;
        }
        return flag;
    }
    String path = "";

    public class MessageBox {

        BufferedImage mImage;
        String name, name1;

        public MessageBox() {
            String source = filechoose();
            if (source != null) {
                if (!source.isEmpty()) {
                    File inputFile = new File(source);
                }
            }
        }

        String filechoose() {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            chooser.setDialogType(JFileChooser.SAVE_DIALOG);


            chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                @Override
                public boolean accept(File f) {
                    name = f.getName().toLowerCase();
                    return name.endsWith(".xml") || name.endsWith(".xml")
                            || name.endsWith(".xml") || f.isDirectory();
                }

                @Override
                public String getDescription() {
                    return ".xml";
                }
            });

            int r = chooser.showSaveDialog(GroupForm.this);

            if (r == JFileChooser.APPROVE_OPTION) {

                name1 = chooser.getSelectedFile().getAbsolutePath();
                if (!name1.isEmpty()) {
                    File file = chooser.getSelectedFile();
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
                    try {
                        PrintStream ps = new PrintStream(file);
                        ps.print(path);
                        ps.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return name1;
        }
    }

    private void dataExport() {

        path = "";
        GroupForm.MessageBox msgBox = new GroupForm.MessageBox();
        //Set<String> saleSet = new HashSet<String>();
        //saleSet.add(jTextFieldReceiptNo.getText().trim());
        BufferedWriter out = null;
        if (path != null) {
            if (!path.isEmpty()) {
                try {
                    // TODO add your handling code here:
                    List<gen.account.groupDTODAO.GroupDTO> groupsDTOList = GroupDAO.exportAllGroups();
                    System.out.println("groupsDTOList-->>>" + groupsDTOList.size());
                    String xmlCode = InventoryTagHelper.generateGroupListNodes(groupsDTOList);

                    File file = new File(path + ".xml");
                    path = file.getPath();
                    out = new BufferedWriter(new FileWriter(file));
                    out.write(xmlCode);
                    out.close();

                    JOptionPane.showMessageDialog(this, "Export Successful");

                } catch (Exception ex) {
                    Logger.getLogger(DayBook.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Export failure");
                }
            }
        }

    }
}
