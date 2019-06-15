package LCQuestions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        File f = new File("C:\\Users\\xuche\\intellijProject\\algorithm-exercise\\leetcodeNote\\LCQuestions");
        File[] fileList = f.listFiles();
        for (File k : fileList) {
            if (k.isDirectory()) {
                Pattern p=Pattern.compile("(\\d+)");
                Matcher m = p.matcher(k.getName());
                String num = null;
                if (m.find()) {
                    num = m.group();
                }
                num = "_" + num +"_";
                for (File sf : k.listFiles()) {
                    File newFile = new File(sf.getParent(), num + sf.getName());
                    Files.move(sf.toPath(), newFile.toPath());
                }

            }
        }
                System.out.println(f);

    }
}
