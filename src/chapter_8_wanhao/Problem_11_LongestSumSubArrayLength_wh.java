package chapter_8_wanhao;

import chapter_8_arrayandmatrix.Problem_11_LongestSumSubArrayLength;
import utils.TimeUtils;

import java.util.HashMap;
import java.util.Map;

public class Problem_11_LongestSumSubArrayLength_wh {

    public static int maxLength2(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int maxLen = 0;
        //key为前i项累加和，value为i，  和冲突时，这里保留更早的和
        Map<Integer, Integer> map = new HashMap<>();
        //s[i]表示前i个元素的累加和
        int[] s = new int[arr.length];
        s[0] = arr[0];
        map.put(s[0], 0);
        for (int i = 1; i < arr.length; i++) {
            s[i] = s[i - 1] + arr[i];
            if(!map.containsKey(s[i])){
                map.put(s[i], i);
            }
        }
        if (map.containsKey(k)) {
            maxLen = map.get(k);
        }

        for (int i = 1; i + maxLen < arr.length; i++) {
            if (map.containsKey(k - s[i])) {
                int j = map.get(k-s[i]);
                maxLen = Math.max(maxLen, Math.abs(j-i)+1);
            }
        }

        return maxLen;
    }

    public static int maxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int maxLen = 0;
        //key为前i项累加和，value为i
        Map<Integer, Integer> map = new HashMap<>();
        //s[i]表示前i个元素的累加和
        int[] s = new int[arr.length];
        s[0] = arr[0];
        map.put(s[0], 0);
        for (int i = 1; i < arr.length; i++) {
            s[i] = s[i - 1] + arr[i];
            map.put(s[i], i);
        }

        for (int i = 1; i + maxLen < arr.length; i++) {
            for (int j = i + maxLen; j < arr.length; j++) {
                if (s[j] - s[i - 1] == k) {
                    maxLen = j - (i - 1);
//					System.out.println("i:"+i+" j:"+j);
                }
            }
        }

        return maxLen;
    }

    public static int[] generateArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 11) - 5;
        }
        return result;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = generateArray(20_0000);
//        printArray(arr);
        TimeUtils.start();
        System.out.println(maxLength(arr, 10));
        TimeUtils.stop();
        System.out.println(Problem_11_LongestSumSubArrayLength.maxLength(arr, 10));
        TimeUtils.stop();
        System.out.println(maxLength2(arr, 10));
        TimeUtils.stop();

    }

}
