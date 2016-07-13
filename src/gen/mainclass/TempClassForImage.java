package gen.mainclass;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.border.*;

public class TempClassForImage implements Border {

    private final Image image;
    static Dimension dimension = new Dimension();

    public TempClassForImage(Image image, Dimension siz) {
        this.image = image; //working
        dimension = siz;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawImage(image, 0, 0, null);     //working fine
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}
