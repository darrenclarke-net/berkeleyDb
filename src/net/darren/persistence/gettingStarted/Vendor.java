package net.darren.persistence.gettingStarted;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;

@Entity
public class Vendor {

    private String address;
    private String bizPhoneNumnber;
    private String city;
    private String repName;
    private String repPhoneNumber;
    private String state;

    @PrimaryKey
    private String vendor;

    private String zipcode;

    public void setRepName(String data) {
        this.repName = data;
    }

    public void setAddress(String data) {
        this.address = data;
    }

    public void setCity(String data) {
        this.city = data;
    }

    public void setState(String data) {
        this.state = data;
    }

    public void setZipcode(String data) {
        this.zipcode = data;
    }

    public void setBusinessPhoneNumber(String data) {
        this.bizPhoneNumnber = data;
    }

    public void setRepPhoneNumber(String data) {
        this.repPhoneNumber = data;
    }

    public void setVendorName(String data) {
        this.vendor = data;
    }

    public String getRepName() {
        return repName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getBusinessPhoneNumber() {
        return bizPhoneNumnber;
    }

    public String getRepPhoneNumber() {
        return repPhoneNumber;
    }
}
