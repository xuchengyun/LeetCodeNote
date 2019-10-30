package LCQuestions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _554_BrickWall {

    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (List<Integer> w : wall) {
            int preSum = 0;
            for (int i = 0; i < w.size() - 1; i++) {
                preSum += w.get(i);
                map.put(preSum, map.getOrDefault(preSum, 0) + 1);
                max = Math.max(max, map.get(preSum));
            }
        }
        return wall.size() - max;
    }
    public static void main(String[] args) {

    }
}
