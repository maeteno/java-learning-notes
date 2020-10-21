package com.maeteno.study.concurrent.example1;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class RunnableImplTest {

    @Test
    public void main() {
        RunnableImpl r = new RunnableImpl("Demo-1");

        Thread t = new Thread(r);
        t.start();

        r.setName("Update name");
        Thread t2 = new Thread(r);
        t2.start();

        Thread t3 = new Thread(() -> {
            int index = 0;
            while (index++ < 10) {
                System.out.println("Lambda => Index:" + index);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t3.start();

        log.info("Main Class End");

        Assert.assertTrue(true);
    }
}