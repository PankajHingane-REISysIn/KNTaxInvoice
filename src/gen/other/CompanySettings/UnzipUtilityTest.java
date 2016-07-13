/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.other.CompanySettings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class UnzipUtilityTest {

    public static void main(String[] args) {
	try {
	    String zipFilePath = "D:/databasecheck/ffdfdsgfd.abc";
	    String destDirectory = "C:/Check";
	    UnzipUtility unzipper = new UnzipUtility();
	    try {
		unzipper.unzip(zipFilePath, destDirectory);
	    } catch (Exception ex) {
		// some errors occurred
		ex.printStackTrace();
	    }

            /// dalete extra genearated file
	    Path target = Paths.get("C:/Check/sdf");
	    Files.delete(target);

	    
	    /// read DAtabase present in file
	    BufferedReader brin;
	    brin = new BufferedReader(new FileReader("E:/New5/aj.sql"));
	    String line = brin.readLine();
	    int count = 0;
	    while (line != null && count < 5) {
		if (!line.equals("")) {
		    System.out.println("AAAAAAAAAAAAAAAAAAAAAa" + line);
		    System.out.println("Count -------------" + count);
		    String databaseName = line;
		    System.out.println("sdfdfsd     " + databaseName.indexOf("Database:"));
		    String last_name = "";
		    if (count == 2) {
			StringTokenizer st = new StringTokenizer(databaseName, ":");
			while (st.hasMoreTokens()) {
			    last_name = st.nextToken();
			}
		    }
		    System.out.println("fdfsdfd         " + last_name);
		}
		line = brin.readLine();
		count++;
	    }
	    brin.close();
	    
	    
	} catch (IOException ex) {
	    Logger.getLogger(UnzipUtilityTest.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
}
