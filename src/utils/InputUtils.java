package utils;

import java.util.*;


/**
 * 可以辅助构造数据，并且可以打印构造的数据。
 * 每个方法可以测试下性能，然后再用暴力枚举的方式，验证下性能和结果的正确性。
 */

public class InputUtils {
    //打印数量超过这莫多，就不再打印
    public static final int OUTPUT_LIMIT = 100;

    public enum SortType {
        //升序，降序，随机顺序
        increase, decrease, normal
    }

    public enum RandomRatio {
        //将原来的数组打乱，程度
        //只打乱一点点，
        less,
        //打乱五分之一
        some,
        //打乱三分之一
        much,
        num20,//打乱20个元素
        num10,//打乱10个元素

    }

    public static int[] generateIntArray(int number) {
        int[] arr = generateIntArray(number, 0, number * 10, true, SortType.normal);
        if (number <= OUTPUT_LIMIT) {
            printlnArr(arr);
        }
        return arr;
    }

    public static int[] generateIntArray(int number, boolean repeat, SortType sortType) {
        int[] arr = generateIntArray(number, 0, number * 100, repeat, sortType);
        return arr;
    }

    public static int[] generateIntArray(int number, boolean repeat, SortType sortType, RandomRatio random) {
        int[] arr = generateIntArray(number, 0, number * 100, repeat, sortType);

        int swapNums = 0;
        if (random == RandomRatio.less) {
            swapNums = number / 100 + 1;
        } else if (random == RandomRatio.some) {
            swapNums = number / 10 + 1;
        } else if (random == RandomRatio.much) {
            swapNums = number / 4 + 1;
        } else if (random == RandomRatio.num10) {
            swapNums = 10;
        } else if (random == RandomRatio.num20) {
            swapNums = 20;
        }
        while (swapNums > 0) {
            swapNums--;
            int indexFrom = (int) (Math.random() * (arr.length - 1));
            int indexTo = (int) (Math.random() * (arr.length - 1));
            int temp = arr[indexFrom];
            arr[indexFrom] = arr[indexTo];
            arr[indexTo] = temp;
        }
        return arr;
    }

    /**
     * 多参数，构造一个测试输入数组
     *
     * @param number   数量
     * @param min      最小值
     * @param max      最大值
     * @param repeat   是否可以重复
     * @param sortType 是否增续，降序，乱序
     * @return
     */
    public static int[] generateIntArray(int number, int min, int max, boolean repeat, SortType sortType) {

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
        if (!repeat && number > max - min) {
            throw new RuntimeException("min max范围过小");
        }

        int[] input = new int[number];
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < number; i++) {
            input[i] = (int) (Math.random() * (max - min)) + min;

            if (!repeat) {
                //不可重复
                while (set.contains(input[i])) {
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
        Arrays.stream(arr).forEach(i -> list.add(i));
        System.out.println("输入:" + list.toString());
        return list;
    }

    /**
     * 返回树的头节点。 感觉这最好用一个数组生成一个二叉树
     *
     * @param number
     * @return
     */
    public static Node generateTree(int number) {
        return generateTree(generateIntArray(number));
    }

    public static Node generateTree(int number, boolean repeat, SortType sortType) {
        return generateTree(generateIntArray(number, repeat, sortType));
    }


    /**
     * 根据数组的中序遍历的结果，生成这颗二叉树。返回根节点，
     * 也就是说，若是数组是有序的，那么生成的是有序的二叉排序树。
     *
     * @param array
     * @return
     */
    public static Node generateTree(int[] array) {
        return generateTree(array, 0, array.length - 1);
    }

    /**
     * 根据array[left,right]来生成一棵树
     *
     * @param array
     * @param left
     * @param right
     * @return
     */
    public static Node generateTree(int[] array, int left, int right) {
        if (left == right) {
            return new Node(array[left]);
        } else if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        Node head = new Node(array[mid]);
        head.left = generateTree(array, left, mid - 1);
        head.right = generateTree(array, mid + 1, right);
        return head;
    }

    public static Node generateCompletedTree(int num) {
        return generateCompletedTree(InputUtils.generateIntArray(num, false, SortType.increase), 0, num - 1);
    }

    /**
     * 创建完全二叉树.
     *
     * @param array
     * @param left
     * @param right
     * @return
     */
    public static Node generateCompletedTree(int[] array, int left, int right) {
        if (left == right) {
            return new Node(array[left]);
        } else if (left > right) {
            return null;
        } else if (left + 1 == right) {
            Node h = new Node(array[left]);
            h.left = new Node(array[left + 1]);
            return h;
        }
        int[] two = new int[100];
        int[] fullTree = new int[100];//满二叉树,x层,有多少结点
        two[0] = 1;
        for (int i = 1; i < 100; i++) {
            two[i] = 2 * two[i - 1];
        }
        fullTree[0] = 1;
        for (int i = 1; i < 100; i++) {
            fullTree[i] = fullTree[i - 1] + two[i];
        }

        /**
         * 确认mid结点,然后拆分为左右
         * 左右至少有一边是完美二叉树,然后左边的树数量,大于等于右边的树
         */
        int totalNum = right - left;//去掉根节点
        int level = 0;
        while (fullTree[level + 1] * 2 < totalNum) {
            level++;
        }
        int leftCou = 0;
        int rightCou = 0;
        //不完整的树在左边还是右边.
        if (fullTree[level] + fullTree[level + 1] <= totalNum) {//右
            leftCou = fullTree[level + 1];
            rightCou = totalNum - fullTree[level + 1];
        } else {//左
            leftCou = totalNum - fullTree[level];
            rightCou = fullTree[level];
        }
        //极端情况
        if (leftCou < rightCou) {
            int temp = leftCou;
            leftCou = rightCou;
            rightCou = temp;
        }

        int mid = left + leftCou;
        Node head = new Node(array[mid]);
        head.left = generateCompletedTree(array, left, left + leftCou - 1);
        head.right = generateCompletedTree(array, right - rightCou + 1, right);
        return head;
    }

    public static void arrayReverse(int[] originArray) {
        int[] reverseArray = new int[originArray.length];
        for (int i = 0; i < originArray.length; i++) {
            reverseArray[i] = originArray[originArray.length - i - 1];
        }
        System.arraycopy(reverseArray, 0, originArray, 0, originArray.length);
    }

    public static void printArr(int[] arr) {
        System.out.print("{" + arr[0]);
        for (int i = 1; i < arr.length; i++) {
            System.out.print(", " + arr[i]);
        }
        System.out.print("}");
        System.out.println();
    }

    public static void printArr(Integer[] arr) {
        System.out.print("{" + arr[0].intValue());
        for (int i = 1; i < arr.length; i++) {
            System.out.print(", " + arr[i].intValue());
        }
        System.out.print("}");
    }

    public static void printlnArr(int[] arr) {
        printArr(arr);
        System.out.println();
    }

    /**
     * 校验arr1 和 arr2 数组长度和内容是一致的，否则报错
     *
     * @param arr1
     * @param arr2
     */
    public static void checkEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            throw new RuntimeException("arr1.length:" + arr1.length + ",arr2.length:" + arr2.length);
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                throw new RuntimeException("position " + i + " arr1:" + arr1[i] + " arr2:" + arr2[i]);
            }
        }

    }

    /**
     * 校验两个链表是否相同
     *
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
     *
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
        if (head.left == null && head.right == null) {
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
        int[] array = generateIntArray(number, 0, number * 10, true, SortType.normal);
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
     *
     * @return
     */
    public static int[][] generateMatrix(int rows, int colunms) {
        return generateMatrix(rows, colunms, 0, rows * colunms * 10);
    }

    public static int[][] generateMatrix(int rows, int colunms, int minValue, int maxValue) {
        int[][] result = new int[rows][colunms];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colunms; j++) {
                result[i][j] = minValue + (int) ((maxValue - minValue + 1) * Math.random());
            }
        }

        return result;
    }

    public static void fill(int[][] input, int number, int value) {
        for (int i = 0; i < number; i++) {
            while (true) {
                int x = (int) ((input.length) * Math.random());
                int y = (int) ((input[0].length) * Math.random());
                if (input[x][y] != value) {
                    input[x][y] = value;
                    break;
                }
            }
        }
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        System.out.println("{");
        for (int i = 0; i != matrix.length; i++) {
            System.out.print("{");
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
                if (j != matrix[0].length - 1) {
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

    public static String generateString(int number) {
        return generateString(number, 'A', 'Z');
    }

    public static String generateString(int number, char start, char end) {
        StringBuffer sb = new StringBuffer(number);
        for (int i = 0; i < number; i++) {
            sb.append((char) (start + (char) ((end - start) * Math.random())));
        }
        return sb.toString();
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

//        Node n = generateLinkedList(50);
//        printLinkedList(n);
//        Node newNode = cloneNewNode(n);
//        printLinkedList(newNode);
//        checkEqual(n, newNode);

        Node n = generateTree(10, false, SortType.increase);
        OutputUtils.printTree(n);
    }

}
