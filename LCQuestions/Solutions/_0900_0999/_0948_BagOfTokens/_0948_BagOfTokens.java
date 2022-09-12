package LCQuestions.Solutions._0900_0999._0948_BagOfTokens;

import java.util.Arrays;

public class _0948_BagOfTokens {
    public int bagOfTokensScore(int[] tokens, int power) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }

        // Greedy
        Arrays.sort(tokens);
        int i = 0;
        int j = tokens.length - 1;
        int score = 0;
        while (i <= j) {
            while (i < tokens.length && tokens[i] <= power && power > 0) {
                power -= tokens[i++];
                score++;
            }

            if (i < j && score > 0) {
                power += tokens[j--];
                score--;
            } else {
                break;
            }
        }
        return score;
    }
}
