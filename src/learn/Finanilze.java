package learn;

public class Finanilze {


    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }

    public static void main(String[] args) {
        Finanilze f1;
        for(int i = 0;i < 2;i++) {
            f1 = new Finanilze();
            f1 = null;
        }
        Finanilze f2 = new Finanilze();
    }
}
