package LCQuestions;

public class _1234_ReplacetheSubstringforBalancedString {

    // sliding window
    public int balancedString(String s) {
        int[] count = new int[128];
        for (char c : s.toCharArray()) {
            count[c]++;
        }

        int total = s.length();
        int left = 0;
        int res = total;
        for (int i = 0; i < s.length() + 1; i++) {
            while (left <= i && count['Q'] <= total / 4 && count['W'] <= total / 4
                    && count['E'] <= total / 4 && count['R'] <= total / 4) {
                System.out.println(i);
                res = Math.min(res, i - left);
                count[s.charAt(left)]++;
                left++;
            }
            if (i == s.length()) {
                break;
            }
            count[s.charAt(i)]--;
        }
        return res;
    }

    public static void main(String[] args) {
        _1234_ReplacetheSubstringforBalancedString obj = new _1234_ReplacetheSubstringforBalancedString();
        obj.balancedString("WQWRQQQW");
    }
}
