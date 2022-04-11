To run use the following:

JVM Args for SSL: 
-Djavax.net.ssl.trustStore=/path/to/client.truststore
-Djavax.net.ssl.trustStorePassword=password123
# If you're using client authentication:
-Djavax.net.ssl.keyStore=/path/to/client.keystore
-Djavax.net.ssl.keyStorePassword=password123

**Run Command:**
java -jar ./jar/CassandraConnectionTest.jar <contact point> <user> <password> <true|false - ssl>
  
**Run Example:**
java -jar ./jar/CassandraConnectionTest.jar 127.0.0.1 "user" "password" false
