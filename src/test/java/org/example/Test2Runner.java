package org.example;

import org.testng.TestNG;

public class Test2Runner {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        testng.setTestSuites(java.util.Arrays.asList("test2-only.xml"));
        testng.run();
    }
}