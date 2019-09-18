package LCQuestions;
/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf);
 */
public class _158_ReadCharactersGivenRead4IICallmultipletime {

    private int count = 0;
    private int pointer = 0;
    private char[] temp = new char[4];

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        int index = 0;
        while (index < n) {
            if (pointer == 0) {
                count = read4(temp);
            }
            while (index < n && pointer < count) {
                buf[index++] = temp[pointer++];
            }
            if (pointer == count) {
                pointer = 0;
            }
            if (count < 4) {
                break;
            }
        }
        return index;
    }

    private int read4(char[] temp) {
        return 0;
    }
}
