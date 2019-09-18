package LCQuestions;

import java.util.Arrays;

public class _135_Candy {

    /**
     There are N children standing in a line. Each child is assigned a rating value.
     You are giving candies to these children subjected to the following requirements:
     Each child must have at least one candy.
     Children with a higher rating get more candies than their neighbors.
     What is the minimum candies you must give?
     Example 1:
     Input:    [1,0,2]
     Output: 5 (2,1,2)
     Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
     Example 2:

     Input:     [1,2,2]
     Output: 4  (1,2,1)

     ratings: [4, 5, 1, 1, 3, 7]
     candies: [1, 1, 1, 1, 1, 1]
     Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
     The third child gets 1 candy because it satisfies the above two conditions.
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        for (int i = 0; i < ratings.length - 1; i++) {
            if (ratings[i + 1] > ratings[i]) {
                candies[i + 1] = candies[i] + 1;
            }
        }

        for (int i = ratings.length - 1; i >= 1; i--) {
            if (ratings[i - 1] > ratings[i]) {
                candies[i - 1] = Math.max(candies[i - 1], candies[i] + 1);
            }
        }

        int sum = 0;
        for (int k : candies) {
            sum += k;
        }
        return sum;
    }
}
