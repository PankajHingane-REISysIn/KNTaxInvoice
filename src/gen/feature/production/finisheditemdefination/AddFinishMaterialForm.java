/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.feature.production.finisheditemdefination;

/**
 *
 * @author admin
 */
import com.sun.media.sound.ModelPatch;
import com.toedter.calendar.JDateChooser;
import gen.accountvoucher.TableCellListener;
import gen.accountvoucher.sale.SaleDTO;
import gen.accountvoucher.sale.SaleForm;
import gen.dto.Constants;
import gen.dto.Label;
import gen.dto.Util;
import gen.mainclass.MainClass;
import gen.feature.production.impregnatedpaperproduction.ImpregnatedPaperProductionDAO;
import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import net.miginfocom.swing.MigLayout;

public class AddFinishMaterialForm extends JInternalFrame {

    /**
     *
     */
    private Vector<String> finishStockItemVector = new Vector<String>();
    private Vector<String> rawStockItemVector = new Vector<String>();
    private static final long serialVersionUID = 1L;
    private JTable jtableRequireMaterial;
    AddRawMaterialDTO addRawMaterialDTO = new AddRawMaterialDTO();
    private List<AddRawMaterialDTO> addRawMaterialDTOTransactionList = new ArrayList<AddRawMaterialDTO>();
    private Map<String, String> finishStockItemForComboMap = new HashMap<String, String>();
    private Map<String, String> finishStockItemForCheckMap = new HashMap<String, String>();
    private Map<String, String> rawStockItemMap = new HashMap<String, String>();
    private Map<String, String> rawStockItemInTableMap = new HashMap<String, String>();
    private Long stockItemTimeStamp;
    static int temp = 1000, deleteRawItem = 0; //for storing index of selected row of table
    private int currentFocusValue = 0;
    private Boolean isEdit = false;

    public AddFinishMaterialForm(String s, Dimension d) {
        try {
            initComponents();
            addRawMaterialDTO = new AddRawMaterialDTO();
            initilize();
            this.setPreferredSize(d);
            setClosable(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Create the frame.
     */
    public void initComponents() {

        setBounds(100, 100, 810, 716);
        getContentPane().setLayout(new BorderLayout(0, 0));

        panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new MigLayout("", "[0px:100px:100px,grow,shrink 10,fill][0px:100px:100px,grow,shrink 10,fill][0px:100px:100px,grow,shrink 10,fill][0px:100px:100px,grow,shrink 10,fill][0px:30px:30px,grow,shrink 10,fill][0px:100px:100px,grow,shrink 10,fill][0px:70px:70px,grow,shrink 10,fill][0px:70px:70px,grow,shrink 10,fill][0px:80px:80px,grow,shrink 10,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        lblFinishItem = new JLabel("Finish Item");
        panel.add(lblFinishItem, "cell 1 2,alignx trailing");

        lblDate = new JLabel("Date");
        panel.add(lblDate, "cell 5 2");

        comboBoxFinishItem = new JComboBox();
        comboBoxFinishItem.setPrototypeDisplayValue("xxxxxx");
        comboBoxFinishItem.setEditable(true);
        panel.add(comboBoxFinishItem, "cell 1 3 3 1,growx");

        dateChooser = new JDateChooser();
        panel.add(dateChooser, "cell 5 3,grow");

        lblNewLabel = new JLabel("Require Material");
        panel.add(lblNewLabel, "cell 1 5 2 1");

        lblQuantity = new JLabel("Quantity");
        panel.add(lblQuantity, "cell 5 5");

        comboBoxRawMaterial = new JComboBox();
        comboBoxRawMaterial.setPrototypeDisplayValue("xxxxxx");
        comboBoxRawMaterial.setEditable(true);
        panel.add(comboBoxRawMaterial, "cell 1 6 3 1,growx");

        jTextFieldQuantity = new JTextField();
        panel.add(jTextFieldQuantity, "cell 5 6,growx");
        jTextFieldQuantity.setColumns(10);

        btnAdd = new JButton("ADD");
        btnAdd.setMnemonic('A');
        panel.add(btnAdd, "cell 6 6");

        btnDelete = new JButton("Delete");
        btnDelete.setMnemonic('D');
        panel.add(btnDelete, "cell 7 6");

        JScrollPane pane = new JScrollPane();
        panel.add(pane, "cell 1 8 5 11,grow");

        String col[] = {"SN", Label.PARTICULARS, Label.QUANTITY};
        String data[][] = {{"", "", ""}};
        rawStockItemTableModel = new DefaultTableModel(data, col);
        jtableRequireMaterial = new JTable() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 2) {
                    return true;
                } else {
                    return false;
                }
            }
        ;
        };
        
        jtableRequireMaterial.setModel(rawStockItemTableModel);
        JTableHeader header = jtableRequireMaterial.getTableHeader();
        jtableRequireMaterial.getTableHeader().setReorderingAllowed(false);
        header.setBackground(Color.yellow);
        header.setFont(font);
        rawStockItemTableModel = (DefaultTableModel) jtableRequireMaterial.getModel();
        rawStockItemTableModel.setRowCount(0);
        rawStockItemTableModel.setColumnCount(3);
        jtableRequireMaterial.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtableRequireMaterial.getColumnModel().getColumn(0).setPreferredWidth(45);
        jtableRequireMaterial.getColumnModel().getColumn(0).setMaxWidth(45);
        jtableRequireMaterial.getColumnModel().getColumn(1).setPreferredWidth(300);
        jtableRequireMaterial.getColumnModel().getColumn(1).setMaxWidth(300);
        jtableRequireMaterial.getColumnModel().getColumn(2).setPreferredWidth(100);
        jtableRequireMaterial.getColumnModel().getColumn(2).setMaxWidth(100);

        Action action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                try {
                    TableCellListener tcl = (TableCellListener) e.getSource();
                    int column = tcl.getColumn();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AddFinishMaterialForm.this, ex.getMessage());
                }
            }
        };

        TableCellListener tcl = new TableCellListener(jtableRequireMaterial, action);
        jtableRequireMaterial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtableRequireMaterial.setEnabled(true);
        pane.setViewportView(jtableRequireMaterial);

        btnBack = new JButton("Back");
        btnBack.setMnemonic('B');
        panel.add(btnBack, "cell 2 21");

        btnSubmit = new JButton("Submit");
        btnSubmit.setMnemonic('S');
        panel.add(btnSubmit, "cell 3 21");

        initialiseActionListeners();
        tfDatePicker.setTransferHandler(null);
    }

    private void initialiseActionListeners() {

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(Util.getImageIconPath())));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            @Override
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                try {
                    formInternalFrameActivated(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AddFinishMaterialForm.this, ex.getMessage());
                }
            }

            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
            }

            @Override
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                try {
                    formInternalFrameClosing(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AddFinishMaterialForm.this, ex.getMessage());
                }
            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
            }

            @Override
            public void internalFrameIconified(InternalFrameEvent e) {
            }

            @Override
            public void internalFrameDeiconified(InternalFrameEvent e) {
            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {
            }
        });

        comboBoxFinishItem.setEditable(true);
        tfFinishItemAccountText = (JTextField) comboBoxFinishItem.getEditor().getEditorComponent();
        tfFinishItemAccountText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String text = tfFinishItemAccountText.getText();
                        if (text.length() == 0) {
                            comboBoxFinishItem.hidePopup();
                            setFinishModel(new DefaultComboBoxModel(finishStockItemVector), "");
                        } else {
                            DefaultComboBoxModel m = Util.getSuggestedModel(finishStockItemVector, text);
                            if (m.getSize() == 0 || hide_flag) {
                                comboBoxFinishItem.hidePopup();
                                hide_flag = false;
                            } else {
                                setFinishModel(m, text);
                                comboBoxFinishItem.showPopup();
                            }
                        }
                        int code = e.getKeyCode();
                        if (code == KeyEvent.VK_ENTER) {
                            if (!finishStockItemVector.contains(text)) {
                                finishStockItemVector.addElement(text);
                                Collections.sort(finishStockItemVector);
                                setFinishModel(Util.getSuggestedModel(finishStockItemVector, text), text);
                            }
                            hide_flag = true;

                        } else if (code == KeyEvent.VK_ESCAPE) {
                            hide_flag = true;
                        } else if (code == KeyEvent.VK_RIGHT) {
                            for (int i = 0; i < finishStockItemVector.size(); i++) {
                                String str = finishStockItemVector.elementAt(i);
                                if (str.startsWith(text)) {
                                    comboBoxFinishItem.setSelectedIndex(-1);
                                    tfFinishItemAccountText.setText(str);
                                    return;
                                }
                            }
                        }
                    }
                });

            }
        });
        tfFinishItemAccountText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent event) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                            setFocus(event);
                        }
                        if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            btnBackActionPerformed(null);
                        }
                    }
                });
            }
        });

        tfFinishItemAccountText = (JTextField) comboBoxFinishItem.getEditor().getEditorComponent();
        tfFinishItemAccountText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                currentFocusValue = 0;
                String text = tfFinishItemAccountText.getText();
                if (text.length() == 0) {
                    comboBoxFinishItem.hidePopup();
                    setFinishModel(new DefaultComboBoxModel(finishStockItemVector), "");
                } else {
                    DefaultComboBoxModel m = Util.getSuggestedModel(finishStockItemVector, text);
                    if (m.getSize() == 0 || hide_flag) {
                        comboBoxFinishItem.hidePopup();
                        hide_flag = false;
                    } else {
                        setFinishModel(m, text);
                        comboBoxFinishItem.showPopup();
                    }

                }
                tfFinishItemAccountText.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                // As new item should be add in map


                if (!tfFinishItemAccountText.getText().trim().equalsIgnoreCase("")) {
                    try {
                        rawStockItemInTableMap.clear();
                        initRawStockItem();
                        addRawMaterialDTO = new AddRawMaterialDTO();
                        callTableByFinishItemChange();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    rawStockItemMap.clear();
                    rawStockItemVector.clear();
                }
            }
        });

        comboBoxRawMaterial.setEditable(true);
        tfRawStockItemText = (JTextField) comboBoxRawMaterial.getEditor().getEditorComponent();
        tfRawStockItemText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String text = tfRawStockItemText.getText();
                        if (text.length() == 0) {
                            comboBoxRawMaterial.hidePopup();
                            setRawModel(new DefaultComboBoxModel(rawStockItemVector), "");
                        } else {
                            DefaultComboBoxModel m = Util.getSuggestedModel(rawStockItemVector, text);
                            if (m.getSize() == 0 || hide_flag) {
                                comboBoxRawMaterial.hidePopup();
                                hide_flag = false;
                            } else {
                                setRawModel(m, text);
                                comboBoxRawMaterial.showPopup();
                            }
                        }
                        int code = e.getKeyCode();
                        if (code == KeyEvent.VK_ENTER) {
                            if (!rawStockItemVector.contains(text)) {
                                rawStockItemVector.addElement(text);
                                Collections.sort(rawStockItemVector);
                                setRawModel(Util.getSuggestedModel(rawStockItemVector, text), text);
                            }
                            hide_flag = true;

                        } else if (code == KeyEvent.VK_ESCAPE) {
                            hide_flag = true;
                        } else if (code == KeyEvent.VK_RIGHT) {
                            for (int i = 0; i < rawStockItemVector.size(); i++) {
                                String str = rawStockItemVector.elementAt(i);
                                if (str.startsWith(text)) {
                                    comboBoxRawMaterial.setSelectedIndex(-1);
                                    tfRawStockItemText.setText(str);
                                    return;
                                }
                            }
                        }
                    }
                });

            }
        });
        tfRawStockItemText.addKeyListener(new KeyAdapter() {
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

        tfRawStockItemText = (JTextField) comboBoxRawMaterial.getEditor().getEditorComponent();
        tfRawStockItemText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                currentFocusValue = 2;
                String text = tfRawStockItemText.getText();
                if (text.length() == 0) {
                    comboBoxRawMaterial.hidePopup();
                    setRawModel(new DefaultComboBoxModel(rawStockItemVector), "");
                } else {
                    DefaultComboBoxModel m = Util.getSuggestedModel(rawStockItemVector, text);
                    if (m.getSize() == 0 || hide_flag) {
                        comboBoxRawMaterial.hidePopup();
                        hide_flag = false;
                    } else {
                        setRawModel(m, text);
                        comboBoxRawMaterial.showPopup();
                    }

                }
                tfRawStockItemText.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        dateChooser.setDateFormatString(Constants.DATE_FORMAT_STRING);
        tfDatePicker = (JTextField) dateChooser.getComponent(1);

        tfDatePicker.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                int code = e.getKeyCode();

                if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
                    setFocus(e);
                }
            }
        });

        tfDatePicker.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }
        });

        tfDatePicker.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tfDatePicker.selectAll();
                currentFocusValue = 1;
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!tfDatePicker.getText().equalsIgnoreCase("")) {
                    rawStockItemInTableMap.clear();
                    addRawMaterialDTO = new AddRawMaterialDTO();
                    callTableByFinishItemChange();
//                    tfDateISS.setText(tfDatePicker.getText().trim());
//                    tfDateRem.setText(tfDatePicker.getText().trim());
                }
            }
        });

        jTextFieldQuantity.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jTextFieldQuantityFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AddFinishMaterialForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldQuantityFocusLost(evt);
            }
        });

        jTextFieldQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                try {
                    jTextFieldQuantityKeyPressed(evt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jTextFieldQuantity);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AddFinishMaterialForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AddFinishMaterialForm.this, ex.getMessage());
                }
            }
        });

        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnAddActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AddFinishMaterialForm.this, ex.getMessage());
                }
            }
        });

        btnAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAddKeyPressed(evt);
            }
        });

        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnDeleteActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AddFinishMaterialForm.this, ex.getMessage());
                }
            }
        });

        btnDelete.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDeleteKeyPressed(evt);
            }
        });

        btnBack.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnBackActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AddFinishMaterialForm.this, ex.getMessage());
                }
            }
        });

        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnSubmitActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AddFinishMaterialForm.this, ex.getMessage());
                }
            }
        });

        jtableRequireMaterial.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtableRequireMaterialMouseClicked(evt);
            }
        });

        jtableRequireMaterial.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtableRequireMaterialKeyPressed(evt);
            }
        });

    }

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) throws Exception {
        if (this.stockItemTimeStamp != Constants.STOCK_ITEM_TIME_STAMP) {
            initFinsishStockItem();
        }
//        setFocus(null);
    }

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) throws Exception {
        MainClass.setstaticvar();
        //this.dispose();
    }

    private void jTextFieldQuantityFocusGained(java.awt.event.FocusEvent evt) throws Exception {
        currentFocusValue = 3;
        Util.checkForZero(jTextFieldQuantity);
        jTextFieldQuantity.selectAll();
    }

    private void jTextFieldQuantityFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jTextFieldQuantity);
    }

    private void jTextFieldQuantityKeyPressed(java.awt.event.KeyEvent evt) throws Exception {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnAddActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            jtableRequireMaterial.requestFocus();
        }
    }

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        partiadd();
    }

    private void btnAddKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            jTextFieldQuantity.requestFocus();
        }
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        functionDeleteKeyPressed(deleteRawItem);
        jTextFieldQuantity.setText("");
        comboBoxRawMaterial.setSelectedItem("");
        comboBoxRawMaterial.requestFocus();
        jtableRequireMaterial.clearSelection();
        temp = 1000;
        btnAdd.setText("ADD");
    }

    private void btnDeleteKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            jTextFieldQuantity.requestFocus();
        }
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        try {
//            getDesktopPane().setLayout(new MigLayout("center panel",
//                    "[100px:100px:1366px,grow,shrink 50,center]",
//                    "[100px:100px:768px,grow,shrink 50,center]"));
            getDesktopPane().setLayout(new CardLayout());
            MainClass.setstaticvar();
            MainClass m = new MainClass();
//            m.menuselection(4);
            AddFinishMaterialForm.this.setClosed(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {
        try {

            if (jtableRequireMaterial.getRowCount() > 0) {
                if (validateFinishItem()) {
                    addRawMaterialDTO = bindGUItoDTO(); /// ADD Data From GUI To ADDRAWMATERIAL DTO
                    submit();
                } else {
                    JOptionPane.showMessageDialog(this, "Select Correct FINISHITEM Name");
                    tfFinishItemAccountText.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "First ADD Raw Material");
                tfFinishItemAccountText.requestFocus();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jtableRequireMaterialMouseClicked(java.awt.event.MouseEvent evt) {
        try {
            deleteRawItem = jtableRequireMaterial.getSelectedRow();
            if (jtableRequireMaterial.getRowCount() > 0) {
                int i = jtableRequireMaterial.getSelectedRow();
                if (i > -1) {
                    Object o = jtableRequireMaterial.getValueAt(i, 0);
                    if (o != null) {
                        temp = jtableRequireMaterial.getSelectedRow();
                        comboBoxRawMaterial.setSelectedItem(jtableRequireMaterial.getValueAt(temp, 1).toString());
                        jTextFieldQuantity.setText(jtableRequireMaterial.getValueAt(temp, 2).toString());
                        btnAdd.setText("Alter");
                        comboBoxRawMaterial.requestFocus();
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jtableRequireMaterialKeyPressed(java.awt.event.KeyEvent evt) {
        try {
            int i = 0;
            deleteRawItem = jtableRequireMaterial.getSelectedRow();
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                btnSubmit.requestFocus();
            }
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                comboBoxRawMaterial.requestFocus();
            }
            if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                functionDeleteKeyPressed(deleteRawItem);
                temp = 1000;
                btnAdd.setText("ADD");
            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                if ((jtableRequireMaterial.getRowCount() - 1) == jtableRequireMaterial.getSelectedRow()) {
                    btnSubmit.requestFocus();
                }
            }
            if (jtableRequireMaterial.getSelectedRow() == 0) {
                if (evt.getKeyCode() == KeyEvent.VK_UP) {
                    tfRawStockItemText.requestFocus();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private JTextField tfDatePicker = null;
    private Font font;
    private JTextField jTextFieldQuantity;
    private JPanel panel;
    private JLabel lblFinishItem;
    private JLabel lblDate;
    private JLabel lblNewLabel;
    private JLabel lblQuantity;
    private JComboBox comboBoxFinishItem;
    private JComboBox comboBoxRawMaterial;
    private JDateChooser dateChooser;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnSubmit;
    private JButton btnBack;
    private JScrollPane scrollPane;
    private JTextField tfFinishItemAccountText, tfRawStockItemText;
    private DefaultTableModel rawStockItemTableModel;

    private void initilize() throws SQLException, ParseException, Exception {
        initVariables();
        bindTOGUI();
    }
    private boolean hide_flag = false;

    private void initVariables() throws Exception {
        initFinsishStockItem();
        initFinishItemCheck();
        isEdit = false;
        currentFocusValue = 0;
        stockItemTimeStamp = Constants.STOCK_ITEM_TIME_STAMP;
        rawStockItemInTableMap.clear();
    }

    private void initFinsishStockItem() throws Exception {
        List<String> stockGroups = new ArrayList<String>();
        finishStockItemForComboMap = gen.account.stockitem.StockItemDAO.getStockItemsNameID(stockGroups, false);
        finishStockItemVector.clear();
        rawStockItemVector.clear();
        for (String str : finishStockItemForComboMap.keySet()) {
            finishStockItemVector.add(str);
            rawStockItemVector.add(str);
        }

        Collections.sort(
                finishStockItemVector,
                new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        return lhs.compareToIgnoreCase(rhs);
                    }
                });

        finishStockItemForComboMap = Util.getSmallCaseMap(finishStockItemForComboMap);
        gen.feature.production.finisheditemdefination.AddRawMaterialDAO.getInfo();
    }

    private void initFinishItemCheck() throws Exception {
        finishStockItemForCheckMap = gen.feature.production.finisheditemdefination.AddRawMaterialDAO.getAllFinishItems();
        finishStockItemForCheckMap = Util.getSmallCaseMap(finishStockItemForCheckMap);
    }

    private void initRawStockItem() throws Exception {
        String finidhItem = "";
        Boolean flag = false;
        if (finishStockItemForCheckMap.containsKey(tfFinishItemAccountText.getText().trim().toLowerCase())) {
            finidhItem = finishStockItemForComboMap.get(tfFinishItemAccountText.getText().trim().toLowerCase());
            flag = true;
        } else {
            finidhItem = finishStockItemForComboMap.get(tfFinishItemAccountText.getText().trim().toLowerCase());
        }

        rawStockItemMap = gen.feature.production.finisheditemdefination.AddRawMaterialDAO.getAllStockItems(finidhItem, flag);
        rawStockItemVector.clear();
        for (String str : rawStockItemMap.keySet()) {
            rawStockItemVector.add(str);
        }

        Collections.sort(
                rawStockItemVector,
                new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        return lhs.compareToIgnoreCase(rhs);
                    }
                });

        rawStockItemMap = Util.getSmallCaseMap(rawStockItemMap);
    }

    private void bindTOGUI() throws ParseException, SQLException, Exception {
        bindDTOtoGUI();
    }

    private void bindDTOtoGUI() throws Exception {
        addRawMaterialDTO = new AddRawMaterialDTO();
        dateChooser.setDate((java.util.Date) Constants.DATE_FORMATER.parse(addRawMaterialDTO.getDate().trim()));
        jTextFieldQuantity.setText("");
        rawStockItemTableModel.setRowCount(0);
    }

    private void setFinishModel(DefaultComboBoxModel mdl, String str) {
        comboBoxFinishItem.setModel(mdl);
        comboBoxFinishItem.setSelectedIndex(-1);
        comboBoxFinishItem.showPopup();
        tfFinishItemAccountText.setText(str);
    }

    private void setRawModel(DefaultComboBoxModel mdl, String str) {
        comboBoxRawMaterial.setModel(mdl);
        comboBoxRawMaterial.setSelectedIndex(-1);
        comboBoxRawMaterial.showPopup();
        tfRawStockItemText.setText(str);
    }

    public void partiadd() {
        if (validateStockitems()) {
            if (!jTextFieldQuantity.getText().toString().trim().equalsIgnoreCase("")) {
                Double a = Double.parseDouble(jTextFieldQuantity.getText().toString());
                if (jTextFieldQuantity.getText().equals("") || a.equals(0.0)) {
                    JOptionPane.showMessageDialog(this, "Enter Correct Value of Quantity");
                    jTextFieldQuantity.requestFocus();
                } else {
                    if (jtableRequireMaterial.getRowCount() > updateItemIfPresentInJTable()) {
                        System.out.println("Update table value For the ADD Functoin>>>>>>>>>");
                        updatetable(updateItemIfPresentInJTable());
                    } else {
                        System.out.println("ADD table value For the ADD Functoin>>>>>>>>>");
                        addDatatoTable();
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select Correct STOCKITEM Name");
            tfRawStockItemText.requestFocus();
            tfRawStockItemText.setText("");
        }
    }

    private void updatetable(int j) {
        temp = j;
        jtableRequireMaterial.setValueAt(comboBoxRawMaterial.getSelectedItem(), temp, 1);
        jtableRequireMaterial.setValueAt(jTextFieldQuantity.getText(), temp, 2);
        rawStockItemInTableMap.put(comboBoxRawMaterial.getSelectedItem().toString(), jTextFieldQuantity.getText());
        jTextFieldQuantity.setText("");
        btnAdd.setText("ADD");
        comboBoxRawMaterial.requestFocus();
        temp = 1000;
    }

    private void addDatatoTable() {
        if (temp == 1000) //Normal Addition
        {
            int row = jtableRequireMaterial.getRowCount();
            rawStockItemTableModel.setRowCount(row + 1);
            jtableRequireMaterial.setValueAt(row + 1, row, 0);
            jtableRequireMaterial.setValueAt(comboBoxRawMaterial.getSelectedItem(), row, 1);
            jtableRequireMaterial.setValueAt(jTextFieldQuantity.getText(), row, 2);
            rawStockItemInTableMap.put(comboBoxRawMaterial.getSelectedItem().toString(), jTextFieldQuantity.getText());
            jTextFieldQuantity.setText("");
            tfRawStockItemText.setText("");
            comboBoxRawMaterial.requestFocus();
        }
    }

    private int updateItemIfPresentInJTable() {
        int i = 0;
        int j = 0;
        int returntype = 10000000;
        while (i < rawStockItemInTableMap.size()) {
            while (j < jtableRequireMaterial.getRowCount()) {
                if (tfRawStockItemText.getText().equalsIgnoreCase(jtableRequireMaterial.getValueAt(j, 1).toString())) {
                    returntype = j;
                }
                j++;
            }
            i++;
        }
        return returntype;
    }

    private Boolean validateStockitems() {
        int i = 0;
        Boolean returntype = true;
//        while (i == rawItemsMap.size() || i < rawItemsMap.size()) {
//            if (rawItemsMap.get(tfRawStockItemText.getText().trim()) == null) {
//                returntype = false;
//            }
//            i++;
//        }

        if (rawStockItemInTableMap != null && !rawStockItemInTableMap.isEmpty()
                && !btnAdd.getText().toString().trim().equalsIgnoreCase("Alter")
                && rawStockItemInTableMap.containsKey(tfRawStockItemText.getText().toString())) {
            returntype = false;
        }
        return returntype;
    }

    private void functionDeleteKeyPressed(int rowSelected) {
        JOptionPane optionPane = new JOptionPane("Do you want to Delete  " + jtableRequireMaterial.getValueAt(rowSelected, 1).toString() + " ?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
        JDialog dialog = optionPane.createDialog("Delete");
        Set<AWTKeyStroke> focusTraversalKeys = new HashSet<AWTKeyStroke>(dialog.getFocusTraversalKeys(0));
        focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.VK_UNDEFINED));
        focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_LEFT, KeyEvent.VK_UNDEFINED));
        dialog.setFocusTraversalKeys(0, focusTraversalKeys);
        dialog.setVisible(true);
        dialog.dispose();

        int option = (Integer) optionPane.getValue();

        if (option == 0) {
            deleteRowFromJTableRaw(rowSelected);
            if (jtableRequireMaterial.getRowCount() == 0) {
                tfRawStockItemText.requestFocus();
            } else {
                jtableRequireMaterial.setRowSelectionInterval(0, 0); //select first row of jtableRequireMaterial
            }
        } else {
            jtableRequireMaterial.setRowSelectionInterval(rowSelected, rowSelected);
        }
    }

    private void deleteRowFromJTableRaw(int rowselected) {
        int i = 0;
//        partiTableModel.setColumnCount(3);
        if (!(rawStockItemTableModel.getValueAt(rowselected, 1).toString()).equalsIgnoreCase("")) {
            rawStockItemInTableMap.remove(jtableRequireMaterial.getValueAt(rowselected, 1).toString());
            rawStockItemTableModel.removeRow(rowselected);
            while (jtableRequireMaterial.getRowCount() > i) {
                jtableRequireMaterial.setValueAt(i + 1, i, 0);
                i++;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Cant Delete the Row");
        }
        temp = 1000;
        /* 
         * Set Width of First Column of jtableRequireMaterial
         */
//        setWidthOfFirstcolumn(jtableRequireMaterial);
    }

    private Boolean validateFinishItem() {
        int i = 0;
        Boolean returntype = true;
        System.out.println("Map value ----------- "+finishStockItemForComboMap.entrySet());
        while (i < finishStockItemForComboMap.size()) {
            if (finishStockItemForComboMap.get(tfFinishItemAccountText.getText().toLowerCase()) == null) {
                returntype = false;
            }
            i++;
        }
        return returntype;
    }

    private AddRawMaterialDTO bindGUItoDTO() {
        int i = 0;
        Long prod_def_id = 0l;
        // if it is editable
        if (isEdit) {
            prod_def_id = addRawMaterialDTO.getListStockItemDTO().get(0).getProduction_def_id();
        }
        AddRawMaterialDTO AddRawMaterialDTOToReturn = new AddRawMaterialDTO();
        List<gen.account.StockItemFormation.StockItemDTO> newRawStockItemDTOList = new ArrayList<gen.account.StockItemFormation.StockItemDTO>();
        while (i < jtableRequireMaterial.getRowCount()) {
            gen.account.StockItemFormation.StockItemDTO newRawStockItemDTO = new gen.account.StockItemFormation.StockItemDTO();
            if (!(jtableRequireMaterial.getValueAt(i, 0).toString()).equalsIgnoreCase("")) {
                newRawStockItemDTO.setName(jtableRequireMaterial.getValueAt(i, 1).toString());
                newRawStockItemDTO.setID(Long.parseLong(finishStockItemForComboMap.get(jtableRequireMaterial.getValueAt(i, 1).toString().toLowerCase().trim())));
                newRawStockItemDTO.setQuantity(Double.parseDouble(jtableRequireMaterial.getValueAt(i, 2).toString().trim()));
                newRawStockItemDTO.setProduction_def_id(prod_def_id);
                System.out.println("setting value to DTO ---------");
            }
            i++;
            newRawStockItemDTOList.add(newRawStockItemDTO);
        }
        AddRawMaterialDTOToReturn.setListStockItemDTO(newRawStockItemDTOList);
        AddRawMaterialDTOToReturn.setFinishStockItem(finishStockItemForComboMap.get(tfFinishItemAccountText.getText().trim().toLowerCase()));
        AddRawMaterialDTOToReturn.setDate(Constants.simpleDateFormatDatabaseWithDash.format(dateChooser.getDate()).toString().trim());

        return AddRawMaterialDTOToReturn;
    }

    private void submit() throws SQLException, Exception {
        upsert();
    }

    private void upsert() throws SQLException, Exception {
        List<AddRawMaterialDTO> addRawMaterialDTOList = new ArrayList<AddRawMaterialDTO>();
        addRawMaterialDTOList.add(addRawMaterialDTO);
        AddRawMaterialDAO.upsert(addRawMaterialDTOList, isEdit);
        JOptionPane.showMessageDialog(this, "Raw Material is added!!");
        initilize();
        comboBoxRawMaterial.hidePopup();
        comboBoxFinishItem.setSelectedItem("");
        comboBoxRawMaterial.setSelectedItem("");
        comboBoxFinishItem.requestFocus();
    }

    private void callTableByFinishItemChange() {
        try {

            String finishItem = finishStockItemForComboMap.get(tfFinishItemAccountText.getText().toLowerCase().trim());
            String date = Constants.simpleDateFormatDatabaseWithDash.format(dateChooser.getDate()).toString().trim();

//            addRawMaterialDTO = gen.production.finisheditemdefination.AddRawMaterialDAO.getAllRawStockItemsInfo(finishItem, date, null);
            List<gen.account.StockItemFormation.StockItemDTO> stockItemDTOList = new ArrayList<gen.account.StockItemFormation.StockItemDTO>();
            stockItemDTOList = gen.feature.production.finisheditemdefination.AddRawMaterialDAO.getAllRawStockItemsInfo(finishItem, date, null);

            addRawMaterialDTO.setListStockItemDTO(stockItemDTOList);

            // if Finish Item Present then change date to  prevois setted date 
            if (stockItemDTOList.size() > 0) {
                dateChooser.setDate((java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(addRawMaterialDTO.getListStockItemDTO().get(0).getStockitem_Date()));
            } else {

                String date1 = tfDatePicker.getText().trim();
                System.out.println("DAte111111111111111111111111      " + date1);

//                if (!finishStockItemForCheckMap.containsKey(tfFinishItemAccountText.getText().trim().toLowerCase())) {
//                    dateChooser.setDate((java.util.Date) Constants.DATE_FORMATER.parse(addRawMaterialDTO.getDate().trim()));
//                } else {
                    tfDatePicker.setText(date1);
//                }
            }
            addRawMaterialDTO.setFinishStockItem(tfFinishItemAccountText.getText().toLowerCase().trim());
            addRawMaterialDTO.setDate(Constants.simpleDateFormatDatabaseWithDash.format(dateChooser.getDate()).toString().trim());

            rawStockItemTableModel.setRowCount(0);
            // if no Finishitem is present but after setted date so 
            // first make it false then if isEdit = true if list is exits.
            isEdit = false;
            if (addRawMaterialDTO != null) {
                for (gen.account.StockItemFormation.StockItemDTO rawStockItemDTO : addRawMaterialDTO.getListStockItemDTO()) {
                    rawStockItemTableModel.setRowCount(rawStockItemTableModel.getRowCount() + 1);
                    rawStockItemTableModel.setValueAt(rawStockItemTableModel.getRowCount(), rawStockItemTableModel.getRowCount() - 1, 0);
                    rawStockItemInTableMap.put(rawStockItemDTO.getName(), rawStockItemDTO.getQuantity().toString());
                    rawStockItemTableModel.setValueAt(rawStockItemDTO.getName(), rawStockItemTableModel.getRowCount() - 1, 1);
                    rawStockItemTableModel.setValueAt(rawStockItemDTO.getQuantity(), rawStockItemTableModel.getRowCount() - 1, 2);
                    isEdit = true;
                }
            }
            tfRawStockItemText.setText("");
            jTextFieldQuantity.setText("0.0");
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(AddFinishMaterialForm.class.getName()).log(Level.SEVERE, null, ex);
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
        if (currentFocusValue > 4) {
            currentFocusValue = 4;
        }

        if (currentFocusValue == 0) {
            tfFinishItemAccountText.requestFocus();
        } else if (currentFocusValue == 1) {
            tfDatePicker.requestFocus();
        } else if (currentFocusValue == 2) {
            tfRawStockItemText.requestFocus();
        } else if (currentFocusValue == 3) {
            jTextFieldQuantity.requestFocus();
        }
    }
}
