package chapter_4_wanhao;

import utils.InputUtils;

import java.util.HashMap;
import java.util.Map;

public class Problem_16_NumberToLetter {
	public static Map<Integer,Character> map = new HashMap<>();

	public static int num1(String str) {
		if (str == null || str.equals("")) {
			return 0;
		}
		/**
		 * dp[x] 代表str[x] 到str[y]范围内可以组成的组合数量 []
		 * dp[x+1]  当str[x]是一个字母的时候
		 * dp[x+2]  当str[x,x+1] 两个是一个字母的时候
		 *
		 */
		int[]dp = new int[str.length()];
		dp[dp.length-1] = 1;
		dp[dp.length-2] = getChar(str.substring(str.length()-2)).length()>0?2:0;
		for (int i = dp.length-3; i >=0 ; i--) {
			int res = 0 ;
			if (getChar(str.charAt(i)).length()>0) {
				res = res + dp[i+1];
			}
			if(getChar(str.substring(i,i+2)).length()>0){
				res = res + dp[i+2];
			}
			dp[i] = res ;
		}

		return dp[0];
	}

	public static String getChar(char c){
		return getChar(c+"");
	}
	public static String getChar(String str){
		return getChar(Integer.parseInt(str));
	}
	public static String getChar(int num){
		if(num>=1 && num<=26){
			return (char)('A'+(num-1))+"";
		}
		return "";
	}



	public static void main(String[] args) {
		for (int i = 1; i <=26 ; i++) {
			map.put(i,(char)('A'+i-1));
		}

		System.out.println(num1("1111"));
		System.out.println(Problem_16_NumberToLetter.num1("1111"));

		System.out.println(num1("781231783161018231"));
		System.out.println(Problem_16_NumberToLetter.num1("781231783161018231"));

		String str= InputUtils.generateString(100,'0','3');
		str= str.replaceAll("00","");
		System.out.println(str);
		System.out.println(num1(str));
		System.out.println(Problem_16_NumberToLetter.num1(str));

	}
}
