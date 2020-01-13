package LCQuestions;

public class _151_ReverseWordsinaString {
    public static void main(String[] args) {
        _151_ReverseWordsinaString obj = new _151_ReverseWordsinaString();
        obj.reverseWords("the sky is blue");
    }

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        s = s.trim();
        String[] words = s.split(" +");
        StringBuilder res = new StringBuilder();
        for (int i = words.length - 1; i > 1; i--) {
            res.append(words[i]).append(" ");
        }
        return res + words[0];
    }

    // reverse 2 times
    public String reverseWords1(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        s = s.trim();
        char[] ca = s.toCharArray();
        reverse(ca, 0, ca.length - 1);
        reverseWord(ca);
        return cleanSpace(ca);
    }

    private String cleanSpace(char[] ca) {
        int index = 0;
        int i = 0;
        while (i < ca.length) {
            while (i < ca.length && ca[i] == ' ') {
                i++;
            }
            while (i < ca.length && ca[i] != ' ') {
                ca[index++] = ca[i++];
            }
            if (i < ca.length) {
                ca[index++] = ' ';
            }
        }
        return new String(ca).substring(0, index);
    }

    private void reverseWord(char[] ca) {
        for (int i = 0; i < ca.length; i++) {
            if (ca[i] == ' ') {
                continue;
            }
            int j = i;
            while (j < ca.length && ca[j] != ' ') {
                j++;
            }
            reverse(ca, i, j - 1);
            i = j;
        }
    }

    private void reverse(char[] ca, int i, int j) {
        while (i < j) {
            swap(ca, i++, j--);
        }
    }

    private void swap(char[] ca, int i, int j) {
        char tmp = ca[i];
        ca[i] = ca[j];
        ca[j] = tmp;
    }
}
