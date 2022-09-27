package LCQuestions.Solutions._0800_0899._0838_PushDominoes;

public class _0838_PushDominoes {
    // 2 pointers
    /**
	In this approach, you just need to find sections like this
	X .   .   .   . X
	i                j
	Where X can be 'R' or 'L' and in between there can be as many dots
	Now,
	- you know the length of mid-part
	- If char[i] == char[j] == 'R', means all go towards right (R)
	-  char[i]  == char[j] == 'L', means all go towards Left (L)
	-  If char[i] = 'L' and char[j] = 'R', means middle part is not affected so they remain '.'
	-  If char[i] = 'R' and char[j] = 'L', then it will affect the middle part.
	   The middle_part/2 close to i will be affected by 'R' and middle_part/2 close to j will be
	   effected by 'L'  and the last mid point (middle_part%2) will be unaffected due to equal
	   force from left and right so it remains '.'
    */
    public static String pushDominoes(String dominoes) {
        dominoes = "L" + dominoes + "R";
        int n = dominoes.length();
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 1;
        while (i < n && j < n) {
            if (dominoes.charAt(j) == '.') {
                j++;
                continue;
            }
            if (dominoes.charAt(i) == dominoes.charAt(j)) {
                while (i < j) {
                    sb.append(dominoes.charAt(j));
                    i++;
                }
            } else {
                if (dominoes.charAt(i) == 'L') {
                    sb.append('L');
                    i++;
                    while (i < j) {
                        sb.append('.');
                        i++;
                    }
                } else {
                    sb.append('R');
                    int mid = (j - i - 1) / 2;
                    if ((j - i) % 2 == 0) {
                        for (int x = 0; x < mid; x++) {
                            sb.append('R');
                        }
                        sb.append('.');
                        for (int x = 0; x < mid; x++) {
                            sb.append('L');
                        }
                    } else {
                        for (int x = 0; x < mid; x++) {
                            sb.append('R');
                        }
                        for (int x = 0; x < mid; x++) {
                            sb.append('L');
                        }
                    }
                    i = j;
                }
            }
            j++;
        }
        return sb.substring(1);
    }

    public static String pushDominoes2(String dominoes) {
        int N = dominoes.length();
        int[] indexes = new int[N+2];
        char[] symbols = new char[N+2];
        int len = 1;
        indexes[0] = -1;
        symbols[0] = 'L';

        for (int i = 0; i < N; ++i)
            if (dominoes.charAt(i) != '.') {
                indexes[len] = i;
                symbols[len++] = dominoes.charAt(i);
            }

        indexes[len] = N;
        symbols[len++] = 'R';

        char[] ans = dominoes.toCharArray();
        for (int index = 0; index < len - 1; ++index) {
            int i = indexes[index], j = indexes[index+1];
            char x = symbols[index], y = symbols[index+1];
            char write;
            if (x == y) {
                for (int k = i+1; k < j; ++k)
                    ans[k] = x;
            } else if (x > y) { // RL
                for (int k = i+1; k < j; ++k)
                    ans[k] = k-i == j-k ? '.' : k-i < j-k ? 'R' : 'L';
            }
        }

        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        System.out.println(pushDominoes2(".L.R...LR..L.."));
    }
}
