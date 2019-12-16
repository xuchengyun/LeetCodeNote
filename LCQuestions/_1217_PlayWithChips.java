package LCQuestions;

public class _1217_PlayWithChips {
    public int minCostToMoveChips(int[] chips) {
        int odd = 0;
        int even = 0;
        for (int i = 0; i < chips.length; i++) {
            even += chips[i] % 2;
            odd += (chips[i] + 1) % 2;
        }
        return Math.min(even, odd);
    }
}
