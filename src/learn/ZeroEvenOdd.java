package learn;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    AtomicInteger atomicInteger = new AtomicInteger(0);

    Semaphore semaZero = new Semaphore(1);
    Semaphore semaOne = new Semaphore(0);
    Semaphore semaTwo = new Semaphore(0);

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; ++i) {
            semaZero.acquire();
            printNumber.accept(0);

            if (i % 2 == 1) {
                semaOne.release();
            } else {
                semaTwo.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        semaTwo.acquire();
        int value = atomicInteger.incrementAndGet();
        printNumber.accept(value);

        semaZero.release();
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        semaOne.acquire();
        int value = atomicInteger.incrementAndGet();
        printNumber.accept(value);

        semaZero.release();
    }
}