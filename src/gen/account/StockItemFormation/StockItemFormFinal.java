package gen.account.StockItemFormation;

import gen.dto.Constants;
import gen.dto.Label;
import gen.dto.Util;
import gen.mainclass.MainClass;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.xswingx.PromptSupport;

@SuppressWarnings("serial")
public class StockItemFormFinal extends JInternalFrame {

    /**
     * Launch the application.
     */
    private Boolean flagCategory = false;
    private Boolean flagColor = false;
    private Boolean flagFinishType = false;
    private Boolean flagType = false;
    private Boolean isEdit = false;
    private Map<String, String> isToBeDisplayedCategory = new HashMap<String, String>();
    private Map<String, String> isToBeDisplayedFinishType = new HashMap<String, String>();
    String stockItemNameForDatabase = "";
    String stockItemNameForCatchingDuplicates = "";
    private StockItemDTO stockItemDTO = new StockItemDTO();
    List<StockItemDTO> stockItemDTOList = new ArrayList<StockItemDTO>();
    private int temp = 1000;
    private int indexForDeletion = 0;
    private static int jTableCategoryClick = 0;
    private static int jTableLengthClick = 0;
    private static int jTableWidthClick = 0;
    private static int jTableColorClick = 0;
    private static int jTableThicknessClick = 0;
    private static int jTableFinishTypeClick = 0;
    private static int jTableTypeClick = 0;
    private static int jTableUnderClick = 0;
    private static int jTableUnitClick = 0;
    private List<StockItemDTO> stockItemTransactionList = new ArrayList<StockItemDTO>();
    private List<StockItemCategoryDTO> stockItemCategoryDTOList = new ArrayList<StockItemCategoryDTO>();
    private List<StockItemDTO> stockItemLengthDTOList = new ArrayList<StockItemDTO>();
    private List<StockItemDTO> stockItemWidthDTOList = new ArrayList<StockItemDTO>();
    private List<StockItemDTO> stockItemThicknessDTOList = new ArrayList<StockItemDTO>();
    private List<StockItemColorDTO> stockItemColorDTOList = new ArrayList<StockItemColorDTO>();
    private List<StockItemFinishTypeDTO> stockItemFinishTypeDTOList = new ArrayList<StockItemFinishTypeDTO>();
    private List<StockItemTypeDTO> stockItemTypeDTOList = new ArrayList<StockItemTypeDTO>();
    private static int last_Number_In_TransactionList = 0;
    private static int last_Number_In_CategoryList = 0;
    private static int last_Number_In_LengthList = 0;
    private static int last_Number_In_WidthList = 0;
    private static int last_Number_In_ThicknessList = 0;
    private static int last_Number_In_ColorList = 0;
    private static int last_Number_In_FinishList = 0;
    private static int last_Number_In_TypeList = 0;
    private static int last_Number_In_UnderList = 0;
    private static int last_Number_In_UnitList = 0;
    private Vector<String> StockGroupVector = new Vector<String>();
    private Vector<String> UnitOfMeasureVector = new Vector<String>();
    private Map<String, String> stockGroupIDToNameMap = new HashMap<String, String>();
    private Map<String, String> stockGroupNameToIDMap = new HashMap<String, String>();
    private Map<String, String> UOMIDToNameMap = new HashMap<String, String>();
    private Map<String, String> UOMNameToIDMap = new HashMap<String, String>();
    private Map<String, String> categoryIDToNameMap = new HashMap<String, String>();
    private Map<String, String> categoryNameToIDMap = new HashMap<String, String>();
    private Map<String, String> colorIDToNameMap = new HashMap<String, String>();
    private Map<String, String> colorNameToIDMap = new HashMap<String, String>();
    private Map<String, String> finishTypeIDToNameMap = new HashMap<String, String>();
    private Map<String, String> finishTypeNameToIDMap = new HashMap<String, String>();
    private Map<String, String> typeIDToNameMap = new HashMap<String, String>();
    private Map<String, String> typeNameToIDMap = new HashMap<String, String>();
    private Map<Integer, Boolean> maintainCategorySelection = new HashMap<Integer, Boolean>();
    private Map<Integer, Boolean> maintainLengthSelection = new HashMap<Integer, Boolean>();
    private Map<Integer, Boolean> maintainWidthSelection = new HashMap<Integer, Boolean>();
    private Map<Integer, Boolean> maintainColorSelection = new HashMap<Integer, Boolean>();
    private Map<Integer, Boolean> maintainThicknessSelection = new HashMap<Integer, Boolean>();
    private Map<Integer, Boolean> maintainFinishTypeSelection = new HashMap<Integer, Boolean>();
    private Map<Integer, Boolean> maintainTypeSelection = new HashMap<Integer, Boolean>();
    private Map<Integer, Boolean> maintainUnderSelection = new HashMap<Integer, Boolean>();
    private Map<Integer, Boolean> maintainUnitSelection = new HashMap<Integer, Boolean>();

    /**
     * Create the frame.
     */
    public StockItemFormFinal() {
        try {
            initComponents();
            initilize();
            initStockItemTransactionList();
            initStockItemCategoryList();
            initStockItemLengthList();
            initStockItemWidthList();
            initStockItemThicknessList();
            initStockItemColorList();
            initStockItemFinishTypeList();
            initStockItemTypeList();
            initStockGroup();
            initUOM();
        } catch (Exception ex) {
            Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
        }
    }

    private void initComponents() throws Exception {

        setTitle("Stock Item Form");
        setClosable(true);
        setBounds(100, 100, 1332, 674);
        getContentPane()
                .setLayout(
                new MigLayout("", "[0px:70px:70px,grow,shrink 50,fill][0px:70px:70px,grow,shrink 50,fill][0px:70px:70px,grow,shrink 50,fill][0px:70px:70px,grow,shrink 50,fill][0px:70px:70px,grow,shrink 50,fill][0px:70px:70px,grow,shrink 50,fill][0px:105px:105px,grow,shrink 50,fill][0px:105px:105px,grow,shrink 50,fill][0px:70px:70px,grow,shrink 50,fill][0px:70px:70px,grow,shrink 50,fill][0px:70px:70px,grow,shrink 50,fill][0px:70px:70px,grow,shrink 50,fill][0px:70px:70px,grow,shrink 50,fill][0px:70px:70px,grow,shrink 50,fill][0px:75px:75px,grow,shrink 50,fill][0px:75px:75px,grow,shrink 50,fill][grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][grow,shrink 50,fill]"));

        jPanelCategory = new JPanel();
        jPanelCategory.setBorder(new TitledBorder(null, "Category", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(jPanelCategory, "cell 0 0 2 7,grow");
        jPanelCategory.setLayout(new MigLayout("", "[0px:75px:75px,grow,shrink 50,fill][grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        jTextFieldCategory = new JTextField();
        PromptSupport.setPrompt("Search/Hit Enter To Add Item", jTextFieldCategory);
        jTextFieldCategory.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (jTableCategory.getRowCount() > 0) {
                        jTableCategory.requestFocus();
                        jTableCategory.changeSelection(0, 0, false, false);
                    }
                }
                if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    try {
                        jButtonBackActionPerformed();
                    } catch (Exception ex) {
                        Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                    }
                }
                if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (jTableCategory.getRowCount() > 0) {
                        jTableCategory.requestFocus();
                        jTableCategory.changeSelection(0, 0, false, false);
                    }
                    if (categoryNameToIDMap.get(jTextFieldCategory.getText().toString().trim()) == null && !jTextFieldCategory.getText().trim().equalsIgnoreCase("")) {
                        try {
                            StockItemFormationDAO.insertCategory(jTextFieldCategory.getText().trim().toString());
                            formInternalFrameActivated(null);
                            jTextFieldCategory.setText("");
                        } catch (Exception ex) {
                            Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                        }
                    } else {
                        jTextFieldLength.requestFocus();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    for (int i = 0; i < jTableCategory.getRowCount(); i++) {
                        if (jTableCategory.getValueAt(i, 1) != null) {
                            maintainCategorySelection.put(Integer.parseInt(jTableCategory.getValueAt(i, 0).toString()), (Boolean) jTableCategory.getValueAt(i, 1));
                        }
                    }

                    List<StockItemCategoryDTO> stockItemCategoryList = new ArrayList<StockItemCategoryDTO>();
                    String text = jTextFieldCategory.getText().trim();

                    for (StockItemCategoryDTO stockItemCategoryDTO : stockItemCategoryDTOList) {
                        if (stockItemCategoryDTO.getCategoryName().toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
                            stockItemCategoryList.add(stockItemCategoryDTO);
                        }
                    }
                    bindStockItemCategoryDTOToTable(stockItemCategoryList);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
        jPanelCategory.add(jTextFieldCategory, "cell 0 0 2 1,growx");
        jTextFieldCategory.setColumns(10);

        jScrollPaneCategory = new JScrollPane();
        jPanelCategory.add(jScrollPaneCategory, "cell 0 1 2 5,grow");

        jTableCategory = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0 ? false : true;
            }
        };

        jTableCategory.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    List<StockItemCategoryDTO> stockItemCategoryDTOs = new ArrayList<StockItemCategoryDTO>();
                    StockItemCategoryDTO stockItemCategoryDTO = null;
                    int categoryCount = 0;
                    for (int i = 0; i < jTableCategory.getRowCount(); i++) {
                        if (jTableCategory.getValueAt(i, 1) != null && ((Boolean) jTableCategory.getValueAt(i, 1)) != false) {
                            stockItemCategoryDTO = new StockItemCategoryDTO();
                            stockItemCategoryDTO.setCategoryID(Integer.parseInt(jTableCategory.getValueAt(i, 0).toString()));
                            stockItemCategoryDTO.setCategoryName(jTableCategory.getValueAt(i, 2).toString());
                            stockItemCategoryDTOs.add(stockItemCategoryDTO);
                            categoryCount++;
                        }
                    }

                    if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                        for (int i = 0; i < jTableCategory.getRowCount(); i++) {
                            if (jTableCategory.getValueAt(i, 1) != null && ((Boolean) jTableCategory.getValueAt(i, 1)) != false) {
                                flagCategory = true;
                            }
                        }
                        if (flagCategory) {
                            if (StockItemFormationDAO.deleteCategory(stockItemCategoryDTOs)) {
                                JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.CATEGORY_CAN_NOT_BE_DELETED);
                            } else {
                                JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.CATEGORY_DELETED_SUCCESSFULLY);
                            }
                            flagCategory = false;
                        } else {
                            JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.SELECT_ATLEAST_ONE_RECORD);
                        }
                        jTextFieldCategory.setText("");
                    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        StockItemFormationDAO.updateCategory(stockItemCategoryDTOs);
                        JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.CATEGORY_UPDATED_SUCCESSFULLY);
                    }

                    formInternalFrameActivated(null);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                }
            }
        });

        jTableCategory.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "", "Category", "Add"
                }) {
            Class[] columnTypes = new Class[]{
                Object.class, Boolean.class, Object.class, Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        jTableCategory.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTableCategory.getColumnModel().getColumn(0).setMinWidth(0);
        jTableCategory.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableCategory.getColumnModel().getColumn(1).setPreferredWidth(35);
        jTableCategory.getColumnModel().getColumn(3).setPreferredWidth(35);
        jTableCategory.getColumnModel().getColumn(3).setMinWidth(35);
        jTableCategory.getColumnModel().getColumn(3).setMaxWidth(35);
        jTableStockItemCategoryListModel = (DefaultTableModel) jTableCategory.getModel();

        TableColumn tableColumnCategory = jTableCategory.getColumnModel().getColumn(1);
        tableColumnCategory.setHeaderRenderer(new CheckBoxHeader(new MyItemListenerForCategory()));

        tableColumnCategory.setHeaderRenderer(new CheckBoxHeader(new MyItemListenerForCategory()));
        jTableStockItemCategoryListModel = (DefaultTableModel) jTableCategory.getModel();

        tableColumnCategory.setHeaderRenderer(new CheckBoxHeader(new MyItemListenerForCategory()));
        jScrollPaneCategory.setViewportView(jTableCategory);

        jPanelLength = new JPanel();
        jPanelLength.setBorder(new TitledBorder(null, "Length", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(jPanelLength, "cell 2 0 2 7,grow");
        jPanelLength.setLayout(new MigLayout("", "[0px:75px:75px,grow,shrink 50,fill][grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        jTextFieldLength = new JTextField();
        jTextFieldLength.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!jTextFieldLength.getText().trim().equalsIgnoreCase("")) {
                        try {
                            StockItemFormationDAO.insertLength(Double.parseDouble(jTextFieldLength.getText().trim().toString()));
                            formInternalFrameActivated(null);
                            jTextFieldLength.setText("");
                        } catch (Exception ex) {
                            Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                        }
                    } else {
                        jTextFieldWidth.requestFocus();
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldCategory.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                for (int i = 0; i < jTableLength.getRowCount(); i++) {
                    if (jTableLength.getValueAt(i, 1) != null) {
                        maintainLengthSelection.put(Integer.parseInt(jTableLength.getValueAt(i, 0).toString()), (Boolean) jTableLength.getValueAt(i, 1));
                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
        jPanelLength.add(jTextFieldLength, "cell 0 0 2 1,growx");
        jTextFieldLength.setColumns(10);

        jScrollPaneLength = new JScrollPane();
        jPanelLength.add(jScrollPaneLength, "cell 0 1 2 5,grow");

        jTableLength = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2 ? false : true;
            }
        };

        jTableLength.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "", "Length"
                }) {
            Class[] columnTypes = new Class[]{
                Object.class, Boolean.class, Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        jTableLength.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTableLength.getColumnModel().getColumn(0).setMinWidth(0);
        jTableLength.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableLength.getColumnModel().getColumn(1).setPreferredWidth(35);

        jTableStockItemLengthListModel = (DefaultTableModel) jTableLength.getModel();

        TableColumn tableColumnLength = jTableLength.getColumnModel().getColumn(1);
        tableColumnLength.setHeaderRenderer(new CheckBoxHeader(new MyItemListenerForLength()));

        tableColumnLength.setHeaderRenderer(new CheckBoxHeader(new MyItemListenerForLength()));
        jScrollPaneLength.setViewportView(jTableLength);

        jPanelWidth = new JPanel();
        jPanelWidth.setBorder(new TitledBorder(null, "Width", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(jPanelWidth, "cell 4 0 2 7,grow");
        jPanelWidth.setLayout(new MigLayout("", "[0px:75px:75px,grow,shrink 50,fill][grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        jTextFieldWidth = new JTextField();
        jTextFieldWidth.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!jTextFieldWidth.getText().trim().equalsIgnoreCase("")) {
                        try {
                            StockItemFormationDAO.insertWidth(Double.parseDouble(jTextFieldWidth.getText().trim().toString()));
                            formInternalFrameActivated(null);
                            jTextFieldWidth.setText("");
                        } catch (Exception ex) {
                            Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                        }
                    } else {
                        jTextFieldColor.requestFocus();
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldLength.requestFocus();
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                for (int i = 0; i < jTableWidth.getRowCount(); i++) {
                    if (jTableWidth.getValueAt(i, 1) != null) {
                        maintainWidthSelection.put(Integer.parseInt(jTableWidth.getValueAt(i, 0).toString()), (Boolean) jTableWidth.getValueAt(i, 1));
                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
        jPanelWidth.add(jTextFieldWidth, "cell 0 0 2 1,growx");
        jTextFieldWidth.setColumns(10);

        jScrollPaneWidth = new JScrollPane();
        jPanelWidth.add(jScrollPaneWidth, "cell 0 1 2 5,grow");

        jTableWidth = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2 ? false : true;
            }
        };
        jTableWidth.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "", "Width"
                }) {
            Class[] columnTypes = new Class[]{
                Object.class, Boolean.class, Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        jTableWidth.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTableWidth.getColumnModel().getColumn(0).setMinWidth(0);
        jTableWidth.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableWidth.getColumnModel().getColumn(1).setPreferredWidth(35);

        jTableStockItemWidthListModel = (DefaultTableModel) jTableWidth.getModel();

        TableColumn tableColumnWidth = jTableWidth.getColumnModel().getColumn(1);
        tableColumnWidth.setHeaderRenderer(new CheckBoxHeader(new MyItemListenerForWidth()));

        tableColumnWidth.setHeaderRenderer(new CheckBoxHeader(new MyItemListenerForWidth()));
        jScrollPaneWidth.setViewportView(jTableWidth);

        jPanelColor = new JPanel();
        jPanelColor.setBorder(new TitledBorder(null, "Color", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        getContentPane().add(jPanelColor, "cell 6 0 2 7,grow");
        jPanelColor.setLayout(new MigLayout("", "[0px:75px:75px,grow,shrink 50,fill][grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        jTextFieldColor = new JTextField();
        PromptSupport.setPrompt("Search/Hit Enter To Add Item", jTextFieldColor);
        jTextFieldColor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (jTableColor.getRowCount() > 0) {
                        jTableColor.requestFocus();
                        jTableColor.changeSelection(0, 0, false, false);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldWidth.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!jTextFieldColor.getText().trim().equalsIgnoreCase("")) {
                        if (jTableColor.getRowCount() > 0) {
                            jTableColor.requestFocus();
                            jTableColor.changeSelection(0, 0, false, false);
                        }
                        if (colorNameToIDMap.get(jTextFieldColor.getText().toString().trim()) == null) {
                            try {
                                StockItemFormationDAO.insertColor(jTextFieldColor.getText().trim().toString());
                                formInternalFrameActivated(null);
                                jTextFieldColor.setText("");
                            } catch (Exception ex) {
                                Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                            }
                        }
                    } else {
                        jTextFieldThickness.requestFocus();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    for (int i = 0; i < jTableColor.getRowCount(); i++) {
                        if (jTableColor.getValueAt(i, 1) != null) {
                            maintainColorSelection.put(Integer.parseInt(jTableColor.getValueAt(i, 0).toString()), (Boolean) jTableColor.getValueAt(i, 1));
                        }
                    }

                    List<StockItemColorDTO> stockItemColorList = new ArrayList<StockItemColorDTO>();
                    String text = jTextFieldColor.getText().trim();

                    for (StockItemColorDTO stockItemColorDTO : stockItemColorDTOList) {
                        if (stockItemColorDTO.getColorName().toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
                            stockItemColorList.add(stockItemColorDTO);
                        }
                    }
                    bindStockItemColorDTOToTable(stockItemColorList);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                }

            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
        jPanelColor.add(jTextFieldColor, "cell 0 0 2 1,growx");
        jTextFieldColor.setColumns(10);

        jScrollPaneColor = new JScrollPane();
        jPanelColor.add(jScrollPaneColor, "cell 0 1 2 5,grow");

        jTableColor = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0 ? false : true;
            }
        };

        jTableColor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    List<StockItemColorDTO> stockItemColorDTOs = new ArrayList<StockItemColorDTO>();
                    StockItemColorDTO stockItemColorDTO = null;
                    int colorCount = 0;
                    for (int i = 0; i < jTableColor.getRowCount(); i++) {
                        if (jTableColor.getValueAt(i, 1) != null && ((Boolean) jTableColor.getValueAt(i, 1)) != false) {
                            stockItemColorDTO = new StockItemColorDTO();
                            stockItemColorDTO.setColorID(Integer.parseInt(jTableColor.getValueAt(i, 0).toString()));
                            stockItemColorDTO.setColorName(jTableColor.getValueAt(i, 2).toString());
                            stockItemColorDTOs.add(stockItemColorDTO);
                            colorCount++;
                        }
                    }

                    if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                        for (int i = 0; i < jTableColor.getRowCount(); i++) {
                            if (jTableColor.getValueAt(i, 1) != null && ((Boolean) jTableColor.getValueAt(i, 1)) != false) {
                                flagColor = true;
                            }
                        }
                        if (flagColor) {
                            if (StockItemFormationDAO.deleteColor(stockItemColorDTOs)) {
                                JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.COLOR_CAN_NOT_BE_DELETED);
                            } else {
                                JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.COLOR_DELETED_SUCCESSFULLY);
                            }
                            flagColor = false;
                        } else {
                            JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.SELECT_ATLEAST_ONE_RECORD);
                        }
                        jTextFieldColor.setText("");
                    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        StockItemFormationDAO.updateColor(stockItemColorDTOs);
                        JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.COLOR_UPDATED_SUCCESSFULLY);
                    }

                    formInternalFrameActivated(null);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                }
            }
        });

        jTableColor.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "", "Color"
                }) {
            Class[] columnTypes = new Class[]{
                Object.class, Boolean.class, Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        jTableColor.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTableColor.getColumnModel().getColumn(0).setMinWidth(0);
        jTableColor.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableColor.getColumnModel().getColumn(1).setPreferredWidth(35);

        jTableStockItemColorListModel = (DefaultTableModel) jTableColor.getModel();

        TableColumn tableColumnColor = jTableColor.getColumnModel().getColumn(1);
        tableColumnColor.setHeaderRenderer(new CheckBoxHeader(new MyItemListenerForColor()));

        tableColumnColor.setHeaderRenderer(new CheckBoxHeader(new MyItemListenerForColor()));
        jScrollPaneColor.setViewportView(jTableColor);

        jPanelThickness = new JPanel();
        jPanelThickness.setBorder(new TitledBorder(null, "Thickness",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(jPanelThickness, "cell 8 0 2 7,grow");
        jPanelThickness.setLayout(new MigLayout("", "[0px:75px:75px,grow,shrink 50,fill][grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        jTextFieldThickness = new JTextField();
        jTextFieldThickness.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!jTextFieldThickness.getText().trim().equalsIgnoreCase("")) {
                        try {
                            StockItemFormationDAO.insertThickness(Double.parseDouble(jTextFieldThickness.getText().trim().toString()));
                            formInternalFrameActivated(null);
                            jTextFieldThickness.setText("");
                        } catch (Exception ex) {
                            Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                        }
                    } else {
                        jTextFieldFinishType.requestFocus();
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldColor.requestFocus();
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                for (int i = 0; i < jTableThickness.getRowCount(); i++) {
                    if (jTableThickness.getValueAt(i, 1) != null) {
                        maintainThicknessSelection.put(Integer.parseInt(jTableThickness.getValueAt(i, 0).toString()), (Boolean) jTableThickness.getValueAt(i, 1));
                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
        jPanelThickness.add(jTextFieldThickness, "cell 0 0 2 1,growx");
        jTextFieldThickness.setColumns(10);

        jScrollPaneThickness = new JScrollPane();
        jPanelThickness.add(jScrollPaneThickness, "cell 0 1 2 5,grow");

        jTableThickness = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2 ? false : true;
            }
        };
        jTableThickness.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "", "Thickness"
                }) {
            Class[] columnTypes = new Class[]{
                Object.class, Boolean.class, Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        jTableThickness.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTableThickness.getColumnModel().getColumn(0).setMinWidth(0);
        jTableThickness.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableThickness.getColumnModel().getColumn(1).setPreferredWidth(35);

        TableColumn tableColumnThickness = jTableThickness.getColumnModel().getColumn(1);
        tableColumnThickness.setHeaderRenderer(new CheckBoxHeader(new MyItemListenerForThickness()));

        tableColumnThickness.setHeaderRenderer(new CheckBoxHeader(new MyItemListenerForThickness()));

        jTableStockItemThicknessListModel = (DefaultTableModel) jTableThickness.getModel();
        jScrollPaneThickness.setViewportView(jTableThickness);

        jPanelFinishType = new JPanel();
        jPanelFinishType.setBorder(new TitledBorder(null, "Finish Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(jPanelFinishType, "cell 10 0 2 7,grow");
        jPanelFinishType.setLayout(new MigLayout("", "[0px:75px:75px,grow,shrink 50,fill][grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        jTextFieldFinishType = new JTextField();
        PromptSupport.setPrompt("Search/Hit Enter To Add Item", jTextFieldFinishType);
        jTextFieldFinishType.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (jTableFinishType.getRowCount() > 0) {
                        jTableFinishType.requestFocus();
                        jTableFinishType.changeSelection(0, 0, false, false);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldThickness.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!jTextFieldFinishType.getText().trim().equalsIgnoreCase("")) {
                        if (jTableFinishType.getRowCount() > 0) {
                            jTableFinishType.requestFocus();
                            jTableFinishType.changeSelection(0, 0, false, false);
                        }
                        if (finishTypeNameToIDMap.get(jTextFieldFinishType.getText().toString().trim()) == null) {
                            try {
                                StockItemFormationDAO.insertFinishType(jTextFieldFinishType.getText().trim().toString());
                                formInternalFrameActivated(null);
                                jTextFieldFinishType.setText("");
                            } catch (Exception ex) {
                                Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                            }
                        }
                    } else {
                        jTextFieldType.requestFocus();
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                try {

                    for (int i = 0; i < jTableFinishType.getRowCount(); i++) {
                        if (jTableFinishType.getValueAt(i, 1) != null) {
                            maintainFinishTypeSelection.put(Integer.parseInt(jTableFinishType.getValueAt(i, 0).toString()), (Boolean) jTableFinishType.getValueAt(i, 1));
                        }
                    }

                    List<StockItemFinishTypeDTO> stockItemFinishTypeList = new ArrayList<StockItemFinishTypeDTO>();
                    String text = jTextFieldFinishType.getText().trim();

                    for (StockItemFinishTypeDTO stockItemFinishTypeDTO : stockItemFinishTypeDTOList) {
                        if (stockItemFinishTypeDTO.getFinishTypeName().toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
                            stockItemFinishTypeList.add(stockItemFinishTypeDTO);
                        }
                    }
                    bindStockItemFinishTypeDTOToTable(stockItemFinishTypeList);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                }

            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
        jPanelFinishType.add(jTextFieldFinishType, "cell 0 0 2 1,growx");
        jTextFieldFinishType.setColumns(10);

        jScrollPaneFinishType = new JScrollPane();
        jPanelFinishType.add(jScrollPaneFinishType, "cell 0 1 2 5,grow");

        jTableFinishType = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0 ? false : true;
            }
        };

        jTableFinishType.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    List<StockItemFinishTypeDTO> stockItemFinishTypeDTOs = new ArrayList<StockItemFinishTypeDTO>();
                    StockItemFinishTypeDTO stockItemFinishTypeDTO = null;
                    int fTypeCount = 0;
                    for (int i = 0; i < jTableFinishType.getRowCount(); i++) {
                        if (jTableFinishType.getValueAt(i, 1) != null && ((Boolean) jTableFinishType.getValueAt(i, 1)) != false) {
                            stockItemFinishTypeDTO = new StockItemFinishTypeDTO();
                            stockItemFinishTypeDTO.setFinishTypeID(Integer.parseInt(jTableFinishType.getValueAt(i, 0).toString()));
                            stockItemFinishTypeDTO.setFinishTypeName(jTableFinishType.getValueAt(i, 2).toString());
                            stockItemFinishTypeDTOs.add(stockItemFinishTypeDTO);
                            fTypeCount++;
                        }
                    }

                    if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                        for (int i = 0; i < jTableFinishType.getRowCount(); i++) {
                            if (jTableFinishType.getValueAt(i, 1) != null && ((Boolean) jTableFinishType.getValueAt(i, 1)) != false) {
                                flagFinishType = true;
                            }
                        }
                        if (flagFinishType) {
                            if (StockItemFormationDAO.deleteFinishType(stockItemFinishTypeDTOs)) {
                                JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.FINISHTYPE_CAN_NOT_BE_DELETED);
                            } else {
                                JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.FINISHTYPE_DELETED_SUCCESSFULLY);
                            }
                            flagFinishType = false;
                        } else {
                            JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.SELECT_ATLEAST_ONE_RECORD);
                        }
                        jTextFieldFinishType.setText("");
                    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        StockItemFormationDAO.updateFinishType(stockItemFinishTypeDTOs);
                        JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.FINISHTYPE_UPDATED_SUCCESSFULLY);
                    }

                    formInternalFrameActivated(null);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                }
            }
        });

        jTableFinishType.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "", "Finished Type", "Add"
                }) {
            Class[] columnTypes = new Class[]{
                Object.class, Boolean.class, Object.class, Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        jTableFinishType.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTableFinishType.getColumnModel().getColumn(0).setMinWidth(0);
        jTableFinishType.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableFinishType.getColumnModel().getColumn(1).setPreferredWidth(35);
        jTableFinishType.getColumnModel().getColumn(3).setPreferredWidth(35);

        jTableStockItemFinishTypeListModel = (DefaultTableModel) jTableFinishType.getModel();

        TableColumn tableColumnFinishType = jTableFinishType.getColumnModel().getColumn(1);
        tableColumnFinishType.setHeaderRenderer(new CheckBoxHeader(new MyItemListenerForFinishType()));

        tableColumnFinishType.setHeaderRenderer(new CheckBoxHeader(new MyItemListenerForFinishType()));
        jScrollPaneFinishType.setViewportView(jTableFinishType);

        jPanelType = new JPanel();
        jPanelType.setBorder(new TitledBorder(null, "Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(jPanelType, "cell 12 0 2 7,grow");
        jPanelType.setLayout(new MigLayout("", "[0px:75px:75px,grow,shrink 50,fill][grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        jTextFieldType = new JTextField();
        PromptSupport.setPrompt("Search/Hit Enter To Add Item", jTextFieldType);
        jTextFieldType.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {

                if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (jTableType.getRowCount() > 0) {
                        jTextFieldType.requestFocus();
                        jTableType.changeSelection(0, 0, false, false);
                    }
                } else if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldFinishType.requestFocus();
                } else if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!jTextFieldType.getText().trim().equalsIgnoreCase("")) {
                        if (jTableType.getRowCount() > 0) {
                            jTextFieldType.requestFocus();
                            jTableType.changeSelection(0, 0, false, false);
                        }
                        if (typeNameToIDMap.get(jTextFieldType.getText().toString().trim()) == null) {
                            try {
                                StockItemFormationDAO.insertType(jTextFieldType.getText().trim().toString());
                                formInternalFrameActivated(null);
                                jTextFieldType.setText("");
                            } catch (Exception ex) {
                                Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                            }
                        }
                    } else {
                        jTextFieldUnder.requestFocus();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent arg0) {
                try {

                    for (int i = 0; i < jTableType.getRowCount(); i++) {
                        if (jTableType.getValueAt(i, 1) != null) {
                            maintainTypeSelection.put(Integer.parseInt(jTableType.getValueAt(i, 0).toString()), (Boolean) jTableType.getValueAt(i, 1));
                        }
                    }

                    List<StockItemTypeDTO> stockItemTypeList = new ArrayList<StockItemTypeDTO>();
                    String text = jTextFieldType.getText().trim();

                    for (StockItemTypeDTO stockItemTypeDTO : stockItemTypeDTOList) {
                        if (stockItemTypeDTO.getTypeName().toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
                            stockItemTypeList.add(stockItemTypeDTO);
                        }
                    }
                    bindStockItemTypeDTOToTable(stockItemTypeList);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                }

            }

            @Override
            public void keyTyped(KeyEvent arg0) {
            }
        });
        jPanelType.add(jTextFieldType, "cell 0 0 2 1,growx");
        jTextFieldType.setColumns(10);

        jScrollPaneType = new JScrollPane();
        jPanelType.add(jScrollPaneType, "cell 0 1 2 5,grow");

        jTableType = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0 ? false : true;
            }
        };

        jTableType.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    List<StockItemTypeDTO> stockItemTypeDTOs = new ArrayList<StockItemTypeDTO>();
                    StockItemTypeDTO stockItemTypeDTO = null;
                    int typeCount = 0;
                    for (int i = 0; i < jTableType.getRowCount(); i++) {
                        if (jTableType.getValueAt(i, 1) != null && ((Boolean) jTableType.getValueAt(i, 1)) != false) {
                            stockItemTypeDTO = new StockItemTypeDTO();
                            stockItemTypeDTO.setTypeID(Integer.parseInt(jTableType.getValueAt(i, 0).toString()));
                            stockItemTypeDTO.setTypeName(jTableType.getValueAt(i, 2).toString());
                            stockItemTypeDTOs.add(stockItemTypeDTO);
                            typeCount++;
                        }
                    }

                    if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                        for (int i = 0; i < jTableType.getRowCount(); i++) {
                            if (jTableType.getValueAt(i, 1) != null && ((Boolean) jTableType.getValueAt(i, 1)) != false) {
                                flagType = true;
                            }
                        }
                        if (flagType) {
                            if (StockItemFormationDAO.deleteType(stockItemTypeDTOs)) {
                                JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.TYPE_CAN_NOT_BE_DELETED);
                            } else {
                                JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.TYPE_DELETED_SUCCESSFULLY);
                            }
                            flagType = false;
                        } else {
                            JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.SELECT_ATLEAST_ONE_RECORD);
                        }
                        jTextFieldType.setText("");
                    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        StockItemFormationDAO.updateType(stockItemTypeDTOs);
                        JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.TYPE_UPDATED_SUCCESSFULLY);
                    }

                    formInternalFrameActivated(null);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                }
            }
        });

        jTableType.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "", "Type"
                }) {
            Class[] columnTypes = new Class[]{
                Object.class, Boolean.class, Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        jTableType.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTableType.getColumnModel().getColumn(0).setMinWidth(0);
        jTableType.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableType.getColumnModel().getColumn(1).setPreferredWidth(35);

        jTableStockItemTypeListModel = (DefaultTableModel) jTableType.getModel();

        TableColumn tableColumnType = jTableType.getColumnModel().getColumn(1);
        tableColumnType.setHeaderRenderer(new CheckBoxHeader(new MyItemListenerForType()));

        tableColumnType.setHeaderRenderer(new CheckBoxHeader(new MyItemListenerForType()));
        jScrollPaneType.setViewportView(jTableType);

        lblSearchName = new JLabel("Search Name");
        lblSearchName.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblSearchName, "flowx,cell 14 0");

        jTextFieldNameSearch = new JTextField();
        PromptSupport.setPrompt("Search", jTextFieldNameSearch);
        jTextFieldNameSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (jTableStockItemList.getRowCount() > 0) {
                        jTableStockItemList.requestFocus();
                        jTableStockItemList.changeSelection(0, 0, false, false);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (jTableStockItemList.getRowCount() > 0) {
                        jTableStockItemList.requestFocus();
                        jTableStockItemList.changeSelection(0, 0, false, false);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    List<StockItemDTO> stockItemDTOs = new ArrayList<StockItemDTO>();
                    String text = jTextFieldNameSearch.getText().trim();

                    for (StockItemDTO stockItemDTO : stockItemTransactionList) {
                        if ((categoryIDToNameMap.get("" + stockItemDTO.getStockItemCategoryDTO().getCategoryID())).toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
                            stockItemDTOs.add(stockItemDTO);
                        }
                    }
                    bindStockItemDTOToTransactionTable(stockItemDTOs);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });

        getContentPane().add(jTextFieldNameSearch, "flowx,cell 15 0");
        jTextFieldNameSearch.setColumns(10);

        jPanelStockItemList = new JPanel();
        jPanelStockItemList.setBorder(new LineBorder(new Color(0, 0, 0)));
        getContentPane().add(jPanelStockItemList, "cell 14 1 3 20,grow");
        jPanelStockItemList.setLayout(new MigLayout("", "[grow]", "[grow]"));

        jScrollPaneStockItemList = new JScrollPane();
        jPanelStockItemList.add(jScrollPaneStockItemList, "cell 0 0,grow");

        jTableStockItemList = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0 || column == 1 ? false : true;
            }
        };
        jTableStockItemList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {
            }
        });
        jTableStockItemList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                try {
                    isEdit = true;

                    jTableCategory.setValueAt(null, jTableCategoryClick, 1);
                    jTableLength.setValueAt(null, jTableLengthClick, 1);
                    jTableWidth.setValueAt(null, jTableWidthClick, 1);
                    jTableColor.setValueAt(null, jTableColorClick, 1);
                    jTableThickness.setValueAt(null, jTableThicknessClick, 1);
                    System.out.println("jTableThickness==" + jTableThickness);
                    System.out.println("jTableFinishTypeClick==" + jTableFinishTypeClick);
                    if(jTableFinishTypeClick>0){
                        jTableFinishType.setValueAt(null, jTableFinishTypeClick, 1);
                    }
                    if(jTableTypeClick >0){
                        jTableType.setValueAt(null, jTableTypeClick, 1);
                    }
                    if(jTableUnderClick>0){
                        jTableUnder.setValueAt(null, jTableUnderClick, 1);
                    }
                    if(jTableUnitClick>0){
                        jTableUnit.setValueAt(null, jTableUnitClick, 1);
                    }
                    

                    String id = jTableStockItemList.getValueAt(jTableStockItemList.getSelectedRow(), 0).toString();

                    loadEditForm(id);

                    for (StockItemDTO stockItemDTO : stockItemDTOList) {
                        for (Map.Entry<String, String> e : categoryIDToNameMap.entrySet()) {
                            if (stockItemDTO.getStockItemCategoryDTO().getCategoryID() == Integer.parseInt(e.getKey())) {
                                for (int i = 0; i < jTableCategory.getRowCount(); i++) {
                                    if (Integer.parseInt("" + jTableCategory.getValueAt(i, 0)) == stockItemDTO.getStockItemCategoryDTO().getCategoryID()) {
                                        jTableCategory.setValueAt(true, i, 1);
                                        jTableCategoryClick = i;
                                    }
                                }
                            }
                        }

                        for (int i = 0; i < jTableLength.getRowCount(); i++) {
                            if (Double.parseDouble("" + jTableLength.getValueAt(i, 2)) == stockItemDTO.getLength()) {
                                jTableLength.setValueAt(true, i, 1);
                                jTableLengthClick = i;
                            }
                        }

                        for (int i = 0; i < jTableWidth.getRowCount(); i++) {
                            if (Double.parseDouble("" + jTableWidth.getValueAt(i, 2)) == stockItemDTO.getWidth()) {
                                jTableWidth.setValueAt(true, i, 1);
                                jTableWidthClick = i;
                            }
                        }

                        for (Map.Entry<String, String> e : colorIDToNameMap.entrySet()) {
                            if (stockItemDTO.getStockItemColorDTO().getColorID() == Integer.parseInt(e.getKey())) {
                                for (int i = 0; i < jTableColor.getRowCount(); i++) {
                                    if (Integer.parseInt("" + jTableColor.getValueAt(i, 0)) == stockItemDTO.getStockItemColorDTO().getColorID()) {
                                        jTableColor.setValueAt(true, i, 1);
                                        jTableColorClick = i;
                                    }
                                }
                            }
                        }

                        for (int i = 0; i < jTableThickness.getRowCount(); i++) {
                            if (Double.parseDouble("" + jTableThickness.getValueAt(i, 2)) == stockItemDTO.getThkness()) {
                                jTableThickness.setValueAt(true, i, 1);
                                jTableThicknessClick = i;
                            }
                        }

                        for (Map.Entry<String, String> e : finishTypeIDToNameMap.entrySet()) {
                            if (stockItemDTO.getStockItemFinishTypeDTO().getFinishTypeID() == Integer.parseInt(e.getKey())) {
                                for (int i = 0; i < jTableFinishType.getRowCount(); i++) {
                                    if (Integer.parseInt("" + jTableFinishType.getValueAt(i, 0)) == stockItemDTO.getStockItemFinishTypeDTO().getFinishTypeID()) {
                                        jTableFinishType.setValueAt(true, i, 1);
                                        jTableFinishTypeClick = i;
                                    }
                                }
                            }
                        }

                        for (Map.Entry<String, String> e : typeIDToNameMap.entrySet()) {
                            if (stockItemDTO.getStockItemTypeDTO().getTypeID() == Integer.parseInt(e.getKey())) {
                                for (int i = 0; i < jTableType.getRowCount(); i++) {
                                    if (Integer.parseInt("" + jTableType.getValueAt(i, 0)) == stockItemDTO.getStockItemTypeDTO().getTypeID()) {
                                        jTableType.setValueAt(true, i, 1);
                                        jTableTypeClick = i;
                                    }
                                }
                            }
                        }

                        for (Map.Entry<String, String> e : stockGroupIDToNameMap.entrySet()) {
                            if (Integer.parseInt(stockItemDTO.getUnder()) == Integer.parseInt(e.getKey())) {
                                for (int i = 0; i < jTableUnder.getRowCount(); i++) {
                                    if (Integer.parseInt("" + jTableUnder.getValueAt(i, 0)) == Integer.parseInt(stockItemDTO.getUnder())) {
                                        jTableUnder.setValueAt(true, i, 1);
                                        jTableUnderClick = i;
                                    }
                                }
                            }
                        }

                        for (Map.Entry<String, String> e : UOMIDToNameMap.entrySet()) {
                            if (Integer.parseInt(stockItemDTO.getUnit()) == Integer.parseInt(e.getKey())) {
                                for (int i = 0; i < jTableUnit.getRowCount(); i++) {
                                    if (Integer.parseInt("" + jTableUnit.getValueAt(i, 0)) == Integer.parseInt(stockItemDTO.getUnit())) {
                                        jTableUnit.setValueAt(true, i, 1);
                                        jTableUnitClick = i;
                                    }
                                }
                            }
                        }

                        bindStockItemListDTOToJTableDisplay();

                    }
                } catch (Exception ex) {
                    Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                }
            }
        });

        jTableStockItemList.setModel(new DefaultTableModel(
                new Object[][]{
                    {null, null},
                    {null, null},
                    {null, null},
                    {null, null},
                    {null, null},},
                new String[]{
                    "ID", "StockItemName"
                }));
        jTableStockItemList.getColumnModel().getColumn(0).setPreferredWidth(15);
        jTableStockItemList.getColumnModel().getColumn(0).setMinWidth(10);

        jTableStockItemListModel = (DefaultTableModel) jTableStockItemList.getModel();

        jScrollPaneStockItemList.setViewportView(jTableStockItemList);

        jButtonCreate = new JButton("Create");
        jButtonCreate.setMnemonic(KeyEvent.VK_C);
        jButtonCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                isEdit = false;
                try {
                    if (validateForUnder()) {
                        jButtonCreateActionPerformed();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                }
            }
        });

        jButtonCreate.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                    jButtonDisplayDelete.requestFocus();
                }
                if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldUnit.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent event) {
            }

            @Override
            public void keyTyped(KeyEvent event) {
            }
        });

        jButtonDisplayDelete = new JButton("Delete");
        jButtonDisplayDelete.setMnemonic(KeyEvent.VK_E);
        jButtonDisplayDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (jTableDisplay.getSelectedRow() >= 0) {
                    indexForDeletion = jTableDisplay.getSelectedRow();
                    DefaultTableModel myTableModel = (DefaultTableModel) jTableDisplay.getModel();
                    myTableModel.removeRow(indexForDeletion);
                } else {
                    JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.SELECT_ATLEAST_ONE_RECORD);
                }
            }
        });

        jButtonDisplayDelete.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                    jButtonCreate.requestFocus();
                }
                if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldUnit.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent event) {
            }

            @Override
            public void keyTyped(KeyEvent event) {
            }
        });

        jPanelUnit = new JPanel();
        jPanelUnit.setBorder(new TitledBorder(null, "Unit", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(jPanelUnit, "cell 2 7 2 4,grow");
        jPanelUnit.setLayout(new MigLayout("", "[0px:75px:75px,grow,shrink 50,fill][grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        jTextFieldUnit = new JTextField();
        jTextFieldUnit.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (jTableUnit.getRowCount() > 0) {
                        jTableUnit.requestFocus();
                        jTableUnit.changeSelection(0, 0, false, false);
                    }
                } else if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldUnder.requestFocus();
                } else if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!jTextFieldUnit.getText().trim().equalsIgnoreCase("")) {
                        if (jTableUnit.getRowCount() > 0) {
                            jTableUnit.requestFocus();
                            jTableUnit.changeSelection(0, 0, false, false);
                        }
                    } else {
                        jButtonCreate.requestFocus();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent event) {
                try {
                    for (int i = 0; i < jTableUnit.getRowCount(); i++) {
                        if (jTableUnit.getValueAt(i, 1) != null) {
                            maintainUnitSelection.put(Integer.parseInt(jTableUnit.getValueAt(i, 0).toString()), (Boolean) jTableUnit.getValueAt(i, 1));
                        }
                    }

                    Map<String, String> UOMScannedUnderMap = new HashMap<String, String>();
                    String text = jTextFieldUnit.getText().trim();

                    for (Map.Entry<String, String> e : UOMIDToNameMap.entrySet()) {
                        if (e.getValue().toString().toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
                            UOMScannedUnderMap.put(e.getKey(), e.getValue());
                        }
                    }
                    bindStockItemUnitToTable(UOMScannedUnderMap);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                }
            }

            @Override
            public void keyTyped(KeyEvent event) {
            }
        });
        jPanelUnit.add(jTextFieldUnit, "cell 0 0 2 1,growx");
        jTextFieldUnit.setColumns(10);

        jScrollPaneUnit = new JScrollPane();
        jPanelUnit.add(jScrollPaneUnit, "cell 0 2 2 2,grow");

        jTableUnit = new JTable();
        jTableUnit.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Mark", "Unit"
                }) {
            private boolean ImInLoop = false;
            Class[] columnTypes = new Class[]{
                Object.class, Boolean.class, Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                if (columnIndex == 1) {
                    if (!ImInLoop) {
                        ImInLoop = true;
                        if (aValue == null) {
                            aValue = false;
                        }
                        Boolean bol = (Boolean) aValue;
                        super.setValueAt(aValue, rowIndex, columnIndex);
                        if (aValue == (Boolean) true) {
                            for (int i = 0; i < this.getRowCount(); i++) {
                                if (i != rowIndex) {
                                    super.setValueAt(!bol, i, columnIndex);
                                }
                            }
                        }
                        ImInLoop = false;
                    }
                } else {
                    super.setValueAt(aValue, rowIndex, columnIndex);
                }
            }
        });

        jTableStockItemUnitListModel = (DefaultTableModel) jTableUnit.getModel();

        jTableUnit.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTableUnit.getColumnModel().getColumn(0).setMinWidth(0);
        jTableUnit.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableUnit.getColumnModel().getColumn(1).setPreferredWidth(35);
        jScrollPaneUnit.setViewportView(jTableUnit);

        getContentPane().add(jButtonDisplayDelete, "cell 13 7");
        getContentPane().add(jButtonCreate, "cell 12 7");

        jPanelUnder = new JPanel();
        jPanelUnder.setBorder(new TitledBorder(null, "Under", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(jPanelUnder, "cell 0 7 2 4,grow");
        jPanelUnder.setLayout(new MigLayout("", "[0px:75px:75px,grow,shrink 50,fill][grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        jTextFieldUnder = new JTextField();
        jTextFieldUnder.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (jTableUnder.getRowCount() > 0) {
                        jTableUnder.requestFocus();
                        jTableUnder.changeSelection(0, 0, false, false);
                    }
                } else if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldType.requestFocus();
                } else if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!jTextFieldUnder.getText().trim().equalsIgnoreCase("")) {
                        if (jTableUnder.getRowCount() > 0) {
                            jTableUnder.requestFocus();
                            jTableUnder.changeSelection(0, 0, false, false);
                        }
                    } else {
                        jTextFieldUnit.requestFocus();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent event) {
                try {
                    for (int i = 0; i < jTableUnder.getRowCount(); i++) {
                        if (jTableUnder.getValueAt(i, 1) != null) {
                            maintainUnderSelection.put(Integer.parseInt(jTableUnder.getValueAt(i, 0).toString()), (Boolean) jTableUnder.getValueAt(i, 1));
                        }
                    }

                    Map<String, String> stockItemScannedUnderMap = new HashMap<String, String>();
                    String text = jTextFieldUnder.getText().trim();

                    for (Map.Entry<String, String> e : stockGroupIDToNameMap.entrySet()) {
                        if (e.getValue().toString().toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
                            stockItemScannedUnderMap.put(e.getKey(), e.getValue());
                        }
                    }
                    bindStockItemUnderToTable(stockItemScannedUnderMap);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                }
            }

            @Override
            public void keyTyped(KeyEvent event) {
            }
        });
        jPanelUnder.add(jTextFieldUnder, "cell 0 0 2 1,growx");
        jTextFieldUnder.setColumns(10);

        jScrollPaneUnder = new JScrollPane();
        jPanelUnder.add(jScrollPaneUnder, "cell 0 2 2 2,grow");

        jTableUnder = new JTable();
        jTableUnder.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Mark", "Under"
                }) {
            private boolean ImInLoop = false;
            Class[] columnTypes = new Class[]{
                Object.class, Boolean.class, Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                if (columnIndex == 1) {
                    if (!ImInLoop) {
                        ImInLoop = true;
                        Boolean bol = (Boolean) aValue;
                        super.setValueAt(aValue, rowIndex, columnIndex);
                        if (aValue == (Boolean) true) {
                            for (int i = 0; i < this.getRowCount(); i++) {
                                if (i != rowIndex) {
                                    super.setValueAt(!bol, i, columnIndex);
                                }
                            }
                        }
                        ImInLoop = false;
                    }
                } else {
                    super.setValueAt(aValue, rowIndex, columnIndex);
                }
            }
        });

        jTableStockItemUnderListModel = (DefaultTableModel) jTableUnder.getModel();

        jTableUnder.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTableUnder.getColumnModel().getColumn(0).setMinWidth(0);
        jTableUnder.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableUnder.getColumnModel().getColumn(1).setPreferredWidth(35);
        jScrollPaneUnder.setViewportView(jTableUnder);

        jPanelDisplay = new JPanel();
        jPanelDisplay.setBorder(new TitledBorder(null, "Display Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(jPanelDisplay, "cell 0 11 14 8,grow");
        jPanelDisplay.setLayout(new MigLayout("", "[grow]", "[grow]"));

        jScrollPaneDisplay = new JScrollPane();
        jPanelDisplay.add(jScrollPaneDisplay, "cell 0 0,grow");

        jTableDisplay = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0 || column == 1 || column == 2 || column == 3 || column == 4 || column == 5 || column == 6 || column == 7 ? false : true;
            }
        };
        jTableDisplay.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Name", "Category", "Color", "Length", "Width", "Thickness", "F.Type", "Type", "Quantity", "ID", "UnderID", "UnitID"
                }));

        jTableDisplay.getColumnModel().getColumn(9).setMinWidth(0);
        jTableDisplay.getColumnModel().getColumn(9).setMaxWidth(0);
        jTableDisplay.getColumnModel().getColumn(9).setPreferredWidth(0);
        jTableDisplay.getColumnModel().getColumn(10).setMinWidth(0);
        jTableDisplay.getColumnModel().getColumn(10).setMaxWidth(0);
        jTableDisplay.getColumnModel().getColumn(10).setPreferredWidth(0);
        jTableDisplay.getColumnModel().getColumn(11).setMinWidth(0);
        jTableDisplay.getColumnModel().getColumn(11).setMaxWidth(0);
        jTableDisplay.getColumnModel().getColumn(11).setPreferredWidth(0);
        jScrollPaneDisplay.setViewportView(jTableDisplay);

        jButtonSubmit = new JButton("Submit");
        jButtonSubmit.setMnemonic(KeyEvent.VK_S);
        jButtonSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                isEdit = true;
                try {
                    if (validateForUnder() && jTableDisplay.getRowCount() != 0) {
                        jButtonSubmitActionPerformed();
                    } else {
                        JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.NO_STOCK_ITEM_IS_CREATED_YET);
                    }

                } catch (Exception ex) {
                    Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                }
            }
        });

        jButtonStockItemDelete = new JButton("Delete");
        jButtonStockItemDelete.setMnemonic(KeyEvent.VK_T);
        jButtonStockItemDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (jTableStockItemList.getSelectedRow() >= 0) {
                    try {
                        String id = jTableStockItemList.getValueAt(jTableStockItemList.getSelectedRow(), 0).toString();
                        Set<String> idSet = new HashSet<String>();
                        idSet.add(id);
                        stockItemDTOList = StockItemFormationDAO.getStockItemList(idSet);
                        if (StockItemFormationDAO.deleteStockItem(stockItemDTOList)) {
                            JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.STOCK_ITEM_CAN_NOT_BE_DELETED);
                        } else {
                            JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.STOCK_ITEM_DELETED_SUCCESSFULLY);
                        }
                        formInternalFrameActivated(null);
                        jButtonNewActionPerformed();
                    } catch (Exception ex) {
                        Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.NO_STOCK_ITEM_IS_SELECTED_FOR_DELETION);
                }
            }
        });

        jButtonNew = new JButton("New");
        jButtonNew.setMnemonic(KeyEvent.VK_N);
        jButtonNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    jButtonNewActionPerformed();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                }

            }
        });

        jButtonBack = new JButton("Back");
        jButtonBack.setMnemonic(KeyEvent.VK_B);
        jButtonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    jButtonBackActionPerformed();
                } catch (Exception ex) {
                    Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                }
            }
        });

        getContentPane().add(jButtonBack, "cell 10 19");
        getContentPane().add(jButtonNew, "cell 11 19");
        getContentPane().add(jButtonStockItemDelete, "cell 12 19");
        getContentPane().add(jButtonSubmit, "cell 13 19");

        initialiseActionListeners();

    }

    private void initialiseActionListeners() throws Exception {
        try {
            setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(Util.getImageIconPath())));
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
                @Override
                public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                    try {
                        formInternalFrameActivated(evt);
                    } catch (Exception ex) {
                        Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                    }
                }

                @Override
                public void internalFrameOpened(InternalFrameEvent e) {
                    try {
                        formInternalFrameOpened(e);
                    } catch (Exception ex) {
                        Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
                    }
                }

                @Override
                public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                    try {
                        formInternalFrameClosing(evt);
                    } catch (Exception ex) {
                        Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(StockItemFormFinal.this, ex.getMessage());
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

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    //GUI Fields Declaration
    private JScrollPane jScrollPaneDisplay;
    private JPanel jPanelDisplay;
    private JButton jButtonSubmit;
    private JScrollPane jScrollPaneFinishType;
    private JPanel jPanelFinishType;
    private JScrollPane jScrollPaneThickness;
    private JPanel jPanelThickness;
    private JScrollPane jScrollPaneColor;
    private JPanel jPanelColor;
    private JScrollPane jScrollPaneWidth;
    private JPanel jPanelWidth;
    private JScrollPane jScrollPaneLength;
    private JPanel jPanelLength;
    private JScrollPane jScrollPaneCategory;
    private JPanel jPanelCategory;
    private JTextField jTextFieldCategory;
    private JTable jTableCategory;
    private JTextField jTextFieldLength;
    private JTable jTableLength;
    private JTextField jTextFieldWidth;
    private JTable jTableWidth;
    private JTextField jTextFieldColor;
    private JTable jTableColor;
    private JTextField jTextFieldThickness;
    private JTable jTableThickness;
    private JTextField jTextFieldFinishType;
    private JTable jTableFinishType;
    private JTable jTableDisplay;
    private JPanel jPanelStockItemList;
    private JButton jButtonCreate;
    private JScrollPane jScrollPaneStockItemList;
    private JTable jTableStockItemList;
    private DefaultTableModel jTableDisplayTableModel;
    private DefaultTableModel jTableStockItemListModel;
    private DefaultTableModel jTableStockItemCategoryListModel;
    private DefaultTableModel jTableStockItemLengthListModel;
    private DefaultTableModel jTableStockItemWidthListModel;
    private DefaultTableModel jTableStockItemThicknessListModel;
    private DefaultTableModel jTableStockItemColorListModel;
    private DefaultTableModel jTableStockItemFinishTypeListModel;
    private DefaultTableModel jTableStockItemTypeListModel;
    private DefaultTableModel jTableStockItemUnderListModel;
    private DefaultTableModel jTableStockItemUnitListModel;
    private JButton jButtonStockItemDelete;
    private JButton jButtonNew;
    private JButton jButtonDisplayDelete;
    private JLabel lblSearchName;
    private JTextField jTextFieldNameSearch;
    private JButton jButtonBack;
    private JPanel jPanelType;
    private JTextField jTextFieldType;
    private JScrollPane jScrollPaneType;
    private JTable jTableType;

    private void initilize() {
        bindTOGUI();
    }

    private void bindTOGUI() {
    }

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
        System.out.println("Frame closing");
        MainClass.setstaticvar();
        MainClass m = new MainClass();
        m.menuselection(1);
    }

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
        try {
            initStockItemTransactionList();
            initStockItemCategoryList();
            initStockItemLengthList();
            initStockItemWidthList();
            initStockItemThicknessList();
            initStockItemColorList();
            initStockItemFinishTypeList();
            initStockItemTypeList();
            initStockGroup();
            initUOM();
        } catch (SQLException ex) {
            Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
    }

    private void initStockItemCategoryList() throws SQLException, Exception {
        stockItemCategoryDTOList = StockItemFormationDAO.getCategoryList();
        Collections.sort(stockItemCategoryDTOList, CATEGORY_ALPHABETICAL_ORDER);
        bindStockItemCategoryDTOToTable(stockItemCategoryDTOList);
    }
    private static Comparator<StockItemCategoryDTO> CATEGORY_ALPHABETICAL_ORDER = new Comparator<StockItemCategoryDTO>() {
        public int compare(StockItemCategoryDTO str1, StockItemCategoryDTO str2) {
            int res = String.CASE_INSENSITIVE_ORDER.compare(str1.getCategoryName(), str2.getCategoryName());
            if (res == 0) {
                res = str1.getCategoryName().compareTo(str2.getCategoryName());
            }
            return res;
        }
    };

    private void bindStockItemCategoryDTOToTable(List<StockItemCategoryDTO> stockItemCategoryDTOList) throws Exception {

        if (stockItemCategoryDTOList != null && !stockItemCategoryDTOList.isEmpty()) {
            jTableStockItemCategoryListModel.setRowCount(0);
            for (StockItemCategoryDTO stockItemCategoryDTO : stockItemCategoryDTOList) {
                try {
                    jTableStockItemCategoryListModel.setRowCount(jTableStockItemCategoryListModel.getRowCount() + 1);
                    jTableStockItemCategoryListModel.setValueAt(stockItemCategoryDTO.getCategoryID(), jTableStockItemCategoryListModel.getRowCount() - 1, 0);
                    jTableStockItemCategoryListModel.setValueAt(stockItemCategoryDTO.getCategoryName(), jTableStockItemCategoryListModel.getRowCount() - 1, 2);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                    throw ex;
                }
            }

            for (Map.Entry<Integer, Boolean> e : maintainCategorySelection.entrySet()) {
                for (int i = 0; i < jTableCategory.getRowCount(); i++) {
                    if (e.getKey() == jTableCategory.getValueAt(i, 0)) {
                        jTableStockItemCategoryListModel.setValueAt(e.getValue(), i, 1);
                    }
                }
            }

        } else {
            if (last_Number_In_CategoryList != 1) {
                jTableStockItemCategoryListModel.setRowCount(0);
            }
        }
        last_Number_In_CategoryList = 0;

    }

    private void initStockItemLengthList() throws SQLException, Exception {
        stockItemLengthDTOList = StockItemFormationDAO.getLengthList();
        bindStockItemLengthDTOToTable(stockItemLengthDTOList);
    }

    private void bindStockItemLengthDTOToTable(List<StockItemDTO> stockItemLengthDTOList) throws Exception {

        if (stockItemLengthDTOList != null && !stockItemLengthDTOList.isEmpty()) {
            jTableStockItemLengthListModel.setRowCount(0);
            for (StockItemDTO stockItemLengthDTO : stockItemLengthDTOList) {
                try {
                    jTableStockItemLengthListModel.setRowCount(jTableStockItemLengthListModel.getRowCount() + 1);
                    jTableStockItemLengthListModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemLengthDTO.getLength()), jTableStockItemLengthListModel.getRowCount() - 1, 2);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            for (Map.Entry<Integer, Boolean> e : maintainLengthSelection.entrySet()) {
                for (int i = 0; i < jTableLength.getRowCount(); i++) {
                    if (e.getKey() == jTableLength.getValueAt(i, 0)) {
                        jTableStockItemLengthListModel.setValueAt(e.getValue(), i, 1);
                    }
                }
            }

        } else {
            if (last_Number_In_LengthList != 1) {
                jTableStockItemLengthListModel.setRowCount(0);
            }
        }
        last_Number_In_LengthList = 0;

    }

    private void initStockItemWidthList() throws SQLException, Exception {
        stockItemWidthDTOList = StockItemFormationDAO.getWidthList();
        bindStockItemWidthDTOToTable(stockItemWidthDTOList);
    }

    private void bindStockItemWidthDTOToTable(List<StockItemDTO> stockItemWidthDTOList) throws Exception {

        if (stockItemWidthDTOList != null && !stockItemWidthDTOList.isEmpty()) {
            jTableStockItemWidthListModel.setRowCount(0);
            for (StockItemDTO stockItemLengthDTO : stockItemWidthDTOList) {
                try {
                    jTableStockItemWidthListModel.setRowCount(jTableStockItemWidthListModel.getRowCount() + 1);
                    jTableStockItemWidthListModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemLengthDTO.getWidth()), jTableStockItemWidthListModel.getRowCount() - 1, 2);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            for (Map.Entry<Integer, Boolean> e : maintainWidthSelection.entrySet()) {
                for (int i = 0; i < jTableWidth.getRowCount(); i++) {
                    if (e.getKey() == jTableWidth.getValueAt(i, 0)) {
                        jTableStockItemWidthListModel.setValueAt(e.getValue(), i, 1);
                    }
                }
            }

        } else {
            if (last_Number_In_WidthList != 1) {
                jTableStockItemWidthListModel.setRowCount(0);
            }
        }
        last_Number_In_WidthList = 0;

    }

    private void initStockItemThicknessList() throws SQLException, Exception {
        stockItemThicknessDTOList = StockItemFormationDAO.getThicknessList();
        bindStockItemThicknessDTOToTable(stockItemThicknessDTOList);
    }

    private void bindStockItemThicknessDTOToTable(List<StockItemDTO> stockItemThicknessDTOList) throws Exception {

        if (stockItemThicknessDTOList != null && !stockItemThicknessDTOList.isEmpty()) {
            jTableStockItemThicknessListModel.setRowCount(0);
            for (StockItemDTO stockItemThicknessDTO : stockItemThicknessDTOList) {
                try {
                    jTableStockItemThicknessListModel.setRowCount(jTableStockItemThicknessListModel.getRowCount() + 1);
                    jTableStockItemThicknessListModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemThicknessDTO.getThkness()), jTableStockItemThicknessListModel.getRowCount() - 1, 2);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            for (Map.Entry<Integer, Boolean> e : maintainThicknessSelection.entrySet()) {
                for (int i = 0; i < jTableThickness.getRowCount(); i++) {
                    if (e.getKey() == jTableThickness.getValueAt(i, 0)) {
                        jTableStockItemThicknessListModel.setValueAt(e.getValue(), i, 1);
                    }
                }
            }

        } else {
            if (last_Number_In_ThicknessList != 1) {
                jTableStockItemThicknessListModel.setRowCount(0);
            }
        }
        last_Number_In_ThicknessList = 0;

    }

    private void initStockItemColorList() throws SQLException, Exception {
        stockItemColorDTOList = StockItemFormationDAO.getColorList();
        Collections.sort(stockItemColorDTOList, COLOR_ALPHABETICAL_ORDER);
        bindStockItemColorDTOToTable(stockItemColorDTOList);
    }
    private static Comparator<StockItemColorDTO> COLOR_ALPHABETICAL_ORDER = new Comparator<StockItemColorDTO>() {
        public int compare(StockItemColorDTO str1, StockItemColorDTO str2) {
            int res = String.CASE_INSENSITIVE_ORDER.compare(str1.getColorName(), str2.getColorName());
            if (res == 0) {
                res = str1.getColorName().compareTo(str2.getColorName());
            }
            return res;
        }
    };

    private void bindStockItemColorDTOToTable(List<StockItemColorDTO> stockItemColorDTOList) throws Exception {
        if (stockItemColorDTOList != null && !stockItemColorDTOList.isEmpty()) {
            jTableStockItemColorListModel.setRowCount(0);
            for (StockItemColorDTO stockItemColorDTO : stockItemColorDTOList) {
                try {
                    jTableStockItemColorListModel.setRowCount(jTableStockItemColorListModel.getRowCount() + 1);
                    jTableStockItemColorListModel.setValueAt(stockItemColorDTO.getColorID(), jTableStockItemColorListModel.getRowCount() - 1, 0);
                    jTableStockItemColorListModel.setValueAt(stockItemColorDTO.getColorName(), jTableStockItemColorListModel.getRowCount() - 1, 2);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            for (Map.Entry<Integer, Boolean> e : maintainColorSelection.entrySet()) {
                for (int i = 0; i < jTableColor.getRowCount(); i++) {
                    if (e.getKey() == jTableColor.getValueAt(i, 0)) {
                        jTableStockItemColorListModel.setValueAt(e.getValue(), i, 1);
                    }
                }
            }

        } else {
            if (last_Number_In_ColorList != 1) {
                jTableStockItemColorListModel.setRowCount(0);
            }
        }
        last_Number_In_ColorList = 0;
    }

    private void initStockItemFinishTypeList() throws SQLException, Exception {
        stockItemFinishTypeDTOList = StockItemFormationDAO.getFinishTypeList();
        Collections.sort(stockItemFinishTypeDTOList, FINISHTYPE_ALPHABETICAL_ORDER);
        bindStockItemFinishTypeDTOToTable(stockItemFinishTypeDTOList);
    }
    private static Comparator<StockItemFinishTypeDTO> FINISHTYPE_ALPHABETICAL_ORDER = new Comparator<StockItemFinishTypeDTO>() {
        public int compare(StockItemFinishTypeDTO str1, StockItemFinishTypeDTO str2) {
            int res = String.CASE_INSENSITIVE_ORDER.compare(str1.getFinishTypeName(), str2.getFinishTypeName());
            if (res == 0) {
                res = str1.getFinishTypeName().compareTo(str2.getFinishTypeName());
            }
            return res;
        }
    };

    private void bindStockItemFinishTypeDTOToTable(List<StockItemFinishTypeDTO> stockItemFinishTypeDTOList) throws Exception {
        if (stockItemFinishTypeDTOList != null && !stockItemFinishTypeDTOList.isEmpty()) {
            jTableStockItemFinishTypeListModel.setRowCount(0);
            for (StockItemFinishTypeDTO stockItemFinishTypeDTO : stockItemFinishTypeDTOList) {
                try {
                    jTableStockItemFinishTypeListModel.setRowCount(jTableStockItemFinishTypeListModel.getRowCount() + 1);
                    jTableStockItemFinishTypeListModel.setValueAt(stockItemFinishTypeDTO.getFinishTypeID(), jTableStockItemFinishTypeListModel.getRowCount() - 1, 0);
                    jTableStockItemFinishTypeListModel.setValueAt(stockItemFinishTypeDTO.getFinishTypeName(), jTableStockItemFinishTypeListModel.getRowCount() - 1, 2);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                    throw ex;
                }
            }

            for (Map.Entry<Integer, Boolean> e : maintainFinishTypeSelection.entrySet()) {
                for (int i = 0; i < jTableFinishType.getRowCount(); i++) {
                    if (e.getKey() == jTableFinishType.getValueAt(i, 0)) {
                        jTableStockItemFinishTypeListModel.setValueAt(e.getValue(), i, 1);
                    }
                }
            }

        } else {
            if (last_Number_In_FinishList != 1) {
                jTableStockItemFinishTypeListModel.setRowCount(0);
            }
        }
        last_Number_In_FinishList = 0;
    }

    private void initStockItemTypeList() throws SQLException, Exception {
        stockItemTypeDTOList = StockItemFormationDAO.getTypeList();
        Collections.sort(stockItemTypeDTOList, TYPE_ALPHABETICAL_ORDER);
        bindStockItemTypeDTOToTable(stockItemTypeDTOList);
    }
    private static Comparator<StockItemTypeDTO> TYPE_ALPHABETICAL_ORDER = new Comparator<StockItemTypeDTO>() {
        public int compare(StockItemTypeDTO str1, StockItemTypeDTO str2) {
            int res = String.CASE_INSENSITIVE_ORDER.compare(str1.getTypeName(), str2.getTypeName());
            if (res == 0) {
                res = str1.getTypeName().compareTo(str2.getTypeName());
            }
            return res;
        }
    };
    private JPanel jPanelUnder;
    private JTextField jTextFieldUnder;
    private JScrollPane jScrollPaneUnder;
    private JTable jTableUnder;
    private JPanel jPanelUnit;
    private JTextField jTextFieldUnit;
    private JScrollPane jScrollPaneUnit;
    private JTable jTableUnit;

    private void bindStockItemTypeDTOToTable(List<StockItemTypeDTO> stockItemTypeDTOList) throws Exception {
        if (stockItemTypeDTOList != null && !stockItemTypeDTOList.isEmpty()) {
            jTableStockItemTypeListModel.setRowCount(0);
            for (StockItemTypeDTO stockItemFinishTypeDTO : stockItemTypeDTOList) {
                try {
                    jTableStockItemTypeListModel.setRowCount(jTableStockItemTypeListModel.getRowCount() + 1);
                    jTableStockItemTypeListModel.setValueAt(stockItemFinishTypeDTO.getTypeID(), jTableStockItemTypeListModel.getRowCount() - 1, 0);
                    jTableStockItemTypeListModel.setValueAt(stockItemFinishTypeDTO.getTypeName(), jTableStockItemTypeListModel.getRowCount() - 1, 2);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                    throw ex;
                }
            }

            for (Map.Entry<Integer, Boolean> e : maintainTypeSelection.entrySet()) {
                for (int i = 0; i < jTableType.getRowCount(); i++) {
                    if (e.getKey() == jTableType.getValueAt(i, 0)) {
                        jTableStockItemTypeListModel.setValueAt(e.getValue(), i, 1);
                    }
                }
            }

        } else {
            if (last_Number_In_TypeList != 1) {
                jTableStockItemTypeListModel.setRowCount(0);
            }
        }
        last_Number_In_TypeList = 0;
    }

    private void initStockItemTransactionList() throws Exception {

        Set<String> idSet = new HashSet<String>();
        stockItemTransactionList = StockItemFormationDAO.getStockItemList(idSet);
        bindStockItemDTOToTransactionTable(stockItemTransactionList);


    }

    private void bindStockItemDTOToTransactionTable(List<StockItemDTO> stockItemTransactionList) throws Exception {

        categoryIDToNameMap = StockItemFormationDAO.loadCategoryIDToNameMap();
        categoryNameToIDMap = StockItemFormationDAO.loadCategoryNameToIDMap();
        colorIDToNameMap = StockItemFormationDAO.loadColorIDToNameMap();
        colorNameToIDMap = StockItemFormationDAO.loadColorNameToIDMap();
        finishTypeIDToNameMap = StockItemFormationDAO.loadFinishTypeIDToNameMap();
        finishTypeNameToIDMap = StockItemFormationDAO.loadFinishTypeNameToIDMap();
        typeIDToNameMap = StockItemFormationDAO.loadTypeIDToNameMap();
        typeNameToIDMap = StockItemFormationDAO.loadTypeNameToIDMap();

        if (stockItemTransactionList != null && !stockItemTransactionList.isEmpty()) {
            jTableStockItemListModel.setRowCount(0);
            for (StockItemDTO stockItemDTO : stockItemTransactionList) {
                try {
                    int row = jTableStockItemList.getRowCount();
                    jTableStockItemListModel.setRowCount(row + 1);
                    jTableStockItemList.setValueAt(stockItemDTO.getID(), row, 0);
                    jTableStockItemList.setValueAt(stockItemDTO.getName(), row, 1);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                    throw ex;
                }
            }
        } else {
            if (last_Number_In_TransactionList != 1) {
                jTableStockItemListModel.setRowCount(0);
            }
        }
        last_Number_In_TransactionList = 0;
    }

    public void loadEditForm(String id) throws Exception {
        try {
            Set<String> idSet = new HashSet<String>();
            idSet.add(id);
            stockItemDTOList = StockItemFormationDAO.getStockItemList(idSet);
            if (stockItemDTOList != null && !stockItemDTOList.isEmpty()) {
                stockItemDTO = stockItemDTOList.get(0);
                isEdit = true;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    class CheckListItem {

        private String label;
        private boolean isSelected = false;

        public CheckListItem(String label) {
            this.label = label;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }

        public String toString() {
            return label;
        }
    }

    public boolean validateStockItem() throws Exception {
        for (int i = 0; i < jTableDisplay.getRowCount(); i++) {
            for (int j = 0; j < stockItemTransactionList.size(); j++) {
                if (jTableDisplay.getValueAt(i, 0).toString().equalsIgnoreCase(stockItemTransactionList.get(j).getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean validateStockItemCreation() throws Exception {

        try {
            int category = 0;
            for (int i = 0; i < jTableCategory.getRowCount(); i++) {
                if (jTableCategory.getValueAt(i, 1) != null) {
                    category++;
                }
            }

            int length = 0;
            for (int i = 0; i < jTableLength.getRowCount(); i++) {
                if (jTableLength.getValueAt(i, 1) != null) {
                    length++;
                }
            }

            int width = 0;
            for (int i = 0; i < jTableWidth.getRowCount(); i++) {
                if (jTableWidth.getValueAt(i, 1) != null) {
                    width++;
                }
            }

            int thickness = 0;
            for (int i = 0; i < jTableThickness.getRowCount(); i++) {
                if (jTableThickness.getValueAt(i, 1) != null) {
                    thickness++;
                }
            }

            if (category == 0) {
                JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.PLEASE_SELECT_STOCK_ITEM_CATEGORY);
            } else if (length == 0) {
                JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.PLEASE_SELECT_STOCK_ITEM_LENGTH);
            } else if (width == 0) {
                JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.PLEASE_SELECT_STOCK_ITEM_WIDTH);
            } else if (thickness == 0) {
                JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.PLEASE_SELECT_STOCK_ITEM_THICKNESS);
            } else {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return false;
    }

    private Boolean validateForUnder() throws Exception {
        if (!isEdit) {
            for (int i = 0; i < jTableUnder.getRowCount(); i++) {
                if (jTableUnder.getValueAt(i, 1) == null && Boolean.parseBoolean("" + jTableUnder.getValueAt(i, 1)) == false) {
                    JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.ENTER_VALUE_FOR_GROUP_UNDER_FIELD);
                    return false;
                }
            }
        } else {
            for (int i = 0; i < jTableDisplay.getRowCount(); i++) {
                if (jTableDisplay.getValueAt(i, 10).toString().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.ENTER_VALUE_FOR_GROUP_UNDER_FIELD);
                    return false;
                }
            }
        }
        return true;
    }

    private void bindStockItemListDTOToJTableDisplay() throws Exception {
        try {

            jTableDisplayTableModel = (DefaultTableModel) jTableDisplay.getModel();
            jTableDisplayTableModel.setRowCount(0);

            int row = jTableDisplay.getRowCount();
            jTableDisplayTableModel = (DefaultTableModel) jTableDisplay.getModel();
            jTableDisplayTableModel.setRowCount(row + 1);
            jTableDisplay.setValueAt(stockItemDTO.getName().toString(), row, 0);
            jTableDisplay.setValueAt(categoryIDToNameMap.get("" + stockItemDTO.getStockItemCategoryDTO().getCategoryID()), row, 1);
            jTableDisplay.setValueAt(colorIDToNameMap.get("" + stockItemDTO.getStockItemColorDTO().getColorID()), row, 2);
            jTableDisplay.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(stockItemDTO.getLength().toString())), row, 3);
            jTableDisplay.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(stockItemDTO.getWidth().toString())), row, 4);
            jTableDisplay.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(stockItemDTO.getThkness().toString())), row, 5);
            jTableDisplay.setValueAt(finishTypeIDToNameMap.get("" + stockItemDTO.getStockItemFinishTypeDTO().getFinishTypeID()), row, 6);
            jTableDisplay.setValueAt(typeIDToNameMap.get("" + stockItemDTO.getStockItemTypeDTO().getTypeID()), row, 7);
            jTableDisplay.setValueAt(stockItemDTO.getOpeningBal(), row, 8);
            jTableDisplay.setValueAt(stockItemDTO.getID(), row, 9);
            jTableDisplay.setValueAt(stockItemDTO.getUnder(), row, 10);
            if (Integer.parseInt(stockItemDTO.getUnit()) == 0) {
                jTableDisplay.setValueAt("", row, 11);
            } else {
                jTableDisplay.setValueAt(stockItemDTO.getUnit(), row, 11);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    private void jButtonCreateActionPerformed() throws Exception {

        stockItemDTOList = new ArrayList<StockItemDTO>();
        List<List<String>> listToBeSendToFormCombinations = new ArrayList<List<String>>();
        listToBeSendToFormCombinations = getAllPossibleCombinationsOfStockItem();
        stockItemDTOList = bindGUIToDTO(listToBeSendToFormCombinations);
        StockItemFormationDAO.insertStockItem(stockItemDTOList);
        JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.RECORD_SUBMITTED_SUCCESSFULLY);
        jButtonNewActionPerformed();
        bindDTOToJTable();
        formInternalFrameActivated(null);
    }

    private void bindDTOToJTable() throws Exception {
        indexForDeletion = 0;
        while (indexForDeletion < jTableDisplay.getRowCount()) {
            if (jTableDisplay.getValueAt(indexForDeletion, 0) == null) {
                int row = jTableDisplay.getRowCount();
                jTableDisplayTableModel.setRowCount(row - 1);
            }
            indexForDeletion++;
        }


        for (StockItemDTO stockItemDTO : stockItemDTOList) {
            if (temp == 1000) {

                jTableDisplayTableModel = (DefaultTableModel) jTableDisplay.getModel();
                int row = jTableDisplay.getRowCount();
                jTableDisplayTableModel.setRowCount(row + 1);

                jTableDisplay.setValueAt(stockItemDTO.getName(), row, 0);

                if (stockItemDTO.getStockItemCategoryDTO().getCategoryName().toString().equalsIgnoreCase("")) {
                    jTableDisplay.setValueAt("", row, 1);
                } else {
                    jTableDisplay.setValueAt(stockItemDTO.getStockItemCategoryDTO().getCategoryName(), row, 1);
                }

                if (stockItemDTO.getStockItemColorDTO().getColorName().equalsIgnoreCase("")) {
                    jTableDisplay.setValueAt("", row, 2);
                } else {
                    jTableDisplay.setValueAt(stockItemDTO.getStockItemColorDTO().getColorName(), row, 2);
                }

                if (stockItemDTO.getLength().toString().equalsIgnoreCase("")) {
                    jTableDisplay.setValueAt(0D, row, 3);
                } else {
                    jTableDisplay.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(stockItemDTO.getLength().toString())), row, 3);
                }

                if (stockItemDTO.getWidth().toString().equalsIgnoreCase("")) {
                    jTableDisplay.setValueAt(0D, row, 4);
                } else {
                    jTableDisplay.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(stockItemDTO.getWidth().toString())), row, 4);
                }

                if (stockItemDTO.getThkness().toString().equalsIgnoreCase("")) {
                    jTableDisplay.setValueAt(0D, row, 5);
                } else {
                    jTableDisplay.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(stockItemDTO.getThkness().toString())), row, 5);
                }

                if (stockItemDTO.getStockItemFinishTypeDTO().getFinishTypeName().equalsIgnoreCase("")) {
                    jTableDisplay.setValueAt("", row, 6);
                } else {
                    jTableDisplay.setValueAt(stockItemDTO.getStockItemFinishTypeDTO().getFinishTypeName(), row, 6);
                }

                if (stockItemDTO.getStockItemTypeDTO().getTypeName().equalsIgnoreCase("")) {
                    jTableDisplay.setValueAt("", row, 7);
                } else {
                    jTableDisplay.setValueAt(stockItemDTO.getStockItemTypeDTO().getTypeName(), row, 7);
                }

                jTableDisplay.setValueAt(stockItemDTO.getOpeningBal(), row, 8);
                jTableDisplay.setValueAt(stockItemDTO.getUnder(), row, 10);
                if (stockItemDTO.getUnit().equalsIgnoreCase("")) {
                    jTableDisplay.setValueAt("", row, 11);
                } else {
                    jTableDisplay.setValueAt(stockItemDTO.getUnit(), row, 11);
                }

            } else {
                int row = temp;
                jTableDisplay.setValueAt(stockItemDTO.getName(), row, 0);
                if (stockItemDTO.getStockItemCategoryDTO().getCategoryName().equalsIgnoreCase("")) {
                    jTableDisplay.setValueAt("", row, 1);
                } else {
                    jTableDisplay.setValueAt(stockItemDTO.getStockItemCategoryDTO().getCategoryName(), row, 1);
                }

                if (stockItemDTO.getStockItemColorDTO().getColorName().equalsIgnoreCase("")) {
                    jTableDisplay.setValueAt("", row, 2);
                } else {
                    jTableDisplay.setValueAt(stockItemDTO.getStockItemColorDTO().getColorName(), row, 2);
                }

                if (stockItemDTO.getLength().toString().equalsIgnoreCase("")) {
                    jTableDisplay.setValueAt(0D, row, 3);
                } else {
                    jTableDisplay.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(stockItemDTO.getLength().toString())), row, 3);
                }

                if (stockItemDTO.getWidth().toString().equalsIgnoreCase("")) {
                    jTableDisplay.setValueAt(0D, row, 4);
                } else {
                    jTableDisplay.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(stockItemDTO.getWidth().toString())), row, 4);
                }

                if (stockItemDTO.getThkness().toString().equalsIgnoreCase("")) {
                    jTableDisplay.setValueAt(0D, row, 5);
                } else {
                    jTableDisplay.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(stockItemDTO.getThkness().toString())), row, 5);
                }

                if (stockItemDTO.getStockItemFinishTypeDTO().getFinishTypeName().equalsIgnoreCase("")) {
                    jTableDisplay.setValueAt("", row, 6);
                } else {
                    jTableDisplay.setValueAt(stockItemDTO.getStockItemFinishTypeDTO().getFinishTypeName(), row, 6);
                }

                if (stockItemDTO.getStockItemTypeDTO().getTypeName().equalsIgnoreCase("")) {
                    jTableDisplay.setValueAt("", row, 7);
                } else {
                    jTableDisplay.setValueAt(stockItemDTO.getStockItemTypeDTO().getTypeName(), row, 7);
                }

                jTableDisplay.setValueAt(stockItemDTO.getOpeningBal(), row, 8);
                jTableDisplay.setValueAt(stockItemDTO.getUnder(), row, 10);
                if (stockItemDTO.getUnit().equalsIgnoreCase("")) {
                    jTableDisplay.setValueAt("", row, 11);
                } else {
                    jTableDisplay.setValueAt(stockItemDTO.getUnit(), row, 11);
                }

                temp = 1000;
            }
        }

        int i = 0;
        for (Integer integer : StockItemFormationDAO.returningAutoGeneratedKeys) {
            jTableDisplay.setValueAt(integer, i, 9);
            i++;
        }
    }

    private List<StockItemDTO> bindGUIToDTO(List<List<String>> listToBeSendToFormCombinations) throws Exception {

        for (List<String> eachStockItem : listToBeSendToFormCombinations) {

            stockItemDTO = new StockItemDTO();
            stockItemNameForDatabase = "";
            StockItemCategoryDTO stockItemCategoryDTO = new StockItemCategoryDTO();
            StockItemColorDTO stockItemColorDTO = new StockItemColorDTO();
            StockItemFinishTypeDTO stockItemFinishTypeDTO = new StockItemFinishTypeDTO();
            StockItemTypeDTO stockItemTypeDTO = new StockItemTypeDTO();

            Iterator i = eachStockItem.iterator();
            while (i.hasNext()) {

                //Color brought first instead of category as Sachin sir asked to display color name first.
                //So the same changes are also made in getAllPossibleCombinationsOfStockItem().
                String selectedColor = i.next().toString().trim();
                if (!(selectedColor.equalsIgnoreCase(""))) {
                    stockItemColorDTO.setColorID(Integer.parseInt(colorNameToIDMap.get(selectedColor)));
                    stockItemColorDTO.setColorName(colorIDToNameMap.get("" + stockItemColorDTO.getColorID()));
                } else {
                    stockItemColorDTO.setColorID(0);
                }
                stockItemDTO.setStockItemColorDTO(stockItemColorDTO);
                stockItemNameForDatabase = stockItemNameForDatabase + stockItemDTO.getStockItemColorDTO().getColorName();

                String selectedCategory = i.next().toString().trim();
                if (!(selectedCategory.equalsIgnoreCase(""))) {
                    stockItemCategoryDTO.setCategoryID(Integer.parseInt(categoryNameToIDMap.get(selectedCategory)));
                    stockItemCategoryDTO.setCategoryName(categoryIDToNameMap.get("" + stockItemCategoryDTO.getCategoryID()));
                    if (isToBeDisplayedCategory.get(stockItemCategoryDTO.getCategoryName()).equalsIgnoreCase("true")) {
                        stockItemNameForDatabase = stockItemNameForDatabase + " " + stockItemCategoryDTO.getCategoryName();
                    }
                } else {
                    stockItemCategoryDTO.setCategoryID(0);
                }
                stockItemDTO.setStockItemCategoryDTO(stockItemCategoryDTO);

                String selectedLength = i.next().toString().trim();
                if (!(selectedLength.equalsIgnoreCase(""))) {
                    stockItemDTO.setLength(Double.parseDouble(selectedLength));
                } else {
                    stockItemDTO.setLength(Double.parseDouble("" + 0));
                }
                stockItemNameForDatabase = stockItemNameForDatabase + " " + Constants.DECIMAL_FORMAT.format(stockItemDTO.getLength());

                String selectedWidth = i.next().toString().trim();
                if (!(selectedWidth.equalsIgnoreCase(""))) {
                    stockItemDTO.setWidth(Double.parseDouble(selectedWidth));
                } else {
                    stockItemDTO.setWidth(Double.parseDouble("" + 0));
                }
                stockItemNameForDatabase = stockItemNameForDatabase + " x " + Constants.DECIMAL_FORMAT.format(stockItemDTO.getWidth());

//                String selectedColor = i.next().toString().trim();
//                if (!(selectedColor.equalsIgnoreCase(""))) {
//                    stockItemColorDTO.setColorID(Integer.parseInt(colorNameToIDMap.get(selectedColor)));
//                    stockItemColorDTO.setColorName(colorIDToNameMap.get("" + stockItemColorDTO.getColorID()));
//                } else {
//                    stockItemColorDTO.setColorID(0);
//                }
//                stockItemDTO.setStockItemColorDTO(stockItemColorDTO);
//                stockItemNameForDatabase = stockItemNameForDatabase + " " + stockItemDTO.getStockItemColorDTO().getColorName();

                String selectedThickness = i.next().toString().trim();
                if (!(selectedThickness.equalsIgnoreCase(""))) {
                    stockItemDTO.setThkness(Double.parseDouble(selectedThickness));
                } else {
                    stockItemDTO.setThkness(Double.parseDouble("" + 0));
                }

                stockItemNameForDatabase = stockItemNameForDatabase + " x " + Constants.DECIMAL_FORMAT.format(stockItemDTO.getThkness());
//                stockItemNameForDatabase = stockItemNameForDatabase + " " + stockItemDTO.getStockItemColorDTO().getColorName();

                String selectedFinishType = i.next().toString().trim();
                if (!(selectedFinishType.equalsIgnoreCase(""))) {
                    stockItemFinishTypeDTO.setFinishTypeID(Integer.parseInt(finishTypeNameToIDMap.get(selectedFinishType)));
                    stockItemFinishTypeDTO.setFinishTypeName(finishTypeIDToNameMap.get("" + stockItemFinishTypeDTO.getFinishTypeID()));
                    if (isToBeDisplayedFinishType.get("" + stockItemFinishTypeDTO.getFinishTypeName()).equalsIgnoreCase("true")) {
                        stockItemNameForDatabase = stockItemNameForDatabase + " " + stockItemFinishTypeDTO.getFinishTypeName();
                    }
                } else {
                    stockItemFinishTypeDTO.setFinishTypeID(0);
                }
                stockItemDTO.setStockItemFinishTypeDTO(stockItemFinishTypeDTO);

                String selectedType = i.next().toString().trim();
                if (!(selectedType.equalsIgnoreCase(""))) {
                    stockItemTypeDTO.setTypeID(Integer.parseInt(typeNameToIDMap.get(selectedType)));
                    stockItemTypeDTO.setTypeName(typeIDToNameMap.get("" + stockItemTypeDTO.getTypeID()));
                } else {
                    stockItemTypeDTO.setTypeID(0);
                }
                stockItemDTO.setStockItemTypeDTO(stockItemTypeDTO);
                stockItemNameForDatabase = stockItemNameForDatabase + " " + stockItemDTO.getStockItemTypeDTO().getTypeName();

                stockItemDTO.setName(stockItemNameForDatabase.trim());
            }

            for (int j = 0; j < jTableUnder.getRowCount(); j++) {
                if (jTableUnder.getValueAt(j, 1) != null && !((Boolean) jTableUnder.getValueAt(j, 1).equals(false))) {
                    stockItemDTO.setUnder("" + stockGroupNameToIDMap.get("" + jTableUnder.getValueAt(j, 2)));
                }
            }

            for (int j = 0; j < jTableUnit.getRowCount(); j++) {
                if (jTableUnit.getValueAt(j, 1) != null && (!(Boolean) jTableUnit.getValueAt(j, 1).equals(false))) {
                    stockItemDTO.setUnit("" + UOMNameToIDMap.get("" + jTableUnit.getValueAt(j, 2)));
                }
            }

            Calendar currentDate = Calendar.getInstance();
            String dateNow = Constants.simpleDateFormatDatabase.format(currentDate.getTime());
            stockItemDTO.setStockitem_Date(dateNow);

            stockItemDTOList.add(stockItemDTO);
        }
        return stockItemDTOList;
    }

    private List<List<String>> getAllPossibleCombinationsOfStockItem() throws Exception {

        List<List<String>> listToBeSendToFormCombinations = new ArrayList<List<String>>();

        try {


            //Getting All Selected Colors
            List<String> selectedColor = new ArrayList<String>();
            for (int i = 0; i < jTableColor.getRowCount(); i++) {
                if (jTableColor.getValueAt(i, 1) != null && (!(Boolean) jTableColor.getValueAt(i, 1).equals(false))) {
                    selectedColor.add(jTableColor.getValueAt(i, 2).toString().trim());
                }
            }
            if (selectedColor.size() == 0) {
                selectedColor.add("");
            }
            listToBeSendToFormCombinations.add(selectedColor);

            //Getting All Selected Categories
            List<String> selectedCategorys = new ArrayList<String>();
            for (int i = 0; i < jTableCategory.getRowCount(); i++) {
                if (jTableCategory.getValueAt(i, 1) != null && (!(Boolean) jTableCategory.getValueAt(i, 1).equals(false))) {
                    selectedCategorys.add(jTableCategory.getValueAt(i, 2).toString().trim());
                }
                isToBeDisplayedCategory.put("" + jTableCategory.getValueAt(i, 2), "" + jTableCategory.getValueAt(i, 3));
            }
            if (selectedCategorys.size() == 0) {
                selectedCategorys.add("");
            }
            listToBeSendToFormCombinations.add(selectedCategorys);

            //Getting All Selected Lengths
            List<String> selectedLengths = new ArrayList<String>();
            for (int i = 0; i < jTableLength.getRowCount(); i++) {
                if (jTableLength.getValueAt(i, 1) != null && (!(Boolean) jTableLength.getValueAt(i, 1).equals(false))) {
                    selectedLengths.add(jTableLength.getValueAt(i, 2).toString().trim());
                }
            }
            if (selectedLengths.size() == 0) {
                selectedLengths.add("");
            }
            listToBeSendToFormCombinations.add(selectedLengths);

            //Getting All Selected Widths
            List<String> selectedWidths = new ArrayList<String>();
            for (int i = 0; i < jTableWidth.getRowCount(); i++) {
                if (jTableWidth.getValueAt(i, 1) != null && (!(Boolean) jTableWidth.getValueAt(i, 1).equals(false))) {
                    selectedWidths.add(jTableWidth.getValueAt(i, 2).toString().trim());
                }
            }
            if (selectedWidths.size() == 0) {
                selectedWidths.add("");
            }
            listToBeSendToFormCombinations.add(selectedWidths);

//            //Getting All Selected Colors
//            List<String> selectedColor = new ArrayList<String>();
//            for (int i = 0; i < jTableColor.getRowCount(); i++) {
//                if (jTableColor.getValueAt(i, 1) != null && (!(Boolean) jTableColor.getValueAt(i, 1).equals(false))) {
//                    selectedColor.add(jTableColor.getValueAt(i, 2).toString().trim());
//                }
//            }
//            if (selectedColor.size() == 0) {
//                selectedColor.add("");
//            }
//            listToBeSendToFormCombinations.add(selectedColor);

            //Getting All Selected Thicknesses
            List<String> selectedThickness = new ArrayList<String>();
            for (int i = 0; i < jTableThickness.getRowCount(); i++) {
                if (jTableThickness.getValueAt(i, 1) != null && (!(Boolean) jTableThickness.getValueAt(i, 1).equals(false))) {
                    selectedThickness.add(jTableThickness.getValueAt(i, 2).toString().trim());
                }
            }
            if (selectedThickness.size() == 0) {
                selectedThickness.add("");
            }
            listToBeSendToFormCombinations.add(selectedThickness);

            //Getting All Selected FinishTypes
            List<String> selectedFinishType = new ArrayList<String>();
            for (int i = 0; i < jTableFinishType.getRowCount(); i++) {
                if (jTableFinishType.getValueAt(i, 1) != null && (!(Boolean) jTableFinishType.getValueAt(i, 1).equals(false))) {
                    selectedFinishType.add(jTableFinishType.getValueAt(i, 2).toString().trim());
                }
                isToBeDisplayedFinishType.put("" + jTableFinishType.getValueAt(i, 2), "" + jTableFinishType.getValueAt(i, 3));
            }
            if (selectedFinishType.size() == 0) {
                selectedFinishType.add("");
            }
            listToBeSendToFormCombinations.add(selectedFinishType);

            //Getting All Selected Types
            List<String> selectedType = new ArrayList<String>();
            for (int i = 0; i < jTableType.getRowCount(); i++) {
                if (jTableType.getValueAt(i, 1) != null && (!(Boolean) jTableType.getValueAt(i, 1).equals(false))) {
                    selectedType.add(jTableType.getValueAt(i, 2).toString().trim());
                }
            }
            if (selectedType.size() == 0) {
                selectedType.add("");
            }
            listToBeSendToFormCombinations.add(selectedType);

            //Generating All Possible Combinations
            listToBeSendToFormCombinations = Combinations.generateAllPossibleCombinations(listToBeSendToFormCombinations);

        } catch (Exception ex) {
            Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listToBeSendToFormCombinations;
    }

    private void jButtonSubmitActionPerformed() throws Exception {

        stockItemDTOList = new ArrayList<StockItemDTO>();

        for (int i = 0; i < jTableDisplay.getRowCount(); i++) {
            stockItemDTO = new StockItemDTO();
            StockItemCategoryDTO stockItemCategoryDTO = new StockItemCategoryDTO();
            StockItemColorDTO stockItemColorDTO = new StockItemColorDTO();
            StockItemFinishTypeDTO stockItemFinishTypeDTO = new StockItemFinishTypeDTO();
            StockItemTypeDTO stockItemTypeDTO = new StockItemTypeDTO();

            stockItemDTO.setName(jTableDisplay.getValueAt(i, 0).toString());
            if (!(jTableDisplay.getValueAt(i, 1).toString().equalsIgnoreCase(""))) {
                stockItemCategoryDTO.setCategoryID(Integer.parseInt(categoryNameToIDMap.get("" + jTableDisplay.getValueAt(i, 1))));
            } else {
                stockItemCategoryDTO.setCategoryID(0);
            }
            stockItemCategoryDTO.setCategoryName(categoryIDToNameMap.get("" + stockItemCategoryDTO.getCategoryID()));
            stockItemDTO.setStockItemCategoryDTO(stockItemCategoryDTO);

            if (!(jTableDisplay.getValueAt(i, 2).toString().equalsIgnoreCase(""))) {
                stockItemColorDTO.setColorID(Integer.parseInt(colorNameToIDMap.get("" + jTableDisplay.getValueAt(i, 2))));
            } else {
                stockItemColorDTO.setColorID(0);
            }
            stockItemColorDTO.setColorName(colorIDToNameMap.get("" + stockItemColorDTO.getColorID()));
            stockItemDTO.setStockItemColorDTO(stockItemColorDTO);

            stockItemDTO.setLength(Double.parseDouble("" + jTableDisplay.getValueAt(i, 3)));

            stockItemDTO.setWidth(Double.parseDouble("" + jTableDisplay.getValueAt(i, 4)));

            stockItemDTO.setThkness(Double.parseDouble("" + jTableDisplay.getValueAt(i, 5)));

            if (!(jTableDisplay.getValueAt(i, 6).toString().equalsIgnoreCase(""))) {
                stockItemFinishTypeDTO.setFinishTypeID(Integer.parseInt(finishTypeNameToIDMap.get("" + jTableDisplay.getValueAt(i, 6))));
            } else {
                stockItemFinishTypeDTO.setFinishTypeID(0);
            }
            stockItemFinishTypeDTO.setFinishTypeName(finishTypeIDToNameMap.get("" + stockItemFinishTypeDTO.getFinishTypeID()));
            stockItemDTO.setStockItemFinishTypeDTO(stockItemFinishTypeDTO);

            if (!(jTableDisplay.getValueAt(i, 7).toString().equalsIgnoreCase(""))) {
                stockItemTypeDTO.setTypeID(Integer.parseInt(typeNameToIDMap.get("" + jTableDisplay.getValueAt(i, 7))));
            } else {
                stockItemTypeDTO.setTypeID(0);
            }
            stockItemTypeDTO.setTypeName(typeIDToNameMap.get("" + stockItemTypeDTO.getTypeID()));
            stockItemDTO.setStockItemTypeDTO(stockItemTypeDTO);

            stockItemDTO.setOpeningBal(Double.parseDouble("" + jTableDisplay.getValueAt(i, 8)));

            stockItemDTO.setID(Long.parseLong("" + jTableDisplay.getValueAt(i, 9)));

            stockItemDTO.setUnder("" + jTableDisplay.getValueAt(i, 10));

            stockItemDTO.setUnit("" + jTableDisplay.getValueAt(i, 11));

            Calendar currentDate = Calendar.getInstance();
            String dateNow = Constants.simpleDateFormatDatabase.format(currentDate.getTime());

            stockItemDTO.setStockitem_Date(dateNow);

            stockItemDTOList.add(stockItemDTO);
        }

        if (validateForUnder()) {
            StockItemFormationDAO.updateStockItem(stockItemDTOList);
            JOptionPane.showMessageDialog(StockItemFormFinal.this, Label.RECORD_SUBMITTED_SUCCESSFULLY);
            jButtonNewActionPerformed();
            formInternalFrameActivated(null);
        }

    }

    private void jButtonBackActionPerformed() throws Exception {
        try {
            MainClass.setstaticvar();
            MainClass m = new MainClass();
            m.menuselection(1);
            StockItemFormFinal.this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jButtonNewActionPerformed() throws Exception {
        try {
            isEdit = false;

            stockItemDTO = new StockItemDTO();

            maintainCategorySelection = new HashMap<Integer, Boolean>();
            maintainColorSelection = new HashMap<Integer, Boolean>();
            maintainFinishTypeSelection = new HashMap<Integer, Boolean>();
            maintainLengthSelection = new HashMap<Integer, Boolean>();
            maintainThicknessSelection = new HashMap<Integer, Boolean>();
            maintainTypeSelection = new HashMap<Integer, Boolean>();
            maintainUnderSelection = new HashMap<Integer, Boolean>();
            maintainWidthSelection = new HashMap<Integer, Boolean>();

            jTextFieldCategory.setText("");
            jTextFieldLength.setText("");
            jTextFieldWidth.setText("");
            jTextFieldColor.setText("");
            jTextFieldThickness.setText("");
            jTextFieldFinishType.setText("");
            jTextFieldType.setText("");
            jTextFieldUnder.setText("");
            jTextFieldUnit.setText("");

            for (int i = 0; i < jTableCategory.getRowCount(); i++) {
                if (jTableCategory.getValueAt(i, 1) != null) {
                    jTableCategory.setValueAt(null, i, 1);
                }
            }

            for (int i = 0; i < jTableLength.getRowCount(); i++) {
                if (jTableLength.getValueAt(i, 1) != null) {
                    jTableLength.setValueAt(null, i, 1);
                }
            }

            for (int i = 0; i < jTableWidth.getRowCount(); i++) {
                if (jTableWidth.getValueAt(i, 1) != null) {
                    jTableWidth.setValueAt(null, i, 1);
                }
            }

            for (int i = 0; i < jTableColor.getRowCount(); i++) {
                if (jTableColor.getValueAt(i, 1) != null) {
                    jTableColor.setValueAt(null, i, 1);
                }
            }

            for (int i = 0; i < jTableThickness.getRowCount(); i++) {
                if (jTableThickness.getValueAt(i, 1) != null) {
                    jTableThickness.setValueAt(null, i, 1);
                }
            }

            for (int i = 0; i < jTableFinishType.getRowCount(); i++) {
                if (jTableFinishType.getValueAt(i, 1) != null) {
                    jTableFinishType.setValueAt(null, i, 1);
                }
            }

            for (int i = 0; i < jTableType.getRowCount(); i++) {
                if (jTableType.getValueAt(i, 1) != null) {
                    jTableType.setValueAt(null, i, 1);
                }
            }

            jTableDisplayTableModel = (DefaultTableModel) jTableDisplay.getModel();
            jTableDisplayTableModel.setRowCount(0);
            jTableStockItemList.clearSelection();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    private void initStockGroup() throws Exception {
        stockGroupIDToNameMap = StockItemFormationDAO.loadStockGroupIDToNameMap();
        stockGroupNameToIDMap = StockItemFormationDAO.loadStockGroupNameToIDMap();

        StockGroupVector.clear();
        for (String str : stockGroupNameToIDMap.keySet()) {
            StockGroupVector.add(str);
        }

        Collections.sort(StockGroupVector, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareToIgnoreCase(rhs);
            }
        });

        bindStockItemUnderToTable(stockGroupIDToNameMap);
    }

    private void bindStockItemUnderToTable(Map<String, String> stockItemUnderMap) throws Exception {

        if (stockItemUnderMap != null && !stockItemUnderMap.isEmpty()) {
            jTableStockItemUnderListModel.setRowCount(0);
            for (Map.Entry<String, String> e : stockItemUnderMap.entrySet()) {
                try {
                    jTableStockItemUnderListModel.setRowCount(jTableStockItemUnderListModel.getRowCount() + 1);
                    jTableStockItemUnderListModel.setValueAt(e.getKey(), jTableStockItemUnderListModel.getRowCount() - 1, 0);
                    jTableStockItemUnderListModel.setValueAt(e.getValue(), jTableStockItemUnderListModel.getRowCount() - 1, 2);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                    throw ex;
                }
            }

            for (Map.Entry<Integer, Boolean> e : maintainUnderSelection.entrySet()) {
                for (int i = 0; i < jTableUnder.getRowCount(); i++) {
                    if (Integer.parseInt(e.getKey().toString()) == Integer.parseInt(jTableUnder.getValueAt(i, 0).toString())) {
                        jTableStockItemUnderListModel.setValueAt(e.getValue(), i, 1);
                    }
                }
            }

        } else {
            if (last_Number_In_UnderList != 1) {
                jTableStockItemUnderListModel.setRowCount(0);
            }
        }
        last_Number_In_UnderList = 0;

    }

    private void initUOM() throws Exception {
        UOMIDToNameMap = StockItemFormationDAO.loadUnitOfMeasureIDToNameMap();
        UOMNameToIDMap = StockItemFormationDAO.loadUnitOfMeasureNameToIDMap();

        UnitOfMeasureVector.clear();
        for (String str : UOMNameToIDMap.keySet()) {
            UnitOfMeasureVector.add(str);
        }

        Collections.sort(UnitOfMeasureVector, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareToIgnoreCase(rhs);
            }
        });

        bindStockItemUnitToTable(UOMIDToNameMap);
    }

    private void bindStockItemUnitToTable(Map<String, String> stockItemUnitMap) throws Exception {

        if (stockItemUnitMap != null && !stockItemUnitMap.isEmpty()) {
            jTableStockItemUnitListModel.setRowCount(0);
            for (Map.Entry<String, String> e : stockItemUnitMap.entrySet()) {
                try {
                    jTableStockItemUnitListModel.setRowCount(jTableStockItemUnitListModel.getRowCount() + 1);
                    jTableStockItemUnitListModel.setValueAt(e.getKey(), jTableStockItemUnitListModel.getRowCount() - 1, 0);
                    jTableStockItemUnitListModel.setValueAt(e.getValue(), jTableStockItemUnitListModel.getRowCount() - 1, 2);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemFormFinal.class.getName()).log(Level.SEVERE, null, ex);
                    throw ex;
                }
            }

            for (Map.Entry<Integer, Boolean> e : maintainUnitSelection.entrySet()) {
                for (int i = 0; i < jTableUnit.getRowCount(); i++) {
                    if (Integer.parseInt(e.getKey().toString()) == Integer.parseInt(jTableUnit.getValueAt(i, 0).toString())) {
                        jTableStockItemUnitListModel.setValueAt(e.getValue(), i, 1);
                    }
                }
            }

        } else {
            if (last_Number_In_UnitList != 1) {
                jTableStockItemUnitListModel.setRowCount(0);
            }
        }
        last_Number_In_UnitList = 0;

    }

    class MyItemListenerForCategory implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            Object source = e.getSource();
            if (source instanceof AbstractButton == false) {
                return;
            }
            boolean checked = e.getStateChange() == ItemEvent.SELECTED;
            for (int x = 0, y = jTableCategory.getRowCount(); x < y; x++) {
                jTableCategory.setValueAt(new Boolean(checked), x, 1);
            }
        }
    }

    class MyItemListenerForLength implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            Object source = e.getSource();
            if (source instanceof AbstractButton == false) {
                return;
            }
            boolean checked = e.getStateChange() == ItemEvent.SELECTED;
            for (int x = 0, y = jTableLength.getRowCount(); x < y; x++) {
                jTableLength.setValueAt(new Boolean(checked), x, 1);
            }
        }
    }

    class MyItemListenerForWidth implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            Object source = e.getSource();
            if (source instanceof AbstractButton == false) {
                return;
            }
            boolean checked = e.getStateChange() == ItemEvent.SELECTED;
            for (int x = 0, y = jTableWidth.getRowCount(); x < y; x++) {
                jTableWidth.setValueAt(new Boolean(checked), x, 1);
            }
        }
    }

    class MyItemListenerForColor implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            Object source = e.getSource();
            if (source instanceof AbstractButton == false) {
                return;
            }
            boolean checked = e.getStateChange() == ItemEvent.SELECTED;
            for (int x = 0, y = jTableColor.getRowCount(); x < y; x++) {
                jTableColor.setValueAt(new Boolean(checked), x, 1);
            }
        }
    }

    class MyItemListenerForThickness implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            Object source = e.getSource();
            if (source instanceof AbstractButton == false) {
                return;
            }
            boolean checked = e.getStateChange() == ItemEvent.SELECTED;
            for (int x = 0, y = jTableThickness.getRowCount(); x < y; x++) {
                jTableThickness.setValueAt(new Boolean(checked), x, 1);
            }
        }
    }

    class MyItemListenerForFinishType implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            Object source = e.getSource();
            if (source instanceof AbstractButton == false) {
                return;
            }
            boolean checked = e.getStateChange() == ItemEvent.SELECTED;
            for (int x = 0, y = jTableFinishType.getRowCount(); x < y; x++) {
                jTableFinishType.setValueAt(new Boolean(checked), x, 1);
            }
        }
    }

    class MyItemListenerForType implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            Object source = e.getSource();
            if (source instanceof AbstractButton == false) {
                return;
            }
            boolean checked = e.getStateChange() == ItemEvent.SELECTED;
            for (int x = 0, y = jTableType.getRowCount(); x < y; x++) {
                jTableType.setValueAt(new Boolean(checked), x, 1);
            }
        }
    }
}

class CheckBoxHeader extends JCheckBox implements TableCellRenderer, MouseListener {

    protected CheckBoxHeader rendererComponent;
    protected int column;
    protected boolean mousePressed = false;

    public CheckBoxHeader(ItemListener itemListener) {
        rendererComponent = this;
        rendererComponent.addItemListener(itemListener);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (table != null) {
            JTableHeader header = table.getTableHeader();
            if (header != null) {
                rendererComponent.setForeground(header.getForeground());
                rendererComponent.setBackground(header.getBackground());
                rendererComponent.setFont(header.getFont());
                header.addMouseListener(rendererComponent);
            }
        }
        setColumn(column);
        return rendererComponent;
    }

    protected void setColumn(int column) {
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    protected void handleClickEvent(MouseEvent e) {
        if (mousePressed) {
            mousePressed = false;
            JTableHeader header = (JTableHeader) (e.getSource());
            JTable tableView = header.getTable();
            TableColumnModel columnModel = tableView.getColumnModel();
            int viewColumn = columnModel.getColumnIndexAtX(e.getX());
            int column = tableView.convertColumnIndexToModel(viewColumn);

            if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {
                doClick();
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        handleClickEvent(e);
        ((JTableHeader) e.getSource()).repaint();
    }

    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
