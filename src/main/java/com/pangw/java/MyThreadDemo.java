package com.pangw.java;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: pangw
 * @Date: 2018/7/30 14:09
 * @Description: thread synchronized lock 整理
 * <p>
 * https://blog.csdn.net/u012403290/article/details/64910926?locationNum=11&fps=1
 * <p>
 * <p>
 * new Thread() 所有初始化 都走到一个方法上
 * <p>
 * private void init(ThreadGroup g, Runnable target, String name,long stackSize) {
 * init(g, target, name, stackSize, null, true);}
 * <p>
 * <p>
 * myt.run();  调用该方法直接执行线程的run()方法，但是线程调用start()方法时也会运行run()方法，区别就是一个是由线程调度运行run()方法，一个是直接调用了线程中的run()方法！
 * <p>
 * myt.start();  调用该方法开始执行该线程
 * <p>
 * myt.stop();  调用该方法强制结束该线程执行
 * <p>
 * myt.join();  调用该方法等待该线程结束
 * <p>
 * Thread.sleep(); 调用该方法该线程进入等待
 * <p>
 * <p>
 * <p>
 * Object.wait();
 * Object.notify();
 * <p>
 * 要注意，其实wait()与notify()方法是Object的方法，不是Thread的方法！！同时，wait()与notify()会配合使用，分别表示线程挂起和线程恢复。
 * <p>
 * wait() 和 sleep() 区别
 * <p>
 * <p>
 * <p>
 * 线程总共有5大状态:
 * 新建状态：新建线程对象，并没有调用start()方法之前
 * 就绪状态：调用start()方法之后线程就进入就绪状态，但是并不是说只要调用start()方法线程就马上变为当前线程，在变为当前线程之前都是为就绪状态。
 * 值得一提的是，线程在睡眠和挂起中恢复的时候也会进入就绪状态哦。
 * 运行状态：线程被设置为当前线程，开始执行run()方法。就是线程进入运行状态
 * 阻塞状态：线程被暂停，比如说调用sleep()方法后线程就进入阻塞状态
 * 死亡状态：线程执行结束
 * <p>
 * lock();
 * <p>
 * Lock mylock = new ReentrantLock();
 * <p>
 * mylock.lock();获取锁，如果锁被暂用则一直等待
 * mylock.tryLock();注意返回类型是boolean，如果获取锁的时候锁被占用就返回false，否则返回true
 * mylock.unlock();释放锁
 * mylock.tryLock(12, TimeUnit.SECONDS);比起tryLock()就是给了一个时间期限，保证等待参数时间
 * mylock.lockInterruptibly();用该锁的获得方式，如果线程在获取锁的阶段进入了等待，那么可以中断此线程，先去做别的事
 * <p>
 * 备注：
 * synchronized 和 lock 优先选择 synchronized 因为 synchronized 是jvm 的孩子
 * 开始synchronized 的性能赶不上lock 最后赶上了
 */
public class MyThreadDemo {
    public static void main(String[] args) {


        /**
         * lock 测试demo
         */
        final MyThreadDemo demo = new MyThreadDemo();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo.method(Thread.currentThread());
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo.method(Thread.currentThread());
            }
        }, "t2");

        t1.start();
        t2.start();
    }

    /**
     * new ReentrantLock(false):默认 非公平锁
     * new ReentrantLock(true) 公平锁
     */
    private Lock lock = new ReentrantLock();

    private void method(Thread thread) {


        /*System.out.println("线程名ssss：" + thread.getName() + "获得了锁");
        lock.lock();//如果锁被暂用 则一直等待

        try {

            System.out.println("线程名：" + thread.getName() + "获得了锁");
        } catch (Exception e) {

        } finally {
            System.out.println("线程名：" + thread.getName() + "释放了锁");
            lock.unlock();
        }*/


        try {
            if (lock.tryLock(4, TimeUnit.SECONDS)) {
                try {
                    System.out.println("线程名" + thread.getName() + "获得了锁");
                } catch (Exception e) {

                } finally {
                    System.out.println("线程名" + thread.getName() + "释放了锁");
                    lock.unlock();
                }
            } else {
                System.out.println("我是" + Thread.currentThread().getName() + "有人占着锁，我就不要啦");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
