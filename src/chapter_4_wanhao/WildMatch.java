package chapter_4_wanhao;

import java.util.*;

public class WildMatch {

    public static boolean chkWildMatch(String A, int lena, String B, int lenb) {
        B = B.replaceAll("\\*","\\*\\|");
        String[] Bs = B.split("\\*");// 每一段，都去和A去尽快匹配，即可
        for (int i = 0; i < Bs.length; i++) {
            Bs[i] = Bs[i].replaceAll("\\|","");
        }
        int Aend = 0;
        for (int i = 0; i < Bs.length; i++) {
            String b = Bs[i];
            int end = match(A, Aend, b);
            if (end == -1) {
                return false;
            } else {
                Aend = end;
            }
        }
        return true;
    }

    /**
     * 返回首次匹配的末尾index（不包括）
     */
    public static int match(String A, int startIndex, String B) {
        if (B.length()==0){
            return startIndex;
        }
        for (int start = startIndex; start + B.length() <= A.length(); start++) {
            int end = start + B.length();// A[start,end)
            boolean match = true;
            for (int i = 0; i < B.length(); i++) {
                if (A.charAt(start + i) != B.charAt(i) && B.charAt(i)!='.') {
                    match = false;
                    break;
                }
            }
            if (match) {
                return end;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(chkWildMatch("abcd",4,".*",2));
    }
}