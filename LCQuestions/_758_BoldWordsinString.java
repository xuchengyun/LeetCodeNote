package LCQuestions;

public class _758_BoldWordsinString {
    public static void main(String[] args) {
        _758_BoldWordsinString obj = new _758_BoldWordsinString();
        obj.boldWords(new String[]{"ccb", "b", "d", "cba", "dc"}, "eeaadadadc");
    }

    public String boldWords(String[] words, String S) {
        int n = S.length();
        boolean[] mark = new boolean[n];
        mark(mark, S, words);
        return bold(mark, S);
    }

    private String bold(boolean[] mark, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mark.length; i++) {
            if (mark[i] && (i == 0 || !mark[i - 1])) {
                sb.append("<b>");
            }
            sb.append(s.charAt(i));
            if (mark[i] && (i == s.length() - 1 || !mark[i + 1])) {
                sb.append("</b>");
            }
        }
        return sb.toString();
    }

    private void mark(boolean[] mark, String s, String[] words) {
        for (String word : words) {
            for (int i = 0; i < s.length() - word.length(); i++) {
                if (word.equals(s.substring(i, word.length() + i))) {
                    for (int j = i; j < i + word.length(); j++) {
                        mark[j] = true;
                    }
                }
            }
        }
    }
}
