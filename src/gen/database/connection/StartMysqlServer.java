package gen.database.connection;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Kasturi NovaSoft
 */
public class StartMysqlServer extends Thread {

    /**
     * @param args the command line arguments
     */
    static Process p;

    @Override
    public void run() {
        try {

            StartMysqlServer.p = Runtime.getRuntime().exec("server/bin/mysqld --defaults-file=server/my.ini --standalone --console --basedir=server/");
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String Error;

            while ((Error = stdError.readLine()) != null) {
                System.out.println(Error);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
