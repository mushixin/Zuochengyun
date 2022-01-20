package leecode;

import java.util.List;

/**
 * 这个题目反思下，dp的递归公式。
 * 一般是根据dp中的元素来递推的，dp[x] = dp[x-xx]这样，而不是说被外界来被动修改更新的。
 */
class Solution139_redo {

    public boolean wordBreak(String s, List<String> wordDict) {
        //dp[x][y] 代表s[x,y]是否可以被组成。
        //dp[x][y-len] 这里是len个字符串，等于s的末尾
        //dp[x+len][y] 这里是len个字符串，等于s的开头
        //这里直接去匹配dict 和 s，匹配到的部分，都置为true，然后回溯遍历即可。
        //如何回溯呢？从最后一列开始回溯，然后遍历为true的情况，横坐标，再当作起始结点，
        //起始结点，再从起始结点的列开始回溯。
        boolean[][]dp  = new boolean[s.length()][s.length()];
        for(String dict : wordDict){
            int startIndex = 0;
            while(true){
                startIndex = s.indexOf(dict,startIndex);
                if(startIndex!=-1 && !dp[startIndex][startIndex+dict.length()-1]){
                    dp[startIndex][startIndex+dict.length()-1]=true;
                }else{
                    break;
                }
                startIndex++;
            }
        }
        
        return traceBack(dp,0);
    }

    public boolean traceBack(boolean[][]dp,int startX){
        if(startX>=dp.length){
            return true;
        }
        //从0走3个字符串，那么所有的3都是可达的，将3这一列都置为true(也没必要)
        //若是第三行也作为起始行，可以到哪些列，再把这些列，都设置为true代表也都是可以到达的。
        for(int y=startX;y<dp[startX].length;++y){
            if(dp[startX][y]){
                // fillColumn(dp,y);
                boolean r = traceBack(dp,y+1);
                if(r){
                    return true;
                }
            }
        }
        return false;
    }

    //y的位置是可达的。
    public void fillColumn(boolean[][]dp,int y){
        for(int x=0;x<dp.length;x++){
            dp[x][y]=true;
        }
    }

}