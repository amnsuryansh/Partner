package com.meet.partner.esign.webservice;

import com.meet.partner.esign.model.Docs;
import com.meet.partner.esign.model.Esign;
import com.meet.partner.esign.model.InputHash;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class EsignResource {

    public static void main(String[] args) {
        try {
            String apiResponse = "https://api.pichainlabs.com/docs/unsigned_docs/Loan+Agreement_(3)17-April-2023_12:12:34.90517-April-2023_12:12:35.865.pdf";
            String newUrl = "https://www.myurl.com/docs/unsigned_docs/Loan+Agreement_(3)17-April-2023_12:12:34.90517-April-2023_12:12:35.865.pdf";

            URL originalUrl = new URL("https://api.pichainlabs.com/docs/unsigned_docs/Loan+Agreement_(3)17-April-2023_12:12:34.90517-April-2023_12:12:35.865.pdf");
            URL proxyUrl = new URL("https://dashboard.dev.kychub.com/proxy?url=" + originalUrl);
            URLConnection connection = proxyUrl.openConnection();
            InputStream inputStream = connection.getInputStream();
            Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
            String proxyResponse = scanner.hasNext() ? scanner.next() : "";
            System.out.println(proxyResponse);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        String xml = "<Esign ver=\"hey\" sc=\"i m\" ts=\"esign\" txn=\"txn\" ekycId=\"ekycId\" ekycIdType=\"ekycIdType\" aspId=\"aspId\"\n" +
//                "AuthMode=\"AuthMode\" responseSigType=\"responseSigType\" responseUrl=\"responseUrl\">\n" +
//                "<Docs>\n" +
//                "<InputHash id=\"InputHash id\" hashAlgorithm=\"InputHash hashAlgorithm\" docInfo=\"InputHash docInfo\">Document\n" +
//                "Hash in Hex</InputHash>\n" +
//                "</Docs>\n" +
//                "<Signature>Digital signature of ASP</Signature>\n" +
//                "</Esign>";
//
//
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(Esign.class);
//            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//            Esign esign = (Esign) unmarshaller.unmarshal(new StringReader(xml));
//
//            System.out.println("ver: " + esign.getVer());
//            System.out.println("sc: " + esign.getSc());
//            System.out.println("ts: " + esign.getTs());
//            System.out.println("txn: " + esign.getTxn());
//            System.out.println("ekycId: " + esign.getEkycId());
//            System.out.println("ekycIdType: " + esign.getEkycIdType());
//            System.out.println("aspId: " + esign.getAspId());
//            System.out.println("AuthMode: " + esign.getAuthMode());
//            System.out.println("responseSigType: " + esign.getResponseSigType());
//            System.out.println("responseUrl: " + esign.getResponseUrl());
//            System.out.println("inputHash id: " + esign.getDocs().getInputHash().getId());
//            System.out.println("inputHash hashAlgorithm: " + esign.getDocs().getInputHash().getHashAlgorithm());
//            System.out.println("inputHash DocInfo: " + esign.getDocs().getInputHash().getDocInfo());
//            System.out.println("inputHash Value: " + esign.getDocs().getInputHash().getValue());
//
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
//        // Create an instance of the Esign class
//        Esign esign = new Esign();
//        esign.setVer("1.0");
//        esign.setSc("Test");
//        esign.setTs("2023-04-15T12:30:00Z");
//        esign.setTxn("12345");
//        esign.setEkycId("1234567890");
//        esign.setEkycIdType("Aadhaar");
//        esign.setAspId("TestASP");
//        esign.setAuthMode("OTP");
//        esign.setResponseSigType("PKCS7");
//        esign.setResponseUrl("http://test.com");
//        Docs docs = new Docs();
//        InputHash inputHash = new InputHash();
//        inputHash.setId("doc1");
//        inputHash.setHashAlgorithm("SHA-256");
//        inputHash.setDocInfo("Test document");
//        inputHash.setValue("abcd1234");
//        docs.setInputHash(inputHash);
//        esign.setDocs(docs);
//        esign.setSignature("xyz789");
//
//        try {
//            // Create a JAXBContext instance for the Esign class
//            JAXBContext jaxbContext = JAXBContext.newInstance(Esign.class);
//
//            // Create a Marshaller instance
//            Marshaller marshaller = jaxbContext.createMarshaller();
//
//            // Set properties for the Marshaller
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//
//            // Marshal the Esign object to XML
//            StringWriter writer = new StringWriter();
//            marshaller.marshal(esign, writer);
//            String xmlString = writer.toString();
//
//            // Print the generated XML
//            System.out.println(xmlString);
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
//    }
}
