package LCQuestions.Solutions._2400_2499._2433_FindTheOriginalArrayOfPrefixXor;

public class _2433_FindTheOriginalArrayOfPrefixXor {
    public int[] findArray(int[] pref) {
        int[] res = new int[pref.length];
        for (int i = 0; i < pref.length; i++) {
            res[i] = i == 0 ? pref[i] : pref[i -1] ^ pref[i];
        }
        return res;
    }
}
