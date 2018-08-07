import pl.jalokim.propertiestojson.util.PropertiesToJsonConverter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Properties properties = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("/tmp/my.properties");

            // load a properties file
            properties.load(input);

            // get the property value and print it out
            //System.out.println(properties.getProperty("ldap.user.mappings.0"));

            // convert to json
            String json = new PropertiesToJsonConverter().parseToJson(input);
            //System.out.println(json.length());

            // manual
            Set<String> keys = properties.stringPropertyNames();
            for (String key : keys) {
                System.out.println(key + " : " + properties.getProperty(key));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
