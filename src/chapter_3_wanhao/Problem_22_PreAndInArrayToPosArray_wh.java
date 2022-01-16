package chapter_3_wanhao;

public class Problem_22_PreAndInArrayToPosArray_wh {

    public static int[] fillPosArray(int[] pre, int[] in) {
        int[] pos = new int[pre.length];
        fillPosArray(pre, 0, pre.length - 1, in, 0, in.length - 1, pos, 0, pos.length - 1);
        return pos;
    }

    public static void fillPosArray(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn,
                                    int[] pos, int startPos, int endPos) {
        if (endPos < startPos) {
            return;
        } else if (endPos == startPos) {
            pos[endPos] = pre[startPre];
            return;
        }

        pos[endPos] = pre[startPre];
        int findIn = -1;
        for (int i = startIn; i <= endIn; i++) {
            if (in[i] == pre[startPre]) {
                findIn = i;
                break;
            }
        }
        int leftLen = findIn - startIn;
        int rightLen = endIn - findIn;
        //左子树填充
        fillPosArray(pre, startPre + 1, startPre + leftLen,
                in, startIn, findIn-1,
                pos, startPos, startPos + leftLen - 1);

        //右子树填充
        fillPosArray(pre, endPre-rightLen+1, endPre,
                in, findIn+1, endIn,
                pos, endPos-rightLen, endPos-1);
    }


    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 5, 3, 6, 7};
        int[] in = {4, 2, 5, 1, 6, 3, 7};
        int[] pos = fillPosArray(pre, in);
        printArray(pos);

    }
}
