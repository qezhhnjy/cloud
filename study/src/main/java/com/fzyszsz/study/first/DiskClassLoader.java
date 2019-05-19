package com.fzyszsz.study.first;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author fuzy
 * create time 19-3-10-下午8:56
 */
public class DiskClassLoader extends ClassLoader {

    private String mLibPath;

    public DiskClassLoader(String path) {
        mLibPath = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = getFileName(name);
        File file = new File(mLibPath, fileName);
        try {
            FileInputStream is = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len;
            while ((len = is.read()) != -1) {
                bos.write(len ^ Encrypt.SALT);
            }
            byte[] data = bos.toByteArray();
            is.close();
            bos.close();
            return defineClass(name, data, 0, data.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    private static String getFileName(String name) {
        int index = name.lastIndexOf(".");
        if (index == -1) {
            return name + ".classfu";
        } else {
            return name.substring(index + 1) + ".classfu";
        }
    }

    public static void main(String[] args) {
        DiskClassLoader disk = new DiskClassLoader("/home/fuzy/Documents");
        try {
            Class c = disk.loadClass("Test");
            if (c != null) {
                try {
                    Object obj = c.newInstance();
                    Method method = c.getDeclaredMethod("say", null);
                    method.invoke(obj, null);
                } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Encrypt {
    public static final int SALT = Integer.MAX_VALUE;

    public static void encode(String path) {
        try {
            InputStream input = Files.newInputStream(Paths.get(path));
            OutputStream output = Files.newOutputStream(Paths.get(path + "fu"));
            int b;
            while ((b = input.read()) != -1) {
                output.write(b ^ SALT);
            }
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] decode(String path) {
        try {
            InputStream input = Files.newInputStream(Paths.get(path));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int b;
            while ((b = input.read()) != -1) {
                out.write(b ^ SALT);
            }
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
