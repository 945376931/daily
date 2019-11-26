package com.alibaba.webproject.Controller;

public class test46 {

    public static void main(String args[]){

       int sum=1000000000;
       long startTime=System.currentTimeMillis();
        while(true){
            if(sum<=0) {
                long  endtime=System.currentTimeMillis();
                System.out.println("1结束时间为"+endtime);
                System.out.println(endtime-startTime+"ms");
                break;
            }
            sum--;
        }
    }


//        int sum = 0;
//        int i = 2,j = 1;
//        long startTime = System.currentTimeMillis();   //开始时间戳“startTime”
//
//        for(i = 2; i <= 30000; i++)
//        {
//            for(j = 1; j < i; j++)
//            {
//                if(i%j == 0)
//                    sum = sum+j;
//            }
//            if(sum == i)
//                System.out.println(" " + sum);
//            sum = 0;
//        }
//
//        long endTime = System.currentTimeMillis();   //开始时间戳“endTime”
//        System.out.println("运行时间:" + (endTime - startTime) + "ms");   //输出程序执行所花时间
//    }
}