package LCQuestions;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class _460_LFUCache {

    int min;
    int capacity;
    Map<Integer, Integer> valueMap;
    Map<Integer, Integer> freqMap;
    Map<Integer, LinkedHashSet<Integer>> lists;

    public _460_LFUCache(int capacity) {
        min = -1;
        valueMap = new HashMap<>();
        freqMap = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!valueMap.containsKey(key)) {
            return -1;
        }
        update(key);
        return valueMap.get(key);
    }

    private void update(int key) {
        int freq = freqMap.get(key);
        freqMap.put(key, freq + 1);
        lists.get(freq).remove(key);
        LinkedHashSet<Integer> tmp = lists.getOrDefault(freq + 1, new LinkedHashSet<>());
        tmp.add(key);
        lists.put(freq + 1, tmp);
        if (freq == min && lists.get(freq).size() == 0) {
            min++;
        }
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        if (valueMap.containsKey(key)) {
            valueMap.put(key, value);
            update(key);
            return;
        }
        if (valueMap.size() >= capacity) {
            int removed = lists.get(min).iterator().next();
            lists.get(min).remove(removed);
            valueMap.remove(removed);
        }
        valueMap.put(key, value);
        freqMap.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }
}

