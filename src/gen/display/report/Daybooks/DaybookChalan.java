package gen.display.report.Daybooks;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.sql.ResultSet;
import java.util.Set;
import java.util.TreeSet;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import gen.ImpExp.TagsHelper1;
import gen.accountvoucher.chalan.ChalanDTO;
import gen.accountvoucher.chalan.ChalanForm1;
import gen.database.connection.DatabaseConnection1;
import gen.display.report.DayBookChalan;
import gen.dto.Util;
import gen.mainclass.MainClass;
import gen.other.print.PrintAllReport;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

public class DaybookChalan extends JInternalFrame {

    private JTable tableChalan;
    int i = 0, flag = 0;
    ResultSet rs1, rs2, rs3, rs4;
    String q = "", str = "";
    String path = "";
    private JTextField tfDatePicker = null;
    private JTextField tfDatePicker1 = null;
    private boolean hide_flag = false;
    Set<String> chalanIDSet = new TreeSet<String>();
    /**
     * Launch the application.
     */
    private JPanel panel;
    private JLabel lblDaybook;
    private JDateChooser dat1;
    private JDateChooser dat2;
    private JButton btnShow;
    private JButton btnPrint;
    private JButton btnBack;
    private JButton btnNewButton_3;
    private JButton btnExport;
    private JPanel panelDebit;
    private JLabel lblCredit;
    private JLabel lblTo;
    private JLabel lblDebit;
    private JPanel panel_2;
    private JScrollPane scrollPane;
    private JPanel panel_3;
    private JScrollPane scrollPane_1;
    private JLabel lblNewLabel;
    private JLabel lblTotalAmount;
    private JLabel labelTotalDebit;
    private JLabel labelTotalCredit;
    private JPanel panel_1;
    //private JScrollPane scrollpaneMain;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DaybookChalan frame = new DaybookChalan();
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
    public DaybookChalan() {
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(Util.getImageIconPath())));
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                MainClass.setstaticvar();
            }

            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                Calendar currentDate = Calendar.getInstance();
                dat1.setDate(currentDate.getTime());
                dat2.setDate(currentDate.getTime());

                DefaultTableModel tableModel = (DefaultTableModel) tableChalan
                        .getModel();
                tableModel.setRowCount(0);
                tableModel.setColumnCount(4);

                btnShow.setMnemonic(KeyEvent.VK_S);
                btnPrint.setMnemonic(KeyEvent.VK_P);
                btnBack.setMnemonic(KeyEvent.VK_B);
		btnExport.setMnemonic(KeyEvent.VK_X);

                btnShowActionPerformed(null);

                //scrollpaneMain.getVerticalScrollBar().setUnitIncrement(20);

                dat1.requestFocus();

                // tableChalan.setGridColor(Color.red);
                tableChalan.setShowGrid(true);

                JTableHeader header = tableChalan.getTableHeader();
                header.setBackground(Color.yellow);
                setColumnWidth(tableChalan);
            }

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                loadChalanEntry();
            }
        });
        setTitle("Daily Chalan");
        setBounds(100, 100, 1322, 674);

        panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new MigLayout(
                "",
                "[0px:10px:10px,grow,shrink 50,fill][grow,shrink 50,fill][0px:10px:10px,grow,shrink 50,fill]",
                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        lblDaybook = new JLabel("Daybook");
        lblDaybook.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblDaybook.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblDaybook, "cell 1 0");

        dat1 = new JDateChooser();
        dat1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnShowActionPerformed(null);
                }
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    btnBackActionPerformed(null);
                }
            }
        });
        panel.add(dat1, "flowx,cell 1 1");

        lblTo = new JLabel("To");
        lblTo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTo.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panel.add(lblTo, "cell 1 1");

        dat2 = new JDateChooser();
        panel.add(dat2, "cell 1 1");

        btnShow = new JButton("Show");
        btnShow.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnShowActionPerformed(null);
                }
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    tfDatePicker1.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                    btnPrint.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                    tfDatePicker1.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (tableChalan.getRowCount() > 0) {
                        tableChalan.requestFocus();
                        tableChalan.changeSelection(0, 0, false, false);
                    } else {
                        btnShow.requestFocus();
                    }
                }
            }
        });
        btnShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnShowActionPerformed(e);
            }
        });
        panel.add(btnShow, "cell 1 1");

        btnPrint = new JButton("Print");
        btnPrint.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                    btnBack.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                    btnShow.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (tableChalan.getRowCount() > 0) {
                        tableChalan.requestFocus();
                        tableChalan.changeSelection(0, 0, false, false);
                    } else {
                        btnShow.requestFocus();
                    }
                }
            }
        });
        btnPrint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnPrintActionPerformed(e);
            }
        });
        panel.add(btnPrint, "cell 1 1");

        btnBack = new JButton("Back");
        btnBack.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnBackActionPerformed(null);
                }
                if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                    btnPrint.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
		    btnExport.requestFocus();
                }
            }
        });
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnBackActionPerformed(e);
            }
        });
        panel.add(btnBack, "cell 1 1");

        btnNewButton_3 = new JButton("Advance Daybook");
        btnNewButton_3.setEnabled(false);
        panel.add(btnNewButton_3, "cell 1 1");

	btnExport = new JButton("Export");
	btnExport.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnChalanActionPerformed(null);
                }
                if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                    btnBack.requestFocus();
                }
            }
        });
	btnExport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnChalanActionPerformed(e);
            }
        });
	panel.add(btnExport, "cell 1 1");

        panelDebit = new JPanel();
        panelDebit.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.add(panelDebit, "cell 1 2 1 19,grow");
        panelDebit
                .setLayout(new MigLayout(
                "",
                "[0px:1280px:1280px,grow,shrink 50,fill]",
                "[0px:25px:25px,grow,shrink 50,fill][grow][0px:25px:25px,grow,shrink 50,fill]"));

        panel_2 = new JPanel();
        panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelDebit.add(panel_2, "cell 0 1 2 1,grow");
        panel_2.setLayout(new MigLayout("", "[grow]", "[grow]"));

        scrollPane = new JScrollPane();
        panel_2.add(scrollPane, "cell 0 0,grow");

        tableChalan = new JTable();
        tableChalan.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    i = tableChalan.getSelectedRow();
                    if (tableChalan.getRowCount() > 0) {
                        if (tableChalan.getValueAt(i, 0) == null) {
                            dat1.requestFocus();
                        } else {
                            //tableChalanMouseClicked(null);
                        }
                    }
                }
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dat1.requestFocus();
                }
            }
        });
        tableChalan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getDesktopPane().setLayout(new CardLayout());
                int row = tableChalan.getSelectedRow();
                if (tableChalan.getValueAt(row, 1) != null
                        && tableChalan.getValueAt(row, 2) != null) {
                    try {
                        String id = tableChalan.getValueAt(
                                tableChalan.getSelectedRow(), 2).toString();
                        ChalanDTO chalanDTO = new ChalanDTO();
                        ChalanForm1 c = new ChalanForm1(chalanDTO, true);
                        c.loadEditForm(id);
                        c.pack();
                        JDesktopPane desktopPane = getDesktopPane();
                        desktopPane.add(c);
                        Dimension desktopSize = getDesktopPane().getSize();
                        c.setSize(desktopSize);
                        c.setPreferredSize(desktopSize);
                        c.setVisible(!c.isVisible());
                        // c.setVisible(true);
                        // this.getParent().add(c);
                        // this.getParent().setVisible(true);
                        // Dimension desktopSize = this.getParent().getSize();
                        // c.setSize(desktopSize);
                        // c.setPreferredSize(desktopSize);
                        try {
                            c.setSelected(true);
                        } catch (java.beans.PropertyVetoException ex) {
                            ex.printStackTrace();
                        }
                        BasicInternalFrameUI ui = (BasicInternalFrameUI) c
                                .getUI();

                        Component north = ui.getNorthPane();
                        MouseMotionListener[] actions = (MouseMotionListener[]) north
                                .getListeners(MouseMotionListener.class);

                        for (int i = 0; i < actions.length; i++) {
                            north.removeMouseMotionListener(actions[i]);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(DaybookChalan.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        tableChalan.setModel(new DefaultTableModel(new Object[][]{{null,
                        null, null, null},}, new String[]{"Sr.No", "Particulars",
                    "VCH No", "Quantity"}));
        scrollPane.setViewportView(tableChalan);

        setClosable(true);
        //initComponents();
        dat1.setDateFormatString("dd-MM-yyyy");
        dat2.setDateFormatString("dd-MM-yyyy");
        //this.setTitle(s);

        // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        tfDatePicker = (JTextField) dat1.getComponent(1);
        System.out.println(tfDatePicker.toString());
        tfDatePicker1 = (JTextField) dat2.getComponent(1);

        // tf = (JTextField) btnShow.getEditor().getEditorComponent();//Edits
        // the component in combobox.

        tfDatePicker.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("code:" + e.getKeyCode());
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tfDatePicker1.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    btnBackActionPerformed(null);
                }
            }

            @Override
            public void keyTyped(final KeyEvent e) {
                System.out.println("Key pressed");
                int code = e.getKeyCode();
                System.out.println("code" + code);
                if (code == KeyEvent.VK_ENTER) {
                    System.out.println("Enter");

                }
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int code = e.getKeyCode();
                        System.out.println("code" + code);
                        if (code == KeyEvent.VK_ENTER) {
                            System.out.println("Enter");
                        } else if (code == KeyEvent.VK_ESCAPE) {
                            System.out.println("Escape");
                            hide_flag = true;
                        } else if (code == KeyEvent.VK_RIGHT) {
                            System.out.println("Right");
                        }
                    }
                });
            }
        });

        // //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // tfDatePicker1 = (JTextField) dat2.getComponent(1);
        // System.out.println(tfDatePicker.toString());

        // tf = (JTextField) btnShow.getEditor().getEditorComponent();//Edits
        // the component in combobox.

        tfDatePicker1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("code:" + e.getKeyCode());
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnShow.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    tfDatePicker.requestFocus();
                }
            }

            @Override
            public void keyTyped(final KeyEvent e) {
                System.out.println("Key pressed");
                int code = e.getKeyCode();
                System.out.println("code" + code);
                if (code == KeyEvent.VK_ENTER) {
                    System.out.println("Enter");

                }
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int code = e.getKeyCode();
                        System.out.println("code" + code);
                        if (code == KeyEvent.VK_ENTER) {
                            System.out.println("Enter");
                        } else if (code == KeyEvent.VK_ESCAPE) {
                            System.out.println("Escape");
                            hide_flag = true;
                        } else if (code == KeyEvent.VK_RIGHT) {
                            System.out.println("Right");
                        }
                    }
                });
            }
        });

    }

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {
        if (dat1.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
            tfDatePicker.requestFocus();
            Calendar currentDate = Calendar.getInstance();
            dat1.setDate(currentDate.getTime());
            dat2.setDate(currentDate.getTime());
            btnShowActionPerformed(null);
        } else if (dat2.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
            tfDatePicker1.requestFocus();
            Calendar currentDate = Calendar.getInstance();
            dat1.setDate(currentDate.getTime());
            dat2.setDate(currentDate.getTime());
            btnShowActionPerformed(null);
        } else if (dat1.getDate().getTime() > dat2.getDate().getTime()) {
            JOptionPane.showMessageDialog(this, "Invalid Date selection");
            tfDatePicker1.requestFocus();
        } else {
            loadChalanEntry();
        }

        setColumnWidth(tableChalan);
    }

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        btnShowActionPerformed(evt);
        if (dat1.getDate() != null && dat2.getDate() != null
                && tableChalan.getRowCount() > 0
                && dat1.getDate().getTime() <= dat2.getDate().getTime()) {
            Map parameter = new HashMap();
            SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
            parameter.put("dateFrom", saDa.format(this.dat1.getDate())
                    .toString());
            parameter
                    .put("dateTo", saDa.format(this.dat2.getDate()).toString());
            PrintAllReport.printDAybookchalanReport(new JRTableModelDataSource(
                    this.tableChalan.getModel()), parameter);
        } else {
            JOptionPane.showMessageDialog(this,
                    "No Data Available For Printing");
        }
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            MainClass mainClass = new MainClass();
            mainClass.menuselection(3);

            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    private void btnChalanActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
	path = "";
        imageloading o = new imageloading();

        BufferedWriter out = null;
	if (path != null) {
	    if (!path.isEmpty()) {
        try {
            // TODO add your handling code here:
            Map<String, Set<String>> IDMapSet = new HashMap<String, Set<String>>();
            IDMapSet.put("Chalan", chalanIDSet);

            String xmlCode = TagsHelper1.exportDayBook(IDMapSet);
            System.out.println("------------------------->>XMLCODE:" + xmlCode);

            // File file = new File("C:\\ChalanBook.xml");
            File file = new File(path + ".xml");
            String path = file.getPath();
            out = new BufferedWriter(new FileWriter(file));
            out.write(xmlCode);
            out.close();

            JOptionPane.showMessageDialog(this, "Export Successful");

        } catch (Exception ex) {
            Logger.getLogger(DaybookChalan.class.getName()).log(Level.SEVERE, null,
                    ex);
            JOptionPane.showMessageDialog(this, "Export failure");
        }
	    }
	}

    }

    public void loadChalanEntry() {

        chalanIDSet = new TreeSet<String>();
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = f.format(dat1.getDate());
            String date2 = f.format(dat2.getDate());

            // -----------------------Table:
            // Credit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableChalan
                    .getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(4);

            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_index=0 and trans_type = "+gen.dto.Constants.DEBIT+" and trans_id in(select trans_id from tbltransactionmain where trans_date>='"
                    + date1
                    + "' and trans_date<='"
                    + date2
                    + "' and trans_typeIndex=7)";
            rs2 = st1.executeQuery(q);
            int k = 0; // for putting amount with table header
            while (rs2.next()) {
                // System.out.println("Entry found");
                q = "select ledger_name from tblledger where ledger_id="
                        + rs2.getLong("trans_ledgerid") + "";
                rs3 = st3.executeQuery(q);
                if (rs3.next()) {
                    int row = tableChalan.getRowCount();
                    tableModel.setRowCount(row + 1);

                    // Set serial no
                    tableChalan.setValueAt(row + 1, row, 0);

                    // Get Ledger name
                    tableChalan
                            .setValueAt(rs3.getString("ledger_name"), row, 1);

                    // VCH No(Receipt No)
                    q = "select trans_receiptNo,trans_typeIndex from tbltransactionmain where trans_id="
                            + rs2.getLong("trans_id") + "";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableChalan.setValueAt(rs4.getLong("trans_receiptNo"),
                                row, 2);
                        chalanIDSet.add(tableChalan.getValueAt(row, 2)
                                .toString());
                    }
                    rs4.close();

                    // Qty
                    q = "select sum(invtrans_qty) as totalQty from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id="
                            + rs2.getLong("trans_id") + ")";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableChalan.setValueAt(rs4.getDouble("totalQty"), row,
                                3);
                    }
                    rs4.close();
                }
            }
            // --------------------------------------------------------------
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        setColumnWidth(tableChalan);
    }

    public class imageloading // extends JFrame
    {

        BufferedImage mImage;
        String name, name1;

        public imageloading() {
            // JFrame frm=new JFrame("file loading test");
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

            int r = chooser.showSaveDialog(gen.display.report.Daybooks.DaybookChalan.this);

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
                    // FileWriter fstream = new
                    // FileWriter(System.currentTimeMillis() + "chalan.xml");
                    // BufferedWriter out = new BufferedWriter(fstream);
                    // out.write(path);
                    PrintStream ps = new PrintStream(file);
                    ps.print(path);

                    // Close the output stream
                    ps.close();
                } catch (Exception e) {
                }
            }
	    }
            return name1;
        }
        // public static void main(String a[])
        // {
        // new imageloading();
        // }
    }

    private void setColumnWidth(JTable passedTable) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

//        passedTable.getColumnModel().getColumn(0)
//                .setCellRenderer(rightRenderer);
        
        passedTable.getColumnModel().getColumn(2)
                .setCellRenderer(rightRenderer);
        
//        passedTable.getColumnModel().getColumn(0).setPreferredWidth(50);
//        passedTable.getColumnModel().getColumn(0).setMinWidth(50);
//        passedTable.getColumnModel().getColumn(0).setMaxWidth(50);
//
//        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
//        passedTable.getColumnModel().getColumn(1).setMinWidth(387);
//        passedTable.getColumnModel().getColumn(1).setMaxWidth(387);
//
//        passedTable.getColumnModel().getColumn(2).setPreferredWidth(0);
//        passedTable.getColumnModel().getColumn(2).setMinWidth(50);
//        passedTable.getColumnModel().getColumn(2).setMaxWidth(50);
//
//        passedTable.getColumnModel().getColumn(3).setPreferredWidth(0);
//        passedTable.getColumnModel().getColumn(3).setMinWidth(100);
//        passedTable.getColumnModel().getColumn(3).setMaxWidth(100);
        passedTable.getColumnModel().getColumn(3)
                .setCellRenderer(rightRenderer);
    }
}
