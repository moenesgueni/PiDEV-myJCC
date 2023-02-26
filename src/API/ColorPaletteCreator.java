package API;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import org.json.JSONArray;
import org.json.JSONObject;

public class ColorPaletteCreator {

    private URL url;
    private HttpURLConnection connection;
    private String input = "{\"model\":\"default\",\"input\":[";
    private int[][] colors;

    private void initialise() throws Exception {
        url = new URL("http://colormind.io/api/");
        connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");

    }

    public ColorPaletteCreator(String c1) throws Exception {
        initialise();
        input += c1 + ",\"N\",\"N\",\"N\",\"N\"]}";
        send();
    }
    public ColorPaletteCreator(String c1, String c2) throws Exception {
        initialise();
        input += c1 +","+ c2 + ",\"N\",\"N\",\"N\"]}";
        send();
    }
    public ColorPaletteCreator(String c1, String c2, String c3) throws Exception {
        initialise();
        input += c1 +","+ c2 +","+ c3 + ",\"N\",\"N\"]}";
        send();
    }
    public ColorPaletteCreator(String c1, String c2, String c3, String c4) throws Exception {
        initialise();
        input += c1 +","+ c2 +","+ c3 +","+ c4 + ",\"N\"]}";
        send();
    }

    private void send() throws Exception {
        System.out.println(input);
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(input);
        writer.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        String result = response.toString();
        //{"result":[[43,42,45],[116,84,78],[184,169,163],[226,236,232],[206,141,61]]}

        // Parse the JSON response string
        JSONObject responseJson = new JSONObject(response.toString());
        // Get the "result" array from the JSON object
        JSONArray resultArray = responseJson.getJSONArray("result");

        // Loop through the "result" array and extract the RGB values of each color
        //{"result":[[43,42,45],[116,84,78],[184,169,163],[226,236,232],[206,141,61]]}
        colors = new int[resultArray.length()][3];
        for (int i = 0; i < resultArray.length(); i++) {
            JSONArray colorArray = resultArray.getJSONArray(i);
            for (int j = 0; j < colorArray.length(); j++) {
                colors[i][j] = colorArray.getInt(j);
            }
        }
        // The "colors" array now contains the RGB values of the five colors
        // Each color is represented as an array of three integers, for red, green, and blue values
    }

    public int[][] getColors() {
        return colors;
    }

}
