package chapter_4_wanhao;

import chapter_4_recursionanddp.Problem_02_MinPathSum;

import static utils.InputUtils.generateMatrix;

public class Problem_02_MinPathSum_wh {

	/**
	 * 左上角到右下角，路径最短
	 *
	 * @param m
	 * @return
	 */
	public static int minPathSum1(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		//dp[i][j] 从起点到这一点的最短路径长度
		int[][] dp = new int[m.length][m[0].length];
		dp[0][0] = m[0][0];
		//初始化第一行和第一列，然后路径长度直接加。
		for (int i = 1; i < m[0].length; i++) {
			dp[0][i] = dp[0][i - 1] + m[0][i];
		}
		for (int i = 1; i < m.length; i++) {
			dp[i][0] = dp[i - 1][0] + m[i][0];
		}

		for (int i = 1; i < m.length; i++) {
			for (int j = 1; j < m[i].length; j++) {
				int minInt = dp[i][j - 1] < dp[i - 1][j] ? dp[i][j - 1] : dp[i - 1][j];
				dp[i][j] = m[i][j] + minInt;
			}
		}
//		printMatrix(dp);
		return dp[m.length-1][m[0].length-1];
	}

	/**
	 * 动态规划，矩阵压缩。
	 * 22,13,15,12
	 * @param m
	 * @return
	 */
	public static int minPathSum2(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		//dp[i] 从起点到这一行的第i个元素的最短路径长度
		int[] dp = new int[m[0].length];
		dp[0] = m[0][0];
		for (int i = 1; i < m[0].length; i++) {
			dp[i] = m[0][i] + dp[i - 1];
		}
		for (int i = 1; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				if (j == 0) {
					dp[j] = dp[j] + m[i][j];
				} else {
					dp[j] = m[i][j] + Math.min(dp[j-1] , dp[j]) ;
				}
			}
		}
//		printArr(dp);
		return dp[m[0].length - 1];
	}

	public static void main(String[] args) {
		 int[][] m = generateMatrix(3001, 441);
//		int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 },
//				{ 8, 8, 4, 0 } };
//		int [][] m = {
//				{87,196,78,52,93},
//				{146,91,198,164,187},
//				{86,18,6,191,24},
//				{3,142,67,127,78}
//		};

//		printMatrix(m);

		System.out.println(minPathSum1(m));
		System.out.println(minPathSum2(m));
		System.out.println(Problem_02_MinPathSum.minPathSum1(m));


	}
}
