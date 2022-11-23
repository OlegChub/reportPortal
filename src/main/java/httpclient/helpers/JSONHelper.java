package httpclient.helpers;

import exeptions.ProceedFailedException;
import httpclient.log.HttpClientLogger;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static httpclient.helpers.StringHelper.generateRandomString;
import static httpclient.helpers.StringHelper.replaceVarsWithRandomString;

public class JSONHelper {
    private static final String PATH_TO_JSON_FILE = "src/test/resources/testData/json/";
    private static final String PATTERN_BEGINNING = "${";
    private static final String PATTERN_ENDING = "}";

    static public String getJSONValueByKey(String key, JSONObject json) {
        return String.valueOf(json.get(key));
    }

    static public String getPreparedJSONFileAsString(String JSONFileName) {
        return replaceVarsWithRandomString(PATTERN_BEGINNING, PATTERN_ENDING, convertJSONFileToString(JSONFileName),
                generateRandomString());
    }

    static public String convertJSONFileToString(String JSONFileName) {
        String pathToJSONFIle = String.format(PATH_TO_JSON_FILE + "%s.json", JSONFileName);
        try {
            return new String(Files.readAllBytes(Paths.get(pathToJSONFIle)));
        } catch (IOException e) {
            HttpClientLogger.logger.error("Failed to read JSON file");
            throw new ProceedFailedException("Check file: " + e.getMessage());
        }
    }
}
