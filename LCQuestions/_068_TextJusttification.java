package LCQuestions;

import java.util.ArrayList;
import java.util.List;

public class _068_TextJusttification {

    /**
     * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
     * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
     * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
     * For the last line of text, it should be left justified and no extra space is inserted between words.
     * Note:
     * A word is defined as a character sequence consisting of non-space characters only.
     * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
     * The input array words contains at least one word.
     * Example 1:
     * <p>
     * Input:
     * words = ["This", "is", "an", "example", "of", "text", "justification."]
     * maxWidth = 16
     * Output:
     * [
     * "This    is    an",
     * "example  of text",
     * "justification.  "
     * ]
     *
     * @param words
     * @param maxWidth
     * @return
     */
    public static void main(String[] args) {

    }

    /**
     * 首先要做的就是确定每一行能放下的单词数，这个不难，就是比较n个单词的长度和加上n - 1个空格的长度跟给定的长度L来比较即可
     * 找到了一行能放下的单词个数，然后计算出这一行存在的空格的个数，是用给定的长度L减去这一行所有单词的长度和。
     * 得到了空格的个数之后，就要在每个单词后面插入这些空格，这里有两种情况，比如某一行有两个单词"to" 和 "a"，给定长度L为6
     * 如果这行不是最后一行，那么应该输出"to   a"，如果是最后一行，则应该输出 "to a  "，所以这里需要分情况讨论，最后一行的处理方法和其他行之间略有不同。
     * 最后一个难点就是，如果一行有三个单词，这时候中间有两个空，如果空格数不是2的倍数，那么左边的空间里要比右边的空间里多加入一个空格，那么我们只需要用总的空格数除以空间个数
     * 能除尽最好，说明能平均分配，除不尽的话就多加个空格放在左边的空间里"
     *
     * @param words
     * @param maxWidth
     * @return
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> lines = new ArrayList<String>();
        int index = 0;
        while (index < words.length) {
            //count：该行所有单词累计总长度
            int count = words[index].length();
            //last:该行最后一个词的index
            int last = index + 1;
            while (last < words.length) {
                //out of bound
                if (words[last].length() + count + 1 > maxWidth) break;
                //plus one for the space, if its a perfect fit it will fit
                count += 1 + words[last].length();
                last++;
            }
            StringBuilder builder = new StringBuilder();
            //append该行第一个单词
            builder.append(words[index]);
            //这一行除去第一个已经append的单词，共剩下几个词语：diff 个：从index到last-1
            int diff = last - index - 1;
            // if last line or number of words in the line is 1, left-justified
            //最后一行：每个单词中间一个空格， 剩余补上空白
            if (last == words.length || diff == 0) {
                for (int i = index + 1; i < last; i++) {
                    builder.append(" ");
                    builder.append(words[i]);
                }
                for (int i = builder.length(); i < maxWidth; i++) {
                    builder.append(" ");
                }
            } else {
                //不是最后一行：middle justified
                //这一行总space的个数：（长度-累计单词总长度）
                //每个单词后面space的个数：（长度-累计单词总长度）/单词个数
                // r为需要平均分配到中间的空格总数
                int spaces = (maxWidth - count) / diff;
                int r = (maxWidth - count) % diff;
                for (int i = index + 1; i < last; i++) {
                    for (int k = spaces; k > 0; k--) {
                        builder.append(" ");
                    }
                    if (r > 0) {
                        builder.append(" ");
                        r--;
                    }
                    builder.append(" ");
                    builder.append(words[i]);
                }
            }
            lines.add(builder.toString());
            index = last;
        }
        return lines;
    }

    public List<String> fullJustify1(String[] words, int maxWidth) {
        int left = 0;
        List<String> result = new ArrayList<>();

        while (left < words.length) {
            int right = findRight(left, words, maxWidth);
            result.add(justify(left, right, words, maxWidth));
            left = right + 1;
        }

        return result;
    }

    private int findRight(int left, String[] words, int maxWidth) {
        int right = left;
        int count = words[right++].length();

        while (right < words.length) {
            if (words[right].length() + count + 1 > maxWidth) break;
            count += 1 + words[right++].length();
        }
        return right - 1;
    }

    private String justify(int left, int right, String[] words, int maxWidth) {
        // only one word in this line
        if (right - left == 0) {
            return padResult(words[left], maxWidth);
        }

        boolean isLastLine = right == words.length - 1;
        int numSpaces = right - left;
        int totalSpace = maxWidth - wordsLength(left, right, words);

        String space = isLastLine ? " " : blank(totalSpace / numSpaces);
        int remainder = isLastLine ? 0 : totalSpace % numSpaces;

        StringBuilder result = new StringBuilder();
        for (int i = left; i <= right; i++)
            result.append(words[i])
                    .append(space)
                    .append(remainder-- > 0 ? " " : "");

        return padResult(result.toString().trim(), maxWidth);
    }

    private int wordsLength(int left, int right, String[] words) {
        int wordsLength = 0;
        for (int i = left; i <= right; i++) {
            wordsLength += words[i].length();
        }
        return wordsLength;
    }

    private String padResult(String result, int maxWidth) {
        return result + blank(maxWidth - result.length());
    }

    private String blank(int length) {
        return new String(new char[length]).replace('\0', ' ');
    }
}
