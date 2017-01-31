package com.jsc.webservices;

import com.jsc.jaxb.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;

public class ApplicantProfileUsingJAXB {
    public static void main(String[] args) {
        try {
            ApplicantProfileUsingJAXB applicantProfileUsingJAXB = new ApplicantProfileUsingJAXB();
            applicantProfileUsingJAXB.parseAndCreateNewDocument();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseAndCreateNewDocument() throws JAXBException, FileNotFoundException {
        JAXBContext shortCvJaxbContext = JAXBContext.newInstance(ShortCv.class);
        Unmarshaller shortCvJaxbContextUnmarshaller = shortCvJaxbContext.createUnmarshaller();

        JAXBContext employmentRecordJaxbContext = JAXBContext.newInstance(EmploymentRecord.class);
        Unmarshaller employmentRecordJaxbContextUnmarshaller = employmentRecordJaxbContext.createUnmarshaller();

        JAXBContext transcriptJaxbContext = JAXBContext.newInstance(com.jsc.jaxb.Transcript.class);
        Unmarshaller transcriptJaxbContextUnmarshaller = transcriptJaxbContext.createUnmarshaller();

        // load resource from classpath i.e xml/applicantProfile.xml
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream shortCVInputStream = classLoader.getResourceAsStream("xml/shortCv.xml");
        InputStream empRecordInputStream = classLoader.getResourceAsStream("xml/employmentRecord.xml");
        InputStream transcriptInputStream = classLoader.getResourceAsStream("xml/transcript.xml");
        ShortCv shortCv = (ShortCv) shortCvJaxbContextUnmarshaller.unmarshal(shortCVInputStream);
        EmploymentRecord employmentRecord = (EmploymentRecord) employmentRecordJaxbContextUnmarshaller.unmarshal(empRecordInputStream);
        com.jsc.jaxb.Transcript transcript = (com.jsc.jaxb.Transcript) transcriptJaxbContextUnmarshaller.unmarshal(transcriptInputStream);

        ApplicantProfile applicantProfile = new ApplicantProfile();
        ApplicantProfile.PersonalInformation pi = new ApplicantProfile.PersonalInformation();
        pi.setApplicantName(shortCv.getFirstName() + " " + shortCv.getLastName());
        pi.setCity(shortCv.getCity());
        pi.setSSN(shortCv.getSSN());
        pi.setDOB(transcript.getDOB());


        ApplicantProfile.EducationalQualification eduQual = new ApplicantProfile.EducationalQualification();
        eduQual.setUniversity(transcript.getUniversity());
        eduQual.setDegree(transcript.getProgramme());
        eduQual.setPassingYear(transcript.getPeriodOfProgramme().split("-")[1]);
        eduQual.setNameOfProgramme(transcript.getProgramme());
        eduQual.setTotalGrade(transcript.getTotalGrade());
        eduQual.setDurationOfProgramme(transcript.getDurationOfProgramme());
        eduQual.setPeriodOfProgramme(transcript.getPeriodOfProgramme());

        ApplicantProfile.EmploymentInfo employmentInfo = new ApplicantProfile.EmploymentInfo();

        List<ApplicantProfile.EmploymentInfo.Employment> employments = employmentInfo.getEmployment();
        List<EmploymentRecord.JobInfo> jobInfo = employmentRecord.getJobInfo();
        for (EmploymentRecord.JobInfo info : jobInfo) {
            ApplicantProfile.EmploymentInfo.Employment employment = new ApplicantProfile.EmploymentInfo.Employment();
            employment.setCompanyName(info.getCompanyName());
            employment.setDesignation(info.getDesignation());
            employment.setStartDate(info.getStartDate());
            employment.setEndDate(info.getEndDate());
            employments.add(employment);
        }

        //add all info to application profile object
        applicantProfile.setPersonalInformation(pi);
        applicantProfile.setEducationalQualification(eduQual);
        applicantProfile.setEmploymentInfo(employmentInfo);


        //write
        JAXBContext jaxbContext = JAXBContext.newInstance(ApplicantProfile.class);
        OutputStream outFile = new FileOutputStream("src/main/out/ApplicantProfileJAXB.xml");
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(applicantProfile, outFile);
    }
}






















