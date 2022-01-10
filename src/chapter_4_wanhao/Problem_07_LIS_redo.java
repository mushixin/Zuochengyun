package chapter_4_wanhao;

import utils.InputUtils;

import java.util.ArrayList;
import java.util.List;

public class Problem_07_LIS_redo {
	/**
	 * 最长增长子序列 10：20完成
	 * @param arr
	 * @return
	 */
	public static int[] getLongIncreaseSequenceRedo(int[] arr) {
		/**
		 * 以当前元素结尾的最长增长子序列
		 */
		int[]dp = new int[arr.length];


		return dp;
	}


	public static int[] getLongIncreaseSequence(int[] arr) {
		/**
		 * dp[i] 表示以i元素结尾的最大增长长度
		 */
		int[]dp = new int[arr.length];
		/**
		 * 表示自增序列的结果。
		 * 从前往后遍历，然后 ends[i] 表示长度为i的增长序列中，末尾最小的元素
		 */
		List<Integer> ends = new ArrayList<>();
		ends.add(arr[0]);
		dp[0] = 1;

		for (int i = 1; i < arr.length; i++) {
			int index = findIndex(ends, arr[i]);
			if (index >= ends.size()) {
				ends.add(arr[i]);
				dp[i] = ends.size();
			} else if (index < ends.size()) {
				if (ends.get(index)>arr[i]){
					ends.set(index , arr[i]);
					dp[i] = index + 1;
				}else{
					System.out.println("error");
				}
			} else {
				System.out.println("error");
			}

		}


		int[] res = new int[ends.size()];
		int record = res.length-1;
		for (int i = dp.length-1; i >= 0 && record>=0; i--) {
			if (dp[i]==record+1 && (record+1>=res.length || arr[i]<=res[record+1])){
				res[record] = arr[i];
				record--;
			}
		}
		return res;
	}

	// 二分查找，找到一个最大的小于等于当前元素的结点。（但这里不会相等的，因为会确保不会有相同长宽的信封）
	// 如果下一个结点大于你的结点，那就用当前更小的结点来替换，若是下个结点小于你的结点，则不变。
	public static int findIndex(List<Integer> ends, int value){
		int left = 0;
		int right = ends.size()-1;
		while (left <= right) {
			int mid = (left + right) / 2  ;
			if (ends.get(mid) > value) {
				right = mid - 1;
			} else if (ends.get(mid) == value) {
				return mid;
			} else {
				left = mid + 1 ;
			}
		}
		return left;
	}


	public static void main(String[] args) {
		int[] arr = { 2, 1, 5, 3, 6, 4, 8, 9, 7 };
		InputUtils.printArr(getLongIncreaseSequence(arr));

	}
}