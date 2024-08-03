package com.uan.bikeshared.application;

import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import com.uan.interfaces.*;

@Service
public class StationServiceClient {

    private final WebServiceTemplate webServiceTemplate;
    private final UDDIServiceUtil uddiServiceUtil;

    public StationServiceClient(WebServiceTemplate webServiceTemplate, UDDIServiceUtil uddiServiceUtil) {
        this.webServiceTemplate = webServiceTemplate;
        this.uddiServiceUtil = uddiServiceUtil;
    }

    public GetStationResponse getStation(String stationName) {
        String stationUrl = uddiServiceUtil.getStationUrl(stationName);
        webServiceTemplate.setDefaultUri(stationUrl);

        GetStationRequest request = new GetStationRequest();
        request.setStationName(stationName);
        return (GetStationResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public LockBikeResponse lockBike(Long stationId, Long dockId) {
        LockBikeRequest request = new LockBikeRequest();
        request.setStationId(stationId);
        request.setDockId(dockId);
        return (LockBikeResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public UnlockBikeResponse unlockBike(Long stationId, Long dockId) {
        UnlockBikeRequest request = new UnlockBikeRequest();
        request.setStationId(stationId);
        request.setDockId(dockId);
        return (UnlockBikeResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public AddDockResponse addDock(Long stationId, DockType dock) {
        AddDockRequest request = new AddDockRequest();
        request.setStationId(stationId);
        request.setDock(dock);
        return (AddDockResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public DeleteDockResponse deleteDock(Long stationId, Long dockId) {
        DeleteDockRequest request = new DeleteDockRequest();
        request.setStationId(stationId);
        request.setDockId(dockId);
        return (DeleteDockResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public AlterStateDockInUpBikeResponse alterStateDockInUpBike(Long stationId, Long dockId, int state) {
        AlterStateDockInUpBikeRequest request = new AlterStateDockInUpBikeRequest();
        request.setStationId(stationId);
        request.setDockId(dockId);
        request.setState(state);
        return (AlterStateDockInUpBikeResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public AlterStateDockInDownBikeResponse alterStateDockInDownBike(Long stationId, Long dockId, int state, String info) {
        AlterStateDockInDownBikeRequest request = new AlterStateDockInDownBikeRequest();
        request.setStationId(stationId);
        request.setDockId(dockId);
        request.setState(state);
        request.setInfo(info);
        return (AlterStateDockInDownBikeResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public LevantarBicicletaResponse levantarBicicleta(Long stationId, Long dockId) {
        LevantarBicicletaRequest request = new LevantarBicicletaRequest();
        request.setStationId(stationId);
        request.setDockId(dockId);
        return (LevantarBicicletaResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public EntregarBicicletaResponse entregarBicicleta(Long stationId, Long dockId) {
        EntregarBicicletaRequest request = new EntregarBicicletaRequest();
        request.setStationId(stationId);
        request.setDockId(dockId);
        return (EntregarBicicletaResponse) webServiceTemplate.marshalSendAndReceive(request);
    }
}
