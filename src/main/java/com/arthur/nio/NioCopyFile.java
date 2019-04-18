package com.arthur.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by xusheng on 2019/4/18.
 */
public class NioCopyFile {
    public static void main(String[] args) throws IOException {
        nioCopyFile("D:\\ideaWorkspace18\\Concurrent\\src\\main\\java\\com\\arthur\\nio\\file.txt",
                "D:\\ideaWorkspace18\\Concurrent\\src\\main\\java\\com\\arthur\\nio\\fileCopy.txt");
        //bufferTest();
    }

    private static void nioCopyFile(String resource, String destion) throws IOException {
        FileInputStream fis = new FileInputStream(resource);
        FileOutputStream fos = new FileOutputStream(destion);
        FileChannel readChannel = fis.getChannel();
        FileChannel writeChannel = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(20);

        while (readChannel.read(buffer) != -1) {
            buffer.flip();
            writeChannel.write(buffer);
            buffer.clear();
        }

        readChannel.close();
        writeChannel.close();
    }

    private static void bufferTest() {
        ByteBuffer buffer = ByteBuffer.allocate(15);
        printInfo(buffer);

        for (int i = 0; i < 10; i++) {
            buffer.put((byte) i);
        }
        printInfo(buffer);

        buffer.flip();//重置position
        printInfo(buffer);

        for (int i = 0; i < 5; i++) {
            System.out.print(buffer.get());
        }

        System.out.println();
        printInfo(buffer);
        buffer.flip();
        printInfo(buffer);

    }

    private static void printInfo(ByteBuffer buffer) {
        System.out.println("limit = " + buffer.limit() +
                " capacity = " + buffer.capacity() +
                " position = " + buffer.position());
    }


}
