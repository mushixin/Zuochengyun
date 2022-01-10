package chapter_4_wanhao;

/**
 * 空间不压缩30min
 * 空间压缩45min,
 * 空间压缩重新实现下。
 */
public class Problem_13_EditCost_redo {

	/**
	 * 11:55
	 * @param str1
	 * @param str2
	 * @param ic
	 * @param dc
	 * @param rc
	 * @return
	 */
	public static int minCost1_redo(String str1, String str2, int ic, int dc, int rc) {
		/**
		 * dp[x][y]
		 * 将str1[x] 前x 编辑为 str2[y] 的最小代价。
		 * 这里需要分为几种情况
		 * 相同的情况，dp[x-1][y-1]
		 * str1末尾增加一个字符，ic+dp[x][y-1]
		 * str1末尾删除一个字符，dc+dp[x-1][y]
		 * str1修改一个字符，rc+dp[x-1][y-1]
		 */
		int[][]dp = new int[str1.length()][str2.length()];



		return 0;
	}

	/**
	 * 看清楚题，这里只能由str1，转换为str2，不能逆转换。
	 * @param str1
	 * @param str2
	 * @param ic
	 * @param dc
	 * @param rc
	 * @return
	 */
	public static int minCost1(String str1, String str2, int ic, int dc, int rc) {
		if (str1 == null || str2 == null) {
			return 0;
		}
		// str1.length() == 0 || str2.length() == 0
		if (str1.length() == 0) {
			return ic * str2.length();
		}
		if (str2.length() == 0) {
			return dc * str1.length();
		}

		/**
		 * dp[x][y] 代表 str1 前x个字符串， str2前y个字符串 匹配的最小代价
		 int r0 =  dp[x-1][y-1]; //修改的代价
		 int r1 =  dp[x-1][y-1] + rc; //修改的代价
		 int r2 =  dp[x-1][y] + dc; // 删除str1[x]字符
		 int r3 =  dp[x][y-1] + ic; // 向str1添加str2[y]字符
		 */
		rc = Math.min(rc, ic+dc);
		int[][]dp = new int[str1.length()][str2.length()];

		int index1 = str2.indexOf(str1.charAt(0) + "");
		for (int i = 0; i < str2.length(); i++) {
			if (index1 == -1 || i < index1) {
				dp[0][i] = ic * (i) + rc;
			} else {
				dp[0][i] = ic * (i);
			}
		}
		int index2 = str1.indexOf(str2.charAt(0) + "");
		for (int i = 0; i < str1.length(); i++) {
			if (index2 == -1 || i < index2) {
				dp[i][0] = ic * (i) + rc;
			} else {
				dp[i][0] = ic * (i);
			}
		}

		for (int x = 1; x < str1.length(); x++) {
			for (int y = 1; y < str2.length(); y++) {
				if (str1.charAt(x) == str2.charAt(y)) {
					dp[x][y] = dp[x - 1][y - 1];
				} else {
					int r1 = dp[x - 1][y - 1] + rc; //修改的代价
					int r2 = dp[x - 1][y] + dc; // 删除str1[x]字符
					int r3 = dp[x][y - 1] + ic; // 添加str2[y]字符
					dp[x][y] = Math.min(Math.min(r1, r2), r3);
				}
			}
		}

		return dp[str1.length()-1][str2.length()-1];
	}

	public static int minCost2(String str1, String str2, int ic, int dc, int rc) {
		if (str1 == null || str2 == null) {
			return 0;
		}
		// str1.length() == 0 || str2.length() == 0
		if (str1.length() == 0) {
			return ic * str2.length();
		}
		if (str2.length() == 0) {
			return dc * str1.length();
		}

		rc = Math.min(rc, ic+dc);
		//dp[i] 表示某个str1的子串到  str2[i]的最小改动代价。
		int[]dp = new int[str2.length()];
		//上一行的计算记录
		int[]dpLast = new int[str2.length()];

		int index1 = str2.indexOf(str1.charAt(0) + "");
		for (int i = 0; i < str2.length(); i++) {
			if (index1 == -1 || i < index1) {
				dpLast[i] = ic * (i) + rc;
				dp[i]  = ic * (i) + rc;
			} else {
				dpLast[i] = ic * (i);
				dp[i]  =  ic * (i);
			}
		}
		/**
		 * dpStri0[i] 表示str[0] 到str2[i]的最小改动代价
		 */
		int []dpStri0 = new int[str1.length()];
		int index2 = str1.indexOf(str2.charAt(0) + "");
		for (int i = 0; i < str1.length(); i++) {
			if (index2 == -1 || i < index2) {
				dpStri0[i] = ic * (i) + rc;
			} else {
				dpStri0[i] = ic * (i);
			}
		}

		for (int x = 1; x < str1.length(); x++) {
			dp[0] =  dpStri0[x];
			for (int y = 1; y < str2.length(); y++) {
				if (str1.charAt(x) == str2.charAt(y)) {
					dp[y] = dpLast[y - 1];
				} else {
					int r1 = dpLast[y - 1] + rc; //修改的代价
					int r2 = dpLast[y] + dc; // 删除str1[x]字符
					int r3 =  dp[y - 1] + ic; // 添加str2[y]字符
					dp[y] = Math.min(Math.min(r1, r2), r3);
				}
			}
			int[]temp = dpLast;
			dpLast = dp;
			dp = temp;
		}

		return dpLast[str2.length()-1];
	}

	public static void main(String[] args) {
		String str1 = "ab12cd3";
		String str2 = "abcdf";
		System.out.println(minCost1(str1, str2, 5, 3, 2));//8
		System.out.println(minCost2(str1, str2, 5, 3, 2));

		str1 = "abcdf";
		str2 = "ab12cd3";
		System.out.println(minCost1(str1, str2, 3, 2, 4));//10  12 6 8 10 10 8 10
		System.out.println(minCost2(str1, str2, 3, 2, 4));//15

		str1 = "";
		str2 = "ab12cd3";
		System.out.println(minCost1(str1, str2, 1, 7, 5));//7
		System.out.println(minCost2(str1, str2, 1, 7, 5));

		str1 = "abcdf";
		str2 = "";
		System.out.println(minCost1(str1, str2, 2, 9, 8));//45
		System.out.println(minCost2(str1, str2, 2, 9, 8));

	}

}
