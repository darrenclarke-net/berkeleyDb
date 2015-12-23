package net.darren.persistence.gettingStarted;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

@Entity
public class Inventory {

    @PrimaryKey
    private String sku;

    @SecondaryKey(relate= Relationship.MANY_TO_ONE)
    private String itemName;

    private String category;
    private String vendor;
    private int vendorInventory;
    private float vendorPrice;

    public void setSku(String data) {
        this.sku = data;
    }

    public void setItemName(String data) {
        this.itemName = data;
    }

    public void setCategory(String data) {
        this.category = data;
    }

    public void setVendorInventory(int data) {
        this.vendorInventory = data;
    }

    public void setVendorPrice(float data) {
        this.vendorPrice = data;
    }

    public String getSku() {
        return sku;
    }

    public String getItemName() {
        return itemName;
    }

    public String getCategory() {
        return category;
    }

    public int getVendorInventory() {
        return vendorInventory;
    }

    public String getVendor() {
        return vendor;
    }

    public float getVendorPrice() {
        return vendorPrice;
    }

    public void setVendor(String s) {
        this.vendor = s;
    }
}
