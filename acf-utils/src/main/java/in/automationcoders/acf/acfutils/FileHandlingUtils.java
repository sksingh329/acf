package in.automationcoders.acf.acfutils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHandlingUtils {
    public static String readFromFile(String fileName){
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
        }
        return contentBuilder.toString();
    }
}
