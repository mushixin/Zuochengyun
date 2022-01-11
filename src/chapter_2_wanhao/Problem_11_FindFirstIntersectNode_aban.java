package chapter_2_wanhao;

public class Problem_11_FindFirstIntersectNode_aban {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        //返回进入环的前一个结点。
        Node loop1 = getLoop(head1);
		Node loop2 = getLoop(head2);
        if (loop1 != null && loop2 != null) {
            if (loop1 == loop2) {
                //说明进入环前，就有公共的部分了。


            } else {
                return loop1;
            }
        } else if(loop1==null && loop2==null){
            //如何找到两个链表中，相交的结点？

        }

        return null;
    }

    /**
     * 这里根据快慢指针，得到链表的循环开启点
     *
     * @param head
     * @return
     */
    public static Node getLoop(Node head) {
        Node fast = head;
        Node slow = head;
        while (fast.next != null && fast!=null) {
            fast = fast.next.next;
            slow = slow.next;
			if (fast == slow){
				break;
			}
        }
        if (fast ==null || fast.next == null) {
            return null;
        }
        fast = head;
        while (fast.next != slow.next) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }


    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }

}
