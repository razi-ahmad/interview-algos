package ada;

import java.util.ArrayList;
import java.util.List;

class TextUtils {

    public static String justified(String text, int columnWitdth) {
        String[] words = text.split(" ");
        StringBuilder result = new StringBuilder();
        int subStringlength = 0;
        List<String> subString = new ArrayList<>();

        for (String word : words) {
            if (subStringlength + word.length() > columnWitdth) {
                appendSpaces(columnWitdth, subStringlength - 1, subString, result);
                result.append("\n");
                subStringlength = 0;
                subString.clear();
            }
            subString.add(word);
            subStringlength += word.length() + 1;
        }
        if (subString.size() > 0) {
            appendSpaces(columnWitdth, subStringlength, subString, result);
        }
        return result.toString();
    }

    private static void appendSpaces(int columnWitdth, int subStringlength, List<String> subString, StringBuilder result) {
        if (subString.size() - 1 <= 0) {
            result.append(subString.get(0));
            return;
        }
        int spaces = (columnWitdth - subStringlength) / (subString.size() - 1);
        int mod = (columnWitdth - subStringlength) % (subString.size() - 1);
            for (int i = 0; i < subString.size() - 1; i++) {
                result.append(subString.get(i));
                result.append(" ");
                for (int j = 0; j < spaces; j++) {
                    result.append(" ");
                }
                if (mod > 0) {
                    result.append(" ");
                    mod--;
                }
            }
        result.append(subString.get(subString.size() - 1));
    }

    public static void main(String[] args) {
        String input = "Aenean volutpat aliquam lectus ac laoreet. Sed nibh purus, eleifend sit amet arcu molestie, molestie maximus enim.";
        System.out.println(justified(input, 30));
    }
}
//4 ->3-> 5/3->