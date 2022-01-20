package leecode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    //1~9
    public static int[][] map = new int[10][10];
    
    public static boolean traceBack(){
        for(int x=1;x<=9;++x){
            for(int y=1;y<=9;++y){
                if(map[x][y]==0){
                    List<Integer> sets = getAvailable(x,y);
                    if(sets.size()<=0){
                        return false;
                    }
                    for(int ind=0;ind<sets.size();++ind){
                        map[x][y] = sets.get(ind);
                        boolean res = traceBack();
                        if(res){
                            return true;
                        }
                    }
                    map[x][y]=0;
                    return false;
                }
            }
        }
        return check();
    }
    
    
    //1-9 校验这个地图是否正常。
    public static boolean check(){
        for(int x=1;x<=3;++x){
            for(int y=1;y<=3;++y){
                Set<Integer> sets = new HashSet<>();
                for(int sx=(x-1)*3+1;sx<=(x-1)*3+3;sx++){
                    for(int sy=(y-1)*3+1;sy<=(y-1)*3+3;++sy){
                        sets.add(map[sx][sy]);
                    }
                }

                if(sets.size()<9){
                    return false;
                }
            }
        }

        for(int x=1;x<=9;++x){
            Set<Integer>sets = new HashSet<>();
            for(int y=1;y<=9;++y){
                sets.add(map[x][y]);
            }
            if(sets.size()<9){
                return false;
            }
        }
        for(int y=1;y<=9;++y){
            Set<Integer>sets = new HashSet<>();
            for(int x=1;x<=9;++x){
                sets.add(map[x][y]);
            }
            if(sets.size()<9){
                return false;
            }
        }

        return true;
    }
    
    //校验x,y这个位置，可以放哪些元素
    public static List<Integer> getAvailable(int x,int y){
        int startX = x<4?(1):(x<7?4:7);
        int startY = y<4?(1):(y<7?4:7);
        int endX = startX+2;
        int endY = startY+2;
        Set<Integer> sets = new HashSet<>();
        sets.add(1);       
        sets.add(2);
        sets.add(3);
        sets.add(4);
        sets.add(5);
        sets.add(6);
        sets.add(7);
        sets.add(8);
        sets.add(9);
        for(;startX<=endX;startX++){
            for(;startY<=endY;++startY){
                if(map[startX][startY]!=0){
                    sets.remove(map[startX][startY]);
                }
            }
        }
        for(int tempx=1;tempx<=9;tempx++){
            if(map[tempx][y]!=0){
                sets.remove(map[tempx][y]);
            }
            if(map[x][tempx]!=0){
                sets.remove(map[x][tempx]);
            }
        }
        return new ArrayList<>(sets);
    }

    public void solveSudoku(char[][] board) {
        for(int i=0;i<9;++i){
            for(int j=0;j<9;++j){
                if(board[i][j]>='1' && board[i][j]<='9'){
                    map[i+1][j+1]=(board[i][j]-'0');
                }
            }
        }
        traceBack();
        for(int i=0;i<9;++i){
            for(int j=0;j<9;++j){
                board[i][j] = (char)(map[i+1][j+1]+'0');
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        char[][] board={{'.','.','9','7','4','8','.','.','.'},{'7','.','.','.','.','.','.','.','.'},{'.','2','.','1','.','9','.','.','.'},{'.','.','7','.','.','.','2','4','.'},{'.','6','4','.','1','.','5','9','.'},{'.','9','8','.','.','.','3','.','.'},{'.','.','.','8','.','3','.','2','.'},{'.','.','.','.','.','.','.','.','6'},{'.','.','.','2','7','5','9','.','.'}};
        new Solution().solveSudoku(board);

    }
}