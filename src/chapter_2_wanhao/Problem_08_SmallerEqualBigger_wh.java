package chapter_2_wanhao;
import utils.InputUtils;
import utils.InputUtils.Node;

public class Problem_08_SmallerEqualBigger_wh {


	public static Node listPartition2(Node head, int pivot) {
		Node[] small = new Node[2];
		Node[] equal = new Node[2];
		Node[] big = new Node[2];

		while (head != null) {
			Node next = head.next;
			if (head.value < pivot) {
				addNode(small, head);
			} else if (head.value == pivot) {
				addNode(equal, head);
			} else {
				addNode(big, head);
			}
			head = next;
		}
		// 连接三部分，并返回头节点
		if ( small[0] != null) {
			small[1].next = equal[0];
		}
		Node[] newhead = join(join(small, equal), big);
		return newhead[0];
	}

	/**
	 * 将两段连接，然后返回第一个结点的头部
	 * @param small
	 * @param equal
	 * @return
	 */
	public static Node[] join(Node[] small, Node[] equal) {
		if (small == null || small[0] == null) {
			return equal;
		}
		Node[] newList = new Node[2];
		if (small[1]==null) {
			newList[0] = small[0];
			small[0].next = equal[0];
			newList[1] = equal[1];
			return newList;
		} else {
			small[1].next = equal[0];
			newList[0] = small[0];
			newList[1] = equal[1];
			return newList;
		}

	}

	/**
	 * nodes[0] 表示链表的第一个结点
	 * nodes[1] 表示链表的最后一个结点。
	 * 返回也是这样返回的,添加到链表末尾即可
	 *
	 * 会加入到链表中，然后会将n下一个重置
	 * @param nodes
	 * @param n
	 * @return
	 */
	public static void addNode(Node[] nodes, Node n) {
		if (nodes[0] == null ) {
			nodes[0] = n;
		} else if (nodes[1] == null) {
			nodes[1] = n;
			nodes[0].next = nodes[1];
		}else{
			nodes[1].next = n;
			nodes[1] = n;
		}
		n.next = null;
	}


	public static void printLinkedList(Node node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
//		Node head1 = new Node(7);
//		head1.next = new Node(9);
//		head1.next.next = new Node(1);
//		head1.next.next.next = new Node(8);
//		head1.next.next.next.next = new Node(5);
//		head1.next.next.next.next.next = new Node(2);
//		head1.next.next.next.next.next.next = new Node(5);
//		printLinkedList(head1);
//		head1 = listPartition2(head1, 5);
//		printLinkedList(head1);

		Node myNode = InputUtils.generateLinkedList(500);
		Node myNode2 = InputUtils.cloneNewNode(myNode);

		Node newNode = listPartition2(myNode, 500);

		Node newNode2 = listPartition2(myNode2,500);
		InputUtils.printLinkedList(newNode);
		InputUtils.printLinkedList(newNode2);
		InputUtils.checkEqual(newNode, newNode2);

	}

}
