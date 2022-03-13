package learn;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

class FooBar {
    private int n;

    public FooBar(int n) {
        this.n = n;
    }
    Semaphore sfoo = new Semaphore(1);
    Semaphore sbar = new Semaphore(0);


    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            sfoo.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            sbar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            sbar.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            sfoo.release();
        }
    }


    public static void main(String[] args) throws InterruptedException {

        FooBar fooBar = new FooBar(5);

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            fooBar.bar(new Runnable() {
                                @Override
                                public void run() {
                                    System.out.println("bar");
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();


        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            fooBar.foo(new Runnable() {
                                @Override
                                public void run() {
                                    System.out.println("foo");
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();

    }
}