package LCQuestions;

import java.util.List;

public class _120_Triangle {
    // dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j])
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = 1; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (j == 0) {
                    triangle.get(i).set(j , (triangle.get(i).get(j) + triangle.get(i - 1).get(j)));
                } else if (j == triangle.get(i).size() - 1) {
                    triangle.get(i).set(j , (triangle.get(i).get(j) + triangle.get(i - 1).get(j - 1)));
                } else {
                    triangle.get(i).set(j , Math.min(triangle.get(i).get(j) + triangle.get(i - 1).get(j),
                            triangle.get(i).get(j) + triangle.get(i - 1).get(j - 1)
                    ));
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int e : triangle.get(triangle.size() - 1)) {
            min = Math.min(min, e);
        }
        return min;
    }

    public int minimumTotal1(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size() + 1];
        for(int i = triangle.size() - 1; i >= 0; i--){
            for(int j = 0; j < triangle.get(i).size(); j++){
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
