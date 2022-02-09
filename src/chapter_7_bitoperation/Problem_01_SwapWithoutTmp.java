package chapter_7_bitoperation;

public class Problem_01_SwapWithoutTmp {

    public static int add(int a, int b) {
        int yu = (a & b);
        int hu = (a ^ b);
        while (yu != 0) {
            yu = (yu << 1);
            int yu2 = (yu & hu);
            int hu2 = (yu ^ hu);
            yu = yu2;
            hu = hu2;
        }
        return hu;
    }

    public static String int2String(int n) {
        StringBuilder sb = new StringBuilder();

//		for (int i = 0; i < 32; i++) {
//			if((n & (1 << i))==0){
//				sb.insert(0, '0');
//			}else{
//				sb.insert(0, '1');
//			}
//		}

		int model = 1;
        while (model !=(1<<31)) {
            if ((n & model) == 0) {
                sb.insert(0, '0');
            } else {
                sb.insert(0, '1');
            }
			model = (model<<1);
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static void main(String[] args) {
//		int a = 16;
//		int b = 111;
//		System.out.println(a);
//		System.out.println(b);
//		a = a ^ b;
//		b = a ^ b;
//		a = a ^ b;
//		System.out.println(a);
//		System.out.println(b);

//		System.out.println(add(1,1));
//		System.out.println(add(1,2));
//		System.out.println(add(2,2));

//        int2String(1);
		int2String(-2);
        int2String((~(-2))+1);
		int2String(-1); // 取反码+1  0000000000000001

//		System.out.println(add(-1,2));


        //交换两个变量的值
//        int a = 1;//01
//        int b = 2;//10
//        a = a^b;//11
//        b = a^b;//01
//        a = a^b;//
//        System.out.println("a="+a);
//        System.out.println("b="+b);
    }

}
