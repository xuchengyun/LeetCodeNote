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

    public List<String> topKFrequent1(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }

        List<String>[] bucket = new List[words.length + 1];
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            String s = entry.getKey();
            int freq = entry.getValue();
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(s);
        }

        List<String> res = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] == null) {
                continue;
            }
            Collections.sort(bucket[i]);
            List<String> tmp = bucket[i].subList(0, Math.min(bucket[i].size(), k));
            res.addAll(tmp);
            k -= tmp.size();
            if (k <= 0) {
                break;
            }
        }
        return res;
    }

}
