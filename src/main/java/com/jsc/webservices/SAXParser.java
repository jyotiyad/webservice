package com.jsc.webservices;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SAXParser extends DefaultHandler {

    private Transcript transcript;
    private List<Course> courseList;
    private Course currentCourse;
    private String currentCharacterValue;


    public SAXParser() {
    }

    private void parse() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("xml/transcript.xml");
        saxParser.parse(inputStream, this);
        System.out.println("PARSED TRANSCRIPT VALUES:");
        System.out.println(transcript);
    }

    //start event handler
    public void startElement(String uri, String localName, String qName, Attributes attributes)
        throws SAXException {
        //reset for each tag
        currentCharacterValue = "";
        if (qName.equalsIgnoreCase("ts:transcript")) {
            //create a new instance of Transcript
            transcript = new Transcript();
        } else if (qName.equalsIgnoreCase("ts:Courses")) {
            //create a new list of courseList
            courseList = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("ts:Course")) {
            //create a new instance of Course
            currentCourse = new Course();
        }
    }


    public void characters(char[] ch, int start, int length) throws SAXException {
        currentCharacterValue = new String(ch, start, length);

    }

    //end event ahndler
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("ts:StudentFirstName")) {
            transcript.setStudentFirstName(currentCharacterValue);
        } else if (qName.equalsIgnoreCase("ts:StudentLastName")) {
            transcript.setStudentLastName(currentCharacterValue);
        } else if (qName.equalsIgnoreCase("ts:StudentId")) {
            transcript.setStudentId(currentCharacterValue);
        } else if (qName.equalsIgnoreCase("ts:DOB")) {
            transcript.setDob(currentCharacterValue);
        } else if (qName.equalsIgnoreCase("ts:Programme")) {
            transcript.setProgramme(currentCharacterValue);
        } else if (qName.equalsIgnoreCase("ts:MediumOfInstruction")) {
            transcript.setMediumOfInstruction(currentCharacterValue);
        } else if (qName.equalsIgnoreCase("ts:DurationOfProgramme")) {
            transcript.setDurationOfProgramme(Integer.valueOf(currentCharacterValue));
        } else if (qName.equalsIgnoreCase("ts:PeriodOfProgramme")) {
            transcript.setPeriodOfProgramme(currentCharacterValue);
        } else if (qName.equalsIgnoreCase("ts:IssueDate")) {
            transcript.setIssueDate(currentCharacterValue);
        } else if (qName.equalsIgnoreCase("ts:University")) {
            transcript.setUniversity(currentCharacterValue);
        } else if (qName.equalsIgnoreCase("ts:Degree")) {
            transcript.setUniversity(currentCharacterValue);
        } else if (qName.equalsIgnoreCase("ts:TotalGrade")) {
            transcript.setTotalGrade(currentCharacterValue);
        } else if (qName.equalsIgnoreCase("ts:GPA")) {
            transcript.setGpa(Float.valueOf(currentCharacterValue));
        } else if (qName.equalsIgnoreCase("ts:Courses")) {
            transcript.setCourses(courseList);
        } else if (qName.equalsIgnoreCase("ts:Course")) {
            courseList.add(currentCourse);
        } else if (qName.equalsIgnoreCase("ts:NameOfCourse")) {
            currentCourse.setNameOfCourse(currentCharacterValue);
        } else if (qName.equalsIgnoreCase("ts:CodeForCourse")) {
            currentCourse.setCodeForCourse(currentCharacterValue);
        } else if (qName.equalsIgnoreCase("ts:GradesInCourse")) {
            currentCourse.setGradesInCourse(Float.valueOf(currentCharacterValue));
        }
    }

    public static void main(String[] args) {
        SAXParser saxParser = new SAXParser();
        try {
            saxParser.parse();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
