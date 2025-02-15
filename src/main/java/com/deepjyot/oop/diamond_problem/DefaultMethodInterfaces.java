package com.deepjyot.oop.diamond_problem;

interface InterfaceA {
    default void show() {
        System.out.println("InterfaceA's show()");
    }
}

interface InterfaceB {
    default void show2() {
        System.out.println("InterfaceB's show()");
    }
}

class MyClass implements InterfaceA, InterfaceB {

}

public class DefaultMethodInterfaces {

    public static void main(String[] args) {
        MyClass obj = new MyClass();
        obj.show();  // Output: "Resolved show() in MyClass"
    }
}
