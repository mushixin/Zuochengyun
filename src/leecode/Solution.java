package leecode;

import java.util.Arrays;

class Solution {
    int[]matchsticks;
    int length;//每条边的长度
    int currentLen = 0;//当前总长度
    boolean[]unUsed;// 没有使用火柴的index

    public boolean makesquare(int[] matchsticks) {
        this.matchsticks = matchsticks;
        if(matchsticks.length<=3){
            return false;
        }
        length = 0;
        for(int i=0;i<matchsticks.length;++i){
            length+=matchsticks[i];
        }
        if((length%4)!=0){
            return false;
        }
        Arrays.sort(matchsticks);
        length = length/4;
        if(matchsticks[matchsticks.length-1]>length){
            return false;
        }
        unUsed = new boolean[matchsticks.length];

        //先把最长的放入
        for(int i=0;i<matchsticks.length-1;++i){
            unUsed[i] = true;
        }
        currentLen = matchsticks[matchsticks.length-1];
        return dfs();
    }

    //用到了第index个火柴了
    public boolean dfs(){
        //拼好了三条边就完成了
        if(length*3 == currentLen){
            return true;
        }
        for(int index=matchsticks.length-2;index>=0;--index){
            if(unUsed[index] && canput(matchsticks[index])){
                currentLen += matchsticks[index];
                unUsed[index] = false;
                boolean res = dfs();
                if(res){
                    return true;
                }
                currentLen -= matchsticks[index];
                unUsed[index] = true;
            }

        }
        return false;
    }

    public boolean canput(int stickLen){
        //之前 必须小于length
        int leftLen = currentLen - currentLen/length*length;
        if(leftLen + stickLen>length ){
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        int[]stick = new int[]{1,5,6,7,8};
//        System.out.println(new Solution().makesquare(stick));
//        double d = 1e-6;
//        System.out.println(d );

        System.out.println(Arrays.toString(stick));
//        Arrays.asList();
    }
}