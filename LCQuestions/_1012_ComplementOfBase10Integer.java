package LCQuestions;

public class _1012_ComplementOfBase10Integer {
    public static void main(String[] args) {
        _1012_ComplementOfBase10Integer c = new _1012_ComplementOfBase10Integer();
        System.out.println(c.bitwiseComplement(5));
    }

    public int bitwiseComplement(int N) {
        int len = (int) Math.floor(Math.log(N) / Math.log(2)) + 1;
        return N ^ ((1 << len) - 1);
    }
}
