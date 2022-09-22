package LCQuestions.Solutions._0500_0599._0557_ReverseWordsInAStringIii;

public class _0557_ReverseWordsInAStringIii {
    public String reverseWords(String s) {
        String[] sarr = s.split(" ");
        for (int i = 0; i < sarr.length; i++) {
            sarr[i] = reverse(sarr[i]);
        }
        return String.join(" ", sarr);

    }

    private String reverse(String s) {
        char[] carr = s.toCharArray();
        int i = 0, j = carr.length - 1;
        while (i < j) {
            swap(carr, i++, j--);
        }
        return new String(carr);
    }

    private void swap(char[] carr, int i, int j){
        char tmp = carr[i];
        carr[i] = carr[j];
        carr[j] = tmp;
    }
}
