package chapter_2_wanhao;

public class Problem_06_JosephusProblem_aban {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node josephusKill1(Node head, int m) {
		if (head == null || head.next == head || m < 1) {
			return head;
		}
		if (m == 1) {
			//t是head的前一个结点。
			Node t = head;
			while (t.next != head) {
				t = t.next;
			}
			t.next = t;
			return t;
		}

		while (head.next != head) {
			int tempm = m - 2;
			while (tempm > 0) {
				head = head.next;
				tempm--;
			}
			head.next = head.next.next;
		}
		return head;
	}

	public static void printCircularList(Node head) {
		if (head == null) {
			return;
		}
		System.out.print("Circular List: " + head.value + " ");
		Node cur = head.next;
		while (cur != head) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println("-> " + head.value);
	}

	/**
	 * 统计节点数为n，节点数为n,将1~n放进去，然后每次直接删除第m个元素。
	 * @param head
	 * @param m
	 * @return
	 */
	public static Node josephusKill2(Node head, int m) {
		int total = m;

		return null;
	}

	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = head1;
		printCircularList(head1);
		head1 = josephusKill1(head1, 3);
		printCircularList(head1);

		Node head2 = new Node(1);
		head2.next = new Node(2);
		head2.next.next = new Node(3);
		head2.next.next.next = new Node(4);
		head2.next.next.next.next = new Node(5);
		head2.next.next.next.next.next = head2;
		printCircularList(head2);
		head2 = josephusKill2(head2, 3);
		printCircularList(head2);

	}

}