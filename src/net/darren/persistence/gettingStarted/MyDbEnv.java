package net.darren.persistence.gettingStarted;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.StoreConfig;

import java.io.File;

public class MyDbEnv {

    private Environment myEnv;
    private EntityStore store;

    public MyDbEnv() {}

    public void setUp(File envHome, boolean readOnly) throws DatabaseException {
        EnvironmentConfig myEnvConfig = new EnvironmentConfig();
        StoreConfig storeConfig = new StoreConfig();

        myEnvConfig.setReadOnly(readOnly);
        storeConfig.setReadOnly(readOnly);

        myEnvConfig.setAllowCreate(!readOnly);
        storeConfig.setAllowCreate(!readOnly);

        myEnv = new Environment(envHome, myEnvConfig);
        store = new EntityStore(myEnv, "EntityStore", storeConfig);
    }

    public EntityStore getEntityStore() {
        return store;
    }

    public Environment getEnv() {
        return myEnv;
    }

    public void close() {
        if (store != null) {
            try {
                store.close();
            } catch (DatabaseException e) {
                System.err.println("Error closing store: " + e.toString());
                System.exit(-1);
            }
        }

        if (myEnv != null) {
            try {
                myEnv.close();
            } catch (DatabaseException e) {
                System.err.println("Error closing environment: " + e.toString());
                System.exit(-1);
            }
        }
    }

}
