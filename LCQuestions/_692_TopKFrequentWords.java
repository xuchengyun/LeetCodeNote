package LCQuestions;

import java.util.*;

public class _692_TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (o1, o2) -> {
                    if (o1.getValue().equals(o2.getValue())) {
                        return o2.getKey().compareTo(o1.getKey());
                    } else {
                        return o1.getValue() - o2.getValue();
                    }
                }
        );

        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<String> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            res.add(0, pq.poll().getKey());
        }
        return res;
    }
}
