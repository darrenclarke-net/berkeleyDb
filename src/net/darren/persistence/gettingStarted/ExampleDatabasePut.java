package net.darren.persistence.gettingStarted;

import com.sleepycat.je.DatabaseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExampleDatabasePut {

    private static File myDbEnvPath = new File("/tmp/JEDB");
    private static File inventoryFile = new File("./inventory.txt");
    private static File vendorsFile = new File("./vendors.txt");

    private DataAccessor da;

    private static MyDbEnv myDbEnv = new MyDbEnv();

    private static void usage() {
        System.out.println("ExampleDatabasePut [-h <env-directory>]");
        System.out.println("            [-i <inventory file>]");
        System.out.println("            [-v <vendors file>]");
        System.exit(-1);
    }

    public static void main(String args[]) {
        ExampleDatabasePut edp = new ExampleDatabasePut();

        try {
            edp.run(args);
        } catch (DatabaseException e) {
            System.out.println("ExampleDatabasePut: " + e.toString());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
            e.printStackTrace();
        } finally {
            myDbEnv.close();
        }
        System.out.println("All Done...");
    }

    private void run(String[] args) throws DatabaseException {
        parseArgs(args);

        myDbEnv.setUp(myDbEnvPath, false);

        /* open data accessor */
        da = new DataAccessor(myDbEnv.getEntityStore());

        System.out.println("loading vendors db.....");
        loadVendorsDb();

        System.out.println("loading inventory db......");
        loadInventoryDb();
    }

    private void parseArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                switch(args[i].charAt(1)) {
                    case 'h':
                        myDbEnvPath = new File(args[++i]);
                        break;
                    case 'i':
                        inventoryFile = new File(args[++i]);
                        break;
                    case 'v':
                        vendorsFile = new File(args[++i]);
                    default:
                        usage();
                }
            }
        }
    }

    private void loadInventoryDb() {
        List inventoryArray = loadFile(inventoryFile, 6);

        for (int i = 0; i <inventoryArray.size(); i++) {
            String[] sArray = (String[]) inventoryArray.get(i);
            String sku = sArray[1];

            Inventory theInventory = new Inventory();
            theInventory.setItemName(sArray[0]);
            theInventory.setSku(sArray[1]);
            theInventory.setVendorPrice(new Float(sArray[2]).floatValue());
            theInventory.setVendorInventory(new Integer(sArray[3]).intValue());
            theInventory.setCategory(sArray[4]);
            theInventory.setVendor(sArray[5]);

            da.inventoryBySku.put(theInventory);
        }
    }

    private List loadFile(File theFile, int numFields) {
        List<String[]> records = new ArrayList<String[]>();

        try {
            String theLine = null;
            FileInputStream fis = new FileInputStream(theFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            while ((theLine = br.readLine()) != null) {
                String[] theLineArray = theLine.split("#");
                if (theLineArray.length != numFields) {
                    System.out.println("Malformed line found in " + theFile.getPath());
                    System.out.println("Line was: " + theLine);
                    System.out.println("Length found was: " + theLineArray.length);
                    System.exit(-1);
                }
                records.add(theLineArray);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            System.err.println(theFile.getPath() + " does not exist.");
            e.printStackTrace();
            usage();
        } catch (IOException e) {
            System.err.println("IO Exception: " + e.toString());
            e.printStackTrace();
            System.exit(-1);
        }
        return records;
    }

    protected ExampleDatabasePut() {}

    private void loadVendorsDb() throws DatabaseException {
        List vendors = loadFile(vendorsFile, 8);

        for (int i = 0; i < vendors.size(); i++) {
            String[] sArray = (String[]) vendors.get(i);
            Vendor theVendor = new Vendor();
            theVendor.setVendorName(sArray[0]);
            theVendor.setAddress(sArray[1]);
            theVendor.setCity(sArray[2]);
            theVendor.setState(sArray[3]);
            theVendor.setZipcode(sArray[4]);
            theVendor.setBusinessPhoneNumber(sArray[5]);
            theVendor.setRepName(sArray[6]);
            theVendor.setRepPhoneNumber(sArray[7]);

            da.vendorByName.put(theVendor);
        }
    }
}
