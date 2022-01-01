package utils;

import java.util.*;

/**
 * 可以辅助构造数据，并且可以打印构造的数据。
 * 每个方法可以测试下性能，然后再用暴力枚举的方式，验证下性能和结果的正确性。
 */

public class InputUtils {

    public enum SortType {
        //升序，降序，随机顺序
        increase, decrease, normal
    }

    public static int[] generateIntArray(int number) {
        int[]arr = generateIntArray(number,  0,number * 10, true, SortType.normal);
        printlnArr(arr);
        return arr;
    }
    public static int[] generateIntArray(int number, boolean repeat, SortType sortType) {
        int[]arr = generateIntArray(number,  0,number * 100, repeat, sortType);
        return arr;
    }


    /**
     * 多参数，构造一个测试输入数组
     * @param number 数量
     * @param min 最小值
     * @param max 最大值
     * @param repeat 是否可以重复
     * @param sortType 是否增续，降序，乱序
     * @return
     */
    public static int[] generateIntArray(int number,int min, int max, boolean repeat, SortType sortType) {

        int[] res = generateIntArray(number, min, max, repeat);

        if (sortType == SortType.increase) {
            Arrays.sort(res);
            return res;
        } else if (sortType == SortType.decrease) {
            Arrays.sort(res);
            arrayReverse(res);
            return res;
        } else {
            return res;
        }
    }


    public static int[] generateIntArray(int number, int min, int max, boolean repeat) {
        if(!repeat && number>max-min){
            throw new RuntimeException("min max范围过小");
        }

        int[] input = new int[number];
        Set<Integer>set = new HashSet<>();

        for (int i = 0; i < number; i++) {
            input[i] = (int) (Math.random() * (max - min)) + min;

            if (!repeat) {
                //不可重复
                while(set.contains(input[i])){
                    input[i] = (int) (Math.random() * (max - min)) + min;
                }
                set.add(input[i]);
            }
        }

        return input;
    }


    public static List<Integer> generateIntList(int number) {
        int[] arr = generateIntArray(number);
        List<Integer> list = new ArrayList<>(number);
        Arrays.stream(arr).forEach(i->list.add(i));
        System.out.println("输入:"+list.toString());
        return list;
    }


    public static void arrayReverse(int[]originArray) {
        int[]reverseArray = new int[originArray.length];
        for (int i = 0; i < originArray.length; i++) {
            reverseArray[i] = originArray[originArray.length - i - 1];
        }
        System.arraycopy(reverseArray,0,originArray,0,originArray.length);
    }

    public static void printArr(int[] arr) {
        System.out.print("{" + arr[0]);
        for (int i = 1; i < arr.length; i++) {
            System.out.print(", "+arr[i]);
        }
        System.out.print("}");
    }
    public static void printlnArr(int[] arr) {
        printArr(arr);
        System.out.println();
    }

    /**
     * 校验arr1 和 arr2 数组长度和内容是一致的，否则报错
     * @param arr1
     * @param arr2
     */
    public static void checkEqual(int[] arr1, int[] arr2){
        if (arr1.length!=arr2.length){
            throw new RuntimeException("arr1.length:"+arr1.length+",arr2.length:"+arr2.length);
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i]!=arr2[i]){
                throw new RuntimeException("position "+i +" arr1:" +arr1[i]+ " arr2:"+arr2[i]);
            }
        }

    }

    /**
     * 校验两个链表是否相同
     * @param head1
     * @param head2
     */
    public static void checkEqual(Node head1, Node head2) {
        while (head1 != head2) {
            if (head1 == null || head2 == null) {
                throw new RuntimeException("NULL node");
            }
            if (head1.value == head2.value) {
                head1 = head1.next;
                head2 = head2.next;
            }
        }
    }

    /**
     * 会将一个链表，复制一份，返回另一个完全独立的链表。
     * @return
     */
    public static Node cloneNewNode(Node head) {
        if (head == null) {
            return null;
        }
        Node newHead = new Node(head.value);
        Node temp = newHead;
        while (head.next != null) {
            head = head.next;
            temp.next = new Node(head.value);
            temp = temp.next;
        }

        return newHead;
    }

    public static Node cloneNewTreeNode(Node head) {
        if (head == null) {
            return null;
        }
        if (head.left == null && head.right == null){
            return new Node(head.value);
        }

        Node newHead = new Node(head.value);
        newHead.left = cloneNewTreeNode(head.left);
        newHead.right = cloneNewTreeNode(head.right);
        return newHead;
    }

    /**
     * 单向链表是next
     * 树是left，right
     */
    public static class Node {
        public int value;
        public Node next;
        public Node left;
        public Node right;


        public Node(int data) {
            this.value = data;
        }
    }

    public static Node generateLinkedList(int number) {
        int[] array = generateIntArray(number,  0,number * 10, true, SortType.normal);
        Node head = new Node(array[0]);
        Node temp = head;
        for (int i = 1; i < array.length; i++) {
            temp.next = new Node(array[i]);
            temp = temp.next;
        }

        return head;
    }

    /**
     * 返回一个矩阵
     * @return
     */
    public static int[][] generateMatrix(int rows, int colunms) {
        int[][] result = new int[rows][colunms];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colunms; j++) {
                result[i][j] = (int) (rows * colunms * 10 * Math.random());
            }
        }

        return result;
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        System.out.println("{");
        for (int i = 0; i != matrix.length; i++) {
            System.out.print("{");
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
                if (j!=matrix[0].length-1){
                    System.out.print(",");
                }
            }
            if (i == matrix.length - 1) {
                System.out.println("}");
            } else {
                System.out.println("},");
            }
        }
        System.out.println("};");
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
//        generateIntArray(10);
//        printArr(generateIntArray(1000,0,1000*100,false,SortType.increase));
//        printArr(generateIntArray(100,0,1000*100,false,SortType.decrease));

//        checkEqual( generateIntArray(10),  generateIntArray(10));
        Node n = generateLinkedList(50);
        printLinkedList(n);
        Node newNode = cloneNewNode(n);
        printLinkedList(newNode);
        checkEqual(n, newNode);
    }

}
