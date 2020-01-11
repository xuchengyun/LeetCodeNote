package OthersAlgorithms;

public class ReplaceIntegerWithoutTemp {
    void swap(int a, int b) {
        a = a + b;
        b = a - b;
        a = a - b;
    }

    void swap1(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
    }
}
