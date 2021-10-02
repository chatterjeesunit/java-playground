package com.util.jsoup;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.poi.util.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by sunitc on 3/27/18.
 */
public class Test {
    private static String DATE_FORMAT_LONG = "dd MMM yyyy";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_LONG);
    public static void main(String[] args) throws IOException, URISyntaxException {

        String resourceFolderPath = "jsoup-example";
        ClassLoader classLoader = Test.class.getClassLoader();


        String resourceLocation = "jsoup-example/Austria.mtarc";
        InputStream inputStream1 = classLoader.getResourceAsStream(resourceLocation);

//        ZipInputStream zipInputStream1 = new ZipInputStream(inputStream1);
//        ZipEntry zipEntry1 = null;
//        while((zipEntry1 = zipInputStream1.getNextEntry())!= null) {
//            System.out.println(zipEntry1.getName());
//        }
//        inputStream1.close();

        InputStream inputStream = classLoader.getResourceAsStream(resourceLocation);
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);

        List<Map<String,Object>> result = new ArrayList<>();



        try {
            ZipEntry zipEntry = null;
            while((zipEntry = zipInputStream.getNextEntry())!= null) {
                Map<String, Object> whatsNew = new HashMap<>();
                String name = zipEntry.getName();
                System.out.println("File ----> " + name);
                if(name.contains("page.xml")) {
                     InputStream currentInputStream = convertToInputStream(zipInputStream);
                     Document document = Jsoup.parse(currentInputStream, "UTF-8", "");
//                    System.out.println(document);

                    String title = fetchWhatsNewTitle(document);
                    String countryName = extractCountryNameFromTitle(title);
                    Map<String, String> dataMap = modifyDocumentArticleUrlAndFetchTopicAndCountry(
                            "Link to Article in Knowledge Base (if applicable)", countryName, document);

                    String topicNumber = dataMap.get("topicNumber");
                    countryName = dataMap.get("countryName");

                    String templateType = extractTemplateTypeFromTitle(title);
                    Date dateAnnouncedByJurisdiction = getDate("Date Announced By Jurisdiction:", document);
                    Date dateEffective = getDate("Date Effective:", document);
                    Date datePublished = getDate("Date Published:", document);

                    String content = fetchContent(document);

                    whatsNew.put("name", title);
                    whatsNew.put("topicNumber", topicNumber);
                    whatsNew.put("countryName", countryName);
                    whatsNew.put("templateType", templateType);
                    whatsNew.put("content", content);
                    whatsNew.put("dateAnnouncedByJurisdiction", dateAnnouncedByJurisdiction);
                    whatsNew.put("dateEffective", dateEffective);
                    whatsNew.put("datePublished", datePublished);

                    String json = getJson(whatsNew);
                    System.out.println(json);
                    result.add(whatsNew);
                }
            }
        }catch(IOException ex) {
//                System.out.println("Error occurred in processing file = " + fileName + " :  " + ex);
//                ex.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        String json = getJson(result);

        System.out.println(json);
    }


    private static InputStream convertToInputStream(final ZipInputStream inputStreamIn) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtils.copy(inputStreamIn, out);
        return new ByteArrayInputStream(out.toByteArray());
    }

    private static String getJson(Object result) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        SimpleModule module = new SimpleModule();
        module.addSerializer(Date.class, new DateSerializer());
        mapper.registerModule(module);
        LinkedHashMap kv = new LinkedHashMap(0);
        kv.put(1, 2);
        return mapper.writeValueAsString(result);
    }


    private static List<String> getFileNames(String resourceFolderPath) throws URISyntaxException, IOException {
        ClassLoader classLoader = Test.class.getClassLoader();
        URI uri = classLoader.getResource(resourceFolderPath).toURI();
//        System.out.println(uri);
        Path path = Paths.get(uri);
//        System.out.println(path);

        List<String> fileNames = new ArrayList<String>();
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
        directoryStream.forEach(directoryPath -> {
            File file = directoryPath.toFile();
            if(!file.isDirectory()) {
                fileNames.add(file.getName());
            }
        });
        return fileNames;
    }

    private static String fetchWhatsNewTitle(Document document) {
        Elements elements = document.getElementsByTag("content");
        return elements.first().attr("title");
    }


    private static Date getDate(String name, Document document) {
        Optional<Element> elementOpt = fetchTableDataForSiblingColumn(name, document);

        Date finalDate = elementOpt
                        .map(se -> se.text())
                        .map(dateStr -> {  //If sibling text available, then extract date from it
                            Date date = null;
                            try {
                                date = dateFormat.parse(dateStr);
                            } catch (ParseException e) {
//                                System.out.println("Unable to parse date : " + dateStr);
                            }
                            return date;
                        }).orElse(null);

//        System.out.println(name + " " + finalDate);

        return finalDate;
    }

    private static Optional<Element> fetchTableDataForSiblingColumn(String name, Document document) {
        Elements elements = document.getElementsByTag("td");
        return elements.stream()
            .filter(e -> e.text().contains(name))  //Match all elements that match provided text
            .findFirst()  //Get first match
            .flatMap(e -> {   //Retrieve sibling text if available
                return Optional.ofNullable(e.nextElementSibling());
            });
    }

    private static Map<String, String> modifyDocumentArticleUrlAndFetchTopicAndCountry(String name, String countryName,  Document document) {
        Optional<Element> elementOpt = fetchTableDataForSiblingColumn(name, document);
        Map<String, String> defaultMap = new HashMap<>();
        defaultMap.put("countryName", countryName);

        return elementOpt.map(e -> {
            Element hrefElement = e.getElementsByTag("a").first();
            if(hrefElement != null) {
                String articleName = hrefElement.text();
                Map<String, String> dataMap = extractCountryAndTopicFromDocumentArticle(articleName);
                if(countryName != null) dataMap.put("countryName", countryName.trim()); //Keep original country name if already present.

                String newDocumentURL = "http://localhost:8080/gckb/#/document/country/" +
                        dataMap.getOrDefault("countryName", "dummyCountry") + "/topic/" +
                        dataMap.getOrDefault("topicNumber", "dummyTopic") + "/";

                String newHrefHtml = "<a href=\"" + newDocumentURL + "\">" + articleName + "</a>";
//                System.out.println(newHrefHtml);
                hrefElement.parent().html(newHrefHtml);
                return dataMap;
            }
            return null;
        }).orElse(defaultMap);
    }

    private static String fetchContent(Document document) {
        Elements elements = document.getElementsByTag("content");
        return elements.first().html();
    }

    private static String extractCountryNameFromTitle(String title) {
        String templateTypeRegex = "([\\w\\s\\(\\)]*)";
        String separatorRegex = "[\\s]*[-–][\\s]*";
        String countryNameRegex = "([\\w\\s]*)";
        String remainderTitleRegex = "[-–\\w\\d\\s\\(\\);:']*";
        Pattern titlePattern = Pattern.compile(templateTypeRegex + separatorRegex + countryNameRegex + separatorRegex + remainderTitleRegex, Pattern.CASE_INSENSITIVE);
        Matcher titleMatcher = titlePattern.matcher(title);
        if(titleMatcher.matches()) {
            return titleMatcher.group(2);
        }
        return null;
    }


    private static Map<String, String> extractCountryAndTopicFromDocumentArticle(String article) {
        Map<String, String> map = new HashMap<>();

        String topicNumberRegex = "([\\d\\.]*[\\d]+)";
        String countryNameRegex = "([\\w\\s]*)";
        String remainderRegex = "[-–\\w\\d\\s\\(\\);:']*";
        Pattern pattern = Pattern.compile(topicNumberRegex + "([\\s\\.]*)" + countryNameRegex + "([\\s]*:[\\s]*)" + remainderRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(article);
        if(matcher.matches()) {
            map.put("countryName", Optional.ofNullable(matcher.group(2)).map(str -> str.trim()).orElse(null));
            map.put("topicNumber", Optional.ofNullable(matcher.group(1)).map(str -> str.trim()).orElse(null));
        }
        return map;
    }

    private static String extractTemplateTypeFromTitle(String title) {

        Pattern legislativeUpdatePattern = Pattern.compile("(.*)(Legislative Update)(.*)", Pattern.CASE_INSENSITIVE);
        Pattern headsUpPattern = Pattern.compile("(.*)(Heads Up)(.*)", Pattern.CASE_INSENSITIVE);
        Pattern ERPattern = Pattern.compile("(.*)(ER)(.*)");

        boolean headsUpMatches = headsUpPattern.matcher(title).matches();
        boolean erMatches = ERPattern.matcher(title).matches();
        boolean legislativeUpdateMatches = legislativeUpdatePattern.matcher(title).matches();

        String templateType = null;
        if(legislativeUpdateMatches) {
            templateType = "Legislative Update";
            if (headsUpMatches && !erMatches)
                templateType = "Legislative Update (Heads Up)";
            else if (headsUpMatches && erMatches)
                templateType = "Legislative Update (Heads Up) - Information for ER";
            else if (!headsUpMatches && erMatches)
                templateType = "Legislative Update - Information for ER";
        }
        return templateType;
    }
}


class DateSerializer extends StdSerializer<Date> {

    private static String DATE_FORMAT_LONG = "dd MMM yyyy";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_LONG);

    public DateSerializer() {
        this(null);
    }
    protected DateSerializer(Class<Date> t) {
        super(t);
    }

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        String dateStr = dateFormat.format(value);
        gen.writeString(dateStr);
    }
}
