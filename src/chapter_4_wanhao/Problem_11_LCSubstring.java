package chapter_4_wanhao;

import utils.InputUtils;
import utils.TimeUtils;

/**
 * 用dp的方法去解，然后做db的空间压缩。
 */
public class Problem_11_LCSubstring {

	/**
	 * 没空间压缩。10：55
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String lcstDP_Redo(String str1, String str2) {
		/**
		 * dp[x][y] 为str1[x] str2[y] 结尾的 为公共子串。
		 */
		int[][]dp = new int[str1.length()][str2.length()];
		String maxRes = "";
		for (int x = 0; x < str1.length(); x++) {
			for (int y = 0; y < str2.length(); y++) {
				if (x==0||y==0){
					dp[x][y] = (str1.charAt(x) == str2.charAt(y)?1:0);
				} else if (str1.charAt(x) == str2.charAt(y)){
					dp[x][y] = dp[x-1][y-1]+1;
					if (dp[x][y]>maxRes.length()){
						maxRes = str1.substring(x-dp[x][y]+1,x+1);
					}
				}else{
					dp[x][y] = 0;
				}
			}
		}

		return maxRes;
	}

	/**
	 * 空间压缩。15min，11:00
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String lcstDPCompress_Redo(String str1, String str2) {
		String maxRes = "";
//		int startRow =


		return "";
	}

	/**
	 *
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String lcstDP(String str1, String str2) {
		/**
		 * dp[x][y] 第1个字符串前x个，和第2个字符串前y个的最大公共子串
		 */
		String[][] dp = new String[str1.length()][str2.length()];

		for (int i = 0; i < str2.length(); i++) {
			if (str1.charAt(0) == str2.charAt(i)) {
				dp[0][i] = "" + str1.charAt(0);
			} else if (i > 0 && dp[0][i - 1] != null) {
				dp[0][i] = dp[0][i - 1];
			} else {
				dp[0][i] = "";
			}
		}

		for (int i = 0; i < str1.length(); i++) {
			if (str1.charAt(i) == str2.charAt(0)) {
				dp[i][0] = "" + str2.charAt(0);
			} else if (i > 0 && dp[i - 1][0] != null) {
				dp[i][0] = dp[i - 1][0];
			} else {
				dp[i][0] = "";
			}
		}

		for (int x = 1; x < str1.length(); x++) {
			for (int y = 1; y < str2.length(); y++) {
				if (str1.charAt(x) == str2.charAt(y)) {
					dp[x][y] = dp[x - 1][y - 1] + str1.charAt(x);
				} else {
					String maxStr = "";
					for (int i = 1; i <= y; i++) {
						if (str1.charAt(x) == str2.charAt(i) && dp[x - 1][i - 1].length() >= maxStr.length()) {
							maxStr = dp[x - 1][i - 1] + str1.charAt(x);
						} else if (dp[x - 1][i - 1].length() > maxStr.length()) {
							maxStr = dp[x - 1][i - 1];
						}
					}
					dp[x][y] = maxStr;
				}
			}

		}
		return dp[str1.length() - 1][str2.length() - 1];
	}


	/**
	 * 最长公共子串
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String getLongestCommonStrDP(String str1, String str2) {
		//dp[x][y] 表示以str[x]结尾 和 str2[y] 结尾的公共子串的最大长度
		String[][] dp = new String[str1.length()][str2.length()];
		for (int i = 0; i < str1.length(); i++) {
			if (str1.charAt(i) == str2.charAt(0)) {
				dp[i][0] = "" + str2.charAt(0);
			} else {
				dp[i][0] = "";
			}
		}
		for (int i = 0; i < str2.length(); i++) {
			if (str1.charAt(0) == str2.charAt(i)) {
				dp[0][i] = "" + str1.charAt(0);
			} else {
				dp[0][i] = "";
			}
		}
		String res = "";
		for (int x = 1; x < str1.length(); x++) {
			for (int y = 1; y < str2.length(); y++) {
				if (str1.charAt(x) == str2.charAt(y)) {
					dp[x][y] = dp[x-1][y-1] + str1.charAt(x);
					res = (res.length()> dp[x][y].length()?res: dp[x][y]);
				} else {
					dp[x][y] = "";
				}
			}
		}

		return res;
	}

	/**
	 * 空间压缩
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String getLongestCommonStrDPCompress(String str1, String str2) {
		String res = "";
		int startRow=0;
		int startColumn=str1.length()-1;
		while (startRow < str2.length()) {
			int tempLen = 0;
			int str1X = startColumn;
			int str2Y = startRow;
			while (str1X < str1.length() && str2Y < str2.length()) {
				if (str1.charAt(str1X) == str2.charAt(str2Y)) {
					str1X++;
					str2Y++;
					tempLen++;
					if (tempLen > res.length()) {
						res = str1.substring(str1X - tempLen, str1X);
					}
				} else {
					tempLen = 0;
					str1X++;
					str2Y++;
				}
			}
			if (startColumn>0){
				startColumn--;
			}else{
				startRow++;
			}
		}

		return res;
	}


	/**
	 * 动态规划的方式去解，
	 * dp[x][y] 第1个字符串前x个，和第2个字符串前y个的最大公共子串
	 * 若str1[x] == str2[y] ,则dp[x][y]=dp[x-1][y-1]+str1[x]
	 * 若str1[x] 等于str2[0 ~ y-1] 某个元素时，值加1，或者不等的时候，就是原来的值，找一个最长的，设置进来。
	 * 最后返回 dp[str1.length-1][str2.length-1]
	 *
	 * 能否去优化下？
	 * dp[x] 第1个字符串前x个（可以不是第dp[x]结尾），和第2个字符串的最大公共字串
	 * 这里的时间复杂度为m*n*n
	 *
	 * 我在想，全暴力可以嘛？
	 * 直接用str[1]和第二个字符串匹配，匹配到了后长度加1，再去匹配，直到无法匹配。记录最长长度。
	 * str[2] 跨越最长长度+1，再去匹配，
	 * @param str1
	 * @param str2
	 * @return
	 */
	/**
	 * 这里的dp分割点就有问题，时间复杂度更低的方式是，dp【x】【y】 是以str1【x】 str【y】 结尾的最大字符串，
	 * 这里的时间复杂度为m*n
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String lcst1(String str1, String str2) {
		if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
			return "";
		}
		String result = "";
		int longest = 0;
		for (int startIndex = 0; startIndex + longest + 1 <= str1.length(); startIndex++) {
			int index2 = str2.indexOf(str1.substring(startIndex, startIndex + longest + 1));
			if (index2 == -1) {
				continue;
			}else {
				result = str1.substring(startIndex, startIndex + longest + 1);
				longest++;
				startIndex--;//继续在这个位置
			}
		}

		return result;
	}

	public static void main(String[] args) {
		String str1 = "ABC1234567DEFG";
		String str2 = "HIJKL1234567MNOP";
		System.out.println(lcst1(str1, str2));
//		System.out.println(lcstDP(str1,str2));
		System.out.println(getLongestCommonStrDP(str1,str2));

		System.out.println(getLongestCommonStrDPCompress(str1,str2));

		System.out.println(lcstDP_Redo(str1,str2));

//		System.out.println(lcst2(str1, str2));
		System.out.println("-----------------------------------------------------");

		str1 = InputUtils.generateString(10000);
		str2 = InputUtils.generateString(10000);
		TimeUtils.start();
		System.out.println(lcst1(str1, str2));
		TimeUtils.stop();
		System.out.println(chapter_4_recursionanddp.Problem_11_LCSubstring.lcst2(str1, str2));
		TimeUtils.stop();
//		System.out.println(lcstDP(str1,str2));
		System.out.println(chapter_4_recursionanddp.Problem_11_LCSubstring.lcst1(str1,str2));
		TimeUtils.stop();

		System.out.println(getLongestCommonStrDP(str1,str2));
		TimeUtils.stop();
		System.out.println(getLongestCommonStrDPCompress(str1,str2));
		TimeUtils.stop();
		System.out.println(lcstDP_Redo(str1,str2));
		TimeUtils.stop();

	}

}