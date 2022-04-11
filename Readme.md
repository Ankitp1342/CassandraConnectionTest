To run use the following:

**SSL Args:**<br>
    -Djavax.net.ssl.trustStore=/path/to/client.truststore<br> 
    <br>-Djavax.net.ssl.trustStorePassword=password123<br>
    <br>-Djavax.net.ssl.keyStore=/path/to/client.keystore<br>
    <br>-Djavax.net.ssl.keyStorePassword=password123<br>

**Run Command:**
java -jar ./jar/CassandraConnectionTest.jar <contact point> <user> <password> <true|false - ssl>
  
**Run Example:**
java -jar ./jar/CassandraConnectionTest.jar 127.0.0.1 "user" "password" false
