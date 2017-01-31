package com.jsc.webservices;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class DOMParser {

    private final DocumentBuilder documentBuilder;
    private Element root;

    public DOMParser() throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //Using factory get an instance of document builder
        documentBuilder = dbf.newDocumentBuilder();
    }

    public void parse(InputStream inputStream) throws IOException, SAXException {
        //parseAndCreateNewDocument using builder to get DOM representation of the XML file
        Document document = documentBuilder.parse(inputStream);
        root = document.getDocumentElement();
    }


    public static void main(String[] args) {
        try {
            DOMParser domParser = new DOMParser();
            InputStream inputStream = DOMParser.class.getClassLoader().getResourceAsStream("xml/shortCv.xml");
            domParser.parse(inputStream);
            String firstName = domParser.getTextValue("cv:FirstName");
            String lastName = domParser.getTextValue("cv:LastName");
            String city = domParser.getTextValue("cv:City");
            String phone = domParser.getTextValue("cv:phone");
            String email = domParser.getTextValue("cv:email");
            String university = domParser.getTextValue("cv:University");
            String degree = domParser.getTextValue("cv:Degree");
            String fieldOfWork = domParser.getTextValue("cv:FieldOfWork");
            String previousWorkExperience = domParser.getTextValue("cv:PreviousWorkExperience");
            String drivingLicenceStatus = domParser.getTextValue("cv:DrivingLicenceStatus");
            String ssn = domParser.getTextValue("cv:SSN");
            String relevantExperience = domParser.getTextValue("cv:RelevantExperience");
            String interests = domParser.getTextValue("cv:Interests");




            System.out.println("PARSED VALUES:");
            System.out.println("firstName:" + firstName);
            System.out.println("LastName:" + lastName);
            System.out.println("city:" + city);
            System.out.println("phone:" + phone);
            System.out.println("email:" + email);
            System.out.println("University:" + university);
            System.out.println("Degree:" + degree);
            System.out.println("FieldOfWork:" + fieldOfWork);
            System.out.println("PreviousWorkExperience:" + previousWorkExperience);
            System.out.println("SSN:" + ssn);
            System.out.println("relevantExperience:" + relevantExperience);
            System.out.println("interests:" + interests);
            System.out.println("DrivingLicenceStatus:" + drivingLicenceStatus);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getTextValue(String tagName) {
        return getTextValue(root, tagName);
    }


    private String getTextValue(Element element, String tagName) {
        String textValue = null;
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            Element element1 = (Element) nodeList.item(0);
            textValue = element1.getFirstChild().getNodeValue();
        }

        return textValue;
    }
}
