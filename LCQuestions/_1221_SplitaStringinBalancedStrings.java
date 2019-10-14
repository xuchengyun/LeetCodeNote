package LCQuestions;

public class _1221_SplitaStringinBalancedStrings {

    public int balancedStringSplit(String s) {
        int res = 0;
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == 'L') {
                count--;
            } else {
                count++;
            }
            if (count == 0) {
                res++;
            }
        }
        return res;
    }
}
