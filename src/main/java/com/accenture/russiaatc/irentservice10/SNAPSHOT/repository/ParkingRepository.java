package com.accenture.russiaatc.irentservice10.SNAPSHOT.repository;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.Status;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.parking.Parking;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {
    @Override
    Optional<Parking> findById(Long aLong);
    Optional<Parking> findByName(String name);
    List<Parking> findAllByStatus(Status status);
}
