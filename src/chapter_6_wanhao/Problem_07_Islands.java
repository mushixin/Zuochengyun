package chapter_6_wanhao;

import javafx.util.Pair;

import java.util.*;

public class Problem_07_Islands {
    /**
     * 重新计算有多少岛屿
     *
     * @param m
     * @return
     */
    public static int countIslands_redo(int[][] m) {
        if (m == null || m[0] == null) {
            return 0;
        }
        int result = 0;
        for (int x = 0; x < m.length; x++) {
            for (int y = 0; y < m[0].length; y++) {
                if (m[x][y] == 1) {
                    result++;
					mark(m,x,y);
                }
            }
        }
		return result;
    }

    public static void mark(int[][] m, int x, int y) {
        if (x < 0 || y < 0 || x >= m.length || y >= m[0].length || m[x][y]==0) {
			return;
        }
		m[x][y]=0;
		mark(m,x+1,y);
		mark(m,x,y+1);
		mark(m,x-1,y);
		mark(m,x,y-1);
	}


    /**
     * 17:05 开始，
     * bfs，然后全部标记即可。
     * 遍历每个结点，若是1，则进行标记，和count，后续跳过标记的结点即可。
     * dfs
     *
     * @param m
     * @return
     */
    public static int countIslands(int[][] m) {
        if (m == null || m[0] == null) {
            return 0;
        }
        int result = 0;
        boolean[][] marked = new boolean[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == 0 || marked[i][j]) {
                    continue;
                }
                if (m[i][j] == 1) {
                    result++;
                    putRelatedNodes(m, i, j, marked);
//					System.out.println(i+","+j);
                }
            }
        }

        return result;
    }

    /**
     * 将i，j结点及相关的结点，放入map中，跳过。
     *
     * @param m
     * @param i
     * @param j
     * @param marked
     */
    public static void putRelatedNodes(int[][] m, int i, int j, boolean[][] marked) {
        marked[i][j] = true;
        LinkedList<Pair> list = new LinkedList<>();
        LinkedList<Pair> unlist = new LinkedList<>();

        for (int k = j; k < m[0].length; k++) {
            if (0 == m[i][k]) {
                break;
            }
            marked[i][k] = true;
            list.addFirst(new Pair(i, k));
        }
        //结点的下一层遍历
        while (!list.isEmpty() || !unlist.isEmpty()) {

            for (Pair p : list) {
                Integer x = (Integer) p.getKey();
                Integer y = (Integer) p.getValue();
                if (x + 1 < m.length && m[x + 1][y] == 1) {
                    unlist.addFirst(new Pair(x + 1, y));
                    marked[x + 1][y] = true;

                    for (int k = y - 1; k >= 0; k--) {
                        if (m[x + 1][k] == 1) {
                            unlist.addFirst(new Pair(x + 1, k));
                            marked[x + 1][k] = true;
                        } else {
                            break;
                        }
                    }

                } else {
                    continue;
                }

            }
            list = unlist;
            unlist = new LinkedList<>();
        }
    }


    public static void main(String[] args) {
        int[][] m1 = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        System.out.println(countIslands(m1));

        int[][] m2 = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        System.out.println(countIslands(m2));

    }

}
