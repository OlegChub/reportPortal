package httpclient.helpers;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static httpclient.helpers.StringHelper.generateRandomString;
import static httpclient.helpers.StringHelper.replaceVarsWithRandomString;

public class JSONHelper {
    private static final String PATH_TO_JSON_FILE = "src/test/resources/testData/json/";

    static public String getJSONValueByKey(String key, JSONObject json) {
        return String.valueOf(json.get(key));
    }

    static public String getPreparedJSONFileAsString(String JSONFileName) {
        String patternBeginning = "${";
        String patternEnding = "}";
        String JSONAsString = convertJSONFileToString(JSONFileName);

        return replaceVarsWithRandomString(patternBeginning, patternEnding, JSONAsString, generateRandomString());
    }

    static public String convertJSONFileToString(String JSONFileName) {
        String pathToJSONFIle = String.format(PATH_TO_JSON_FILE + "%s.json", JSONFileName);
        try {
            return new String(Files.readAllBytes(Paths.get(pathToJSONFIle)));
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
