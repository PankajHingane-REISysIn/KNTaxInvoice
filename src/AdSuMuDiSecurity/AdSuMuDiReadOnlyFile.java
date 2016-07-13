package AdSuMuDiSecurity;

import java.io.File;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pc5
 */
public class AdSuMuDiReadOnlyFile {

    public static void makeFileReadOnly(File file) {

        if (file.exists()) {

            file.setReadOnly();
            System.out.println("Control visited..file set to read only mode");

        }

    }
}
