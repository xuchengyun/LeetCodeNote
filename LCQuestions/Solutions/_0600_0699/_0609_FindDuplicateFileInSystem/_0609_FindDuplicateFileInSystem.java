package LCQuestions.Solutions._0600_0699._0609_FindDuplicateFileInSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class _0609_FindDuplicateFileInSystem {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();

        for (String path : paths) {
            String[] files = path.split(" ");
            String dir = files[0];
            for (int i = 1; i < files.length; i++) {
                String[] fileArr = files[i].split("\\(");
                String name = fileArr[0];
                String hash = fileArr[1].substring(0, fileArr[1].indexOf(")"));
                List<String> l = map.getOrDefault(hash, new ArrayList<String>());
                l.add(dir + "/" + name);
                map.put(hash, l);
            }
        }

        return map.values().stream().filter(l -> l.size() > 1).collect(Collectors.toList());
    }
}

