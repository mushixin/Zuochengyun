package chapter_4_wanhao;

import utils.InputUtils;

import java.util.*;

/**
 * https://www.nowcoder.com/question/next?pid=123743&qid=25141&tid=51248947
 * 有一个整型数组A，代表数值不同的纸牌排成一条线。玩家a和玩家b依次拿走每张纸牌，规定玩家a先拿，玩家B后拿，但是每个玩家每次只能拿走最左或最右的纸牌，玩家a和玩家b都绝顶聪明，他们总会采用最优策略。请返回最后获胜者的分数。
 * <p>
 * 给定纸牌序列A及序列的大小n，请返回最后分数较高者得分数(相同则返回任意一个分数)。保证A中的元素均小于等于1000。且A的大小小于等于300。
 * <p>
 * 测试样例：
 * [1,2,100,4],4
 * 返回：101
 */
public class Cards {


    public static int cardGame(int[] A, int n) {
        /**
         * dp[x][y] 表示 纸牌从 x 到 y 范围内，A 可以拿多少分（B最优），间隙从1开始，逐渐调大步长，最后到全长。
         * A[x] + min(dp[x+2][y], dp[x+1][y-1] ) 因为B 会给A最少的分
         * A[y] + min(dp[x][y-2], dp[x+1][y-1] )
         */
        int[][] dp = new int[n][n];
        for (int len = 1; len <= n; len++) {
            for (int x = 0; x + len - 1 < n; x++) {
                int y = x + len - 1;//[x,y]范围内
                if (len == 1) {
                    dp[x][y] = A[x];
                } else if (len == 2) {
                    dp[x][y] = Math.max(A[x], A[y]);
                } else {
                    int left = A[x] + Math.min(dp[x + 2][y], dp[x + 1][y - 1]);
                    int right = A[y] + Math.min(dp[x][y - 2], dp[x + 1][y - 1]);
                    dp[x][y] = Math.max(left, right);
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
        }
        return Math.max(dp[0][n - 1], sum-dp[0][n - 1]) ;
    }

    /*这是一道很明显的动态规划题
    用F
    [l][r]表示先选的人能拿到的最高分
    用S
    [l][r]来表示后选的人能拿到的最高分
    如对于一组从0,1,2，...,n-1的数
    对于先选者，他有两种选法
    若先选者选A
    [0],则对于后面的1，...,n-1数组，他就变成了后选者，此时能拿到的分为A
    [0]+S
    [1][n-1]
    若先选者选A
    [n-1],则对于前面的数组0，...，n-2,同样变为后选者，此时能拿到得分为A
    [n-1]+S
    [0][n-2];
    所以 F
    [0][n-1]=max(A
    [0]+S
    [1][n-1],A
    [n-1]+S
    [0][n-2])
    对于后选者，他能能到的最高分是受先选者控制的，即他只能选到先选者留给他的最小值，将其转化为数学形式就是
    S
    [l][r]=min(F
    [l+1][r],F
    [l][r-1]);
    这里的最小值是先选者留给他的，他只能拿到最小值，打个比方，我是先选者，我若选A
    [0]，剩下的留给你选，这个时候主动权在你
    所以你能得到的最大分必为F
    [1][n-1],我若选A
    [n-1]，剩下的留给你选，这个时候主动权在你
    所以你能得到的分必为F
    [0][n-2],我肯定是要把能得到的分少的那个留给你，所以你只能得到Min(F
    [1][n-1],F
    [0][n-2]);
    */
    public static int cardGame_answer(int
                                [] A, int n) {
        int
                [][] F = new int
                [n][n];
        int
                [][] S = new int
                [n][n];
        for (int r = 0; r < n; r++) {
            F
                    [r][r] = A
                    [r];
            S
                    [r][r] = 0;
            for (int l = r - 1; l >= 0; l--) {
                F
                        [l][r] = Math.max(A
                        [l] + S
                        [l + 1][r], A
                        [r] + S
                        [l][r - 1]);
                S
                        [l][r] = Math.min(F
                        [l + 1][r], F
                        [l][r - 1]);
            }
        }
        return Math.max(F
                [0][n - 1], S
                [0][n - 1]);
    }


    public static void main(String[] args) {
        int[] input = InputUtils.generateIntArray(300);
        System.out.println(cardGame(input, input.length));
        System.out.println(cardGame_answer(input, input.length));

        int[] arr = {1, 2, 3, 100, 4};
        System.out.println(cardGame(arr, arr.length) == 8);
        int[] arr2 = {1, 2, 100, 4};
        System.out.println(cardGame(arr2, arr2.length) == 101);
        int[] arr3 = {1};
        System.out.println(cardGame(arr3, arr3.length) == 1);
        int[] arr4 = {1, 2};
        System.out.println(cardGame(arr4, arr4.length) == 2);

    }
}