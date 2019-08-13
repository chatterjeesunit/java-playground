package com.pojo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sunitc on 6/8/17.
 */
public class RegexGroupReplace {

    public static void main(String[] args) {

        String sql = "SELECT" +
                "        jh.jobHistoryId," +
                "        j.jobId," +
                "        j.jobName," +
                "        j.jobType," +
                "        jh.startedOn," +
                "        user.userId startedByUserId," +
                "        jh.completedOn," +
                "        jh.jobStatus status," +
                "        jh.totalRecordsProcessed," +
                "        jh.fileAttachmentId," +
                "        jh.exportConfig," +
                "        jh.errorInfo " +
                "FROM" +
                "        job_history jh" +
                "        INNER JOIN job j ON jh.jobId = j.jobId" +
                "        INNER JOIN user_ user ON jh.startedBy = user.userId" +
                "        where 1=1";
        String countQuery = patternMatchAndReplace(sql);
        System.out.println(countQuery);

        String fileName = "2346786876_AI.Companies.pdf";
        patternMatch(fileName);
    }

    private static String patternMatchAndReplace(String sql) {
        String regex = "[\\s]*(select)([.,_\\-\\s\\d\\w]*)(from.*)";
        Pattern pattern =  Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sql);

        if(matcher.matches()) {
            return (matcher.group(1) + " count(*) " + matcher.group(3));
        }
        return null;
    }


    private static void patternMatch(String inputFileName) {
        String regexPattern = "([\\d]*)_(.*)\\.([\\w]*)";
        Pattern pattern = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputFileName);

        if(matcher.matches()) {
            String fileName = matcher.group(2);
            String extension = matcher.group(3);
            System.out.println(fileName);
            System.out.println(extension);
        }
    }
}
