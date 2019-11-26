package com.alibaba.webproject.Test;

public class TestThread {

    public  static int n=500000;
    public  static int m=1000000;
    public static void main(String args[]){
        long startTime = System.currentTimeMillis(); //开始时间戳“startTime”
        System.out.println("开始时间为"+startTime);
        RunableTest R =new RunableTest("Thread-1"){
            @Override
            public  void run(){
                while(true){
                    if(n<=0) {
                      long  endtime=System.currentTimeMillis();
                        System.out.println("1结束时间为"+endtime);
                        System.out.println(endtime-startTime+"ms");
                      break;
                    }
                        n--;
                }
            }
        };
        RunableTest R1 =new RunableTest("Thread"){
            @Override
            public  void run(){
                while(true){
                    if(m<=500000){
                        long  endtime=System.currentTimeMillis();
                        System.out.println("2结束时间为"+endtime);
                        System.out.println(endtime-startTime+"ms");
                        break;
                    }
                    m--;
                }
            }
        };
        R.start();
        R1.start();
    }

}

//
//       System.out.println("运行时间:" + (endtime - startTime) + "ms");   //输出程序执行所花时间


//        RunableTest R3 = new RunableTest( "Thread-2");
//        R3.start();
