package leecode;

import java.util.LinkedList;

class Solution {

    /**
    dfs 数据量过大了，这里可以剪枝，每个点只能走一遍，

    dp[x][y][z] 使用z个消除能力，到达x,y所需要的最少步数，记录到这里
    这里从上下左右都可以去更新。
    这里就更新得太多了。

    
    bfs，感觉可以，每个步骤记录下使用的消除能力。
    Status{
        int x;
        int y;
        int step;
        int xiaochu;
    }
    一旦找到了目标路径，就返回次数即可。
    */

    public class Status{
        int x;
        int y;
        int xiaochu;
        public Status(int x,int y,int xiaochu){
            this.x= x;
            this.y = y;
            this.xiaochu = xiaochu;
        }
    }
    int[][] grid;
    int k;

    public int shortestPath(int[][] grid, int k) {
        int m = grid.length-1;
        int n = grid[0].length-1;
        this.grid = grid;
        this.k = k;

        LinkedList<Status> queue = new LinkedList<>();
        queue.addLast(new Status(0,0,0));
        LinkedList<Status> nextQueue = new LinkedList<>();
        int currentStep = 0;
        int[][] around = {{0,1},{0,-1},{1,0},{-1,0}};

        while (queue.size()>0) {
            Status first = queue.removeFirst();
            if (first.x==m && first.y==n) {
                return currentStep;
            }
            for(int i=0;i<4;++i){
                int nextx = first.x + around[i][0];
                int nexty = first.y + around[i][1];
                if(available(nextx,nexty)){
                    int newxiaochu = grid[nextx][nexty]=='1'? (first.xiaochu+1):(first.xiaochu);
                    if(newxiaochu>k){
                        continue;
                    }
                    System.out.println("nextx"+nextx + " nexty"+nexty+" newxiaochu"+newxiaochu);
                    nextQueue.addLast(new Status(nextx,nexty, newxiaochu));
                }
            }

            if(queue.size()==0){
                queue = nextQueue;
                nextQueue = new LinkedList();
                currentStep++;
            }

        }
        return -1;
    }

    public boolean available(int x,int y){ 
        if(x<0||y<0||x>=grid.length|| y>=grid[x].length){
            return false;
        }
        return true;
    }

}