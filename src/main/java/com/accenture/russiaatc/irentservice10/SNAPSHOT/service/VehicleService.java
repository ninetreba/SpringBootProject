package com.accenture.russiaatc.irentservice10.SNAPSHOT.service;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.Status;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto.CreateTransportDto;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.Transport;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.TransportStatus;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.Type;

import java.util.List;

public interface VehicleService {

    List<Transport> getVehicles();
    Transport deleteTransport(Long id);
    List<Transport> findByType(Type type);
    List<Transport> findByParking(Long id);
    List<Transport> findByStatus(Status status);
    List<Transport> findByTransportStatusAndStatus(TransportStatus transportStatus, Status status);
    Transport createTransport(CreateTransportDto createTransportDto);
    Transport getById(Long id);
    void save(Transport transport);
    Transport findByNumber(String number);

}


