package com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CloseRentDto {
    private Long idTransport;
    private Long idUser;
    private Long idParking;
}
