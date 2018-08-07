import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        String inputFile = "/dev/null";
        if (args.length > 0) {
            inputFile = args[0];
            System.out.println(inputFile);
        }

        Properties properties = readProperties(inputFile);
        JSONObject jsonProperties = convertPropertiesToJson(properties);
        returnJsonString(jsonProperties);
    }

    private static Properties readProperties(String inputFile) {
        Properties properties = new Properties();
        InputStream input;

        try {
            input = new FileInputStream("/tmp/my.properties");
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(2);
        }

        return properties;
    }

    private static JSONObject convertPropertiesToJson(Properties properties) {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(properties);
        } catch (JSONException jex) {
            jex.printStackTrace();
            System.exit(3);
        }

        return jsonObject;
    }

    private static void returnJsonString(JSONObject json) {
        String jsonString = json.toString(4);

        /*try (FileWriter file = new FileWriter("/tmp/my.json")) {
            file.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        System.out.println(jsonString);
    }
}
