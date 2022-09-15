package LCQuestions.Solutions._2000_2099._2007_FindOriginalArrayFromDoubledArray;

public class _2007_FindOriginalArrayFromDoubledArray {
    public int[] findOriginalArray(int[] changed) {
        if (changed == null || changed.length == 0) return new int[0];
        int length = changed.length;
        if (length % 2 != 0) {
            return new int[0];
        }
        Arrays.sort(changed);
        Map<Integer, Integer> freq = new HashMap<>();

        for (int value: changed) {
            freq.put(value, freq.getOrDefault(value, 0) + 1);
        }

        int[] original= new int[changed.length / 2];
        int index = 0;
        for (int i = 0; i < length; i++) {
            int val = changed[i];
            if (!freq.containsKey(val)) {
                continue;
            }

            original[index++] = val;
            int curCnt = freq.get(val);
            if (curCnt == 1) {
                freq.remove(val);
            } else {
                freq.put(val, curCnt - 1);
            }

            if (!freq.containsKey(val * 2)) {
                return new int[0];
            }
            int doubleCnt = freq.get(val * 2);

            if (doubleCnt == 1) {
                freq.remove(val * 2);
            } else {
                freq.put(val * 2, doubleCnt - 1);
            }
        }
        return original;
    }
}
