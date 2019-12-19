package com.iflysse.test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TestJava {
    public static void main(String[] args) throws CloneNotSupportedException {
//        int i = 0;
//        boolean b = 4 < 2 & 3 > i++ ; // false   且 false 情况下 后面的表达式还会执行
//        System.out.println(i);

//        int i = 1, j = 3;   // 01  11
//        System.out.println(i & j);

        String str = "a"; // jdk1.8 里面的
//        long l = 100l;
//        switch (l) {
//            case "a":
//                System.out.println(str);
//        }
        // byte 1  short  char 2  int 4  long 8
//        short s = 1;
////        s = s + 1;   // 1  int
//        short s1 = 1 + 2; // 注意： 1 + 2  常量
//        short s2 = 1;
//        s2 += 1;  // += 做一个强制转换
//        System.out.println();

        // char 有两个字节   一个英文字母或者一个数字是一个字节  汉字是两个字节
//        int i = 2, j = 8;  // 2^3   2 << 3
        // 00000010   00001000
//        long l = 1; // 8
//        System.out.println(Integer.MAX_VALUE);

//        final int a = 4;
////        a = 5; // 不行的   引用的值不能变 （基本值，复杂数据类型 地址不变）
//        final A aa = new A();  // aa 不能够再次进行一个赋值操作
//        aa.i = 200; // 对象中的属性值是可以修改的
//        final StringBuffer sbf = new StringBuffer("abc"); // sb
//        sbf.append(4);
//        System.out.println(sbf == sbf.append(66));

//        String s1 = "abc";
//        String s2 = "abc";
//        System.out.println(s1 == s2); // true
//
//        String s3 = new String("abc");
//        String s4 = new String("abc");
//        System.out.println(s3 == s4); // false  比较的是地址
//        System.out.println(s3.equals(s4)); // true  比较的是对象属性里面的值  value属性
//
//        String s5 = s1 + s2;  // 电脑 认知当中 代码没有运行 s1 s2 变量   s1 + s2 : new 新的字符串
//        String s6 = "abcabc";
//        System.out.println(s5 == s6); // false
//
//        String s7 = "abc" + "abc"; // 电脑 认知当中 代码没有运行 “abc” 常量  s1 + s2 : 从字符串缓存区中找   创建一个
//        System.out.println(s6 == s7); // true

//        System.out.println(Math.round(11.4));    // 12 > 11
//        System.out.println(Math.round(-11.6));   // -11 > -12

//        String username = null;
//        if ("zxx".equals( username )){
//
//        }
//        int x = 1;
//        boolean b = x==1?true:false;
//        System.out.println(b);
        // 浅复制  ：  基本数据类型是可以复制的，但是复杂数据类型 是不能够复制的，原样带入
//        B b = new B();
//        B bb = (B) b.clone();
//        System.out.println(b.a);
//        System.out.println(bb.a);
////        System.out.println("bb.a.i="+bb.a.i);
//        System.out.println("bb.i="+bb.i);
//        b.a.i = 300;
//        b.i = 20;
////        System.out.println(bb.a.i);
//        System.out.println("bb.i="+bb.i);

        // 定义： 父类的类型，实现是子类的方法   此时才是多态
        C c = new D();
        c.test(); // 100
        c = new S();
        c.test(); // 200

        List list = new ArrayList<>();
        list.size();
        str = "";
        str.length();
        int[] arr = new int[100];

    }
}

class A{
    int i = 100;
    List<A> aList;
    public A(){}
}

class B implements Cloneable{
    // 构造函数： 方法名与类名相同  无返回值
    // 符合方法定义？   修饰符  返回值  方法名（参数）
    // public A() {}
    int i = 10;

    A a = new A();

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // 深复制  使用反射来做  性能   不会超过三层递归
    }
}

abstract  class C{
    int i = 10;
    abstract void test();  // 多态
    public int getI(){
        return i;
    }
}

class D extends  C{

    @Override
    void test() {
//        // Returns the runtime class of this
//        System.out.println(super.getClass());
//        System.out.println(getI());  // 多态和继承 针对的是父子类的关系
        this.i = 100;
        System.out.println(i);
    }

//    public int getI(){
//        return i;
//    }
}

class S extends C{
    int i = 200;
    @Override
    void test() {
        System.out.println(i);
    }
}

class E{
    int x = 1;
    class F{
        public void test(){
            System.out.println(x);
        }
    }
}