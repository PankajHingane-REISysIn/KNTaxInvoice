/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.other.startup;

/**
 *
 * @author ABC
 */
import java.io.*;

public class FileWrite {

    public FileWrite(String str) throws IOException {

        System.out.print("Please enter the file name to create : ");
        String file_name = "others/abc.ds";
        File file = new File(file_name);
        boolean exist = file.createNewFile();
        /*  if (!exist)
         {
         System.out.println("File already exists.");
         System.exit(0);
         }
         else*/
        {
            FileWriter fstream = new FileWriter(file_name);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(str);
            out.close();
            System.out.println("File created successfully.");
        }
    }
}
