package interview;

public class A {
    public static synchronized void aaa() {
        try{
            Thread.sleep(1000*3);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public synchronized void bbb(){
        try{
            Thread.sleep(1000*3);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
