package leecode;

import java.util.*;

class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(-1);
        for(int i=0;i<nums.length;++i){
            if((nums[i]%2)==1){
                list.add(i);
            }
        }
        list.add(nums.length);
        int result = 0;
        for(int start=1;start+k-1<list.size()-1;++start){
            int end = start+k-1;//[start,end]
            //
            int rightEnd = 1;
            int leftStart = 1;
            if(start-1>=0){
                leftStart = list.get(start) - list.get(start-1);
            }
            if(end+1<list.size()){
                rightEnd = list.get(end+1)-list.get(end);
            }

            result = result + (leftStart)*(rightEnd);
        }

        return result;
    }

    public static void main(String[] args) {
//        int[] nums = {1,1,2,1,1};
//        System.out.println(new Solution().numberOfSubarrays(nums,3));
        int[] nums2 = {2,2,2,1,2,2,1,2,2,2};
        System.out.println(new Solution().numberOfSubarrays(nums2,2));


    }
}