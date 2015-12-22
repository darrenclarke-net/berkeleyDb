package net.darren.persistence.gettingStarted;

import java.io.File;

public class ExampleDatabasePut {

    private static File myDbEnvPAth = new File("/tmp/JEDB");
    private static File inventoryFile = new File("./inventory.txt");
    private static File vendorsFile = new File("./vendors.txt");

    private DataAccessor da;

    private static MyDbEnv myDbEnv = new MyDbEnv();

    private static void usage() {
        
    }
}
