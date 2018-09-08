package selected;

import java.util.HashMap;
import java.util.Map;

public class SmallestWindowContainingAllChars {

    private static String swcc(String str, String pattern) {

        int n = str.length();
        int m = pattern.length();

        Map<Character, Integer> strMap = new HashMap<>();
        Map<Character, Integer> strPat = new HashMap<>();

        for(Character c : pattern.toCharArray()) {
            if(!strPat.containsKey(c)) {
                strPat.put(c, 1);
            } else {
                strPat.put(c, strPat.get(c) + 1);
            }
        }

        int start = 0;
        int count = 0;

        String result = "";
        int resultIndex = 0;

        for(int i = 0; i < n; i++) {
            if(!strMap.containsKey(str.charAt(i))) {
                strMap.put(str.charAt(i), 1);
            } else {
                strMap.put(str.charAt(i), strMap.get(str.charAt(i)) + 1);
            }

            if(strPat.get(str.charAt(i)) != null && strMap.get(str.charAt(i)) <= strPat.get(str.charAt(i))) {
                count ++;
            }

            if(count == m) {

                result = str.substring(start, i);
                resultIndex = start;

                while(strPat.get(str.charAt(start)) == null || strMap.get(str.charAt(start)) > strPat.get(str.charAt(start))) {
                    strMap.put(str.charAt(start), strMap.get(str.charAt(start)) - 1);
                    start ++;
                }

                if(i - start + 1 < result.length()) {
                    result = str.substring(start, i);
                    resultIndex = start;
                }
            }
        }

        System.out.println("Result start index: " + resultIndex);
        System.out.println("Result: " + result);

        return result;
    }

    public static void main(String[] args) {
        String str = "this is a test string";
        String pat = "tist";

        swcc(str, pat);
    }
}
