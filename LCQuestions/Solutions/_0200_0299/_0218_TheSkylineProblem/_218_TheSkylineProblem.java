package LCQuestions.Solutions._0200_0299._0218_TheSkylineProblem;

import java.util.*;

public class _218_TheSkylineProblem {
    public static void main(String[] args) {
        _218_TheSkylineProblem obj = new _218_TheSkylineProblem();
        int[][] buildings = new int[][]{
                {2, 9, 10}
                , {3, 7, 15}
                , {5, 12, 12},
                {15, 20, 10}, {
                19, 24, 8
        }
        };
        obj.getSkyline(buildings);
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<Event> events = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int[] building : buildings) {
            events.add(new Event(building[0], building[2], true));
            events.add(new Event(building[1], building[2], false));
        }
        // sort events
        //1.比较x，把x小的放前面
        //2.如果两个events 都是 start，h大的放前面
        //3.如果两个events 都是 end h小的放前面
        //4.如果两个events 一前一后，start应该放在end前面
        events.sort((a, b) -> {
            if (a.x == b.x) {
                return (a.isStart ? -a.h : a.h) - (b.isStart ? -b.h : b.h);
            } else {
                return a.x - b.x;
            }
        });

        Queue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        q.offer(0);
        for (Event event : events) {
            int x = event.x;
            int h = event.h;
            boolean isStart = event.isStart;
            if (isStart) {
                if (h > q.peek()) {
                    res.add(Arrays.asList(x, h));
                }
                q.offer(h);
            } else {
                q.remove(h);
                if (h > q.peek()) {
                    res.add(Arrays.asList(x, q.peek()));
                }
            }
        }
        return res;
    }

    private static class Event {
        int x;
        int h;
        boolean isStart;

        public Event(int x, int h, boolean isStart) {
            this.x = x;
            this.h = h;
            this.isStart = isStart;
        }
    }

    public List<List<Integer>> getSkyline2(int[][] buildings) {
        List<Event> events = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int[] building : buildings) {
            events.add(new Event(building[0], building[2], true));
            events.add(new Event(building[1], building[2], false));
        }
        // sort events
        //1.比较x，把x小的放前面
        //2.如果两个events 都是 start，h大的放前面
        //3.如果两个events 都是 end h小的放前面
        //4.如果两个events 一前一后，start应该放在end前面
        events.sort((a, b) -> {
            if (a.x == b.x) {
                return (a.isStart ? -a.h : a.h) - (b.isStart ? -b.h : b.h);
            } else {
                return a.x - b.x;
            }
        });

        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 1);
        int preHeight = 0;

        for (Event event : events) {
            int x = event.x;
            int h = event.h;
            boolean isStart = event.isStart;
            if (isStart) {
                map.put(h, map.getOrDefault(h, 0) + 1);
            } else {
                if (map.get(h) == 1) {
                    map.remove(h);
                } else {
                    map.put(h, map.get(h) - 1);
                }
            }
            int cur = map.lastKey();
            if (cur != preHeight) {
                preHeight = cur;
                res.add(Arrays.asList(x, cur));
            }
        }
        return res;
    }
}
