package LCQuestions.lc1012ComplementOfBase10Integer;

public class ComplementOfBase10Integer {
    public int bitwiseComplement(int N) {
        int len = (int)Math.floor(Math.log(N) / Math.log(2)) + 1;
        return N ^ ((1 << len) - 1);
    }

    public static void main(String[] args) {
        ComplementOfBase10Integer c = new ComplementOfBase10Integer();
        System.out.println(c.bitwiseComplement(5));
    }
}
