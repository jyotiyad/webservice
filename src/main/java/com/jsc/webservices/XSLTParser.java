package com.jsc.webservices;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class XSLTParser{

        public static void main(String[] args ) throws IOException, URISyntaxException, TransformerException {
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(new File("src/main/resources/xml/applicantProfile.xsl"));
            Transformer transformer = factory.newTransformer(xslt);

            Source text = new StreamSource(new File("src/main/resources/xml/applicantProfile.xml"));
            transformer.transform(text, new StreamResult(new File("src/main/java/com/jsc/webservices/XSLToutput.xml")));


        }

}
