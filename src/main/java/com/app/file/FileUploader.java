package com.app.file;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunitc on 8/9/17.
 */
@RestController
@RequestMapping(path = "upload")
public class FileUploader {

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            path = "/one"
    )
    public ResponseEntity uploadFileOne(MultipartFile file) {

        try
        {
            InputStream inputStream = file.getInputStream();
            List<String> lines = readFile(inputStream);
            return ResponseEntity.ok(lines);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error occured : " + e.getMessage());
        }
    }

    private List<String> readFile(InputStream inputStream) throws IOException {
        List<String> lines = new ArrayList<String>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }


        } catch (Exception e) {
            System.out.println("Error occured reading file. Error = " + e.getMessage());
        } finally {
            bufferedReader.close();
        }
        return lines;
    }


    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            path = "/two"
    )
    public ResponseEntity uploadFileTwo(File file) {

        try
        {
            InputStream inputStream = new FileInputStream(file);
            List<String> lines = readFile(inputStream);
            return ResponseEntity.ok(lines);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error occured : " + e.getMessage());
        }
    }
}
