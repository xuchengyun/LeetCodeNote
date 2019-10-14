package LCQuestions;

import java.util.HashMap;
import java.util.Map;

public class _1166_DesignFileSystem {

    // Simple hashMap
    Map<String, Integer> map;
    public _1166_DesignFileSystem() {
        map = new HashMap<>();
    }

    public boolean createPath(String path, int value) {
        int index = path.lastIndexOf('/');
        if (!map.containsKey(path.substring(0, index))) {
            return false;
        }
        if (map.containsKey(path)) {
            return false;
        }
        map.putIfAbsent(path, value);
        return true;
    }

    public int get(String path) {
        if (map.containsKey(path)) {
            return map.get(path);
        }
        return -1;
    }

}
