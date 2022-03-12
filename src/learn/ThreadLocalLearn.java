package learn;

public class ThreadLocalLearn {

    public static void main(String[] args) throws InterruptedException {

        ThreadLocal<String> threadLocalOld = new ThreadLocal<String>(){
            @Override
            protected String initialValue() {
                return new String("dsadsa");
            }
        };
        ThreadLocal<String> threadLocalOld2 = new ThreadLocal<String>(){
            @Override
            protected String initialValue() {
                return new String("dsadsa2");
            }
        };

        new Thread(()->{
            String s = threadLocalOld.get();//每个线程调度get函数获取本线程的副本。
            System.out.println("thread1:"+s);
            threadLocalOld.set("s1");// set函数set的值，只会设置本线程的值，不会对其他线程有任何影响。
            s = threadLocalOld.get();//每个线程调度get函数获取本线程的副本。
            System.out.println("thread1:"+s);
            System.out.println();
            s = threadLocalOld2.get();//每个线程调度get函数获取本线程的副本。
            System.out.println("thread1:"+s);
            threadLocalOld2.set("s12");// set函数set的值，只会设置本线程的值，不会对其他线程有任何影响。
            s = threadLocalOld2.get();//每个线程调度get函数获取本线程的副本。
            System.out.println("thread1:"+s);

        }).start();

        Thread.sleep(1000);

        new Thread(()->{
            System.out.println();
            System.out.println();
            System.out.println();
            String s = threadLocalOld.get();//每个线程调度get函数获取本线程的副本。
            System.out.println("thread2:"+s);
            System.out.println();
            s = threadLocalOld2.get();//每个线程调度get函数获取本线程的副本。
            System.out.println("thread2:"+s);

        }).start();

    }

}
