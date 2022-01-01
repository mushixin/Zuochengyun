package chapter_1_wanhao;

import chapter_1_stackandqueue.Problem_07_SlidingWindowMaxArray;
import utils.InputUtils;
import utils.TimeUtils;

import java.util.LinkedList;

public class Problem_07_SlidingWindowMaxArray_needredo {

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
		int[]result2 = Problem_07_SlidingWindowMaxArray.getMaxWindow(arr, w);
		printArray(result2);
		TimeUtils.stop();
		InputUtils.checkEqual(result,result2);
	}

}
