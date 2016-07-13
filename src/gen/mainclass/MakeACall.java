package gen.mainclass;

import gen.dto.Util;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import javax.comm.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;

public class MakeACall extends JInternalFrame implements Runnable, SerialPortEventListener {

    @Override
    public void run() {
    }
    static String dir = System.getProperty("user.dir");
    static Image img, imageIcon;
    private JPanel contentPane;
    private JTextField jTextFieldCallToNumber;
    static InputStream inputStream;
    static SerialPort serialPort;
    static OutputStream outputStream;
    static Enumeration portList;
    static CommPortIdentifier portId;
    static String dialing = "";
    public ArrayList<String> availableCOMPorts = new ArrayList<String>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MakeACall frame = new MakeACall();
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
    public void listAvailableCOMPorts() {
        jComboBoxPorts.removeAllItems();
        jComboBoxBaudRate.removeAllItems();
        Enumeration pList = CommPortIdentifier.getPortIdentifiers();
        while (pList.hasMoreElements()) {
            CommPortIdentifier cpi = (CommPortIdentifier) pList.nextElement();
            if (cpi.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                System.out.print("Port " + cpi.getName() + " ");
                System.out.println("is a Serial Port: " + cpi);
                jComboBoxPorts.addItem(cpi.getName());
            }
        }
        jComboBoxBaudRate.addItem("300");
        jComboBoxBaudRate.addItem("1200");
        jComboBoxBaudRate.addItem("2400");
        jComboBoxBaudRate.addItem("4800");
        jComboBoxBaudRate.addItem("9600");
        jComboBoxBaudRate.addItem("19200");
        jComboBoxBaudRate.addItem("38400");
        jComboBoxBaudRate.addItem("57600");
        jComboBoxBaudRate.addItem("115200");
        jComboBoxBaudRate.addItem("230400");
    }

    public MakeACall() {
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            @Override
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                System.out.println("Activated in make a call");
                listAvailableCOMPorts();
            }

            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
            }

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
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
        setTitle("Phone Call");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(Util.getImageIconPath())));
        setClosable(true);
        setBounds(100, 100, 407, 390);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        MakeACall.this.setLocation(dim.width / 2 - MakeACall.this.getSize().width / 2, dim.height / 2 - MakeACall.this.getSize().height / 2);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("", "[0px:50px:50px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        JButton jButtonThree = new JButton("3");
        jButtonThree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextFieldCallToNumber.setText(jTextFieldCallToNumber.getText() + "3");
            }
        });

        JButton jButtonTwo = new JButton("2");
        jButtonTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextFieldCallToNumber.setText(jTextFieldCallToNumber.getText() + "2");
            }
        });

        JButton jButtonOne = new JButton("1");
        jButtonOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                jTextFieldCallToNumber.setText(jTextFieldCallToNumber.getText() + "1");
            }
        });

        JLabel lblPort = new JLabel("Port");
        lblPort.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(lblPort, "cell 0 0,alignx trailing");

        jComboBoxPorts = new JComboBox();
        contentPane.add(jComboBoxPorts, "cell 1 0 2 1,growx");

        JLabel lblBaudRatefps = new JLabel("Baud Rate (FPS)");
        lblBaudRatefps.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(lblBaudRatefps, "cell 3 0 2 1");

        jComboBoxBaudRate = new JComboBox();
        contentPane.add(jComboBoxBaudRate, "cell 5 0 2 1,growx");

        jTextFieldCallToNumber = new JTextField();
        contentPane.add(jTextFieldCallToNumber, "cell 2 3 3 1,growx");
        jTextFieldCallToNumber.setColumns(10);
        contentPane.add(jButtonOne, "cell 2 4");
        contentPane.add(jButtonTwo, "cell 3 4");
        contentPane.add(jButtonThree, "cell 4 4");

        JButton jButtonSix = new JButton("6");
        jButtonSix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextFieldCallToNumber.setText(jTextFieldCallToNumber.getText() + "6");
            }
        });

        JButton jButtonFive = new JButton("5");
        jButtonFive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextFieldCallToNumber.setText(jTextFieldCallToNumber.getText() + "5");
            }
        });

        JButton jButtonFour = new JButton("4");
        jButtonFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextFieldCallToNumber.setText(jTextFieldCallToNumber.getText() + "4");
            }
        });
        contentPane.add(jButtonFour, "cell 2 5");
        contentPane.add(jButtonFive, "cell 3 5");
        contentPane.add(jButtonSix, "cell 4 5");

        JButton jButtonNine = new JButton("9");
        jButtonNine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextFieldCallToNumber.setText(jTextFieldCallToNumber.getText() + "9");
            }
        });

        JButton jButtonEight = new JButton("8");
        jButtonEight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextFieldCallToNumber.setText(jTextFieldCallToNumber.getText() + "8");
            }
        });

        JButton jButtonSeven = new JButton("7");
        jButtonSeven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextFieldCallToNumber.setText(jTextFieldCallToNumber.getText() + "7");
            }
        });
        contentPane.add(jButtonSeven, "cell 2 6");
        contentPane.add(jButtonEight, "cell 3 6");
        contentPane.add(jButtonNine, "cell 4 6");

        JButton jButtonErase = new JButton("<");
        jButtonErase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextFieldCallToNumber.setText("" + jTextFieldCallToNumber.getText().substring(0, jTextFieldCallToNumber.getText().length() - 1));
            }
        });

        JButton jButtonHash = new JButton("#");
        jButtonHash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextFieldCallToNumber.setText(jTextFieldCallToNumber.getText() + "#");
            }
        });

        JButton jButtonZero = new JButton("0");
        jButtonZero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextFieldCallToNumber.setText(jTextFieldCallToNumber.getText() + "0");
            }
        });

        JButton jButtonStar = new JButton("*");
        jButtonStar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextFieldCallToNumber.setText(jTextFieldCallToNumber.getText() + "*");
            }
        });
        contentPane.add(jButtonStar, "cell 2 7");
        contentPane.add(jButtonZero, "cell 3 7");
        contentPane.add(jButtonHash, "cell 4 7");

        JButton jButtonDial = new JButton();
        ImageIcon imageIcon = new ImageIcon(dir + "\\images\\app-phone-icon (1).png");
        jButtonDial.setIcon(imageIcon);
        jButtonDial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(jTextFieldCallToNumber.getText().toString().trim().equalsIgnoreCase(""))) {
                    connect();
                    dialACall(jTextFieldCallToNumber.getText().toString().trim());
                } else {
                    JOptionPane.showMessageDialog(MakeACall.this, "Please Enter A Valid Contact Number");
                }
            }
        });

        JButton jButtonHangUp = new JButton();
        ImageIcon imageIcon1 = new ImageIcon(dir + "\\images\\missed-calls-icon.png");
        jButtonHangUp.setIcon(imageIcon1);
        jButtonHangUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hangUpACall();
            }
        });
        contentPane.add(jButtonHangUp, "cell 2 8");
        contentPane.add(jButtonDial, "cell 3 8");
        contentPane.add(jButtonErase, "cell 4 8");
    }
    private JComboBox jComboBoxPorts;
    private JComboBox jComboBoxBaudRate;

    public void connect() {
        String line1 = "AT" + "\r\n";
        portList = CommPortIdentifier.getPortIdentifiers();

        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName().equals("" + jComboBoxPorts.getSelectedItem())) {
                    try {
                        serialPort = (SerialPort) portId.open("MakeACall", 2000);
                        System.out.println("Port Opened");
                    } catch (PortInUseException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(MakeACall.this, "Port is already in use by some other application");
                    }
                    try {
                        serialPort.setSerialPortParams(Integer.parseInt("" + jComboBoxBaudRate.getSelectedItem()),
                                SerialPort.DATABITS_8,
                                SerialPort.STOPBITS_1,
                                SerialPort.PARITY_NONE);
                        System.out.println("BaudRate set,Databits Set,Stop bit set,Parity Set");
                    } catch (UnsupportedCommOperationException e) {
                        e.printStackTrace();
                    }
                    try {
                        outputStream = serialPort.getOutputStream();
                        outputStream.write(line1.getBytes());
                        System.out.println("Command Written To Outputstream->" + line1.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        System.out.println("Into inputstream for port response");
                        serialPort.enableReceiveTimeout(1000);
                        inputStream = serialPort.getInputStream();
                        if (inputStream.read() == -1) {
                            JOptionPane.showMessageDialog(MakeACall.this, "Invalid Port");
                        } else {
                            System.out.println("Dialing...");
                            return;
                        }
                        System.out.println("inputStream.read()-->>" + inputStream.read());
                    } catch (UnsupportedCommOperationException ex) {
                        Logger.getLogger(MakeACall.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        serialPort.addEventListener(this);
                    } catch (TooManyListenersException e) {
                        e.printStackTrace();
                    }
                    serialPort.notifyOnDataAvailable(true);
                }
            }
        }
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;
            case SerialPortEvent.DATA_AVAILABLE: {

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                try {
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    System.err.println("Error while reading Port " + e);
                }
                break;

            }
        }
    }

    public void dialACall(String dialing) {

        String line1 = "AT+CVHU=0" + ";" + "\r\n";
        String line2 = "ATDT" + dialing + ";" + "\r\n";

        try {
            outputStream.write(line1.getBytes());
            outputStream.write(line2.getBytes());
        } catch (Exception e) {
            System.out.println("Error writing message " + e);
        }

    }

    public void hangUpACall() {

        String line1 = "ATH" + "\r\n";

        try {
            outputStream.write(line1.getBytes());
            outputStream.close();
            serialPort.close();
        } catch (Exception e) {
            System.out.println("Error writing message " + e);
        }

    }
}
