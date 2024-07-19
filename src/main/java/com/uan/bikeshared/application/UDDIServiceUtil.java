package com.uan.bikeshared.application;

import com.uan.bikeshared.interfaces.ServiceDetails;
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

    private String selectedStationUrl;
    private String selectedStationNamespace;

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

    public boolean selectStation(Long stationId) {
        // Find the service details for the given stationId
        List<ServiceDetails> services = listServices();
        ServiceDetails selectedService = null;
        for (ServiceDetails service : services) {
            if (service.getId() == (stationId)) {
                selectedService = service;
                break;
            }
        }

        if (selectedService == null) {
            System.err.println("Station with ID " + stationId + " not found.");
            return false;
        }

        // Set the selected station URL for future communications
        this.selectedStationUrl = selectedService.getServiceUrl();
        // Derive the namespace from the service URL or name
        this.selectedStationNamespace = deriveNamespace(selectedService.getServiceName());
        System.out.println("Selected station URL: " + this.selectedStationUrl);
        System.out.println("Selected station namespace: " + this.selectedStationNamespace);
        return true;
    }

    private String deriveNamespace(String serviceName) {
        return "http://interfaces." + serviceName.toLowerCase() + ".uan.com";
    }

    private String communicateWithStation(String requestPayload) {
        if (this.selectedStationUrl == null) {
            throw new IllegalStateException("No station selected.");
        }

        try {
            WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
            StringSource source = new StringSource(requestPayload);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            StreamResult result = new StreamResult(outputStream);
            webServiceTemplate.sendSourceAndReceiveToResult(this.selectedStationUrl, source, result);

            String response = outputStream.toString();
            System.out.println("Communication with selected station successful.");
            System.out.println("Response: " + response);
            return response;
        } catch (Exception e) {
            System.err.println("Failed to communicate with selected station: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public String getStationInfo(Long stationId) {
        String requestPayload = "<GetStationRequest xmlns=\"" + this.selectedStationNamespace + "\">" +
                "<stationId>" + stationId + "</stationId>" +
                "</GetStationRequest>";
        return communicateWithStation(requestPayload);
    }

    public String lockBike(Long stationId, Long dockId) {
        String requestPayload = "<LockBikeRequest xmlns=\"" + this.selectedStationNamespace + "\">" +
                "<stationId>" + stationId + "</stationId>" +
                "<dockId>" + dockId + "</dockId>" +
                "</LockBikeRequest>";
        return communicateWithStation(requestPayload);
    }

    public String unlockBike(Long stationId, Long dockId) {
        String requestPayload = "<UnlockBikeRequest xmlns=\"" + this.selectedStationNamespace + "\">" +
                "<stationId>" + stationId + "</stationId>" +
                "<dockId>" + dockId + "</dockId>" +
                "</UnlockBikeRequest>";
        return communicateWithStation(requestPayload);
    }

    public String addDock(Long stationId, Long dockId, String state) {
        String requestPayload = "<AddDockRequest xmlns=\"" + this.selectedStationNamespace + "\">" +
                "<stationId>" + stationId + "</stationId>" +
                "<dock>" +
                "<dockId>" + dockId + "</dockId>" +
                "<state>" + state + "</state>" +
                "</dock>" +
                "</AddDockRequest>";
        return communicateWithStation(requestPayload);
    }

    public String deleteDock(Long stationId, Long dockId) {
        String requestPayload = "<DeleteDockRequest xmlns=\"" + this.selectedStationNamespace + "\">" +
                "<stationId>" + stationId + "</stationId>" +
                "<dockId>" + dockId + "</dockId>" +
                "</DeleteDockRequest>";
        return communicateWithStation(requestPayload);
    }

    public String alterStateDockInUpBike(Long stationId, Long dockId, String state) {
        String requestPayload = "<AlterStateDockInUpBikeRequest xmlns=\"" + this.selectedStationNamespace + "\">" +
                "<stationId>" + stationId + "</stationId>" +
                "<dockId>" + dockId + "</dockId>" +
                "<state>" + state + "</state>" +
                "</AlterStateDockInUpBikeRequest>";
        return communicateWithStation(requestPayload);
    }

    public String alterStateDockInDownBike(Long stationId, Long dockId, String state, String info) {
        String requestPayload = "<AlterStateDockInDownBikeRequest xmlns=\"" + this.selectedStationNamespace + "\">" +
                "<stationId>" + stationId + "</stationId>" +
                "<dockId>" + dockId + "</dockId>" +
                "<state>" + state + "</state>" +
                "<info>" + info + "</info>" +
                "</AlterStateDockInDownBikeRequest>";
        return communicateWithStation(requestPayload);
    }

    public String levantarBicicleta(Long stationId, Long dockId) {
        String requestPayload = "<LevantarBicicletaRequest xmlns=\"" + this.selectedStationNamespace + "\">" +
                "<stationId>" + stationId + "</stationId>" +
                "<dockId>" + dockId + "</dockId>" +
                "</LevantarBicicletaRequest>";
        return communicateWithStation(requestPayload);
    }

    public String entregarBicicleta(Long stationId, Long dockId) {
        String requestPayload = "<EntregarBicicletaRequest xmlns=\"" + this.selectedStationNamespace + "\">" +
                "<stationId>" + stationId + "</stationId>" +
                "<dockId>" + dockId + "</dockId>" +
                "</EntregarBicicletaRequest>";
        return communicateWithStation(requestPayload);
    }
}