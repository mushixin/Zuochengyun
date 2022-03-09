package leecode;

import java.util.HashSet;
import java.util.Set;

class Solution {

    /**
     * Set<Integer>dp[] = new Set[];
     * 合为x的元素出现了set次。
     */
    public boolean splitArraySameAverage(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        double avg = sum * 1.0 / nums.length;
        Set<Integer>[] dp = new Set[sum + 1];
        dp[0] = new HashSet<>();
        dp[0].add(0);
        int index = 0;
        while (index < nums.length) {
            for (int i = sum; i >= 0; --i) {
                if (i >= nums[index] && dp[i - nums[index]] != null) {
                    if (dp[i] == null) {
                        dp[i] = new HashSet<>();
                    }
                    Set<Integer> temp = dp[i - nums[index]];
                    for(Integer oldT:temp){
                        dp[i].add(1+oldT);
                    }

                    for(Integer times:dp[i]){
                        if (i*1.0/times == avg){
                            return true;
                        }
                    }
                }
            }
            index++;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().splitArraySameAverage(new int[]{1,2,3,4,5,6,7,8}));
    }
}