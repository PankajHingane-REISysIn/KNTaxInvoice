/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.account.stockgroup;

import gen.ImpExp.InventoryTagHelper;
import gen.account.groupDTODAO.GroupDAO;
import gen.account.groupDTODAO.GroupForm;
import gen.accountvoucher.sale.SaleForm;
import gen.display.report.DayBook;
import gen.dto.Constants;
import gen.dto.GUIConstants;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
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
import javax.swing.JFileChooser;

/**
 *
 * @author pc5
 */
public class StockGroupForm extends javax.swing.JInternalFrame implements java.awt.event.ActionListener {

    private List<StockGroupDTO> stockGroupDTOsNameList;
    private StockGroupDTO stockGroupDTO = new StockGroupDTO();
    private StockGroupDTO editModeStockGroupDTO = new StockGroupDTO();
    private Boolean isEdit = false;
    Integer selectedRow = 0;
    private int currentFocusValue = 0;
    private Map<String, String> stockGroupNameList = new HashMap<String, String>();
    private Vector<String> StockGroupVector = new Vector<String>();
    private Map<String, String> stockGroupNameMap, mapStockGroupNameUnder;
    private Map<String, String> bindToGUIStockGroupNameMap;
    private boolean hide_flag = false;
    String editModeStockGroupName = "";

    public StockGroupForm() {
        try {
            initComponents();
            initilize();
            initStockGroup();
            setClosable(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(StockGroupForm.this, ex.getMessage());
        }
    }

    private void initComponents() {

        try {
            setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        setResizable(true);
        setTitle("Stock Group Information Details");
        setClosable(true);
        setBounds(100, 100, 791, 539);

        jMainPanel = new JPanel();
        getContentPane().add(jMainPanel, BorderLayout.CENTER);

        JPanel availableStockGroupsPanel = new JPanel();
        availableStockGroupsPanel.setBorder(new TitledBorder(null, "Available Stock Groups", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel stockGroupInformationPanel = new JPanel();
        stockGroupInformationPanel.setBorder(new TitledBorder(null, "Stock Group Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel controlsPanel = new JPanel();
        controlsPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GroupLayout gl_jMainPanel = new GroupLayout(jMainPanel);
        gl_jMainPanel.setHorizontalGroup(
                gl_jMainPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_jMainPanel.createSequentialGroup()
                        .addGroup(gl_jMainPanel.createParallelGroup(Alignment.LEADING, false)
                                .addGroup(gl_jMainPanel.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(controlsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(gl_jMainPanel.createSequentialGroup()
                                        .addGap(14)
                                        .addComponent(stockGroupInformationPanel, GroupLayout.PREFERRED_SIZE, 512, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(availableStockGroupsPanel, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)));
        gl_jMainPanel.setVerticalGroup(
                gl_jMainPanel.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_jMainPanel.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_jMainPanel.createParallelGroup(Alignment.BASELINE)
                                .addComponent(stockGroupInformationPanel, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                                .addComponent(availableStockGroupsPanel, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(controlsPanel, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)));
        stockGroupInformationPanel.setLayout(new MigLayout("", "[100px:100px:100px,grow,fill][25px:25px:25px,grow,shrink 50,fill][225px:225px:225px,grow,fill][grow]", "[][][][][][][][10px:10px:10px,grow,fill][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][10px:10px:10px,grow,fill][][][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][]"));

        labelName = new JLabel("Name");
        stockGroupInformationPanel.add(labelName, "cell 0 6,alignx trailing");

        jTextFieldName = new JTextField();
        stockGroupInformationPanel.add(jTextFieldName, "cell 2 6,growx");
        jTextFieldName.setColumns(10);

        labelAlias = new JLabel("Alias");
        stockGroupInformationPanel.add(labelAlias, "cell 0 9");

        jTextFieldAlias = new JTextField();
        stockGroupInformationPanel.add(jTextFieldAlias, "cell 2 9,growx");
        jTextFieldAlias.setColumns(10);

        labelUnder = new JLabel("Under");
        stockGroupInformationPanel.add(labelUnder, "cell 0 12");

        jComboBoxUnder = new JComboBox();
        jComboBoxUnder.setPrototypeDisplayValue("xxxxxx");
        tfUnderField = (JTextField) jComboBoxUnder.getEditor().getEditorComponent();
        stockGroupInformationPanel.add(jComboBoxUnder, "cell 2 12,growx");
        controlsPanel.setLayout(new MigLayout("wrap", "[0px:95px:95px,grow,shrink 50][0px:95px:95px,grow,shrink 50][0px:95px:95px,grow,shrink 50][0px:95px:95px,grow,shrink 50][0px:95px:95px,grow,shrink 50]", "[]unrel[]unrel[][]"));

        jButtonBack = new JButton("Back");
        controlsPanel.add(jButtonBack, "tag other,cell 0 1,growx");
        jButtonBack.setMnemonic('B');

        jButtonNew = new JButton("New");
        controlsPanel.add(jButtonNew, "tag other,cell 1 1,growx");
        jButtonNew.setMnemonic('N');

        jButtonDelete = new JButton("Delete");
        controlsPanel.add(jButtonDelete, "tag other,cell 2 1,growx");
    	jButtonDelete.setMnemonic('T');

        jButtonSubmit = new JButton("Submit");
        controlsPanel.add(jButtonSubmit, "tag other,cell 3 1,growx");
        jButtonSubmit.setMnemonic('S');

        jButtonExport = new JButton("Export");
        jButtonExport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dataExport();
            }
        });
        jButtonExport.setMnemonic('X');
        controlsPanel.add(jButtonExport, "cell 4 1,growx");
        availableStockGroupsPanel.setLayout(new MigLayout("", "[][][grow]", "[][][grow]"));

        labelSearch = new JLabel("Search");
        availableStockGroupsPanel.add(labelSearch, "cell 1 0,alignx trailing");

        jTextFieldSearch = new JTextField();
        availableStockGroupsPanel.add(jTextFieldSearch, "cell 0 1 3 1,growx");
        jTextFieldSearch.setColumns(10);

        JScrollPane pane1 = new JScrollPane();
        availableStockGroupsPanel.add(pane1, "cell 0 2 3 1,grow");

        jTableStockGroupList = new JTable();
        jTableStockGroupList.setBorder(new LineBorder(new Color(0, 0, 0)));
        jTableStockGroupList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        String col1[] = {Label.STOCK_GROUP_NAME};
        String data1[][] = {{"", ""}};

        jTableStockGroupListModel = new DefaultTableModel(data1, col1) {
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

        jTableStockGroupList = new JTable() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;
        };
        
        jTableStockGroupList.setModel(jTableStockGroupListModel);
        JTableHeader header1 = jTableStockGroupList.getTableHeader();
        jTableStockGroupList.getTableHeader().setReorderingAllowed(false);
        header1.setBackground(Color.yellow);
        header1.setFont(font);
        jTableStockGroupListModel = (DefaultTableModel) jTableStockGroupList.getModel();
        jTableStockGroupListModel.setRowCount(0);
        jTableStockGroupListModel.setColumnCount(2);
        jTableStockGroupList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jTableStockGroupListModel);
        jTableStockGroupList.setRowSorter(sorter);
        jTableStockGroupList.getColumnModel().getColumn(1).setPreferredWidth(0);
        jTableStockGroupList.getColumnModel().getColumn(1).setMinWidth(0);
        jTableStockGroupList.getColumnModel().getColumn(1).setMaxWidth(0);
        pane1.setViewportView(jTableStockGroupList);
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
                            jTextFieldName.setFocusable(true);
                            jTextFieldName.setRequestFocusEnabled(true);
                            jTextFieldName.requestFocus();
                        } catch (Exception e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(StockGroupForm.this, e.getMessage());
                        }
                    }
                });

                try {
                    formInternalFrameActivated(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(StockGroupForm.this, ex.getMessage());
                }

            }

            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                try {
                    // throw new UnsupportedOperationException("Not supported yet.");
                    formInternalFrameOpened(e);
                } catch (PropertyVetoException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(StockGroupForm.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockGroupForm.this, ex.getMessage());
                }
            }

            @Override
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {

                try {
                    formInternalFrameClosing(evt);
                } catch (PropertyVetoException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockGroupForm.this, ex.getMessage());
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
                try {
                    jTextFieldNameKeyPressed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(StockGroupForm.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockGroupForm.this, ex.getMessage());
                }
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
                            setStockGroupModel(new DefaultComboBoxModel(StockGroupVector), "");
                        } else {
                            DefaultComboBoxModel m = Util.getSuggestedModel(StockGroupVector, text);
                            if (m.getSize() == 0 || hide_flag) {
                                jComboBoxUnder.hidePopup();
                                hide_flag = false;
                            } else {
                                //setAccountLedgetModel(m, text);
                                setStockGroupModel(m, text);
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
                            tfUnderFieldKeyPressed(event);
                        }
                    }
                });
            }
        });

//        tfUnderField.addKeyListener(new java.awt.event.KeyAdapter() {
//            @Override
//            public void keyPressed(java.awt.event.KeyEvent evt) {
//                tfUnderFieldKeyPressed(evt);
//            }
//
//            @Override
//            public void keyTyped(java.awt.event.KeyEvent evt) {
//                EventQueue.invokeLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        String text = tfUnderField.getText();
//                        if (text.length() == 0) {
//                            jComboBoxUnder.hidePopup();
//                            setStockGroupModel(new DefaultComboBoxModel(StockGroupVector), "");
//                        } else {
//                            DefaultComboBoxModel m = Util.getSuggestedModel(StockGroupVector, text);
//                            if (m.getSize() == 0 || hide_flag) {
//                                jComboBoxUnder.hidePopup();
//                                hide_flag = false;
//                            } else {
//                                setStockGroupModel(m, text);
//                                jComboBoxUnder.showPopup();
//                            }
//                        }
//                    }
//                });
//            }
//
//            @Override
//            public void keyReleased(java.awt.event.KeyEvent evt) {
//            }
//        });
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
                    JOptionPane.showMessageDialog(StockGroupForm.this, ex.getMessage());
                }
            }
        });

        jTableStockGroupList.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        jTableStockGroupListMouseClicked(null);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(StockGroupForm.this, ex.getMessage());
                    }
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        jTableStockGroupList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jTableStockGroupListMouseClicked(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(StockGroupForm.this, ex.getMessage());
                }
            }

            public void mousePressed(MouseEvent me) {
            }

            public void mouseReleased(MouseEvent me) {
            }
        });

        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Back Button Clicked");
                try {
                    jButtonBackActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(StockGroupForm.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockGroupForm.this, ex.getMessage());
                }
            }
        });

        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    System.out.println("New Button Clicked");
                    jButtonNewActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(StockGroupForm.this, ex.getMessage());
                }
            }
        });

        jButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    jButtonSubmitActionPerformed(evt);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(StockGroupForm.this, e.getMessage());
                    if (e.getMessage().equalsIgnoreCase("Stock Group Name Exceeding 40 Character Limit")) {
                        jTextFieldName.requestFocus();
                    } else if (e.getMessage().equalsIgnoreCase("Stock Group Alias Exceeding 40 Character Limit")) {
                        jTextFieldAlias.requestFocus();
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
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(StockGroupForm.this, ex.getMessage());
                }
            }
        });

        jButtonBack.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                try {
                    jButtonBackKeyPressed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(StockGroupForm.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockGroupForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        jButtonNew.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                try {
                    jButtonNewKeyPressed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(StockGroupForm.this, ex.getMessage());
                }
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
        jButtonDelete.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonDeleteKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
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

    private void jTextFieldNameKeyPressed(java.awt.event.KeyEvent evt) throws PropertyVetoException {
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
            setStockGroupModel(new DefaultComboBoxModel(StockGroupVector), "");
        } else {
            DefaultComboBoxModel m = Util.getSuggestedModel(StockGroupVector, text);
            if (m.getSize() == 0 || hide_flag) {
                jComboBoxUnder.hidePopup();
                hide_flag = false;
            } else {
                setStockGroupModel(m, text);
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

        if (code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        if (code == KeyEvent.VK_ENTER) {

            jButtonSubmit.requestFocus();

        }
    }

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) throws PropertyVetoException {

            MainClass.setstaticvar();
            MainClass m = new MainClass();
            m.menuselection(1);
            this.setClosed(true);
    }

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        jButtonDelete.setEnabled(false);
        newButton();
        clearFieldData();
        jTextFieldName.requestFocus();
    }

    private void jButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
            if (validateData()) {
                submit();
            }
    }

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        if (validateDelete()) {
            delete();
        }
    }

    private void jButtonBackKeyPressed(java.awt.event.KeyEvent evt) throws PropertyVetoException {
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {

            jButtonNew.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            jButtonBackActionPerformed(null);

        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            tfUnderField.requestFocus();

        }
    }

    private void jButtonNewKeyPressed(java.awt.event.KeyEvent evt) throws Exception {
            if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {

                if (jButtonDelete.isEnabled()) {
                    jButtonDelete.requestFocus();
                } else {
                    jButtonSubmit.requestFocus();
                }

            }
            if (evt.getKeyCode() == KeyEvent.VK_LEFT) {

                jButtonBack.requestFocus();

            }
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                jButtonNewActionPerformed(null);

            }
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

                tfUnderField.requestFocus();

            }
    }

    private void jButtonSubmitKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {

            jButtonDelete.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {

            if (jButtonDelete.isEnabled()) {
                jButtonDelete.requestFocus();
            } else {
                jButtonNew.requestFocus();
            }

        }
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//
//            jButtonSubmitActionPerformed(null);
//
//        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            tfUnderField.requestFocus();

        }
    }

    private void jButtonDeleteKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            jButtonNew.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            jButtonSubmit.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            tfUnderField.requestFocus();
        }
    }

    private void jTableStockGroupListMouseClicked(java.awt.event.MouseEvent evt) throws Exception {

            editModeStockGroupName = jTableStockGroupList.getValueAt(jTableStockGroupList.getSelectedRow(), 0).toString().trim();
            String id = jTableStockGroupList.getValueAt(jTableStockGroupList.getSelectedRow(), 1).toString();
            loadEditForm(id);
            jButtonDelete.setEnabled(true);
            isEdit = true;
            jTextFieldName.requestFocus();
    }

    private void jTextFieldSearchKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (jTableStockGroupList.getRowCount() > 0) {
                jTableStockGroupList.requestFocus();
                jTableStockGroupList.changeSelection(0, 0, false, false);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (jTableStockGroupList.getRowCount() > 0) {
                jTableStockGroupList.requestFocus();
                jTableStockGroupList.changeSelection(0, 0, false, false);
            }
        }
    }

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) throws SQLException {

        List<StockGroupDTO> stockGroupList = new ArrayList<StockGroupDTO>();
        String text = jTextFieldSearch.getText().trim();

        for (StockGroupDTO saleDTOEntity : stockGroupDTOsNameList) {
            if (saleDTOEntity.getStockGroupName().toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
                stockGroupList.add(saleDTOEntity);
            }
        }
        bindDTOTojTableStockGroupList(stockGroupList);

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
    private JLabel labelAlias;
    private JTextField jTextFieldAlias;
    private JLabel labelUnder;
    private JLabel labelSearch;
    private JComboBox jComboBoxUnder;
    private JTextField tfUnderField;
    private JTextField jTextFieldSearch;
    private JButton jButtonExport;
    private JButton jButtonSubmit;
    private JButton jButtonBack;
    private JButton jButtonNew;
    private JButton jButtonDelete;
    private JTable jTableStockGroupList;
    private DefaultTableModel jTableStockGroupListModel;

    private void initilize() throws Exception {
        bindTOGUI();
    }

    private void setStockGroupModel(DefaultComboBoxModel mdl, String str) {
        jComboBoxUnder.setModel(mdl);
        jComboBoxUnder.setSelectedIndex(-1);
        tfUnderField.setText(str);
    }

    private void initStockGroup() throws Exception {
            stockGroupNameMap = StockGroupDAO.loadStockGroupNameToIDMap();
	mapStockGroupNameUnder = StockGroupDAO.loadStockGroupIDToUnderMap();
            StockGroupVector.clear();
            for (String str : stockGroupNameMap.keySet()) {
                StockGroupVector.add(str);
            }

            Collections.sort(
                    StockGroupVector,
                    new Comparator<String>() {
                        @Override
                        public int compare(String lhs, String rhs) {
                            return lhs.compareToIgnoreCase(rhs);
                        }
                    });

            stockGroupNameMap = Util.getSmallCaseMap(stockGroupNameMap);
	mapStockGroupNameUnder = Util.getSmallCaseMap(mapStockGroupNameUnder);
	
    }

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) throws PropertyVetoException {
        jButtonDelete.setEnabled(false);
    }

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) throws PropertyVetoException {
        MainClass.setstaticvar();
        //this.dispose();
    }

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) throws Exception {
            bindToGUIStockGroupNameMap = StockGroupDAO.loadStockGroupIDToNameMap();
            jButtonDelete.setEnabled(false);
            initStockGroup();
            initStockGroupNameList();
    }

    private void newButton() throws Exception {
            stockGroupDTO = new StockGroupDTO();
            isEdit = false;
            bindTOGUI();
    }

    private void submit() throws Exception {
            stockGroupDTO = bindGUITODTO();

            if (!isEdit) {
                insertStockGroup();

            } else {
                updateStockGroup();
            }
            JOptionPane.showMessageDialog(this, Label.RECORD_SUBMITTED_SUCCESSFULLY);
            jTextFieldName.requestFocus();
            formInternalFrameActivated(null);
            jButtonNewActionPerformed(null);
    }

    private void delete() throws Exception {
            List<StockGroupDTO> stockGroupDTOList = new ArrayList<StockGroupDTO>();
            stockGroupDTOList.add(stockGroupDTO);
            if (StockGroupDAO.deleteStockGroup(stockGroupDTOList)) {
                JOptionPane.showMessageDialog(this, "This Stock Group Can Not Be Deleted As It Is Used In Other Transactions");
            } else {
                JOptionPane.showMessageDialog(this, "Stock Group Deleted Successfully");
            }
            formInternalFrameActivated(null);
            jButtonNewActionPerformed(null);
    }

    private void clearFieldData() {

        jTextFieldName.setText("");
        jTextFieldAlias.setText("");
        tfUnderField.setText("");
        jComboBoxUnder.setSelectedItem("");

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
            jTextFieldName.requestFocus();
        } else if (currentFocusValue == 1) {
            jTextFieldAlias.requestFocus();
        } else if (currentFocusValue == 2) {
            tfUnderField.requestFocus();
        }
    }

    private Boolean validateData() throws Exception {

        Boolean flag = true;
        String ledger_name = jTextFieldName.getText().trim();
        Pattern p = Pattern.compile("[a-zA-Z0-9]");
        Matcher m = p.matcher(ledger_name);

        System.out.println("stock Group id ---------- " + stockGroupDTO.getStockGroupID());

        List<String> check_List = new ArrayList<String>();
        
        if (stockGroupDTO.getStockGroupID() != 0 && stockGroupDTO.getStockGroupID() != null) {
            if (stockGroupNameMap.get(tfUnderField.getText().toLowerCase().trim()) != null) {
                if (!stockGroupDTO.getStockGroupUnder().equals(stockGroupNameMap.get(tfUnderField.getText().toLowerCase().trim()))) {
                    check_List = gen.account.stockgroup.StockGroupDAO.getGroupIDListForGroupHeirarchy(stockGroupDTO.getStockGroupID().toString());
                }
            }
        }


	if (jTextFieldName.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthSMALL) {
	    flag = false;
	    jTextFieldName.requestFocus();
	    throw new Exception("Stock Group Name Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
	} else if (jTextFieldAlias.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthSMALL) {
	    flag = false;
	    jTextFieldAlias.requestFocus();
	    throw new Exception("Stock Group Alias Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
	} else if (tfUnderField.getText().toString().toLowerCase().trim().equalsIgnoreCase(jTextFieldName.getText().toString().toLowerCase().trim())) {
	    /// if Group Name and Under is Same.
	    System.out.println("--- In ELse If ----- ");
//	    JOptionPane.showMessageDialog(jMainPanel, Label.SAME_NAME_GROUP_UNDER_ERROR);
	    jTextFieldName.requestFocus();
	    flag = false;
            throw new Exception(Label.SAME_NAME_GROUP_UNDER_ERROR);
	} else if (isEdit) {
            if (editModeStockGroupName.equalsIgnoreCase("Primary")) {
                if (jTextFieldName.getText().trim().equalsIgnoreCase("Primary") || bindToGUIStockGroupNameMap.containsValue(tfUnderField.getText().trim())) {
//                    JOptionPane.showMessageDialog(this, "Primary is a core group,it can not be modified");
                    jTextFieldName.requestFocus();
                    flag = false;
                    throw new Exception("Primary is a core group,it can not be modified");
                }
            }
            if (!bindToGUIStockGroupNameMap.containsValue(tfUnderField.getText().trim())) {
//                JOptionPane.showMessageDialog(this, "Enter valid value for under field");
                tfUnderField.requestFocus();
                flag = false;
                throw new Exception("Enter valid value for under field");
            }
            if (!jTextFieldName.getText().trim().equalsIgnoreCase(editModeStockGroupName)) {
                if (bindToGUIStockGroupNameMap.containsValue(jTextFieldName.getText().trim())) {
//                    JOptionPane.showMessageDialog(this, "Stock Group Already Exists");
                    jTextFieldName.requestFocus();
                    flag = false;
                    throw new Exception("Stock Group Already Exists");
                }
            }
        } else if (jTextFieldName.getText().trim().equalsIgnoreCase("")) {
//            JOptionPane.showMessageDialog(this, Label.ENTER_VALID_STOCKGROUP_NAME);
            jTextFieldName.requestFocus();
            flag = false;
            throw new Exception(Label.ENTER_VALID_STOCKGROUP_NAME);
        } else if (!m.find()) {
//            JOptionPane.showMessageDialog(this, Label.ENTER_VALID_STOCKGROUP_NAME);
            jTextFieldName.requestFocus();
            flag = false;
            throw new Exception(Label.ENTER_VALID_STOCKGROUP_NAME);
        } else if ((tfUnderField.getText().trim().equalsIgnoreCase(""))) {
//            JOptionPane.showMessageDialog(this, Label.ENTER_VALUE_FOR_UNDER_FIELD);
            tfUnderField.requestFocus();
            flag = false;
            throw new Exception(Label.ENTER_VALUE_FOR_UNDER_FIELD);
        } else if (bindToGUIStockGroupNameMap.containsValue(jTextFieldName.getText().trim())) {
//            JOptionPane.showMessageDialog(this, "Stock Group Already Exists");
            jTextFieldName.requestFocus();
            flag = false;
            throw new Exception("Stock Group Already Exists");
        } else if (!bindToGUIStockGroupNameMap.containsValue(tfUnderField.getText().trim())) {
//            JOptionPane.showMessageDialog(this, "Enter valid value for under field");
            tfUnderField.requestFocus();
            flag = false;
            throw new Exception("Enter valid value for under field");
        } else if (jTextFieldName.getText().trim().equalsIgnoreCase(tfUnderField.getText().trim())) {
//            JOptionPane.showMessageDialog(this, "Stock Group Already Exists");
            jTextFieldName.requestFocus();
            flag = false;
            throw new Exception("Stock Group Already Exists");
        }
        if (check_List.contains(stockGroupNameMap.get(tfUnderField.getText().toLowerCase().trim()))) {
            flag = false;
            jTextFieldName.requestFocus();
            throw new Exception("Can not Create Stock Group under this hierarchy ...");
        }

        return flag;
    }

    private boolean validateDelete() {
        boolean flag = true;
        if (jTextFieldName.getText().trim().equalsIgnoreCase("Primary")) {
            JOptionPane.showMessageDialog(this, "Primary is a core group,it can not be deleted or modified");
            jTextFieldName.requestFocus();
            flag = false;
        } else if (mapStockGroupNameUnder.containsValue(stockGroupDTO.getStockGroupID().toString())) {
		// Group under is Created By this Group
		JOptionPane.showMessageDialog(jMainPanel, Label.GROUP_IS_CREATED);
		jTextFieldName.requestFocus();
		flag = false;
        }
	
        return flag;
    }

    private StockGroupDTO bindGUITODTO() {

        if (!jTextFieldName.getText().trim().equalsIgnoreCase("")) {
            stockGroupDTO.setStockGroupName(jTextFieldName.getText().trim());
        }
        //if (!jTextFieldAlias.getText().trim().equalsIgnoreCase("")) {
        stockGroupDTO.setStockGroupAlias(jTextFieldAlias.getText().trim());
        // }
        if (!tfUnderField.getText().trim().equalsIgnoreCase("")) {
            stockGroupDTO.setStockGroupUnder(stockGroupNameMap.get(tfUnderField.getText().trim().toLowerCase()));
        }
        return stockGroupDTO;
    }

    private void insertStockGroup() throws Exception {
        List<StockGroupDTO> stockGroupDTOList = new ArrayList<StockGroupDTO>();
        stockGroupDTOList.add(stockGroupDTO);
        StockGroupDAO.upsertStockItem(stockGroupDTOList);
    }

    private void updateStockGroup() throws Exception {
        List<StockGroupDTO> stockGroupDTOList = new ArrayList<StockGroupDTO>();
        stockGroupDTO.setStockGroupUnder(stockGroupNameMap.get(tfUnderField.getText().trim().toLowerCase()));
        stockGroupDTOList.add(stockGroupDTO);
        StockGroupDAO.upsertStockItem(stockGroupDTOList);
    }

    private void bindDTOTojTableStockGroupList(List<StockGroupDTO> receivedMap) throws SQLException {
        if (receivedMap != null && !receivedMap.isEmpty()) {
            jTableStockGroupListModel.setRowCount(0);
            for (StockGroupDTO stockGroupDTO : receivedMap) {
                if (!stockGroupDTO.getStockGroupName().toString().equalsIgnoreCase("Primary")) {
                    jTableStockGroupListModel.setRowCount(jTableStockGroupListModel.getRowCount() + 1);
                    jTableStockGroupListModel.setValueAt(stockGroupDTO.getStockGroupName(), jTableStockGroupListModel.getRowCount() - 1, 0);
                    jTableStockGroupListModel.setValueAt(stockGroupDTO.getStockGroupID(), jTableStockGroupListModel.getRowCount() - 1, 1);
                }
            }
	} else {
	    jTableStockGroupListModel.setRowCount(0);
        }
    }

    public void loadEditForm(String id) throws Exception {
        Set<String> idSet = new HashSet<String>();
        idSet.add(id);
        List<StockGroupDTO> stockGroupDTOList = StockGroupDAO.getStockGroupList(idSet, Constants.STOCK_GROUP_ID);
        if (stockGroupDTOList != null && !stockGroupDTOList.isEmpty()) {
            stockGroupDTO = stockGroupDTOList.get(0);
            editModeStockGroupDTO = stockGroupDTO;
            isEdit = true;
            bindTOGUI();
            bindDTOtoGUI();
        }
    }

    private void bindTOGUI() throws Exception {
        if (!isEdit) {
            initStockGroupNameList();
        }
    }

    private void bindDTOtoGUI() throws ParseException {
        jTextFieldName.setText(stockGroupDTO.getStockGroupName());
        jTextFieldAlias.setText(stockGroupDTO.getStockGroupAlias());
        tfUnderField.setText(bindToGUIStockGroupNameMap.get(stockGroupDTO.getStockGroupUnder()));
    }

    private void initStockGroupNameList() throws Exception {
        Set<String> idSet = new HashSet<String>();
        stockGroupDTOsNameList = StockGroupDAO.getStockGroupList(idSet, Constants.STOCK_GROUP_ID);
        bindDTOTojTableStockGroupList(stockGroupDTOsNameList);
        for (StockGroupDTO siDTO : stockGroupDTOsNameList) {
            System.out.println("siDTO.getName()--->>" + siDTO.getStockGroupName());
            System.out.println("siDTO.getID()--->>>>" + siDTO.getStockGroupID().toString());
            stockGroupNameList.put(siDTO.getStockGroupName(), siDTO.getStockGroupID().toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
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

            int r = chooser.showSaveDialog(StockGroupForm.this);

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
        StockGroupForm.MessageBox msgBox = new StockGroupForm.MessageBox();
        //Set<String> saleSet = new HashSet<String>();
        //saleSet.add(jTextFieldReceiptNo.getText().trim());
        BufferedWriter out = null;
        if (path != null) {
            if (!path.isEmpty()) {
                try {
                    // TODO add your handling code here:
                    List<StockGroupDTO> stockGroupsDTOList = StockGroupDAO.exportAllStockGroups();
                    System.out.println("stockGroupsDTOList-->>>" + stockGroupsDTOList.size());
                    String xmlCode = InventoryTagHelper.generateStockGroupListNodes(stockGroupsDTOList);

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
