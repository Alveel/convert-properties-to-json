import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Must define one or more properties files as parameters.");
            System.exit(1);
        }

        JSONObject jsonPropertiesObject = new JSONObject();

        for (String inputFile : args) {
            Properties properties = readProperties(inputFile);
            JSONObject jsonProperties = convertPropertiesToJson(properties);
            jsonPropertiesObject.put(inputFile, jsonProperties);
        }

        returnJsonString(jsonPropertiesObject);
    }

    private static Properties readProperties(String inputFile) {
        Properties properties = new Properties();
        InputStream input;

        try {
            input = new FileInputStream(inputFile);
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return properties;
    }

    private static JSONObject convertPropertiesToJson(Properties properties) {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(properties);
        } catch (JSONException jex) {
            jex.printStackTrace();
        }

        return jsonObject;
    }

    private static void returnJsonString(JSONObject json) {
        String jsonString = json.toString(4);

        System.out.println(jsonString);
    }
}
