package chapter_1_wanhao;

import utils.InputUtils;
import utils.TimeUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Problem_07_SlidingWindowMaxArray {

	/**
	 * 搞一个队列记录元素的index，
	 * 搞一个队列，最大的元素放在开头，
	 * 若是后续的元素小于当前元素，放到队列末尾，
	 * 若是后续的元素大于等于当前元素，则清空当前队列。
	 * @param arr
	 * @param w
	 * @return
	 */
	public static int[] getMaxWindowRedo(int[] arr, int w) {
		List<Integer>result = new ArrayList<>();
		LinkedList<Integer>maxQueue = new LinkedList<>();
		LinkedList<Integer>index = new LinkedList<>();
		maxQueue.addFirst(arr[0]);
		index.addFirst(0);

		// 窗口[i-w+1,i]
		for (int i = 1; i < arr.length; i++) {
			if (i < w-1) {
				if (arr[i] >= maxQueue.getFirst()) {
					maxQueue = new LinkedList<>();
					index = new LinkedList<>();
					maxQueue.addFirst(arr[i]);
					index.addFirst(i);
				} else {
					while (maxQueue.getLast() <= arr[i]) {
						maxQueue.removeLast();
						index.removeLast();
					}
					maxQueue.addLast(arr[i]);
					index.addLast(i);
				}
			} else {

				if (arr[i] >= maxQueue.getFirst()) {
					maxQueue = new LinkedList<>();
					index = new LinkedList<>();
					maxQueue.addFirst(arr[i]);
					index.addFirst(i);
				} else {
					while (maxQueue.getLast() <= arr[i]) {
						maxQueue.removeLast();
						index.removeLast();
					}
					maxQueue.addLast(arr[i]);
					index.addLast(i);
				}
				while (index.getFirst() < i - w +1) {
					index.removeFirst();
					maxQueue.removeFirst();
				}
				result.add(maxQueue.getFirst());
			}

		}

		int[]arrayRes = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			arrayRes[i]= result.get(i);
		}
		return arrayRes;
	}

	/**
	 * 30 min
	 * @param arr
	 * @param w
	 * @return
	 */
	public static int[] getMaxWindow(int[] arr, int w) {
		int[] result = new int[arr.length - w + 1];

		//位置靠后的较小的变量，先到先押入，后续可能会用到
		LinkedList<Integer>  currentMax = new LinkedList<>();

		//init [0,w)放入
		for (int i = 0; i < w; i++) {
			if (currentMax.isEmpty() ) {
				currentMax.addFirst(i);
			} else if (arr[i] >= arr[currentMax.getLast()]) {
				currentMax =  new LinkedList<>();
				currentMax.addFirst(i);
			} else if (arr[i] >= arr[currentMax.getFirst()]) {
				while (!currentMax.isEmpty() && arr[i] >= arr[currentMax.getFirst()]) {
					currentMax.removeFirst();
				}
				currentMax.addFirst(i);
			}else{
				currentMax.addFirst(i);
			}
		}
		result[0] = arr[currentMax.getLast()];
		for (int startIndex = 1; startIndex <= arr.length - w; startIndex++) {
			int endIndex = startIndex + w;// [startIndex,endIndex]
			// 弹出最后一个元素
			if (currentMax.getLast()<startIndex){
				currentMax.removeLast();
			}
			if (currentMax.isEmpty()) {
				currentMax.addFirst(endIndex - 1);
			} else if (arr[endIndex - 1] > arr[currentMax.getLast()]) {
				currentMax = new LinkedList<>();
				currentMax.addFirst(endIndex - 1);
			} else if (!currentMax.isEmpty() && arr[endIndex - 1] >= arr[currentMax.getFirst()]) {
				while (!currentMax.isEmpty() && arr[endIndex - 1] >= arr[currentMax.getFirst()]) {
					currentMax.removeFirst();
				}
				currentMax.addFirst(endIndex - 1);
			} else {
				currentMax.addFirst(endIndex - 1);
			}

			result[startIndex] = arr[currentMax.getLast()];
		}

		return result;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
//		int[] arr = {36743, 5451, 25280, 14244, 21212, 582, 42540, 7534, 32331, 37971, 13055, 18098 };
		int[] arr = InputUtils.generateIntArray(5000);
		int w = 3;
		TimeUtils.start();
		int[]result = getMaxWindow(arr, w);
		printArray(result);
		TimeUtils.stop();
		int[]result2 = chapter_1_stackandqueue.Problem_07_SlidingWindowMaxArray.getMaxWindow(arr, w);
		printArray(result2);
		TimeUtils.stop();
		InputUtils.checkEqual(result,result2);

		int[]result3 = getMaxWindowRedo(arr, w);
		printArray(result3);
		InputUtils.checkEqual(result,result3);
		TimeUtils.stop();


	}

}
