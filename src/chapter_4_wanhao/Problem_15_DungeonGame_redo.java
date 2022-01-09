package chapter_4_wanhao;

import chapter_4_recursionanddp.Problem_15_DungeonGame;
import utils.InputUtils;
import utils.TimeUtils;

/**
 * dp还是出错了呀
 * 这里还是不能用这样的方式，不满足最优子结构。
 * 就是说，minhp[x][y] 不是依赖上面或者右边的结点血量最少值。
 *
 * 这个题是没办法，从头到尾计算的。只能从尾到头计算。
 * 因为从从头到尾计算的时候，还是没办法选择哪条路径去走，有两个属性都很重要，一个是路径长度，另一个是路径最小值。
 * 两个值，在不同的情况下,是不同情况下的最优解，都会影响后续路径选择。
 */
public class Problem_15_DungeonGame_redo {

	/**
	 * 动态规划，就是分治法，然后将重复计算的结果，给缓存一下，避免重复计算。
	 * @param map
	 * @return
	 */
	public static int minHP1_Fix(int[][]map) {
		/**
		 * -2   -3  20
		 * -1   0   0
		 * -10 -10 -10
		 * 末尾开始的血量是1
		 * dp[x][y] 代表了从末尾走到这一点，需要的最少的血量
		 * 可以往上走，也可以往左走。
		 * dp[x][y] 可以为负数，负数情况下 表示当前还剩下1血（还需要多少血），正数情况下，
		 *
		 * dp[x][y] 表示从这一点，走到了末尾，所需要的最少血量
		 * dp[x+1][y] 就是
		 * 	当map[x+1][y]大于0  Max( dp[x][y]-map[x+1][y] , 1 )
		 * 	当map[x+1][y]小于0  Max( dp[x][y]-map[x+1][y] , 1 )
		 *
		 * dp[x][y+1] 就是
		 * 	当map[x][y+1]大于0  Max( dp[x][y]-map[x][y+1] , 1 )
		 * 	当map[x][y+1]小于0  Max( dp[x][y]-map[x][y+1] , 1 )
		 */
		int[][] dp = new int[map.length][map[0].length];
		dp[map.length-1][map[0].length-1] = map[map.length-1][map[0].length-1]>=0?1:(-map[map.length-1][map[0].length-1]+1);
		for (int x = map.length-2; x >= 0; x--) {
			dp[x][map[0].length-1] = Math.max(dp[x+1][map[0].length-1]-map[x][map[0].length-1], 1);
		}
		for (int y = map[0].length-2; y >=0 ; y--) {
			dp[map.length-1][y] = Math.max(dp[map.length-1][y+1]-map[map.length-1][y] ,1);
		}

		for (int x = map.length-2; x >=0; x--) {
			for (int y = map[0].length-2; y >=0 ; y--) {
				dp[x][y] = Math.max(Math.min(dp[x+1][y],dp[x][y+1]) - map[x][y], 1);
			}
		}

		return dp[0][0];
	}

	/**
	 * dfs的方式，走到这一点，然后走到最终结果 所需要的
	 * @param startR
	 * @param startC
	 * @param map
	 * @return
	 */
//	public static int dfs(int startR, int startC, int[][] map,int hp,int minhp) {
//		if (startR > map.length - 1 || startC > map[0].length - 1) {
//			return Integer.MIN_VALUE;
//		}
//
//		if (startR == map.length - 1 && startC == map[0].length - 1) {
//
//
//		} else if (startR == map.length - 1) {
//
//
//		} else if (startC == map[0].length - 1) {
//
//		} else {
//
//
//		}
//
//
//	}


	/**
	 * @param map
	 * @return
	 */
	public static int minHP1(int[][] map) {
		/**
		 * 到达这一点需要的最少血量,取负数
		 * dp[x][y] = map[x][y] + dp[x-1][y]
		 * dp[x][y] = map[x][y] + dp[x][y-1]
		 *
		 * minhp[x][y] = min(dp[x][y], max(maxhp[x-1][y], maxhp[x][y-1])  );
		 *
		 * 选一个最小的minhp，这里dp的路径和minhp的路径肯定是一样的。所以这里先求minhp，再记录dp血量
		 */
		//minhp 这里表示走到这个位置的时候，（最好情况下）最低的hp
		int[][]minhp = new int[map.length][map[0].length];
		//dp 表示走到这个位置，还有多少血量。记录下。
		int[][] dp = new int[map.length][map[0].length];
		dp[0][0] = map[0][0];
		minhp[0][0] = map[0][0];
		for (int x = 1; x < map.length; x++) {
			dp[x][0] = dp[x - 1][0] + map[x][0];
			minhp[x][0] = Math.min(minhp[x - 1][0], dp[x][0]);
		}
		for (int y = 1; y < map[0].length; y++) {
			dp[0][y] = dp[0][y - 1] + map[0][y];
			minhp[0][y] = Math.min(minhp[0][y - 1], dp[0][y]);
		}

		for (int x = 1; x < map.length; x++) {
			for (int y = 1; y < map[0].length; y++) {
				int r1 = Math.min(minhp[x-1][y], dp[x-1][y] + map[x][y]);
				int r2 = Math.min(minhp[x][y-1], dp[x][y-1] + map[x][y]);
				if (r1>r2){
					minhp[x][y] = r1;
					dp[x][y] = dp[x-1][y] + map[x][y];
				}else if(r1<r2){
					minhp[x][y] = r2;
					dp[x][y] = dp[x][y-1] + map[x][y];
				}else {
					//谁血多，跟谁走，而不是随机走
					if (dp[x - 1][y] > dp[x][y - 1]) {
						minhp[x][y] = r1;
						dp[x][y] = dp[x - 1][y] + map[x][y];
					} else {
						minhp[x][y] = r2;
						dp[x][y] = dp[x][y - 1] + map[x][y];
					}
				}
			}
		}
//		printTrace(dp,minhp,map);
		return minhp[map.length-1][map[0].length-1]<0?(-minhp[map.length-1][map[0].length-1]+1):1;
	}

//	public static void printTrace(int[][] dp, int[][] mindp, int[][]map) {
//		int hp = mindp[map.length-1][map[0].length-1]<0?(-mindp[map.length-1][map[0].length-1]+1):1;
//		int startRow = 0;
//		int startCol = 0;
//		hp = hp + map[startCol][startRow];
//		System.out.println("走到" + startRow + "," + startCol + "血量" + (hp));
//		while (startRow < map.length-1 || startCol < map[0].length-1) {
//			if (startRow == map.length-1) {
//				startCol++;
//			} else if (startCol == map[0].length-1) {
//				startRow++;
//			} else {
//				if (mindp[startRow+1][startCol] == mindp[startRow][startCol]
//						|| mindp[startRow+1][startCol] == dp[startRow][startCol] + map[startRow+1][startCol]) {
//					startRow++;
//				} else {
//					startCol++;
//				}
//			}
//			hp = hp + map[startCol][startRow];
//			System.out.println("走到" + startRow + "," + startCol + "血量" + hp);
//		}
//	}


	public static int minHP1_compress(int[][] map) {
		//minhp 这里表示走到这个位置的时候，（最好情况下）最低的hp
		int[] minhpX0 = new int[map.length];
		//dp 表示走到这个位置，还有多少血量。记录下。
		int[] dpX0 = new int[map.length];
		dpX0[0] = map[0][0];
		minhpX0[0] = map[0][0];
		for (int x = 1; x < map.length; x++) {
			dpX0[x] = dpX0[x - 1] + map[x][0];
			minhpX0[x] = Math.min(minhpX0[x - 1], dpX0[x]);
		}

		/**
		 * 某一行的血量
		 */
		int[]dpCurrent = new int[map[0].length];
		int[]dpLast = new int[map[0].length];//上一行的血量
		/**
		 * 最好情况下最低的hp
		 */
		int[]minhpCurrent =  new int[map[0].length];
		int[]minhpLast =  new int[map[0].length];//上一行的最低hp记录。

		dpLast[0] = map[0][0];
		minhpLast[0] = map[0][0];
		for (int y = 1; y < map[0].length; y++) {
			dpLast[y] = dpLast[y - 1] + map[0][y];
			minhpLast[y] = Math.min(minhpLast[y - 1], dpLast[y]);
		}

		for (int x = 1; x < map.length; x++) {
			for (int y = 1; y < map[0].length; y++) {
				int r1 = Math.min(minhpLast[y], dpLast[y] + map[x][y]);
				int r2 = Math.min(y - 1 == 0 ? minhpX0[x] : minhpCurrent[y - 1], (y - 1 == 0 ? dpX0[x] : dpCurrent[y - 1]) + map[x][y]);
				if (r1 > r2) {
					minhpCurrent[y] = r1;
					dpCurrent[y] = dpLast[y] + map[x][y];
				} else if (r2 > r1) {
					minhpCurrent[y] = r2;
					dpCurrent[y] = dpCurrent[y - 1] + map[x][y];
				} else {
					if (dpLast[y] > (y - 1 == 0 ? dpX0[x] : dpCurrent[y - 1])) {
						minhpCurrent[y] = r1;
						dpCurrent[y] = dpLast[y] + map[x][y];
					} else {
						minhpCurrent[y] = r2;
						dpCurrent[y] = dpCurrent[y - 1] + map[x][y];
					}
				}
			}
			int[]temp = dpLast;
			dpLast = dpCurrent;
			dpCurrent = temp;

			temp = minhpLast;
			minhpLast = minhpCurrent;
			minhpCurrent = temp;
		}
		return minhpLast[map[0].length-1]<0?(-minhpLast[map[0].length-1]+1):1;
	}


	public static void main(String[] args) {
//		int[][] map = { { -2, -3, 3 }, { -5, -10, 1 }, { 10, 30, -5 }, };
//		System.out.println(minHP1_Fix(map));
//		TimeUtils.start();
//		System.out.println(minHP1(map));
//		TimeUtils.stop();
//		System.out.println(minHP1_compress(map));
//		TimeUtils.stop();
//		System.out.println(Problem_15_DungeonGame.minHP2(map));
//		TimeUtils.stop();

		int[][] map = {
				{2,-9,3,-9,3,-5,-4,-1,-9,-10},
				{-6,-4,-1,-1,-4,3,2,-2,0,-4},
				{0,-1,-9,-1,-10,-1,-10,-1,-3,-6},
				{-5,-3,-9,-5,0,3,-2,-6,-5,-9},
				{3,-7,-1,-5,-9,-7,-8,3,-3,-7},
				{-10,3,-8,-8,1,2,3,1,2,-4},
				{-9,-7,-4,2,3,-4,3,3,-7,0},
				{-9,-6,-7,0,-2,-7,-9,-1,-8,-8},
				{0,2,1,-9,-10,-8,0,2,3,3},
				{-4,-8,-1,-10,-4,-6,-8,-10,-5,-2},
				{3,-6,-3,0,-3,-5,-3,-1,-7,-10},
				{-1,-1,-2,-8,-8,-5,2,-8,-2,-10},
				{1,-2,0,-5,-1,-8,-9,-1,-4,-8},
				{1,-3,-7,-4,1,-5,-6,2,-7,3},
				{-6,2,-5,-4,-5,-5,-5,3,3,-2},
				{-1,-5,-2,-2,-8,-6,-9,1,1,3},
				{2,-2,-4,-5,-9,-2,-10,-3,3,-10},
				{1,-10,-1,-9,-5,-2,0,0,-10,-1},
				{1,-2,-7,0,3,-4,-4,-7,-10,-1},
				{-1,-4,-10,-6,3,-3,-6,2,-4,-1}
		};
		map = InputUtils.generateMatrix(200, 1000, -10, 3);
//		InputUtils.printMatrix(map);
		TimeUtils.start();
		System.out.println(minHP1(map));
		TimeUtils.stop();
		System.out.println(minHP1_compress(map));
		TimeUtils.stop();
		System.out.println(Problem_15_DungeonGame.minHP1(map));
		TimeUtils.stop();
		System.out.println(minHP1_Fix(map));
		TimeUtils.stop();
	}

}
