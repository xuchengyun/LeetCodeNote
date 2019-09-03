package LCQuestions;

import java.util.PriorityQueue;

public class _295_FindMedianFromDataStream {

    /**
     Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
     For example,
     [2,3,4], the median is 3
     [2,3], the median is (2 + 3) / 2 = 2.5
     Design a data structure that supports the following two operations:
     void addNum(int num) - Add a integer number from the data stream to the data structure.
     double findMedian() - Return the median of all elements so far.
     Example:

     addNum(1)
     addNum(2)
     findMedian() -> 1.5
     addNum(3)
     findMedian() -> 2
     */
    /** initialize your data structure here. */
    // maxheap
    PriorityQueue<Long> small;
    // minheap
    PriorityQueue<Long> large;
    public _295_FindMedianFromDataStream() {
        small = new PriorityQueue<>((a, b) -> b.compareTo(a));
        large = new PriorityQueue<>();
    }

    public void addNum(int num) {
        large.add((long)num);
        small.add(large.poll());
        if (large.size() < small.size()) {
            large.add(small.poll());
        }
    }

    public double findMedian() {
        return large.size() > small.size() ? large.peek() : (large.peek() + small.peek()) / 2.0;
    }
}
