package com.play.integration.aws;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by sunitc on 5/24/18.
 */
public class AWSS3Client {


    public static void main(String[] args) {

        System.out.println("Arguments Length = " + args.length);

        if(args.length < 3) {
            System.out.println("Usage");
            System.out.println("java -cp s3test-1.0-SNAPSHOT.jar com.aws.AWSS3Client <region> <bucket> <folder> <accessKey> <secretKey>");
            System.out.println("java -cp s3test-1.0-SNAPSHOT.jar com.aws.AWSS3Client ap-south-1 adp-gckb-dev WhatsNew");
            System.out.println("java -cp s3test-1.0-SNAPSHOT.jar com.aws.AWSS3Client ap-south-1 adp-gckb-dev WhatsNew AKIAIAZGNR7X5X5GOAPA C7y/tPPCt4mhv0C/R8MXpNLdecawntWqhYrEAXgW");
        }

        String region = args[0];
        String bucketName = args[1];
        String folderName = args[2];
        String accessKey = null;
        String secretKey = null;

        if(args.length > 3 ) {
            System.out.println("Using Access Key and Secret Key");
            accessKey = args[3];
            secretKey = args[4];
        }



        AmazonS3Connector connector = null;
        try {
            connector = new AmazonS3Connector(accessKey, secretKey, region, bucketName);
            List<String> files = connector.getAllFileNames(folderName);
            System.out.println(files);

            final AmazonS3Connector finalConnector = connector;
            files.forEach(file -> {
                InputStream is = null;
                try {

                    is = finalConnector.downloadFile(file);
                    download(is, file);
                }finally {
                    if(is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


        } catch (Exception ex) {
            System.out.println("Error occurred : " + ex.getMessage());
        }finally {
            System.out.println("Closing s3 client");
            connector.close();
        }
    }



    private static void download(InputStream inputStream, String objectKey) {

        FileOutputStream fos = null;
        String fileName = objectKey.substring(objectKey.lastIndexOf("/") +1);
        try {
            fos = new FileOutputStream(new File("/home/ec2-user/" + fileName));

            byte[] bytes = new byte[1024];
            int read_len = 0;
            while((read_len = inputStream.read(bytes)) > 0) {
                fos.write(bytes, 0, read_len);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
