package com.uan.bikeshared.application;

import com.uan.interfaces.ServiceDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.xml.transform.StringSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

@Component
public class UDDIServiceUtil {

    @Value("${uddi.server.url}")
    private String uddiServerUrl;

    private static final Logger LOGGER = Logger.getLogger(UDDIServiceUtil.class.getName());

    public List<ServiceDetails> listServices() {
        String requestPayload = "<getAllServicesRequest xmlns=\"http://interfaces.uddi.uan.com\"/>";

        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        StringSource source = new StringSource(requestPayload);

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            StreamResult result = new StreamResult(outputStream);
            webServiceTemplate.sendSourceAndReceiveToResult(uddiServerUrl, source, result);

            String xmlResponse = outputStream.toString();
            System.out.println("Services listed successfully.");
            System.out.println("Response: " + xmlResponse);

            List<ServiceDetails> services = parseServicesFromResult(xmlResponse);
            return services;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to list services: " + e.getMessage(), e);
        }
        return new ArrayList<>();
    }

    private List<ServiceDetails> parseServicesFromResult(String xmlResponse) throws Exception {
        List<ServiceDetails> services = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // Make the factory namespace aware
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xmlResponse.getBytes()));

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();

        XPathExpression expr = xpath.compile("//*[local-name()='getAllServicesResponse']/*[local-name()='services']");

        NodeList serviceNodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

        for (int i = 0; i < serviceNodes.getLength(); i++) {
            ServiceDetails serviceDetails = new ServiceDetails();

            XPathExpression idExpr = xpath.compile("*[local-name()='id']");
            XPathExpression nameExpr = xpath.compile("*[local-name()='serviceName']");
            XPathExpression urlExpr = xpath.compile("*[local-name()='serviceUrl']");

            serviceDetails.setId(Long.parseLong(idExpr.evaluate(serviceNodes.item(i), XPathConstants.STRING).toString()));
            serviceDetails.setServiceName(nameExpr.evaluate(serviceNodes.item(i), XPathConstants.STRING).toString());
            serviceDetails.setServiceUrl(urlExpr.evaluate(serviceNodes.item(i), XPathConstants.STRING).toString());

            services.add(serviceDetails);
        }

        return services;
    }

    public String getStationUrl(String stationName) {
        String requestPayload = "<GetStationUrlRequest xmlns=\"http://interfaces.uddi.uan.com\"><stationName>" + stationName + "</stationName></GetStationUrlRequest>";

        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        StringSource source = new StringSource(requestPayload);

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            StreamResult result = new StreamResult(outputStream);
            webServiceTemplate.sendSourceAndReceiveToResult(uddiServerUrl, source, result);

            String xmlResponse = outputStream.toString();
            System.out.println("Station URL retrieved successfully.");
            System.out.println("Response: " + xmlResponse);

            return parseUrlFromResult(xmlResponse);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to retrieve station URL: " + e.getMessage(), e);
        }
        return null;
    }

    private String parseUrlFromResult(String xmlResponse) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xmlResponse.getBytes()));

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();

        XPathExpression expr = xpath.compile("//*[local-name()='GetStationUrlResponse']/*[local-name()='url']");

        return expr.evaluate(doc, XPathConstants.STRING).toString();
    }

}