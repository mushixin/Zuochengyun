package chapter_4_wanhao;

public class Problem_14_StringCross_wh {

	/**
	 *
	 * @param str1
	 * @param str2
	 * @param aim
	 * @return
	 */
	public static boolean isCross1(String str1, String str2, String aim) {
		/**
		 * dp[x][y] 表示str1前x个字符 和 str2的前y个字符 是否组成了aim的前x+y字符
		 * dp[x-1][y] 如果aim第x+y个字符，等于str1的第x个字符
		 * dp[x][y-1] 如果aim第x+y个字符，等于str2的第y个字符
		 * 两者 或 一下即可。
		 */
		boolean[][] dp = new boolean[str1.length() + 1][str2.length() + 1];
		dp[0][0] = true;

		for (int x = 1; x <= str1.length(); x++) {
			dp[x][0] = dp[x - 1][0] && (str1.charAt(x - 1) == aim.charAt(x - 1));
		}
		for (int y = 1; y <= str2.length(); y++) {
			dp[0][y] = dp[0][y - 1] && (str2.charAt(y - 1) == aim.charAt(y - 1));
		}

		for (int x = 1; x <= str1.length(); x++) {
			for (int y = 1; y <= str2.length(); y++) {
				boolean result = false;
				if (str1.charAt(x-1)==aim.charAt(x+y-1)){
					result = result || (dp[x-1][y]);
				}
				if(str2.charAt(y-1) == aim.charAt(x+y-1)){
					result = result || (dp[x][y-1]);
				}
				dp[x][y] = result;
			}
		}
		return dp[str1.length()][str2.length()];
	}

	public static void main(String[] args) {
		String str1 = "1234";
		String str2 = "abcd";
		String aim = "1a23bcd4";
		System.out.println(isCross1(str1, str2, aim));

	}

}
