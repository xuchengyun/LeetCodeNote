package LCQuestions;

public class _1160_FindWordsThatCanBeFormedbyCharacters {
    public int countCharacters(String[] words, String chars) {
        char[] charArr = chars.toCharArray();
        int[] map = new int[26];
        for (char c : chars.toCharArray()) {
            map[c - 'a']++;
        }
        int lenSum = 0;
        for (String word : words) {
            int[] temp = map.clone();
            boolean isGood = true;
            for (char c : word.toCharArray()) {
                temp[c - 'a']--;
                if (temp[c - 'a'] < 0) {
                    isGood = false;
                    break;
                }
            }
            if (isGood) {
                lenSum += word.length();
            }
        }
        return lenSum;
    }
}
