package com.gavin.kotlinlearner.util;

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-06-12
 * Time: 13:29
 */
public class HelloWorld {

    protected static final String TAG = "HelloWorld";
    public static final String HELLO_WORLD = "HelloWorld!";

    private boolean isFirstCreated = false;

    public static void main(String[] args) {
        System.out.println("输出测试文本：" + HELLO_WORLD);
    }

    public void setIsFirstCreated(boolean check) {
        this.isFirstCreated = check;
    }

    public boolean isFirstCreated() {
        return isFirstCreated;
    }
}
