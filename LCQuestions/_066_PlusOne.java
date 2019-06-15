package LCQuestions;

public class _066_PlusOne {
    public int[] plusOne(int[] digits) {
        int[] res = new int[digits.length];
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int cur = carry + digits[i];
            carry = cur / 10;
            res[i] = cur % 10;
        }

        if (res[0] == 0) {
            res = new int[digits.length + 1];
            res[0] = 1;
            return res;
        }
        return res;
    }

    public int[] plusOne1(int[] digits) {
        int[] res = digits;
        int i = digits.length - 1;
        while (i >= 0) {
            if (res[i] == 9) {
                res[i] = 0;
            } else {
                res[i] += 1;
                break;
            }
            i--;
        }

        if (res[0] == 0) {
            res = new int[digits.length + 1];
            res[0] = 1;
        }

        return res;
    }
}
