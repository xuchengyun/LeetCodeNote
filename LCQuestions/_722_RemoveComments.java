package LCQuestions;

import java.util.ArrayList;
import java.util.List;

public class _722_RemoveComments {
    String lc = "//"; // line comment
    String bcs = "/*"; // block comment start
    String bce = "*/"; // block comment end

    // TODO
    public List<String> removeComments(String[] source) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < source.length; i++) {
            int idxLine = source[i].indexOf(lc); // get idex of line comment
            int idxBlock = source[i].indexOf(bcs); // get idex of block comment
            // if there is any comment in the current line
            if (idxLine != -1 || idxBlock != -1) {
                // case 1: line comment takes precedence
                if (idxLine != -1 && (idxLine < idxBlock || idxBlock == -1)) {
                    source[i] = source[i].substring(0, idxLine);
                } else {
                    // case 2: block comment takes precedence
                    String s = source[i].substring(0, idxBlock);
                    int start = idxBlock + 2;
                    int idxBlockEnd = -1;
                    while (true) {
                        idxBlockEnd = source[i].indexOf(bce, start);
                        if (idxBlockEnd != -1) {
                            break;
                        }
                        i++;
                        start = 0;
                    }
                    s += source[i].substring(idxBlockEnd + 2, source[i].length());
                    source[i] = s;
                    // recurse here
                    i--;
                    continue;
                }
            }
            // only add non-empty line
            if (!source[i].isEmpty()) {
                res.add(source[i]);
            }
        }
        return res;
    }
}
