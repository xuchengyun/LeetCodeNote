package LCQuestions.lc380InsertDeleteGetRandomO1;

import java.util.*;

public class InsertDeleteGetRandomO1 {

    // Use arrayList and hashMap to solve this problem
    List<Integer> list;
    Map<Integer, Integer> map;
    Random rand;

    /**
     * Initialize your data structure here.
     */
    public InsertDeleteGetRandomO1() {
        list = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        int len = list.size();
        list.add(val);
        map.put(val, len);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int index = map.get(val);
        map.remove(val);
        int last = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        map.put(last, index);
        list.set(index, last);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        int len = list.size();
        int index = rand.nextInt(len);
        return list.get(index);
    }
}
