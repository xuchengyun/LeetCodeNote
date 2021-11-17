package LCQuestions;

public class Main {
    public int minimumDistance(String word) {
        if (word == null || word.length() <= 2) {
            return 0;
        }
        int[] p1 = getPos(word.charAt(0));
        int[] p2 = getPos(word.charAt(1));
        int res = 0;
        for (int i = 3; i < word.length(); i++) {
            int[] pos = getPos(word.charAt(i));
            int dis1 = Math.abs(pos[0] - p1[0]) + Math.abs(pos[1] - p1[1]);
            int dis2 = Math.abs(pos[0] - p2[0]) + Math.abs(pos[1] - p2[1]);
            if (dis1 < dis2) {
                res += dis1;
                p1 = pos;
            } else {
                res += dis2;
                p2 = pos;
            }

        }
        return res;
    }


    private int[] getPos(char c) {
        int r = (c - 'a') / 6;
        int col = (c - 'a') % 6;
        return new int[]{r, col};
    }


    public boolean isMatch(String s, String p) {

        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i - 1]) {
                dp[0][i + 1] = true;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
    public static void main(String[] args) {
    }
}
