package gen.database.connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kasturi NovaSoft
 */
public class StopMysqlServer1 extends Thread {

    /**
     * @param args the command line arguments
     */
    static Process p;

    @Override
    public void run() {
        try {

            //StartMysqlServer.p = 
            Runtime.getRuntime().exec("server/bin/mysqladmin.exe --port=" + DatabaseConnection1.portNo + " --user=" + DatabaseConnection1.userName + " --password=" + DatabaseConnection1.password + " shutdown");
//              BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
//              String Error;
//
//              while ((Error = stdError.readLine()) != null) {
//                  System.out.println(Error);
//              }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
