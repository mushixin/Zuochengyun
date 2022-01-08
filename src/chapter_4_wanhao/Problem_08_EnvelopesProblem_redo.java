package chapter_4_wanhao;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Problem_08_EnvelopesProblem_redo {

	public static class Envelope {
		public int len;
		public int wid;

		public Envelope(int weight, int hight) {
			len = weight;
			wid = hight;
		}
	}

	public static class EnvelopeComparator implements Comparator<Envelope> {
		@Override
		public int compare(Envelope o1, Envelope o2) {
			return o1.len != o2.len ? o1.len - o2.len : o2.wid - o1.wid;
		}
	}

	public static int maxEnvelopes(int[][] matrix) {
		Envelope[] envs = new Envelope[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			envs[i] = new Envelope(matrix[i][0], matrix[i][1]);
		}
		List<Envelope> envList = Arrays.stream(envs).sorted(new EnvelopeComparator()).collect(Collectors.toList());
		/**
		 * 宽度的最大自增序列
		 */
		int[]array  = new int[envs.length];
		for (int i = 0; i < envList.size(); i++) {
			array[i] = envList.get(i).wid;
		}
		/**
		 * dp[i] 以第 i 个数结尾的最长序列长度
		 * dp[i-1]+1,  第i个数大于第i-1个数
		 * dp[k] +1, 向前找到第一个小于i的数，第k个，然后加1
		 */
		int[]dp = new int[array.length];
		dp[0]=1;


		return 0;
	}

	public static void main(String[] args) {
		int[][] test = { { 3, 4 }, { 2, 3 }, { 4, 5 }, { 1, 3 }, { 2, 2 },
				{ 3, 6 }, { 1, 2 }, { 3, 2 }, { 2, 4 } };
		System.out.println(maxEnvelopes(test));
	}
}
