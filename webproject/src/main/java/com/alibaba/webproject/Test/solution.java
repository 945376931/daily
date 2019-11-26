package com.alibaba.webproject.Test;

public class solution {
        int q=0;
        public void merge(int[][] intervals){
            //合并时间区间,一次for循环
            int[][] array={{1,3},{2,6},{8,10},{15,18}};
            for(int i=0;i<array.length;i++){
                if( array[i][1]>array[i+1][0]){
                    //如果第一行的末尾时区大于第二个的开始时区，则进行判断
                    array[i+1][0]=array[i][0];  //此时合并
                    q++;
                }
            }
            for(int i=q;i<array.length;i++){
                System.out.println(array[i]);
            }
        }
    }


