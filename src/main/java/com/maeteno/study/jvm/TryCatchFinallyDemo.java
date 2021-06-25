package com.maeteno.study.jvm;

import lombok.SneakyThrows;

import java.io.IOException;

public class TryCatchFinallyDemo {
    @SneakyThrows
    public static Integer foo() {
        try {
            ThrowEx("1");
            return 1;
        } catch (RuntimeException e) {
            return 2;
        } catch (Exception e) {
            System.out.println("Exception");
            ThrowEx("Exception");
            return 3;
        } finally {
            System.out.println("finally");
            ThrowEx("finally");
            return 4;
        }
    }

    public static void ThrowEx(String msg) throws IOException {
        System.out.println(msg);
        throw new IOException(msg);
    }

    public static void main(String[] args) {
        Integer foo = foo();
        System.out.println(foo);
    }
}
