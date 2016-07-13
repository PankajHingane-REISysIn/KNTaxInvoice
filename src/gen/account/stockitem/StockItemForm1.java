/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.account.stockitem;

import gen.account.stockgroup.StockGroupForm;
import gen.account.unitofmeasure.UnitOfMeasureForm;
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
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
public class StockItemForm1 extends javax.swing.JInternalFrame implements
        java.awt.event.ActionListener {

    private List<StockItemDTO> stockItemDTOsNameList;
    private StockItemDTO stockItemDTO = new StockItemDTO();
    private StockItemDTO editModeUOMDTO = new StockItemDTO();
    private Boolean isEdit = false;
    Integer selectedRow = 0;
    int jTextFieldRateResult = 0;
    int jTextFieldOpeningBalanceResult = 0;
    int jTextFieldLengthResult = 0;
    int jTextFieldWidthResult = 0;
    int jTextFieldThicknessResult = 0;
    private int currentFocusValue = 0;
    private Map<String, String> stockGroupDTONameList;
    private Map<String, String> stockItemNameList = new HashMap<String, String>();
    private Vector<String> StockGroupVector = new Vector<String>();
    private Vector<String> UnitOfMeasureVector = new Vector<String>();
    private Map<String, String> stockGroupNameMap;
    private Map<String, String> bindToGUIStockGroupNameMap;
    private Map<String, String> unitOfMeasureNameMap;
    private Map<String, String> bindToGUIUnitOfMeasureNameMap;
    private boolean hide_flag = false;
    String editModeStockItemName = "";

    public StockItemForm1() {
        try {
            // this.setClosable(true);
            initComponents();
            initilize();
            initStockGroup();
            initUOM();
            setClosable(true);
            // bindToGUIStockGroupNameMap =
            // StockItemDAO.loadStockGroupIDToNameMap();
            // bindToGUIUnitOfMeasureNameMap =
            // StockItemDAO.loadUnitOfMeasureIDToNameMap();
        } catch (Exception ex) {
            Logger.getLogger(StockItemForm1.class.getName()).log(Level.SEVERE,
                    null, ex);
            JOptionPane.showMessageDialog(StockItemForm1.this, ex.getMessage());
        }
    }

    private void initComponents() throws PropertyVetoException {
        setSelected(true);
        setResizable(true);
        setTitle("Stock Item Information Details");
        setClosable(true);
        setBounds(100, 100, 791, 539);

        jMainPanel = new JPanel();
        getContentPane().add(jMainPanel, BorderLayout.CENTER);

        JPanel availableStockItemsPanel = new JPanel();
        availableStockItemsPanel.setBorder(new TitledBorder(null,
                "Available Stock Items", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));

        JPanel stockItemInformationPanel = new JPanel();
        stockItemInformationPanel.setBorder(new TitledBorder(null,
                "Stock Item Information", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));

        JPanel controlsPanel = new JPanel();
        controlsPanel.setBorder(new TitledBorder(null, "",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GroupLayout gl_jMainPanel = new GroupLayout(jMainPanel);
        gl_jMainPanel
                .setHorizontalGroup(gl_jMainPanel
                .createParallelGroup(Alignment.LEADING)
                .addGroup(
                gl_jMainPanel
                .createSequentialGroup()
                .addGroup(
                gl_jMainPanel
                .createParallelGroup(
                Alignment.LEADING,
                false)
                .addGroup(
                gl_jMainPanel
                .createSequentialGroup()
                .addContainerGap()
                .addComponent(
                controlsPanel,
                GroupLayout.DEFAULT_SIZE,
                GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE))
                .addGroup(
                gl_jMainPanel
                .createSequentialGroup()
                .addGap(14)
                .addComponent(
                stockItemInformationPanel,
                GroupLayout.PREFERRED_SIZE,
                512,
                GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(
                ComponentPlacement.RELATED)
                .addComponent(availableStockItemsPanel,
                GroupLayout.DEFAULT_SIZE, 243,
                Short.MAX_VALUE)));
        gl_jMainPanel
                .setVerticalGroup(gl_jMainPanel
                .createParallelGroup(Alignment.TRAILING)
                .addGroup(
                gl_jMainPanel
                .createSequentialGroup()
                .addContainerGap()
                .addGroup(
                gl_jMainPanel
                .createParallelGroup(
                Alignment.BASELINE)
                .addComponent(
                stockItemInformationPanel,
                GroupLayout.DEFAULT_SIZE,
                429,
                Short.MAX_VALUE)
                .addComponent(
                availableStockItemsPanel,
                GroupLayout.DEFAULT_SIZE,
                429,
                Short.MAX_VALUE))
                .addPreferredGap(
                ComponentPlacement.RELATED)
                .addComponent(controlsPanel,
                GroupLayout.PREFERRED_SIZE, 54,
                GroupLayout.PREFERRED_SIZE)
                .addGap(10)));
        stockItemInformationPanel
                .setLayout(new MigLayout("", "[100px:100px:100px,grow,fill][25px:25px:25px,grow,shrink 50,fill][225px:225px:225px,grow,fill][grow]", "[][][][][][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][]"));

        labelName = new JLabel("Name");
        stockItemInformationPanel.add(labelName, "cell 0 5,alignx trailing");

        jTextFieldName = new JTextField();
        stockItemInformationPanel.add(jTextFieldName, "cell 2 5,growx");
        jTextFieldName.setColumns(10);

        labelAlias = new JLabel("Alias");
        stockItemInformationPanel.add(labelAlias, "cell 0 7");

        jTextFieldAlias = new JTextField();
        stockItemInformationPanel.add(jTextFieldAlias, "cell 2 7,growx");
        jTextFieldAlias.setColumns(10);

        labelUnder = new JLabel("Under");
        stockItemInformationPanel.add(labelUnder, "cell 0 9");

        jComboBoxUnder = new JComboBox();
        jComboBoxUnder.setPrototypeDisplayValue("xxxxxx");
        tfUnderField = (JTextField) jComboBoxUnder.getEditor()
                .getEditorComponent();
        stockItemInformationPanel.add(jComboBoxUnder, "cell 2 9,growx");

        jbuttonCreateGroup = new JButton("Create Group");
        jbuttonCreateGroup.setMnemonic('O');
        stockItemInformationPanel.add(jbuttonCreateGroup, "cell 3 9");

        JLabel lblLength = new JLabel("Length");
        stockItemInformationPanel.add(lblLength, "cell 0 11");

        jTextFieldLength = new JTextField();
        stockItemInformationPanel.add(jTextFieldLength, "cell 2 11,growx");
        jTextFieldLength.setColumns(10);

        JLabel lblWidth = new JLabel("Width");
        stockItemInformationPanel.add(lblWidth, "cell 0 13");

        jTextFieldWidth = new JTextField();
        stockItemInformationPanel.add(jTextFieldWidth, "cell 2 13,growx");
        jTextFieldWidth.setColumns(10);

        jComboBoxUnit = new JComboBox();
        jComboBoxUnit.setPrototypeDisplayValue("xxxxxx");
        tfUnitField = (JTextField) jComboBoxUnit.getEditor()
                .getEditorComponent();

        JLabel lblThickness = new JLabel("Thickness");
        stockItemInformationPanel.add(lblThickness, "cell 0 15");

        jTextFieldThickness = new JTextField();
        stockItemInformationPanel.add(jTextFieldThickness, "cell 2 15,growx");
        jTextFieldThickness.setColumns(10);

        labelUnit = new JLabel("Measurement Unit");
        stockItemInformationPanel.add(labelUnit, "cell 0 17");
        stockItemInformationPanel.add(jComboBoxUnit, "cell 2 17,growx");

        jButtonAddUOM = new JButton("Create Unit    ");
        jButtonAddUOM.setMnemonic('E');
        stockItemInformationPanel.add(jButtonAddUOM, "cell 3 17");

        labelRate = new JLabel("Rate");
        stockItemInformationPanel.add(labelRate, "cell 0 19");

        jTextFieldRate = new JTextField();
        jTextFieldRate.setText("0.0");
        stockItemInformationPanel.add(jTextFieldRate, "cell 2 19,growx");
        jTextFieldRate.setTransferHandler(null);
        jTextFieldRate.setColumns(10);

        labelOpeningBalance = new JLabel("Opening Balance");
        stockItemInformationPanel.add(labelOpeningBalance, "cell 0 21");

        jTextFieldOpeningBalance = new JTextField();
        jTextFieldOpeningBalance.setText("0.0");
        stockItemInformationPanel.add(jTextFieldOpeningBalance,
                "cell 2 21,growx");
        jTextFieldOpeningBalance.setTransferHandler(null);
        jTextFieldOpeningBalance.setColumns(10);
        controlsPanel.setLayout(new MigLayout("wrap",
                "[grow,left][grow,center][grow,right][grow,fill,center]",
                "[]unrel[][]"));

        jButtonBack = new JButton("Back");
        jButtonBack.setMnemonic('B');
        controlsPanel.add(jButtonBack, new CC().spanX(5).split(5).tag("other")
                .growX());

        jButtonNew = new JButton("New");
        jButtonNew.setMnemonic('N');
        controlsPanel.add(jButtonNew, new CC().tag("other").growX());

        jButtonDelete = new JButton("Delete");
        jButtonDelete.setMnemonic('T');
        controlsPanel.add(jButtonDelete, new CC().tag("other").growX());

        jButtonSubmit = new JButton("Submit");
        jButtonSubmit.setMnemonic('S');
        controlsPanel.add(jButtonSubmit, new CC().tag("other").growX());
        availableStockItemsPanel.setLayout(new MigLayout("", "[][][grow]",
                "[][][grow]"));

        labelSearch = new JLabel("Search");
        availableStockItemsPanel.add(labelSearch, "cell 1 0,alignx trailing");

        jTextFieldSearch = new JTextField();
        availableStockItemsPanel.add(jTextFieldSearch, "cell 0 1 3 1,growx");
        jTextFieldSearch.setColumns(10);

        JScrollPane pane1 = new JScrollPane();
        availableStockItemsPanel.add(pane1, "cell 0 2 3 1,grow");

        jTableStockItemList = new JTable();
        jTableStockItemList.setBorder(new LineBorder(new Color(0, 0, 0)));
        jTableStockItemList
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        String col1[] = {Label.STOCK_ITEM_NAME};
        String data1[][] = {{"", ""}};

        jTableStockItemListModel = new DefaultTableModel(data1, col1) {
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

        jTableStockItemList = new JTable() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;
        };

		jTableStockItemList.setModel(jTableStockItemListModel);
        JTableHeader header1 = jTableStockItemList.getTableHeader();
        jTableStockItemList.getTableHeader().setReorderingAllowed(false);
        header1.setBackground(Color.yellow);
        header1.setFont(font);
        jTableStockItemListModel = (DefaultTableModel) jTableStockItemList
                .getModel();
        jTableStockItemListModel.setRowCount(0);
        jTableStockItemListModel.setColumnCount(2);
        jTableStockItemList.setBorder(javax.swing.BorderFactory
                .createLineBorder(new java.awt.Color(0, 0, 0)));
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
                jTableStockItemListModel);
        jTableStockItemList.setRowSorter(sorter);
        jTableStockItemList.getColumnModel().getColumn(1).setPreferredWidth(0);
        jTableStockItemList.getColumnModel().getColumn(1).setMinWidth(0);
        jTableStockItemList.getColumnModel().getColumn(1).setMaxWidth(0);
        pane1.setViewportView(jTableStockItemList);
        jMainPanel.setLayout(gl_jMainPanel);

        initialiseActionListeners();
    }

    private void initialiseActionListeners() {
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(
                Util.getImageIconPath())));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            @Override
            public void internalFrameActivated(
                    javax.swing.event.InternalFrameEvent evt) {

                try {
                    formInternalFrameActivated(evt);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemForm1.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockItemForm1.this,
                            ex.getMessage());
                }

            }

            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                try {
                    // throw new
                    // UnsupportedOperationException("Not supported yet.");
                    formInternalFrameOpened(e);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(StockItemForm1.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockItemForm1.this,
                            ex.getMessage());
                }
            }

            @Override
            public void internalFrameClosing(
                    javax.swing.event.InternalFrameEvent evt) {

                try {
                    formInternalFrameClosing(evt);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(SaleForm.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                // throw new
                // UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameIconified(InternalFrameEvent e) {
                // throw new
                // UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameDeiconified(InternalFrameEvent e) {
                // throw new
                // UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {
                // throw new
                // UnsupportedOperationException("Not supported yet.");
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
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(StockItemForm1.class.getName()).log(
                            Level.SEVERE, null, ex);
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
        tfUnderField = (JTextField) jComboBoxUnder.getEditor()
                .getEditorComponent();
        tfUnderField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String text = tfUnderField.getText();
                        if (text.length() == 0) {
                            jComboBoxUnder.hidePopup();
                            setStockGroupModel(new DefaultComboBoxModel(
                                    StockGroupVector), "");
                        } else {
                            DefaultComboBoxModel m = Util.getSuggestedModel(
                                    StockGroupVector, text);
                            if (m.getSize() == 0 || hide_flag) {
                                jComboBoxUnder.hidePopup();
                                hide_flag = false;
                            } else {
                                // setAccountLedgetModel(m, text);
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
                        if (event.getKeyCode() == KeyEvent.VK_ENTER
                                || event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            // setFocus(event);
                            tfUnderFieldKeyPressed(event);
                        }
                    }
                });
            }
        });

        tfUnitField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfUnitFieldFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfUnitFieldFocusLost(evt);
                jComboBoxUnit.hidePopup();
            }
        });

        jComboBoxUnit.setEditable(true);
        tfUnitField = (JTextField) jComboBoxUnit.getEditor()
                .getEditorComponent();
        tfUnitField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String text = tfUnitField.getText();
                        if (text.length() == 0) {
                            jComboBoxUnit.hidePopup();
                            setUOMModel(new DefaultComboBoxModel(
                                    UnitOfMeasureVector), "");
                        } else {
                            DefaultComboBoxModel m = Util.getSuggestedModel(
                                    UnitOfMeasureVector, text);
                            if (m.getSize() == 0 || hide_flag) {
                                jComboBoxUnit.hidePopup();
                                hide_flag = false;
                            } else {
                                // setAccountLedgetModel(m, text);
                                setUOMModel(m, text);
                                jComboBoxUnit.showPopup();
                            }
                        }
                    }
                });

            }
        });
        tfUnitField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent event) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (event.getKeyCode() == KeyEvent.VK_ENTER
                                || event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            // setFocus(event);
                            tfUnitFieldKeyPressed(event);
                        }
                    }
                });
            }
        });

        jTextFieldLength.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent arg0) {
                jTextFieldLengthFocusGained(arg0);
            }

            @Override
            public void focusLost(FocusEvent e) {
                jTextFieldLengthFocusLost(e);
            }
        });
        jTextFieldLength.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                jTextFieldLengthKeyPressed(arg0);
            }

            @Override
            public void keyTyped(KeyEvent e) {
                jTextFieldLengthKeyTyped(e);
            }
        });

        jTextFieldWidth.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent arg0) {
                jTextFieldWidthFocusGained(arg0);
            }

            @Override
            public void focusLost(FocusEvent e) {
                jTextFieldWidthFocusLost(e);
            }
        });
        jTextFieldWidth.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                jTextFieldWidthKeyPressed(arg0);
            }

            @Override
            public void keyTyped(KeyEvent e) {
                jTextFieldWidthKeyTyped(e);
            }
        });

        jTextFieldThickness.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent arg0) {
                jTextFieldThicknessFocusGained(arg0);
            }

            @Override
            public void focusLost(FocusEvent e) {
                jTextFieldThicknessFocusLost(e);
            }
        });
        jTextFieldThickness.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                jTextFieldThicknessKeyPressed(arg0);
            }

            @Override
            public void keyTyped(KeyEvent e) {
                jTextFieldThicknessKeyTyped(e);
            }
        });

        jTextFieldRate.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldRateFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldRateFocusLost(evt);
            }
        });
        jTextFieldRate.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldRateKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jTextFieldRate);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(StockItemForm1.this,
                            ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
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
                    JOptionPane.showMessageDialog(StockItemForm1.this,
                            ex.getMessage());
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
                    Logger.getLogger(SaleForm.class.getName()).log(
                            Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(StockItemForm1.this,
                            ex.getMessage());
                }
            }
        });

        jTableStockItemList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jTableStockGroupListMouseClicked(evt);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemForm1.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockItemForm1.this,
                            ex.getMessage());
                }
            }

            public void mousePressed(MouseEvent me) {
            }

            public void mouseReleased(MouseEvent me) {
            }
        });

        jbuttonCreateGroup.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jbuttonCreateGroupActionPerformed(evt);
            }
        });

        jButtonAddUOM.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonAddUOMActionPerformed(evt);
            }
        });

        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    jButtonBackActionPerformed(evt);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(StockItemForm1.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockItemForm1.this,
                            ex.getMessage());
                }
            }
        });

        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    jButtonNewActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemForm1.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockItemForm1.this,
                            ex.getMessage());
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
                    JOptionPane.showMessageDialog(StockItemForm1.this,
                            e.getMessage());
                }
            }
        });

        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    jButtonDeleteActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemForm1.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockItemForm1.this,
                            ex.getMessage());
                }
            }
        });

        jButtonAddUOM.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonAddUOMKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        jButtonBack.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                try {
                    jButtonBackKeyPressed(evt);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(StockItemForm1.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockItemForm1.this,
                            ex.getMessage());
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
                    Logger.getLogger(StockItemForm1.class.getName()).log(
                            Level.SEVERE, null, ex);
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

    private void jTextFieldNameKeyPressed(java.awt.event.KeyEvent evt)
            throws PropertyVetoException {
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
            DefaultComboBoxModel m = Util.getSuggestedModel(StockGroupVector,
                    text);
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

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void tfUnitFieldFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        tfUnitField.selectAll();
        currentFocusValue = 6;
        String text = tfUnitField.getText();
        if (text.length() == 0) {
            jComboBoxUnit.hidePopup();
            setUOMModel(new DefaultComboBoxModel(UnitOfMeasureVector), "");
        } else {
            DefaultComboBoxModel m = Util.getSuggestedModel(
                    UnitOfMeasureVector, text);
            if (m.getSize() == 0 || hide_flag) {
                jComboBoxUnit.hidePopup();
                hide_flag = false;
            } else {
                setUOMModel(m, text);
                jComboBoxUnit.showPopup();
            }

        }
    }

    private void tfUnitFieldFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void tfUnitFieldKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldLengthFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        jTextFieldLength.selectAll();
        currentFocusValue = 3;
        if (jTextFieldLength.getText().toString().trim().equals("" + 0D)) {
            jTextFieldLength.setText("");
        }
    }

    private void jTextFieldLengthFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        if (jTextFieldLength.getText().toString().trim().equalsIgnoreCase("")
                || jTextFieldLength.getText().toString().trim().equals(".")) {
            jTextFieldLength.setText("" + 0D);
        }
    }

    private void jTextFieldLengthKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldLengthKeyTyped(java.awt.event.KeyEvent evt) {
        try {
            // TODO add your handling code here:
            Util.filterCharacter(evt, jTextFieldLength);
        } catch (Exception ex) {
            Logger.getLogger(StockItemForm1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jTextFieldWidthFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        jTextFieldWidth.selectAll();
        currentFocusValue = 4;
        if (jTextFieldWidth.getText().toString().trim().equals("" + 0D)) {
            jTextFieldWidth.setText("");
        }
    }

    private void jTextFieldWidthFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        if (jTextFieldWidth.getText().toString().trim().equalsIgnoreCase("")
                || jTextFieldWidth.getText().toString().trim().equals(".")) {
            jTextFieldWidth.setText("" + 0D);
        }
    }

    private void jTextFieldWidthKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldWidthKeyTyped(java.awt.event.KeyEvent evt) {
        try {
            // TODO add your handling code here:
            Util.filterCharacter(evt, jTextFieldWidth);
        } catch (Exception ex) {
            Logger.getLogger(StockItemForm1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jTextFieldThicknessFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        jTextFieldThickness.selectAll();
        currentFocusValue = 5;
        if (jTextFieldThickness.getText().toString().trim().equals("" + 0D)) {
            jTextFieldThickness.setText("");
        }
    }

    private void jTextFieldThicknessFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        if (jTextFieldThickness.getText().toString().trim().equalsIgnoreCase("")
                || jTextFieldThickness.getText().toString().trim().equals(".")) {
            jTextFieldThickness.setText("" + 0D);
        }
    }

    private void jTextFieldThicknessKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldThicknessKeyTyped(java.awt.event.KeyEvent evt) {
        try {
            // TODO add your handling code here:
            Util.filterCharacter(evt, jTextFieldThickness);
        } catch (Exception ex) {
            Logger.getLogger(StockItemForm1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jTextFieldRateFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        jTextFieldRate.selectAll();
        currentFocusValue = 7;
        if (jTextFieldRate.getText().toString().trim().equals("" + 0D)) {
            jTextFieldRate.setText("");
        }
    }

    private void jTextFieldRateFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        if (jTextFieldRate.getText().toString().trim().equalsIgnoreCase("")
                || jTextFieldRate.getText().toString().trim().equals(".")) {
            jTextFieldRate.setText("" + 0D);
        }
    }

    private void jTextFieldRateKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldOpeningBalanceFocusGained(
            java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        jTextFieldOpeningBalance.selectAll();
        currentFocusValue = 8;
        if (jTextFieldOpeningBalance.getText().trim().equalsIgnoreCase("" + 0D)) {
            jTextFieldOpeningBalance.setText("");
        }
    }

    private void jTextFieldOpeningBalanceFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        if (jTextFieldOpeningBalance.getText().trim().equalsIgnoreCase("")
                || jTextFieldOpeningBalance.getText().toString().trim()
                .equals(".")) {
            jTextFieldOpeningBalance.setText("" + 0D);
        }
    }

    private void jTextFieldOpeningBalanceKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        if (code == KeyEvent.VK_ENTER) {
            jButtonSubmit.requestFocus();
        }
    }

    private void jButtonAddUOMKeyPressed(java.awt.event.KeyEvent evt) {
    }

    private void jbuttonCreateGroupActionPerformed(
            java.awt.event.ActionEvent evt) {

        Container container = SwingUtilities.getAncestorOfClass(
                JDesktopPane.class, (Component) evt.getSource());

        if (container != null) {
            JDesktopPane desktopPane = getDesktopPane();
            desktopPane.setLayout(null);
            StockGroupForm form = new StockGroupForm();
            desktopPane.add(form);
            Dimension desktopSize = getDesktopPane().getSize();
            Dimension jInternalFrameSize = form.getSize();
            int width = (desktopSize.width - jInternalFrameSize.width) / 2;
            int height = (desktopSize.height - jInternalFrameSize.height) / 2;
            form.setLocation(width, height);
            form.setVisible(true);
        }

    }

    private void jButtonAddUOMActionPerformed(java.awt.event.ActionEvent evt) {

        Container container = SwingUtilities.getAncestorOfClass(
                JDesktopPane.class, (Component) evt.getSource());

        if (container != null) {
            JDesktopPane desktopPane = getDesktopPane();
            desktopPane.setLayout(null);
            UnitOfMeasureForm form = new UnitOfMeasureForm();
            desktopPane.add(form);
            Dimension desktopSize = getDesktopPane().getSize();
            Dimension jInternalFrameSize = form.getSize();
            int width = (desktopSize.width - jInternalFrameSize.width) / 2;
            int height = (desktopSize.height - jInternalFrameSize.height) / 2;
            form.setLocation(width, height);
            form.setVisible(true);
        }

    }

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt)
            throws PropertyVetoException {

        MainClass.setstaticvar();
        MainClass m = new MainClass();
        m.menuselection(1);
        this.setClosed(true);
    }

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) throws ParseException, SQLException {
        jButtonDelete.setEnabled(false);
        newButton();
        clearFieldData();
        jTextFieldName.requestFocus();
    }

    private void jButtonSubmitActionPerformed(java.awt.event.ActionEvent evt)
            throws Exception {
        if (validateData()) {
            submit();
        }
    }

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt)
            throws SQLException, ParseException {
        // if (validateData()) {
        delete();
        // }
    }

    private void jTableStockGroupListMouseClicked(java.awt.event.MouseEvent evt)
            throws SQLException, ParseException {

        editModeStockItemName = jTableStockItemList.getValueAt(
                jTableStockItemList.getSelectedRow(), 0).toString();
        String id = jTableStockItemList.getValueAt(
                jTableStockItemList.getSelectedRow(), 1).toString();
        loadEditForm(id);
        jButtonDelete.setEnabled(true);
        isEdit = true;
        jTextFieldName.requestFocus();
    }

    private void jButtonBackKeyPressed(java.awt.event.KeyEvent evt)
            throws PropertyVetoException {
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {

            jButtonNew.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            jButtonBackActionPerformed(null);

        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            jTextFieldOpeningBalance.requestFocus();

        }
    }

    private void jButtonNewKeyPressed(java.awt.event.KeyEvent evt)
            throws ParseException, SQLException {
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

            jTextFieldOpeningBalance.requestFocus();

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
        // if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        //
        // jButtonSubmitActionPerformed(null);
        //
        // }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            jTextFieldOpeningBalance.requestFocus();

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
            jTextFieldOpeningBalance.requestFocus();
        }
    }

    private void jTextFieldSearchKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (jTableStockItemList.getRowCount() > 0) {
                jTableStockItemList.requestFocus();
                jTableStockItemList.changeSelection(0, 0, false, false);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (jTableStockItemList.getRowCount() > 0) {
                jTableStockItemList.requestFocus();
                jTableStockItemList.changeSelection(0, 0, false, false);
            }
        }
    }

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt)
            throws SQLException {

        List<StockItemDTO> stockGroupList = new ArrayList<StockItemDTO>();
        String text = jTextFieldSearch.getText().trim();

        for (StockItemDTO stockItemDTOEntity : stockItemDTOsNameList) {
            if (stockItemDTOEntity.getName().toLowerCase()
                    .matches("(.*)" + text.toLowerCase() + "(.*)")) {
                stockGroupList.add(stockItemDTOEntity);
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
    private JLabel labelUnit;
    private JLabel labelRate;
    private JLabel labelOpeningBalance;
    private JLabel labelSearch;
    private JComboBox jComboBoxUnder;
    private JComboBox jComboBoxUnit;
    private JTextField tfUnderField;
    private JTextField jTextFieldRate;
    private JTextField jTextFieldOpeningBalance;
    private JTextField jTextFieldSearch;
    private JTextField tfUnitField;
    private JButton jButtonAddUOM;
    private JButton jbuttonCreateGroup;
    private JButton jButtonSubmit;
    private JButton jButtonBack;
    private JButton jButtonNew;
    private JButton jButtonDelete;
    private JTable jTableStockItemList;
    private DefaultTableModel jTableStockItemListModel;
    private JTextField jTextFieldLength;
    private JTextField jTextFieldWidth;
    private JTextField jTextFieldThickness;

    private void initilize() throws SQLException, ParseException {
        bindTOGUI();
    }

    private void setStockGroupModel(DefaultComboBoxModel mdl, String str) {
        jComboBoxUnder.setModel(mdl);
        jComboBoxUnder.setSelectedIndex(-1);
        tfUnderField.setText(str);
    }

    private void setUOMModel(DefaultComboBoxModel mdl, String str) {
        jComboBoxUnit.setModel(mdl);
        jComboBoxUnit.setSelectedIndex(-1);
        tfUnitField.setText(str);
    }

    private void initStockGroup() throws SQLException {
        stockGroupNameMap = StockItemDAO.loadStockGroupNameToIDMap();// gen.database.connection.LedgerDAO.getLedgerFromGroupName(groups,
        // false);
        StockGroupVector.clear();
        for (String str : stockGroupNameMap.keySet()) {
            StockGroupVector.add(str);
            System.out.println("Names--->>>" + str);
        }

        Collections.sort(StockGroupVector, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareToIgnoreCase(rhs);
            }
        });

        stockGroupNameMap = Util.getSmallCaseMap(stockGroupNameMap);
    }

    private void initUOM() throws SQLException {
        unitOfMeasureNameMap = StockItemDAO.loadUnitOfMeasureNameToIDMap();// gen.database.connection.LedgerDAO.getLedgerFromGroupName(groups,
        // false);
        UnitOfMeasureVector.clear();
        for (String str : unitOfMeasureNameMap.keySet()) {
            UnitOfMeasureVector.add(str);
            System.out.println("Names Unit--->>" + str);
        }

        Collections.sort(UnitOfMeasureVector, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareToIgnoreCase(rhs);
            }
        });

        unitOfMeasureNameMap = Util.getSmallCaseMap(unitOfMeasureNameMap);
    }

    private void formInternalFrameClosing(
            javax.swing.event.InternalFrameEvent evt)
            throws PropertyVetoException {
        MainClass.setstaticvar();
        // getDesktopPane().setLayout(
        // new MigLayout("center panel",
        // "[100px:100px:1366px,grow,shrink 50,center]",
        // "[100px:100px:768px,grow,shrink 50,center]"));
        // this.dispose();
    }

    private void formInternalFrameActivated(
            javax.swing.event.InternalFrameEvent evt)
            throws PropertyVetoException, SQLException {
        System.out.println("Frame activated");
        bindToGUIUnitOfMeasureNameMap = StockItemDAO
                .loadUnitOfMeasureIDToNameMap();
        bindToGUIStockGroupNameMap = StockItemDAO.loadStockGroupIDToNameMap();
        jButtonDelete.setEnabled(false);
        initStockGroup();
        initUOM();
        initStockItemNameList();
        setFocus(null);
    }

    private void formInternalFrameOpened(
            javax.swing.event.InternalFrameEvent evt)
            throws PropertyVetoException {
        jButtonDelete.setEnabled(false);
    }

    private void newButton() throws ParseException, SQLException {
        stockItemDTO = new StockItemDTO();
        isEdit = false;
        bindTOGUI();
    }

    private void submit() throws SQLException, Exception {
        stockItemDTO = bindGUITODTO();

        if (!isEdit) {
            insertStockItem();
        } else {
            updateStockItem();
        }
        JOptionPane.showMessageDialog(this, Label.RECORD_SUBMITTED_SUCCESSFULLY);
        jTextFieldName.requestFocus();
        jButtonNewActionPerformed(null);
    }

    private void delete() throws SQLException, ParseException {
        List<StockItemDTO> stockItemDTOList = new ArrayList<StockItemDTO>();
        stockItemDTOList.add(stockItemDTO);
        if (StockItemDAO.deleteStockItem(stockItemDTOList)) {
            JOptionPane
                    .showMessageDialog(this,
                    "This Stock Group Can Not Be Deleted As It Is Used In Other Transactions");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Stock Item Deleted Successfully");
        }
        initStockItemNameList();
        jButtonNewActionPerformed(null);
    }

    private void clearFieldData() {

        jTextFieldName.setText("");
        jTextFieldAlias.setText("");
        tfUnderField.setText("");
        jComboBoxUnder.setSelectedItem("");
        jTextFieldLength.setText("");
        jTextFieldWidth.setText("");
        jTextFieldThickness.setText("");
        tfUnitField.setText("");
        jComboBoxUnit.setSelectedItem("");
        jTextFieldRate.setText("0.0");
        jTextFieldOpeningBalance.setText("0.0");

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
        } else if (currentFocusValue == 3) {
            jTextFieldLength.requestFocus();
        } else if (currentFocusValue == 4) {
            jTextFieldWidth.requestFocus();
        } else if (currentFocusValue == 5) {
            jTextFieldThickness.requestFocus();
        } else if (currentFocusValue == 6) {
            tfUnitField.requestFocus();
        } else if (currentFocusValue == 7) {
            jTextFieldRate.requestFocus();
        } else if (currentFocusValue == 8) {
            jTextFieldOpeningBalance.requestFocus();
        }
    }

    private Boolean validateData() throws Exception {
        Boolean flag = true;
        String ledger_name = jTextFieldName.getText().trim();
        Pattern p = Pattern.compile("[a-zA-Z0-9]");
        Matcher m = p.matcher(ledger_name);
        jTextFieldRateResult = new BigDecimal(jTextFieldRate.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
        jTextFieldOpeningBalanceResult = new BigDecimal(jTextFieldOpeningBalance.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
        jTextFieldLengthResult = new BigDecimal(jTextFieldLength.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
        jTextFieldWidthResult = new BigDecimal(jTextFieldWidth.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
        jTextFieldThicknessResult = new BigDecimal(jTextFieldThickness.getText().trim()).compareTo(Constants.jTextFieldAmountLength);

        if (jTextFieldName.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthMEDIUM) {
            flag = false;
            jTextFieldName.requestFocus();
            throw new Exception("Stock Item Name Exceeding " + Constants.jTextFieldCharacterLengthMEDIUM + " Character Limit");
        } else if (jTextFieldAlias.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthSMALL) {
            flag = false;
            jTextFieldAlias.requestFocus();
            throw new Exception("Stock Item Alias Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
        } else if (jTextFieldLength.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            flag = false;
            jTextFieldLength.requestFocus();
            throw new Exception("Stock Item Length only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldLengthResult == 1) {
            flag = false;
            jTextFieldLength.requestFocus();
            throw new Exception("Stock Item Length only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldWidth.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            flag = false;
            jTextFieldWidth.requestFocus();
            throw new Exception("Stock Item Width only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldWidthResult == 1) {
            flag = false;
            jTextFieldWidth.requestFocus();
            throw new Exception("Stock Item Width only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldThickness.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            flag = false;
            jTextFieldThickness.requestFocus();
            throw new Exception("Stock Item Thickness only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldThicknessResult == 1) {
            flag = false;
            jTextFieldThickness.requestFocus();
            throw new Exception("Stock Item Thickness only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldRate.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            flag = false;
            jTextFieldRate.requestFocus();
            throw new Exception("Stock Item Rate only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldRateResult == 1) {
            flag = false;
            jTextFieldRate.requestFocus();
            throw new Exception("Stock Item Rate only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldOpeningBalance.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            flag = false;
            jTextFieldOpeningBalance.requestFocus();
            throw new Exception("Opening Balance only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldOpeningBalanceResult == 1) {
            flag = false;
            jTextFieldOpeningBalance.requestFocus();
            throw new Exception("Opening Balance only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (isEdit) {
            if (!bindToGUIStockGroupNameMap.containsValue(tfUnderField.getText().trim())) {
                JOptionPane.showMessageDialog(this, "Enter valid value for under field");
                tfUnderField.requestFocus();
                flag = false;
            }
            if (!bindToGUIUnitOfMeasureNameMap.containsValue(tfUnitField.getText().trim())) {
                JOptionPane.showMessageDialog(this, "Enter valid value for unit field");
                tfUnitField.requestFocus();
                flag = false;
            }
            if (!jTextFieldName.getText().trim().equalsIgnoreCase(editModeStockItemName)) {
                if (stockItemNameList.containsKey(jTextFieldName.getText().trim())) {
                    JOptionPane.showMessageDialog(this, "Stock Item Already Exists");
                    jTextFieldName.requestFocus();
                    flag = false;
                }
            }
        } else if (jTextFieldName.getText().trim().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, Label.ENTER_VALID_STOCKITEM_NAME);
            jTextFieldName.requestFocus();
            flag = false;
        } else if (!m.find()) {
            JOptionPane.showMessageDialog(this, Label.ENTER_VALID_STOCKITEM_NAME);
            jTextFieldName.requestFocus();
            flag = false;
        } else if ((tfUnderField.getText().trim().equalsIgnoreCase(""))) {
            JOptionPane.showMessageDialog(this, Label.ENTER_VALUE_FOR_UNDER_FIELD);
            tfUnderField.requestFocus();
            flag = false;
        } else if ((tfUnitField.getText().trim().equalsIgnoreCase(""))) {
            JOptionPane.showMessageDialog(this, Label.ENTER_VALUE_FOR_UNIT_FIELD);
            tfUnitField.requestFocus();
            flag = false;
        } else if (stockItemNameList.containsKey(jTextFieldName.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Stock Item already exists");
            jTextFieldName.requestFocus();
            flag = false;
        } else if (!bindToGUIStockGroupNameMap.containsValue(tfUnderField.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Enter valid value for under field");
            tfUnderField.requestFocus();
            flag = false;
        } else if (!bindToGUIUnitOfMeasureNameMap.containsValue(tfUnitField.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Enter valid value for unit field");
            tfUnitField.requestFocus();
            flag = false;
        }
        return flag;
    }

    private StockItemDTO bindGUITODTO() {

        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        Calendar currentDate = Calendar.getInstance();
        String dateNow = f.format(currentDate.getTime());

        stockItemDTO.setStockitem_Date(dateNow);

        if (!jTextFieldName.getText().trim().equalsIgnoreCase("")) {
            stockItemDTO.setName(jTextFieldName.getText().trim());
        }
        // if (!jTextFieldAlias.getText().trim().equalsIgnoreCase("")) {
        stockItemDTO.setAlias(jTextFieldAlias.getText().trim());
        // }
        if (!tfUnderField.getText().trim().equalsIgnoreCase("")) {
            stockItemDTO.setUnder(stockGroupNameMap.get(tfUnderField.getText()
                    .trim().toLowerCase()));
        }
        if (!tfUnitField.getText().trim().equalsIgnoreCase("")) {
            stockItemDTO.setUnit(unitOfMeasureNameMap.get(tfUnitField.getText()
                    .trim().toLowerCase()));
        }
        if (!jTextFieldLength.getText().trim().equalsIgnoreCase("")) {
            stockItemDTO.setLength(Double.parseDouble(jTextFieldLength.getText()));
        }
        if (!jTextFieldWidth.getText().trim().equalsIgnoreCase("")) {
            stockItemDTO.setWidth(Double.parseDouble(jTextFieldWidth.getText()));
        }
        if (!jTextFieldThickness.getText().trim().equalsIgnoreCase("")) {
            stockItemDTO.setThkness(Double.parseDouble(jTextFieldThickness.getText()));
        }
        if (!jTextFieldRate.getText().trim().equalsIgnoreCase("")) {
            stockItemDTO.setRate(Double.parseDouble(jTextFieldRate.getText()));
        }
        if (!jTextFieldOpeningBalance.getText().trim().equalsIgnoreCase("")) {
            stockItemDTO.setOpeningBal(Double.parseDouble(jTextFieldOpeningBalance.getText()));
        }
        return stockItemDTO;
    }

    private void insertStockItem() throws SQLException, Exception {
        List<StockItemDTO> stockItemDTOList = new ArrayList<StockItemDTO>();
        stockItemDTOList.add(stockItemDTO);
        StockItemDAO.upsertStockItem(stockItemDTOList);
    }

    private void updateStockItem() throws SQLException, Exception {
        List<StockItemDTO> stockItemDTOList = new ArrayList<StockItemDTO>();
        stockItemDTO.setUnder(stockGroupNameMap.get(tfUnderField.getText()
                .trim().toLowerCase()));
        stockItemDTOList.add(stockItemDTO);
        StockItemDAO.upsertStockItem(stockItemDTOList);
    }

    private void bindDTOTojTableStockGroupList(List<StockItemDTO> receivedMap)
            throws SQLException {
        if (receivedMap != null && !receivedMap.isEmpty()) {
            jTableStockItemListModel.setRowCount(0);
            for (StockItemDTO stockItemDTO : receivedMap) {
                jTableStockItemListModel.setRowCount(jTableStockItemListModel
                        .getRowCount() + 1);
                jTableStockItemListModel.setValueAt(stockItemDTO.getName(),
                        jTableStockItemListModel.getRowCount() - 1, 0);
                jTableStockItemListModel.setValueAt(stockItemDTO.getID(),
                        jTableStockItemListModel.getRowCount() - 1, 1);
            }
        } else {
            jTableStockItemListModel.setRowCount(0);
        }
    }

    public void loadEditForm(String id) throws SQLException, ParseException {
        Set<String> idSet = new HashSet<String>();
        idSet.add(id);
        List<StockItemDTO> stockItemDTOList = StockItemDAO.getStockItemList(
                idSet, Constants.STOCK_ITEM_ID);
        if (stockItemDTOList != null && !stockItemDTOList.isEmpty()) {
            stockItemDTO = stockItemDTOList.get(0);
            editModeUOMDTO = stockItemDTO;
            isEdit = true;
            bindTOGUI();
            bindDTOtoGUI();
        }
    }

    private void bindTOGUI() throws ParseException, SQLException {
        if (!isEdit) {
            initStockItemNameList();
        }
    }

    private void bindDTOtoGUI() throws ParseException {
        jTextFieldName.setText(stockItemDTO.getName());
        jTextFieldAlias.setText(stockItemDTO.getAlias());
        tfUnderField.setText(bindToGUIStockGroupNameMap.get(stockItemDTO.getUnder()));
        jTextFieldLength.setText("" + Constants.DECIMAL_FORMAT.format(stockItemDTO.getLength()));
        jTextFieldWidth.setText("" + Constants.DECIMAL_FORMAT.format(stockItemDTO.getWidth()));
        jTextFieldThickness.setText("" + Constants.DECIMAL_FORMAT.format(stockItemDTO.getThkness()));
        tfUnitField.setText(bindToGUIUnitOfMeasureNameMap.get(stockItemDTO.getUnit()));
        jTextFieldRate.setText("" + Constants.DECIMAL_FORMAT.format(stockItemDTO.getRate()));
        jTextFieldOpeningBalance.setText("" + Constants.DECIMAL_FORMAT.format(stockItemDTO.getOpeningBal()));
    }

    private void initStockItemNameList() throws SQLException {
        Set<String> stockItemMap = new HashSet<String>();
        stockItemDTOsNameList = StockItemDAO.getStockItemList(stockItemMap,
                Constants.STOCK_ITEM_ID);
        bindDTOTojTableStockGroupList(stockItemDTOsNameList);
        for (StockItemDTO siDTO : stockItemDTOsNameList) {
            System.out.println("siDTO.getName()--->>" + siDTO.getName());
            System.out.println("siDTO.getID()--->>>>"
                    + siDTO.getID().toString());
            stockItemNameList.put(siDTO.getName(), siDTO.getID().toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
