package LCQuestions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class _281_ZigzagIterator {

    /**
     Given two 1d vectors, implement an iterator to return their elements alternately.
     Example:
     Input:
     v1 = [1,2]
     v2 = [3,4,5,6]
     Output: [1,3,2,4,5,6]
     Explanation: By calling next repeatedly until hasNext returns false,
     the order of elements returned by next should be: [1,3,2,4,5,6].
     Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
     Clarification for the follow up question:
     The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example:
     Input:
     [1,2,3]
     [4,5,6,7]
     [8,9]
     Output: [1,4,8,2,5,9,3,6,7].
     * @param v1
     * @param v2
     */
    int i, j;
    List<Integer> v1;
    List<Integer> v2;
    public _281_ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.i = 0;
        this.j = 0;
        this.v1 = v1;
        this.v2 = v2;
    }

    public int next() {
        return i <= j ? v1.get(i++) : v2.get(j++);
    }

    public boolean hasNext() {
        if (i >= v1.size()) {
            return j < v2.size();
        }
        if (j >= v2.size()) {
            return i < v1.size();
        }
        return true;
    }
    /**   分界线  **/
    private Iterator<Integer> i1, j1, temp;

    private void ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        i1 = v1.iterator();
        j1 = v2.iterator();
    }

    public int next1() {
        if (i1.hasNext()) {
            temp = j1;
            j1 = i1;
            i1 = temp;
        }
        return i1.next();
    }

    public boolean hasNext1() {
        return i1.hasNext() || j1.hasNext();
    }


    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>();
        l1.add(1);
        List<Integer> l2 = new ArrayList<>();
        _281_ZigzagIterator z = new _281_ZigzagIterator(l1, l2);
        while (z.hasNext()) {
            System.out.println(z.next());
        }
    }
}
