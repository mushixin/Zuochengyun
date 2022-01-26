package leecode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        ArrayList<Long>gapCount = new ArrayList<>();
        for(List<Integer>wallTemp:wall){
            long column=0;
            for(int i=0;i<wallTemp.size()-1;++i){
                column = column + wallTemp.get(i);
                gapCount.add(column);
            }
        }
        gapCount.sort(new Comparator<Long>(){
            public int compare(Long a,Long b){
                return (int)(a-b);
            }
        });
        //统计每列的缝隙，选择缝隙最多的那一列。count[i]表示第i个缝隙，[1,sum-1]个缝隙。
        int maxGap = 0;
        for(int i=0;i<gapCount.size();++i){
            int start = i;
            int end = start + maxGap;
            while(end<gapCount.size() && gapCount.get(end).longValue()==gapCount.get(start).longValue()){
                i = end;
                end++;
            }
            maxGap = Math.max(maxGap,end-start);
            if(maxGap>=wall.size()){
                break;
            }
        }
        return wall.size()-maxGap;
    }

    public static void main(String[] args) {
        List<List<Integer>> wall = new ArrayList<>();
        List<Integer>w1 = new ArrayList<>();
        w1.add(10000_0000);
        w1.add(10000_0000);
        w1.add(100);
        w1.add(100);
        System.out.println(w1.get(0)==w1.get(1));


//        List<Integer>w2 = new ArrayList<>();
//        w2.add(10000_0000);
//        w2.add(10000_0000);
//
//        wall.add(w1);
//        wall.add(w2);
//        System.out.println(new Solution().leastBricks(wall));
    }
}