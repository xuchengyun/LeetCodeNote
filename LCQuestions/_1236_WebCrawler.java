package LCQuestions;

import java.util.*;

public class _1236_WebCrawler {

    // bfs
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        Queue<String> q = new LinkedList<>();
        List<String> res = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        q.offer(startUrl);
        visited.add(startUrl);
        res.add(startUrl);
        while (!q.isEmpty()) {
            String cur = q.poll();
            for (String url : htmlParser.getUrls(cur)) {
                if (getHostName(cur).equals(getHostName(url)) && !visited.contains(url)) {
                    q.offer(url);
                    visited.add(url);
                    res.add(url);
                }
            }
        }
        return res;
    }

    // dfs
    public List<String> crawl1(String startUrl, HtmlParser htmlParser) {
        List<String> res = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        helper(startUrl, htmlParser, res, visited);
        return res;
    }

    private void helper(String startUrl, HtmlParser htmlParser, List<String> res, Set<String> visited) {
        if (visited.contains(startUrl)) {
            return;
        }
        visited.add(startUrl);
        res.add(startUrl);
        for (String url : htmlParser.getUrls(startUrl)) {
            if (getHost(url).equals(getHost(startUrl))) {
                helper(url, htmlParser, res, visited);
            }
        }
    }

    private String getHost(String url) {
        return url.split("/")[2];
    }

    private String getHostName(String url) {
        String[] str = url.split("/");
        return str[2];
    }

    private class HtmlParser {
        public String[] getUrls(String cur) {
            return new String[]{""};
        }
    }
}
