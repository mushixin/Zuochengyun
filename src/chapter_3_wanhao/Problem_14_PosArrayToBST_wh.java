package chapter_3_wanhao;

import utils.InputUtils.*;
import utils.OutputUtils;
import utils.OutputUtils.*;

public class Problem_14_PosArrayToBST_wh {


    public static Node posArrayToBST(int[] arr) {
        return posArrayToBST(arr, 0, arr.length - 1);
    }

    public static Node posArrayToBST(int[] arr, int left, int right) {
        if (right < left) {
            return null;
        } else if (right == left) {
            return new Node(arr[right]);
        }

        int leftSide = Integer.MIN_VALUE;//[left,leftSide)左子树
        int rightSide = Integer.MAX_VALUE;//[rightSide,right]右子树，
        for (int i = left; i < right; i++) {
            if (arr[i] < arr[right]) {
                leftSide = Math.max(leftSide, i);
            } else if (arr[i] > arr[right]) {
                rightSide = Math.min(rightSide, i);
            } else {
                return null;
            }
        }
        Node h = new Node(arr[right]);
        if (rightSide!=left){
			h.left = posArrayToBST(arr, left, leftSide);
		}
		if (leftSide!=right-1){
			h.right = posArrayToBST(arr,  rightSide,right-1);
		}
        return h;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 3, 5, 6, 7, 4};
//		System.out.println(isPost(arr, 0, arr.length - 1));
        OutputUtils.printTree(posArrayToBST(arr));

    }

}
