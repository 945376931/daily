package com.alibaba.webproject.Test;

public class RunableTest  implements  Runnable{

        private Thread t;
        private String threadName;
        private static int a=100;
        private static   int n=20;

    RunableTest( String name) {
            threadName = name;
            System.out.println("Creating " +  threadName );
        }
//        public void run() {
//            System.out.println("Running " +  threadName );
//            for( int i = 4; i > 0; i--) {
//                a++;
//                System.out.println(a);
//                // 让线程睡眠一会
////                    Thread.sleep(50);
//            }
//            System.out.println("Thread " +  threadName + " exiting.");
//        }
//    public void run() {
//            for ( i = 0; i < 100; i++) {
//                synchronized (this) {
//                System.out.println(Thread.currentThread().getName() + "抢到第" + i + "张票");
//            }
//
//        }
//    }
    public  void  run() {
//        long startTime = System.currentTimeMillis();   //开始时间戳“startTime”
        while (true) {
//            synchronized (this) {
                if (n < 0) {
                    long endTime = System.currentTimeMillis();   //开始时间戳“endTime”
//                    System.out.println("运行时间:" + (endTime - TestThread.starttime) + "ms");   //输出程序执行所花时间
                    System.out.println("结束时间" + endTime);
                    break;
                }
                n--;
                System.out.println(Thread.currentThread().getName() + "抢到第" + n + "张票");
            }
            //        long endTime = System.currentTimeMillis();   //开始时间戳“endTime”
//        System.out.println("运行时间:" + (endTime - startTime) + "ms");   //输出程序执行所花时间
        }
//    }
        public void start () {            //先执行start 在执行run方法
            System.out.println("Starting " +  threadName );
            if (t == null) {
                t = new Thread (this, threadName);
                t.start ();
            }
        }
}

