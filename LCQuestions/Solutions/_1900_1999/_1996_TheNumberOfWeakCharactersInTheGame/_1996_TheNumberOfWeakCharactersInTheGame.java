package LCQuestions.Solutions._1900_1999._1996_TheNumberOfWeakCharactersInTheGame;

import java.util.Arrays;

public class _1996_TheNumberOfWeakCharactersInTheGame {
    public int numberOfWeakCharacters(int[][] properties) {
        if (properties == null || properties.length == 0 || properties[0].length == 0) {
            return 0;
        }
        /**
         * sort attack in ascending order, sort defense in descending order, so
         * in case of a tie in the attack values, we can ignore the defense value of the pairs
         * with the same attack value.
        */
        Arrays.sort(properties, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int res = 0;
        int maxDefense = Integer.MIN_VALUE;
        for (int i = properties.length - 1; i >=0; i--) {
            if (properties[i][1] < maxDefense) {
                res++;
            }
            maxDefense = Math.max(maxDefense, properties[i][1]);
        }

        return res;
    }
}
