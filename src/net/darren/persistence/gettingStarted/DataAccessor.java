package net.darren.persistence.gettingStarted;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.SecondaryIndex;

public class DataAccessor {
    public DataAccessor(EntityStore store) throws DatabaseException {

        /* Primary Key for Inentory */
        inventoryBySku = store.getPrimaryIndex(String.class, Inventory.class);

        /* secondary key for Inventory class */
        inventoryByName = store.getSecondaryIndex(inventoryBySku, String.class, "itemName");

        /* primary key for Vendor.class */
        vendorByName = store.getPrimaryIndex(String.class, Vendor.class);
    }

    /* Inventory Accessors */
    PrimaryIndex<String, Inventory> inventoryBySku;
    SecondaryIndex<String,String,Inventory> inventoryByName;

    /* Vendor Accessors */
    PrimaryIndex<String,Vendor> vendorByName;

}
