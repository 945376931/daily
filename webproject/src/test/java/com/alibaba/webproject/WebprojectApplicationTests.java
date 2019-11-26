//package com.alibaba.webproject;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class WebprojectApplicationTests {
//
//    @Test
//    void contextLoads() {
//
//        Solution(2,"helll");
//
//    }
//
//    @Test
//    public void Solution(int n, String s1) {
//        for (int i = 0; i < s1.length(); i++) {
//            if (s1.charAt(i) == s1.charAt(i + 1)) { //  有多种情况  hello,helloo,helllooo
//                if (s1.charAt(i + 1) == s1.charAt(i + 2)) { //判断helll的情况
//                    System.out.println("helll,去除掉带三个L");
//                    s1.replace(s1.charAt(i+2),' ');
//                    System.out.println(s1);
//                }
//
//            }
//        }
//
//    }
//}
//
//
