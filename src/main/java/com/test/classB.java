package com.test;
import com.test.classA;

public class classB extends classA{
    public void test() {
        System.out.println("class B");
    }

    public static void main(String[] args) {
        classB object = new classB();
        object.test();
        object.print();
    }
}
