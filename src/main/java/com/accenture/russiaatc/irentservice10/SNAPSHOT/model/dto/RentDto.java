package com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.rent.StatusRent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class RentDto {
    private Long id;
    private StatusRent statusRent;
    private BigDecimal cost;

    private UserDto userDto;
    private TransportDto transportDto;
    private ParkingDto startParkingDto;
    private ParkingDto endParkingDto;

    private LocalDateTime startRent;
    private LocalDateTime endRent;

}
