package com.app.file;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by sunitc on 4/20/17.
 */
@RestController
@RequestMapping(path = "/download")
public class FileDownloader {


    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity downloadFile() {
        String filePath = "/datadrive/Wikkerwork/ADP/GCKB/FunctionalAnalysis/ADP_Global_Compliance_Requirements_DRAFT_22.03.2017.xlsx";
        File file = new File(filePath);
        return ResponseEntity.ok((Object)file);
    }


    @RequestMapping(path = "/pdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity downloadPDFFile() throws FileNotFoundException {
        String filePath = "/datadrive/Wikkerwork/ADP/GCKB/FunctionalAnalysis/GCKB_Visuals (1).pdf";
        File file = new File(filePath);
        InputStream stream = new FileInputStream(file);
        return getFileDownloadResponse(stream, MediaType.APPLICATION_PDF, "GCKB_Visuals.pdf");

    }


    @RequestMapping(path = "/xls", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity downloadXLSXFile() throws FileNotFoundException {
//        String filePath = "/datadrive/Wikkerwork/ADP/GCKB/FunctionalAnalysis/ADP_Global_Compliance_Requirements_DRAFT_22.03.2017.xlsx";
        InputStream stream  = this.getClass().getClassLoader().getResourceAsStream("ADP_Global_Compliance_Requirements_DRAFT_22.03.2017.xlsx");
        return getFileDownloadResponse(stream, MediaType.APPLICATION_OCTET_STREAM, "ADP_Global_Compliance_Requirements_DRAFT_22.03.2017.xlsx");

    }

    private ResponseEntity getFileDownloadResponse(InputStream stream, MediaType mediaType, String fileName) throws FileNotFoundException {

        // counting the length of data
//        final long contentLength = file.length() ;
        InputStreamResource inputStreamResource = new InputStreamResource(stream);
        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentLength(contentLength);
        httpHeaders.setContentType(mediaType);
        httpHeaders.set("Content-disposition", "attachment; filename="+ fileName);
        return new ResponseEntity(inputStreamResource, httpHeaders, HttpStatus.OK);
    }


}
