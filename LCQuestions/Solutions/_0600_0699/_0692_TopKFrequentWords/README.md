# [692. Top K Frequent Words](https://leetcode.com/problems/top-k-frequent-words)

## Description

<p>Given an array of strings <code>words</code> and an integer <code>k</code>, return <em>the </em><code>k</code><em> most frequent strings</em>.</p>

<p>Return the answer <strong>sorted</strong> by <strong>the frequency</strong> from highest to lowest. Sort the words with the same frequency by their <strong>lexicographical order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;i&quot;,&quot;love&quot;,&quot;leetcode&quot;,&quot;i&quot;,&quot;love&quot;,&quot;coding&quot;], k = 2
<strong>Output:</strong> [&quot;i&quot;,&quot;love&quot;]
<strong>Explanation:</strong> &quot;i&quot; and &quot;love&quot; are the two most frequent words.
Note that &quot;i&quot; comes before &quot;love&quot; due to a lower alphabetical order.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;the&quot;,&quot;day&quot;,&quot;is&quot;,&quot;sunny&quot;,&quot;the&quot;,&quot;the&quot;,&quot;the&quot;,&quot;sunny&quot;,&quot;is&quot;,&quot;is&quot;], k = 4
<strong>Output:</strong> [&quot;the&quot;,&quot;is&quot;,&quot;sunny&quot;,&quot;day&quot;]
<strong>Explanation:</strong> &quot;the&quot;, &quot;is&quot;, &quot;sunny&quot; and &quot;day&quot; are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 500</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
	<li><code>k</code> is in the range <code>[1, The number of <strong>unique</strong> words[i]]</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow-up:</strong> Could you solve it in <code>O(n log(k))</code> time and <code>O(n)</code> extra space?</p>


## Solutions

<!-- tabs:start -->


### **Java**

```java
public class _0692_TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for (String word : words) {
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
        for (String word : words) {
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

```

### **...**

```

```

<!-- tabs:end -->