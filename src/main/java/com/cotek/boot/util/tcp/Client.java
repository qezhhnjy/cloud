package com.cotek.boot.util.tcp;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @author fuzy
 * create time 19-1-16-上午9:43
 */
@Slf4j
public class Client {

    public static final String IP = "localhost";
    public static final int PORT = 9999;
    private static Socket socket;

    private static boolean isClosed() {
        try{
            if (socket == null) {
                socket = new Socket(IP, PORT);
            }
            socket.sendUrgentData(0);
            return false;
        } catch (IOException e) {
            log.info(e.getMessage());
            return true;
        }
    }

    public static Socket getSocket() {
        try {
            if (isClosed()) {
                socket = new Socket(IP, PORT);
                log.info(String.valueOf(socket));
            }
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return socket;
    }

    public static void comm() {
        try {
            Socket socket = getSocket();
            if (socket == null) return;
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            output.write("fuzy".getBytes());
            output.flush();
            log.info("send success");
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            comm();
            TimeUnit.SECONDS.sleep(3);
        }
    }

}
