package newcoder;

public class Test {

    public static String error(){
        try {

            return "return";
        } catch (Exception e) {

        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(error());

    }
}
