package ua.com.csltd.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by valeriy_solyanik on 22.03.2017.
 */
public class ProducerConsumer {
    public static List<Integer> integerList = new ArrayList<>();
    public static AtomicBoolean isProduced = new AtomicBoolean(false);

    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        producer.start();
        consumer.start();

    }

    private static class Consumer extends Thread {
        @Override
        public void run() {
            int count = 0;
            while (true) {
                synchronized (integerList) {
                    while (!isProduced.get()) {
                        try {
                            integerList.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(1000l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Consume = " + integerList.get(count++));
                    isProduced.set(false);
                    integerList.notify();
                }
            }
        }
    }

    private static class Producer extends Thread {
        @Override
        public void run() {
            int count = 0;
            while (true) {
                synchronized (integerList) {
                    while (isProduced.get()) {
                        try {
                            integerList.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        Thread.sleep(1000l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    integerList.add(count);//fee
                    System.out.println("Produce  = " + count++);
                    isProduced.set(true);
                    integerList.notify();
                }
            }
        }
    }
}
