package com.gavin.kotlinlearner

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-06-12
 * Time: 13:33
 */
class HelloWorld {

    var isFirstCreated = false

    companion object {

        protected val TAG = "HelloWorld"
        val HELLO_WORLD = "HelloWorld!"

        @JvmStatic fun main(args: Array<String>) {
            println("输出测试文本：" + HELLO_WORLD)
        }
    }
}