package com.cotek.boot.util.tcp;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author fuzy
 * create time 19-1-16-上午9:43
 */
@Slf4j
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(Client.PORT);
        while (true) {
            Socket accept = server.accept();
            InputStream input = accept.getInputStream();
            OutputStream output = accept.getOutputStream();
            int count = 0;
            while (count == 0) {
                count = input.available();
            }
            byte[] b = new byte[input.available()];
            while (input.read(b) != -1) {
                log.info(accept.getPort() + " - " + new String(b));
            }
        }
    }
}
