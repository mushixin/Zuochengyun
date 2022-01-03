package chapter_8_wanhao;

import java.util.Arrays;

public class Problem_28_SplitGold_redo {

	/**
	 * 14:10
	 * @param arr
	 * @return
	 */
	public static int getMinSplitCost(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		Arrays.sort(arr);
		/**
		 * dp[x][y] 从x，y的最小花费
		 */
		int[][] dp = new int[arr.length][arr.length];
		for (int length = 2; length <= arr.length; length++) {
			for (int start = 0; start + length - 1 < arr.length; start++) {
				int minCost = Integer.MAX_VALUE;
				int end = start + length - 1; // [start,end]
				for (int spl = start; spl <= end; spl++) {

				}

			}
		}


		return dp[0][arr.length-1];
	}

	public static void main(String[] args) {
		int[] arr = { 3, 9, 5, 2, 4, 4 };
//		int[] arr2 = {10,30,20};
		System.out.println(getMinSplitCost(arr));
//		System.out.println(getMinSplitCost(arr2));
	}
}
