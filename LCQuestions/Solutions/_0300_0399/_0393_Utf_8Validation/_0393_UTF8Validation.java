package LCQuestions.Solutions._0300_0399._0393_Utf_8Validation;

public class _0393_UTF8Validation {
    public boolean validUtf8(int[] data) {
        if (data == null || data.length == 0) return false;
        for (int i = 0; i < data.length; i++) {
            if (data[i] > 0b11111111) return false;
            int numberOfBytes = 0;
            if ((data[i] & 128) == 0) {
                numberOfBytes = 1;
            } else if ((data[i] & 224) == 192) {
                numberOfBytes = 2;
            } else if ((data[i] & 240) == 224) {
                numberOfBytes = 3;
            } else if ((data[i] & 248) == 240) {
                numberOfBytes = 4;
            } else {
                return false;
            }
            for (int j = 1; j < numberOfBytes; j++) {
                if (i + j >= data.length) return false;
                if ((data[i + j] & 192) != 128) return false;
            }
            i = i + numberOfBytes - 1;
        }
        return true;
    }

    public boolean validUtf8_1(int[] data) {
        // cnt represent bytesRemaining
        int cnt = 0;
        for (int d : data) {
            if (cnt == 0) {
                if ((d >> 5) == 0b110) cnt = 1;
                else if ((d >> 4) == 0b1110) cnt = 2;
                else if ((d >> 3) == 0b11110) cnt = 3;
                else if ((d >> 7) != 0) return false; // if ((d>>7) == 0) represent 1 byte character
            } else {
                if ((d >> 6) != 0b10) return false;
                cnt--;
            }
        }
        return cnt == 0;
    }
}
