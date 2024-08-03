package com.uan.bikeshared.application;

import com.uan.interfaces.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ServiceEndpoint {
    private static final String NAMESPACE_URI = "http://interfaces.uan.com";

    @Autowired
    private UDDIServiceUtil uddiServiceUtil;
    
    @Autowired
    private StationServiceClient stationServiceClient;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllServicesRequest")
    @ResponsePayload
    public GetAllServicesResponse getAllServices(@RequestPayload GetAllServicesRequest request) {
        GetAllServicesResponse response = new GetAllServicesResponse();
        List<ServiceDetails> services = uddiServiceUtil.listServices();
        response.getServices().addAll(services);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetStationUrlRequest")
    @ResponsePayload
    public GetStationUrlResponse getStationUrl(@RequestPayload GetStationUrlRequest request) {
        GetStationUrlResponse response = new GetStationUrlResponse();
        String stationUrl = uddiServiceUtil.getStationUrl(request.getStationName());
        response.setUrl(stationUrl);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetStationRequest")
    @ResponsePayload
    public GetStationResponse getStationInfo(@RequestPayload GetStationRequest request) {
        return stationServiceClient.getStation(request.getStationName());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "LockBikeRequest")
    @ResponsePayload
    public LockBikeResponse lockBike(@RequestPayload LockBikeRequest request) {
        return stationServiceClient.lockBike(request.getStationId(), request.getDockId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UnlockBikeRequest")
    @ResponsePayload
    public UnlockBikeResponse unlockBike(@RequestPayload UnlockBikeRequest request) {
        return stationServiceClient.unlockBike(request.getStationId(), request.getDockId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddDockRequest")
    @ResponsePayload
    public AddDockResponse addDock(@RequestPayload AddDockRequest request) {
        return stationServiceClient.addDock(request.getStationId(), request.getDock());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteDockRequest")
    @ResponsePayload
    public DeleteDockResponse deleteDock(@RequestPayload DeleteDockRequest request) {
        return stationServiceClient.deleteDock(request.getStationId(), request.getDockId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AlterStateDockInUpBikeRequest")
    @ResponsePayload
    public AlterStateDockInUpBikeResponse alterStateDockInUpBike(@RequestPayload AlterStateDockInUpBikeRequest request) {
        return stationServiceClient.alterStateDockInUpBike(request.getStationId(), request.getDockId(), request.getState());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AlterStateDockInDownBikeRequest")
    @ResponsePayload
    public AlterStateDockInDownBikeResponse alterStateDockInDownBike(@RequestPayload AlterStateDockInDownBikeRequest request) {
        return stationServiceClient.alterStateDockInDownBike(request.getStationId(), request.getDockId(), request.getState(), request.getInfo());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "LevantarBicicletaRequest")
    @ResponsePayload
    public LevantarBicicletaResponse levantarBicicleta(@RequestPayload LevantarBicicletaRequest request) {
        return stationServiceClient.levantarBicicleta(request.getStationId(), request.getDockId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "EntregarBicicletaRequest")
    @ResponsePayload
    public EntregarBicicletaResponse entregarBicicleta(@RequestPayload EntregarBicicletaRequest request) {
        return stationServiceClient.entregarBicicleta(request.getStationId(), request.getDockId());
    }
}