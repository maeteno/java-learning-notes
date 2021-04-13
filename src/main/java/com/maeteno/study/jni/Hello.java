package com.maeteno.study.jni;

public class Hello {

    static {
        try {
            // 此处即为本地方法所在链接库名
            System.loadLibrary("hello");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Cannot load hello library:\n" + e.toString());
        }
    }

    public native void SayHello(String strName);
}
