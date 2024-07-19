package com.uan.bikeshared.application;

import com.uan.bikeshared.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class ServiceEndpoint {
    private static final String NAMESPACE_URI = "http://interfaces.bikeshared.uan.com";

    @Autowired
    private UDDIServiceUtil uddiServiceUtil;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllServicesRequest")
    @ResponsePayload
    public GetAllServicesResponse getAllServices(@RequestPayload GetAllServicesRequest request) {
        GetAllServicesResponse response = new GetAllServicesResponse();
        List<ServiceDetails> services = uddiServiceUtil.listServices();
        response.getServices().addAll(services);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "selectStationRequest")
    @ResponsePayload
    public SelectStationResponse selectStation(@RequestPayload SelectStationRequest request) {
        SelectStationResponse response = new SelectStationResponse();
        boolean success = uddiServiceUtil.selectStation(request.getStationId());
        response.setSuccess(success);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStationInfoRequest")
    @ResponsePayload
    public GetStationInfoResponse getStationInfo(@RequestPayload GetStationInfoRequest request) {
        GetStationInfoResponse response = new GetStationInfoResponse();
        String stationInfoResponse = uddiServiceUtil.getStationInfo(request.getStationId());
        response.setResponse(stationInfoResponse);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "lockBikeRequest")
    @ResponsePayload
    public LockBikeResponse lockBike(@RequestPayload LockBikeRequest request) {
        LockBikeResponse response = new LockBikeResponse();
        String lockBikeResponse = uddiServiceUtil.lockBike(request.getStationId(), request.getDockId());
        response.setResponse(lockBikeResponse);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "unlockBikeRequest")
    @ResponsePayload
    public UnlockBikeResponse unlockBike(@RequestPayload UnlockBikeRequest request) {
        UnlockBikeResponse response = new UnlockBikeResponse();
        String unlockBikeResponse = uddiServiceUtil.unlockBike(request.getStationId(), request.getDockId());
        response.setResponse(unlockBikeResponse);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addDockRequest")
    @ResponsePayload
    public AddDockResponse addDock(@RequestPayload AddDockRequest request) {
        AddDockResponse response = new AddDockResponse();
        String addDockResponse = uddiServiceUtil.addDock(request.getStationId(), request.getDock().getDockId(), request.getDock().getState());
        response.setResponse(addDockResponse);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteDockRequest")
    @ResponsePayload
    public DeleteDockResponse deleteDock(@RequestPayload DeleteDockRequest request) {
        DeleteDockResponse response = new DeleteDockResponse();
        String deleteDockResponse = uddiServiceUtil.deleteDock(request.getStationId(), request.getDockId());
        response.setResponse(deleteDockResponse);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "alterStateDockInUpBikeRequest")
    @ResponsePayload
    public AlterStateDockInUpBikeResponse alterStateDockInUpBike(@RequestPayload AlterStateDockInUpBikeRequest request) {
        AlterStateDockInUpBikeResponse response = new AlterStateDockInUpBikeResponse();
        String alterStateResponse = uddiServiceUtil.alterStateDockInUpBike(request.getStationId(), request.getDockId(), request.getState());
        response.setResponse(alterStateResponse);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "alterStateDockInDownBikeRequest")
    @ResponsePayload
    public AlterStateDockInDownBikeResponse alterStateDockInDownBike(@RequestPayload AlterStateDockInDownBikeRequest request) {
        AlterStateDockInDownBikeResponse response = new AlterStateDockInDownBikeResponse();
        String alterStateResponse = uddiServiceUtil.alterStateDockInDownBike(request.getStationId(), request.getDockId(), request.getState(), request.getInfo());
        response.setResponse(alterStateResponse);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "levantarBicicletaRequest")
    @ResponsePayload
    public LevantarBicicletaResponse levantarBicicleta(@RequestPayload LevantarBicicletaRequest request) {
        LevantarBicicletaResponse response = new LevantarBicicletaResponse();
        String levantarBicicletaResponse = uddiServiceUtil.levantarBicicleta(request.getStationId(), request.getDockId());
        response.setResponse(levantarBicicletaResponse);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "entregarBicicletaRequest")
    @ResponsePayload
    public EntregarBicicletaResponse entregarBicicleta(@RequestPayload EntregarBicicletaRequest request) {
        EntregarBicicletaResponse response = new EntregarBicicletaResponse();
        String entregarBicicletaResponse = uddiServiceUtil.entregarBicicleta(request.getStationId(), request.getDockId());
        response.setResponse(entregarBicicletaResponse);
        return response;
    }
}
