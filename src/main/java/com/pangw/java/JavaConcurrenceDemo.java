package com.pangw.java;

import java.util.concurrent.*;

/**
 * @Auther: pangw
 * @Date: 2018/7/27 18:11
 * @Description:
 *
 * 假设任务队列没有大小限制：
 * 1、如果线程数量<=核心线程数量，那么直接启动一个核心线程来执行任务，不会放入队列中
 * 2、如果线程数量>核心线程数，但<=最大线程数，并且任务队列是LinkedBlockingDeque的时候，超过核心线程数量的任务会放在任务队列中排队
 * 3、如果线程数量>核心线程数，但<=最大线程数，并且任务队列是SynchronousQueue的时候，线程池会创建新线程执行任务，这些任务也不会被放在任务队列中。这些线程属于非核心线程，在任务完成后，闲置时间达到了超时时间就会被清除。
 * 4、如果线程数量>核心线程数，并且>最大线程数，当任务队列是LinkedBlockingDeque，会将超过核心线程的任务放在任务队列中排队。也就是当任务队列是LinkedBlockingDeque并且没有大小限制时，线程池的最大线程数设置是无效的，他的线程数最多不会超过核心线程数
 * 5、如果线程数量>核心线程数，并且>最大线程数，当任务队列是SynchronousQueue的时候，会因为线程池拒绝添加任务而抛出异常。
 *
 * 任务队列大小有限时
 * 1、当LinkedBlockingDeque塞满时，新增的任务会直接创建新线程来执行，当创建的线程数量超过最大线程数量时会抛异常
 * 2、SynchronousQueue没有数量限制。因为他根本不保持这些任务，而是直接交给线程池去执行。当任务数量超过最大线程数时会直接抛异常
 */
public class JavaConcurrenceDemo {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 所有的任务
         */
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "is runing....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        /**
         * ---先开三个---
         核心线程数：6
         线程池数：3
         队列任务数0
         ---再开三个---
         核心线程数：6
         线程池数：6
         队列任务数0
         pool-1-thread-2is runing....
         pool-1-thread-5is runing....
         pool-1-thread-6is runing....
         pool-1-thread-4is runing....
         pool-1-thread-3is runing....
         pool-1-thread-1is runing....
         ---8秒之后再来三个---
         核心线程数：6
         线程池数：6
         队列任务数0
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());


        /**
         * --先开三个---
         核心线程数：3
         线程池数：3
         队列任务数0
         ---再开三个---
         核心线程数：3
         线程池数：3
         队列任务数3
         pool-1-thread-2is runing....
         pool-1-thread-1is runing....
         pool-1-thread-3is runing....
         pool-1-thread-2is runing....
         pool-1-thread-1is runing....
         pool-1-thread-3is runing....
         ---8秒之后再来三个---
         核心线程数：3
         线程池数：3
         队列任务数0
         */
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());

        executor.execute(runnable);
        executor.execute(runnable);
        executor.execute(runnable);

        System.out.println("---先开三个---");
        System.out.println("核心线程数：" + executor.getCorePoolSize());
        System.out.println("线程池数：" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());

        executor.execute(runnable);
        executor.execute(runnable);
        executor.execute(runnable);

        System.out.println("---再开三个---");
        System.out.println("核心线程数：" + executor.getCorePoolSize());
        System.out.println("线程池数：" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());

        Thread.sleep(8000);

        System.out.println("---8秒之后---");
        System.out.println("核心线程数：" + executor.getCorePoolSize());
        System.out.println("线程池数：" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());

    }
}
