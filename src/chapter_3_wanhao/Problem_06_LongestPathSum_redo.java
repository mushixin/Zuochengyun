package chapter_3_wanhao;

import java.util.HashMap;
import java.util.Map;

public class Problem_06_LongestPathSum_redo {
	/**
	 * key
	 */
	public static Map<Integer,Integer> sumPath = new HashMap<>();

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * 二叉树的前序遍历。
	 * 遍历到根节点后，将数组结果，进行判断，是否满足最大和。
	 * 再次到根节点，重新生成数组（也可以复用之前的数组），进行判断，是否有最大和。
	 *
	 * 可以将元素放到二维数组中，前序遍历，每个遍历结果，都放到数组中，然后逐次去遍历数组是否满足最大累加和即可。
	 *
	 * 边递归边解决。
	 * <Integer,Integer>map 存放一个map，key为当前已经有的所有累加和，及最大长度，值表示length，
	 * 返回的是满足目标的最大长度。从前往后遍历即可。
	 * @param sum
	 * @return
	 */
	public static int getMaxLength(Node head, int sum) {
		Map<Integer,Integer>sumLen = new HashMap<>();
		traceSum(sumLen, head, 1,sum);
		return sumLen.get(sum);
	}

	/**
	 * 返回当前结点累加到目标和的map,以及把当前结点，也放入尝试。
	 * @param sumLen
	 * @param head
	 * @return
	 */
	public static void traceSum(Map<Integer, Integer> sumLen, Node head,int level, int sum) {
		if (head==null){
			return;
		}
		if (sumLen.containsKey(head.value)){
//			sumLen
		}

	}

	public static void main(String[] args) {
		Node head = new Node(-3);
		head.left = new Node(3);
		head.right = new Node(-9);
		head.left.left = new Node(1);
		head.left.right = new Node(0);
		head.left.right.left = new Node(1);
		head.left.right.right = new Node(6);
		head.right.left = new Node(2);
		head.right.right = new Node(1);
//		printTree(head);
		System.out.println(getMaxLength(head, 6));
		System.out.println(getMaxLength(head, -9));
	}

}
