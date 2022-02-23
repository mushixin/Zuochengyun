package leecode;

import java.util.Arrays;

class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        int n = nums.length;
        int[] lower = new int[n], upper = new int[n];
        find(lower, nums, k);
        find(upper, nums, k - 1);
        int ans = 0;
        for (int i = 0; i < n; i++) ans += upper[i] - lower[i];
        System.out.println(Arrays.toString(upper));
        System.out.println(Arrays.toString(lower));

        return ans;
    }
    void find(int[] arr, int[] nums, int k) {
        int n = nums.length;
        int[] cnt = new int[n + 1];  //[i,j]这段范围数字总数， cnt[x] 表示x这个数字的出现次数。
        for (int i = 0, j = 0, sum = 0; i < n; i++) {
            int right = nums[i];
            if (cnt[right] == 0) sum++;
            cnt[right]++;
            while (sum > k) {
                int left = nums[j++];
                cnt[left]--;
                if (cnt[left] == 0) sum--;
            }
            arr[i] = j;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().subarraysWithKDistinct(new int[]{1,2,1,2,3},2));
    }
}