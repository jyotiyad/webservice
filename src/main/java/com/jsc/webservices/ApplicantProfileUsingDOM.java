package com.jsc.webservices;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ApplicantProfileUsingDOM {
    private final DocumentBuilder documentBuilder;

    public ApplicantProfileUsingDOM() throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        //dbf.setValidating(true); //it gives warnings for true
        documentBuilderFactory.setNamespaceAware(true);
        documentBuilderFactory.setIgnoringElementContentWhitespace(true);
        documentBuilderFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                "http://www.w3.org/2001/XMLSchema");
        documentBuilderFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", "src/main/resources/xml/ApplicantProfile.xsd");
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
    }

    public static void main(String[] args) {
        try {
            ApplicantProfileUsingDOM applicantProfileUsingDOM = new ApplicantProfileUsingDOM();
            applicantProfileUsingDOM.parseAndCreateNewDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void parseAndCreateNewDocument() throws IOException, SAXException, TransformerException {
        //output xml document where will be writing the content
        Document outputDocument = documentBuilder.newDocument();
        Element outputDocumentRootElement = outputDocument.createElement("app:applicantProfile");
        outputDocumentRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:app", "http://www.jsc.applicantProfile.se");
        outputDocument.appendChild(outputDocumentRootElement);

        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream shortCVInputStream = classLoader.getResourceAsStream("xml/shortCv.xml");
        InputStream empRecordInputStream = classLoader.getResourceAsStream("xml/employmentRecord.xml");
        InputStream transcriptInputStream = classLoader.getResourceAsStream("xml/transcript.xml");
        Document shortCVDocument = documentBuilder.parse(shortCVInputStream);
        Document employmentRecord = documentBuilder.parse(empRecordInputStream);
        Document transcriptDocument = documentBuilder.parse(transcriptInputStream);

        //read from shortCv.xml and create app:personalInformation node
        Element personalInfoRoot = outputDocument.createElement("app:personalInformation");
        Element shortCvRoot = shortCVDocument.getDocumentElement();
        String firstName = getTextValue(shortCvRoot, "cv:FirstName");
        String lastName = getTextValue(shortCvRoot, "cv:LastName");
        String city = getTextValue(shortCvRoot, "cv:City");
        String ssn = getTextValue(shortCvRoot, "cv:SSN");
        Element applicantNameNode = outputDocument.createElement("app:ApplicantName");
        applicantNameNode.appendChild(outputDocument.createTextNode(firstName + " " + lastName));
        personalInfoRoot.appendChild(applicantNameNode);

        Element cityNode = outputDocument.createElement("app:City");
        cityNode.appendChild(outputDocument.createTextNode(city));
        personalInfoRoot.appendChild(cityNode);

        Element ssnNode = outputDocument.createElement("app:SSN");
        ssnNode.appendChild(outputDocument.createTextNode(ssn));
        personalInfoRoot.appendChild(ssnNode);

        //read from transcript.xml and create app:EducationalQualification node
        Element transcriptRoot = transcriptDocument.getDocumentElement();
        String dob = getTextValue(transcriptRoot, "ts:DOB");
        Element dobNode = outputDocument.createElement("app:DOB");
        dobNode.appendChild(outputDocument.createTextNode(dob));
        personalInfoRoot.appendChild(dobNode);

        String university = getTextValue(transcriptRoot, "ts:University");
        String degree = getTextValue(transcriptRoot, "ts:Programme");
        String studyPeriod = getTextValue(transcriptRoot, "ts:PeriodOfProgramme");
        String passingYear = studyPeriod.split("-")[1];

        String courseDuration = getTextValue(transcriptRoot, "ts:DurationOfProgramme");
        String totalGrades = getTextValue(transcriptRoot, "ts:TotalGrade");

        Element educationalQualificationRoot = outputDocument.createElement("app:EducationalQualification");
        Element univNode = outputDocument.createElement("app:University");
        univNode.appendChild(outputDocument.createTextNode(university));
        educationalQualificationRoot.appendChild(univNode);

        Element degreeNode = outputDocument.createElement("app:Degree");
        degreeNode.appendChild(outputDocument.createTextNode(degree));
        educationalQualificationRoot.appendChild(degreeNode);

        Element passYearNode = outputDocument.createElement("app:PassingYear");
        passYearNode.appendChild(outputDocument.createTextNode(passingYear));
        educationalQualificationRoot.appendChild(passYearNode);

        Element nameofCourseNode = outputDocument.createElement("app:NameOfProgramme");
        nameofCourseNode.appendChild(outputDocument.createTextNode(degree));
        educationalQualificationRoot.appendChild(nameofCourseNode);

        Element totalGradeNode = outputDocument.createElement("app:TotalGrade");
        totalGradeNode.appendChild(outputDocument.createTextNode(totalGrades));
        educationalQualificationRoot.appendChild(totalGradeNode);

        Element durationOdCourseNode = outputDocument.createElement("app:DurationOfProgramme");
        durationOdCourseNode.appendChild(outputDocument.createTextNode(courseDuration));
        educationalQualificationRoot.appendChild(durationOdCourseNode);

        Element studyPeriodNode = outputDocument.createElement("app:PeriodOfProgramme");
        studyPeriodNode.appendChild(outputDocument.createTextNode(studyPeriod));
        educationalQualificationRoot.appendChild(studyPeriodNode);

        //read from emloymentRecord.xml and create app:EmploymentInfo node
        Element empRecordRoot = employmentRecord.getDocumentElement();
        String company = getTextValue(empRecordRoot, "epr:companyName");
        String position = getTextValue(empRecordRoot, "epr:designation");
        String startDate = getTextValue(empRecordRoot, "epr:startDate");
        String endDate = getTextValue(empRecordRoot, "epr:endDate");

        Element employmentInfoRoot = outputDocument.createElement("app:EmploymentInfo");
        outputDocumentRootElement.appendChild(employmentInfoRoot);
        Element employmentNode = outputDocument.createElement("app:Employment");
        employmentInfoRoot.appendChild(employmentNode);

        Element companyNameNode = outputDocument.createElement("app:companyName");
        companyNameNode.appendChild(outputDocument.createTextNode(company));
        employmentNode.appendChild(companyNameNode);

        Element DesignationNode = outputDocument.createElement("app:Designation");
        DesignationNode.appendChild(outputDocument.createTextNode(position));
        employmentNode.appendChild(DesignationNode);

        Element startDtNode = outputDocument.createElement("app:startDate");
        startDtNode.appendChild(outputDocument.createTextNode(startDate));
        employmentNode.appendChild(startDtNode);

        Element endDtNode = outputDocument.createElement("app:endDate");
        endDtNode.appendChild(outputDocument.createTextNode(endDate));
        employmentNode.appendChild(endDtNode);

        //append all child nodes to the main root document
        outputDocumentRootElement.appendChild(personalInfoRoot);
        outputDocumentRootElement.appendChild(educationalQualificationRoot);
        outputDocumentRootElement.appendChild(employmentInfoRoot);

        // Write the output file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(outputDocument);
        StreamResult result = new StreamResult(new File("src/main/out/GeneratedApplicantProfileDOM.xml"));
        transformer.transform(source, result);
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
