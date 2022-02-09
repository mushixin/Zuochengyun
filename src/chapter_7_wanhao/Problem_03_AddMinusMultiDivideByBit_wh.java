package chapter_7_wanhao;

public class Problem_03_AddMinusMultiDivideByBit_wh {

	public static int add(int a, int b) {
		int sum  = a^b;
		int jinwei = a&b;
		while (jinwei!=0){
			jinwei = (jinwei<<1);
			int sum2  = sum ^ jinwei;
			int jinwei2 = sum & jinwei;
			sum = sum2;
			jinwei = jinwei2;
		}
		return  sum;
	}

	/**
	 * 1 - 1 = 0
	 * 0 - 1 = 1
	 * 1 - 0 = 1
	 * 0 - 0 = 0
	 * 遍历每一位，计算结果是否借位，将a b右移，然后一直计算结果
	 * @param a
	 * @param b
	 * @return
	 */
	public static int minus(int a, int b) {
		return add(a,neg(b));
	}

	//取反
	public static int neg(int n){
		return add(~n,1);
	}

	//11 * 01 = 0011
	//11 * 10 = 0110
	//11 * 11 = 1001  9
	//a  * b
	public static int multi(int a, int b) {
		if(a<0 && b<0){
			a = neg(a);
			b = neg(b);
		}else if( b<0){
			a = neg(a);
			b = neg(b);
		}

		int res = 0;
		int move = 0;
		while(b!=0){
			if((b&1)==1) {
				res = add(res, (a<<move) );
			}
			b = (b>>1);
			move++;
//			a = ();
		}
		return res;
	}

	public static int divide(int a, int b) {

		return a;
	}

	public static void main(String[] args) {
		int a = (int) (Math.random() * 100000) - 50000;
		int b = (int) (Math.random() * 100000) - 50000;
		System.out.println("a = " + a + ", b = " + b);
		System.out.println(add(a, b));
		System.out.println(a + b);
		System.out.println("=========");
		System.out.println(minus(a, b));
		System.out.println(a - b);
		System.out.println("=========");
		System.out.println(multi(a, b));
		System.out.println(a * b);
		System.out.println("=========");
		System.out.println(divide(a, b));
		System.out.println(a / b);
		System.out.println("=========");

		a = Integer.MIN_VALUE;
		b = 32;
		System.out.println(divide(a, b));
		System.out.println(a / b);

	}

}
