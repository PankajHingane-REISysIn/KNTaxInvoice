/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.dto;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintStream;
import javax.swing.JFileChooser;

/**
 *
 * @author pc5
 */
public class MessageBox {

    BufferedImage mImage;
    String name, name1;
    String path = "";

    public MessageBox() {
        String source = filechoose();
        File inputFile = new File(source);
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

        int r = chooser.showSaveDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {
            name1 = chooser.getSelectedFile().getAbsolutePath();
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
            }
        }
        return name1;
    }
}
