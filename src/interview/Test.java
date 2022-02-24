package interview;

public class Test {

    public static class P{
        public int a;

    }

    public static class S extends P{
        public int a;

    }

    public static void main(String[] args) {
        String s1 = "s1";
        String s2 = "s1";

        System.out.println(s1==s2);
    }


}
