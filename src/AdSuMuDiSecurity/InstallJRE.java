package AdSuMuDiSecurity;

import java.io.IOException;

public class InstallJRE {

    public static void main(String args[]) {

        final String dir = System.getProperty("user.dir");
        String filePath = dir + "\\jre-7-windows-i586.exe";
        try {

            Process p = Runtime.getRuntime().exec(filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}