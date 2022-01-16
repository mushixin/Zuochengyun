package chapter_3_wanhao;

public class Problem_17_DescendantNode_wh {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 一个笨办法，找到根结点，然后中序遍历一遍，得到下个结点即可。
     * 一个聪明办法：（分三种情况）
     *
     * @param node
     * @return
     */
    public static Node getNextNode(Node node) {
        /**
         * 当前结点有右子树
         * 下一个结点就是右子树最左边的结点。
         */
        if (node.right != null) {
            return getLeftest(node.right);
        }

        /**
         * 又没有右子树，又没有父节点，就会返回空
         */
        if (node.parent == null) {
            return null;
        }


        /**
         * 当前结点是一个左子树的情况下
         * 下一个结点就是父结点
         */
        if (node == node.parent.left) {
            return node.parent;
        }

        /**
         * 当前结点是一个右子树的情况下。
         * 下一个结点是（向上找第一个根节点，这个根节点的左子树是当前结点。没有返回空即可）
         */
        if (node == node.parent.right) {
            Node p = node.parent;
            while (p!=null && p.right == node) {
                node=p;
                p=node.parent;
            }
            return p;
        }

        return null;
    }

    /**
     * 得到这个子树最左边的点
     *
     * @param node
     * @return
     */
    public static Node getLeftest(Node node) {
        if (node.left != null) {
            return getLeftest(node.left);
        }
        return node;
    }


    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getNextNode(test));
    }

}
