package chapter_3_wanhao;

import chapter_3_binarytreeproblem.Problem_11_T1ContainsT2Topology;
import utils.InputUtils;
import utils.InputUtils.*;
import utils.TimeUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem_11_T1ContainsT2Topology_wh {


    /**
     * 思路是先序遍历，二子树的遍历到根节点用*
     * 正则匹配，这种方式，可能会有问题。若是有重复结点，那就会有问题。若没有重复结点，那就没有问题。
     * 但是性能也会很差。正则匹配，模糊匹配效率挺低的。
     * @param t1
     * @param t2
     * @return
     */
    public static boolean containsTopo(Node t1, Node t2) {
        StringBuilder sb = get(new StringBuilder(), t1);
        StringBuilder sb2 = getX(new StringBuilder(), t2);
        String str2 = sb2.toString().replaceAll("\\*\\*", "\\*");
        str2 = str2.replaceAll("\\*\\*", "\\*");
        str2 = str2.replaceAll("\\*", "\\.\\*");

        Pattern p = Pattern.compile(str2);
        Matcher matcher = p.matcher(sb);

        return matcher.find();
    }

    public static StringBuilder get(StringBuilder sb, Node t1) {
        if (t1 == null) {
            sb.append("#");
            return sb;
        }
        sb.append(t1.value + "|");
        get(sb, t1.left);
        get(sb, t1.right);
        return sb;
    }

    public static StringBuilder getX(StringBuilder sb, Node t2) {
        if (t2 == null) {
            sb.append('*');
            return sb;
        }
        sb.append(t2.value + "|");
        getX(sb, t2.left);
        getX(sb, t2.right);
        return sb;
    }

    /**
     * 递归去解决即可。
     *
     * @param t1
     * @param t2
     * @return
     */
    public static boolean contains(Node t1, Node t2) {
        if (t2 == null) {
            return true;
        }
        if (t1 == null) {
            return false;
        }
        return subTree(t1, t2) || contains(t1.left, t2) || contains(t1.right, t2);
    }

    public static boolean subTree(Node t1, Node t2) {
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
        System.out.println(containsTopo(t1, t2));

        t1 = InputUtils.generateTree(100_0000);
        t2 = InputUtils.cloneNewTreeNode(t1);
        t2 = t2.left.right.left;
        TimeUtils.start();
        System.out.println(contains(t1, t2));
        TimeUtils.stop();
        System.out.println(containsTopo(t1, t2));
        TimeUtils.stop();
        System.out.println(Problem_11_T1ContainsT2Topology.contains(t1, t2));
        TimeUtils.stop();

        t2.left.right.right.value=1000;
        System.out.println(contains(t1, t2));
        TimeUtils.stop();
        System.out.println(containsTopo(t1, t2));
        TimeUtils.stop();
        System.out.println(Problem_11_T1ContainsT2Topology.contains(t1, t2));
        TimeUtils.stop();
    }

}
