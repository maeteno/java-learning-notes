package com.maeteno.study.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class DownloadImg2 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        String path1 = "/pics/adidasneo/2019/101231471/101231471_01_f.png?2";
        String path2 = "/pics/adidasneo/2019/101231471/101231471_01__9_f.png?2";

        int index = 0;
        List<Image> images = new ArrayList<>();
        while (index++ < 50) {
            String path = path1;
//            if(index % 5 ==0){
//                path = path2;
//            }

            images.add(Image.builder()
                    .path(path)
                    .name("demo-" + index + ".png")
                    .build());
        }

        try (Download download = new Download("http://i1.ygimg.cn", 5)) {
            List<ByteArrayOutputStream> list = download.down(images);
            AtomicInteger i = new AtomicInteger();
            list.forEach(item -> {
                try (FileOutputStream out = new FileOutputStream("demo-" + i.getAndIncrement() + ".png")) {
                    out.write(item.toByteArray());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        long time = System.currentTimeMillis() - start;

        log.info("Time: {} s", time / 1000);
    }
}
