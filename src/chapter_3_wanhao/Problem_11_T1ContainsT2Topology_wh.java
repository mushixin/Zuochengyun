package chapter_3_wanhao;

import utils.InputUtils.*;

public class Problem_11_T1ContainsT2Topology_wh {

    /**
     * 递归去解决即可。
     *
     * @param t1
     * @param t2
     * @return
     */
    public static boolean contains(Node t1, Node t2) {
        boolean hr  = subTree(t1,t2);
        if (hr){
            return true;
        }
        hr = contains(t1.left,t2);
        if (hr){
            return true;
        }
        hr = contains(t1.right,t2);
        if (hr){
            return true;
        }
        return false;
    }
    public static boolean subTree(Node t1, Node t2){
        if (t2 == null) {
            return true;
        }
        if (t1 == null) {
            return false;
        }
        if (t1.value == t2.value) {
            if (!contains(t1.left, t2.left) || !contains(t1.right, t2.right)) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        Node t1 = new Node(1);
        t1.left = new Node(2);
        t1.right = new Node(3);
        t1.left.left = new Node(4);
        t1.left.right = new Node(5);
        t1.right.left = new Node(6);
        t1.right.right = new Node(7);
        t1.left.left.left = new Node(8);
        t1.left.left.right = new Node(9);
        t1.left.right.left = new Node(10);

        Node t2 = new Node(2);
        t2.left = new Node(4);
        t2.left.left = new Node(8);
        t2.right = new Node(5);

        System.out.println(contains(t1, t2));

    }

}
