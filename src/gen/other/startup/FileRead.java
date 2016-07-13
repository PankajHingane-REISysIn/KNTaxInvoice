/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.other.startup;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author ABC
 */
public class FileRead {

    public String fileRead1() {
        String str1[] = null;
        try {
            // Open the file that is the first 
            // command line parameter
//  FileInputStream fstream;//getClass().getResource
//  URL url = this.getClass().getResource("/others/abc.ds");
//  System.out.println(url);
//  File f=new File(url.toURI());
//            fstream = new FileInputStream(f);
            FileInputStream fstream = new FileInputStream("others/abc.ds");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                str1 = strLine.split(":");
                // System.out.print(strLine +"  pan  "+str1.length);;


            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.out.println("" + e.getMessage());
            System.exit(0);


        }

        return str1[1];
    }
}
