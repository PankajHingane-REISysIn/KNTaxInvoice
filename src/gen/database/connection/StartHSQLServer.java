/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.database.connection;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl.AclFormatException;

/**
 *
 * @author admin
 */
public class StartHSQLServer {

    public static void startHSQLServer() {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            Server hsqlServer = null;
            hsqlServer = new Server();
            HsqlProperties p = new HsqlProperties();

            p.setProperty("server.database.0", "data/default;crypt_key=604a6105889da65326bf35790a923932;crypt_type=AES");
            p.setProperty("server.dbname.0", "xdb");
            p.setProperty("server.database.1", "data/companies_details;crypt_key=604a6105889da65326bf35790a923932;crypt_type=AES");
            p.setProperty("server.dbname.1", "udb");
            p.setProperty("server.remote_open", true);

            hsqlServer.setProperties(p);
            hsqlServer.setPort(9001);

            hsqlServer.start();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StartHSQLServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(StartHSQLServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(StartHSQLServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StartHSQLServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AclFormatException ex) {
            Logger.getLogger(StartHSQLServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) {
        StartHSQLServer.startHSQLServer();
    }
}
