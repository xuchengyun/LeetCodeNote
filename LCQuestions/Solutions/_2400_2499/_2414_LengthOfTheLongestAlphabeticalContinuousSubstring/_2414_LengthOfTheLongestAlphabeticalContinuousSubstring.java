package LCQuestions.Solutions._2400_2499._2414_LengthOfTheLongestAlphabeticalContinuousSubstring;

public class _2414_LengthOfTheLongestAlphabeticalContinuousSubstring {
    public int longestContinuousSubstring(String s) {
        char[] cArr = s.toCharArray();
        int len = s.length();
        // 2 pointers;
        int res = 1;
        int cur = 1;
        for (int i = 0; i < len - 1; i++) {
            if (cArr[i + 1] - cArr[i] == 1) {
                cur++;
            } else{
                cur = 1;
            }
            res = Math.max(cur, res);
        }
        return res;
    }
}
