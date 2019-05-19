package com.cotek.boot.util;

import java.io.*;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * create by qezhhnjy at 2018/6/30-17:14
 */
public class IOUtil {

    public static void zipFile(Collection<File> files, ZipOutputStream out) {

        for (File file : files) {
            zipFile(file, out);
        }
    }

    public static void zipFile(File file, ZipOutputStream out) {
        if (file.exists() || file.isFile()) {
            try (InputStream in = new BufferedInputStream(new FileInputStream(file))) {
                ZipEntry zipEntry = new ZipEntry(file.getName());
                out.putNextEntry(zipEntry);

                final int MAX_BYTE = 1024 * 1024;

                byte[] temp = new byte[MAX_BYTE];

                int position;

                while ((position = in.read(temp, 0, temp.length)) != -1) {
                    out.write(temp, 0, position);
                }

                out.closeEntry();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 1
        System.out.println(Math.floor(1.25));
    }

}
