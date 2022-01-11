package chapter_8_wanhao;

import javafx.util.Pair;
import utils.InputUtils;
import utils.TimeUtils;

import java.util.LinkedList;

public class Problem_24_MinPathValue {
    public static boolean[][] mapSteped = null;

    /**
     * 返回最短路径。
     * bfs，每次只走一步，然后走到目的地的最小耗费
     *
     * @param m
     * @return
     */
    public static int minPathValue_redo(int[][] m) {
        mapSteped = new boolean[m.length][m[0].length];
        mapSteped[0][0] = true;
        int currentStep = 1;
        LinkedList<Integer> x = new LinkedList<>();
        LinkedList<Integer> y = new LinkedList<>();
        x.add(0);
        y.add(0);
        LinkedList<Integer> xNext = new LinkedList<>();
        LinkedList<Integer> yNext = new LinkedList<>();

        while (!x.isEmpty()) {
            int currentX = x.poll();
            int currentY = y.poll();
            if (currentX == m.length - 1 && currentY == m[0].length - 1) {
                return currentStep;
            }
            if (isAvailable(m, currentX + 1, currentY)) {
                xNext.addLast(currentX + 1);
                yNext.addLast(currentY);
            }
            if (isAvailable(m, currentX, currentY + 1)) {
                xNext.addLast(currentX);
                yNext.addLast(currentY + 1);
            }
            if (isAvailable(m, currentX - 1, currentY)) {
                xNext.addLast(currentX - 1);
                yNext.addLast(currentY);
            }
            if (isAvailable(m, currentX, currentY - 1)) {
                xNext.addLast(currentX);
                yNext.addLast(currentY - 1);
            }
            if (x.isEmpty()) {
                x = xNext;
                y = yNext;
                xNext = new LinkedList<>();
                yNext = new LinkedList<>();
				currentStep++;
            }
        }

        return -1;
    }

    public static boolean isAvailable(int[][] m, int x, int y) {
        //走过了就别走了
        if (x < 0 || y < 0 || x >= m.length || y >= m[0].length || m[x][y] == 0 || mapSteped[x][y] == true) {
            return false;
        }
        mapSteped[x][y] = true;
        return true;
    }


    /**
     * 19:55 更新全图，算是深度优先遍历，每次更新一个点，要更新这个点相关的所有点。
     * 最好还是广度优先遍历。
     *
     * @param m
     * @return
     */
    public static int minPathValue_bfs(int[][] m) {
        if (m == null || m.length <= 0 || m[0].length <= 0 || m[0][0] != 1) {
            return 0;
        }
        int stepNum = 1;
        boolean[][] map = new boolean[m.length][m[0].length];
        LinkedList<Pair> step = new LinkedList<>();
        LinkedList<Pair> nextStep = new LinkedList<>();
        step.addLast(new Pair(0, 0));
        while (!step.isEmpty()) {
            Pair<Integer, Integer> xy = step.removeFirst();
            if (xy.getKey() == m.length - 1 && xy.getValue() == m[0].length - 1) {
                return stepNum;
            }
            map[xy.getKey()][xy.getValue()] = true;
            if (available(m, xy.getKey() - 1, xy.getValue(), map)) {
                nextStep.addFirst(new Pair(xy.getKey() - 1, xy.getValue()));
            }
            if (available(m, xy.getKey() + 1, xy.getValue(), map)) {
                nextStep.addFirst(new Pair(xy.getKey() + 1, xy.getValue()));
            }
            if (available(m, xy.getKey(), xy.getValue() - 1, map)) {
                nextStep.addFirst(new Pair(xy.getKey(), xy.getValue() - 1));
            }
            if (available(m, xy.getKey(), xy.getValue() + 1, map)) {
                nextStep.addFirst(new Pair(xy.getKey(), xy.getValue() + 1));
            }
            if (step.isEmpty()) {
                step = nextStep;
                nextStep = new LinkedList<>();
                stepNum++;
            }
        }
        return 0;
    }

    /**
     * 是否可以走到x,y位置
     *
     * @param m
     * @param x
     * @param y
     * @return
     */
    public static boolean available(int[][] m, int x, int y, boolean[][] map) {
        if (x < 0 || y < 0 || x >= m.length || y >= m[0].length || map[x][y]) {
            return false;
        }
        map[x][y] = true;
        return m[x][y] == 1;
    }


    public static int minPathValue(int[][] m) {
        if (m == null || m.length <= 0 || m[0].length <= 0 || m[0][0] != 1) {
            return 0;
        }
        int[][] minPath = new int[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                minPath[i][j] = Integer.MAX_VALUE;
            }
        }
        update(m, minPath, 0, 0, 1);
        return minPath[m.length - 1][m[0].length - 1];
    }

    public static void update(int[][] m, int[][] minPath, int x, int y, int path) {
        if (x < 0 || y < 0 || x >= m.length || y >= m[0].length || m[x][y] == 0) {
            return;
        }
        if (minPath[x][y] > path) {
            minPath[x][y] = path;
            update(m, minPath, x - 1, y, path + 1);
            update(m, minPath, x + 1, y, path + 1);
            update(m, minPath, x, y - 1, path + 1);
            update(m, minPath, x, y + 1, path + 1);
        }

    }


    public static void main(String[] args) {
        int[][] matrix = {{1, 0, 1, 1, 1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1}, {1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1}, {1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1}, {1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1}, {1, 1, 1, 0, 1, 1, 1, 0, 1}};

        matrix = InputUtils.generateMatrix(600, 500, 0, 1);
        InputUtils.fill(matrix, 500 * 200, 1);
        matrix[0][0] = 1;
        matrix[matrix.length - 1][matrix[0].length - 1] = 1;
        TimeUtils.start();
        System.out.println(chapter_8_arrayandmatrix.Problem_24_MinPathValue.minPathValue(matrix));
        TimeUtils.stop();
//		System.out.println(minPathValue(matrix));
//		TimeUtils.stop();
        System.out.println(minPathValue_bfs(matrix));
        TimeUtils.stop();

		System.out.println(minPathValue_redo(matrix));
		TimeUtils.stop();
    }

}
