package com.datastax.test;



import com.datastax.driver.core.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;


public class TestConnection {


    public static Logger logger = Logger.getLogger(TestConnection.class);
    /*


    -Djavax.net.ssl.trustStore=/path/to/client.truststore
-Djavax.net.ssl.trustStorePassword=password123
# If you're using client authentication:
-Djavax.net.ssl.keyStore=/path/to/client.keystore
-Djavax.net.ssl.keyStorePassword=password123


     */
    public static void main(String[] args) {

        ConnectionConfigProperties properties = new ConnectionConfigProperties();
        properties.setContactPoint(args[0]);
        properties.setUsername(args[1]);
        properties.setPassword(args[2]);
        properties.setUseSSL(Boolean.parseBoolean(args[3]));

        runTest(properties);
    }


    public static void runTest(ConnectionConfigProperties properties){

        // Create the Cluster object:
        Cluster cluster = null;

        Collection<String> ids = new ArrayList<String>();


        try {

            if(properties.getUseSSL()) {
                System.setProperty("javax.net.ssl.trustStore",properties.getTrustStore());
                System.setProperty("javax.net.ssl.trustStorePassword",properties.getTrustStorePassword());

                cluster = Cluster.builder()
                        .addContactPoint(properties.getContactPoint())
                        .withCredentials(properties.getUsername(), properties.getPassword())
                        .withSSL()
                        .build();
            }else{
                cluster = Cluster.builder()
                        .addContactPoint(properties.getContactPoint())
                        .withCredentials(properties.getUsername(), properties.getPassword())
                        .build();

            }
            Session session = cluster.connect();

            while(true) {
                try {
                    // Select the release_version from the system.local table:
                    long time = System.currentTimeMillis();
                    ResultSet rs = session.execute("select now() from system.local");
                    Row row = rs.one();
                    //Print the results of the CQL query to the console:
                    if (row != null) {
                        logger.info("Now Value: " + row.getObject(0));
                    } else {
                        logger.error("An error occurred.");
                    }

                    logger.info("Total Time (ms): " + (System.currentTimeMillis()-time));
                }catch(Exception e){
                    logger.error("Error making query", e);
                }
                Thread.sleep(3000);
            }

        } catch(Exception e){
            e.printStackTrace();
        } finally{
            if (cluster != null) cluster.close();
        }

    }



}