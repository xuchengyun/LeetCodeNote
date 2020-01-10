package LCQuestions;

public class _470_ImplementRand10UsingRand7 {
    // 拒绝采样 ！！很重要
    public int rand10() {
        while (true) {
            int rand49 = (rand7() - 1) * 7 + rand7();
            if (rand49 > 40) {
                continue;
            }
            return rand49 % 10 + 1;
        }
    }

    private int rand7() {
        return 1;
    }
}
