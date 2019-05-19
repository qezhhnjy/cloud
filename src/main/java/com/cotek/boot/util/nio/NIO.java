package com.cotek.boot.util.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * @author fuzy
 * create time 18-11-16-下午9:05
 */
@Slf4j
public class NIO {

    public static final String FILE = "/home/fuzy/expect.sh";

    public static void main(String[] args) throws IOException {
//        channel();

//        gather("wo men shi shen mo ne fuzy jiu zhe yang ba !!");
//        scatter();

//        transfer();

        new Thread(() -> {
            try {
                select();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                client();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public static void channel() throws IOException {

        ByteBuffer buff = ByteBuffer.allocate(1024);

        FileInputStream input = new FileInputStream(FILE);
        FileChannel fileChannel = input.getChannel();

        int read = fileChannel.read(buff);

        log.info(String.valueOf(read));

        log.info(Arrays.toString(buff.array()));

        FileOutputStream output = new FileOutputStream(FILE);
        FileChannel channel = output.getChannel();

        buff.flip();
        channel.write(buff);

/*
        DatagramChannel dataChannel = DatagramChannel.open();
        dataChannel.close();

        SocketChannel localhost = SocketChannel.open(new InetSocketAddress("localhost", 8081));
        localhost.connect(new InetSocketAddress("localhost", 8082));

        localhost.bind(new InetSocketAddress("localhost", 8818));

        SocketChannel so = SocketChannel.open(new InetSocketAddress("localhost", 6621));

        so.close();
*/

    }

    /**
     * 聚集写入
     */
    public static void gather(String data) throws IOException {
        ByteBuffer buff1 = ByteBuffer.allocate(8);
        char[] chars = data.toCharArray();
        ByteBuffer buff2 = ByteBuffer.allocate(chars.length * 2);
        buff1.asIntBuffer().put(251);
        buff2.asCharBuffer().put(data);

        log.info(String.valueOf(buff2.limit()));

        log.info(String.valueOf(buff2.capacity()));

        log.info(Arrays.toString(buff2.array()));

        FileChannel channel = new FileOutputStream(FILE).getChannel();

        channel.write(new ByteBuffer[]{buff1, buff2});

    }

    /**
     * 分散读取
     */
    public static void scatter() throws IOException {
        ByteBuffer buff1 = ByteBuffer.allocate(8);
        ByteBuffer buff2 = ByteBuffer.allocate(100);

        FileChannel channel = new FileInputStream(FILE).getChannel();

        channel.read(new ByteBuffer[]{buff1, buff2});

        buff1.flip();
        buff2.flip();

        log.info(String.valueOf(buff1.asIntBuffer().get()));
        log.info(buff2.asCharBuffer().toString());
    }

    public static void transfer() throws IOException {
        String path = System.getProperty("user.dir");

        File file1 = new File(path + "/1.txt");
        File file2 = new File(path + "/2.txt");
        File file3 = new File(path + "/3.txt");
        File file4 = new File(path + "/4.txt");
        File combine = new File(path + "/combine.txt");

        ByteBuffer buff = ByteBuffer.allocate(20);

        FileChannel channel = new FileOutputStream(file1).getChannel();
        buff.put("1.txt".getBytes());
        buff.flip();
        channel.write(buff);

        buff.clear();
        buff.put("2.txt".getBytes());
        buff.flip();
        new FileOutputStream(file2).getChannel().write(buff);

        buff.clear();
        buff.put("3.txt".getBytes());
        buff.flip();
        new FileOutputStream(file3).getChannel().write(buff);

        buff.clear();
        buff.put("4.txt".getBytes());
        buff.flip();
        new FileOutputStream(file4).getChannel().write(buff);

        FileChannel channelCombine = new FileOutputStream(combine).getChannel();

        FileChannel channel1 = new FileInputStream(file1).getChannel();
        channel1.transferTo(0, channel1.size(), channelCombine);

        FileChannel channel2 = new FileInputStream(file2).getChannel();
        channel2.transferTo(0, channel2.size(), channelCombine);

        FileChannel channel3 = new FileInputStream(file3).getChannel();
        FileChannel channel4 = new FileInputStream(file4).getChannel();


        channelCombine.transferFrom(channel3, channelCombine.size(), channel3.size());
        channelCombine.transferFrom(channel4, channelCombine.size(), channel4.size());

    }

    public static void select() throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel open = ServerSocketChannel.open();

        open.bind(new InetSocketAddress("localhost", 8311));

        open.configureBlocking(false);
        int ops = open.validOps();
        open.register(selector, ops, null);
        for (; ; ) {
            log.info("waiting for the select operation");
            int select = selector.select();
            log.info("the number of selected keys are : " + select);

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                if (next.isAcceptable()) {
                    SocketChannel accept = open.accept();
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                    log.info("the new connection is accepted from the client : " + accept);
                } else if (next.isReadable()) {
                    SocketChannel channel = (SocketChannel) next.channel();
                    ByteBuffer buff = ByteBuffer.allocate(256);
                    channel.read(buff);
                    buff.flip();
                    log.info("message read from client : " + new String(buff.array()).trim());
                    ByteBuffer out = ByteBuffer.allocate("bye bye".getBytes().length);
                    out.put("bye bye".getBytes());
                    out.flip();
                    channel.write(out);

                }
                iterator.remove();
            }

        }
    }

    public static void client() throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        long start = System.currentTimeMillis();
        SocketChannel localhost = SocketChannel.open(new InetSocketAddress("localhost", 8311));
        ByteBuffer buff = ByteBuffer.allocate(100);
        int count = 0;
        while (count < 100000) {
            buff.clear();
            buff.put(("wo men shi shen mo " + ((InetSocketAddress) localhost.getLocalAddress()).getPort() + " - " + (++count)).getBytes());
            buff.flip();
            while (buff.hasRemaining()) localhost.write(buff);
            ByteBuffer allocate = ByteBuffer.allocate(20);
            localhost.read(allocate);
            allocate.flip();
            byte[] b = new byte[allocate.limit()];
            allocate.get(b);
            log.info("message from server : " + new String(b));
            TimeUnit.MILLISECONDS.sleep(200);
        }
        log.info(String.valueOf(System.currentTimeMillis() - start));
    }
}
