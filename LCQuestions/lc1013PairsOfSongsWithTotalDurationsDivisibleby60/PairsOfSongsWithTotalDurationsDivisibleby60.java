package LCQuestions.lc1013PairsOfSongsWithTotalDurationsDivisibleby60;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PairsOfSongsWithTotalDurationsDivisibleby60 {
    // two sum(变种)
    // brute force
    public int numPairsDivisibleBy60(int[] time) {
        if (time == null || time.length < 2) throw new RuntimeException("input is not valid");
        int cnt = 0;
        for (int i = 0; i < time.length - 1; i++) {
            for (int j = i + 1; j < time.length; j++) {
                int sum = time[i] + time[j];
                if (sum % 60 == 0) cnt++;
            }
        }
        return cnt;
    }

    // HashTable
    public int numPairsDivisibleBy60_1(int[] time) {
        if (time == null || time.length == 0) throw new RuntimeException("input is not valid");
        int count[] = new int[60];
        for (int i = 0; i < time.length; i++) {
            count[time[i] % 60] ++;
        }

        int res = 0;
        res += count[0] * (count[0] - 1) / 2;
        res += count[30] * (count[30] - 1) / 2;
        for (int i = 1; i < 30; i++) {
            res += count[i] * count[60 - i];
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
