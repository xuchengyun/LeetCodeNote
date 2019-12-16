package LCQuestions;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class _379_DesignPhoneDiresctory {
    /**
     Design a Phone Directory which supports the following operations:
     get: Provide a number which is not assigned to anyone.
     check: Check if a number is available or not.
     release: Recycle or release a number.
     Example:
     // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
     PhoneDirectory directory = new PhoneDirectory(3);
     // It can return any available phone number. Here we assume it returns 0.
     directory.get();
     // Assume it returns 1.
     directory.get();
     // The number 2 is available, so return true.
     directory.check(2);
     // It returns 2, the only number that is left.
     directory.get();
     // The number 2 is no longer available, so return false.
     directory.check(2);
     // Release number 2 back to the pool.
     directory.release(2);
     // Number 2 is available again, return true.
     directory.check(2);
     */

    /**
     * Initialize your data structure here
     *
     * @param maxNumbers - The maximum numbers that can be stored in the phone directory.
     */
    Set<Integer> used = new HashSet<>();
    Queue<Integer> available = new LinkedList<>();
    int max;

    public _379_DesignPhoneDiresctory(int maxNumbers) {
        max = maxNumbers;
        for (int i = 0; i < maxNumbers; i++) {
            available.offer(i);
        }
    }

    /**
     * Provide a number which is not assigned to anyone.
     *
     * @return - Return an available number. Return -1 if none is available.
     */
    public int get() {
        if (available.size() == 0) {
            return -1;
        }
        int n = available.poll();
        used.add(n);
        return n;
    }

    /**
     * Check if a number is available or not.
     */
    public boolean check(int number) {
        if (number >= max || number < 0) {
            return false;
        }
        return !used.contains(number);
    }

    /**
     * Recycle or release a number.
     */
    public void release(int number) {
        if (used.remove(number)) {
            available.offer(number);
        }
    }
}
