package LCQuestions;

public class _622_DesignCircularQueue {
    int size;
    int[] data;
    int count;
    int head;
    int tail;

    /**
     * Initialize your data structure here. Set the size of the queue to be k.
     */
    public _622_DesignCircularQueue(int k) {
        data = new int[k];
        size = k;
        count = 0;
        head = 0;
        tail = 0;
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        data[head] = value;
        head = (head + 1) % size;
        count++;
        return true;
    }

    /**
     * Delete an element from the circular queue. Return true if the operation is successful.
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        data[tail] = 0;
        tail = (tail + 1) % size;
        count--;
        return true;
    }

    /**
     * Get the front item from the queue.
     */
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return data[tail];
    }

    /**
     * Get the last item from the queue.
     */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return data[(size + (head - 1)) % size];

    }

    /**
     * Checks whether the circular queue is empty or not.
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Checks whether the circular queue is full or not.
     */
    public boolean isFull() {
        return count == size;
    }
}
