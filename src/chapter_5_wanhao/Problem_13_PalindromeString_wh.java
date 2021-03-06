package chapter_5_wanhao;

import chapter_5_stringproblem.Problem_13_PalindromeString;
import utils.InputUtils;
import utils.TimeUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Problem_13_PalindromeString_wh {

	public static class Action{
		public char c;
		public int pos;
		public Action(char c, int pos) {
			this.c = c;
			this.pos = pos;
		}
	}
	/**
	 * 18:12 动态规划，前1h
	 * 20:00 开始，后
	 * @param str
	 * @return
	 */
	public static String getPalindrome1(String str) {
		if (str == null || str.length() < 2) {
			return str;
		}
		/**
		 * dp[i][j] 表示str从i到j 组成回文串的最小代价
		 */
		int[][] dp = new int[str.length()][str.length()];
		for (int len = 2; len <= str.length(); len++) {
			for (int start = 0; start + len - 1  < str.length() ; start++) {
				int end = start + len - 1; // [start,end]
				if (str.charAt(start) == str.charAt(end) ){
					dp[start][end] = dp[start+1][end-1];
				}else{
					dp[start][end] = Math.min(dp[start+1][end], dp[start][end-1]) + 1 ;
				}
			}
		}

		/**
		 * 回溯下，拿到哪个位置插入哪个结点后，将其插入到一个列表中，然后每次插入，都是从后往前插入即可。
		 */
		StringBuffer sb = new StringBuffer(str);
		List<Action> actions = new ArrayList<>();

		int start = 0;
		int end = str.length() - 1;
		while (start < end) {
			if (str.charAt(start) == str.charAt(end)) {
				start++;
				end--;
			} else if (dp[start][end] == dp[start + 1][end] + 1) {
//				actions.add(new Action(str.charAt(start), end+1));
				sb.insert(end+1, str.charAt(start));
				start++;
			} else {
				// dp[start][end-1] + 1
				actions.add(new Action(str.charAt(end), start));
//				sb.insert(start, str.charAt(end));
				end--;
			}
		}
		for (int i = actions.size() - 1; i >= 0; i--) {
			sb.insert(actions.get(i).pos, actions.get(i).c);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String str = "AB1CD2EFG3H43IJK2L1MN";
		str = InputUtils.generateString(1000);
		System.out.println(str);
		TimeUtils.start();
		String mr = getPalindrome1(str);
		System.out.println(mr);
		TimeUtils.stop();

		String m2r = Problem_13_PalindromeString.getPalindrome1(str);
		System.out.println(m2r);
		TimeUtils.stop();
		System.out.println(mr.equals(m2r));

//		System.out.println("ABNM1CDL2EFGKJI3H4H3IJKGFE2LDC1MNBA".equals("ABMN1CDL2EFGIJK3H4H3IJKGFE2LDC1MNBA"));

//		String strlps = "1234321";
//		System.out.println(getPalindrome2(str, strlps));
	}

}
