package com.pojo.poi;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sunitc on 3/14/18.
 */
public class EmbeddedDocuments {

    public static void main(String[] args) throws IOException, OpenXML4JException {
        FileInputStream fis = new FileInputStream("/home/sunitc/Desktop/document_1521012279912.docx");
            XWPFDocument document = new XWPFDocument(fis);
            listEmbeds (document);
        }


    private static void listEmbeds (XWPFDocument doc) throws OpenXML4JException {
        List<PackagePart> embeddedDocs = doc.getAllEmbedds();
        if (embeddedDocs != null && !embeddedDocs.isEmpty()) {
            Iterator<PackagePart> pIter = embeddedDocs.iterator();
            while (pIter.hasNext()) {
                PackagePart pPart = pIter.next();
                System.out.print(pPart.getPartName()+", ");
                System.out.print(pPart.getContentType()+", ");
                System.out.println();
            }
        }
    }
}
