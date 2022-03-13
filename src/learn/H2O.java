package learn;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class H2O {

    public H2O() {

    }

    AtomicInteger hInt = new AtomicInteger(0);
    AtomicInteger oInt = new AtomicInteger(0);
    ReentrantLock reentrantLock = new ReentrantLock();
    Condition conH = reentrantLock.newCondition();
    Condition conO = reentrantLock.newCondition();


    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        reentrantLock.lock();
        try{
            hInt.incrementAndGet();
            if (hInt.get() >= 2 && oInt.get() >= 1) {
                conO.signal();
                conH.signal();
                oInt.decrementAndGet();
                hInt.decrementAndGet();
                hInt.decrementAndGet();

            } else {
                conH.await();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();

        }finally{
            reentrantLock.unlock();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        reentrantLock.lock();
        try{
            oInt.incrementAndGet();
            if (hInt.get() >= 2 && oInt.get() >= 1) {
                conH.signal();
                conH.signal();
                oInt.decrementAndGet();
                hInt.decrementAndGet();
                hInt.decrementAndGet();
            } else {
                conO.await();
            }
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();

        }finally{
            reentrantLock.unlock();
        }

    }
}