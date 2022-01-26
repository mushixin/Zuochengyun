package interview;


import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    /**
     * 编码后的最小长度。
     *
     * @param words
     * @return
     */
    public static int minimumLengthEncoding(String[] words) {
        /**
         * 长的先生成，若是某个串x是y的子串，应该把y先拼接。
         */
        Arrays.sort(words, (o1, o2) -> o2.length() - o1.length());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; ++i) {
            if (sb.indexOf(words[i] + "#") == -1) {
                sb.append(words[i]);
                sb.append("#");
            }
        }
        return sb.length();
    }

    public static void main(String[] args) {
        String[] words = {"time", "me", "bell"};
        System.out.println(minimumLengthEncoding(words));

        String[] words2 = {"time", "ime2", "bell"};
        System.out.println(minimumLengthEncoding(words2) == "tim2#ime2#bell#".length());//tim2#ime2#bell#
    }
}
