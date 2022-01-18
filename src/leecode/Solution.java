package leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Solution {

    /**
    比较的时候，根据首数字排序，若是首字母相同，则根据第二位排序，没有第二位，则认为第二位等于第一位
     */
    public String largestNumber(int[] nums) {
        List<String> list = new ArrayList<>(nums.length);
        for(int i=0;i<nums.length;++i){
            list.add(""+nums[i]);
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String a,String b){
                int index=0;
                while(index<a.length() || index<b.length()){
                    char bt = index<b.length()?b.charAt(index):b.charAt(0);
                    char at = index<a.length()?a.charAt(index):a.charAt(0);
                    if(bt!=at){
                        return bt-at;
                    }
                    index++;
                }
                return 0;
            }
        });
        return concat(list);
    }

    public String concat(List<String>list){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<list.size();++i){
            sb.append(list.get(i));
        }
        return sb.toString();
    }
}