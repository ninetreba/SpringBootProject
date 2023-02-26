package com.accenture.russiaatc.irentservice10.SNAPSHOT.repository;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.rent.Rent;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.rent.StatusRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Rent, Long> {
    Rent findByUser_IdAndStatusRentAndTransport_Id(Long idUser, StatusRent statusRent, Long idTransport);
    Integer countByUser_IdAndStatusRent(Long idUser, StatusRent statusRent);
    List<Rent> findByUser_Id(Long id);
    List<Rent> findAllByUser_Id(Long id);
}
