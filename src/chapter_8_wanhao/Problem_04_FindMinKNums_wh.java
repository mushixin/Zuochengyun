package chapter_8_wanhao;

import utils.InputUtils;

import java.util.PriorityQueue;

public class Problem_04_FindMinKNums_wh {

	// O(N*logK)  18:50
	public static int[] getMinKNumsByHeap(int[] arr, int k) {
		if (k < 1 || k > arr.length) {
			return arr;
		}
		/**
		 * 大顶堆，然后逐一将后续的元素，和大顶堆最大的元素对比，比最大的小，则替换最大的数即可。
		 */
		PriorityQueue<Integer> queue = new PriorityQueue((Object int1, Object int2) -> {
			return (Integer) int2 - (Integer) int1;
		});
		for (int i = 0; i < arr.length; i++) {
			if (i<k){
				queue.add(arr[i]);
			}else{
				int queMax = queue.peek();
				if (queMax > arr[i]) {
					queue.poll();
					queue.add(arr[i]);
				}
			}
		}
		int[]result = new int[k];
		for (int i = 0; i < k; i++) {
			result[i] = queue.poll();
		}
		return result;
	}




	public static void main(String[] args) {
		int[] arr = { 6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9 };
		// sorted : { 1, 1, 1, 1, 2, 2, 2, 3, 3, 5, 5, 5, 6, 6, 6, 7, 9, 9, 9 }
		InputUtils.printArr(getMinKNumsByHeap(arr, 9));
	}

}
