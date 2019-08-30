package LCQuestions;

import java.util.LinkedList;
import java.util.Queue;

public class _346_MovingAverageFromDataStream {
    Queue<Integer> q;
    int size;
    double sum;
    public _346_MovingAverageFromDataStream(int size) {
        q = new LinkedList<>();
        this.size = size;
        this.sum = 0;
    }

    public double next(int val) {
        if (q.size() == size) {
            sum -= q.remove();
        }
        sum += val;
        q.offer(val);
        return sum / size;
    }
}
