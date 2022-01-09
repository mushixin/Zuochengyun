package chapter_9_wanhao;


public class Problem_32_TopKTimes1_aban {

	/**
	 * 打印出现次数topk的字符串。
	 * @param arr
	 * @param topk
	 */
	public static void printTopKAndRank(String[] arr, int topk){

	}

	public static String[] generateRandomArray(int len, int max) {
		String[] res = new String[len];
		for (int i = 0; i != len; i++) {
			res[i] = String.valueOf((int) (Math.random() * (max + 1)));
		}
		return res;
	}

	public static void printArray(String[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		String[] arr = generateRandomArray(50, 10);
		int topK = 3;
		printArray(arr);
		printTopKAndRank(arr, topK);

	}
}