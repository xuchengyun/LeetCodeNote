package LCQuestions;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _451_SortCharactersByFrequency {

    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> pq
                = new PriorityQueue<>((a, b) -> (b.getValue() - a.getValue()));
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.offer(entry);
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();
            for (int i = 0; i < entry.getValue(); i++) {
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }

    public String frequencySort1(String s) {
        String[] bucket = new String[s.length() + 1];
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char key : map.keySet()) {
            int freq = map.get(key);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < freq; i++) {
                sb.append(key);
            }
            bucket[freq] = bucket[freq] == null ? sb.toString() : bucket[freq] + sb.toString();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != null) {
                sb.append(bucket[i]);
            }
        }
        return sb.toString();
    }
}
