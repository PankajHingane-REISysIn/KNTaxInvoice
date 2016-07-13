/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.progressebar;

import gen.database.connection.StartMysqlServer;
import gen.other.login.Login1;
import gen.other.startup.USBRunnable;
import java.awt.*;
import javax.swing.*;

public class ProgressBar extends JFrame {

    JLabel l1;
    JProgressBar current;
    JTextArea ta;
    JButton bu;
    Thread runner;
    int num = 0;
    final String dir = System.getProperty("user.dir");
    Image img = new ImageIcon(getClass().getResource("/images/Symbol.png")).getImage();

    public ProgressBar() {
        super("KASTURI NOVASOFT presents...");
        this.setIconImage(img);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pane = new JPanel();
        //  bu = new JButton("go");
        pane.setLayout(new GridLayout());
        current = new JProgressBar(0, 500);
        current.setValue(0);
        current.setStringPainted(true);
        pane.add(current);
        setContentPane(pane);
        //  pane.add(bu);
    }

    public void iterate() {
        while (num < 1000) {
            current.setValue(num);
            try {
                Thread.sleep(850);
            } catch (InterruptedException e) {
            }
            num += 150;
        }
    }

    public static void main(String[] arguments) {


        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        ProgressBar frame = new ProgressBar();
        //frame.pack();
        frame.setVisible(true);
        Dimension screenSize1 = Toolkit.getDefaultToolkit().getScreenSize();
//        frame.setLocation((screenSize1.width - 1750/2),(screenSize1.height- 150)/2);//Changes made by sudeep by manual value calculation:Date:7-01-2013
        int screenWidth = (int) screenSize1.getWidth();
        int screenHeight = (int) screenSize1.getHeight();

        int x = screenWidth / 4;
        int y = screenHeight / 4;
//        frame.setLocation((screenSize1.width/2),(screenSize1.height)/2);
        frame.setLocation((screenWidth - x) / 2, (screenHeight - y) / 2);
        frame.setSize(400, 55);


        //frame.setLocation(500, 300);
        USBRunnable usbRunnable = new USBRunnable();
        usbRunnable.runApps();
//        
//        StartMysqlServer mysqlConnection = new StartMysqlServer();
//        mysqlConnection.start();//start mysql Server
        frame.iterate();
        frame.setVisible(false);



        Login1 m = new Login1();
        m.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //l.setSize(screenSize);
        Dimension jInternalFrameSize = m.getSize();
        // Dimension jInternalFrameSize1 = frame.getSize();

        m.setLocation(((screenSize.width) - (jInternalFrameSize.width)) / 2,//Changes made by sudeep just put brackets:Date:7-01-2013
                ((screenSize.height) - (jInternalFrameSize.height)) / 2);
        //m.setLocation(400, 250);


    }
}