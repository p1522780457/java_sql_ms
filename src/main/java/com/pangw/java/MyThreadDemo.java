package com.pangw.java;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: pangw
 * @Date: 2018/7/30 14:09
 * @Description: thread synchronized lock 整理
 * <p>
 * https://blog.csdn.net/u012403290/article/details/64910926?locationNum=11&fps=1
 */
public class MyThreadDemo {
    public static void main(String[] args) {
        System.out.println("fsdfdsfds");

        Thread myt = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }, "fdsfd");
        /**
         *  new Thread() 所有初始化 都走到一个方法上
         *
         *  private void init(ThreadGroup g, Runnable target, String name,long stackSize) {
         *  init(g, target, name, stackSize, null, true);
         *  }
         */


        /**
         * myt.run();  调用该方法直接执行线程的run()方法，但是线程调用start()方法时也会运行run()方法，区别就是一个是由线程调度运行run()方法，一个是直接调用了线程中的run()方法！
         *
         * myt.start();  调用该方法开始执行该线程
         *
         * myt.stop();  调用该方法强制结束该线程执行
         *
         * myt.join();  调用该方法等待该线程结束
         *
         * Thread.sleep(); 调用该方法该线程进入等待
         *
         */

        /**
         * Object.wait();
         * Object.notify();
         *
         * 要注意，其实wait()与notify()方法是Object的方法，不是Thread的方法！！同时，wait()与notify()会配合使用，分别表示线程挂起和线程恢复。
         */

        /**
         * wait() 和 sleep() 区别
         *
         *
         *
         *
         */

        /**
         * 线程总共有5大状态:
         * 新建状态：新建线程对象，并没有调用start()方法之前
         * 就绪状态：调用start()方法之后线程就进入就绪状态，但是并不是说只要调用start()方法线程就马上变为当前线程，在变为当前线程之前都是为就绪状态。值得一提的是，线程在睡眠和挂起中恢复的时候也会进入就绪状态哦。
         * 运行状态：线程被设置为当前线程，开始执行run()方法。就是线程进入运行状态
         * 阻塞状态：线程被暂停，比如说调用sleep()方法后线程就进入阻塞状态
         * 死亡状态：线程执行结束
         */


        /**
         * lock();
         */
        Lock mylock = new ReentrantLock();


        /**
         mylock.lock();
         mylock.tryLock();
         mylock.unlock();
         mylock.tryLock(12, TimeUnit.SECONDS);
         mylock.lockInterruptibly();
         */


        /**
         *
         *
         *
         *
         */

    }

}
