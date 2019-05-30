package LCQuestions.lc318MaximumProductOfWordLenghs;

public class MaxinumProductOfWordLengths {

    public int maxProduct(String[] words) {
        if (words.length == 0 || words == null) return 0;
        int[] value = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            String temp = words[i];
            for (int j = 0; j < temp.length(); j++) {
                value[i] |= 1 << temp.charAt(j);
            }
        }

        int res = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((value[i] & value[j]) == 0) {
                    res = Math.max(res, words[i].length() * words[j].length());
                }
            }
        }
        return res;
    }
}
