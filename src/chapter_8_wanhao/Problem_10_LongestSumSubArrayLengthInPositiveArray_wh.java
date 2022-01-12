package chapter_8_wanhao;

import chapter_8_arrayandmatrix.Problem_10_LongestSumSubArrayLengthInPositiveArray;
import utils.TimeUtils;

public class Problem_10_LongestSumSubArrayLengthInPositiveArray_wh {

    /**
     * sum[x][y] 从x加到y的累加和。二分法，可以做很多缓存，临时存储数据即可。
     * <p>
     * 这里左右指针，来去实现
     * left，right，然后这里，若是不等于目标期望。
     * arr[left,right]这个范围，若是小于sum，那就将right右移动。
     * 若是大于sum，将left右移动。
     *
     * 相当于二分查找，固定left，然后right二分查找，这里移动的方向比较固定。
     * 若是过大了，再移动left向右，换一个位置二分查找。
     *
     * left是不需要往左边移动的，
     * @param arr
     * @param k
     * @return
     */
    public static int getMaxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        int left = 0;
        int right = 0;//[left,right]
        int sum = arr[0];
        int maxLen = 0;
        while (right < arr.length && left < arr.length ) {
            if (sum == k) {
                if (maxLen < right - left + 1) {
                    maxLen = right - left + 1;
                }
                if(right+1>=arr.length){
                    break;
                }
                sum -= arr[left];
                left++;
                right++;
                sum += arr[right];
            } else if (sum < k) {
                right++;
                if (right < arr.length) {
                    sum += arr[right];
                } else {
                    break;
                }
            } else {
                sum -= arr[left];
                left++;
                if (left > right) {
                    if (right+1 < arr.length) {
                        right++;
                        sum += arr[right];
                    } else {
                        break;
                    }
                }
            }
        }

        return maxLen;
    }


    public static int[] generatePositiveArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 10) + 1;
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
        int len = 20_0000;
        int k = 10_0000;
        int[] arr = generatePositiveArray(len);
//        printArray(arr);
        TimeUtils.start();
        System.out.println(getMaxLength(arr, k));
        TimeUtils.stop();
        System.out.println(Problem_10_LongestSumSubArrayLengthInPositiveArray.getMaxLength(arr, k));
        TimeUtils.stop();
    }

}
