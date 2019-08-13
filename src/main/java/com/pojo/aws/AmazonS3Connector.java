package com.pojo.aws;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AmazonS3Connector {
	
	private AmazonS3 s3client;
    private String bucketName;
    
    public AmazonS3Connector(String accessKey, String secretKey, String region, String bucketName) {    	 
    	 if(accessKey != null && secretKey != null) {
    		 System.out.println("Creating new Amazon S3 Client, using Access Key and Secret Key");
    		 AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
             AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
             s3client =  AmazonS3ClientBuilder.standard().withCredentials(credentialsProvider).withRegion(region).build();	 
    	 }
    	 else {
    		 System.out.println("Creating new Amazon S3 Client, without AccessKey/SecretKey");
    		 s3client =  AmazonS3ClientBuilder.standard().withRegion(region).build();
    	 }
    	 
    	 this.bucketName = bucketName;         
    }
    
    
    public List<String> getAllFileNames(String folder){
    	
    	List<String> acceptableFiles = listObjects(bucketName, folder);   	

        if (acceptableFiles.size() == 0) {
            System.out.println("No matching files found in S3 bucket.");
        }
        else {
            System.out.println("Found " + acceptableFiles.size() + " files in S3 bucket");
            System.out.println(acceptableFiles);
        }
        return acceptableFiles;
    }
    
    
    public InputStream downloadFile(String objectKey) {

        try {
            
        	System.out.println("Downloading " + objectKey + " from S3.");
            
        	S3Object object = s3client.getObject(bucketName, objectKey);
            
        	return object.getObjectContent();            
        } catch (AmazonServiceException e) {
            System.out.println("Error Downloading file from S3 : " + e.getErrorMessage());            
        }
        
        return null;
    }

    
    public void close() {
    	this.s3client.shutdown();
    }
    
    
    
    
    
    private List<String> listObjects(String bucketName, String prefix) {

    	Map<String, Long>  objectKeys = new HashMap<>();
    	 
        try {
            System.out.println("Listing objects in bucket - " + bucketName + ", with prefix = " + prefix );
            final ListObjectsV2Request req =
                    new ListObjectsV2Request()
                            .withBucketName(bucketName)
                            .withPrefix(prefix);

            ListObjectsV2Result result;
            do {
                result = s3client.listObjectsV2(req);

                Map<String, Long> currentResult = 
                		result
                			.getObjectSummaries().stream()
            				.collect(Collectors.toMap(S3ObjectSummary::getKey, S3ObjectSummary::getSize));
                
                objectKeys.putAll(currentResult);             
                
                req.setContinuationToken(result.getNextContinuationToken());
                
            } while(result.isTruncated() == true );

        } catch (AmazonServiceException ase) {
            System.out.println("AmazonServiceException : "
            		+ "S3 rejected the request."
            		+ "Error Message:    " + ase.getMessage() 
            		+ "HTTP Status Code: " + ase.getStatusCode()
            		+ "AWS Error Code:   " + ase.getErrorCode()
            		+ "Error Type:       " + ase.getErrorType()
            		+ "Request ID:       " + ase.getRequestId());            
        } catch (AmazonClientException ace) {
            System.out.println("AmazonClientException: Possible client error in communication with S3. " +
                    "Error Message: " + ace.getMessage());
        }
        
        
        return objectKeys
        		.entrySet()
        		.stream()
        		.filter(entry -> entry.getValue() > 0)
        		.map(Map.Entry::getKey)
        		.collect(Collectors.toList());
    }

       

}
