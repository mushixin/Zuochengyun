package chapter_8_wanhao;

import chapter_8_arrayandmatrix.Problem_20_PrintMaxTopK;
import utils.InputUtils;
import utils.TimeUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Problem_20_PrintMaxTopK_wh {


    public static void printTopK(int[][] matrix, int topK) {
        //每个数组从后往前遍历，下所有需要被遍历的数组index
        LinkedList<Integer> result = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            result.add(i);
        }
		//每个数组的最后一个元素的索引，下个被遍历的元素
        int[] endIndex = new int[matrix.length];
        for (int i = 0; i < endIndex.length; i++) {
            endIndex[i] = matrix[i].length - 1;
        }
        PriorityQueue<Integer> topMaxK = new PriorityQueue<>();
        while (!result.isEmpty()) {
            Iterator<Integer> resultT = result.iterator();

            while (resultT.hasNext()) {
                Integer index = resultT.next();
                if (topMaxK.size() < topK) {
                    topMaxK.add(matrix[index][endIndex[index]]);
					endIndex[index]--;
					if (endIndex[index]<0){
						resultT.remove();
					}
                } else {
                    //开始遍历每个数组，若是这个数组元素小于最小值，则删除此数组，
					if (matrix[index][endIndex[index]]<=topMaxK.peek()){
						resultT.remove();
					}else{
						topMaxK.poll();
						topMaxK.add(matrix[index][endIndex[index]]);
						endIndex[index]--;
						if (endIndex[index]<0){
							resultT.remove();
						}
					}
                }
            }

        }
		List<Integer>res = topMaxK.stream().sorted(new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		}).collect(Collectors.toList());
		System.out.println(res.toString());
    }

    public static int[][] generateRandomMatrix(int maxRow, int maxCol,
                                               int maxValue) {
        if (maxRow < 0 || maxCol < 0) {
            return null;
        }
        int[][] matrix = new int[(int) (Math.random() * maxRow) + 1][];
        for (int i = 0; i != matrix.length; i++) {
            matrix[i] = new int[(int) (Math.random() * maxCol) + 1];
            for (int j = 0; j != matrix[i].length; j++) {
                matrix[i][j] = (int) (Math.random() * maxValue);
            }
            Arrays.sort(matrix[i]);
        }
        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = generateRandomMatrix(50000, 10000, 100000);
//        printMatrix(matrix);
        System.out.println("===========================");
		TimeUtils.start();
        printTopK(matrix, 1_0000);
		TimeUtils.stop();
		Problem_20_PrintMaxTopK.printTopK(matrix, 1_0000);
		System.out.println();
		TimeUtils.stop();
	}

}
