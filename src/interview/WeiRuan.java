package interview;


import java.util.HashMap;
import java.util.Map;

public class WeiRuan {


    /**
     * 2 3 1 0 7 1 1 0 1
     * 1,4
     * left=0  right=0 [7,1]
     *
     */
    public static int getArray(int[]array) {
        Map<Integer,Integer> numCount = new HashMap<>();
        for(int i:array){
            numCount.put(i, numCount.getOrDefault(i,0)+1);
        }
        int result = 0;
        int left = 0;
        int right = -1;
        int currentNum = array[0];
        //[left，right]内的数据count
        Map<Integer,Integer> leftRight = new HashMap<>();
        while(right+1<array.length){
            right++;//[left,right]
            int  numTims = leftRight.getOrDefault(array[right],0)+1;
            leftRight.put(array[right], numTims);
            //不能再置换第一个数进去了。
            while((leftRight.get(currentNum) == numCount.get(currentNum) && right-left+1>leftRight.get(currentNum))
                    || right-left+1>leftRight.get(currentNum)+1) {
                leftRight.put(array[left], leftRight.get(array[left])-1);//次数减1
                left++;
                currentNum = array[left];
            }
            //[left,right]
            result = Math.max(result, right-left+1);
        }
        return  result;
    }


    public static void main(String[] args) {
//        System.out.println(getArray(new int[]{2, 3, 1, 0, 7, 1, 1, 0, 1}));
//        System.out.println(getArray(new int[]{2, 3, 1, 0, 7, 1, 1, 0, 1, 1}));
//        System.out.println(getArray(new int[]{2, 3, 7, 1, 1, 0, 1}));
//        System.out.println(getArray(new int[]{2, 3, 7, 1, 1, 0}));
//        System.out.println(getArray(new int[]{2, 3, 7, 1, 0}));
//        System.out.println(getArray(new int[]{0}));
//        System.out.println(getArray(new int[]{1, 1, 1, 2, 2, 0, 2, 2}));
//        System.out.println(getArray(new int[]{1, 1, 1, 2, 2, 0, 0, 2, 2}));
        System.out.println(getArray(new int[]{1,2,0,1}));

    }

}
