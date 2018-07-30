package com.pangw.java;

/**
 * @Auther: pangw
 * @Date: 2018/7/30 18:50
 * @Description: synchronized 的理解和学习
 * https://blog.csdn.net/javazejian/article/details/72828483
 * synchronized 是一个关键字  而lock 是一个对象
 * <p>
 * 在 Java 中，关键字 synchronized可以保证在同一个时刻，只有一个线程可以执行某个方法或者某个代码块(主要是对方法或者代码块中存在共享数据的操作)，
 * 同时我们还应该注意到synchronized另外一个重要的作用，synchronized可保证一个线程的变化(主要是共享数据的变化)被其他线程所看到（保证可见性，完全可以替代Volatile功能）
 * <p>
 * ynchronized关键字最主要有以下3种应用方式，下面分别介绍
 * 1、修饰实例方法，作用于当前实例加锁，进入同步代码前要获得当前实例的锁
 * 2、修饰静态方法，作用于当前类对象加锁，进入同步代码前要获得当前类对象的锁
 * 3、修饰代码块，指定加锁对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁。
 */
public class MySynchronizedDemo {
    static int i = 0;
    static AccountingSync3 instance = new AccountingSync3();
    static AccountingSync3 instance2 = new AccountingSync3();

    public static void main(String[] args) throws InterruptedException {
//        typeMethod1();
//        typeMethod2();
        typeMethod3();

    }

    /**
     * 同步代码块
     * 除了使用关键字修饰实例方法和静态方法外，还可以使用同步代码块，在某些情况下，我们编写的方法体可能比较大，同时存在一些比较耗时的操作，
     * 而需要同步的代码又只有一小部分，如果直接对整个方法进行同步操作，可能会得不偿失，此时我们可以使用同步代码块的方式对需要同步的代码进行包裹，
     * 这样就无需对整个方法进行同步操作了，同步代码块的
     *
     */
    private static void typeMethod3() throws InterruptedException {

        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance2);

        t1.start();
        t2.start();

        //join含义:当前线程A等待thread线程终止之后才能从thread.join()返回
        t1.join();
        t2.join();
        System.out.println(i);
    }

    /**
     * 修饰静态方法
     *
     * 当synchronized作用于静态方法时，其锁就是当前类的class对象锁。由于静态成员不专属于任何一个实例对象，是类成员，
     * 因此通过class对象锁可以控制静态 成员的并发操作。需要注意的是如果一个线程A调用一个实例对象的非static synchronized方法，
     * 而线程B需要调用这个实例对象所属类的静态 synchronized方法，是允许的，不会发生互斥现象，因为访问静态 synchronized 方法占用的锁是当前类的class对象，
     * 而访问非静态 synchronized 方法占用的锁是当前实例对象锁，
     *
     */
    private static void typeMethod2() throws InterruptedException {
        AccountingSync2 instance = new AccountingSync2();
        AccountingSync2 instance1 = new AccountingSync2();
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance1);

        t1.start();
        t2.start();

        //join含义:当前线程A等待thread线程终止之后才能从thread.join()返回
        t1.join();
        t2.join();
        System.out.println(i);
    }

    /**
     * 修饰实例方法
     *
     * 上述代码中，我们开启两个线程操作同一个共享资源即变量i，由于i++;操作并不具备原子性，该操作是先读取值，然后写回一个新值，相当于原来的值加上1，分两步完成，
     * 如果第二个线程在第一个线程读取旧值和写回新值期间读取i的域值，那么第二个线程就会与第一个线程一起看到同一个值，并执行相同值的加1操作，这也就造成了线程安全失败，
     * 因此对于increase方法必须使用synchronized修饰，以便保证线程安全。此时我们应该注意到synchronized修饰的是实例方法increase，在这样的情况下，
     * 当前线程的锁便是实例对象instance，注意Java中的线程同步锁可以是任意对象。从代码执行结果来看确实是正确的，倘若我们没有使用synchronized关键字，
     * 其最终输出结果就很可能小于2000000，这便是synchronized关键字的作用。这里我们还需要意识到，当一个线程正在访问一个对象的 synchronized 实例方法，
     * 那么其他线程不能访问该对象的其他 synchronized 方法，毕竟一个对象只有一把锁，当一个线程获取了该对象的锁之后，其他线程无法获取该对象的锁，所以无法访问该对象的其他synchronized实例方法，
     * 但是其他线程还是可以访问该实例对象的其他非synchronized方法，当然如果是一个线程 A 需要访问实例对象 obj1 的 synchronized 方法 f1(当前对象锁是obj1)，
     * 另一个线程 B 需要访问实例对象 obj2 的 synchronized 方法 f2(当前对象锁是obj2)，
     * 这样是允许的，因为两个实例对象锁并不同相同，此时如果两个线程操作数据并非共享的，线程安全是有保障的，遗憾的是如果两个线程操作的是共享数据，那么线程安全就有可能无法保证了，如下代码将演示出该现象
     *
     */
    private static void typeMethod1() throws InterruptedException {
        AccountingSync1 instance = new AccountingSync1();
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        t1.start();
        t2.start();

        //join含义:当前线程A等待thread线程终止之后才能从thread.join()返回
        t1.join();
        t2.join();
        System.out.println(i);

    }

    static class AccountingSync1 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                incresse();
            }
        }

        public synchronized void incresse() {
            i++;
        }

    }

    static class AccountingSync2 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                incresse();
            }
        }

        public static synchronized void incresse() {
            i++;
        }

    }

    static class AccountingSync3 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                incresse();
            }
        }

        public void incresse() {
            synchronized (AccountingSync3.class) {
                i++;
            }
        }
    }

}
