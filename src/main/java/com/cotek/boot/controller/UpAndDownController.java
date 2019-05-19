package com.cotek.boot.controller;

import com.cotek.boot.util.IOUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.zip.ZipOutputStream;

/**
 * @author qezhhnjy
 */
@RestController
public class UpAndDownController {

    @PostMapping("/up")
    public String up(@RequestParam("FILE") MultipartFile file) throws IOException {
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File("E:\\test", file.getOriginalFilename()));
//        ResponseEntity.ok(new DefaultResourceLoader().getResource("FILE:E:\\women.jpg"));
        return "success";
    }

    @PostMapping("/multi_up")
    public String multiUp(@RequestParam("FILE") MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File("E:\\test", file.getOriginalFilename()));
        }
//        ResponseEntity.ok(new DefaultResourceLoader().getResource("FILE:E:\\women.jpg"));
        return "success";
    }

    @PostMapping("/down")
    public ResponseEntity<FileSystemResource> down(String fileName) {
        File file = new File("E:\\test", fileName.split("\\\\")[2]);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new FileSystemResource(file));
    }

    @PostMapping("/multi_down")
    public ResponseEntity<FileSystemResource> multiDown() {
        File file = new File("E:\\test\\multi.zip");
        if (file.exists()) {
            file.delete();
        }

        Collection<File> files = FileUtils.listFiles(new File("E:\\test"), new String[]{"jpg", "gif", "png"}, false);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(file))) {
            IOUtil.zipFile(files, zipOut);
        } catch (IOException e) {
            e.printStackTrace();
        }


        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        FileSystemResource source = new FileSystemResource(file);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(source);

    }
}
