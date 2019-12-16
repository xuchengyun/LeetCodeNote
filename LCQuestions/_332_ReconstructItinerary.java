package LCQuestions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class _332_ReconstructItinerary {

    HashMap<String, PriorityQueue<String>> map;
    List<String> res;

    //DFS
    public List<String> findItinerary(List<List<String>> tickets) {
        map = new HashMap<>();
        res = new LinkedList<>();
        for (List<String> ticket : tickets) {
            map.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            map.get(ticket.get(0)).offer(ticket.get(1));
        }

        dfs("JFK");
        return res;
    }

    private void dfs(String departure) {
        PriorityQueue<String> arrivals = map.get(departure);
        while (arrivals != null && !arrivals.isEmpty()) {
            dfs(arrivals.poll());
        }
        res.add(0, departure);
    }
}
