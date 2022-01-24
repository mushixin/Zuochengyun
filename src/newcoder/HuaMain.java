package newcoder;

import java.util.*;
import java.lang.*;

public class HuaMain {

    public static boolean isNum(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

    public static boolean isError(char c) {
        return !isNum(c) && c != '.';
    }

    /**
     * //根据.做所有字符串的拆分，然后取，相邻的两段，然后是最长的即可。
     * 记录每一段的，开头数字长度和结尾数字长度。还有中间数字长度。考虑通过.拼接， 或者 中间数字的拼接。取一个最长的即可
     *
     * @param input
     * @return
     */
    public static String dealResult(String input) {
        /**
         * 先处理字符串，将连续两个.. 及非法字符，全部截取出来，替换为空格。非法字符中间的字符分别做统计。
         */
        while (input.indexOf("\\.\\.\\.") != -1) {
            input = input.replaceAll("\\.\\.\\.", "\\.\\.");
        }
        input = input.replaceAll("\\.\\.", " ");

        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); ++i) {
            if (isError(sb.charAt(i)) || (sb.charAt(i) == '.' && (i == 0 || i == sb.length() - 1))) {
                sb.setCharAt(i, ' ');
            }
        }
        input = sb.toString().replaceAll("  ", " ").trim();
        while (input.indexOf("  ")!=-1){
            input = input.replaceAll("  ", " ");
        }
        //每个串分别计算
        String[] inputs = input.split(" ");

        String res = "";
        for (int i = 0; i < inputs.length; i++) {
            String temp = calculate(inputs[i]);
            res = (temp.length() >= res.length() ? temp : res);
        }
        return res;
    }

    /**
     * 计算合法字符串中的最长数字串。
     * 1.1111.11.11.
     *
     * @param input
     * @return
     */
    public static String calculate(String input) {
        String[] nums = input.split("\\.");
        if(nums.length==1){
            return nums[0];
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i+1 < nums.length; i++) {
            if (nums[i].length() + nums[i + 1].length()+1 >= res.length()) {
                res = new StringBuilder();
                res.append(nums[i]);
                res.append(".");
                res.append(nums[i + 1]);
            }
        }

        return res.toString();
    }


    public static void main(String[] args) {
//        System.out.println(dealResult("abcd123.4567.890.123").equals("4567.890"));
//        System.out.println(dealResult("0000555").equals("0000555"));//
//        System.out.println(dealResult("").equals(""));//
//        System.out.println(dealResult(" ").equals(""));//
//        System.out.println(dealResult("a").equals(""));//
//        System.out.println(dealResult(".1.1.1.55.1..").equals("55.1"));//
//        System.out.println(dealResult(".f111.11a.1fafffst.5d5.a1..").equals("111.11"));//
//        System.out.println("43124891.541".length());
//        System.out.println("541.1341151".length());
//        System.out.println("185195151515".length());

        System.out.println(dealResult("43124891.541.1341151...51590i95fa185195151515.."));//


    }

}