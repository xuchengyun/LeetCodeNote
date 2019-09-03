package LCQuestions;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class _341_FlattenNestedListIterator implements Iterator<Integer> {

    /**
     *
     Given a nested list of integers, implement an iterator to flatten it.
     Each element is either an integer, or a list -- whose elements may also be integers or other lists.
     Example 1:
     Input: [[1,1],2,[1,1]]
     Output: [1,1,2,1,1]
     Explanation: By calling next repeatedly until hasNext returns false,
     the order of elements returned by next should be: [1,1,2,1,1].
     Example 2:
     Input: [1,[4,[6]]]
     Output: [1,4,6]
     Explanation: By calling next repeatedly until hasNext returns false,
     the order of elements returned by next should be: [1,4,6].
     */

    Stack<NestedInteger> s = new Stack<>();
    public _341_FlattenNestedListIterator(List<NestedInteger> nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            s.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return s.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!s.isEmpty()) {
            NestedInteger cur = s.peek();
            if (cur.isInteger()) return true;
            cur = s.pop();
            for (int i = cur.getList().size() - 1; i >= 0; i--) {
                s.push(cur.getList().get(i));
            }
        }
        return false;
    }

    public class NestedInteger {
        public boolean isInteger() {
            return true;
        }

        public Integer getInteger() {
            return 0;
        }

        public List<NestedInteger> getList() {
            return null;
        }
    }
}
