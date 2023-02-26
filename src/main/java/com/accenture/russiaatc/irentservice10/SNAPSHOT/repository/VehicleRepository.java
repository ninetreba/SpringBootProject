package com.accenture.russiaatc.irentservice10.SNAPSHOT.repository;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.Status;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.Transport;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.TransportStatus;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Transport, Long> {
    List<Transport> findByType(Type type);
    List<Transport> findByCurrentParking_Id(Long id);
    List<Transport> findByStatus(Status status);
    List<Transport> findByTransportStatusAndStatus(TransportStatus transportStatus, Status status);
    Optional<Transport> findByNumber(String number);

}


