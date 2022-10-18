package LCQuestions.Solutions._0000_0099._0038_CountAndSay;

public class _0038_CountAndSay {
    public String countAndSay(int n) {
        String res = "1";
        while (n-- > 1) {
            StringBuilder sb = new StringBuilder();
            int cnt = 0;
            char c = res.charAt(0);
            for (int i = 0; i < res.length(); i++) {
                // corner case(end)
                if (res.charAt(i) == c) {
                    cnt++;
                } else {
                    sb.append(cnt).append(c);
                    cnt = 1;
                    c = res.charAt(i);
                }
            }
            sb.append(cnt).append(c);
            res = sb.toString();
        }
        return res;
    }
}
