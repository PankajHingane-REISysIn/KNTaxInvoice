/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.account.unitofmeasure;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import gen.ImpExp.InventoryTagHelper;
import gen.account.stockitem.StockItemDAO;
import gen.account.stockitem.StockItemDTO;
import gen.account.stockitem.StockItemForm1;
import gen.accountvoucher.sale.SaleForm;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class UnitOfMeasureForm extends javax.swing.JInternalFrame implements
        java.awt.event.ActionListener {

    private List<UnitOfMeasureDTO> UOMDTOsNameList;
    private UnitOfMeasureDTO UOMDTO = new UnitOfMeasureDTO();
    private UnitOfMeasureDTO editModeUOMDTO = new UnitOfMeasureDTO();
    private Boolean isEdit = false;
    Integer selectedRow = 0;
    private int currentFocusValue = 0;
    //  private List<UnitOfMeasureDTO> UOMDTONameList;
    private Vector<String> UOMVector = new Vector<String>();
    private Map<String, String> UOMNameMap;
    private Map<String, String> UOMNameListMap = new HashMap<String, String>();
    private boolean hide_flag = false;
    String editModeUOMName = "";

    public UnitOfMeasureForm() {
        try {
            initComponents();
            initilize();
            initUOM();
            setClosable(true);
        } catch (Exception ex) {
            Logger.getLogger(
                    gen.account.stockgroup.StockGroupForm.class.getName()).log(
                    Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(UnitOfMeasureForm.this,
                    ex.getMessage());
        }
    }

    private void initComponents() throws PropertyVetoException {

        setSelected(true);
        setResizable(true);
        setTitle("Measurement Unit Information Details");
        setClosable(true);
        setBounds(100, 100, 791, 539);

        jMainPanel = new JPanel();
        getContentPane().add(jMainPanel, BorderLayout.CENTER);

        JPanel availableMeasurementUnitsPanel = new JPanel();
        availableMeasurementUnitsPanel.setBorder(new TitledBorder(null,
                "Available Measurement Units", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));

        JPanel measurementUnitsInformationPanel = new JPanel();
        measurementUnitsInformationPanel.setBorder(new TitledBorder(null,
                "Measurement Units Information", TitledBorder.LEADING,
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
                measurementUnitsInformationPanel,
                GroupLayout.PREFERRED_SIZE,
                512,
                GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(
                ComponentPlacement.RELATED)
                .addComponent(
                availableMeasurementUnitsPanel,
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
                measurementUnitsInformationPanel,
                GroupLayout.DEFAULT_SIZE,
                429,
                Short.MAX_VALUE)
                .addComponent(
                availableMeasurementUnitsPanel,
                GroupLayout.DEFAULT_SIZE,
                429,
                Short.MAX_VALUE))
                .addPreferredGap(
                ComponentPlacement.RELATED)
                .addComponent(controlsPanel,
                GroupLayout.PREFERRED_SIZE, 54,
                GroupLayout.PREFERRED_SIZE)
                .addGap(10)));
        measurementUnitsInformationPanel
                .setLayout(new MigLayout(
                "",
                "[150px:150px:150px,grow,fill][25px:25px:25px,grow,shrink 50,fill][225px:225px:225px,grow,fill][grow]",
                "[][][][][][][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][]"));

        labelUnder = new JLabel("Type");
        measurementUnitsInformationPanel.add(labelUnder,
                "cell 0 6,alignx trailing");

        jComboBoxUnder = new JComboBox();
        jComboBoxUnder.setPrototypeDisplayValue("xxxxxx");
        tfUnderField = (JTextField) jComboBoxUnder.getEditor()
                .getEditorComponent();
        measurementUnitsInformationPanel.add(jComboBoxUnder, "cell 2 6,growx");

        labelAlias = new JLabel("Symbol");
        measurementUnitsInformationPanel.add(labelAlias, "cell 0 8");

        jTextFieldSymbol = new JTextField();
        measurementUnitsInformationPanel
                .add(jTextFieldSymbol, "cell 2 8,growx");
        jTextFieldSymbol.setColumns(10);

        labelName = new JLabel("Formal Name");
        measurementUnitsInformationPanel.add(labelName, "cell 0 10");

        jTextFieldName = new JTextField();
        measurementUnitsInformationPanel.add(jTextFieldName, "cell 2 10,growx");
        jTextFieldName.setColumns(10);

        labelDecimalPlaces = new JLabel("Number Of Decimal Places");
        measurementUnitsInformationPanel.add(labelDecimalPlaces, "cell 0 12");

        jTextFieldNODPlaces = new JTextField();
        measurementUnitsInformationPanel.add(jTextFieldNODPlaces,
                "cell 2 12,growx");
	jTextFieldNODPlaces.setTransferHandler(null);
        jTextFieldNODPlaces.setColumns(10);
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
            public void actionPerformed(ActionEvent e) {
                dataExport();
            }
        });
        jButtonExport.setMnemonic('X');
        controlsPanel.add(jButtonExport, "cell 4 1,growx");
        availableMeasurementUnitsPanel.setLayout(new MigLayout("",
                "[][][grow]", "[][][grow]"));

        labelSearch = new JLabel("Search");
        availableMeasurementUnitsPanel.add(labelSearch,
                "cell 1 0,alignx trailing");

        jTextFieldSearch = new JTextField();
        availableMeasurementUnitsPanel.add(jTextFieldSearch,
                "cell 0 1 3 1,growx");
        jTextFieldSearch.setColumns(10);

        JScrollPane pane1 = new JScrollPane();
        availableMeasurementUnitsPanel.add(pane1, "cell 0 2 3 1,grow");

        jTableUOMList = new JTable();
        jTableUOMList.setBorder(new LineBorder(new Color(0, 0, 0)));
        jTableUOMList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        String col1[] = {Label.UNITOFMEASURE_NAME};
        String data1[][] = {{"", ""}};

        jTableUOMListModel = new DefaultTableModel(data1, col1) {
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

        jTableUOMList = new JTable() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;
        };
        
        jTableUOMList.setModel(jTableUOMListModel);
        JTableHeader header1 = jTableUOMList.getTableHeader();
        jTableUOMList.getTableHeader().setReorderingAllowed(false);
        header1.setBackground(Color.yellow);
        header1.setFont(font);
        jTableUOMListModel = (DefaultTableModel) jTableUOMList.getModel();
        jTableUOMListModel.setRowCount(0);
        jTableUOMListModel.setColumnCount(2);
        jTableUOMList.setBorder(javax.swing.BorderFactory
                .createLineBorder(new java.awt.Color(0, 0, 0)));
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
                jTableUOMListModel);
        jTableUOMList.setRowSorter(sorter);
        jTableUOMList.getColumnModel().getColumn(1).setPreferredWidth(0);
        jTableUOMList.getColumnModel().getColumn(1).setMinWidth(0);
        jTableUOMList.getColumnModel().getColumn(1).setMaxWidth(0);
        pane1.setViewportView(jTableUOMList);
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

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("Focus requested to name field");
                            tfUnderField.setFocusable(true);
                            tfUnderField.setRequestFocusEnabled(true);
                            tfUnderField.requestFocus();
                        } catch (Exception e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(
                                    UnitOfMeasureForm.this, e.getMessage());
                        }
                    }
                });

                try {
                    formInternalFrameActivated(evt);
                } catch (Exception ex) {
                    Logger.getLogger(
                            gen.account.stockgroup.StockGroupForm.class
                            .getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(UnitOfMeasureForm.this,
                            ex.getMessage());
                }

            }

            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                // throw new
                // UnsupportedOperationException("Not supported yet.");
                try {
                    formInternalFrameOpened(e);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(
                            gen.account.stockgroup.StockGroupForm.class
                            .getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(UnitOfMeasureForm.this,
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
                    JOptionPane.showMessageDialog(UnitOfMeasureForm.this,
                            ex.getMessage());
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
                            setUOMModel(new DefaultComboBoxModel(UOMVector), "");
                        } else {
                            DefaultComboBoxModel m = Util.getSuggestedModel(
                                    UOMVector, text);
                            if (m.getSize() == 0 || hide_flag) {
                                jComboBoxUnder.hidePopup();
                                hide_flag = false;
                            } else {
                                // setAccountLedgetModel(m, text);
                                setUOMModel(m, text);
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
                            try {
                                // setFocus(event);
                                tfUnderFieldKeyPressed(event);
                            } catch (PropertyVetoException ex) {
                                Logger.getLogger(
                                        UnitOfMeasureForm.class.getName()).log(
                                        Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(
                                        UnitOfMeasureForm.this, ex.getMessage());
                            }
                        }
                    }
                });
            }
        });
        // tfUnderField.addKeyListener(new java.awt.event.KeyAdapter() {
        // @Override
        // public void keyPressed(java.awt.event.KeyEvent evt) {
        // tfUnderFieldKeyPressed(evt);
        // }
        //
        // @Override
        // public void keyTyped(java.awt.event.KeyEvent evt) {
        // EventQueue.invokeLater(new Runnable() {
        // @Override
        // public void run() {
        // String text = tfUnderField.getText();
        // if (text.length() == 0) {
        // jComboBoxUnder.hidePopup();
        // setUOMModel(new DefaultComboBoxModel(UOMVector), "");
        // } else {
        // DefaultComboBoxModel m = Util.getSuggestedModel(UOMVector, text);
        // if (m.getSize() == 0 || hide_flag) {
        // jComboBoxUnder.hidePopup();
        // hide_flag = false;
        // } else {
        // setUOMModel(m, text);
        // jComboBoxUnder.showPopup();
        // }
        // }
        // }
        // });
        // }
        //
        // @Override
        // public void keyReleased(java.awt.event.KeyEvent evt) {
        // }
        // });

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

        jTextFieldSymbol.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldAliasFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldAliasFocusLost(evt);
            }
        });
        jTextFieldSymbol.addKeyListener(new java.awt.event.KeyAdapter() {
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

        jTextFieldNODPlaces.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNODPlacesFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldNODPlacesFocusLost(evt);
            }
        });
        jTextFieldNODPlaces.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldNODPlacesKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterToNumbers(evt, jTextFieldNODPlaces);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(UnitOfMeasureForm.this,
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
                    JOptionPane.showMessageDialog(UnitOfMeasureForm.this,
                            ex.getMessage());
                }
            }
        });

        jTableUOMList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jTableUOMListMouseClicked(evt);
                } catch (Exception ex) {
                    Logger.getLogger(UnitOfMeasureForm.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(UnitOfMeasureForm.this,
                            ex.getMessage());
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }
        });

        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    jButtonBackActionPerformed(evt);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(UnitOfMeasureForm.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(UnitOfMeasureForm.this,
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
                    Logger.getLogger(UnitOfMeasureForm.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(UnitOfMeasureForm.this,
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
                    JOptionPane.showMessageDialog(UnitOfMeasureForm.this,
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
                    Logger.getLogger(UnitOfMeasureForm.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(UnitOfMeasureForm.this,
                            ex.getMessage());
                }
            }
        });

        jButtonBack.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                try {
                    jButtonBackKeyPressed(evt);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(UnitOfMeasureForm.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(UnitOfMeasureForm.this,
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
                    Logger.getLogger(UnitOfMeasureForm.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(UnitOfMeasureForm.this,
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

    private void tfUnderFieldFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        tfUnderField.selectAll();
        currentFocusValue = 0;
        String text = tfUnderField.getText();
        if (text.length() == 0) {
            jComboBoxUnder.hidePopup();
            setUOMModel(new DefaultComboBoxModel(UOMVector), "");
        } else {
            DefaultComboBoxModel m = Util.getSuggestedModel(UOMVector, text);
            if (m.getSize() == 0 || hide_flag) {
                jComboBoxUnder.hidePopup();
                hide_flag = false;
            } else {
                setUOMModel(m, text);
                jComboBoxUnder.showPopup();
            }

        }
    }

    private void tfUnderFieldFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void tfUnderFieldKeyPressed(java.awt.event.KeyEvent evt)
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
        jTextFieldSymbol.selectAll();
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

    private void jTextFieldNameFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        jTextFieldName.selectAll();
        currentFocusValue = 2;
    }

    private void jTextFieldNameFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextFieldNameKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:

        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        // if (code == KeyEvent.VK_ESCAPE) {
        // jButtonBackActionPerformed(null);
        // }
    }

    private void jTextFieldNODPlacesFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
	jTextFieldNODPlaces.selectAll();
        currentFocusValue = 3;
        if (jTextFieldNODPlaces.getText().toString().trim().equals("0")) {
            jTextFieldNODPlaces.setText("");
        }

    }

    private void jTextFieldNODPlacesFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        if (jTextFieldNODPlaces.getText().toString().trim().isEmpty()) {
            jTextFieldNODPlaces.setText("" + 0);
        }
    }

    private void jTextFieldNODPlacesKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:

        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        if (code == KeyEvent.VK_ENTER) {
            jButtonSubmit.requestFocus();
        }
    }

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt)
            throws PropertyVetoException {

        MainClass.setstaticvar();
        MainClass m = new MainClass();
        m.menuselection(1);
        this.setClosed(true);
    }

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt)
            throws Exception {
        jButtonDelete.setEnabled(false);
        newButton();
        clearFieldData();
        tfUnderField.requestFocus();
    }

    private void jButtonSubmitActionPerformed(java.awt.event.ActionEvent evt)
            throws Exception {
        if (validateData()) {
            submit();
        }
    }

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt)
            throws SQLException, Exception {
        delete();
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

            jTextFieldNODPlaces.requestFocus();

        }
    }

    private void jButtonNewKeyPressed(java.awt.event.KeyEvent evt)
            throws Exception {
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

            jTextFieldNODPlaces.requestFocus();

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

            jTextFieldNODPlaces.requestFocus();

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
            jTextFieldNODPlaces.requestFocus();
        }
    }

    private void jTableUOMListMouseClicked(java.awt.event.MouseEvent evt)
            throws SQLException, ParseException, Exception {

        editModeUOMName = jTableUOMList.getValueAt(
                jTableUOMList.getSelectedRow(), 0).toString();
        String id = jTableUOMList.getValueAt(jTableUOMList.getSelectedRow(), 1)
                .toString();
        loadEditForm(id);
        jButtonDelete.setEnabled(true);
        isEdit = true;
        tfUnderField.requestFocus();
    }

    private void jTextFieldSearchKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (jTableUOMList.getRowCount() > 0) {
                jTableUOMList.requestFocus();
                jTableUOMList.changeSelection(0, 0, false, false);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (jTableUOMList.getRowCount() > 0) {
                jTableUOMList.requestFocus();
                jTableUOMList.changeSelection(0, 0, false, false);
            }
        }
    }

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt)
            throws SQLException {

        List<UnitOfMeasureDTO> stockGroupList = new ArrayList<UnitOfMeasureDTO>();
        String text = jTextFieldSearch.getText().trim();

        for (UnitOfMeasureDTO UOMDTOEntity : UOMDTOsNameList) {
            if (UOMDTOEntity.getUnitOfMeasure_FormalName().toLowerCase()
                    .matches("(.*)" + text.toLowerCase() + "(.*)")) {
                stockGroupList.add(UOMDTOEntity);
            }
        }
        bindDTOTojTableUOMList(stockGroupList);

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
    private JLabel labelDecimalPlaces;
    private JTextField jTextFieldNODPlaces;
    private JLabel labelAlias;
    private JTextField jTextFieldSymbol;
    private JTextField jTextFieldSearch;
    private JLabel labelUnder;
    private JLabel labelSearch;
    private JComboBox jComboBoxUnder;
    private JTextField tfUnderField;
    private JButton jButtonSubmit;
    private JButton jButtonBack;
    private JButton jButtonNew;
    private JButton jButtonDelete;
    private JTable jTableUOMList;
    private DefaultTableModel jTableUOMListModel;
    private JButton jButtonExport;

    private void initilize() throws SQLException, ParseException, Exception {
        bindTOGUI();
    }

    private void setUOMModel(DefaultComboBoxModel mdl, String str) {
        jComboBoxUnder.setModel(mdl);
        jComboBoxUnder.setSelectedIndex(-1);
        tfUnderField.setText(str);
    }

    private void initUOM() throws SQLException {
        UOMNameMap = UnitOfMeasureDAO.getUOMTypes();
        UOMVector.clear();
        for (String str : UOMNameMap.keySet()) {
            UOMVector.add(str);
        }

        Collections.sort(UOMVector, new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        return lhs.compareToIgnoreCase(rhs);
                    }
                });

        UOMNameMap = Util.getSmallCaseMap(UOMNameMap);
    }

    private void formInternalFrameClosing(
            javax.swing.event.InternalFrameEvent evt)
            throws PropertyVetoException {
        MainClass.setstaticvar();
        // this.dispose();
    }

    private void formInternalFrameActivated(
            javax.swing.event.InternalFrameEvent evt)
            throws PropertyVetoException, SQLException, Exception {
        jButtonDelete.setEnabled(false);
        initUOM();
        initUOMNameList();
    }

    private void formInternalFrameOpened(
            javax.swing.event.InternalFrameEvent evt)
            throws PropertyVetoException {
        jButtonDelete.setEnabled(false);
    }

    private void newButton() throws Exception {
        UOMDTO = new UnitOfMeasureDTO();
        isEdit = false;
        bindTOGUI();
    }

    private void submit() throws SQLException, ParseException, Exception {
        UOMDTO = bindGUITODTO();

        if (!isEdit) {
            insertUOM();

        } else {
            updateUOM();
        }
        JOptionPane
                .showMessageDialog(this, Label.RECORD_SUBMITTED_SUCCESSFULLY);
        tfUnderField.requestFocus();
        jButtonNewActionPerformed(null);
    }

    private void delete() throws SQLException, Exception {
        List<UnitOfMeasureDTO> UOMDTOList = new ArrayList<UnitOfMeasureDTO>();
        UOMDTOList.add(UOMDTO);
        //   UnitOfMeasureDAO.upsertUOM(UOMDTOList);
        if (UnitOfMeasureDAO.deleteUOM(UOMDTOList)) {
            JOptionPane
                    .showMessageDialog(UnitOfMeasureForm.this,
                    "This UOM Can Not Be Deleted As It Is Used In Other Transactions");
        } else {
            JOptionPane.showMessageDialog(UnitOfMeasureForm.this,
                    "UOM Deleted Successfully");
        }
        initUOMNameList();
        jButtonNewActionPerformed(null);
    }

    private void clearFieldData() {
        tfUnderField.setText("");
        jComboBoxUnder.setSelectedItem("");
        jTextFieldSymbol.setText("");
        jTextFieldName.setText("");
        jTextFieldNODPlaces.setText("0");
	jTextFieldSearch.setText("");
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
            tfUnderField.requestFocus();
        } else if (currentFocusValue == 1) {
            jTextFieldSymbol.requestFocus();
        } else if (currentFocusValue == 2) {
            jTextFieldName.requestFocus();
        } else if (currentFocusValue == 3) {
            jTextFieldNODPlaces.requestFocus();
        }
    }

    private Boolean validateData() throws Exception {
        Boolean flag = true;

        String ledger_name = jTextFieldName.getText().trim();
        Pattern p = Pattern.compile("[a-zA-Z0-9]");
        Matcher m = p.matcher(ledger_name);

	if (jTextFieldSymbol.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthSMALL) {
	    flag = false;
	    jTextFieldSymbol.requestFocus();
            throw new Exception("Measurement Unit Symbol Exceeds "
                    + Constants.jTextFieldCharacterLengthSMALL
                    + " Character Limit");
	} else if (jTextFieldName.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthSMALL) {
	    flag = false;
	    jTextFieldName.requestFocus();
            throw new Exception("Measurement Unit Formal Name Exceeds "
                    + Constants.jTextFieldCharacterLengthSMALL
                    + " Character Limit");
	} else if (jTextFieldNODPlaces.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthSMALL) {
	    flag = false;
	    jTextFieldNODPlaces.requestFocus();
            throw new Exception("Measurement Unit Decimal Places Exceeds "
                    + Constants.jTextFieldCharacterLengthSMALL
                    + " Integer Limit");
	} else if (jTextFieldNODPlaces.getText().trim().length() > Constants.jTextFieldDecimalPlacesLimit) {
            System.out
                    .println("jTextFieldNODPlaces.getText().trim().length()----"
                    + jTextFieldNODPlaces.getText().trim().length());
	    flag = false;
	    jTextFieldNODPlaces.requestFocus();
            throw new Exception("Measurement Unit Decimal Places Exceeds "
                    + Constants.jTextFieldDecimalPlacesLimit + " Places Limit");
	} else if (isEdit) {
            if (!jTextFieldName.getText().trim()
                    .equalsIgnoreCase(editModeUOMName)) {
                if (UOMNameListMap.containsKey(jTextFieldName.getText().trim())) {
                    JOptionPane.showMessageDialog(UnitOfMeasureForm.this,
                            "Unit Of Measure Already Exists");
                    jTextFieldName.requestFocus();
                    flag = false;
                }
            }
            if (!UOMNameMap.containsKey(tfUnderField.getText().toString()
                    .trim().toLowerCase())) {
                JOptionPane.showMessageDialog(this,
                        "Enter valid value for under field");
                tfUnderField.requestFocus();
                flag = false;
            }
        } else if (UOMNameMap.get(tfUnderField.getText().trim().toLowerCase()) == null) {
            JOptionPane.showMessageDialog(this,
                    Label.ENTER_VALUE_FOR_TYPE_FIELD);
            tfUnderField.requestFocus();
            flag = false;
        } else if (jTextFieldName.getText().trim().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, Label.ENTER_VALID_UNIT_NAME);
            jTextFieldName.requestFocus();
            flag = false;
        } else if (jTextFieldSymbol.getText().trim().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, Label.ENTER_VALID_UNIT_SYMBOL);
            jTextFieldSymbol.requestFocus();
            flag = false;
        } else if (!m.find()) {
            JOptionPane.showMessageDialog(this, Label.ENTER_VALID_UNIT_NAME);
            jTextFieldName.requestFocus();
            flag = false;
        } else if (jTextFieldNODPlaces.getText().trim().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, Label.ENTER_NOD_PLACES);
            jTextFieldNODPlaces.requestFocus();
            flag = false;
        }

        return flag;
    }

    private UnitOfMeasureDTO bindGUITODTO() {

        // UOMDTO.setUnitOfMeasure_ID(editModeUOMDTO.getUnitOfMeasure_ID());
        if (!tfUnderField.getText().trim().equalsIgnoreCase("")) {
            UOMDTO.setUnitOfMeasure_Type(UOMNameMap.get(tfUnderField.getText().trim().toLowerCase()));
        }
        UOMDTO.setUnitOfMeasure_TypeName(tfUnderField.getText().trim().toLowerCase());
        UOMDTO.setUnitOfMeasure_Symbol(jTextFieldSymbol.getText().trim());
        if (!jTextFieldName.getText().trim().equalsIgnoreCase("")) {
            UOMDTO.setUnitOfMeasure_FormalName(jTextFieldName.getText().trim());
        }
        UOMDTO.setUnitOfMeasure_NoDecimalPlaces(jTextFieldNODPlaces.getText().trim());

        System.out.println("<<<<-----Binding GUI to DTO in UMO---->>>");

        System.out.println("UOMDTO.getUnitOfMeasure_Type()----" + UOMDTO.getUnitOfMeasure_Type());
        System.out.println("UOMDTO.getUnitOfMeasure_TypeName()---" + UOMDTO.getUnitOfMeasure_TypeName());
        System.out.println("UOMDTO.getUnitOfMeasure_Symbol()--" + UOMDTO.getUnitOfMeasure_Symbol());
        System.out.println("UOMDTO.getUnitOfMeasure_FormalName()--" + UOMDTO.getUnitOfMeasure_FormalName());
        System.out.println("UOMDTO.getUnitOfMeasure_NoDecimalPlaces()--" + UOMDTO.getUnitOfMeasure_NoDecimalPlaces());

        return UOMDTO;
    }

    private void insertUOM() throws SQLException, ParseException, Exception {
        List<UnitOfMeasureDTO> UOMDTOList = new ArrayList<UnitOfMeasureDTO>();
        UOMDTOList.add(UOMDTO);
        UnitOfMeasureDAO.upsertUOM(UOMDTOList);
    }

    private void updateUOM() throws SQLException, ParseException, Exception {
        List<UnitOfMeasureDTO> UOMDTOList = new ArrayList<UnitOfMeasureDTO>();
        UOMDTOList.add(UOMDTO);
        UnitOfMeasureDAO.upsertUOM(UOMDTOList);
    }

    private void bindDTOTojTableUOMList(List<UnitOfMeasureDTO> receivedMap)
            throws SQLException {
        if (receivedMap != null && !receivedMap.isEmpty()) {
            jTableUOMListModel.setRowCount(0);
            for (UnitOfMeasureDTO UOMDTO : receivedMap) {
                System.out.println("into bindDTOTojTableStockGroupList()-->>");
                jTableUOMListModel
                        .setRowCount(jTableUOMListModel.getRowCount() + 1);
                System.out.println("FormalName--->>>"
                        + UOMDTO.getUnitOfMeasure_FormalName());
                jTableUOMListModel.setValueAt(
                        UOMDTO.getUnitOfMeasure_FormalName(),
                        jTableUOMListModel.getRowCount() - 1, 0);
                jTableUOMListModel.setValueAt(UOMDTO.getUnitOfMeasure_ID(),
                        jTableUOMListModel.getRowCount() - 1, 1);
                System.out.println("ID-->>>" + UOMDTO.getUnitOfMeasure_ID());
            }
	} else {
	    jTableUOMListModel.setRowCount(0);
        }
    }

    public void loadEditForm(String id) throws SQLException, ParseException,
            Exception {
        Set<String> idSet = new HashSet<String>();
        idSet.add(id);
        System.out.println("id==============" + id);
        List<UnitOfMeasureDTO> UOMDTOList = UnitOfMeasureDAO
                .getUnitOfMeasureList(idSet, Constants.UNIT_MEASURE_ID);
        if (UOMDTOList != null && !UOMDTOList.isEmpty()) {
            UOMDTO = UOMDTOList.get(0);
            editModeUOMDTO = UOMDTO;
            System.out.println("UOM DTO ---------->>>>>>"
                    + UOMDTO.getUnitOfMeasure_FormalName().toString());
            isEdit = true;
            bindTOGUI();
            bindDTOtoGUI();
        }
    }

    private void bindTOGUI() throws ParseException, SQLException, Exception {
        if (!isEdit) {
            initUOMNameList();
        }
        jTextFieldNODPlaces.setText("0");
    }

    private void bindDTOtoGUI() throws ParseException {

        tfUnderField.setText(UOMDTO.getUnitOfMeasure_TypeName().toString());
        jTextFieldSymbol.setText(UOMDTO.getUnitOfMeasure_Symbol());
        jTextFieldName.setText(UOMDTO.getUnitOfMeasure_FormalName());
        jTextFieldNODPlaces.setText(""
                + UOMDTO.getUnitOfMeasure_NoDecimalPlaces());
    }

    private void initUOMNameList() throws SQLException, Exception {
        Set<String> idSet = new HashSet<String>();
        UOMDTOsNameList = UnitOfMeasureDAO.getUnitOfMeasureList(idSet,
                Constants.UNIT_MEASURE_ID);
        bindDTOTojTableUOMList(UOMDTOsNameList);
        for (UnitOfMeasureDTO UOMDTO : UOMDTOsNameList) {
            UOMNameListMap.put(UOMDTO.getUnitOfMeasure_FormalName(), UOMDTO
                    .getUnitOfMeasure_ID().toString());
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

            int r = chooser.showSaveDialog(UnitOfMeasureForm.this);

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
        UnitOfMeasureForm.MessageBox msgBox = new UnitOfMeasureForm.MessageBox();
        //Set<String> saleSet = new HashSet<String>();
        //saleSet.add(jTextFieldReceiptNo.getText().trim());
        BufferedWriter out = null;
        if (path != null) {
            if (!path.isEmpty()) {
                try {
                    // TODO add your handling code here:
                    List<UnitOfMeasureDTO> UOMDTOList = UnitOfMeasureDAO.exportAllUnitOfMeasures();
                    System.out.println("UOMDTOList-->>>" + UOMDTOList.size());
                    String xmlCode = InventoryTagHelper.generateUnitOfMeasureListNodes(UOMDTOList);

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
