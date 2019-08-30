package LCQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _170_TwoSumIII {

    /**
     Design and implement a TwoSum class. It should support the following operations: add and find.
     add - Add the number to an internal data structure.
     find - Find if there exists any pair of numbers which sum is equal to the value.

     Example 1:

     add(1); add(3); add(5);
     find(4) -> true
     find(7) -> false
     Example 2:

     add(3); add(1); add(2);
     find(3) -> true
     find(6) -> false
     */

    /** Initialize your data structure here. */
    Map<Integer, Integer> map;
    public _170_TwoSumIII() {
        map = new HashMap<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0));
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int i = entry.getKey();
            int j = value - i;
            if ((i == j && entry.getValue() > 1) || (i != j && map.containsKey(j))) {
                return true;
            }
        }
        return false;
    }


    // 方法2
    private List<Integer> list;

    public void TwoSum() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public void add2(int number) {
        if (!map.containsKey(number)) {
            map.put(number, 1);
            list.add(number);
        } else {
            map.put(number, map.get(number) + 1);
        }
    }

    public boolean find2(int value) {
        for (int num1 : list) {
            int num2 = value - num1;
            if ((num1 == num2) && map.get(num1) > 1 || (num1 != num2 && map.containsKey(num2))) {
                return true;
            }
        }
        return false;
    }

}
