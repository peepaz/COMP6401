package com.ptasdevz.practice;

public class ExceptionTest {

    public static void main (String[]  args) throws Exception {

        exceptionTest(0);
    }

    public static void exceptionTest(int a) throws Exception{

         if (a == 1) throw new Exception("a== 1");

        try {

            int b = 45/a;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
