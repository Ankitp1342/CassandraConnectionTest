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

        // Create the Cluster object:
        Cluster cluster = null;

        Collection<String> ids = new ArrayList<String>();


        try {

            if(args.length==4 && Boolean.parseBoolean(args[3])) {
                cluster = Cluster.builder()
                        .addContactPoint(args[0])
                        .withCredentials(args[1], args[2])
                        .withSSL()
                        .build();
            }else{
                cluster = Cluster.builder()
                        .addContactPoint(args[0])
                        .withCredentials(args[1], args[2])
                        .build();

            }
            Session session = cluster.connect();

            while(true) {
                try {
                    // Select the release_version from the system.local table:
                    ResultSet rs = session.execute("select release_version from system.local");
                    Row row = rs.one();
                    //Print the results of the CQL query to the console:
                    if (row != null) {
                        logger.info("Release Version" + row.getString("release_version"));
                    } else {
                        logger.error("An error occurred.");
                    }
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