package chapter_3_wanhao;

public class Problem_23_UniqueBST_wh {

    /**
     * 计算出二叉树的数量
     *
     * @param n
     * @return
     */
    public static int numTrees(int n) {
        if (n == 0) {
            return 1;
        } else if (n <= 2) {
            return n;
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            //i作为根节点的情况[0,i-1]  [i,n-i]
            res = res + numTrees(i - 1) * numTrees(n - i);
        }

        return res;
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.println(numTrees(n));
//		List<Node> res = generateTrees(n);
//		for (Node node : res) {
//			printTree(node);
//		}
    }

}
