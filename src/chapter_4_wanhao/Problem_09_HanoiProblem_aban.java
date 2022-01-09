package chapter_4_wanhao;

/**
 * 还是先放弃，和动态规划没有关系
 * 递归问题。
 */
public class Problem_09_HanoiProblem_aban {
	public static int[]step ;
	public static int stepCount=0;

    public static void hanoi(int n) {
        if (n > 0) {
            func(n, "left", "mid", "right");
        }
    }

    public static void func(int count, String left, String mid, String right) {
		move(count, left, right, mid);
    }

	/**
	 * 讲count个盘子从from迁移到to，中途经过walkby
	 * @param count
	 * @param from
	 * @param to
	 * @param walkby
	 */
	public static void move(int count, String from, String to, String walkby) {
		if (count == 1) {
			printPath(from, to);
			return;
		}
		move(count - 1, from, walkby, to);
		move(1, from, to, walkby);
		move(count - 1, walkby, to, from);
	}


    public static void printPath(String from, String to) {
        System.out.println("move from " + from + " to " + to);
    }

	/**
	 * 这里直接统计移到初始点，所需要的步数。
	 * @param arr
	 * @return
	 */
	public static int step1(int[]arr){
		step = new int[arr.length];
		stepCount = 0;



		return 0;
	}


    public static void main(String[] args) {
        int n = 3;
        hanoi(n);

		int[] arr = { 3, 3, 2, 1 };
		System.out.println(step1(arr));//4
//		System.out.println(step2(arr));

    }
}
