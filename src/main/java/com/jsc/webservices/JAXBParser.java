package com.jsc.webservices;

import com.jsc.jaxb.ApplicantProfile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class JAXBParser {
    public static void main(String[] args) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ApplicantProfile.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            // load resource from classpath i.e xml/applicantProfile.xml
            InputStream inputStream = JAXBParser.class.getClassLoader().getResourceAsStream("xml/applicantProfile.xml");
            ApplicantProfile applicantProfile = (ApplicantProfile) unmarshaller.unmarshal(inputStream);
            System.out.println("parsed");
            //write
            OutputStream outFile = new FileOutputStream("src/main/out/jaxbParserOutput.xml");
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(applicantProfile, outFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






















