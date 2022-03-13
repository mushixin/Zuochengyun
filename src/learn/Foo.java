package learn;

import java.util.concurrent.CountDownLatch;

class Foo {
    CountDownLatch cdtb = new CountDownLatch(1);
    CountDownLatch  cdtc = new CountDownLatch(1);

    public Foo() {
        
    }

    public void first(Runnable printFirst) throws InterruptedException {
        
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        cdtb.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        cdtc.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        cdtc.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}