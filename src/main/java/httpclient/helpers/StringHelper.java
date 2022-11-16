package httpclient.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StringHelper {
    static public String generateRandomString() {
        int length = 8;
        Random random = new Random();
        return random.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    static public String replaceVarsWithRandomString(String patternBeginning, String patternEnding, String originalString, String replacementString) {
        StringBuilder originalStringAsSB = new StringBuilder(originalString);
        int startIndex = originalStringAsSB.indexOf(patternBeginning);
        String result = originalString;

        Map<Integer, Integer> map = new HashMap<>();
        if (startIndex > -1) {
            while (true) {
                int start = originalStringAsSB.indexOf(patternBeginning, startIndex);
                int end = originalStringAsSB.indexOf(patternEnding, startIndex);

                if (start > -1) {
                    map.put(start, end);
                    startIndex++;
                } else {
                    break;
                }
            }
            if (map.size() != 0) {
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    originalStringAsSB.replace(entry.getKey(), entry.getValue() + 1, replacementString);
                }
                result = originalStringAsSB.toString();
            }
        }

        return result;

    }
}
