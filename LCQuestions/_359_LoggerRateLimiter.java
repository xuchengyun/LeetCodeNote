package LCQuestions;

import java.util.HashMap;
import java.util.Map;

public class _359_LoggerRateLimiter {

    Map<String, Integer> map;

    /**
     * Initialize your data structure here.
     */
    public _359_LoggerRateLimiter() {
        map = new HashMap<>();
    }

    /**
     * Returns true if the message should be printed in the given timestamp, otherwise returns false.
     * If this method returns false, the message will not be printed.
     * The timestamp is in seconds granularity.
     */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!map.containsKey(message) || timestamp - map.get(message) >= 10) {
            map.put(message, timestamp);
            return true;
        }
        return false;
    }
}
