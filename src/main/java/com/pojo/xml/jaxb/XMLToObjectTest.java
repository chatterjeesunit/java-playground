package com.pojo.xml.jaxb;

import com.pojo.xml.jaxb.model.SearchConfig;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * Created by sunitc on 6/2/17.
 */
public class XMLToObjectTest {

    public static void main(String[] args) throws JAXBException {
//
//        InputStream is1 =
//                XMLToObjectTest.class.getClassLoader().getResourceAsStream("person.xml");
//
//        readXMl(is1, Person.class);

        InputStream is2 =
                XMLToObjectTest.class.getClassLoader().getResourceAsStream("pojo-xml-jaxb/search-config.xml");

        readXMl(is2, SearchConfig.class);


    }

    private static void readXMl(InputStream is, Class classz) throws JAXBException {
        Reader fileReader = new InputStreamReader(is);
        JAXBContext context = JAXBContext.newInstance(classz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        System.out.println(unmarshaller.unmarshal(fileReader));
    }

    private static String readInputStream(InputStream is) {
        String line;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
