/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.other.login;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ProgressBar extends JFrame {

    JLabel l1;
    JProgressBar current;
    JTextArea ta;
    JButton bu;
    Thread runner;
    int num = 0;

    public ProgressBar() {
        super("ProgressBar");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pane = new JPanel();
        bu = new JButton("go");
        pane.setLayout(new GridLayout());
        current = new JProgressBar(0, 100);
        current.setValue(0);
        current.setStringPainted(true);
        pane.add(current);
        setContentPane(pane);
        pane.add(bu);
    }

    public void iterate() {
        while (num < 1000) {
            current.setValue(num);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            num += 90;
        }
    }

    public static void main(String[] arguments) {
        ProgressBar frame = new ProgressBar();
        frame.pack();
        frame.setVisible(true);
        frame.iterate();
    }
}