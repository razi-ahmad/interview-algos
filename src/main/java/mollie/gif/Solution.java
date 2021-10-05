package mollie.gif;

import java.io.*;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;


public class Solution {
    private static final Scanner scan = new Scanner(System.in);

    public static Set<String> readFile(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            Set<String> set = new HashSet<>();
            String line = br.readLine();
            while (line != null) {
                String[] temp = line.split(" ");
                String tempRes = temp[6];
                String lastWord = tempRes.substring(tempRes.lastIndexOf("/") + 1);
                if (lastWord.toLowerCase(Locale.ROOT).contains(".gif") && temp[8].equals("200")) {
                    set.add(lastWord);
                }
                line = br.readLine();
            }
            return set;
        }
    }

    public static void main(String args[]) throws Exception {
        // read the string filename
        String filename;
        filename = "/Users/razi.ahmad/IdeaProjects/dummy/src/com/mollie/gif/test.txt";//scan.nextLine();
        OutputStream os = null;
        Set<String> output=readFile(filename);
        StringBuilder sb=new StringBuilder();
        for (String s:output) {
            sb.append(s);
            sb.append("\n");
        }
        String data = sb.toString();
        System.out.println(data);
        String resultFileName = "gifs_" + filename;
        try {
            os = new FileOutputStream(resultFileName, true);
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}