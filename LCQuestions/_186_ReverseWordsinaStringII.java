package LCQuestions;

public class _186_ReverseWordsinaStringII {
    public void reverseWords(char[] s) {
        reverseChars(s, 0, s.length - 1);
        int i = 0;
        for (int j = 0; j < s.length; j++) {
            if (s[j] == ' ') {
                reverseChars(s, i, j - 1);
                i = j + 1;
            }
        }
        reverseChars(s, i, s.length - 1);
    }

    private void reverseChars(char[] s, int i, int j) {
        while (i < j) {
            swap(s, i++, j--);
        }
    }

    private void swap(char[] s, int i, int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }
}
