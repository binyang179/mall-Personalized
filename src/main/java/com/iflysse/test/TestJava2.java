package com.iflysse.test;

public class TestJava2 {
    public static void main(String[] args) {
//        System.out.println(test(2));
        System.out.println(getV());
    }

    public static int test(int code){
        int i = 0;
        int j = 100;
        System.out.println(j / i);
        try {
            if (code == 1){
                i = 100;
            }
            else if (code == 2){
                i = 200;
                throw new RuntimeException("出错了");
            }
            else{
                i = 300;
            }
            System.out.println(i);
            return i;
        }catch(RuntimeException e){
            i = 400;
            return i;
        }finally{
            i = 500;
            System.out.println(i);
        }
    }

    public static int getV() {
        try {
            return 1;
        }finally {
            return 2;
        }
    }

    public static int divide(int i,int  j){
        return i / j ; // j = 0;
    }
}
