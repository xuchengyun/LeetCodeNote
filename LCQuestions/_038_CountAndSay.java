package LCQuestions;

public class _038_CountAndSay {
    public String countAndSay(int n) {
        String res = "1";
        while (n-- > 1) {
            StringBuilder sb = new StringBuilder();
            int cnt = 0;
            char c = res.charAt(0);
            for (int i = 0; i <= res.length(); i++) {
                // corner case(end)
                if (i != res.length() && res.charAt(i) == c) {
                    cnt++;
                } else {
                    sb.append(cnt);
                    sb.append(c);
                    if (i != res.length()) {
                        cnt = 1;
                        c = res.charAt(i);
                    }
                }
            }
            res = sb.toString();
        }
        return res;
    }
}
