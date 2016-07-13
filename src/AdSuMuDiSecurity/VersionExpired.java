/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdSuMuDiSecurity;

/**
 *
 * @author admin
 */
import gen.other.login.SupremoLink;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VersionExpired extends JFrame {

    private JPanel pan;
    private JLabel website;
    final String dir = System.getProperty("user.dir");
    Image img = new ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png")).getImage();

    public VersionExpired() {
        this.setTitle("Purchase AdSuMuDi...");
        this.setSize(500, 90);
        this.setLocationRelativeTo(null);
        this.setIconImage(img);

        pan = new JPanel();

        JButton buttonPurchase = new JButton("Purchase Here...!");
        pan.add(buttonPurchase);

        website = new JLabel();


        website.setText("<html>Trial Version Time Period Expired Visit Us At:<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href=\"\">http://www.kasturinovasoft.com/</a><br>To Purchase Licenced Version Of AdSuMuDi</html>");
        website.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pan.add(website);
        this.setContentPane(pan);
        this.setVisible(true);

        goWebsite(website);

        buttonPurchase.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPurchaseActionPerformed(evt);
            }
        });
    }

    private void goWebsite(JLabel website) {
        website.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    try {
                        Desktop.getDesktop().browse(new URI("http://www.kasturinovasoft.com/"));
                    } catch (IOException ex) {
                        Logger.getLogger(SupremoLink.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (URISyntaxException ex) {
                }
            }
        });
    }

    public void buttonPurchaseActionPerformed(ActionEvent evt) {
        PurchaseLicence purchase = new PurchaseLicence("");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension jInternalFrameSize = purchase.getSize();
        purchase.setLocation(((screenSize.width) - (jInternalFrameSize.width)) / 2,//Changes made by sudeep just put brackets:Date:7-01-2013
                ((screenSize.height) - (jInternalFrameSize.height)) / 2);
        purchase.setVisible(true);
    }
}
