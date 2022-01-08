package chapter_8_wanhao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Problem_06_FindKMajority_wh {

	/**
	 * 23:23开始的
	 * @param arr
	 */
	public static void printHalfMajor(int[] arr) {
		int result = arr[0];
		int count = 1;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == result) {
				count++;
			} else {
				count--;
			}
			if (count == 0) {
				count = 1;
				result = arr[i];
			}
		}
		/**
		 * 重新check下结果
		 */
		count=0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == result) {
				count++;
			} else {
				count--;
			}
		}
		if (count > 0) {
			System.out.println(result);
		} else {
			System.out.println("no result");
		}
	}

	/**
	 * 23：35
	 * @param arr
	 * @param k
	 */
	public static void printKMajor(int[] arr, int k) {
		int currentTimes = arr.length / k+1;
		int maxNum = arr.length / (currentTimes);
		/**
		 * 记录可能的数字
		 */
		Map<Integer,Integer> arrayRes = new HashMap<>();



	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 1, 1, 2, 1 };
		printHalfMajor(arr);
		int K = 4;
		printKMajor(arr, K);
	}

}
